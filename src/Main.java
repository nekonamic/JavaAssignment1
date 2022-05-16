import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    private static final ArrayList<Event> events = new ArrayList<>(); //Save data of events
    private static final ArrayList<Athlete> athletes = new ArrayList<>(); //Save data of athletes
    private static final ArrayList<Referee> referees = new ArrayList<>(); //Save data of referees
    private static final ArrayList<String > menTracks = new ArrayList<>(); //Save name of men's track events
    private static final ArrayList<String > womenTracks = new ArrayList<>(); //Save name of women's track events
    private static final ArrayList<String > menFields = new ArrayList<>(); //Save name of men's field events
    private static final ArrayList<String > womenFields = new ArrayList<>();// Save name of women's field events

    public static void main (String[] args) throws ParseException {
        // Initialize various kinds of events
        menTracks.addAll(Arrays.asList("men's 100m", "men's 1000m", "men's 3000m", "men's 5000m", "men's relay 4*100m", "men's relay 4*400m"));
        womenTracks.addAll(Arrays.asList("woman's 100m", "woman's 1000m", "woman's 3000m", "woman's 5000m", "woman's relay 4*100m", "woman's relay 4*400m"));
        menFields.addAll(Arrays.asList("men's long jump", "men's shot put", "men's javelin", "men's discus", "men's hammer"));
        womenFields.addAll(Arrays.asList("woman's long jump", "woman's shot put", "woman's javelin", "woman's discus", "woman's hammer"));
        // Read data from files
        ReadEvents();
        ReadAthletes();
        ReadReferees();
        // Show welcome interface
        VVelcome();
        // Show selection interface
        Selection();
        // Show farewell interface
        System.out.println("------------------------------");
        System.out.println("Thank You For You Use Sports Competition information system");
        System.out.println("Enter any key to continue...");
        System.out.println("------------------------------");
        Scanner scanner = new Scanner(System.in);
        scanner.next();
    }

    // Read events data from file
    private static void ReadEvents () {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/events.ser");
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            while (fileInputStream.available() > 0) {
                events.add((Event) in.readObject());
            }
        } catch (IOException | ClassNotFoundException ignored) {}
    }

    // Read athletes data from file
    private static void ReadAthletes () {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/athletes.ser");
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            while (fileInputStream.available() > 0) {
                athletes.add((Athlete) in.readObject());
            }
        } catch (IOException | ClassNotFoundException ignored) {}

    }

    // Read referees data from file
    private static void ReadReferees () {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/referees.ser");
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            while (fileInputStream.available() > 0) {
                referees.add((Referee) in.readObject());
            }
        } catch (IOException | ClassNotFoundException ignored) {}

    }

    // Write events data from file
    private static void WriteEvents () {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/events.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            for (Event event : events) {
                out.writeObject(event);
            }
        } catch (IOException ignored) {}
    }

    // Write athletes data from file
    private static void WriteAthletes () {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/athletes.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            for (Athlete athlete : athletes) {
                out.writeObject(athlete);
            }
        } catch (IOException ignored) {}

    }

    // Write referees data from file
    private static void WriteReferees () {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/referees.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            for (Referee referee : referees) {
                out.writeObject(referee);
            }
        } catch (IOException ignored) {}

    }

    // Add event for arraylist events
    private static void AddEvent () throws ParseException {
        Scanner scanner = new Scanner(System.in);
        String type = "", event = "", level, place;
        String sex = "";
        int i = 1;
        System.out.println("Type of the event: ");
        System.out.println("1. Track");
        System.out.println("2. Field");
        System.out.println("Enter the index of the type: ");
        switch (scanner.nextInt()) {
            case 1 -> type = "Track";
            case 2 -> type = "Field";
        }
        System.out.println("Sex of the event: ");
        System.out.println("1. Men");
        System.out.println("2. Women");
        System.out.println("Enter the index of the sex: ");
        switch (scanner.nextInt()) {
            case 1 -> sex = "Men";
            case 2 -> sex = "Women";
        }
        System.out.println("Enter the index of the event: ");
        if (sex.equals("Men") && type.equals("Track")) {
            for (String _event_ : menTracks) {
                System.out.println(i + " " + _event_);
                i++;
            }
            event = menTracks.get(scanner.nextInt() - 1);
        } else if (sex.equals("Men") && type.equals("Field")) {
            for (String _event_ : menFields) {
                System.out.println(i + " " + _event_);
                i++;
            }
            event = menFields.get(scanner.nextInt() - 1);
        } else if (sex.equals("Women") && type.equals("Track")) {
            for (String _event_ : womenTracks) {
                System.out.println(i + " " + _event_);
                i++;
            }
            event = womenTracks.get(scanner.nextInt() - 1);
        } else if (sex.equals("Women") && type.equals("Field")) {
            for (String _event_ : womenFields) {
                System.out.println(i + " " + _event_);
                i++;
            }
            event = womenFields.get(scanner.nextInt() - 1);
        }
        System.out.println("Enter level: ");
        level = scanner.next();
        System.out.println("Enter place: ");
        place = scanner.next();
        System.out.println("Enter date (yyyy-MM-dd/HH:mm:ss): ");
        Date date = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss").parse(new Scanner(System.in).next());
        events.add(new Event(type, event, level, date, place));
        System.out.println("Added successfully");
        System.out.println("Enter any key to return to upper level...");
        scanner.next();
        Selection();
    }

    // Add athlete for arraylist athletes
    private static void AddAthlete () throws ParseException {
        Scanner scanner = new Scanner(System.in);
        int i = 1;
        String name, event = "", grade, sex = "", score, type = "";
        System.out.println("Enter name: ");
        name = scanner.next();
        System.out.println("Type of the event: ");
        System.out.println("1. Track");
        System.out.println("2. Field");
        System.out.println("Enter the index of the type: ");
        switch (scanner.nextInt()) {
            case 1 -> type = "Track";
            case 2 -> type = "Field";
        }
        System.out.println("Sex of the event: ");
        System.out.println("1. Men");
        System.out.println("2. Women");
        System.out.println("Enter the index of the sex: ");
        switch (scanner.nextInt()) {
            case 1 -> sex = "Men";
            case 2 -> sex = "Women";
        }
        System.out.println("Enter the index of the event: ");
        if (sex.equals("Men") && type.equals("Track")) {
            for (String _event_ : menTracks) {
                System.out.println(i + " " + _event_);
                i++;
            }
            event = menTracks.get(scanner.nextInt() - 1);
        } else if (sex.equals("Men") && type.equals("Field")) {
            for (String _event_ : menFields) {
                System.out.println(i + " " + _event_);
                i++;
            }
            event = menFields.get(scanner.nextInt() - 1);
        } else if (sex.equals("Women") && type.equals("Track")) {
            for (String _event_ : womenTracks) {
                System.out.println(i + " " + _event_);
                i++;
            }
            event = womenTracks.get(scanner.nextInt() - 1);
        } else if (sex.equals("Women") && type.equals("Field")) {
            for (String _event_ : womenFields) {
                System.out.println(i + " " + _event_);
                i++;
            }
            event = womenFields.get(scanner.nextInt() - 1);
        }
        System.out.println("Enter grade: ");
        grade = scanner.next();
        System.out.println("Enter score (percentile): ");
        score = scanner.next();
        athletes.add(new Athlete(name, event, grade, sex, score));
        System.out.println("Added successfully");
        System.out.println("Enter any key to return to upper level...");
        scanner.next();
        Selection();
    }

    // Add referee for arraylist referees
    private static void AddReferee () throws ParseException {
        int i = 1;
        Scanner scanner = new Scanner(System.in);
        String name, event = "", type = "", sex = "";
        System.out.println("Enter name: ");
        name = scanner.next();
        System.out.println("Type of the event: ");
        System.out.println("1. Track");
        System.out.println("2. Field");
        System.out.println("Enter the index of the type: ");
        switch (scanner.nextInt()) {
            case 1 -> type = "Track";
            case 2 -> type = "Field";
        }
        System.out.println("Sex of the event: ");
        System.out.println("1. Men");
        System.out.println("2. Women");
        System.out.println("Enter the index of the sex: ");
        switch (scanner.nextInt()) {
            case 1 -> sex = "Men";
            case 2 -> sex = "Women";
        }
        System.out.println("Enter the index of the event: ");
        if (sex.equals("Men") && type.equals("Track")) {
            for (String _event_ : menTracks) {
                System.out.println(i + " " + _event_);
                i++;
            }
            event = menTracks.get(scanner.nextInt() - 1);
        } else if (sex.equals("Men") && type.equals("Field")) {
            for (String _event_ : menFields) {
                System.out.println(i + " " + _event_);
                i++;
            }
            event = menFields.get(scanner.nextInt() - 1);
        } else if (sex.equals("Women") && type.equals("Track")) {
            for (String _event_ : womenTracks) {
                System.out.println(i + " " + _event_);
                i++;
            }
            event = womenTracks.get(scanner.nextInt() - 1);
        } else if (sex.equals("Women") && type.equals("Field")) {
            for (String _event_ : womenFields) {
                System.out.println(i + " " + _event_);
                i++;
            }
            event = womenFields.get(scanner.nextInt() - 1);
        }
        referees.add(new Referee(name, event));
        System.out.println("Added successfully");
        System.out.println("Enter any key to return to upper level...");
        scanner.next();
        Selection();
    }

    // Add athlete from athletes arraylist events for event in arraylist events
    private static void AddAthleteForEvent () throws ParseException {
        String continueString;
        int i = 1;
        int j = 0;
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
                    System.out.printf("%-3s %-12s %-12s %-8s %-24s %-6s\n", i, athlete.name, athlete.grade, athlete.sex, athlete.event, athlete.score);
                    i++;
                }
            }
            System.out.println("------------------------------------------------------------");
            System.out.println("Enter the index of the athlete who needs to join the event: ");
            int addedAthlete = scanner.nextInt();
            i = 1;
            for (Athlete athlete : athletes) {
                if (athlete.event.equals(events.get(eventIndex).event)) {
                    if (i == addedAthlete) {
                        temp = events.get(eventIndex);
                        temp.athletes.add(athletes.get(j));
                        events.set(eventIndex, temp);
                        break;
                    }
                    i++;
                }
                j++;
            }
            System.out.println("Added successfully, whether to continue adding? (Y/N)");
            continueString = scanner.next();
            if (! (continueString.equalsIgnoreCase("y") || continueString.equalsIgnoreCase("yes"))) {
                continueAdd = false;
            }
        }
        CheckEvents();
    }

    // Add referee from arraylist referees events for event in arraylist events
    private static void AddRefereeForEvent () throws ParseException {
        String continueString;
        int i = 1;
        int j = 0;
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
                    System.out.printf("%-3s %-12s %-24s\n", i, referee.name, referee.event);
                    i++;
                }
            }
            System.out.println("------------------------------");
            System.out.println("Enter the index of the referee who needs to join the event: ");
            int addedReferee = scanner.nextInt();
            i = 1;
            for (Referee referee : referees) {
                if (referee.event.equals(events.get(eventIndex).event)){
                    if (i == addedReferee) {
                        temp = events.get(eventIndex);
                        temp.referees.add(referees.get(j));
                        events.set(eventIndex, temp);
                    }
                    i++;
                }
                j++;
            }
            System.out.println("Added successfully, whether to continue adding? (Y/N)");
            continueString = scanner.next();
            if (! (continueString.equalsIgnoreCase("y") || continueString.equalsIgnoreCase("yes"))) {
                continueAdd = false;
            }
        }
        CheckEvents();
    }

    //  Call functions to save all arraylist data to file
    private static void SaveAll () {
        WriteEvents();
        WriteAthletes();
        WriteReferees();
    }

    // Welcome interface
    private static void VVelcome () {
        System.out.println("------------------------------");
        System.out.println("Welcome Sports Competition information system");
        System.out.println("Enter any key to continue...");
        System.out.println("------------------------------");
        Scanner scanner = new Scanner(System.in);
        scanner.next();
    }

    // Select operation interface
    private static void Selection () throws ParseException {
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

    // Show all event in arraylist events
    private static void CheckEvents () throws ParseException {
        int i = 1;
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.printf("%-8s %-12s %-24s %-8s %-36s %-24s %-24s %-72s", "index", "type", "event", "level", "time", "place", "referees", "athletes");
        System.out.println("\n------------------------------------------------------------------------------------------");
        for (Event event : events) {
            System.out.printf("%-8s %-12s %-24s %-8s %-36s %-24s %-24s %-72s\n", i, event.type, event.event, event.level, event.date, event.place, GetReferees(event.referees), GetAthletes(event.athletes));
            i++;
        }
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("1. Sort by type");
        System.out.println("2. Sort by event");
        System.out.println("3. Sort by level");
        System.out.println("4. Sort by place");
        System.out.println("5. Check result of event");
        System.out.println("6. Events by time period");
        System.out.println("7. Add athlete for  the event");
        System.out.println("8. Add referee for the event");
        System.out.println("9. Add event");
        System.out.println("0. return to upper level");
        System.out.println("------------------------------------------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> EventsSortByType();
            case 2 -> EventsSortByEvent();
            case 3 -> EventsSortByLevel();
            case 4 -> EventSortByPlace();
            case 5 -> CheckInformation();
            case 6 -> EventsByTimePeriod();
            case 7 -> AddAthleteForEvent();
            case 8 -> AddRefereeForEvent();
            case 9 -> AddEvent();
            case 0 -> Selection();
        }
    }

    // Show all athlete in arraylist athletes
    private static void CheckAthletes () throws ParseException {
        int i = 1;
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-8s %-12s %-12s %-8s %-24s %-6s", "index", "name", "grade", "sex", "event", "score");
        System.out.println("\n------------------------------------------------------------");
        for (Athlete athlete : athletes) {
            System.out.printf("%-8s %-12s %-12s %-8s %-24s %-6s\n", i, athlete.name, athlete.grade, athlete.sex, athlete.event, athlete.score);
            i++;
        }
        System.out.println("------------------------------------------------------------");
        System.out.println("1. Sort by name");
        System.out.println("2. Sort by grade");
        System.out.println("3. Sort by sex");
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

    // Show all referee in arraylist referees
    private static void CheckReferees () throws ParseException {
        int i = 0;
        System.out.println("------------------------------");
        System.out.printf("%-8s %-12s %-24s", "index", "name", "event");
        System.out.println("\n------------------------------");
        for (Referee referee : referees) {
            System.out.printf("%-8d %-12s %-24s\n", i, referee.name, referee.event);
            i++;
        }
        System.out.println("------------------------------");
        System.out.println("1. Sort by name");
        System.out.println("2. Sort by event");
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

    // Sort event in arraylist events by type of event
    private static void EventsSortByType () throws ParseException {
        Event temp;
        for (int i=0; i<events.size() - 1; i++) {
            for (int j=i+1; j<events.size() - 1; j++) {
                if (events.get(i).type.compareTo(events.get(j).type) > 0) {
                    temp = events.get(j);
                    events.set(j, events.get(j + 1));
                    events.set(j + 1, temp);
                }
            }
        }
        CheckEvents();
    }

    // Sort event in arraylist events by name of event
    private static void EventsSortByEvent () throws ParseException {
        Event temp;
        for (int i=0; i<events.size() - 1; i++) {
            for (int j=i+1; j<events.size() - 1; j++) {
                if (events.get(i).event.compareTo(events.get(j).event) > 0) {
                    temp = events.get(j);
                    events.set(j, events.get(j + 1));
                    events.set(j + 1, temp);
                }
            }
        }
        CheckEvents();
    }

    // Sort event in arraylist events by level of event
    private static void EventsSortByLevel () throws ParseException {
        Event temp;
        for (int i=0; i<events.size() - 1; i++) {
            for (int j=i+1; j<events.size() - 1; j++) {
                if (events.get(i).level.compareTo(events.get(j).level) > 0) {
                    temp = events.get(j);
                    events.set(j, events.get(j + 1));
                    events.set(j + 1, temp);
                }
            }
        }
        CheckEvents();
    }

    // Sort event in arraylist events by place of event
    private static void EventSortByPlace () throws ParseException {
        Event temp;
        for (int i=0; i<events.size() - 1; i++) {
            for (int j=i+1; j<events.size() - 1; j++) {
                if (events.get(i).place.compareTo(events.get(j).place) > 0) {
                    temp = events.get(j);
                    events.set(j, events.get(j + 1));
                    events.set(j + 1, temp);
                }
            }
        }
        CheckEvents();
    }

    // Sort athlete in arraylist athletes by name of athlete
    private static void AthletesSortByName () throws ParseException {
        Athlete temp;
        for (int i=0; i<athletes.size() - 1; i++) {
            for (int j=i+1; j<athletes.size() - 1; j++) {
                if (athletes.get(i).name.compareTo(athletes.get(j).name) > 0) {
                    temp = athletes.get(j);
                    athletes.set(j, athletes.get(j + 1));
                    athletes.set(j + 1, temp);
                }
            }
        }
        CheckAthletes();
    }

    // Sort athlete in arraylist athletes by grade of athlete
    private static void AthletesSortByGrade () throws ParseException {
        Athlete temp;
        for (int i=0; i<athletes.size() - 1; i++) {
            for (int j=i+1; j<athletes.size() - 1; j++) {
                if (athletes.get(i).grade.compareTo(athletes.get(j).grade) > 0) {
                    temp = athletes.get(j);
                    athletes.set(j, athletes.get(j + 1));
                    athletes.set(j + 1, temp);
                }
            }
        }
        CheckAthletes();
    }

    // Sort athlete in arraylist athletes by sex of athlete
    private static void AthletesSortBySex () throws ParseException {
        Athlete temp;
        for (int i=0; i<athletes.size() - 1; i++) {
            for (int j=i+1; j<athletes.size() - 1; j++) {
                if (athletes.get(i).sex.compareTo(athletes.get(j).sex) > 0) {
                    temp = athletes.get(j);
                    athletes.set(j, athletes.get(j + 1));
                    athletes.set(j + 1, temp);
                }
            }
        }
        CheckAthletes();
    }

    // Sort athlete in arraylist athletes by event of athlete
    private static void AthletesSortByEvent () throws ParseException {
        Athlete temp;
        for (int i=0; i<athletes.size() - 1; i++) {
            for (int j=i+1; j<athletes.size() - 1; j++) {
                if (athletes.get(i).event.compareTo(athletes.get(j).event) > 0) {
                    temp = athletes.get(j);
                    athletes.set(j, athletes.get(j + 1));
                    athletes.set(j + 1, temp);
                }
            }
        }
        CheckAthletes();
    }

    // Sort athlete in arraylist athletes by score of athlete
    private static void AthletesSortByScore () throws ParseException {
        Athlete temp;
        for (int i=0; i<athletes.size() - 1; i++) {
            for (int j=i+1; j<athletes.size() - 1; j++) {
                if (athletes.get(i).score.compareTo(athletes.get(j).score) > 0) {
                    temp = athletes.get(j);
                    athletes.set(j, athletes.get(j + 1));
                    athletes.set(j + 1, temp);
                }
            }
        }
        CheckAthletes();
    }

    // Sort referee in arraylist referees by name of referee
    private static void RefereesSortByName () throws ParseException {
        Referee temp;
        for (int i=0; i<referees.size() - 1; i++) {
            for (int j=i+1; j<referees.size() - 1; j++) {
                if (referees.get(i).name.compareTo(referees.get(j).name) > 0) {
                    temp = referees.get(j);
                    referees.set(j, referees.get(j + 1));
                    referees.set(j + 1, temp);
                }
            }
        }
        CheckReferees();
    }

    // Sort referee in arraylist referees by event of referee
    private static void RefereesSortByEvent () throws ParseException {
        Referee temp;
        for (int i=0; i<referees.size() - 1; i++) {
            for (int j=i+1; j<referees.size() - 1; j++) {
                if (referees.get(i).event.compareTo(referees.get(j).event) > 0) {
                    temp = referees.get(j);
                    referees.set(j, referees.get(j + 1));
                    referees.set(j + 1, temp);
                }
            }
        }
        CheckReferees();
    }

    // Show detail information about the event, including result of event
    private static void CheckInformation () throws ParseException {
        System.out.println("Enter the index of the event that needs to be found: ");
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        index--;
        System.out.printf("Type: %-8s \nEvent name: %-12s \nLevel: %-8s \nTime: %-24s \nPlace: %-12s \nReferees: %-24s \nAthletes: %-72s \nResult: %-24s \n", events.get(index).type, events.get(index).event, events.get(index).level, events.get(index).date, events.get(index).place, GetReferees(events.get(index).referees), GetAthletes(events.get(index).athletes), GetResult(events.get(index).athletes));
        System.out.println("Check successfully");
        System.out.println("Enter any key to continue...");
        scanner.next();
        CheckEvents();
    }

    // Show events in the custom time period
    private static void EventsByTimePeriod () throws ParseException {
        System.out.println("Enter the start date (yyyy-MM-dd/HH:mm:ss): ");
        Date startDate = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss").parse(new Scanner(System.in).next());
        System.out.println("Enter the end date (yyyy-MM-dd/HH:mm:ss): ");
        Date endDate = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss").parse(new Scanner(System.in).next());
        int index = 1;
        System.out.println("Competitions during this period are: ");
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.printf("%-8s %-12s %-24s %-8s %-36s %-24s %-24s %-72s", "index", "type", "event", "level", "time", "place", "referees", "athletes");
        System.out.println("\n------------------------------------------------------------------------------------------");
        for (Event event : events) {
            if (inPeriod(event.date, startDate, endDate)) {
                System.out.printf("%-8s %-12s %-24s %-8s %-36s %-24s %-24s %-72s\n", index, event.type, event.event, event.level, event.date, event.place, GetReferees(event.referees), GetAthletes(event.athletes));
                index++;
            }
        }
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Enter any key to return to upper level ...");
        Scanner scanner = new Scanner(System.in);
        scanner.next();
        CheckEvents();
    }

    // Estimate Whether the input event is in the custom time period
    public static boolean inPeriod(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        return date.after(begin) && date.before(end);
    }

    //Formatting output String of name of athlete in arraylist
    private static String GetAthletes (ArrayList<Athlete> inputAthletes) {
        StringBuilder returnString = new StringBuilder();
        for (Athlete athlete : inputAthletes) {
            returnString.append(athlete.name).append("  ");
        }
        return returnString.toString();
    }

    //Formatting output String of name of referee in arraylist
    private static String GetReferees (ArrayList<Referee> inputReferees) {
        StringBuilder returnString = new StringBuilder();
        for (Referee referee : inputReferees) {
            returnString.append(referee.name).append("  ");
        }
        return returnString.toString();
    }

    //Formatting output String of result of event by athlete in arraylist
    private static String GetResult (ArrayList<Athlete> inputAthletes) {
        Athlete bestAthlete = inputAthletes.get(0);
        for (Athlete athlete : inputAthletes) {
            if (athlete.event.compareTo(bestAthlete.score) > 0) {
                bestAthlete = athlete;
            }
        }
        return "\n\tChampion：" + bestAthlete.name + "\n\tScore：" + bestAthlete.score;
    }
}

// Define class event, including various information of event
// Implement Serializable interface to save information more easily
class Event implements Serializable {
    String type;
    String event;
    String level;
    Date date;
    String place;
    ArrayList<Athlete> athletes = new ArrayList<>();
    ArrayList<Referee> referees = new ArrayList<>();

    Event (String s, String e, String l, Date d, String p) {
        type = s;
        event = e;
        level = l;
        date = d;
        place = p;
    }
}

// Define class athlete, including various information of athlete
// Implement Serializable interface to save information more easily
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

// Define class referee, including various information of referee
// Implement Serializable interface to save information more easily
class Referee implements Serializable {
    String name;
    String event;

    Referee (String n, String e) {
        name = n;
        event = e;
    }
}