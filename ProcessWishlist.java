
//...............................................................
//Assignment 3
//© Rutwikkumar Sunilkumar Patel
//Written By: Rutwikkumar Sunilkumar Patel, Student Id: 40160646
//...............................................................

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * The class has a main method. This class create two empty list using default and copy constructor of the ShowList class.
 * Two files namely TVGuide.txt and Interest.txt files are been read and processed. All the necessary data are stored.
 * Then using the data available all the shows that user can / can't watch are been displayed.
 * Then there is switch case which tests all the methods that have been developed in the project using required objects.
 */

public class ProcessWishlist {
    /**
     * This method gets user input for entering show details.
     *
     * @param scanner is the Scanner object to get the input from the user.
     * @return Show class object.
     */
    public static TVShow getShowDetails(Scanner scanner) {
        System.out.println("Please enter show id: ");
        String showId = scanner.nextLine();
        System.out.println("Please enter show name: ");
        String showName = scanner.nextLine();
        System.out.println("Please enter show start time: ");
        double startTime = scanner.nextDouble();
        System.out.println("Please enter show end time: ");
        double endTime = scanner.nextDouble();
        scanner.nextLine();
        return new TVShow(showId, showName, startTime, endTime);
    }

    /**
     * The method to print and get the size of the list.
     *
     * @param list    is the list of whose the size and data is to be printed.
     * @param message is the message to be shown alongside showcasing its data.
     */
    public static void printAndGetSize(ShowList list, String message) {
        System.out.println("\n" + message);
        list.printList();
        System.out.println("\n" + "The size of the list is: ");
        System.out.println(list.getSize());
    }


