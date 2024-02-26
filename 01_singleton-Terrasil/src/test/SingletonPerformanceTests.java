package src.test;

import org.junit.jupiter.api.Test;
import src.Singleton;
import src.SlowSingleton;

//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class SingletonPerformanceTests {

    private final static Integer MAX_TRIES = Integer.MAX_VALUE / 10;

//    @Test
//    public void performanceTest() {
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        Callable<Long> callableTask = () -> {
//            Long startTime = System.nanoTime();
//            for(Integer i : IntStream.rangeClosed(0, Integer.MAX_VALUE).toArray()){
//                 // ...
//            }
//            Long estimatedTime = System.nanoTime() - startTime;
//            return estimatedTime;
//        };
//
//        Future<Long> testTime = executor.submit(callableTask);
//
//        executor.shutdown();
//
//        assertNotNull(testTime);
//    }

    @Test
    public void performanceTest1() {
        List<SlowSingleton> slowSingletonArray = new ArrayList<>();
        Long startTime = System.nanoTime();
        for(Integer i : IntStream.rangeClosed(0, MAX_TRIES).toArray()){
            slowSingletonArray.add(SlowSingleton.getInstance());
        }
        Long estimatedTime = System.nanoTime() - startTime;

        assertNotNull(estimatedTime);
        System.out.println("performanceTest1: on SlowSingleton - time: " + String.format("%,d", estimatedTime));
        assertEquals(Arrays.stream(slowSingletonArray.toArray()).collect(Collectors.toSet()).size(), 1);
    }

    @Test
    public void performanceTest2() {
        List<Singleton> singletonArray = new ArrayList<>();
        Long startTime = System.nanoTime();
        for(Integer i : IntStream.rangeClosed(0, MAX_TRIES).toArray()){
            singletonArray.add(Singleton.getInstance());
        }
        Long estimatedTime = System.nanoTime() - startTime;

        assertNotNull(estimatedTime);
        System.out.println("performanceTest2: on Singleton - time: " + String.format("%,d", estimatedTime));
        assertEquals(Arrays.stream(singletonArray.toArray()).collect(Collectors.toSet()).size(), 1);
    }
}
