package gr.hua.dit.ds.grp41.rentalmanagement.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Schema(description = "List for emails")
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private List<String> message;

    public List<String> getMessage(){
        return message;
    }

    public void setMessage(List<String> newMessage){
        this.message = newMessage;
    }
}
