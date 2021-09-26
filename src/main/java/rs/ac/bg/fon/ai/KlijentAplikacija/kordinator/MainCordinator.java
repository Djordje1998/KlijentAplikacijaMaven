/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kordinator;

import forme.FrmMain;
import forme.FrmPretrazivanje;
import forme.komponente.FrmKomponenta;
import forme.komponente.FrmKomponentaOcenjivanje;
import forme.login.FrmLogin;
import forme.login.FrmLoginConfiguration;
import forme.racunar.FrmRacunar;
import forme.uporedjivanje.FrmUporedjivanjeKomponenti;
import forme.uporedjivanje.FrmUporedjivanjeRacunara;
import forme.util.FrmModOtvaranja;
import java.util.HashMap;
import java.util.Map;
import kontroler.KomponentaController;
import kontroler.KomponentaOcenjivanjeController;
import kontroler.PretrazivanjeController;
import kontroler.LoginConfigurationController;
import kontroler.LoginController;
import kontroler.MainController;
import kontroler.RacunarController;
import kontroler.UporedjivanjeKomponentiController;
import kontroler.UporedjivanjeRacunaraController;

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
