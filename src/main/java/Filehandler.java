import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
public class Filehandler {

    public ArrayList<Member> loadData() throws IOException {
        ArrayList<Member> memberArrayList = new ArrayList<>();

        Scanner scanner = new Scanner(new File("Members"));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            memberArrayList.add(new Member(parts[0],
                    parts[1],
                    parts[2],
                    Integer.parseInt(parts[3]),
                    parts[4],
                    parts[5],
                    Boolean.parseBoolean(parts[6]),
                    Boolean.parseBoolean(parts[7]),
                    parts[8]));
        }
        return memberArrayList;
    }
    public void saveMembers(ArrayList<Member> members){
        try {
            PrintStream output = new PrintStream("Members");
            for (Member member : members) {
                output.println(member.getName() + ";" + member.getSurName() + ";" +
                member.getEmail() + ";" + member.getPhoneNumber() + ";" +
                        member.getDateOfBirth() + ";" + member.getDateJoined() + ";" +
                        member.getIsActive() + ";" + member.getIsCompetitor() + ";" + member.getMemberID());
            }
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Result> loadResults() throws IOException {
        ArrayList<Result> resultsList = new ArrayList<>();
        Scanner scanner = new Scanner(new File("Results"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");

            String[] timeParts = parts[4].split("[:.]");
            int minutes = Integer.parseInt(timeParts[0]);
            int seconds = Integer.parseInt(timeParts[1]);
            int hundredths = Integer.parseInt(timeParts[2]);

            CompetitionTime competitionTime = new CompetitionTime(minutes, seconds, hundredths);

            resultsList.add(new Result(parts[0],
                    parts[1],
                    LocalDate.parse(parts[2]),
                    Enum.valueOf(Result.SwimStyle.class, parts[3].toUpperCase()),
                    competitionTime,
                    Boolean.parseBoolean(parts[5])));
                }
            return resultsList;
     }
     public void saveResults(ArrayList<Result> resultsList){
        try {
            PrintStream output = new PrintStream("Results");
            for (Result result : resultsList) {
                output.println(result.getMemberID() + ";" + result.getEventName() + ";" + result.getDate() + ";" + result.getStyle() + ";" +
                        result.getTime() + ";" +  result.isPractice());
            }
            output.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
     }
}