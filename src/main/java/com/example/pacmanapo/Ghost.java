package com.example.pacmanapo;

public class Ghost {
    private int x;
    private int y;
    private int size;
    private int speed;
    private int life;
    private int score;
    private int direction;
    private int nextDirection;
    private int[][] board;
    private int width;
    private int height;
    private int ghostNumber;

    public Ghost(int x, int y, int size, int speed, int life, int score, int direction, int nextDirection, int[][] board, int width, int height, int ghostNumber) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;
        this.life = life;
        this.score = score;
        this.direction = direction;
        this.nextDirection = nextDirection;
        this.board = board;
        this.width = width;
        this.height = height;
        this.ghostNumber = ghostNumber;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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

    public int getNextDirection() {
        return nextDirection;
    }

    public void setNextDirection(int nextDirection) {
        this.nextDirection = nextDirection;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }
}
