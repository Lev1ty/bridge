#pragma once
class Card
{
public:
	Card();
	Card(int val) :val(val) {}
	~Card();
	int Val() { return val; }
private:
	int val;
};

