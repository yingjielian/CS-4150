import sys

numArray = {}
def findMax(N, v, r, num, k):
    
    if (r,num, k) in numArray:
        return numArray[(r,num, k)]

    maxNum = 0
    diff = N - r

    if k < diff and r < N:
        if num == 1:
            case1 = findMax(N,v,r+1,-1,k)+v[r][0]+v[r][1]
            case2 = findMax(N,v,r+1,1,k-1)+v[r][1]
            maxNum = max(case2, case1)
            numArray[(r,num, k)] = maxNum
            return maxNum
        if num == 0:
            case1 = findMax(N,v,r+1,-1,k)+v[r][0]+v[r][1]
            case3 = findMax(N,v,r+1,0,k-1)+v[r][0]
            maxNum = max(case3, case1)
            numArray[(r,num, k)] = maxNum
            return maxNum
        if num == -1:
            case1 = findMax(N,v,r+1,-1,k)+v[r][0]+v[r][1]
            case2 = findMax(N,v,r+1,1,k-1)+v[r][1]
            case3 = findMax(N,v,r+1,0,k-1)+v[r][0]
            maxNum = max(case3, case2, case1)
            numArray[(r,num, k)] = maxNum
            return maxNum

    if k == diff and r < N:
        if num == 1:
            maxNum = findMax(N,v,r+1,1,k-1)+v[r][1]
            numArray[(r,num, k)] = maxNum
            return maxNum
        if num == 0:
            maxNum = findMax(N,v,r+1,0,k-1)+v[r][0]
            numArray[(r,num, k)] = maxNum
            return maxNum
        if num == -1:
            case2 = findMax(N,v,r+1,1,k-1)+v[r][1]
            case3 = findMax(N,v,r+1,0,k-1)+v[r][0]
            maxNum = max(case3, case2)
            return maxNum
    return maxNum

def main():
    readLine = sys.stdin.readline().split()
    num = 0
    N = int(readLine[0])

    nums = [[0 for x in range(2)] for y in range(N)]

    k = int(readLine[1])
    for i in range(0,N,1):
        line = sys.stdin.readline()
        newLine = line.split()
        nums[i][1] = int(newLine[1])
        nums[i][0] = int(newLine[0])

    sys.stdin.readline()
    
    if k <= N:
        num += findMax(N, nums, 0, -1, k);
    print (num)
if __name__ == "__main__":
    main()