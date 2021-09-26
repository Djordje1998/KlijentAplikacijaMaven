/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kont;

import communication.Operation;
import domen.Benchmark;
import domen.Komponenta;
import domen.Korisnik;
import domen.Ocena;
import domen.Racunar;
import domen.StressTest;
import domen.TipKomponente;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import communication.Receiver;
import communication.Sender;
import communication.object.Request;
import communication.object.Response;
import util.PropertyConst;
import util.PropertyRead;

/**
 *
 * @author DarkForce
 */
public class Komunikacija {

    private static Komunikacija instance;
    private Socket socket;
    private Sender sender;
    private Receiver receiver;

    private Komunikacija() {
    }

    public static Komunikacija getInstance() {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    public void izlogujSe() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ugasiAplikaciju() throws Exception {
        Request zahtev = new Request();
        zahtev.setOperation(Operation.UGASI);
        sender.send(zahtev);

        Response odgovor = (Response) receiver.recieve();
        if (odgovor.getException() != null) {
            throw odgovor.getException();
        }
    }

    public Korisnik prijaviSe(String korisnickoIme, String lozinka) throws IOException, Exception {
        PropertyRead read = new PropertyRead();
        socket = new Socket(read.getString(PropertyConst.ADRESA), read.getInteger(PropertyConst.PORT));
        sender = new Sender(socket);
        receiver = new Receiver(socket);

        String[] imeILozinka = {korisnickoIme, lozinka};
        Request zahtev = new Request();
        zahtev.setOperation(Operation.LOGOVANJE);
        zahtev.setPaket(imeILozinka);
        sender.send(zahtev);

        Response odgovor = (Response) receiver.recieve();
        if (odgovor.getException() != null) {
            throw odgovor.getException();
        } else {
            return (Korisnik) odgovor.getResult();
        }
    }

    public ArrayList<TipKomponente> vratiTipoveKomponenti() throws Exception {
        Request zahtev = new Request();
        zahtev.setOperation(Operation.VRATI_TIPOVE_KOMPONENTI);
        sender.send(zahtev);

        Response odgovor = (Response) receiver.recieve();
        if (odgovor.getException() != null) {
            throw odgovor.getException();
        } else {
            return (ArrayList<TipKomponente>) odgovor.getResult();
        }
    }

    public ArrayList<StressTest> vratiTestove() throws Exception {
        Request zahtev = new Request();
        zahtev.setOperation(Operation.VRATI_STRESS_TESTOVE);
        sender.send(zahtev);

        Response odgovor = (Response) receiver.recieve();
        if (odgovor.getException() != null) {
            throw odgovor.getException();
        } else {
            return (ArrayList<StressTest>) odgovor.getResult();
        }
    }

    public void kreirajKomponentu(Komponenta komponenta) throws Exception {
        Request zahtev = new Request();
        zahtev.setOperation(Operation.KREIRAJ_KOMPONENTU);
        zahtev.setPaket(komponenta);
        sender.send(zahtev);

        Response odgovor = (Response) receiver.recieve();
        if (odgovor.getException() != null) {
            throw odgovor.getException();
        }
    }

    public void obrisiKomponentu(Komponenta komponenta) throws Exception {
        Request zahtev = new Request();
        zahtev.setOperation(Operation.OBRISI_KOMPONENTU);
        zahtev.setPaket(komponenta);
        sender.send(zahtev);

        Response odgovor = (Response) receiver.recieve();
        if (odgovor.getException() != null) {
            throw odgovor.getException();
        }
    }

    public ArrayList<Komponenta> pretragaKomponenti(String naziv) throws Exception {
        Request zahtev = new Request();
        zahtev.setOperation(Operation.PRETRAZI_KOMPONENTU);
        zahtev.setPaket(naziv);
        sender.send(zahtev);

        Response odgovor = (Response) receiver.recieve();
        if (odgovor.getException() != null) {
            throw odgovor.getException();
        } else {
            return (ArrayList<Komponenta>) odgovor.getResult();
        }
    }

    public void izmeniKomponentu(Komponenta nova) throws Exception {
        Request zahtev = new Request();
        zahtev.setOperation(Operation.IZMENI_KOMPONENTU);
        zahtev.setPaket(nova);
        sender.send(zahtev);

        Response odgovor = (Response) receiver.recieve();
        if (odgovor.getException() != null) {
            throw odgovor.getException();
        }
    }

    public void kreirajRacunar(Racunar racunar) throws Exception {
        Request zahtev = new Request();
        zahtev.setOperation(Operation.KREIRAJ_RACUNAR);
        zahtev.setPaket(racunar);
        sender.send(zahtev);

        Response odgovor = (Response) receiver.recieve();
        if (odgovor.getException() != null) {
            throw odgovor.getException();
        }
    }

    public void izmeniRacunar(Racunar novi) throws Exception {
        Request zahtev = new Request();
        zahtev.setOperation(Operation.IZMENI_RACUNAR);
        zahtev.setPaket(novi);
        sender.send(zahtev);

        Response odgovor = (Response) receiver.recieve();
        if (odgovor.getException() != null) {
            throw odgovor.getException();
        }
    }

    public void obrisiRacunar(Racunar racunar) throws Exception {
        Request zahtev = new Request();
        zahtev.setOperation(Operation.OBRISI_RACUNAR);
        zahtev.setPaket(racunar);
        sender.send(zahtev);

        Response odgovor = (Response) receiver.recieve();
        if (odgovor.getException() != null) {
            throw odgovor.getException();
        }
    }

    public ArrayList<Racunar> pretragaRacunara(String naziv) throws Exception {
        Request zahtev = new Request();
        zahtev.setOperation(Operation.PRETRAZI_RACUNAR);
        zahtev.setPaket(naziv);
        sender.send(zahtev);

        Response odgovor = (Response) receiver.recieve();
        if (odgovor.getException() != null) {
            throw odgovor.getException();
        } else {
            return (ArrayList<Racunar>) odgovor.getResult();
        }
    }

    public void sacuvajBenchmark(Benchmark benchmark) throws Exception {
        Request zahtev = new Request();
        zahtev.setOperation(Operation.KREIRAJ_BENCHMARK);
        zahtev.setPaket(benchmark);
        sender.send(zahtev);

        Response odgovor = (Response) receiver.recieve();
        if (odgovor.getException() != null) {
            throw odgovor.getException();
        }
    }

    public void sacuvajOcenu(Ocena ocena) throws Exception {
        Request zahtev = new Request();
        zahtev.setOperation(Operation.KREIRAJ_OCENU);
        zahtev.setPaket(ocena);
        sender.send(zahtev);

        Response odgovor = (Response) receiver.recieve();
        if (odgovor.getException() != null) {
            throw odgovor.getException();
        }
    }

    public int oceniKomponentu(Komponenta prva, StressTest test) throws Exception {
        Request zahtev = new Request();
        zahtev.setOperation(Operation.OCENI_KOMPONENTU);
        Object[] komponentaTest = {prva, test};
        zahtev.setPaket(komponentaTest);
        sender.send(zahtev);

        Response odgovor = (Response) receiver.recieve();
        if (odgovor.getException() != null) {
            throw odgovor.getException();
        } else {
            return (int) odgovor.getResult();
        }
    }

    public String vratiZakljucakKomponente(int prvaOcena, int drugaOcena) throws Exception {
        Request zahtev = new Request();
        zahtev.setOperation(Operation.VRATI_ZAKLJUCAK_KOMPONENTI);
        int[] ocene = {prvaOcena, drugaOcena};
        zahtev.setPaket(ocene);
        sender.send(zahtev);

        Response odgovor = (Response) receiver.recieve();
        if (odgovor.getException() != null) {
            throw odgovor.getException();
        } else {
            return (String) odgovor.getResult();
        }
    }

    public int oceniRacunar(Racunar drugi, StressTest test) throws Exception {
        Request zahtev = new Request();
        zahtev.setOperation(Operation.OCENI_RACUNAR);
        Object[] racunarTest = {drugi, test};
        zahtev.setPaket(racunarTest);
        sender.send(zahtev);

        Response odgovor = (Response) receiver.recieve();
        if (odgovor.getException() != null) {
            throw odgovor.getException();
        } else {
            return (int) odgovor.getResult();
        }
    }

    public String vratiZakljucakRacunar(int prvaOcena, int drugaOcena) throws Exception {
        Request zahtev = new Request();
        zahtev.setOperation(Operation.VRATI_ZAKLJUCAK_RACUNARA);
        int[] ocene = {prvaOcena, drugaOcena};
        zahtev.setPaket(ocene);
        sender.send(zahtev);

        Response odgovor = (Response) receiver.recieve();
        if (odgovor.getException() != null) {
            throw odgovor.getException();
        } else {
            return (String) odgovor.getResult();
        }
    }

}
