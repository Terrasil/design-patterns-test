package src.registration.abstract_factory;

import src.abstract_factory.products.Plane;
import src.abstract_factory.products.Vehicle;

public class PlaneRegistrationFactory extends VehicleRegistrationFactory {
    public PlaneRegistrationFactory() {
        VehicleRegistrationFactory.registerVehicle("plane", Plane.class);
    }

    @Override
    public Vehicle createVehicle() throws Exception {
        Vehicle vehicle = null;
        try {
            vehicle = registeredComponents.get("plane").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicle;
    }
}
