// Bridge V2.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"


int main()
{
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

		int last_bid_val = 0;
		bool doub = false;
		bool redoub = false;
		int pass_cnt = 0;
		int bid_cnt = 0;
		int dir_rng = rand() % 3;

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
		cout << "End game? (Y/N) ";
		char end; while (cin)
		{
			cin >> end;
			if (end == 'Y' || end == 'N')
				break;
		}
		if (end == 'Y') return 1;

//**********

//Auction

		for (; !(pass_cnt >= 3 && bid_cnt >= 4); ++dir_rng, ++bid_cnt)
		{
			cout << "Bid from " << (dir_rng %= 4) << ": ";
			cin >> last_bid_val;
			players[dir_rng].auc.push_back(Bid{ last_bid_val,dir_rng });
			switch (last_bid_val)
			{
			case 0: ++pass_cnt; break;
			case 1: doub = true; break;
			case 2: redoub = true; break;
			default:
				break;
			}
		}
		cout << "Auction has ended after " << bid_cnt - 1
			<< " rounds by the bid of " << last_bid_val
			<< " from " << dir_rng - 1
			<< "." << endl;
		if (redoub) cout << "Contract is redoubled." << endl;
		else if (doub) cout << "Contract is doubled." << endl;

//**********

//Play

		for (int i = 0; i < 4; ++i)
		{
			cout << i << ": ";
			for (Card& card : players[i].hand)
			{
				cout << card.Val() << ' ';
			}
			cout << endl;
		}

//**********
	}
    return 0;
}

