//beats 43% runtime
//Time Complexity = O(2N) = n=amortized O(N), due to the two for loops
class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        Set<String> skip = new HashSet<>(Arrays.asList("..", ".", ""));
        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty()) stack.pop();
            else if (!skip.contains(dir)) stack.push(dir);
        }
        
        StringBuilder res = new StringBuilder();
        
        for (String dir : stack) {
            /*
            res.insert(0, dir);
            res.insert(0, "/");
            */
            res.append("/");
            res.append(dir);
        }
        
        return res.length() == 0 ? "/" : res.toString();
    }
}

//Note: Stack Iteration
//stack iterates in the order you add the elements since the underlying 
//implementation is a list.
//eg. stack.add(1,2,3,4);
//stack iteration order: 1->2->3->4
//stack pop out order: 4->3->2->1



//Version2, same idea
//different in line 43-45
class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        Set<String> skip = new HashSet<>(Arrays.asList(".", ""));
        for (String dir : path.split("/")) {
            if (dir.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            }
            else if (!skip.contains(dir)) stack.push(dir);
        }
        
        StringBuilder res = new StringBuilder();
        
        for (String dir : stack) {
            res.append("/").append(dir);
        }
        
        return res.length() == 0 ? "/" : res.toString();
    }
}