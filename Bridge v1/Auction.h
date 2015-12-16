#pragma once
class Auction
{
public:
	Auction();
	~Auction();
	void Next_Bid(Bid& bid);
	Bid Final();
	void Print_auction();
	void Print_Final();
private:
	vector<Bid> auction;
	Bid final;
	void init_final();
	int pass_cnt;
	void end();
};

