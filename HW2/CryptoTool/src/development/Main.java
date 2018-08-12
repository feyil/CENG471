package development;


import asymmetrical.*;
import symmetrical.*;


public class Main {
	
	public static void main(String[] args) throws Exception {
			 
		 int option = 2;
		 
		 if(option == 0) {
			 //ElGamal 
			 ElGamal.main(null);
		 }
		 
		 else if(option == 1) {
			 
			 RSA.main(null);
			 			 
		 }
		 
		 else if(option == 2) {
			 //DES
			 
			 String[] argu = new String[2];
			 argu[0] = "1";
			 
			 KeyExchange.main(argu);
		 }
		 
		 else if(option == 3) {
			 //AES
			 
			 BlumBlumShub.DEFAULT_BIT_SIZE = 128;
			 String[] argu = new String[2];
			 argu[0] = "0";
			 
			 KeyExchange.main(argu);
		 }
		 else {
			 
			 System.out.println("Invalid option!!");
		 }
		  
		
	}

}
