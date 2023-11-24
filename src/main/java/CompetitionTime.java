public class CompetitionTime implements Comparable<CompetitionTime> {
    private int minutes;
    private int seconds;
    private int hundredths;

    public CompetitionTime(int minutes, int seconds, int hundredths) {
        setMinutes(minutes);
        setSeconds(seconds);
        setHundredths(hundredths);
    }
    // Getters and setters with validation
    public void setMinutes(int minutes) {
        // Add validation if needed
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        if (seconds >= 0 && seconds < 60) {
            this.seconds = seconds;
        } else {
            throw new IllegalArgumentException("Seconds must be between 0 and 59");
        }
    }

    public void setHundredths(int hundredths) {
        if (hundredths >= 0 && hundredths < 100) {
            this.hundredths = hundredths;
        } else {
            throw new IllegalArgumentException("Hundredths must be between 0 and 99");
        }
    }

    // Implement compareTo for sorting
    @Override
    public int compareTo(CompetitionTime other) {
        // Convert times to a comparable unit, e.g., total seconds
        int totalSecondsThis = this.minutes * 60 + this.seconds;
        int totalSecondsOther = other.minutes * 60 + other.seconds;

        // Compare these values
        return Integer.compare(totalSecondsThis, totalSecondsOther);
    }


    // Override toString to return time in the correct format
    @Override
    public String toString() {
        return String.format("%d:%02d.%02d", minutes, seconds, hundredths);
    }
}