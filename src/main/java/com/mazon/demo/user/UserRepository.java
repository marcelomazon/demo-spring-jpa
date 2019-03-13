package com.mazon.demo.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByEmailLike(String email);

    @Query(value = "SELECT * FROM user WHERE " +
            "LOWER(nome) LIKE LOWER(CONCAT('%',:busca, '%')) OR " +
            "LOWER(email) LIKE LOWER(CONCAT('%',:busca, '%'))", nativeQuery = true)
    public List<User> findByTermoBusca(@Param("busca") String busca);

    @Query(value = "SELECT id, nome FROM user WHERE nome LIKE CONCAT('%',:nome, '%')",
            countQuery = "select count(*) from user where nome LIKE CONCAT('%',:nome, '%')",
            nativeQuery = true)
    List<Object[]> findByAsArray(@Param("nome") String nome);

}
