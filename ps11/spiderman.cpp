#include <bits/stdc++.h>
using namespace std;

int N, M, up, down, curr, arr[42], dp[42][1003], dir[42][1003];
char direction;

int main() {
    cin >> N;

    while(N-- > 0) {
        cin >> M;

        int i;
        for(i = 0; i <= M-1; i++) {
            cin >> arr[i];
        }
        memset(dp, -1, sizeof(dp));
        int store = dp[0][0];
        dp[M][0] = 0;
         for (i = M - 1; i > -1; i--) {
         	int j;
            for (j = 0; j <= 1002; j++) {
            	int numMax = arr[i] + j;
            	int numMin = dp[i + 1][arr[i] + j];
            	int temp = j - arr[i];
                if ( numMax < 1003 && numMin != -1) {
                    dp[i][j] = max(j, numMin);
                    dir[i][j] = 1;
                }

                if (temp >= 0 && dp[i + 1][temp] != -1 && (dp[i][j] == -1 || dp[i][j] > dp[i + 1][temp])) {
                	store = 2;
                    dp[i][j] = max(j, dp[i + 1][temp]);
                    dir[i][j] = -1;
                }
            }
        }
        int initial = dp[0][0];
        if (initial == -1) {
            cout << "IMPOSSIBLE" << endl;
        } else {
            curr = 0;
            int i;
            for (i = 0; i <= M-1; i++) {
                direction = dir[i][curr] == 1 ? 'U' : 'D';
                curr += dir[i][curr] * arr[i];
                cout << direction;
            }
            cout << endl;
        }
    }

    return 0;
}