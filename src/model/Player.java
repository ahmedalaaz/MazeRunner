
package model;

import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class Player extends MapCell implements Subject{
    
	ImageView imageview;
    SpriteSheet spriteSheet;
    private int health = 10;
    SpriteAnimation animation;
    private int score =0;
    Rectangle rect;
    Rectangle collision=null;
    ArrayList<Observer> observers = new ArrayList<>();
    Image collisionImage = COLLISON_IMAGE;
    public Player(ImageView imageView , double x, double y, Image image) {
		 super(x, y, image);
	     this.imageview = imageView;
	     this.imageview.setViewport(new Rectangle2D(0, 0,32, 48)); 
	     spriteSheet = new SpriteSheet(this.imageview, Duration.millis(250), 3, 3, 0, 0, 32, 48);
	     animation =new SpriteAnimation(spriteSheet);
	     //TODO add to view here
	     setLayoutY(80);
	     getChildren().addAll(imageview);
    }
    
    public void MoveX(double x){
        boolean right = x>0?true:false;
        for (double i =0;i<Math.abs(x);i+=0.2){
        if (right){
        this.setTranslateX(this.getTranslateX()+0.2);
          
        }
        else {
        this.setTranslateX(this.getTranslateX()-0.2);
        }
         CheckBomb();
         CheckPower();
        }
        }
    
    public void MoveY(double Y){
        boolean right = Y>0?true:false;
        for (double i =0;i<Math.abs(Y);i+=0.2){
        if (right){
        this.setTranslateY(this.getTranslateY()+0.2);
        }
        else {
        this.setTranslateY(this.getTranslateY()-0.2);
        }
        
        CheckBomb();
        CheckPower();
           }
        
        }
    public void CheckBomb(){
       
       /* for (Rectangle r :TestMaze.b){
            if (this.getBoundsInParent().intersects(r.getBoundsInParent())){
                
                rect=r;
                int HealthChange = this.getHealth()-1;
                this.setHealth(HealthChange);
                this.notifyObservers(HealthChange, score);
                collision=new Rectangle(im.getWidth(), im.getHeight());
                collision.setX(rect.getX()-10);
                collision.setY(rect.getY()-10);
                ImagePattern e =new ImagePattern(im);
                collision.setFill(e);
                TestMaze.n.getChildren().add(collision);
                
            }
            else {
                
                 if (collision!=null){
                 final FadeTransition transition = new FadeTransition(Duration.millis(500), collision);
                 transition.setFromValue(collision.getOpacity());
                 transition.setToValue(0);
                 transition.setInterpolator(Interpolator.EASE_BOTH);
                 transition.setOnFinished((event) -> {
                     TestMaze.n.getChildren().remove(collision);
                 });
                 transition.play();
                 collision=null;
                     
               }
            }
        }
                TestMaze.b.remove(rect);
                TestMaze.n.getChildren().remove(rect);
                if (TestMaze.b.size()==0){
                   TestMaze.b.add(new Rectangle()); 
                }*/
    }

    @Override
    public void addObserver(Observer ob) {
        observers.add(ob);
    }

    @Override
    public void removeObserver(Observer ob) {
        observers.remove(ob);
    }

    @Override
    public void notifyObservers(int health, int score) {
        for (Observer b : observers){
            b.update(health, score);
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

   public void CheckPower(){
      /* Circle remocirc=null;
       for (Circle r :TestMaze.circ){
            if (this.getBoundsInParent().intersects(r.getBoundsInParent())){
                remocirc=r;
              int HealthChange = this.getHealth()+2;
              this.setHealth(HealthChange);
              this.notifyObservers(HealthChange, score);
            }
       } 
               if (remocirc!=null){
                TestMaze.circ.remove(remocirc);
                TestMaze.n.getChildren().remove(remocirc);
               }*/
   }
@Override
public String getCellName() {
	// TODO Auto-generated method stub
	return "Player";
}

    
}
        

