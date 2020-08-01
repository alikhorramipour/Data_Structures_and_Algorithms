#include <iostream>
#include <vector>
#include <list>

#define INT_MAX 100000

using namespace std;


void add_edge(vector<int> adj[], int src, int dest) 
{ 
    adj[src].push_back(dest); 
    adj[dest].push_back(src); 
} 

bool BFS(vector<int> adj[], int src, int dest, int v, int pred[], int dist[]) 
{ 
    list<int> queue; 
    bool visited[v]; 
    for (int i = 0; i < v; i++) { 
        visited[i] = false; 
        dist[i] = INT_MAX; 
        pred[i] = -1; 
    }
    visited[src] = true; 
    dist[src] = 0; 
    queue.push_back(src); 
    while (!queue.empty()) { 
        int u = queue.front(); 
        queue.pop_front(); 
        for (int i = 0; i < adj[u].size(); i++) { 
            if (visited[adj[u][i]] == false) { 
                visited[adj[u][i]] = true; 
                dist[adj[u][i]] = dist[u] + 1; 
                pred[adj[u][i]] = u; 
                queue.push_back(adj[u][i]); 
                if (adj[u][i] == dest) 
                   return true; 
            } 
        } 
    } 
    return false; 
} 
  
int printShortestDistance(vector<int> adj[], int s, int dest, int v) 
{ 
    int pred[v], dist[v]; 
    if (BFS(adj, s, dest, v, pred, dist) == false) { 
        return -1; 
    } 
  
    vector<int> path; 
    int crawl = dest; 
    path.push_back(crawl); 
    while (pred[crawl] != -1) { 
        path.push_back(pred[crawl]); 
        crawl = pred[crawl]; 
    } 
    return dist[dest];
}

int main() 
{ 
    int n, k, m;
    cin >> n >> k >> m;
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
	int tmp;
	for(int i = 0; i < n; i++){
		if(hospitals[i] == 1){
			cout << 0 << " ";
			i++;
		}
		for(int j = 0; j < n; j++){
			if(hospitals[j] == 1){
				dest = j;
			}
			else j++;
			tmp = printShortestDistance(edges, i, dest, n);
			if(tmp == -1){
				tmp = n;
				break;
			}
		}
		cout << tmp << " ";
	}
    return 0; 
}  
