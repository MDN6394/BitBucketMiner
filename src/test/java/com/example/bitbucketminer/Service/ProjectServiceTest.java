package com.example.bitbucketminer.Service;

import com.example.bitbucketminer.commentModel.Comment;
import com.example.bitbucketminer.commitModel.Commit;
import com.example.bitbucketminer.issueModel.Value;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.bitbucketminer.projectModel.Repository;

import java.util.List;

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

    @Test
    @DisplayName("Get Commit")
    void getCommit() {
        Commit commit = projectService.getCommit("gentlero","bitbucket-api", "67a0362");
        assertNotNull(commit);
        System.out.println("Type: " + commit.getType());
        System.out.println("Hash: " + commit.getHash());
        System.out.println("Date: " + commit.getDate());
        System.out.println("Author: " + commit.getAuthor());
        System.out.println("Message: " + commit.getMessage());
        System.out.println("Summary: " + commit.getSummary());
        System.out.println("Links: " + commit.getLinks());
        System.out.println("Parents: " + commit.getParents());
        System.out.println("Rendered: " + commit.getRendered());
        System.out.println("Repository: " + commit.getRepository());
        System.out.println("Participants: " + commit.getParticipants());


    }

    @Test
    @DisplayName("Get issueList")
    void getIssueList() {
        List<Value> issueList = projectService.getIssueList("gentlero","bitbucket-api");
        assertNotNull(issueList);
        System.out.println(issueList.size());
        issueList.forEach(System.out::println);
    }

    @Test
    @DisplayName("GetCommentList")
    void getCommentList() {
        List<Comment> commentList = projectService.getCommentList("gentlero","bitbucket-api",
                "87");
        assertNotNull(commentList);
        System.out.println(commentList.size());
        commentList.forEach(System.out::println);
    }
}