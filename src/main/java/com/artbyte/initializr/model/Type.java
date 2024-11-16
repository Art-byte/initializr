package com.artbyte.initializr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {
    private String defaultValue;
    private List<TypeValues> values;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TypeValues{
        private String id;
        private String name;
        private String description;
    }
}

