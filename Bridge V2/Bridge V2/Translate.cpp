#include "stdafx.h"
#include "Translate.h"


Translate::Translate()
{
}


Translate::~Translate()
{
}

void Translate::Card_Val(int num, string & suit, string & rank)
{
	const string convert_suit[4] = { "Spade","Heart","Diamond","Club" };
	suit = convert_suit[num / 13];
	const string convert_rank[13] = { "2","3","4","5","6","7","8","9","10","J","Q","K","A" };
	rank = convert_rank[num % 13];
}

string Translate::Card_Val(int num, bool full_form)
{
	string val = "";
	const string convert_rank[13] = { "2","3","4","5","6","7","8","9","10","J","Q","K","A" };
	if (full_form)
	{
		const string convert_suit[4] = { "Spade","Heart","Diamond","Club" };
		val += convert_rank[num % 13];
		val += " of ";
		val += convert_suit[num / 13];
		val += "s";
	}
	else
	{
		const string convert_suit[4] = { "S","H","D","C" };
		val += convert_rank[num % 13];
		val += convert_suit[num / 13];
	}
	return val;
}

int Translate::Card_Num(string suit, string rank)
{
	int s = -1, r = -1;
	switch (suit[0])
	{
	case 'S': case 's': s = 0; break;
	case 'H': case 'h': s = 1; break;
	case 'D': case 'd': s = 2; break;
	case 'C': case 'c': s = 3; break;
	default: s = -1;
		break;
	}
	if (rank.length() == 1)
	{
		if (rank[0] >= '2'&&rank[0] <= '9')
		{
			r = rank[0] - '0' - 2;
		}
		else
		{
			switch (rank[0])
			{
			case 'J': case 'j': r = 9; break;
			case 'Q': case 'q': r = 10; break;
			case 'K': case 'k': r = 11; break;
			case 'A': case 'a': r = 12; break;
			default: r = -1;
				break;
			}
		}
	}
	else if (rank.length() == 2 && rank[0] == '1'&&rank[1] == '0')
	{
		r = 8;
	}
	if (r == -1 || s == -1)
	{
		return -1;
	}
	else
	{
		int rtn = s * 13 + r;
		return rtn;
	}
}

string Translate::Player_Dir(int num, bool full_form)
{
	if (!full_form)
	{
		const string convert[4] = { "N","E","S","W" };
		return convert[num];
	}
	else
	{
		const string convert[4] = { "North","East","South","West" };
		return convert[num];
	}
}

int Translate::Player_Num(string dir)
{
	switch (dir[0])
	{
	case 'N': case 'n': return 0;
	case 'E': case 'e': return 1;
	case 'S': case 's': return 2;
	case 'W': case 'w': return 3;
	default: return -1;
	}
}

string Translate::Bid_Val(int num)
{
	const string convert[8][6] =
	{
		{ "Pass","Double","Redouble","NULL","NULL" },
		{ "1C","1D","1H","1S","1NT" },
		{ "2C","2D","2H","2S","2NT" },
		{ "3C","3D","3H","3S","3NT" },
		{ "4C","4D","4H","4S","4NT" },
		{ "5C","5D","5H","5S","5NT" },
		{ "6C","6D","6H","6S","6NT" },
		{ "7C","7D","7H","7S","7NT" }
	};
	return convert[num / 5][num % 5];
}

int Translate::Bid_Num(string val)
{
	int num = -1;
	if (val.compare("Pass") == 0 || val.compare("pass") == 0)
	{
		return 0;
	}
	else if (val.compare("Double") == 0 || val.compare("double") == 0)
	{
		return 1;
	}
	else if (val.compare("Redouble") == 0 || val.compare("redouble") == 0)
	{
		return 2;
	}
	else if (val.length() == 2 || (val.length() == 3 && (val[2] == 'T' || val[2] == 't')))
	{
		if (val[0] >= '1'&&val[0] <= '7')
		{
			num = (val[0] - '0') * 5;
		}
		if (num != -1)
		{
			switch (val[1])
			{
			case 'C': case 'c': num += 0; break;
			case 'D': case 'd': num += 1; break;
			case 'H': case 'h': num += 2; break;
			case 'S': case 's': num += 3; break;
			case 'N': case 'n': num += 4; break;
			default: num = -1;
				break;
			}
		}
	}
	return num;
}

string Translate::Player_Vul(bool b)
{
	const string convert[2] = { "Nonvulnerable","Vulnerable" };
	return convert[b];
}

bool Translate::Player_Vul_B(string vul)
{
	switch (vul[0])
	{
	case 'v': case 'V': return true;
	case 'n': case 'N': return false;
	default: return false;
	}
}

void Translate::Print_Sorted(vector<Card>& cards)
{
	string suit{}, rank{};
	for (int i = 0; i < 4; ++i)
	{
		Translate::Card_Val(i * 13, suit, rank);
		cout << suit << ":";
		vector<int>sub_card_rank{};
		for (auto it = cards.begin() + 1; it != cards.end(); ++it)
		{
			if (it->Val() / 13 == i)
			{
				sub_card_rank.push_back(it->Val());
			}
		}
		if (sub_card_rank.size()==0)
		{
			cout << " Void" << endl;
			continue;
		}
		sort(sub_card_rank.begin(), sub_card_rank.end());
		for (vector<int>::reverse_iterator rit = sub_card_rank.rbegin(); 
		rit != sub_card_rank.rend(); ++rit)
		{
			Translate::Card_Val(*rit, suit, rank);
			cout << ' ' << rank;
		}
		cout << endl;
	}
}
