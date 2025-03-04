package project.com.Recruitment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import project.com.Recruitment.dto.LoginDTO;
import project.com.Recruitment.dto.RegisterDTO;
import project.com.Recruitment.exceptions.IllegalRegistrationException;
import project.com.Recruitment.model.Person;
import project.com.Recruitment.repository.PersonRepository;

@Service
public class PersonService{
    
    @Autowired
    private PersonRepository personRepository;


    /**
     * Method to validate a user
     * @param loginDTO the login data
     * @return true if the user is validated, false otherwise
     */
    public boolean validateUser(LoginDTO loginDTO) {
        Optional<Person> person = personRepository.findByUsername(loginDTO.getUsername());

        if (person.isPresent()) {
            String storedPassword = person.get().getPassword();
            System.out.println("Stored password: " + storedPassword); //visas bara i cmd för debugging
            System.out.println("Entered password: " + loginDTO.getPassword());

            if (storedPassword.equals(loginDTO.getPassword())) {
                System.out.println("Password match for person: " + loginDTO.getUsername());
                return true;
            } else {
                System.out.println("Incorrect password for person: " + loginDTO.getUsername());
                return false;
            }
        } else {
            System.out.println("No person found with username: " + loginDTO.getUsername());
            return false;
        }
    }

    /**
     * Method to get a person by username
     * @param username the username to search for
     * @return the person if found, otherwise null
     */

    public Person getPersonByUsername(String username) {
        Optional<Person> person = personRepository.findByUsername(username);
        return person.orElse(null);
    }
    
    /**
     * Method to register a new person
     * @param registerDTO the data to register
     * @return the person that was registered
     * @throws IllegalRegistrationException if there exists duplicate data
    */
     
         @Transactional
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
        Person newPerson = new Person(registerDTO.getUsername(), registerDTO.getPassword(), registerDTO.getEmail(), registerDTO.getName(), registerDTO.getSurname(), registerDTO.getPnr(), 1L);
        return personRepository.save(newPerson);
    }

    /**
     * Method to retrieve all applications
     * 2L is for filtering role_id to only show applicants 
     * @return a list of persons with their applications
     */
    public List<Person> getAllApplications() {
        return personRepository.findByRoleId(2L); // Only fetch persons with role_id = 2
    }

    /**
     * Method to retrieve person by their specified id, used when getting a single application based on the search url
     * could also be used for set retrieval if an id is specified
     * @param id the id of the person to search for
     * @return a person object or empty Optional object if one couldn't be found
     */
    public Optional<Person> getPersonById(Long id){
        return personRepository.findByPersonId(id);
    }
}
