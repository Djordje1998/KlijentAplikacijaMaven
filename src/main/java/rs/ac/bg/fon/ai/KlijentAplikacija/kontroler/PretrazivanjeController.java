/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.KlijentAplikacija.kontroler;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Komponenta;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Racunar;
import rs.ac.bg.fon.ai.KlijentAplikacija.forme.FrmPretrazivanje;
import rs.ac.bg.fon.ai.KlijentAplikacija.forme.util.FrmModOtvaranja;
import rs.ac.bg.fon.ai.KlijentAplikacija.forme.util.ParamConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ai.KlijentAplikacija.kont.Komunikacija;
import rs.ac.bg.fon.ai.KlijentAplikacija.kordinator.MainCordinator;
import rs.ac.bg.fon.ai.KlijentAplikacija.model.TabelModelKomponente;
import rs.ac.bg.fon.ai.KlijentAplikacija.model.TabelModelRacunari;

/**
 *
 * @author DarkForce
 */
public class PretrazivanjeController {

    private final FrmPretrazivanje frmPretrazivanje;
    private final FrmModOtvaranja frmModOtvaranja;

    public PretrazivanjeController(FrmPretrazivanje frmPretrazivanje, FrmModOtvaranja frmModOtvaranja) {
        this.frmPretrazivanje = frmPretrazivanje;
        this.frmModOtvaranja = frmModOtvaranja;
        addActionListeners();
    }

    public void openForm() {
        pripremiFormu();
        frmPretrazivanje.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
        frmPretrazivanje.setVisible(true);
    }

    private void addActionListeners() {
        frmPretrazivanje.getBtnObrisi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnObrisi();
            }

            private void btnObrisi() {
                int row = frmPretrazivanje.getTblKomponente().getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(frmPretrazivanje, "Morate selektovati red za brisanje", "Greska pri brisanju", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int izbor = JOptionPane.showConfirmDialog(frmPretrazivanje, "Da li zelite da obrisete selektovani element u tabeli?", "Brisanje iz baze!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (izbor == JOptionPane.NO_OPTION) {
                    return;
                }
                if (izbor == JOptionPane.YES_OPTION) {
                    switch (frmModOtvaranja) {
                        case KOMPONENTA_PRETRAZIVANJE:
                            try {
                                TabelModelKomponente tmk = (TabelModelKomponente) frmPretrazivanje.getTblKomponente().getModel();
                                Komponenta komponenta = tmk.getKomponentaZaBrisanje(row);
                                Komunikacija.getInstance().obrisiKomponentu(komponenta);
                                JOptionPane.showMessageDialog(frmPretrazivanje, "Uspesno obrisana komponenta " + komponenta.getNazivKomponente() + "!", "Uspesno brisanje", JOptionPane.INFORMATION_MESSAGE);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(frmPretrazivanje, "Greska pri brisanju komponente iz baze podataka\n" + ex.getMessage(), "Greska pri brisanju", JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        case RACUNAR_PRETRAZIVANJE:
                            try {
                                TabelModelRacunari tmr = (TabelModelRacunari) frmPretrazivanje.getTblKomponente().getModel();
                                Racunar racunar = tmr.getRacunarZaBrisanje(row);
                                Komunikacija.getInstance().obrisiRacunar(racunar);
                                JOptionPane.showMessageDialog(frmPretrazivanje, "Uspesno obrisan racunar " + racunar.getNazivRacunara() + "!", "Uspesno brisanje", JOptionPane.INFORMATION_MESSAGE);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(frmPretrazivanje, "Greska pri brisanju racunara iz baze podataka\n" + ex.getMessage(), "Greska pri brisanju", JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                    }
                }
            }
        });

        frmPretrazivanje.getBtnIzmeni().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnIzmeni();
            }

            private void btnIzmeni() {
                int row = frmPretrazivanje.getTblKomponente().getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(frmPretrazivanje, "Morate selektovati red za izmenu", "Greska pri izmeni", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                switch (frmModOtvaranja) {
                    case KOMPONENTA_PRETRAZIVANJE:
                        TabelModelKomponente tmk = (TabelModelKomponente) frmPretrazivanje.getTblKomponente().getModel();
                        Komponenta komponenta = tmk.getKomponentaZaBrisanje(row);

                        MainCordinator.getInstance().addParam(ParamConstants.KOMPONENTA_ZA_IZMENU, komponenta);
                        MainCordinator.getInstance().otvoriFrmKomponentaIzmena();

                        break;
                    case RACUNAR_PRETRAZIVANJE:
                        TabelModelRacunari tmr = (TabelModelRacunari) frmPretrazivanje.getTblKomponente().getModel();
                        Racunar racunar = tmr.getRacunarZaBrisanje(row);

                        MainCordinator.getInstance().addParam(ParamConstants.RACUNAR_ZA_IZMENU, racunar);
                        MainCordinator.getInstance().otvoriFrmRacunarIzmena();

                        break;
                }
            }
        });

        frmPretrazivanje.getBtnPretraga().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnPretraga();
            }

