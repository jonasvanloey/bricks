package hellotvxlet;

import org.havi.ui.*;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Color;
import org.dvb.event.*;
import org.havi.ui.event.*;
import java.util.Timer;
import java.util.Random;
import java.awt.event.*;
import javax.tv.xlet.*;




public abstract class HelloTVXlet implements Xlet,UserEventListener, ObserverInterface, HActionListener {
    static HScene scene = null;
    static Subject publisher = null;
    Brick brick1 = null;
    Rectangle[][] smallbrickranden = new Rectangle[7][7];
    Brick[][] bricks = new Brick[7][7];
    Ball ball = null;
    Random rand = new Random();
    private HTextButton reset;
    Timer timer;
    Brick ending = null;

  
    public HelloTVXlet() {
        
    }
    public static HScene getScene(){
    return scene;
    }
    public static Subject getPublisher(){
    return publisher;
    }

    public void initXlet(XletContext context) {
      scene  = HSceneFactory.getInstance().getDefaultHScene();
        
         publisher = new Subject();
        timer = new Timer();
        timer.scheduleAtFixedRate(publisher,0,10); //elke 10ms
        
        brick1 = new Brick(180,450,200,20, Color.GREEN);
        scene.add(brick1);
        int breedte = 100;
        int hoogte = 15;
               for(int i=0;i<7;i++){
                   for(int j=0;j<7;j++){
                      Brick bricksmall = new Brick(30+132*i,40+31*j,breedte,hoogte, Color.YELLOW);
                      scene.add(bricksmall);
                      smallbrickranden[i][j] = bricksmall.getRect();
                      bricks[i][j] = bricksmall;
                      
                   }
               }
        ball=  new Ball(100,300,20,20);
        scene.add(ball);
        scene.validate();
        scene.setVisible(true);
        publisher.register(this);
     
    }

   public void startXlet() throws XletStateChangeException {
        //event manager aanvragen
        EventManager  manager = EventManager.getInstance();
        UserEventRepository repos = new UserEventRepository("controls");
        repos.addAllArrowKeys();
        repos.addKey(HRcEvent.VK_SPACE);
        manager.addUserEventListener(this, repos);     
    }

    
    public void userEventReceived(UserEvent e) {
      if(e.getType() == KeyEvent.KEY_PRESSED){
      switch(e.getCode()){
          case HRcEvent.VK_LEFT: 
              brick1.MoveLeft();
              break;
           case HRcEvent.VK_RIGHT: 
              brick1.MoveRight();
              break;   
            case HRcEvent.VK_SPACE:  
               publisher.register(ball);
              break;
            }
      
      }
    }
    public void CollideBalk(){
        Rectangle ballranden = ball.getRect();
        Rectangle brickranden = brick1.getRect();
        if(brickranden.intersects(brickranden ))
        {
            ball.setYDir(-1);
        }
        if(brickranden .y > 720)
        {
            timer.cancel();
            endGame();
        }
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                    if(ballranden.intersects(smallbrickranden[i][j])){
                        int ballLeft =  ballranden.x;
                        int ballHeight =  ballranden.height;
                        int ballWidth =  ballranden.width;
                        int ballTop =  ballranden.y;
                        Point pointRight = new Point(ballranden.x + ballranden.width + 1,ballranden.y);
                        Point pointLeft = new Point(ballranden.x - 1, ballranden.y);
                        Point pointTop = new Point(ballranden.x, ballranden.y - 1);
                        Point pointBottom = new Point(ballranden.x, ballranden.y + ballranden.height + 1);
                        if(!bricks[i][j].isDestroyed()){
                            if (smallbrickranden[i][j].contains(pointRight)) {
                            ball.setXDir(-1); 
                                
                            } else if (smallbrickranden[i][j].contains(pointLeft)) {
                            ball.setXDir(1);
                            }
                            if (smallbrickranden[i][j].contains(pointTop)) {
                            ball.setYDir(1);
                                if(ball.getXDir() == 1){
                                    ball.setXDir(-1);
                                }
                                else{
                                    ball.setXDir(1);
                                }
                                      
                            } else if (bricks[i][j].contains(pointBottom)) {
                                ball.setYDir(-1);
                                if(ball.getXDir() == 1){
                                    ball.setXDir(-1);
                                }
                                else{
                                    ball.setXDir(1);
                                }
                            }
                        }
                        bricks[i][j].setDestroyed(true);
                        scene.remove(bricks[i][j]);
                        scene.repaint();
                    }
            }
        }
    }
    
    public void reset(){
        
    }
    public void endGame(){
        ending = new Brick(110,200,500,200,Color.RED);
        reset = new HTextButton("RESET");
        reset.setLocation(150,300);
        reset.setSize(100,50);
        reset.setBackground(Color.GREEN);
        reset.setBackgroundMode(HVisible.BACKGROUND_FILL);
        reset.setActionCommand("reset");
        reset.addHActionListener(this);
        scene.add(reset);
        scene.add(ending);
        reset.requestFocus();
        scene.validate();
        scene.setVisible(true);
        scene.repaint();
    }
    
    public void update(int tijd) {
       CollideBalk();
    }

    public void actionPerformed(ActionEvent arg0) {
        String action = arg0.getActionCommand();
        if(action.equals("reset")){
            reset();
        } 
    }
}
