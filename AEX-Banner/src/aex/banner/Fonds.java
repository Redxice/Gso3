/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aex.banner;

import java.io.Serializable;
import static java.lang.Math.round;
import static java.lang.Math.round;
import static java.lang.Math.round;
import static java.lang.Math.round;
import static java.lang.Math.round;
import static java.lang.Math.round;
import static java.lang.Math.round;
import static java.lang.Math.round;



/**
 *
 * @author redxice
 */
public class Fonds implements IFonds {

    private String naam;
    private double koers;
    
    public Fonds(String naam, double koers ){
        this.naam = naam;
        this.koers= koers;
    }
    @Override
    public String Getnaam() {
       return this.naam;
    }

    @Override
    public double getKoers() {
        return this.koers;
    }
    @Override
    public void setKoers(double koers){
        koers = round(koers*100);
        koers = koers/100;
        this.koers = koers;
    }

    @Override
    public String toString() {
        
        return  naam + " :" + koers;
    }
    
   
}
