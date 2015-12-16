#pragma once
class Bid
{
public:
	Bid();
	Bid(const char& dir, const string& usr_in) :usr_in(usr_in), dir(dir) {}
	~Bid();
	int Level();
	int Suit();
	int Val();
	string Input();
	void Print_Private();
private:
	string usr_in;
	int n_level;
	int n_suit;
	int n_val;
	char dir;
	void input_to_num();
	void init_n_val();
};

