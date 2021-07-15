package consolehelper;

import org.apache.commons.lang3.*;
import time.Time;

public class Console {

    public void write(String massege){
        System.out.println(massege);
    }

    public void writeUpperCase(String massege){
        System.out.println(StringUtils.upperCase(massege));
    }

    public void currentTime() {
        Time time = new Time();
        System.out.println("Current time is " + time.getDate());
    }
}