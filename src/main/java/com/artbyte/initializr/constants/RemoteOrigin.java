package com.artbyte.initializr.constants;

import lombok.Getter;

@Getter
public enum RemoteOrigin {
    SPRING_START("https://start.spring.io/starter.zip");
    private final String origin;
    RemoteOrigin(String origin){
        this.origin = origin;
    }
}
