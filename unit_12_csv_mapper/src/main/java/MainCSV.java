import entity.User;
import service.CSVMapper;
import service.CSVParser;

import java.util.List;

public class MainCSV {

    public static void main(String[] args) {
        String[] csvStrings = CSVParser.read(args[0]);

        List<User> list = CSVMapper.getObjects(csvStrings, User.class);
        for (User user : list) {
            System.out.println(user.toString());
        }
    }
}
