import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

    CarModel model;
    // A list of cars, modify if needed
//    private final ArrayList<Car> cars;
//    private int workshopX = 300;
//    private int workshopY = 300;
//    Workshop<Volvo240> volvoWorkshop = new Workshop<>(10);



//    public CarController() throws IOException {
//
//        cars = new ArrayList<>();
//
////        cars.add(new Volvo240(0, 0));
////        cars.add(new Saab95(0, 200));
////        cars.add(new Scania(0, 400));
////        cars.add(new Volvo240(0, 300));
//
//        model = new CarModel(cars);
//
//        // Start a new view and send a reference of self
//        frame = new CarView("CarSim 1.0", model, cars);
//
//        // Start the timer
//        timer.start();
//    }

    //methods:
    public CarController(CarModel model){
        this.model = model;
    }

    public void setFrame(CarView frame){
        this.frame = frame;
    }

    public void start(){
        timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            model.update();
            frame.drawPanel.repaint();
        }
    }

    public void addCar(CarType type){
        try {
            model.addCar(type);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void removeCar(){
        model.removeCar();
        frame.drawPanel.repaint();
    }

}
