#include "stdafx.h"
#include "Auction.h"


Auction::Auction()
{
	pass_cnt = 0;
	end_bool = false;
	auction.clear();
}


Auction::~Auction()
{
}


void Auction::Next_Bid(Bid bid)
{
	auction.push_back(bid);
	if (bid.Level() == 7 && bid.Suit() == 0) {
		++pass_cnt;
	}
	if (pass_cnt >= 3 && auction.size() >= 4) {
		end();
	}
}


Bid Auction::Last()
{
	return last;
}


void Auction::Print_Auction()
{
	for (Bid& bid : auction) {
		bid.Print_Private();
	}
}


void Auction::Print_Final()
{
	last.Print_Private();
}


void Auction::init_final()
{
	last = auction[auction.size() - 4];
}


void Auction::end()
{
	init_final();
	end_bool = true;
	if (last.Level() == 7 && last.Suit() == 0)
	{
		cout << "The game has been passed out." << endl;
	}
	else
	{
		cout << "The auction has ended." << endl;
	}
}


int Auction::Pass()
{
	return pass_cnt;
}


void Auction::Print_Private()
{
	cout << "pass_cnt " << pass_cnt << ' ';
	cout << "end_bool " << end_bool << ' ';
	cout << endl;
}


bool Auction::End()
{
	return end_bool;
}


vector<Bid> Auction::Auction_()
{
	return auction;
}
