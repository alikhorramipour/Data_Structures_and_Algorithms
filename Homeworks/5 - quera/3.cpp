#include<iostream>

using namespace std;

void add_edge(vector<int> adj[], int src, int dest) 
{ 
    adj[src].push_back(dest); 
    adj[dest].push_back(src); 
} 

int main(){
	int n, q;
	cin >> n >> q;
    int chance = n;
	vector<int> edges[m];
    
    bool hospitals[n] = {0};
    int distance[n] = {n};
    int src, dest;
    while(k--){
    	cin >> src;
    	src--;
    	hospitals[src] = 1;
	}
    while(m--){
    	cin >> src >> dest;
    	src--;
    	dest--;
    	add_edge(edges, src, dest);
	}
}
