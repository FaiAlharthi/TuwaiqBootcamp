package com.example.lab10.Service;

import com.example.lab10.Model.JobApplication;
import com.example.lab10.Repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    //CRUD

    public List<JobApplication> getAllJobApplications(){
        return jobApplicationRepository.findAll();
    }

    public Boolean addJobApplication(JobApplication jobApplication){
        jobApplicationRepository.save(jobApplication);
        return true;
    }

    public Boolean updateJobApplication(Integer id, JobApplication jobApplication){
        JobApplication jobApplication1 = jobApplicationRepository.findJobApplicationById(id);
        if(jobApplication1 == null){
            return false;
        }
        jobApplication1.setUserId(jobApplication.getUserId());
        jobApplication1.setJobPostId(jobApplication.getJobPostId());

        jobApplicationRepository.save(jobApplication1);
        return true;
    }

    public Boolean deleteJobApplication(Integer id){
        JobApplication jobApplication = jobApplicationRepository.findJobApplicationById(id);
        if(jobApplication == null){
            return false;
        }
        jobApplicationRepository.delete(jobApplication);
        return true;
    }
}