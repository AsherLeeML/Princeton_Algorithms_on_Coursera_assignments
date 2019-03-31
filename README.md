# Princeton_Algorithms_on_Coursera_assignments

**Note:**

The new version of assignments in this course has updated to work with a customized IntelliJ IDE with plugins like Check-style ([read here](<http://coursera.cs.princeton.edu/algs4/checklists/percolation.html>)), and there are great visualizers and some materials by the lectures to help understand the marvelous algorithms, so I keep all the contexts with the to-be-submitted files to give convenience to those who want to see direct results. 

**But as an experienced, I have to say that copy-and-paste is not a good choice. I looked up many discussions and even some source code, really hard to get this 100/100 with bonuses. Those efforts pay.**



## Week1: percolation

*This assignment is like a warm-up but getting 100/100 with a bonus is fairly difficult.*

The lecture mentioned virtual top and bottom nodes help get 100/100 scores very easily. Sure you can do this with two UFs, but the bonus asks you to do it with only one UF.

Ok, but how?

Extra space is needed to record the "state" of the sites in the n-by-n grid.

Some come up with two `boolean` arrays, one bind with bottom, one with top. That works.

My solution is inspired by a blog: <https://segmentfault.com/a/1190000005345079#articleHeader1>.

It's in Chinese but I try to translate the core steps below:

1. Use a byte 2d-array to record the states: 0 for blocked, 1 for open,2 for connected with bottom.
2. When opening a site, assign 2 to its state if it's in the n-th row, or assign 1.
3. Check the 4 neighbors using `uf.find()` : if the state of the neighbor site or that of the current site is 2, write both states to 2 and union the two sites.

In this way, if the state > 0 `isOpen()`  return true; using `uf.connected()` to tell `isFull()`; in each `open()` check if the state of `uf.find(virtualTop)` equals 2 at the last line, using a private `boolean` to save this flag. 

> Aggregate score: 101.25%
>
> Estimated student memory = 9.00 n^2 + 0.00 n + 184.00   (R^2 = 1.000)
>
> Test 2 (bonus): check that total memory <= 11 n^2 + 128 n + 1024 bytes
>
> ==> passed

It feels good.





