//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Veichle> rentedVeichles = new ArrayList<>();
    int counter =1;

    System.out.println("Veichle Rental System");
    System.out.println("Choose from the menue");
    System.out.println("1/ Rent a CAR");
    System.out.println("2/ Rent a BIKE");
    System.out.println("3/ Rent a TRUCK");
    System.out.println("4/ View ALL rented veichles");
    System.out.println("5/ EXIT ");
    System.out.print("Enter your Choice : ");
    int answer = scanner.nextInt();

    while( answer!= 5){
        switch (answer){
            case 1:
                System.out.print("Type your Car Model : ");
                scanner.nextLine();
                String model = scanner.nextLine();
                System.out.print("Enter your Rental Days : ");
                int days = scanner.nextInt();

                Veichle car = new Car(model,days);
                rentedVeichles.add(car);
                car.displayDetails();
                break;

            case 2:
                System.out.print("Type your Bike brand : ");
                scanner.nextLine();
                String brand = scanner.nextLine();
                System.out.print("Enter your Rental hours : ");
                int hours = scanner.nextInt();

                Veichle bike = new Bike(brand,hours);
                rentedVeichles.add(bike);
                bike.displayDetails();
                break;

            case 3:
                System.out.print("Type your Truck Type : ");
                scanner.nextLine();
                String type = scanner.nextLine();
                System.out.print("Enter your Rental Weeks : ");
                int weeks = scanner.nextInt();

                Veichle truck = new Truck(type,weeks);
                rentedVeichles.add(truck);
                truck.displayDetails();
                break;

            case 4:
                System.out.println("                                                  ");
                System.out.println("you have "+ rentedVeichles.size() + " Veichles ");
                System.out.println("rented Veichles details : ");
                for(Veichle veichle : rentedVeichles){
                    System.out.println("            ");
                    System.out.println("Veichle Number "+ counter +" /");
                    counter++;
                    veichle.displayDetails();
                }

        }

        System.out.println("----------------------------");

        System.out.println("Veichle Rental System");
        System.out.println("Choose from the menue");
        System.out.println("1/ Rent a CAR");
        System.out.println("2/ Rent a BIKE");
        System.out.println("3/ Rent a TRUCK");
        System.out.println("4/ View ALL rented veichles");
        System.out.println("5/ EXIT ");
        System.out.print("Enter your Choice : ");
        answer = scanner.nextInt();
    }


    System.out.print(" Thank you for using the Veichle Rental system ! " );

}
