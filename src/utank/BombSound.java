package utank;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;

public class BombSound extends JFrame {
    public BombSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("wav.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

}