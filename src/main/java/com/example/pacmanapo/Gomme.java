package com.example.pacmanapo;

public class Gomme {
    private int x;
    private int y;
    private int size;
    private int[][] board;
    private int width;
    private int height;

    public Gomme(int x, int y, int size, int[][] board, int width, int height) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.board = board;
        this.width = width;
        this.height = height;
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

    public void setHeight(int height) {
        this.height = height;
    }
}
