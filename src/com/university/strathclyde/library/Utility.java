package com.university.strathclyde.library;

import javax.swing.JOptionPane;
import java.awt.Component;

/***
 * This Utility class used to print message on calling by any class and method.
 * @author Vijayshree Joshi
 */
public class Utility {

    /***
     * Static method to print message given by any method.
     * @param messageToPrint add new message to print.
     */
    public static void printMessage(String messageToPrint) {
        System.out.println(messageToPrint);
    }

    /***
     * Static method to show message to user while doing UI operation.
     * @param callingComponent from UI.
     * @param message to be displayed to user.
     */
    public static void showMessageToUser(Component callingComponent, String message) {
        JOptionPane.showMessageDialog(callingComponent, message);
    }

}
