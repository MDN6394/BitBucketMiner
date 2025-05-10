package com.example.bitbucketminer.GitMinerModels;

import java.util.ArrayList;
import java.util.List;

public class GitMinerIssue {

    private String id;
    private String title;
    private String description;
    private String state;
    private String created_at;
    private String updated_at;
    private String closed_at;
    private List<String> labels;
    private String votes;
    private List<GitMinerComment> comments;
    private GitMinerUser author;
    private GitMinerUser assignee;

    public GitMinerIssue(String id, String title,
                         String description, String state,
                         String created_at, String updated_at,
                         String closed_at, List<String> labels,
                         String votes, List<GitMinerComment> comments,
                         GitMinerUser author, GitMinerUser assignee) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.state = state;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.closed_at = closed_at;
        this.labels = labels;
        this.votes = votes;
        this.comments = comments;
        this.author = author;
        this.assignee = assignee;

    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getCreated_at() {
        return created_at;
    }
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    public String getUpdated_at() {
        return updated_at;
    }
    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
    public String getClosed_at() {
        return closed_at;
    }
    public void setClosed_at(String closed_at) {
        this.closed_at = closed_at;
    }
    public List<String> getLabels() {
        return new ArrayList<>(labels);
    }
    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
    public String getVotes() {
        return votes;
    }
    public void setVotes(String votes) {
        this.votes = votes;
    }
    public List<GitMinerComment> getComments() { return new ArrayList<>(comments); }
    public void setComments(List<GitMinerComment> comments) { this.comments = comments; }
    public GitMinerUser getAuthor() { return author; }
    public void setAuthor(GitMinerUser author) { this.author = author; }
    public GitMinerUser getAssignee() { return assignee; }
    public void setAssignee(GitMinerUser assignee) { this.assignee = assignee; }
    public String  getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String toString() {
        return "Issue:[title " + title +
                " description " + description +
                " state " + state +
                " created_at " + created_at +
                " updated_at " + updated_at +
                " closed_at " + closed_at +
                " votes " + votes +
                " comments " + comments +
                " author " + author +
                " assignee " + assignee +
                " id " + id +"]";
    }

}
