package project.com.Recruitment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.com.Recruitment.model.JobApplication;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The repository, which allows access to applications stored in the database
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long>{

    /**
     * A method to filter applications based on the applicant's competence
     * @param competenceId the ID of the competence (e.g. ticket sales = 1, lotteries = 2, roller coaster operation = 3)
     * @return The applications that match the name
     */
    JobApplication findByCompetenceId(int competenceId);

    /**
     * A method to find the job application of a specific user
     * @param userId the applicant's specific ID
     * @return The user's application
     */
   Optional<JobApplication> findByUserId(Long userId);

}