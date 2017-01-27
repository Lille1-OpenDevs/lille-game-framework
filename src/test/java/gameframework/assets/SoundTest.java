package gameframework.assets;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.junit.Test;

public class SoundTest {

	@Test
	public void testcrossFading(int duration, int interval) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		Sound soundTest = new Sound("welcome.wav");
		soundTest.crossFading(3000, 100);
		assertFalse(soundTest.isPlaying());
	}

}