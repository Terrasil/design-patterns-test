package registration;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import src.Product;
import src.abstract_factory.factories.*;
import src.abstract_factory.products.Vehicle;
import src.creation_method.products.Clothes;
import src.registration.abstract_factory.CarRegistrationFactory;
import src.registration.abstract_factory.HelicopterRegistrationFactory;
import src.registration.abstract_factory.PlaneRegistrationFactory;
import src.registration.abstract_factory.VehicleRegistrationFactory;
import src.registration.creation_method.AllClothesRegistrationFactory;
import src.registration.creation_method.FabricClothesRegistrationFactory;
import src.registration.creation_method.LeatherClothesRegistrationFactory;
import src.registration.creation_method.RegistrationFactory;
import src.registration.simple_method.SimpleRegistrationFactory;
import src.simple_factory.factories.PeripheralsFactory;
import src.simple_factory.factories.SimpleFactory;
import src.simple_factory.products.Keyboard;
import src.simple_factory.products.Mouse;
import src.simple_factory.products.Peripherals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest {

  @Nested
  class AbstractFactoryRegistration {

    private static final int NUM_THREADS = 1000;

    private void shouldCreateProductsReTest() {
      List<VehicleFactory> factories = new ArrayList<>();

      factories.add(new CarFactory());
      factories.add(new HelicopterFactory());
      factories.add(new MotorFactory());
      factories.add(new PlaneFactory());

      List<Product> components = new ArrayList<>();

      for(VehicleFactory factory : factories) {
        components.add(factory.makeVehicle());
      }

      for(Product component : components) {
        assertTrue(component instanceof Product);
      }
    }

    @Test
    public void shouldCreateVehiclesByRegistrationFactory() {
      List<VehicleRegistrationFactory> factories = new ArrayList<>();

      factories.add(new CarRegistrationFactory());
      factories.add(new HelicopterRegistrationFactory());
      factories.add(new PlaneRegistrationFactory());

      List<Vehicle> vehicles = new ArrayList<>();

      for (VehicleRegistrationFactory factory : factories) {
        vehicles.add(factory.makeVehicle());
      }

      for (Vehicle vehicle : vehicles) {
        assertTrue(vehicle instanceof Vehicle);
      }
    }

    @Test
    public void testFactoryOnMultipleThreads() throws InterruptedException {
      Thread[] threads = new Thread[NUM_THREADS];
      final long[] timesWithRegistration = new long[NUM_THREADS];
      final long[] timesWithoutRegistration = new long[NUM_THREADS];

      for (int i = 0; i < NUM_THREADS; i++) {
        final int innerI = i;
        threads[i] = new Thread(() -> {
          Long startTime = System.nanoTime();
          shouldCreateVehiclesByRegistrationFactory();
          timesWithRegistration[innerI] = System.nanoTime() - startTime;
          startTime = System.nanoTime();
          shouldCreateProductsReTest();
          timesWithoutRegistration[innerI] = System.nanoTime() - startTime;
        });
      }

      for (int i = 0; i < NUM_THREADS; i++) {
        threads[i].start();
      }

      for (int i = 0; i < NUM_THREADS; i++) {
        threads[i].join();
      }

      System.out.println("Average time with registration: "
          + Arrays.stream(timesWithRegistration).average().getAsDouble() / 1000000 + " ms");
      System.out.println(
          "Min time with registration: " + Arrays.stream(timesWithRegistration).min().getAsLong() / 1000000 + " ms");
      System.out.println(
          "Max time with registration: " + Arrays.stream(timesWithRegistration).max().getAsLong() / 1000000 + " ms");
      System.out.println("Average time without registration: "
          + Arrays.stream(timesWithoutRegistration).average().getAsDouble() / 1000000 + " ms");
      System.out.println("Min time without registration: "
          + Arrays.stream(timesWithoutRegistration).min().getAsLong() / 1000000 + " ms");
      System.out.println("Max time without registration: "
          + Arrays.stream(timesWithoutRegistration).max().getAsLong() / 1000000 + " ms");
    }
  }

  @Nested
  class CreationMethodRegistration {
    @Test
    public void shouldCreateClothesByAllClothesRegistrationFactory() {
      AllClothesRegistrationFactory factory = new AllClothesRegistrationFactory();

      List<Clothes> clothesList = new ArrayList<>();

      clothesList.add(factory.makeClothes("leather", "jacket"));
      clothesList.add(factory.makeClothes("leather", "pants"));
      clothesList.add(factory.makeClothes("leather", "boots"));
      clothesList.add(factory.makeClothes("fabric", "jacket"));
      clothesList.add(factory.makeClothes("fabric", "pants"));
      clothesList.add(factory.makeClothes("fabric", "boots"));

      for (Clothes clothes : clothesList) {
        assertTrue(clothes instanceof Clothes);
      }
    }

    @Test
    public void shouldCreateClothesBySpecifyRegistrationFactory() {
      List<RegistrationFactory> factories = new ArrayList<>();

      factories.add(new FabricClothesRegistrationFactory());
      factories.add(new LeatherClothesRegistrationFactory());

      List<Clothes> clothesList = new ArrayList<>();

      for (RegistrationFactory factory : factories) {
        try {
          clothesList.add(factory.makeClothes("jacket"));
          clothesList.add(factory.makeClothes("pants"));
          clothesList.add(factory.makeClothes("boots"));
        } catch (Exception e) {
          System.out.println("Error: " + e.getMessage());
        }
      }

      for (Clothes clothes : clothesList) {
        assertTrue(clothes instanceof Clothes);
      }
    }
  }

  @Nested
  class SimpleFactoryRegistration {

    private static final int NUM_THREADS = 1000;

    private void shouldPeripheralsByFactoryWithSingletonFactoryUsedInsideReTest() {
      PeripheralsFactory factory = new PeripheralsFactory(SimpleFactory.getInstance());

      List<Peripherals> components = new ArrayList<>();

      try {
        components.add(factory.createPeripherals("keyboard"));
        components.add(factory.createPeripherals("mouse"));
        components.add(factory.createPeripherals("monitor"));
        components.add(factory.createPeripherals("pad"));
      } catch (Exception e) {
        System.out.println("Error: " + e);
      }

      for (Peripherals component : components) {
        assertTrue(component instanceof Peripherals);
      }
    }

    @Test
    public void shouldCreatePeripheralsBySimpleRegistrationFactory() {
      SimpleRegistrationFactory factory = SimpleRegistrationFactory.getInstance();

      List<Peripherals> peripherals = new ArrayList<>();

      try {
        factory.registerPeripherals("mouse", Mouse.class);
        factory.registerPeripherals("monitor", "src.simple_factory.products.Monitor");
        Peripherals keyboard = new Keyboard();
        factory.registerPeripherals("keyboard", keyboard);
        peripherals.add(factory.createPeripherals("mouse"));
        peripherals.add(factory.createPeripherals("monitor"));
        peripherals.add(factory.createPeripherals("keyboard"));
      } catch (Exception e) {
        e.printStackTrace();
      }

      for (Peripherals peripheral : peripherals) {
        assertTrue(peripheral instanceof Peripherals);
      }
    }

    @Test
    public void shouldCreatePeripheralsBySimpleRegistrationFactoryOnMultipleThreads() throws InterruptedException {
      Thread[] threads = new Thread[NUM_THREADS];
      final long[] timesWithRegistration = new long[NUM_THREADS];
      final long[] timesWithoutRegistration = new long[NUM_THREADS];

      for (int i = 0; i < NUM_THREADS; i++) {
        final int innerI = i;
        threads[i] = new Thread(() -> {
          Long startTime = System.nanoTime();
          timesWithRegistration[innerI] = System.nanoTime() - startTime;
          startTime = System.nanoTime();
          shouldPeripheralsByFactoryWithSingletonFactoryUsedInsideReTest();
          timesWithoutRegistration[innerI] = System.nanoTime() - startTime;
        });
      }

      for (int i = 0; i < NUM_THREADS; i++) {
        threads[i].start();
      }

      for (int i = 0; i < NUM_THREADS; i++) {
        threads[i].join();
      }

      System.out.println("Average time with registration: "
              + Arrays.stream(timesWithRegistration).average().getAsDouble() / 1000000 + " ms");
      System.out.println(
              "Min time with registration: " + Arrays.stream(timesWithRegistration).min().getAsLong() / 1000000 + " ms");
      System.out.println(
              "Max time with registration: " + Arrays.stream(timesWithRegistration).max().getAsLong() / 1000000 + " ms");
      System.out.println("Average time without registration: "
              + Arrays.stream(timesWithoutRegistration).average().getAsDouble() / 1000000 + " ms");
      System.out.println("Min time without registration: "
              + Arrays.stream(timesWithoutRegistration).min().getAsLong() / 1000000 + " ms");
      System.out.println("Max time without registration: "
              + Arrays.stream(timesWithoutRegistration).max().getAsLong() / 1000000 + " ms");
    }
  }
}
