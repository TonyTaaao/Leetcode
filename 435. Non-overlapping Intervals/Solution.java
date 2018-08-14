/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public int eraseOverlapIntervals(Interval[] intervals) {
        int size = intervals.length;
        if (size <= 1) return 0;
        // sort intervals according to end of interval
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                int diff = a.end - b.end;
                if (diff < 0) return -1;
                else if (diff == 0) return 0;
                else return 1;
            }
        });
        
        int deleteCount = 0;
        int currentEnd = intervals[0].end;
        for (int i = 1; i < size; i++) {
            if (intervals[i].start < currentEnd) {
                // if two intervals overlap, delete the latter one.
                deleteCount++;
            } else {
                // update current end
                currentEnd = intervals[i].end;
            }
        }
        return deleteCount;
    }
}