package Spring.wallet;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import Spring.wallet.controller.PersonController;
import Spring.wallet.model.Gender;
import Spring.wallet.model.Person;
import Spring.wallet.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;


public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    public void createPerson_ValidPerson_ReturnsCreated() throws Exception {
        Person person = new Person();
        person.setUsername("testUser");
        person.setPassword("password");
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setNationalId("1234567890");
        person.setMobileNumber("09123456789");
       // person.setBirthDate(LocalDate.of(1990, 1, 1));
        person.setGender(Gender.MALE);
        person.setMilitaryStatus(false);
        person.setEmail("john.doe@example.com");

        when(personService.createPerson(any(Person.class))).thenReturn(person);

        mockMvc.perform(post("/api/persons")
                .contentType(APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(person)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("testUser"));
    }

    @Test
    public void getAllPersons_ReturnsListOfPersons() throws Exception {
        Person person1 = new Person();
        person1.setUsername("user1");
        
        Person person2 = new Person();
        person2.setUsername("user2");
        
        List<Person> persons = Arrays.asList(person1, person2);
        when(personService.getAllPersons()).thenReturn(persons);

        mockMvc.perform(get("/api/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void getPersonById_ValidId_ReturnsPerson() throws Exception {
        Person person = new Person();
        person.setUsername("testUser");
        
        when(personService.getPersonById(1L)).thenReturn(person);

        mockMvc.perform(get("/api/persons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUser"));
    }

    @Test
    public void updatePerson_ValidId_ReturnsUpdatedPerson() throws Exception {
        Person updatedPerson = new Person();
        updatedPerson.setUsername("updatedUser");

        when(personService.updatePerson(eq(1L), any(Person.class))).thenReturn(updatedPerson);

        mockMvc.perform(put("/api/persons/1")
                .contentType(APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedPerson)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("updatedUser"));
    }

    @Test
    public void deletePerson_ValidId_ReturnsNoContent() throws Exception {
        doNothing().when(personService).deletePerson(1L);

        mockMvc.perform(delete("/api/persons/1"))
                .andExpect(status().isNoContent());
    }
}

