import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateInformation extends JFrame implements ActionListener {

    JButton cancel,update;
    JTextField tfaddress,tfcity,tfphone,tfemail,tfstate;
    String meter;
    JLabel name,lblname;
    UpdateInformation(String meter){
        this.meter=meter;
        setVisible(true);

        JLabel heading = new JLabel("Update Customer Information");
        heading.setBounds(110,0,400,30);
        heading.setFont(new Font("tahoma",Font.ITALIC,20));
        add(heading);


         lblname = new JLabel("Name");
        lblname.setBounds(30,70,100,20);
        add(lblname);

        name = new JLabel("");
        name.setBounds(230,70,200,20);
        add(name);

        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(30,110,100,10);
        add(lblmeternumber);

        JLabel meternumber = new JLabel("");
        meternumber.setBounds(230,110,200,20);
        add(meternumber);

        JLabel lbladdresss = new JLabel("Address");
        lbladdresss.setBounds(30,150,100,20);
        add(lbladdresss);

        tfaddress = new JTextField();
        tfaddress.setBounds(230,150,200,20);
        add(tfaddress);

        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(30,190,100,20);
        add(lblcity);

        tfcity = new JTextField("");
        tfcity.setBounds(230,190,200,20);
        add(tfcity);

        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(30,230,100,20);
        add(lblstate);

        tfstate = new JTextField();
        tfstate.setBounds(230,230,200,20);
        add(tfstate);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(30,270,100,20);
        add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(230,270,200,20);
        add(tfemail);

        JLabel lblphone= new JLabel("Phone");
        lblphone.setBounds(30,310,100,20);
        add(lblphone);

        tfphone = new JTextField("");
        tfphone.setBounds(230,310,200,20);
        add(tfphone);



        update =new JButton("Update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.white);
        update.setBounds(70,360,100,25);
        update.addActionListener(this);
        add(update);

        cancel =new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.white);
        cancel.setBounds(230,360,100,25);
        cancel.addActionListener(this);
        add(cancel);

        try{
            Conn c=new Conn();
            String query="select *from customer where meter_no='"+meter+"'";
            PreparedStatement ps=c.cn.prepareStatement(query);
            ResultSet rs=ps.executeQuery();

            while (rs.next()){
                name.setText(rs.getString("name"));
                tfaddress.setText(rs.getString("address"));
                tfcity.setText(rs.getString("city"));
                tfstate.setText(rs.getString("state"));
                tfemail.setText(rs.getString("email"));
                tfphone.setText(rs.getString("phone"));
                meternumber.setText(rs.getString("meter_no"));
//
            }
        }catch (Exception e){
            e.printStackTrace();
        }





        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image i2=i1.getImage().getScaledInstance(400,300,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(550,50,400,300);
        add(image);







        setBounds(300,150,1050,450);
        getContentPane().setBackground(Color.white);
        setLayout(null);


    }
    public static void main(String[] args) {

        new UpdateInformation("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==update){
            String address=tfaddress.getText();
            String city=tfcity.getText();
            String state=tfstate.getText();
            String email=tfemail.getText();
            String phone=tfphone.getText();

            try{
                Conn c=new Conn();
                String query="update customer set address='"+address +"',city='"+city +"',state='"+ state+"',email='"+email +"',phone='"+phone +"' where meter_no='"+meter+"'";
                PreparedStatement ps=c.cn.prepareStatement(query);
                int y=ps.executeUpdate();
                if(y>0){
                    JOptionPane.showMessageDialog(null,"User Information Updated Successfully");
                }else{
                    JOptionPane.showMessageDialog(null,"Failed to update");
                }
            }catch(Exception ae){
                ae.printStackTrace();
            }



        }else {
            setVisible(false);
        }
    }
}
