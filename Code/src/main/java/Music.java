import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Music {
    Clip clip;
    URL musicURL[] = new URL[30];
//    File musicFiles[];
    private String[] musicFilePaths = {
            "/music/Click_music_1.wav",// 0
            "/music/Click_music_2.wav",// 1
            "/music/Click_music_3.wav",// 2
            "/music/bgm_mysteriousSound.wav",// 3
            "/music/bgm_darkCaveSound.wav"// 4
    };

    public Music() throws MalformedURLException {
        for(int i = 0; i < musicFilePaths.length; i++) {
            musicURL[i] = getClass().getResource(musicFilePaths[i]);
        }
    }

    public void setFile(int i) {
        System.out.println("[setFile] setting music file");
        try {
            System.out.println("[setFile] entering try statement");
            AudioInputStream ais = AudioSystem.getAudioInputStream(musicURL[i]);
            System.out.println("[setFile] AudioInputStream ais = AudioSystem.getAudioInputStream(musicURL[i]);");
            clip = AudioSystem.getClip();
            System.out.println("[setFile] clip = AudioSystem.getClip();");
            if(clip == null) {
                System.out.println("[setFile] Clip is null.");
            }else {
                System.out.println("[setFile] Clip is NOT null.");
                clip.open(ais);
            }

        }catch (Exception e) {
            System.out.println("[setFile] FAILED");
        }
    }

    public void play() {
        if(clip == null) {
            System.out.println("[play] Clip is null.");
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
