/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Komponenta;
import domen.TipKomponente;
import forme.komponente.FrmKomponenta;
import forme.util.FrmModOtvaranja;
import forme.util.ParamConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import kont.Komunikacija;
import kordinator.MainCordinator;

/**
 *
 * @author DarkForce
 */
public class KomponentaController {

    private final FrmKomponenta frmKomponentaKreiranje;
    private final FrmModOtvaranja frmModOtvaranja;

    public KomponentaController(FrmKomponenta frmKomponentaKreiranje, FrmModOtvaranja frmModOtvaranja) {
        this.frmKomponentaKreiranje = frmKomponentaKreiranje;
        this.frmModOtvaranja = frmModOtvaranja;
        addActionListener();
    }

    public void openForm() {
        pripremiFormu();
        frmKomponentaKreiranje.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
        frmKomponentaKreiranje.setVisible(true);

    }

    private void pripremiFormu() {
        popuniTip();
        switch (frmModOtvaranja) {
            case KOMPONENTA_KREIRANJE:
                frmKomponentaKreiranje.getBtnSacuvajIzmene().setVisible(false);
                frmKomponentaKreiranje.getBtnZapamtiKomponentu().setVisible(true);
                frmKomponentaKreiranje.setTitle("Kreiranje komponenti");
                break;
            case KOMPONENTA_IZMENA:
                frmKomponentaKreiranje.getBtnSacuvajIzmene().setVisible(true);
                frmKomponentaKreiranje.getBtnZapamtiKomponentu().setVisible(false);
                frmKomponentaKreiranje.setTitle("Izmena komponenti");
                popuniFormuIzmena();
                break;
        }
    }

    private void popuniTip() {
        try {
            frmKomponentaKreiranje.getCmbTip().removeAllItems();
            frmKomponentaKreiranje.getCmbTip().setModel(new DefaultComboBoxModel(Komunikacija.getInstance().vratiTipoveKomponenti().toArray()));
            frmKomponentaKreiranje.getCmbTip().setSelectedIndex(-1);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmKomponentaKreiranje, ex.getMessage(), "Greska prilikom pripreme forme", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void popuniFormuIzmena() {
        Komponenta komponenta = (Komponenta) MainCordinator.getInstance().getParam(ParamConstants.KOMPONENTA_ZA_IZMENU);
        frmKomponentaKreiranje.getTxtNaziv().setText(komponenta.getNazivKomponente());
        frmKomponentaKreiranje.getCmbTip().setSelectedItem(komponenta.getTipKomponente());
        frmKomponentaKreiranje.getTxtTakt().setText(String.valueOf(komponenta.getTakt()));
        frmKomponentaKreiranje.getTxtOpis().setText(komponenta.getOpis());
        frmKomponentaKreiranje.getTxtCena().setText(komponenta.getCena() + "");

    }

    private void addActionListener() {
        frmKomponentaKreiranje.getBtnZapamtiKomponentu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnZapamtiKomponentu();
            }

            private void btnZapamtiKomponentu() {
                try {
                    Komponenta komponenta = komponentaSaForme();
                    Komunikacija.getInstance().kreirajKomponentu(komponenta);
                    JOptionPane.showMessageDialog(frmKomponentaKreiranje, "Uspesno kreirana komponenta!", "Kreirana komponenta", JOptionPane.INFORMATION_MESSAGE);
                    obrisiPolja();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmKomponentaKreiranje, ex.getMessage(), "Greska pri kreiranju komponentu", JOptionPane.ERROR_MESSAGE);
                }
            }

            private void obrisiPolja() {
                frmKomponentaKreiranje.getTxtNaziv().setText("");
                frmKomponentaKreiranje.getTxtOpis().setText("");
                frmKomponentaKreiranje.getTxtTakt().setText("");
                frmKomponentaKreiranje.getTxtCena().setText("");
                frmKomponentaKreiranje.getCmbTip().setSelectedIndex(-1);
                frmKomponentaKreiranje.getTxtNaziv().grabFocus();
            }
        });

        frmKomponentaKreiranje.getBtnSacuvajIzmene().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSacuvajIzmenu();
            }

            private void btnSacuvajIzmenu() {
                try {
                    Komponenta stara = (Komponenta) MainCordinator.getInstance().getParam(ParamConstants.KOMPONENTA_ZA_IZMENU);
                    Komponenta nova = komponentaSaForme();
                    nova.setKomponentaId(stara.getKomponentaId());
                    Komunikacija.getInstance().izmeniKomponentu(nova);
                    JOptionPane.showMessageDialog(frmKomponentaKreiranje, "Uspesno izmenjena komponenta!", "Izmenjena komponenta", JOptionPane.INFORMATION_MESSAGE);
                    frmKomponentaKreiranje.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmKomponentaKreiranje, ex.getMessage(), "Greska pri izmeni komponente", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
    }

    private Komponenta komponentaSaForme() throws Exception {
        TipKomponente tip = (TipKomponente) frmKomponentaKreiranje.getCmbTip().getSelectedItem();
        String naziv = frmKomponentaKreiranje.getTxtNaziv().getText().trim();
        String opis = frmKomponentaKreiranje.getTxtOpis().getText().trim();

        double takt = 0;
        try {
            takt = Double.parseDouble(frmKomponentaKreiranje.getTxtTakt().getText().trim());
        } catch (NumberFormatException e) {
            throw new Exception("Takt mora da bude broj!");
        }

        double cena = 0;
        try {
            cena = Double.parseDouble(frmKomponentaKreiranje.getTxtCena().getText().trim());
        } catch (NumberFormatException e) {
            throw new Exception("Cena mora da bude broj!");
        }
        
        return new Komponenta(0, naziv, tip, takt, opis, cena);
    }

}
