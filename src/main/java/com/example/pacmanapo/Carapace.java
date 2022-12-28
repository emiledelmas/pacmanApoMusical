package com.example.pacmanapo;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author camille
 */
public class Carapace extends Personnage
{
    //attribut
    private String couleur;
    private Image image;
    private ImageView imageView;

    private int width;
    private int height;
    private double scaleX;
    private double scaleY;
    //constructeur
    public Carapace(int speed, String couleur,int direction){
        this.speed=speed;
        this.couleur=couleur;
        this.direction=direction;
        //load la bonne image selon la couleur de la carapace
        if(couleur.equals("rouge")){
            image = new Image("file:src/main/java/com/example/pacmanapo/carapace25.png");
            scaleX = 1.0;
            scaleY = 1.0;
        }
        else {
            image = new Image("file:src/main/java/com/example/pacmanapo/carapaceverte.png");
            scaleX = 0.094;
            scaleY = 0.182;
        }
        imageView = new ImageView(image);
        Box.getChildren().add(imageView);
        width = (int) (image.getWidth() * scaleX);
        height = (int) (image.getHeight() * scaleY);
    }


    public String getCouleur()
    {
        /**
         * This method is used to get the color of the carapace
         * @return the color of the carapace
         */
        return couleur;
    }

    public void setCouleur(String couleur)
    {
        /**
         * This method is used to set the color of the carapace
         * @param couleur the color of the carapace
         */
        this.couleur = couleur;
    }
    public int getX() {
        /**
         * This method is used to get the x position of the carapace
         * @return the x position of the carapace
         */
        return (int) (Box.getLayoutX());
    }
    public int getY() {
        /**
         * This method is used to get the y position of the carapace
         * @return the y position of the carapace
         */
        return (int) Box.getLayoutY();
    }
    public void setX(int x) {
        /**
         * This method is used to set the x position of the carapace
         * @param x the x position of the carapace
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
         * This method is used to set the y position of the carapace
         * @param y the y position of the carapace
         */
        Box.setLayoutY(y);
    }

    public void changeDirection(ArrayList<Mur> murs){
        /**
         * This method is used to change the direction of the carapace
         * @param murs the list of the walls
         */
        Random rd = new Random();
        //rd.nextInt(5);
        int direction = rd.nextInt(4);
        while (isWall(murs, direction)){
            direction = rd.nextInt(4);
        }
        //System.out.println("Direction "+direction);
        setDirection(direction);
    }
    public void move(ArrayList<Mur> murs,ArrayList<Carapace> carapaces){
        /**
         * This method is used to move the carapace
         * @param murs the list of the walls
         * @param carapaces the list of the carapaces
         */
        if (!isWall(murs, direction)) {
            switch (direction) {
                case 0:
                    //DOWN
                    setY(getY() + getSpeed());
                    break;

                case 1:
                    //UP
                    setY(getY() - getSpeed());
                    break;
                case 2:
                    //LEFT
                    setX(getX() - getSpeed());
                    break;
                case 3:
                    //RIGHT
                    setX(getX() + getSpeed());
                    break;
            }
        }
        else {
            changeDirection(murs);
        }
    }
}
