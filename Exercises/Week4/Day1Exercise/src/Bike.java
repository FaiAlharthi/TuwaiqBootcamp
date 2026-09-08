public class Bike implements Veichle{
    private String brand;
    private double hours;

    public Bike(String brand, double hours) {
        this.brand = brand;
        this.hours = hours;
    }

    @Override
    public double calculateRentalCost() {
        return (getRate()*hours);
    }

    @Override
    public void displayDetails() {
        System.out.println("The Bike brand : "+ brand+ "\nHourly Rental Rate : "+ getRate() +"\nTotal Rental Cost : "+ calculateRentalCost());
    }

    public int getRate(){
        return 10;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }


    public double getHours() {
        return hours;
    }



    public String getBrand() {
        return brand;
    }


}
