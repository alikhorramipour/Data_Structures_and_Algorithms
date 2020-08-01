#include <iostream>
#include <set>
#include <iterator>

using namespace std;

int sum = 0;

set<int>::iterator avgElement(set<int> dst){
	int avg = sum/dst.size();
	set<int>::iterator itr = dst.end(); // declare an iterator
	int element = *itr;
	cout << "element in the initialization is = " << element << endl;
	for (itr = dst.begin(); itr != dst.end(); ++itr) {
		cout << "*itr = " << *itr << endl;
    	if(*itr >= avg && *itr < element){
				element = int(*itr);
				cout << "element = " << element << endl;
		}
    } 
	return itr;
}

int main(){
	set <int> dst;
	int n, op, numTmp;
	cin >> n;
	while(n--){
		cin >> op >> numTmp;
		if(op == 1){
			sum += numTmp;
			dst.insert(numTmp);
		}
		else if(op == 2){
			sum -= numTmp;
			dst.erase(numTmp);
		}
		cout << "avgElement(dst) = " << *avgElement(dst) << endl;
	}
	return 0;
}
