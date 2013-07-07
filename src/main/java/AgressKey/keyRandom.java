package AgressKey;

import java.util.Random;

public class keyRandom {
	public static String CharRandom = "qwertyuiopasdfghjklzxcvbnm";
	
	public static String key() {
		return random()+number()+random()+number()+"-"+number()+number()+random()+random()+"-"+random()+random()+random()+random();
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
