public class Polygon
{
    //variabel    
    private PointNode _head; //the head of the list

    //constructor
    /**
     * Constructor a polygon.
     * time complexity-O(1); space complexity-O(1).
     */
    public Polygon ()
    {
        _head=null;
    }

    //other methods
    /**
     * Add vertex to the polygon's list at a specific location.
     * if the polygon's list is empty, the method will returns false.
     * if the number is under 1, or longer more than one from the list; method returns false.
     * if the number is longer from th elist by one, the method add the vertex in the end of the list.
     * @param p the point to be add.
     * @param pos the number of the node which the method will add the point.
     * @return true if the the point added successfully.
     * time complexity-O(n)(n=pos); space complexity-O(1).
     */
    public boolean addVertex (Point p, int pos)
    {
        if (pos<1)
        {
            return false;
        }
        if (pos == 1)
        {
            PointNode node= new PointNode (p,_head);
            _head=node;
            return true;
        }
        PointNode temp=_head;
        if (_head == null) //if there is no vertexes in the polygon
        {
            return false;
        }
        for (int i=1; i<pos-1;i++) //go to the pos place in the list
        {
            temp=temp.getNext();
            if (temp==null) //the end of the list
            {
                return false;
            }
        }
        PointNode node= new PointNode (p,temp.getNext());
        temp.setNext(node);
        return true;
    }

    /**
     * Find and return the highest vertex in the list.
     * if the list is empty, the method will return null.
     * if there is more than one point, the method will return the first to be found.
     * @return a copy of the highest point.
     * time complexity-O(n)(n=the list Length); space complexity-O(1).
     */
    public Point highestVertex()
    {
        if (_head == null) //list is empty
        {
            return null;
        }
        PointNode temp= _head;
        Point p=new Point(_head.getPoint());
        while (temp!=null)
        {
            if (p.getY()<temp.getPoint().getY()) //the new temp is higher
            {
                p=new Point(temp.getPoint());
            }
            temp=temp.getNext();
        }
        return p;
    }

    /**
     * Returns a string representation of the polygon list.
     * @return the list of the vertexes.
     * time complexity-O(n)(n=the list Length); space complexity-O(n).
     */
    public String toString ()
    {
        if (_head==null) //the list is empty
        {
            return ("The polygon has 0 vertices.");
        }
        PointNode temp = _head;
        int verCounter=0;
        String s="";
        while (temp != null)
        {
            s+=(temp.getPoint().toString());
            if (temp.getNext() != null) //there is more vertexes to come
            {
                s+=(",");
            }
            temp=temp.getNext();
            verCounter++;
        }
        return ("The polygon has " + verCounter + " vertices:\n(" + s + ")");
    }

    /**
     * Return the perimeter of the polygon.
     * if the polygon have no vertex or have one, the method return 0.
     * if the polygon have only two vertex, the method will return the section's length.
     * @return the perimeter of the polygon.
     * time complexity-O(n)(n=the list length); space complexity-O(1).
     */
    public double calcPerimeter ()
    {
        if (_head==null) //the list is empty
        {
            return 0;
        }
        if (_head.getNext()==null) //there is only one point in the list
        {
            return 0;
        }
        if (_head.getNext().getNext()== null) //there is 2 points in the list
        {
            return _head.getPoint().distance(_head.getNext().getPoint());
        }
        PointNode temp = _head;
        double per=0;
        while (temp.getNext()!=null)
        {
            per+=temp.getPoint().distance(temp.getNext().getPoint()); //add the distance
            temp=temp.getNext();    
        }
        per+=temp.getPoint().distance(_head.getPoint());
        return per;
    }

    private static double calcTriArea(Point a, Point b, Point c)
    {
        double ab=a.distance(b);
        double bc=b.distance(c);
        double ca=c.distance(a);
        double s=(ab+bc+ca)/2; //half of the perimeter
        return Math.sqrt(s*(s-ab)*(s-bc)*(s-ca)); //by heron formula
    }

