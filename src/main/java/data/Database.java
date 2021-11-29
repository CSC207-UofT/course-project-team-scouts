package data;

import java.util.ArrayList;
import java.util.List;

/**
 * Database superclass with subclasses that store team
 * and player databases.
 */
class Database<T> {
    private List<T> entityList;

    public Database() {
        entityList = new ArrayList<>();
    }

    /**
     * Adds new entity to the database
     *
     * @param entity object to be added to database
     */
    public void addEntity(T entity) {
        entityList.add(entity);
    }
    
    /** 
     * Sets the list of entities in the database to
     * the given list
     * 
     * @param entities list of entity objects
     */
    public void setEntities(List<T> entities) {
        entityList = entities;
    }

    /**
     * Returns a list of enities in the database
     *
     * @return list of entity objects
     */
    public List<T> getEntities() {
        return entityList;
    }
}
