package com.artbyte.initializr.constants;

import lombok.Getter;

@Getter
public enum Params {
    ID("id"),
    NAME("name"),
    VALUES("values"),
    DESCRIPTION("description"),
    DEPENDENCIES("dependencies"),
    BOOT_VERSION("bootVersion"),
    VERSION("version"),
    TYPE("type"),
    GROUP_ID("groupId"),
    PACKAGING("packaging"),
    PACKAGE_NAME("packageName"),
    ARTIFACT_ID("artifactId"),
    LANGUAGE("language"),
    JAVA_VERSION("javaVersion"),
    DEFAULT("default");

    private final String value;
    Params(String value){
        this.value = value;
    }
}
