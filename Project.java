import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;

public class Project extends JFrame  implements ActionListener {
    String atype, meter;

    Project(String atype, String meter) {
        this.atype = atype;
        this.meter = meter;
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1550, 825, 1);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);


        JMenuBar mb = new JMenuBar();


        JMenu master = new JMenu("Master");
        master.setForeground(Color.BLUE);


        JMenuItem newCustomer = new JMenuItem("New Customer");
        newCustomer.setFont(new Font("monospaced", 0, 12));
        newCustomer.setBackground(Color.WHITE);
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon1.png"));
        Image image1 = icon1.getImage().getScaledInstance(20, 20, 1);
        newCustomer.setIcon(new ImageIcon(image1));
        newCustomer.setMnemonic('D');
        newCustomer.addActionListener(this);
        newCustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        master.add(newCustomer);

        JMenuItem customerdetails = new JMenuItem("Customer Details");
        customerdetails.setFont(new Font("monospaced", 0, 12));
        customerdetails.setBackground(Color.WHITE);
        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("icon/icon2.png"));
        Image image2 = icon2.getImage().getScaledInstance(20, 20, 1);
        customerdetails.setIcon(new ImageIcon(image2));
        customerdetails.setMnemonic('D');
        customerdetails.addActionListener(this);
        customerdetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        master.add(customerdetails);

        JMenuItem depositdetails = new JMenuItem("Deposit Details");
        depositdetails.setFont(new Font("monospaced", 0, 12));
        depositdetails.setBackground(Color.WHITE);
        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("icon/icon3.png"));
        Image image3 = icon3.getImage().getScaledInstance(20, 20, 1);
        depositdetails.setIcon(new ImageIcon(image3));
        depositdetails.setMnemonic('D');
        depositdetails.addActionListener(this);
        depositdetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        master.add(depositdetails);

        JMenuItem calculatebill = new JMenuItem("Calculate Bill");
        calculatebill.setFont(new Font("monospaced", 0, 12));
        calculatebill.setBackground(Color.WHITE);
        ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource("icon/icon5.png"));
        Image image4 = icon4.getImage().getScaledInstance(20, 20, 1);
        calculatebill.setIcon(new ImageIcon(image4));
        calculatebill.setMnemonic('D');
        calculatebill.addActionListener(this);
        calculatebill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        master.add(calculatebill);


        JMenu info = new JMenu("Information");
        info.setForeground(Color.RED);


        JMenuItem updateinformation = new JMenuItem("Update Information");
        updateinformation.setFont(new Font("monospaced", 0, 12));
        updateinformation.setBackground(Color.WHITE);
        ImageIcon icon5 = new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));
        Image image5 = icon5.getImage().getScaledInstance(20, 20, 1);
        updateinformation.setIcon(new ImageIcon(image5));
        updateinformation.setMnemonic('D');
        updateinformation.addActionListener(this);
        updateinformation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        info.add(updateinformation);

        JMenuItem viewinformation = new JMenuItem("View Information");
        viewinformation.setFont(new Font("monospaced", 0, 12));
        viewinformation.setBackground(Color.WHITE);
        ImageIcon icon6 = new ImageIcon(ClassLoader.getSystemResource("icon/icon6.png"));
        Image image6 = icon6.getImage().getScaledInstance(20, 20, 1);
        viewinformation.setIcon(new ImageIcon(image6));
        viewinformation.setMnemonic('L');
        viewinformation.addActionListener(this);
        viewinformation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        info.add(viewinformation);


        JMenu user = new JMenu("User");
        user.setForeground(Color.BLUE);


        JMenuItem paybill = new JMenuItem("Pay Bill");
        paybill.setFont(new Font("monospaced", 0, 12));
        paybill.setBackground(Color.WHITE);
        ImageIcon icon7 = new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));
        Image image7 = icon7.getImage().getScaledInstance(20, 20, 1);
        paybill.setIcon(new ImageIcon(image7));
        paybill.setMnemonic('R');
        paybill.addActionListener(this);
        paybill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        user.add(paybill);

        JMenuItem billdetails = new JMenuItem("Bill Details");
        billdetails.setFont(new Font("monospaced", 0, 12));
        billdetails.setBackground(Color.WHITE);
        ImageIcon icon8 = new ImageIcon(ClassLoader.getSystemResource("icon/icon6.png"));
        Image image8 = icon6.getImage().getScaledInstance(20, 20, 1);
        billdetails.setIcon(new ImageIcon(image8));
        billdetails.setMnemonic('B');
        billdetails.addActionListener(this);
        billdetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        user.add(billdetails);

        JMenu report = new JMenu("Report");
        report.setForeground(Color.RED);


        JMenuItem generatebilll = new JMenuItem("Generate Bill");
        generatebilll.setFont(new Font("monospaced", 0, 12));
        generatebilll.setBackground(Color.WHITE);
        ImageIcon icon9 = new ImageIcon(ClassLoader.getSystemResource("icon/icon12.png"));
        Image image9 = icon9.getImage().getScaledInstance(20, 20, 1);
        generatebilll.setIcon(new ImageIcon(image9));
        generatebilll.setMnemonic('G');
        generatebilll.addActionListener(this);
        generatebilll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        report.add(generatebilll);


        JMenu utility = new JMenu("Utility");
        utility.setForeground(Color.BLUE);


        JMenuItem notepad = new JMenuItem("Notepad");
        notepad.setFont(new Font("monospaced", 0, 12));
        notepad.setBackground(Color.WHITE);
        ImageIcon icon10 = new ImageIcon(ClassLoader.getSystemResource("icon/icon12.png"));
        Image image10 = icon10.getImage().getScaledInstance(20, 20, 1);
        notepad.setIcon(new ImageIcon(image10));
        notepad.setMnemonic('N');
        notepad.addActionListener(this);
        notepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        utility.add(notepad);


        JMenuItem calculator = new JMenuItem("Calculator");
        calculator.setFont(new Font("monospaced", 0, 12));
        calculator.setBackground(Color.WHITE);
        ImageIcon icon11 = new ImageIcon(ClassLoader.getSystemResource("icon/icon9.png"));
        Image image11 = icon11.getImage().getScaledInstance(20, 20, 1);
        calculator.setIcon(new ImageIcon(image11));
        calculator.setMnemonic('C');
        calculator.addActionListener(this);
        calculator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        utility.add(calculator);



        JMenu mexit = new JMenu("Exit");
        mexit.setForeground(Color.RED);


        JMenuItem exit = new JMenuItem("Exit");
        exit.setFont(new Font("monospaced", 0, 12));
        exit.setBackground(Color.WHITE);
        ImageIcon icon12 = new ImageIcon(ClassLoader.getSystemResource("icon/icon11.png"));
        Image image12 = icon12.getImage().getScaledInstance(20, 20, 1);
        exit.setIcon(new ImageIcon(image12));
        exit.setMnemonic('W');
        exit.addActionListener(this);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));


        if (atype.equals("Admin")) {
            mb.add(master);
        } else {
            mb.add(info);
            mb.add(user);
            mb.add(report);

        }

        mb.add(utility);
        mb.add(mexit);
        mexit.add(exit);


        setJMenuBar(mb);
        setLayout(new FlowLayout());

        setVisible(true);
        add(image);
    }

    public static void main(String[] args) {
        new Project("", "");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = e.getActionCommand();
        if (msg.equals("New Customer")) {
            new NewCustomer();
        } else if (msg.equals("Customer Details")) {
            new CustomerDetails();
        } else if (msg.equals("Deposit Details")) {
            new DepositDetails();
        } else if (msg.equals("Calculate Bill")) {
            try {
                new CalculateBill();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } else if (msg.equals("View Information")) {
            new ViewInformation(meter);
        } else if (msg.equals("Update Information")) {
            new UpdateInformation(meter);
        } else if (msg.equals("Bill Details")) {
            new BillDetails(meter);
        } else if (msg.equals("Notepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            } catch (Exception aae) {
                aae.printStackTrace();
            }

        } else if (msg.equals("Calculator")) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }else if (msg.equals("Exit")){
            setVisible(false);
            new Login();
        } else if (msg.equals("Pay Bill")) {
            new PayBill(meter);
        }

    }
}

