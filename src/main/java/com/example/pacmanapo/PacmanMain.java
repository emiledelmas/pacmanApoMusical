package com.example.pacmanapo;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class PacmanMain extends Application {
    GraphicsContext gc;
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        Board b = new Board(9, 9);
        Mario mario = new Mario(4, 3, 0, 0);
        Carapace carapace = new Carapace(250,250,1, "rouge");
        ArrayList <Mur> murs = new ArrayList<>();
        //for (int i = 0; i < 20; i++) {
        //    murs.add(new Mur(25*i, 0));
        //    murs.add(new Mur( 25*i, 400));
        //}
        //for the element in the 2D array b.getBoard()
        // Set the path to the local audio file
        ;
        File audioFile = new File("src/main/java/com/example/pacmanapo/music.mp3");
        String audioFilePath = audioFile.toURI().toString();
        // Create a Media object using the audio file path
        Media audio = new Media(audioFilePath);

        // Create a MediaPlayer object using the Media object
        MediaPlayer mediaPlayer = new MediaPlayer(audio);

        // Create a MediaView object using the MediaPlayer object
        MediaView mediaView = new MediaView(mediaPlayer);


        for (int i = 0; i < b.getBoard().length; i++) {
            for (int j = 0; j < b.getBoard()[i].length; j++) {
                if (b.getBoard()[i][j] == 1) {
                    murs.add(new Mur(25*j, 25*i));
                }
            }
        }


        //Mur mur = new Mur(50, 50, 0, 0);

        var root = new Pane();
        //Draw the board
        Canvas canvas = new Canvas(500, 500);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0 , 500, 500);
        final long startNanoTime = System.nanoTime();
        mario.setX(25);
        mario.setY(25+mario.height);
        //To fix the framerate we do:
        AnimationTimer timer =  new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, 500, 500);
                System.out.println("x: " + mario.getX());
                System.out.println("y: " + mario.getY());
                switch (mario.getDirection()) {
                    case 0:
                        //DOWN
                        //Check if the next position is a wall
                        if (mario.getY() + mario.height < 500) {
                            boolean isWall = false;
                            for (Mur mur : murs) {
                                if (mario.getX() + mario.width > mur.getX() && mario.getX() < mur.getX() + mur.getWidth() && mario.getY() + mario.height + mario.getSpeed() > mur.getY() && mario.getY() + mario.height < mur.getY() + mur.getHeight()) {
                                    isWall = true;
                                }
                            }
                            if (!isWall) {
                                mario.setY(mario.getY() + mario.getSpeed());
                            }
                        }
                        //mario.setY(mario.getY() + mario.getSpeed());
                        break;
                    case 1:
                        //UP
                        if (mario.getY() > 0) {
                            boolean isWall = false;
                            for (Mur mur : murs) {
                                if (mario.getX() + mario.width > mur.getX() && mario.getX() < mur.getX() + mur.getWidth() && mario.getY() - mario.getSpeed() < mur.getY() + mur.getHeight() && mario.getY() > mur.getY()) {
                                    isWall = true;
                                }
                            }
                            if (!isWall) {
                                mario.setY(mario.getY() - mario.getSpeed());
                            }
                        }

                        //mario.setY(mario.getY() - mario.getSpeed());
                        break;
                    case 2:
                        //LEFT
                        if (mario.getX() - mario.getSpeed() > 0) {
                            boolean isWall = false;
                            for (Mur mur : murs) {
                                if (mario.getX() - mario.getSpeed() < mur.getX() + mur.getWidth() && mario.getX() > mur.getX() && mario.getY() + mario.height > mur.getY() && mario.getY() < mur.getY() + mur.getHeight()) {
                                    isWall = true;
                                }
                            }
                            if (!isWall) {
                                mario.setX(mario.getX() - mario.getSpeed());
                            }
                        }
                        //mario.setX(mario.getX() - mario.getSpeed());
                        break;
                    case 3:
                        //RIGHT
                        if (mario.getX() + mario.width < 500) {
                            boolean isWall = false;
                            for (Mur mur : murs) {
                                if (mario.getX() + mario.width > mur.getX() && mario.getX() < mur.getX() && mario.getY() + mario.height > mur.getY() && mario.getY() < mur.getY() + mur.getHeight()) {
                                    isWall = true;
                                }
                            }
                            if (!isWall) {
                                mario.setX(mario.getX() + mario.getSpeed());
                            }
                        }
                        //mario.setX(mario.getX() + mario.getSpeed());
                        break;
                }

                carapace.move();
                //Wait for having only 40 fps
                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        //timer.setCycleDuration(Duration.millis(1000.0 / 40.0));
        //Set the framerate to 40 fps
        timer.start();
        root.getChildren().add(canvas);
        root.getChildren().add(mario.Box);
        root.getChildren().add(carapace.Box);
        root.getChildren().add(mediaView);
        for (Mur mur : murs) {
            root.getChildren().add(mur.Box);
        }
        //root.getChildren().add(mur.Box);

        Scene scene = new Scene(root, 500, 500);
        scene.setOnKeyPressed(event -> {
            mario.handleDirection(event);
            carapace.changeDirection();
        });
        stage.setTitle("PACMARIO");
        stage.setScene(scene);
        stage.show();
        mediaPlayer.play();
    }
    public static void main(String[] args) {
        launch(args);
    }
}               