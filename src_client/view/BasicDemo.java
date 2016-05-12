package view;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.DayOfWeek;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.github.lgooddatepicker.datepicker.DatePicker;
import com.github.lgooddatepicker.datepicker.DatePickerSettings;
import com.github.lgooddatepicker.zinternaltools.InternalUtilities;
public class BasicDemo extends JFrame {

    /**
     * main, This is the entry point for the basic demo.
     */
    public static void main(String[] args) {
        // Use the standard swing code to start this demo inside a swing thread.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create an instance of the demo.
                BasicDemo basicDemo = new BasicDemo();
                // Make the demo visible on the screen.
                basicDemo.setVisible(true);
            }
        });
    }

    /**
     * Default Constructor.
     */
    public BasicDemo() {
        initializeComponents();
    }

    /**
     * initializeComponents, This creates the user interface for the basic demo.
     */
    private void initializeComponents() {
        // Set up the form which holds the date picker components. 
        setTitle("LGoodDatePicker Basic Demo " + InternalUtilities.getProjectVersionString());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(new Dimension(640, 480));
        setLocationRelativeTo(null);

        /**
         * The code below shows: 1) How to create a DateTimePicker (with default settings), 2) How
         * to create a DatePicker with some custom settings, and 3) How to create a TimePicker with
         * some custom settings. To keep the Basic Demo interface simpler, the lines for adding
         * these components to the form have been commented out.
         */
        // Create a DateTimePicker. (But don't add it to the form).
        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setFirstDayOfWeek(DayOfWeek.MONDAY);
        DatePicker datePicker = new DatePicker(dateSettings);
        // To display this picker, uncomment this line.
        add(datePicker);

    }
}