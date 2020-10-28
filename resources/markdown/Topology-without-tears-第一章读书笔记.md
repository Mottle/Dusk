---
title: Topology without tears 第一章读书笔记
date: 2020-02-01 04:53:45
tags: Topology 读书笔记
---

# Topological Spaces

<!-- more -->
Here is series of reading notes for I reading the book "Toplogy without tears".
And I'm so sorry about my poor English skill which caused u reading difficulties.

<!-- Before beginning I will tell u how I read this book, and what my notes will seem to be. -->


## 1.1 Topology

>Definitions 1.1.1 
>
>Let X be a non-empty set. A set τ of subsets of X is said to be a topology on X if
>
>(i) $X$ and the empty set, $\emptyset$, belong to $\tau$ ,
>
>(ii) the union of any (finite or infinite) number of sets in $\tau$ belongs to $\tau$, and
>
>(iii) the intersection of any two sets in $\tau$ belongs to $\tau$. The pair $(X,\tau)$ is called a **topological space**.

Here is an example

Let $X$ = $\{a, b, c, d, e, f\}$ and
$\tau_1$ = $\{X, \emptyset, \{a\}, \{c,d\}, \{a,c,d\}, \{b,c,d,e,f\}\}$.

Then $\tau_1$ is a topology on $X$ as it satisfies conditions (i), (ii) and (iii) of Definitions 1.1.1,and we can say that $(X,\tau_1)$ is a topological space.

Now it's time for some special definitions.

>Definitions 1.1.2 
>
>Let $X$ be any non-empty set and let $\tau$ be the collection of all subsets of $X$. Then $\tau$ is called the **discrete topology** on the set $X$. The topological space $(X,\tau)$ is called a **discrete space**.

We note that τ in Definitions 1.1.2 does satisfy the conditions of Definitions 1.1.1 and so is indeed a topology.

Observe that the set $X$ in Definitions 1.1.2 can be any non-empty set. So there is an infinite number of discrete spaces – one for each set $X$.
 
>Definitions 1.1.3 
>
>Let $X$ be any non-empty set and $\tau$ = $\{X, \emptyset\}$. Then $\tau$ is called the **indiscrete topology** and $(X,\tau)$ is said to be an **indiscrete space**.

Once again we have to check that $\tau$ satisfies the conditions of 1.1.1 and so is indeed a topology.

We observe again that the set $X$ in Definitions 1.1.3 can be any non-empty set. So there is an infinite number of indiscrete spaces – one for each set $X$.


>Definitions 1.1.4 
>
>$\tau_1$ consists of $\mathbb{N}$,$\emptyset$, and every set $\{1,2,...,n\}$, for n any positive integer. $\tau_1$ called the **initial segment topology**.
>
>$\tau_2$ consists of $\mathbb{N}$,$\emptyset$, and every set $\{n,n+1,...\}$, for n any positive integer. $\tau_2$ is called the **final segment topology**.

ez to prove they satisfy the conditions of Definitions 1.1.1.

>Proposition 1.1.5 
>
>If $(X,\tau)$ is a topological space such that, for every $x \in X$, the singleton set $\{x\}$ is in $\tau$ , then $\tau$ is the discrete topology.

Prove this proposition is not hard,so u can try to prove that.(ps: use mathematical induction)

## 1.2 Open Sets,Closed Sets and "Clopen" Sets

Rather than continually refer to “members of $\tau$ ", we find it more convenient to give such sets a name. We call them “open sets”. 

>Definition 1.2.1
>
>Let $(X,\tau)$ be any topological space. Then the members of $\tau$ are said to be **open sets**.

so we have

>Proposition 1.2.2  
>
>If $(X,\tau)$ is any topological space, then 
>
>(i) $X$ and $\emptyset$ are open sets,
>
>(ii) the union of any (finite or infinite) number of open sets is an open set, and 
>
>(iii) the intersection of any **finite** number of open sets is an open set.

We shall also name the complements of open sets. They will be called “closed sets”.

>Definition 1.2.3 
>
>Let $(X,\tau)$ be a topological space. A subset S of X is said to be a closed set in $(X,\tau)$ if its complement in X, namely $X \backslash S$, is open in $(X,\tau)$.

here is an example

Let $X$ = $\{a, b, c, d, e, f\}$ and
$\tau_1$ = $\{X, \emptyset, \{a\}, \{c,d\}, \{a,c,d\}, \{b,c,d,e,f\}\}$.

the closed sets are $\emptyset$, $X$, $\{b,c,d,e,f\}$, $\{a,b,e,f\}$, $\{b,e,f\}$ and $\{a\}$.

If $(X,\tau)$ is a discrete space, then it is obvious that every subset of $X$ is a closed set. However in an indiscrete space, $(X, \tau)$, the only closed sets are $X$ and $\emptyset$.

