
    import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

    public class CustomerDetails extends JFrame implements ActionListener {
        JLabel lblmeternumber,lblmonth;
        JButton search,print;
        Choice meternumber,cmonth;
        JTable table;

        CustomerDetails(){
            super("Customer Details ");




            table=new JTable();
            try{
                Conn c=new Conn();
                String query="select * from customer";
                PreparedStatement ps=c.cn.prepareStatement(query);
                ResultSet rs=ps.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));


            }catch (Exception e){
                e.printStackTrace();
            }

            JScrollPane sp = new JScrollPane(table);
//            sp.setBounds(0, 100, 700, 600);
            add(sp);



            print = new JButton("Print");
//            print.setBounds(120,70,80,20);
            print.addActionListener(this);
            add(print,"South");


            setSize(1200,650);
            setLocation(200,100);
//            setLayout(null);
            setVisible(true);
        }


        public static void main(String[] args) {
            new CustomerDetails();
        }
        @Override
        public void actionPerformed(ActionEvent e) {


                try{
                    table.print();
                }catch (Exception ae){
                    ae.printStackTrace();
                }
            }
        }



