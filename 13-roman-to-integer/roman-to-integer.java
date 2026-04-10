class Solution {
    public int romanToInt(String s) {
        int [] map=new int[128];
        map['I']=1;map['V']=5;
        map['L']=50 ;map['X']=10;
        map['C'] =100; map['D']=500;map['M']=1000;
        int total=0;
        for(int i=0;i<s.length();i++){
            if(i<s.length()-1 && map[s.charAt(i)]<map[s.charAt(i+1)]){
                total -=map[s.charAt(i)];

            }
            else{
                total+=map[s.charAt(i)];
            }
        }
        return total;
    }
   
}
