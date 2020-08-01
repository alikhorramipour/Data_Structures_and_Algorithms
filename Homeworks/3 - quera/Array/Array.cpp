#include <iostream>

using namespace std;

bool isMaxHeap(int arr[], int i, int n) {
	if (i > (n-2)/2)
		return true;
	if ((arr[i] >= arr[2*i + 1]) && (arr[i] >= arr[2*i + 2]) && (isMaxHeap(arr, 2*i+1, n)) && (isMaxHeap(arr, 2*i+2, n)))
    	return true;  
	else 
		return false; 
} 

int main() {
	int n;
	cin >> n;
    int arr[100000];
    for(int i=0; i<n; i++){
    	cin >> arr[i];
	}
	if(isMaxHeap(arr, 0, n))
		cout << "YES";
	else
  		cout << "NO";
  		
    return 0; 
} 