>Proposition 1.2.4 
>
>If $(X,\tau)$ is any topological space, then 
>
>(i) $\emptyset$ and $X$ are closed sets,
>
>(ii) the intersection of any (finite or infinite) number of closed sets is a closed set and
>
>(iii) the union of any finite number of closed sets is a closed set.

Warning. The names “open” and “closed” often lead newcomers to the world of topology into error. Despite the names, some open sets are also closed sets! Moreover, **some sets are neither open sets nor closed sets!**

Give an example

Let $X$ = $\{a, b, c, d, e, f\}$ and
$\tau_1$ = $\{X, \emptyset, \{a\}, \{c,d\}, \{a,c,d\}, \{b,c,d,e,f\}\}$.

we see that

(i) the set $\{a\}$ is both open and closed;

(ii) the set $\{b, c\}$ is neither open nor closed;

(iii) the set $\{c, d\}$ is open but not closed;

(iv) the set $\{a,b,e,f\}$ is closed but not open.


In a discrete space every set is both open and closed, while in an indiscrete space
$(X,\tau)$, all subsets of $X$ except $X$ and $\emptyset$ are neither open nor closed.

To remind you that sets can be both open and closed we introduce the following definition.

>Definition 1.2.5
>
> A subset $S$ of a topological space $(X,\tau)$ is said to be clopen if it is both open and closed in $(X,\tau)$.

In every topological space $(X, \tau)$ both $X$ and $\emptyset$ are clopen. 

In a discrete space all subsets of $X$ are clopen.

In an indiscrete space the only clopen subsets are $X$ and $\emptyset$.

## 1.3 The Finite-Closed Topology

It is usual to define a topology on a set by stating which sets are open. 

However, sometimes it is more natural to describe the topology by saying which sets are closed.

>Definition 1.3.1 
>
>Let $X$ be any non-empty set. A topology $\tau$ on $X$ is called the **finite-closed topology** or the **cofinite topology** if the closed subsets of $X$ are $X$ and all finite subsets of $X$;
>
>that is, the open sets are $\emptyset$ and all subsets of $X$ which have finite complements.

example

If $\mathbb{N}$ is the set of all positive integers, then sets such as 

$$\{1\}, \{5,6,7\}, \{2,4,6,8\}$$

are finite and hence closed in the finite-closed topology. Thus their complements

$$\{2,3,4,5,...\}, \{1,2,3,4,8,9,10,...\}, \{1,3,5,7,9,10,11,...\}$$

are open sets in the finite-closed topology.

>Definitions 1.3.2 Let $f$ be a function from a set $X$ into a set $Y$ .
>
>(i) The function $f$ is said to be one-to-one or **injective** if $f(x_1) = f(x_2)$ implies $x_1 = x_2$, for $x_1,x_2 \in X$;
>
>(ii) The function $f$ is said to be onto or **surjective** if for each $y \in Y$ there exists an $x\in X$ such that $f(x)=y$;
>
>(iii) The function $f$ is said to be **bijective** if it is both one-to-one and onto.

>Definitions 1.3.3 
>
>Let $f$ be a function from a set $X$ into a set $Y$. The function $f$ is said to have an **inverse** if there exists a function g of $Y$ into $X$ such that $g(f(x))=x$, for all $x \in X$ and $f(g(y))=y$, for all $y \in Y$. The function $g$ is called an **inverse function** of $f$.

>Proposition 1.3.4 
>
>Let $f$ be a function from a set $X$ into a set $Y$.
>
>(i) The function $f$ has an inverse if and only if $f$ is bijective.
>
>(ii) Let $g_1$ and $g_2$ be functions from $Y$ into $X$. If $g_1$ and $g_2$ are both inverse functions of $f$, then $g_1 = g_2$; that is, $g_1(y) = g_2(y)$, for all $y \in Y$ .
>
>(iii) Let g be a function from $Y$ into $X$. Then $g$ is an inverse function of $f$ if and only if $f$ is an inverse function of $g$.

All functions **map one point to one point**. Indeed this is part of the definition of a function.

A **one-to-one function** is a function that **maps different points to different points**.

>Definition 1.3.5 
>
>Let $f$ be a function from a set $X$ into a set $Y$. If S is any subset of $Y$ , then the set $f^{-1}(S)$ is defined by
>$$f^{-1}(S)=\{x:x \in X~~and~~f(x) \in S\}$$
>The subset $f^{-1}(S)$ of $X$ is said to be the inverse image of $S$.

Note that an inverse function of $f : X \to Y$ exists if and only if $f$ is bijective. But the inverse image of any subset of $Y$ exists even if $f$ is neither one-to-one nor onto.

>Definition 1.3.6
>
>A topological space $(X,\tau)$ is said to be a **T1-space** if every singleton set $\{x\}$ is closed in $(X,\tau)$. Show that precisely two of the following nine topological spaces are **T1-spaces**.

>Definition 1.3.7
>
>A topological space $(X,\tau)$ is said to be a **T0-space** if for each pair of distinct points $a$, $b$ in $X$.