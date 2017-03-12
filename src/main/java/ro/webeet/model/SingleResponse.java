package ro.webeet.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by andrei on 11/03/2017.
 *
 * SingleResponse model for the icndb service
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleResponse {

    private String type;
    @JsonProperty(value = "value")
    private Message message;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
