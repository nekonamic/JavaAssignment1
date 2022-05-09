import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        ArrayList<Event> events = new ArrayList<>();
        ArrayList<Athlete> athletes = new ArrayList<>();
        ArrayList<Referee> referees = new ArrayList<>();
        ReadEvents(events);
        ReadAthletes(athletes);
        ReadReferees(referees);
        VVelcome();
    }

    private static Event Find (ArrayList<Event> events, String eventName) {
        for (Event event : events) {
            if (Objects.equals(event.name, eventName)) {
                return event;
            }
        }
        return new Event();
    }

    private static void ReadEvents (ArrayList<Event> events) {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/events.ser");
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            while (fileInputStream.available() > 0) {
                events.add((Event) in.readObject());
            }
        } catch (IOException | ClassNotFoundException ignored) {}
    }

    private static void ReadAthletes (ArrayList<Athlete> athletes) {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/athletes.ser");
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            while (fileInputStream.available() > 0) {
                athletes.add((Athlete) in.readObject());
            }
        } catch (IOException | ClassNotFoundException ignored) {}

    }

    private static void ReadReferees (ArrayList<Referee> referees) {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/referees.ser");
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            while (fileInputStream.available() > 0) {
                referees.add((Referee) in.readObject());
            }
        } catch (IOException | ClassNotFoundException ignored) {}

    }

    private static void WriteEvents (ArrayList<Event> events) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/events");
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            for (Event event : events) {
                out.writeObject(event);
            }
        } catch (IOException ignored) {}
    }

    private static void WriteAthletes (ArrayList<Athlete> athletes) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/athletes");
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            for (Athlete athlete : athletes) {
                out.writeObject(athlete);
            }
        } catch (IOException ignored) {}

    }

    private static void WriteReferees (ArrayList<Referee> referees) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/referees");
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            for (Referee referee : referees) {
                out.writeObject(referee);
            }
        } catch (IOException ignored) {}

    }

    private static void VVelcome () {
        System.out.println("------------------------------");
        System.out.println("欢迎来到运动会消息系统");
        System.out.println("按任意键继续...");
        System.out.println("------------------------------");
        Scanner scanner = new Scanner(System.in);
        scanner.next();
    }

    private static void Selection () {
        System.out.println("------------------------------");
        System.out.println("1. 查看比赛项目");
        System.out.println("2. 查看运动员");
        System.out.println("3. 查看裁判员");
        System.out.println("------------------------------");
    }

    private static void CheckEvents (ArrayList<Event> events) {
        System.out.println("------------------------------");
        System.out.println("类型   名称   等级   时间   地点   裁判员   运动员");
        for (Event event : events) {
            System.out.println(event.style + "   " + event.event + "   " + event.level + "   " + event.time + "   " + event.place + "   " + event.referees + "   " + event.althletes);
        }
        System.out.println("------------------------------");
    }

    private static void CheckAthletes (ArrayList<Athlete> athletes) {
        System.out.println("------------------------------");
        System.out.println("姓名   年级   性别   比赛项目   比赛成绩");
        for (Athlete athlete : athletes) {
            System.out.println(athlete.name + "   " + athlete.grade + "   " + athlete.sex + "   " + athlete.event + "   " + athlete.score + "   ");
        }
        System.out.println("------------------------------");
    }

    private static void CheckReferees (ArrayList<Referee> referees) {
        System.out.println("------------------------------");
        System.out.println("姓名 项目");
        for (Referee referee : referees) {
            System.out.println(referee.name + "   " + referee.event + "   ");
        }
        System.out.println("------------------------------");
    }
}

class Event extends Time implements Serializable {
    String style;
    String event;
    String level;
    Time time;
    String place;
    ArrayList<Athlete> althletes = new ArrayList<Athlete>();
    ArrayList<Referee> referees = new ArrayList<Referee>();
    Event () {
        super();
        style = null;
        event = null;
        level = null;
        time = null;
        place = null;
    }
    Event (String s, String e, String l, Time t, String p) {
        style = s;
        event = e;
        level = l;
        time = t;
        place = p;
    }
}

class Athlete implements Serializable{
    String name;
    String event;
    String grade;
    String sex;
    String score;
}

class Referee implements Serializable {
    String name;
    String event;
}

class Time implements Serializable{
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