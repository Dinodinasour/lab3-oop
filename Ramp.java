public class Ramp {

    private int angle;
    private final int maxAngle;
    private final int minAngle;

    public Ramp(int max, int min){
        angle = 0;
        maxAngle = max;
        minAngle = min;
    }
    public void setAngle(int newAngle){
        if (newAngle > maxAngle){
            setMaxAngle();
        } else if (newAngle < minAngle) {
            setMinAngle();
        } else
            angle = newAngle;
    }

    public int getAngle() {
        return angle;
    }

    public void setMaxAngle(){
        angle = maxAngle;
    }
    public void setMinAngle(){
        angle = minAngle;
    }
}
