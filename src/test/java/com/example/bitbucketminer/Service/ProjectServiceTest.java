package com.example.bitbucketminer.Service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.bitbucketminer.projectModel.Repository;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProjectServiceTest {

    @Autowired
    ProjectService projectService;

    @Test
    @DisplayName("Get Repository")
    void getRepository() {
        Repository repository = projectService.getRepository("gentlero","bitbucket-api");
        assertNotNull(repository);
        System.out.println("Type: " + repository.getType());
        System.out.println("Links: " + repository.getLinks());
        System.out.println("uuid: "+ repository.getUuid());
        System.out.println("Full name: "+ repository.getFullName());
        System.out.println("Is private? : "+ repository.getIsPrivate());
        System.out.println("scm: "+ repository.getScm());
        System.out.println("type: "+ repository.getType());
        System.out.println("Owner: "+ repository.getOwner());
        System.out.println("Name: "+ repository.getName());
        System.out.println("Description: "+ repository.getDescription());
        System.out.println("Created on: "+ repository.getCreatedOn());
        System.out.println("Updated On: "+ repository.getUpdatedOn());
        System.out.println("Size: "+ repository.getSize());
        System.out.println("Language: "+ repository.getLanguage());
        System.out.println("Has Issues? : "+ repository.getHasIssues());
        System.out.println("Has Wiki? : "+ repository.getHasWiki());
        System.out.println("Fork policy: "+ repository.getForkPolicy());
        System.out.println("Project: "+ repository.getProject());
        System.out.println("Main branch: "+ repository.getMainbranch());
    }
}