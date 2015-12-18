// Bridge v1.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"


int main()
{

	Deck deck;
	/*Auction auc;
	auc.Next_Bid(Bid('N', "1C"));
	auc.Next_Bid(Bid('E', "1D"));
	auc.Next_Bid(Bid('W', "1H"));
	auc.Next_Bid(Bid('S', "Pass"));
	auc.Next_Bid(Bid{ 'N',"Pass" });
	auc.Next_Bid(Bid{ 'E',"Pass" });
	auc.Next_Bid(Bid{ 'W',"Pass" });
	auc.Print_Private();*/
	deck.Shuffle();
	deck.Remove_Card("Spade", "A");
	/*auc.Print_Auction();
	auc.Print_Private();*/
	deck.Print_Deck();
	deck.Access_Card("Spade", "Q").Print_Private();
	deck = Deck();
	deck.Print_Deck();
	deck.Shuffle();
	deck.Print_Deck();
	Player one(0, deck.Deck_());
	one.Print_Hand();
	Player two(1, deck.Deck_());
	two.Print_Hand();

	while (true)
	{
		int dir_num = rand() % 4;
		string end_program = "";
		Deck deck = Deck();
		deck.Shuffle();
		Auction auction = Auction();
		Score score = Score();
		Player north = Player(0, deck.Deck_()), east = Player(1, deck.Deck_()), south = Player(2, deck.Deck_()), west = Player(3, deck.Deck_());
		while (true)
		{
			while (!auction.End())
			{
				string usr_bid;
				cin >> usr_bid;
				auction.Next_Bid(Bid(dir_num, usr_bid));
				////#debug
				//auction.Print_Private();
				//auction.Print_Auction();
			}
			//if (auction.Last().Level() == 7 && auction.Last().Suit() == 0)
			//{
			//	cout << "Game Passed Out." << endl;
			//	break;
			//}
			//else
			//{
			//	//#debug
			//	cout << "Out of Auction." << endl;
			//	auction.Print_Final();
			//}
			//ending updates
			dir_num = (dir_num < 4 ? ++dir_num : dir_num = 0);
		}
	}
    return 0;
}

