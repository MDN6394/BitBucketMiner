package com.example.bitbucketminer.Service;


import com.example.bitbucketminer.commentModel.Comment;
import com.example.bitbucketminer.commitModel.Commit;
import com.example.bitbucketminer.commitModel.commitList;

import com.example.bitbucketminer.issueModel.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bitbucketminer.GitMinerModels.*;
import com.example.bitbucketminer.projectModel.*;


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
        List<Commit> values = commits.getValues();

        /*for(int i = 0; i < commits.getValues().size(); i++) {
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
        }*/
        return values.stream().map(this::createGitMinerCommit).toList();
    }

    private GitMinerCommit createGitMinerCommit(Commit commit) {
        String email = commit.getAuthor().getRaw()
                .replace(commit.getAuthor().getUser().getDisplayName(), "");
        email = email.replace("<", "");
        email = email.replace(">", "");
        return new GitMinerCommit(commit.getHash(),
                "Bitbucket commit: " + commit.getHash(),
                commit.getMessage(),
                commit.getAuthor().getUser().getDisplayName(),
                email,
                commit.getDate(),
                commit.getLinks().getSelf().getHref()
        );
    }


    public List<GitMinerIssue> getIssues(String workspace, String repo_slug, Integer nIssues) {
        List<Value> issues = projectService.getIssueList(workspace, repo_slug, nIssues.toString());
        return issues.stream().map(i -> createGitMinerIssue(i, workspace, repo_slug))
                .toList();
    }

    private GitMinerIssue createGitMinerIssue(Value issue, String workspace, String repo_slug) {
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

        return new GitMinerIssue(issue.getId().toString(), issue.getTitle(),
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
    }

    private List<GitMinerComment> getComments(String workspace, String repoSlug, String id) {

        List<Comment> comments = projectService.getCommentList(workspace, repoSlug, id);
        return comments.stream().map(this::createGitMinerComment).toList();
    }

    private GitMinerComment createGitMinerComment(Comment comment) {
        GitMinerUser author = new GitMinerUser(comment.getUser().getUuid(),
                comment.getUser().getNickname(),
                comment.getUser().getDisplayName(),
                comment.getUser().getLinks().getAvatar().getHref(),
                comment.getUser().getLinks().getSelf().getHref());

        String content = comment.getContent().getRaw();
        if(content == null) {
            content = "<No message>";
        }

        return new GitMinerComment(comment.getId().toString(), content,
                comment.getCreatedOn(), comment.getUpdatedOn(), author);
    }

    public GitMinerProject getGitMiner(String workspace, String repo_slug, int nCommits,
                                       int nIssues, int maxPages) {
        GitMinerProject result = getProject(workspace, repo_slug);
        result.setCommits(getCommit(workspace, repo_slug, nCommits));
        result.setIssues(getIssues(workspace, repo_slug, nIssues));
        return result;
    }







}
