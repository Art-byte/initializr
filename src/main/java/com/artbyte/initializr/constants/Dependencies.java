package com.artbyte.initializr.constants;

import lombok.Getter;

@Getter
public enum Dependencies {
    WEB("web"),
    JPA("data-jpa"),
    LOMBOK("lombok"),
    MYSQL("mysql"),
    MONGO("data-mongo"),
    THYMELEAF("thymeleaf"),
    SECURITY("security"),
    DEVTOOLS("devtools");

    private final String value;
    Dependencies(String value){
        this.value = value;
    }
}
