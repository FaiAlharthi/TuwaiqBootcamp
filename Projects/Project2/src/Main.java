//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    Scanner scanner = new Scanner(System.in);
    int idCounter=0;
    //Project2

    Route trip1Route = new Route("SAB Metro Station", "LEAP", 8);
    Route trip2Route = new Route("KAFD Metro Station", "University", 15);
    Route trip3Route = new Route("Nourth Riyadh", "Boulivard", 20);
    Route trip4Route = new Route("Roshin Front", "Tuwaiq Academy", 22);


    Car van = new Car("4893", 6, trip1Route);
    Car bus = new Car("0290", 30, trip2Route);
    Car whiteVan = new Car("2941", 7, trip4Route);
    Car Ford = new Car("3205", 4, trip3Route);
    Car camry = new Car("5962", 0, trip1Route);


//all existing cars
    ArrayList<Car> cars = new ArrayList<>();
    cars.add(van);
    cars.add(camry);
    cars.add(bus);
    cars.add(whiteVan);
    cars.add(Ford);

    ArrayList <Passenger> passengers = new ArrayList<>();
//all existing passengers
    Passenger passengerWithSub = new SubscriberPassenger("Fai", "11", van);
    Passenger passengerWithNoSub = new NonSubscriberPassenger("Ali", "12", camry,true);
    Passenger passengerWithNoSub2 = new NonSubscriberPassenger("Ahmed", "13", bus,false);

    passengers.add(passengerWithNoSub);
    passengers.add(passengerWithSub);
    passengers.add(passengerWithNoSub2);

    System.out.println("                   ");
    System.out.println("Hello, choose what do you want to do : ");
    System.out.println("1/ Reserve a car");
    System.out.println("2/ List all available cars");
    System.out.println("3/ List all passengers");
    System.out.println("4/ exit ");
    int answer = scanner.nextInt();

    while ( answer != 4 ){
        switch (answer){
            case 1:
                System.out.println("                                                      ---------------------------------- Reserve a car --------------------------------- ");
                System.out.println("Enter your name");
                scanner.nextLine();
                String name = scanner.nextLine();
                System.out.println("                   ");

                while(true){
                    System.out.println("             ");
                    System.out.println("choose your trip number :");
                    System.out.println("             ");
                    for( Car trip : cars){
                    System.out.println("[ Trip number: "+ cars.indexOf(trip) +" ]");
                    System.out.println("Car Code : "+ trip.getCode() +"\nLeft seats Trip : "+trip.getMaxCapacity()+"\npickUp Address : "+ trip.route.getPickUpAddress()+"\nDestonation Address : "+trip.route.getDestAddress()+"\nTrip Price : "+ trip.route.getTripPrice());
                    System.out.println("             ");
                }

                System.out.println("Write the number of the trip most convenient for you : ");
                int chosenTrip = scanner.nextInt();
                if(cars.get(chosenTrip).getMaxCapacity() <= 0){
                    System.out.println("             ");
                    System.out.println("* Selected car has no Space Choose another one");
                    continue;
                }

                System.out.println("Do you have a subscription ? ( YES OR NO )");
                scanner.nextLine();
                String passengerType = scanner.nextLine();
                if(passengerType.equalsIgnoreCase("yes")){
                    Passenger passengerWithSub_User = new SubscriberPassenger(name, String.valueOf(++idCounter), cars.get(chosenTrip));
                    passengers.add(passengerWithSub_User);
                    System.out.println(" __________________________________ ");
                    System.out.println("* Your car reserved successfully");
                    System.out.println("             ");
                    System.out.println("Your Trip info / ");
                    System.out.println(passengerWithSub_User.displayInfo());
                    break;

                } else if (passengerType.equalsIgnoreCase("no")) {
                    System.out.println("Do you have a Copoun ? ( YES OR NO )");
                    String hasCopoun = scanner.nextLine();
                    if(hasCopoun.equalsIgnoreCase("yes")){
                        Passenger passengerWithNoSub_User = new NonSubscriberPassenger(name, String.valueOf(++idCounter), cars.get(chosenTrip),true);
                        passengers.add(passengerWithNoSub_User);
                        System.out.println(" __________________________________ ");
                        System.out.println("* Your car reserved successfully");
                        System.out.println("             ");
                        System.out.println("Your Trip info / ");
                        System.out.println(passengerWithNoSub_User.displayInfo());
                        break;
                    } else if (hasCopoun.equalsIgnoreCase("no")){
                        Passenger passengerWithNoSub_User = new NonSubscriberPassenger(name, String.valueOf(++idCounter), cars.get(chosenTrip),false);
                        passengers.add(passengerWithNoSub_User);
                        System.out.println(" __________________________________ ");
                        System.out.println("* Your car reserved successfully");
                        System.out.println("             ");
                        System.out.println("Your Trip info / ");
                        System.out.println(passengerWithNoSub_User.displayInfo());
                        break;
                    }
                }}
                break;
                //all available cars with available space
            case 2:
                System.out.println("                                                 ---------------------------------- all available cars --------------------------------- ");
                for (Car car : cars){
                    if(car.getMaxCapacity() != 0){
                        System.out.println("             ");
                        System.out.println("Car number / "+ (cars.indexOf(car)+1) );
                        System.out.println( "Seats Left : "+car.getMaxCapacity() +"\nCar Code : "+ car.getCode() +"\npickUp Address : "+ car.route.getPickUpAddress()+"\nDestonation Address : "+car.route.getDestAddress()+"\nTrip Price : "+ car.route.getTripPrice());
                    }
                }
                break;

            case 3:
                System.out.println("                                                    ---------------------------------- all passengers --------------------------------- ");
                for( Passenger passenger : passengers){
                    System.out.println("             ");
                    System.out.println("Passenger number / "+ (passengers.indexOf(passenger)+1) );
                    System.out.println( passenger.displayInfo());
                    System.out.println( "Seats Left : " + passenger.reservedCar.getMaxCapacity());

                }

        }//end of Switch
        System.out.println(" ____________________________________________________________ ");
        System.out.println("                                              ");
        System.out.println(" Choose another process to do : ");
        System.out.println("1/ Reserve a car");
        System.out.println("2/ List all available cars");
        System.out.println("3/ List all passengers");
        System.out.println("4/ exit ");
        answer = scanner.nextInt();
    }









    }
