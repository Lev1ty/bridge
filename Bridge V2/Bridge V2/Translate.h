#pragma once
class Translate
{
public:
	Translate();
	~Translate();
	void Card_Val(int num, string& suit, string& rank);
	string Card_Val(int num, bool full_form);
	int Card_Num(string suit, string rank);
	string Player_Dir(int num, bool full_form);
	int Player_Num(string dir);
	string Bid_Val(int num);
	int Bid_Num(string val);
	string Player_Vul(bool b);
	bool Player_Vul_B(string vul);
	void Print_Sorted(vector<Card>& cards);
};

