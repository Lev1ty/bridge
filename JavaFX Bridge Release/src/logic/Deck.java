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

    public void remove(int nvalue) {
        int pos = -1;
        for (int i = 0; i < deck.length; i++)
            if (deck[i].nvalue == nvalue) {
                pos = i;
                break;
            }
        if (pos != -1) {
            if (pos > 0) for (int i = pos + 1; i < deck.length; i++)
                deck[i - 1] = deck[i];
            else for (int i = 1; i < deck.length; i++)
                deck[i - 1] = deck[i];
            deck = resize (deck, deck.length - 1);
        } else System.out.println ("No card found that nvalue matches parameter");
    }

    public void printDeck() {
        for (Card c :
                deck)
            c.Print ( );
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
        for (int i = 0; i < length; i++) retDeck[i] = deck[i];
        return retDeck;
    }
}
