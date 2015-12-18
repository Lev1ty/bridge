#pragma once
class Bid
{
public:
	Bid();
	Bid(char dir, string usr_in) :usr_in(usr_in), dir(dir) { Bid(); input_to_num(); dir_to_dir_num(); }
	Bid(int dir_num, string usr_in) :usr_in(usr_in), dir_num(dir_num) { Bid(); input_to_num(); dir_num_to_dir(); }
	~Bid();
	int Level();
	int Suit();
	char Dir();
	int Dir_Num();
	string Input();
	void Print_Private();
private:
	string usr_in;
	int n_level;
	int n_suit;
	char dir;
	int dir_num;
	void input_to_num();
protected:
	void dir_num_to_dir();
	void dir_to_dir_num();
};

