---
title: Luogu-P4053
date: 2020-04-16 22:42:29
tags: ACM 贪心 堆
---

>简单来说，你拥有n个破损的建筑，第 $i$  个建筑需要 $cast_i$的时间才能修好，而如果当前时间超过了$t_i$还没有修好，这个建筑就炸掉了。
>
>现在问你你最多可以修多少建筑
[题面](https://www.luogu.com.cn/problem/P4053)

首先想到贪心，按照报废的时间从小到大排序能修就休，但是如果某个建筑的维修时间特别长而报废时间很短，选择了他就无法选择后面的建筑。这种贪心方法很显然存在问题。

那该怎么做呢？考虑带“反悔”的贪心，依旧按照报废时间排序，把每次选择的建筑话费时间加入优先队列。如果对于第$i$建筑剩余的时间不足以维修但堆顶部的时间大于此时间，那么此时反悔(不修堆顶的那个建筑，转而修当前这个。显然会使得花费的时间变小)。

代码:

```cpp
#include <iostream>
#include <algorithm>
#include <vector>
#include <climits>
#include <queue>

using namespace std;
using lint = long long;

struct House {
    lint cast;
    lint upt;
};
vector<House> hs;
priority_queue<lint> Q;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n;
    cin >> n;
    for (int i = 0; i < n; ++i) {
        lint c, u;
        cin >> c >> u;
        hs.push_back(House{c, u});
    }
    sort(hs.begin(), hs.end(), [](const House & a, const House & b) {
        return a.upt < b.upt;
    });
    lint cast = 0;
    int cnt = 0;
    for (auto &&h : hs) {
        if(cast + h.cast <= h.upt) {
            ++cnt;
            cast += h.cast;
            Q.push(h.cast);
        } else {
            auto t = Q.top();
            if(h.cast < t) {
                cast = cast - t + h.cast;
                Q.pop();
                Q.push(h.cast);
            }
        }
    }
    cout << cnt << endl;
    return 0;
}

```