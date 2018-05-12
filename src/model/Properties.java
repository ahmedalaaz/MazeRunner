
package model;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Properties extends HBox implements Observer {

    public Label healthName = new Label("Health: ");; 
    public Label ScoreName = new Label("Score: ");;
    public Label health=new Label();
    public Label Score=new Label();
    public Properties() {
        //healthName =new Label("Health: ");
       // ScoreName=new Label("Score: ");
    	MapGenerator.getInstance().getPlayer().addObserver(this);
    healthName.setStyle("-fx-font-weight: bold");
    ScoreName.setStyle("-fx-font-weight: bold");
    health.setStyle("-fx-font-weight: bold");
    Score.setStyle("-fx-font-weight: bold");
    health.setText("100");
    Score.setText("0");
    healthName.setTextFill(Color.RED);
    health.setTextFill(Color.RED);
    ScoreName.setTextFill(Color.BLUE);
    Score.setTextFill(Color.BLUE);
    healthName.setFont(new Font(20));
    health.setFont(new Font(20));
    ScoreName.setFont(new Font(20));
    Score.setFont(new Font(20));
    setPadding(new Insets(15));
    setSpacing(15);
    getChildren().addAll(healthName,health,ScoreName,Score);
    }
    
    @Override
    public void update(int health , int score) {
        this.health.setText(String.valueOf(health));
        this.Score.setText(String.valueOf(score));
    }
    
}
