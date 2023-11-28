package jbsoftperu.com.englishwordws.service;

import jbsoftperu.com.englishwordws.bean.BeanLogin;
import jbsoftperu.com.englishwordws.commons.GenericService;
import jbsoftperu.com.englishwordws.model.User;

public interface UserService  extends GenericService<User, Long> {
    User registerUser(User user);
    User findByEmail(String email);

    User login(BeanLogin user);
}
