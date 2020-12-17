import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class Player {
    private String name;
    private ArrayList<Card> deck = new ArrayList<>();
    private boolean turn;
    private HashMap<Card.CardName, Integer> deckMap = new HashMap<>();
    private ArrayList<Card.CardName> suits = new ArrayList<>();
    private int numOfSuits = 0;

    public Player(String name){
        this.name = name;
    }

    public void addToDeck(Card card){
        card.setPlayer(this);
        deck.add(card);

        if(deckMap.containsKey(card.getName())){
            deckMap.put(card.getName(), deckMap.get(card.getName()) + 1);
        }else{
            deckMap.put(card.getName(), 1);
        }
    }

    public Card removeFromDeck(int index){
        Card x = deck.remove(index);
        deckMap.put(x.getName(), deckMap.get(x.getName()) -1);
        return x;
    }

    public boolean removeFromDeck(Card c){
        deckMap.put(c.getName(), deckMap.get(c.getName()) -1);
        return deck.remove(c);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public ArrayList<Card.CardName> getSuits() {
        return suits;
    }

    public HashMap<Card.CardName, Integer> getDeckMap() {
        return deckMap;
    }

    public int getNumOfSuits() {
        return numOfSuits;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public boolean getTurn() {
        return turn;
    }

    public boolean checkSuits(){
        AtomicBoolean q = new AtomicBoolean(false);
        deckMap.forEach((x, y) ->{
            if (y == 4) {
                q.set(true);
                System.out.println(name + " completed the " + x.toString() +
                        " suit.");
                suits.add(x);
                deckMap.put(x, -1);
                numOfSuits++;
            }
        });

        return q.get();

    }

    public String deckToString(){
        String top;
        StringBuilder middle = new StringBuilder("| ");
        String bottom;

        for (Card c : deck) {
            middle.append(c.getName()).append("-").
                    append(c.getType()).append(" | ");
        }

        top = "-".repeat(middle.length()-1);
        bottom = "-".repeat(middle.length()-1);

        return "\nPlayer: " + name + "\n" + "Number of Suits: " + numOfSuits +
                "\n" + top + "\n" + middle + "\n" + bottom;

    }

    public Card findCard(Card.CardName cn){
        for (Card c : deck) {
            if(c.getName() == cn)
                return c;
        }

        return null;
    }

}
