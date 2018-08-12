package development;

import java.math.BigInteger;
import java.util.ArrayList;
import java.security.SecureRandom;

public class CryptoTool {
	
	public CryptoTool() {
		
		System.out.println("CryptoTool Created");
	}
	
	public BigInteger createNumber(String number) {
		
		BigInteger bigNumber = new BigInteger(number);
		
		return bigNumber;
		
	}
	
	public BigInteger getSecureRandom(int bound) {
	
		//I prefer to getInt but it can be extended with nextBytes to implement random BigInteger
		SecureRandom secure = new SecureRandom();
		BigInteger bigRandom = new BigInteger(Integer.toString(secure.nextInt(bound)));
	
		return bigRandom;
		
	}
	
	public ArrayList<BigInteger> extendedGCD(BigInteger aBig, BigInteger bBig) {
		//It directly implements extended Euclidean algorithm
		
		BigInteger x = BigInteger.ZERO;
		BigInteger y = BigInteger.ONE;
		BigInteger u = BigInteger.ONE;
		BigInteger v = BigInteger.ZERO;
		
		while(!(aBig.equals(BigInteger.ZERO))) {
		
			BigInteger q = bBig.divide(aBig);
			BigInteger r = bBig.mod(aBig);
			
			BigInteger m = x.subtract(u.multiply(q));
			BigInteger n = y.subtract(v.multiply(q));
			
			bBig = aBig;
			aBig = r;
			x = u;
			y = v;
			u = m;
			v = n;
			
		}
		
		ArrayList<BigInteger> result = new ArrayList<BigInteger>();
		result.add(bBig);
		result.add(x);
		result.add(y);
		//It returns GCD,x,y in the ArrayList<BigInteger> structures
		return result;
	}
	
	public boolean isRelativePrime(BigInteger a, BigInteger b) {
		
		//Solve the equation ax+by=1 if it is equal one they are relatively prime
		
		if(extendedGCD(a, b).get(0).equals(BigInteger.ONE)) {
			return true;
		}
		return false;
	}
	
	public BigInteger multiplicativeInverse(BigInteger a, BigInteger p) {

		//Based on presentations it solves ax + py = 1
		ArrayList<BigInteger> result = extendedGCD(a, p);
		if(result.get(0).equals(BigInteger.ONE)) {
			return result.get(1);
		}
		else {
			//throw new ArithmeticException("No multiplicative Inverse");
			System.out.println("There is not a multiplicative inverse for given input");
		}
		return null;
	}
	
	public BigInteger chineseRemainder(ArrayList<BigInteger> aList, ArrayList<BigInteger> mList) {
		//It solves for x = a1(modm1) x=a2(modm2) ...
		//It implements given algorithm in presentation
		
		BigInteger mTotal = BigInteger.ONE;
		
		for(int i=0; i<mList.size(); i++) {
			mTotal = mTotal.multiply(mList.get(i));
		}
		
		BigInteger remainder = BigInteger.ZERO;
		
		for(int i=0; i<mList.size(); i++) {
			try {
				remainder = remainder.add(mTotal.divide(mList.get(i)).multiply(aList.get(i).multiply(multiplicativeInverse((mTotal.divide(mList.get(i))),mList.get(i)))));
			}
			catch(Exception e) {
				System.out.println("There is no solution for given input!!");
			}
		}
		
		BigInteger result = remainder.mod(mTotal);
		
		return result;
	}
	
	// This function is called for all k trials. 
    // It returns false if n is composite and 
    // returns false if n is probably prime.
    // d is an odd number such that d*2<sup>r</sup>
    // = n-1 for some r >= 1
    public boolean miillerTest(BigInteger n) {
         
        // Pick a random number in [2..n-2]
        // Corner cases make sure that n > 4
        BigInteger a = getSecureRandom(64).add(new BigInteger("2"));
     
        int counter = 0;
		BigInteger temp = n.subtract(BigInteger.ONE);
		while((temp.mod(new BigInteger("2"))).equals(BigInteger.ZERO)) {
			counter++;
			temp = temp.divide(new BigInteger("2"));
		}
		
		BigInteger d = n.subtract(BigInteger.ONE).divide((new BigInteger("2").pow(counter)));
        
		BigInteger x = a.modPow(d, n);
     
        if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE)))
            return true;
     
        // Keep squaring x while one of the
        // following doesn't happen
        // (i) d does not reach n-1
        // (ii) (x^2) % n is not 1
        // (iii) (x^2) % n is not n-1
        while (d.equals(n.subtract(BigInteger.ONE)) == false) {
        	
        	x = x.multiply(x).mod(n);
           
            d = d.multiply(new BigInteger("2"));
        
            if (x.equals(BigInteger.ONE))
                return false;
            if (x.equals(n.subtract(BigInteger.ONE)))
                return true;
        }
     
        // Return composite
        return false;
    }
     
    // It returns false if n is composite 
    // and returns true if n is probably 
    // prime. k is an input parameter that 
    // determines accuracy level. Higher 
    // value of k indicates more accuracy.
    public boolean isPrime(BigInteger n) {
         
        // Corner cases
        if (n.compareTo(BigInteger.ONE) ==-1 || n.equals(new BigInteger("4")))
            return false;
        if (n.compareTo(new BigInteger("3")) == -1)
            return true;
     
        // Find r such that n = 2^d * r + 1 
        // for some r >= 1
        BigInteger d = n.subtract(BigInteger.ONE);
         
        while (d.mod(new BigInteger("2")).equals(BigInteger.ZERO))
        	d = d.divide(new BigInteger("2"));
        
        int k = 10; //precision
        for (int i = 0; i < k; i++)
 
            if (miillerTest(n) == false)
                return false;
    
        return true;
    }
    /* This code is contributed by Nikita Tiwari.
     * 
     * This isPrime and millerTest not my design I have tried to implement it 
     * but somehow I made mistakes and spend a lot of time on it. Therefore, I took it
     * and convert the design compatible with BigInteger class.
     * 
     * */
    
}
