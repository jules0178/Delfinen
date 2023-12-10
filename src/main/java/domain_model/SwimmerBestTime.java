package domain_model;

public class SwimmerBestTime {
    private Swimmer swimmer;
    private CompetitionTime bestTime;

    public SwimmerBestTime(Swimmer swimmer, CompetitionTime bestTime) {
        this.swimmer = swimmer;
        this.bestTime = bestTime;
    }

    public Swimmer getSwimmer() {
        return swimmer;
    }

    public CompetitionTime getBestTime() {
        return bestTime;
    }
}