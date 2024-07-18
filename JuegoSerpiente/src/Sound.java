import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    private Clip clip;

    public Sound(String soundFile) {
        try {
            AudioInputStream originalStream = AudioSystem.getAudioInputStream(new File(soundFile).getAbsoluteFile());
            AudioFormat baseFormat = originalStream.getFormat();
            AudioFormat decodedFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
            );
            AudioInputStream decodedStream = AudioSystem.getAudioInputStream(decodedFormat, originalStream);

            clip = AudioSystem.getClip();
            clip.open(decodedStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            System.out.println("Reproduciendo sonido: " + clip.getFrameLength());
            clip.setFramePosition(0);  // Rewind to the beginning
            clip.start();
        } else {
            System.out.println("Clip no cargado correctamente.");
        }
    }

}
