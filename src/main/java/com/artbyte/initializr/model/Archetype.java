package com.artbyte.initializr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Archetype {

    private String dependencies;
    private String type;
    private String language;
    private String bootVersion;
    private String groupId;
    private String artifactId;
    private String name;
    private String description;
    private String javaVersion;
    private String packaging;
    private String packageName;
}
