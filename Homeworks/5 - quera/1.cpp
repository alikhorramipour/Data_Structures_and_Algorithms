#include <iostream>
#include <string.h>

using namespace std;

int getMaxOccuringChar(string str, int startPnt, int endPnt) 
{ 
	int count[1000] = {0}; 
    int max = 0;
    
    for (int i = startPnt; i <= endPnt; i++) { 
        count[str[i]]++; 
        if (max < count[str[i]]) { 
            max = count[str[i]]; 
        } 
    } 
  
    return max; 
} 



int main(){
	string S;
	cin >> S;
	int stringLength = S.length();
	int q;
	cin >> q;
	int l, r, diff = 0, diffDivBy2 = 0;
	while (q--){
		cin >> l >> r;
		l--;
		r--;
		diff = r - l + 1;
		diffDivBy2 = diff/2;
//		cout << "getMaxOccuringChar(S, l, r) = " << getMaxOccuringChar(S, l, r) << endl;
//		cout << endl << "diff = " << diff << endl;
//		cout << "diffDivBy2 = " << diffDivBy2 << endl;
//		cout << "else if (getMaxOccuringChar(S, l, r) > diffDivBy2)" << (getMaxOccuringChar(S, l, r) > diffDivBy2);
		if ((diff < 2) || (r > stringLength) || (l > r))
			cout << "No" << endl;
		else if (getMaxOccuringChar(S, l, r) > diffDivBy2)
			cout << "Yes" << endl;
		else cout << "No" << endl;
	}
}
