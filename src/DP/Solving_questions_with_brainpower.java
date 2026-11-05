/*
Solving Questions With Brainpower

You are given a 0-indexed 2D integer array questions where questions[i] = [pointsi, brainpoweri].

The array describes the questions of an exam, where you have to process the questions in order (i.e., starting from question 0) and make a decision whether to solve or skip each question. Solving question i will earn you pointsi points but you will be unable to solve each of the next brainpoweri questions. If you skip question i, you get to make the decision on the next question.

For example, given questions = [[3, 2], [4, 3], [4, 4], [2, 5]]:
If question 0 is solved, you will earn 3 points but you will be unable to solve questions 1 and 2.
If instead, question 0 is skipped and question 1 is solved, you will earn 4 points but you will be unable to solve questions 2 and 3.
Return the maximum points you can earn for the exam.

https://leetcode.com/problems/solving-questions-with-brainpower/description/
*/

private Long[] dp;
private long f(int[][] q, int i) {
 int n=q.length;
 if(i==n)
     return dp[i]=0L;
 return dp[i]=Math.max(f(q, i+1), (long)q[i][0]+f(q, Math.min(i+1+q[i][1], n)));
}

public long mostPoints(int[][] q) {
    int n=q.length;
    long[] dp=new long[n+1];
    for(int i=n-1;i>=0;i--)
        dp[i]=Math.max(dp[i+1], (long)q[i][0]+dp[Math.min(i+1+q[i][1], n)]);
    return dp[0];
}

void main() {
    int[][] questions = {{3,2},{4,3},{4,4},{2,5}};
    int n=questions.length;
    dp=new Long[n+1];
    IO.println(f(questions, 0));
}