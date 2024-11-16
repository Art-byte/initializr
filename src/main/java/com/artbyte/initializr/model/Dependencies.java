package com.artbyte.initializr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dependencies {
    private List<DependenciesValues> values;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DependenciesValues{
        private String sectionName;
        private List<DependenciesContent> dependenciesContents;


        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class DependenciesContent{
            private String id;
            private String depName;
            private String description;
        }
    }
}

