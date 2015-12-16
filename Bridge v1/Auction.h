#pragma once
class Auction
{
public:
	Auction();
	Auction(char first_dir) :first_dir(first_dir) { Auction(); }
	~Auction();
	void Next_Bid(Bid bid);
	Bid Last();
	void Print_auction();
	void Print_Final();
private:
	vector<Bid> auction;
	Bid last;
	void init_final();
	int pass_cnt;
	int first_dir_cnt;
	char first_dir;
	void end();
};

