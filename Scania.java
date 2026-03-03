import java.awt.*;

public class Scania extends Car implements Truck{

    protected Ramp ramp;
/** ramp angle between 0 and 70.
 * Can only lower and rise ramp while speed = 0
 * Can only drive if ramp angle = 0
 *
   **/
    public Scania(){
        nrDoors = 2;
        color = Color.blue;
        enginePower = 130;
        modelName = "Scania";
        stopEngine();
        ramp = new Ramp(70, 0);
    }


    public void raiseRamp(){
        if (currentSpeed == 0){
            ramp.setAngle(ramp.getAngle() + 10);
        }
    }

    public void lowerRamp(){
        if (currentSpeed == 0){
            ramp.setAngle(ramp.getAngle() - 10);
        }
    }


    @Override
    public void gas(double amount) {
        if (ramp.getAngle() == 0 && amount > 0.0 && amount < 1.0){
            incrementSpeed(amount);
        }
        else
            System.out.println("Cannot gas under those conditions");
    }

    @Override
    public void brake(double amount) {
        if (ramp.getAngle() == 0 && amount > 0.0 && amount < 1.0){
            decrementSpeed(amount);
        }
        else
            System.out.println("Cannot brake under those conditions");
    }

}
