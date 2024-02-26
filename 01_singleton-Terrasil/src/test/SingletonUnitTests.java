package src.test;

import org.junit.jupiter.api.Test;
import src.*;

import java.io.*;
import java.util.Random;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SingletonUnitTests {
    private static final Random RNG = new Random();

    private static void sleep(int min, int max) {
        try {
            Thread.sleep(RNG.nextInt(max - min + 1) + min);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void returnSameSingleton_whenSerializedAndDeserialized()
            throws IOException, ClassNotFoundException {
        Singleton singleton = Singleton.getInstance();

        FileOutputStream fileOutputStream = new FileOutputStream("singleton_test.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        FileInputStream fileInputStream = new FileInputStream("singleton_test.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        // Serializing
        objectOutputStream.writeObject(singleton);

        // Deserializing
        Singleton singletonDeserialized = (Singleton) objectInputStream.readObject();

        assertEquals(singleton, singletonDeserialized);
    }

    @Test
    public void returnSameSingletonInstance_whenCompareFirstSingletonWithSingleton() {
        Singleton singleton = Singleton.getInstance();
        Singleton firstSingleton = FirstSingleton.getInstance();

        assertEquals(singleton, firstSingleton);
    }

    @Test
    public void returnDifferentSingletonInstance_whenCompareSecondSingletonWithSingleton() {
        Singleton singleton = Singleton.getInstance();
        Singleton secondSingleton = SecondSingleton.getInstance();

        assertNotEquals(singleton, secondSingleton);
    }

    @Test
    public void returnDifferentSingletonInstance_whenCompareFirstSingletonWithSecondSingleton() {
        Singleton firstSingleton = FirstSingleton.getInstance();
        Singleton secondSingleton = SecondSingleton.getInstance();

        assertNotEquals(firstSingleton, secondSingleton);
    }

    @Test
    public void returnSameSingletonPerThreadInstance_whenCompareSingletonPerThreadWithSingletonPerThread() {
        SingletonPerThread singleton1 = SingletonPerThread.getInstance();
        SingletonPerThread singleton2 = SingletonPerThread.getInstance();

        assertEquals(singleton1, singleton2);
    }

    @Test
    public void returnDifferentSingletonPerThreadInstance_whenCompareSingletonPerThreadWithSingletonPerThread()
            throws ExecutionException, InterruptedException {
        Callable<SingletonPerThread> result = () -> SingletonPerThread.getInstance();

        FutureTask<SingletonPerThread> task1 = new FutureTask<>(result);
        FutureTask<SingletonPerThread> task2 = new FutureTask<>(result);

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();

        SingletonPerThread singleton1 = task1.get();
        SingletonPerThread singleton2 = task2.get();

        assertNotEquals(singleton1, singleton2);
    }

    @Test
    public void returnSameSingletonInstance_whenCompareSecondSingletonWithSingletonFromDifferentThreads()
            throws ExecutionException, InterruptedException {
        Callable<Singleton> result = () -> Singleton.getInstance();

        FutureTask<Singleton> task1 = new FutureTask<>(result);
        FutureTask<Singleton> task2 = new FutureTask<>(result);

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();

        Singleton singleton1 = task1.get();
        Singleton singleton2 = task2.get();

        assertEquals(singleton1, singleton2);
    }

//    @Test
//    public void processTest() {
//        ExecutorService executor = Executors.newFixedThreadPool(2);
//        Callable<Singleton> callableTask = () -> Singleton.getInstance();
//
//        Future<Singleton> singletonProcess1 = executor.submit(callableTask);
//        Future<Singleton> singletonProcess2 = executor.submit(callableTask);
//
//        executor.shutdown();
//
//        assertNotEquals(singletonProcess1, singletonProcess2);
//    }
}
