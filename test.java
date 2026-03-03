import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class test {
    private Volvo240 volvo = new Volvo240();
    private Saab95 saab = new Saab95();
    private Scania scania = new Scania();
    private Ferrari ferrari = new Ferrari();
    private Workshop<Volvo240> volvoWorkshop = new Workshop<Volvo240>(10);

    @Test
    public void test_volvo_move_without_gas() {
        volvo.move();
        assertEquals(0.0, volvo.getPos_x(), 0.0001);
        assertEquals(0.0, volvo.getPos_y(), 0.0001);
    }
    @Test
    public void test_saab_move_without_gas() {
        saab.move();
        assertEquals(0.0, saab.getPos_x(), 0.0001);
        assertEquals(0.0, saab.getPos_y(), 0.0001);
    }
    @Test
    public void test_volvo_gas_with_trimfactor() {
        volvo.gas(0.3);
        assertEquals(0.375, volvo.getCurrentSpeed(), 0.0001);
    }
    @Test
    public void test_saab_gas_without_turbo() {
        saab.setTurboOff();
        saab.gas(0.4);
        double currentSpeed = saab.getCurrentSpeed();
        assertEquals(0.5, saab.getCurrentSpeed(), 0.0001);
    }
    @Test
    public void test_saab_gas_with_turbo(){
        saab.setTurboOn();
        saab.gas(0.4);
        assertEquals(0.65, saab.getCurrentSpeed(), 0.0001);
    }
    @Test
    public void test_brake_volvo(){
        volvo.gas(0.3);
        volvo.brake(0.1);
        assertEquals(0.25, volvo.getCurrentSpeed(), 0.0001);
    }
    @Test
    public void test_brake_saab(){
        saab.gas(0.4);
        saab.brake(0.1);
        assertEquals(0.375, saab.getCurrentSpeed(), 0.0001);
    }
    @Test
    public void test_cannot_gas_more_than_1(){
        volvo.gas(1.1);
        assertEquals(0.0, volvo.getCurrentSpeed(), 0.0001);
    }
    @Test
    public void test_cannot_brake_less_than_0(){
        saab.brake(-0.5);
        assertEquals(0.0, saab.getCurrentSpeed(), 0.0001);
    }
    @Test
    public void test_turn_left(){
        volvo.gas(0.4);
        volvo.turnLeft();
        assertEquals(1.0, volvo.dx, 0.0001);
        assertEquals(0.0, volvo.dy, 0.0001);
    }
    @Test
    public void test_move_with_gas_volvo(){
        volvo.gas(0.5);
        volvo.move();
        assertEquals(0.0, volvo.pos_x, 0.0001);
        assertNotEquals(0.0, volvo.pos_y, 0.0001);
    }
    @Test
    public void test_nrDoors_volvo(){
        volvo.getNrDoors();
        assertEquals(4, volvo.getNrDoors(), 0.0001);

    }
    @Test
    public void test_nrDoors_saab(){
        saab.getNrDoors();
        assertEquals(2, saab.getNrDoors(), 0.0001);
    }
    @Test
    public void test_color_volvo(){
        volvo.getColor();
        assertEquals(Color.black, volvo.getColor());
    }
    @Test
    public void test_color_saab(){
        saab.getColor();
        assertEquals(Color.red, saab.getColor());
    }
    //Tester för Scania
    @Test
    public void test_raise_ramp(){
      scania.raiseRamp();
      assertEquals(10, scania.ramp.getAngle(), 0.001);
    }

    @Test
    public void test_lower_ramp(){
        scania.raiseRamp();
        scania.raiseRamp();
        scania.lowerRamp();
        assertEquals(10, scania.ramp.getAngle(), 0.0001);
    }

    //Tester för Truck
    @Test
    public void test_load_car(){
        ferrari.raiseRamp();
        ferrari.loadCar(volvo);
        assertArrayEquals(volvo, ferrari.getCarList());
    }

    @Test
    public void test_load_many_cars(){
        ferrari.raiseRamp();
        ferrari.loadCar(volvo);
        ferrari.loadCar(saab);
        ferrari.loadCar(saab);
        ferrari.loadCar(volvo);
        ferrari.loadCar(saab);
        assertArrayEquals(volvo, ferrari.getCarList());
        System.out.println(ferrari.getCarList());
    }

    @Test
    public void test_unload_last_car(){
        ferrari.raiseRamp();
        ferrari.loadCar(volvo);
        ferrari.loadCar(saab);
        ferrari.loadCar(saab);
        ferrari.loadCar(volvo);
        ferrari.loadCar(saab);
        assertArrayEquals(saab, ferrari.getCarList());
        ferrari.unloadCar();
        assertArrayEquals(saab, ferrari.getCarList());
    }

    @Test
    public void test_position_of_loaded_car(){
        Double fposx = ferrari.getPos_x();
        Double fposy = ferrari.getPos_y();
        Double vposx = volvo.getPos_x();
        Double vposy = volvo.getPos_y();
        ferrari.raiseRamp();
        ferrari.loadCar(volvo);
        assertEquals(fposx, vposx);
        assertEquals(fposy, vposy);
    }
    private void assertArrayEquals(Car volvo, ArrayList carList) {
    }

    @Test
    public void test_get_info_of_car_from_Workshop(){
        Car volvo1 = volvo;
        volvoWorkshop.loadCar(volvo);
        volvoWorkshop.unloadCar(volvo);
        assertEquals(volvo1, volvo);

    }
}
