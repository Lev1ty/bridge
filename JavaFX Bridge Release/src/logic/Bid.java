package logic;

/**
 * Created by adamx on 1/8/2016.
 */
public class Bid {
    // TODO: 1/5/2016 Make bid then make auction wrapper for bid
    public int nvalue, ndirection, nlevel, nsuit;
    public String slevel, ssuit, sdirection, svalue;
    public boolean x, xx;

    public Bid() {
        nvalue = 0;
        nlevel = 0;
        nsuit = 0;
        ndirection = 0;
        x = false;
        xx = false;
    }

    public Bid(int nvalue, int ndirection) {
        new Bid ( );
        this.nvalue = nvalue;
        this.ndirection = ndirection;
        nValuetonSuitLevel ( );
        nSuitLeveltosSuitLevel ( );
        nDirectiontosDirection ( );
    }

    public Bid(String svalue, String sdirection) {
        new Bid ( );
        this.sdirection = sdirection;
        sValuetonValue (svalue);
        ndirection = sDirectiontonDirection (sdirection);
        nValuetonSuitLevel ( );
        nSuitLeveltosSuitLevel ( );
        nDirectiontosDirection ( );
    }

    public Bid(String svalue, int ndirection) {
        new Bid ( );
        this.ndirection = ndirection;
        sValuetonValue (svalue);
        nValuetonSuitLevel ( );
        nSuitLeveltosSuitLevel ( );
        nDirectiontosDirection ( );
    }

    public static String nDirectiontosDirection(int ndirection) {
        String sdirection = "Error";
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
        }
        else System.out.println ("Range error at class Bid in nDirectiontosDirection.");
        return sdirection;
    }

    public static String nDirectiontolsDirection(int ndirection) {
        String sdirection = "Error";
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
        }
        else System.out.println ("Range error at class Bid in nDirectiontolsDirection.");
        return sdirection;
    }

    public static int sDirectiontonDirection(String sdirection) {
        int ndirection = -1;
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

    public static String nSuittosSuit(int nsuit) {
        String ssuit = null;
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

    public void Print() {
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
                        " redouble: " + xx
        );
    }

    private void sValuetonValue(String svalue) {
        this.svalue = svalue;
        int nlevel = 0, nsuit = 0;
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
            }
        } else {
            nlevel = svalue.charAt (0) - '1';
            switch (svalue.charAt (1)) {
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
        }
        this.nvalue = nlevel * 5 + nsuit;
    }

    private void nValuetonSuit() {
        if (nsuit == 0 && nvalue >= 0 && nvalue <= 37) {
            if (nvalue == 35) nsuit = -1;
            else if (nvalue == 36) nsuit = -2;
            else if (nvalue == 37) nsuit = -3;
            else nsuit = nvalue % 5;
        } else System.out.println ("Overwrite or Range error in class Bid nValuetonSuit.");
    }

    private void nValuetonLevel() {
        if (nlevel == 0 && nvalue >= 0 && nvalue <= 37) {
//            if (nvalue >= 35) nlevel = -1;
//            else nlevel = nvalue / 5;
            nlevel = nvalue / 5;
        } else System.out.println ("Overwrite or Range error in class Bid nValuetonSuit.");
    }

    private void nValuetonSuitLevel() {
        nValuetonLevel ( );
        nValuetonSuit ( );
    }

    private void nSuittosSuit() {
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
        if (slevel == null && nlevel >= 0 && nlevel <= 7) {
            if (nlevel == 7) slevel = "Aux";
            else slevel = String.valueOf ((char) (nlevel + 1 + '0'));
        } else System.out.println ("Overwrite or Range error at class Bid nLeveltosLevel.");
    }

    private void nSuitLeveltosSuitLevel() {
        nSuittosSuit ( );
        nLeveltosLevel ( );
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
        else System.out.println ("Overwrite or Range error at class Bid in nDirectiontosDirection.");
    }
}
