package ro.webeet.util;

import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by andrei on 12/03/2017.
 *
 * Joke service url builder
 */
public class UrlBuilder {
    private StringBuilder url;
    private String endpoint;
    private String firstName;
    private String lastName;
    private List<String> categories;
    private int count;

    public UrlBuilder() {
        url=new StringBuilder();
    }

    public UrlBuilder endpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public UrlBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UrlBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UrlBuilder categories(List<String> categories) {
        this.categories = categories;
        return this;
    }

    public UrlBuilder count(int count) {
        this.count = count;
        return this;
    }

    public String build() throws MalformedURLException{
        if(endpoint==null){
            throw new MalformedURLException("Endpoint was not specified");
        }
        url.append(endpoint);
        url.append("/").append(count>0?count:1);
        url.append(String.format("?limitTo=[%s]",categories.stream().collect(Collectors.joining(","))));
        if(firstName!=null) {
            url.append("&firstName=").append(firstName);
        }
        if(lastName!=null) {
            url.append("&lastName=").append(lastName);
        }
        return url.toString();
    }
}
