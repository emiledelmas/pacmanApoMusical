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
        /**
         * This method is used to get the x position of the carapace
         * @return the x position of the carapace
         */
        return x;
    }

    public void setX(int x) {
        /**
         * This method is used to set the x position of the carapace
         * @param x the x position of the carapace
         */
        Box.setLayoutX(x);
    }
    public int getY() {
        /**
         * This method is used to get the y position of the carapace
         * @return the y position of the carapace
         */
        return y;
    }


    public void setY(int y) {
        /**
         * This method is used to set the y position of the carapace
         * @param y the y position of the carapace
         */
        Box.setLayoutY(y);
    }
    public int getWidth() {
        /**
         * This method is used to get the width of the carapace
         * @return the width of the carapace
         */
        return width;
    }

    public void setWidth(int width) {
        /**
         * This method is used to set the width of the carapace
         * @param width the width of the carapace
         */
        this.width = width;
    }

    public int getHeight() {
        /**
         * This method is used to get the height of the carapace
         * @return the height of the carapace
         */
        return height;
    }

    public void setHeight(int height) {
        /**
         * This method is used to set the height of the carapace
         * @param height the height of the carapace
         */
        this.height = height;
    }

    public HBox getBox() {
        /**
         * This method is used to get the box of the carapace
         * @return the box of the carapace
         */
        return Box;
    }

    public void setBox(HBox box) {
        /**
         * This method is used to set the box of the carapace
         * @param box the box of the carapace
         */
        this.Box = box;
    }

    public Image getImage() {
        /**
         * This method is used to get the image of the carapace
         * @return the image of the carapace
         */
        return image;
    }

    public void setImage(Image image) {
        /**
         * This method is used to set the image of the carapace
         * @param image the image of the carapace
         */
        this.image = image;
    }

}


