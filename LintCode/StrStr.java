
public class StrStr {
    /*
     * @param source: source string to be scanned.
     * @param target: target string containing the sequence of characters to match
     * @return: a index to the first occurrence of target in source, or -1  if target is not part of source.
     */
/*
    public int strStr(String source, String target) {
        // write your code here
        if(source==null || target==null) {
            return -1;
        }

        int lenSrc=source.length();
        int lenDst=target.length();
        if(lenSrc < lenDst) {
            return -1;
        }

        int     start=0;
        boolean bFind=false;
        while(!bFind && start < (lenSrc-lenDst+1)) {

            bFind=false;
            int i=0;
            while(i<lenDst && source.charAt(start+i)==target.charAt(i)) {
                i++;
            }
            if(i == lenDst) {
                bFind=true;
            } else {
                start++;
            }
        }
        if(bFind) {
            return start;
        } else {
            return -1;
        }
    }
*/
    public int strStr(String source, String target) {
        if(source==null || target==null) {
            return -1;
        }

        int lengSrc = source.length();
        int lengDst = target.length();
        int i, j;
        for(i=0; i<lengSrc-lengDst+1; i++) {
            for(j=0; j<lengDst; j++) {
                if(source.charAt(i+j) != target.charAt(j)) {
                    break;
                }
            }

            if(j == lengDst) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        StrStr solution = new StrStr();
        String src = "abcdefg";
        String dst = "bcd";
        int pos = solution.strStr(src, dst);
        System.out.println("\""+dst+"\" is in the position of \"" + src+"\" - pos=" + pos);
    }
}
