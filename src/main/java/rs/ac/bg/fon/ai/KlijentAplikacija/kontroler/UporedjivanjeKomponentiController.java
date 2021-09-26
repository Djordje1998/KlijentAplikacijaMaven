/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Komponenta;
import domen.StressTest;
import forme.uporedjivanje.FrmUporedjivanjeKomponenti;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import kont.Komunikacija;
import kordinator.MainCordinator;

/**
 *
 * @author DarkForce
 */
public class UporedjivanjeKomponentiController {

    private final FrmUporedjivanjeKomponenti frmUporedjivanjeKomponenti;

    public UporedjivanjeKomponentiController(FrmUporedjivanjeKomponenti frmUporedjivanjeKomponenti) {
        this.frmUporedjivanjeKomponenti = frmUporedjivanjeKomponenti;
        addActionListeners();
    }

    public void openForm() {
        srediFormu();
        srediBiranje();
        frmUporedjivanjeKomponenti.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
        frmUporedjivanjeKomponenti.setVisible(true);
    }

    private void srediFormu() {
        frmUporedjivanjeKomponenti.getCmbTipPrva().setEditable(false);
        frmUporedjivanjeKomponenti.getTxtTaktPrva().setEditable(false);
        frmUporedjivanjeKomponenti.getTxtOpisPrva().setEditable(false);
        frmUporedjivanjeKomponenti.getTxtCenaPrva().setEditable(false);

        frmUporedjivanjeKomponenti.getCmbTipDruga().setEditable(false);
        frmUporedjivanjeKomponenti.getTxtTaktDruga().setEditable(false);
        frmUporedjivanjeKomponenti.getTxtOpisDruga().setEditable(false);
        frmUporedjivanjeKomponenti.getTxtCenaDruga().setEditable(false);

        frmUporedjivanjeKomponenti.getTxtOpisTesta().setEditable(false);
        frmUporedjivanjeKomponenti.getTxtRezultatPrve().setEditable(false);
        frmUporedjivanjeKomponenti.getTxtRezultatDruge().setEditable(false);

        frmUporedjivanjeKomponenti.getTxtRezultatPrve().setText("0");
        frmUporedjivanjeKomponenti.getTxtRezultatDruge().setText("0");
        frmUporedjivanjeKomponenti.getLblZakljucak().setText("");

        try {
            napuniTest();
            napuniKomponente();
            napuniTipove();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmUporedjivanjeKomponenti, ex.getMessage(), "Greska pri otvaranju forme", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void napuniTest() throws Exception {
        frmUporedjivanjeKomponenti.getCmbNazivTesta().removeAllItems();
        frmUporedjivanjeKomponenti.getCmbNazivTesta().setModel(new DefaultComboBoxModel(Komunikacija.getInstance().vratiTestove().toArray()));
        frmUporedjivanjeKomponenti.getCmbNazivTesta().setSelectedIndex(-1);
    }

    private void napuniKomponente() throws Exception {
        frmUporedjivanjeKomponenti.getCmbNazivPrva().removeAllItems();
        frmUporedjivanjeKomponenti.getCmbNazivPrva().setModel(new DefaultComboBoxModel(Komunikacija.getInstance().pretragaKomponenti("").toArray()));
        frmUporedjivanjeKomponenti.getCmbNazivPrva().setSelectedIndex(-1);

        frmUporedjivanjeKomponenti.getCmbNazivDruga().removeAllItems();
        frmUporedjivanjeKomponenti.getCmbNazivDruga().setModel(new DefaultComboBoxModel(Komunikacija.getInstance().pretragaKomponenti("").toArray()));
        frmUporedjivanjeKomponenti.getCmbNazivDruga().setSelectedIndex(-1);

        frmUporedjivanjeKomponenti.getCmbTipPrva().removeAllItems();
        frmUporedjivanjeKomponenti.getCmbTipDruga().removeAllItems();
    }

    private void napuniTipove() throws Exception {
        frmUporedjivanjeKomponenti.getCmbTipPrva().removeAllItems();
        frmUporedjivanjeKomponenti.getCmbTipPrva().setModel(new DefaultComboBoxModel(Komunikacija.getInstance().vratiTipoveKomponenti().toArray()));
        frmUporedjivanjeKomponenti.getCmbTipPrva().setSelectedIndex(-1);
        
        frmUporedjivanjeKomponenti.getCmbTipDruga().removeAllItems();
        frmUporedjivanjeKomponenti.getCmbTipDruga().setModel(new DefaultComboBoxModel(Komunikacija.getInstance().vratiTipoveKomponenti().toArray()));
        frmUporedjivanjeKomponenti.getCmbTipDruga().setSelectedIndex(-1);
    }

    private void addActionListeners() {
        frmUporedjivanjeKomponenti.getBtnUporedi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uporediKomponente();
            }

            private void uporediKomponente() {

                if (!(frmUporedjivanjeKomponenti.getCmbNazivPrva().getSelectedItem() instanceof Komponenta)) {
                    JOptionPane.showMessageDialog(frmUporedjivanjeKomponenti, "Morate izabrati prvu komponentu", "Greska pri uporedjivanju", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!(frmUporedjivanjeKomponenti.getCmbNazivDruga().getSelectedItem() instanceof Komponenta)) {
                    JOptionPane.showMessageDialog(frmUporedjivanjeKomponenti, "Morate izabrati drugu komponentu", "Greska pri uporedjivanju", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!(frmUporedjivanjeKomponenti.getCmbNazivDruga().getSelectedItem() instanceof Komponenta)) {
                    JOptionPane.showMessageDialog(frmUporedjivanjeKomponenti, "Morate izabrati test", "Greska pri uporedjivanju", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Komponenta prva = (Komponenta) frmUporedjivanjeKomponenti.getCmbNazivPrva().getSelectedItem();
                Komponenta druga = (Komponenta) frmUporedjivanjeKomponenti.getCmbNazivDruga().getSelectedItem();
                StressTest test = (StressTest) frmUporedjivanjeKomponenti.getCmbNazivTesta().getSelectedItem();

                try {
                    int prvaOcena = Komunikacija.getInstance().oceniKomponentu(prva, test);
                    int drugaOcena = Komunikacija.getInstance().oceniKomponentu(druga, test);
                    String zakljucak = Komunikacija.getInstance().vratiZakljucakKomponente(prvaOcena, drugaOcena);

                    frmUporedjivanjeKomponenti.getTxtRezultatPrve().setText(String.valueOf(prvaOcena));
                    frmUporedjivanjeKomponenti.getTxtRezultatDruge().setText(String.valueOf(drugaOcena));
                    frmUporedjivanjeKomponenti.getLblZakljucak().setText(zakljucak);

                    JOptionPane.showMessageDialog(frmUporedjivanjeKomponenti, "Uspesno ocenjene komponete", "Uspesno ocenjene komponete", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmUporedjivanjeKomponenti, ex.getMessage(), "Greska pri uporedjivanju", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private void srediBiranje() {
        frmUporedjivanjeKomponenti.getCmbNazivPrva().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                biranjePrvaKomponenta();
            }

            private void biranjePrvaKomponenta() {
                if (frmUporedjivanjeKomponenti.getCmbNazivPrva().getSelectedItem() instanceof Komponenta) {
                    Komponenta komponenta = (Komponenta) frmUporedjivanjeKomponenti.getCmbNazivPrva().getSelectedItem();

                    frmUporedjivanjeKomponenti.getCmbTipPrva().setSelectedItem(komponenta.getTipKomponente());
                    frmUporedjivanjeKomponenti.getTxtTaktPrva().setText(komponenta.getTakt() + "");
                    frmUporedjivanjeKomponenti.getTxtOpisPrva().setText(komponenta.getOpis());
                    frmUporedjivanjeKomponenti.getTxtCenaPrva().setText(komponenta.getCena() + "");
                    resetResultat();
                }
            }
        });

        frmUporedjivanjeKomponenti.getCmbNazivDruga().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                biranjeDrugaKomponenta();
            }

            private void biranjeDrugaKomponenta() {
                if (frmUporedjivanjeKomponenti.getCmbNazivDruga().getSelectedItem() instanceof Komponenta) {
                    Komponenta komponenta = (Komponenta) frmUporedjivanjeKomponenti.getCmbNazivDruga().getSelectedItem();

                    frmUporedjivanjeKomponenti.getCmbTipDruga().setSelectedItem(komponenta.getTipKomponente());
                    frmUporedjivanjeKomponenti.getTxtTaktDruga().setText(komponenta.getTakt() + "");
                    frmUporedjivanjeKomponenti.getTxtOpisDruga().setText(komponenta.getOpis());
                    frmUporedjivanjeKomponenti.getTxtCenaDruga().setText(komponenta.getCena() + "");
                    resetResultat();
                }
            }
        });

        frmUporedjivanjeKomponenti.getCmbNazivTesta().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                biranjeStressTesta();
            }

            private void biranjeStressTesta() {
                if (frmUporedjivanjeKomponenti.getCmbNazivTesta().getSelectedItem() instanceof StressTest) {
                    StressTest test = (StressTest) frmUporedjivanjeKomponenti.getCmbNazivTesta().getSelectedItem();
                    frmUporedjivanjeKomponenti.getTxtOpisTesta().setText(test.getOpisTesta());
                    resetResultat();
                }
            }
        });

    }

    private void resetResultat() {
        frmUporedjivanjeKomponenti.getTxtRezultatPrve().setText("0");
        frmUporedjivanjeKomponenti.getTxtRezultatDruge().setText("0");
        frmUporedjivanjeKomponenti.getLblZakljucak().setText("");
    }

}
