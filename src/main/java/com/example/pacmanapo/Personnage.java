
package com.example.pacmanapo;

class Personnage 
{
    private int speed;
    private int life;
    private int score;
    private int direction;

    Personnage(int speed, int life, int score, int direction) {
        this.speed = speed;
        this.life = life;
        this.score = score;
        this.direction = direction;
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
    
}
