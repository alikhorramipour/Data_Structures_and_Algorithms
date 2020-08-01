#include <iostream>
#include <vector>
#include <math.h>

using namespace std;

int sum = 0;

void printVector(vector<int> dst){
	for (int i = 0; i < dst.size(); i++){
		cout << dst[i] << " ";
	}
	cout << endl;
}

void del(vector<int> dst, int num){
	for (int i = 0; i < dst.size(); i++){
		if(dst[i] == num){
			sum -= dst[i];
			dst.erase(dst.begin() + i);
			vector<int>(dst).swap(dst);
			return;
		}
	}
}

int avgElement(vector<int> dst){
	int avg = sum/dst.size();
	//cout << "dst.size() = " << dst.size() << endl;
	cout << "avg = " << avg << endl;
	int element = dst[0];
	for (int i = dst.size() - 1; i >= 0; i--){
		cout << "dst[i] = " << dst[i] << endl;
		cout << "if((abs(dst[i] - avg) <= abs(element - avg)) && (dst[i] > element)) " << (abs(dst[i] - avg) <= abs(element - avg)) << " " << (dst[i] > element) << endl;
		if((abs(dst[i] - avg) <= abs(element - avg)) && (dst[i] > element)){
				element = dst[i];
				cout << "element = " << element << endl;
		}
	}
	return element;
}

int main(){
	vector<int> dst;
	int n, op, numTmp;
	cin >> n;
	while(n--){
		cin >> op >> numTmp;
		
		if(op == 1){
			sum += numTmp;
			//cout << "sum = " << sum << endl;
			dst.push_back(numTmp);
		}
		else if(op == 2){
			for (int i = 0; i < dst.size(); i++){
				if(dst[i] == numTmp){
					sum -= dst[i];
					dst.erase(dst.begin() + i);
				}
			}
		}
	//	printVector(dst);
	//	cout << "avgElement(dst) = " << avgElement(dst) << endl;
		cout << avgElement(dst) << endl;
	}
	return 0;
}
