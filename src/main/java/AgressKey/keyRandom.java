package AgressKey;

import java.util.Random;

public class keyRandom {
	public static String CharRandom = "qwertyuiopasdfghjklzxcvbnm";

	public static String key(String s) {
	    StringBuilder a = new StringBuilder();
	    
	    for (char c: s.toCharArray()) {
	    	int f = 1;
	    	if(String.valueOf(c).contains("A")) {
	    		a.append(random());
	    	} else if(String.valueOf(c).contains("0")) {
	    		a.append(number());
	    	} else {
	    		a.append(String.valueOf(c));

	    	}
	    }
	    return a.toString();
		}

	public static String number() {
		Random random = new Random();
		return Integer.toString(random.nextInt(9));
	}
	
	public static String random() {
		Random random = new Random();
		return String.valueOf(CharRandom.charAt(random.nextInt(CharRandom.length())));

		
	}
}
