import java.util.ArrayList;

public class Main {
    public static void main (String[] args) {}

}

class Event extends Crew {
    String style;
    String event;
    String level;
    String time;
    String place;
    ArrayList<Athlete> althletes = new ArrayList<Athlete>();
    ArrayList<Referee> referees = new ArrayList<Referee>();
    Event (String s, String e, String l, String t, String p) {
        style = s;
        event = e;
        level = l;
        time = t;
        place = p;
    }
    void Find
}

class Crew {
    String name;
    String event;
}

class Athlete extends Crew {
    String class__;
    String sex;
    String score;
}

class Referee extends Crew {
}