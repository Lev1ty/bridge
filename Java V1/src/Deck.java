/**
 * Created by Adam on 1/5/2016.
 */
import java.util.ArrayList;
class Deck{
    ArrayList<Card> deck;
    public Deck() {
        deck = new ArrayList<>();
        for (int i = 0; i < 52; i++) deck.add(new Card(i));
    }
    public Deck(boolean b){
        if (b) new Deck();
        else deck = new ArrayList<>();
    }
    public void printDeck(){
        for (Card c :
                deck)
            System.out.println(
                "nvalue: " + c.nvalue +
                " nrank: " + c.nrank +
                " nsuit: " + c.nsuit +
//                " nindex: " + c.nindex +
                " ndirection: " + c.ndirection +
                " ssuit: " + c.ssuit +
                " srank: " + c.srank +
                " lssuit: " + c.lssuit +
                " sdirection: " + c.sdirection
            );
    }
}
