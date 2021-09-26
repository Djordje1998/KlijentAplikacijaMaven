/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.KlijentAplikacija.kordinator;

import rs.ac.bg.fon.ai.KlijentAplikacija.forme.FrmMain;
import rs.ac.bg.fon.ai.KlijentAplikacija.forme.FrmPretrazivanje;
import rs.ac.bg.fon.ai.KlijentAplikacija.forme.komponente.FrmKomponenta;
import rs.ac.bg.fon.ai.KlijentAplikacija.forme.komponente.FrmKomponentaOcenjivanje;
import rs.ac.bg.fon.ai.KlijentAplikacija.forme.login.FrmLogin;
import rs.ac.bg.fon.ai.KlijentAplikacija.forme.login.FrmLoginConfiguration;
import rs.ac.bg.fon.ai.KlijentAplikacija.forme.racunar.FrmRacunar;
import rs.ac.bg.fon.ai.KlijentAplikacija.forme.uporedjivanje.FrmUporedjivanjeKomponenti;
import rs.ac.bg.fon.ai.KlijentAplikacija.forme.uporedjivanje.FrmUporedjivanjeRacunara;
import rs.ac.bg.fon.ai.KlijentAplikacija.forme.util.FrmModOtvaranja;
import java.util.HashMap;
import java.util.Map;
import rs.ac.bg.fon.ai.KlijentAplikacija.kontroler.KomponentaController;
import rs.ac.bg.fon.ai.KlijentAplikacija.kontroler.KomponentaOcenjivanjeController;
import rs.ac.bg.fon.ai.KlijentAplikacija.kontroler.PretrazivanjeController;
import rs.ac.bg.fon.ai.KlijentAplikacija.kontroler.LoginConfigurationController;
import rs.ac.bg.fon.ai.KlijentAplikacija.kontroler.LoginController;
import rs.ac.bg.fon.ai.KlijentAplikacija.kontroler.MainController;
import rs.ac.bg.fon.ai.KlijentAplikacija.kontroler.RacunarController;
import rs.ac.bg.fon.ai.KlijentAplikacija.kontroler.UporedjivanjeKomponentiController;
import rs.ac.bg.fon.ai.KlijentAplikacija.kontroler.UporedjivanjeRacunaraController;

/**
 *
 * @author DarkForce
 */
public class MainCordinator {

    private static MainCordinator instance;

    private final MainController mainController;
    private final Map<String, Object> params;

    private MainCordinator() {
        mainController = new MainController(new FrmMain());
        params = new HashMap<>();
    }

    public static MainCordinator getInstance() {
        if (instance == null) {
            instance = new MainCordinator();
        }
        return instance;
    }

    // main
    public void otvoriFrmMain() {
        mainController.openForm();
    }

    // login
    public void otvoriFrmLogin() {
        LoginController loginController = new LoginController(new FrmLogin());
        loginController.openForm();
    }

    public void otvoriFrmLoginConfiguration(FrmLogin frmLogin) {
        LoginConfigurationController configurationController = new LoginConfigurationController(new FrmLoginConfiguration(frmLogin, true));
        configurationController.openForm();
    }

    // komponente
    public void otvoriFrmKomponentaKreiranje() {
        KomponentaController komponentaKreiranjeController = new KomponentaController(new FrmKomponenta(mainController.getFrmMain(), true), FrmModOtvaranja.KOMPONENTA_KREIRANJE);
        komponentaKreiranjeController.openForm();
    }

    public void otvoriFrmKomponentaPretrazivanje() {
        PretrazivanjeController komponentaPretrazivanjeController = new PretrazivanjeController(new FrmPretrazivanje(), FrmModOtvaranja.KOMPONENTA_PRETRAZIVANJE);
        komponentaPretrazivanjeController.openForm();
    }

    public void otvoriFrmKomponentaIzmena() {
        KomponentaController komponentaKreiranjeController = new KomponentaController(new FrmKomponenta(mainController.getFrmMain(), true), FrmModOtvaranja.KOMPONENTA_IZMENA);
        komponentaKreiranjeController.openForm();
    }

    public void otvoriFrmKomponentaOcenjivanje() {
        KomponentaOcenjivanjeController komponentaOcenjivanjeController = new KomponentaOcenjivanjeController(new FrmKomponentaOcenjivanje());
        komponentaOcenjivanjeController.openForm();
    }

    // racunar
    public void otvoriFrmRacunarKreiranje() {
        RacunarController racunarController = new RacunarController(new FrmRacunar(mainController.getFrmMain(), true), FrmModOtvaranja.RACUNAR_KREIRANJE);
        racunarController.openForm();
    }

    public void otvoriFrmRacunarPretrazivanje() {
        PretrazivanjeController pretrazivanjeController = new PretrazivanjeController(new FrmPretrazivanje(), FrmModOtvaranja.RACUNAR_PRETRAZIVANJE);
        pretrazivanjeController.openForm();
    }

    public void otvoriFrmRacunarIzmena() {
        RacunarController racunarController = new RacunarController(new FrmRacunar(mainController.getFrmMain(), true), FrmModOtvaranja.RACUNAR_IZMENA);
        racunarController.openForm();
    }

    // uporedi
    public void otvoriFrmUporediKomponente() {
        UporedjivanjeKomponentiController uporedjivanjeKomponentiController = new UporedjivanjeKomponentiController(new FrmUporedjivanjeKomponenti());
        uporedjivanjeKomponentiController.openForm();
    }

    public void otvoriFrmUporediRacunare() {
        UporedjivanjeRacunaraController uporedjivanjeRacunaraController = new UporedjivanjeRacunaraController(new FrmUporedjivanjeRacunara());
        uporedjivanjeRacunaraController.openForm();
    }

    public MainController getMainController() {
        return mainController;
    }

    public void addParam(String key, Object value) {
        params.put(key, value);
    }

    public Object getParam(String key) {
        return params.get(key);
    }

}
