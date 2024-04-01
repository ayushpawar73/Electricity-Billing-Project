import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PayBill extends JFrame {

    Choice cmonth;
    String meter;
    PayBill(String meter){
        this.meter=meter;
        setLayout(null);


        JLabel heading = new JLabel("Electricity Bill");
        heading.setFont(new Font("tanoma", Font.BOLD, 24));
        heading.setBounds(120, 5, 400, 30);
        add(heading);

        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(35, 80, 200, 20);
        add(lblmeternumber);

        JLabel meternumber = new JLabel("");
       meternumber.setBounds(300, 80, 200, 20);
        add(meternumber);

        JLabel lblname = new JLabel("Name ");
        lblname.setBounds(35, 140, 200, 20);
        add(lblname);

        JLabel labelname = new JLabel("");
        labelname.setBounds(300, 140, 200, 20);
        add(labelname);

        JLabel lblmonth = new JLabel("Month ");
        lblmonth.setBounds(35, 200, 200, 20);
        add(lblmonth);

        cmonth = new Choice();
        cmonth.setBounds(300, 200, 200, 20);
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


        JLabel lblunits = new JLabel("Units ");
        lblunits.setBounds(35, 260, 200, 20);
        add(lblunits);

        JLabel labelunits = new JLabel("");
        labelunits.setBounds(300, 260, 200, 20);
        add(labelunits);

        JLabel lbltotalbill= new JLabel("Total Bill ");
        lbltotalbill.setBounds(35, 320, 200, 20);
        add(lbltotalbill);

        JLabel labeltotalbill = new JLabel("");
        labeltotalbill.setBounds(300, 320, 200, 20);
        add(labeltotalbill);

        JLabel lblstatus= new JLabel("Status");
        lblstatus.setBounds(35, 380, 200, 20);
        add(lblstatus);

        JLabel labelstatus = new JLabel("");
        labelstatus.setForeground(Color.red);
        labelstatus.setBounds(300, 380, 200, 20);
        add(labelstatus);


        try{
            Conn c=new Conn();
            String query="select * from customer where meter_no='"+meter+"'";
            PreparedStatement ps=c.cn.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
while(rs.next()){
    meternumber.setText(meter);
    labelname.setText(rs.getString("name"));
}
             query="select * from bill where meter_no='"+meter+"'and month='"+cmonth.getSelectedItem()+"'";
             ps=c.cn.prepareStatement(query);
             rs=ps.executeQuery();
            while(rs.next()){

                labelunits.setText(rs.getString("units"));
                labeltotalbill.setText(rs.getString("totalbill"));
                labelstatus.setText(rs.getString("status"));
            }


        }catch (Exception e){
            e.printStackTrace();
        }
cmonth.addItemListener(new ItemListener() {
    @Override
    public void itemStateChanged(ItemEvent e) {
        try{
            Conn c=new Conn();
           String  query="select * from bill where meter_no='"+meter+"'and month='"+cmonth.getSelectedItem()+"'";
           PreparedStatement ps=c.cn.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){

                labelunits.setText(rs.getString("units"));
                labeltotalbill.setText(rs.getString("totalbill"));
                labelstatus.setText(rs.getString("status"));
            }
        }catch (Exception ae){
            ae.printStackTrace();
        }
    }
});


        setVisible(true);
        setBounds(300,70,900,600);

    }

    public static void main(String[] args) {
        new PayBill("");
    }
}
