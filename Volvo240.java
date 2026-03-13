import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Volvo240 extends Car{

    private final static double trimFactor = 1.25;
    private double pos_x;
    private double pos_y;

    public Volvo240(double pos_x, double pos_y) throws IOException {
        super(pos_x, pos_y);
        nrDoors = 4;
        color = Color.black;
        enginePower = 100;
        modelName = "Volvo240";
        stopEngine();
        image = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Volvo240.jpg"));
    }

    @Override
    public double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }

}