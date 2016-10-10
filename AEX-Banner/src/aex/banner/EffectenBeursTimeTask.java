/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aex.banner;

import java.util.TimerTask;

/**
 *
 * @author redxice
 */
public class EffectenBeursTimeTask extends TimerTask{
    private MockEffectenbeurs effectenbeurs;
    
    public EffectenBeursTimeTask(MockEffectenbeurs effectenbeurs){
        this.effectenbeurs =effectenbeurs ;
    }
    
 
    @Override
    public void run() {
        this.effectenbeurs.UpdateKoers();
    }

}
    
 

