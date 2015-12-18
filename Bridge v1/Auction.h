#pragma once
class Auction
{
public:
	Auction();
	~Auction();
	void Next_Bid(Bid bid);
	Bid Last();
	void Print_Auction();
	void Print_Final();
	int Pass();
	void Print_Private();
	bool End();
private:
	vector<Bid> auction;
	Bid last;
	void init_final();
	int pass_cnt;
	bool end_bool;
	void end();
};

