package logic;

/**
 * Created by adamx on 1/22/2016.
 */
public class BridgeScore {
    public static int score;
    public static int calculateScore(int bidLevel, int bidSuit, boolean x, boolean xx, boolean win, int tricksMade) {
        int num = ++bidLevel, made, down;
        int score = 70;
        int score2 = 80;
        int score3 = 90;
        int score4 = 110;
        int score5 = 120;
        int score6 = 140;
        int score7 = 400;
        int score8 = 130;
        int score9 = 420;
        int score10 = 430;
        int score11 = 450;
        int score12 = 460;
        int score13 = 920;
        int score14 = 980;
        int score15 = 990;
        int score16 = 1440;
        int score17 = 1510;
        int score18 = 1520;
        String suit, dbl, redbl, result;

        suit = Bid.nSuittosSuit ( bidSuit );
        dbl = (x ? "y" : "n");
        redbl = (xx ? "y" : "n");
        result = (win?"y":"n");
        made = tricksMade - 6;
        down = bidLevel + 4 - tricksMade;
        System.out.println ( "bidLevel = [" + bidLevel + "], bidSuit = [" + bidSuit + "], x = [" + x + "], xx = [" + xx + "], win = [" + win + "], tricksMade = [" + tricksMade + "]" );

//
//            print ("Please enter the number of the final bid (1-7): ");
//            num = getInt (1, 7);
//
//            print ("Please enter the suit of the final bid (c,d,h,s,nt): ");
//            suit = readString ();
//
//            do
//            {
//                print ("Double? (y/n) ");
//                dbl = getChoice ();
//
//                print ("Redouble? (y/n) ");
//                redbl = getChoice ();
//            }
//            while (dbl.equalsIgnoreCase ("n") && redbl.equalsIgnoreCase ("y"));
//
//            print ("Win? (y/n) ");
//            result = getChoice ();

        if (result.equalsIgnoreCase ( "y" )) {
//            print ( "Please enter the number of tricks made (from 6): " );
//            made = getInt ( 1, 7 );

            if (num == 1 && (suit.equalsIgnoreCase ( "c" ) || suit.equalsIgnoreCase ( "d" ))) {
                if (result.equalsIgnoreCase ( "y" )) {
                    if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "n" )) {
                        score = 140;
                        for (int i = 1; i < made; i++)
                            score = score + 100;
                    } else if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "y" )) {
                        score = 230;
                        for (int i = 1; i < made; i++)
                            score = score + 200;
                    } else {
                        for (int i = 1; i < made; i++)
                            score = score + 20;
                    }
                }

                Score ( score );
            }

            if (num == 1 && (suit.equalsIgnoreCase ( "h" ) || suit.equalsIgnoreCase ( "s" ))) {
                if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "n" )) {
                    score2 = 160;
                    for (int i = 1; i < made; i++)
                        score2 = score2 + 100;
                } else if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "y" )) {
                    score2 = 520;
                    for (int i = 1; i < made; i++)
                        score2 = score2 + 200;
                } else {
                    for (int i = 1; i < made; i++)
                        score2 = score2 + 30;
                }
                Score ( score2 );
            }


            if (num == 1 && suit.equalsIgnoreCase ( "NT" )) {
                if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "n" )) {
                    score3 = 180;
                    for (int i = 1; i < made; i++)
                        score3 = score3 + 100;
                } else if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "y" )) {
                    score3 = 560;
                    for (int i = 1; i < made; i++)
                        score3 = score3 + 200;
                } else {
                    for (int i = 1; i < made; i++)
                        score3 = score3 + 30;
                }
                Score ( score3 );
            }

            if (num == 2 && (suit.equalsIgnoreCase ( "c" ) || suit.equalsIgnoreCase ( "d" ))) {
                if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "n" )) {
                    score3 = 180;
                    for (int i = 2; i < made; i++)
                        score3 = score3 + 100;
                } else if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "y" )) {
                    score3 = 560;
                    for (int i = 2; i < made; i++)
                        score3 = score3 + 200;
                } else {
                    for (int i = 2; i < made; i++)
                        score3 = score3 + 20;
                }
                Score ( score3 );
            }

            if (num == 2 && (suit.equalsIgnoreCase ( "h" ) || suit.equalsIgnoreCase ( "s" ))) {
                if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "n" )) {
                    score4 = 470;
                    for (int i = 2; i < made; i++)
                        score4 = score4 + 100;
                } else if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "y" )) {
                    score4 = 640;
                    for (int i = 2; i < made; i++)
                        score4 = score4 + 200;
                } else {
                    for (int i = 2; i < made; i++)
                        score4 = score4 + 30;
                }
                Score ( score4 );
            }

            if (num == 2 && suit.equalsIgnoreCase ( "NT" )) {
                if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "n" )) {
                    score5 = 490;
                    for (int i = 2; i < made; i++)
                        score5 = score5 + 100;
                } else if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "y" )) {
                    score5 = 680;
                    for (int i = 2; i < made; i++)
                        score5 = score5 + 200;
                } else {
                    for (int i = 2; i < made; i++)
                        score5 = score5 + 30;
                }
                Score ( score5 );
            }

            if (num == 3 && (suit.equalsIgnoreCase ( "c" ) || suit.equalsIgnoreCase ( "d" ))) {
                if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "n" )) {
                    score4 = 470;
                    for (int i = 3; i < made; i++)
                        score4 = score4 + 100;
                } else if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "y" )) {
                    score4 = 640;
                    for (int i = 3; i < made; i++)
                        score4 = score4 + 200;
                } else {
                    for (int i = 3; i < made; i++)
                        score4 = score4 + 20;
                }
                Score ( score4 );
            }

            if (num == 3 && (suit.equalsIgnoreCase ( "h" ) || suit.equalsIgnoreCase ( "s" ))) {
                if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "n" )) {
                    score6 = 530;
                    for (int i = 3; i < made; i++)
                        score6 = score6 + 100;
                } else if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "y" )) {
                    score6 = 760;
                    for (int i = 3; i < made; i++)
                        score6 = score6 + 200;
                } else {
                    for (int i = 3; i < made; i++)
                        score6 = score6 + 30;
                }
                Score ( score6 );
            }


            if (num == 3 && suit.equalsIgnoreCase ( "NT" )) {
                if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "n" )) {
                    score7 = 550;
                    for (int i = 3; i < made; i++)
                        score7 = score7 + 100;
                } else if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "y" )) {
                    score7 = 800;
                    for (int i = 3; i < made; i++)
                        score7 = score7 + 200;
                } else {
                    for (int i = 3; i < made; i++)
                        score7 = score7 + 30;
                }
                Score ( score7 );
            }


            if (num == 4 && (suit.equalsIgnoreCase ( "c" ) || suit.equalsIgnoreCase ( "d" ))) {
                if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "n" )) {
                    score8 = 510;
                    for (int i = 4; i < made; i++)
                        score8 = score8 + 100;
                } else if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "y" )) {
                    score8 = 720;
                    for (int i = 4; i < made; i++)
                        score8 = score8 + 200;
                } else {
                    for (int i = 4; i < made; i++)
                        score8 = score8 + 20;
                }
                Score ( score8 );
            }


            if (num == 4 && (suit.equalsIgnoreCase ( "h" ) || suit.equalsIgnoreCase ( "s" ))) {
                if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "n" )) {
                    score9 = 590;
                    for (int i = 4; i < made; i++)
                        score9 = score9 + 100;
                } else if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "y" )) {
                    score9 = 880;
                    for (int i = 4; i < made; i++)
                        score9 = score9 + 200;
                } else {
                    for (int i = 4; i < made; i++)
                        score9 = score9 + 30;
                }
                Score ( score9 );
            }

            if (num == 4 && suit.equalsIgnoreCase ( "NT" )) {
                if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "n" )) {
                    score10 = 610;
                    for (int i = 4; i < made; i++)
                        score10 = score10 + 100;
                } else if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "y" )) {
                    score10 = 920;
                    for (int i = 4; i < made; i++)
                        score10 = score10 + 200;
                } else {
                    for (int i = 4; i < made; i++)
                        score10 = score10 + 30;
                }
                Score ( score10 );
            }


            if (num == 5 && (suit.equalsIgnoreCase ( "c" ) || suit.equalsIgnoreCase ( "d" ))) {
                if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "n" )) {
                    score7 = 550;
                    for (int i = 5; i < made; i++)
                        score7 = score7 + 100;
                } else if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "y" )) {
                    score7 = 800;
                    for (int i = 5; i < made; i++)
                        score7 = score7 + 200;
                } else {
                    for (int i = 5; i < made; i++)
                        score7 = score7 + 20;
                }
                Score ( score7 );
            }

            if (num == 5 && (suit.equalsIgnoreCase ( "h" ) || suit.equalsIgnoreCase ( "s" ))) {
                if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "n" )) {
                    score11 = 650;
                    for (int i = 5; i < made; i++)
                        score11 = score11 + 100;
                } else if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "y" )) {
                    score11 = 1000;
                    for (int i = 5; i < made; i++)
                        score11 = score11 + 200;
                } else {
                    for (int i = 5; i < made; i++)
                        score11 = score11 + 30;
                }
                Score ( score11 );
            }


            if (num == 5 && suit.equalsIgnoreCase ( "NT" )) {
                if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "n" )) {
                    score12 = 670;
                    for (int i = 5; i < made; i++)
                        score12 = score12 + 100;
                } else if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "y" )) {
                    score12 = 1040;
                    for (int i = 5; i < made; i++)
                        score12 = score12 + 200;
                } else {
                    for (int i = 5; i < made; i++)
                        score12 = score12 + 30;
                }
                Score ( score12 );
            }


            if (num == 6 && (suit.equalsIgnoreCase ( "c" ) || suit.equalsIgnoreCase ( "d" ))) {
                if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "n" )) {
                    score13 = 1090;
                    for (int i = 6; i < made; i++)
                        score13 = score13 + 100;
                } else if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "y" )) {
                    score13 = 1380;
                    for (int i = 6; i < made; i++)
                        score13 = score13 + 200;
                } else {
                    for (int i = 6; i < made; i++)
                        score13 = score13 + 20;
                }
                Score ( score13 );
            }


            if (num == 6 && (suit.equalsIgnoreCase ( "h" ) || suit.equalsIgnoreCase ( "s" ))) {
                if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "n" )) {
                    score14 = 1210;
                    for (int i = 6; i < made; i++)
                        score14 = score14 + 100;
                } else if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "y" )) {
                    score14 = 1620;
                    for (int i = 6; i < made; i++)
                        score14 = score14 + 200;
                } else {
                    for (int i = 6; i < made; i++)
                        score14 = score14 + 30;
                }
                Score ( score14 );
            }

            if (num == 6 && suit.equalsIgnoreCase ( "NT" )) {
                if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "n" )) {
                    score15 = 1230;
                    for (int i = 6; i < made; i++)
                        score15 = score15 + 100;
                } else if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "y" )) {
                    score15 = 1660;
                    for (int i = 6; i < made; i++)
                        score15 = score15 + 200;
                } else {
                    for (int i = 6; i < made; i++)
                        score15 = score15 + 30;
                }
                Score ( score15 );
            }

            if (num == 7 && (suit.equalsIgnoreCase ( "c" ) || suit.equalsIgnoreCase ( "d" ))) {
                if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "n" )) {
                    score16 = 1630;
                } else if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "y" )) {
                    score16 = 1960;
                }
                Score ( score16 );
            }


            if (num == 7 && (suit.equalsIgnoreCase ( "h" ) || suit.equalsIgnoreCase ( "s" ))) {
                if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "n" )) {
                    score17 = 1770;
                } else if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "y" )) {
                    score17 = 2240;
                }
                Score ( score17 );
            }


            if (num == 7 && suit.equalsIgnoreCase ( "NT" )) {
                if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "n" )) {
                    score18 = 1790;
                } else if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "y" )) {
                    score18 = 2280;
                }
                Score ( score18 );
            }
        } else {
//            print ( "Down by? (1-13): " );
//            down = getInt ( 1, 13 );
            if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "n" )) {
                score = 100;
                if (down <= 3) {
                    for (int i = 1; i < 4; i++)
                        score = score + 200;
                } else {
                    for (int i = 1; i < 4; i++)
                        score = score + 200;
                    for (int i = 4; i < down; i++)
                        score = score + 300;
                }
            } else if (dbl.equalsIgnoreCase ( "y" ) && redbl.equalsIgnoreCase ( "y" )) {
                score = 200;
                if (down <= 3) {
                    for (int i = 1; i < 3; i++)
                        score = score + 400;
                } else {
                    for (int i = 1; i < 3; i++)
                        score = score + 400;
                    for (int i = 3; i < down; i++)
                        score = score + 600;
                }
            } else {
                score = 50;
                for (int i = 1; i <= down; i++)
                    score = score + 50;
            }
            Score ( score );
        }
        System.out.println ("BridgeScore: " + BridgeScore.score);
        return (win?BridgeScore.score:(-1*BridgeScore.score));
    }
//
//
//    public static int getInt(int a, int b) {
//        int mid;
//        mid = readInt ();
//
//        while (mid < a || mid > b) {
//            print ( "Enter a value between " + a + " and " + b + ": " );
//            mid = readInt ();
//        }
//
//
//        return mid;
//    }
//
//
//    public static String getChoice() {
//        String guess;
//        guess = readString ();
//        while (!guess.equalsIgnoreCase ( "y" ) && !guess.equalsIgnoreCase ( "n" )) {
//            print ( "Enter another choice: " );
//            guess = readString ();
//        }
//
//
//        return guess;
//    }

    private static void Score (int score) {
        BridgeScore.score = score;
    }
}
