#pragma once
class Deck
{
public:
	Deck();
	~Deck();
	void Shuffle();
	void Print_Deck();
	vector<Card> Deck_();
	Card Access_Card(string suit, string rank);
	void Remove_Card(string suit, string rank);
private:
	void init_deck();
	vector<Card> deck;
};

