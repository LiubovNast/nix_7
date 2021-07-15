package consolehelper;

import org.apache.commons.lang3.*;

public class Console {

    public void write(String massege){
        System.out.println(massege);
    }

    public void writeUpperCase(String massege){
        System.out.println(StringUtils.upperCase(massege));
    }
}