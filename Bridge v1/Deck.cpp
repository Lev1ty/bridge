#include "stdafx.h"
#include "Deck.h"


Deck::Deck()
{
	deck.clear();
	init_deck();
}


Deck::~Deck()
{
}


void Deck::init_deck()
{
	for (int i = 0; i < 52; i++) {
		deck.push_back(Card(i));
		deck[i].Assign_Index(i);
	}
}


void Deck::Shuffle()
{
	//Random rand = new Random();//intiailize new random generator
	//for (int i = deck.length - 1;i >= 1;i--)//shuffle algorithm designed so to-be-moved cards positions don't overlap
	//{
	//	int j = rand.nextInt(i), temp = deck[j];//set j to new random number every time
	//	deck[j] = deck[i];deck[i] = temp;//swap elem
	//}
	for (int i = deck.size() - 1; i >= 1; i--) {
		int j = rand() % i;
		deck[j].Assign_Index(i);
		deck[i].Assign_Index(j);
		Card temp = deck[j];
		deck[j] = deck[i];
		deck[i] = temp;
	}
}


void Deck::Print_deck()
{
	for (Card&c : deck)
		c.Print_Private();
}


Card Deck::Access_Card(const string& suit, const string& rank)
{
	//for (int i = 0; i < deck.size(); i++) {
	//	if (deck[i].Suit_num() == suit && (deck[i].Rank()).compare(rank)) {
	//		return deck[i];
	//	}
	//}
	for (Card&c : deck) {
		if (c.Suit().compare(suit) == 0 && c.Rank().compare(rank) == 0) {
			return c;
		}
	}
}


void Deck::Remove_Card(const string& suit, const string& rank)
{
	const int&pos = Access_Card(suit, rank).Index();
	deck.erase(deck.begin() + pos);
	for (int i = pos; i < deck.size(); i++) {
		int index = deck[i].Index() - 1;
		deck[i].Assign_Index(index);
	}
}
