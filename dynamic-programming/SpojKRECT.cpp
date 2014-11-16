#include<iostream>
#include<cstdio>
#include<string>
#include<cstring>

using namespace std;

int table[1<<8];

int bitcount(int x){
    return table[x&0xff] + table[x>>8 & 0xff] + table[x>>16 & 0xff] + table[x>>24 & 0xff];
}

int main(){
    for(int i=0;i<8;i++) table[1<<i] = 1;
    for(int i=0;i<(1<<8);i++) table[i] = table[i&(i-1)] + table[i&-i];
    int m,n,k;
    cin>>m>>n>>k;
    string input[m];
    int grid[m][n];
    for(int i=0;i<m;i++){
        cin>>input[i];
        for(int j=0;j<n;j++){
            grid[i][j] = 1 << (input[i][j] - 'A');
        }
    }    
    int mask[n+1];
    int count = 0;
    for(int i1=0;i1<m;i1++){
        for(int j1=0;j1<n;j1++){
            memset(mask,0,sizeof(mask));
            for(int i2=i1;i2<m;i2++){
                for(int j2=j1;j2<n;j2++){
                    mask[j2+1] |= mask[j2] | grid[i2][j2];
                    if(bitcount(mask[j2+1]) == k){
                        count++;
                    }                    
                }
            }   
        }

    }
    cout<<count<<"\n";

}
