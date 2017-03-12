package ro.webeet.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.webeet.model.MultiResponse;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

/**
 * Created by andrei on 11/03/2017.
 *
 * Unit test for the joke service
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JokeServiceTest {

    @Autowired
    private JokeService service;

    @Test
    public void getJoke() throws Exception {
        assertThat(service.getJoke("Andrei", "Chirinciuc"), anyOf(containsString("Andrei"),containsString("Chirinciuc")));
        assertThat(service.getJoke("John", "Doe"), anyOf(containsString("John"),containsString("Doe")));
    }

    @Test
    public void getJokes() throws Exception {
        MultiResponse jokes = service.getJokes("Andrei", "Chirinciuc",5);
        assertNotNull(jokes);
        assertEquals(5,jokes.getMessages().size());

        jokes = service.getJokes(null, "Chirinciuc",-5);
        assertNotNull(jokes);
        assertEquals(1,jokes.getMessages().size());
    }
}
