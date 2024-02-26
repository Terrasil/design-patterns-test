package src.abstract_factory.factories;

import src.abstract_factory.products.Vehicle;

public abstract class VehicleFactory {
    public abstract Vehicle createVehicle();

    public Vehicle makeVehicle() {
        Vehicle component = createVehicle();
        component.make();
        return component;
    }
}
