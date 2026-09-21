class Solution:
    def groupAnagrams(self, strs: list[str]) -> list[list[str]]:
      
        l=len(strs)
        if(l==0):
            return []
        else:
            group=defaultdict(list)
            for s in strs:
                so= "".join(sorted(s))
                if so in group:
                    group[so].append(s)
                else:
                    group[so].append(s)
        return list(group.values())
        