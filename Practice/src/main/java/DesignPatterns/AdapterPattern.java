package DesignPatterns;



public class AdapterPattern {
    //-----------------------Step 5----------------------

    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");
    }
}
//-----------------------Step 1----------------------
interface MediaPlayer {
    void play(String audioType, String filename);
}

interface AdvancedMediaPlayer {
    void playVLC(String filename);

    void playMP4(String filename);
}
//-----------------------Step 2----------------------
class VLCPlayer implements AdvancedMediaPlayer {

    @Override
    public void playVLC(String filename) {
        System.out.println("Play VLC Media File: " + filename);
    }

    @Override
    public void playMP4(String filename) {

    }
}

class MP4Player implements AdvancedMediaPlayer {

    @Override
    public void playVLC(String filename) {

    }

    @Override
    public void playMP4(String filename) {
        System.out.println("Play MP4 Media File: " + filename);
    }
}

//-----------------------Step 3----------------------
class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedMediaPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer = new VLCPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer = new MP4Player();
        }
    }

    @Override
    public void play(String audioType, String filename) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer.playVLC(filename);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer.playMP4(filename);
        }
    }
}

//-----------------------Step 4----------------------

class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String filename) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing MP3 file: " + filename);
        } else if (audioType.equalsIgnoreCase("VLC") || audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, filename);
        } else {
            System.out.println("invalid media. " + audioType + " format not supposted");
        }
    }
}