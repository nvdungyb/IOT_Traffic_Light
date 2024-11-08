package iot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ThinkSpeakUploader {
    private final RestTemplate restTemplate;

    @Value("${READ_CHANNEL_URL}")
    private String readChannelUrl;
    @Value("${WRITE_CHANNEL_URL}")
    private String writeChannelUrl;
    @Value("${API_KEY}")
    private String apiKey;
    @Value("${CHANNEL}")
    private int channel;

    public ThinkSpeakUploader(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String sendData(String field1) {
        String url = UriComponentsBuilder.fromHttpUrl(writeChannelUrl)
                .queryParam("api_key", apiKey)
                .queryParam("field1", field1)
                .toUriString();

        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);
        return response;
    }

    public String readChannel(int result) {
        String url = UriComponentsBuilder.fromHttpUrl(readChannelUrl + channel + "/feeds.json")
                .queryParam("api_key", apiKey)
                .queryParam("result", result)
                .toUriString();

        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);
        return response;
    }
}
