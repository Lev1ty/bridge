// Bridge V2.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"


int main()
try 
{
	srand(time(nullptr));
	int round_num = 0;
	while (true)
	{

//Redefintion

		Translate translator{};
		Player players[4];
		for (int i = 0; i < 4; ++i)
		{
			players[i] = Player{ false,i };
		}

		vector<Bid> bid_history{};
		int last_bid_val = 0;
		bool doub = false;
		bool redoub = false;
		bool contract_bid = false;
		int pass_cnt = 0;
		int bid_cnt = 0;
		int dir_rng = rand() % 4;
		/*int doub_pair = 0;
		bool doub_pair_b = false;*/

		int deck[52];
		for (int i = 0; i < 52; ++i)
		{
			deck[i] = i;
		}
		for (int i = 51, temp; i >= 1; --i)
		{
			int j = rand() % i;
			temp = deck[i];
			deck[i] = deck[j];
			deck[j] = temp;
		}
		for (int i = 0, dir = 0; i < 52; ++i, dir = i / 13)
		{
			players[dir].hand.push_back(Card{ deck[i] });
		}

		cout << "Game " << ++round_num << "**********" << endl;
		/*cout << "End game? (Y/N) ";
		char end; while (cin)
		{
			cin >> end;
			if (end == 'Y' || end == 'N')
				break;
		}
		if (end == 'Y') return 1;*/

//**********

//Auction

		for (string in; !(contract_bid && pass_cnt >= 3 && bid_cnt >= 4); (++dir_rng) %= 4, ++bid_cnt)
		{
			if (bid_cnt == 4 && pass_cnt == 4)
			{
				break;
			}
			cout << "Bid from " << translator.Player_Dir(dir_rng, false) << ": ";
			do { 
				cin >> in; last_bid_val = translator.Bid_Num(in); 
			} 
			while (last_bid_val == -1 || (bid_history.size() >= 1 && 
				(last_bid_val <= bid_history.back().Val() && last_bid_val >= 3)));
			switch (last_bid_val)
			{
			case 0: ++pass_cnt; break;
			case 1: 
			{ 
				if (!doub)
				{
					/*doub_pair = dir_rng % 2;*/
					doub = true;
				} 
				/*else if (dir_rng % 2 != doub_pair)
				{
					doub_pair = dir_rng % 2;
				}*/
				pass_cnt = 0; 
				break;
			}
			case 2:
			{
				if (!doub)
				{
					cout << "Cannot redouble before doubling." << endl;
					--dir_rng; --bid_cnt; continue;
				}
				/*else if (dir_rng % 2 == doub_pair)
				{
					cout << "Cannot redouble own double." << endl;
					--dir_rng; --bid_cnt; continue;
				}*/
				else
				{
					redoub = true; 
					pass_cnt = 0;
				}
				break;
			}
			default: pass_cnt = 0; contract_bid = true; break;
			}
			players[dir_rng].auc.push_back(Bid{ last_bid_val,dir_rng });
			bid_history.push_back(Bid{ last_bid_val,dir_rng });
		}
		int from = -1, contract = -1, i1 = -1;
		if (pass_cnt == 4 && bid_cnt == 4)
		{
			cout << "Auction has been passed out." << endl;
			continue;
		}
		else
		{
			cout << "Auction has ended after " << bid_cnt << " rounds by the bid of ";
			for (i1 = bid_history.size() - 1; i1 >= 0; --i1)
			{
				if (bid_history.at(i1).Val() >= 3)
				{
					contract = bid_history.at(i1).Val();
					break;
				}
			}
			for (; i1 >= 0; --i1)
			{
				if ((bid_history.at(i1).Val() - contract) % 5 == 0)
				{
					from = bid_history.at(i1).Dir();
					contract = bid_history.at(i1).Val();
				}
			}
			cout << translator.Bid_Val(contract);
			cout << " from ";
			cout << translator.Player_Dir(from, true);
			cout << "." << endl;
		}
		Bid last_bid{ contract,from };
		if (redoub)
		{
			cout << "Contract is redoubled by ";
			for (int i = bid_history.size() - 1; i >= 0; --i)
			{
				if (bid_history.at(i).Val() == 2)
				{
					cout << translator.Player_Dir(bid_history.at(i).Dir(), true) << "." << endl;
					break;
				}
			}
		}
		else if (doub)
		{
			cout << "Contract is doubled by ";
			for (int i = bid_history.size() - 1; i >= 0; --i)
			{
				if (bid_history.at(i).Val() == 1)
				{
					cout << translator.Player_Dir(bid_history.at(i).Dir(), true) << "." << endl;
					break;
				}
			}
		}

//**********

//Play

		/*for (int i = 0; i < 4; ++i)
		{
			cout << i << ": ";
			for (Card& card : players[i].hand)
			{
				cout << card.Val() << ' ';
			}
			cout << endl;
		}*/
		vector<Card> trick{};
		for (int dir = last_bid.Dir() + 1, temp_cnt = 0; temp_cnt < 8; (++dir) %= 4, ++temp_cnt)
		{
			/*for (auto it = players[dir].hand.begin() + 1; it != players[dir].hand.end(); ++it)
			{
				cout << translator.Card_Val(it->Val(), false) << ' ';
			}
			cout << endl;*/
			/*string rank{}, suit{};
			cout << endl;
			cout << "Spade:";
			for (auto it = players[dir].hand.begin() + 1; it != players[dir].hand.end(); ++it)
			{
				if (it->Val() / 13 == 0)
				{
					translator.Card_Val(it->Val(), suit, rank);
					cout << ' ' << rank;
				}
			}
			cout << endl;
			cout << "Heart:";
			for (auto it = players[dir].hand.begin() + 1; it != players[dir].hand.end(); ++it)
			{
				if (it->Val() / 13 == 1)
				{
					translator.Card_Val(it->Val(), suit, rank);
					cout << ' ' << rank;
				}
			}
			cout << endl;
			cout << "Diamond:";
			for (auto it = players[dir].hand.begin() + 1; it != players[dir].hand.end(); ++it)
			{
				if (it->Val() / 13 == 2)
				{
					translator.Card_Val(it->Val(), suit, rank);
					cout << ' ' << rank;
				}
			}
			cout << endl;
			cout << "Club:";
			for (auto it = players[dir].hand.begin() + 1; it != players[dir].hand.end(); ++it)
			{
				if (it->Val() / 13 == 3)
				{
					translator.Card_Val(it->Val(), suit, rank);
					cout << ' ' << rank;
				}
			}
			cout << endl;
			cout << endl;*/
			cout << endl;
			cout << translator.Player_Dir(players[dir].Dir(), true) << endl;
			translator.Print_Sorted(players[dir].hand);
			cout << endl;
			cout << "Card to be played e.g. 4 of Spades: ";
			string rank{}, suit{};
			string waste{};
			cin >> rank >> waste >> suit;
			int card_val = translator.Card_Num(suit, rank);
			trick.push_back(Card{ card_val, dir });
			for (auto it = players[dir].hand.begin(); it != players[dir].hand.end(); ++it)
			{
				if (it->Val() == card_val)
				{
					//cout << it - players[dir].hand.begin() << endl;
					players[dir].hand.erase(it);
					break;
				}
			}
			translator.Print_Sorted(players[dir].hand);
			if (trick.size() == 4)
			{
				cout << endl;
				for (auto it = trick.begin(); it != trick.end(); ++it)
				{
					cout << translator.Player_Dir(it->Dir(), true) << ": " << translator.Card_Val(it->Val(), true) << endl;
				}
				trick.clear();
			}
		}


//**********
	}
    return 0;
}
catch (const std::exception&)
{
	cout << "Error in Main" << endl;
}

