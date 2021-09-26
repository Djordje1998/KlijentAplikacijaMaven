/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.Komponenta;
import domen.Korisnik;
import domen.Racunar;
import domen.StavkaRacunara;
import forme.util.ParamConstants;
import java.util.Date;
import javax.swing.table.AbstractTableModel;
import kordinator.MainCordinator;

/**
 *
 * @author DarkForce
 */
public class TabelModelStavkeRacunara extends AbstractTableModel {
    
    private Racunar racunar;
    String[] kolone = {"RB", "Naziv", "Tip", "Cena", "Kolicina", "Ukupno"};
    
    public TabelModelStavkeRacunara(Racunar racunar) {
        this.racunar = racunar;
    }
    
    @Override
    public int getRowCount() {
        return racunar.getStavke().size();
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
        StavkaRacunara stavka = racunar.getStavke().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return stavka.getRedniBroj();
            case 1:
                return stavka.getKomponenta().getNazivKomponente();
            case 2:
                return stavka.getKomponenta().getTipKomponente().getNaziv();
            case 3:
                return stavka.getCena();
            case 4:
                return stavka.getKolicina();
            case 5:
                return stavka.getUkupnaCena();
            default:
                return "error";
        }
    }
    
    public void dodajStavku(Komponenta komponenta, double cena, int kolicina) {
        
        for (StavkaRacunara stavka : racunar.getStavke()) {
            if (stavka.getKomponenta().getKomponentaId() == komponenta.getKomponentaId() && stavka.getCena() == cena) {
                stavka.setKolicina(stavka.getKolicina() + kolicina);
                stavka.setUkupnaCena(stavka.getUkupnaCena() + cena * kolicina);
                racunar.setUkupnaCena(racunar.getUkupnaCena() + cena * kolicina);
                fireTableDataChanged();
                return;
            }
        }
        
        StavkaRacunara stavka = new StavkaRacunara();
        stavka.setCena(cena);
        stavka.setDatumKreiranja(new Date());
        stavka.setKolicina(kolicina);
        stavka.setKomponenta(komponenta);
        stavka.setKreirao((Korisnik) MainCordinator.getInstance().getParam(ParamConstants.ULOGOVANI_KORISNIK));
        stavka.setRacunar(racunar);
        stavka.setRedniBroj(racunar.getStavke().size() + 1);
        stavka.setUkupnaCena(cena * kolicina);
        
        racunar.getStavke().add(stavka);
        racunar.setUkupnaCena(racunar.getUkupnaCena() + stavka.getUkupnaCena());
        fireTableDataChanged();
    }
    
    public void obrisiStavku(int red) {
        StavkaRacunara stavka = racunar.getStavke().get(red);
        racunar.getStavke().remove(red);
        racunar.setUkupnaCena(racunar.getUkupnaCena() - stavka.getUkupnaCena());
        srediRedosled();
        fireTableDataChanged();
    }
    
    public StavkaRacunara vratiStavku(int red) {
        return racunar.getStavke().get(red);
    }
    
    public void izmeniStavku(int redniBroj, Komponenta komponenta, double cena, int kolicina) {
        for (StavkaRacunara stavka : racunar.getStavke()) {
            if (stavka.getRedniBroj() == redniBroj) {
                racunar.setUkupnaCena(racunar.getUkupnaCena() - stavka.getUkupnaCena());
                stavka.setKomponenta(komponenta);
                stavka.setCena(cena);
                stavka.setKolicina(kolicina);
                stavka.setUkupnaCena(cena * (double) kolicina);
                racunar.setUkupnaCena(racunar.getUkupnaCena() + stavka.getUkupnaCena());
                fireTableDataChanged();
            }
        }
    }
    
    private void srediRedosled() {
        int rb = 0;
        for (StavkaRacunara stavka : racunar.getStavke()) {
            stavka.setRedniBroj(++rb);
        }
    }
    
    public Racunar getRacunar() {
        return racunar;
    }
    
    public void setRacunar(Racunar racunar) {
        this.racunar = racunar;
        fireTableDataChanged();
    }
    
    public double getUkupnaCena() {
        return (double) racunar.getUkupnaCena();
    }
    
}
