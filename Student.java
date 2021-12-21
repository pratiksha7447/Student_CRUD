
/********************************************************************************/
/* CRUD Operation on Student | Name: Pratiksha Thorat                           */
/* ******************************************************************************/



// Importing packages

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.event.*;

// UI for showing CRUD operations

class Student extends JFrame implements ActionListener
{
	
	JFrame f;
	JButton insert,update,delete,display_stud,display_one;
	JPanel p1,p2;
	
	Student()
	{
		
		f=new JFrame("Student");
		
		insert=new JButton("To Insert Click Me");
		update=new JButton("To Update Click Me");
		delete=new JButton("To Delete Click Me");
		display_one=new JButton("To Display One Click Me");
		display_stud=new JButton("To Display Names Click Me");
		
		p1=new JPanel();
		p2=new JPanel();
		p2.setBackground(Color.BLACK);
		p1.setBackground(Color.BLACK);
		
		
		p1.setLayout(new GridLayout(5,1));
		p1.add(insert); 
		insert.setBounds(280,250,130,30);
		insert.addActionListener(this);		
		p1.add(update);
		update.setSize(10,5);
		update.addActionListener(this);
		p1.add(delete);   
		delete.addActionListener(this);     	
		p1.setSize(20,10);
		
		p2.setLayout(new GridLayout(1,2,2,2));
		p1.add(display_one);
		display_one.addActionListener(this);
		p1.add(display_stud);
		
		FlowLayout layout = new FlowLayout();
        layout.setHgap(60);              
        layout.setVgap(20);
		
		
		display_stud.addActionListener(this);
		p1.setLayout(layout);
		f.add(p1,(BorderLayout.CENTER));
		
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400,300);
		f.setResizable(false);
		f.setLocation(400,200);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==insert)
		{
			new Add();
		}
		
		if(ae.getSource()==delete)
		{
			new Delete();
		}
		
		if(ae.getSource()==update)
		{
			new Update();
		}
		
		if(ae.getSource()==display_one)
		{
			new DisplayOne();
		}
		
		if(ae.getSource()==display_stud)
		{
			DisplayAll S = new DisplayAll();
			S.display();
		}
		
	}
	
	public static void main(String args[])
	{
		new Student();
	}
}




// Insert student data into Student table 

class Add extends JFrame implements ActionListener
{
	JFrame f;
	JLabel stud_no,stud_name,dob,doj;
	JPanel p1,p2;
	JButton insert,back;
	JTextField name,no,dob1,doj1;
	Add()
	{
		f=new JFrame("Add Student");
		
		insert=new JButton("Insert");
		back=new JButton("Cancel");
		
		stud_no=new JLabel("Student Number");
		stud_no.setForeground(Color.WHITE);
		stud_name=new JLabel("Student Name");
		stud_name.setForeground(Color.WHITE);
		dob=new JLabel("Date Of Birth(yyyy-mm-dd)");
		dob.setForeground(Color.WHITE);
		doj=new JLabel("Date Of Joining(yyyy-mm-dd)");
		doj.setForeground(Color.WHITE);
		
		p1=new JPanel();
		p2=new JPanel();
		p2.setBackground(Color.BLACK);
		p1.setBackground(Color.BLACK);
		
		
		name=new JTextField();
		no=new JTextField();
		dob1=new JTextField(10);
		doj1=new JTextField(10);
		
		p1.setLayout(new GridLayout(4,4,2,2));
		p1.add(stud_no); 		p1.add(no);
		p1.add(stud_name); 		p1.add(name);
		p1.add(dob);        	p1.add(dob1);
		p1.add(doj);			p1.add(doj1);
		
		p2.add(insert);
		p2.add(back);
		insert.addActionListener(this);
		back.addActionListener(this);
		f.add(p1,BorderLayout.CENTER);
		f.add(p2,BorderLayout.SOUTH);
		
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400,300);
		f.setResizable(false);
		f.setLocation(400,200);
		}
	
	public void actionPerformed(ActionEvent ae)
	{
		
			Connection conn=null;
			Statement stmt=null;
			
			String sql;
			int ch;
			try
			{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost/STUDENT","root","Mahesh@2001");
			stmt=conn.createStatement();
			
			if(ae.getSource()==insert)
			{
				String n,name1,d1,d2;
				n=no.getText();
				name1=name.getText();
				d1=dob1.getText();
				d2=doj1.getText();
				sql="insert into STUDENT values('"+n+"','"+name1+"','"+d1+"','"+d2+"');";
				ch =stmt.executeUpdate(sql);
				if(ch==1)
				{
				JOptionPane.showMessageDialog(this, "Record Added Successfully");
				}
			}
			if(ae.getSource()==back)
			{
				f.dispose();
			}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(this, "Record added  failure");
				System.out.println(e);
			}
			
			
	}
}

