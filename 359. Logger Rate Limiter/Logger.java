// Solution 1, beats 7%
class Logger {
    Map<String, Integer> map;

    /** Initialize your data structure here. */
    public Logger() {
        map = new HashMap<>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (map.containsKey(message) && timestamp - map.get(message) < 10) return false;
        else {
            map.put(message, timestamp);
            return true;
        }
    }
}



// Solution 2, beats 82%
/*
A typical (accepted) solution is to keep a hash map of String that maps to the recent time stamp.
But this way, it needs to keep the record of the entire messages, even when the message is rare.

Alternatively, I keep a heap to get rid of the old message and set of String to keep the recent messages 
only. This approach would make sense when the number of logs within 10 minutes time window is not too 
large and when we have lots of different messages.
*/
class Log {
    int timestamp;
    String message;
    public Log(int aTimestamp, String aMessage) {
        timestamp = aTimestamp;
        message = aMessage;
    }
}

public class Logger {
    PriorityQueue<Log> recentLogs;
    Set<String> recentMessages;   
    
    /** Initialize your data structure here. */
    public Logger() {
        recentLogs = new PriorityQueue<Log>(10, new Comparator<Log>() {
            public int compare(Log l1, Log l2) {
                return l1.timestamp - l2.timestamp;
            }
        });
        
        recentMessages = new HashSet<String>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        while (recentLogs.size() > 0)   {
            Log log = recentLogs.peek();
            // discard the logs older than 10 minutes
            if (timestamp - log.timestamp >= 10) {
                recentLogs.poll();
                recentMessages.remove(log.message);
            } else 
                break;
        }
        boolean res = !recentMessages.contains(message);
        if (res) {
            recentLogs.add(new Log(timestamp, message));
            recentMessages.add(message);
        }
        return res;
    }
}
