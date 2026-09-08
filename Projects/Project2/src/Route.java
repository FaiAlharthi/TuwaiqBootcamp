public class Route {
    private String pickUpAddress;
    private String destAddress;
    private double tripPrice;

    public Route() {
    }

    public Route(String pickUpAddress, String destAddress, double tripPrice) {
        this.pickUpAddress = pickUpAddress;
        this.destAddress = destAddress;
        this.tripPrice = tripPrice;
    }

    //setters
    public void setPickUpAddress(String pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    public void setTripPrice(double tripPrice) {
        this.tripPrice = tripPrice;
    }

    //getters
    public String getPickUpAddress() {
        return pickUpAddress;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public double getTripPrice() {
        return tripPrice;
    }
}
