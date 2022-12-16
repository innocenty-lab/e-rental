package org.putra.repository;

import org.putra.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserCredentialRepo extends JpaRepository<UserCredential, String> {
    @Query(value = "SELECT * FROM mst_usercredential mu WHERE mu.email = ?1 AND mu.password = ?2", nativeQuery = true)
    UserCredential getUserCredential(String email, String password);
}
