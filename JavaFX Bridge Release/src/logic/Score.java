package logic;

/**
 * Created by Adam on 1/22/2016.
 */
public class Score {
    public static int totalTricks, made, level, suit, score, overtricks, undertricks;
    public static Bid contractBid;

    public Score(Bid contractBid, int totalTricks) {
        made = totalTricks - 6;
        level = contractBid.slevel.charAt (0) - '0';
        suit = contractBid.nsuit;
        overtricks = made - level;
        undertricks = level - made;
        Score.contractBid = contractBid;
        Score.totalTricks = totalTricks;
    }

    public static int calculate() {
        boolean win = made >= level;
        if (win) {
            score = 50 + level * (suit <= 1 ? 20 : 30);
            if (suit == 4) score += 10;
            if (contractBid.xx) score = score * 4 - 50 + overtricks * 200 +
                    ((level == 1 && (suit == 2 || suit == 3 || suit == 4)) ||
                            (level == 2 && (suit == 0 || suit == 1)) ? 250 : 0);
            else if (contractBid.x) score = score * 2 + overtricks * 100 +
                    ((level == 2 && (suit == 2 || suit == 3 || suit == 4)) ||
                            (level == 3 && (suit == 0 || suit == 1)) ? 250 : 0);
            if (((suit == 2 || suit == 3) && level == 4) ||
                    ((suit == 0 || suit == 1) && level == 5) ||
                    (suit == 4 && level == 3)) score += 250;
            else if (level == 6) score += 500;
            else if (level == 7) score += 1000;
        } else {
            score = 50 * (contractBid.xx ? 4 : contractBid.x ? 2 : 1);
            int i = undertricks;
            for (; i >= 4; i--) score += (contractBid.xx ? 600 : contractBid.x ? 300 : 50);
            for (; i >= 2; i--) score += (contractBid.xx ? 400 : contractBid.x ? 200 : 50);
        }
        return win ? score : (score *= -1);
    }
}
