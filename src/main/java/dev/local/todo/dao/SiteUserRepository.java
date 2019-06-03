package dev.local.todo.dao;

import dev.local.todo.model.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface SiteUserRepository extends JpaRepository<SiteUser, Long>{
    @Query("from SiteUser u where u.username=:username")
    SiteUser findUser(@Param("username") String username);
}
