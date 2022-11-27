
//...............................................................
//Assignment 3
//© Rutwikkumar Sunilkumar Patel
//Written By: Rutwikkumar Sunilkumar Patel, Student Id: 40160646
//...............................................................

/**
 * An interface to be implemented in the class that implements it. The method declared  which tells whether a user can
 * watch his/her wishlist shows given its interest.
 */
public interface Watchable {
    /**
     * The method having core logic of the project.
     * @param s is Show class instance.
     * @return returns the decision in String format.
     */
    String isOnSameTime(TVShow s);
}
