/**
 * This class represents time as "hours:minutes".
 * 
 * @author Elroi Izaks
 */
public class Time1
{

    //declarations
    private int _hour;
    private int _minute;
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
     * Constructs a Time1 object.
     * Construct a new time instance with the specified hour and minute .
     * hour should be between 0-23, otherwise it should be set to 0.
     * minute should be between 0-59, otherwise it should be set to 0.
     * @param h the hour of the time
     * @param m the minute of the time
     */
    public Time1 (int h, int m) {
        if (MIN_HOUR<=h && h<=MAX_HOUR)//check if hour is in the correct range
            _hour= h;
        else _hour= DEFAULT_VAL;
        if (MIN_MINUTES<=m && m<=MAX_MINUTES)//check if minute is in the correct range
            _minute= m;
        else _minute= DEFAULT_VAL;
    }

    /**
     * Copy constructor for Time1.
     * Construct a time with the same instance variables as another time.
     * @param other The time object from which to construct the new time
     */
    public Time1 (Time1 t) {
        _hour=t._hour;
        _minute=t._minute;
    }

    //getters
    /**
     * Returns the hour of the time.
     * @return hour of the time
     */
    public int getHour() {
        return _hour; 
    }

    /**
     * Returns the minute of the time.
     * @return minute of the time
     */
    public int getMinute() {
        return _minute;
    }

    //setters
    /**
     * Changes the hour of the time.
     * If an illegal number is received hour will be unchanged.
     * @param num The new hour 
     */
    public void setHour (int num) {
        if (MIN_HOUR<=num && num<=MAX_HOUR)//check if hour is in the correct range
            _hour= num;
    }

    /**
     * Changes the minute of the time.
     * If an illegal number is received minute will be unchanged.
     * @param num The new minute 
     */
    public void setMinute (int num) {
        if (MIN_MINUTES<=num && num<=MAX_MINUTES)//check if minute is in the correct range
            _minute= num;
    }

    //other methods
    /**
     * Returns a string representation of this time ("hh:mm").
     * @return String representation of this time ("hh:mm").
     */
    public String toString () {
        if (_hour>=MIN_TWO_DIGIT_NUM && _minute>=MIN_TWO_DIGIT_NUM)//check what number of digits the hour & the minute have 
        {return _hour + ":" + _minute;}
        if (_minute>=MIN_TWO_DIGIT_NUM)
        {return "0" + _hour + ":" + _minute;} 
        if (_hour>=MIN_TWO_DIGIT_NUM)
        {return _hour + ":" + "0" + _minute;}   
        return "0" + _hour + ":" + "0" + _minute;
    }

    /**
     * Return the amount of minutes since midnight.
     * @return the amount of minutes since midnight.
     */
    public int minFromMidnight (){
        return _hour*MINUTES_IN_HOUR + _minute;  
    }

    /**
     * Checks if the received time is equal to this time.
     * @param other the time to be compared with this time
     * @return true if the received time is equal to this time
     */
    public boolean equals (Time1 other){
        return (_hour==other._hour && _minute==other._minute);    
    }

    /**
     * Checks if this time is before a received time.
     * @param other The time to check if this time is before
     * @return true if this time is before other time
     */
    public boolean before (Time1 other){
        return (this.minFromMidnight() < other.minFromMidnight() );    
    }

    /**
     * Check if this time is after a received time.
     * @param other The time to check if this time is after
     * @return true if this time is after other time
     */
    public boolean after (Time1 other){
        return (other.before(this));    
    }

    /**
     * Calculates the difference (in minutes) between two times.
     * Assumption: this time is after other time.
     * @param other The time to check the difference to
     * @return int difference in minutes
     */
    public int difference (Time1 other){
        return this.minFromMidnight() - other.minFromMidnight();   
    }

    /**
     * Adds num Minutes to time.
     * @param num The number of minutes to add
     * @return the update time in other Time1 object
     */
    public Time1 addMinutes (int num){
        int t= this.minFromMidnight() + num;
        int days= (int)Math.floor(t/(double)MINUTES_IN_DAY);
        t= t-days*MINUTES_IN_DAY;
        int h= t/MINUTES_IN_HOUR;
        int m= t-h*MINUTES_IN_HOUR;
        return new Time1 (h,m);
    }
}