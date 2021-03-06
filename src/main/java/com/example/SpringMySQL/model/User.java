package com.example.SpringMySQL.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Integer id;

    @NotNull
    @Size(min = 6)
    @Getter @Setter
    @Column(name = "name")
    private String name;

    @Getter @Setter
    @Email(message = "Must be a valid email")
    @Column(name = "email")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 70, message = "Age must not be over 70")
    @Getter @Setter
    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    @Getter @Setter
    private String gender;

    @Column(name = "description")
    @Size(min = 10)
    @Getter @Setter
    private String description;

    public User() {
    }

    public User(@NotNull Integer id, @NotNull @Size(min = 6) String name, @Email(message = "Must be a valid email") String email, @Min(value = 18, message = "Age must be at least 18") @Max(value = 70, message = "Age must not be over 70") Integer age, String gender, @Size(min = 10) String description) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.description = description;
    }

    public User(@NotNull @Size(min = 6) String name, @Email(message = "Must be a valid email") String email, @Min(value = 18, message = "Age must be at least 18") @Max(value = 70, message = "Age must not be over 70") Integer age, String gender, @Size(min = 10) String description) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("User [id=%d, name=%s, email=%s, age=%d, gender=%s, description=%s]", id, name, email, age, gender, description);
    }
}
