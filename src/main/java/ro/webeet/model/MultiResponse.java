package ro.webeet.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by andrei on 11/03/2017.
 *
 * SingleResponse model for the icndb service
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MultiResponse {

    private String type;
    @JsonProperty(value = "value")
    private List<Message> message;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Message> getMessages() {
        return message;
    }

    public void setMessages(List<Message> messages) {
        this.message = messages;
    }
}
