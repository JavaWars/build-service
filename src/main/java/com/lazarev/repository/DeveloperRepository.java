package com.lazarev.repository;

import com.lazarev.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer,Long> {

    Developer findByPhone(String phone);

    @Query(nativeQuery = true,
            value = "select * from developers where user_id=? limit 1")
    Developer findByUserId(Long id);

    @Query(nativeQuery = true,
            value = "delete from developer_admin where user_id=?;")
    void deleteAdmin(long id);

    @Query(nativeQuery = true,
            value = "SELECT developers.* FROM developers , developer_admin\n" +
                    "WHERE  ( (developers.user_id=:id)or (developer_admin.user_id=:id) ) and developers.developer_id=developer_admin.developer_id\n" +
                    "GROUP BY developer_id\n")
    Developer findByAdminOrOwner(long id);

    @Query(nativeQuery = true,
            value = "select * from developers where developers.user_id=:id")
    Optional<Developer> findOwnerOfCompanyByUserId(long id);
}
