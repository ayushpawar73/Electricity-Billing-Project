import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JLabel lblusername, lblpassword, loggininas;
    JTextField username;
    JPasswordField password;
    Choice logginin;
    JButton login, cancel, signup;

    Login() {
        super("Login Page");
        setLayout(null);
        getContentPane().setBackground(Color.white);

        lblusername = new JLabel("Username");
        lblusername.setBounds(300, 30, 100, 15);
        add(lblusername);

        username = new JTextField();
        username.setBounds(400, 27, 150, 20);
        add(username);

        lblpassword = new JLabel("Password");
        lblpassword.setBounds(300, 70, 100, 15);
        add(lblpassword);

        password = new JPasswordField();
        password.setBounds(400, 67, 150, 20);
        add(password);

        loggininas = new JLabel("Loggin in as");

        loggininas.setBounds(300, 110, 100, 15);
        add(loggininas);

        logginin = new Choice();
        logginin.add("Admin");
        logginin.add("Customer");
        logginin.setBounds(400, 107, 150, 20);
        add(logginin);


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i2 = i1.getImage().getScaledInstance(16, 16, 1);
        login = new JButton("Login", new ImageIcon(i2));
        login.setBounds(330, 160, 100, 20);
        login.addActionListener(this);
        add(login);

        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image i4 = i3.getImage().getScaledInstance(16, 16, 1);
        cancel = new JButton("Cancel", new ImageIcon(i4));
        cancel.setBounds(450, 160, 100, 20);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i6 = i5.getImage().getScaledInstance(16, 16, 1);
        signup = new JButton("Signup", new ImageIcon(i6));
        signup.setBounds(380, 200, 100, 20);
        signup.addActionListener(this);
        add(signup);


        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i8 = i7.getImage().getScaledInstance(250, 250, 1);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel image = new JLabel(i9);
        image.setBounds(0, 0, 250, 250);
        add(image);


        setSize(640, 300);
        setLocation(400, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
            setVisible(false);
        } else if (e.getSource() == signup) {
            setVisible(false);
            new Signup();
        } else {
            String susername = username.getText();
            String spassword = password.getText();
            String user = logginin.getSelectedItem();

            try {
                Conn c = new Conn();
                String query = "Select *from login where username='" + susername + "' and password='" + spassword + "'and user='" + user + "'";
                PreparedStatement ps = c.cn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String meter = rs.getString("meter_no");
                    setVisible(false);
                    new Project(user, meter);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                    username.setText("");
                    password.setText("");
                }


            } catch (Exception ae) {
                ae.printStackTrace();

            }


        }
    }
}
