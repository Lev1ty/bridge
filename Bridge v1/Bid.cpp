#include "stdafx.h"
#include "Bid.h"


Bid::Bid()
{
	usr_in = "";
	n_level = 0;
	n_suit = 0;
	dir = '-1';
}


Bid::~Bid()
{
}


void Bid::input_to_num()
{
	const string convert[8][5] = {
		{ "1C","1D","1H","1S","1NT" },
		{ "2C","2D","2H","2S","2NT" },
		{ "3C","3D","3H","3S","3NT" },
		{ "4C","4D","4H","4S","4NT" },
		{ "5C","5D","5H","5S","5NT" },
		{ "6C","6D","6H","6S","6NT" },
		{ "7C","7D","7H","7S","7NT" },
		{"Pass","Double","Redouble","Out_of_bounds_1","Out_of_bounds_2"}
	};
	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 5; j++) {
			if (convert[i][j].compare(usr_in) == 0) {
				n_level = i;
				n_suit = j;
			}
		}
	}
}


int Bid::Level()
{
	return n_level;
}


int Bid::Suit()
{
	return n_suit;
}


string Bid::Input()
{
	return usr_in;
}


void Bid::Print_Private()
{
	cout << "usr_in " << usr_in << ' ';
	cout << "n_level " << n_level << ' ';
	cout << "n_suit " << n_suit << ' ';
	cout << "dir " << dir << ' ';
	cout << "dir_num " << dir_num << ' ';
	cout << endl;
}


void Bid::dir_num_to_dir()
{
	switch (dir_num)
	{
	case 0:dir = 'N';break;
	case 1:dir = 'E';break;
	case 2:dir = 'S';break;
	case 3:dir = 'W';break;
	default:
		break;
	}
}


char Bid::Dir()
{
	return dir;
}


int Bid::Dir_Num()
{
	return dir_num;
}
