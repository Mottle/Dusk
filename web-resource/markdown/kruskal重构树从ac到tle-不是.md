---
title: kruskal重构树从ac到tle(不是)
date: 2020-04-14 00:01:06
tags: ACM 图论
---

今天写到货车运输这题时。本想着拿倍增lca~~乱搞~~,结果在题解区发现了神奇的**kruskal重构树**觉得很有意思，~~来水一篇文章~~。<!-- more -->
[货车运输](https://www.luogu.com.cn/problem/P1967)
话不多说，先说书kruskal重构树到底是拿来干嘛的。

假定给你一张边权连通图(不连通其实也可以，这个等下再说)，想要知道从图中u出发到v的所有**简单路径**中的最小边权的最大值是多少(或者反过来求最大边权的最小值)

举个例子免得把你绕晕了
![](https://s1.ax1x.com/2020/04/14/Gvzizd.md.png)

比如这张无向图中1，5两点的所有路径中最小权值的最大值就是4，而1，5所有路径的最大权值的最小值就是7。

解决此问题有很多种方法比如树链剖分。当然这里讲的kurskal也是一种方法，而且更为简单。


## 实现方式
顾名思义kruskal重构树肯定和kruskal算法有关(复杂度$O(Elog_2E)$)

初始时与kruskal算法没有区别，对边进行排序(求最小边权最大则求最小生成树，反之求最大生成树)，初始化并查集。

遍历排序好的边，对于当前遍历到的一条边 $(u, v)$(u, v连接的两点)，首先判断u，v所在的集合是否相同

- 若相同 跳过这条边
- 若不同 建立以新点w，为此点赋权值为$(u, v)$的权值，并把u，v各自所在的子树根节点与此点连接(这一步具体实现依赖并查集，见代码)

<!-- kruskal重构树的构造方法就是在利用kruskal算法求MST的同时把选出来的边作为一个新的节点连接这条边在原图中连接的两点的子树的根，把其原有的边权转化为新构成的点的点权，并把此点(刚刚新形成的点)作为新的根，不断重复这个过程直到所有点都被加入，则kruskal重构树构造完毕。可以比较容易的发现，原图的点全部都是kruskal重构树的叶子节点。而假设你要求u，v所有简单路径边权最大值得最小值，只需要求$lca(u, v)$的点权就得到了答案。(求最小边权最大则求最小生成树，反之求最大生成树) -->

先说明一下kruskal重构树的性质

- 原图中每个点都是kruskal重构树的一个叶子节点
- kruskal重构树是二叉树
- kruskal重构树构成二叉堆
- kruskal重构树共有 2n - 1个节点
- 原图中u，v间的路径边权最大(最小)为$lca(u, v)$的权值

比如上图的kruskal重构树就是

![](https://s1.ax1x.com/2020/04/14/GxS0N8.md.png)

还需要提一下的就是，对于非连通图，求出的是kruskal重构森林，每一棵树代表着那一个连通分量的kruskal重构树。

废话不多说 上代码

```cpp
auto buildKT(int n) -> int {
    //初始化并查集，用于判断每个节点属于哪一个子树
    for (int i = 0; i <= 2 * n ; ++i) parent[i] = i;
    int id = n;
    sort(edges.begin(), edges.end(), [](Edge u, Edge v){
        return u.w > v.w;
    });
    for (auto &&e : edges) {
        // u, v不光代表f,t各自的并查集所属,还代表其子树的根
        int u = find(e.f);
        int v = find(e.t);
        if(u == v) continue;
        parent[u] = parent[v] = ++id;
        d[id] = e.w;
        //vector邻接表存图
        E[id].push_back(u);
        E[id].push_back(v);
        E[u].push_back(id);
        E[v].push_back(id);
    }
    //最后一个节点的id
    //此处有点多余，最后id一定为2n - 1
    return id;
}
```