import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
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


}
