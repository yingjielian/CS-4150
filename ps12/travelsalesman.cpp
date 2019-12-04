#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
#include <cmath>

using namespace std;

int bound = 99999;
int te = 0;
int N, *d;
bool *s;
vector<vector<int>> priority;
vector<vector<int>> matrix;
vector<vector<int>> cache;

int travel(int curr, int remain, int max) {
    if (cache[curr][remain] == 0) {

        if (max <= bound) {
            int min = 99999;
            int i;
            int t;
            int z;
            int newmax;
            for (i = 0; i <= N-1; i++) {
                z = priority[curr][i];
                if (s[z]) {
                    s[z] = 0;
                    t = travel(z, remain + d[z], max + matrix[curr][z]) + matrix[curr][z];
                    s[z] = 1;

                    if (t < min) {
                        min = t;
                        newmax = min + max + matrix[curr][z] + t;
                        if (newmax < bound) {
                            bound = newmax;
                        }
                    }
                }
            }

            cache[curr][remain] = min;
            
        } else {
            return 99999;
        }

        if (find(s, s + N, 1) == s + N) {
            if (max + matrix[curr][0] < bound) {
                bound = max + matrix[curr][0];
            }

            return matrix[curr][0];
        }
    }

    return cache[curr][remain];
}

bool Compare(int lhs, int rhs) {
    bool com;
    if(matrix[te][lhs] < matrix[te][rhs])
    {
        com = true;
    }
    else
    {
        com = false;
    }
    return com;
}

int main() {
    cin >> N;

    matrix.resize(N);
    int N2 = int(pow(2.0, N + 1));
    int i;
    int x = 1;
    d = new int[N];
    s = new bool[N];

    for (i = 0; i <= N-1; i++) {
        d[i] = x;
        s[i] = true;
        x *= 2;
        matrix[i].resize(N);
        vector<int> s;
        int j;
        int a;
        for (j = 0; j <= N-1; j++) {
            cin >> a;
            matrix[i][j] = a;

            if (j != i) {
                s.push_back(j);
            }
        }
        sort(s.begin(), s.end(), Compare);
        te = i;
        priority.push_back(s);
        vector<int> m(N2, 0);
        cache.push_back(m);
    }

    s[0] = 0;

    cout << travel(0, 0, 0) << endl;
}