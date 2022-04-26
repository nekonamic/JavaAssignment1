import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    private static void GUI () {
        JFrame frame = new JFrame("运动会建模");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        menu(panel);

        frame.setVisible(true);
    }
    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater(Main::GUI);
    }

    private static Event Find (ArrayList<Event> events, String eventName) {
        for (Event event : events) {
            if (Objects.equals(event.name, eventName)) {
                return event;
            }
        }
        return new Event();
    }

    private static void ReadFile () {

    }

    private static void menu(JPanel panel, int rows) {
        panel.setLayout(null);

        JLabel eventsLabel = new JLabel("Events");
        eventsLabel.setBounds(10, 20, 80, 25);
        panel.add(eventsLabel);

        String[] column = {"类型", "名称", "等级", "时间", "地点", "裁判员"}
        JTable eventsTable = new JTable(,  column);

        JTextArea message = new JTextArea("test");
        message.setBounds(100, 20, 165, 80);
        panel.add(message);

        JButton findButton = new JButton("Find");
        findButton.setBounds(10, 120, 80, 25);
        panel.add(findButton);
    }
}

class Event extends Crew {
    String style;
    String event;
    String level;
    String time;
    String place;
    ArrayList<Athlete> althletes = new ArrayList<Athlete>();
    ArrayList<Referee> referees = new ArrayList<Referee>();
    Event () {
        style = null;
        event = null;
        level = null;
        time = null;
        place = null;
    }
    Event (String s, String e, String l, String t, String p) {
        style = s;
        event = e;
        level = l;
        time = t;
        place = p;
    }
}

class Time {
    int year;
    int month;
    int day;
    int hour;
    int minute;

    Time (int y, int m, int d, int h, int min) {
        year = y;
        month = m;
        day = d;
        hour = h;
        minute = min;
    }

    @Override
    public String toString() {
        return year + "/" + month + "/" + day + " " + hour + ":" + minute;
    }
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