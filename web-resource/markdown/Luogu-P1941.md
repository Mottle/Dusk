---
title: Luogu-P1941
date: 2019-12-15 17:40:41
tags: ACM DP
---
题目大意是要玩一个类似Flappy Bird的游戏，输入会给出地图以及每个x坐标对应的数据。小鸟每秒前进一格，可以在1s内连续点很多下让小鸟上升，也可以不点让他下降，对于每个不同的横坐标x上升和下降的值都不同，问你最少点多少下能让小鸟到达终点，如果到不了终点那么最远可以走多远。(具体题目看题面吧)<!-- more -->
[题面](https://www.luogu.com.cn/problem/P1941)

也就是说，在每个点我们都有两种选择：点k下使得可以到达下一格，或者不点让小鸟下降。

我们记状态转移函数为 $F(i, j)$ 其中 $i$ 为当前到达的格子(横坐标)，$j$ 为当前的高度，函数的值代表对于$i, j$需要点击的最小次数

~~很显然嘛~~在每个点如果选择上升就相当于是完全背包(因为可以上升任意次)

```cpp
for (int j = up[i - 1] + 1; j <= M + up[i - 1]; ++j) {
	F[i][j] = min(F[i][j - up[i - 1]] + 1, F[i - 1][j - up[i - 1]] + 1);
}
```

对于下降则是01背包(只能下降一次)。

```cpp
for (int j = 1; j <= M - down[i - 1]; ++j) {
	F[i][j] = min(F[i][j], F[i - 1][j + down[i - 1]]);
}
```

但是这题有个恶心的点在于，如果小鸟上升后高度超过了最高点，那么其高度仍然在最高点(你永远超过不了最高点)所以对于最高点需要特殊处理
(我们把高度上限设置为原来的两倍，然后对于每个超出最大高度的点单独处理)

```cpp
for (int j = M + 1; j <= M + up[i - 1]; ++j) {
	F[i][M] = min(F[i][M], F[i][j]);
}
```
对于无法到达的点，则将其值设为无穷

AC代码
```cpp
#include <iostream>
#include <algorithm>

using namespace std;

const int Inf = 0x3f3f3f3f;
const int maxn = 10015;
const int maxm = 1015 * 2;
const int maxk = maxn;
int up[maxn];
int down[maxn];
int ls[maxn];
int hs[maxn];
int _id[maxn];
int F[maxn][maxm];

auto main() -> int {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	int N, M, K;
	cin >> N >> M >> K;
	fill(ls, ls + maxn, 1);
	fill(hs, hs + maxn, M + 1);
	for (int i = 0; i < N; ++i) {
		cin >> up[i] >> down[i];
	}
	for (int i = 0; i < K; ++i) {
		int p;
		cin >> p;
		cin >> ls[p] >> hs[p];
		ls[p]++;
		hs[p]--;
		_id[p] = 1;
	}
	for (int i = 0; i <= N; ++i) {
		if(_id[i] == 0) _id[i] = _id[i - 1];
		else _id[i] = _id[i - 1] + 1;
	}
	F[0][0] = Inf;
	fill(F[0] + 1, F[0] + maxm, 0);
	for (int i = 1; i <= N; ++i) {
		fill(F[i], F[i] + maxm, Inf);
	}
	for (int i = 1; i <= N; ++i) {
		for (int j = up[i - 1] + 1; j <= M + up[i - 1]; ++j) {
			F[i][j] = min(F[i][j - up[i - 1]] + 1, F[i - 1][j - up[i - 1]] + 1);
		}
		for (int j = M + 1; j <= M + up[i - 1]; ++j) {
			F[i][M] = min(F[i][M], F[i][j]);
		}
		for (int j = 1; j <= M - down[i - 1]; ++j) {
			F[i][j] = min(F[i][j], F[i - 1][j + down[i - 1]]);
		}
		for (int j = 0; j < ls[i]; ++j) {
			F[i][j] = Inf;
		}
		for (int j = hs[i] + 1; j <= M; ++j) {
			F[i][j] = Inf;
		}
	}
	int ans = Inf;
	for (int i = N; i >= 0; --i) {
		for (int j = 1; j <= M; ++j) {
			ans = min(ans, F[i][j]);
			if(N != i && ans != Inf) {
				cout << 0 << endl;
				cout << _id[i] << endl;
				return 0;
			}
		}
		if(i == N && ans != Inf) {
			cout << 1 << endl;
			cout << ans << endl;
			return 0;
		}
	}
	return 0;
}
```