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

    /**
     * @return the couleur
     */
    public String getCouleur()
    {
        return couleur;
    }

    /**
     * @param couleur the couleur to set
     */
    public void setCouleur(String couleur)
    {
        this.couleur = couleur;
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

    public void changeDirection(ArrayList<Mur> murs){
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
        if (!isWall(murs, direction)) {
            switch (direction) {
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
                    //mario.setX(mario.getX() - mario.getSpeed());
                    break;
                case 3:
                    //RIGHT
                    setX(getX() + getSpeed());
                    //mario.setX(mario.getX() + mario.getSpeed());
                    break;
            }
        }
        else {
            changeDirection(murs);
        }
    }
}
