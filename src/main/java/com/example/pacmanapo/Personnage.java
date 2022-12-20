
package com.example.pacmanapo;

import javafx.scene.layout.HBox;

import java.util.ArrayList;

class Personnage
{
    public HBox Box = new HBox();
    protected int speed;
    protected int direction;


    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void relocate(int x, int y) {
        Box.relocate(x, y);
    }

    public int getX() {
        return (int) (Box.getLayoutX());
    }
    public int getY() {
        return (int) Box.getLayoutY();
    }
    public void setX(int x) {
        if (x<-25){
            x=525;
        }
        else if (x>525){
            x=-25;
        }
        Box.setLayoutX(x);
    }
    public void setY(int y) {
        Box.setLayoutY(y);
    }
    public boolean isWall(ArrayList<Mur> murs, int dir){
        boolean isWall = false;
        switch (dir) {
            case 0:
                //DOWN
                //Check if the next position is a wall
                if (getY() + 25 < 500) {
                    for (Mur mur : murs) {
                        if (getX() + 25 > mur.getX() && getX() < mur.getX() + 25 && getY() + 25 + getSpeed() > mur.getY() && getY() + 25 < mur.getY() + 25) {
                            isWall = true;
                        }
                    }
                }
                //setY(getY() + getSpeed());
                break;
            case 1:
                //UP
                //Check if the next position is a wall
                if (getY() > 0) {
                    for (Mur mur : murs) {
                        //if (getX() + width > mur.getX() && getX() < mur.getX() + 25 && getY() - getSpeed() < mur.getY() + 25 && getY() > mur.getY()) {
                        if (getX() + 25 > mur.getX() && getX() < mur.getX() + 25 && getY() - getSpeed() < mur.getY() + 25 && getY() > mur.getY()){
                            isWall = true;
                        }
                    }
                }

                //setY(getY() - getSpeed());
                break;
            case 2:
                //LEFT
                //Check if the next position is a wall
                if (getX() > 0) {
                    for (Mur mur : murs) {
                        if (getX() <= mur.getX() + 25 && getX() > mur.getX() && getY() + 25 > mur.getY() && getY() < mur.getY() + 25) {
                            isWall = true;
                        }
                    }
                }
                //setX(mario.getX() - mario.getSpeed());
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
                //setX(getX() + getSpeed());
                break;

        }
        return isWall;
    }

}
