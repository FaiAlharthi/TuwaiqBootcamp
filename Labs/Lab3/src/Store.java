import java.util.ArrayList;

public class Store {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Media> medias = new ArrayList<>();

    public Store() {
    }

    public Store(ArrayList<User> users, ArrayList<Media> medias) {
        this.users = users;
        this.medias = medias;
    }

    public void addUser( User user){
        users.add(user);
    }

    public ArrayList<User> displayUsers(){
        return users;
    }

    public void addMedia ( Media media){
            medias.add(media);
    }

    public ArrayList<Media> displayMedias(){
        return medias;
    }


    public Book searchBook (String title){
        for(Media media : medias){
            if(media instanceof Book && media.getTitle().equalsIgnoreCase(title)){
                return (Book)media;
            }
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Media> getMedia() {
        return medias;
    }

    public void setMedia(ArrayList<Media> medias) {
        this.medias = medias;
    }

    @Override
    public String toString() {
        return "Store{" +
                "users=" + users +
                ", media=" + medias +
                '}';
    }
}
