import java.util.ArrayList;

public class Workshop<T extends IworkshopCar> {

    private final int maxLoads;
    private ArrayList<T> carList;

    public Workshop(int maxLoads){
        this.maxLoads = maxLoads;
        this.carList = new ArrayList<>();
    }

    public void loadCar(T car) {
        if (carList.size() < maxLoads) {

            carList.add(car);
        }
    }

    public T unloadCar(T car) {
        if (!(carList.isEmpty())){
            car = carList.remove(carList.size() - 1);


        }
        return car;
    }
}
