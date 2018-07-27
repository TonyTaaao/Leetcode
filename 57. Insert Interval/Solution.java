/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
// beats 47.94% --> res.removeLast() takes O(n) time, which is not very efficient
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        LinkedList<Interval> res = new LinkedList<>();
        boolean merged = false;
        boolean stopMerge = false;
        for (Interval interval : intervals) {
            if (stopMerge) res.add(interval);
            else if (!merged) {
                // if interval overlaps with newInterval
                if (!(interval.start > newInterval.end || newInterval.start > interval.end)) {
                    int newStart = Math.min(interval.start, newInterval.start);
                    int newEnd = Math.max(interval.end, newInterval.end);
                    res.add(new Interval(newStart, newEnd));
                    merged = true;
                } 
                else {
                    if (interval.start > newInterval.end) {
                        res.add(newInterval);
                        stopMerge = true;                                  
                    }
                    res.add(interval);
                }
            } else {
                // merged and !stopMerge
                Interval top = res.getLast();
                if (!(interval.start > top.end || top.start > interval.end)) {
                    int newStart = Math.min(interval.start, top.start);
                    int newEnd = Math.max(interval.end, top.end);
                    res.removeLast(); // O(n) time
                    res.add(new Interval(newStart, newEnd));
                }
                else {
                    res.add(interval);
                    stopMerge = true;
                }
            }
        }
        if (res.isEmpty() || res.getLast().end < newInterval.start) res.add(newInterval);
        return res;
    }
}



// Solution 1
// beats 94.22% --> res.getLast() takes O(1) time, and we directly change its value instead of popping and pushing again
// This is much faster cuz every single operation within the loop takes O(1) time
// Time Complexity = O(n), one list traverse.
// Space Complexity = O(n)
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        LinkedList<Interval> res = new LinkedList<>();
        boolean merged = false;
        boolean stopMerge = false;
        for (Interval interval : intervals) {
            if (stopMerge) res.add(interval);
            else if (!merged) {
                // if interval overlaps with newInterval
                if (!(interval.start > newInterval.end || newInterval.start > interval.end)) {
                    int newStart = Math.min(interval.start, newInterval.start);
                    int newEnd = Math.max(interval.end, newInterval.end);
                    res.add(new Interval(newStart, newEnd));
                    merged = true;
                } 
                else {
                    if (interval.start > newInterval.end) {
                        res.add(newInterval);
                        stopMerge = true;                                  
                    }
                    res.add(interval);
                }
            } else {
                // merged and !stopMerge
                Interval top = res.getLast();
                if (!(interval.start > top.end || top.start > interval.end)) {
                    int newStart = Math.min(interval.start, top.start);
                    int newEnd = Math.max(interval.end, top.end);
                    Interval last = res.getLast(); // O(1), better than the version above
                    last.start = newStart;
                    last.end = newEnd;
                }
                else {
                    res.add(interval);
                    stopMerge = true;
                }
            }
        }
        if (res.isEmpty() || res.getLast().end < newInterval.start) res.add(newInterval);
        return res;
    }
}



// Solution 2, different logic
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new LinkedList<>();
        int i = 0;
        // add all the intervals ending before newInterval starts
        while (i < intervals.size() && intervals.get(i).end < newInterval.start)
            result.add(intervals.get(i++));
        // merge all overlapping intervals to one considering newInterval
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            i++;
        }
        result.add(newInterval); // add the union of intervals we got
        // add all the rest
        while (i < intervals.size()) result.add(intervals.get(i++)); 
        return result;
    }
}