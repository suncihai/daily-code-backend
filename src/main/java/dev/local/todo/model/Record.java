package dev.local.todo.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "record")
public class Record {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   @Column(nullable = false)
    private String user;

   @Column(nullable = false)
    private int problem;

   @Column(nullable = false)
    private Timestamp createTime;

   @Column(nullable = false)
    private boolean success;

   public Record(){}

    public Record(String user, int problem, Timestamp createTime, boolean success) {
        this.user = user;
        this.problem = problem;
        this.createTime = createTime;
        this.success = success;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getProblem() {
        return problem;
    }

    public void setProblem(int problem) {
        this.problem = problem;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
       this.createTime = createTime;
    }
    
    public boolean getSuccess() {
       return success;
    }

    public void setSuccess(boolean success) {
       this.success = success;
    }
}
