import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<Car> cars = new ArrayList<>();
        CarModel model = new CarModel();
        CarController cc = new CarController(model);
        CarView view = new CarView("CarSim 1.0", model);

        cc.setFrame(view);
        cc.start();

    }
}
