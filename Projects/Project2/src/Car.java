public class Car {
    private String code;
    private int maxCapacity;
    Route route;

    public Car() {
    }

    public Car(String code, int maxCapacity, Route route) {
        this.code = code;
        this.maxCapacity = maxCapacity;
        this.route = route;
    }

    //setters
    public void setCode(String code) {
        this.code = code;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    //getters

    public String getCode() {
        return code;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public Route getRoute() {
        return route;
    }
}
