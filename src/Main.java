import java.io.*;
import java.util.ArrayList;
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
        Selection(events, athletes, referees);
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

    private static void AddEvent (ArrayList<Event> events) {
        Scanner scanner = new Scanner(System.in);
        String style, event, level, place;
        int year, month, day, hour, minute;
        System.out.println("请输入类型：");
        style = scanner.next();
        System.out.println("请输入项目：");
        event = scanner.next();
        System.out.println("请输入等级：");
        level = scanner.next();
        System.out.println("请输入地点：");
        place = scanner.next();
        System.out.println("请输入年份：");
        year = scanner.nextInt();
        System.out.println("请输入月份：");
        month = scanner.nextInt();
        System.out.println("请输入日份：");
        day = scanner.nextInt();
        System.out.println("请输入小时：");
        hour = scanner.nextInt();
        System.out.println("请输入分钟：");
        minute = scanner.nextInt();
        events.add(new Event(style, event, level, new Time(year, month, day, hour, minute), place));
    }

    private static void AddAthlete (ArrayList<Athlete> athletes) {
        Scanner scanner = new Scanner(System.in);
        String name, event, grade, sex, score;
        System.out.println("请输入姓名：");
        name = scanner.next();
        System.out.println("请输入项目：");
        event = scanner.next();
        System.out.println("请输入成绩：");
        grade = scanner.next();
        System.out.println("请输入性别：");
        sex = scanner.next();
        System.out.println("请输入成绩：");
        score = scanner.next();
        athletes.add(new Athlete(name, event, grade, sex, score));
    }

    private static void AddReferee (ArrayList<Referee> referees) {
        Scanner scanner = new Scanner(System.in);
        String name, event;
        System.out.println("请输入姓名：");
        name = scanner.next();
        System.out.println("请输入项目：");
        event = scanner.next();
        referees.add(new Referee(name, event));
    }

    private static void VVelcome () {
        System.out.println("------------------------------");
        System.out.println("欢迎来到运动会信息系统");
        System.out.println("按任意键继续...");
        System.out.println("------------------------------");
        Scanner scanner = new Scanner(System.in);
        scanner.next();
    }

    private static void Selection (ArrayList<Event> events, ArrayList<Athlete> athletes, ArrayList<Referee> referees) {
        System.out.println("------------------------------");
        System.out.println("1. 查看比赛项目");
        System.out.println("2. 查看运动员");
        System.out.println("3. 查看裁判员");
        System.out.println("4. 增加比赛项目");
        System.out.println("5. 增加运动员");
        System.out.println("6. 增加裁判员");
        System.out.println("------------------------------");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> CheckEvents(events);
            case 2 -> CheckAthletes(athletes);
            case 3 -> CheckReferees(referees);
            case 4 -> AddEvent(events);
            case 5 -> AddAthlete(athletes);
            case 6 -> AddReferee(referees);
        }
    }

    private static void CheckEvents (ArrayList<Event> events) {
        int i = 1;
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.printf("%-3s %-2s %-3s %-2s %-12s %-6s %-12s %-36s", "序号", "类型", "名称", "等级", "时间", "地点", "裁判员", "运动员");
        System.out.println("\n------------------------------------------------------------------------------------------");
        for (Event event : events) {
            System.out.printf("%-3d %-2s %-3s %-2s %-12s %-6s %-12s %-36s", i + 1, event.style, event.event, event.level, event.time, event.place, event.referees, event.athletes);
        }
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("1. 按类型排序");
        System.out.println("2. 按名称排序");
        System.out.println("3. 按等级排序");
        System.out.println("4. 按时间排序");
        System.out.println("5. 按地点排序");
        System.out.println("6. 查询指定比赛结果");
        System.out.println("------------------------------------------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> EventSortByStyle(events);
            case 2 -> EventSortByEvent(events);
            case 3 -> EventSortByLevel(events);
            case 4 -> EventsSortByTime(events);
            case 5 -> EventSortByPlace(events);
            case 6 -> CheckInformation(events);
        }
    }

    private static void CheckAthletes (ArrayList<Athlete> athletes) {
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-4s %-3s %-1s %-6s %-2s", "姓名", "年级", "性别", "比赛项目", "比赛成绩");
        System.out.println("\n------------------------------------------------------------");
        for (Athlete athlete : athletes) {
            System.out.printf("%-4s %-3s %-1s %-6s %-2s", athlete.name, athlete.grade, athlete.sex, athlete.event, athlete.score);
        }
        System.out.println("\n------------------------------------------------------------");
    }

    private static void CheckReferees (ArrayList<Referee> referees) {
        System.out.println("------------------------------");
        System.out.printf("%-4s %-6s", "姓名", "项目");
        System.out.println("\n------------------------------");
        for (Referee referee : referees) {
            System.out.printf("%-4s %-6s", referee.name, referee.event);
        }
        System.out.println("\n------------------------------");
    }

    private static boolean FormerLatterThanLatter (Event formerEvent,Event laterEvent) {
        if (formerEvent.year != laterEvent.year) {
            return formerEvent.year > laterEvent.year;
        } else if (formerEvent.month != laterEvent.month) {
            return formerEvent.month > laterEvent.month;
        } else if (formerEvent.day != laterEvent.day) {
            return formerEvent.day > laterEvent.day;
        } else if (formerEvent.hour != laterEvent.hour) {
            return formerEvent.hour > laterEvent.hour;
        } else {
            return formerEvent.minute > laterEvent.minute;
        }
    }

    private static void EventSortByStyle (ArrayList<Event> events) {
        Event temp;
        for (int i=0; i<events.size() - 1; i++) {
            for (int j=i+1; j<events.size(); j++) {
                if (events.get(i).style.compareTo(events.get(j).style) > 0) {
                    temp = events.get(j);
                    events.set(j, events.get(j + 1));
                    events.set(j + 1, temp);
                }
            }
        }
    }

    private static void EventSortByEvent (ArrayList<Event> events) {
        Event temp;
        for (int i=0; i<events.size() - 1; i++) {
            for (int j=i+1; j<events.size(); j++) {
                if (events.get(i).event.compareTo(events.get(j).event) > 0) {
                    temp = events.get(j);
                    events.set(j, events.get(j + 1));
                    events.set(j + 1, temp);
                }
            }
        }
    }

    private static void EventSortByLevel (ArrayList<Event> events) {
        Event temp;
        for (int i=0; i<events.size() - 1; i++) {
            for (int j=i+1; j<events.size(); j++) {
                if (events.get(i).level.compareTo(events.get(j).level) > 0) {
                    temp = events.get(j);
                    events.set(j, events.get(j + 1));
                    events.set(j + 1, temp);
                }
            }
        }
    }

    private static void EventSortByPlace (ArrayList<Event> events) {
        Event temp;
        for (int i=0; i<events.size() - 1; i++) {
            for (int j=i+1; j<events.size(); j++) {
                if (events.get(i).place.compareTo(events.get(j).place) > 0) {
                    temp = events.get(j);
                    events.set(j, events.get(j + 1));
                    events.set(j + 1, temp);
                }
            }
        }
    }

    private static void EventsSortByTime (ArrayList<Event> events) {
        Event temp;
        for (int i=0; i<events.size() - 1; i++) {
            for (int j=0; j<events.size() - 1 - i; j++) {
                if (FormerLatterThanLatter(events.get(j), events.get(j + 1))) {
                    temp = events.get(j);
                    events.set(j, events.get(j + 1));
                    events.set(j + 1, temp);
                }
            }
        }
    }

    private static void CheckInformation (ArrayList<Event> events) {
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        index--;
        System.out.printf("类型：%-2s \n名称：%-3s \n等级：%-2s \n时间:%-12s \n地点：%-6s \n运动员：%-12s \n裁判员：%-36s \n结果：", events.get(index).style, events.get(index).event, events.get(index).level, events.get(index).time, events.get(index).place, GetReferees(events.get(index).referees), GetAthletes(events.get(index).athletes));
    }

    private static String GetAthletes (ArrayList<Athlete> athletes) {
        StringBuilder returnString = new StringBuilder();
        for (Athlete athlete : athletes) {
            returnString.append(athlete.name);
        }
        return returnString.toString();
    }

    private static String GetReferees (ArrayList<Referee> referees) {
        StringBuilder returnString = new StringBuilder();
        for (Referee referee : referees) {
            returnString.append(referee.name);
        }
        return returnString.toString();
    }
}

class Event extends Time implements Serializable {
    String style;
    String event;
    String level;
    Time time;
    String place;
    ArrayList<Athlete> athletes = new ArrayList<Athlete>();
    ArrayList<Referee> referees = new ArrayList<Referee>();
    Event (String s, String e, String l, Time t, String p) {
        super(t);
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

    Athlete (String n, String e, String g, String s, String sc) {
        name = n;
        event = e;
        grade = g;
        sex = s;
        score = sc;
    }
}

class Referee implements Serializable {
    String name;
    String event;

    Referee (String n, String e) {
        name = n;
        event = e;
    }
}

class Time implements Serializable{
    int year;
    int month;
    int day;
    int hour;
    int minute;

    Time (Time time) {
        year = time.year;
        month = time.month;
        day = time.day;
        hour = time.hour;
        minute = time.minute;
    }

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