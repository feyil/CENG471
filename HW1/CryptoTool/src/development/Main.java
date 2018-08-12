package development;

import java.math.BigInteger;
import java.util.ArrayList;


public class Main {
	
	public static void main(String[] args) {
		
		CryptoTool test = new CryptoTool();
		
		System.out.println("Test Cases for each module.");
		
		System.out.println("Test extended Euclidean algorithm");
		
		BigInteger a1 = new BigInteger("1259"); 
		a1 = a1.multiply(new BigInteger("1277")).multiply(new BigInteger("1279")).multiply(new BigInteger("1283"));
		
		BigInteger b1 = new BigInteger("1277"); 
		b1 = b1.multiply(new BigInteger("1283")).multiply(new BigInteger("1759")).multiply(new BigInteger("2633"));
		
		
		System.out.println("1-Given numbers:" + a1 + "," + b1);
		System.out.println("Result:" + test.extendedGCD(a1, b1));
		
		BigInteger a2 = new BigInteger("491"); 
		a2 = a2.multiply(new BigInteger("499")).multiply(new BigInteger("3449"));
		
		BigInteger b2 = new BigInteger("499"); 
		b2 = b2.multiply(new BigInteger("3769")).multiply(new BigInteger("3793"));
		
		System.out.println("2-Given numbers:"+ a2 + "," + b2);
		System.out.println("Result:" + test.extendedGCD(a2, b2));
		
		BigInteger a3 = new BigInteger("4943"); 
		a3 = a3.multiply(new BigInteger("5779")).multiply(new BigInteger("5683"));
		
		BigInteger b3 = new BigInteger("5683"); 
		b3 = b3.multiply(new BigInteger("4943")).multiply(new BigInteger("557")).multiply(new BigInteger("467"));
		
		System.out.println("3-Given numbers:" + a3 + "," + b3);
		System.out.println("Result:" + test.extendedGCD(a3, b3));
		
		BigInteger a4 = new BigInteger("293"); 
		a4 = a4.multiply(new BigInteger("307")).multiply(new BigInteger("275")).multiply(new BigInteger("5")).multiply(new BigInteger("3")).multiply(new BigInteger("2"));
		
		BigInteger b4 = new BigInteger("5"); 
		b4 = b4.multiply(new BigInteger("275")).multiply(new BigInteger("31")).multiply(new BigInteger("7")).multiply(new BigInteger("11"));
		
		System.out.println("4-Given numbers:" + a4 + "," + b4);
		System.out.println("Result:" + test.extendedGCD(a4, b4));
		System.out.println();
		
		System.out.println("Test the multiplicative inverse:");
		
		BigInteger mi1 = new BigInteger("3");
		BigInteger mi2 = new BigInteger("7");
		
		System.out.println("1-Given numbers:" + mi1 + "," + mi2);
		System.out.println("Result:" + test.multiplicativeInverse(mi1, mi2));
		
		BigInteger mi3 = new BigInteger("2");
		BigInteger mi4 = new BigInteger("6");
		
		System.out.println("2-Given numbers:" + mi3 + "," + mi4);
		System.out.println("Result:" + test.multiplicativeInverse(mi3, mi4));
		
		BigInteger mi5 = new BigInteger("11111");
		BigInteger mi6 = new BigInteger("12345");
		
		System.out.println("3-Given numbers:" + mi5 + "," + mi6);
		System.out.println("Result:" + test.multiplicativeInverse(mi5, mi6));
		System.out.println();
		
		System.out.println("Test the relatively prime condition");
		
		BigInteger rp1 = new BigInteger("58021664585639791181184025950440248398226136069516938232493687505822471836536824298822733710342250697739996825938232641940670857624514103125986134050997697160127301547995788468137887651823707102007839");
		BigInteger rp2 = new BigInteger("40992408416096028179761232532587525402909285099086220133403920525409552083528606215439915948260875718893797824735118621138192569490840098061133066650255608065609253901288801302035441884878187944219033");
		
		System.out.println("1-Given numbers:\n " + rp1 + ",\n" + rp2);
		System.out.println("Result:" + test.isRelativePrime(rp1, rp2));
		
		BigInteger rp3 = new BigInteger("2555480246614015637242066218495468367958491958791520916609927980929585688386540022963356429758954681777165591120866518535295021508514987925230304828573354059077193219265153006831360318167711");
		BigInteger rp4 = new BigInteger("2");
		
		System.out.println("2-Given numbers:\n " + rp3 + ",\n" + rp4);
		System.out.println("Result:" + test.isRelativePrime(rp3, rp4));
		
		System.out.println("3-Given numbers:" + a1 + "," + b1);
		System.out.println("Result:" + test.isRelativePrime(a1, b1));
		System.out.println();
		
		System.out.println("Test Chinese Remainder Theorem");
		
		ArrayList<BigInteger> ca = new ArrayList<BigInteger>();
		ArrayList<BigInteger> cm = new ArrayList<BigInteger>();
	 
		ca.add(new BigInteger("1"));
		ca.add(new BigInteger("4"));
		ca.add(new BigInteger("6"));
			  
		cm.add(new BigInteger("3"));
		cm.add(new BigInteger("5"));
		cm.add(new BigInteger("7"));
		
		System.out.println("1-Given congurences:" + ca + "," + cm);
		System.out.println("Result x:" + test.chineseRemainder(ca, cm));
		
		ArrayList<BigInteger> ca2 = new ArrayList<BigInteger>();
		ArrayList<BigInteger> cm2 = new ArrayList<BigInteger>();
	 
		ca2.add(new BigInteger("2"));
		ca2.add(new BigInteger("5"));
		ca2.add(new BigInteger("7"));
			  
		cm2.add(new BigInteger("6"));
		cm2.add(new BigInteger("9"));
		cm2.add(new BigInteger("15"));
		
		System.out.println("2-Given congurences:" + ca2 + "," + cm2);
		System.out.println("Result x:" + test.chineseRemainder(ca2, cm2));
		
		System.out.println("Test Primarility Test Algorithm");
		
		BigInteger pt1 = new BigInteger("54661163828798316406139641599131347203445399912295442826728168170210404446004717881354193865401223990331513412680314853190460368937597393179445867548835085746203514200061810259071519181681661892618329");
		BigInteger pt2 = new BigInteger("54661163828798316406139641599131347203445399912295442826728168170210404446004717881354193865401223990331513412680314853190460368837597393179445867548835085746203514200061810259071519181681661892618329");
		BigInteger pt3 = new BigInteger("5026229637416821096651957347055990616313190018857502650953480553921800879342828248572616370546407789646201959941290047780315122634922833439742729257401674232444211439320664156608343988857391");
		
		System.out.println("1-Given number:" + pt1);
		System.out.println("Result:" + test.isPrime(pt1));
		
		System.out.println("2-Given number:" + pt2);
		System.out.println("Result:" + test.isPrime(pt2));
		
		System.out.println("3-Given number:" + pt3);
		System.out.println("Result:" + test.isPrime(pt3));
		  
		
	}

}
