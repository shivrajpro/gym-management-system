/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gms;

import java.util.Random;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import static gms.Connect.con;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author dell
 */
public class AddMember extends javax.swing.JFrame {

    /**
     * Creates new form AddMember
     */
public String s;
    public AddMember() {
        initComponents();
        print.setEnabled(false);
        addS();
        addF();
        addTrainer();
        Random();
        s=null;
        getContentPane().setBackground(Color.GRAY);
        dateFunc();
        Connection con=Connect.toMysql();
    }
    
void dateFunc()
{
DateFormat dF=new SimpleDateFormat("yyyy-MM-dd");
java.util.Date date=new java.util.Date();
((JTextField)fdate.getDateEditor().getUiComponent()).setText(dF.format(date));
fdate.setMinSelectableDate(date);
ddate.setMinSelectableDate(date);
stdate.setMinSelectableDate(date);
edate.setMinSelectableDate(date);
}


public void Random()
{
    Random r=new Random();
    regid.setText(""+r.nextInt(100+1));
}
    public ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

public void addS()
{
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded");
            
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/GMS","root", "shiv");
            System.out.println("Connection Esatablished");
            
            
            PreparedStatement ps=con.prepareStatement("select * from Subs");
           
            ResultSet rs=ps.executeQuery();
             while(rs.next())
                subs.addItem(rs.getString(1));
            
            
          
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } catch(SQLException e)
        {
             JOptionPane.showMessageDialog(null,e);
        }
}
public void addF()
{
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded");
            
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/GMS","root", "shiv");
            System.out.println("Connection Esatablished");
            
            
            PreparedStatement ps=con.prepareStatement("select * from Fac");
           
            ResultSet rs=ps.executeQuery();
             while(rs.next())
               fac.addItem(rs.getString(1));
            
            
          
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } catch(SQLException e)
        {
             JOptionPane.showMessageDialog(null,e);
        }
}
 public void addTrainer()
{
     try
        {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded");
            
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/GMS","root", "shiv");
            System.out.println("Connection Esatablished");
            
            
            PreparedStatement ps=con.prepareStatement("select * from trainer");
           
            ResultSet rs=ps.executeQuery();
          
            while(rs.next())
                trainer.addItem(rs.getString(2));
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } catch(SQLException e)
        {
             JOptionPane.showMessageDialog(null,e);
        }
}   
 public void pdf()
{
    Document doc=new Document();
    
    String sdate,eedate,ffdate,duedate;
    sdate= ((JTextField)stdate.getDateEditor().getUiComponent()).getText();
    eedate= ((JTextField)edate.getDateEditor().getUiComponent()).getText();
    ffdate= ((JTextField)fdate.getDateEditor().getUiComponent()).getText();
    duedate= ((JTextField)ddate.getDateEditor().getUiComponent()).getText();
    
    
    try
    {
         PdfWriter.getInstance(doc, new FileOutputStream("Bill.pdf"));
         doc.open();
         doc.add(new Paragraph("                                                   POWERHOUSE FITNESS CLUB"));
         doc.add(new Paragraph("                                                Keshavnagar,Mundhwa,Pune-411036"));
         doc.add(new Paragraph("Gym_Info"));
         doc.add(new Paragraph("Reg ID: "+regid.getText()));
         doc.add(new Paragraph("Name: "+fname.getText()+"   "+lname.getText()));
         doc.add(new Paragraph("Contact: "+ph_no.getText()));
         doc.add(new Paragraph("Subscription: "+subs.getSelectedItem()+"                Facility: "+fac.getSelectedItem()));
         doc.add(new Paragraph("Start Date: "+sdate+"               End Date: "+eedate));
         
         doc.add(new Paragraph("    "));
         doc.add(new Paragraph("    "));
         
         doc.add(new Paragraph("Fees_Info"));
         doc.add(new Paragraph("Fee Date: "+ffdate));
         doc.add(new Paragraph("Due Date: "+duedate));
         doc.add(new Paragraph("Amount: "+amt.getText()));
         doc.add(new Paragraph("Amount Status: "+amt_st.getSelectedItem()));
         doc.add(new Paragraph("Balance Amount: "+bal.getText()));
         
         doc.add(new Paragraph("    "));
         doc.add(new Paragraph("    "));
         doc.add(new Paragraph("    "));
         doc.add(new Paragraph("Member Sign                                                                     Admin Sign"));

                  
         doc.close();
         JOptionPane.showMessageDialog(null, "Receipt Created");
    }catch(Exception e)
    {
        JOptionPane.showMessageDialog(null, e);
    }
 
}
 
 public void printpdf()
    {
            pdf(); 
            int p=JOptionPane.showConfirmDialog(null, "Do you wish to print receipt?","Receipt",JOptionPane.YES_NO_OPTION);
            if(p==0)
             {
                 try
                {
                    Desktop.getDesktop().open(new File("C:\\Users\\Dell\\Documents\\NetBeansProjects\\GMS\\Bill.pdf"));
                }catch(Exception e)
                    {
                              JOptionPane.showMessageDialog(null,e);
                    }
             }
  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lname = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        gender = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        age = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ph_no = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        addr = new javax.swing.JTextArea();
        label = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        ht = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        trainer = new javax.swing.JComboBox<>();
        subs = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        regid = new javax.swing.JTextField();
        wt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        fac = new javax.swing.JComboBox<>();
        stdate = new com.toedter.calendar.JDateChooser();
        edate = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        print = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        bal = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        amt = new javax.swing.JTextField();
        amt_st = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        fdate = new com.toedter.calendar.JDateChooser();
        ddate = new com.toedter.calendar.JDateChooser();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ADD MEMBER");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(53, 82, 70), 2, true), "Personal Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Serif Condensed", 1, 24))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel2.setText("First Name");

        jLabel9.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel9.setText("Age");

        gender.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        jLabel5.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel5.setText("Email");

        jLabel10.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel10.setText("Gender");

        jLabel6.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel6.setText("Address");

        jLabel8.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel8.setText("Contact");

        jLabel7.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel7.setText("Last Name");

        ph_no.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ph_noFocusLost(evt);
            }
        });

        addr.setColumns(20);
        addr.setRows(5);
        jScrollPane2.setViewportView(addr);

        label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gms/user.png"))); // NOI18N

        jButton3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gms/image (2).png"))); // NOI18N
        jButton3.setText("Select");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(ph_no, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(age, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lname, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(age, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ph_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {age, email, fname, jLabel7, lname, ph_no});

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Gym Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Serif Condensed", 1, 24))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(917, 394));

        jLabel17.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel17.setText("End Date");

        jLabel19.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel19.setText("Facility");

        jLabel16.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel16.setText("Start Date");

        trainer.setFont(new java.awt.Font("Abyssinica SIL", 3, 18)); // NOI18N

        subs.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        subs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subsActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel18.setText("Trainer");

        regid.setEditable(false);
        regid.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel12.setText("Subscription  ");

        jLabel20.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel20.setText("Height(cms)");

        jLabel4.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel4.setText("Reg. ID   ");

        jLabel11.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel11.setText("Weight(kg) ");

        fac.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        fac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facActionPerformed(evt);
            }
        });

        stdate.setDateFormatString("yyy-MM-dd");
        stdate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                stdatePropertyChange(evt);
            }
        });

        edate.setDateFormatString("yyy-MM-dd");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(regid, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(wt, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addGap(65, 65, 65)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(stdate, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(subs, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(65, 65, 65)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel18))
                                .addGap(114, 114, 114)
                                .addComponent(trainer, 0, 156, Short.MAX_VALUE))
                            .addComponent(jLabel4))
                        .addGap(65, 65, 65)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ht, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fac, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {subs, trainer, wt});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {edate, stdate});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(edate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(regid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(wt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11)
                                .addComponent(ht, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(subs, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(fac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(trainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {edate, ht, stdate, subs, trainer, wt});

        jButton1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gms/save.jpg"))); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        print.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gms/print.png"))); // NOI18N
        print.setText("Receipt");
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Fees Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Abyssinica SIL", 1, 24))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Abyssinica SIL", 3, 23)); // NOI18N
        jLabel15.setText("Balance          ");

        jLabel22.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel22.setText("Fee Date");

        amt_st.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        amt_st.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Balance", "Paid" }));

        jLabel13.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel13.setText("Amount           ");

        jLabel14.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel14.setText("Fee Status    ");

        jLabel21.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel21.setText("Due Date");

        fdate.setDateFormatString("yyy-MM-dd");

        ddate.setDateFormatString("yyy-MM-dd");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bal, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(amt_st, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(amt, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel21))
                                .addGap(62, 62, 62)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fdate, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(ddate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(amt)
                        .addGap(1, 1, 1)))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(amt_st, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15)
                    .addComponent(bal))
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ddate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gms/back.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1006, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(print))
                .addContainerGap(137, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, print});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void subsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subsActionPerformed

    private void facActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_facActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try
        {
           
            PreparedStatement ps=con.prepareStatement("insert into per_info values (?,?,?,?,?,?,?,?,?);");
            
            PreparedStatement ps1=con.prepareStatement("insert into gym_info values (?,?,?,?,?,?,?,?,?,?,?,?,?);");
            InputStream is = null;
            
            if(s==null){
                try {
                 is=new FileInputStream(new File("C:\\Users\\Dell\\Documents\\NetBeansProjects\\GMS\\src\\gms\\user.png"));
                    } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex);
                }
            }
            else{
                try {
                 is=new FileInputStream(new File(s));
                } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex);
                }
            }
            
            
            ps.setBlob(1, is);
            ps.setString(2, regid.getText());
            ps.setString(3, fname.getText());
            ps.setString(4, lname.getText());
            ps.setString(5, ph_no.getText());
            ps.setString(6, addr.getText());
            ps.setString(7, (String) gender.getSelectedItem());
            ps.setString(8, email.getText());
            ps.setString(9, age.getText());
            
            ps.executeUpdate();
            
            ps1.setString(1, regid.getText());
            ps1.setString(2, wt.getText());
            ps1.setString(3, ht.getText());
            ps1.setString(4, (String) subs.getSelectedItem());
            ps1.setString(5, amt.getText());
            ps1.setString(6, (String) amt_st.getSelectedItem());
            ps1.setString(7, ((JTextField)stdate.getDateEditor().getUiComponent()).getText());
            ps1.setString(8, ((JTextField)edate.getDateEditor().getUiComponent()).getText());
            ps1.setString(9, (String) trainer.getSelectedItem());
            ps1.setString(10, (String) fac.getSelectedItem());
            ps1.setString(11, bal.getText());
            ps1.setString(12, ((JTextField)fdate.getDateEditor().getUiComponent()).getText());
            ps1.setString(13, ((JTextField)ddate.getDateEditor().getUiComponent()).getText());
            
            ps1.executeUpdate();
            
            PreparedStatement ps2=con.prepareStatement("insert into col values (?,?)");
            ps2.setString(1, ((JTextField)fdate.getDateEditor().getUiComponent()).getText());
            ps2.setString(2,amt.getText());
            
            ps2.executeUpdate();
            print.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Data Saved");
    

            pdf();
           
            
            //new Menu().setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Please fill all the fields correctly");
        }  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        // TODO add your handling code here:
        try
        {
            Desktop.getDesktop().open(new File("C:\\Users\\Dell\\Documents\\NetBeansProjects\\GMS\\Bill.pdf"));
                    
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
         dispose();
    }//GEN-LAST:event_printActionPerformed

    private void ph_noFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ph_noFocusLost
        // TODO add your handling code here:
        Pattern p=Pattern.compile("^[7-9][0-9]{9}$");
        Matcher m=p.matcher(ph_no.getText());
            if(!m.matches())
                JOptionPane.showMessageDialog(null, "Please Enter Valid Contact Number");
    }//GEN-LAST:event_ph_noFocusLost

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        JFileChooser fc=new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter=new  FileNameExtensionFilter("*.IMAGE","jpg","gif","png","jpeg");
        fc.addChoosableFileFilter(filter);
        int result=fc.showSaveDialog(null);
        if(result==JFileChooser.APPROVE_OPTION)
        {
            File sel_file=fc.getSelectedFile();
            String path=sel_file.getAbsolutePath();
            label.setIcon(ResizeImage(path));            
            s = path;
        }
        else if(result==JFileChooser.CANCEL_OPTION)
            JOptionPane.showMessageDialog(null, "Image not selected");

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void stdatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_stdatePropertyChange
        // TODO add your handling code here
    }//GEN-LAST:event_stdatePropertyChange

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddMember().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea addr;
    private javax.swing.JTextField age;
    private javax.swing.JTextField amt;
    private javax.swing.JComboBox<String> amt_st;
    private javax.swing.JTextField bal;
    private com.toedter.calendar.JDateChooser ddate;
    private com.toedter.calendar.JDateChooser edate;
    private javax.swing.JTextField email;
    private javax.swing.JComboBox<String> fac;
    private com.toedter.calendar.JDateChooser fdate;
    private javax.swing.JTextField fname;
    private javax.swing.JComboBox<String> gender;
    private javax.swing.JTextField ht;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label;
    private javax.swing.JTextField lname;
    private javax.swing.JTextField ph_no;
    private javax.swing.JButton print;
    private javax.swing.JTextField regid;
    private com.toedter.calendar.JDateChooser stdate;
    private javax.swing.JComboBox<String> subs;
    private javax.swing.JComboBox<String> trainer;
    private javax.swing.JTextField wt;
    // End of variables declaration//GEN-END:variables
}
