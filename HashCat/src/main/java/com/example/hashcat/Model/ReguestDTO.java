package com.example.hashcat.Model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;



@Data
@Document(collection = "Application")
public class ReguestDTO {

    @NotNull(message = "Email is missing")
    @Email
    private String email;

    @NotNull(message = "Hershey is missing")
    @Size(min = 1,message = "The minimum number of hashes is 1")
    private ArrayList<String> hashList;
}
