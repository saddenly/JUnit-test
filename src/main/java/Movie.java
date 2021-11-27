import java.util.Date;
import java.util.Objects;

public class Movie {
    private final String title;
    private final Date releaseDate;
    @SuppressWarnings("unused")
    private final String duration;

    public Movie(String title, Date releaseDate, String duration) {
        super();
        this.title = title;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (!Objects.equals(title, movie.title)) return false;
        if (!Objects.equals(releaseDate, movie.releaseDate)) return false;
        return Objects.equals(duration, movie.duration);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        return result;
    }
}