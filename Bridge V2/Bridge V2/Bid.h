#pragma once
class Bid
{
public:
	Bid();
	Bid(int val, int dir) :val(val), dir(dir) {}
	~Bid();
	int Val() { return val; }
	int Dir() { return dir; }
private:
	int val;
	int dir;
};

