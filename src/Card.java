public class Card {
    enum CardName {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN,
        KING
    }

    enum CardType {
        CLUB, DIAMOND, HEART, SPADE
    }

    private CardName name;
    private CardType type;
    private Player player;

    public Card(CardName name, CardType type, Player player){
        this.name = name;
        this.type = type;
        this.player = player;
    }

    public void setName(CardName name) {
        this.name = name;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public CardName getName() {
        return name;
    }

    public CardType getType() {
        return type;
    }

    public Player getPlayer() {
        return player;
    }
}
