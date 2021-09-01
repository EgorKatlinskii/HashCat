package com.example.decodingservice.Model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CurrentApplication {

    private String email;

    private ArrayList<String> hashList;
}
