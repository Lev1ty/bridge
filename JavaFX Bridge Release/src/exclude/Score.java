//package exclude;
//
//import hsa.Console;
//
//public class BridgeScore
//{
//    static Console c;
//
//    public static void main (String[] args)
//    {
//	c = new Console ();
//
//	int num, made;
//	int score = 70;
//	int score2 = 80;
//	int score3 = 90;
//	int score4 = 110;
//	int score5 = 120;
//	int score6 = 140;
//	int score7 = 400;
//	int score8 = 130;
//	int score9 = 420;
//	int score10 = 430;
//	int score11 = 450;
//	int score12 = 460;
//	int score13 = 920;
//	int score14 = 980;
//	int score15 = 990;
//	int score16 = 1440;
//	int score17 = 1510;
//	int score18 = 1520;
//	String suit,dbl,redbl;
//
//	c.print ("Please enter the number of the final bid (1-7): ");
//	num = c.readInt ();
//
//	c.print ("Please enter the suit of the final bid (c,d,h,s,nt): ");
//	suit = c.readString ();
//
//	c.print ("Please enter the number of tricks made (1-7): ");
//	made = c.readInt ();
//
//	c.print ("Double? (y/n) ");
//	dbl = c.readString ();
//
//	c.print ("Redouble? (y/n) ");
//	redbl = c.readString ();
//
//
//	if (num == 1 && (suit.equalsIgnoreCase ("c") || suit.equalsIgnoreCase ("d")))
//	{
//	    if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("n"))
//	    {
//		score = 140;
//		for (int i = 1 ; i < made ; i++)
//		    score = score + 100;
//	    }
//
//	    else if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("y"))
//	    {
//		score = 230;
//		for (int i = 1 ; i < made ; i++)
//		    score = score + 200;
//	    }
//
//	    else
//	    {
//		for (int i = 1 ; i < made ; i++)
//		    score = score + 20;
//	    }
//	    c.print (score);
//	}
//
//	if (num == 1 && (suit.equalsIgnoreCase ("h") || suit.equalsIgnoreCase ("s")))
//	{
//	    if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("n"))
//	    {
//		score2 = 160;
//		for (int i = 1 ; i < made ; i++)
//		    score2 = score2 + 100;
//	    }
//
//	    else if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("y"))
//	    {
//		score2 = 520;
//		for (int i = 1 ; i < made ; i++)
//		    score2 = score2 + 200;
//	    }
//	    else
//	    {
//		for (int i = 1 ; i < made ; i++)
//		    score2 = score2 + 30;
//	    }
//	    c.print (score2);
//	}
//
//
//	if (num == 1 && suit.equalsIgnoreCase ("NT"))
//	{
//	    if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("n"))
//	    {
//		score3 = 180;
//		for (int i = 1 ; i < made ; i++)
//		    score3 = score3 + 100;
//	    }
//
//	    else if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("y"))
//	    {
//		score3 = 560;
//		for (int i = 1 ; i < made ; i++)
//		    score3 = score3 + 200;
//	    }
//
//	    else
//	    {
//		for (int i = 1 ; i < made ; i++)
//		    score3 = score3 + 30;
//	    }
//	    c.print (score3);
//	}
//
//	if (num == 2 && (suit.equalsIgnoreCase ("c") || suit.equalsIgnoreCase ("d")))
//	{
//	    if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("n"))
//	    {
//		score3 = 180;
//		for (int i = 2 ; i < made ; i++)
//		    score3 = score3 + 100;
//	    }
//	    else if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("y"))
//	    {
//		score3 = 560;
//		for (int i = 2 ; i < made ; i++)
//		    score3 = score3 + 200;
//	    }
//	    else
//	    {
//		for (int i = 2 ; i < made ; i++)
//		    score3 = score3 + 20;
//	    }
//	    c.print (score3);
//	}
//
//	if (num == 2 && (suit.equalsIgnoreCase ("h") || suit.equalsIgnoreCase ("s")))
//	{
//	    if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("n"))
//	    {
//		score4 = 470;
//		for (int i = 2 ; i < made ; i++)
//		    score4 = score4 + 100;
//	    }
//
//	    else if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("y"))
//	    {
//		score4 = 640;
//		for (int i = 2 ; i < made ; i++)
//		    score4 = score4 + 200;
//	    }
//	    else
//	    {
//		for (int i = 2 ; i < made ; i++)
//		    score4 = score4 + 30;
//	    }
//	    c.print (score4);
//	}
//
//	if (num == 2 && suit.equalsIgnoreCase ("NT"))
//	{
//	    if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("n"))
//	    {
//		score5 = 490;
//		for (int i = 2 ; i < made ; i++)
//		    score5 = score5 + 100;
//	    }
//
//	    else if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("y"))
//	    {
//		score5 = 680;
//		for (int i = 2 ; i < made ; i++)
//		    score5 = score5 + 200;
//	    }
//	    else
//	    {
//		for (int i = 2 ; i < made ; i++)
//		    score5 = score5 + 30;
//	    }
//	    c.print (score5);
//	}
//
//	if (num == 3 && (suit.equalsIgnoreCase ("c") || suit.equalsIgnoreCase ("d")))
//	{
//	    if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("n"))
//	    {
//		score4 = 470;
//		for (int i = 3 ; i < made ; i++)
//		    score4 = score4 + 100;
//	    }
//
//	    else if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("y"))
//	    {
//		score4 = 640;
//		for (int i = 3 ; i < made ; i++)
//		    score4 = score4 + 200;
//	    }
//	    else
//	    {
//		for (int i = 3 ; i < made ; i++)
//		    score4 = score4 + 20;
//	    }
//	    c.print (score4);
//	}
//
//	if (num == 3 && (suit.equalsIgnoreCase ("h") || suit.equalsIgnoreCase ("s")))
//	{
//	    if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("n"))
//	    {
//		score6 = 530;
//		for (int i = 3 ; i < made ; i++)
//		    score6 = score6 + 100;
//	    }
//
//	    else if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("y"))
//	    {
//		score6 = 760;
//		for (int i = 3 ; i < made ; i++)
//		    score6 = score6 + 200;
//	    }
//	    else
//	    {
//		for (int i = 3 ; i < made ; i++)
//		    score6 = score6 + 30;
//	    }
//	    c.print (score6);
//	}
//
//
//	if (num == 3 && suit.equalsIgnoreCase ("NT"))
//	{
//	    if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("n"))
//	    {
//		score7 = 550;
//		for (int i = 3 ; i < made ; i++)
//		    score7 = score7 + 100;
//	    }
//
//	    else if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("y"))
//	    {
//		score7 = 800;
//		for (int i = 3 ; i < made ; i++)
//		    score7 = score7 + 200;
//	    }
//	    else
//	    {
//		for (int i = 3 ; i < made ; i++)
//		    score7 = score7 + 30;
//	    }
//	    c.print (score7);
//	}
//
//
//	if (num == 4 && (suit.equalsIgnoreCase ("c") || suit.equalsIgnoreCase ("d")))
//	{
//	    if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("n"))
//	    {
//		score8 = 510;
//		for (int i = 4 ; i < made ; i++)
//		    score8 = score8 + 100;
//	    }
//
//	    else if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("y"))
//	    {
//		score7 = 720;
//		for (int i = 4 ; i < made ; i++)
//		    score8 = score8 + 200;
//	    }
//	    else
//	    {
//		for (int i = 4 ; i < made ; i++)
//		    score8 = score8 + 20;
//	    }
//	    c.print (score8);
//	}
//
//
//	if (num == 4 && (suit.equalsIgnoreCase ("h") || suit.equalsIgnoreCase ("s")))
//	{
//	    if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("n"))
//	    {
//		score9 = 590;
//		for (int i = 4 ; i < made ; i++)
//		    score9 = score9 + 100;
//	    }
//
//	    else if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("y"))
//	    {
//		score9 = 880;
//		for (int i = 4 ; i < made ; i++)
//		    score9 = score9 + 200;
//	    }
//	    else
//	    {
//		for (int i = 4 ; i < made ; i++)
//		    score9 = score9 + 30;
//	    }
//	    c.print (score9);
//	}
//
//	if (num == 4 && suit.equalsIgnoreCase ("NT"))
//	{
//	    if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("n"))
//	    {
//		score10 = 610;
//		for (int i = 4 ; i < made ; i++)
//		    score10 = score10 + 100;
//	    }
//
//	    else if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("y"))
//	    {
//		score10 = 920;
//		for (int i = 4 ; i < made ; i++)
//		    score10 = score10 + 200;
//	    }
//	    else
//	    {
//		for (int i = 4 ; i < made ; i++)
//		    score10 = score10 + 30;
//	    }
//	    c.print (score10);
//	}
//
//
//	if (num == 5 && (suit.equalsIgnoreCase ("c") || suit.equalsIgnoreCase ("d")))
//	{
//	    if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("n"))
//	    {
//		score7 = 550;
//		for (int i = 5 ; i < made ; i++)
//		    score7 = score7 + 100;
//	    }
//
//	    else if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("y"))
//	    {
//		score7 = 800;
//		for (int i = 5 ; i < made ; i++)
//		    score7 = score7 + 200;
//	    }
//	    else
//	    {
//		for (int i = 5 ; i < made ; i++)
//		    score7 = score7 + 20;
//	    }
//	    c.print (score7);
//	}
//
//	if (num == 5 && (suit.equalsIgnoreCase ("h") || suit.equalsIgnoreCase ("s")))
//	{
//	    if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("n"))
//	    {
//		score11 = 650;
//		for (int i = 5 ; i < made ; i++)
//		    score11 = score11 + 100;
//	    }
//
//	    else if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("y"))
//	    {
//		score11 = 1000;
//		for (int i = 5 ; i < made ; i++)
//		    score11 = score11 + 200;
//	    }
//	    else
//	    {
//		for (int i = 5 ; i < made ; i++)
//		    score11 = score11 + 30;
//	    }
//	    c.print (score11);
//	}
//
//
//	if (num == 5 && suit.equalsIgnoreCase ("NT"))
//	{
//	    if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("n"))
//	    {
//		score12 = 550;
//		for (int i = 5 ; i < made ; i++)
//		    score12 = score12 + 100;
//	    }
//
//	    else if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("y"))
//	    {
//		score12 = 800;
//		for (int i = 5 ; i < made ; i++)
//		    score12 = score12 + 200;
//	    }
//	    else
//	    {
//		for (int i = 5 ; i < made ; i++)
//		    score12 = score12 + 30;
//	    }
//	    c.print (score12);
//	}
//
//
//	if (num == 6 && (suit.equalsIgnoreCase ("c") || suit.equalsIgnoreCase ("d")))
//	{
//	    if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("n"))
//	    {
//		score13 = 1090;
//		for (int i = 6 ; i < made ; i++)
//		    score13 = score13 + 100;
//	    }
//
//	    else if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("y"))
//	    {
//		score13 = 1380;
//		for (int i = 6 ; i < made ; i++)
//		    score13 = score13 + 200;
//	    }
//	    else
//	    {
//		for (int i = 6 ; i < made ; i++)
//		    score13 = score13 + 20;
//	    }
//	    c.print (score13);
//	}
//
//
//	if (num == 6 && (suit.equalsIgnoreCase ("h") || suit.equalsIgnoreCase ("s")))
//	{
//	    if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("n"))
//	    {
//		score14 = 1210;
//		for (int i = 6 ; i < made ; i++)
//		    score14 = score14 + 100;
//	    }
//
//	    else if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("y"))
//	    {
//		score14 = 1620;
//		for (int i = 6 ; i < made ; i++)
//		    score14 = score14 + 200;
//	    }
//	    else
//	    {
//		for (int i = 6 ; i < made ; i++)
//		    score14 = score14 + 30;
//	    }
//	    c.print (score14);
//	}
//
//	if (num == 6 && suit.equalsIgnoreCase ("NT"))
//	{
//	    if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("n"))
//	    {
//		score15 = 1230;
//		for (int i = 6 ; i < made ; i++)
//		    score15 = score15 + 100;
//	    }
//
//	    else if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("y"))
//	    {
//		score15 = 1660;
//		for (int i = 6 ; i < made ; i++)
//		    score15 = score15 + 200;
//	    }
//	    else
//	    {
//		for (int i = 6 ; i < made ; i++)
//		    score15 = score15 + 30;
//	    }
//	    c.print (score15);
//	}
//
//	if (num == 7 && (suit.equalsIgnoreCase ("c") || suit.equalsIgnoreCase ("d")))
//	{
//	    if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("n"))
//	    {
//		score16 = 1630;
//	    }
//
//	    else if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("y"))
//	    {
//		score16 = 1960;
//	    }
//	    c.print (score16);
//	}
//
//
//	if (num == 7 && (suit.equalsIgnoreCase ("h") || suit.equalsIgnoreCase ("s")))
//	{
//	    if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("n"))
//	    {
//		score17 = 1770;
//	    }
//
//	    else if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("y"))
//	    {
//		score17 = 2240;
//	    }
//	    c.print (score17);
//	}
//
//
//	if (num == 7 && suit.equalsIgnoreCase ("NT"))
//	{
//	    if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("n"))
//	    {
//		score18 = 1790;
//	    }
//
//	    else if (dbl.equalsIgnoreCase ("y") && redbl.equalsIgnoreCase ("y"))
//	    {
//		score18 = 2280;
//	    }
//	    c.print (score18);
//	}
//    }
//}
