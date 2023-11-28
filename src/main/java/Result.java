import java.time.LocalDate;

public class Result {
    private final String eventName;
    private final LocalDate date;
    private final SwimStyle swimStyle;
    private final CompetitionTime time;
    private final boolean isPractice;

    public Result(String eventName, LocalDate date, SwimStyle swimStyle, CompetitionTime time, boolean isPractice){
        this.eventName = eventName;
        this.date = date;
        this.swimStyle = swimStyle;
        this.time = time;
        this.isPractice = isPractice;

    }

    public enum SwimStyle {
        BUTTERFLY,
        CRAWL,
        BACK_CRAWL,
        BREASTSTROKE;

    }
    public SwimStyle getStyle() {
        return swimStyle;
    }
    @Override
    public String toString() {
        return "I " + eventName + " : d. " + date + " : " + time + " i " + swimStyle;
    }

    public CompetitionTime getTime() {
        return time;
    }

}