package src.registration.abstract_factory;

import src.Product;
import src.abstract_factory.products.Vehicle;

import java.util.HashMap;
import java.util.Map;

public abstract class VehicleRegistrationFactory {
    public abstract Vehicle createVehicle() throws Exception;

    public Vehicle makeVehicle() {
        try {
            Vehicle vehicle = createVehicle();
            vehicle.make();
            return vehicle;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    protected static final Map<String, Class<? extends Vehicle>> registeredComponents = new HashMap<>();

    public static void registerVehicle(String name, Class<? extends Vehicle> clazz) {
        registeredComponents.put(name, clazz);
    }

    public static void registerVehicle(String name, String className) throws ClassNotFoundException {
        Class<? extends Vehicle> clazz = Class.forName(className).asSubclass(Vehicle.class);
        registeredComponents.put(name, clazz);
    }

    public static void registerVehicle(String name, Vehicle vehicle) {
        registeredComponents.put(name, vehicle.getClass());
    }
}
