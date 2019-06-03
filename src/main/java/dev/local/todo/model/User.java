package dev.local.todo.model;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   @Column(nullable = false)
    private String name;

   @Column(nullable = false)
    private Integer age;

   public User(){}

    public User(String name, Integer age, String username, String password) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
