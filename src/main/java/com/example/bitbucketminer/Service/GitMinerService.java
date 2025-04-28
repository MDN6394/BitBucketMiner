package com.example.bitbucketminer.Service;


import com.example.bitbucketminer.commitModel.Commit;
import org.springframework.stereotype.Service;
import com.example.bitbucketminer.GitMinerModels.*;
import com.example.bitbucketminer.projectModel.*;
import com.example.bitbucketminer.Service.ProjectService.*;


@Service
public class GitMinerService {

    private ProjectService projectService;

    public GitMinerProject getProject(String workspace, String repo_slug) {
        Repository bitBucketRepository = projectService.getRepository(workspace, repo_slug);
        String name = bitBucketRepository.getName();
        String web_url = bitBucketRepository.getLinks().getHtml().toString();
        return new GitMinerProject(name, web_url);
    }

    public GitMinerCommit getCommit(String workspace, String repo_slug, String nCommits) {
        Commit commit = projectService.getCommit(workspace, repo_slug, nCommits); ///CREATE COMMIT LIST CLASS FOR BITBUCKET.
        ///  COMMIT CLASS IS ALMOST FINE (IN GET LIST COMMIT THERE IS NOT A LIST OF PARTICIPANTS)
        return null;
    }
    public GitMinerCommit getCommit(String workspace, String repo_slug) {
        return getCommit(workspace, repo_slug, "5");
    }


}