// Delete student data from Student table 

class Delete extends JFrame implements ActionListener
{
	JFrame f;
	JLabel stud_no,info;
	JPanel p1,p2,p3;
	JButton delete,cancel;
	JTextField no;
	Delete()
	{
		f=new JFrame("Add Student");
		delete=new JButton("Delete");
		cancel=new JButton("Cancel");
		stud_no=new JLabel("Student Number");
		info=new JLabel("ENTER STUDENT NUMBER HERE TO DELETE RECORD");
		
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		
		
		no=new JTextField(10);
		no.setSize(10,2);
		p1.setLayout(new GridLayout(2,2,2,2));
		p1.add(info);
		p2.add(stud_no); 		p2.add(no);
		
		p3.add(delete);
		p3.add(cancel);
		delete.addActionListener(this);
		cancel.addActionListener(this);
		f.add(p1,BorderLayout.NORTH);
		f.add(p2,BorderLayout.CENTER);
		f.add(p3,BorderLayout.SOUTH);
		
		
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(350,150);
		f.setResizable(false);
		f.setLocation(400,200);
	}
		
		public void actionPerformed(ActionEvent ae)
		{
		
			Connection conn=null;
			Statement stmt=null;
			
			String sql;
			int ch;
			try
			{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost/STUDENT","root","Mahesh@2001");
			stmt=conn.createStatement();
			
			if(ae.getSource()==delete)
			{
				String n;
				n=no.getText();
				sql="delete from STUDENT where STUDENT_NO='"+n+"';";
				ch =stmt.executeUpdate(sql);
				if(ch==1)
				{
				JOptionPane.showMessageDialog(this, "Record deleted Successfully");
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Insert Correct Student Number");
				}
			}
			
			
			if(ae.getSource()==cancel)
			{
				f.dispose();
			}
			
			
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(this, "Record deleted  failure");
				System.out.println(e);
			}
		}
}

// Update student data into Student table 

class Update extends JFrame implements ActionListener
{
	JFrame f;
	JLabel stud_no,stud_name,dob,doj;
	JPanel p1,p2;
	JButton update,cancel;
	JTextField name,no,dob1,doj1;
	Update()
	{
		f=new JFrame("Add Student");
		update=new JButton("Update");
		cancel=new JButton("Cancel");
		stud_no=new JLabel("Student Number");
		stud_no.setForeground(Color.WHITE);
		stud_name=new JLabel("Student Name");
		stud_name.setForeground(Color.WHITE);
		dob=new JLabel("Date Of Birth(yyyy-mm-dd)");
		dob.setForeground(Color.WHITE);
		doj=new JLabel("Date Of Joining(yyyy-mm-dd)");
		doj.setForeground(Color.WHITE);
		
		p1=new JPanel();
		p2=new JPanel();
		p2.setBackground(Color.BLACK);
		p1.setBackground(Color.BLACK);
		name=new JTextField();
		
		no=new JTextField();
		
		dob1=new JTextField(10);
		doj1=new JTextField(10);
		
		p1.setLayout(new GridLayout(4,4,2,2));
		p1.add(stud_no); 		p1.add(no);
		p1.add(stud_name); 		p1.add(name);
		p1.add(dob);        	p1.add(dob1);
		p1.add(doj);			p1.add(doj1);
		
		p2.add(update);
		p2.add(cancel);
		update.addActionListener(this);
		cancel.addActionListener(this);
		f.add(p1,BorderLayout.CENTER);
		f.add(p2,BorderLayout.SOUTH);
		
		
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(500,200);
		f.setResizable(false);
		f.setLocation(400,200);
		}
	
