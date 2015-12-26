// Bridge v1.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"


int main()
{
	//Deck deck;
	//Auction auc;
	//auc.Next_Bid(Bid('N', "1C"));
	//auc.Next_Bid(Bid('E', "1D"));
	//auc.Next_Bid(Bid('W', "1H"));
	//auc.Next_Bid(Bid('S', "Pass"));
	//auc.Next_Bid(Bid{ 'N',"Pass" });
	//auc.Next_Bid(Bid{ 'E',"Pass" });
	//auc.Next_Bid(Bid{ 'W',"Pass" });
	//auc.Print_Private();
	//deck.Shuffle();
	//deck.Remove_Card("Spade", "A");
	//auc.Print_Auction();
	//auc.Print_Private();
	//deck.Print_Deck();
	//deck.Access_Card("Spade", "Q").Print_Private();
	//deck = Deck();
	//deck.Print_Deck();
	//deck.Shuffle();
	//deck.Print_Deck();
	//Player one(0, deck.Deck_());
	//one.Print_Hand();
	//Player two(1, deck.Deck_());
	//two.Print_Hand();
	while (true)
	{
		static int cnt = 1;
		cout << cnt << "------------" << endl;
		cnt++;
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
	/*cout<<"Base example:"<<endl;
	Deck demo_deck;
	Auction demo_auction;
	Score demo_score;
	Player demo_north(0,demo_deck.Deck_()), demo_east(1,demo_deck.Deck_()), demo_south(2,demo_deck.Deck_()), demo_west(3,demo_deck.Deck_());
	demo_auction.Next_Bid(Bid(0,"1NT"));
	demo_auction.Next_Bid(Bid(1,"Pass"));
	demo_auction.Next_Bid(Bid(2,"Pass"));
	demo_auction.Next_Bid(Bid(3,"2H"));
	cout<<"Auction so far:"<<endl;
	demo_auction.Print_Auction();
	cout<<"Auction private members:"<<endl;
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
	cout << "Auction is a container for Bid." << endl
		<< "Deck is a container for Card." << endl
		<< endl
		<< "Menu" << endl
		<< "---------------------------------------" << endl
		<< "0 END" << endl
		<< endl
		<< "class: Auction public methods." << endl
		<< "1.1 Next_Bid: push next bid in auction." << endl
		<< "1.2 Print_Auction: print private members of all bids contained by auction." << endl
		<< "1.3 Print_Final: print bid that decides contract for the game." << endl
		<< "1.4 Pass: return pass counter." << endl
		<< "1.5 Print_Private: print private members." << endl
		<< "1.6 End: return bool end corresponding with end of auction." << endl
		<< "1.7 Auction_: return vector auction." << endl
		<< endl
		<< "class: Bid public methods." << endl
		<< endl
		<< "2.1 Level: return level of bid." << endl
		<< "2.2 Suit: return suit of bid." << endl
		<< "2.3 Dir: return character dir of bid." << endl
		<< "2.4 Dir_num: return int version of dir (N=0)." << endl
		<< "2.5 Print_Private: print private members of class." << endl
		<< endl
		<< "class: Card public methods." << endl
		<< endl
		<< "3.1 Assign_Index: assign private member: index, position of card in deck." << endl
		<< "3.2 Index: return index." << endl
		<< "3.3 Elem: return elem." << endl
		<< "3.4 Rank_num: return rank num." << endl
		<< "3.5 Suit_num: return suit num." << endl
		<< "3.6 Rank: return string representation of rank_num." << endl
		<< "3.7 Suit: return string representation of string_num." << endl
		<< "3.8 Print_Private: print all private members of class." << endl
		<< endl
		<< "class: Deck public methods." << endl
		<< endl
		<< "4.1 Shuffle: shuffle deck" << endl
		<< "4.2 Print_deck: print private members of Card(s)." << endl
		<< "4.3 Deck_: return vector holding cards." << endl
		<< "4.4 Access_Card: return Card from Deck" << endl
		<< "4.5 Remove_Card: remove Card from Deck" << endl
		<< endl
		<< "class: Player public methods" << endl
		<< endl
		<< "5.1 Print_Hand: prints player's hand" << endl
		<< endl
		<< "class: Score is in progress." << endl
		<< endl;
	while (true)
	{
		cout << endl;
		cin >> usr_in;
		if (usr_in.compare("0") == 0)
		{
			break;
		}
		if (usr_in.compare("1.1") == 0)
		{
			char dir; string bid;
			cout << "Enter direction of bidder (char): " << endl;
			cin >> dir;
			cout << "Enter bid e.g. 6NT: " << endl;
			cin >> bid;
			demo_auction.Next_Bid(Bid(dir, bid));
		}
		if (usr_in.compare("1.2") == 0)
		{
			demo_auction.Print_Auction();
		}
		if (usr_in.compare("1.3") == 0)
		{
			demo_auction.Print_Final();
		}
		if (usr_in.compare("1.4") == 0)
		{
			cout << demo_auction.Pass();
		}
		if (usr_in.compare("1.5") == 0)
		{
			demo_auction.Print_Private();
		}
		if (usr_in.compare("1.6") == 0)
		{
			cout << demo_auction.End();
		}
		if (usr_in.compare("1.7") == 0)
		{
			for (Bid bid : demo_auction.Auction_())
			{
				bid.Print_Private();
			}
		}
		if (usr_in[0] == '2')
		{
			cout << "Will be performing methods on first bid." << endl;
			if (usr_in.compare("2.1") == 0)
			{
				cout << demo_auction.Auction_()[0].Level();
			}
			if (usr_in.compare("2.2") == 0)
			{
				cout << demo_auction.Auction_()[0].Suit();
			}
			if (usr_in.compare("2.3") == 0)
			{
				cout << demo_auction.Auction_()[0].Dir();
			}
			if (usr_in.compare("2.4") == 0)
			{
				cout << demo_auction.Auction_()[0].Dir_Num();
			}
			if (usr_in.compare("2.5") == 0)
			{
				demo_auction.Auction_()[0].Print_Private();
			}
		}
		if (usr_in[0] == '3')
		{
			cout << "Will be performing methods on first card." << endl;
			if (usr_in.compare("3.1") == 0)
			{
				int index;
				cout << "Index number to be assigned: " << endl;
				cin >> index;
				demo_deck.Deck_()[0].Assign_Index(index);
				cout << "Changed index is:" << endl;
				cout << demo_deck.Deck_()[0].Index() << endl;
				cout << "Assigned index is targeted towards copied vector returned from Deck_ to protect private member deck." << endl;
				cout << "Shuffle method will change index of private member Deck" << endl;
			}
			if (usr_in.compare("3.2") == 0)
			{
				cout << demo_deck.Deck_()[0].Index();
			}
			if (usr_in.compare("3.3") == 0)
			{
				cout << demo_deck.Deck_()[0].Elem();
			}
			if (usr_in.compare("3.4") == 0)
			{
				cout << demo_deck.Deck_()[0].Rank_num();
			}
			if (usr_in.compare("3.5") == 0)
			{
				cout << demo_deck.Deck_()[0].Suit_num();
			}
			if (usr_in.compare("3.6") == 0)
			{
				cout << demo_deck.Deck_()[0].Rank();
			}
			if (usr_in.compare("3.7") == 0)
			{
				cout << demo_deck.Deck_()[0].Suit();
			}
			if (usr_in.compare("3.8") == 0)
			{
				demo_deck.Deck_()[0].Print_Private();
			}
		}
		if (usr_in[0] == '4')
		{
			if (usr_in.compare("4.1") == 0)
			{
				demo_deck.Shuffle();
				demo_deck.Print_Deck();
			}
			if (usr_in.compare("4.2") == 0)
			{
				demo_deck.Print_Deck();
			}
			if (usr_in.compare("4.3") == 0)
			{
				for (Card card : demo_deck.Deck_())
				{
					card.Print_Private();
				}
			}
			if (usr_in.compare("4.4") == 0)
			{
				string suit, rank;
				cout << "Suit desired e.g. Spade:" << endl;
				cin >> suit;
				cout << "Rank desired e.g. A:" << endl;
				cin >> rank;
				demo_deck.Access_Card(suit, rank).Print_Private();
			}
			if (usr_in.compare("4.5") == 0)
			{
				string suit, rank;
				cout << "Suit desired e.g. Spade:" << endl;
				cin >> suit;
				cout << "Rank desired e.g. A:" << endl;
				cin >> rank;
				demo_deck.Remove_Card(suit, rank);
				demo_deck.Print_Deck();
			}
		}
		if (usr_in.compare("5.1") == 0)
		{
			cout << "North ";
			demo_north.Print_Hand();
			cout << endl;
			cout << "East ";
			demo_east.Print_Hand();
			cout << endl;
			cout << "South ";
			demo_south.Print_Hand();
			cout << endl;
			cout << "West ";
			demo_west.Print_Hand();
		}
		cout << endl;
	}*/
    return 0;
}

