/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Komponenta;
import domen.Racunar;
import domen.StavkaRacunara;
import forme.racunar.FrmRacunar;
import forme.util.FrmModOtvaranja;
import forme.util.ParamConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import kont.Komunikacija;
import kordinator.MainCordinator;
import model.TabelModelStavkeRacunara;

/**
 *
 * @author DarkForce
 */
public class RacunarController {

    private final FrmModOtvaranja frmModOtvaranja;
    private final FrmRacunar frmRacunar;
    private int redniBrojZaIzmenu = -1;

    public RacunarController(FrmRacunar frmRacunar, FrmModOtvaranja frmModOtvaranja) {
        this.frmModOtvaranja = frmModOtvaranja;
        this.frmRacunar = frmRacunar;
        addActionListeners();
    }

    public void openForm() {
        srediFormu();
        frmRacunar.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
        frmRacunar.setVisible(true);
    }

    private void addActionListeners() {

        frmRacunar.getBtnDodajKomponentu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDodajKomponentu();
            }

            private void btnDodajKomponentu() {
                TabelModelStavkeRacunara tmsr = (TabelModelStavkeRacunara) frmRacunar.getTblStavkeRacunara().getModel();
                Komponenta komponenta = (Komponenta) frmRacunar.getCmbNaziv().getSelectedItem();
                double cena = Double.parseDouble(frmRacunar.getTxtCena().getText().trim());
                int kolicina = -1;

                try {
                    kolicina = Integer.parseInt(frmRacunar.getTxtKolicina().getText().trim());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frmRacunar, "Kolicina mora biti broj", "Los format kolicine komponente", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (kolicina <= 0) {
                    JOptionPane.showMessageDialog(frmRacunar, "Kolicina mora biti broj veci od 0", "Los unos kolicine komponente", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                tmsr.dodajStavku(komponenta, cena, kolicina);
                double ukupnaCena = tmsr.getUkupnaCena();
                frmRacunar.getTxtUkupncaCena().setText(ukupnaCena + "");

            }
        });

        frmRacunar.getBtnObrisiStavku().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnObrisiStavku();
            }

            private void btnObrisiStavku() {
                int red = frmRacunar.getTblStavkeRacunara().getSelectedRow();
                if (red < 0) {
                    JOptionPane.showMessageDialog(frmRacunar, "Morate selektovati red u tabeli", "Greska pri brisanju stavke", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                TabelModelStavkeRacunara tmsr = (TabelModelStavkeRacunara) frmRacunar.getTblStavkeRacunara().getModel();
                tmsr.obrisiStavku(red);

                double ukupnaCena = tmsr.getUkupnaCena();
                frmRacunar.getTxtUkupncaCena().setText(ukupnaCena + "");
            }
        });

