package com.javarush.myactivities.services.interfaces;

import com.javarush.myactivities.entities.Activity;
import com.javarush.myactivities.entities.Entry;

import java.util.List;
import java.util.Map;

public interface EntryService extends CrudService<Entry, Long> {
    Map<Activity, List<Entry>> getActivityEntriesMap();
}
