package com.example.lab10.Service;

import com.example.lab10.Model.JobPost;
import com.example.lab10.Model.User;
import com.example.lab10.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {
    private final JobPostRepository jobPostRepository;

    //CRUD

    public List<JobPost> getAllJobPosts(){
        return jobPostRepository.findAll();
    }

    public Boolean addJobPost(JobPost jobPost){
        jobPostRepository.save(jobPost);
        return true;
    }

    public Boolean updateJobPost(Integer id, JobPost jobPost){
        JobPost jobPost1 = jobPostRepository.findJobPostById(id);
        if(jobPost1 == null){
            return false;
        }
        jobPost1.setTitle(jobPost.getTitle());
        jobPost1.setDescription(jobPost.getDescription());
        jobPost1.setLocation(jobPost.getLocation());
        jobPost1.setPostingDate(jobPost.getPostingDate());
        jobPost1.setSalary(jobPost.getSalary());

        jobPostRepository.save(jobPost1);
        return true;
    }

    public Boolean deleteJobPost(Integer id){
        JobPost jobPost = jobPostRepository.findJobPostById(id);
        if(jobPost == null){
            return false;
        }
        jobPostRepository.delete(jobPost);
        return true;
    }

}
