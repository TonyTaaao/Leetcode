/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

//runtime beats 76.78%
/*
 Time complexity = O(nlgn)
 Other than the sort invocation, we simply do a linear scan of the list, 
 so the runtime is dominated by the O(nlgn) complexity of sorting.

 Space complexity : O(n)
 We are creating a new list for the output. The space usage is proportional to
 the number of intervals in the output, which in the worst case is O(n).
 */
class Solution {
    // Comparator<T> is an interface, Class ... implements Comparator<T>
    // need to override: int compare(T obj1, T obj2), a method of interface Comparator<T>
    private class IntervalComparator implements Comparator<Interval> {
        @Override // can omit this
        // PUBLIC cannot be omitted
        public int compare(Interval a, Interval b) {
            if (a.start < b.start) return -1;
            else if (a.start == b.start) return 0;
            else return 1;
        }
    }
    
    public List<Interval> merge(List<Interval> intervals) {
        // Collections.sort(List<T> list) -- sorts list in ascending order according to the natural ordering of its elements
        // Collections.sort(List<T> list, Comparator<? super T> c) -- sorts list according to the order induced by the specified comparator.
        Collections.sort(intervals, new IntervalComparator());
        
        LinkedList<Interval> merged = new LinkedList<>();
        for (Interval interval : intervals) {
            // if the merged list is empty or if current interval does not 
            // overlap with the previous one, append current interval.
            if (merged.isEmpty() || interval.start > merged.getLast().end) {
                merged.add(interval);
            }
            // otherwise, there is an overlap, so we merge current 
            // and previous intervals.
            else {
                merged.getLast().end = Math.max(merged.getLast().end, interval.end);
            }
        }
        
        return merged;
    }
}