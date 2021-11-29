package data;

import java.util.List;

/**
 * Database superclass with subclasses that store team
 * and player databases.
 */
abstract class Database {
    public abstract void addEntity();
    public abstract Object createEntity(String name, List<Object> objects);
}
