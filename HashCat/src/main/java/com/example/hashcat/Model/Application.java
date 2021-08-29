package com.example.hashcat.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@Document(collection = "Application")
public class Application {

    @Id
    private ObjectId id;

    @NotNull(message = "Email is missing")
    @Pattern(regexp = "([a-z])+@([a-z])+\\.ru", message = "Email should match the pattern a-z @ a-z .ru")
    private String email;

    @NotNull(message = "Hershey is missing")
    @Size(min = 1,message = "The minimum number of hashes is 1")
    private ArrayList<String> hashList;
}
