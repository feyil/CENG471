package asymmetrical;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import development.CryptoTool;

public class PrimitiveRootSearch extends CryptoTool {
	
	public static BigInteger primitiveRootSearch(BigInteger p) throws Exception {
		if (p == null || ! isPrime(p))
			throw new Exception("Invalid p for primitive root search");

		// Find prime factors of p-1 once
		BigInteger n = p.subtract(BigInteger.ONE);
		Set<BigInteger> factors = findPrimeFactors(n);

		// Try to find the primitive root by starting at random number
		BigInteger g = randomBigInteger(new BigInteger("2"),
				n.subtract(BigInteger.ONE));
		while (!checkPrimitiveRoot(g, p, n, factors)) {
			g = g.add(BigInteger.ONE);
		}
		
		return g;
	}

	private static boolean checkPrimitiveRoot(BigInteger g, BigInteger p,
			BigInteger n, Set<BigInteger> factors) {
		// Run g^(n / "each factor) mod p
		// If the is 1 mod p then g is not a primitive root
		Iterator<BigInteger> i = factors.iterator();
		while (i.hasNext()) {
			
			if (g.modPow(n.divide(i.next()), p).equals(BigInteger.ONE)) {
				return false;
			}
		}
		return true;
	}

	private static Set<BigInteger> findPrimeFactors(BigInteger n) {
		// Set is unique
		Set<BigInteger> factors = new HashSet<BigInteger>();
		for (BigInteger i = BigInteger.valueOf(2); i.compareTo(n) <= 0; i = i
				.add(BigInteger.ONE)) {
			while (n.mod(i).equals(BigInteger.ZERO)) {
				// Add y to factors and decrease n
				factors.add(i);
				n = n.divide(i);
				// This should speed things up a bit for very large numbers!
				if (isPrime(n))
					return factors;
			}
		}
		return factors;
	}
}
