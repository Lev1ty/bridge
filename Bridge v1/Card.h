#pragma once
class Card
{
public:
	Card();
	Card(int n_elem) :n_elem(n_elem) { Card(); n_suit = n_elem / 13; n_rank = n_elem % 13; rank = n_rank_to_rank(n_rank); suit = n_suit_to_suit(n_suit); }
	~Card();
	void Assign_Index(int index);
	int Index();
	int Elem();
	int Rank_num();
	int Suit_num();
	string Rank();
	string Suit();
	void Print_Private();
private:
	int n_index;
	int n_elem;
	int n_suit;
	int n_rank;
	string n_rank_to_rank(int n_rank);
	string n_suit_to_suit(int n_suit);
	string rank;
	string suit;
};

