//Brute force Method, Time Complexity = O(N^2)
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            if (gas[i] >= cost[i])
                if (isValid(gas, cost, i, 0)) return i;
        }
        return -1;
    }
    
    private boolean isValid(int[] gas, int[] cost, int start, int tank) {
        for (int i = start; i <= start + gas.length - 1; i++) {
            int idx = i % gas.length;
            tank += gas[idx] - cost[idx];
            if (tank < 0) return false;
        }
        return true;
    }
}




//O(N) Solution
/*思路：
一旦从点i开始，经过若干站后不能到达点j（j为i开始第一个不能到达的点），那么从点i到点j之间的任意一点开始都不可能到达点j。

没想明白？不要紧，我再重复一遍：如果从点i开始，经过若干站后不能到达点j，那么从点i到点j之间的任意一点开始都不可能到达点j。

原因是：假设i与j之间我们任选一点A，i到A的时候最差最差是剩0个油，这与从A开始的情况完全相同，i最差的情况都不能到j，那么从A开始当然不能到j了。

所以基于上述理由，一旦i开始不能到j，i与j之间的点我们就不必再试了，它们必然不能到j。

还有一个非常重要的点，就是一圈总的油如果加起来大于等于消耗的，那么一定可以转一圈。证明也比较容易，可以采用反证法。

综合利用上述两点，可以实现一个 O(n) 的解法
*/
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0, tank = 0, total = 0;
        for (int i = 0; i < gas.length; i++) {
            tank += gas[i] - cost[i];
            if (tank < 0) {
                start = i + 1;
                total += tank;
                tank = 0;
            }
        }
        return (tank + total < 0) ? -1 : start;
    }
}