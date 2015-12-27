#pragma once
class Card
{
public:
	Card();
	Card(int val) :val(val) { dir = 0; }
	Card(int val, int dir) :val(val), dir(dir) {}
	~Card();
	int Val() { return val; }
	int Dir() { return dir; }
private:
	int val;
	int dir;
};

