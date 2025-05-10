package com.example.bitbucketminer.Service;


import com.example.bitbucketminer.commentModel.Comment;
import com.example.bitbucketminer.commitModel.Commit;
import com.example.bitbucketminer.commitModel.commitList;
import com.example.bitbucketminer.issueModel.IssueList;
import com.example.bitbucketminer.issueModel.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bitbucketminer.GitMinerModels.*;
import com.example.bitbucketminer.projectModel.*;
import com.example.bitbucketminer.Service.ProjectService.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class GitMinerService {

    @Autowired
    private ProjectService projectService;

    public GitMinerProject getProject(String workspace, String repo_slug) {
        Repository bitBucketRepository = projectService.getRepository(workspace, repo_slug);
        String name = bitBucketRepository.getName();
        String web_url = bitBucketRepository.getLinks().getHtml().toString();
        String id = bitBucketRepository.getUuid();
        return new GitMinerProject(id, name, web_url, new ArrayList<>(), new ArrayList<>());
    }

    public List<GitMinerCommit> getCommit(String workspace, String repo_slug, Integer nCommits) {
        commitList commits = projectService.getCommits(workspace, repo_slug, nCommits.toString());
        List<GitMinerCommit> result = new ArrayList<>();
        for(int i = 0; i < commits.getValues().size(); i++) {
            Commit commit = commits.getValues().get(i);
            String email = commit.getAuthor().getRaw()
                    .replace(commit.getAuthor().getUser().getDisplayName(), "");
            email = email.replace("<", "");
            email = email.replace(">", "");
            GitMinerCommit gCommit = new GitMinerCommit(commit.getHash(),
                    "Bitbucket commit: " + commit.getHash(),
                    commit.getMessage(),
                    commit.getAuthor().getUser().getDisplayName(),
                    email,
                    commit.getDate(),
                    commit.getLinks().getSelf().getHref()
                    );
            result.add(gCommit);
        }
        return result;
    }


    public List<GitMinerIssue> getIssues(String workspace, String repo_slug, Integer nIssues) {
        List<Value> issues1 = projectService.getIssueList(workspace, repo_slug);
        List<Value> issues;
        if(issues1.size() < nIssues) {
            issues = new ArrayList<>(issues1);
        } else {
            issues = issues1.subList(0, nIssues);
        }
        List<GitMinerIssue> result = new ArrayList<>();
        for (Value issue : issues) {
            String closed_at = null;
            if (Objects.equals(issue.getState(), "resolved")) {
                closed_at = issue.getUpdatedOn();
            }
            List<GitMinerComment> comments = getComments(workspace, repo_slug, issue.getId().toString());

            GitMinerUser author = new GitMinerUser(issue.getReporter().getUuid(),
                    issue.getReporter().getNickname(),
                    issue.getReporter().getDisplayName(),
                    issue.getReporter().getLinks().getAvatar().getHref(),
                    issue.getReporter().getLinks().getSelf().getHref()
                    );

            GitMinerUser assignee;
            if(issue.getAssignee() == null) {
                assignee = null;
            } else {
                assignee = new GitMinerUser(issue.getAssignee().getUuid(),
                        issue.getAssignee().getNickname(),
                        issue.getAssignee().getDisplayName(),
                        issue.getAssignee().getLinks().getAvatar().getHref(),
                        issue.getAssignee().getLinks().getSelf().getHref()
                        );
            }

            GitMinerIssue gIssue = new GitMinerIssue(issue.getId().toString(), issue.getTitle(),
                    issue.getContent().getRaw(),
                    issue.getState(),
                    issue.getCreatedOn(),
                    issue.getUpdatedOn(),
                    closed_at,
                    new ArrayList<>(),
                    issue.getVotes().toString(),
                    comments,
                    author,
                    assignee
            );

            result.add(gIssue);
        }
        return result;
    }

    private List<GitMinerComment> getComments(String workspace, String repoSlug, String id) {
        List<GitMinerComment> result = new ArrayList<>();
        List<Comment> comments = projectService.getCommentList(workspace, repoSlug, id);
        for(Comment comment : comments) {
            GitMinerUser author = new GitMinerUser(comment.getUser().getUuid(),
                    comment.getUser().getNickname(),
                    comment.getUser().getDisplayName(),
                    comment.getUser().getLinks().getAvatar().getHref(),
                    comment.getUser().getLinks().getSelf().getHref());

            GitMinerComment gComment = new GitMinerComment(comment.getId().toString(), comment.getContent().getRaw(),
                    comment.getCreatedOn(), comment.getUpdatedOn(), author);

            result.add(gComment);
        }
        return result;
    }

    public GitMinerProject getGitMiner(String workspace, String repo_slug, int nCommits,
                                       int nIssues, int maxPages) {
        GitMinerProject result = getProject(workspace, repo_slug);
        result.setCommits(getCommit(workspace, repo_slug, nCommits));
        result.setIssues(getIssues(workspace, repo_slug, nIssues));
        return result;
    }







}
