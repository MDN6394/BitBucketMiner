package com.example.bitbucketminer.Service;

import com.example.bitbucketminer.commentListModel.CommentList;
import com.example.bitbucketminer.commentModel.Comment;
import com.example.bitbucketminer.commitModel.Commit;
import com.example.bitbucketminer.commitModel.commitList;
import com.example.bitbucketminer.issueModel.IssueList;
import com.example.bitbucketminer.issueModel.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.example.bitbucketminer.projectModel.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import java.util.List;
import java.util.Objects;


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

    public Commit getCommit(String workspace, String repo_slug, String commit) {
        String uri = "https://api.bitbucket.org/2.0/repositories/" + workspace + "/" + repo_slug +
                "/commit/" + commit;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Commit> request = new HttpEntity<>(null, headers);

        ResponseEntity<Commit> response = restTemplate.exchange(uri, HttpMethod.GET,
                request, Commit.class);

        return response.getBody();
    }

    public commitList getCommits(String workspace, String repo_slug){
        String uri = "https://api.bitbucket.org/2.0/repositories/" + workspace + "/" + repo_slug +
                "/commits";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<commitList> request = new HttpEntity<>(null, headers);

        ResponseEntity<commitList> response = restTemplate.exchange(uri, HttpMethod.GET,
                request, commitList.class);
        return response.getBody();
    }

    public commitList getCommits(String workspace, String repo_slug, String nCommits){
        String uri = "https://api.bitbucket.org/2.0/repositories/" + workspace + "/" + repo_slug +
                "/commits?pagelen="+nCommits;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<commitList> request = new HttpEntity<>(null, headers);

        ResponseEntity<commitList> response = restTemplate.exchange(uri, HttpMethod.GET,
                request, commitList.class);
        return response.getBody();
    }

    public List<Value> getIssueList(String workspace, String repo_slug) {
        String uri = "https://api.bitbucket.org/2.0/repositories/" + workspace + "/" + repo_slug + "/issues";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<IssueList> request = new HttpEntity<>(null, headers);

        ResponseEntity<IssueList> response = restTemplate.exchange(uri, HttpMethod.GET,
                request, IssueList.class);
        List<Value> result = Objects.requireNonNull(response.getBody()).getValues();
        if(Objects.requireNonNull(response.getBody()).getPagelen() != null &&
                Objects.equals(response.getBody().getPagelen(), response.getBody().getSize())) {
            return result;
        }
        Integer c = 2;
        while (!response.getBody().getValues().isEmpty()) {
            String newUri = "https://api.bitbucket.org/2.0/repositories/"
                    + workspace + "/" + repo_slug + "/issues?page=" + c.toString();
            response = restTemplate.exchange(newUri, HttpMethod.GET,
                    request, IssueList.class);
            result.addAll(Objects.requireNonNull(response.getBody()).getValues());
            c++;

        }
        return result;
    }

    public List<Comment> getCommentList(String workspace, String repo_slug, String id) {
        String uri = "https://api.bitbucket.org/2.0/repositories/"
                + workspace + "/" + repo_slug + "/issues/" + id + "/comments";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<CommentList> request = new HttpEntity<>(null, headers);
        ResponseEntity<CommentList> response = restTemplate.exchange(uri, HttpMethod.GET,
                request, CommentList.class);
        List<Comment> result = Objects.requireNonNull(response.getBody()).getComments();
        if(Objects.requireNonNull(response.getBody()).getPagelen() != null &&
                Objects.equals(response.getBody().getPagelen(), response.getBody().getSize())) {
            return result;
        }
        Integer c = 2;
        while(!response.getBody().getComments().isEmpty()) {
            String newUri = "https://api.bitbucket.org/2.0/repositories/"
                    + workspace + "/" + repo_slug + "/issues/" + id + "/comments?page=" + c.toString();
            response = restTemplate.exchange(newUri, HttpMethod.GET,
                    request, CommentList.class);
            result.addAll(Objects.requireNonNull(response.getBody()).getComments());
        }
        return result;
    }
}
