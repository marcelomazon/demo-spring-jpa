package com.mazon.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);

    @Query(value = "SELECT * FROM user WHERE " +
            "LOWER(nome) LIKE LOWER(CONCAT('%',:busca, '%')) OR " +
            "LOWER(email) LIKE LOWER(CONCAT('%',:busca, '%'))", nativeQuery = true)
    List<User> findByTermoBusca(@Param("busca") String busca);

}
