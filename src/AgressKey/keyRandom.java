package AgressKey;

import java.util.ArrayList;
import java.util.Random;

public class keyRandom {
	public static ArrayList b = new ArrayList();
	
	
	public static String key() {
		return random()+number()+random()+number()+"-"+number()+number()+random()+random()+"-"+random()+random()+random()+random();
		}

		public static String number() {
			Random random = new Random();
			return Integer.toString(random.nextInt(9));
		}

	
	public static String random() {
		b.add("q");
		b.add("w");
		b.add("e");
		b.add("r");
		b.add("y");
		b.add("u");
		b.add("i");
		b.add("o");
		b.add("p");
		b.add("a");
		b.add("s");
		b.add("d");
		b.add("f");
		b.add("g");
		b.add("h");
		b.add("j");
		b.add("k");
		b.add("l");
		b.add("z");
		b.add("x");
		b.add("c");
		b.add("v");
		b.add("b");
		b.add("n");
		b.add("m");
		Random random = new Random();

		return (String) b.get(random.nextInt(25));
		
	}
}
