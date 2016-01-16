package logic;

import java.util.ArrayList;

/**
 * Created by adamx on 1/6/2016.
 */
public class Auction {
    public ArrayList<Bid> auction;
    public int nbid, npass;
    public boolean bcontract, x, xx;

    public Auction() {
        auction = new ArrayList<> ( );
        nbid = 0;
        npass = 0;
        bcontract = false;
        x = false;
        xx = false;
    }

    public Bid getContractBid() {
        int nvalue = -1;
        for (int i = auction.size ( ) - 1; i >= 0; --i)
            if (auction.get (i).nvalue < 35) nvalue = auction.get (i).nvalue;
        if (nvalue > -1)
            for (int i = 0; i < auction.size ( ); ++i) if (auction.get (i).nvalue == nvalue) return auction.get (i);
        return new Bid ( );
    }

    public void push_back(int nvalue, int ndirection) {
        auction.add (new Bid (nvalue, ndirection));
    }

    public void printAuction() {
        for (Bid b :
                auction)
            b.Print ( );
    }

    public void printField() {
        System.out.println ("nbid: " + nbid +
                " npass: " + npass +
                " bcontract: " + bcontract +
                " x: " + x +
                " xx: " + xx
        );
    }
}
