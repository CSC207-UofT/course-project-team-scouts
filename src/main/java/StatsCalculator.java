public abstract class StatsCalculator<T> {
    abstract int generateOffensiveRating(T entity);

    abstract int generateDefensiveRating(T entity);
}