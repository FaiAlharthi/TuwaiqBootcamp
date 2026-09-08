public class Account {
    private String id;
    private String name;
    private int balance =0;

    public Account (){
        //
    }

    public Account(String id, String name){
        this.id = id;
        this.name = name;
    }

    public Account (String id,String name, int balance){
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    //methods of class Account

    //add money you yourAccount
    public int credit(int amount){
        balance += amount;
        return balance;
    }

    //withdraw of bank account
    public boolean debits(int amount){
        if ( balance >= amount ){
            balance -= amount;
            System.out.println(" this is your updated balance : "+ balance);
            return true;
        }else{
            System.out.println(" You don't have this amount of money in your bank account");
        }
        return false;
    }

    public int transferTo(Account another, int amount){
        if (debits( amount)){
            another.balance += amount;
            System.out.println("Transaction is Done");
        }else{
            System.out.println("You don't have this amount of money in your bank account");
        }
        return balance;
    }

    public String toString(){
        return "Account Information : " +
                "\n Account ID : " + id +
                "\n Account Name : " + name +
                "\n Account Balance : "+ balance;
    }

    //setters
    public void setId (String id){
        this.id = id;
    }
    public void setName (String name){
        this.name = name;
    }
    public void setBalance (int balance){
        this.balance = balance;
    }

    //getters
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getBalance(){
        return balance;
    }
}
