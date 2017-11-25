package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutoUpdateTest {

    AutoUpdate autoUpdate;
    int counter;

    @BeforeEach
    void setUp() {
        counter = 0;
        autoUpdate = new AutoUpdate(this::setCounter, 500);
    }

    @Test
    void start() {
        assertTrue(autoUpdate.start());
        assertFalse(autoUpdate.start());
        autoUpdate.stop();
    }

    @Test
    void stop() {
        assertTrue(autoUpdate.start());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(autoUpdate.stop());
        assertFalse(autoUpdate.stop());
    }

    @Test
    void isRunning() {
        assertFalse(autoUpdate.isRunning());
        autoUpdate.start();
        assertTrue(autoUpdate.isRunning());
        autoUpdate.stop();
        assertFalse(autoUpdate.isRunning());
    }

    @Test
    void equalsNull() {
        assertFalse(autoUpdate.equals(null));
    }

    void setCounter() {
        counter = 1;
    }

}