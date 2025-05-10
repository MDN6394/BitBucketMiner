package com.example.bitbucketminer.GitMinerModels;

import java.util.ArrayList;
import java.util.List;

public class GitMinerProject {

    private String id;
    private String name;
    private String web_url;
    private List<GitMinerCommit> commits;
    private List<GitMinerIssue> issues;

    public GitMinerProject( String id, String name, String web_url, List<GitMinerCommit> commits,
                            List<GitMinerIssue> issues) {
        this.id = id;
        this.name = name;
        this.web_url = web_url;
        this.commits = commits;
        this.issues = issues;

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getWeb_url() {
        return web_url;
    }
    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }
    public List<GitMinerCommit> getCommits() {
        return new ArrayList<GitMinerCommit>(commits);
    }
    public void setCommits(List<GitMinerCommit> commits) {
        this.commits = commits;
    }
    public List<GitMinerIssue> getIssues() {
        return new ArrayList<>(issues);
    }
    public void setIssues(List<GitMinerIssue> issues) {
        this.issues = issues;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String toString() {
        return "Project [name=" + name + ", web_url=" + web_url +
                ", commits=" + commits + ", issues=" + issues +
                ", id=" + id + "]";
    }
}
