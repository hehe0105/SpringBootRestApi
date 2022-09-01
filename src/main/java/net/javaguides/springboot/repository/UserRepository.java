package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    // define custom query using JPQL with index params
    @Query("select e from User e where e.firstName = ?1 and e.lastName = ?2")
    User findByJPQL(String firstName, String lastName);

    // define custom query using JPQL with named params
    @Query("select e from User e where e.firstName =:firstName and e.lastName =:lastName")
    User findByJPQLNamedParams(@Param("firstName") String firstName, @Param("lastName") String lastName);

    // define custom query using Native SQL with index params
    @Query(value = "select * from shop_users e where e.first_name =?1 and e.last_name =?2", nativeQuery = true)
    User findByNativeSQL(String firstName, String lastName);

    // define custom query using Native SQL with named params
    @Query(value = "select * from shop_users e where e.first_name =:firstName and e.last_name =:lastName",
            nativeQuery = true)
    User findByNativeSQLNamed(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
