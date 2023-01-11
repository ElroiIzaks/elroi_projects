package q1;

import java.util.ArrayList;
import java.util.Iterator;

public class Set <E>{
	private ArrayList<E> arrList;

	//constructors
	public Set () {
		arrList = new ArrayList<E>();
	}
	
	public Set (E[] arr) {
		for (int i = 0; i< arr.length; i++) this.insert(arr [i]);
	}

	/**
	 * 
	 * union 2 sets (and place the union's set as the "this" set)
	 * @param otherSet the other set 
	 */
	public void union (Set<E> otherSet) {
		for (int i = 0; i < otherSet.arrList.size(); i++) this.insert(otherSet.arrList.get(i));
	}
	
	/**
	 * intersect 2 sets (and place the intersect's set as the "this" set)
	 * @param otherSet the other set
	 */
	public void intersect (Set<E> otherSet) {
		for (int i = 0; i < otherSet.arrList.size(); i++) this.delete(otherSet.arrList.get(i));
	}
	
	/**
	 * check if the other set is subset of the "this" set
	 * @param otherSet the other set
	 * @return true if the other set is subset of the "this" set
	 */
	public boolean isSubset (Set<E> otherSet) {
		for (int i = 0; i<otherSet.arrList.size();i++)
			if (!this.isMember(otherSet.arrList.get(i))) return false;
		return true;
	}
	
	/**
	 * check if the object is member of the "this" set
	 * @param objectToCheck
	 * @return true if the object is member of the "this" set
	 */
	public boolean isMember (E objectToCheck) {
		if (arrList.contains(objectToCheck)) return true;
		return false;
	}
	
	
	/**
	 * insert object to the "this" set
	 * @param objectToInsert
	 */
	public void insert (E objectToInsert) {
		if (!this.isMember(objectToInsert)) arrList.add(objectToInsert);
	}
	
	 /**
	  * delete object from the "this" set
	  * @param objectToDelete
	  */
	public void delete (E objectToDelete) {
		arrList.remove(objectToDelete);
	}
	
	/**
	 * return the iterator of the set
	 * @return the iterator
	 */
	public Iterator<E> iterator (){
		return this.arrList.iterator();
	}
	
	//to string method
	public String toString () {
		return ("size :" + arrList.size() + "\n" + arrList.toString());
	}
}
