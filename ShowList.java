
//...............................................................
//Assignment 3
//© Rutwikkumar Sunilkumar Patel
//Written By: Rutwikkumar Sunilkumar Patel, Student Id: 40160646
//...............................................................

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * The showList class has a private inner class which prevents from any privacy leak of the ShowNode class.
 * The inner class has accessor and mutators method of the node.
 * The showList class has methods like insertion, deletion, replace, find, contains and print to / from the linked list.
 * The inner class has a clone and copy constructor.
 * Equals methods has been overridden in the showList class.
 *
 * There is no privacy leak.
 */

public class ShowList {
    /**
     * ShowNode an inner class of ShowList class.
     */
     private class ShowNode implements Cloneable {
        private TVShow TVShow;
        private ShowNode nextNode;

        /**
         * Default Constructor of ShowNode class.
         */
        public ShowNode() {
            this.TVShow = null;
            this.nextNode = null;
        }

        /**
         * Parameterized Constructor of ShowNode class.
         *
         * @param TVShow   is the object of the Show class.
         * @param nextNode is the reference of the next node in the list.
         */
        public ShowNode(TVShow TVShow, ShowNode nextNode) {
            this.TVShow = TVShow;
            this.nextNode = nextNode;
        }

        /**
         * Copy Constructor of ShowNode class.
         *
         * @param showNode is the object of th ShowNode class.
         */
        public ShowNode(ShowNode showNode) {
            this(new TVShow(showNode.TVShow.getShowID(), showNode.TVShow), showNode.getNextNode());
        }

        /**
         * It sets the show object of the ShowNode class.
         *
         * @param TVShow is the object of Show class.
         */
        public void setShow(TVShow TVShow) {
            this.TVShow = TVShow;
        }

        /**
         * It sets the nextnode attribute pointer of the ShowNode class.
         *
         * @param nextNode is the pointer/reference of the next node in the list.
         */
        public void setNextNode(ShowNode nextNode) {
            this.nextNode = nextNode;
        }

        /**
         * Getter method of the Show class object.
         *
         * @return the object of the Show class.
         */
        public TVShow getShow() {
            return TVShow;
        }

        /**
         * Getter method of the nextnode pointer.
         *
         * @return the nextNode attribute of the Show class.
         */
        public ShowNode getNextNode() {
            return nextNode;
        }

        /**
         * A clone() method of the ShowNode class.
         *
         * @return the ShowNode class object.
         */
        @Override
        public Object clone() {
            return new ShowNode(this);
        }
    }

    private ShowNode head;
    private int size;

    /**
     * Default Constructor of the ShowList class.
     */
    public ShowList() {
        head = null;
        size = 0;
    }

    /**
     * Copy Constructor of the ShowList class.
     *
     * @param showList is the object of the ShowList class.
     */
    public ShowList(ShowList showList) {
        this.size = showList.size;
        this.head = showList.head;
    }

    /**
     * This method is to get the size of the list at any time in an execution of the program.
     *
     * @return the size of the list.
     */
    public int getSize() {
        return size;
    }

    /**
     * The method add the node at the start of the list.
     *
     * @param TVShow is the object of the Show class.
     */
    public void addToStart(TVShow TVShow) {
        head = new ShowNode(TVShow, head);
        size += 1;
    }

    /**
     * Inserting the node at any given index in the list.
     *
     * @param TVShow is the object of the Show class.
     * @param index  is the index at where the node is to be inserted in the list.
     */
    public void insertAtIndex(TVShow TVShow, int index) throws NoSuchElementException {
        if (index < 0 || index > size - 1) {
            throw new NoSuchElementException();
        }

        ShowNode showNode = new ShowNode();
        ShowNode currentPosition = head;
        ShowNode previousNode = head;

        if (index == 0 && head != null) {
            head = new ShowNode(TVShow, head);
            size += 1;
            return;
        }

        int indexReference = 0;
        while (currentPosition != null) {
            if (index == indexReference) {
                showNode = new ShowNode(TVShow, currentPosition);
                previousNode.setNextNode(showNode);
                size += 1;
                return;
            }
            indexReference++;
            previousNode = currentPosition;
            currentPosition = currentPosition.getNextNode();
        }
    }

