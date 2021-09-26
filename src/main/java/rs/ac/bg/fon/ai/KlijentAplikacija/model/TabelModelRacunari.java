/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.Racunar;
import domen.StavkaRacunara;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DarkForce
 */
public class TabelModelRacunari extends AbstractTableModel {

    private ArrayList<Racunar> racunari;
    private final String[] kolone = {"ID", "Naziv", "Garancija", "Cena","Br. komponenti"};
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public TabelModelRacunari() {
        racunari = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return racunari.size();
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
        Racunar racunar = racunari.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return racunar.getRacunarID();
            case 1:
                return racunar.getNazivRacunara();
            case 2:
                return sdf.format(racunar.getGarancija());
            case 3:
                return racunar.getUkupnaCena();
            case 4:
                int suma =0;
                for (StavkaRacunara stavka : racunar.getStavke()) {
                    suma+=stavka.getKolicina();
                }
                return suma;
            default:
                return "Error";
        }
    }
    
    
    
    public Racunar getRacunarZaBrisanje(int row) {
        Racunar racunar = racunari.get(row);
        racunari.remove(row);
        fireTableDataChanged();
        return racunar;
    }

    public void setRacunari(ArrayList<Racunar> racunari) {
        this.racunari = racunari;
        fireTableDataChanged();
    }

    public void dodajRacunar(Racunar racunar) {
        racunari.add(racunar);
        fireTableDataChanged();
    }

    public Racunar getRacunar(int row) {
        return racunari.get(row);
    }

}
