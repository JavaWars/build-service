package com.lazarev.repository;

import com.lazarev.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,Long> {

    @Query(nativeQuery = true,
    value = "SELECT act.* FROM developers as dev, developer_activities as d_a, activities as act\n" +
            "WHERE dev.developer_id = d_a.developer_id AND d_a.activity_id = act.activity_id AND dev.developer_id=:developerId")
    List<Activity> getAllActivitiesForDeveloperId(Long developerId);

}
