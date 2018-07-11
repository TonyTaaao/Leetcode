/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
//This solution is very slow.
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1) return intervals; //no need to sort
        
        //sorted intervals according to value of start
        List<Interval> sorted = new ArrayList<>();
        for (Interval interval : intervals) {
            if (sorted.isEmpty()) sorted.add(interval);
            else {
                int size = sorted.size();
                int i = 0; //used to iterate through sorted, access sorted[i]
                //find the first index where sorted.get(i).start > interval.start
                while (i < size && sorted.get(i).start <= interval.start) {
                    i++;
                }
                sorted.add(i, interval);
            }
        }
        
        int i = 0; //iterate through the sorted list
        while (i < sorted.size() - 1) {
            Interval prev = sorted.get(i);
            Interval next = sorted.get(i + 1);
            //if there is no overlap between prev and next, move on to the next interval in sorted[]
            if (prev.end < next.start) i++;
            //if prev contains next, remove next
            else if (prev.start <= next.start && prev.end >= next.end) {
                sorted.remove(i + 1);
            }
            //else if next contains prev, remove prev
            //check condition: next.start == prev.start && next.end > prev.end 
            else if (next.start <= prev.start && next.end > prev.end) {
                sorted.remove(i);
            }
            //else if prev and next overlaps at one side, aka prev.end >= next.start
            else {
                prev.end = next.end;
                sorted.remove(i + 1);
            }
        }
        
        return sorted;
    }
}



//Version2, still very slow
//merge line 37-49 into line86-89
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1) return intervals; //no need to sort
        
        //sorted intervals according to value of start
        List<Interval> sorted = new ArrayList<>();
        for (Interval interval : intervals) {
            if (sorted.isEmpty()) sorted.add(interval);
            else {
                int size = sorted.size();
                int i = 0; //used to iterate through sorted, access sorted[i]
                //find the first index where sorted.get(i).start > interval.start
                while (i < size && sorted.get(i).start <= interval.start) {
                    i++;
                }
                sorted.add(i, interval);
            }
        }
        
        int i = 0; //iterate through the sorted list
        while (i < sorted.size() - 1) {
            Interval prev = sorted.get(i);
            Interval next = sorted.get(i + 1);
            //if there is no overlap between prev and next, move on to next
            if (prev.end < next.start) i++;
            //if prev and end overlap, merge them into one interval
            else {
                prev.end = Math.max(prev.end, next.end);
                sorted.remove(i + 1);
            }
        }
        
        return sorted;
    }
}