/*
String Compression

Given an array of characters chars, compress it using the following algorithm:

Begin with an empty string s. For each group of consecutive repeating characters in chars:

If the group's length is 1, append the character to s.
Otherwise, append the character followed by the group's length.
The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.

After you are done modifying the input array, return the new length of the array.

You must write an algorithm that uses only constant extra space.

Note: The characters in the array beyond the returned length do not matter and should be ignored.

https://leetcode.com/problems/string-compression/description/
*/

public int compress(char[] chars) {
    int i=0, write=0, n=chars.length;
    while(i<n) {
        char ch=chars[i];
        int j=i;
        while(j<n && chars[j]==ch)
            j++;
        String num=String.valueOf(j-i);
        chars[write++]=ch;
        if(j-i>1)
            for(char c:num.toCharArray())
                chars[write++]=c;
        i=j;
    }
    return write;
}

void main() {
    char[] chars={'a', 'a', 'b', 'b', 'c', 'c', 'c'};
    IO.println(compress(chars));
}