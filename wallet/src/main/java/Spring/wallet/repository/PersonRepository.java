package Spring.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Spring.wallet.model.Person;
@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	Person findByUsername(String username);
    Person findByMobileNumber(String mobileNumber);
    Person findByEmail(String email);
}
