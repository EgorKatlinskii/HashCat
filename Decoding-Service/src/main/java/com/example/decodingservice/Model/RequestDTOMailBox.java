package com.example.decodingservice.Model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class RequestDTOMailBox {

    private ObjectId id;

    private String login;

    public RequestDTOMailBox(ObjectId objectId, String email) {
        this.login = email;
    }
}

