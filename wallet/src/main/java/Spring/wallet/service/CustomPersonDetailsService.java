package Spring.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Spring.wallet.model.Person;
import Spring.wallet.repository.PersonRepository;

@Service
public class CustomPersonDetailsService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return org.springframework.security.core.userdetails.User
            .withUsername(person.getUsername())
            .password(person.getPassword())
            .roles(person.getRole()) // در اینجا می‌توانید نقش‌های کاربر را مدیریت کنید
            .build();
    }
}
