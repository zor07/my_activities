package com.javarush.myactivities.services.interfaces;

import com.javarush.myactivities.entities.User;

public interface CrudService<Entity, ID> {

    Iterable<Entity> getAllByUser(User user);
    Entity getById(ID id);
    void delete(ID id);
    void save(Entity e);

}
