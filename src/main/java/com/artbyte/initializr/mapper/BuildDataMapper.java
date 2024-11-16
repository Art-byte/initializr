package com.artbyte.initializr.mapper;


import com.artbyte.initializr.constants.Params;
import com.artbyte.initializr.model.*;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class BuildDataMapper {

    public static BuildData finalObject(JsonNode rootNode){

        BuildData data = new BuildData();
        data.setDependencies(buildDependencies(
                rootNode.path(Params.DEPENDENCIES.getValue())
                        .path(Params.VALUES.getValue())
        ));

        data.setType(buildType(rootNode.path(Params.TYPE.getValue())));
        data.setPackaging(buildPackaging(rootNode.path(Params.PACKAGING.getValue())));
        data.setJavaVersion(buildJavaVersion(rootNode.path(Params.JAVA_VERSION.getValue())));
        data.setLanguage(buildLanguage(rootNode.path(Params.LANGUAGE.getValue())));

        data.setBootVersion(buildBootVersion(
                rootNode.path(Params.BOOT_VERSION.getValue())
                        .path(Params.DEFAULT.getValue()).asText(),

                rootNode.path(Params.BOOT_VERSION.getValue())
                        .path(Params.VALUES.getValue())
        ));

        data.setGroupId(buildGroupId(rootNode.path(Params.GROUP_ID.getValue())));
        data.setArtifactId(buildArtifactId(rootNode.path(Params.ARTIFACT_ID.getValue())));
        data.setVersion(buildVersion(rootNode.path(Params.VERSION.getValue())));
        data.setName(buildName(rootNode.path(Params.NAME.getValue())));
        data.setDescription(buildDescription(rootNode.path(Params.DESCRIPTION.getValue())));
        data.setPackageName(buildPackageName(rootNode.path(Params.PACKAGE_NAME.getValue())));
        return data;
    }

    private static Dependencies buildDependencies(JsonNode rootNode){
        List<Dependencies.DependenciesValues> dependenciesValues = new ArrayList<>();
        for(JsonNode sectionValues: rootNode){
            Dependencies.DependenciesValues dependenciesValuesObj = new Dependencies.DependenciesValues();
            dependenciesValuesObj.setSectionName(sectionValues.path(Params.NAME.getValue()).asText());

            List<Dependencies.DependenciesValues.DependenciesContent> dependenciesContents = new ArrayList<>();
            for(JsonNode contents: sectionValues.path(Params.VALUES.getValue())){
                Dependencies.DependenciesValues.DependenciesContent dependenciesContentObj = new Dependencies.DependenciesValues.DependenciesContent();
                dependenciesContentObj.setId(contents.path(Params.ID.getValue()).asText());
                dependenciesContentObj.setDepName(contents.path(Params.NAME.getValue()).asText());
                dependenciesContentObj.setDescription(contents.path(Params.DESCRIPTION.getValue()).asText());
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
        for(JsonNode typeI: rootNode.path(Params.VALUES.getValue())){
            Type.TypeValues typeValues = new Type.TypeValues();
            typeValues.setId(typeI.path(Params.ID.getValue()).asText());
            typeValues.setName(typeI.path(Params.NAME.getValue()).asText());
            typeValues.setDescription(typeI.path(Params.DESCRIPTION.getValue()).asText());
            values.add(typeValues);
        }
        Type type = new Type();
        type.setDefaultValue(rootNode.path(Params.DEFAULT.getValue()).asText());
        type.setValues(values);
        return type;
    }

    private static Packaging buildPackaging(JsonNode rootNode){
        List<Packaging.PackageValues> values = new ArrayList<>();
        for(JsonNode pkg: rootNode.path(Params.VALUES.getValue())){
            Packaging.PackageValues pkgValues = new Packaging.PackageValues();
            pkgValues.setId(pkg.path(Params.ID.getValue()).asText());
            pkgValues.setName(pkg.path(Params.NAME.getValue()).asText());
            values.add(pkgValues);
        }
        Packaging packaging = new Packaging();
        packaging.setDefaultValue(rootNode.path(Params.DEFAULT.getValue()).asText());
        packaging.setValues(values);
        return packaging;
    }

    private static JavaVersion buildJavaVersion(JsonNode rootNode){
        List<JavaVersion.JavaValues> values = new ArrayList<>();
        for(JsonNode jv: rootNode.path(Params.VALUES.getValue())){
            JavaVersion.JavaValues javaValues = new JavaVersion.JavaValues();
            javaValues.setId(jv.path(Params.ID.getValue()).asText());
            javaValues.setName(jv.path(Params.NAME.getValue()).asText());
            values.add(javaValues);
        }
        JavaVersion javaVersion = new JavaVersion();
        javaVersion.setDefaultValue(rootNode.path(Params.DEFAULT.getValue()).asText());
        javaVersion.setValues(values);
        return javaVersion;
    }

    private static Language buildLanguage(JsonNode rootNode){
        List<Language.LangValues> langValuesList = new ArrayList<>();
        for(JsonNode values: rootNode.path(Params.VALUES.getValue())){
            Language.LangValues lang = new Language.LangValues();
            lang.setId(values.path(Params.ID.getValue()).asText());
            lang.setName(values.path(Params.NAME.getValue()).asText());
            langValuesList.add(lang);
        }
        Language language = new Language();
        language.setDefaultValue(rootNode.path(Params.DEFAULT.getValue()).asText());
        language.setValues(langValuesList);
        return language;
    }

    private static BootVersion buildBootVersion(String defaultVersion, JsonNode rootNode){
        List<BootVersion.BootValues> valuesList = new ArrayList<>();
        for(JsonNode values: rootNode){
            BootVersion.BootValues version = new BootVersion.BootValues();
            version.setId(values.path(Params.ID.getValue()).asText());
            version.setName(values.path(Params.NAME.getValue()).asText());
            valuesList.add(version);
        }
        BootVersion bootVersion = new BootVersion();
        bootVersion.setDefaultValue(defaultVersion);
        bootVersion.setValues(valuesList);
        return bootVersion;
    }

    private static String buildGroupId(JsonNode rootNode){
        return rootNode.path(Params.DEFAULT.getValue()).asText();
    }

    private static String buildArtifactId(JsonNode rootNode){
        return rootNode.path(Params.DEFAULT.getValue()).asText();
    }

    private static String buildVersion(JsonNode rootNode){
        return rootNode.path(Params.DEFAULT.getValue()).asText();
    }

    private static String buildName(JsonNode rootNode){
        return rootNode.path(Params.DEFAULT.getValue()).asText();
    }

    private static String buildDescription(JsonNode rootNode){
        return rootNode.path(Params.DEFAULT.getValue()).asText();
    }

    private static String buildPackageName(JsonNode rootNode){
        return rootNode.path(Params.DEFAULT.getValue()).asText();
    }

}
