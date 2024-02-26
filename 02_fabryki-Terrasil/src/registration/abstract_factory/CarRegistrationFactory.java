package src.registration.abstract_factory;

import src.abstract_factory.products.Car;
import src.abstract_factory.products.Helicopter;
import src.abstract_factory.products.Vehicle;

public class CarRegistrationFactory extends VehicleRegistrationFactory {
    public CarRegistrationFactory() {
        VehicleRegistrationFactory.registerVehicle("car", Car.class);
    }

    @Override
    public Vehicle createVehicle() throws Exception {
        Vehicle vehicle = null;
        try {
            vehicle = registeredComponents.get("car").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicle;
    }
}
