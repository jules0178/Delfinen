package domain_model;

import java.time.LocalDate;

public class Result {
    private String memberID;
    private String eventName;
    private LocalDate date;
    private SwimStyle swimStyle;
    private CompetitionTime time;
    private boolean isPractice;
    private int placement;

    public Result(String memberID,  String eventName, LocalDate date, SwimStyle swimStyle, CompetitionTime time, boolean isPractice, int placement){
        this.memberID = memberID;
        this.eventName = eventName;
        this.date = date;
        this.swimStyle = swimStyle;
        this.time = time;
        this.isPractice = isPractice;
        this.placement = placement;
    }
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date){
        this.date = date;
    }
    public SwimStyle getStyle() {
        return swimStyle;
    }
    public void setSwimStyle(SwimStyle swimStyle) {
        this.swimStyle = swimStyle;
    }
    public String getMemberID() {
        return memberID;
    }
    public void setMemberID(String memberID){
        this.memberID = memberID;
    }

    public boolean isPractice() {
        return isPractice;
    }

    public CompetitionTime getTime() {
        return time;
    }

    public void setTime(int minutes, int seconds, int hundredths){
        this.time = time;
    }
    public int getPlacement(){
        return placement;
    }

    public enum SwimStyle {
        BUTTERFLY("Butterfly"),
        CRAWL("Crawl"),
        BACK_CRAWL("Ryg crawl"),
        BREASTSTROKE("Brystsvømmning");

        private String discipline;
        SwimStyle(String discipline) {
            this.discipline = discipline;
        }
        public String getDiscipline() {
            return discipline;
        }
    }

    @Override
    public String toString() {
        if (!isPractice) {
            return " fik i " + eventName + " d." + date + " tiden " + time + " og placerede "+ placement + ". i " + swimStyle.getDiscipline();
        }else{
            return " fik d." + date + " tiden " + time + ". i " + swimStyle.getDiscipline();
        }
    }
}