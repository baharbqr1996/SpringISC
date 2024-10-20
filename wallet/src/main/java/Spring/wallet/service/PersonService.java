package Spring.wallet.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Spring.wallet.model.Gender;
import Spring.wallet.model.Person;
import Spring.wallet.repository.PersonRepository;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person createPerson(Person person) {
        int age = Period.between(person.getBirthDate(), LocalDate.now()).getYears();
        if (person.getGender() == Gender.MALE && age > 18 && person.getMilitaryStatus() == null) {
            throw new IllegalArgumentException("Military status must be specified for males over 18.");
        }
        return personRepository.save(person);
    }

    
    public Person createPerson2(String username, String password) {
        Person person = new Person();
        person.setUsername(username);
        person.setPassword(password);
        return personRepository.save(person);
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
    }

    public Person updatePerson(Long id, Person personDetails) {
        Person person = getPersonById(id);
        
        person.setFirstName(personDetails.getFirstName());
        person.setLastName(personDetails.getLastName());
        person.setMobileNumber(personDetails.getMobileNumber());
        person.setEmail(personDetails.getEmail());
        person.setBirthDate(personDetails.getBirthDate());
        person.setGender(personDetails.getGender());

        int age = Period.between(person.getBirthDate(), LocalDate.now()).getYears();

        if (personDetails.getGender() == Gender.MALE && age > 18 && personDetails.getMilitaryStatus() == null) {
            throw new IllegalArgumentException("Military status must be specified for males over 18.");
        }

        return personRepository.save(person);
    }


    public void deletePerson(Long id) {
        Person person = getPersonById(id);
        personRepository.delete(person);
    }

}

