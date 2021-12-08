package io;

import java.util.List;

/*
 * This interface defines the core set of responsibilities that any
 * presenter class that handles player/team data must have.
 */
public interface PresentData<T> {
    /**
     * Outputs the results of a search or query performed by the user.
     *
     * @param resultsList list of results (of concrete type of entity) returned by the search or query.
     */
    void outputResults(List<T> resultsList);
}