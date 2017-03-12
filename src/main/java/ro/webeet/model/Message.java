package ro.webeet.model;

/**
 * Created by andrei on 12/03/2017.
 *
 * Joke message model
 */
public class Message{

    private int id;
    private String joke;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}
