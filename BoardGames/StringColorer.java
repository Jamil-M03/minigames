package BoardGames;

/**
 * Utility class for applying ANSI color codes to strings.
 * Supports standard, bright, and background colors.
 * 
 * <p>Usage example:</p>
 * <pre>
 * String coloredText = StringColorer.color("Hello, World!", "red");
 * System.out.println(coloredText);
 * </pre>
 * 
 * <p>Available color options include:</p>
 * <ul>
 *   <li>black</li>
 *   <li>red</li>
 *   <li>green</li>
 *   <li>yellow</li>
 *   <li>blue</li>
 *   <li>purple</li>
 *   <li>cyan</li>
 *   <li>white</li>
 *   <li>bright black</li>
 *   <li>bright red</li>
 *   <li>bright green</li>
 *   <li>bright yellow</li>
 *   <li>bright blue</li>
 *   <li>bright purple</li>
 *   <li>bright cyan</li>
 *   <li>bright white</li>
 *   <li>bg black</li>
 *   <li>bg red</li>
 *   <li>bg green</li>
 *   <li>bg yellow</li>
 *   <li>bg blue</li>
 *   <li>bg purple</li>
 *   <li>bg cyan</li>
 *   <li>bg white</li>
 * </ul>
 */
public class StringColorer {
    private StringColorer(){}

    // TODO: adjust so that it can take an ansi code and use it
    public static String color(String string, String color){
        if (color == null) {
            return "Color cannot be null.";
        }
        if (string == null) {
            return "";
        }

        switch (color.toLowerCase()) {
            case "black":
                return Colors.BLACK + string + Colors.RESET;
            case "red":
                return Colors.RED + string + Colors.RESET;
            case "green":
                return Colors.GREEN + string + Colors.RESET;
            case "yellow":
                return Colors.YELLOW + string + Colors.RESET;
            case "blue":
                return Colors.BLUE + string + Colors.RESET;
            case "purple":
                return Colors.PURPLE + string + Colors.RESET;
            case "cyan":
                return Colors.CYAN + string + Colors.RESET;
            case "white":
                return Colors.WHITE + string + Colors.RESET;

            case "bright black":
                return Colors.BRIGHT_BLACK + string + Colors.RESET;
            case "bright red":
                return Colors.BRIGHT_RED + string + Colors.RESET;
            case "bright green":
                return Colors.BRIGHT_GREEN + string + Colors.RESET;
            case "bright yellow":
                return Colors.BRIGHT_YELLOW + string + Colors.RESET;
            case "bright blue":
                return Colors.BRIGHT_BLUE + string + Colors.RESET;
            case "bright purple":
                return Colors.BRIGHT_PURPLE + string + Colors.RESET;
            case "bright cyan":
                return Colors.BRIGHT_CYAN + string + Colors.RESET;
            case "bright white":
                return Colors.BRIGHT_WHITE + string + Colors.RESET;

            case "bg black":
                return Colors.BG_BLACK + string + Colors.RESET;
            case "bg red":
                return Colors.BG_RED + string + Colors.RESET;
            case "bg green":
                return Colors.BG_GREEN + string + Colors.RESET;
            case "bg yellow":
                return Colors.BG_YELLOW + string + Colors.RESET;
            case "bg blue":
                return Colors.BG_BLUE + string + Colors.RESET;
            case "bg purple":
                return Colors.BG_PURPLE + string + Colors.RESET;
            case "bg cyan":
                return Colors.BG_CYAN + string + Colors.RESET;
            case "bg white":
                return Colors.BG_WHITE + string + Colors.RESET;
            
            default:
                break;
        }

        
        return "Invalid Color: " + color;
    }
}
