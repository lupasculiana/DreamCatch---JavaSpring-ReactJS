package com.example.demo.user;

import com.example.demo.dream.Dream;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Data
@Entity

public class Login {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String username;
    private String password;
    @OneToMany(targetEntity = Dream.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "ud_fk",referencedColumnName = "id")
    private List<Dream> dreams;

    public Login() {
    }

    public Login(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "The user "+username+" has password: "
                +password;
    }
}
