package com.crocodile.test;

import com.crocodile.io.LoadSettings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LoadSettingsTest {
    LoadSettings loadSettings;
    @BeforeEach
    void setUp() {
        loadSettings = new LoadSettings("res/settings.txt");
    }

    @Test
    void getServerPort() {
        assertNotNull(loadSettings.getServerPort());
    }

    @Test
    void getServerIP() {
        assertNotNull(loadSettings.getServerIP());
    }

    @Test
    void loadSettingsFromFile() {
        try {
            loadSettings.loadSettingsFromFile();
        } catch (IOException e) {
            fail("File not found");
        }
    }

}