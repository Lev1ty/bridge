// Bridge v1.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"


int main()
{
	Deck deck;
	Auction auc;
	auc.Next_Bid(Bid('N', "1C"));
	auc.Next_Bid(Bid('E', "1D"));
	auc.Next_Bid(Bid('W', "1H"));
	deck.Shuffle();
	deck.Remove_Card("Spade", "A");
	auc.Print_auction();
	deck.Print_deck();
    return 0;
}

