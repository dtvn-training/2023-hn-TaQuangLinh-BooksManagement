package dev.dactech.booksmanagement.infrastructure.excel;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Header {
    private Map<Integer, String> title;
    public Header(){
        title = new HashMap<>();
    }
}
