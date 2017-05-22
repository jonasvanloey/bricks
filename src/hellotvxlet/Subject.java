/*
 * gaat de observers aansturen
 */

package hellotvxlet;

import java.util.ArrayList;
import java.util.TimerTask;

/**
 *
 * @author student
 */
public class Subject extends TimerTask implements SubjectInterface {
        ArrayList  al = new ArrayList();
        int time = 0;
     public void run() {
       
         time++;
         for(int i =0;i<al.size();i++){
               //System.out.println("run:"+);
             ((ObserverInterface)al.get(i)).update(time);
         }
       
    }

    public void register(ObserverInterface oi) {
       al.add(oi);
    }

    public void unregister(ObserverInterface oi) {
       al.remove(oi);
    }

}