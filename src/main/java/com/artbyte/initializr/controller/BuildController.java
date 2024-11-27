package com.artbyte.initializr.controller;

import com.artbyte.initializr.constants.RemoteOrigin;
import com.artbyte.initializr.mapper.BuildDataMapper;
import com.artbyte.initializr.model.Archetype;
import com.artbyte.initializr.model.BuildData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class BuildController {

    private static final Logger log = LoggerFactory.getLogger(BuildController.class);

    @GetMapping("/build/data")
    public ResponseEntity<BuildData> springVersionsStable(){
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(RemoteOrigin.SPRING_CLIENT.getOrigin()))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.body());
            BuildData buildData = BuildDataMapper.finalObject(rootNode);

            return new ResponseEntity<>(buildData, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/generated")
    public ResponseEntity<Resource> buildProject(@RequestBody Archetype archetype) {
        try{
            StringBuilder urlBuilder = new StringBuilder(RemoteOrigin.SPRING_START.getOrigin());
                urlBuilder.append("?type=").append(URLEncoder.encode(archetype.getType(), StandardCharsets.UTF_8));
                urlBuilder.append("&language=").append(URLEncoder.encode(archetype.getLanguage(), StandardCharsets.UTF_8));
                urlBuilder.append("&bootVersion=").append(URLEncoder.encode(archetype.getBootVersion(), StandardCharsets.UTF_8));
                urlBuilder.append("&groupId=").append(URLEncoder.encode(archetype.getGroupId(), StandardCharsets.UTF_8));
                urlBuilder.append("&artifactId=").append(URLEncoder.encode(archetype.getArtifactId(), StandardCharsets.UTF_8));
                urlBuilder.append("&name=").append(URLEncoder.encode(archetype.getName(), StandardCharsets.UTF_8));
                urlBuilder.append("&dependencies=").append(URLEncoder.encode(archetype.getDependencies(), StandardCharsets.UTF_8));
                urlBuilder.append("&description=").append(URLEncoder.encode(archetype.getDescription(), StandardCharsets.UTF_8));
                urlBuilder.append("&javaVersion=").append(URLEncoder.encode(archetype.getJavaVersion(), StandardCharsets.UTF_8));
                urlBuilder.append("&packaging=").append(URLEncoder.encode(archetype.getPackaging(), StandardCharsets.UTF_8));
                urlBuilder.append("&packageName=").append(URLEncoder.encode(archetype.getPackageName(), StandardCharsets.UTF_8));

            // Soluciona posibles errores de codificación de URL
            String finalUrl = urlBuilder.toString();
            log.info("URL final => {}", finalUrl);

            URL url = new URL(finalUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Verifica la respuesta HTTP
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("Server returned HTTP response code: " + responseCode + " for URL: " + url);
            }

            //Leemos el archivo que se descarga en memoria
            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int byteRead;
            while((byteRead = inputStream.read(buffer)) != -1){
                outputStream.write(buffer, 0, byteRead);
            }
            inputStream.close();

            //Configuramos la respuesta
            ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());
            HttpHeaders headers = new HttpHeaders();
            headers.add(
                    HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=" + archetype.getName() + ".zip"
            );

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

        }catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
