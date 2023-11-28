package jbsoftperu.com.englishwordws.dao;

import jbsoftperu.com.englishwordws.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
