import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Ferrari extends Car implements Truck{

    private Ramp ramp;
    private ArrayList<Car> carList;
    /** ramp angle either 0 or 45
     * Ramp can only be 45 if speed = 0
     * Cars can be loaded if Truck stands still and ramp = 45
     * Cars have to be close to Truck to be loaded
     * Cars can be unloaded if Truck stands still and ramp = 45
     * Cars will be close in position to Truck after being unloaded
     * Cars are loaded first-in-last-out
     * Truck cannot load other Trucks
     * Cars that are loaded have same position as the Truck
     */
    public Ferrari(double pos_x, double pos_y) throws IOException {
        super(pos_x, pos_y);
        nrDoors = 2;
        color = Color.pink;
        enginePower = 120;
        modelName = "Truck";
        ramp = new Ramp(45, 0);
        carList = new ArrayList();
        image = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Ferrari.jpg"));


    }

    public void raiseRamp(){
        if (currentSpeed == 0){
            ramp.setMaxAngle();
        }
    }

    public void lowerRamp(){
        if (currentSpeed == 0){
            ramp.setMinAngle();
        }
    }
    //Behöver lägga till conditions för att bilen som lastas har
    //närliggande position till Truck.
    public void loadCar(Car car){
        if (currentSpeed == 0 && ramp.getAngle() == 45 &&
            !(car instanceof Truck) &&
            this.distanceTo(car) < 2.0){
            carList.add(car);

            car.setPosition(this.getPos_x(), this.getPos_y());
        }
    }

    public void unloadCar(){
        if (currentSpeed == 0 && ramp.getAngle() == 45 && !(carList.isEmpty())){
            Car car = carList.remove(carList.size() - 1);

            car.setPosition(this.getPos_x() - 1, this.getPos_y());
        }
    }

    public ArrayList getCarList() {
        return carList;
    }

    @Override
    public void gas(double amount) {
        if (ramp.getAngle() == 0 && amount > 0.0 && amount < 1.0){
            incrementSpeed(amount);
        }

    }

    @Override
    public void brake(double amount) {
        if (ramp.getAngle() == 0 && amount > 0.0 && amount < 1.0){
            decrementSpeed(amount);
        }

    }

    @Override
    public void move(){
        super.move();

        for (Car car : carList){
            car.setPosition(this.getPos_x(), this.getPos_y());
        }
    }

}
