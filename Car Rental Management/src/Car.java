public class Car {
    private int id;
    private String brand;
    private String model;
    private int year;
    private double price;
    private boolean availability=true;
    private byte carCount;
    private final float taxRate=1.14f;

    public Car(int id, String brand, String model, int year,double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price=price;
    }

    public double getPrice() {
        return price;
    }


    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public byte getCarCount() {
        return carCount;
    }

    public void setCarCount(byte carCount) {
        this.carCount += carCount;
    }

    public float getTaxRate() {
        return taxRate;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }


    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", availability=" + availability +
                '}';
    }
}
