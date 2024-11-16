package com.artbyte.initializr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Packaging {
    private String defaultValue;
    private List<PackageValues> values;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PackageValues{
        private String id;
        private String name;
    }
}


