/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gms;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import static gms.Connect.con;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author dell
 */
public class Update extends javax.swing.JFrame {

    /**
     * Creates new form Update
     */
    public Update() {
        initComponents();
        cont.setVisible(false);
        contl.setVisible(false);
        getregid.setVisible(false);
        msg.setVisible(false);
        updt.setEnabled(false);
        del.setEnabled(false);
        pr.setEnabled(false);
        addTrainer();
        addS();
        addF();
        getContentPane().setBackground(Color.GRAY);
        dateFunc();
        Connection con=Connect.toMysql();
    }
  public int payfees;   
  public String s=null;
  //public ImageIcon newImg=null;
  InputStream is = null;
  
  
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

    public ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
  
  public void addF()
{
        try
        {
            PreparedStatement ps=con.prepareStatement("select * from Fac");
           
            ResultSet rs=ps.executeQuery();
             while(rs.next())
               fac.addItem(rs.getString(1));
            
            
          
        } catch(SQLException e)
        {
             JOptionPane.showMessageDialog(null,e);
        }
}
public void addS()
{
        try
        {
            PreparedStatement ps=con.prepareStatement("select * from Subs");
           
            ResultSet rs=ps.executeQuery();
             while(rs.next())
                subs.addItem(rs.getString(1));          
        } catch(SQLException e)
        {
             JOptionPane.showMessageDialog(null,e);
        }
}    
    
public void addTrainer()
{
     try
        {
            PreparedStatement ps=con.prepareStatement("select * from trainer");
           
            ResultSet rs=ps.executeQuery();
          
            while(rs.next())
                trainer.addItem(rs.getString(2));
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
         doc.add(new Paragraph("Subscription: "+subs.getSelectedItem()+"             Facility: "+fac.getSelectedItem()));
         doc.add(new Paragraph("Start Date: "+sdate+"            End Date: "+eedate));
         
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
         doc.add(new Paragraph("    "));
         doc.add(new Paragraph("Member Sign                                                                     Admin Sign"));

                  
         doc.close();
         
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

        jLabel4 = new javax.swing.JLabel();
        regid = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        contl = new javax.swing.JLabel();
        updt = new javax.swing.JButton();
        del = new javax.swing.JButton();
        msg = new javax.swing.JLabel();
        cont = new javax.swing.JTextField();
        getregid = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
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
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        ht = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        trainer = new javax.swing.JComboBox<>();
        subs = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        wt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        fac = new javax.swing.JComboBox<>();
        stdate = new com.toedter.calendar.JDateChooser();
        edate = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        bal = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        amt = new javax.swing.JTextField();
        amt_st = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        fdate = new com.toedter.calendar.JDateChooser();
        ddate = new com.toedter.calendar.JDateChooser();
        pr = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SEARCH/UPDATE/DELETE");

        jLabel4.setFont(new java.awt.Font("Abyssinica SIL", 3, 18)); // NOI18N
        jLabel4.setText("Reg. ID   ");

        regid.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jButton1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gms/search.png"))); // NOI18N
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        contl.setFont(new java.awt.Font("Abyssinica SIL", 3, 22)); // NOI18N
        contl.setText("Contact");

        updt.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        updt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gms/update.jpg"))); // NOI18N
        updt.setText("Update");
        updt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updtActionPerformed(evt);
            }
        });

        del.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        del.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gms/delete.jpg"))); // NOI18N
        del.setText("Delete");
        del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delActionPerformed(evt);
            }
        });

        msg.setForeground(new java.awt.Color(244, 19, 19));
        msg.setText("Enter phone number above");

        cont.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        getregid.setText("Get Reg. ID");
        getregid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getregidActionPerformed(evt);
            }
        });

        jButton7.setText("Forgot Reg. ID?");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gms/back.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(53, 82, 70), 2, true), "Personal Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Serif Condensed", 1, 24))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel2.setText("First Name");

        lname.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel9.setText("Age");

        gender.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        jLabel5.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel5.setText("Email");

        age.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel10.setText("Gender");

        jLabel6.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel6.setText("Address");

        fname.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        email.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel8.setText("Contact");

        jLabel7.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel7.setText("Last Name");

        ph_no.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ph_no.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ph_noFocusLost(evt);
            }
        });

        addr.setColumns(20);
        addr.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        addr.setRows(5);
        jScrollPane2.setViewportView(addr);

        jButton4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gms/image (2).png"))); // NOI18N
        jButton4.setText("Select");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ph_no, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(age, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lname, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(lname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(age, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ph_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Gym Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Serif Condensed", 1, 24))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(917, 394));

        ht.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

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

        wt.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel12.setText("Subscription  ");

        jLabel20.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel20.setText("Height(cms)");

        jLabel13.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel13.setText("Weight(kg) ");

        fac.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        fac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facActionPerformed(evt);
            }
        });

        stdate.setDateFormatString("yyyy-MM-dd");

        edate.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16))
                                .addGap(73, 73, 73))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(84, 84, 84)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(trainer, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stdate, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(wt, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(subs, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edate, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ht, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fac, 0, 201, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(wt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(ht, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(subs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel19))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(fac, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(stdate, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(edate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(trainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel13, wt});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ht, jLabel20});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {stdate, subs});

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Fees Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Abyssinica SIL", 1, 24))); // NOI18N

        bal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Abyssinica SIL", 3, 23)); // NOI18N
        jLabel15.setText("Balance          ");

        jLabel22.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel22.setText("Fee Date");

        amt.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        amt_st.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        amt_st.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Balance", "Paid" }));

        jLabel14.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel14.setText("Amount           ");

        jLabel21.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel21.setText("Fee Status    ");

        jLabel23.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        jLabel23.setText("Due Date");

        fdate.setDateFormatString("yyyy-MM-dd");

        ddate.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(2, 2, 2)
                        .addComponent(bal, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(amt_st, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(amt, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel23))
                                .addGap(62, 62, 62)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fdate, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                    .addComponent(ddate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(amt)
                        .addGap(1, 1, 1)))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(amt_st, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bal))
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ddate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pr.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        pr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gms/print.png"))); // NOI18N
        pr.setText("Receipt");
        pr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(220, 220, 220)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(regid, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 863, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(contl, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(msg)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cont, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(getregid))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(updt, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(del)
                        .addGap(18, 18, 18)
                        .addComponent(pr)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {del, pr, updt});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(regid, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(contl)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(getregid, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(msg, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updt)
                            .addComponent(del)
                            .addComponent(pr)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cont, contl});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton3, jLabel4});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {del, pr, updt});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    
       try
        {
            PreparedStatement ps=con.prepareStatement("select * from per_info where Reg_ID='"+regid.getText()+"';");      
            ResultSet rs=ps.executeQuery();
            
            if(!rs.next())
                JOptionPane.showMessageDialog(null,"Record Not Found");
            else
            {
                   updt.setEnabled(true);
                   del.setEnabled(true);
                   pr.setEnabled(true);
                  
 payfees=JOptionPane.showConfirmDialog(null, "Do you wish to PAY FEES? If YES, then it will be counted to your collections","Receipt",JOptionPane.YES_NO_OPTION);
                byte[] img=rs.getBytes(1);
                is=new ByteArrayInputStream(img);
                ImageIcon image=new ImageIcon(img);
                Image im=image.getImage();
                Image myimg=im.getScaledInstance(label.getWidth(),label.getHeight(),Image.SCALE_SMOOTH);
                ImageIcon newImg=new ImageIcon(myimg);
                label.setIcon(newImg);
                fname.setText(rs.getString(3));
                lname.setText(rs.getString(4));
                ph_no.setText(rs.getString(5));
                addr.setText(rs.getString(6));
                gender.setSelectedItem(rs.getString(7));
                email.setText(rs.getString(8));
                age.setText(Integer.toString(rs.getInt(9)));
                regid.setEnabled(false);
            }
                            
           PreparedStatement ps1=con.prepareStatement("select * from gym_info where Reg_ID='"+regid.getText()+"';");
           ResultSet rs1=ps1.executeQuery();
            
            if(rs1.next())
            {
                wt.setText(Integer.toString(rs1.getInt(2)));
                ht.setText(Integer.toString(rs1.getInt(3)));
                subs.setSelectedItem(rs1.getString(4));
                amt.setText(Integer.toString(rs1.getInt(5)));
                amt_st.setSelectedItem(rs1.getString(6));
                ((JTextField)stdate.getDateEditor().getUiComponent()).setText(rs1.getString(7));
                ((JTextField)edate.getDateEditor().getUiComponent()).setText(rs1.getString(8));
                trainer.setSelectedItem(rs1.getString(9));
                fac.setSelectedItem(rs1.getString(10));
                bal.setText(Integer.toString(rs1.getInt(11)));
                ((JTextField)fdate.getDateEditor().getUiComponent()).setText(rs1.getString(12));
                ((JTextField)ddate.getDateEditor().getUiComponent()).setText(rs1.getString(13));
                 pdf();
            }
                
            
        } catch(SQLException e)
        {
             JOptionPane.showMessageDialog(null,e);
        }  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void updtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updtActionPerformed
        // TODO add your handling code here:
        
        try
        {
             PreparedStatement ps2=con.prepareStatement("delete from gym_info where Reg_ID='"+regid.getText()+"';");
             ps2.executeUpdate();
             
             PreparedStatement ps3=con.prepareStatement("delete from per_info where Reg_ID='"+regid.getText()+"';");
             ps3.executeUpdate();
            
            //JOptionPane.showMessageDialog(null, "after del");
            PreparedStatement ps=con.prepareStatement("insert into per_info values (?,?,?,?,?,?,?,?,?);");
            //InputStream is = null;
            if(s!=null)
            {
                try {
                    is=new FileInputStream(new File(s));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                 }
            }
            else{
                
            }   
            //JOptionPane.showMessageDialog(null, "after is");
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
            //JOptionPane.showMessageDialog(null, "after per_info");
            PreparedStatement ps1=con.prepareStatement("insert into gym_info values (?,?,?,?,?,?,?,?,?,?,?,?,?);");
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
            
            
            if(payfees==0)
            {
            PreparedStatement ps4=con.prepareStatement("insert into col values (?,?)");
            ps4.setString(1, ((JTextField)fdate.getDateEditor().getUiComponent()).getText());
            ps4.setString(2,amt.getText());
            
            ps4.executeUpdate();

            }
            JOptionPane.showMessageDialog(null, "Record Updated Successfully");
            JOptionPane.showMessageDialog(null, "Receipt is Ready");
            printpdf();
            dispose();
           
        } catch(SQLException e)
        {
             JOptionPane.showMessageDialog(null,"Please fill all the fields correctly");
        }  
    }//GEN-LAST:event_updtActionPerformed

    private void delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delActionPerformed
        // TODO add your handling code here:
              try
        {
             PreparedStatement ps=null;
             ps=con.prepareStatement("delete from gym_info where reg_id='"+regid.getText()+"';");
             
             PreparedStatement ps1=null;
             ps1=con.prepareStatement("delete from per_info where reg_id='"+regid.getText()+"';");
             
          
             int d=JOptionPane.showConfirmDialog(null,"Are you sure to delete?","DELETE",JOptionPane.YES_NO_OPTION);
             
               if(d==0)
               {
                ps.executeUpdate();
                ps1.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record Deleted");
                dispose();
               }
              
               
        } catch(SQLException e)
        {
             JOptionPane.showMessageDialog(null,e);
        }  
    }//GEN-LAST:event_delActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Enter Phone Number to get Reg. ID & click on Get Reg. ID to Search");
        cont.setVisible(true);
        cont.setText("");
        contl.setVisible(true);
        getregid.setVisible(true);
        msg.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void getregidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getregidActionPerformed
        // TODO add your handling code here:       
        try
        {
           PreparedStatement ps=con.prepareStatement("select * from per_info where Contact='"+cont.getText()+"';");      
           ResultSet rs=ps.executeQuery();
          
          if(rs.next())
          {
              regid.setText(Integer.toString(rs.getInt(2)));
               cont.setVisible(false);
               contl.setVisible(false);
               getregid.setVisible(false);
               msg.setVisible(false);
 
          }      
           else
              JOptionPane.showMessageDialog(null,"Phone Number NOT FOUND");
              
        } catch(SQLException e)
        {
             JOptionPane.showMessageDialog(null,e);
        }  
    }//GEN-LAST:event_getregidActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void ph_noFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ph_noFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_ph_noFocusLost

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
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
            JOptionPane.showMessageDialog(null, "Image not selected this time");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void subsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subsActionPerformed

    private void facActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_facActionPerformed

    private void prActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prActionPerformed
        // TODO add your handling code here:
       
        try
        {
            Desktop.getDesktop().open(new File("C:\\Users\\Dell\\Documents\\NetBeansProjects\\GMS\\Bill.pdf"));
                    
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_prActionPerformed

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
            java.util.logging.Logger.getLogger(Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Update().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea addr;
    private javax.swing.JTextField age;
    private javax.swing.JTextField amt;
    private javax.swing.JComboBox<String> amt_st;
    private javax.swing.JTextField bal;
    private javax.swing.JTextField cont;
    private javax.swing.JLabel contl;
    private com.toedter.calendar.JDateChooser ddate;
    private javax.swing.JButton del;
    private com.toedter.calendar.JDateChooser edate;
    private javax.swing.JTextField email;
    private javax.swing.JComboBox<String> fac;
    private com.toedter.calendar.JDateChooser fdate;
    private javax.swing.JTextField fname;
    private javax.swing.JComboBox<String> gender;
    private javax.swing.JButton getregid;
    private javax.swing.JTextField ht;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel23;
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
    private javax.swing.JLabel msg;
    private javax.swing.JTextField ph_no;
    private javax.swing.JButton pr;
    private javax.swing.JTextField regid;
    private com.toedter.calendar.JDateChooser stdate;
    private javax.swing.JComboBox<String> subs;
    private javax.swing.JComboBox<String> trainer;
    private javax.swing.JButton updt;
    private javax.swing.JTextField wt;
    // End of variables declaration//GEN-END:variables
}
