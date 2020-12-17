import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> deck = new ArrayList<>();
    boolean turn;
    private ArrayList<Card> pairs = new ArrayList<>();

    public Player(String name){
        this.name = name;
    }

    public void addToDeck(Card card){
        deck.add(card);
    }

    public Card removeFromDeck(int index){
        return deck.remove(index);
    }

    public boolean removeFromDeck(Card c){
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

    public ArrayList<Card> getPairs() {
        return pairs;
    }

    public boolean checkPairs(){
        for (Card c: deck) {

        }

        return true;
    }
}
