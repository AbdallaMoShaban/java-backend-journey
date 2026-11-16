public class Customer {
    private int id;
    private String name;
    private String phone;
    private int rentedCarId=-1 ;
    private int numberOfRentedDays=0;
    private double totalPaid;
    private  int count;
    public Customer(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid += totalPaid;
    }

    public int getRentedCarId() {
        return rentedCarId;
    }

    public void setRentedCarId(int rentedCarId) {
        this.rentedCarId = rentedCarId;
    }

    public int getNumberOfRentedDays() {
        return numberOfRentedDays;
    }

    public void setNumberOfRentedDays(int numberOfRentedDays) {
        this.numberOfRentedDays = numberOfRentedDays;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", rentedCarId=" + rentedCarId +
                ", totalPaid=" + totalPaid +
                '}';
    }
}
