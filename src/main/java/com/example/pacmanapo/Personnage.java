
package com.example.pacmanapo;

import javafx.scene.layout.HBox;

import java.util.ArrayList;

class Personnage
{
    public HBox Box = new HBox();
    protected int speed;
    protected int direction;


    public int getDirection() {
        /**
         * This method is used to get the direction of the player
         * @return the direction of the character
         */
        return direction;
    }

    public void setDirection(int direction) {
        /**
         * This method is used to set the direction of the character
         * @param direction the direction of the character
         */
        this.direction = direction;
    }
    public int getSpeed() {
        /**
         * This method is used to get the speed of the character
         * @return the speed of the character
         */
        return speed;
    }

    public void setSpeed(int speed) {
        /**
         * This method is used to set the speed of the character
         * @param speed the speed of the Character
         */
        this.speed = speed;
    }
    public void relocate(int x, int y) {
        /**
         * This method is used to relocate the character
         * @param x the x position of the character
         * @param y the y position of the character
         */
        Box.relocate(x, y);
    }

    public int getX() {
        /**
         * This method is used to get the x position of the character
         * @return the x position of the character
         */
        return (int) (Box.getLayoutX());
    }
    public int getY() {
        /**
         * This method is used to get the y position of the character
         * @return the y position of the character
         */
        return (int) Box.getLayoutY();
    }
    public void setX(int x) {
        /**
         * This method is used to set the x position of the character (with the tunnel)
         * @param x the x position of the character
         */
        if (x<-25){
            x=525;
        }
        else if (x>525){
            x=-25;
        }
        Box.setLayoutX(x);
    }
    public void setY(int y) {
        /**
         * This method is used to set the y position of the character
         * @param y the y position of the character
         */
        Box.setLayoutY(y);
    }
    public boolean isWall(ArrayList<Mur> murs, int dir){
        /**
         * This method is used to check if the character is on a wall
         * @param murs the list of the walls
         * @param dir the direction of the character
         * @return true if the character is on a wall, false otherwise
         */
        boolean isWall = false;
        switch (dir) {
            case 0:
                //DOWN
                if (getY() + 25 < 500) {
                    for (Mur mur : murs) {
                        if (getX() + 25 > mur.getX() && getX() < mur.getX() + 25 && getY() + 25 + getSpeed() > mur.getY() && getY() + 25 < mur.getY() + 25) {
                            isWall = true;
                        }
                    }
                }
                break;
            case 1:
                //UP
                if (getY() > 0) {
                    for (Mur mur : murs) {
                        //if (getX() + width > mur.getX() && getX() < mur.getX() + 25 && getY() - getSpeed() < mur.getY() + 25 && getY() > mur.getY()) {
                        if (getX() + 25 > mur.getX() && getX() < mur.getX() + 25 && getY() - getSpeed() < mur.getY() + 25 && getY() > mur.getY()){
                            isWall = true;
                        }
                    }
                }
                break;
            case 2:
                //LEFT
                if (getX() > 0) {
                    for (Mur mur : murs) {
                        if (getX() <= mur.getX() + 25 && getX() > mur.getX() && getY() + 25 > mur.getY() && getY() < mur.getY() + 25) {
                            isWall = true;
                        }
                    }
                }
                break;
            case 3:
                //RIGHT
                if (getX() + 25 < 500) {
                    for (Mur mur : murs) {
                        if (getX() + 25 >= mur.getX() && getX() <= mur.getX() && getY() + 25 > mur.getY() && getY() < mur.getY() + 25) {
                            isWall = true;
                        }
                    }
                }
                break;
        }
        return isWall;
    }
}
