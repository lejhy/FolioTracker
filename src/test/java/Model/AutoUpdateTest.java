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
        autoUpdate = new AutoUpdate(this::setCounter, 100);
    }


    @Test
    void isRunning() {
        assertFalse(autoUpdate.isRunning());
        autoUpdate.start();
        assertTrue(autoUpdate.isRunning());
        autoUpdate.stop();
        assertFalse(autoUpdate.isRunning());
    }

    void setCounter() {
        counter = 1;
    }

}