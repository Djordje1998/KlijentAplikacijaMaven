/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.KlijentAplikacija.forme;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

/**
 *
 * @author DarkForce
 */
public class FrmMain extends javax.swing.JFrame {

    /**
     * Creates new form GlavnaForma
     */
    public FrmMain() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblUlogovaniKorisnik = new javax.swing.JLabel();
        btnIzlogujSe = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmKomponente = new javax.swing.JMenu();
        jmiKomponentaKreiranje = new javax.swing.JMenuItem();
        jmiKomponentaPretrazivanje = new javax.swing.JMenuItem();
        jmiKomponentaOcenjivanje = new javax.swing.JMenuItem();
        jmRacunar = new javax.swing.JMenu();
        jmiRacunarKreiranje = new javax.swing.JMenuItem();
        jmiRacunarPretrazivanje = new javax.swing.JMenuItem();
        jmUporedi = new javax.swing.JMenu();
        jmiUporediKomponente = new javax.swing.JMenuItem();
        jmiUporediRacunare = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Glavni Meni");

        //lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("rs/ac/bg/fon/ai/KlijentAplikacija/res/Logo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblLogo)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Ulogovani korisnik:");

        lblUlogovaniKorisnik.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblUlogovaniKorisnik.setText("jLabel2");

        btnIzlogujSe.setText("Izloguj se");

        jMenuBar1.setToolTipText("Klijentska aplikacija");

        jmKomponente.setText("Komponenta");

        jmiKomponentaKreiranje.setText("Kreiranje");
        jmKomponente.add(jmiKomponentaKreiranje);

        jmiKomponentaPretrazivanje.setText("Pretrazivanje");
        jmKomponente.add(jmiKomponentaPretrazivanje);

        jmiKomponentaOcenjivanje.setText("Oceni");
        jmKomponente.add(jmiKomponentaOcenjivanje);

        jMenuBar1.add(jmKomponente);

        jmRacunar.setText("Racunar");

        jmiRacunarKreiranje.setText("Kreiranje");
        jmRacunar.add(jmiRacunarKreiranje);

        jmiRacunarPretrazivanje.setText("Pretrazivanje");
        jmRacunar.add(jmiRacunarPretrazivanje);

        jMenuBar1.add(jmRacunar);

        jmUporedi.setText("Uporedi");

        jmiUporediKomponente.setText("Uporedi komponente");
        jmUporedi.add(jmiUporediKomponente);

        jmiUporediRacunare.setText("Uporedi racunare");
        jmUporedi.add(jmiUporediRacunare);

        jMenuBar1.add(jmUporedi);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblUlogovaniKorisnik, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnIzlogujSe)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnIzlogujSe, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lblUlogovaniKorisnik)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIzlogujSe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu jmKomponente;
    private javax.swing.JMenu jmRacunar;
    private javax.swing.JMenu jmUporedi;
    private javax.swing.JMenuItem jmiKomponentaKreiranje;
    private javax.swing.JMenuItem jmiKomponentaOcenjivanje;
    private javax.swing.JMenuItem jmiKomponentaPretrazivanje;
    private javax.swing.JMenuItem jmiRacunarKreiranje;
    private javax.swing.JMenuItem jmiRacunarPretrazivanje;
    private javax.swing.JMenuItem jmiUporediKomponente;
    private javax.swing.JMenuItem jmiUporediRacunare;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblUlogovaniKorisnik;
    // End of variables declaration//GEN-END:variables

    public JMenuItem getJmiKomponentaKreiranje() {
        return jmiKomponentaKreiranje;
    }

    public JMenuItem getJmiKomponentaOcenjivanje() {
        return jmiKomponentaOcenjivanje;
    }

    public JMenuItem getJmiKomponentaPretrazivanje() {
        return jmiKomponentaPretrazivanje;
    }

    public JMenuItem getJmiRacunarKreiranje() {
        return jmiRacunarKreiranje;
    }

    public JMenuItem getJmiRacunarPretrazivanje() {
        return jmiRacunarPretrazivanje;
    }

    public JMenuItem getJmiUporediRacunare() {
        return jmiUporediRacunare;
    }

    public JLabel getLblUlogovaniKorisnik() {
        return lblUlogovaniKorisnik;
    }

    public JMenuItem getJmiUporediKomponente() {
        return jmiUporediKomponente;
    }

    public JButton getBtnIzlogujSe() {
        return btnIzlogujSe;
    }

    public JLabel getLblLogo() {
        return lblLogo;
    }
    
}
