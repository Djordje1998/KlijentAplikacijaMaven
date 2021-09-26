/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import forme.login.FrmLoginConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import util.PropertyConst;
import util.PropertyRead;
import util.PropertyWrite;

/**
 *
 * @author DarkForce
 */
public class LoginConfigurationController {

    private final FrmLoginConfiguration frmLoginConfiguration;

    public LoginConfigurationController(FrmLoginConfiguration frmLoginConfiguration) {
        this.frmLoginConfiguration = frmLoginConfiguration;
        addActionListeners();
    }

    public void openForm() {
        pripremiFormu();
        frmLoginConfiguration.setLocationRelativeTo(null);
        frmLoginConfiguration.setVisible(true);
    }

    private void pripremiFormu() {
        PropertyRead read = new PropertyRead();
        frmLoginConfiguration.getTxtAdresa().setText(read.getString(PropertyConst.ADRESA));
        frmLoginConfiguration.getTxtPort().setText(read.getString(PropertyConst.PORT));
    }

    private void addActionListeners() {
        frmLoginConfiguration.getBtnSacuvajPodesavanja().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSacuvajPodesavanja();
            }

            private void btnSacuvajPodesavanja() {
                String adresa = frmLoginConfiguration.getTxtAdresa().getText().trim();
                String port = frmLoginConfiguration.getTxtPort().getText().trim();
                if (adresa.isEmpty() || port.isEmpty()) {
                    JOptionPane.showMessageDialog(frmLoginConfiguration, "Moraju sva polja biti popunjena", "Greska pri cuvanju podesavanja", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                PropertyWrite write = new PropertyWrite();
                write.setValues(PropertyConst.ADRESA, adresa);
                write.setValues(PropertyConst.PORT, port);
                write.writeProperty();
                JOptionPane.showMessageDialog(frmLoginConfiguration, "Uspesno sacuvana podesavanja", "Uspesno sacuvano", JOptionPane.INFORMATION_MESSAGE);
                frmLoginConfiguration.dispose();
            }
        });
    }

}
