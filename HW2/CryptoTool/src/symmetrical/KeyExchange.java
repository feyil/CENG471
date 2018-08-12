package symmetrical;

import java.math.BigInteger;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import asymmetrical.*;
import development.CryptoTool;


public class KeyExchange {
	
	private BigInteger secretVal;
	public BigInteger publicVal;
	public BigInteger primeVal;
	public BigInteger primitiveVal;
	private BigInteger sharedKey;
	
	public KeyExchange() throws Exception {
		//System.out.println("KeyExchange");
	}
	
	public BigInteger createPrime() {
		primeVal = BlumBlumShub.randomStrongPrime();
		return primeVal;
	}
	
	public void setPrime(BigInteger p) {
		this.primeVal = p;
	}
	public BigInteger getPrime() {
		return primeVal;
	}
	
	public BigInteger findPrimitive() throws Exception {
		primitiveVal = PrimitiveRootSearch.primitiveRootSearch(primeVal);
		return primitiveVal;
	}
	
	public void setPrimitive(BigInteger g) {
		
		this.primitiveVal = g;
	}
	
	public BigInteger getPrimitive() {
		return primitiveVal;
	}
	public BigInteger createSecret() {
		secretVal = CryptoTool.randomBigInteger(15,15);
		return null;
	}
	public BigInteger getSecret() {
		
		return secretVal;
	}
	
	public BigInteger createPublic() {
		publicVal = primitiveVal.modPow(secretVal, primeVal);
		return publicVal;
	}
	
	public BigInteger getPublic() {
		return publicVal;
	}
	
	public BigInteger sharedSecret(BigInteger val) {
		sharedKey = val.modPow(secretVal, primeVal);
		return sharedKey;
		
	}
	
	public static void main(String[] args) throws Exception {
		
		KeyExchange alice = new KeyExchange();
		KeyExchange bob = new KeyExchange();
		
		BigInteger prime = alice.createPrime();
		BigInteger gen = alice.findPrimitive();
		
		bob.primeVal = prime;
		bob.primitiveVal = gen;
		
		BigInteger a = alice.createSecret();
		BigInteger alicePublic = alice.createPublic();
		
		BigInteger b = bob.createSecret();
		BigInteger bobPublic = bob.createPublic();
		
		//System.out.println(bob.sharedSecret(alicePublic));
		//System.out.println(alice.sharedSecret(bobPublic));
		
		
		// decode the base64 encoded string
		
		// rebuild key using SecretKeySpec
		
		
		String plainText = "I love BOB";
		if(args[0] == "0") {
			
			SecretKey secKey = new SecretKeySpec(bob.sharedSecret(alicePublic).toByteArray(), "AES"); 
			
			byte[] cipherText = AES.encryptText(plainText, secKey);
			String decryptedText = AES.decryptText(cipherText, secKey);
			System.out.println("Original Text:" + plainText);
			System.out.println("AES Key (Hex Form):"+AES.bytesToHex(secKey.getEncoded()));
			System.out.println("Encrypted Text (Hex Form):"+AES.bytesToHex(cipherText));
			System.out.println("Descrypted Text:"+decryptedText);
		}
		else {
			SecretKey secKey = new SecretKeySpec(bob.sharedSecret(alicePublic).toByteArray(), "DES"); 
			
			byte[] cipherText = DES.encryptText(plainText, secKey);
			String decryptedText = DES.decryptText(cipherText, secKey);
			System.out.println("Original Text:" + plainText);
			System.out.println("DES Key (Hex Form):"+DES.bytesToHex(secKey.getEncoded()));
			System.out.println("Encrypted Text (Hex Form):"+DES.bytesToHex(cipherText));
			System.out.println("Descrypted Text:"+decryptedText);
			
		}

	}

}
