---
title: Luogu-P4832
date: 2020-01-02 15:29:32
tags: ACM DP
---
题目大意是~~珈百璃不想写作业就把作业交给了你~~，给你一些形如s+c的式子，其中s代表 $sin^{2}\frac{\pi}{7}$ ，c代表 $cos^{2}\frac{\pi}{7}$ ,然后你可以从中间选一些式子，要求最后选出的式子之和为**整数**且**最大**
[题面](https://www.luogu.com.cn/problem/P4832)
给一组样例的输入
>3
>
>s+c
>
>s+c+s
>
>c

对应的输出为
>3

看到题目首先想到一定有 $sin^{2}\frac{\pi}{7} + cos^{2}\frac{\pi}{7} = 1$，那么~~很显然~~的我们要想使得结果为整数，选出的式子中的s与c的数量要相同

则问题就成了怎样选式子使得s与c的数量相同且使得答案尽可能的大

考虑状态转移函数 $F(i, s, c)$ 其中 $i$ 已经选过了第 $i$ 个式子， $s, c$分别代表选完后的s与c的数量，而函数的值本身代表选完 $i$ 个式子后总和的**最大整数值**。
则一定有
$$
F(i,s,c) = min\{s, c\}
$$
（一个必要的条件是当前的s, c一定要是可达的，如果我不论怎么选都选不出这样的s与c那么这个结论肯定不成立）

当然这个结论说了和没说一样
还需要有状态转移方程
$$
F(i,s,c) = max\{F(i - 1, s, c), F(i - 1, s - s_i, c - c_i) + \Delta \}
$$
其中 $s_i, c_i$ 代表第 $i$ 个式子中的s与c的个数，$\Delta$ 则是选中了这个式子后当前式子总和的值的增量
这里 $\Delta$ 的值要分类讨论

当 $s - s_i$ 与 $c - c_i$ 相等时，很容易想到 $\Delta = min\{s_i, c_i\}$

当 $s - s_i$ 不等于 $c - c_i$ 时，$\Delta = min\{s, c\} - min\{s - s_i, c - c_i\}$ (想一想为什么)

那么答案就是所有 $max\{F(i, t, t)\}, (0 < t \leq N)$

有了上边这些结论，我们~~貌似~~就可以AC这道题了，但是但是先等等，这个算法的时间复杂度为 $O(NSC)$, （$S, C$ 分别代表所有式子中的s，c数量之和）

默默看了眼数据范围大喊卧槽（自己点击去看看吧），这明显会MLE。哭了

冥思苦想一下有了新的想法，我们的状态转移函数中的s，c明显就是冗余的数据，其实只要记录下s与c的差值d就可以 那么有
$$
F(i, d) = max\{F(i - 1, d), F(i - 1, d + s_i - c_i) + c_i\}
$$
不过这样写会麻烦不少，因为需要处理d为负数的情况，并且对于d可谓负数的情况不可以直接简单的反向遍历数组。

AC代码
```cpp
#include <iostream>
#include <algorithm>

using namespace std;

const int maxm = 1e6 + 15;
const int Inf = 0x3f3f3f3f;
const int D = 50000;

inline auto real(int x) -> int {
    return x + maxm;
}

auto split(string & s) -> pair<int, int> {
    int si = 0, ci = 0;
    for (auto &&c : s) {
        if(c == 'c') ++ci;
        if(c == 's') ++si;
    }
    return {si, ci};
}

int F[2][2 * maxm];

auto main() -> int {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n;
    cin >> n;
    int l = 0, r = 0;
    fill(F[0], F[0] + 2 * maxm, -Inf);
    fill(F[1], F[1] + 2 * maxm, -Inf);
    F[0][real(0)] = 0;
    for (int i = 1; i <= n; ++i) {
        string s;
        cin >> s;
        auto [si, ci] = split(s);
        int d = si - ci;
        l = min(l, l + d);
        r = max(r, r + d);
        for (int j = l; j <= r; ++j) {
            F[i % 2][real(j)] = max(F[i % 2][real(j)], F[i % 2 ^ 1][real(j)]);
            F[i % 2][real(j)] = max(F[i % 2][real(j)], F[i % 2 ^ 1][real(j - d)] + ci);
        }
    }
    cout << F[n % 2][real(0)] << endl;
    return 0;
}
```