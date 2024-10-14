import sys
input = sys.stdin.readline

def distance(x,y):
    result = x**2 + y**2
    return result**(1/2)

def find(a):
    if a!= parent[a]:
        parent[a] = find(parent[a])
    return parent[a]

def union(a,b):
    a = find(a)
    b = find(b)
    if a ==b:
        return
        
    elif a<b:
        parent[b] = a
    else:
        parent[a] = b


n= int(input()) # 정점 개수
node = [] # 노드의 좌표평면에서 위치
edge = [] # 엣지 리스트
parent = [ i for i in range(n)]
for _ in range(n):
    x,y  = map(float, input().split())
    node.append((x,y))

for i in range(n-1):
    for j in range(i+1 , n):
        edge.append(((distance(node[i][0] - node[j][0], node[i][1]-node[j][1]), i, j)))

edge.sort()

result = 0

for weight, start, end in edge:
    if find(start) != find(end):
        result += weight
        union(start, end)
print(round(result, 2))
        