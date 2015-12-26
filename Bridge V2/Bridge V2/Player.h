#pragma once
class Player
{
public:
	Player();
	Player(bool vul, int dir) :vul(vul), dir(dir) { hand.push_back(Card{}); }
	~Player();
	vector<Card>hand;
	vector<Bid>auc;
private:
	int dir;
	bool vul;
};

