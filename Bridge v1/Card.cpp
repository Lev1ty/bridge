#include "stdafx.h"
#include "Card.h"


Card::Card()
{
	n_index = 0;
	n_elem = 0;
	n_suit = 0;
	n_rank = 0;
	rank = "";
	suit = "";
}


Card::~Card()
{
}


string Card::n_rank_to_rank(const int& n_rank)
{
	switch (n_rank) {
	case 0:return "2";
	case 1:return "3";
	case 2:return "4";
	case 3:return "5";
	case 4:return "6";
	case 5:return "7";
	case 6:return "8";
	case 7:return "9";
	case 8:return "10";
	case 9:return "J";
	case 10:return "Q";
	case 11:return "K";
	case 12:return "A";
	default:return "-1";
	}
}


string Card::n_suit_to_suit(const int& n_suit)
{
	switch (n_suit)
	{
	case 0:return"Spade";
	case 1:return"Heart";
	case 2:return"Diamond";
	case 3:return"Club";
	default:return"-1";
	}
}


int Card::Index()
{
	return n_index;
}


int Card::Elem()
{
	return n_elem;
}


int Card::Rank_num()
{
	return n_rank;
}


int Card::Suit_num()
{
	return n_suit;
}


string Card::Rank()
{
	return rank;
}


string Card::Suit()
{
	return suit;
}


void Card::Print_Private()
{
	cout << "Index_n " << n_index << ' ';
	cout << "Elem_n " << n_elem << ' ';
	cout << "Suit_n " << n_suit << ' ';
	cout << "Rank_n " << n_rank << ' ';
	cout << "Rank " << rank << ' ';
	cout << "Suit " << suit << ' ';
	cout << endl;
}


void Card::Assign_Index(const int& index)
{
	n_index = index;
}
