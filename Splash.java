import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable {
Thread th;
    Splash(){
        setVisible(true);
ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("icon/elect.jpg"));
Image i2=img.getImage().getScaledInstance(730,550,Image.SCALE_DEFAULT);
ImageIcon im3=new ImageIcon(i2);
JLabel imglbl=new JLabel(im3);
imglbl.setBounds(0,0,730,550);
add(imglbl);



        setLayout(null);
        for(int i=1;i<=730;i++){
            setSize(i,i);

            setLocation(350,50);
        }
        try{
            Thread.sleep(10);
        }catch (Exception e){
            e.printStackTrace();
        }
        th=new Thread(this);
        th.start();
    }


    public static void main(String[] args) {
        new Splash();
    }

    @Override
    public void run() {
        try{
            Thread.sleep(2500);
            setVisible(false);
            new Login();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
