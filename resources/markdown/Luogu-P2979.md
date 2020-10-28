---
title: Luogu-P2979
date: 2019-12-03 13:03:58
tags: ACM DP
---
>题意大概为给你一些种类的奶酪，每种奶酪都有自己的价值与高度，要求你用这些奶酪搭建一座奶酪塔，塔高不超过T且要求塔的总价值最大。定义高度超过K的奶酪为大奶酪，大奶酪会将其下边的奶酪高度压缩为原有的4/5(被压缩过则不再压缩)。<!-- more -->
[题面](https://www.luogu.com.cn/problem/P2979)

这题如果没有大奶酪的设定，那么就是一道完全背包的模板题，很容易可以给出核心代码。

```cpp
for (int i = 1; i <= N; ++i) {
    for (int j = H[i]; j <= T; ++j) {
        F[j] = max(F[j], F[j - H[i]] + V[i]);
    }
}
```
$F[T]$ 则为答案
但是一旦有了大奶酪压缩小奶酪高度这一设定这题就不能直接套完全被爆了。

仔细思考发现，一座奶酪塔价值最高只有两种可能：一种为完全没有一块大奶酪，另一种为有多快大奶酪，那么显然我在塔顶放一块大奶酪不会使得答案变得更小。所以我可以将塔高度变为原来的5/4来跑一遍完全背包，然后对于每一种塔高枚举一次大奶酪来找出其中最大值

AC代码
```cpp
#include <iostream>
#include <algorithm>

using namespace std;
const int maxn = 105;
const int maxh = 10 * maxn;

int H[maxn];
int V[maxn];
int F[maxh];

auto main() -> int {
    int N, T, K;
    cin >> N >> T >> K;
    for (int i = 1; i <= N; ++i) {
        cin >> V[i] >> H[i];
    }
    for (int i = 1; i <= N; ++i) {
        for (int j = H[i]; j <= T * 5 / 4; ++j) {
            F[j] = max(F[j], F[j - H[i]] + V[i]);
        }
    }
    int ans = F[T];
    for (int i = 1; i <= N; ++i) {
        if(H[i] >= K) {
            ans = max(ans, F[(T - H[i]) * 5 / 4] + V[i]);
        }
    }
    cout << ans << endl;
    return 0;
}
```