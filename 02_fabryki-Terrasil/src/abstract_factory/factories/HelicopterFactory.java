package src.abstract_factory.factories;

import src.abstract_factory.products.Helicopter;
import src.abstract_factory.products.Vehicle;

public class HelicopterFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Helicopter();
    }
}
