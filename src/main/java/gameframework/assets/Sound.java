package gameframework.assets;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Class allowing to create sounds and play them. It is not intended to play musics.
 * If you want to change the sound, you should create a new Sound object.
 */
public class Sound {

	/**
	 * This clip represents a sound. It will load the sound and will be able
	 * to play it any number of times needed.
	 */
	protected final Clip clip;

	/**
	 * True if the sound must loop, false otherwise
	 */
	protected boolean isLooping;

	/**
	 * Creates and loads the sound
	 * @param path the path to the sound asset
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 */
	public Sound(final String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		this(path,1.0);
	}

	/**
	 * Creates and loads the sound with a different speed
	 * @param path the path to the sound asset
	 * @param playSpeed a speed multiplier (1.5 for 150% faster)
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 */
	public Sound(final String path, double playSpeed) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		InputStream resource = getClass().getResourceAsStream(path);
		if (resource == null) {
			throw new IllegalArgumentException("Can't open resource \"" + path + "\"");
		}

		final BufferedInputStream buffInputStream = new BufferedInputStream(resource);
		AudioInputStream audioInputStream= null;

		try {
			audioInputStream = AudioSystem.getAudioInputStream
					(buffInputStream);
		} catch (UnsupportedAudioFileException exception) {
			throw new UnsupportedAudioFileException("The file is not in a valid sound format.");
		} catch (IOException exception) {
			throw exception;
		}
		audioInputStream = changeSpeed(audioInputStream, playSpeed) ;
		final AudioFormat format = audioInputStream.getFormat();
	    
		final DataLine.Info info = new DataLine.Info(Clip.class, format);

		clip = (Clip) AudioSystem.getLine(info);
		clip.open(audioInputStream);

		isLooping = false;
	}
	
	/**
	 * Changes the length of an AudioInputStream using a speed multiplier on the length of the audio converted into bytes
	 * @param path the path to the sound asset
	 * @param playSpeed a speed multiplier (1.5 for 150% faster)
	 * @throws IOException
	 */
	private AudioInputStream changeSpeed(AudioInputStream ais, Double playSpeed) throws IOException{
		AudioFormat format = ais.getFormat();
	    int frameSize = format.getFrameSize();
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    
	    byte[] b = new byte[2^16];
	    int numberOfReadBytes = 1;
	    while( numberOfReadBytes > -1 ) {
	    	numberOfReadBytes = ais.read(b);
	        if ( numberOfReadBytes > 0 )
	            baos.write(b, 0, numberOfReadBytes); }	    
	    byte[] originalLength = baos.toByteArray();
	    
	    byte[] newLength = new byte[(int)(originalLength.length/playSpeed)];
	    for (int i=0 ; i < (newLength.length/frameSize) ; i++) {
	        int index = (int) (i * frameSize * playSpeed);
	        if((index % 2)==1) index ++;
	        
	        for (int j=0 ; j < frameSize ; j++)
	        	newLength[(i*frameSize)+j] = originalLength[index+j];
	    }
	    ByteArrayInputStream bais = new ByteArrayInputStream(newLength);
	    return new AudioInputStream(bais, format, newLength.length/frameSize);
	}
	/**
	 * Determines if the sound is currently playing.
	 * @return true if the sound is playing, false otherwise
	 */
	public boolean isPlaying() {
		return clip.isRunning();
	}

	/**
	 * @return true if the sound must loop, false otherwise
	 */
	public boolean isLooping() {
		return isLooping;
	}

	/**
	 * @param isLooping true if the sound must loop, false otherwise
	 */
	public void setLooping(final boolean isLooping) {
		this.isLooping = isLooping;
	}

	/**
	 * Plays the sound. If you play a sound that is already playing, it will
	 * start again from the beginning.
	 */
	public void play() {
		clip.setFramePosition(0);

		final int loopCount = this.isLooping() ? Clip.LOOP_CONTINUOUSLY : 0;
		clip.loop(loopCount);
	}

}
