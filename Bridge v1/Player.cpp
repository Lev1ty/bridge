#include "stdafx.h"
#include "Player.h"


Player::Player()
{
	hand.clear();
	hand.reserve(13);
	dir = '-1';
	dir_num = 0;
}


Player::~Player()
{
}


void Player::Print_Hand()
{
	for (int i : hand)
	{
		cout << i << ' ';
	}
	cout << endl;
}


void Player::init_hand(int dir_num, vector<Card>deck)
{
	for (int i = dir_num * 13, j = 0; j < 13; i++, j++)
	{
		hand.push_back(deck[i].Elem());
	}
}


void Bid::dir_num_to_dir()
{
	switch (dir_num)
	{
	case 0:dir = 'N';break;
	case 1:dir = 'E';break;
	case 2:dir = 'S';break;
	case 3:dir = 'W';break;
	default:dir = '-1';break;
	}
}


void Bid::dir_to_dir_num()
{
	switch (dir)
	{
	case'N':dir_num = 0;break;
	case'E':dir_num = 1;break;
	case'S':dir_num = 2;break;
	case'W':dir_num = 3;break;
	default:dir_num = -1;break;
	}
}
