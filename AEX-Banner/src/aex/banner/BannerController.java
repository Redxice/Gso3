package aex.banner;

import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.IRemotePublisherForListener;
import java.beans.PropertyChangeEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author redxice
 */
public class BannerController extends UnicastRemoteObject implements IRemotePropertyListener {

    private AEXBanner banner;
    private IEffectenbeurs effectenbeurs;
    private Timer pollingTimer;
    private String BannerText;
    private IRemotePublisherForListener publisher;
    private List<IFonds> fondsen;

    public BannerController(AEXBanner banner) throws RemoteException, NotBoundException {

        this.banner = banner;
        // Start polling timer: update banner every two seconds
//        pollingTimer = new Timer();
//        pollingTimer.schedule(new KoersTimeTask(this),0,2000);

        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            publisher = (IRemotePublisherForListener) registry.lookup("AEX");
            publisher.subscribeRemoteListener(this, "fondsen");
        } catch (RemoteException exc) {
            System.out.println("FAAL");
            BannerText = "AEX SERVER UNREACHABLE";
        }

    }

    public AEXBanner GetBanner() {
        return this.banner;
    }

    public String GetBannerText() {
        return BannerText;
    }

    public void Update() throws NotBoundException {
        BannerText = "";
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            IEffectenbeurs effectenbeurs = (IEffectenbeurs) registry.lookup("AEX");
            List<IFonds> fondsen = effectenbeurs.getKoersen();
            for (IFonds fond : fondsen) {
                BannerText += fond.toString() + " ";
            }
        } catch (RemoteException ex) {
            BannerText = "AEX SERVER UNREACHABLE";
            Logger.getLogger(BannerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Stop banner controller
    public void stop() {
        pollingTimer.cancel();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException {
        BannerText = "";
        fondsen = new ArrayList<IFonds>();
        
            System.out.println("PING! " + evt.toString());
            fondsen.addAll((List<IFonds>) evt.getNewValue());
            System.out.println(fondsen.toString());
            for (IFonds fond : fondsen) {
                BannerText += fond.toString() + " ";
            }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                banner.setKoersen(BannerText);
            }

        });

    }
}
