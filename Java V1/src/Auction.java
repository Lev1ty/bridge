import java.util.ArrayList;

/**
 * Created by adamx on 1/6/2016.
 */
public class Auction {
    ArrayList<Bid> auction;
    int nbid, npass;
    boolean bcontract, x, xx;
    Bid contract;

    public Auction() {
        auction = new ArrayList<>();
        nbid = 0;
        npass = 0;
        bcontract = false;
        x = false;
        xx = false;
        contract = new Bid();
    }

    public void push_back(int nvalue, int ndirection) {
        auction.add(new Bid(nvalue, ndirection));
    }

    public void printAuction() {
        for (Bid b :
                auction)
            b.Print();
    }
}
