package com.Lab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.apache.log4j.PatternLayout;

import com.ShareClass.FocusMoveByEnter;
import com.ShareClass.SessionBeam;
import com.ShareClass.db_coonection;

public class LabInchargeCosultentSet extends JPanel{

	GridBagConstraints gridc = new GridBagConstraints();
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;


	JPanel panelWestCenter = new JPanel();
	JPanel panelWestSouth = new JPanel();
	JPanel panelWest = new JPanel();
	JPanel panelCenter = new JPanel();


	JLabel lblId=new JLabel("ID");
	JLabel lblName=new JLabel("Name");
	JLabel lblDesignation=new JLabel("Designation");
	JLabel lbl1stLine=new JLabel("1st Line");
	JLabel lbl2ndLine=new JLabel("2nd Line");
	JLabel lbl3rdLine=new JLabel("3rd Line");
	JLabel lbl4thLine=new JLabel("4th Line");
	JLabel lbl5thLine=new JLabel("5th Line");
	JLabel lbl6thLine=new JLabel("6th Line");
	JLabel lbl7thLine=new JLabel("7th Line");
	JLabel lbl8thLine=new JLabel("8th Line");

	JTextField txtId= new JTextField(20);
	JTextField txtName= new JTextField(20);
	JTextField txt1stLine= new JTextField(20);
	JTextField txt2ndLine= new JTextField(20);
	JTextField txt3rdLine= new JTextField(20);
	JTextField txt4thLine= new JTextField(20);
	JTextField txt5thLine= new JTextField(20);
	JTextField txt6thLine= new JTextField(20);
	JTextField txt7thLine= new JTextField(20);
	JTextField txt8thLine= new JTextField(20);

	JButton btnSave = new JButton("Save");
	JButton btnEdit = new JButton("Edit");
	JButton btnDelete = new JButton("Delete");
	JButton btnRefresh = new JButton("Refresh");

