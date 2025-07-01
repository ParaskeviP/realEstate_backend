package gr.hua.dit.ds.grp41.rentalmanagement.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
@Schema(description = "List for emails")
public class Mail {


    private List<String> message;

    public List<String> getMessage(){
        return message;
    }

    public void setMessage(List<String> newMessage){
        this.message = newMessage;
    }
}
