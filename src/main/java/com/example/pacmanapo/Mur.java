package com.example.pacmanapo;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Mur {
    public HBox Box = new HBox();
    private Image image = new Image("file:src/main/java/com/example/pacmanapo/mur.jpg");
    private ImageView imageView = new ImageView(image);
    private int width;
    private int height;
    private int x;
    private int y;

    public Mur ( int x, int y) {
        this.x = x;
        this.y = y;
        Box.getChildren().add(imageView);
        width = (int) (image.getWidth());
        height = (int) (image.getHeight());
        //System.out.println(width);
        setX(x);
        setY(y);
    }

    public int getX() {
        return x;

    }

    public void setX(int x) {
        Box.setLayoutX(x);
    }
    public int getY() {
        return y;
    }


    public void setY(int y) {
        Box.setLayoutY(y);
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

    public HBox getBox() {
        return Box;
    }

    public void setBox(HBox box) {
        this.Box = box;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}


