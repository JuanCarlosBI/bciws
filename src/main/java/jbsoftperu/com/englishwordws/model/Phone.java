package jbsoftperu.com.englishwordws.model;



import lombok.Data;

import javax.persistence.*;


@Entity(name = "phone")
@Data
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String number;
    @Column
    private String cityCode;
    @Column
    private String countryCode;
}
