package logic;

/**
 * Created by adamx on 1/8/2016.
 */
public class Deck {
    public Card deck[];//array for cards

    public Deck() {//default init
        initDeck ( );
        Shuffle ( );
        assignDirection ( );
    }

    public Deck(int ndirection, Deck parentDeck) {//for players' hands
        initDeck (ndirection, parentDeck);
        sortBySuit ( );
        sortByRank ( );
    }

    public static Deck[] seperateBySuit(Deck masterDeck) {//seperate hand into suit
        Deck deck[] = new Deck[4];//for 4 suita of a hand

        for (int i = 0; i < 4; i++) {//init deck array
            deck[i] = new Deck ( );
            deck[i].deck = new Card[0];
        }

        for (int i = 0; i < 4; i++) {//sort by suit foreach suit
            for (int j = 0; j < masterDeck.deck.length; j++) {//foreach card in hand
                if (masterDeck.deck[j].prioritysuit == i) {//if is current suit number, post trump
                    deck[i].push_back (masterDeck.deck[j]);
                }
            }
        }

        return deck;
    }

    public static Card[] sortBySuit(Card deck[]) {//sort by suit
        try {//for debugging
            for (int i = 0; i < deck.length - 1; i++) {//bubble sort through decks
                for (int j = 0; j < deck.length - 1; j++) {//foreach card in deck
                    if (deck[j].prioritysuit > deck[j + 1].prioritysuit) {
                        Card temp = deck[j];
                        deck[j] = deck[j + 1];
                        deck[j + 1] = temp;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println ("sortbysuit exception");
        }
        return deck;
    }

    public static Card[] resize(Card deck[], int length) {
        Card retDeck[] = new Card[length];//init resized deck
        for (int i = 0; i < (length >= deck.length ? deck.length : length)/*see which size is bigger*/; i++)
            retDeck[i] = deck[i];//copy elements
        return retDeck;
    }

    public static boolean isThereSuit(Deck deck, int nsuit) {
        for (Card card :
                deck.deck)
            if (card.nsuit == nsuit) return true;//if found
        return false;//else
    }

    public void push_back(Card card) {
        deck = resize (deck, deck.length + 1);//resize
        deck[deck.length - 1] = card;//insert to back
    }

    public Card remove(int nvalue) {
        int pos = -1;//init to error pos
        Card card = null;//init to null
        for (int i = 0; i < deck.length; i++)//find position
            if (deck[i].nvalue == nvalue) {
                pos = i;
                break;
            }
        if (pos != -1) {//if found
            card = deck[pos];//card of pos
            if (pos > 0) for (int i = pos + 1; i < deck.length; i++)//shift all elements up
                deck[i - 1] = deck[i];
            else for (int i = 1; i < deck.length; i++)//if pos is first element, unecessary microoptimization
                deck[i - 1] = deck[i];

            deck = resize (deck, deck.length - 1);//resize deck
        } else System.out.println ("No card found that nvalue matches parameter");
        return card;//return card for debugging
    }

    public void printDeck() {//print all card fields
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
        for (int i = 0; i < deck.length - 1; i++)//bubble sort based on suit
            for (int j = 0; j < deck.length - 1; j++)
                if (deck[j].nsuit > deck[j + 1].nsuit) {
                    Card temp = deck[j];
                    deck[j] = deck[j + 1];
                    deck[j + 1] = temp;
                }
    }

    private void sortByRank() {
        for (int i = 0; i < deck.length - 1; i++)//bubble sort within suits by rank
            for (int j = 0; j < deck.length - 1; j++)
                if (deck[j].nsuit == deck[j + 1].nsuit &&//if next element is of the same suit
                        deck[j].nrank < deck[j + 1].nrank) {
                    Card temp = deck[j];
                    deck[j] = deck[j + 1];
                    deck[j + 1] = temp;
                }
    }

    private void Shuffle() {
        for (int i = deck.length - 1; i >= 1; --i) {
            int j = (int) (Math.random ( ) * 100 % i);//generate random pos between current card and start of deck
            Card temp = deck[i];//swap
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
        for (int i = 0, j = 0; i < parentDeck.deck.length; i++)//insert into corresponding dck according to direction
            if (parentDeck.deck[i].ndirection == ndirection) deck[j++] = parentDeck.deck[i];
    }

    private void assignDirection() {
        int dir = 0, itCnt = 0;//counter and direction
        for (Card c :
                deck) {
            c.nDirection (dir);//set direction
            if (++itCnt % 13 == 0) ++dir;//if after 13 cards in hand move on to next player
        }
    }
}
