import entity.Table;
import entity.User;
import service.*;

import java.util.List;

public class MainCSV {

    public static void main(String[] args) {
        CSVParser parser = new CSVParser();
        Table table = parser.read(args[0]);

        CSVMapper mapper = new CSVMapper();
        List<User> list = mapper.getObjects(table, User.class);
        for (User user : list) {
            System.out.println(user.toString());
        }
    }
}
