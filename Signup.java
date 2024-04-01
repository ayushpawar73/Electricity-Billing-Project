import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Signup extends JFrame implements ActionListener {
    JLabel heading,lblmeter,lblusername,lblname,lblpassword;
    Choice accounttype;
    JTextField meter,username,name,password;
    JButton create,back;

    Signup(){
        getContentPane().setBackground(Color.white);
        setLayout(null);

//        Panel panel=new Panel();
//        panel.setBounds(25,30,650,300);
//        Border loweredBorder = new EtchedBorder(EtchedBorder.LOWERED);
//
////        panel.setBorder(new TitledBorder(new LineBorder(new Color(173,216,230,2)),"Create - Account",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(173,216,230)));
//        add(panel);

        heading=new JLabel("Create Account As");
        heading.setBounds(100,50,140,20);
        heading.setForeground(Color.gray);
        add(heading);

        accounttype=new Choice();
        accounttype.add("Admin");
        accounttype.add("Customer");
        accounttype.setBounds(260,50,150,20);
//        accounttype.addFocusListener(this);
        add(accounttype);

        lblmeter=new JLabel("Meter Number");
        lblmeter.setBounds(100,90,140,20);
        lblmeter.setForeground(Color.gray);
        lblmeter.setVisible(false);
        add(lblmeter);

        meter =new JTextField();
        meter.setBounds(260,90,150,20);
        meter.setVisible(false);
        add(meter);

        meter.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
try{
    Conn c=new Conn();
    String query="select *from login where meter_no='"+meter.getText()+"'";
    PreparedStatement ps=c.cn.prepareStatement(query);
    ResultSet rs=ps.executeQuery();
    while (rs.next()){
        name.setText(rs.getString("name"));
    }
}catch (Exception ae){
    ae.printStackTrace();
}
            }
        });

        lblusername=new JLabel("Username ");
        lblusername.setBounds(100,130,140,20);
        lblusername.setForeground(Color.gray);
        add(lblusername);

        username =new JTextField();
        username.setBounds(260,130,150,20);
        add(username);

        lblname=new JLabel("Name ");
        lblname.setBounds(100,170,140,20);
        lblname.setForeground(Color.gray);
        add(lblname);

        name =new JTextField();
        name.setBounds(260,170,150,20);
        add(name);

        lblpassword=new JLabel("Password ");
        lblpassword.setBounds(100,210,140,20);
        lblpassword.setForeground(Color.gray);
        add(lblpassword);

        password =new JTextField();
        password.setBounds(260,210,150,20);
        add(password);

        accounttype.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user=accounttype.getSelectedItem();
                if (user.equals("Customer")){
                    lblmeter.setVisible(true);
                    meter.setVisible(true);
                    name.setEditable(false);
                }else{
                    lblmeter.setVisible(false);
                    meter.setVisible(false);
                    name.setEditable(true);
                }
            }
        });

        create=new JButton("Create");
        create.setBounds(140,270,120,25);
        create.setBackground(Color.BLACK);
        create.setForeground(Color.WHITE);
        create.addActionListener(this);
        add(create);

        back=new JButton("Back");
        back.setBounds(300,270,120,25);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i2=i1.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(430,30,250,250);
        add(image);



        setSize(700,400);
        setLocation(450,150);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Signup();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
            setVisible(false);
            new Login();
        }else{
String atype=accounttype.getSelectedItem();
String susername=username.getText();
String sname=name.getText();
String spassword=password.getText();
String smeter=meter.getText();
try {
    Conn cn1 = new Conn();
    String query = null;
    if (atype.equals("Admin")){
        query = "Insert into login values('" + smeter + "','" + susername + "','" + sname + "','" + spassword + "','" + atype + "')";
}else{
        query="Update login set username='"+susername+"',password='"+spassword+"',user='"+atype+"' where meter_no='"+smeter+"'";
    }
    PreparedStatement ps=cn1.cn.prepareStatement(query);
    int i= ps.executeUpdate();
    if (i>0){
        System.out.println("Success");
    }else{
        System.out.println("Failed");
    }
   JOptionPane.showMessageDialog(null,"Account Created Successfully");
    System.out.println("Successfully");
    setVisible(false);
    new Login();

}catch (Exception ae){
    ae.printStackTrace();
}
        }
    }
}
