---
title: Luogu-P5322
date: 2019-12-05 00:04:13
tags: ACM DP
---
题目大意：
>小C正在玩一款排兵布阵的游戏。在游戏中有 $n$ 座城堡，每局对战由两名玩家来争夺这些城堡。每名玩家有 $m$ 名士兵，可以向第$i$座城堡派遣 $a_i$ 名士兵去争夺这个城堡，使得总士兵数不超过 $m$。
如果一名玩家向第 $i$ 座城堡派遣的士兵数严格大于对手派遣士兵数的两倍，那么这名玩家就占领了这座城堡，获得 $i$ 分。
>
>现在小C即将和其他 $s$ 名玩家两两对战，这 $s$ 场对决的派遣士兵方案必须相同。小C通过某些途径得知了其他 $s$ 名玩家即将使用的策略，他想知道他应该使用什么策略来最大化自己的总分。
> 其中
>$$1\leq N, S\leq 100$$
>$$1\leq M\leq 20000$$
>$$\sum_{i=1}^Na_i\leq M$$
>且有 
>$$a_i> 0$$
>
>你需要输出小C总分的**最大值**。
[题面](https://www.luogu.com.cn/problem/P5322)

这题乍一看没什么头绪，我们不妨把在每座城堡派出的兵力看做是体积，占领每座城堡所得的积分看作是价值。那么很显然这是一道背包问题的题目。
每座城堡都是一个泛化的物品。

那么如果在第i座城堡派出的兵力是 $x_i$ 则我们的得分函数$p$是
$$p(x_i, i) = i\sum_{t=1}^s[x_i > 2a_{i,t}]$$
很容易推出状态转移方程($M$为总兵力)
$$F(i, v) = max\{F(i - 1, v), F(i - 1, v - k) + p(k, i) | 0\leq k\leq M\} $$
代码

```cpp
#include <iostream>
#include <algorithm>

using namespace std;
const int maxs = 105;
const int maxn = 105;
const int maxm = 20005;

int a[maxn][maxn];
int F[maxm];

inline auto pkg(int k, int i, int S) -> int {
    int p = 0;
    for (int t = 1; t <= S; ++t) {
        p += int(k > 2 * a[t][i]) * i;
    }
    return p;
}

auto main() -> int {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    //S城堡数 N玩家数 M棋子数
    int S, N, M;
    cin >> S >> N >> M;
    for (int i = 1; i <= S; ++i) {
        for (int j = 1; j <= N; ++j) {
            cin >> a[i][j];
        }
    }
    for (int i = 1; i <= N; ++i) {
        for (int v = M; v >= 0; --v) {
            for (int k = 0; k <= v; ++k) {
                int p = pkg(k, i, S);
                F[v] = max(F[v], F[v - k] + p);
            }
        }
    }
    cout << F[M] << endl;
    return 0;
}
```

很显然这么做的复杂度为
$$O(NM^{2}S)$$
然后就很愉快的TLE六个点(大哭)

那么该怎么做呢？我们可以这样想，我们将每个人在$i$城堡派的兵力排序，如果$x_i > 2a_{i,t_0}$且$x_i \leq 2a_{i,t_0 + 1}$那么对于所有$t\leq t_0$都有$x_i > 2a_{i,t}$
则此时在$i$城堡得分为 $t_0i$
此时的状态转移方程为
$$F(i, v)=max\{F(i - 1, v), F(i - 1, v - (2a_{i,k} + 1)) + ik | 0\leq k\leq S\}$$

时间复杂度为
$$O(NMS)$$
~~很显然~~可以AC啦
```cpp
#include <iostream>
#include <algorithm>

using namespace std;
const int maxs = 105;
const int maxn = 105;
const int maxm = 20005;

int a[maxn][maxn];
int F[maxm];

auto main() -> int {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    //S城堡数 N玩家数 M棋子数
    int S, N, M;
    cin >> S >> N >> M;
    for (int i = 1; i <= S; ++i) {
        for (int j = 1; j <= N; ++j) {
            cin >> a[j][i];
        }
    }
    for (int i = 1; i <= N; ++i) {
        sort(a[i] + 1, a[i] + S + 1);
    }
    for (int i = 1; i <= N; ++i) {
        for (int v = M; v >= 0; --v) {
            for (int k = 1; k <= S; ++k) {
                if(v >= 2 * a[i][k] + 1) {
                    F[v] = max(F[v], F[v - 2 * a[i][k] - 1] + i * k);
                }
            }
        }
    }
    cout << F[M] << endl;
    return 0;
}
```