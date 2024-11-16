package com.artbyte.initializr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JavaVersion {
    private String defaultValue;
    private List<JavaValues> values;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JavaValues{
        private String id;
        private String name;
    }

}

