import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewInformation extends JFrame implements ActionListener {
    String meter;
    JButton cancel;
    ViewInformation(String meter) {
        this.meter=meter;
        setLayout(null);
        JLabel heading = new JLabel("View Customer Information");
        heading.setBounds(250,0,500,40);
        heading.setFont(new Font("tahoma",Font.ITALIC,20));
        add(heading);


        JLabel lblname = new JLabel("Name");
        lblname.setBounds(100,80,100,20);
        add(lblname);

        JLabel name = new JLabel("");
        name.setBounds(250,80,100,20);
        add(name);

        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(100,140,100,20);
        add(lblmeternumber);

        JLabel meternumber = new JLabel("");
        meternumber.setBounds(250,140,100,20);
        add(meternumber);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(100,200,100,20);
        add(lbladdress);

        JLabel address = new JLabel("");
        address.setBounds(250,200,100,20);
        add(address);

        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(100,260,100,20);
        add(lblcity);

        JLabel city = new JLabel("");
        city.setBounds(250,260,100,20);
        add(city);

        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(500,80,100,20);
        add(lblstate);

        JLabel state = new JLabel("");
        state.setBounds(650,80,100,20);
        add(state);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(500,140,100,20);
        add(lblemail);

        JLabel email = new JLabel("");
        email.setBounds(650,140,100,20);
        add(email);

        JLabel lblphone= new JLabel("Phone");
        lblphone.setBounds(500,200,100,20);
        add(lblphone);

        JLabel phone = new JLabel("");
        phone.setBounds(650,200,100,20);
        add(phone);

        try{
            Conn c=new Conn();
            String query="select *from customer where meter_no='"+meter+"'";
            PreparedStatement ps=c.cn.prepareStatement(query);
            ResultSet rs=ps.executeQuery();

            while (rs.next()){
                name.setText(rs.getString("name"));
                address.setText(rs.getString("address"));
                city.setText(rs.getString("city"));
                state.setText(rs.getString("state"));
                email.setText(rs.getString("email"));
                phone.setText(rs.getString("phone"));
                meternumber.setText(rs.getString("meter_no"));

            }
        }catch (Exception e){
            e.printStackTrace();
        }





        cancel =new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.white);
        cancel.setBounds(350,350,100,25);
        cancel.addActionListener(this);
        add(cancel);
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image i2=i1.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(20,350,600,300);
        add(image);


        setBounds(300, 80, 850, 650);
        getContentPane().setBackground(Color.white);
        setVisible(true);

    }


    public static void main(String[] args) {
        new ViewInformation("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
setVisible(false);
    }
}
