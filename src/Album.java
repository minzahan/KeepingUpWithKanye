import java.time.LocalDate;
import java.util.ArrayList;

public class Album {
	private String title;
	private LocalDate releaseDate;
	private int minutes;
	private int seconds;
	private ArrayList<Track> tracks;
	
	public Album()
	{
		
	}
	
	public Album(String title, LocalDate releaseDate, int minutes, int seconds, ArrayList<Track> tracks) {
		super();
		this.title = title;
		this.releaseDate = releaseDate;
		this.minutes = minutes;
		this.seconds = seconds;
		this.tracks = tracks;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
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
	public ArrayList<Track> getTracks() {
		return tracks;
	}
	public void setTracks(ArrayList<Track> tracks) {
		this.tracks = tracks;
	}
}