/**
 * Represents time by the minutes from midnight.
 * Values must represent a proper time
 * 
 * @author Elroi Izaks
 */
public class Time2
{

    //declarations
    private int _minFromMid;
    private final int DEFAULT_VAL=0;
    private final int MIN_HOUR=0;
    private final int MAX_HOUR=23;
    private final int MIN_MINUTES=0;
    private final int MAX_MINUTES=59;
    private final int MIN_TWO_DIGIT_NUM=10;
    private final int MINUTES_IN_HOUR=60;
    private final int MINUTES_IN_DAY=1440;

    //constructors
    /**
     * Constructs a Time2 object.
     * Construct a new time instance with the specified hour and minute .
     * hour should be between 0-23, otherwise it should be set to 0.
     * minute should be between 0-59, otherwise it should be set to 0.
     * @param h the hour of the time
     * @param m the minute of the time
     */
    public Time2 (int h, int m) {
        int hour, minute; 
        if (MIN_HOUR<=h && h<=MAX_HOUR)//check if hour is in the correct range
            hour= h;
        else hour= DEFAULT_VAL;
        if (MIN_MINUTES<=m && m<=MAX_MINUTES)//check if minute is in the correct range
            minute= m;
        else minute= DEFAULT_VAL;
        _minFromMid= hour*MINUTES_IN_HOUR + minute;
    }

    /**
     * Copy constructor for Time2.
     * Construct a time with the same instance variables as another time.
     * @param other The time object from which to construct the new time
     */
    public Time2 (Time2 t) {
        _minFromMid= t._minFromMid;
    }

    //getters
    /**
     * Returns the hour of the time.
     * @return hour of the time
     */
    public int getHour() {
        return _minFromMid/MINUTES_IN_HOUR;
    }

    /**
     * Returns the minute of the time.
     * @return minute of the time
     */
    public int getMinute() {
        return _minFromMid - this.getHour()*MINUTES_IN_HOUR;
    }

    //setters
    /**
     * Changes the hour of the time.
     * If an illegal number is received hour will be unchanged.
     * @param num The new hour 
     */
    public void setHour (int num) {
        int hour= this.getHour();
        if (MIN_HOUR<=num && num<=MAX_HOUR)//check if hour is in the correct range
            hour= num;
        _minFromMid= this.getMinute() + hour*MINUTES_IN_HOUR;
    }

    /**
     * Changes the minute of the time.
     * If an illegal number is received minute will be unchanged.
     * @param num The new minute 
     */
    public void setMinute (int num) {
        int minute= this.getMinute ();
        if (MIN_MINUTES<=num && num<=MAX_MINUTES)//check if minute is in the correct range
            minute= num;
        _minFromMid= MINUTES_IN_HOUR*this.getHour () + minute;
    }

    //other methods
    /**
     * Returns a string representation of this time ("hh:mm").
     * @return String representation of this time ("hh:mm").
     */
    public String toString () {
        int hour= this.getHour ();
        int minute= this.getMinute ();
        if (hour>=MIN_TWO_DIGIT_NUM && minute>=MIN_TWO_DIGIT_NUM)//check what number of digits the hour & the minute have 
        {return hour + ":" + minute;} 
        if (minute>=MIN_TWO_DIGIT_NUM)
        { return "0" + hour + ":" + minute;}    
        if (hour>=MIN_TWO_DIGIT_NUM)
        {return hour + ":" + "0" + minute;} 
        return "0" + hour + ":" + "0" + minute;
    }

    /**
     * Return the amount of minutes since midnight.
     * @return the amount of minutes since midnight.
     */
    public int minFromMidnight (){
        return _minFromMid;
    }

    /**
     * Checks if the received time is equal to this time.
     * @param other the time to be compared with this time
     * @return true if the received time is equal to this time
     */
    public boolean equals (Time2 other){
        return this._minFromMid==other._minFromMid;  
    }

    /**
     * Checks if this time is before a received time.
     * @param other The time to check if this time is before
     * @return true if this time is before other time
     */
    public boolean before (Time2 other){
        return (this._minFromMid < other._minFromMid);    
    }

    /**
     * Check if this time is after a received time.
     * @param other The time to check if this time is after
     * @return true if this time is after other time
     */
    public boolean after (Time2 other){
        return (other.before(this));    
    }

    /**
     * Calculates the difference (in minutes) between two times.
     * Assumption: this time is after other time.
     * @param other The time to check the difference to
     * @return int difference in minutes
     */
    public int difference (Time2 other){
        return this._minFromMid - other._minFromMid;   
    }

    /**
     * Adds num Minutes to time.
     * @param num The number of minutes to add
     * @return the update time in other Time2 object
     */
    public Time2 addMinutes (int num){
        int t= this._minFromMid + num;
        int days= (int)Math.floor(t/(double)MINUTES_IN_DAY);
        t= t-days*MINUTES_IN_DAY;
        int h= t/MINUTES_IN_HOUR;
        int m= t-h*MINUTES_IN_HOUR;
        return new Time2 (h,m);
    }
}