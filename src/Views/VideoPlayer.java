package Views;

import Controllers.StudentController;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

public class VideoPlayer extends JFrame {

    private final JFXPanel jfxPanel = new JFXPanel();
    private MediaPlayer mediaPlayer;

    private JButton playButton;
    private JButton pauseButton;
    private JButton skipForwardButton;
    private JButton skipBackwardButton;
    private JButton openButton;
    private JSlider playbackSpeedSlider;
    private JSlider seekBar;
    private String title;
    private String className;
    private StudentController studentController;

    public VideoPlayer(String title, String className) throws SQLException, ClassNotFoundException, IOException {
        this.title = title;
        this.className = className;
        initComponents();
    }

    private void initComponents() throws SQLException, ClassNotFoundException, IOException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Video Player");
        setSize(800, 600);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(jfxPanel, BorderLayout.CENTER);

        // Buttons
        studentController = new StudentController();
        openButton = new JButton("Open Video");
        playButton = new JButton("Play");
        pauseButton = new JButton("Pause");
        skipForwardButton = new JButton("Skip Forward");
        skipBackwardButton = new JButton("Skip Backward");

        openButton.addActionListener(e -> {
            try {
                chooseVideoFile();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        playButton.addActionListener(e -> play());
        pauseButton.addActionListener(e -> pause());
        skipForwardButton.addActionListener(e -> skip(10)); // Skip 10 seconds forward
        skipBackwardButton.addActionListener(e -> skip(-10)); // Skip 10 seconds backward

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(playButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(skipForwardButton);
        buttonPanel.add(skipBackwardButton);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        // Playback Speed Slider
        playbackSpeedSlider = new JSlider(50, 200, 100); // Range from 0.5x to 2.0x with default at 1.0x
        playbackSpeedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setPlaybackSpeed(playbackSpeedSlider.getValue() / 100.0);
            }
        });


        seekBar = new JSlider();
        seekBar.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateSeekBar();
            }
        });

        JPanel sliderPanel = new JPanel();
        sliderPanel.add(new JLabel("Playback Speed:"));
        sliderPanel.add(playbackSpeedSlider);
        sliderPanel.add(new JLabel("Seek:"));
        sliderPanel.add(seekBar);

        contentPane.add(sliderPanel, BorderLayout.NORTH);

        setContentPane(contentPane);

        chooseVideoFile();
    }

    private void play() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    private void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    private void skip(int seconds) {
        if (mediaPlayer != null) {
            mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(seconds)));
        }
    }

    private void setPlaybackSpeed(double speed) {
        if (mediaPlayer != null) {
            mediaPlayer.setRate(speed);
        }
    }

    private void chooseVideoFile() throws SQLException, IOException {


        byte[] videoData = studentController.getLecture(title, className);
        File tempFile = File.createTempFile("tempVideo", ".mp4");
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(videoData);
        }
        playVideo(tempFile.toURI().toString());
    }

    private void playVideo(String videoPath) {
        Platform.runLater(() -> {
            Media media = new Media(videoPath);
            mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);

            jfxPanel.setScene(new Scene(new javafx.scene.layout.BorderPane(mediaView)));

            seekBar.setMaximum((int) mediaPlayer.getTotalDuration().toSeconds());

            mediaPlayer.setOnEndOfMedia(() -> {
                mediaPlayer.seek(Duration.ZERO); // Restart the video
                //play();
            });
            mediaPlayer.play();
        });
    }

    private void updateSeekBar() {
        if (mediaPlayer != null) {
            double currentTime = mediaPlayer.getCurrentTime().toSeconds();
            seekBar.setValue((int) currentTime);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                new VideoPlayer("Intro to Java Swing", "SDA").setVisible(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}


