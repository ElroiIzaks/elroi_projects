public class Point
{
    //variables
    private double _x; //the x value of the point
    private double _y; //the y value of the point

    //constructors
    /**
     * Construct a point.
     * @param x the x coordinate.
     * @param y the y coordinate.
     */
    public Point (double x, double y)
    {
        _x=x;
        _y=y;
    }

    /**
     * Copy constructor for Point.
     * constuct a point with the same coordinate as other point.
     * @param other the point object from which to construct the new point.
     */
    public Point (Point other)
    {
        _x=other._x;
        _y=other._y;
    }

    //getters
    /**
     * Returns the x coordinate of the point.
     * @return the x coordinate of the point.
     */
    public double getX()
    {
        return _x;
    }

    /**
     * 
     * Returns the y coordinate of the point.
     * @return the y coordinate of the point.
     */
    public double getY()
    {
        return _y;
    }

    //setters
    /**
     * Sets the x coordinate of the point.
     * @param x the new x coordinate.
     */
    public void setX (double num)
    {
        _x=num;
    }

    /**
     * 
     * Sets the y coordinate of the point.
     * @param y the new y coordinate.
     */
    public void setY (double num)
    {
        _y=num;
    }

    //other methods
    /**
     * Returns a string representation of this point.
     * @return string representation of this point.
     */
    public String toString ()
    {
        return ("(" + _x + "," + _y + ")");
    }

    /**
     * Check if this point equals other point.
     * @param other the point to be compared with the point.
     * @return true if this point equals other point. 
     */
    public boolean equals (Point other)
    {
        return (_x==other._x && _y==other._y);
    }

    /**
     * Check if this point is above other point.
     * @param other the point to be compared with this point.
     * @return true if this point is above other point.
     */
    public boolean isAbove (Point other)
    {
        return (_y>other._y);
    }

    /**
     * Check if this point is under other point.
     * @param other the point to be compared with this point.
     * @return true if this point is under other point.
     */
    public boolean isUnder (Point other)
    {
        return (other.isAbove(this));
    }

    /**
     * Check if this point is to the left of other point.
     * @param other the point to be compared with this point.
     * @return true if this point is to the left of other point.
     */
    public boolean isLeft (Point other)
    {
        return (_x<other._x);
    }

    /**
     * Check if this point is to the right of other point.
     * @param other the point to be compared with this point.
     * @return true if this point is to the right of other point.
     */
    public boolean isRight (Point other)
    {
        return (other.isLeft(this));
    }

    /**
     * Calculate the distance between this point and other point.
     * @param p the point to calculate the distance from.
     * @return the distance between this and p points.
     */
    public double distance (Point p)
    {
        return (Math.sqrt((_y-p._y)*(_y-p._y)+(_x-p._x)*(_x-p._x)));
    }

    /**
     * moves the x coordinate by dx and the y coordinate by dy.
     * @param dx the amount to move in the x coordinate.
     * @param dy the amount to move the y coordinate.
     */
    public void move (double dx, double dy)
    {
        setX(_x+dx);
        setY(_y+dy);
    }
}