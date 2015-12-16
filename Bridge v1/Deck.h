#pragma once
class Deck
{
public:
	Deck();
	~Deck();
	void Shuffle();
	void Print_deck();
	Card Access_Card(string suit, string rank);
	void Remove_Card(string suit, string rank);
private:
	vector<Card> deck;
	void init_deck();
};

