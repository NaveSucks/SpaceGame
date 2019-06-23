package scoreSystem;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * Hochschule Flensburg
 * Vorlesung Objektorientierte Programmierung
 * Medieninformatik Sommersemester 2019
 * @author Arnold Willemer
 */

public class HighModel implements TableModel {

	@Override
	public Class<?> getColumnClass(int arg0) {
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return 2; // es wird zwei Spalten geben
	}

	/**
	 * Ueberschriften fuer die Tabelle
	 */
	@Override
	public String getColumnName(int col) {
		if (col==0) {
			return "Spieler";
		} else if (col==1) {
			return "Sekunden";
		}
		return null;
	}

	/**
	 * Anzahl der Zeilen - ergibt sich aus der ArrayList-Groesse
	 */
	@Override
	public int getRowCount() {
		return HighScore.getInstance().size();
	}

	/**
	 * Fuellen der Tabelle
	 */
	@Override
	public Object getValueAt(int row, int col) {
		Score score = HighScore.getInstance().get(row);
		if (col==0) {
			return score.getName();
		} else if (col==1){
			return ""+score.getSek();
		}
		return null;
	}

	// ---------- folgende Methoden sind nicht relevant -------
	@Override
	public void addTableModelListener(TableModelListener arg0) {
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
	}
}
