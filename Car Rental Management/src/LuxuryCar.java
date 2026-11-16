public class LuxuryCar extends Car{
    private   float insuranceFee;
    private final byte minimumNumber=3;
    public LuxuryCar(int id, String brand, String model, int year,double price, float insuranceFee) {
        super(id, brand, model, year,price);
        this.insuranceFee = insuranceFee;
    }

    public void setInsuranceFee(float insuranceFee) {
        this.insuranceFee = insuranceFee;
    }

    public float getInsuranceFee() {
        return insuranceFee;
    }

    public byte getMinimumNumber() {
        return minimumNumber;
    }

}


