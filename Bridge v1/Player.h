#pragma once
class Player
{
public:
	Player();
	~Player();
private:
	vector<int>hand;
	char dir;
	Bid bid;
};

