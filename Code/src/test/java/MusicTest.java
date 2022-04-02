import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.sound.sampled.AudioSystem;

public class MusicTest {
    public Music testMusic;

    @Test
    public void testMusicCreation() {
        System.out.println("[testMusicCreation]");
        testMusic = new Music();
        Assertions.assertNotNull(testMusic);
    }

    @Test
    public void testSetFile() {
        System.out.println("[testSetFile]");
    }

    @Test
    public void testPlay() {
        System.out.println("[testPlay]");
    }

    @Test
    public void testLoop() {
        System.out.println("[testLoop]");
    }
    @Test
    public void testStop() {
        System.out.println("[testStop]");
    }



    @Test
    public void checkAudioOutputState() {
        AudioSystem.getMixerInfo();
        System.out.println("[checkAudioOutputState] getting audio output info");
    }
}