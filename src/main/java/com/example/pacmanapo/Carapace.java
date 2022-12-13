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
public class Carapace
{
    //attribut
    private int speed;
    private int direction;
    public HBox Box = new HBox();
    private String couleur;
    private Image image;
    private ImageView imageView;

    private int width;
    private int height;
    private double scale;
    private int x;
    private int y;
    //constructeur
    public Carapace(int x, int y,int speed, String couleur){
        this.speed=speed;
        this.couleur=couleur;
        this.x = x;
        this.y = y;
        //load la bonne image selon la couleur de la carapace
        if(couleur.equals("rouge")){
            image = new Image("file:src/main/java/com/example/pacmanapo/carapace.png");
            scale = 0.08;
        }
        else {
            image = new Image("file:src/main/java/com/example/pacmanapo/carapaceverte.png");
            scale = 0.15;
        }
        imageView = new ImageView(image);
        Box.getChildren().add(imageView);
        Box.setScaleX(scale);
        Box.setScaleY(scale);
        width = (int) (image.getWidth() * 0.05);
        height = (int) (image.getHeight() * 0.05);
    }

    /**
     * @return the speed
     */
    public int getSpeed()
    {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    /**
     * @return the direction
     */
    public int getDirection()
    {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(int direction)
    {
        this.direction = direction;
    }

    /**
     * @return the contenant
     */
    public HBox getContenant()
    {
        return Box;
    }

    /**
     * @param contenant the contenant to set
     */
    public void setContenant(HBox Box)
    {
        this.Box = Box;
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

    /**
     * @return the image
     */
    public Image getImage()
    {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image)
    {
        this.image = image;
    }
    //mettre image dans HBox ?

    public void setX(int x){

        Box.setLayoutX(x-200);
    }

    public void setY(int y){

        Box.setLayoutY(y-250);
    }

    public int getX(){

        return (int) Box.getLayoutX()+200;
    }
    public int getY(){

        return (int) Box.getLayoutY()+250;
    }

    public void changeDirection(){
        Random rd = new Random();
        //rd.nextInt(5);
        int direction = rd.nextInt(4);
        //System.out.println("Direction "+direction);
        setDirection(direction);
    }
    public void move(){
        //print getX and getY
        //System.out.println("X: "+getX()+" Y: "+getY());
        //print width and height
        //System.out.println("Width: "+width+" Height: "+height);
        switch (direction) {
            case 0:
                if (getY() < 500)
                    setY(getY() + speed);
                break;
            case 1:
                if (getY()-height > 0)
                    setY(getY() - speed);
                break;
            case 2:
                if (getX() > 0)
                    setX(getX() - speed);
                break;
            case 3:
                if (getX()+width < 500)
                    setX(getX() + speed);
                break;
        }
    }
}
