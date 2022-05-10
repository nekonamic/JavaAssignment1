import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Event> events = new ArrayList<>();
    private static final ArrayList<Athlete> athletes = new ArrayList<>();
    private static final ArrayList<Referee> referees = new ArrayList<>();

    public static void main (String[] args) {
        ReadEvents();
        ReadAthletes();
        ReadReferees();
        VVelcome();
        Selection();
        System.out.println("------------------------------");
        System.out.println("欢迎使用运动会信息系统");
        System.out.println("输入任意键结束...");
        System.out.println("------------------------------");
        Scanner scanner = new Scanner(System.in);
        scanner.next();
    }

    private static void ReadEvents () {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/events.ser");
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            while (fileInputStream.available() > 0) {
                events.add((Event) in.readObject());
            }
        } catch (IOException | ClassNotFoundException ignored) {}
    }

    private static void ReadAthletes () {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/athletes.ser");
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            while (fileInputStream.available() > 0) {
                athletes.add((Athlete) in.readObject());
            }
        } catch (IOException | ClassNotFoundException ignored) {}

    }

    private static void ReadReferees () {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/referees.ser");
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            while (fileInputStream.available() > 0) {
                referees.add((Referee) in.readObject());
            }
        } catch (IOException | ClassNotFoundException ignored) {}

    }

    private static void WriteEvents () {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/events.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            for (Event event : events) {
                out.writeObject(event);
            }
        } catch (IOException ignored) {}
    }

    private static void WriteAthletes () {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/athletes.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            for (Athlete athlete : athletes) {
                out.writeObject(athlete);
            }
        } catch (IOException ignored) {}

    }

    private static void WriteReferees () {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/referees.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            for (Referee referee : referees) {
                out.writeObject(referee);
            }
        } catch (IOException ignored) {}

    }

    private static void AddEvent () {
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
        System.out.println("添加成功");
        System.out.println("输入任意键返回上级...");
        scanner.next();
        CheckEvents();
    }

    private static void AddAthlete () {
        Scanner scanner = new Scanner(System.in);
        String name, event, grade, sex, score;
        System.out.println("请输入姓名：");
        name = scanner.next();
        System.out.println("请输入项目：");
        event = scanner.next();
        System.out.println("请输入年级：");
        grade = scanner.next();
        System.out.println("请输入性别：");
        sex = scanner.next();
        System.out.println("请输入成绩(百分制)：");
        score = scanner.next();
        athletes.add(new Athlete(name, event, grade, sex, score));
        System.out.println("添加成功");
        System.out.println("输入任意键返回上级...");
        scanner.next();
        CheckEvents();
    }

    private static void AddReferee () {
        Scanner scanner = new Scanner(System.in);
        String name, event;
        System.out.println("请输入姓名：");
        name = scanner.next();
        System.out.println("请输入项目：");
        event = scanner.next();
        referees.add(new Referee(name, event));
        System.out.println("添加成功");
        System.out.println("输入任意键返回上级...");
        scanner.next();
        CheckEvents();
    }

    private static void AddAthleteForEvent () {
        String continueString;
        int i = 1;
        Event temp;
        boolean continueAdd = true;
        System.out.println("请输入需要增加运动员的比赛项目的序号：");
        Scanner scanner = new Scanner(System.in);
        int eventIndex = scanner.nextInt();
        eventIndex--;
        while (continueAdd) {
            System.out.println("------------------------------------------------------------");
            System.out.printf("%-3s %-4s %-3s %-2s %-6s %-2s", "序号", "姓名", "年级", "性别", "比赛项目", "比赛成绩");
            System.out.println("\n------------------------------------------------------------");
            for (Athlete athlete : athletes) {
                if (athlete.event.equals(events.get(eventIndex).event)) {
                    System.out.printf("%-3d %-4s %-3s %-2s %-6s %-2s", i, athlete.name, athlete.grade, athlete.sex, athlete.event, athlete.score);
                    i++;
                }
            }
            System.out.println("\n------------------------------------------------------------");
            System.out.println("请输入需要加入比赛项目的运动员的序号：");
            int addedAthlete = scanner.nextInt();
            addedAthlete--;
            temp = events.get(eventIndex);
            temp.athletes.add(athletes.get(addedAthlete));
            events.set(eventIndex, temp);
            System.out.println("添加成功，是否继续添加？(Y/N)");
            continueString = scanner.next();
            if (! (continueString.equalsIgnoreCase("y") || continueString.equalsIgnoreCase("yes"))) {
                continueAdd = false;
            }
        }
        CheckEvents();
    }

    private static void AddRefereeForEvent () {
        String continueString;
        int i = 1;
        Event temp;
        boolean continueAdd = true;
        System.out.println("请输入需要增加裁判员的比赛项目的序号：");
        Scanner scanner = new Scanner(System.in);
        int eventIndex = scanner.nextInt();
        eventIndex--;
        while (continueAdd) {
            System.out.println("------------------------------");
            System.out.printf("%-3s %-4s %-6s", "序号", "姓名", "项目");
            System.out.println("\n------------------------------");
            for (Referee referee : referees) {
                if (referee.event.equals(events.get(eventIndex).event)){
                    System.out.printf("%-3d %-4s %-6s", i, referee.name, referee.event);
                    i++;
                }
            }
            System.out.println("\n------------------------------");
            System.out.println("请输入需要加入比赛项目的裁判员的序号：");
            int addedReferee = scanner.nextInt();
            addedReferee--;
            temp = events.get(eventIndex);
            temp.referees.add(referees.get(addedReferee));
            events.set(eventIndex, temp);
            System.out.println("添加成功，是否继续添加？(Y/N)");
            continueString = scanner.next();
            if (! (continueString.equalsIgnoreCase("y") || continueString.equalsIgnoreCase("yes"))) {
                continueAdd = false;
            }
        }
        CheckEvents();
    }

    private static void SaveAll () {
        WriteEvents();
        WriteAthletes();
        WriteReferees();
    }

    private static void VVelcome () {
        System.out.println("------------------------------");
        System.out.println("欢迎来到运动会信息系统");
        System.out.println("输入任意键继续...");
        System.out.println("------------------------------");
        Scanner scanner = new Scanner(System.in);
        scanner.next();
    }

    private static void Selection () {
        System.out.println("------------------------------");
        System.out.println("1. 查看比赛项目");
        System.out.println("2. 查看运动员");
        System.out.println("3. 查看裁判员");
        System.out.println("4. 增加比赛项目");
        System.out.println("5. 增加运动员");
        System.out.println("6. 增加裁判员");
        System.out.println("7. 退出");
        System.out.println("------------------------------");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> CheckEvents();
            case 2 -> CheckAthletes();
            case 3 -> CheckReferees();
            case 4 -> AddEvent();
            case 5 -> AddAthlete();
            case 6 -> AddReferee();
            case 7 -> SaveAll();
        }
    }

    private static void CheckEvents () {
        int i = 1;
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.printf("%-3s %-2s %-6s %-4s %-16s %-6s %-24s %-72s", "序号", "类型", "名称", "等级", "时间", "地点", "裁判员", "运动员");
        System.out.println("\n------------------------------------------------------------------------------------------");
        for (Event event : events) {
            System.out.printf("%-3d %-2s %-6s %-4s %-16s %-6s %-24s %-72s", i, event.style, event.event, event.level, event.time, event.place, GetReferees(event.referees), GetAthletes(event.athletes));
            i++;
        }
        System.out.println("\n------------------------------------------------------------------------------------------");
        System.out.println("1. 按类型排序");
        System.out.println("2. 按名称排序");
        System.out.println("3. 按等级排序");
        System.out.println("4. 按时间排序");
        System.out.println("5. 按地点排序");
        System.out.println("6. 查询指定比赛结果");
        System.out.println("7. 增加运动员");
        System.out.println("8. 增加裁判员");
        System.out.println("9. 增加比赛项目");
        System.out.println("0. 返回上级");
        System.out.println("------------------------------------------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> EventsSortByStyle();
            case 2 -> EventsSortByEvent();
            case 3 -> EventsSortByLevel();
            case 4 -> EventsSortByTime();
            case 5 -> EventSortByPlace();
            case 6 -> CheckInformation();
            case 7 -> AddAthleteForEvent();
            case 8 -> AddRefereeForEvent();
            case 9 -> AddEvent();
            case 0 -> Selection();
        }
    }

    private static void CheckAthletes () {
        int i = 1;
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-4s %-3s %-2s %-6s %-2s", "姓名", "年级", "性别", "比赛项目", "比赛成绩");
        System.out.println("\n------------------------------------------------------------");
        for (Athlete athlete : athletes) {
            System.out.printf("%-3d %-4s %-3s %-2s %-6s %-2s", i, athlete.name, athlete.grade, athlete.sex, athlete.event, athlete.score);
            i++;
        }
        System.out.println("\n------------------------------------------------------------");
        System.out.println("1. 按姓名排序");
        System.out.println("2. 按年级排序");
        System.out.println("3. 按性别排序");
        System.out.println("4. 按比赛项目排序");
        System.out.println("5. 按比赛成绩排序");
        System.out.println("6. 增加运动员");
        System.out.println("7. 返回上级");
        System.out.println("------------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> AthletesSortByName();
            case 2 -> AthletesSortByGrade();
            case 3 -> AthletesSortBySex();
            case 4 -> AthletesSortByEvent();
            case 5 -> AthletesSortByScore();
            case 6 -> AddAthlete();
            case 7 -> Selection();
        }
    }

    private static void CheckReferees () {
        int i = 0;
        System.out.println("------------------------------");
        System.out.printf("%-4s %-6s", "姓名", "项目");
        System.out.println("\n------------------------------");
        for (Referee referee : referees) {
            System.out.printf("%-3d %-4s %-6s", i, referee.name, referee.event);
            i++;
        }
        System.out.println("\n------------------------------");
        System.out.println("1. 按姓名排序");
        System.out.println("2. 按项目排序");
        System.out.println("3. 增加裁判员");
        System.out.println("4. 返回上级");
        System.out.println("------------------------------");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> RefereesSortByName();
            case 2 -> RefereesSortByEvent();
            case 3 -> AddReferee();
            case 4 -> Selection();
        }
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

    private static void EventsSortByStyle () {
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
        CheckEvents();
    }

    private static void EventsSortByEvent () {
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
        CheckEvents();
    }

    private static void EventsSortByLevel () {
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
        CheckEvents();
    }

    private static void EventsSortByTime () {
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
        CheckEvents();
    }

    private static void EventSortByPlace () {
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
        CheckEvents();
    }

    private static void AthletesSortByName () {
        Athlete temp;
        for (int i=0; i<athletes.size() - 1; i++) {
            for (int j=i+1; j<athletes.size(); j++) {
                if (athletes.get(i).name.compareTo(athletes.get(j).name) > 0) {
                    temp = athletes.get(j);
                    athletes.set(j, athletes.get(j + 1));
                    athletes.set(j + 1, temp);
                }
            }
        }
        CheckAthletes();
    }

    private static void AthletesSortByGrade () {
        Athlete temp;
        for (int i=0; i<athletes.size() - 1; i++) {
            for (int j=i+1; j<athletes.size(); j++) {
                if (athletes.get(i).grade.compareTo(athletes.get(j).grade) > 0) {
                    temp = athletes.get(j);
                    athletes.set(j, athletes.get(j + 1));
                    athletes.set(j + 1, temp);
                }
            }
        }
        CheckAthletes();
    }

    private static void AthletesSortBySex () {
        Athlete temp;
        for (int i=0; i<athletes.size() - 1; i++) {
            for (int j=i+1; j<athletes.size(); j++) {
                if (athletes.get(i).sex.compareTo(athletes.get(j).sex) > 0) {
                    temp = athletes.get(j);
                    athletes.set(j, athletes.get(j + 1));
                    athletes.set(j + 1, temp);
                }
            }
        }
        CheckAthletes();
    }

    private static void AthletesSortByEvent () {
        Athlete temp;
        for (int i=0; i<athletes.size() - 1; i++) {
            for (int j=i+1; j<athletes.size(); j++) {
                if (athletes.get(i).event.compareTo(athletes.get(j).event) > 0) {
                    temp = athletes.get(j);
                    athletes.set(j, athletes.get(j + 1));
                    athletes.set(j + 1, temp);
                }
            }
        }
        CheckAthletes();
    }

    private static void AthletesSortByScore () {
        Athlete temp;
        for (int i=0; i<athletes.size() - 1; i++) {
            for (int j=i+1; j<athletes.size(); j++) {
                if (athletes.get(i).score.compareTo(athletes.get(j).score) > 0) {
                    temp = athletes.get(j);
                    athletes.set(j, athletes.get(j + 1));
                    athletes.set(j + 1, temp);
                }
            }
        }
        CheckAthletes();
    }

    private static void RefereesSortByName () {
        Referee temp;
        for (int i=0; i<referees.size() - 1; i++) {
            for (int j=i+1; j<referees.size(); j++) {
                if (referees.get(i).name.compareTo(referees.get(j).name) > 0) {
                    temp = referees.get(j);
                    referees.set(j, referees.get(j + 1));
                    referees.set(j + 1, temp);
                }
            }
        }
        CheckReferees();
    }

    private static void RefereesSortByEvent () {
        Referee temp;
        for (int i=0; i<referees.size() - 1; i++) {
            for (int j=i+1; j<referees.size(); j++) {
                if (referees.get(i).event.compareTo(referees.get(j).event) > 0) {
                    temp = referees.get(j);
                    referees.set(j, referees.get(j + 1));
                    referees.set(j + 1, temp);
                }
            }
        }
        CheckReferees();
    }

    private static void CheckInformation () {
        System.out.println("请输入需要查询的比赛项目的序号：");
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        index--;
        System.out.printf("类型：%-2s \n名称：%-3s \n等级：%-2s \n时间:%-12s \n地点：%-6s \n运动员：%-12s \n裁判员：%-36s \n结果：%-12s", events.get(index).style, events.get(index).event, events.get(index).level, events.get(index).time, events.get(index).place, GetReferees(events.get(index).referees), GetAthletes(events.get(index).athletes), GetResult(events.get(index).athletes));
        CheckEvents();
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

    private static String GetResult (ArrayList<Athlete> athletes) {
        Athlete bestAthlete = athletes.get(0);
        for (Athlete athlete : athletes) {
            if (athlete.event.compareTo(bestAthlete.score) > 0) {
                bestAthlete = athlete;
            }
        }
        return "全场最佳：" + bestAthlete.name + "， 成绩：" + bestAthlete.score;
    }
}

class Event extends Time implements Serializable {
    String style;
    String event;
    String level;
    Time time;
    String place;
    ArrayList<Athlete> athletes = new ArrayList<>();
    ArrayList<Referee> referees = new ArrayList<>();
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