import java.awt.*;

public abstract class Car implements Movable, IworkshopCar{

    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name
    protected double pos_x;
    protected double pos_y;
    protected int dx = 1; // Direction x
    protected int dy = 0; // Direction y

    public int getNrDoors() {
        return nrDoors;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }
    public Color getColor(){
        return color;
    }
    public double getPos_x(){return pos_x;}
    public double getPos_y(){return pos_y;}

    public void setPosition(double pos_x, double pos_y){
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }

    public void setColor(Color clr) {
        color = clr;
    }
    public void startEngine() {
        currentSpeed = 0.1;
    }
    public void stopEngine() {
        currentSpeed = 0;
    }
    public double speedFactor(){
        return enginePower * 0.01;
    };

    /**
     * Gases if amout is between 0 and 1
     * @param amount
     */
    public void gas(double amount){
        if (amount > 0.0 && amount < 1.0) {
            incrementSpeed(amount);
        }
        else {
            System.out.println("Invalid gas input");
        }
    };

    public void brake(double amount){
        if (amount > 0 && amount < 1) {
            decrementSpeed(amount);
        }
        else {
            System.out.println("Invalid brake input");
        }
    };


    @Override
    public void move() {
        pos_x = (getPos_x() + currentSpeed * dx);
        pos_y = (getPos_y() + currentSpeed * dy);

        if (pos_x >= 800 || pos_x <= 0) {
            dx = (dx + 180) % 360;
        }
        if (pos_y >= 800 || pos_y <= 0){
            dy = (dy + 180) & 360;
        }
        pos_x = (getPos_x() + currentSpeed * dx);
        pos_y = (getPos_y() + currentSpeed * dy);
    }
    @Override
    public void turnLeft() {
        int temp = dx;
        dx = - dy;
        dy = temp;
    }

    @Override
    public void turnRight() {
        int temp = dx;
        dx = dy;
        dy = - temp;
    }

    public double distanceTo(Car other){
        double x = this.pos_x - other.pos_x;
        double y = this.pos_y - other.pos_y;
        return Math.sqrt(x*x + y*y);
    }


    public void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    };

    public void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    };
}
