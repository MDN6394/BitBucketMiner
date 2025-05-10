package com.example.bitbucketminer.bitBucketController;


import com.example.bitbucketminer.GitMinerModels.GitMinerProject;
import com.example.bitbucketminer.Service.GitMinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/bitbucket")
public class Controller {

    RestTemplate restTemplate;

    @Autowired
    GitMinerService gitMinerService;


    @GetMapping("/{workspace}/{repo_slug}")
    public GitMinerProject getProject(@PathVariable String workspace, @PathVariable String repo_slug,
                                      @RequestParam(defaultValue = "5") int nCommits,
                                      @RequestParam(defaultValue = "5") int nIssues,
                                      @RequestParam(defaultValue = "2") int maxPages) {


        Optional<GitMinerProject> project = Optional.ofNullable(
                gitMinerService.getGitMiner(workspace, repo_slug, nCommits, nIssues, maxPages));
        return project.orElse(null);
    }

    @PostMapping("/{workspace}/{repo_slug}")
    public GitMinerProject create(@PathVariable String workspace, @PathVariable String repo_slug,
                                  @RequestParam(defaultValue = "5") int nCommits,
                                  @RequestParam(defaultValue = "5") int nIssues,
                                  @RequestParam(defaultValue = "2") int maxPages){
        String uri = "URI OF POST IN GITMINER PROJECT";
        GitMinerProject project = getProject(workspace, repo_slug, nCommits, nIssues, maxPages);
        HttpEntity<GitMinerProject> request = new HttpEntity<>(project);
        ResponseEntity<GitMinerProject> response = restTemplate.exchange(uri, HttpMethod.POST,
                request, GitMinerProject.class);

        return response.getBody();
    }



}
