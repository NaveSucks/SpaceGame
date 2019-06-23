package scoreSystem;

import static java.lang.Double.valueOf;

public class Score {

	private String spieler;
	private String sek;
	
	public Score(String spieler, String sek) {
		this.spieler = spieler;
		this.sek = sek;
	}
	
	public Score(String string) {
		String[] str = string.split(":");
		spieler = str[0];
		sek = str[1];
	}

	public String toString() {
		System.out.println(sek + "");
		return spieler + ":" + sek;
	}

	public String getName() {
		return spieler;
	}
	
	public double getSek() {
		return valueOf(sek);
	}
}
