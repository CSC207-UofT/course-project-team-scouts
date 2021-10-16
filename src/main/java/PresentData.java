import java.util.List;

/*
 * This interface defines the core set of responsibilities that any
 * presenter class that handles player/team data must have.
 */
public interface PresentData<T> {
    void outputResults(List<T> resultsList);
}