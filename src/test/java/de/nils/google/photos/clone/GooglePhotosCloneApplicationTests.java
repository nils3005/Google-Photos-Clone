package de.nils.google.photos.clone;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.nils.google.photos.clone.model.Photo;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GooglePhotosCloneApplicationTests {
    public static String createPhotoUrl;
    private final ObjectMapper objectMapper = new ObjectMapper();
    public static RestTemplate restTemplate;
    public static HttpHeaders headers;
    public static JSONObject photoJsonObject;

    @BeforeTestClass
    public static void runBeforeAllTestMethods() throws JSONException {
        createPhotoUrl = "http://localhost:8080/post";
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        photoJsonObject = new JSONObject();
        photoJsonObject.put("id", "1");
        photoJsonObject.put("fileName", "testImg.jpg");
        photoJsonObject.put("base64", "base64String");
        photoJsonObject.put("lastmodified", "30-05-2005");
    }

    @Test
    public void givenDataIsJson_whenDataIsPostedByPostForObject_thenResponseBodyIsNotNull() throws IOException {
        HttpEntity<String> request = new HttpEntity<String>(photoJsonObject.toString(), headers);

        Photo photo = restTemplate.postForObject(createPhotoUrl, request, Photo.class);

        assertNotNull(photo);
        assertNotNull(photo.getId());
    }
}
