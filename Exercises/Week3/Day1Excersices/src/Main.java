//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    System.out.println( "    " );
    System.out.println( " ________________________________________________________ Account System ________________________________________________________________ " );


    Account account1 = new Account();
    Account account2 = new Account();
    Account account3 = new Account();
    Account account4 = new Account();
    Account account5 = new Account();

    //sending data

    //account 1 info
    account1.setId("1");
    account1.setName("Fai");
    account1.setBalance(30000);

    //account 2 info
    account2.setId("2");
    account2.setName("Ali");
    account2.setBalance(200);

    //account 3 info
    account3.setId("3");
    account3.setName("Noura");
    account3.setBalance(500);

    //account 4 info
    account4.setId("4");
    account4.setName("Anoud");
    account4.setBalance(200);

    //account 5 info
    account5.setId("5");
    account5.setName("Asma");
    account5.setBalance(10000);

    //retrieving data

    //from account 1
    System.out.println( "    " );
    System.out.println("Account 1 Information ");
    System.out.println("Account ID : " + account1.getId());
    System.out.println("Account owner name : "+ account1.getName());
    System.out.println("Account Balance : "+ account1.getBalance());
    System.out.println( "    " );

    //from account 2
    System.out.println("Account 3 Information ");
    System.out.println("Account ID : " + account2.getId());
    System.out.println("Account owner name : "+ account2.getName());
    System.out.println("Account Balance : "+ account2.getBalance());
    System.out.println( "    " );

    //from account 1
    System.out.println("Account 3 Information ");
    System.out.println("Account ID : " + account3.getId());
    System.out.println("Account owner name : "+ account3.getName());
    System.out.println("Account Balance : "+ account3.getBalance());
    System.out.println( "    " );

    //from account 1
    System.out.println("Account 4 Information ");
    System.out.println("Account ID : " + account4.getId());
    System.out.println("Account owner name : "+ account4.getName());
    System.out.println("Account Balance : "+ account4.getBalance());
    System.out.println( "    " );

    //from account 1
    System.out.println("Account 5 Information ");
    System.out.println("Account ID : " + account5.getId());
    System.out.println("Account owner name : "+ account5.getName());
    System.out.println("Account Balance : "+ account5.getBalance());
    System.out.println( "  -----------------------------------------------------  " );


    //doing bank operations
    System.out.println("Bank operations : ");

    //credit ( Add money )
    System.out.println("Add money to account 2 ");
    System.out.println(" OLD Balance : "+ account2.getBalance());
    System.out.println(account2.credit(500));
    System.out.println(" NEW Balance : "+ account2.getBalance());
    System.out.println( "    " );

    //Withdraw
    System.out.println("withdraw money from account 1 " );
    System.out.println(" OLD Balance : "+ account1.getBalance());
    System.out.println(" done ? "+account1.debits(1000));
    System.out.println(" NEW Balance : "+ account1.getBalance());
    System.out.println( "    " );

    //Transfer Money
    System.out.println("Transfer money from account 3 To account 4" );
    System.out.println(" Account 3 OLD Balance : "+ account3.getBalance() + " |  Account 4 OLD Balance : "+ account4.getBalance() );
    account3.transferTo(account4,100);
    System.out.println(" Account 3 New Balance : "+ account3.getBalance() + " |  Account 4 NEW Balance : "+ account4.getBalance() );
    System.out.println( "    " );

    System.out.println("Printing account 5 info by toString method" );
    System.out.println(account5.toString());



    System.out.println( " ________________________________________________________ Employee System ________________________________________________________________ " );
    System.out.println( "  " );

    Employee employee1= new Employee();
    Employee employee2= new Employee();
    Employee employee3= new Employee();
    Employee employee4= new Employee();
    Employee employee5= new Employee();

    //sending information

    //Employee1
    employee1.setId("1");
    employee1.setName("Fai");
    employee1.setSalary(40000);

    //Employee2
    employee2.setId("2");
    employee2.setName("Sara");
    employee2.setSalary(20000);

    //Employee3
    employee3.setId("3");
    employee3.setName("Nouf");
    employee3.setSalary(50000);

    //Employee4
    employee4.setId("4");
    employee4.setName("Alanoud");
    employee4.setSalary(8000);

    //Employee5
    employee5.setId("5");
    employee5.setName("Ali");
    employee5.setSalary(35000);


    //retrieving Employees information

    //from Employee 1
    System.out.println("       ");
    System.out.println("Employee 1 Info : ");
    System.out.println("Employee 1 ID : " + employee1.getId());
    System.out.println("Employee 1 Name : " + employee1.getname());
    System.out.println("Employee 1 Salary : " + employee1.getSalary());
    System.out.println("       ");

    //from Employee 2
    System.out.println("Employee 2 Info : ");
    System.out.println("Employee 2 ID : " + employee2.getId());
    System.out.println("Employee 2 Name : " + employee2.getname());
    System.out.println("Employee 2 Salary : " + employee2.getSalary());
    System.out.println("       ");

    //from Employee 3
    System.out.println("Employee 3 Info : ");
    System.out.println("Employee 3 ID : " + employee3.getId());
    System.out.println("Employee 3 Name : " + employee3.getname());
    System.out.println("Employee 3 Salary : " + employee3.getSalary());
    System.out.println("       ");

    //from Employee 4
    System.out.println("Employee 4 Info : ");
    System.out.println("Employee 4 ID : " + employee4.getId());
    System.out.println("Employee 4 Name : " + employee4.getname());
    System.out.println("Employee 4 Salary : " + employee4.getSalary());
    System.out.println("       ");

    //from Employee 5
    System.out.println("Employee 5 Info : ");
    System.out.println("Employee 5 ID : " + employee5.getId());
    System.out.println("Employee 5 Name : " + employee5.getname());
    System.out.println("Employee 5 Salary : " + employee5.getSalary());
    System.out.println("   ------------------------------------------------    ");

    //testing other methods
    System.out.println("the annual salary of Employee 4 : "+ employee4.getAnnualSalary());
    System.out.println("the salary for Employee 4, after having 10% raise : "+ employee4.raisedSalary(10));

    System.out.println("Printing Employee 2 info by toString method" );
    System.out.println(employee2.toString());


}
