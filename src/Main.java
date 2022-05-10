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
        System.out.println("Welcome Sports Competition information system");
        System.out.println("Enter any key to continue...");
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
        String type, event, level, place;
        int year, month, day, hour, minute;
        System.out.println("Enter type: ");
        type = scanner.next();
        System.out.println("Enter event name: ");
        event = scanner.next();
        System.out.println("Enter level: ");
        level = scanner.next();
        System.out.println("Enter place: ");
        place = scanner.next();
        System.out.println("Enter year: ");
        year = scanner.nextInt();
        System.out.println("Enter month: ");
        month = scanner.nextInt();
        System.out.println("Enter day: ");
        day = scanner.nextInt();
        System.out.println("Enter hour: ");
        hour = scanner.nextInt();
        System.out.println("Enter minute: ");
        minute = scanner.nextInt();
        events.add(new Event(type, event, level, new Time(year, month, day, hour, minute), place));
        System.out.println("Added successfully");
        System.out.println("Enter any key to return to upper level...");
        scanner.next();
        Selection();
    }

    private static void AddAthlete () {
        Scanner scanner = new Scanner(System.in);
        String name, event, grade, sex, score;
        System.out.println("Enter name: ");
        name = scanner.next();
        System.out.println("Enter event: ");
        event = scanner.next();
        System.out.println("Enter grade: ");
        grade = scanner.next();
        System.out.println("Enter sex: ");
        sex = scanner.next();
        System.out.println("Enter score (percentile): ");
        score = scanner.next();
        athletes.add(new Athlete(name, event, grade, sex, score));
        System.out.println("Added successfully");
        System.out.println("Enter any key to return to upper level...");
        scanner.next();
        Selection();
    }

    private static void AddReferee () {
        Scanner scanner = new Scanner(System.in);
        String name, event;
        System.out.println("Enter name: ");
        name = scanner.next();
        System.out.println("Enter event: ");
        event = scanner.next();
        referees.add(new Referee(name, event));
        System.out.println("Added successfully");
        System.out.println("Enter any key to return to upper level...");
        scanner.next();
        Selection();
    }

    private static void AddAthleteForEvent () {
        String continueString;
        int i = 1;
        Event temp;
        boolean continueAdd = true;
        System.out.println("Enter the index of the event that needs to add athlete: ");
        Scanner scanner = new Scanner(System.in);
        int eventIndex = scanner.nextInt();
        eventIndex--;
        while (continueAdd) {
            System.out.println("------------------------------------------------------------");
            System.out.printf("%-8s %-12s %-12s %-8s %-24s %-6s", "index", "name", "grade", "sex", "event", "score");
            System.out.println("\n------------------------------------------------------------");
            for (Athlete athlete : athletes) {
                if (athlete.event.equals(events.get(eventIndex).event)) {
                    System.out.printf("%-3s %-12s %-12s %-8s %-24s %-6s", i, athlete.name, athlete.grade, athlete.sex, athlete.event, athlete.score);
                    i++;
                }
            }
            System.out.println("\n------------------------------------------------------------");
            System.out.println("Enter the index of the athlete who needs to join the event: ");
            int addedAthlete = scanner.nextInt();
            addedAthlete--;
            temp = events.get(eventIndex);
            temp.athletes.add(athletes.get(addedAthlete));
            events.set(eventIndex, temp);
            System.out.println("Added successfully, whether to continue adding? (Y/N)");
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
        System.out.println("Enter the index of the event that needs to add referee: ");
        Scanner scanner = new Scanner(System.in);
        int eventIndex = scanner.nextInt();
        eventIndex--;
        while (continueAdd) {
            System.out.println("------------------------------");
            System.out.printf("%-8s %-12s %-24s", "index", "name", "event");
            System.out.println("\n------------------------------");
            for (Referee referee : referees) {
                if (referee.event.equals(events.get(eventIndex).event)){
                    System.out.printf("%-3s %-12s %-24s", i, referee.name, referee.event);
                    i++;
                }
            }
            System.out.println("\n------------------------------");
            System.out.println("Enter the index of the referee who needs to join the event: ");
            int addedReferee = scanner.nextInt();
            addedReferee--;
            temp = events.get(eventIndex);
            temp.referees.add(referees.get(addedReferee));
            events.set(eventIndex, temp);
            System.out.println("Added successfully, whether to continue adding? (Y/N)");
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
        System.out.println("Welcome Sports Competition information system");
        System.out.println("Enter any key to continue...");
        System.out.println("------------------------------");
        Scanner scanner = new Scanner(System.in);
        scanner.next();
    }

    private static void Selection () {
        System.out.println("------------------------------");
        System.out.println("1. Check events");
        System.out.println("2. Check athletes");
        System.out.println("3. Check referees");
        System.out.println("4. Add event");
        System.out.println("5. Add athlete");
        System.out.println("6. Add referee");
        System.out.println("7. Exit");
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
        System.out.printf("%-8s %-12s %-12s %-8s %-24s %-24s %-24s %-72s", "index", "type", "event", "grade", "time", "place", "referees", "athletes");
        System.out.println("\n------------------------------------------------------------------------------------------");
        for (Event event : events) {
            System.out.printf("%-8s %-12s %-12s %-8s %-24s %-24s %-24s %-72s", i, event.type, event.event, event.level, event.time, event.place, GetReferees(event.referees), GetAthletes(event.athletes));
            i++;
        }
        System.out.println("\n------------------------------------------------------------------------------------------");
        System.out.println("1. Sort by type");
        System.out.println("2. Sort by event");
        System.out.println("3. Sort by level");
        System.out.println("4. Sort by time");
        System.out.println("5. Sort by place");
        System.out.println("6. Check result of event");
        System.out.println("7. Add athlete");
        System.out.println("8. Add referee");
        System.out.println("9. Add event");
        System.out.println("0. return to upper level");
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
        System.out.printf("%-8s %-12s %-12s %-8s %-24s %-6s", "index", "name", "grade", "sex", "event", "score");
        System.out.println("\n------------------------------------------------------------");
        for (Athlete athlete : athletes) {
            System.out.printf("%-8s %-12s %-12s %-8s %-24s %-6s", i, athlete.name, athlete.grade, athlete.sex, athlete.event, athlete.score);
            i++;
        }
        System.out.println("\n------------------------------------------------------------");
        System.out.println("1. Sort by name");
        System.out.println("2. Sort by grade");
        System.out.println("3. SOrt by sex");
        System.out.println("4. Sort by event");
        System.out.println("5. Sort by score");
        System.out.println("6. Add athlete");
        System.out.println("7. return to upper level");
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
        System.out.printf("%-8s %-12s %-24s", "index", "name", "event");
        System.out.println("\n------------------------------");
        for (Referee referee : referees) {
            System.out.printf("%-8d %-12s %-24s", i, referee.name, referee.event);
            i++;
        }
        System.out.println("\n------------------------------");
        System.out.println("1. SOrt by name");
        System.out.println("2. SOrt by event");
        System.out.println("3. Add referee");
        System.out.println("4. return to upper level");
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
                if (events.get(i).type.compareTo(events.get(j).type) > 0) {
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
        System.out.println("Enter the index of the event that needs to be found: ");
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        index--;
        System.out.printf("Type: %-8s \nEvent name: %-12s \nLevel: %-8s \nTime: %-24s \nPlace: %-12s \nReferees: %-24s \nAthletes: %-72s \nResult: %-24s \n", events.get(index).type, events.get(index).event, events.get(index).level, events.get(index).time, events.get(index).place, GetReferees(events.get(index).referees), GetAthletes(events.get(index).athletes), GetResult(events.get(index).athletes));
        System.out.println("Check successfully");
        System.out.println("Enter any key to continue...");
        scanner.next();
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
        return "\n\tChampion：" + bestAthlete.name + "\n\tScore：" + bestAthlete.score;
    }
}

class Event extends Time implements Serializable {
    String type;
    String event;
    String level;
    Time time;
    String place;
    ArrayList<Athlete> athletes = new ArrayList<>();
    ArrayList<Referee> referees = new ArrayList<>();
    Event (String s, String e, String l, Time t, String p) {
        super(t);
        type = s;
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