
import java.awt.*;

import java.io.*;
import java.security.spec.ECFieldF2m;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

import java.awt.event.*;
import java.io.*;

public class editor  extends JFrame implements ActionListener{
    //Text Components
    JTextArea t;
    //Frame
    JFrame f;

    //constructer
     editor(){
        f=new JFrame("editor");
        try{
            UIManager.setLookAndFeel("java.wing");
             MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch(Exception e){
                 
        }
        //text Components
        t=new JTextArea();
        //creatre menu
        JMenuBar mb=new JMenuBar();
        //create amenu for menu
        JMenu m1=new JMenu("file");

        //create menu items
        JMenuItem mi1=new JMenuItem("new");
        JMenuItem mi2=new JMenuItem("open");
        JMenuItem mi3=new JMenuItem("save");
        JMenuItem mi9=new JMenuItem("close");

        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi9.addActionListener(this);

        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi9);

        JMenu m2=new JMenu("Edit");
        //create menu items
        JMenuItem mi4=new JMenuItem("cut");
        JMenuItem mi5=new JMenuItem("copy");
        JMenuItem mi6=new JMenuItem("paste");
        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);

        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);

        JMenuItem mc=new JMenuItem("close");
        mc.addActionListener(this);
        mb.add(m1);
        mb.add(m2);
        mb.add(mc);
        f.setJMenuBar(mb);
        f.add(t);
        f.setSize(500,500);
        f.show();
    }
    //if a button is pressed
    public void actionPerformed(ActionEvent e){
        String s=e.getActionCommand();
        if(s.equals("cut")){
            t.cut();
        }
        else if(s.equals("copy")){
            t.copy();
        }
        else if(s.equals("save")){
            t.paste();
        }
        else if(s.equals("save")){
            JFileChooser j=new JFileChooser("f:");
            int r=j.showSaveDialog(null);
            if(r==JFileChooser.APPROVE_OPTION){
                //set the label to the paste
                File fi=new File(j.getSelectedFile().getAbsolutePath());
                try{
                    FileWriter wr=new FileWriter(fi,false);
                    BufferedWriter w=new BufferedWriter(wr);
                    w.write(t.getText());
                    w.flush();
                    w.close();
                }
                catch(Exception evt){
                    JOptionPane.showMessageDialog(f, evt.getMessage());

                }
            }
            //if the user cancelled the opetation
            else
            JOptionPane.showMessageDialog(f,evt.getMessage());

        }
        else if(s.equals("print")){
            try{
                t.print();
            }
            catch(Exception evt){
                JOptionPane.showMessageDialog(f, evt.getMessage());
            }
        }
        else if(s.equals("open")){
           JFileChooser j=new JFileChooser("f:");
           int r=j.showOpenDialog(null);
           if(r==JFileChooser.APPROVE_OPTION){
               File fi=new File(j.getSelectedFile().getAbsolutePath());
            try{
                //string
                String s1=" ", sl="";
                //file Reader
                FileReader fr=new FileReader(fi);
                BufferedReader br=new BufferedReader(fr);
                sl=br.readLine();
                while((s1=br.readLine())!=null){
                    sl=sl +"\n"+s1;
                } 
                t.setText(sl);
            }   
            catch(Exception evt){
                JOptionPane.showMessageDialog(f, evt.getMessage());
            }
           }
           else 
           JOptionPane.showMessageDialog(f,"the user cancelled");
        
        }
        else if(s.equals("New")){
            t.setText("");
        }
        else if(s.equals("close")){
               f.setVisible(false);
        }
    }

    public static void main(String args[]){
        editor e=new editor();
    }
   
}