---
title: Topology without tears 第二章读书笔记
date: 2020-02-14 17:20:56
tags: Topology 读书笔记
---

# The Euclidean Topology

## 2.1 The Euclidean Topology on $\mathbb{R}$

>Definition 2.1.1
>
>A subset $S$ of $\mathbb{R}$ is said to be open in the euclidean topology on $\mathbb{R}$ if it has the following property:
>
>(∗) For each $x \in S$, there exist $a,b \in \mathbb{R}$, with $a < b$, such that $x \in (a,b) \subseteq S$.

Whenever we refer to the topological space $\mathbb{R}$ without specifying the topology, we mean $\mathbb{R}$ with the euclidean topology.

Not all open sets in $\mathbb{R}$ are intervals

## 2.2 Basis for a Topology

>Proposition 2.2.1
>
>A subset $S$ of $\mathbb{R}$ is open if and only if it is a union of **open** intervals.

That's very clear.

> Definition 2.2.2
>
>Let $(X, \tau)$ be a topological space. A collection $\mathcal{B}$ of open subsets of $X$ is said to be a basis for the topology $\tau$ if every open set is a union of members of $\mathcal{B}$.

If $\mathcal{B}$ is a basis for a topology $\tau$ on a set $X$ then a subset $U$ of $X$ is in $\tau$ if and only if it is a union of members of $\mathcal{B}$. 

Example. 
$$\mathcal{B} = \{(a,b) : a,b \in R,a < b\}$$
Then $\mathcal{B}$ is a basis for the euclidean topology on $\mathbb{R}$

Remark. Observe that if $(X,\tau)$ is a topological space then $\mathcal{B}$ = $\tau$ is a basis for the topology $\tau$. So, for example, the set of all subsets of $X$ is a basis for the discrete topology on $X$.

We see, therefore, that there can be many different bases for the same topology. Indeed if $\mathcal{B}$ is a basis for a topology $\tau$ on a set $X$ and $\mathcal{B}_1$ is a collection of subsets of $X$ such that $\mathcal{B} \subseteq \mathcal{B}_1 \subseteq \tau$, then $\mathcal{B}_1$ is also a basis for $\tau$.

>Proposition 2.2.3
>
> Let $X$ be a non-empty set and let $\mathcal{B}$ be a collection of subsets of $X$. Then $\mathcal{B}$ is a  basis for a topology on $X$ if and only if $\mathcal{B}$ has the following properties:
>
>(a) $X= \bigcup_{B \in \mathcal{B}} B$
>
>(b) for any $B_1, B_2 \in \mathcal{B}$, the set $B_1 \cap B_2$ is a union of members of $\mathcal{B}$.

## 2.3 Basis for a Given Topology

Proposition 2.2.3 told us under what conditions a collection $\mathcal{B}$ of subsets of a set $X$ is a basis for some topology on $X$. However sometimes we are given a topology $\tau$ on $X$ and we want to know whether $\mathcal{B}$ is a basis for this specific topology $\tau$. To verify that $\mathcal{B}$ is a basis for $\tau$ we could simply apply Definition 2.2.2 and show that every member of $\tau$ is a union of members of $\mathcal{B}$. However, Proposition 2.3.1 provides us with an alternative method.

>Proposition 2.3.1
>
>Let $(X,\tau)$ be a topological space. A family $\mathcal{B}$ of open subsets of $X$ is a basis for $\tau$ if and only if for any point $x$ belonging to any open set $U$, there is a $B \in \mathcal{B}$ such that $x \in B \subseteq U$.

>Proposition 2.3.2
>
>Let $\mathcal{B}$ be a basis for a topology $\tau$ on a set $X$. Then a subset $U$ of $X$ is open if and only if for each $x \in U$ there exists a $B \in \mathcal{B}$ such that $x \in B \subseteq U$.

We have seen that a topology can have many different bases. The next proposition tells us when two bases $\mathcal{B}_1$ and $\mathcal{B}_2$ on the set $X$ define the same topology.

>Proposition 2.3.3
>
>Let $\mathcal{B}_1$ and $\mathcal{B}_2$ be bases for topologies $\tau_1$ and $\tau_2$, respectively, on a non-empty set $X$. Then $\tau_1 = \tau_2$ if and only if
>
>(i) for each $B \in \mathcal{B}_1$ and each $x \in B$,there exists a $B^{'} \in \mathcal{B}_2$ such that $x \in B^{'} \subseteq \mathcal{B}$, and
>
>(ii) for each $B \in \mathcal{B}_2$ and each $x \in B$, there exists a $B^{'} \in \mathcal{B}_1$ such that $x \in B^{'} \subseteq \mathcal{B}$.