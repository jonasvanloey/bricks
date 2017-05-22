/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.Color;
import java.awt.Image;

import java.awt.MediaTracker;
import java.awt.Rectangle;
import org.havi.ui.HVisible;


public class Brick extends Sprite{

Image mijnImg;
public boolean destroyed; 
int mx;
int my;
int mhoogte;
int mbreedte;
Color mColor;
    private int richtingX;
    public Brick(int x, int y, int breedte, int hoogte, Color color){
        //moet verplicht op eerste regel de super aanroepen
        super(x,y,breedte,  hoogte);
    mx= x;
    my = y;
    mbreedte = breedte;
    mhoogte = hoogte;
    mColor = color;
     mijnImg=   this.getToolkit().getImage("spaceship.png");
     MediaTracker mt = new MediaTracker(this);
     mt.addImage(mijnImg, HVisible.NORMAL_STATE);
     try{
         mt.waitForAll();
     }
     catch(Exception ex){
     ex.printStackTrace();
     }
   //  this.setGraphicContent(mijnImg, HVisible.NORMAL_STATE);
    // this.setSize(mijnImg.getWidth(this),mijnImg.getHeight(this));
    
    this.setBackground(mColor);
    this.setBackgroundMode(HVisible.BACKGROUND_FILL);
     
     }
     public void update(int tijd) {
      
      
    }
     
     public void MoveLeft(){
     mx -= 10;
    
     if(mx <= 0){
        mx =0;
     }
      this.setBounds(mx,my,mbreedte,mhoogte);
     }
     
     
     public void MoveRight(){
     
     mx += 10;
     if(mx >= 520){
        mx =520;
     }
      this.setBounds(mx,my,mhoogte,mbreedte);
     }
     
     Rectangle getRect() {
        return new Rectangle(mx, my,
              mbreedte, mhoogte);
    }
     public boolean isDestroyed() {
        
        return destroyed;
    }
     public void setDestroyed(boolean val) {
        destroyed = val;
    }
    
    

    public int getXDir() {
        return richtingX;
    }
}
    