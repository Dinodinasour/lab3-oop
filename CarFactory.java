import java.io.IOException;
import java.util.Random;

public class CarFactory {

    private static final Random random = new Random();
    public static Car createCar(CarType type, double x, double y) throws IOException{

        if (type == CarType.RANDOM){
            CarType[] values = {CarType.VOLVO, CarType.SAAB, CarType.SCANIA, CarType.FERRARI};
            type = values[random.nextInt(values.length)];
        }
        switch (type){
            case VOLVO:
                return new Volvo240(x, y);

            case SAAB:
                return new Saab95(x, y);

            case SCANIA:
                return new Scania(x, y);

            case FERRARI:
                return new Ferrari(x, y);

            default:
                throw new IllegalArgumentException("Unknown car type");
        }
    }
}