    /**
     * This method delete the node from the list at any given index.
     *
     * @param index is the index from the list where the node is to be removed.
     */
    public void deleteFromIndex(int index) throws NoSuchElementException {
        if (index < 0 || index > size - 1) {
            throw new NoSuchElementException();
        }

        ShowNode currentPosition = head;
        ShowNode previousNode = head;

        if (index == 0 && head != null) {
            head = currentPosition.getNextNode();
            size -= 1;
            return;
        }

        int referenceIndex = 0;
        while (currentPosition != null) {
            if (index == referenceIndex) {
                previousNode.setNextNode(currentPosition.nextNode);
                size -= 1;
                return;
            }
            referenceIndex++;
            previousNode = currentPosition;
            currentPosition = currentPosition.getNextNode();
        }
    }

    /**
     * This method deletes the node form start/head of the list.
     */
    public void deleteFromStart() throws NoSuchElementException {
        ShowNode currentPosition = head;

        if (head != null) {
            head = currentPosition.getNextNode();
            size -= 1;
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * This method replaces a node with the given node(Passed by the user) at any given index in the list.
     *
     * @param TVShow is the object of the Show class.
     * @param index  is the index in the list of which the node is to be replaced.
     */
    public void replaceAtIndex(TVShow TVShow, int index) throws NoSuchElementException {
        if (index < 0 || index > size - 1) {
            throw new NoSuchElementException();
        }

        ShowNode currentPosition = head;
        ShowNode previousNode = head;
        ShowNode showNode = new ShowNode();

        if (index == 0 && head != null) {
            showNode = new ShowNode(TVShow, head.getNextNode());
            head = showNode;
            return;
        }

        int referenceCount = 0;
        while (currentPosition != null) {
            if (referenceCount == index) {
                showNode = new ShowNode(TVShow, currentPosition.getNextNode());
                previousNode.setNextNode(showNode);
                return;
            }
            referenceCount++;
            previousNode = currentPosition;
            currentPosition = currentPosition.getNextNode();
        }
    }


    /**
     * This method finds the given showId from the list.
     *
     * @param showId is the showId of which node is to be found from the list.
     * @return the node in which the given showId is found otherwise returns null.
     */
    private ShowNode find(String showId) {
        ShowNode currentPosition = head;

        int referenceCounter = 0;
        while (currentPosition != null) {
            referenceCounter++;
            if (currentPosition.getShow().getShowID().equals(showId)) {
                currentPosition.getShow().setCount(referenceCounter);
                //return currentPosition.getShow();
                return currentPosition;
            }
            currentPosition = currentPosition.getNextNode();
        }
        return null;
    }


    /**
     * The method finds the TvSHow with the given show id of the show.
     *
     * @param showId is the id of the show.
     * @return deep copy of TVShow reference.
     */
    public TVShow findTVShow(String showId){
        if(find(showId) == null){
            return null;
        }else {
            TVShow tvShow = Objects.requireNonNull(find(showId)).getShow();
            return new TVShow(showId, tvShow);
        }
    }

    /**
     * This method checks if the given showId is present in the list or not.
     *
     * @param showId is the id which we have find in the list.
     * @return true if showId is present in the list otherwise returns false.
     */
    public boolean contains(String showId) {
        ShowNode currentPosition = head;

        while (currentPosition != null) {
            if (currentPosition.getShow().getShowID().equals(showId)) {
                return true;
            }
            currentPosition = currentPosition.getNextNode();
        }
        return false;
    }

    /**
     * This method prints the list items.
     */
    public void printList() {
        ShowNode currentPosition = head;

        if (head == null) {
            System.out.println("No items in the list to display.");
            return;
        }

        while (currentPosition != null) {
            System.out.println(currentPosition.getShow().toString());
            currentPosition = currentPosition.getNextNode();
        }
    }

    /**
     * This method checks if both the list contains same showName, startTime and endTime of the show.
     *
     * @param showList is the object of the ShowList class.
     * @return true if all the parameters except showId are same otherwise returns false.
     */
    public boolean equals(ShowList showList) {
        ShowNode currentPosition = head;
        ShowNode differentListCurrentPosition = showList.head;

        if (showList == this) {
            System.out.println(1);
            return true;
        }

        if (getClass() != showList.getClass()) {
            System.out.println(2);
            return false;
        }

        if (this.getSize() != showList.getSize()) {
            System.out.println(3);
            return false;
        }

        while (currentPosition != null) {
            if (!(
                    currentPosition.getShow().getShowName().equals(differentListCurrentPosition.getShow().getShowName()) &&
                    currentPosition.getShow().getStartTime() == differentListCurrentPosition.getShow().getStartTime() &&
                    currentPosition.getShow().getEndTime() == differentListCurrentPosition.getShow().getEndTime()
                    )) {
                return false;
            }
            currentPosition = currentPosition.getNextNode();
            differentListCurrentPosition = differentListCurrentPosition.getNextNode();
        }
        return true;
    }
}