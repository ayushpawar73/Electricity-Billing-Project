import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class CalculateBill extends JFrame implements ActionListener {
    JLabel heading,lblname,labeladdress,lblmeternumber,lblmeterno,lblmeter,lbladdress,lblcity,lblstate,lblemail,lblphone;
    JTextField tfname,tfaddress,tfunits,tfstate,tfemail,tfphone;
    Choice meternumber,cmonth;
    JButton next,cancel;
    Conn c=new Conn();
    CalculateBill() throws SQLException, ClassNotFoundException {
        getContentPane().setBackground(new Color(173,216,230));

        heading=new JLabel("Calculate Electricity Bill ");
        heading.setBounds(180,10,400,30);
        heading.setFont(new Font("Tahoma",0,24));
        add(heading);

        lblmeternumber=new JLabel("Meter Number");
        lblmeternumber.setBounds(100,80,100,20);
        add(lblmeternumber);

        meternumber =new Choice();
        try{
            Conn c=new Conn();
           PreparedStatement  ps= c.cn.prepareStatement("select * from customer");
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
               meternumber.add( rs.getString("meter_no"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        meternumber.setBounds(240,80,200,20);
        meternumber.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try{
                    PreparedStatement ps=c.cn.prepareStatement("Select *from customer where meter_no='"+meternumber.getSelectedItem()+"'");
                    ResultSet rs=ps.executeQuery();
                    while(rs.next()){
                        lblname.setText(rs.getString("name"));
                        labeladdress.setText(rs.getString("address"));
                    }
                }catch (Exception ae){
                    ae.printStackTrace();
                }
            }
        });
        add(meternumber);

        lblmeterno=new JLabel(" Name");
        lblmeterno.setBounds(100,120,200,20);
        add(lblmeterno);

        lblname=new JLabel("");
        lblname.setBounds(240,120,200,20);
        add(lblname);


        lbladdress=new JLabel("Address");
        lbladdress.setBounds(100,160,100,20);
        add(lbladdress);

        labeladdress=new JLabel();
        labeladdress.setBounds(240,160,200,20);
        add(labeladdress);

        try{
            PreparedStatement ps=c.cn.prepareStatement("Select *from customer where meter_no='"+meternumber.getSelectedItem()+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                lblname.setText(rs.getString("name"));
                labeladdress.setText(rs.getString("address"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        lblcity=new JLabel("Units Consumed ");
        lblcity.setBounds(100,200,200,20);
        add(lblcity);

        tfunits=new JTextField();
        tfunits.setBounds(240,200,200,20);
        add(tfunits);

        lblstate=new JLabel("Month");
        lblstate.setBounds(100,240,100,20);
        add(lblstate);

        cmonth=new Choice();
        cmonth.setBounds(240,240,200,20);
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");

        add(cmonth);



        next =new JButton("Submit");
        next.setBounds(120,350,100,25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.white);
        next.addActionListener(this);
        add(next);

        cancel=new JButton("Cancel");
        cancel.setBounds(250,350,100,25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        add(cancel);


        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
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
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        new CalculateBill();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==next){

            String meter=meternumber.getSelectedItem();
            String units=tfunits.getText();
            String month=cmonth.getSelectedItem();


               int totalbill=0;
       int units_consumed=Integer.parseInt(units);
       String query="Select *from tax";

            try{

                PreparedStatement ps;
                ps=c.cn.prepareStatement(query);
                ResultSet rs=ps.executeQuery();
                while (rs.next()){
                    totalbill +=   units_consumed *Integer.parseInt( rs.getString("cost_per_unit"));
                    totalbill+=Integer.parseInt(rs.getString("meter_rent"));
                    totalbill+=Integer.parseInt(rs.getString("service_charge"));
                    totalbill+=Integer.parseInt(rs.getString("service_tax"));
                    totalbill+=Integer.parseInt(rs.getString("swacch_bharat_cess"));
                    totalbill+=Integer.parseInt(rs.getString("fixed_tax"));


                }
            }catch (Exception ae){
                ae.printStackTrace();
            }
            String query2="insert into bill values('"+meter+"','"+month+"','"+units+"','"+totalbill+"','Not Paid')";
            try{
                PreparedStatement ps=c.cn.prepareStatement(query2);
                int y=ps.executeUpdate();
                if (y>0){
                    JOptionPane.showMessageDialog(null,"Customer Bill Updated Successsfully");
                setVisible(false);
                }else
                    JOptionPane.showMessageDialog(null,"Failed To Update The Customer Bill");
            }catch (Exception be){
                be.printStackTrace();
            }

        }else{
            setVisible(false);

        }
    }
}
