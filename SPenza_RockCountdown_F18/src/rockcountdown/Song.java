package rockcountdown;

public class Song {

	private int rank;
	private String title;
	private String artist;


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getArtist() {
		return artist;
	}


	public void setArtist(String artist) {
		this.artist = artist;
	}


	public Song(int rank, String title, String artist) {
		super();
		this.rank = rank;
		this.title = title;
		this.artist = artist;
	}


	public Song(String tokens) {
		super();
		String[] token = tokens.split("\t");
		for (String songs : token)
			songs = songs.trim();
		rank = Integer.parseInt(token[0]);
		title = token[1];
		artist = token[2];
		// TODO Auto-generated constructor stub
	}

}
