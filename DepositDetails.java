import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DepositDetails extends JFrame implements ActionListener {
    JLabel lblmeternumber, lblmonth;
    JButton search, print;
    Choice meternumber, cmonth;
    JTable table;

    DepositDetails() {
        super("Deposit Details ");

        getContentPane().setBackground(Color.white);
        lblmeternumber = new JLabel("Search By Meter Number");
        lblmeternumber.setBounds(20, 20, 150, 20);
        add(lblmeternumber);

        meternumber = new Choice();
        meternumber.setBounds(180, 20, 150, 20);
        add(meternumber);
        try {
            Conn c = new Conn();
            String query = "select *from customer";

            PreparedStatement ps = c.cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                meternumber.add(rs.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        lblmonth = new JLabel("Search By Month");
        lblmonth.setBounds(400, 20, 100, 20);
        add(lblmonth);

        cmonth = new Choice();
        cmonth.setBounds(520, 20, 150, 20);
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


        table = new JTable();
        try {
            Conn c = new Conn();
            String query = "select * from bill";
            PreparedStatement ps = c.cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));


        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 100, 700, 600);
        add(sp);

        search = new JButton("Search");
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);


        setSize(700, 700);
        setLocation(400, 100);
        setLayout(null);
        setVisible(true);
    }


    public static void main(String[] args) {
        new DepositDetails();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            String query = "select * from bill where meter_no='" + meternumber.getSelectedItem() + "'and month='" + cmonth.getSelectedItem() + "'";
            try {
                Conn c = new Conn();
                PreparedStatement ps = c.cn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception es) {
                es.printStackTrace();
            }

        } else {
            try {
                table.print();
            } catch (Exception ae) {
                ae.printStackTrace();
            }
        }
    }
}
