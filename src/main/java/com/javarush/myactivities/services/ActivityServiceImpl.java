package com.javarush.myactivities.services;

import com.javarush.myactivities.entities.Activity;
import com.javarush.myactivities.entities.User;
import com.javarush.myactivities.repositories.interfaces.ActivityRepository;
import com.javarush.myactivities.services.interfaces.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public Iterable<Activity> getAllByUser(User user) {
        return activityRepository.findAllByUser(user);
    }

    @Override
    public Activity getById(Long id) {
        return activityRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        activityRepository.deleteById(id);
    }

    @Override
    public void save(Activity e) {
        activityRepository.save(e);
    }
}
