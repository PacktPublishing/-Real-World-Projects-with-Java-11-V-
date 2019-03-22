package main;

import customViews.VideoItem;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {
    static Stage primaryStage;
    public MediaView player;
    public ListView<VideoItem> videoLibrary;

    public void loadVideo(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Video File");
        String videoPath = fileChooser.showOpenDialog(primaryStage).toURI().toString();
        Media video = new Media(videoPath);
        playVideo(video);
        addToLibrary(videoPath, video);
    }

    private void addToLibrary(String videoPath, Media video) {
        videoLibrary.getItems().add(new VideoItem(videoPath, video));
    }

    private void playVideo(Media video) {
        MediaPlayer mediaPlayer = new MediaPlayer(video);
        mediaPlayer.setAutoPlay(false);
        player.setMediaPlayer(mediaPlayer);
    }

    public void toggleVideoPlay(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (player.getMediaPlayer().getStatus().equals(MediaPlayer.Status.PLAYING))
               player.getMediaPlayer().pause();
            else
                player.getMediaPlayer().play();
        }
    }

    public void onVideoSelected(MouseEvent mouseEvent) {
        VideoItem selectedItem = videoLibrary.getSelectionModel().getSelectedItem();
        if (selectedItem != null)
            playVideo(selectedItem.getVideo());
    }
}
