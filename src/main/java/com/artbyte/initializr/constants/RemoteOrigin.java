package com.artbyte.initializr.constants;

import lombok.Getter;

@Getter
public enum RemoteOrigin {
    SPRING_START("https://start.spring.io/starter.zip"),
    SPRING_CLIENT("https://start.spring.io/metadata/client");
    private final String origin;
    RemoteOrigin(String origin){
        this.origin = origin;
    }
}
