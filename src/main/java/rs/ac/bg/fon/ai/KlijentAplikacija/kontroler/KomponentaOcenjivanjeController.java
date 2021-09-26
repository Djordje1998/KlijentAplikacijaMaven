/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.KlijentAplikacija.kontroler;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Komponenta;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Ocena;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.StressTest;
import rs.ac.bg.fon.ai.KlijentAplikacija.forme.komponente.FrmKomponentaOcenjivanje;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ai.KlijentAplikacija.kont.Komunikacija;
import rs.ac.bg.fon.ai.KlijentAplikacija.kordinator.MainCordinator;

/**
 *
 * @author DarkForce
 */
public class KomponentaOcenjivanjeController {

    private final FrmKomponentaOcenjivanje frmKomponentaOcenjivanje;

    public KomponentaOcenjivanjeController(FrmKomponentaOcenjivanje frmKomponentaOcenjivanje) {
        this.frmKomponentaOcenjivanje = frmKomponentaOcenjivanje;
        addActionListeners();
    }

    public void openForm() {
        srediFormu();
        srediBiranje();
        frmKomponentaOcenjivanje.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
        frmKomponentaOcenjivanje.setVisible(true);
    }

    private void srediFormu() {
        frmKomponentaOcenjivanje.getCmbNaziv().setEditable(false);
        frmKomponentaOcenjivanje.getTxtTakt().setEditable(false);
        frmKomponentaOcenjivanje.getCmbTip().setEditable(false);
        frmKomponentaOcenjivanje.getTxtOpis().setEditable(false);
        frmKomponentaOcenjivanje.getTxtCena().setEditable(false);
        frmKomponentaOcenjivanje.getTxtOpisTesta().setEditable(false);
        frmKomponentaOcenjivanje.getTxtRezultat().setEditable(false);
        frmKomponentaOcenjivanje.getTxtRezultat().setText("0");

        try {
            napuniTest();
            napuniKomponentu();
            napuniTipove();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frmKomponentaOcenjivanje, ex.getMessage(), "Greska pri otvaranju forme", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void napuniTest() throws Exception {
        frmKomponentaOcenjivanje.getCmbNazivTesta().removeAllItems();
        frmKomponentaOcenjivanje.getCmbNazivTesta().setModel(new DefaultComboBoxModel(Komunikacija.getInstance().vratiTestove().toArray()));
        frmKomponentaOcenjivanje.getCmbNazivTesta().setSelectedIndex(-1);
    }

    public void napuniKomponentu() throws Exception {
        frmKomponentaOcenjivanje.getCmbNaziv().removeAllItems();
        frmKomponentaOcenjivanje.getCmbNaziv().setModel(new DefaultComboBoxModel(Komunikacija.getInstance().pretragaKomponenti("").toArray()));
        frmKomponentaOcenjivanje.getCmbNaziv().setSelectedIndex(-1);

    }

    private void napuniTipove() throws Exception {
        frmKomponentaOcenjivanje.getCmbTip().removeAllItems();
        frmKomponentaOcenjivanje.getCmbTip().setModel(new DefaultComboBoxModel(Komunikacija.getInstance().vratiTipoveKomponenti().toArray()));
        frmKomponentaOcenjivanje.getCmbTip().setSelectedIndex(-1);
    }

    private void addActionListeners() {

        frmKomponentaOcenjivanje.getBtnOceni().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oceni();
            }

            private void oceni() {
                if (!(frmKomponentaOcenjivanje.getCmbNaziv().getSelectedItem() instanceof Komponenta)) {
                    JOptionPane.showMessageDialog(frmKomponentaOcenjivanje, "Morate izabrati komponentu", "Morate izabrati komponentu", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!(frmKomponentaOcenjivanje.getCmbNazivTesta().getSelectedItem() instanceof StressTest)) {
                    JOptionPane.showMessageDialog(frmKomponentaOcenjivanje, "Morate izabrati Stress test", "Morate izabrati Stress test", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!frmKomponentaOcenjivanje.getTxtRezultat().getText().equals("0")) {
                    JOptionPane.showMessageDialog(frmKomponentaOcenjivanje, "Vec je ocenjena komponenta sa izabranim testom", "Ocena je vec formirana", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    StressTest test = (StressTest) frmKomponentaOcenjivanje.getCmbNazivTesta().getSelectedItem();
                    int ocena = Komunikacija.getInstance().oceniKomponentu((Komponenta) frmKomponentaOcenjivanje.getCmbNaziv().getSelectedItem(), test);
                    frmKomponentaOcenjivanje.getTxtRezultat().setText(String.valueOf(ocena));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmKomponentaOcenjivanje, ex.getMessage(), "Greska pri ocenjivanju komponente", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmKomponentaOcenjivanje.getBtnSacuvajOcenu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvajOcenu();
            }

            private void sacuvajOcenu() {
                if (!(frmKomponentaOcenjivanje.getCmbNaziv().getSelectedItem() instanceof Komponenta)) {
                    JOptionPane.showMessageDialog(frmKomponentaOcenjivanje, "Morate izabrati komponentu", "Morate izabrati komponentu", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (frmKomponentaOcenjivanje.getTxtRezultat().getText().equals("0")) {
                    JOptionPane.showMessageDialog(frmKomponentaOcenjivanje, "Morate oceniti komponentu", "Morate oceniti komponentu", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                StressTest test = (StressTest) frmKomponentaOcenjivanje.getCmbNazivTesta().getSelectedItem();
                int brojOcene = Integer.parseInt(frmKomponentaOcenjivanje.getTxtRezultat().getText().trim());
                Ocena ocena = new Ocena((Komponenta) frmKomponentaOcenjivanje.getCmbNaziv().getSelectedItem(), test, brojOcene);

                try {
                    Komunikacija.getInstance().sacuvajOcenu(ocena);
                    JOptionPane.showMessageDialog(frmKomponentaOcenjivanje, "Uspesno sacuvana ocena komponente", "Uspesno sacuvana ocena komponete", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmKomponentaOcenjivanje, ex.getMessage(), "Greska pri cuvanju ocene komponente", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private void srediBiranje() {
        frmKomponentaOcenjivanje.getCmbNaziv().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                izaberiKomponentu();
            }

            private void izaberiKomponentu() {
                if (frmKomponentaOcenjivanje.getCmbNaziv().getSelectedItem() instanceof Komponenta) {
                    Komponenta komponenta = (Komponenta) frmKomponentaOcenjivanje.getCmbNaziv().getSelectedItem();

                    frmKomponentaOcenjivanje.getCmbTip().setSelectedItem(komponenta.getTipKomponente());
                    frmKomponentaOcenjivanje.getTxtTakt().setText(String.valueOf(komponenta.getTakt()));
                    frmKomponentaOcenjivanje.getTxtOpis().setText(komponenta.getNazivKomponente());
                    frmKomponentaOcenjivanje.getTxtCena().setText(komponenta.getCena() + "");
                    frmKomponentaOcenjivanje.getTxtRezultat().setText("0");
                }

            }
        });

        frmKomponentaOcenjivanje.getCmbNazivTesta().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                izaberiTest();
            }

            private void izaberiTest() {
                if (frmKomponentaOcenjivanje.getCmbNazivTesta().getSelectedItem() instanceof StressTest) {
                    StressTest test = (StressTest) frmKomponentaOcenjivanje.getCmbNazivTesta().getSelectedItem();
                    frmKomponentaOcenjivanje.getTxtOpisTesta().setText(test.getOpisTesta());
                    frmKomponentaOcenjivanje.getTxtRezultat().setText("0");
                }
            }
        });

    }

}
