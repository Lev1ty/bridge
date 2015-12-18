// Bridge v1.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"


int main()
{

	/*Deck deck;
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
	*/
	cout<<"Base example:"<<endl;
	Deck demo_deck;
	Auction demo_auction;
	Score demo_score;
	Player demo_north(0,demo_deck.Deck_()), demo_east(1,demo_deck.Deck_()), demo_south(2,demo_deck.Deck_()), demo_west(3,demo_deck.Deck_());
	demo_auction.Next_Bid(0,"7NT");
	demo_auction.Next_Bid(1,"Pass");
	demo_auction.Next_Bid(2,"Pass");
	demo_auction.Next_Bid(3,"Pass");
	cout<<"Auction so far"<<endl;
	demo_auction.Print_Auction();
	cout<<"Auction private members"<<endl;
	demo_auction.Print_Private();
	cout<<"North Player hand:"<<endl;
	demo_north.Print_Hand();
	cout<<"East Player hand."<<endl;
	demo_east.Print_Hand();
	cout<<"South Player hand."<<endl;
	demo_south.Print_Hand();
	cout<<"West Player hand."<<endl;
	demo_west.Print_Hand();
	string usr_in;
	while (true)
	{
		cout    <<"Auction is a container for Bid."<<endl
			<<"Deck is a container for Card."<<endl
			<<endl
			<<"Menu"<<endl
			<<"---------------------------------------"<<endl
			<<"class: Auction public methods."<<endl
			<<"1.1 Next_Bid: push next bid in auction."<<endl
			<<"1.2 Print_Auction: print private members of all bids contained by auction."<<endl
			<<"1.3 Print_Final: print bid that decides contract for the game."<<endl
			<<"1.4 Pass: return pass counter."<<endl
			<<"1.5 Print_Private: print private members."<<endl
			<<"1.6 End: return bool end corresponding with end of auction."<<endl
			<<"1.7 Auction_: return vector auction."<<endl
			<<"class: Bid public methods."<<endl
			<<"2.1 Level: return level of bid."<<endl
			<<"2.2 Suit: return suit of bid."<<endl
			<<"2.3 Dir: return character dir of bid."<<endl
			<<"2.4 Dir_num: return int version of dir (N=0)."<<endl
			<<"2.5 Print_Private: print private members of class."<<endl
			<<"class: Card public methods."<<endl
			<<"3.1 Assign_Index: assign private member: index, position of card in deck."<<endl
			<<"3.2 Index: return index."<<endl
			<<"3.3 Elem: return elem."<<endl
			<<"3.4 Rank_num: return rank num."<<endl
			<<"3.5 Suit_num: return suit num."<<endl
			<<"3.6 Rank: return string representation of rank_num."<<endl
			<<"3.7 Suit: return string representation of string_num."<<endl
			<<"3.8 Print_Private: print all private members of class."<<endl
			<<"class: Deck public methods."<<endl
			<<"4.1 Shuffle: shuffle deck"<<endl
			<<"4.2 Print_deck: print private members of Card(s)."<<endl
			<<"4.3 Deck_: return vector holding cards."<<endl
			<<"4.4 Access_Card: return Card from Deck"<<endl
			<<"4.5 Remove_Card: remove Card from Deck"<<endl
			<<"class: Player public methods"<<endl
			<<"5.1 Print_Hand: prints player's hand"
			<<"class: Score is in progress."<<endl;
		cin>>usr_in;
	}
    return 0;
}

