import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * class to implement and manage Background music and sound effects
 */
public class Music {
    Clip[] clip = new Clip[5];
    private String[] musicFilePaths = {
            "src/main/java/music/Click_music_1.wav",// 0
            "src/main/java/music/Click_music_2.wav",// 1
            "src/main/java/music/Click_music_3.wav",// 2
            "src/main/java/music/bgm_mysteriousSound.wav",// 3
            "src/main/java/music/bgm_darkCaveSound.wav"// 4
    };


    /**
     * set file to get music from
     * @param i
     * @return
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    public boolean setFile(int i) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
//        System.out.println("[setFile] setting music file");
        if(i >= 0 && i < musicFilePaths.length) {
            File file = new File(musicFilePaths[i]);
            AudioInputStream bgm = AudioSystem.getAudioInputStream(file);
            clip[i] = AudioSystem.getClip();
            clip[i].open(bgm);
        }else {
            return false;
        }

        return true;
    }

    /**
     * play music
     */
    public void play(int i) {
        if(clip == null) {
//            System.out.println("[play] Clip is null.");
        }else {
            clip[i].start();
        }
    }

    /**
     * Loop music
     */
    public void loop(int i) {
        clip[i].loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stop music
     */
    public void stop(int i) {
        clip[i].stop();
    }
}
