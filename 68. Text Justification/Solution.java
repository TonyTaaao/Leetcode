// beats 100%
// 逻辑简单，写起来复杂，注意细节处理
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new LinkedList<>();
        int index = 0;
        int length = words.length;
        while (index < length) {
            int count = words[index].length();
            int last = index + 1;
            while(last < length) {
                if (count + 1 + words[last].length() > maxWidth) break;
                count += 1 + words[last].length(); // count space and length of current word
                last++;
            }
            
            StringBuilder sb = new StringBuilder();
            sb.append(words[index]);
            int numberOfWords = last - index;
            if (last == length || numberOfWords == 1) {
                // if last line or number of words in the line is 1, left-justified
                for (int i = index + 1; i < last; i++) {
                    sb.append(" ");
                    sb.append(words[i]);
                }
                for (int i = sb.length(); i < maxWidth; i++) {
                    sb.append(" ");
                }
            } else {
                // middle justified
                int spaces = (maxWidth - count) / (numberOfWords - 1);
                int rem = (maxWidth - count) % (numberOfWords - 1);
                for (int i = index + 1; i < last; i++) {
                    for (int j = 0; j < spaces; j++) {
                        sb.append(" ");
                    }
                    if (rem > 0) {
                        sb.append(" ");
                        rem--;
                    }
                    sb.append(" ");
                    sb.append(words[i]);
                }
            }
            res.add(sb.toString());
            index = last;
        }
        return res;
    }
}