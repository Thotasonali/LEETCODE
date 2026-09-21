class Solution:
    def groupAnagrams(self, strs: list[str]) -> list[list[str]]:
      
        
        group=defaultdict(list)
        for s in strs:
            so= "".join(sorted(s))
            
            group[so].append(s)
           
        return list(group.values())
        