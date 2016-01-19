package logic;

/**
 * Created by adamx on 1/8/2016.
 */
public class Card {
    public int nvalue, nsuit, nrank, ndirection, prioritysuit;
    public String lssuit, ssuit, srank, sdirection;

    public Card() {
        nvalue = 0;
        nsuit = 0;
        nrank = 0;
        ndirection = 0;
    }

    public Card(int nvalue) {
        new Card ( );
        this.nvalue = nvalue;
        nValuetonSuitRank ( );
        nSuitRanktosSuitRank ( );
        nSuittolsSuit ( );
    }

    public void nDirection(int ndirection) {
        if (this.ndirection == 0 && ndirection >= 0 && ndirection <= 3) {
            this.ndirection = ndirection;
            nDirectiontosDirection ( );
        } else System.out.println ("Overwrite or Range error at class Card nDirection.");
    }

    public void Print() {
        System.out.println (
                "nvalue: " + nvalue +
                        " nrank: " + nrank +
                        " nsuit: " + nsuit +
//                " nindex: " + c.nindex +
                        " ndirection: " + ndirection +
                        " ssuit: " + ssuit +
                        " srank: " + srank +
                        " lssuit: " + lssuit +
                        " sdirection: " + sdirection
        );
    }

    //<editor-fold desc="nIndex">
/*    public void nIndex(int nindex){
        if (nindex>=0&&nindex<=51) this.nindex = nindex;
        else System.out.println("Range error at class Card nIndex.");
    }*/
//</editor-fold>
    public void nSuittoprioritySuit(int bidsuit) {
        if (bidsuit >= 0 && bidsuit <= 3) {
            bidsuit = 3 - bidsuit;
            if (nsuit == bidsuit) prioritysuit = 0;
            else if (nsuit > bidsuit) prioritysuit = nsuit - 1;
            else if (nsuit < bidsuit) prioritysuit = nsuit + 1;
        }
    }

    private void nValuetonSuitRank() {
        nValuetonSuit ( );
        nValuetonRank ( );
    }

    private void nValuetonSuit() {
        if (nsuit == 0 && nvalue >= 0 && nvalue <= 51) nsuit = nvalue / 13;
        else System.out.println ("Overwrite or Range error at class Card nValuetoSuit.");
    }

    private void nValuetonRank() {
        if (nrank == 0 && nvalue >= 0 && nvalue <= 51) nrank = nvalue % 13;
        else System.out.println ("Overwrite or Range error at class Card nValuetonRank.");
    }

    private void nSuitRanktosSuitRank() {
        nSuittosSuit ( );
        nRanktosRank ( );
    }

    private void nSuittosSuit() {
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
        if (srank == null && nrank >= 0 && nrank <= 12) {
            if (nrank <= 7) srank = String.valueOf ((char) ('0' + nrank + 2));
            else switch (nrank) {
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
