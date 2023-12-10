package domain_model;

public class CompetitionTime implements Comparable<CompetitionTime> {
    private int minutes;
    private int seconds;
    private int hundredths;

    public static CompetitionTime createCompetitionTime(int minutes, int seconds, int hundredths) {
        if (minutes < 0 || minutes >= 60) {
            throw new IllegalArgumentException("Indtast en værdi fra 0-59");
        }
        if (seconds < 0 || seconds >= 60) {
            throw new IllegalArgumentException("Indtast en værdi fra 0-59");
        }
        if (hundredths < 0 || hundredths >= 100) {
            throw new IllegalArgumentException("Indtast en værdi fra 0-100");
        }
        return new CompetitionTime(minutes, seconds, hundredths);
    }

    // Constructor can be private if it's only called from within the class
    public CompetitionTime(int minutes, int seconds, int hundredths) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.hundredths = hundredths;
    }


    @Override
    public int compareTo(CompetitionTime other) {

        int totalHundredthsThis = this.minutes * 6000 + this.seconds * 100 + this.hundredths;
        int totalHundredthsOther = other.minutes * 6000 + other.seconds * 100 + other.hundredths;

        return Integer.compare(totalHundredthsThis, totalHundredthsOther);
    }

    @Override
    public String toString() {
        return String.format("%d:%02d.%02d", minutes, seconds, hundredths);
    }

    public int getMinutes() {
        return minutes;
    }
    public int getSeconds() {
        return seconds;
    }
    public int getHundredths() {
        return hundredths;
    }
}