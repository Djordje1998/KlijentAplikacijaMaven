/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.KlijentAplikacija.model;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Komponenta;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DarkForce
 */
public class TabelModelKomponente extends AbstractTableModel {

    private ArrayList<Komponenta> komponente;
    private final String[] kolone = {"ID", "Naziv", "Tip", "Takt", "Opis", "Cena"};

    public TabelModelKomponente() {
        komponente = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return komponente.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Komponenta komponenta = komponente.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return komponenta.getKomponentaId();
            case 1:
                return komponenta.getNazivKomponente();
            case 2:
                return komponenta.getTipKomponente().getNaziv();
            case 3:
                return komponenta.getTakt();
            case 4:
                return komponenta.getOpis();
            case 5:
                return komponenta.getCena();

            default:
                return "Error";
        }
    }

    public ArrayList<Komponenta> getKomponente() {
        return komponente;
    }

    public void setKomponente(ArrayList<Komponenta> komponente) {
        this.komponente = komponente;
        fireTableDataChanged();
    }

    public Komponenta getKomponentaZaBrisanje(int row) {
        Komponenta komponenta = komponente.get(row);
        komponente.remove(row);
        fireTableDataChanged();
        return komponenta;
    }

    public void dodajKomponentu(Komponenta komponentaZaIzmenu) {
        komponente.add(komponentaZaIzmenu);
        fireTableDataChanged();
    }

    public Komponenta getKomponenta(int row) {
        return komponente.get(row);
    }

}
