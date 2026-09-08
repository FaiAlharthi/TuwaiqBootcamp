public class SubscriberPassenger extends Passenger {
    final double discount = 0.50;

    public SubscriberPassenger(String name, String ID, Car reservedCar) {
        super(name, ID, reservedCar);
    }

    @Override
    public double reserveCar(Car car) {
        try {
            if(car.getMaxCapacity() <= 0 ) {
                throw new Exception(" No more Available seats left in this car ");
            }else{
                car.setMaxCapacity((car.getMaxCapacity() - 1));
                return (reservedCar.route.getTripPrice() - (reservedCar.route.getTripPrice() * discount));
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
}
