#pragma once
class Score
{
public:
	Score();
	~Score();
private:
	Bid last;
	bool doubled;
	bool redoubled;
	int tricks_made;
	int points;
};

