package com.artbyte.initializr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BootVersion {
    private String defaultValue;
    private List<BootValues> values;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BootValues {
        private String id;
        private String name;
    }

}

