package BoardGames;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class WordBank {

    static final Scanner input = new Scanner(System.in);
    static final Random rand = new Random();

    // consider moving to its own class

    // Themes
    static final ArrayList<String> themes = new ArrayList<>(
            Arrays.asList("Animals", "Food", "Nature", "Professions", "Places", "Colors", "Weather", "Vehicles",
                    "Sports", "Music", "Inventions"));

    // Animals
    public static final ArrayList<String> animals = new ArrayList<>(Arrays.asList(
            "elephant", "kangaroo", "butterfly", "dolphin", "peacock",
            "penguin", "octopus", "hedgehog", "flamingo", "narwhal", "giraffe", "platypus", "chameleon", "seahorse",
            "raccoon", "walrus", "panda", "lynx", "koala", "bear"));

    // Food
    public static final ArrayList<String> food = new ArrayList<>(Arrays.asList(
            "spaghetti", "chocolate", "pineapple", "burger", "cinnamon",
            "cucumber", "avocado", "croissant", "marshmallow", "tortilla", "sushi", "lasagna", "cheesecake", "mango",
            "blueberry", "peanut", "donut", "popcorn", "pretzel", "pizza", "shawarma", "wings"));
    // Nature
    public static final ArrayList<String> nature = new ArrayList<>(Arrays.asList(
            "volcano", "waterfall", "desert", "hurricane", "blizzard",
            "canyon", "glacier", "savanna", "aurora", "meadow", "lagoon", "cliff", "forest", "oasis", "coral", "dune",
            "rainbow", "mountain", "hill", "river", "sea"));

    // Professions
    public static final ArrayList<String> professions = new ArrayList<>(Arrays.asList(
            "firefighter", "scientist", "musician", "architect", "librarian",
            "astronaut", "detective", "engineer", "journalist", "veterinarian", "chef", "pilot", "teacher",
            "pharmacist", "carpenter", "gardener", "actor", "mechanic", "programmer", "designer", "doctor",
            "researcher", "scientist"));

    // Places
    public static final ArrayList<String> places = new ArrayList<>(Arrays.asList(
            "library", "museum", "stadium", "airport", "castle",
            "factory", "lighthouse", "hospital", "laboratory", "marketplace", "celestial dome", "aquarium", "cave",
            "amusement park", "train station", "ski resort", "temple", "harbor", "zoo", "greenhouse",
            "nature reserve"));

    // Colors
    public static final ArrayList<String> colors = new ArrayList<>(Arrays.asList(
            "red", "blue", "green", "yellow", "black", "white", "pink", "orange", "brown", "gray",
            "crimson", "turquoise", "amber", "lavender", "charcoal", "magenta", "jade", "coral",
            "sapphire", "maroon"));

    // Weather
    public static final ArrayList<String> weather = new ArrayList<>(Arrays.asList(
            "thunderstorm", "fog", "blizzard", "lightning", "tornado", "mist", "hailstorm",
            "dew", "cyclone", "monsoon"));

    // Vehicles
    public static final ArrayList<String> vehicles = new ArrayList<>(Arrays.asList(
            "motorcycle", "submarine", "helicopter", "rocket", "airplane", "bus", "skateboard",
            "hot air balloon", "carriage", "tricycle", "car", "racecar"));

    // Sports
    public static final ArrayList<String> sports = new ArrayList<>(Arrays.asList(
            "basketball", "soccer", "tennis", "baseball", "swimming", "golf", "volleyball",
            "badminton", "rugby", "skiing"));
            
    // Music
    public static final ArrayList<String> music = new ArrayList<>(Arrays.asList(
            "guitar", "piano", "violin", "drums", "saxophone", "trumpet", "harp", "accordion",
            "cello", "flute", "drums", "bass", "bass guitar", "electric guitar", "drum kit"));
            
    // Inventions
    public static final ArrayList<String> inventions = new ArrayList<>(Arrays.asList(
            "telephone", "lightbulb", "computer", "camera", "air conditioner", "microwave",
            "printing press", "telescope", "radio", "internet"));

    public static String getWord() {
        displayDifficultyMenu();
        int diff = getDifficulty();
        String theme = "";

        if (diff == 0) {
            theme = selectTheme();
        } else if (diff > 0) {
            theme = selectRandomTheme();
        }

        String word = getRandomWord(theme);

        if (diff == 1){
            int counter = 0;
            while (word.length() > 5){
                if (counter == 10){
                    theme = selectRandomTheme();
                    counter = 0;
                }
                word = getRandomWord(theme);
                counter++;
                // if (word.length() < 6){
                //     break;
                // }
            }
        }

        if (diff == 3){
            int counter = 0;
            while (!word.contains(" ")){
                if (counter == 10){
                    theme = selectRandomTheme();
                    counter = 0;
                }
                word = getRandomWord(theme);
                counter++;
                // if (word.contains(" ")){
                //     break;
                // }
            }
        }
        return word;
    }


    public static String getRandomWord(String theme) {
        switch (theme) {
            case "Animals":
                return animals.get(rand.nextInt(animals.size()));

            case "Food":
                return food.get(rand.nextInt(food.size()));

            case "Nature":
                return nature.get(rand.nextInt(nature.size()));

            case "Professions":
                return professions.get(rand.nextInt(professions.size()));

            case "Places":
                return places.get(rand.nextInt(places.size()));
            
            case "Colors":
                return colors.get(rand.nextInt(colors.size()));
            
            case "Weather":
                return weather.get(rand.nextInt(weather.size()));

            case "Vehicles":
                return vehicles.get(rand.nextInt(vehicles.size()));

            case "Sports":
                return sports.get(rand.nextInt(sports.size()));

            case "Music":
                return music.get(rand.nextInt(music.size()));

            case "Inventions":
                return inventions.get(rand.nextInt(inventions.size()));

            default:
                return "";
        }
    }

    public static void displayDifficultyMenu() {
        Designer.displayDifficultySelect("Difficulty",
                new ArrayList<>(Arrays.asList(StringColorer.color("super easy :D  ", "blue"), StringColorer.color("easy :)        ", "bright green"),
                        StringColorer.color("medium :|      ", "yellow"),
                        StringColorer.color("hard >:(       ", "red"))));
    }

    public static int getDifficulty() {
        while (true) {
            String answer = input.nextLine();
            if (answer.equalsIgnoreCase("easy") || answer.equalsIgnoreCase("1")) {
                return 0;
            } else if (answer.equalsIgnoreCase("easy") || answer.equalsIgnoreCase("2")) {
                return 1;
            } else if (answer.equalsIgnoreCase("medium") || answer.equalsIgnoreCase("3")) {
                return 2;
            } else if (answer.equalsIgnoreCase("hard") || answer.equalsIgnoreCase("4")) {
                return 3;
            } else {
                System.out.println("Invalid difficulty: " + answer);
                try {
                    Thread.sleep(400);
                } catch (InterruptedException ie) {
                    System.out.println(ie.getMessage());
                }
                Designer.clearLastXLines(2);
            }
        }
    }

    public static String selectTheme() {
        displayThemes();
        // TODO: add to inputhandeler class the method getInputInRange(int min, int max);
        while (true) {
            String answer = input.nextLine();
            try {
                if (Integer.valueOf(answer) > themes.size() || Integer.valueOf(answer) < 1) {
                    System.out.println("Invalid input: " + answer);
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException ie) {
                        System.out.println(ie.getMessage());
                    }
                    Designer.clearLastXLines(2);
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Invalid input: " + answer);
                try {
                    Thread.sleep(400);
                } catch (InterruptedException ie) {
                    System.out.println(ie.getMessage());
                }
                Designer.clearLastXLines(2);
                continue;
            }
            return themes.get(Integer.valueOf(answer) - 1);
        }
    }

    public static void displayThemes() {
        Designer.displayMenu("  Themes   ", themes);
    }

    public static String selectRandomTheme() {
        int randomThemeNumber = rand.nextInt(themes.size());
        return themes.get(randomThemeNumber);
    }

    public static void main(String[] args) {
        getWord();
        System.out.println(selectTheme());
    }

    public static String getWordTheme(String word) {
        if (animals.contains(word)) {
            return "Animals";
        }

        if (food.contains(word)) {
            return "Food";
        }

        if (nature.contains(word)) {
            return "Nature";
        }

        if (professions.contains(word)) {
            return "Professions";
        }

        if (places.contains(word)) {
            return "Places";
        }

        if (colors.contains(word)){
            return "Colors";
        }

        if (weather.contains(word)){
            return "Wheater";
        }

        if (vehicles.contains(word)){
            return "Vehicles";
        }

        if (sports.contains(word)){
            return "Sports";
        }

        
        if (music.contains(word)){
            return "Music";
        }
        
        if (inventions.contains(word)){
            return "Inventions";
        }

        return "Misc";
    }
}
