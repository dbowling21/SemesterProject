import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.event.*;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.border.MatteBorder;


public class invgui {

    static JFrame frame;
    static Boolean connected = false;
    static String connection;
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;

    JButton button;

    public static void main(String[] args) {
        //UIManager.setLookAndFeel(PLAF);
        //	UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        //	SwingUtilities.updateComponentTreeUI(frame);
        //	updateComponentTreeUI(frame);


        invgui GUI = new invgui();
        frame.repaint();
    }

    public invgui() {
        frame = new JFrame("Inventory");
        JPanel north = new JPanel();
        JPanel east  = new JPanel();
        JPanel west  = new JPanel();
        JPanel south = new JPanel();
        JPanel center = new JPanel();
        if (connected == false){ connection = "No Connection"; }
        else { connection = "Connected"; }
        JLabel status = new JLabel("Status: " + connection);
        if (connected == false){ status.setForeground(new Color(217, 85, 80));}
        else { status.setForeground(new Color(131, 224, 158)); }
        status.setFont(new Font("Aharoni", Font.BOLD, 22));

        center.setBackground(new Color(50,50,50));
        north.setBackground(new Color(20,20,20));
        south.setBackground(new Color(50,50,50));
        east.setBackground(new Color(50,50,50));
        west.setBackground(new Color(50, 50, 50));
        frame.getContentPane().setBackground(new Color(50,50,50));

        east.setLayout(new GridBagLayout());
        center.setLayout(new GridBagLayout());
        GridBagConstraints middle = new GridBagConstraints();
        JLabel tname = new JLabel("Table Name");
        tname.setForeground(new Color(125, 211, 224));
        tname.setFont(new Font("Aharoni", Font.BOLD, 22));
        middle.weightx = 0.5;
        middle.fill = GridBagConstraints.HORIZONTAL;
        middle.ipady = 70;
        middle.gridx = 0;
        middle.gridy = 0;
        middle.insets = new Insets(10,40,0,50);  //top padding
        center.add(tname, middle);

        JTable inv = new JTable(20, 5);
        middle.fill = GridBagConstraints.HORIZONTAL;
        middle.ipady = 200;      //make this component tall
        middle.weightx = 0.0;
        middle.gridwidth = 6;
        middle.gridx = 0;
        middle.gridy = 1;
        inv.setBackground(new Color(50,50,50));
        inv.setGridColor(new Color(110,110,110));
        inv.setForeground(new Color(125, 211, 224));
        inv.setFont(new Font("Aharoni", Font.BOLD, 15));
        center.add(inv, middle);

        GridBagConstraints c = new GridBagConstraints();
        JLabel pid = new JLabel("Product ID ");
        pid.setForeground(new Color(110, 110, 110));
        pid.setFont(new Font("Aharoni", Font.BOLD,22));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        east.add(pid, c);
        JTextField productid  = new JTextField(10); //creates textfield with 10 columns
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0,0,0,20);  //top padding
        east.add(productid, c);

        JLabel sid = new JLabel("Supplier ID ");
        sid.setForeground(new Color(110, 110, 110));
        sid.setFont(new Font("Aharoni", Font.BOLD, 22));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        east.add(sid, c);
        JTextField supplierid  = new JTextField(10); //creates textfield with 10 columns
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        east.add(supplierid, c);

        JLabel qty = new JLabel("Quantity ");
        qty.setForeground(new Color(110, 110, 110));
        qty.setFont(new Font("Aharoni", Font.BOLD, 22));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        east.add(qty, c);
        JTextField quantity  = new JTextField(10); //creates textfield with 10 columns
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        east.add(quantity, c);

        JLabel wholesale = new JLabel("Wholesale ");
        wholesale.setForeground(new Color(110, 110, 110));
        wholesale.setFont(new Font("Aharoni", Font.BOLD, 22));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        east.add(wholesale, c);
        JLabel wsamount = new JLabel("temp");
        wsamount.setForeground(new Color(125, 211, 224));
        wsamount.setFont(new Font("Aharoni", Font.BOLD, 22));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        east.add(wsamount, c);

        JLabel sale = new JLabel("Set Sale Price ");
        sale.setForeground(new Color(110, 110, 110));
        sale.setFont(new Font("Aharoni", Font.BOLD, 22));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        east.add(sale, c);
        JTextField saleprice = new JTextField(10); //creates textfield with 10 columns
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 5;
        east.add(saleprice, c);

        JLabel inventory = new JLabel("Order Inventory ");
        inventory.setForeground(new Color(110, 110, 110));
        inventory.setFont(new Font("Aharoni", Font.BOLD, 22));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 6;
        east.add(inventory, c);
        JTextField orderinv = new JTextField(10); //creates textfield with 10 columns
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 6;
        east.add(orderinv, c);

        frame.setBounds(200, 400, 1000, 600);
        north.add(status);
        frame.add(north, BorderLayout.NORTH);
        frame.add(east, BorderLayout.EAST);
        frame.add(west, BorderLayout.WEST);
        frame.add(south, BorderLayout.SOUTH);
        frame.add(center, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void paintComponent(Graphics g) {


    }


}