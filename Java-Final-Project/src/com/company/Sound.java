package com.company;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

class Sound {
    private Clip clip;
    static String filePath;

    Sound()
            throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        AudioFormat format = audioInputStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(audioInputStream);
    }

    void play() { clip.start(); }
}
