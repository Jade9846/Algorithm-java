# 숫자 카드

import sys
input  = sys.stdin.readline
have= []
target = []

N = int(input())
have = list(map(int, input().split()))
M = int(input())
target = list(map(int, input().split()))

dic =dict()

for card in have:
    if card in dic:
        dic[card] += 1
    else:
        dic[card] = 1

for i in target:
    if i in dic:
        print(dic[i],end = ' ')

    else:
        print(0, end = ' ')
