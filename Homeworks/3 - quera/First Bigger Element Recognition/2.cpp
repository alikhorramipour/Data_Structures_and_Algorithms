#include <iostream>
#include <stdlib.h>

using namespace std;

int main(){
	int n;
	cin >> n;
	int arr[100000];
	for(int i=0; i<n; i++){
		cin >> arr[i];
	}
	int tmp;
	for(int i=0; i<n; i++){
		tmp = -1;
		for(int j=i+1; j<n; j++){
			if(arr[i]<arr[j]){
				tmp = arr[j];
				break;
			}
		}
		cout << tmp << " ";	
	}
	return 0;	
}