    /**
     * Return the area of the polygon.
     * if the polygon have no area, the method will return 0.
     * @return the area of the polygon.
     * time complexity-O(n)(n=the list length); space complexity-O(1).
     */
    public double calcArea ()
    {
        if (_head==null || _head.getNext()==null || _head.getNext().getNext()==null) //there is less than 3 vertexes
        {
            return 0;
        }
        double area=calcTriArea(_head.getPoint(),_head.getNext().getPoint(),_head.getNext().getNext().getPoint());
        PointNode temp = _head.getNext().getNext();
        while (temp.getNext()!=null)
        {
            area+= calcTriArea(_head.getPoint(),temp.getPoint(),temp.getNext().getPoint());
            //add the area of the triangle- head,n,n+1.
            temp=temp.getNext();
        }
        return area;
    }

    /**
     * Check if the polygon is bigger than other polygon.
     * @param p the polygon to be compared with this polygon.
     * @return true if this polygon is bigger than the other polygon. 
     * time complexity-O(1); space complexity-O(1).
     */
    public boolean isBigger (Polygon p)
    {
        return (this.calcArea()>p.calcArea());
    }

    /**
     * Check which node is the point that we looking for.
     * if the point wasn't fount, the method will return -1.
     * @param p the point we want to find.
     * @return the place in the list which the point is in.
     * time complexity-O(n)(n=the list length); space complexity-O(1).
     */
    public int findVertex (Point p)
    {
        PointNode temp= _head;
        int counter=1;
        while (temp!=null)
        {
            if (temp.getPoint().equals(p))
            {
                return counter;
            }
            temp=temp.getNext();
            counter++;
        }
        return -1;
    }

    /**
     * Get a point from the list, and return the following point.
     * if the point aren't in the list, the method will return null.
     * if the point are in the end of the list, the method will return the first point in the list.
     * @param p the point which we want its following point.
     * @return a copy of the following point.
     * time complexity-O(n)(n=the list length); space complexity-O(1).
     */
    public Point getNextVertex (Point p)
    {
        PointNode temp = _head;
        while (temp!=null)
        {
            if (temp.getPoint().equals(p)) //the point founded
            {
                if (temp.getNext()==null) //the point are in the end of the list
                {
                    return new Point(_head.getPoint());
                }
                return new Point(temp.getNext().getPoint());
            }
            temp=temp.getNext();
        }
        return null;
    }

    /**
     * Return the polygon which bound this polygon parallel to the axes.
     * if there is less than 3 vertex in the original polygon, the method will return null.
     * @return the polygon who bound this polygon.
     * time complexity-O(n)(n=the list length); space complexity-O(1).
     */
    public Polygon getBoundingBox()
    {
        if (_head==null || _head.getNext()==null || _head.getNext().getNext()==null || _head.getNext().getNext().getNext()==null)
        {//there is less than 3 vertexes
        return null;
        }
        Polygon p= new Polygon();

        PointNode upRight;
        PointNode upLeft;
        PointNode downRight;
        PointNode downLeft;

        Point up=highestVertex();
        Point down;
        Point right;
        Point left;

        PointNode temp=_head;
        right=_head.getPoint(); 
        while (temp!=null) //find the rightest value in the polygon
        {
            if (temp.getPoint().isRight(right))
            {
                right=temp.getPoint();
            }
            temp=temp.getNext();
        }

        temp=_head;
        left=_head.getPoint(); 
        while (temp!=null) //find the leftest value in the polygon
        {
            if (temp.getPoint().isLeft(left))
            {
                left=temp.getPoint();
            }
            temp=temp.getNext();
        }

        temp=_head;
        down=_head.getPoint(); 
        while (temp!=null) //find the lowest value in the polygon
        {
            if (temp.getPoint().isUnder(down))
            {
                down=temp.getPoint();
            }
            temp=temp.getNext();
        }

        upRight= new PointNode(new Point(right.getX(),up.getY()),null); //defines new points and connects them.
        upLeft= new PointNode(new Point(left.getX(),up.getY()),upRight);
        downLeft= new PointNode(new Point(left.getX(),down.getY()),upLeft);
        p._head= new PointNode(new Point(right.getX(),down.getY()),downLeft);
        return p;
    }
}
