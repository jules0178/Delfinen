public class CompetitionTime implements Comparable<CompetitionTime> {
    private int minutes;
    private int seconds;
    private int hundredths;

    public CompetitionTime(int minutes, int seconds, int hundredths) {
        setMinutes(minutes);
        setSeconds(seconds);
        setHundredths(hundredths);
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
    public int getMinutes() {
        return minutes;
    }

    public void setSeconds(int seconds) {
        if (seconds >= 0 && seconds < 60) {
            this.seconds = seconds;
        } else {
            throw new IllegalArgumentException("Indtast et tal fra 0-59");
        }
    }

    public void setHundredths(int hundredths) {
        if (hundredths >= 0 && hundredths < 100) {
            this.hundredths = hundredths;
        } else {
            throw new IllegalArgumentException("Indtast et tal fra 0-99");
        }
    }

    @Override
    public int compareTo(CompetitionTime other) {

        int totalSecondsThis = this.minutes * 60 + this.seconds;
        int totalSecondsOther = other.minutes * 60 + other.seconds;

        return Integer.compare(totalSecondsThis, totalSecondsOther);
    }

    @Override
    public String toString() {
        return String.format("%d:%02d.%02d", minutes, seconds, hundredths);
    }
}