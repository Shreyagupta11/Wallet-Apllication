package com.nexdew.wallet.repository;

import javax.transaction.Transactional;

import com.nexdew.wallet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface   UserRepository extends BaseRepository<User, Long> {

  boolean existsByUsername(String username);
  boolean existsByEmail(String email);
  boolean existsById(Long id);



 User findByEmail(String email);

  User findByUsername(String username);

  void deleteByUsername(String username);
  List<User> findByExpireDateOrExpireDate(LocalDate startDate, LocalDate endDate);

}
