
package com.example.pacmanapo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
//Import event
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
class Mario {
    private int speed;
    private int life;
    private int score;
    private int direction;
    private Image i = new Image("file:src/main/java/com/example/pacmanapo/mario.gif");
    private ImageView imageView = new ImageView(i);
    //Création du premier box contenant
    public HBox Box = new HBox();
    public int width;
    public int height;
    Mario(int speed, int life, int score, int direction) {
        this.speed = speed;
        this.life = life;
        this.score = score;
        this.direction = direction;
        Box.getChildren().add(imageView);
        Box.setScaleX(0.05);
        Box.setScaleY(0.05);
        width = (int) (i.getWidth() * 0.05);
        height = (int) (i.getHeight() * 0.05);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void relocate(int x, int y) {
        Box.relocate(x, y);
    }
    public int getX() {
        return (int) (Box.getLayoutX()+200);
    }
    public int getY() {
        return (int) Box.getLayoutY()+225;
    }
    public void setX(int x) {
        Box.setLayoutX(x-200);
    }
    public void setY(int y) {
        Box.setLayoutY(y-225);
    }
    public void handleDirection(KeyEvent e){
        switch (e.getCode()) {
            case UP:
                direction = 1;
                break;
            case DOWN:
                direction = 0;
                break;
            case LEFT:
                direction = 2;
                break;
            case RIGHT:
                direction = 3;
                break;
        }
    }

}
