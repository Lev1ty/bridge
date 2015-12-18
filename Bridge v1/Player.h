#pragma once
class Player : public Bid
{
public:
	Player();
	Player(char dir, vector<Card>deck) :dir(dir) { Player(); dir_to_dir_num(); init_hand(dir_num, deck); }
	Player(int dir_num, vector<Card>deck) :dir_num(dir_num) { Player(); init_hand(dir_num, deck); dir_num_to_dir(); }
	~Player();
	void Print_Hand();
private:
	vector<int>hand;
	char dir;
	int dir_num;
	Bid bid;
	void init_hand(int dir_num, vector<Card>deck);
	/*void dir_num_to_dir();
	void dir_to_dir_num();*/
};

