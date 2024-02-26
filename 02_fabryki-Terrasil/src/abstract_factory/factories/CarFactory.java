package src.abstract_factory.factories;

import src.abstract_factory.products.Car;
import src.abstract_factory.products.Vehicle;

public class CarFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Car();
    }
}
