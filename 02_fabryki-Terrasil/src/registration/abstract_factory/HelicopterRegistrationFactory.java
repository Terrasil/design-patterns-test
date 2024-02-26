package src.registration.abstract_factory;

import src.abstract_factory.products.Helicopter;
import src.abstract_factory.products.Vehicle;

public class HelicopterRegistrationFactory extends VehicleRegistrationFactory {

    public HelicopterRegistrationFactory() {
        VehicleRegistrationFactory.registerVehicle("helicopter", Helicopter.class);
    }

    @Override
    public Vehicle createVehicle() throws Exception {
        Vehicle vehicle = null;
        try {
            vehicle = registeredComponents.get("helicopter").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicle;
    }
}
