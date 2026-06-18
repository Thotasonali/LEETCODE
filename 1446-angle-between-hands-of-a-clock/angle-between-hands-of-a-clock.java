class Solution {
    public double angleClock(int hour, int minutes) {
        double meachmin=360/60 ;
        double heachhr= 360/12;
        double heachmin=heachhr/60;

        double minangle=minutes* meachmin;
        double hrangle=hour*heachhr+minutes*heachmin;
        double diff=Math.abs(minangle-hrangle);
        return Math.min(diff,360-diff);


        
    }
}