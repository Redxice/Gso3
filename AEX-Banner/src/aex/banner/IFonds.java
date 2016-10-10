/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aex.banner;

import java.io.Serializable;

/**
 *
 * @author redxice
 */
public interface IFonds extends Serializable{
    public String Getnaam();
    public double getKoers();
    public void setKoers(double koers);
}
