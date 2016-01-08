/**
 * Created by Adam on 1/5/2016.
 */
class Bid {
    // TODO: 1/5/2016 Make bid then make auction wrapper for bid
    final int nvalue, ndirection;
    int nlevel, nsuit;
    String slevel, ssuit, sdirection;
    boolean x, xx;

    public Bid() {
        nvalue = 0;
        nlevel = 0;
        nsuit = 0;
        ndirection = 0;
        x = false;
        xx = false;
    }

    public Bid(int nvalue, int ndirection) {
        new Bid();
        this.nvalue = nvalue;
        this.ndirection = ndirection;
        nValuetonSuitLevel();
        nSuitLeveltosSuitLevel();
        nDirectiontosDirection();
    }

    public void Print() {
        System.out.println(
                "nvalue: " + nvalue +
                        " ndirection: " + ndirection +
                        " nlevel: " + nlevel +
                        " nsuit: " + nsuit +
                        " slevel: " + slevel +
                        " ssuit: " + ssuit +
                        " sdirection: " + sdirection
        );
    }

    private void nValuetonSuit() {
        if (nsuit == 0 && nvalue >= 0 && nvalue <= 37) {
            if (nvalue == 35) nsuit = -1;
            else if (nvalue == 36) nsuit = -2;
            else if (nvalue == 37) nsuit = -3;
            else nsuit = nvalue % 5;
        } else System.out.println("Overwrite or Range error in class Bid nValuetonSuit.");
    }

    private void nValuetonLevel() {
        if (nlevel == 0 && nvalue >= 0 && nvalue <= 37) {
//            if (nvalue >= 35) nlevel = -1;
//            else nlevel = nvalue / 5;
            nlevel = nvalue / 5;
        } else System.out.println("Overwrite or Range error in class Bid nValuetonSuit.");
    }

    private void nValuetonSuitLevel() {
        nValuetonLevel();
        nValuetonSuit();
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
                assert (nsuit >= -1 && nsuit <= 4);
                break;
        }
        else System.out.println("Overwrite or Range error at class Bid nSuittosSuit.");
    }

    private void nLeveltosLevel() {
        if (slevel == null && nlevel >= 0 && nlevel <= 7) {
            if (nlevel == 7) slevel = "Aux";
            else slevel = String.valueOf((char) (nlevel + 1 + '0'));
        } else System.out.println("Overwrite or Range error at class Bid nLeveltosLevel.");
    }

    private void nSuitLeveltosSuitLevel() {
        nSuittosSuit();
        nLeveltosLevel();
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
        else System.out.println("Overwrite or Range error at class Bid in nDirectiontosDirection.");
    }
}
