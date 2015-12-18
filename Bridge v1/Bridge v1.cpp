// Bridge v1.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"


int main()
{
	/*Deck deck;
	Auction auc;
	auc.Next_Bid(Bid('N', "1C"));
	auc.Next_Bid(Bid('E', "1D"));
	auc.Next_Bid(Bid('W', "1H"));
	auc.Next_Bid(Bid('S', "Pass"));
	auc.Next_Bid(Bid{ 'N',"Pass" });
	auc.Next_Bid(Bid{ 'E',"Pass" });
	auc.Next_Bid(Bid{ 'W',"Pass" });
	auc.Print_Private();
	deck.Shuffle();
	deck.Remove_Card("Spade", "A");
	auc.Print_auction();
	auc.Print_Private();
	deck.Print_deck();
	deck.Access_Card("Spade", "Q").Print_Private();
	deck = Deck();
	deck.Print_deck();*/
	while (true)
	{
		int dir_num = rand() % 4;
		string end_program = "";
		Deck deck = Deck();
		Auction auction = Auction();
		Score score = Score();
		Player north = Player(), east = Player(), south = Player(), west = Player();
		while (true)
		{
			while (!auction.End())
			{
				string usr_bid;
				cin >> usr_bid;
				auction.Next_Bid(Bid(dir_num, usr_bid));
				//debug
				auction.Print_Private();
				auction.Print_Auction();
			}
			//debug
			cout << "Out of Auction" << endl;
			auction.Print_Final();
			//ending updates
			dir_num = (dir_num < 4 ? ++dir_num : dir_num = 0);
		}
	}
    return 0;
}

