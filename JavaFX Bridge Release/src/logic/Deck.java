package logic;

/**
 * Created by adamx on 1/8/2016.
 */
public class Deck {
    Card deck[];
    int nend, eend, send, wend, size;

    public Deck() {
        deck = new Card[52];
        initDeck ();
        Shuffle ();
        assignDirection ();
        nend = 12;
        eend = 25;
        send = 38;
        wend = 51;
        size = 52;
    }

    public void remove(int nvalue) {
        int pos = -1;
        for (int i = 0; i < size; i++)
            if (deck[i].nvalue == nvalue) {
                pos = i;
                break;
            }
        if (pos != -1) {
            if (pos <= 12) {
                --nend;
                --eend;
                --send;
                --wend;
            } else if (pos <= 25) {
                --eend;
                --send;
                --wend;
            } else if (pos <= 38) {
                --send;
                --wend;
            } else --wend;
            if (pos > 0) for (int i = pos + 1; i < size; i++)
                deck[i - 1] = deck[i];
            else for (int i = 1; i < size; i++)
                deck[i - 1] = deck[i];
            --size;
            deck = resize ( deck, size );
        } else System.out.println ( "No card found that nvalue matches parameter" );
    }

    public void printDeck() {
        for (Card c :
                deck)
            c.Print ();
    }

    private void Shuffle() {
        for (int i = 51; i >= 1; --i) {
            int j = (int) (Math.random () * 100 % i);
            Card temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }
    }

    private void initDeck() {
        for (int i = 0; i < 52; i++) deck[i] = new Card ( i );
    }

    private void assignDirection() {
        int dir = 0, itCnt = 0;
        for (Card c :
                deck) {
            c.nDirection ( dir );
            if (++itCnt % 13 == 0) ++dir;
        }
    }

    private Card[] resize(Card deck[], int size) {
        Card retDeck[] = new Card[size];
        for (int i = 0; i < size; i++) retDeck[i] = deck[i];
        return retDeck;
    }
}
