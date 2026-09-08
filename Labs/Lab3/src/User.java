import java.util.ArrayList;
import java.util.Arrays;

public class User {
    private String username;
    private String email;
    private ArrayList <Media> purchaseMediaList= new ArrayList <>();
    private ArrayList <Media> shoppingCart= new ArrayList <>();


    public User() {
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User(String username, String email, ArrayList<Media> purchaseMediaList, ArrayList<Media> shoppingCart) {
        this.username = username;
        this.email = email;
        this.purchaseMediaList = purchaseMediaList;
        this.shoppingCart = shoppingCart;

    }

    public void addToCart( Media media){
            shoppingCart.add(media);
    }

    public void removeFromCart( Media media){
                    shoppingCart.remove(media);
    }

    public void checkOut(){
        for (Media media : shoppingCart){
            if (media instanceof Book){
                ((Book) media).purchase(this);
            } else {
                purchaseMediaList.add(media);
            }
        }
        shoppingCart.clear();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Media> getPurchaseMediaList() {
        return purchaseMediaList;
    }

    public void setPurchaseMediaList(ArrayList<Media> purchaseMediaList) {
        this.purchaseMediaList = purchaseMediaList;
    }

    public ArrayList<Media> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ArrayList<Media> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", purchaseMediaList=" + purchaseMediaList +
                ", shoppingCart=" + shoppingCart +
                '}';
    }
}
