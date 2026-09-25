class Solution:
    def isPalindrome(self, s: str) -> bool:
        clean_s="".join(c.lower() for c in s if c.isalnum())
        n=len(clean_s)
        i=0
        while(i<n):
            if(clean_s[i]==clean_s[n-i-1]):
                i+=1
            else:
                return False
        return True

        