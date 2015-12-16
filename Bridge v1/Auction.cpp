#include "stdafx.h"
#include "Auction.h"


Auction::Auction()
{
	pass_cnt = 0;
	auction.clear();
}


Auction::~Auction()
{
}


void Auction::Next_Bid(Bid& bid)
{
	auction.push_back(bid);
	if (bid.Val() == 43) {
		++pass_cnt;
	}
	if (pass_cnt == 3) {
		end();
	}
}

Bid Auction::Final()
{
	return this->final;
}


void Auction::Print_auction()
{
	for (Bid& bid : auction) {
		bid.Print_Private();
	}
}


void Auction::Print_Final()
{
	this->final.Print_Private();
}


void Auction::init_final()
{
	this->final = auction[auction.size() - 1];
}

void Auction::end()
{
	init_final();
	cout << "The auction has ended." << endl;
}
