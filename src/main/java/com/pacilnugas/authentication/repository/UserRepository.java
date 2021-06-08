package com.pacilnugas.authentication.repository;

import com.pacilnugas.authentication.core.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Account, String> {
//    Account findByUsername(String username);
}
