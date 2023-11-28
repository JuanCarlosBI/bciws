package jbsoftperu.com.englishwordws.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    @NotBlank(message = "El correo no puede estar en blanco")
    @Email(message = "El correo debe tener un formato válido")
    private String email;
    @Column
    @NotBlank(message = "El password no puede estar en blanco")
    private String password;
    @Column
    private Date created;
    @Column
    private Date modified;
    @Column
    private Date lastLogin;
    @Column
    private String token;
    @Column
    private boolean isActive;
   // @Transient
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<Phone> phones;

    public void setPhones(List<Phone> phones) {
        if (phones == null){
            phones = new ArrayList<>();
        }
        this.phones = phones;
    }
}
