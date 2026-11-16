public static void menu(){
    System.out.println(" ");
    System.out.println("===========================================");
    System.out.println("Welcome to A M S CAR RENTAl ");
    System.out.println("===========================================");
    System.out.println("Press 1 to Add Regular Car");
    System.out.println("Press 2 to Add Luxury Car");
    System.out.println("Press 3 to Add Customer");
    System.out.println("Press 4 to Display All Car");
    System.out.println("Press 5 to Display Available Cars");
    System.out.println("Press 6 to Rent a Car");
    System.out.println("Press 7 to Return a Car");
    System.out.println("Press 8 to Search Car by ID");
    System.out.println("Press 9 to Search Car by Brand");
    System.out.println("Press 10 to Display All Customers");
    System.out.println("Press 0 to Exist");
    System.out.println("===========================================");
    System.out.print("Enter your Choice --> ");
    System.out.println(" ");
}

void main() {
    Scanner in =new Scanner(System.in);
    Car [] cars= new Car[20];
    Customer [] customers=new Customer[20];
    int choice;
    do
    {
        menu();
        choice=Integer.parseInt(in.nextLine());
        switch (choice)
        {
            case 1 :{
                boolean isFull=true;
                int counter=-1;
                int carId;
                for (int i =0;i<cars.length;i++){
                    if (cars[i]==null){
                        isFull=false;
                        counter=i;
                        break;
                    }
                }
                if (isFull) System.out.println("The queue is full");
                else {
                    boolean isExist=false;
                    System.out.println("Enter Car id");
                    do {
                        carId=Integer.parseInt(in.nextLine());
                        for (Car car : cars) {
                            if (car != null && carId == car.getId()) {
                                System.out.println("Re Enter the id");
                                isExist = true;
                                break;
                            }
                            else isExist=false;
                            }

                    }while (isExist);
                    System.out.println("Enter Car Brand");
                    String carBrand=(in.nextLine());
                    System.out.println("Enter Car Model");
                    String carModel=(in.nextLine());
                    System.out.println("Enter Car Year");
                    int carYear=Integer.parseInt(in.nextLine());
                    System.out.println("Enter Car Price per day");
                    int carPrice=Integer.parseInt(in.nextLine());
                        cars[counter]=new Car(carId,carBrand,carModel,carYear,carPrice);
                    System.out.println("Add car process is successful" + "Car ID = "+carId);}

                break;}
            case 2 :{
                boolean isFull=true;
                int counter=-1;
                int carId;
                for (int i=0;i<cars.length;i++){
                    if (cars[i]==null){
                        isFull=false;
                        counter=i;
                        break;
                    }
                }
                    if (isFull) System.out.println("The Queue is Full");
                    else {
                        boolean isExist = false;

                        System.out.println("Enter Car id");
                        do {

                            carId=Integer.parseInt(in.nextLine());
                            for (Car car: cars) {
                                if (car != null && carId == car.getId()) {
                                    System.out.println("Re Enter the id");
                                    isExist = true;
                                    break;
                                }
                                else isExist=false;
                            }

                        }while (isExist);
                        System.out.println("Enter Car Brand");
                        String carBrand=(in.nextLine());
                        System.out.println("Enter Car Model");
                        String carModel=(in.nextLine());
                        System.out.println("Enter Car Year");
                        int carYear=Integer.parseInt(in.nextLine());
                        System.out.println("Enter Car Price per day");
                        int carPrice=Integer.parseInt(in.nextLine());
                        System.out.println("This Car is Luxury has insuranceFee Enter this");
                        float insuranceFee=Float.parseFloat(in.nextLine());
                        cars[counter]=new LuxuryCar(carId,carBrand,carModel,carYear,carPrice,insuranceFee);
                        System.out.println("Add car process is successful" + "Car ID = "+carId);
                    }


                break;}
            case 3 :{
                boolean isFull=true;
                int counter=-1;
                int customerId;
                for (int i=0;i<customers.length;i++){
                    if (customers[i]==null){
                        isFull=false;
                        counter=i;
                        break;
                    }
                }
                    if (isFull) System.out.println("The Queue is full");
                    else {
                        boolean exist=false;
                        System.out.println("Enter Customer Id");
                        do {
                            customerId=Integer.parseInt(in.nextLine());
                            for (Customer customer:customers){
                                if (customer!=null&&customerId==customer.getId()){
                                    System.out.println("Re Enter Customer id");
                                exist=true;
                                }
                            }
                        }while (exist);
                        System.out.println("Enter Customer Name");
                        String customerName=in.nextLine();
                        System.out.println("Enter Customer phone Number ");
                        String phone=in.nextLine();
                        customers[counter]=new Customer(customerId,customerName,phone);

                    }
                System.out.println("Add Customer Process is Successful");
                break;}
            case 4 :{
                boolean Empty=false;
                int counter=1;
                for (Car car:cars){
                    if (car!=null){
                        System.out.println("Car number "+counter+"- "+car);
                        counter++;
                        Empty=true;
                    }
                }
                if (!Empty) System.out.println("The Fleet is Empty");


                break;}
            case 5 :{
                int counter=1;
                for (Car car : cars) {
                    if (car != null && car.isAvailability()) {
                        System.out.println("Car number " + counter + "- " + car);
                        counter++;
                    }
                }
                break;}
            case 6 :{
                boolean customerIsExist = false;
                boolean carNotFound = false;
                int customerCount = -1;
                System.out.println("Enter Customer Id ");
                int customerId = Integer.parseInt(in.nextLine());
                for (int i = 0; i < customers.length; i++) {
                    if (customers[i] != null && customers[i].getId() == customerId && customers[i].getRentedCarId() ==-1) {
                        customerIsExist = true;
                        customerCount = i;
                        break;
                    }

                }
                if (!customerIsExist){ System.out.println("Error");}

                else {
                    int carCounter = -1;
                    System.out.println("Enter Car Id");
                    int carId = Integer.parseInt(in.nextLine());
                    for (int i = 0; i < cars.length; i++) {
                        if (cars[i] != null && cars[i].getId() == carId && cars[i].isAvailability()) {
                            carCounter = i;
                            carNotFound = true;
                            break;
                        }

                    }
                    if (!carNotFound) System.out.println("Error");
                    else {
                        if (cars[carCounter] instanceof LuxuryCar luxuryCar) {
                            int days;
                            do {
                                System.out.println("the Car is Luxury Enter Number of days to rent greater than " + luxuryCar.getMinimumNumber());
                                days = Integer.parseInt(in.nextLine());
                                if (days < luxuryCar.getMinimumNumber()) {
                                    System.out.println("Re Enter");
                                }


                            } while (days < luxuryCar.getMinimumNumber());
                            System.out.println("============The Cost================");
                            double finalCost = luxuryCar.getInsuranceFee() * luxuryCar.getTaxRate() * luxuryCar.getPrice() * days;
                            System.out.println("Customer Name: " + customers[customerCount].getName() + "Car Id: " + luxuryCar.getId() + "\n" +
                                    " Car Brand: " + luxuryCar.getBrand() + "Car Model: " + luxuryCar.getModel() + "Number od Days: " + days + "\n" +
                                    "The Cost: " + finalCost);
                            System.out.println("====================================");
                            customers[customerCount].setRentedCarId(luxuryCar.getId());
                            customers[customerCount].setTotalPaid(finalCost);
                            luxuryCar.setAvailability(false);
                            System.out.println("Car Rented Successful");
                        } else {
                            int days;
                            do {
                                System.out.println("Enter Number of days to rent ");
                                days = Integer.parseInt(in.nextLine());
                                if (days < 0) {
                                    System.out.println("Re Enter");
                                }


                            } while (days < 0);
                            System.out.println("============The Cost================");
                            assert cars[carCounter] != null;
                            double finalCost = cars[carCounter].getTaxRate() * cars[carCounter].getPrice() * days;
                            System.out.println("Customer Name: " + customers[customerCount].getName() + "Car Id: " + cars[carCounter].getId() + "\n" +
                                    " Car Brand: " + cars[carCounter].getBrand() + "Car Model: " + cars[carCounter].getModel() + "Number od Days: " + days + "\n" +
                                    "The Cost: " + finalCost);
                            customers[customerCount].setRentedCarId(cars[carCounter].getId());
                            customers[customerCount].setTotalPaid(finalCost);
                            cars[carCounter].setAvailability(false);

                            System.out.println("====================================");

                            System.out.println("Car Rented Successful");
                        }
                    }
                }




                break;
            }
            case 7 :{
                System.out.println("Enter Customer Id ");
                int customerId=Integer.parseInt(in.nextLine());
                int customerCount=-1;
                boolean FoundTheId=false;
                for (int i=0;i<customers.length;i++){
                    if (customers[i]!=null&&customers[i].getId()==customerId){
                        FoundTheId=true;
                        customerCount=i;

                    }
                }
                if (!FoundTheId) System.out.println("The Customer Not Found");
                else {
                    boolean returned=false;
                    if (customers[customerCount].getRentedCarId()>-1){
                        for (Car car :cars ){
                            if (car!=null&&car.getId()==customers[customerCount].getRentedCarId()){
                                car.setAvailability(true);
                                customers[customerCount].setRentedCarId(-1);
                                customers[customerCount].setCount(1);
                                returned=true;
                            }
                        }
                        if (returned) System.out.println("car is returned");
                        else System.out.println("Car not found");
                    }else System.out.println("Car id Not found");
                }



                break;}
            case 8 :{
                boolean carIsFound=false;
                int counter=-1;
                System.out.println("Enter Car Id");
                int carId=Integer.parseInt(in.nextLine());
                for (int i =0;i< cars.length;i++){
                    if (cars[i]!=null&&cars[i].getId()==carId){
                        carIsFound=true;
                        counter=i;
                        break;
                    }
                }
                if (!carIsFound) System.out.println("Car not found");
                else {
                    System.out.println(cars[counter]);
                }


                break;}
            case 9 :{
              System.out.println("Enter Car Brand Name");
              String brandName=in.nextLine();
                for (Car car : cars) {
                    if (car != null && car.getBrand().equals(brandName)) {
                        System.out.println(car);
                    }
                }





              break;}
            case 10:{
                boolean noCus=false;
                for (Customer customer:customers){
                    if (customer!=null){
                    System.out.println(customer);
                    noCus=true;

                }
                }
                if (!noCus) System.out.println("No customer");
                break;}
            case 0 :{
                System.out.println("Thank You ");

                break;}
        }
    }while (choice!=0);

}