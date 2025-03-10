package project.com.Recruitment.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import project.com.Recruitment.model.JobApplication;
import project.com.Recruitment.repository.JobApplicationRepository;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The service is responsible for handling the business logic.
 * It connects the repository and the controller so that the data can be transferred correctly
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class JobApplicationService {

    private JobApplicationRepository jobApplicationRepository;

    /**
     * A constructor for JobApplicationService with the JobApplicationRepository
     * @param jobApplicationRepository is used to get the job application's data
     */
    @Autowired
    public JobApplicationService(JobApplicationRepository jobApplicationRepository){
        this.jobApplicationRepository = jobApplicationRepository;
    }

    /**
     * a function to save the application in the database
     * @param jobApplication The job application that is saved
     * @return The saved job application
     */
    public JobApplication save(JobApplication jobApplication){
        return jobApplicationRepository.save(jobApplication);
    }

    /**
     * Allows an admin to retrieve a list of all the job applications stored in the database
     * @return The list of the applications
     */
    public List<JobApplication> getAllApplications() {
        return jobApplicationRepository.findAll();
    }

    /**
     * Retrieves a job applications by a specific user.
     * @param userId The user's unique ID
     * @return The user's job application stored in the database
     */
    public Optional<JobApplication> getApplicationByUser(Long userId){
        return jobApplicationRepository.findByUserId(userId);
    }

}