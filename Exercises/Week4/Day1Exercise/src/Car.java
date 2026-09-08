public class Car implements Veichle{
    private String model;
    private int days;

    public Car(String model, int days) {
        this.model = model;
        this.days = days;
    }

    @Override
    public double calculateRentalCost() {
        return ( days* getRate() );
    }

    public int getRate(){
        return 50;
    }

    @Override
    public void displayDetails() {
        System.out.println("The car model : "+ model+ "\nDaily Rental Rate : "+ getRate() +"\nTotal Rental Cost : "+ calculateRentalCost());
    }

    //setters
    public void setModel(String model) {
        this.model = model;
    }

    public void setDays(int days) {
        this.days = days;
    }

    //getters
    public String getModel() {
        return model;
    }

    public int getDays() {
        return days;
    }
}
