import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    Clip clip;
    private String[] musicFilePaths = {
            "src/main/java/music/Click_music_1.wav",// 0
            "src/main/java/music/Click_music_2.wav",// 1
            "src/main/java/music/Click_music_3.wav",// 2
            "src/main/java/music/bgm_mysteriousSound.wav",// 3
            "src/main/java/music/bgm_darkCaveSound.wav"// 4
    };


    public boolean setFile(int i) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
//        System.out.println("[setFile] setting music file");
        if(i >= 0 && i < musicFilePaths.length) {
            File file = new File(musicFilePaths[i]);
            AudioInputStream bgm = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(bgm);
        }else {
            return false;
        }

        return true;
    }

    public void play() {
        if(clip == null) {
//            System.out.println("[play] Clip is null.");
        }else {
            clip.start();
        }
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
