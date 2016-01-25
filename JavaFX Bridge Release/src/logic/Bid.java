package logic;

/**
 * Created by adamx on 1/8/2016.
 */
public class Bid {
    // TODO: 1/5/2016 Make bid then make auction wrapper for bid
    public int nvalue, ndirection, nlevel, nsuit;//int data for computation
    public String slevel, ssuit, sdirection, svalue, lsdirection;//string data for display
    public boolean x, xx;//switches for double and redouble

    public Bid() {//default initialization
        nvalue = 0;
        nlevel = 0;
        nsuit = 0;
        ndirection = 0;
        x = false;
        xx = false;
    }

    public Bid(int nvalue, int ndirection) {
        new Bid ( );
        //set global values
        this.nvalue = nvalue;
        this.ndirection = ndirection;
        //translate
        nValuetonSuitLevel ( );
        nSuitLeveltosSuitLevel ( );
        nDirectiontosDirection ( );
        nDirectiontolsDirection ( );
    }

    public Bid(String svalue, String sdirection) {
        new Bid ( );
        //set globals
        this.sdirection = sdirection;
        sValuetonValue (svalue);
        ndirection = sDirectiontonDirection (sdirection);
        //translate
        nValuetonSuitLevel ( );
        nSuitLeveltosSuitLevel ( );
        nDirectiontosDirection ( );
        nDirectiontolsDirection ( );
    }

    public Bid(String svalue, int ndirection) {
        new Bid ( );
        //set globals
        this.ndirection = ndirection;
        //translate
        sValuetonValue (svalue);
        nValuetonSuitLevel ( );
        nSuitLeveltosSuitLevel ( );
        nDirectiontosDirection ( );
        nDirectiontolsDirection ( );
    }

    public static String nDirectiontosDirection(int ndirection) {
        String sdirection = "Error";
        //program internal range checking
        if (ndirection >= 0 && ndirection <= 3) switch (ndirection) {
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
        } else System.out.println ("Range error at class Bid in nDirectiontosDirection.");
        return sdirection;
    }

    public static String nDirectiontolsDirection(int ndirection) {//full form directions
        String sdirection = "Error";
        //program internal range checking
        if (ndirection >= 0 && ndirection <= 3) switch (ndirection) {
            case 0:
                sdirection = "North";
                break;
            case 1:
                sdirection = "East";
                break;
            case 2:
                sdirection = "South";
                break;
            case 3:
                sdirection = "West";
                break;
            default:
                assert (ndirection >= 0 && ndirection <= 3);
                break;
        } else System.out.println ("Range error at class Bid in nDirectiontolsDirection.");
        return sdirection;
    }

    public static int sDirectiontonDirection(String sdirection) {
        int ndirection = -1;//preset error
        switch (sdirection.charAt (0)) {
            case 'N':
                ndirection = 0;
                break;
            case 'E':
                ndirection = 1;
                break;
            case 'S':
                ndirection = 2;
                break;
            case 'W':
                ndirection = 3;
                break;
            default:
                break;
        }
        return ndirection;
    }

    public static String nSuittosSuit(int nsuit) {//overloaded non-static nSuittosSuit method for external usage
        String ssuit = null;
        //range checking
        if (nsuit >= -3 && nsuit <= 4) switch (nsuit) {
            case -3:
                ssuit = "XX";
                break;
            case -2:
                ssuit = "X";
                break;
            case -1:
                ssuit = "P";
                break;
            case 0:
                ssuit = "C";
                break;
            case 1:
                ssuit = "D";
                break;
            case 2:
                ssuit = "H";
                break;
            case 3:
                ssuit = "S";
                break;
            case 4:
                ssuit = "NT";
                break;
            default:
                assert (nsuit >= -3 && nsuit <= 4);
                break;
        }
        else System.out.println ("Overwrite or Range error at class Bid nSuittosSuit.");
        return ssuit;
    }

