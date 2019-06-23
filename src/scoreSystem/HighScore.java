package scoreSystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Properties;

/**
 * Hochschule Flensburg
 * Vorlesung Objektorientierte Programmierung
 * Medieninformatik Sommersemester 2019
 * @author Arnold Willemer
 */

public class HighScore extends ArrayList<Score> {

	// Dateiname der Highscore-Datei
	private static String FILE = "highscore.cfg";
	
	// Singleton-Instanz
	private static HighScore instance = null;
	
	// Singleton, also privater Konstruktor
	private HighScore() {
	}
	
	// Singleton: static-Methode liefert einzige Instanz
	public static HighScore getInstance() {
		if (instance==null) {
			instance = new HighScore();
			instance.load();
		}
		return instance;
	}
	
	/**
	 * Die Methode add wird überschrieben, um die Liste
	 * sortieren zu koennen.
	 */
	@Override
	public boolean add(Score score) {
		boolean ok = super.add(score);
		// Aufruf der Collection Frame sort-Methode. Diese benoetigt
		// als Parameter einen Comparator, da sich nicht von selbst
		// ergibt, wie ein Score zu sortieren ist.
		this.sort(new Comparator<Score>() {

			// Die Methode compare legt die Reihenfolge fest, indem sie
			// beim Vergleich zweier Scores -1, 0 oder +1 zurueckgibt.
			@Override
			public int compare(Score arg0, Score arg1) {
				int erg = 0;
				// Die Sortierreihenfolge ergibt sich aus den Sekunden
				if (arg0.getSek() < arg1.getSek())
					erg = -1;
				else if (arg0.getSek() > arg1.getSek())
					erg = 1;
				return erg;
			}
		});
		return ok;		
	}
	
	/**
	 * Speichern der Highscore-Tabelle
	 */
	public void store() {
		// Die ArrayList fuellt die Properties
		Properties prop = new Properties();
		for (int i=0; i<HighScore.getInstance().size(); i++) {
			Score score = HighScore.getInstance().get(i);
			prop.setProperty(""+i, score.toString());
		}
		// ab damit in die Datei
		try {
			prop.store(new FileOutputStream(FILE), "huhu");
		} catch (IOException e) {
			e.printStackTrace(); // gibt eine Fehlermeldung in der Konsole
		}
	}

	/**
	 * Lade die Tabelle aus der Datei
	 */
	public void load() {
		instance.clear();	// Bisherige Tabelle leeren

		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(FILE)); // lese Datei
			// Hole alle Elemente über die Schluesselmenge
			for (Object obj : prop.keySet()) {
				// Schlueesel und Wert einlesen
				String key = (String) obj;
				String wert = prop.getProperty(key);
				if (wert!=null) {
					Score score = new Score(wert);
					add(score);					
				}
			}
		} catch (FileNotFoundException e) {
			// Alles halb so wild. Die Datei gibt es noch nicht.
		} catch (IOException e) {
			e.printStackTrace(); // gibt eine Fehlermeldung in der Konsole
		}
	}
}
