package com.artbyte.initializr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildData {
    private Dependencies dependencies;
    private Type type;
    private Packaging packaging;
    private JavaVersion javaVersion;
    private Language language;
    private BootVersion bootVersion;
    private String groupId;
    private String artifactId;
    private String version;
    private String name;
    private String description;
    private String packageName;
}
