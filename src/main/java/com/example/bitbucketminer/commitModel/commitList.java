package com.example.bitbucketminer.commitModel;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class commitList {

    @JsonProperty("pagelen")
    public String pagelen;
    @JsonProperty("next")
    public String next;
    @JsonProperty("previous")
    public String previous;
    @JsonProperty("values")
    public List<Commit> values;

    public commitList(String pagelen,String next,String previous,List<Commit> values) {
        this.pagelen = pagelen;
        this.next = next;
        this.previous = previous;
        this.values = values;
    }

    @JsonProperty("pagelen")
    public String getPagelen() {
        return pagelen;
    }

    @JsonProperty("pagelen")
    public void setPagelen(String pagelen) {
        this.pagelen = pagelen;
    }

    @JsonProperty("next")
    public String getNext() {
        return next;
    }

    @JsonProperty("next")
    public void setNext(String next) {
        this.next = next;
    }
    @JsonProperty("previous")
    public String getPrevious() {
        return previous;
    }

    @JsonProperty("previous")
    public void setPrevious(String previous) {
        this.previous = previous;
    }

    @JsonProperty("values")
    public List<Commit> getValues() {
        return new ArrayList<>(this.values);
    }

    @JsonProperty("values")
    public void setValues(List<Commit> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "commitList{" +
                "pagelen='" + pagelen + '\'' +
                ", next='" + next + '\'' +
                ", previous='" + previous + '\'' +
                ", values=" + values +
                '}';
    }
}
