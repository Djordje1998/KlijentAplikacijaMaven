/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.KlijentAplikacija.kontroler;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.domen.Korisnik;
import rs.ac.bg.fon.ai.KlijentAplikacija.forme.login.FrmLogin;
import rs.ac.bg.fon.ai.KlijentAplikacija.forme.util.ParamConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ai.KlijentAplikacija.kont.Komunikacija;
import rs.ac.bg.fon.ai.KlijentAplikacija.kordinator.MainCordinator;

/**
 *
 * @author DarkForce
 */
public class LoginController {

    private final FrmLogin frmLogin;

    public LoginController(FrmLogin frmLogin) {
        this.frmLogin = frmLogin;
        addActionListeners();
    }

    public void openForm() {
        frmLogin.setLocationRelativeTo(null);
        frmLogin.setVisible(true);
    }

    private void addActionListeners() {
        frmLogin.getBtnPrijava().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnPrijava();
            }

            private void btnPrijava() {
                String korisnickoIme = frmLogin.getTxtKorisnickoIme().getText().trim();
                String lozinka = String.valueOf(frmLogin.getTxtLozinka().getPassword());

                try {
                    Korisnik korisnik = Komunikacija.getInstance().prijaviSe(korisnickoIme, lozinka);
                    MainCordinator.getInstance().addParam(ParamConstants.ULOGOVANI_KORISNIK, korisnik);
                    JOptionPane.showMessageDialog(frmLogin, "Dobrodosao " + korisnik.getIme() + " " + korisnik.getPrezime() + " !", "Uspesna prijava", JOptionPane.INFORMATION_MESSAGE);
                    MainCordinator.getInstance().otvoriFrmMain();
                    frmLogin.dispose();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frmLogin, "Aplikacija ne moze da se poveze na server", "Neuspesno povezivanje sa serverom", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    //"Ne postoji korisnik sa unetim podacima "
                    JOptionPane.showMessageDialog(frmLogin, ex.getMessage(), "Neuspesna prijava", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmLogin.getJmiServer().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jmiServer();
            }

            private void jmiServer() {
                MainCordinator.getInstance().otvoriFrmLoginConfiguration(frmLogin);
            }
        });
    }

}
