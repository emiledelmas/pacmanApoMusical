package com.example.pacmanapo;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
public class PacmanMain extends Application {
    GraphicsContext gc;
    @Override
    public void start(Stage stage) throws IOException {
        Board b = new Board(9, 9);
        Mario mario = new Mario(1, 3, 0, 0);
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
        gc.setFill(javafx.scene.paint.Color.BLACK);
        gc.fillRect(0, 0 , 500, 500);
        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                gc.setFill(javafx.scene.paint.Color.BLACK);
                gc.fillRect(0, 0, 500, 500);
                mario.move();
                carapace.move();
            }
        }.start();
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