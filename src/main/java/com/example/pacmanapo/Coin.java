package com.example.pacmanapo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Coin extends Personnage{
    private Image image;
    private ImageView imageView;
    public int width;
    public int height;
    public int x;
    public int y;

    boolean isTaken;

    public Coin(int x, int y){
        this.x=x;
        this.y=y;
        image = new Image("file:src/main/java/com/example/pacmanapo/coin25.png");
        imageView = new ImageView(image);
        Box.getChildren().add(imageView);
        width = 25;
        height = 25;
        isTaken = false;
        setX(x);
        setY(y);
    }

    public void hide(){
        Box.getChildren().remove(imageView);
        isTaken = true;
    }
}
