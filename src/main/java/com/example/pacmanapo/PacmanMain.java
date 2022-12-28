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
        Mario mario = new Mario(1, 3, 0, -1);
        ArrayList <Carapace> carapaces = new ArrayList<>();
        ArrayList <Mur> murs = new ArrayList<>();
        ArrayList <Coin> coins = new ArrayList<>();
        // Set the path to the local audio file
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
                else if (b.getBoard()[i][j] == 2) {
                    coins.add(new Coin(25*j, 25*i));
                }

            }
        }
        for (int i=0;i<3; i++) {
            carapaces.add(new Carapace( 1, "rouge",-1));
        }
        int i=0;
        for (Carapace carapace : carapaces) {
            carapace.setX(225+25*i);
            carapace.setY(275);
            i++;
        }
        mario.spawn();
        var root = new Pane();

        //Draw the board
        Canvas canvas = new Canvas(500, 520);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0 , 500, 500);

        AnimationTimer timer =  new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, 500, 520);
                mario.move(murs, carapaces);
                //carapace.move();

                for (Carapace c : carapaces) {
                    //if mario and the carapace are less than 25 px apart, then mario loses a life and respawns
                    if (Math.abs(mario.getX() - c.getX()) < 25 && Math.abs(mario.getY() - c.getY()) < 25) {
                        if (mario.getLife() > 0) {
                            mario.setLife(mario.getLife() - 1);
                            mario.spawn();
                            int i=0;
                            for (Carapace carapace : carapaces) {
                                carapace.setX(225+25*i);
                                carapace.setY(275);
                                i++;
                                carapace.setDirection(-1);
                            }

                        }
                        else {
                            System.exit(0);
                        }
                    }
                    c.move(murs, carapaces);
                }
                for (Coin coin: coins) {
                    //If the coordinate of the coin is the same as the coordinate of mario delete the coin
                    if (coin.getX() == mario.getX() && coin.getY() == mario.getY() && !coin.isTaken) {
                        //System.out.println("Coin");
                        coin.hide();
                        mario.setScore(mario.getScore()+10);
                        break;
                    }
                }
                //Show the score
                gc.setFill(Color.WHITE);
                gc.fillText("Score : "+mario.getScore(), 10, 510);
            }

        };
        //timer.setCycleDuration(Duration.millis(1000.0 / 40.0));
        //Set the framerate to 40 fps
        timer.start();
        root.getChildren().add(canvas);
        //root.getChildren().add(carapace.Box);
        root.getChildren().add(mediaView);
        //Add score
        for (Mur mur : murs) {
            root.getChildren().add(mur.Box);
        }
        for (Coin coin: coins) {
            root.getChildren().add(coin.Box);
        }
        root.getChildren().add(mario.Box);
        for (Carapace c : carapaces) {
            root.getChildren().add(c.Box);
        }
        Scene scene = new Scene(root, 500, 520);
        scene.setOnKeyPressed(event -> {
            mario.handleDirection(event,murs);
            for (Carapace c : carapaces) {
                if (c.getDirection() == -1)
                c.changeDirection(murs);
            }
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