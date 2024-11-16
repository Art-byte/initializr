package com.artbyte.initializr.mapper;


import com.artbyte.initializr.model.*;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class BuildDataMapper {

    public static BuildData finalObject(JsonNode rootNode){

        BuildData data = new BuildData();
        data.setDependencies(buildDependencies(rootNode.path("dependencies").path("values")));

        data.setType(buildType(rootNode.path("type")));
        data.setPackaging(buildPackaging(rootNode.path("packaging")));
        data.setJavaVersion(buildJavaVersion(rootNode.path("javaVersion")));
        data.setLanguage(buildLanguage(rootNode.path("language")));

        data.setBootVersion(buildBootVersion(
                rootNode.path("bootVersion").path("default").asText(),
                rootNode.path("bootVersion").path("values")));

        data.setGroupId(buildGroupId(rootNode.path("groupId")));
        data.setArtifactId(buildArtifactId(rootNode.path("artifactId")));
        data.setVersion(buildVersion(rootNode.path("version")));
        data.setName(buildName(rootNode.path("name")));
        data.setDescription(buildDescription(rootNode.path("description")));
        data.setPackageName(buildPackageName(rootNode.path("packageName")));
        return data;
    }

    private static Dependencies buildDependencies(JsonNode rootNode){
        List<Dependencies.DependenciesValues> dependenciesValues = new ArrayList<>();
        for(JsonNode sectionValues: rootNode){
            Dependencies.DependenciesValues dependenciesValuesObj = new Dependencies.DependenciesValues();
            dependenciesValuesObj.setSectionName(sectionValues.path("name").asText());

            List<Dependencies.DependenciesValues.DependenciesContent> dependenciesContents = new ArrayList<>();
            for(JsonNode contents: sectionValues.path("values")){
                Dependencies.DependenciesValues.DependenciesContent dependenciesContentObj = new Dependencies.DependenciesValues.DependenciesContent();
                dependenciesContentObj.setId(contents.path("id").asText());
                dependenciesContentObj.setDepName(contents.path("name").asText());
                dependenciesContentObj.setDescription(contents.path("description").asText());
                dependenciesContents.add(dependenciesContentObj);
            }
            dependenciesValuesObj.setDependenciesContents(dependenciesContents);
            dependenciesValues.add(dependenciesValuesObj);
        }

        Dependencies dependencies = new Dependencies();
        dependencies.setValues(dependenciesValues);
        return dependencies;
    }

    private static Type buildType(JsonNode rootNode){
        List<Type.TypeValues> values = new ArrayList<>();
        for(JsonNode typeI: rootNode.path("values")){
            Type.TypeValues typeValues = new Type.TypeValues();
            typeValues.setId(typeI.path("id").asText());
            typeValues.setName(typeI.path("name").asText());
            typeValues.setDescription(typeI.path("description").asText());
            values.add(typeValues);
        }
        Type type = new Type();
        type.setDefaultValue(rootNode.path("default").asText());
        type.setValues(values);
        return type;
    }

    private static Packaging buildPackaging(JsonNode rootNode){
        List<Packaging.PackageValues> values = new ArrayList<>();
        for(JsonNode pkg: rootNode.path("values")){
            Packaging.PackageValues pkgValues = new Packaging.PackageValues();
            pkgValues.setId(pkg.path("id").asText());
            pkgValues.setName(pkg.path("name").asText());
            values.add(pkgValues);
        }
        Packaging packaging = new Packaging();
        packaging.setDefaultValue(rootNode.path("default").asText());
        packaging.setValues(values);
        return packaging;
    }

    private static JavaVersion buildJavaVersion(JsonNode rootNode){
        List<JavaVersion.JavaValues> values = new ArrayList<>();
        for(JsonNode jv: rootNode.path("values")){
            JavaVersion.JavaValues javaValues = new JavaVersion.JavaValues();
            javaValues.setId(jv.path("id").asText());
            javaValues.setName(jv.path("name").asText());
            values.add(javaValues);
        }
        JavaVersion javaVersion = new JavaVersion();
        javaVersion.setDefaultValue(rootNode.path("default").asText());
        javaVersion.setValues(values);
        return javaVersion;
    }

    private static Language buildLanguage(JsonNode rootNode){
        List<Language.LangValues> langValuesList = new ArrayList<>();
        for(JsonNode values: rootNode.path("values")){
            Language.LangValues lang = new Language.LangValues();
            lang.setId(values.path("id").asText());
            lang.setName(values.path("name").asText());
            langValuesList.add(lang);
        }
        Language language = new Language();
        language.setDefaultValue(rootNode.path("default").asText());
        language.setValues(langValuesList);
        return language;
    }

    private static BootVersion buildBootVersion(String defaultVersion, JsonNode rootNode){
        List<BootVersion.BootValues> valuesList = new ArrayList<>();
        for(JsonNode values: rootNode){
            BootVersion.BootValues version = new BootVersion.BootValues();
            version.setId(values.path("id").asText());
            version.setName(values.path("name").asText());
            valuesList.add(version);
        }
        BootVersion bootVersion = new BootVersion();
        bootVersion.setDefaultValue(defaultVersion);
        bootVersion.setValues(valuesList);
        return bootVersion;
    }

    private static String buildGroupId(JsonNode rootNode){
        return rootNode.path("default").asText();
    }

    private static String buildArtifactId(JsonNode rootNode){
        return rootNode.path("default").asText();
    }

    private static String buildVersion(JsonNode rootNode){
        return rootNode.path("default").asText();
    }

    private static String buildName(JsonNode rootNode){
        return rootNode.path("default").asText();
    }

    private static String buildDescription(JsonNode rootNode){
        return rootNode.path("default").asText();
    }

    private static String buildPackageName(JsonNode rootNode){
        return rootNode.path("default").asText();
    }

}
