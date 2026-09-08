public class NonSubscriberPassenger extends Passenger {
    final double discount = 0.10;
    private boolean discCopoun;

    public NonSubscriberPassenger(String name, String ID, Car reservedCar, boolean discCopoun) {
        super(name, ID, reservedCar);
        this.discCopoun = discCopoun;
    }

    @Override
    public double reserveCar(Car car) {
        try {
            if(car.getMaxCapacity() <= 0 ) {
                throw new Exception("* No more Available seats left in this car ");
            }else{
                car.setMaxCapacity((car.getMaxCapacity() - 1));
                if(discCopoun == true) {
                    return (reservedCar.route.getTripPrice() - (reservedCar.route.getTripPrice() * discount));
                }else {
                    return (reservedCar.route.getTripPrice());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return reservedCar.route.getTripPrice();
    }

    @Override
    public String displayInfo() {
        return "Passenger Name : "+ name +"\nPassenger ID : "+ ID+"\nCar Code : "+ reservedCar.getCode() + "\nStart pickUp Address : "+ reservedCar.route.getPickUpAddress() + "\nDestination Address : "+ reservedCar.route.getDestAddress() + "\nTrip Cost : "+ reserveCar(reservedCar);
    }

    //setter
    public void setDiscCopoun(boolean discCopoun) {
        this.discCopoun = discCopoun;
    }

    //getter
    public boolean isDiscCopoun() {
        return discCopoun;
    }


}
