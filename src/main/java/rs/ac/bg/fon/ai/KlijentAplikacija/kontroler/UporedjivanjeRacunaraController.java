/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.KlijentAplikacija.kontroler;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Benchmark;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Racunar;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.StressTest;
import rs.ac.bg.fon.ai.KlijentAplikacija.forme.uporedjivanje.FrmUporedjivanjeRacunara;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ai.KlijentAplikacija.kont.Komunikacija;
import rs.ac.bg.fon.ai.KlijentAplikacija.kordinator.MainCordinator;

/**
 *
 * @author DarkForce
 */
public class UporedjivanjeRacunaraController {

    private final FrmUporedjivanjeRacunara frmUporedjivanjeRacunara;

    public UporedjivanjeRacunaraController(FrmUporedjivanjeRacunara frmUporedjivanjeRacunara) {
        this.frmUporedjivanjeRacunara = frmUporedjivanjeRacunara;
        addActionListeners();
    }

    public void openForm() {
        srediFormu();
        srediBiranje();
        frmUporedjivanjeRacunara.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
        frmUporedjivanjeRacunara.setVisible(true);
    }

    private void srediFormu() {
        frmUporedjivanjeRacunara.getTxtNamenaPrvi().setEditable(false);
        frmUporedjivanjeRacunara.getTxtGarancijaPrvi().setEditable(false);

        frmUporedjivanjeRacunara.getTxtNamenaDrugi().setEditable(false);
        frmUporedjivanjeRacunara.getTxtGarancijaDrugi().setEditable(false);

        frmUporedjivanjeRacunara.getTxtOpisTesta().setEditable(false);
        frmUporedjivanjeRacunara.getTxtRezultatPrve().setEditable(false);
        frmUporedjivanjeRacunara.getTxtRezultatDruge().setEditable(false);

        frmUporedjivanjeRacunara.getTxtRezultatPrve().setText("0");
        frmUporedjivanjeRacunara.getTxtRezultatDruge().setText("0");
        frmUporedjivanjeRacunara.getTxtZakljucak().setText("");

        try {
            napuniTest();
            napuniRacunare();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmUporedjivanjeRacunara, ex.getMessage(), "Greska pri otvaranju forme", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void napuniTest() throws Exception {
        frmUporedjivanjeRacunara.getCmbNazivTesta().removeAllItems();
        frmUporedjivanjeRacunara.getCmbNazivTesta().setModel(new DefaultComboBoxModel(Komunikacija.getInstance().vratiTestove().toArray()));
        frmUporedjivanjeRacunara.getCmbNazivTesta().setSelectedIndex(-1);
    }

    private void napuniRacunare() throws Exception {
        frmUporedjivanjeRacunara.getCmbNazivPrvi().removeAllItems();
        frmUporedjivanjeRacunara.getCmbNazivPrvi().setModel(new DefaultComboBoxModel(Komunikacija.getInstance().pretragaRacunara("").toArray()));
        frmUporedjivanjeRacunara.getCmbNazivPrvi().setSelectedIndex(-1);

        frmUporedjivanjeRacunara.getCmbNazivDrugi().removeAllItems();
        frmUporedjivanjeRacunara.getCmbNazivDrugi().setModel(new DefaultComboBoxModel(Komunikacija.getInstance().pretragaRacunara("").toArray()));
        frmUporedjivanjeRacunara.getCmbNazivDrugi().setSelectedIndex(-1);
    }

    private void addActionListeners() {
        frmUporedjivanjeRacunara.getBtnUporedi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uporediRacunare();
            }

            private void uporediRacunare() {
                if (!(frmUporedjivanjeRacunara.getCmbNazivPrvi().getSelectedItem() instanceof Racunar)) {
                    JOptionPane.showMessageDialog(frmUporedjivanjeRacunara, "Morate izabrati prvi racunar", "Greska pri uporedjivanju", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!(frmUporedjivanjeRacunara.getCmbNazivDrugi().getSelectedItem() instanceof Racunar)) {
                    JOptionPane.showMessageDialog(frmUporedjivanjeRacunara, "Morate izabrati drugi racunar", "Greska pri uporedjivanju", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Racunar prvi = (Racunar) frmUporedjivanjeRacunara.getCmbNazivPrvi().getSelectedItem();
                Racunar drugi = (Racunar) frmUporedjivanjeRacunara.getCmbNazivDrugi().getSelectedItem();

                try {
                    StressTest test = (StressTest) frmUporedjivanjeRacunara.getCmbNazivTesta().getSelectedItem();
                    int prvaOcena = Komunikacija.getInstance().oceniRacunar(prvi, test);
                    int drugaOcena = Komunikacija.getInstance().oceniRacunar(drugi, test);
                    String zakljucak = Komunikacija.getInstance().vratiZakljucakRacunar(prvaOcena, drugaOcena);
                    frmUporedjivanjeRacunara.getTxtRezultatPrve().setText(String.valueOf(prvaOcena));
                    frmUporedjivanjeRacunara.getTxtRezultatDruge().setText(String.valueOf(drugaOcena));
                    frmUporedjivanjeRacunara.getTxtZakljucak().setText(zakljucak);
                    JOptionPane.showMessageDialog(frmUporedjivanjeRacunara, "Uspesno ocenjeni racunari", "Uspesno ocenjene komponete", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmUporedjivanjeRacunara, ex.getMessage(), "Greska pri uporedjivanju", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmUporedjivanjeRacunara.getBtnZapamti().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zapamtiRacunar();
            }

            private void zapamtiRacunar() {
                if (!(frmUporedjivanjeRacunara.getCmbNazivPrvi().getSelectedItem() instanceof Racunar)) {
                    JOptionPane.showMessageDialog(frmUporedjivanjeRacunara, "Morate izabrati prvi racunar", "Greska pri uporedjivanju", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!(frmUporedjivanjeRacunara.getCmbNazivDrugi().getSelectedItem() instanceof Racunar)) {
                    JOptionPane.showMessageDialog(frmUporedjivanjeRacunara, "Morate izabrati drugi racunar", "Greska pri uporedjivanju", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (frmUporedjivanjeRacunara.getTxtRezultatPrve().equals("0")) {
                    JOptionPane.showMessageDialog(frmUporedjivanjeRacunara, "Morate uporediti racunare da bi ste ih sacuvali!", "Greska pri cuvanju benchmarka", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Racunar prvi = (Racunar) frmUporedjivanjeRacunara.getCmbNazivPrvi().getSelectedItem();
                Racunar drugi = (Racunar) frmUporedjivanjeRacunara.getCmbNazivDrugi().getSelectedItem();
                StressTest test = (StressTest) frmUporedjivanjeRacunara.getCmbNazivTesta().getSelectedItem();
                int prviBodovi = Integer.parseInt(frmUporedjivanjeRacunara.getTxtRezultatPrve().getText().trim());
                int drugiBodovi = Integer.parseInt(frmUporedjivanjeRacunara.getTxtRezultatDruge().getText().trim());
                Date datum = new Date();

                Benchmark benchmark = new Benchmark(0, datum, prviBodovi, drugiBodovi, prvi, drugi, test);
                try {
                    Komunikacija.getInstance().sacuvajBenchmark(benchmark);
                    JOptionPane.showMessageDialog(frmUporedjivanjeRacunara, "Uspesno sacuvan benchmark!", "Uspesno sacuvan benchmark!", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmUporedjivanjeRacunara, ex.getMessage(), "Neuspesno cuvanje Benchmarka", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private void srediBiranje() {
        

        frmUporedjivanjeRacunara.getCmbNazivPrvi().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                biranjePrvogRacunara();
            }

            private void biranjePrvogRacunara() {
            	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                if (frmUporedjivanjeRacunara.getCmbNazivPrvi().getSelectedItem() instanceof Racunar) {
                    Racunar racunar = (Racunar) frmUporedjivanjeRacunara.getCmbNazivPrvi().getSelectedItem();
                    frmUporedjivanjeRacunara.getTxtNamenaPrvi().setText(racunar.getNamena());
                    frmUporedjivanjeRacunara.getTxtGarancijaPrvi().setText(sdf.format(racunar.getGarancija()));
                    resetResultat();
                }
            }

        });

        frmUporedjivanjeRacunara.getCmbNazivDrugi().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                buranjeDrugogRacunara();
            }

            private void buranjeDrugogRacunara() {
            	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                if (frmUporedjivanjeRacunara.getCmbNazivDrugi().getSelectedItem() instanceof Racunar) {
                    Racunar racunar = (Racunar) frmUporedjivanjeRacunara.getCmbNazivDrugi().getSelectedItem();
                    frmUporedjivanjeRacunara.getTxtNamenaDrugi().setText(racunar.getNamena());
                    frmUporedjivanjeRacunara.getTxtGarancijaDrugi().setText(sdf.format(racunar.getGarancija()));
                    resetResultat();
                }
            }
        });

        frmUporedjivanjeRacunara.getCmbNazivTesta().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                biranjeTesta();
            }

            private void biranjeTesta() {
                if (frmUporedjivanjeRacunara.getCmbNazivTesta().getSelectedItem() instanceof StressTest) {
                    StressTest test = (StressTest) frmUporedjivanjeRacunara.getCmbNazivTesta().getSelectedItem();
                    frmUporedjivanjeRacunara.getTxtOpisTesta().setText(test.getOpisTesta());
                    resetResultat();
                }
            }
        });

    }

    private void resetResultat() {
        frmUporedjivanjeRacunara.getTxtRezultatPrve().setText("0");
        frmUporedjivanjeRacunara.getTxtRezultatDruge().setText("0");
        frmUporedjivanjeRacunara.getTxtZakljucak().setText("");
    }

}
