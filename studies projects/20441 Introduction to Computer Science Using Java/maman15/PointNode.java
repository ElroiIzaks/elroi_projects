public class PointNode
{
    //variebels
    private Point _point;
    private PointNode _next; //the next node in the list

    //constructors
    /**
     *Construct a point node.
     * construct _point with the same coordinate as other point.
     * construct _next as null.
     * @param p the point from which to construct the new point.
     */
    public PointNode (Point p)
    {
        _point=new Point (p);
        _next=null;
    }

    /**
     * Copy constructor for point and a point node (from different sources).
     * @param p the point to be copy.
     * @param n the point node to be copy as the next node.
     */
    public PointNode (Point p, PointNode n)
    {
        _point=new Point (p);
        _next=n;
    }

    /**
     * Copy constructor for point node.
     * construct a point node with the same values as other point node.
     * @param p the point node to be copy.
     */
    public PointNode (PointNode p)
    {
        _point=p._point;
        _next=p._next;
    }

    //getters
    /**
     * Returns a copy of the point.
     * @return a copy of the point.
     */
    public Point getPoint ()
    {
        return new Point (_point);
    }

    /**
     * Return the next node in the list.
     * @return the next node in the list (not a copy).
     */
    public PointNode getNext()
    {
        return _next;
    }

    //setters
    /**
     * sets the point value of this point node.
     * @param p the new point value.
     */
    public void setPoint (Point p)
    {
        _point=new Point (p);
    }

    /**
     * sets the next node of this point node.
     * @param next the new point node value.
     */
    public void setNext (PointNode next)
    {
        _next=next;
    }
}
