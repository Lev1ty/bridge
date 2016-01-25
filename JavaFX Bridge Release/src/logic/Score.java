package logic;

/**
 * Created by Adam on 1/22/2016.
 */
public class Score {
    public static int totalTricks, made, level, suit, score, overtricks, undertricks;
    public static Bid contractBid;

    public Score(Bid contractBid, int totalTricks) {
        //init globals
        made = totalTricks - 6;//translate to by contract instead of raw total
        level = contractBid.slevel.charAt (0) - '0';//translate to int
        suit = contractBid.nsuit;//set
        overtricks = made - level;//difference if win, neg when lost
        undertricks = level - made;//difference if lost, neg when win
        Score.contractBid = contractBid;
        Score.totalTricks = totalTricks;
    }

    public static int calculate() {
        boolean win = made >= level;//check if win or loose
        if (win) {
            score = 50 + level * (suit <= 1 ? 20 : 30);//50 base score, major suit or minor suit
            if (suit == 4) score += 10;//if notrump
            if (contractBid.xx) score = score * 4 - 50 + overtricks * 200 +//by redoubling: score*4 - base score of 50,
                    //is the suit levels below eligible for game bonus?
                    ((level == 1 && (suit == 2 || suit == 3 || suit == 4)) ||
                            (level == 2 && (suit == 0 || suit == 1)) ? 250 : 0);//for redoubling
            else if (contractBid.x) score = score * 2 + overtricks * 100 +//by doubling: score*4 - base score of 50,
                    //is the suit levels below eligible for game bonus?
                    ((level == 2 && (suit == 2 || suit == 3 || suit == 4)) ||
                            (level == 3 && (suit == 0 || suit == 1)) ? 250 : 0);//for doubling
            if (((suit == 2 || suit == 3) && level == 4) ||
                    ((suit == 0 || suit == 1) && level == 5) ||
                    (suit == 4 && level == 3)) score += 250;//game bonus
            else if (level == 6) score += 500;//small slam bonus
            else if (level == 7) score += 1000;//grand slam bonus
        } else {//lose
            score = 50 * (contractBid.xx ? 4 : contractBid.x ? 2 : 1);//multiplier of 50 based on normal, x, and xx
            int i = undertricks;//number of undertricks
            for (; i >= 4; i--) score += (contractBid.xx ? 600 : contractBid.x ? 300 : 50);//additional tricks
            for (; i >= 2; i--) score += (contractBid.xx ? 400 : contractBid.x ? 200 : 50);//after 4 undertricks, calculation changes
        }
        return win ? score : (score *= -1);//negative score based on loss
    }
}
