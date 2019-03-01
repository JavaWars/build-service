package com.lazarev.service;

import com.lazarev.model.Activity;
import com.lazarev.model.Developer;
import com.lazarev.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivitiesService {

    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public List<Activity> getActivitiesForDeveloperId(Long developerId) {
        return activityRepository.getAllActivitiesForDeveloperId(developerId);
    }

    public void insert(Activity activity) {
        activityRepository.save(activity);
    }

    public void remove(Long activityId) {
        activityRepository.deleteById(activityId);
    }
}
