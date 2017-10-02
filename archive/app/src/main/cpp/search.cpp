//
// Created by Owner on 2017/09/20.
//
#include<vector>
#include<string>
#include<algorithm>
#include<queue>
#include<stack>
#include<list>
#include<fstream>
#include<queue>
#include<sstream>
#include<functional>
#include<iostream>
#include<utility>
#include<map>
#include<limits>

#include"search.h"
#include"toString.h"
//将来的にstdでto_stringが使えるようなると困るので

using namespace std;

//入力データ構造体

typedef struct input_data{
    //今のエッジ
    int first_edge;
    //次のエッジ
    int second_edge;
    //２つのエッジ間のコスト
    int cost;
};



//初期値
constexpr int INF = INT_MAX;
//配列サイズ（四国内なら100000もあれば十分？）
constexpr int ARRAY_SIZE = 10000;

//各エッジの情報
struct edge{
    //結ばれる先
    int to;
    //コスト
    int cost;
};

//グラフ
std::vector<std::vector<edge>>graph;

//firstは最短経路、pは頂点
typedef pair<int,int>P;

//プライオリティーキュー
priority_queue<P,vector<P>,greater<P>>pq;

//最短距離
int dist[ARRAY_SIZE];

//ベースとなるアルゴリズム
void dijkstra(int s,int g){
    //スタートは現在時間
    pq.push(P{0,s});
    P now;
    //今のコスト、ノード
    int node,cost;
    //新しいノード
    int newcost;
    //コスト
    int newnode;
    //キューが空になるまで
    while(!pq.empty()){
        //値の取り出し
        now = pq.top();
        //キューから削除
        pq.pop();
        //今の居場所
        node = now.second;
        //時間
        cost = now.first;
        if(dist[node] < cost)continue;
        for(int i = 0; i < graph[node].size();i++){
            //新しいコストを追加、その時のコストを計算する
            newnode = graph[node][i].to;
            newcost = graph[node][i].cost;
            //それまでより安いなら
            if(newcost < dist[newnode]){
                dist[newnode] = newcost;
                pq.push((P){newcost,newnode});
            }

        }

    }
}

//経路復元
string Restore(int s,int g){
    int node = s;
    string Rute = matatabi::to_string(node) + ' ';
    while(node != g){
        //最少を探っていく（個々のアルゴリズムを変えることでいろいろできそうな気がする）←そうなるとワーシャルフロイドでもよさそう
        int minnode = INF;
        int newnode;
        for(int i = 0; i < graph[node].size();i++){
            newnode = graph[node][i].to;
            if(dist[newnode] + graph[node][i].cost == dist[node]){
                if(minnode > newnode)minnode = newnode;
            }
        }
        node = minnode;
        Rute += matatabi::to_string(node);
        if(node != g)Rute += ' ';
    }
    return Rute;
}

void search(int node_size,int first_node,int second_node,std::vector<input_data>data){

    //引数を代入
    int N = node_size;
    int M = data.size();
    int start = first_node;
    int goal = second_node;
    int first_edge;
    int second_edge;
    int node_cost;
    //ノードデータを挿入する

    for(auto x:data){
        int a,b,c;
        a = x.first_edge;
        b = x.second_edge;
        c = x.cost;

        graph[a].push_back((edge){b,c});
        graph[b].push_back((edge){a,c});

    }





}