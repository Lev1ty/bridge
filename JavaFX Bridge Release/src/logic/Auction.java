package logic;

import java.util.ArrayList;

/**
 * Created by adamx on 1/6/2016.
 */
public class Auction {
    public ArrayList<Bid> auction;//container for bids
    public int nbid, npass;//counter for pass and bids
    public boolean bcontract, x, xx;//switches for contract, double and redouble

    public Auction() {//default initialization
        auction = new ArrayList<> ( );
        nbid = 0;
        npass = 0;
        bcontract = false;
        x = false;
        xx = false;
    }

    public Bid getContractBid() {
        int nvalue = -1;
        for (int i = auction.size ( ) - 1; i >= 0; --i)//get auction suit and level
            if (auction.get (i).nvalue < 35) nvalue = auction.get (i).nvalue;
        if (nvalue > -1)//if found, get first instance of the bid with the suit and level
            for (Bid anAuction : auction)
                if (anAuction.nvalue == nvalue) {
                    anAuction.x = x;
                    anAuction.xx = xx;
                    return anAuction;
                }
        return null;//if not found return nothing
    }

    public void push_back(int nvalue, int ndirection) {//wrapper
        auction.add (new Bid (nvalue, ndirection));
    }

    public void printAuction() {
        auction.forEach (Bid::Print);//print auction using method in each bid
    }

    public void printField() {//print globals
        System.out.println ("nbid: " + nbid +
                " npass: " + npass +
                " bcontract: " + bcontract +
                " x: " + x +
                " xx: " + xx
        );
    }
}
