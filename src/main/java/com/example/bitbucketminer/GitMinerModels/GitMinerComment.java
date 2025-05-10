package com.example.bitbucketminer.GitMinerModels;

public class GitMinerComment {

    private String id;
    private String body;
    private String created_at;
    private String updated_at;
    private GitMinerUser author;

    public GitMinerComment( String id, String body, String created_at, String updated_at, GitMinerUser author ) {

        this.id = id;
        this.body = body;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.author = author;

    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
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
    public GitMinerUser getAuthor() {return author;}
    public void setAuthor(GitMinerUser author) {this.author = author;}
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }


    public String toString() {
        return "Comment:[body: " + body +
                ", created_at: " + created_at +
                ", updated_at: " + updated_at +
                ", author: " + author +"]";
    }
}
