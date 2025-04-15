package com.example.bitbucketminer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.example.bitbucketminer.projectModel.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;


@Service
public class ProjectService {

    RestTemplate restTemplate;

    @Autowired
    public ProjectService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Repository getRepository(String workspace, String repo_slug) {
        String uri = "https://api.bitbucket.org/2.0/repositories/" + workspace + "/" + repo_slug;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Repository> request = new HttpEntity<>(null, headers);

        ResponseEntity<Repository> response = restTemplate.exchange(uri, HttpMethod.GET,
                request, Repository.class);

        return response.getBody();
    }
}