        frmRacunar.getBtnSacuvajRacunar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSacuvajRacunar();
            }

            private void btnSacuvajRacunar() {
                String naziv = frmRacunar.getTxtNaziv().getText().trim();
                String namena = frmRacunar.getTxtNamena().getText().trim();
                Date garancija = frmRacunar.getJdcGarancija().getDate();
                double ukupnaCena = Double.parseDouble(frmRacunar.getTxtUkupncaCena().getText().trim());;
                
                TabelModelStavkeRacunara tmsr = (TabelModelStavkeRacunara) frmRacunar.getTblStavkeRacunara().getModel();
                Racunar racunar = tmsr.getRacunar();
                racunar.setGarancija(garancija);
                racunar.setNamena(namena);
                racunar.setNazivRacunara(naziv);
                racunar.setUkupnaCena(ukupnaCena);

                try {
                    Komunikacija.getInstance().kreirajRacunar(racunar);
                    JOptionPane.showMessageDialog(frmRacunar, "Uspesno kreiran racunar!", "Uspesno kreiran racunar", JOptionPane.INFORMATION_MESSAGE);
                    obrisiPolja();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmRacunar, ex.getMessage(), "Greska pri kreiranju racunara", JOptionPane.ERROR_MESSAGE);
                }

            }

            private void obrisiPolja() {
                frmRacunar.getTblStavkeRacunara().setModel(new TabelModelStavkeRacunara(new Racunar()));
                frmRacunar.getTxtNaziv().setText("");
                frmRacunar.getTxtNamena().setText("");
                podesiDatum();
                frmRacunar.getTxtUkupncaCena().setText("0.0");

                frmRacunar.getCmbNaziv().setSelectedIndex(-1);
                frmRacunar.getCmbTip().setSelectedIndex(-1);
                frmRacunar.getTxtTakt().setText("");
                frmRacunar.getTxtCena().setText("");
                frmRacunar.getTxtOpis().setText("");
                frmRacunar.getTxtKolicina().setText("");
                frmRacunar.getTxtNaziv().grabFocus();
            }
        });

        frmRacunar.getBtnSacuvajIzmeneRacunara().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvajIzmeneRacuanra();
            }

            private void sacuvajIzmeneRacuanra() {
                String naziv = frmRacunar.getTxtNaziv().getText().trim();
                String namena = frmRacunar.getTxtNamena().getText().trim();
                Date garancija = frmRacunar.getJdcGarancija().getDate();
                double ukupnaCena = Double.parseDouble(frmRacunar.getTxtUkupncaCena().getText().trim());;

                TabelModelStavkeRacunara tmsr = (TabelModelStavkeRacunara) frmRacunar.getTblStavkeRacunara().getModel();
                Racunar racunar = tmsr.getRacunar();
                racunar.setGarancija(garancija);
                racunar.setNamena(namena);
                racunar.setNazivRacunara(naziv);
                racunar.setUkupnaCena(ukupnaCena);

                try {
                    Komunikacija.getInstance().izmeniRacunar(racunar);
                    JOptionPane.showMessageDialog(frmRacunar, "Uspesno sacuvan racunar", "Uspesno sacuvan racunar", JOptionPane.INFORMATION_MESSAGE);
                    frmRacunar.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmRacunar, ex.getMessage(), "Greska pri cuvanju racunara", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmRacunar.getBtnIzmeniStavku().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeniStavku();
            }

            private void izmeniStavku() {
                int red = frmRacunar.getTblStavkeRacunara().getSelectedRow();
                if (red < 0) {
                    JOptionPane.showMessageDialog(frmRacunar, "Morate selektovati red u tabeli", "Greska pri izmeni stavke", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                TabelModelStavkeRacunara tmsr = (TabelModelStavkeRacunara) frmRacunar.getTblStavkeRacunara().getModel();
                StavkaRacunara stavka = tmsr.vratiStavku(red);
                redniBrojZaIzmenu = stavka.getRedniBroj();

                frmRacunar.getCmbNaziv().setEnabled(true);
                frmRacunar.getCmbNaziv().setSelectedItem(stavka.getKomponenta());
                frmRacunar.getTxtCena().setText(stavka.getCena() + "");
                frmRacunar.getTxtKolicina().setText(stavka.getKolicina() + "");
                frmRacunar.getBtnPotvrdiIzmenu().setEnabled(true);
            }
        });

        frmRacunar.getBtnPotvrdiIzmenu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                potvrdiIzmenu();
            }

            private void potvrdiIzmenu() {
                TabelModelStavkeRacunara tmsr = (TabelModelStavkeRacunara) frmRacunar.getTblStavkeRacunara().getModel();
                Komponenta komponenta = (Komponenta) frmRacunar.getCmbNaziv().getSelectedItem();
                double cena = Double.parseDouble(frmRacunar.getTxtCena().getText().trim());
                int kolicina = -1;

                try {
                    kolicina = Integer.parseInt(frmRacunar.getTxtKolicina().getText().trim());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frmRacunar, "Kolicina mora biti broj", "Los format kolicine komponente", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (kolicina <= 0) {
                    JOptionPane.showMessageDialog(frmRacunar, "Kolicina mora biti broj veci od 0", "Los unos kolicine komponente", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                tmsr.izmeniStavku(redniBrojZaIzmenu, komponenta, cena, kolicina);
                double ukupnaCena = tmsr.getUkupnaCena();

                frmRacunar.getTxtUkupncaCena().setText(ukupnaCena + "");
                frmRacunar.getCmbNaziv().setEnabled(false);
                frmRacunar.getBtnPotvrdiIzmenu().setEnabled(false);
            }
        });

    }

    private void srediFormu() {
        frmRacunar.getTblStavkeRacunara().setModel(new TabelModelStavkeRacunara(new Racunar()));
        frmRacunar.getCmbTip().setEditable(false);
        frmRacunar.getTxtTakt().setEditable(false);
        frmRacunar.getTxtCena().setEditable(true);
        frmRacunar.getTxtOpis().setEditable(false);
        frmRacunar.getTxtUkupncaCena().setEditable(false);

        podesiDatum();
        napuniTipove();
        srediBiranjeKomponenti();

        switch (frmModOtvaranja) {
            case RACUNAR_IZMENA:
                frmRacunar.setTitle("Izmena racunara");
                frmRacunar.getBtnSacuvajRacunar().setVisible(false);
                frmRacunar.getBtnSacuvajIzmeneRacunara().setVisible(true);
                frmRacunar.getBtnIzmeniStavku().setVisible(true);
                frmRacunar.getBtnObrisiStavku().setVisible(false);
                frmRacunar.getBtnDodajKomponentu().setVisible(false);
                frmRacunar.getBtnPotvrdiIzmenu().setVisible(true);
                frmRacunar.getBtnPotvrdiIzmenu().setEnabled(false);
                frmRacunar.getCmbNaziv().setEnabled(false);
                napuniPolja();

                break;
            case RACUNAR_KREIRANJE:
                frmRacunar.setTitle("Kreiranje racunara");
                frmRacunar.getTxtUkupncaCena().setText("0.0");
                frmRacunar.getBtnSacuvajRacunar().setVisible(true);
                frmRacunar.getBtnSacuvajIzmeneRacunara().setVisible(false);
                frmRacunar.getBtnObrisiStavku().setVisible(true);
                frmRacunar.getBtnDodajKomponentu().setVisible(true);
                frmRacunar.getBtnIzmeniStavku().setVisible(false);
                frmRacunar.getBtnPotvrdiIzmenu().setVisible(false);

                break;
        }
    }

    private void napuniTipove() {
        try {
            frmRacunar.getCmbTip().removeAllItems();
            frmRacunar.getCmbTip().setModel(new DefaultComboBoxModel(Komunikacija.getInstance().vratiTipoveKomponenti().toArray()));
            frmRacunar.getCmbTip().setSelectedIndex(-1);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frmRacunar, ex.getMessage(), "Greska pri otvaranju forme", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void srediBiranjeKomponenti() {
        try {
            frmRacunar.getCmbNaziv().removeAllItems();
            frmRacunar.getCmbNaziv().setModel(new DefaultComboBoxModel(Komunikacija.getInstance().pretragaKomponenti("").toArray()));
            frmRacunar.getCmbNaziv().setSelectedIndex(-1);
            frmRacunar.getTxtNaziv().grabFocus();

            frmRacunar.getCmbNaziv().addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    izmenaKomponente(e);
                }

                private void izmenaKomponente(ItemEvent e) {
                    Komponenta komponenta = (Komponenta) e.getItem();
                    frmRacunar.getCmbTip().setSelectedItem(komponenta.getTipKomponente());
                    frmRacunar.getTxtTakt().setText(komponenta.getTakt() + "");
                    frmRacunar.getTxtCena().setText(komponenta.getCena() + "");
                    frmRacunar.getTxtOpis().setText(komponenta.getOpis() + "");

                    frmRacunar.getTxtKolicina().setText("1");
                    frmRacunar.getTxtKolicina().grabFocus();
                    frmRacunar.getTxtKolicina().setSelectionStart(0);
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frmRacunar, ex.getMessage(), "Greska pri otvaranju forme", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void napuniPolja() {
        Racunar racunar = (Racunar) MainCordinator.getInstance().getParam(ParamConstants.RACUNAR_ZA_IZMENU);
        TabelModelStavkeRacunara tmsr = (TabelModelStavkeRacunara) frmRacunar.getTblStavkeRacunara().getModel();
        tmsr.setRacunar(racunar);

        frmRacunar.getTxtNaziv().setText(racunar.getNazivRacunara());
        frmRacunar.getTxtNamena().setText(racunar.getNamena());
        frmRacunar.getJdcGarancija().setDate(racunar.getGarancija());
        frmRacunar.getTxtUkupncaCena().setText(racunar.getUkupnaCena() + "");

    }

    private void podesiDatum() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, 2);
        frmRacunar.getJdcGarancija().setDate(c.getTime());
    }

}
