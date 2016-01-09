//package exclude;
//
//import logic.Auction;
//import logic.Bid;
//import logic.Deck;
//
//import java.util.Random;
//import java.util.Scanner;
//
///**
// * Created by Adam on 1/5/2016.
// */
//class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        for (int i = 0; i < 2; ++i) {
//            int rn1 = (int) (Math.random() * 100 % 4);
//            System.out.println("Game " + i);
//            Auction auc = new Auction();
//            Deck deck = new Deck();
//            System.out.println("Auction starts.");
//            for (int lastbid; !(auc.bcontract && auc.npass >= 3 && auc.nbid >= 4);
//                 ++rn1, ++auc.nbid) {
//                rn1 %= 4;
//                if (auc.nbid == 4 && auc.npass == 4) break;
//                System.out.println("Bid from " + rn1 + ": ");
//                lastbid = sc.nextInt();
//                auc.push_back(lastbid, rn1);
//                switch (lastbid) {
//                    case 35:
//                        ++auc.npass;
//                        break;
//                    case 36:
//                        auc.x = true;
//                        break;
//                    case 37:
//                        auc.xx = true;
//                        break;
//                    default:
//                        auc.bcontract = true;
//                        auc.npass = 0;
//                        break;
//                }
//                ++rn1;
//                rn1 %= 4;
//                ++auc.nbid;
//                if (!(auc.bcontract && auc.npass >= 3 && auc.nbid >= 4)) {
//                    if (++lastbid >= 35) lastbid = 35;
//                    auc.push_back(lastbid, rn1);
//                    switch (lastbid) {
//                        case 35:
//                            ++auc.npass;
//                            break;
//                        case 36:
//                            auc.x = true;
//                            auc.npass = 0;
//                            break;
//                        case 37:
//                            auc.xx = true;
//                            auc.npass = 0;
//                            break;
//                        default:
//                            auc.bcontract = true;
//                            auc.npass = 0;
//                            break;
//                    }
//                }
////                for (int j = 0; j < 3 &&
////                        !(auc.bcontract && auc.npass >= 3 && auc.nbid >= 4);
////                     j++, ++rn1, ++auc.nbid) {
////                    if (auc.nbid == 4 && auc.npass == 4) break;
////                    rn1 %= 4;
////                    if (++lastbid >= 35) lastbid = 35;
////                    auc.push_back(lastbid, rn1);
////                    switch (lastbid) {
////                        case 35:
////                            ++auc.npass;
////                            break;
////                        case 36:
////                            auc.x = true;
////                            break;
////                        case 37:
////                            auc.xx = true;
////                            break;
////                        default:
////                            auc.bcontract = true;
////                            auc.npass = 0;
////                            break;
////                    }
////                }
//            }
//            if (auc.npass == 4 && auc.nbid == 4) {
//                //AUCTION PASSED OUT
//                continue;
//            } else {
//                int ndir = -1, nval = -1, j;
//                for (j = auc.auction.size() - 1; j >= 0; j--)
//                    if (auc.auction.get(j).nvalue <= 34) {
//                        nval = auc.auction.get(j).nvalue;
//                        ndir = auc.auction.get(j).ndirection;
//                        break;
//                    }
//                for (; j >= 0; j--)
//                    if (auc.auction.get(j).nvalue == nval) ndir = auc.auction.get(j).ndirection;
//                if (ndir >= 0 && nval >= 0 && nval <= 34) auc.contract = new Bid(nval, ndir);
//            }
//            if (auc.xx) auc.contract.xx = true;
//            else if (auc.x) auc.contract.x = true;
//            auc.printAuction();
//            System.out.println(auc.contract.nvalue + " " + auc.contract.ndirection);
//            deck.printDeck();
//            deck.remove(1);
//            System.out.println();
//            deck.printDeck();
//        }
//    }
//}
