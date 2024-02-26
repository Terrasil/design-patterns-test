package src.abstract_factory.factories;

import src.abstract_factory.products.Motor;
import src.abstract_factory.products.Vehicle;

public class MotorFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Motor();
    }
}
