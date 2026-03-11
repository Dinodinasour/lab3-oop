import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    private final ArrayList<Car> cars;
    private int workshopX = 300;
    private int workshopY = 300;
    Workshop<Volvo240> volvoWorkshop = new Workshop<>(10);



    public CarController() throws IOException {

        cars = new ArrayList<>();

        cars.add(new Volvo240(0, 0));
        cars.add(new Saab95(0, 200));
        cars.add(new Scania(0, 400));
        cars.add(new Volvo240(0, 300));

        // Start a new view and send a reference of self
        frame = new CarView("CarSim 1.0", this, cars);

        // Start the timer
        timer.start();
    }

    //methods:

    public static void main(String[] args) throws IOException {
        // Instance of this class
        CarController cc = new CarController();


    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Collection<Car> toRemove = new ArrayList<>();
            for (Car car : cars) {
                car.move();
                int x = (int) Math.round(car.getPos_x());
                int y = (int) Math.round(car.getPos_y());
                if (car instanceof Volvo240) {
                    double dx = car.getPos_x() - workshopX;
                    double dy = car.getPos_y() - workshopY;

                    double distance = Math.sqrt(dx*dx + dy*dy);

                    if (distance < 50) {
                        volvoWorkshop.loadCar((Volvo240) car);
                        toRemove.add(car);
                    }
                }
                //frame.drawPanel.moveit(x, y);
                // repaint() calls the paintComponent method of the panel
                //frame.drawPanel.repaint();
            }
            cars.removeAll(toRemove);
            frame.drawPanel.repaint();
        }
    }

    // Calls the gas method for each car once
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
