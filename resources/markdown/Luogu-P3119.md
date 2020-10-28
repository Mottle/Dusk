---
title: Luogu-P3119
date: 2020-01-09 23:57:54
tags: ACM 图论 强连通分量/缩点
---
很有意思的一道题

>约翰有n块草场，编号1到n，这些草场由若干条单行道相连。奶牛贝西是美味牧草的鉴赏家，她想到达尽可能多的草场>去品尝牧草。
>
>贝西总是从1号草场出发，最后回到1号草场。她想经过尽可能多的草场，贝西在通一个草场只吃一次草，所以一个草场>可以经过多次。因为草场是单行道连接，这给贝西的品鉴工作带来了很大的不便，贝西想偷偷逆向行走一次，但最多只能有一次逆行。问，贝西最多能吃到多少个草场的牧草。
[题面](https://www.luogu.com.cn/problem/P3119)
看到要尽量多的通过草场，且草场可以重复通过但不重复计数可以想到，在一个联通快内部的点都要走到（只有这样才能使得答案尽量的大）

那么我们找出所有的连通块然后缩点，问题就变成了在带点权DAG上找到一种路线使得在允许有一次走反向边且回到原点的最大路径（或者这么说，把任意一条边变成双向边使得包含点1的环尽可能的大）

对于缩点后的DAG

不妨这么考虑，把所有的点分为两类，一类是可以从点1直接到达的，还有一类是可以直接到达点1的
那么问题就变成了把两种不同的点间的某条边变成双向边使得环最大（仔细想想为什么）

那么这题就好写了， 只需要先跑联通分量缩点，然后对新图跑一遍spfa结果记录在dis1，然后对新图的反向图跑spfa结果记录在dis2（对反向图跑spfa计算从任意点到1的距离），然后答案就是max(dis1[u] + dis2[v] + 点1的连通块的点数)

```cpp
#include <iostream>
#include <algorithm>
#include <vector>
#include <set>
#include <queue>

using namespace std;

const int maxn = 100005;
const int maxm  = maxn;
const int Inf = 0x3f3f3f3f;

vector<int> E[maxn];
vector<int> Eb[maxn];
bool vis[maxn];
vector<int> s;
set<int> En[maxn];
set<int> Enb[maxn];
int color[maxn];
int w[maxn];
int dis1[maxn];
int dis2[maxn];

auto spfa1(int n, int s, int e) -> void {
    std::fill(dis1, dis1 + 1 + maxn, -Inf);
    std::fill(vis, vis + 1 + maxn, false);
    queue<int> q;
    q.push(s);
    vis[s] = true;
    dis1[s] = 0;
    while(!q.empty()) {
        int now = q.front();
        q.pop();
        vis[now] = false;
        for (auto && v : En[now]) {
            //最长路改为 <
            if(dis1[v] < dis1[now] + w[v]) {
                dis1[v] = dis1[now] + w[v];
                if(vis[v]) continue;
                if(!vis[v]) {
                    vis[v] = true;
                    q.push(v);
                }
            }
        }
    }
}

auto spfa2(int n, int s, int e) -> void {
    std::fill(dis2, dis2 + 1 + maxn, -Inf);
    std::fill(vis, vis + 1 + maxn, false);
    queue<int> q;
    q.push(s);
    vis[s] = true;
    dis2[s] = 0;
    while(!q.empty()) {
        int now = q.front();
        q.pop();
        vis[now] = false;
        for (auto && v : Enb[now]) {
            if(dis2[v] < dis2[now] + w[v]) {
                dis2[v] = dis2[now] + w[v];
                if(vis[v]) continue;
                if(!vis[v]) {
                    vis[v] = true;
                    q.push(v);
                }
            }
        }
    }
}


auto dfs1(int u) -> void {
    vis[u] = true;
    for (auto &&p : E[u]) {
        if(!vis[p]) dfs1(p);
    }
    s.push_back(u);
}

auto dfs2(int u, int sccc) -> void {
    color[u] = sccc;
    ++w[sccc];
    for (auto && p : Eb[u]) {
        if(color[p] == 0) dfs2(p, sccc);
    }
}

auto kosaraju(int n) -> int {
    int sccc = 0;
    for (int i = 1; i <= n; ++i) {
        if(!vis[i]) dfs1(i);
    }
    for (int i = s.size() - 1; i >= 0; --i) {
        if(color[s[i]] == 0) {
            ++sccc;
            dfs2(s[i], sccc);
        }
    }
    return sccc;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n, m;
    cin >> n >> m;
    for (int i = 1; i <= m; ++i) {
        int u, v;
        cin >> u >> v;
        if(u == v) continue;
        E[u].push_back(v);
        Eb[v].push_back(u);
    }
    int cn = kosaraju(n);
    for (int i = 1; i <= n; ++i) {
        for (auto &&p : E[i]) {
            if(color[i] == color[p]) continue;
            En[color[i]].insert(color[p]);
            Enb[color[p]].insert(color[i]);
        }
    }
    spfa1(cn, color[1], cn);
    spfa2(cn, color[1], cn);
    int ans = w[color[1]];
//    for (int i = 1; i <= n; ++i) {
//        cout << i << " " << color[i] << endl;
//    }
////    for_each(w + 1, w + 1 + cn, [](const int i) {
////        cout << i << " ";
////    });
//    cout << endl;
//    for (int i = 1; i <= cn; ++i) {
//        cout << i << ": ";
//        for_each(En[i].begin(), En[i].end(), [](const int & t) {
//            cout << t << " ";
//        });
//        cout << endl;
//    }

    for (int i = 1; i <= cn; ++i) {
//        cout << i << " " << dis1[i] << " " << dis2[i] << endl;
        for (auto &&p : Enb[i]) {
            if(dis1[i] != -Inf &&  dis2[p] != -Inf) ans = max(ans, dis1[i] + dis2[p] + w[color[1]]);
        }
    }
    cout << ans << endl;
    return 0;
}
```