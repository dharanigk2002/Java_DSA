/*
Valid Parenthesis String

Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.

The following rules define a valid string:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".

https://leetcode.com/problems/valid-parenthesis-string/description/
*/

private Boolean[][] dp;
private boolean f(String s, int i, int cnt) {
    int n=s.length();
    if(dp[i][cnt]!=null)
        return dp[i][cnt];
    if(i==n)
        return dp[i][cnt]=cnt==0;
    if(s.charAt(i)=='(' && f(s, i+1, cnt+1))
        return dp[i][cnt]=true;
    if(s.charAt(i)==')' && cnt>0 && f(s, i+1, cnt-1))
        return dp[i][cnt]=true;
    if(s.charAt(i)=='*' && (f(s, i+1, cnt+1) || f(s, i+1, cnt) || cnt>0 && f(s, i+1, cnt-1)))
        return dp[i][cnt]=true;
    return dp[i][cnt]=false;
}

public boolean checkValidString(String s) {
    int n=s.length();
    dp=new Boolean[n+1][n+1];
    return f(s, 0, 0);
}

public boolean bottomUp(String s) {
    int n=s.length();
    boolean[][] dp=new boolean[n+1][n+1];
    dp[n][0]=true;
    for(int i=n-1;i>=0;i--)
        for(int cnt=n-1;cnt>=0;cnt--) {
            if(s.charAt(i)=='(' && dp[i+1][cnt+1])
                dp[i][cnt]=true;
            else if(s.charAt(i)==')' && cnt>0 && dp[i+1][cnt-1])
                dp[i][cnt]=true;
            else if(s.charAt(i)=='*' && (dp[i+1][cnt+1] || dp[i+1][cnt] || cnt>0 && dp[i+1][cnt-1]))
                dp[i][cnt]=true;
            else
                dp[i][cnt]=false;
        }
    return dp[0][0];
}

public boolean bottomUpOptimized(String s) {
    int n=s.length();
    boolean[] dp=new boolean[n+1];
    dp[0]=true;
    for(int i=n-1;i>=0;i--) {
        boolean[] curr=new boolean[n+1];
        curr[0]=true;
        for(int cnt=n-1;cnt>=0;cnt--) {
            if(s.charAt(i)=='(' && dp[cnt+1])
                curr[cnt]=true;
            else if(s.charAt(i)==')' && cnt>0 && dp[cnt-1])
                curr[cnt]=true;
            else if(s.charAt(i)=='*' && (dp[cnt+1] || dp[cnt] || cnt>0 && dp[cnt-1]))
                curr[cnt]=true;
            else
                curr[cnt]=false;
        }
        dp=curr;
    }
    return dp[0];
}

public boolean optimized(String s) {
    int high = 0,low=0;
    for(char c: s.toCharArray()){
        if(c=='('){
            low++;
            high++;
        }
        if(c==')'){
            low--;
            high--;
        }
        if(c=='*'){
            low--;
            high++;
        }
        if(low<0) low=0;
        if(high<0) return false;
    }
    if(low==0) return true;
    return false;
}

void main() {
    String s = "(*)";
    IO.println(checkValidString(s));
}