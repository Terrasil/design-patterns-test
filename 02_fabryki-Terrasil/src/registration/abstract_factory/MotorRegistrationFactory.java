package src.registration.abstract_factory;

import src.abstract_factory.products.Motor;
import src.abstract_factory.products.Vehicle;

public class MotorRegistrationFactory extends VehicleRegistrationFactory {

    public MotorRegistrationFactory() {
        VehicleRegistrationFactory.registerVehicle("motor", Motor.class);
    }

    @Override
    public Vehicle createVehicle() throws Exception {
        Vehicle vehicle = null;
        try {
            vehicle = registeredComponents.get("motor").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicle;
    }
}
