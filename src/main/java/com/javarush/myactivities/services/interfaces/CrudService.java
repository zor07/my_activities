package com.javarush.myactivities.services.interfaces;

import java.util.List;

public interface CrudService<Entity, ID> {

    List<Entity> getAll();
    Entity getById(ID id);
    void delete(ID id);
    void save(Entity e);

}
