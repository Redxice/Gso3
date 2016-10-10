/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aex.banner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author redxice
 */
public class MockEffectenbeurs  implements IEffectenbeurs{
private List<IFonds> fonds = new ArrayList<>();
Random RD = new Random();
Timer t = new Timer();

    public MockEffectenbeurs(){
         
         IFonds shell = new Fond("shell",RD.nextDouble());
         IFonds Unilever = new Fond("Unilever",RD.nextDouble());
         fonds.add(shell);
         fonds.add(Unilever);
         this.t.schedule(new EffectenBeursTimeTask(this),0,2000);
       
    }
    @Override
    public List<IFonds> getKoersen() {
      return fonds;
    }

    
   public void UpdateKoers(){
       
      for(IFonds fond :fonds){
         fond.setKoers(RD.nextDouble());
      }
   
}
     public void stop(){
        this.t.cancel();
    }

     
  
}

