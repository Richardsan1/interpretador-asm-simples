public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("> ");
            String[] input = scanner.nextLine().split(" ");
            switch(input[0].toLowerCase()) {
                case "load"-> {
                    System.out.println("todo: implement load");
                }
                case "ins" -> {
                    REPL repl = new REPL();
                    System.out.println(Arrays.copyOfRange(input, 1, input.length-1).toString());
                    // repl.validate(Arrays.copyOfRange(input, 1, input.length-1).toString());
                }
                default -> {
                    System.out.println("Invalid command");
                }
            }
        }
    }
}