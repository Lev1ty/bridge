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


void Card::n_rank_to_rank()
{
	switch (n_rank) {
	case 0:rank = "2";break;
	case 1:rank = "3";break;
	case 2:rank = "4";break;
	case 3:rank = "5";break;
	case 4:rank = "6";break;
	case 5:rank = "7";break;
	case 6:rank = "8";break;
	case 7:rank = "9";break;
	case 8:rank = "10";break;
	case 9:rank = "J";break;
	case 10:rank = "Q";break;
	case 11:rank = "K";break;
	case 12:rank = "A";break;
	default:rank = "-1";break;
	}
}


void Card::n_suit_to_suit()
{
	switch (n_suit)
	{
	case 0:suit = "Spade";break;
	case 1:suit = "Heart";break;
	case 2:suit = "Diamond";break;
	case 3:suit = "Club";break;
	default:suit = "-1";break;
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


void Card::Assign_Index(int index)
{
	n_index = index;
}