	String[] designation = {"Lab Incharge","Consultant"};
	JComboBox cmbDesignation = new JComboBox<>(designation);	
	String TestCol[]={"ID","Name"};
	Object TestRow[][]={};
	DefaultTableModel model=new DefaultTableModel(TestRow,TestCol);
	JTable table=new JTable(model){
		@Override
		public boolean isCellEditable(int row,int col){
			
			return false;	
		}
		@Override
		public Component prepareRenderer 
		(TableCellRenderer renderer, int row, int column) 
		{ 
			Component c = super.prepareRenderer( renderer, row, column); 
			// We want renderer component to be 
			//transparent so background image is visible 
			if( c instanceof JComponent ) 
				((JComponent)c).setOpaque(false); 
			return c; 
		} 
	};
	JScrollPane Scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	BufferedImage image;
	String sql="";
	public void background(){
		try {                
			image = ImageIO.read(new File("icon/bg.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
	}

	public LabInchargeCosultentSet(SessionBeam session) {

		this.sessionBeam=session;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addCmp();
		idLoad();
		btnActionEvent();
		refreshAction();
		background();

	}



	private void btnActionEvent() {
		// TODO Auto-generated method stub
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub


				if(!isIdExist()) {
					if(validationCheck()) {
						if(confirmationCheck("Save")) {
							if(saveAction()) {
								JOptionPane.showMessageDialog(null, "Save Successfully", "Successfull...", JOptionPane.INFORMATION_MESSAGE);
								refreshAction();
							}
						}
						

					}

				}else {
					JOptionPane.showMessageDialog(null, "This Id allready Exist... You Can Edit This Id..", "Information..", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(isIdExist()) {
					if(validationCheck()) {
						if(confirmationCheck("Edit")) {
							if(editAction()) {
								JOptionPane.showMessageDialog(null, "Edit Successfully", "Successfull...", JOptionPane.INFORMATION_MESSAGE);
								refreshAction();
							}
						}
						

					}

				}else {
					JOptionPane.showMessageDialog(null, "This Id Is Not Exist... You Can Not Edit This Id..", "Information..", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				refreshAction();
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(isIdExist()) {
					
						if(confirmationCheck("Delete")) {
							if(deleteAction()) {
								JOptionPane.showMessageDialog(null, "delete Successfully", "Successfull...", JOptionPane.INFORMATION_MESSAGE);
								refreshAction();
							}
						}
						

					

				}else {
					JOptionPane.showMessageDialog(null, "This Id Is Not Exist... You Can Not Delete This Id..", "Information..", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				String id = table.getValueAt(table.getSelectedRow(), 0).toString();
				tableClickAction(id);
			}
		});
		
		
	}


	private void tableClickAction(String id) {
		try {
			
			sql = "select * from tbLabInchargeConsultantDegree where id= '"+id+"'";
			System.out.println(sql);
			ResultSet rs = db.sta.executeQuery(sql);
			if(rs.next()) {
				txtId.setText(rs.getString("id"));
				txtName.setText(rs.getString("name"));
				txt1stLine.setText(rs.getString("line1"));
				txt2ndLine.setText(rs.getString("line2"));
				txt3rdLine.setText(rs.getString("line3"));
				txt4thLine.setText(rs.getString("line4"));
				txt5thLine.setText(rs.getString("line5"));
				txt6thLine.setText(rs.getString("line6"));
				txt7thLine.setText(rs.getString("line7"));
				txt8thLine.setText(rs.getString("line8"));
				cmbDesignation.setSelectedItem(rs.getString("designation"));
			}
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
	}


	protected boolean deleteAction() {
		try {
			sql = "delete from tbLabInchargeConsultantDegree where id = '"+txtId.getText().trim()+"'";
			System.out.println(sql);
			db.sta.execute(sql);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
		return false;
	}

	private boolean confirmationCheck(String name) {
		if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Are You Sure to "+name+" ?","Confrimaateion..",JOptionPane.YES_NO_OPTION)) return true;
		return false;
	}

	private boolean editAction() {
		// TODO Auto-generated method stub
		try {
			sql = "update tbLabInchargeConsultantDegree "
					+ "set Name='"+txtName.getText().trim()+"',"
							+ "designation='"+cmbDesignation.getSelectedItem()+"',"
									+ "line1='"+txt1stLine.getText().trim()+"',"
									+ "line2='"+txt2ndLine.getText().trim()+"',"
									+ "line3='"+txt3rdLine.getText().trim()+"',"
									+ "line4='"+txt4thLine.getText().trim()+"',"
									+ "line5='"+txt5thLine.getText().trim()+"',"
									+ "line6='"+txt6thLine.getText().trim()+"',"
									+ "line7='"+txt7thLine.getText().trim()+"',"
									+ "line8='"+txt8thLine.getText().trim()+"',"
									+ "entryTime=CURRENT_TIMESTAMP,"
									+ "entryBy='"+sessionBeam.getUserId()+"' where id = '"+txtId.getText().trim()+"'";
					
			System.out.println(sql);
			db.sta.execute(sql);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
		return false;
	}
	private boolean saveAction() {
		// TODO Auto-generated method stub
		try {
			sql = "insert into tbLabInchargeConsultantDegree (id,Name,designation,line1,line2,line3,line4,line5,line6,line7,line8,entryTime,entryBy) "
					+ "values('"+txtId.getText().trim()+"','"+txtName.getText().trim()+"','"+cmbDesignation.getSelectedItem()+"','"+txt1stLine.getText().trim()+"','"+txt2ndLine.getText().trim()+"','"+txt3rdLine.getText().trim()+"','"+txt4thLine.getText().trim()+"','"+txt5thLine.getText().trim()+"','"+txt6thLine.getText().trim()+"','"+txt7thLine.getText().trim()+"','"+txt8thLine.getText().trim()+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"');";
			System.out.println(sql);
			db.sta.execute(sql);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
		return false;
	}
	
	private void refreshAction() {
		// TODO Auto-generated method stub
		idLoad();
		txtName.setText("");
		cmbDesignation.setSelectedIndex(0);
		txt1stLine.setText("");
		txt2ndLine.setText("");
		txt3rdLine.setText("");
		txt4thLine.setText("");
		txt5thLine.setText("");
		txt7thLine.setText("");
		txt8thLine.setText("");
		txt6thLine.setText("");
		tableDataLoad();
	}

	public boolean validationCheck() {

		if(!txtName.getText().trim().isEmpty()) {
			if(!isNameDuplicate()) {
				return true;
			}else {
				JOptionPane.showMessageDialog(null, "Your Name is All ready Exist...\n Please Enter other Name", "Sorry", JOptionPane.WARNING_MESSAGE);
				txtName.requestFocusInWindow();
			}
		}else {
			JOptionPane.showMessageDialog(null, "Please Enter Name", "Sorry", JOptionPane.WARNING_MESSAGE);
			txtName.requestFocusInWindow();
		}
		return false;
	}

	private boolean isNameDuplicate() {
		// TODO Auto-generated method stub
		try {
			ResultSet rs=db.sta.executeQuery("select * from tbLabInchargeConsultantDegree where name='"+txtName.getText().trim().isEmpty()+"'");
			if(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return false;
	}

	protected boolean isIdExist() {

		try {
			ResultSet rs=db.sta.executeQuery("select * from tbLabInchargeConsultantDegree where id='"+txtId.getText().trim()+"'");
			if(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}

		return false;
	}

	

	private void idLoad() {

		try {
			ResultSet rs=db.sta.executeQuery("select ISNULL(max(id),0)+1 as maxId from tbLabInchargeConsultantDegree ");
			while(rs.next()){
				txtId.setText(rs.getString("maxId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}


	}

	private void tableDataLoad() {
		// TODO Auto-generated method stub
		for(int i = table.getRowCount()-1;i>=0;i--) {
			model.removeRow(i);
		}
		try {
			ResultSet rs=db.sta.executeQuery("select id,name from tbLabInchargeConsultantDegree ");
			while(rs.next()){
				model.addRow(new Object[] {rs.getString("id"),rs.getString("name")});
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}

	private void addCmp() {
		// TODO Auto-generated method stub
		setOpaque(false);
		setPreferredSize(new Dimension(700, 500));
		setLayout(new BorderLayout());
		add(panelWest,BorderLayout.WEST);
		westPanelWork();
		add(panelCenter,BorderLayout.CENTER);
		centerPanelWork();
	}

	private void westPanelWork() {
		// TODO Auto-generated method stub
		panelWest.setOpaque(false);
		panelWest.setPreferredSize(new Dimension(350, 0));
		panelWest.setLayout(new BorderLayout());
		panelWest.add(panelWestCenter,BorderLayout.CENTER);
		westCenterPanelWork();
		panelWest.add(panelWestSouth,BorderLayout.SOUTH);
		westSouthPanelWork();
	}

	private void westSouthPanelWork() {
		// TODO Auto-generated method stub
		panelWestSouth.setPreferredSize(new Dimension(0, 100));
		panelWestSouth.setOpaque(false);
		panelWestSouth.setLayout(new FlowLayout());;

		panelWestSouth.add(btnSave);
		panelWestSouth.add(btnEdit);
		panelWestSouth.add(btnRefresh);
		panelWestSouth.add(btnDelete);

	}

	private void westCenterPanelWork() {
		// TODO Auto-generated method stub
		panelWestCenter.setOpaque(false);
		//panelWestCenter.setPreferredSize(new Dimension(300, 0));

		panelWestCenter.setLayout(new GridBagLayout());

		gridc.fill = new GridBagConstraints().BOTH;
		gridc.insets = new Insets(5, 1, 5, 5);
		gridc.gridx = 0;
		gridc.gridy = 0;
		panelWestCenter.add(lblId,gridc);
		txtId.setEditable(false);

		gridc.gridx = 0;
		gridc.gridy = 1;
		panelWestCenter.add(lblName,gridc);

		gridc.gridx = 0;
		gridc.gridy = 2;
		panelWestCenter.add(lblDesignation,gridc);

		gridc.gridx = 0;
		gridc.gridy = 3;
		panelWestCenter.add(lbl1stLine,gridc);

		gridc.gridx = 0;
		gridc.gridy = 4;
		panelWestCenter.add(lbl2ndLine,gridc);


		gridc.gridx = 0;
		gridc.gridy = 5;
		panelWestCenter.add(lbl3rdLine,gridc);


		gridc.gridx = 0;
		gridc.gridy = 6;
		panelWestCenter.add(lbl4thLine,gridc);

		gridc.gridx = 0;
		gridc.gridy = 7;
		panelWestCenter.add(lbl5thLine,gridc);

		gridc.gridx = 0;
		gridc.gridy = 8;
		panelWestCenter.add(lbl6thLine,gridc);


		gridc.gridx = 0;
		gridc.gridy = 9;
		panelWestCenter.add(lbl7thLine,gridc);


		gridc.gridx = 0;
		gridc.gridy = 10;
		panelWestCenter.add(lbl8thLine,gridc);


		gridc.gridx = 1;
		gridc.gridy = 0;
		panelWestCenter.add(txtId,gridc);

		gridc.gridx = 1;
		gridc.gridy = 1;
		panelWestCenter.add(txtName,gridc);

		gridc.gridx = 1;
		gridc.gridy = 2;
		panelWestCenter.add(cmbDesignation,gridc);

		gridc.gridx = 1;
		gridc.gridy = 3;
		panelWestCenter.add(txt1stLine,gridc);

		gridc.gridx = 1;
		gridc.gridy = 4;
		panelWestCenter.add(txt2ndLine,gridc);


		gridc.gridx = 1;
		gridc.gridy = 5;
		panelWestCenter.add(txt3rdLine,gridc);


		gridc.gridx = 1;
		gridc.gridy = 6;
		panelWestCenter.add(txt4thLine,gridc);

		gridc.gridx = 1;
		gridc.gridy = 7;
		panelWestCenter.add(txt5thLine,gridc);

		gridc.gridx = 1;
		gridc.gridy = 8;
		panelWestCenter.add(txt6thLine,gridc);


		gridc.gridx = 1;
		gridc.gridy = 9;
		panelWestCenter.add(txt7thLine,gridc);


		gridc.gridx = 1;
		gridc.gridy = 10;
		panelWestCenter.add(txt8thLine,gridc);
		
		Component[] cmp = {txtName,cmbDesignation,txt1stLine,txt2ndLine,txt3rdLine,txt4thLine,txt5thLine,txt6thLine,txt7thLine,txt8thLine,btnSave};
		new FocusMoveByEnter(cmp);
	}

	private void centerPanelWork() {
		// TODO Auto-generated method stub
		panelCenter.setOpaque(false);
		panelCenter.setLayout(new BorderLayout());

		panelCenter.add(Scroll);
		Scroll.setPreferredSize(new Dimension(980, 470));
		Scroll.setOpaque(false);
		Scroll.getViewport().setOpaque(false);
		table.setOpaque(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(380);
		
		table.setRowHeight(table.getRowHeight() + 10);
		table.setShowGrid(true);
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 13));

	}

}
