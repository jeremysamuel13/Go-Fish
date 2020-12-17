import java.util.ArrayList;

public class Game {
    static ArrayList<Card> cardPool = new ArrayList<>();
    static Player p1;
    static Player p2;

    public static void main(String[] args) {
        Card.CardType[] t = Card.CardType.values();
        Card.CardName[] n = Card.CardName.values();

        for (Card.CardName x : n) {
            for (Card.CardType y : t) {
                cardPool.add(new Card(x, y, null));
            }
        }
    }

}
