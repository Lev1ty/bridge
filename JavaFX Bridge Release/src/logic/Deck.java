package logic;

/**
 * Created by adamx on 1/8/2016.
 */
public class Deck {
    public Card deck[];

    public Deck() {
        initDeck ( );
        Shuffle ( );
        assignDirection ( );
    }

    public Deck(int ndirection, Deck parentDeck) {
        initDeck (ndirection, parentDeck);
        sortBySuit ( );
        sortByRank ( );
    }

    public static Deck[] seperateBySuit(Deck masterDeck) {
//        Deck deck[] = new Deck[5];
//        for (int i = 0; i < 5; i++) {
//            deck[i] = new Deck ( );
//            deck[i].deck = deck[i].resize (deck[i].deck, 0);
//        }
//        for (int i = 1; i <= 4; ++i)
//            for (int j = 0; j < masterDeck.deck.length; ++j)
//                if (masterDeck.deck[j].nsuit == i - 1) deck[i].push_back (masterDeck.deck[j]);
//        if (bid.nsuit >= 0 && bid.nsuit <= 3) {
//            deck[0] = deck[bid.nsuit];
//            for (int i = bid.nsuit + 1; i < 5; i++) deck[i - 1] = deck[i];
//        } else for (int i = 0; i < 4; i++) deck[i] = deck[i + 1];
//        Deck retDeck[] = new Deck[4];
//        for (int i = 0; i < 4; i++) retDeck[i] = deck[i];
//        return retDeck;
        Deck deck[] = new Deck[4];
        for (int i = 0; i < 4; i++) {
            deck[i] = new Deck ( );
            deck[i].deck = new Card[0];
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < masterDeck.deck.length; j++) {
                if (masterDeck.deck[j].prioritysuit == i) deck[i].push_back (masterDeck.deck[j]);
            }
        }
        return deck;
    }

    public static Card[] sortBySuit(Card deck[]) {
        try {
            for (int i = 0; i < deck.length - 1; i++)
                for (int j = 0; j < deck.length - 1; j++) {
                    if (deck[j].prioritysuit > deck[j + 1].prioritysuit) {
                        Card temp = deck[j];
                        deck[j] = deck[j + 1];
                        deck[j + 1] = temp;
                    }
                }
        } catch (Exception e) {
            System.out.println ("sortbysuit exception");
        }
        return deck;
    }

    public void push_back(Card card) {
        deck = resize (deck, deck.length + 1);
        deck[deck.length - 1] = card;
    }

    public Card remove(int nvalue) {
        int pos = -1;
        Card card = null;
        for (int i = 0; i < deck.length; i++)
            if (deck[i].nvalue == nvalue) {
                pos = i;
                break;
            }
        if (pos != -1) {
            card = deck[pos];
            if (pos > 0) for (int i = pos + 1; i < deck.length; i++)
                deck[i - 1] = deck[i];
            else for (int i = 1; i < deck.length; i++)
                deck[i - 1] = deck[i];
            deck = resize (deck, deck.length - 1);
        } else System.out.println ("No card found that nvalue matches parameter");
        return card;
    }

    public void printDeck() {
        for (Card c :
                deck)
            c.Print ( );
    }

    private void sortBySuit() {
//        for (int i = 0; i < size; i++) {
//            int lowPos = i;
//            for (int j = lowPos + 1; j < size; j++) {
//                if (deck[j].nsuit < deck[lowPos].nsuit) {
//                    lowPos = j;
//                }
//            }
//            Card temp = deck[lowPos];
//            deck[lowPos] = deck[i];
//            deck[i] = temp;
//        }
        for (int i = 0; i < deck.length - 1; i++)
            for (int j = 0; j < deck.length - 1; j++)
                if (deck[j].nsuit > deck[j + 1].nsuit) {
                    Card temp = deck[j];
                    deck[j] = deck[j + 1];
                    deck[j + 1] = temp;
                }
    }

    private void sortByRank() {
        for (int i = 0; i < deck.length - 1; i++)
            for (int j = 0; j < deck.length - 1; j++)
                if (deck[j].nsuit == deck[j + 1].nsuit &&
                        deck[j].nrank < deck[j + 1].nrank) {
                    Card temp = deck[j];
                    deck[j] = deck[j + 1];
                    deck[j + 1] = temp;
                }
    }

    private void Shuffle() {
        for (int i = deck.length - 1; i >= 1; --i) {
            int j = (int) (Math.random ( ) * 100 % i);
            Card temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }
    }

    private void initDeck() {
        deck = new Card[52];
        for (int i = 0; i < 52; i++) deck[i] = new Card (i);
    }

    private void initDeck(int ndirection, Deck parentDeck) {
        deck = new Card[13];
//        for (Card card :
//                parentDeck.deck) {
//            if (card.ndirection == ndirection) deck[it] = card;
//            ++it;
//        }
        for (int i = 0, j = 0; i < parentDeck.deck.length; i++)
            if (parentDeck.deck[i].ndirection == ndirection) deck[j++] = parentDeck.deck[i];
    }

    private void assignDirection() {
        int dir = 0, itCnt = 0;
        for (Card c :
                deck) {
            c.nDirection (dir);
            if (++itCnt % 13 == 0) ++dir;
        }
    }

    private Card[] resize(Card deck[], int length) {
        Card retDeck[] = new Card[length];
        for (int i = 0; i < (length >= deck.length ? deck.length : length); i++) retDeck[i] = deck[i];
        return retDeck;
    }
}
