import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Music {
    Clip clip;
//    URL musicURL[] = new URL[30];
//    File file = null;
    File musicFiles[];
    private String[] musicFilePaths = {
            "src/main/java/music/Click_music_1.wav",// 0
            "src/main/java/music/Click_music_2.wav",// 1
            "src/main/java/music/Click_music_3.wav",// 2
            "src/main/java/music/bgm_mysteriousSound.wav",// 3
            "src/main/java/music/bgm_darkCaveSound.wav"// 4

    };

    public Music() throws MalformedURLException {
//        for(int i = 0; i < musicFilePaths.length; i++) {
//            musicURL[i] = getClass().getResource(musicFilePaths[i]);
//        }
//        file = new File("/music/bgm_mysteriousSound.wav");
    }

    public boolean setFile(int i) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        System.out.println("[setFile] setting music file");
//        System.out.println("[setFile] File file = new File(\"/music/Click_music_1.wav\");");
//        File file = new File("src/main/java/music/Click_music_1.wav");
//        File file = new File("src/main/java/music/bgm_mysteriousSound.wav");
        if(i >= 0 && i < musicFilePaths.length) {
            File file = new File(musicFilePaths[i]);

//            System.out.println("AudioInputStream bgm = AudioSystem.getAudioInputStream(file);");
            AudioInputStream bgm = AudioSystem.getAudioInputStream(file);
//            System.out.println("clip = AudioSystem.getClip();");
            clip = AudioSystem.getClip();
//            System.out.println("clip.open(bgm);");
            clip.open(bgm);
        }else {
            return false;
        }




//        try {
//            System.out.println("[setFile] entering try statement");
//            AudioInputStream ais = AudioSystem.getAudioInputStream(musicURL[i]);
//            System.out.println("[setFile] AudioInputStream ais = AudioSystem.getAudioInputStream(musicURL[i]);");
//            clip = AudioSystem.getClip();
//            System.out.println("[setFile] clip = AudioSystem.getClip();");
//            if(clip == null) {
//                System.out.println("[setFile] Clip is null.");
//            }else {
//                System.out.println("[setFile] Clip is NOT null.");
//                clip.open(ais);
//            }

//        }catch (Exception e) {
//            System.out.println("[setFile] FAILED");
//        }
        return true;
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
