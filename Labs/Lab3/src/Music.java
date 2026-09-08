import java.util.ArrayList;

public class Music extends Media {
    private String artist;

    public Music() {
    }

    public Music(String title, String auther, String ISBN, double price) {
        super(title, auther, ISBN, price);
        this.artist = auther;
    }

    public void listen(User user){
        System.out.println("Listenning to music");

    }

    public ArrayList<Music> generatePlaylist (ArrayList<Music> musics){
        ArrayList<Music> reccommendedMusic = new ArrayList<>();
        for(Music music : musics){
            if( music != this && music.getAuteur().equalsIgnoreCase(this.getAuteur())){
                reccommendedMusic.add(music);
            }
        }
        return reccommendedMusic;
    }

    public String getMediaType(){
        if(getPrice() >= 10){
            return "Premium Music";
        }
        return "Music";
    }


    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Music{" +
                "artist='" + artist + '\'' +
                '}';
    }
}
