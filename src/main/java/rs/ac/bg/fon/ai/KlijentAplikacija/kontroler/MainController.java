/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Korisnik;
import forme.FrmMain;
import forme.util.ParamConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import kont.Komunikacija;
import kordinator.MainCordinator;

/**
 *
 * @author DarkForce
 */
public class MainController {

    private final FrmMain frmMain;

    public MainController(FrmMain glavnaForma) {
        this.frmMain = glavnaForma;
        addActionListeners();
    }

    public void openForm() {
        frmMain.setLocationRelativeTo(null);
        frmMain.setVisible(true);
        pripremiFormu();

    }

    private void pripremiFormu(){
        Korisnik ulogovaniKorisnik = (Korisnik) MainCordinator.getInstance().getParam(ParamConstants.ULOGOVANI_KORISNIK);
        frmMain.getLblUlogovaniKorisnik().setText(ulogovaniKorisnik.getIme() + " " + ulogovaniKorisnik.getPrezime());

    }

    private void addActionListeners() {
        frmMain.getJmiKomponentaKreiranje().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jmiKomponentaKreiranje(e);
            }

            private void jmiKomponentaKreiranje(ActionEvent e) {
                MainCordinator.getInstance().otvoriFrmKomponentaKreiranje();
            }

        });

        frmMain.getJmiKomponentaPretrazivanje().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jmiKomponentaPretrazivanje(e);
            }

            private void jmiKomponentaPretrazivanje(ActionEvent e) {
                MainCordinator.getInstance().otvoriFrmKomponentaPretrazivanje();
            }
        });

        frmMain.getJmiKomponentaOcenjivanje().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jmiKomponentaOcenjivanje(e);
            }

            private void jmiKomponentaOcenjivanje(ActionEvent e) {
                MainCordinator.getInstance().otvoriFrmKomponentaOcenjivanje();
            }
        });

        frmMain.getJmiRacunarKreiranje().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jmiRacunarKreiranje();
            }

            private void jmiRacunarKreiranje() {
                MainCordinator.getInstance().otvoriFrmRacunarKreiranje();
            }
        });

        frmMain.getJmiRacunarPretrazivanje().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jmiRacunarPretrazivanje();
            }

            private void jmiRacunarPretrazivanje() {
                MainCordinator.getInstance().otvoriFrmRacunarPretrazivanje();
            }
        });

        frmMain.getJmiUporediKomponente().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jmiUporediKomponente();
            }

            private void jmiUporediKomponente() {
                MainCordinator.getInstance().otvoriFrmUporediKomponente();
            }
        });

        frmMain.getJmiUporediRacunare().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jmiUporediRacunare();
            }

            private void jmiUporediRacunare() {
                MainCordinator.getInstance().otvoriFrmUporediRacunare();
            }
        });

        frmMain.getBtnIzlogujSe().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izlogujSe();
            }

            private void izlogujSe() {
                Komunikacija.getInstance().izlogujSe();
                frmMain.dispose();
                MainCordinator.getInstance().otvoriFrmLogin();
            }
        });

        frmMain.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    gasenjeAplikacije();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmMain, "Aplikacija ce biti ugasena!", "Good bye!", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            private void gasenjeAplikacije() throws Exception {
                Komunikacija.getInstance().ugasiAplikaciju();
                frmMain.dispose();
            }

        });

    }

    public FrmMain getFrmMain() {
        return frmMain;
    }

}
