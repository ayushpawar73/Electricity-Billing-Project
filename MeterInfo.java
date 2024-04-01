import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Random;

public class MeterInfo extends JFrame implements ActionListener {
    JLabel heading,lblemails,lblphones,lblname,lblmeterno,lblmeter,lbladdress,lblmeternumber,lblcity,lblstate,lblemail,lblphone;
    JTextField tfname,tfaddress,tfcity,tfstate,tfemail,tfphone;
    JButton next,cancel;
    Choice meterlocation,metertype,phasecode,billtype;
    String meternumber;
    MeterInfo(String meternumber){
        this.meternumber=meternumber;
        getContentPane().setBackground(new Color(173,216,230));

        heading=new JLabel("Meter Information");
        heading.setBounds(180,10,200,20);
        heading.setFont(new Font("Tahoma",0,24));
        add(heading);

        lblname=new JLabel("Meter Number");
        lblname.setBounds(100,80,100,20);
        add(lblname);

        lblmeternumber=new JLabel(meternumber);
        lblmeternumber.setBounds(240,80,100,20);
        add(lblmeternumber);

        lblmeterno=new JLabel("Meter Location ");
        lblmeterno.setBounds(100,120,100,20);
        add(lblmeterno);

       meterlocation=new Choice();
        meterlocation.add("Outside");
        meterlocation.add("Inside");
        meterlocation.setBounds(240,120,200,20);
        add(meterlocation);





        lbladdress=new JLabel("Meter Type");
        lbladdress.setBounds(100,160,100,20);
        add(lbladdress);


        metertype=new Choice();
        metertype.add("Electric Meter");
        metertype.add("Solar Meter");
        metertype.add("Smart Meter");
        metertype.setBounds(240,160,200,20);
        add(metertype);


        lblcity=new JLabel("Phase Code");
        lblcity.setBounds(100,200,100,20);
        add(lblcity);

        phasecode=new Choice();
        phasecode.add("011");
        phasecode.add("022");
        phasecode.add("033");
        phasecode.add("044");
        phasecode.add("055");
        phasecode.add("066");
        phasecode.add("077");
        phasecode.add("088");
        phasecode.add("099");
//        phasecode.add("033");
        phasecode.setBounds(240,200,200,20);
        add(phasecode);




        lblstate=new JLabel("Bill Type");
        lblstate.setBounds(100,240,100,20);
        add(lblstate);

        billtype=new Choice();
        billtype.add("Normal");
        billtype.add("Industrial");
        billtype.setBounds(240,240,200,20);
        add(billtype);

        lblemail=new JLabel("Days");
        lblemail.setBounds(100,280,200,20);
        add(lblemail);

        lblemail=new JLabel("Note");
        lblemail.setBounds(100,320,100,20);
        add(lblemail);

        lblemails=new JLabel("30 Days");
        lblemails.setBounds(240,280,100,20);
        add(lblemails);

        lblphones=new JLabel("By default Bill is Calculated For 30 Days\n only");
        lblphones.setBounds(240,320,500,20);
        add(lblphones);



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

        new MeterInfo("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==next){
            String meter=meternumber;
            String location=meterlocation.getSelectedItem();
            String type=metertype.getSelectedItem();
            String code=phasecode.getSelectedItem();
            String typebill=billtype.getSelectedItem();
            String days="30";


            String query="insert into meter_info values('"+meter+"','"+location+"','"+type+"','"+code+"','"+typebill+"','"+days+"')";

            try{
                Conn c=new Conn();
                PreparedStatement ps,ps2;
                ps=c.cn.prepareStatement(query);
                ps.executeUpdate();



                JOptionPane.showMessageDialog(null,"Meter Information Added Successfully");
//    tfname.setText("");
                setVisible(false);

            }catch (Exception ae){
                ae.printStackTrace();
            }
        }else{
            setVisible(false);

        }
    }
}
