/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.KlijentAplikacija.kontroler;

import rs.ac.bg.fon.ai.KlijentAplikacija.forme.login.FrmLoginConfiguration;
import rs.ac.bg.fon.ai.KlijentAplikacija.json.JsonConfigFormat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JOptionPane;

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
//		  STARI KOD
//        PropertyRead read = new PropertyRead();
//        frmLoginConfiguration.getTxtAdresa().setText(read.getString(PropertyConst.ADRESA));
//        frmLoginConfiguration.getTxtPort().setText(read.getString(PropertyConst.PORT));
        
		try {
			JsonConfigFormat read = JsonConfigFormat.readFromFile();
			frmLoginConfiguration.getTxtAdresa().setText(read.getUrl());
	        frmLoginConfiguration.getTxtPort().setText(String.valueOf(read.getPort()));
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frmLoginConfiguration, e.getMessage(), "Greska pri citanju podesavanja iz fajla", JOptionPane.ERROR_MESSAGE);
            return;
		}

    }

    private void addActionListeners() {
        frmLoginConfiguration.getBtnSacuvajPodesavanja().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSacuvajPodesavanja();
            }

            private void btnSacuvajPodesavanja() {
                String adresa = frmLoginConfiguration.getTxtAdresa().getText().trim();
                String portS = frmLoginConfiguration.getTxtPort().getText().trim();
                
                if (adresa.isEmpty() || portS.isEmpty()) {
                    JOptionPane.showMessageDialog(frmLoginConfiguration, "Moraju sva polja biti popunjena", "Greska pri cuvanju podesavanja", JOptionPane.ERROR_MESSAGE);
                    return;
                }
//				  STARI KOD
//                PropertyWrite write = new PropertyWrite();
//                write.setValues(PropertyConst.ADRESA, adresa);
//                write.setValues(PropertyConst.PORT, port);
//                write.writeProperty();
                
                try {
                	int port = Integer.parseInt(portS);
                    JsonConfigFormat write = new JsonConfigFormat();
                    write.setPort(port);
                    write.setUrl(adresa);
                    write.writeToFile();
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(frmLoginConfiguration, "Port mora da bude broj!", "Greska pri cuvanju podesavanja", JOptionPane.ERROR_MESSAGE);
	                return;
				}catch (Exception e) {
					JOptionPane.showMessageDialog(frmLoginConfiguration, e.getMessage(), "Greska pri cuvanju podesavanja", JOptionPane.ERROR_MESSAGE);
	                return;
				}
                
                JOptionPane.showMessageDialog(frmLoginConfiguration, "Uspesno sacuvana podesavanja", "Uspesno sacuvano", JOptionPane.INFORMATION_MESSAGE);
                frmLoginConfiguration.dispose();
            }
        });
    }

}
