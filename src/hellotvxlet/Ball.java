/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;


import java.awt.*;
import java.awt.MediaTracker;
import org.havi.ui.HVisible;


public class Ball extends Sprite{

Image mijnImg;

int bx;
int by;
int Ygr;
int Xgr;
int Xr = -1;
int Yr = -1;
    public Ball(int x, int y, int sizeX, int sizeY){
        //moet verplicht op eerste regel de super aanroepen
        super(x,y,sizeX,  sizeY);
        this.setBordersEnabled(true);
    bx= x;
    by = y;
    Xgr = sizeX;
    Ygr = sizeY;
     //mijnImg=   this.getToolkit().getImage("bal.png");
     
      MediaTracker mt = new MediaTracker(this);
     mt.addImage(mijnImg, 1);
     try{
         mt.waitForAll();
       
     }
     catch(Exception ex){
     ex.printStackTrace();
     
     }
     
     this.setBackground(Color.RED);
     //this.setSize(msizeX,msizeY);
     this.setBackgroundMode(HVisible.BACKGROUND_FILL);
     this.setBounds(x, y, Xgr, Ygr);
  
    
     mt.isErrorAny();
     }
    
     public void update(int tijd) {
      by = by + Yr;
      bx = bx + Xr;
      if(bx<=0){
            Xr = 1;
        }
      if(bx >= 720-this.getWidth()){
            Xr= -1;
        }
      if(by<=0){
            Yr = 1;
        }
      this.setLocation(bx, by);
    }
    public void setXDir(int x) {
        Xr= x;
    }

    public void setYDir(int y) {
        Yr = y;
    }
    
    public int getXDir() {
        return Xr;
    }
    
    Rectangle getRect() {
        return new Rectangle(bx, by,Xgr, Ygr);
    }
}