    /**
     * Main Method
     */
    public static void main(String[] args) {
        ShowList list1 = new ShowList();

        //Creating the list using ShowList class copy constructor.
        ShowList list2 = new ShowList(list1);

        Scanner scanner = new Scanner(System.in);

        System.out.println("-----------------Welcome to our new application about TVGuide-----------------" + "\n");

        //Opening the TVGuide.txt file and storing it is as Show class object.
        System.out.println("Please enter the TVGuide file name: ");
        String TVGuide = scanner.nextLine();
        File file = new File(TVGuide);
        try (Scanner readTVGuide = new Scanner(new FileInputStream(file.getAbsolutePath()))) {

            String TVGuideContent = "";
            boolean createObject = true;
            TVShow TVShow = null;
            while (readTVGuide.hasNextLine()) {
                if (createObject) {
                    TVShow = new TVShow();
                    createObject = false;
                }
                TVGuideContent = readTVGuide.nextLine();
                if (TVGuideContent.startsWith("S ")) {
                    TVShow.setStartTime(Double.parseDouble(TVGuideContent.substring(2)));
                } else if (TVGuideContent.startsWith("E ")) {
                    TVShow.setEndTime(Double.parseDouble(TVGuideContent.substring(2)));
                    readTVGuide.nextLine();

                    //To check if the element is already present in the list or not.
                    if (!(list1.contains(TVShow.getShowID()))) {
                        list1.addToStart(TVShow);
                    }

                    createObject = true;
                } else {
                    String[] showIdAndName = TVGuideContent.split(" ");
                    TVShow.setShowId(showIdAndName[0]);
                    TVShow.setShowName(showIdAndName[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Reading Interests.txt file and then storing its data onto an arraylist and then processing the requests.
        ArrayList<String> watchingShows = new ArrayList<>();
        ArrayList<String> wishlistShows = new ArrayList<>();

        //Reading and processing Interest.txt file.
        System.out.println("\n" + "Please enter the Interest file name: ");
        String interestFileName = scanner.nextLine();
        File interestFile = new File(interestFileName);
        try (Scanner readInterestFile = new Scanner(new FileInputStream(interestFile.getAbsolutePath()))) {
            String interestFileContent = "";
            boolean wishlist = false;
            while (readInterestFile.hasNextLine()) {
                interestFileContent = readInterestFile.nextLine();
                if (interestFileContent.equals("Watching")) {
                    wishlist = false;
                } else if (interestFileContent.equals("Wishlist")) {
                    wishlist = true;
                    //readInterestFile.nextLine();
                } else {
                    if (!wishlist) {
                        watchingShows.add(interestFileContent);
                    } else {
                        if (!interestFileContent.equals("")) {
                            wishlistShows.add(interestFileContent);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n" + "-----------------Processing the wishlist of the user-----------------" + "\n");
        for (String wishlistShowId : wishlistShows) {
            TVShow wishlistTVShow = list1.findTVShow(wishlistShowId);
            String answer = "";
            for (String watchingShowId : watchingShows) {
                TVShow watchedTVShow = list1.findTVShow(watchingShowId);
                answer = watchedTVShow.isOnSameTime(wishlistTVShow);
                if (answer.contains("can't watch")) {
                    break;
                }
            }
            System.out.println(answer);
        }

        System.out.println("\n" + "--------------Printing list 1--------------");
        printAndGetSize(list1, "Here is the list:");


        //To check if the show id's given by the user is present in the list or not.
        System.out.println("\n" + "Please enter the number of show ids you want to enter(To check if the showId is present in the list or not):");
        int numberOfEntries = scanner.nextInt();
        scanner.nextLine();
        while (numberOfEntries > 0) {
            System.out.println("\n" + "Enter the show ID name to check if is present in the list or not:");
            String showIdToCheck = scanner.nextLine();
            TVShow tvShow = list1.findTVShow(showIdToCheck);
            if (tvShow == null) {
                System.out.println("The show id mentioned is not given in the list.");
            } else {
                System.out.println("It took " + tvShow.getCount() + " iterations to find the given Show Id.");
                System.out.println("\n" + tvShow.toString());
            }
            numberOfEntries--;
        }


        System.out.println("\n" + "Let's try out some more features of the application: ");
        int n;
        printAndGetSize(list2, "Here is the list 2:");
        do {
            System.out.println("\n" + "Please enter the option you want to explore on list2: ");
            System.out.println("Case 1: Adding a new element at the start of the list.");
            System.out.println("Case 2: Adding a new element in the list at any given index.");
            System.out.println("Case 3: Deleting an element from the start of the list.");
            System.out.println("Case 4: Deleting an element from the list at any given index.");
            System.out.println("Case 5: Replacing an existing element from the list at any given index.");
            System.out.println("Case 6: To find any given element in the list.");
            System.out.println("Case 7: To check if any given element is in the list or not.");
            System.out.println("Case 8: To check if the given two lists are equal or not.");
            System.out.println("Case 9: Clone the given show.");
            System.out.println("Case 10: Clone the given list.");
            System.out.println("Case 11: To print the list 2.");
            System.out.println("Case 12: EXIT!!");
            n = scanner.nextInt();
            scanner.nextLine();

            switch (n) {
                case 1 -> {
                    TVShow TVShowDetails = getShowDetails(scanner);
                    list2.addToStart(TVShowDetails);
                    printAndGetSize(list2, "Value has been added at the start. Here is the updated list below:");
                }
                case 2 -> {
                    TVShow TVShow = getShowDetails(scanner);
                    System.out.println("Please enter the index at which you want to insert the Show Details.");
                    int indexToInsert = scanner.nextInt();
                    try {
                        list2.insertAtIndex(TVShow, indexToInsert);
                        printAndGetSize(list2, "Value has been added at the given index. Here is the updated list below:");
                    } catch (NoSuchElementException e) {
                        System.out.println("The index entered is not valid. Please enter a valid index.");
                    }
                }
                case 3 -> {
                    try {
                        list2.deleteFromStart();
                        printAndGetSize(list2, "Value has been deleted from the start. Here is the updated list below:");
                    } catch (NoSuchElementException e) {
                        System.out.println("The index entered is not valid. Please enter a valid index.");
                    }
                }
                case 4 -> {
                    System.out.println("Please enter the index at which you want to insert the show Details.");
                    int indexToDelete = scanner.nextInt();
                    try {
                        list2.deleteFromIndex(indexToDelete);
                        printAndGetSize(list2, "Value has been deleted from the given index. Here is the updated list below:");
                    } catch (NoSuchElementException e) {
                        System.out.println("The index entered is not valid. Please enter a valid index.");
                    }
                }
                case 5 -> {
                    TVShow replaceTVShow = getShowDetails(scanner);
                    System.out.println("Please enter the index at which you want to replace the existing element in the list.");
                    int indexToReplace = scanner.nextInt();
                    try {
                        list2.replaceAtIndex(replaceTVShow, indexToReplace);
                        printAndGetSize(list2, "Value has been replaced at the given index. Here is the updated list below:");
                    } catch (NoSuchElementException e) {
                        System.out.println("The index entered is not valid. Please enter a valid index.");
                    }

                }
                case 6 -> {
                    System.out.println("Please enter the show id of the show you want to find.");
                    String showIdToFind = scanner.nextLine();
                    TVShow tvShow = list2.findTVShow(showIdToFind);

                    if (tvShow == null) {
                        System.out.println("No such Show Id found in the list.");
                    } else {
                        System.out.println("It took " + tvShow.getCount() + " iterations to find the given Show Id.");
                        System.out.println("Here is the data found from the list: ");
                        System.out.println(tvShow);
                    }
                }
                case 7 -> {
                    System.out.println("Please enter the show id of the show you want to see of it is present or not.");
                    String showIdContain = scanner.nextLine();
                    if (list2.contains(showIdContain)) {
                        System.out.println("Yes the " + showIdContain + " is present in the list.");
                    } else {
                        System.out.println("No the " + showIdContain + " is not present in the list.");
                    }
                }
                case 8 -> {
                    ShowList list3 = new ShowList(list2);
                    System.out.println(list3.equals(list2));
                    TVShow TVShow = new TVShow();
                    list3.replaceAtIndex(TVShow, 0);
                    printAndGetSize(list3, "Here is the list 3");
                    printAndGetSize(list2, "Here is the list 2");

                }
                case 9 -> {
                    TVShow TVShow = new TVShow();
                    TVShow TVShow1 = new TVShow("abc", TVShow);
                    TVShow clonedTVShow = (TVShow) TVShow.clone();
                    System.out.println("\n" + "Below is the original show.");
                    System.out.println(TVShow.toString());
                    System.out.println("\n" + "Below is the clone copy of the object.");
                    System.out.println(clonedTVShow.toString());
                    clonedTVShow.setShowId("cloned");

                    System.out.println("\n"+ "I alter the show id of clonedShow but the show will not change.");
                    System.out.println("Original Show: "+ TVShow);
                    System.out.println("Cloned Show: "+ clonedTVShow);

                    System.out.println("\n"+ "Let us check for equals() method: ");
                    System.out.println(TVShow.equals(clonedTVShow));

                    System.out.println("\n" + "here is the show details created using copy constructor.");
                    System.out.println(TVShow1.toString());
                }
                case 10 -> {
                    ShowList list4 = new ShowList(list2);
                    if (list4.hashCode() != list2.hashCode()) {
                        System.out.println("\n" + "Deep Copy Done.");
                    } else {
                        System.out.println("\n" + "Deep Copy Not Done.");
                    }

                    //Add to the newly created method. Creating list 5.
                    TVShow TVShow = new TVShow();
                    TVShow TVShow1 = new TVShow("abc", new TVShow());
                    ShowList list5 = new ShowList();
                    list5.addToStart(TVShow);
                    list5.addToStart(TVShow1);

                    //Altering the list 6 to show that deep copy of list 6 is being performed.
                    ShowList list6 = new ShowList(list5);
                    list6.replaceAtIndex(new TVShow("xyz", new TVShow()), 0);

                    //Checking for equals method.
                    System.out.println("\n"+ "Let us check for equals() method for two lists: ");
                    System.out.println(list5.equals(list6));

                    printAndGetSize(list5, "here is the list 5: ");
                    printAndGetSize(list6, "here is the list 6: ");
                }
                case 11 -> {
                    System.out.println("\n" + "--------------Printing list 2--------------");
                    printAndGetSize(list2, "Here is the list:");
                }
                case 12 -> {
                    System.out.println("\n" + "Thank You!! for using our application. Visit Again!!!");
                    System.exit(0);
                }
                default -> System.out.println("\n" + "You have chosen an inappropriate option. Kindly chose from the above given options.");
            }
        } while (n >= 1);
    }
}