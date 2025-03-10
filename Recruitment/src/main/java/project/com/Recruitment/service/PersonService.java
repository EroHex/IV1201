package project.com.Recruitment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import project.com.Recruitment.dto.LoginDTO;
import project.com.Recruitment.dto.RegisterDTO;
import project.com.Recruitment.exceptions.IllegalRegistrationException;
import project.com.Recruitment.model.Person;
import project.com.Recruitment.repository.PersonRepository;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class PersonService{
    
    @Autowired
    private PersonRepository personRepository;

    /**
     * Method to validate a user
     * @param loginDTO the login data
     * @return true if the user is validated, false otherwise
     */
    @Transactional(readOnly = true)
    public boolean validateUser(LoginDTO loginDTO) {
        Optional<Person> person = personRepository.findByUsername(loginDTO.getUsername());

        if (person.isPresent()) {
            String storedPassword = person.get().getPassword();
            if (storedPassword.equals(loginDTO.getPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Method to get a person by username
     * @param username the username to search for
     * @return the person if found, otherwise null
     */
    @Transactional(readOnly = true)
    public Person getPersonByUsername(String username) {
        Optional<Person> person = personRepository.findByUsername(username);
        return person.orElse(null);
    }
    
    /**
     * Method to register a new person
     * @param registerDTO the data to registration
     * @return the person that was registered
     * @throws IllegalRegistrationException if there exists duplicate data
    */
    
    @Transactional(readOnly = false)
    public Person registerPerson(RegisterDTO registerDTO) throws IllegalRegistrationException {
        if (personRepository.findByUsername(registerDTO.getUsername()).isPresent()) {
            throw new IllegalRegistrationException("Username already exists!");
        }
        if (personRepository.findByEmail(registerDTO.getEmail()).isPresent()) {
            throw new IllegalRegistrationException("That email aldready has an account associated with it!");
        }
        if (personRepository.findByPnr(registerDTO.getPnr()).isPresent()) {
            throw new IllegalRegistrationException("That personal number is already in use!");
        }
        Person newPerson = new Person(registerDTO.getUsername(), registerDTO.getPassword(), registerDTO.getEmail(), registerDTO.getName(), registerDTO.getSurname(), registerDTO.getPnr(), 2L);
        return personRepository.save(newPerson);
    }

    /**
     * Method to retrieve all applications
     * 2L is for filtering role_id to only show applicants 
     * @return a list of persons with their applications
     */
    @Transactional(readOnly = true)
    public Page<Person> getAllApplications(Pageable pageable) {
        return personRepository.findByRoleId(2L, pageable); // Only fetch persons with role_id = 2
    }

    /**
     * Method to retrieve person by their specified id, used when getting a single application based on the search url
     * could also be used for set retrieval if an id is specified
     * @param id the id of the person to search for
     * @return a person object or empty Optional object if one couldn't be found
     */
    @Transactional(readOnly = true)
    public Optional<Person> getPersonById(Long id){
        return personRepository.findByPersonId(id);
    }
}