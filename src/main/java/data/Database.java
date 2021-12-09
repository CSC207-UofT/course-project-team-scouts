package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Database superclass used to store a variety of entities.
 */
public class Database<T> implements Serializable {
    protected List<T> entityList;

    public Database() {
        entityList = new ArrayList<>();
    }

    /**
     * Adds given entity to the database.
     *
     * @param entity object to be added to database
     */
    public void addEntity(T entity) {
        entityList.add(entity);
    }

    /**
     * Returns a list of entities in the database.
     *
     * @return list of entity objects
     */
    public List<T> getEntities() {
        return entityList;
    }

    /**
     * Sets the list of entities in the database to the given list.
     *
     * @param entities list of entity objects
     */
    public void setEntities(List<T> entities) {
        entityList = entities;
    }
}
