public class Truck implements Veichle{
    private String type;
    private int nofWeeks;

    public Truck(String type, int nofWeeks) {
        this.type= type;
        this.nofWeeks = nofWeeks;
    }

    public int getRate(){
        return 500;
    }

    @Override
    public double calculateRentalCost() {
        return (getRate() * nofWeeks);
    }

    @Override
    public void displayDetails() {
        System.out.println("The Truck Type : "+ type+ "\nWeekly Rental Rate : "+ getRate() +"\nTotal Rental Cost : "+ calculateRentalCost());
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNofWeeks(int nofWeeks) {
        nofWeeks = nofWeeks;
    }

    public String getType() {
        return type;
    }

    public int getNofWeeks() {
        return nofWeeks;
    }
}
