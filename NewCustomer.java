import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Random;

public class NewCustomer extends JFrame implements ActionListener {
    JLabel heading,lblname,lblmeterno,lblmeter,lbladdress,lblcity,lblstate,lblemail,lblphone;
    JTextField tfname,tfaddress,tfcity,tfstate,tfemail,tfphone;
    JButton next,cancel;
    NewCustomer(){
        getContentPane().setBackground(new Color(173,216,230));

        heading=new JLabel("New Customer");
        heading.setBounds(180,10,200,20);
        heading.setFont(new Font("Tahoma",0,24));
        add(heading);

        lblname=new JLabel("Customer Name");
        lblname.setBounds(100,80,200,20);
        add(lblname);

        tfname=new JTextField();
        tfname.setBounds(240,80,200,20);
        add(tfname);

        lblmeterno=new JLabel("Meter Number");
        lblmeterno.setBounds(100,120,200,20);
        add(lblmeterno);

        lblmeter=new JLabel("");
        lblmeter.setBounds(240,120,200,20);
        add(lblmeter);
        Random ran=new Random();
        long number=ran.nextLong()%1000000;
        lblmeter.setText("" +Math.abs(number));



        lbladdress=new JLabel("Address");
        lbladdress.setBounds(100,160,100,20);
        add(lbladdress);

        tfaddress=new JTextField();
        tfaddress.setBounds(240,160,200,20);
        add(tfaddress);

        lblcity=new JLabel("City");
        lblcity.setBounds(100,200,200,20);
        add(lblcity);

        tfcity=new JTextField();
        tfcity.setBounds(240,200,200,20);
        add(tfcity);

        lblstate=new JLabel("State");
        lblstate.setBounds(100,240,200,20);
        add(lblstate);

        tfstate=new JTextField();
        tfstate.setBounds(240,240,200,20);
        add(tfstate);

        lblemail=new JLabel("Email");
        lblemail.setBounds(100,280,200,20);
        add(lblemail);

        tfemail=new JTextField();
        tfemail.setBounds(240,280,200,20);
        add(tfemail);

        lblphone=new JLabel("Phone");
        lblphone.setBounds(100,320,200,20);
        add(lblphone);

        tfphone=new JTextField();
        tfphone.setBounds(240,320,200,20);
        add(tfphone);

        next =new JButton("Next");
        next.setBounds(120,390,100,25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.white);
        next.addActionListener(this);
        add(next);

        cancel=new JButton("Cancel");
        cancel.setBounds(250,390,100,25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        add(cancel);


        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2=i1.getImage().getScaledInstance(150,300,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(400,25,300,400);
        add(image);



setLayout(null);
        setSize(700,500);
        setLocation(400,150);
        setVisible(true);
    }
    public static void main(String[] args) {
        new NewCustomer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==next){
            String name=tfname.getText();
            String meter=lblmeter.getText();
            String address=tfaddress.getText();
            String city=tfcity.getText();
            String state=tfstate.getText();
            String email=tfemail.getText();
            String phone=tfphone.getText();

            String query1="insert into customer values('"+name+"','"+meter+"','"+address+"','"+city+"','"+state+"','"+email+"','"+phone+"')";
            String query2="insert into login values('"+meter+"','','"+name+"','','')";

try{
    Conn c=new Conn();
    PreparedStatement ps,ps2;
    ps=c.cn.prepareStatement(query1);
    ps.executeUpdate();

    ps2=c.cn.prepareStatement(query2);
    ps2.executeUpdate();
    JOptionPane.showMessageDialog(null,"Successful");
//    tfname.setText("");
setVisible(false);
new MeterInfo(meter);
}catch (Exception ae){
    ae.printStackTrace();
}
        }else{
            setVisible(false);

        }
    }
}
