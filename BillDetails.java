import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BillDetails extends JFrame  {
    String meter;
    BillDetails(String meter){
        this.meter=meter;
        setLayout(null);

        getContentPane().setBackground(Color.white);
        JTable table=new JTable();
        try{
            Conn c=new Conn();
            String query="select *from bill where meter_no='"+meter+"'";
            PreparedStatement ps=c.cn.prepareStatement(query);
            ResultSet rs=ps.executeQuery();

            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            e.printStackTrace();
        }
        JScrollPane sp=new JScrollPane(table);
        sp.setBounds(0,0,700,650);
        add(sp);



        setSize(700,650);
        setLocation(400,70);
        setVisible(true);
    }

    public static void main(String[] args) {
        new BillDetails("");
    }
}
