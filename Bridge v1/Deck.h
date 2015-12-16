#pragma once
class Deck
{
public:
	Deck();
	~Deck();
	void Shuffle();
	void Print_deck();
	Card Access_Card(const string& suit, const string& rank);
	void Remove_Card(const string& suit, const string& rank);
private:
	vector<Card> deck;
	void init_deck();
};

