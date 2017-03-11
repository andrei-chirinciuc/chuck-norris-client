package ro.webeet.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.webeet.model.Joke;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by andrei on 11/03/2017.
 *
 * Client service for calling ICNDB services
 */
@Service
public class JokeService {
    private Logger logger = LoggerFactory.getLogger(JokeService.class);
    private RestTemplate restTemplate;

    @Value("${icndb.endpoint}")
    private String endpoint;

    @Value("#{'${icndb.categories:nerdy}'.split(',')}")
    private List<String> categories;

    public JokeService(RestTemplateBuilder builder){
        restTemplate = builder.build();
    }

    /**
     * Changing the name of the main character
     * The API permits changing the name of the main character when fetching a joke.
     */
    protected String getJoke(String firstName, String lastName){
        String url = String.format("%s[%s]&firstName=%s&lastName=%s", endpoint,
                categories.stream().collect(Collectors.joining(",")),
                firstName, lastName);
        Joke joke = restTemplate.getForObject(url,Joke.class);
        String jokeString = joke.getValue().getJoke();
        logger.info(url);
        logger.info(jokeString);
        return jokeString;
    }
}
