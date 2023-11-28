package jbsoftperu.com.englishwordws.service;

import jbsoftperu.com.englishwordws.bean.BeanLogin;
import jbsoftperu.com.englishwordws.commons.GenericServiceImpl;
import jbsoftperu.com.englishwordws.dao.UserDao;
import jbsoftperu.com.englishwordws.model.User;
import jbsoftperu.com.englishwordws.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl   extends GenericServiceImpl<User,Long > implements UserService{
    @Autowired
    UserDao userDao;
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtil jwtUtil;

    @Override
    public CrudRepository<User, Long> getDao() {
        return userDao;
    }

    public User registerUser(User user) {
        passwordEncoder = new BCryptPasswordEncoder();
        String enconderPaswword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(enconderPaswword);
        return save(user);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User login(BeanLogin user) {

        User found = userDao.findByEmail(user.getEmail());

        if (found != null){
            // Password codificado previamente (almacenado en la base de datos)
            String encodedPassword = found.getPassword();
            // Contraseña que deseas verificar
            String rawPasswordToCheck = user.getPassword();
            // Crea un BCryptPasswordEncoder
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            // Verifica si la contraseña coincide
            if (passwordEncoder.matches(rawPasswordToCheck, encodedPassword)) {
                //le pongo token
                String token = jwtUtil.generateToken(user.getEmail());
                //actualizo datos user
                found.setToken(token);
                found.setLastLogin(new Date());

                return save(found);
            } else {
                return null;
            }
        }
        else {
            return null;
        }
    }
}
