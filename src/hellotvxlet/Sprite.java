/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import org.havi.ui.HStaticIcon;

/**
 *
 * @author student
 */
public abstract class Sprite extends HStaticIcon implements ObserverInterface{

    
    
    public Sprite(int x, int y,int Xgr,int Ygr){
        super();
        this.setLocation(x, y);
        
        this.setSize(Xgr,Ygr);
  
    }

   
}
