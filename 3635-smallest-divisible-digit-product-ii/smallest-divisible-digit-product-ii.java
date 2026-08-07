class Solution {
    int[] f2={0,0,1,0,2,0,1,0,3,0};
    int[] f3={0,0,0,1,0,0,1,0,0,2};
    int[] f5={0,0,0,0,0,1,0,0,0,0};
    int[] f7={0,0,0,0,0,0,0,1,0,0};
    int[][] dp=new int[60][40];

    public String smallestNumber(String num,long t){
        int a=0,b=0,c=0,d=0;

        while(t%2==0){a++;t/=2;}
        while(t%3==0){b++;t/=3;}
        while(t%5==0){c++;t/=5;}
        while(t%7==0){d++;t/=7;}

        if(t!=1) return "-1";

        buildDP();

        int n=num.length();
        int r2=a,r3=b,r5=c,r7=d;
        boolean zero=false;

        for(char ch:num.toCharArray()){
            int x=ch-'0';
            if(x==0) zero=true;
            r2=Math.max(0,r2-f2[x]);
            r3=Math.max(0,r3-f3[x]);
            r5=Math.max(0,r5-f5[x]);
            r7=Math.max(0,r7-f7[x]);
        }

        if(!zero && r2+r3+r5+r7==0) return num;

        int firstZero=num.indexOf('0');
        if(firstZero==-1) firstZero=n;

        int limit=Math.min(n-1,firstZero);
        int p2=0,p3=0,p5=0,p7=0;

        for(int i=0;i<limit;i++){
            int x=num.charAt(i)-'0';
            p2+=f2[x]; p3+=f3[x];
            p5+=f5[x]; p7+=f7[x];
        }

        for(int i=limit;i>=0;i--){
            int cur=num.charAt(i)-'0';

            for(int x=cur+1;x<=9;x++){
                int n2=Math.max(0,a-p2-f2[x]);
                int n3=Math.max(0,b-p3-f3[x]);
                int n5=Math.max(0,c-p5-f5[x]);
                int n7=Math.max(0,d-p7-f7[x]);

                int len=n-i-1;

                if(can(n2,n3,n5,n7,len))
                    return num.substring(0,i)+x+build(n2,n3,n5,n7,len);
            }

            if(i>0){
                int x=num.charAt(i-1)-'0';
                p2-=f2[x]; p3-=f3[x];
                p5-=f5[x]; p7-=f7[x];
            }
        }

        int len=Math.max(n+1,c+d+dp[a][b]);
        return build(a,b,c,d,len);
    }

    void buildDP(){
        for(int[] row:dp) java.util.Arrays.fill(row,1000000);
        dp[0][0]=0;

        int[][] f={{1,0},{0,1},{2,0},{1,1},{3,0},{0,2}};

        for(int i=0;i<60;i++)
            for(int j=0;j<40;j++)
                for(int[] x:f){
                    int ni=Math.min(59,i+x[0]);
                    int nj=Math.min(39,j+x[1]);
                    dp[ni][nj]=Math.min(dp[ni][nj],dp[i][j]+1);
                }

        for(int i=59;i>=0;i--)
            for(int j=39;j>=0;j--){
                if(i<59) dp[i][j]=Math.min(dp[i][j],dp[i+1][j]);
                if(j<39) dp[i][j]=Math.min(dp[i][j],dp[i][j+1]);
            }
    }

    boolean can(int a,int b,int c,int d,int len){
        return c+d+dp[a][b]<=len;
    }

    String build(int a,int b,int c,int d,int len){
        StringBuilder s=new StringBuilder();

        for(int i=0;i<len;i++){
            for(int x=1;x<=9;x++){
                int A=Math.max(0,a-f2[x]);
                int B=Math.max(0,b-f3[x]);
                int C=Math.max(0,c-f5[x]);
                int D=Math.max(0,d-f7[x]);

                if(can(A,B,C,D,len-i-1)){
                    s.append(x);
                    a=A;b=B;c=C;d=D;
                    break;
                }
            }
        }
        return s.toString();
    }
}