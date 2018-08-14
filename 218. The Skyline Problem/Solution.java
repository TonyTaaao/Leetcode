// beats 89%
// TreeMap: Time Complexity = O(nlogn), Space Complexity = O(N)
/*
每栋楼都有一个进入点（高度为正）和一个退出点（高度为负），每当一栋楼进入的时候，给对应高度＋1；每当一栋楼
退出的时候，给对应高度－1，若当前高度出现的次数减为0，删除该高度。每当一栋楼进出的时候，判断当前位置的最大高度
是否发生了改变，若改变则做出修改（在该位置添加新数组［当前位置，更新后的当前最高高度］）。
只判断当前位置的最高高度是否变化，因为低的楼会被高的楼覆盖。
*/
class HeightComparator implements Comparator<int[]> {
	@Override
	public int compare(int[] h1, int[] h2) {
		// if both arrays are at the same position, put the one with greater height first
		if (h1[0] != h2[0])
			return h1[0] - h2[0];
		else
			return h2[1] - h1[1];
	}
	
}
public class Solution {
	public List<int[]> getSkyline(int[][] buildings) {
		List<int[]> res = new ArrayList<>();
		if (buildings == null || buildings.length == 0)
			return res;
		int n = buildings.length;
		int[][] heights = new int[2 * n][2];
		for (int i =  0; i < n; ++i) {
			//e.g. for  [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ], store [2  10], [9, -10]
			heights[2 * i][0] = buildings[i][0];
			heights[2 * i][1] = buildings[i][2];
			
			heights[2 * i + 1][0] = buildings[i][1];
			heights[2 * i + 1][1] = -buildings[i][2];
		}
		
		Arrays.sort(heights, new HeightComparator());
		
		// Then scan from left to right
		TreeMap<Integer, Integer> cnt = new TreeMap<>();

		for (int i = 0; i < 2 * n; ++i) {
			int this_pos = heights[i][0];
			int this_height = heights[i][1];
			if (this_height > 0) {
				cnt.put(this_height, cnt.getOrDefault(this_height, 0) + 1);
			} else {
				int this_cnt = cnt.get(-this_height);
				if (this_cnt > 1) {
					cnt.put(-this_height, this_cnt - 1);
				} else
					cnt.remove(-this_height);
			}
			
            // 优化：当相邻两个的左端点相同时，高度高的在前面
            /*
			if (i < 2 * n - 1 && heights[i][0] == heights[i + 1][0])
				continue;
			*/
            
			// try to update with new height
			int cur_height = (cnt.isEmpty()) ? 0 : cnt.lastKey(); //返回tree map中最大的key值
			if (res.size() == 0 || cur_height != res.get(res.size() - 1)[1])
				res.add(new int[] {this_pos, cur_height});
		}
		return res;
	}
}

