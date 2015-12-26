#pragma once
class Bid
{
public:
	Bid();
	Bid(int val, int dir) :val(val), dir(dir) {}
	~Bid();
private:
	int val;
	int dir;
};

