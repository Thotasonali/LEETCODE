class Solution:
    def groupAnagrams(self, strs: list[str]) -> list[list[str]]:
      
        
        group=defaultdict(list)
        for s in strs:
            ls=s.lower()
            key=[0]*26
            for c in ls:
                key[ord(c)-97]+=1
            group[tuple(key)].append(s)

          
        return list(group.values())
        