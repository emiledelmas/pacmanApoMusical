
package com.example.pacmanapo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
//Import event
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Rotate;
class Mario extends Personnage {
    private int life;
    private int score;
    private Image i = new Image("file:src/main/java/com/example/pacmanapo/mario1.gif");
    private ImageView imageView = new ImageView(i);
    //Création du premier box contenant
    public int width;
    public int height;
    public int lastDirection;
    public int nextDirection;
    public boolean changingDirection;
    public boolean isMarioLookingRight;
    Mario(int speed, int life, int score, int direction) {
        this.speed = speed;
        this.life = life;
        this.score = score;
        this.direction = direction;
        Box.getChildren().add(imageView);
        //Box.setScaleX(0.05952);
        //Box.setScaleY(0.05208);
        //Box.setScaleX(0.1);
        //Box.setScaleY(0.1);
        //width = (int) (i.getWidth() * 0.05952);
        //height = (int) (i.getHeight() * 0.05208);
        width = 25;
        height = 25;
        nextDirection = -1;
        changingDirection = false;
        isMarioLookingRight = true;
        setX(25);
        setY(25);
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

    public void move(ArrayList<Mur> murs,ArrayList<Carapace> carapaces){
        if (nextDirection != -1) {
            if (!isWall(murs, nextDirection)) {
                setDirection(nextDirection);
                nextDirection = -1;
            }
        }
        //mario.lastDirection = mario.getDirection();
        if (!isWall(murs, getDirection())) {
            switch (getDirection()) {
                case 0:
                    //DOWN
                    //Check if the next position is a wall
                    setY(getY() + getSpeed());
                    //mario.setY(mario.getY() + mario.getSpeed());
                    break;

                case 1:
                    //UP
                    setY(getY() - getSpeed());
                    //mario.setY(mario.getY() - mario.getSpeed());
                    break;
                case 2:
                    //LEFT
                    setX(getX() - getSpeed());
                    //Change i url to left
                    if (isMarioLookingRight) {
                        // Create a Scale transformation to flip the image horizontally
                        Scale flip = new Scale(-1, 1);
                        flip.setPivotX(i.getWidth() / 2);
                        // Apply the transformation to the ImageView
                        imageView.getTransforms().add(flip);
                        isMarioLookingRight = false;
                    }
                    break;
                case 3:
                    //RIGHT
                    setX(getX() + getSpeed());
                    if (!isMarioLookingRight) {
                        Scale flip = new Scale(-1, 1);
                        flip.setPivotX(i.getWidth() / 2);
                        // Apply the transformation to the ImageView
                        imageView.getTransforms().add(flip);
                        isMarioLookingRight = true;
                    }
                    break;
            }
        }
    }
    public void handleDirection(KeyEvent e, ArrayList<Mur> murs){
        //Chefk if mario has change direction
        int dir = direction;
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
        if (dir != direction) {
            if (isWall(murs, direction)) {
                System.out.println("wall");
                nextDirection = direction;
                direction = dir;
            }
        }

    }


    }
