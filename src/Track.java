import java.util.ArrayList;
import java.util.HashMap;

public class Track {
	private int trackNumber;
	private String title;
	private ArrayList<String> writers;
	private int minutes;
	private int seconds;
	private HashMap<String, Integer> wordCount;
	
	public Track() {
		
	}
	
	public Track(int trackNumber, String title, ArrayList<String> writers, int minutes,
			int seconds) {
		this.trackNumber = trackNumber;
		this.title = title;
		this.writers = writers;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	public int getTrackNumber() {
		return trackNumber;
	}
	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<String> getWriters() {
		return writers;
	}
	public void setWriters(ArrayList<String> writers) {
		this.writers = writers;
	}
	
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public int getSeconds() {
		return seconds;
	}
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	
	public HashMap<String, Integer> getWordCount() {
		return wordCount;
	}
	public void setWordCount(HashMap<String, Integer> wordCount) {
		this.wordCount = wordCount;
	}
	
	
	
	
}
