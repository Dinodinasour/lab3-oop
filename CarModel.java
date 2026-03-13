import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarModel {

    private final ArrayList<Car> cars;
    private int nextY = 0;
    private int workshopX = 300;
    private int workshopY = 300;
    Workshop<Volvo240> volvoWorkshop = new Workshop<>(10);

    public CarModel() throws IOException {

        cars = new ArrayList<>();

//        cars.add(CarFactory.createCar(CarType.VOLVO, 0, 0));
//        cars.add(CarFactory.createCar(CarType.SAAB, 0, 100));
//        cars.add(CarFactory.createCar(CarType.SCANIA, 0, 200));
//        cars.add(CarFactory.createCar(CarType.VOLVO, 0, 300));
    }
    public ArrayList<Car> getCars(){
        return cars;
    }

    public void addCar(CarType type) throws IOException{
        int maxCars = 6;
        if (cars.size() < maxCars) {
            Car car = CarFactory.createCar(type, 0, nextY);
            cars.add(car);
            nextY += 100;
        }
    }

    public void removeCar(){
        if (!cars.isEmpty()){
            cars.remove(cars.size()-1);
            nextY -= 100;
        }
    }

    public void update(){
        List<Car> toRemove = new ArrayList<Car>();

        for (Car car : cars){
            car.move();

            if (car instanceof Volvo240){
                double dx = car.getPos_x() - workshopX;
                double dy = car.getPos_y() - workshopY;

                double distance = Math.sqrt(dx*dx + dy*dy);

                if (distance < 50){
                    volvoWorkshop.loadCar((Volvo240) car);
                    toRemove.add(car);
                }
            }
        }
        cars.removeAll(toRemove);
    }

    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars
        ) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) /100;
        for (Car car : cars) {
            car.brake((brake));
        }
    }

    void turboOn() {
        for (Car car : cars) {
            if (car instanceof Saab95) {
                Saab95.setTurboOn();
            }
        }
    }

    void turboOff() {
        for (Car car : cars) {
            if (car instanceof Saab95) {
                Saab95.setTurboOff();
            }
        }
    }

    void liftBed() {
        for (Car car : cars) {
            if (car instanceof Scania) ((Scania)car).raiseRamp();
        }
    }

    void lowerBed() {
        for (Car car : cars){
            if (car instanceof Scania) ((Scania)car).lowerRamp();
        }
    }

    void startCars() {
        for (Car car : cars){
            car.startEngine();
        }
    }

    void stopCars() {
        for (Car car : cars){
            car.stopEngine();
        }
    }
}
