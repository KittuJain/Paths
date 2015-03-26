package com.paths;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CitiesCountryManagerTest {
    CitiesCountryManager cm;

    @Before
    public void setUp() throws Exception {
        cm = new CitiesCountryManager("./Data/cities.txt");
    }

    @Test
    public void testGetCountryGetsCountryForTheGivenCity() {
        String city = "Bangalore";
        assertEquals("India", cm.getCountry(city));
    }
}