import java.util.*;

public class Game {
    static ArrayList<Card> cardPool = new ArrayList<>();
    static Random rand = new Random();
    static Player player;
    static Player aiPlayer;
    static Scanner scan = new Scanner(System.in);
    static HashMap<Card.CardName, Boolean> aiMap = new HashMap<>();

    public static void main(String[] args) {
        setup();

        boolean active = true;
        String input;

        while(active){

            if(player.getTurn()) {
                System.out.println(player.deckToString());
                printMenu();
                input = scan.nextLine();
                active = playerMove(input);
            }else {
                aiMove();
            }

            player.checkSuits();
            aiPlayer.checkSuits();

            changeTurns();

            if(cardPool.size() == 0){
                determineWinner();
                break;
            }

        }
    }

    private static void determineWinner() {
        if(player.getNumOfSuits() > aiPlayer.getNumOfSuits()){
            System.out.println(player.getName() + " has won the game!");
        }else if(player.getNumOfSuits() == aiPlayer.getNumOfSuits()){
            System.out.println(player.getName() + " and " + aiPlayer.getName() + " have tied!");
        }else{
            System.out.println(aiPlayer.getName() + " has won the game!");
        }

        System.out.println("Score: " + player.getNumOfSuits() + "-" + aiPlayer.getNumOfSuits());
    }

    public static void setup(){
        System.out.print("Enter your name: ");
        player = new Player(scan.nextLine());
        aiPlayer = new Player("AI");

        System.out.println("\nWelcome to Go Fish " + player.getName() + "!");
        System.out.println("\nGenerating game ...");

        Card.CardType[] t = Card.CardType.values();
        Card.CardName[] n = Card.CardName.values();

        //adds all cards to card pool
        for (Card.CardName x : n) {
            for (Card.CardType y : t) {
                cardPool.add(new Card(x, y, null));
            }
        }

        //creates a randomized 7 card deck for both players
        for(int i = 0; i < 8; i++){
            int x = rand.nextInt(cardPool.size());
            player.addToDeck(cardPool.remove(x));
            x = rand.nextInt(cardPool.size());
            aiPlayer.addToDeck(cardPool.remove(x));
        }

        //randomly generates who gets the first turn
        player.setTurn(rand.nextBoolean());
        aiPlayer.setTurn(!player.getTurn());

        System.out.println("\nDone!");

        if(player.getTurn())
            System.out.println("\n" + player.getName() + " gets the first " +
                    "move!");
        else{
            System.out.println("\n" + aiPlayer.getName() + " gets the first move!");
        }
    }

    public static Card fish(){
        int x = rand.nextInt(cardPool.size());
        return cardPool.remove(x);
    }

    public static boolean ask(Card c, Player asker, Player giver){
        boolean x = giver.getDeck().contains(c);
        if(x)
            asker.addToDeck(giver.removeFromDeck(giver.getDeck().indexOf(c)));

        return x;
    }

    public static void printMenu(){
        System.out.print("\n(A) Ask for card\n(T) Take a card from the card " +
                "pool\n(Q) Quit\nPlease select an option: ");
    }


    public static void changeTurns(){
        player.setTurn(!player.getTurn());
        aiPlayer.setTurn(!aiPlayer.getTurn());
    }

    public static boolean playerMove(String input){
        switch (input.toLowerCase()) {
            case "a" -> {
                System.out.println("\n(1) Ace (2) Two (3) Three \n(4) Four " +
                        "(5) Five (6) Six \n(7) Seven (8) Eight (9) Nine \n" +
                        "(10) Ten (11) Jack (12) Queen (13) King");
                System.out.print("\nWhat card would you like to ask for?: ");

                int want = scan.nextInt();
                scan.nextLine();
                Card.CardName[] temp = Card.CardName.values();
                try{
                    Card c = aiPlayer.findCard(temp[want - 1]);
                    if(c != null){
                        System.out.println("\nThe opposing player has a " + c.getName() + ". \nAdding it to your deck.");
                        player.addToDeck(c);
                        aiPlayer.removeFromDeck(c);
                    }else{
                        System.out.println("The opposing player does not have" +
                                " this card.");
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("\nInvalid input, try again");
                    playerMove(input);
                }

            }
            case "t" ->{
                Card f = fish();
                System.out.println("\nYou fished a " + f.getName().toString());
                player.addToDeck(f);
            }
            case "q" -> {
                System.out.println("\nThank you for playing!");
                System.out.println("Shutting down ...");
                return false;
            }
            default -> System.out.println("Invalid input");
        }

        return true;

    }

    public static void aiMove(){
        aiPlayer.getDeckMap().forEach((x, y) ->{
            if (y == 3) {
                if(!aiMap.containsKey(x))
                    aiMap.put(x, false);
            }
        });

        boolean ind = false;

        for (Card.CardName c : aiMap.keySet()) {
            if(!aiMap.get(c)){
                ind = true;
                boolean x = ask(player.findCard(c), aiPlayer, player);
                System.out.println("\n" + aiPlayer.getName() + " asked for a "
                        + c.toString());
                if(x){
                    System.out.println("You have this card. Giving it to " + aiPlayer.getName());
                }else{
                    System.out.println("You don't have this card");
                }
                aiMap.put(c, true);
            }
        }

        if(!ind){
            Card f = fish();
            System.out.println("\n" + aiPlayer.getName() + " fished a card");
            aiPlayer.addToDeck(f);
        }
    }

}
