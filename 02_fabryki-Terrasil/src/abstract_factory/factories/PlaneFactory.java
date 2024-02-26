package src.abstract_factory.factories;

import src.abstract_factory.products.Plane;
import src.abstract_factory.products.Vehicle;

public class PlaneFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Plane();
    }
}
