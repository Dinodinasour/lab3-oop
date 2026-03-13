import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Saab95 extends Car{

    private static boolean turboOn;

    public Saab95(double pos_x, double pos_y) throws IOException {
        super(pos_x, pos_y);
        nrDoors = 2;
        color = Color.red;
        enginePower = 125;
        turboOn = false;
        modelName = "Saab95";
        stopEngine();
        image = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Saab95.jpg"));
    }

    public static void setTurboOn(){
        turboOn = true;
    }

    public static void setTurboOff(){
        turboOn = false;
    }

    @Override
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }

}