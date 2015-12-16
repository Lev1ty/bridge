#pragma once
class Card
{
public:
	Card();
	Card(const int& n_elem) :n_elem(n_elem) {}
	~Card();
	void Assign_Index(const int& index);
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
	int n_suit = n_elem / 13;
	int n_rank = n_elem % 13;
	string n_rank_to_rank(const int& n_rank);
	string n_suit_to_suit(const int& n_suit);
	string rank = n_rank_to_rank(n_rank);
	string suit = n_suit_to_suit(n_suit);
};