	public void actionPerformed(ActionEvent ae)
	{
		
			Connection conn=null;
			Statement stmt=null;
			
			String sql;
			int ch;
			try
			{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost/STUDENT","root","Mahesh@2001");
			stmt=conn.createStatement();
			
			if(ae.getSource()==update)
			{
				String n,name1,d1,d2;
				n=no.getText();
				name1=name.getText();
				d1=dob1.getText();
				d2=doj1.getText();
				sql="update STUDENT set STUDENT_NAME='"+name1+"',STUDENT_DOB='"+d1+"',STUDENT_DOJ='"+d2+"'where STUDENT_NO='"+n+"';";
				ch =stmt.executeUpdate(sql);
				if(ch==1)
				{
				JOptionPane.showMessageDialog(this, "Record Updateed Successfully");
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Record Updateed Failure");
				}
			}
			if(ae.getSource()==cancel)
			{
				f.dispose();
			}
			
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(this, "Record Updation failure");
				System.out.println(e);
			}
			
			
	}
}

// Get one student information depending on the student id filter. 


class DisplayOne extends JFrame implements ActionListener
{
	JFrame f;
	JLabel stud_no,info;
	JPanel p1,p2,p3;
	JButton d1,cancel;
	JTextField no;
	
	DisplayOne()
	{
		f=new JFrame("Add Student");
		d1=new JButton("display_one");
		cancel=new JButton("Cancel");
		stud_no=new JLabel("Student Number");
		info=new JLabel("ENTER STUDENT NUMBER HERE TO DISPLAY RECORD");
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		
				
		no=new JTextField(10);
		no.setSize(10,2);
		p1.setLayout(new GridLayout(2,2,2,2));
		p1.add(info);
		p2.add(stud_no); 		p2.add(no);
		
		p3.add(d1);
		p3.add(cancel);
		d1.addActionListener(this);
		cancel.addActionListener(this);
		f.add(p1,BorderLayout.NORTH);
		f.add(p2,BorderLayout.CENTER);
		f.add(p3,BorderLayout.SOUTH);
		
		
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(350,150);
		f.setResizable(false);
		f.setLocation(400,200);
	}
		
		public void actionPerformed(ActionEvent ae)
		{
		
			Connection conn=null;
			Statement stmt=null;
			ResultSet rs=null;
			String sql;
			String n1,name,dob,doj;
			
			try
			{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost/STUDENT","root","Mahesh@2001");
			stmt=conn.createStatement();
			
			if(ae.getSource()==d1)
			{
				String n;
				n=no.getText();
				sql="select * from STUDENT where STUDENT_NO='"+n+"';";
				rs=stmt.executeQuery(sql);
				
				while(rs.next())
				{
				n1=rs.getString(1);
				name=rs.getString(2);
				dob=rs.getString(3);
				doj=rs.getString(4);
				JOptionPane.showMessageDialog(this, "Number="+n1+" "+"name="+name+" "+"dob="+dob+" "+"doj="+doj);
				
				}
				
			}
			if(ae.getSource()==cancel)
			{
				f.dispose();
			}
			
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(this, "Display failure");
				System.out.println(e);
			}
		}
}

// // Get a list of all students 

class DisplayAll 
{
		void display()
		{
			try
			{
			JFrame f;
			Connection conn=null;
			Statement stmt=null;
			ResultSet rs=null;
			String sql;
			JTable jtb;
			JScrollPane jsp;
			Vector heading,rowdata,row;
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost/STUDENT","root","Mahesh@2001");
			stmt=conn.createStatement();
			sql="select STUDENT_NAME from STUDENT";
			rs=stmt.executeQuery(sql);
			heading = new Vector();
			heading.add("Student Names");
			rowdata= new Vector();
			while (rs.next())
			{
				row= new Vector();
				row.add(rs.getString(1));
				rowdata.add(row);
			}
			jtb= new JTable(rowdata,heading);
			jsp=new JScrollPane(jtb);
			f=new JFrame("STUDENT Names");
			f.add(jsp,BorderLayout.CENTER);
			f.setVisible(true);
			f.setLocation(400,200);
			f.setSize(300,400);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
}