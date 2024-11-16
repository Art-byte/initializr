package com.artbyte.initializr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Language {
    private String defaultValue;
    private List<LangValues> values;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LangValues{
        private String id;
        private String name;
    }
}

