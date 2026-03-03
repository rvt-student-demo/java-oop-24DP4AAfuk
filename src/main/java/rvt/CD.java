package rvt;

public class CD implements Packable {
    private String artist;
    private String title;
    private int releaseYear;

    public CD(String artist, String title, int releaseYear) {
        this.artist = artist;
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public double weight() {
        return 0.1;
    }

    @Override
    public String toString() {
        return artist + ": " + title + " (" + releaseYear + ")";
    }
}
