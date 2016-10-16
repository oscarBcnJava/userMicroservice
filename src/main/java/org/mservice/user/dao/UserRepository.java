package org.mservice.user.dao;

import org.mservice.user.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by oscarimac122 on 15/10/16.
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    List<User> findAllUsers();

    @Query("SELECT u FROM User u WHERE u.id>= :startId AND u.id<=:endId")
    List<User> findUsersBetweenIds(@Param("startId") Long startId, @Param("endId") Long endId);
}
