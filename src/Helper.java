import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

public class Helper {

	public static ArrayList<Album> albums;

	public static void readAlbums() {
		System.out.println("reading albums of kanye west ...");
		albums = new ArrayList<Album>();
		BufferedReader bf;
		try {
			bf = new BufferedReader(new FileReader("kanye_database.txt"));
			// read the <albums> tag
			String line = bf.readLine();
			// albums iterator
			while (true) {
				// read the <album> tag
				line = bf.readLine();
				if (line.equals("</albums>")) {
					System.out.println(albums.size() + " albums read...");
					break;
				}
				Album newAlbum = new Album();
				// read the title
				line = bf.readLine().trim().toLowerCase();
				newAlbum.setTitle(StringUtils.substringBetween(line, "<title>", "</title"));
				
				System.out.println("reading album " + newAlbum.getTitle());

				// read the release date
				line = bf.readLine().trim().toLowerCase();
				String dateComma = StringUtils.substringBetween(line, "<releasedate>", "</releasedate>");
				int year, month, day;
				if (dateComma != null) {
					String[] dateSep = dateComma.split(",");
					year = Integer.parseInt(dateSep[0]);
					month = Integer.parseInt(dateSep[1]);
					day = Integer.parseInt(dateSep[2]);
					LocalDate theDate = LocalDate.of(year, month, day);
					newAlbum.setReleaseDate(theDate);
				} else {
					System.out.println("Release date not read properly");
				}
				// read the length
				line = bf.readLine().trim().toLowerCase();
				String lengComma = StringUtils.substringBetween(line, "<length>", "</length>");
				String[] lengSep = lengComma.split(":");
				int minutes = Integer.parseInt(lengSep[0]);
				int seconds = Integer.parseInt(lengSep[1]);
				newAlbum.setMinutes(minutes);
				newAlbum.setSeconds(seconds);

				// collection of tracks
				ArrayList<Track> theTracks = new ArrayList<Track>();
				newAlbum.setTracks(theTracks);

				// read the <tracks> tag
				line = bf.readLine();

				// tracks iterator
				while (true) {
					// read the <track> tag
					line = bf.readLine();
					if (line.equals("</tracks>")) {
						System.out.println(theTracks.size()+" tracks read");

						break;
					}

					Track tx = new Track();
					
					// read the track number
					line = bf.readLine().trim().toLowerCase();
					String number = StringUtils.substringBetween(line, "<number>", "</number");
					tx.setTrackNumber(Integer.parseInt(number));

					// read the title
					line = bf.readLine().trim().toLowerCase();
					tx.setTitle(StringUtils.substringBetween(line, "<title>", "</title"));
					
					System.out.println("Reading Track: " + tx.getTitle());
					
					// read the writers
					line = bf.readLine().trim().toLowerCase();
					String writersComma = StringUtils.substringBetween(line, "<writers>", "</writers");
					String[] writersSep = writersComma.split(",");
					ArrayList<String> writers = new ArrayList<String>();
					for (String s : writersSep) {
						writers.add(s);
					}
					tx.setWriters(writers);

					// read the length
					line = bf.readLine().trim().toLowerCase();
					String lengCommaT = StringUtils.substringBetween(line, "<length>", "</length>");
					String[] lengSepT = lengCommaT.split(":");
					int minutesT = Integer.parseInt(lengSepT[0]);
					int secondsT = Integer.parseInt(lengSepT[1]);
					tx.setMinutes(minutesT);
					tx.setSeconds(secondsT);

					// read the lyrics tag <lyrics>
					line = bf.readLine();
					HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
					
					// lyrics iterator
					while (true) {
						line = bf.readLine().trim().toLowerCase();
						if (line.equals("</lyrics>")) {
							break;
						}
						String[] splitted = line.split(" ");
						for (int i = 0; i < splitted.length; i++) {
							if (wordCount.containsKey(splitted[i])) {
								int cont = wordCount.get(splitted[i]);
								wordCount.put(splitted[i], cont + 1);
							} else {
								wordCount.put(splitted[i], 1);
							}
						}
					}
					// System.out.println(wordCount);
					// read end of track
					line = bf.readLine();
					tx.setWordCount(wordCount);
					System.out.println("Word count vector for " + tx.getTitle());
					System.out.println(tx.getWordCount());
					// finally add the track to the collection
					theTracks.add(tx);
				}
				// read the </album> tag
				line = bf.readLine();
				// add the album to the collection of albums
				albums.add(newAlbum);
			}
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<ArrayList<String>> searchLyrics(String searchTerm) {

		ArrayList<ArrayList<String>> found = new ArrayList<ArrayList<String>>();
		searchTerm = searchTerm.toLowerCase();
		for(Album a : albums)
		{
			for(Track t: a.getTracks())
			{
				if(t.getWordCount().get(searchTerm) != null)
				{
					ArrayList<String> inTrack = new ArrayList<String>();
					int count = t.getWordCount().get(searchTerm);
					inTrack.add(a.getTitle());
					inTrack.add(t.getTrackNumber() + "");
					inTrack.add(t.getTitle() + "");
					inTrack.add(count + "");
					found.add(inTrack);
				}
			}
			
		}
		
		return found;
		
	}

}
