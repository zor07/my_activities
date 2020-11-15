package com.javarush.myactivities.services;

import com.javarush.myactivities.entities.Activity;
import com.javarush.myactivities.repositories.interfaces.ActivityRepository;
import com.javarush.myactivities.services.interfaces.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public List<Activity> getAll() {
        return StreamSupport.stream(activityRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
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
