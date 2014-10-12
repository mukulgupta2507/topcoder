#include<iostream>
#include<cstdio>

using namespace std;

int max(int a,int b){
    return a>b?a:b;
}
int solution(int y[505][505],int b[505][505],int n, int m){
    for(int i=0;i<n;i++){
        for(int j=1;j<m;j++){
            y[i][j] += y[i][j-1];
        }
    }
    for(int j=0;j<m;j++){
        for(int i=1;i<n;i++){
            b[i][j] += b[i-1][j];
        }
    }
    int dp[n+1][m+1];
    for(int i=0;i<=m;i++){
        dp[0][i] = 0;
    }
    for(int i=0;i<=n;i++){
        dp[i][0] = 0;
    }
    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            dp[i][j] = max(dp[i-1][j]+y[i-1][j-1],dp[i][j-1]+b[i-1][j-1]);
        }
    }
    return dp[n][m];
    
}

int main(){
    while(1){
        int n,m;
        scanf("%d%d",&n,&m);
        if(n==0 && m==0)
            break;
        int y[505][505];
        int b[505][505];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                scanf("%d",&y[i][j]);
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                scanf("%d",&b[i][j]);
            }
        }

        cout<<solution(y,b,n,m)<<"\n";
    }
    return 0;
}
