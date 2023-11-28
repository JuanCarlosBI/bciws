package jbsoftperu.com.englishwordws.controller;

import jbsoftperu.com.englishwordws.bean.BeanLogin;
import jbsoftperu.com.englishwordws.model.User;
import jbsoftperu.com.englishwordws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody User user) {
        try {

            if(user.getPassword().isEmpty() || user.getPassword().isBlank()){
                return new ResponseEntity<>(new Error("Error password en blanco "), HttpStatus.BAD_REQUEST);
            }
            else {
                User found =  userService.findByEmail(user.getEmail());

                if (found != null){
                    return new ResponseEntity<>(new Error("Error al registrar usuario: email encontrado"), HttpStatus.BAD_REQUEST);
                }
                else {
                    User registeredUser = userService.registerUser(user);
                    return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
                }
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new Error("Error al registrar usuario: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody BeanLogin user) {

        if(user.getEmail().isEmpty()){
            return new ResponseEntity<>(new Error("Error email en blanco " ), HttpStatus.BAD_REQUEST);
        }else{
            if (user.getPassword().isEmpty()){
                return new ResponseEntity<>(new Error("Error password en blanco " ), HttpStatus.BAD_REQUEST);
            }
            else{
                try {
                    User found =  userService.login(user);

                    if (found == null){
                        return new ResponseEntity<>(new Error("Error usuario y password incorrectos" ), HttpStatus.BAD_REQUEST);
                    }
                    else {
                        return new ResponseEntity<>(found, HttpStatus.OK);
                    }
                } catch (Exception e) {
                    return new ResponseEntity<>(new Error("Error en el login: " + e.getMessage()), HttpStatus.BAD_REQUEST);
                }
            }
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.get(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User userDetails) {
        User updatedUser = userService.save(userDetails);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>("Usuario eliminado correctamente", HttpStatus.OK);
    }

}
