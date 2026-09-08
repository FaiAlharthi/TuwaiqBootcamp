public class Employee {

    private String id;
    private String name;
    private int salary;

    public Employee(){
        //
    }

    public Employee( String id, String name, int Salary){
        this.id = id;
        this.name = name;
        this.salary = salary;
    }


    //Employee methods

    public int getAnnualSalary(){
        return salary * 12;
    }

    public int raisedSalary ( int percent){
        salary += (salary * (percent / 100));
        return salary;
    }

    //setters
    public void setId (String id ){
        this.id = id;
    }
    public void setName (String name ){
        this.name = name;
    }
    public void setSalary (int salary ){
        this.salary = salary;
    }

    //getters
    public String getId(){
        return id;
    }
    public String getname(){
        return name;
    }
    public int getSalary(){
        return salary;
    }


    public String toString (){
        return "Employee Information \n  Employee id : "+ id +
                "\n Employee name : "+ name +
                "\n Employee Salary : "+ salary;
    }
}
