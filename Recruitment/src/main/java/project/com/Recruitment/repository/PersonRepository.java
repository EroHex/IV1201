package project.com.Recruitment.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.com.Recruitment.model.Person;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface PersonRepository extends JpaRepository<Person, Long> {
    
    /**
     * Finds and returns a person with username
     * @param username  the username to search for
     * @return  Person if any is found otherwise optional object which is empty
     */
    Optional<Person> findByUsername(String username); 

    /**
     * Finds and returns a list of all persons with role_id
     * @param roleId   1 or 2, 1 = recruiter, 2 = applicants
     * @return  Page of persons with param role_id
     */
    Page<Person> findByRoleId(Long roleId, Pageable pageable);

    /**
     * Finds and returns a person object for specific application info
     * @param personId   id to identify application by
     * @return  Person if any is found otherwise optional object which is empty
     */
    Optional<Person> findByPersonId(Long personId); 

    /**
     * Finds and returns a person object based on email
     * @param email   email to identify application by
     * @return  Person if any is found otherwise optional object which is empty
     */
    Optional<Person> findByEmail(String email);

    /**
     * Finds and returns a person object based on Personal Number
     * @param pnr   personal number to identify application by
     * @return  Person if any is found otherwise optional object which is empty
     */
    Optional<Person> findByPnr(String pnr);
}