import java.util.Arrays;

public class KMP {


    public static int kmp(String haystack, String needle) {
        int[] next = getNextArray(needle);

        int m=haystack.length();
        int n=needle.length();
        for (int i=0,k = 0; k <m; k++) {
            while(i>0 && haystack.charAt(k)!=needle.charAt(i)){
                i=next[i-1];
            }
            if (haystack.charAt(k)==needle.charAt(i)){
                i++;
            }
            if (i==n){
                return k-i+1;
            }
        }
        return -1;
    }

    private static int[] getNextArray(String needle) {
        /**
         * 寻找next数组 i j分别代表前缀与后缀
         * aabaaac 则int[] next ={0,1,0,1,2,2,0}
         * 索引0 a         前缀                              后缀                                 则0
         * 索引1 aa        前缀 a                            后缀  a                              则1
         * 索引2 aab       前缀 a aa                         后缀  b ba                           则0
         * 索引3 aaba      前缀 a aa aab                     后缀 a ab aba                        则1
         * 索引4 aabaa     前缀 a aa aab aaba                后缀  a aa aab aaba                  则2
         * 索引5 aabaaa    前缀 a aa aab aaba aabaa          后缀  a aa aaa aaab  aaaba           则2
         * 索引6 aabaaac   前缀 a aa aab aaba aabaa  aabaaa  后缀  c ca caa caaa  caaab caaaba    则0
         * 索引 j前缀 i后缀
         *  i j 位置相等的处理
         *  i j 位置不相等的处理
         */
        int[] next = new int[needle.length()];
        int j = 0;
        for (int i = 1; i < needle.length(); i++) {
            while (j >0 && needle.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            next[i]=j;
        }
        return next;
    }

    public static void main(String[] args) {
        String s = "aaaaaabaaaaaaaaaaacfaaaaaaaaaaddd";
        String t = "aaaaad";
        //System.out.println(strStr(s, t));
        System.out.println(kmp(s,t));

    }


}

