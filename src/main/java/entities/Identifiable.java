package entities;

/**
 * This interface is implemented by Player and Team entities and is used in SearchByName
 * to ensure that the generic type has the method getName.
 */
public interface Identifiable {
    String getName();
}
