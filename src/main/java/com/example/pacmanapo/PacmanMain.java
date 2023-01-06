package com.example.pacmanapo;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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
        Mario mario = new Mario(1, 2, 0, -1);
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
        //The same for Lost.mp3
        File audioFile2 = new File("src/main/java/com/example/pacmanapo/Lost.mp3");
        String audioFilePath2 = audioFile2.toURI().toString();
        Media audio2 = new Media(audioFilePath2);
        MediaPlayer mediaPlayer2 = new MediaPlayer(audio2);
        MediaView mediaView2 = new MediaView(mediaPlayer2);
        //The same for OOF.mp3
        File audioFile3 = new File("src/main/java/com/example/pacmanapo/OOF.mp3");
        String audioFilePath3 = audioFile3.toURI().toString();
        Media audio3 = new Media(audioFilePath3);
        MediaPlayer mediaPlayer3 = new MediaPlayer(audio3);
        MediaView mediaView3 = new MediaView(mediaPlayer3);
        //The same for coin.mp3
        File audioFile4 = new File("src/main/java/com/example/pacmanapo/coin.mp3");
        String audioFilePath4 = audioFile4.toURI().toString();
        Media audio4 = new Media(audioFilePath4);
        MediaPlayer mediaPlayer4 = new MediaPlayer(audio4);
        MediaView mediaView4 = new MediaView(mediaPlayer4);
        //The same for UP.mp3
        File audioFile5 = new File("src/main/java/com/example/pacmanapo/UP.mp3");
        String audioFilePath5 = audioFile5.toURI().toString();
        Media audio5 = new Media(audioFilePath5);
        MediaPlayer mediaPlayer5 = new MediaPlayer(audio5);
        MediaView mediaView5 = new MediaView(mediaPlayer5);
        //set down the volume
        mediaPlayer5.setVolume(0.3);

        //Same for winSound.mp3
        File audioFile6 = new File("src/main/java/com/example/pacmanapo/winSound.mp3");
        String audioFilePath6 = audioFile6.toURI().toString();
        Media audio6 = new Media(audioFilePath6);
        MediaPlayer mediaPlayer6 = new MediaPlayer(audio6);
        MediaView mediaView6 = new MediaView(mediaPlayer6);

        //Import intro.mp4 as a video to show in the beginning of the game
        File videoFile = new File("src/main/java/com/example/pacmanapo/nintendo.mp4");
        String videoFilePath = videoFile.toURI().toString();
        Media video = new Media(videoFilePath);
        MediaPlayer mediaPlayerVideo = new MediaPlayer(video);
        MediaView mediaViewVideo = new MediaView(mediaPlayerVideo);
        //Set the size of the video
        mediaViewVideo.setFitWidth(500);
        mediaViewVideo.setFitHeight(500);
        //Set the position of the video
        mediaViewVideo.setX(0);
        mediaViewVideo.setY(0);
        //Set the volume of the video
        mediaPlayerVideo.setVolume(2);

        //Import heart.png
        Image heart = new Image("file:src/main/java/com/example/pacmanapo/heart.png");
        final int[] nbCoins = {0};
        int nbTotal = 0;


        for (int i = 0; i < b.getBoard().length; i++) {
            for (int j = 0; j < b.getBoard()[i].length; j++) {
                if (b.getBoard()[i][j] == 1) {
                    murs.add(new Mur(25*j, 25*i));
                }
                else if (b.getBoard()[i][j] == 2) {
                    coins.add(new Coin(25*j, 25*i));
                    nbTotal++;
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
        gc.fillRect(0, 0 , 500, 520);
        Canvas blackScreen = new Canvas(500, 520);
        GraphicsContext gc2 = blackScreen.getGraphicsContext2D();
        gc2.setFill(Color.BLACK);
        gc2.fillRect(0, 0 , 500, 520);
        //Add gameover text

        int finalNbTotal = nbTotal;
        AnimationTimer timer =  new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0 , 500, 520);
                mario.move(murs, carapaces);
                //carapace.move();

                for (Carapace c : carapaces) {
                    //if mario and the carapace are less than 25 px apart, then mario loses a life and respawns
                    if (Math.abs(mario.getX() - c.getX()) < 25 && Math.abs(mario.getY() - c.getY()) < 25) {
                        mediaPlayer3.stop();
                        mediaPlayer3.play();
                        //If all the coins are collected, then the game is won
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
                            //Show black screen
                            //Wait 0.5 seconds
                            blackScreen.setVisible(true);
                            gc2.setFill(Color.WHITE);
                            gc2.fillText("GAME OVER", 200, 250);
                            mediaPlayer.stop();
                            mediaPlayer2.play();
                        }
                    }
                    c.move(murs, carapaces);
                }
                for (Coin coin: coins) {
                    //If the coordinate of the coin is the same as the coordinate of mario delete the coin
                    if (coin.getX() == mario.getX() && coin.getY() == mario.getY() && !coin.isTaken) {
                        nbCoins[0]++;

                        //System.out.println("Coin");
                        //If mediaPlayer4 just stop playing, stop it and increase the pitch
                        mediaPlayer4.stop();
                        if (mediaPlayer4.getRate() < 1.5) {
                            mediaPlayer4.setRate(mediaPlayer4.getRate() + 0.05);
                        }
                        else {
                            mediaPlayer5.stop();
                            mediaPlayer5.play();
                            mediaPlayer4.setRate(1);
                        }
                        //mediaPlayer4.stop();
                        //Pitch up the mediaPlayer4 sound every time
                        //mediaPlayer4.setRate(mediaPlayer4.getRate() + 0.05);
                        mediaPlayer4.play();
                        coin.hide();
                        mario.setScore(mario.getScore()+10);
                    if (finalNbTotal == nbCoins[0]) {
                        blackScreen.setVisible(true);
                        gc2.setFill(Color.WHITE);
                        gc2.fillText("YOU WIN", 200, 250);
                        mediaPlayer.stop();
                        mediaPlayer2.stop();
                        mediaPlayer3.stop();
                        mediaPlayer4.stop();
                        mediaPlayer5.stop();
                        mediaPlayerVideo.stop();
                        mediaPlayer6.play();
                        this.stop();
                    }
                        break;
                    }
                }
                //Show the score
                gc.setFill(Color.WHITE);
                gc.fillText("Score : "+mario.getScore(), 10, 510);


                //Show the life with heart.png
                for (int i=0; i<mario.getLife()+1; i++) {
                    //scale the image 25x25
                    gc.drawImage(heart, 80+25*i, 498, 25, 25);
                }

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
        //Create black screen

        root.getChildren().add(blackScreen);
        //hide it
        blackScreen.setVisible(false);
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
        //Show the intro video
        mediaPlayerVideo.play();
        root.getChildren().add(mediaViewVideo);
        //Hide the video when it's done
        mediaPlayerVideo.setOnEndOfMedia(new Runnable() {
            public void run() {
                //Hide the video
                root.getChildren().remove(mediaViewVideo);
                mediaPlayer.play();
            }
        });



    }
    public static void main(String[] args) {
        launch(args);
    }
}               