import java.time.LocalDate;

public class Result {
    private final String eventName;
    private final LocalDate date;
    private final SwimStyle swimStyle;
    private final CompetitionTime time;
    private final String swimmerID;
    private final boolean isPractice;

    public Result(String eventName, LocalDate date, SwimStyle swimStyle, CompetitionTime time, String swimmerID, boolean isPractice){
        this.eventName = eventName;
        this.date = date;
        this.swimStyle = swimStyle;
        this.time = time;
        this.swimmerID = swimmerID;
        this.isPractice = isPractice;

    }

    public String getEventName() {
        return eventName;
    }

    public LocalDate getDate() {
        return date;
    }

    public SwimStyle getStyle() {
        return swimStyle;
    }

    public String getSwimmerID() {
        return swimmerID;
    }

    public boolean isPractice() {
        return isPractice;
    }

    public CompetitionTime getTime() {
        return time;
    }

    public enum SwimStyle {
        BUTTERFLY,
        CRAWL,
        BACK_CRAWL,
        BREASTSTROKE;
    }
    @Override
    public String toString() {
        return "I " + eventName + " : d. " + date + " : " + time + " i " + swimStyle;
    }


}