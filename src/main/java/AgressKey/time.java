package AgressKey;

public class time {
public String second5(int second) {
	String sec = null;
	switch(second) {
	case 0: sec = " секунд"; break;
	case 1: sec = " секунду"; break;
	case 5: sec = " секунд"; break;
	default: sec = " секунды"; break;
	}
	return sec;
	
}

}
