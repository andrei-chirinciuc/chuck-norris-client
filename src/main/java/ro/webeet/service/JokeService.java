package ro.webeet.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.webeet.model.Message;
import ro.webeet.model.MultiResponse;
import ro.webeet.model.SingleResponse;
import ro.webeet.util.UrlBuilder;

import java.net.MalformedURLException;
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
     *
     * param firstName
     * param lastName
     *
     * return String of the joke message
     */
    protected String getJoke(String firstName, String lastName){
        String url = String.format("%s?limitTo=[%s]&firstName=%s&lastName=%s", endpoint,
                categories.stream().collect(Collectors.joining(",")),
                firstName, lastName);
        SingleResponse singleResponse = restTemplate.getForObject(url,SingleResponse.class);
        String jokeString = singleResponse.getMessage().getJoke();
        logger.info(url);
        logger.info(jokeString);
        return jokeString;
    }
    /**
     * Changing the name of the main character
     * The API permits changing the name of the main character when fetching a specific number of jokes.
     *
     * param firstName
     * param lastName
     * param numberOfJokes minimum value is 1
     * throws {@link MalformedURLException} in case endpoint is not specified
     *
     * return MultiResponse
     */
    protected MultiResponse getJokes(String firstName, String lastName, int numberOfJokes) throws MalformedURLException {

        String url = new UrlBuilder()
                .endpoint(endpoint)
                .firstName(firstName)
                .lastName(lastName)
                .categories(categories)
                .count(numberOfJokes).build();

        logger.info(url);
        ResponseEntity<MultiResponse> jokeResponse = restTemplate.getForEntity(url,
                MultiResponse.class);
        for(Message message : jokeResponse.getBody().getMessages()){
            logger.info(message.getJoke());
        }
        return jokeResponse.getBody();
    }
}