    public void Print() {//print fields
        System.out.println (
                "nvalue: " + nvalue +
                        " ndirection: " + ndirection +
                        " nlevel: " + nlevel +
                        " nsuit: " + nsuit +
                        " svalue: " + svalue +
                        " slevel: " + slevel +
                        " ssuit: " + ssuit +
                        " sdirection: " + sdirection +
                        " double: " + x +
                        " redouble: " + xx +
                        " lsdireciton: " + lsdirection
        );
    }

    private void sValuetonValue(String svalue) {
        this.svalue = svalue;//set global
        int nlevel = 0, nsuit = 0;
        //auxillary bids
        if (svalue.length ( ) > 3) {
            switch (svalue) {
                case "Pass":
                    nsuit = 35;
                    break;
                case "Double":
                    nsuit = 36;
                    break;
                case "Redouble":
                    nsuit = 37;
                    break;
            }//contract bids
        } else {
            nlevel = svalue.charAt (0) - '1';//get level
            switch (svalue.charAt (1)) {//get suit
                case 'C':
                    nsuit = 0;
                    break;
                case 'D':
                    nsuit = 1;
                    break;
                case 'H':
                    nsuit = 2;
                    break;
                case 'S':
                    nsuit = 3;
                    break;
                case 'N':
                    nsuit = 4;
                    break;
                default:
                    break;
            }
        }//set global nvalue
        this.nvalue = nlevel * 5 + nsuit;
    }

    private void nValuetonSuit() {
        //range checking, overwrite checking
        if (nsuit == 0 && nvalue >= 0 && nvalue <= 37) {
            //auxillary bids
            if (nvalue == 35) nsuit = -1;
            else if (nvalue == 36) nsuit = -2;
            else if (nvalue == 37) nsuit = -3;
            //contract bids
            else nsuit = nvalue % 5;
        } else System.out.println ("Overwrite or Range error in class Bid nValuetonSuit.");
    }

    private void nValuetonLevel() {
        //range and overwrite checking
        if (nlevel == 0 && nvalue >= 0 && nvalue <= 37) {
//            if (nvalue >= 35) nlevel = -1;
//            else nlevel = nvalue / 5;
            nlevel = nvalue / 5;//ge level
        } else System.out.println ("Overwrite or Range error in class Bid nValuetonSuit.");
    }

    private void nValuetonSuitLevel() {//wrapper method
        nValuetonLevel ( );
        nValuetonSuit ( );
    }

    private void nSuittosSuit() {
        //overwrite and range checking
        if (ssuit == null && nsuit >= -3 && nsuit <= 4) switch (nsuit) {
            case -3:
                ssuit = "XX";
                break;
            case -2:
                ssuit = "X";
                break;
            case -1:
                ssuit = "P";
                break;
            case 0:
                ssuit = "C";
                break;
            case 1:
                ssuit = "D";
                break;
            case 2:
                ssuit = "H";
                break;
            case 3:
                ssuit = "S";
                break;
            case 4:
                ssuit = "NT";
                break;
            default:
                assert (nsuit >= -3 && nsuit <= 4);
                break;
        }
        else System.out.println ("Overwrite or Range error at class Bid nSuittosSuit.");
    }

    private void nLeveltosLevel() {
        //overwrite and range checking
        if (slevel == null && nlevel >= 0 && nlevel <= 7) {
            if (nlevel == 7) slevel = "Aux";//aux level for pass, double, redouble
            else slevel = String.valueOf ((char) (nlevel + 1 + '0'));//contract bid
        } else System.out.println ("Overwrite or Range error at class Bid nLeveltosLevel.");
    }

    private void nSuitLeveltosSuitLevel() {//wrapper method
        nSuittosSuit ( );
        nLeveltosLevel ( );
    }

    private void nDirectiontosDirection() {
        //overwrite and range error checking
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
        else System.out.println ("Overwrite or Range error at class Bid in nDirectiontosDirection.");
    }

    private void nDirectiontolsDirection() {
        //no overwrite or range checking
        switch (ndirection) {
            case 0:
                lsdirection = "North";
                break;
            case 1:
                lsdirection = "East";
                break;
            case 2:
                lsdirection = "South";
                break;
            case 3:
                lsdirection = "West";
                break;
            default:
                break;
        }
    }
}
