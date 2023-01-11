/**
 * class Train - represents a Train
 * 
 * @author Elroi Izaks
 */ 
public class Train
{

    //declarations
    private String _destination;
    private Time1 _departure;
    private int _duration;
    private int _passengers;
    private int _seats;
    private int _price;
    private final int MIN_VALUE= 0;
    private final int MINUTES_IN_HOUR= 60;

    //constructors
    /**
     * Constructor of class Train.
     * Constructs a new train. duration should be positive, otherwise it should be set to 0.
     * pass should be positive, otherwise it should be set to 0.
     * pass should be less than seats otherwise it should be set to seats.
     * seats should be positive, otherwise it should be set to 0.
     * price should be positive, otherwise it should be set to 0.
     * @param des the destination of the train
     * @param depHour the hour of departure
     * @param depMin the minute of departure
     * @param dur the duration of the travel
     * @param pass the number of passeners
     * @param seats  the number of seats in the train
     * @param pri the price of the travel
     */
    public Train (String des, int depHour, int depMin, int dur, int pass, int seats, int pri)  {
        _destination= des;
        _departure= new Time1 (depHour,depMin);//check if parameters are in the correct range
        if (dur<0)
        {dur=MIN_VALUE;}
        _duration= dur;
        if (pass>seats)
        {pass=seats;}
        if (pass<MIN_VALUE)
        {pass=MIN_VALUE;}
        _passengers= pass;
        if (seats<MIN_VALUE)
        {seats=MIN_VALUE;}
        _seats= seats;
        if (pri<MIN_VALUE)
        {pri=MIN_VALUE;}
        _price= pri;  
    }

    /**
     * Copy constructor for Train.
     * Construct a train with the same instance variables as another train.
     * @param other The train object from which to construct the new train.
     */
    public Train (Train other)  {
        _destination= new String(other._destination);
        _departure= new Time1(other._departure);
        _duration= other._duration;
        _passengers= other._passengers;
        _seats= other._seats;
        _price= other._price;   
    }

    //getters
    /**
     * Returns the destination.
     * @return the distenation of the train
     */
    public String getDestination () {
        return _destination;
    }

    /**
     * Returns the departure time.
     * @return the departure time
     */
    public Time1 getDeparture () {
        return new Time1(_departure);   
    }

    /**
     * Returns the duration of the train.
     * @return the duration of the train
     */
    public int getDuration () {
        return _duration;   
    }

    /**
     * Returns the number of passengers.
     * @return the number of passangers
     */
    public int getPassengers () {
        return _passengers;   
    }

    /**
     * Returns the number of seats.
     * @return the number of seats
     */
    public int getSeats () {
        return _seats;   
    }

    /**
     * Returns the price pf the train.
     * @return the price of thr train
     */
    public int getPrice () {
        return _price;   
    }

    //setters
    /**
     * Updates the destination of the train.
     * d in not null.
     * @param d the new destination of the train
     */
    public void setDestination (String d) {
        _destination= d;   
    }

    /**
     * Updates the departure time of the train.
     * t in not null.
     * @param t the new departure time of the train
     */
    public void setDeparture (Time1 t) {
        _departure= new Time1(t);   
    }

    /**
     * Updates the duration of the train.
     * d should be positive or zero, otherwise duration is unchanged.
     * @param d the new duration of the train
     */
    public void setDuration (int d) {
        if (d>=MIN_VALUE)//check if d is in the correct range
        {_duration= d;}
    }

    /**
     * Updates the number of passengers.
     * p should be positive or zero, otherwise passengers is unchanged.
     * p should be less than seats otherwise it should be set to seats.
     * @param p the new number of passengers
     */
    public void setPassengers (int p) {
        if (p>=MIN_VALUE)//check if p is in the correct range
        {_passengers= p;}
        if (_passengers > _seats) //check if there is more passengers than seats in the train
        {_passengers = _seats;}
    }

    /**
     * Updates the number of seats.
     * s should be positive or zero, otherwise seats is unchanged.
     * s should be larger than passengers, otherwise seats is unchanged.
     * @param s the new number of seats
     */
    public void setSeats (int s) {
        if (s>=MIN_VALUE && s>_passengers)//check if s is in the correct range & more than the number of passengers
        {_seats= s;}
    }

    /**
     * updates the price.
     * p should be positive or zero, otherwise price is unchanged.
     * @param p the new price
     */
    public void setPrice (int p) {
        if (p>=MIN_VALUE)//check if p is in the correct range
        {_price= p;}
    }

    //other methods
    /**
     * Check if the received train is equal to this train.
     * @param other the train to be compared with this train
     * @return true if the received train is equal to this train
     */
    public boolean equals (Train other) {
        return (_destination.equals(other._destination) && _seats==other._seats && _departure.equals(other._departure));
    }

    /**
     * Returns the arrival time.
     * @return the arrival time
     */
    public Time1 getArrivalTime () {
        int arrT,h,m;
        arrT=_departure.minFromMidnight ();
        arrT= arrT+ _duration;
        h= arrT / MINUTES_IN_HOUR;
        m= arrT- h*MINUTES_IN_HOUR;
        return new Time1 (h,m);
    }

    /**
     * Add num passengers to the train.
     * @param num the number of passengers to add
     * @return true if the total number of current passengers and num is less or equal to seats
     */
    public boolean addPassengers (int num) {
        if (_passengers+num<_seats)
        {_passengers+=num; return true;}
        return false;
    }

    /**
     * Returns true if train is full.
     * @return true if train is full
     */
    public boolean isFull () {
        return (_passengers==_seats);   
    }

    /**
     * Returns true if the price for this train is cheaper than the other train.
     * other is not null.
     * @param other  the other train to compare price with
     * @return true if the price for this train is cheaper than the other train
     */
    public boolean isCheaper (Train other) {
        return (_price < other._price);   
    }

    /**
     * Returns the total price for all passengers.
     * @return the total price for all passengers
     */
    public int totalPrice () {
        return _passengers*_price;
    }

    /**
     * Returns true if the arrival time of this train is earlier than the arrival time of the other train.
     * other is not null.
     * @param other the other train to compare arrival time with
     * @return true if the arrival time of this train is earlier than arrival time of the other train
     */
    public boolean arrivesEarlier (Train other) {
        return (this.getArrivalTime().minFromMidnight () < other.getArrivalTime().minFromMidnight () );
    }

    /**
     * Return a string representation of the train.
     * @return string representation of the train
     */
    public String toString () {
        String trainStatus;
        if (this.isFull())//check if the train is full
        {trainStatus= "full";}
        else
        {trainStatus= "not full";}
        return ("Train to "+ _destination + " departs at "+ _departure + ". train is " + trainStatus);   
    }
}
