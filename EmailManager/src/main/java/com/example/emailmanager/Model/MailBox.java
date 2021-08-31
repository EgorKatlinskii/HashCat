package com.example.emailmanager.Model;


import com.example.emailmanager.Enums.BoxStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@Document(collection = "MailBox")
public class MailBox {

    @Id
    private ObjectId id;

    /*@NotNull(message = "Email is missing")
    @Email*/
    private String login;


 /*   @NotNull(message = "Hershey is missing")
    @Size(min = 1,message = "The minimum number of hashes is 1")
    private ArrayList<String> hashList;*/

}
