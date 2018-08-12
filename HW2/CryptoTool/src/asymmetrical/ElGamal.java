package asymmetrical;

import java.math.BigInteger;

import development.CryptoTool;


public class ElGamal {
	public static void main(String[] args) throws Exception {
		ElGamal test = new ElGamal();
		String plainText = "Alice";
		test.showPrivateInfo();
		test.showPublicInfo();
		
		BigInteger cipher = test.encrypt(new BigInteger(plainText.getBytes()));
		BigInteger plain = test.decrypt(cipher);
		byte[] pl = plain.toByteArray();
		System.out.println(new String(pl));
			
	}

	private BigInteger cyclicGroup, primitiveRoot, privateKey, publicKey,
			sharedKey;

	public ElGamal() {
		generateNewData();
		setSharedKey(publicKey);
	}

	public BigInteger getCyclicGroup() {
		return cyclicGroup;
	}

	public BigInteger getPublicKey() {
		return publicKey;
	}

	public BigInteger getPrimitiveRoot() {
		return primitiveRoot;
	}

	public void setPublicInfo(BigInteger cyclicGroup, BigInteger primitiveRoot) {
		this.cyclicGroup = cyclicGroup;
		this.primitiveRoot = primitiveRoot;
		this.publicKey = primitiveRoot.modPow(privateKey,cyclicGroup);
	}

	public void setPrivateInfo(BigInteger privateKey) {
		this.privateKey = privateKey;
		this.publicKey = primitiveRoot.modPow(privateKey, cyclicGroup);
	
	}

	public void setSharedKey(BigInteger sharedKey) {
		this.sharedKey = sharedKey;
	}

	public BigInteger encrypt(BigInteger msg) throws Exception {
		// Make sure message is smaller than the group
		if (msg.compareTo(cyclicGroup) != -1) {
			throw new Exception("Message too large for public key!");
		}
		// Make sure public key is smaller than the group
		if (sharedKey.compareTo(cyclicGroup) != -1) {
			throw new Exception("Shared key too large for public n!");
		}
		// Make sure private key is smaller than the group
		if (privateKey.compareTo(cyclicGroup) != -1) {
			System.out.println(privateKey);
			throw new Exception("Private key too large for public n!");
		}

		BigInteger encryptionKey = sharedKey.modPow(privateKey, cyclicGroup);
		return msg.multiply(encryptionKey).mod(cyclicGroup);
	}

	public BigInteger decrypt(BigInteger msg) {
		BigInteger decryptionKey = sharedKey.modPow(cyclicGroup.subtract(BigInteger.ONE).subtract(privateKey),cyclicGroup);
		return msg.multiply(decryptionKey).mod(cyclicGroup);
	}

	public void showPrivateInfo() {
		System.out.println();
		System.out.println("Private info:" + "\n\tl (private key) = "
				+ privateKey);
		System.out.println();
	}


	public void showPublicInfo() {
		System.out.println();
		System.out.println("Public info:" + "\n\tp (cyclic group) = "
				+ cyclicGroup + "\n\tb (primitive root) = " + primitiveRoot
				+ "\n\tmy public key = " + publicKey);
		System.out.println();
	}

	private BigInteger generateNewPrivateKey() {
		return CryptoTool.randomBigInteger(new BigInteger("2"),
				cyclicGroup.subtract(BigInteger.ONE));
	}
	
	public void generateNewData() {
		boolean failed = true;
		while (failed) {
			cyclicGroup = BlumBlumShub.randomStrongPrime();
			privateKey = generateNewPrivateKey();

			// Find a primitive root in the group
			try {
				primitiveRoot = PrimitiveRootSearch
						.primitiveRootSearch(cyclicGroup);

				// Public key is primitive root to the power of private key
				publicKey =  primitiveRoot.modPow(privateKey, cyclicGroup);
				failed = false;
			} catch (Exception e) {
				// Do nothing lets just generate another cyclic group
			}
		}
	}
}