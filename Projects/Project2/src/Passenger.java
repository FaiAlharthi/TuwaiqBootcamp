public abstract class Passenger {
    String name;
    String ID;
    Car reservedCar;

    public Passenger(String name, String ID, Car reservedCar) {
        this.name = name;
        this.ID = ID;
        this.reservedCar = reservedCar;
    }


    public abstract double reserveCar (Car car);
    public abstract String displayInfo();
}
