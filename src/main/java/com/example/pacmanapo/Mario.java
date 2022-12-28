
package com.example.pacmanapo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
        width = 25;
        height = 25;
        }

    public void spawn(){
        /**
         * This method is used to spawn the player
         */
        setX(25);
        setY(25);
        nextDirection = -1;
        direction = -1;
        isMarioLookingRight = true;
        changingDirection = false;
    }
    public int getLife() {
        /**
         * @return the life of the player
         */
        return life;
    }

    public void setLife(int life) {
        /**
         * This method is used to set the life for the player
         * @param life the life for the player
         */
        this.life = life;
    }

 
    public int getScore() {
     /**
     * @return the score of the player
     */
        return score;
    }

    public void setScore(int score) {
        /**
         * This method is used to set the score for the player
         * @param score the score for the player
         */
        this.score = score;
    }

    public void move(ArrayList<Mur> murs,ArrayList<Carapace> carapaces){
        /**
         * This method is used to move the player
         * @param murs the ArrayList of the walls
         * @param carapaces the Ararylist of the carapaces
         */
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
        /**
         * This method is used to handle the direction of the player
         * @param e the KeyEvent
         * @param murs the ArrayList of the walls
         */
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
