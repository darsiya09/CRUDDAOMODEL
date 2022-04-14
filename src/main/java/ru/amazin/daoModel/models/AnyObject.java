package ru.amazin.daoModel.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class AnyObject  implements MessageTemplate{
    private String first;
    private String second;
    private Map<Integer, String> users;
    private String[] parts;
}
