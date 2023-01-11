/**
 * class RailwayStation - represents a Train station
 * 
 * @author Elroi Izaks
 */ 
public class RailwayStation
{

    //declarations
    private Train [] _station;
    private int _noOfTrs;
    private final int MAX_TRAINS= 100;

    //constructor
    /**
     * Constructor of class RailwayStation.
     * Constructs a new railway station.
     * made a railway station with a room for 100 trains.
     * set the trains's counter with the value 0. 
     */
    public RailwayStation ()  {
        _station= new Train [MAX_TRAINS];
        _noOfTrs= 0;
    }

    //methods
    /**
     * Add new train to the station.
     * if the station is full, the train won't be added.
     * if the train is already in the station, the train won't be added.
     * @param f the train that would be added to the station.
     * @return true if the train was added successfully.
     */
    public boolean addTrain (Train f) {
        for (int i=0;i<_noOfTrs;i++){
            if (_station[i].equals(f)){//check if train f is already in the station
                return false;
            }
        }
        if (_noOfTrs==MAX_TRAINS) {//check if the station is full
            return false;}
        _station [_noOfTrs++]=new Train(f);//declare the first null as train f, and +1 the _noOfTrs
        return true;
    }

    /**
     * Remove specific train from the station.
     * if the station is empty, or the train isn't in the station, the method return false.
     * @param f the train that would be removed from the station.
     * @return true if the train was removed successfully.
     */
    public boolean removeTrain (Train f) {
        int i;
        if (_station [0]!=null){
            for (i=0;i<_noOfTrs;i++) {//found the train to be removed
                if (_station[i].equals(f)) {
                    _station [i]=_station [--_noOfTrs];//put the last train at the place of the removed train 
                    _station [_noOfTrs]=null;//nulls the last train
                    return true;
                }
            }
        }
        return false;//train wasn't found
    }

    /**
     * Check the first time of departure for specific destination.
     * if there is no train to this destination, the method returns null.
     * @param place the destination to be checked
     * @return the first departure to this destination.
     */
    public Time1 firstDepartureToDestination (String place) {
        Time1 firstTrain=null;
        for (int i=0;i<_noOfTrs;i++){
            if (_station[i].getDestination().equals (place)){//check which train goes to this destination
                if (firstTrain== null){//put the 'first train in the array to this destination' as firstTrain
                    firstTrain=new Time1 (_station[i].getDeparture());
                }
                if (_station[i].getDeparture().before(firstTrain)){//check which train goes to the dastination earlier
                    firstTrain=new Time1 (_station[i].getDeparture());//set the time of the first train
                }
            }
        }
        return firstTrain;
    }

    /**
     * Returns how many trains are full.
     * @return number of full trains.
     */
    public int howManyFullTrains () {
        int i;
        int sum= 0;
        for (i=0;i<_noOfTrs;i++){
            if (_station[i].isFull()) {//check how many trains are full
                sum++;
            }
        }
        return sum;
    }

    /**
     * Returns the most popular destination (by counting the number of trains).
     * if there are several destinations that are equally popular, method returns the first destination to be found. 
     * if there are no trains, the method returns null.
     * @return string of the most popular destination
     */
    public String mostPopularDestination () {
        int mosPopDesTrs=0;
        String mosPopDes=null;
        if (_station[0]!=null){
            mosPopDes=_station[0].getDestination();

            for (int i=0;i<_noOfTrs;i++){
                int thisTrain=0;
                for (int j=i+1;j<_noOfTrs;j++){

                    if (_station[i].getDestination().equals(_station [j].getDestination())){
                        //check if trainJ goes to the same destination as trainI
                        thisTrain++;
                    }

                }
                if (thisTrain>mosPopDesTrs){//check if trainI's destination is the most popular destination
                    mosPopDesTrs=thisTrain;
                    mosPopDes= _station[i].getDestination();
                }
            }
        }
        return mosPopDes;
    }

    /**
     * Returns the train that have the most expensive ticket.
     * if there are no trains, method returns null.
     * if there are several trains that equally have the most expensive ticket, mathod returns the first to be found.
     * @return the train that has the most expensive ticket.
     */
    public Train mostExpensiveTicket () {
        Train moExpTra=null;
        int moExpPri=-1;
        for (int i=0;i<_noOfTrs;i++){
            if (_station[i].getPrice()>moExpPri) {//check if trainI's ticket has the most expensive price 
                moExpPri=_station[i].getPrice();
                moExpTra=new Train (_station[i]);
            }
        }
        return moExpTra;
    }

    /**
     * Returns the train with the longest road (by time).
     * if there are no trains, method returns null.
     * if there are several trains with equal road time, mathod returns the first to be found.
     * @return the train that have the longest road.
     */
    public Train longestTrain () {
        Train moLoTra=null;
        int moLoRoa=0;
        for (int i=0;i<_noOfTrs;i++){
            if (_station[i].getDuration()>moLoRoa) {//check if trainI's duration is longer than moLoRoa
                moLoRoa=_station[i].getDuration();
                moLoTra= new Train (_station[i]);
            }
        }
        return moLoTra;
    }

    /**
     * Return a string representation of the railway station.
     * @return string representation of the railway station
     */
    public String toString () {
        if (_noOfTrs==0){//check if there are trains in the station
            return "There are no trains today."; 
        }
        String trainsStatus= "The trains today are:\n";
        for (int i=0;i<_noOfTrs;i++){//string the list of the trains
            trainsStatus+=_station[i].toString() + "\n";
        }
        return trainsStatus;   
    }
}