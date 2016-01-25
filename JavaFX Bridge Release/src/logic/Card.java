package logic;

/**
 * Created by adamx on 1/8/2016.
 */
public class Card {
    //priority suit for arranging suits after trump suit is initialized
    public int nvalue, nsuit, nrank, ndirection, prioritysuit;//int for computation
    public String lssuit, ssuit, srank, sdirection;//string for output

    public Card() {//default
        nvalue = 0;
        nsuit = 0;
        nrank = 0;
        ndirection = 0;
    }

    public Card(int nvalue) {
        new Card ( );
        this.nvalue = nvalue;//set global
        //translate
        nValuetonSuitRank ( );
        nSuitRanktosSuitRank ( );
        nSuittolsSuit ( );
    }

    public void nDirection(int ndirection) {
        //range and overwrite checking
        if (this.ndirection == 0 && ndirection >= 0 && ndirection <= 3) {
            this.ndirection = ndirection;//set global
            nDirectiontosDirection ( );//translate
        } else System.out.println ("Overwrite or Range error at class Card nDirection.");
    }

    public void Print() {//set fields
        System.out.println (
                "nvalue: " + nvalue +
                        " nrank: " + nrank +
                        " nsuit: " + nsuit +
//                " nindex: " + c.nindex +
                        " ndirection: " + ndirection +
                        " ssuit: " + ssuit +
                        " srank: " + srank +
                        " lssuit: " + lssuit +
                        " sdirection: " + sdirection +
                        " prioritysuit: " + prioritysuit
        );
    }

    //<editor-fold desc="nIndex"> //NOT USED
/*    public void nIndex(int nindex){
        if (nindex>=0&&nindex<=51) this.nindex = nindex;
        else System.out.println("Range error at class Card nIndex.");
    }*/
//</editor-fold>
    public void nSuittoprioritySuit(int bidsuit) {
        //get suit from Bid Class
        //3 - bidsuit because of reverse ordering of suit in Bid Class compared to Card Class
        //range checking
        bidsuit = 3 - bidsuit;
        if (bidsuit == -1) prioritysuit = nsuit;//case of NT, no trump needed
        else if (bidsuit >= 0 && bidsuit <= 3) {
            if (nsuit == bidsuit) prioritysuit = 0;//suit is trump suit, push to highest suit value, 0
                //push back or front according to position relative to trump suit
            else if (nsuit > bidsuit) prioritysuit = nsuit - 1;
            else if (nsuit < bidsuit) prioritysuit = nsuit + 1;
        } else System.out.println ("Range error at class Card nSuittoprioritySuit.");
    }

    private void nValuetonSuitRank() {//wrapper method
        nValuetonSuit ( );
        nValuetonRank ( );
    }

    private void nValuetonSuit() {
        //range and overwrite checking
        if (nsuit == 0 && nvalue >= 0 && nvalue <= 51) nsuit = nvalue / 13;//13 cards in each suit, so suit = */13
        else System.out.println ("Overwrite or Range error at class Card nValuetoSuit.");
    }

    private void nValuetonRank() {
        //overwrite and range checking
        if (nrank == 0 && nvalue >= 0 && nvalue <= 51) nrank = nvalue % 13;//getting rank in a suit, so rank = *%13
        else System.out.println ("Overwrite or Range error at class Card nValuetonRank.");
    }

    private void nSuitRanktosSuitRank() {//wrapper method
        nSuittosSuit ( );
        nRanktosRank ( );
    }

    private void nSuittosSuit() {
        //range and overwrite checking
        if (ssuit == null && nsuit >= 0 && nsuit <= 3) switch (nsuit) {
            case 0:
                ssuit = "S";
                break;
            case 1:
                ssuit = "H";
                break;
            case 2:
                ssuit = "D";
                break;
            case 3:
                ssuit = "C";
                break;
            default:
                assert (nsuit >= 0 && nsuit <= 3);
                break;
        }
        else System.out.println ("Overwrite or Range error at class Card nSuittosSuit.");
    }

    private void nRanktosRank() {
        //range and overwrite checking
        if (srank == null && nrank >= 0 && nrank <= 12) {
            //1 digit char to int cases
            if (nrank <= 7) srank = String.valueOf ((char) ('0' + nrank + 2));
            else switch (nrank) {//special cases
                case 8:
                    srank = "10";
                    break;
                case 9:
                    srank = "J";
                    break;
                case 10:
                    srank = "Q";
                    break;
                case 11:
                    srank = "K";
                    break;
                case 12:
                    srank = "A";
                    break;
                default:
                    assert (nrank >= 0 && nrank <= 12);
                    break;
            }
        } else System.out.println ("Overwrite or Range error at class Card nRanktosRank.");
    }

    private void nSuittolsSuit() {
        //range and overwrite checking
        if (lssuit == null && nsuit >= 0 && nsuit <= 3) switch (nsuit) {
            case 0:
                lssuit = "Spade";
                break;
            case 1:
                lssuit = "Heart";
                break;
            case 2:
                lssuit = "Diamond";
                break;
            case 3:
                lssuit = "Club";
                break;
            default:
                assert (nsuit >= 0 && nsuit <= 3);
                break;
        }
        else System.out.println ("Overwrite or Range error at class Card nSuittolsSuit.");
    }

    private void nDirectiontosDirection() {
        //range and overwrite checking
        if (sdirection == null && ndirection >= 0 && ndirection <= 3) switch (ndirection) {
            case 0:
                sdirection = "N";
                break;
            case 1:
                sdirection = "E";
                break;
            case 2:
                sdirection = "S";
                break;
            case 3:
                sdirection = "W";
                break;
            default:
                assert (ndirection >= 0 && ndirection <= 3);
                break;
        }
        else System.out.println ("Overwrite or Range error in Card nDirectiontosDirection.");
    }
}