            private void btnPretraga() {
                pretragaElemenata();
            }
        });

        frmPretrazivanje.addWindowListener(new WindowAdapter() {

            @Override
            public void windowActivated(WindowEvent e) {
                pretragaElemenata();
            }

        });
    }

    private void pripremiFormu() {
        switch (frmModOtvaranja) {
            case KOMPONENTA_PRETRAZIVANJE:
                frmPretrazivanje.getTblKomponente().setModel(new TabelModelKomponente());
                frmPretrazivanje.getTblKomponente().getColumnModel().getColumn(0).setPreferredWidth(30);
                frmPretrazivanje.getTblKomponente().getColumnModel().getColumn(1).setPreferredWidth(150);
                frmPretrazivanje.getTblKomponente().getColumnModel().getColumn(2).setPreferredWidth(50);
                frmPretrazivanje.getTblKomponente().getColumnModel().getColumn(3).setPreferredWidth(60);
                frmPretrazivanje.getTblKomponente().getColumnModel().getColumn(4).setPreferredWidth(230);
                frmPretrazivanje.getTblKomponente().getColumnModel().getColumn(5).setPreferredWidth(60);

                frmPretrazivanje.getLblFilter().setText("Naziv komponente");
                frmPretrazivanje.getLblTabela().setText("Tabela komponenti:");
                frmPretrazivanje.setTitle("Pretraga komponente");
                frmPretrazivanje.getBtnPretraga().setText("Pretraga komponente");
                frmPretrazivanje.getBtnPretraga().setVisible(true);
                frmPretrazivanje.getBtnObrisi().setVisible(true);
                frmPretrazivanje.getBtnIzmeni().setVisible(true);
                break;
            case RACUNAR_PRETRAZIVANJE:
                frmPretrazivanje.getTblKomponente().setModel(new TabelModelRacunari());
                frmPretrazivanje.getTblKomponente().getColumnModel().getColumn(0).setPreferredWidth(1);
                frmPretrazivanje.getTblKomponente().getColumnModel().getColumn(1).setPreferredWidth(300);
                frmPretrazivanje.getTblKomponente().getColumnModel().getColumn(2).setPreferredWidth(70);
                frmPretrazivanje.getTblKomponente().getColumnModel().getColumn(3).setPreferredWidth(40);
                frmPretrazivanje.getTblKomponente().getColumnModel().getColumn(4).setPreferredWidth(90);

                frmPretrazivanje.getLblFilter().setText("Naziv racunara");
                frmPretrazivanje.getLblTabela().setText("Tabela racunara:");
                frmPretrazivanje.setTitle("Pretraga racunara");
                frmPretrazivanje.getBtnPretraga().setText("Pretraga racunara");
                frmPretrazivanje.getBtnPretraga().setVisible(true);
                frmPretrazivanje.getBtnObrisi().setVisible(true);
                frmPretrazivanje.getBtnIzmeni().setVisible(true);
                break;
        }
    }

    private void pretragaElemenata() {
        switch (frmModOtvaranja) {
            case KOMPONENTA_PRETRAZIVANJE:
                TabelModelKomponente tmk = (TabelModelKomponente) frmPretrazivanje.getTblKomponente().getModel();
                try {
                    String naziv = frmPretrazivanje.getTxtNaziv().getText().trim();
                    ArrayList<Komponenta> komponente = Komunikacija.getInstance().pretragaKomponenti(naziv);
                    tmk.setKomponente(komponente);
                } catch (Exception ex) {
                    tmk.setKomponente(new ArrayList<Komponenta>());
                }
                break;
            case RACUNAR_PRETRAZIVANJE:
                TabelModelRacunari tmr = (TabelModelRacunari) frmPretrazivanje.getTblKomponente().getModel();
                try {
                    String naziv = frmPretrazivanje.getTxtNaziv().getText().trim();
                    ArrayList<Racunar> racunari = Komunikacija.getInstance().pretragaRacunara(naziv);
                    tmr.setRacunari(racunari);
                } catch (Exception ex) {
                    tmr.setRacunari(new ArrayList<Racunar>());
                }
                break;
        }
    }
}
