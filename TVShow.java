

//...............................................................
//Assignment 3
//© Rutwikkumar Sunilkumar Patel
//Written By: Rutwikkumar Sunilkumar Patel, Student Id: 40160646
//...............................................................

import java.util.Scanner;

/**
 * The class TVShow has all the important attributes of the shows like show id, name, start time and end time.
 * It has accessor and mutator methods. Along with clone and equals method overridden from Object class.
 * The class also implements Watchable interface which has core logic of the code, which has been implemented.
 */

public class TVShow implements Watchable, Cloneable {
    private String showID;
    private String showName;
    private double startTime;
    private double endTime;
    private int count = 0;

    /**
     * Default constructor of Show class.
     */
    public TVShow() {
        this.showID = "Dummy_Id";
        this.showName = "Dummy_Name";
        this.startTime = 12.00;
        this.endTime = 13.00;
    }

    /**
     * Parameterized Constructor of Show class.
     *
     * @param showID    is the id of the show.
     * @param showName  is the name of the show.
     * @param startTime is the start time of the show.
     * @param endTime   is the end time of the show.
     */
    public TVShow(String showID, String showName, double startTime, double endTime) {
        this.showID = showID;
        this.showName = showName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Copy Constructor of the Show class.
     *
     * @param showID is the id of the show.
     * @param TVShow   is an instace of the Show class.
     */
    public TVShow(String showID, TVShow TVShow) {
        this.showID = showID;
        this.showName = TVShow.showName;
        this.startTime = TVShow.startTime;
        this.endTime = TVShow.endTime;
        this.count = TVShow.count;
    }

    /**
     * This method sets the show id.
     *
     * @param showID is the id for the show.
     */
    public void setShowId(String showID) {
        this.showID = showID;
    }

    /**
     * This method sets the name of the show.
     *
     * @param showName is the name of the show.
     */
    public void setShowName(String showName) {
        this.showName = showName;
    }

    /**
     * This method sets the start time of the show.
     *
     * @param startTime is the start time of the show.
     */
    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    /**
     * This method sets the end time of the show.
     *
     * @param endTime is the end time of the show.
     */
    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    /**
     * This method gets the id of the show.
     *
     * @return id of the show.
     */
    public String getShowID() {
        return this.showID;
    }

    /**
     * This method gets the name of the show.
     *
     * @return show of the name.
     */
    public String getShowName() {
        return this.showName;
    }

    /**
     * This method gets the start time of the show.
     *
     * @return start time of the show.
     */
    public double getStartTime() {
        return this.startTime;
    }

    /**
     * This method gets the end time of the show.
     *
     * @return end time of the show.
     */
    public double getEndTime() {
        return this.endTime;
    }

    /**
     * The method sets the count variable used while finding any element from the list.
     * @param count is the number of iterations performed to find the number in the list.
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * The method returns the count variable which stores the number of iterations performed to find a given number in the list.
     * @return the number of iterations count.
     */
    public int getCount() {
        return this.count;
    }

    /**
     * The clone method which performs deep copy of the calling object.
     * @return the Object which is cloned.
     */
    @Override
    public Object clone() {
        TVShow TVShow;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the show id to check the output of clone() method.");
        String showId = scanner.nextLine();
        try {
            TVShow = (TVShow) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
        TVShow.setShowId(showId);
        return TVShow;
    }

    /**
     * This is the toString() method of the class.
     *
     * @return attributes of calling instance of the class.
     */
    @Override
    public String toString() {
        return (this.showID + " " + this.showName + " " + this.startTime + " " + this.endTime);
    }

    /**
     * Equals method check if the two objects are equal or not. Not only by comparing the object but also the attributes are compared.
     *
     * @param otherTVShowObject is the other object of Show class.
     * @return true if the values and instances are same otherwise false.
     */
    @Override
    public boolean equals(Object otherTVShowObject) {
        if (this == otherTVShowObject) {
            return true;
        }

        if (getClass() != otherTVShowObject.getClass()) {
            return false;
        }

        TVShow TVShow = (TVShow) otherTVShowObject;
        return this.showName.equals(TVShow.showName) && this.startTime == TVShow.startTime && this.endTime == TVShow.endTime;
    }

    /**
     * The interface with core logic has been implemented.
     * @param s is the Show class instance,
     * @return the string format consisting of the decision.
     */
    @Override
    public String isOnSameTime(TVShow s) {
        if (this.getStartTime() < s.getStartTime() && this.getEndTime() >= s.getEndTime()) {
            return "User can't watch show " + s.getShowID() + " as he/she is not finished with a show he/she is watching.";
        } else if (this.getStartTime() == s.getStartTime()) {
            return "User can't watch show " + s.getShowID() + " as he/she will begin another show at the same time.";
        } else {
            return "User can watch show " + s.getShowID() + " as he/she is not watching anything else during that time.";
        }
    }
}