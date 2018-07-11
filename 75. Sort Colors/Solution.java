//This problem must be solved IN-PLACE. --> NO auxiliary data structures

//Solution 1
/*
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array 
with total number of 0's, then 1's and followed by 2's.
*/
//Time Complexity = O(2N), scan the array twice
//Space Complexity = O(K), K = number of colors == 3
class Solution {
    public void sortColors(int[] nums) {
        int red = 0, white = 0, blue = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) red++;
            else if (nums[i] == 1) white++;
            else blue++;
        }
        
        for (int i = 0; i < red; i++) {
            nums[i] = 0;
        }
        for (int i = 0; i < white; i++) {
            nums[red + i] = 1;
        }
        for (int i = 0; i < blue; i++) {
            nums[red + white + i] = 2;
        }
    }
}



//Solution 2
/*
计数排序，元素空间需要三个元素.
上面的代码是计数排序的标准解法，可以看到总共进行了三次扫描，其实最后一次只是把结果数组复制到原数组而已，如果不需要in-place的结果只需要两次扫描。
其实就算返回元素组也可以是两次扫描，这需要用到元素只有0,1,2的本质。我们知道helper[i]中是包含着0,1,2的元素数量，我们只需要按照helper[0,1,2]的数量依次赋值过来即可（每层循环把helper[i]--，如果helper[i]到0就i++就可以了），只是这样就不是计数排序比较标准的解法，我希望还是复习一下。
这种方法的时间复杂度是O(2*n)，空间是O(k)，k是颜色的数量，这里是3。
*/
class Solution {
    public void sortColors(int[] A) {
        if (A == null || A.length <= 1) return;
        int[] res = new int[A.length];
        int[] helper = new int[3]; //helper[0/1/2] counts the respective color
        for (int i = 0; i < A.length; i++) {
            helper[A[i]]++;
        }
        for (int i = 1; i < helper.length; i++) {
            helper[i] += helper[i - 1];
        }
        for (int i = 0; i < A.length; i++) {
            res[helper[A[i]] - 1] = A[i];
            helper[A[i]]--;
        }
        for (int i = 0; i < A.length; i++) {
            A[i] = res[i];
        }
    }
}


//Solution 2: Scan twice
class Solution {
    public void sortColors(int[] A) {
        if (A == null || A.length <= 1) return;
        int[] res = new int[A.length];
        int[] helper = new int[3]; //helper[0/1/2] counts the respective color
        for (int i = 0; i < A.length; i++) {
            helper[A[i]]++;
        }
        
        int color = 2;
        while (color >= 0) {
            int prev = 0; //stores the number of previous elements, help locate current index
            int c = color;
            while (c > 0) {
                prev += helper[--c];
            }
            while (helper[color] > 0) {
                A[prev + helper[color] - 1] = color;
                helper[color]--;
            }
            color--;
        }
    }
}



//Solution 3
/*
只用一次扫描来解决。其实还是利用了颜色是三种这一点，道理其实也简单，就是搞两个指针，一个指在当前0的
最后一个下标，另一个是指在当前1的最后一个下标（2不需要指针因为剩下的都是2了）。进行一次扫描，
如果遇到0就两个指针都前进一步并进行赋值，如果遇到1就后一个指针前进一步并赋值。

时间复杂度还是O(n)，只是只需要一次扫描，空间上是O(1)（如果颜色种类是已知的话）。
*/
class Solution {
    public void sortColors(int[] A) {
        if (A == null || A.length <= 1) return;
        
        int index0 = 0; //index of last occurred 0
        int index1 = 0; //index of last occurred 1
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                A[i] = 2;
                A[index1++] = 1;
                A[index0++] = 0;
            }
            else if (A[i] == 1) {
                A[i] = 2;
                A[index1++] = 1;
            }
        }
    }
}


//Solution 3 -- another version
// one pass in place solution
void sortColors(int A[], int n) {
    int n0 = -1, n1 = -1, n2 = -1;
    for (int i = 0; i < n; ++i) {
        if (A[i] == 0) 
        {
            A[++n2] = 2; A[++n1] = 1; A[++n0] = 0;
        }
        else if (A[i] == 1) 
        {
            A[++n2] = 2; A[++n1] = 1;
        }
        else if (A[i] == 2) 
        {
            A[++n2] = 2;
        }
    }
}