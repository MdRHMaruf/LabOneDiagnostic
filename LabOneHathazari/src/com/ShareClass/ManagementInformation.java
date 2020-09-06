package com.ShareClass;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.ShareClass.FocusMoveByEnter;
import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
import com.ShareClass.db_coonection;
import com.mysql.jdbc.PreparedStatement;

import com.toedter.calendar.JDateChooser;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ManagementInformation extends JPanel{
	SessionBeam sessionbeam;
	db_coonection db=new db_coonection();
	JPanel mainPanel=new JPanel();
	JPanel eastPanel=new JPanel();
	JPanel eastPanelNorth=new JPanel();
	JPanel eastPanelCenter=new JPanel();
	JPanel westPanel=new JPanel();
	JPanel westNorthPanel=new JPanel();
	JPanel westCenterPanel=new JPanel();
	JPanel westSouthPanel=new JPanel();
	JPanel westEastPanel=new JPanel();
	JPanel westEastNorthPanel=new JPanel();
	JPanel westEastCenterPanel=new JPanel();
	
	HashMap map;

	JFileChooser fileChooser;
	File file=new File("");

	BufferedImage buffer;
	byte[] immAsBytes;
	
	Image logoImage;

	JLabel lblSearchName=new JLabel("<html><font color=red>*</font>Name</html>");


	JLabel lblPhoto=new JLabel();
	int upload=0;


	//JLabel lblFormNo=new JLabel("<html><font color=red>*</font>Form No</html>");
	JLabel lblUnion=new JLabel("Union");
	JLabel lblFatherName=new JLabel("Father Name");
	JLabel lblShareValue=new JLabel("Share Value");
	JLabel lblSl=new JLabel("<html><font color=red>*</font>SL</html>");
	JLabel lblName=new JLabel("<html><font color=red>*</font>Name</html>");
	JLabel lblDesignation=new JLabel("<html><font color=red>*</font>Designation</html>");
	//JLabel lblReligion=new JLabel("<html><font color=red>*</font>Religion</html>");
	JLabel lblGender=new JLabel("<html><font color=red>*</font>Gender</html>");
	//JLabel lblNationality=new JLabel("Nationality");
	JLabel lblNationalId=new JLabel("National ID");
	JLabel lblPresentAddress=new JLabel("Present Address");
	JLabel lblEmail=new JLabel("Email");
	JLabel lblMotherName=new JLabel("Mother Name");
	JLabel lblSpouseName=new JLabel("Spouse Name");
	JLabel lblDateOfbirth=new JLabel("Date Of Birth");
	JLabel lblUnitQty=new JLabel("Unit Quantity");
	JLabel lblNorminy=new JLabel("Nominy");
	JLabel lblRelation=new JLabel("Relation");
	JLabel lblProfession=new JLabel("Professsion");

	//JLabel lblTelephone=new JLabel("Telphone");
	JLabel lblMobile=new JLabel("<html><font color=red>*</font>Mobile</html>");
	JLabel lblParmanentAddress=new JLabel("Parmanent");
	
	JTextField txtSl=new JTextField(17);
	public JTextField txtName=new JTextField(17);



	String designation[]={"","Director","Share Holder"};
	JComboBox cmbDesignation=new JComboBox(designation);

	//	String religion[]={"","Islam","Hindu","Buddha","Christian"};
	//	JComboBox cmbReligion=new JComboBox(religion);

	String gender[]={"Male","Female","Other"};
	JComboBox cmbgender=new JComboBox(gender);

	//JTextField txtNationality=new JTextField(17);
	JTextField txtNationalId=new JTextField(17);
	JTextField txtEmail=new JTextField(17);
	JTextField txtMobile=new JTextField(17);
	JTextField txtMotherName=new JTextField(17);
	JTextField txtSpouseName=new JTextField(17);
	JTextField txtUnitQuantity=new JTextField(17);
	JTextField txtNominy=new JTextField(17);
	JTextField txtRelation=new JTextField(17);
	JTextField txtProfession=new JTextField(17);
	
	JDateChooser dateOfBirth=new JDateChooser();
	//JTextField txtTelephone=new JTextField(17);

	//JTextField txtFormNo=new JTextField(17);
	JTextField txtFatherName=new JTextField(17);
	JTextField txtShareValue=new JTextField(17);

	SuggestText cmbUnion=new SuggestText();
	SuggestText cmbSearchName=new SuggestText();

	JTextArea txtPresentAddress=new JTextArea(3,2);
	JTextArea txtParmanentAddress=new JTextArea(3,2);


	JScrollPane txtParmanentscroll=new JScrollPane(txtParmanentAddress,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JScrollPane txtPresentscroll=new JScrollPane(txtPresentAddress,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	String cardType[]={};
	JComboBox cmbCarType=new JComboBox(cardType);
	JDateChooser txtDate=new JDateChooser();
	JCheckBox checkAsc=new JCheckBox("Assecding");
	JCheckBox checkDsc=new JCheckBox("Dessecding");
	ButtonGroup gp=new ButtonGroup();
	String col[]={"   SL#","        Name","      Type","       Mobile No","            Address"};
	Object row[][]={};
	DefaultTableModel model=new DefaultTableModel(row,col);
	JTable table=new JTable(model){
		@Override
		public boolean isCellEditable(int row,int col){
			col=convertColumnIndexToModel(col);
			row=convertRowIndexToModel(row);
			if(col==5){
				return false;
			}
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
	JScrollPane scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	GridBagConstraints grid=new GridBagConstraints();

	JButton btnFine=new JButton("Find",new ImageIcon("icon/find.png"));
	JButton btnUpload=new JButton("Upload");
	JButton btnPrint=new JButton("Print Share Holder List",new ImageIcon("icon/print.png"));
	JButton btnPrintProfile=new JButton("Print Profile",new ImageIcon("icon/print.png"));
	JButton btnSave=new JButton("Save",new ImageIcon("icon/save.png"));
	JButton btnEdit=new JButton("Edit",new ImageIcon("icon/edt.png"));
	JButton btnReset=new JButton("Reset",new ImageIcon("icon/reresh.png"));
	String startDate="",ledgerName="",Sl="";
	int ledgerId=0;
	BufferedImage image;
	public ManagementInformation(SessionBeam sessionbeam) {
		this.sessionbeam=sessionbeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addcmp();
		date_take();
		btnAction();
		autoId();
		autoLedgerId();
		ViewTableData();
		CTxtEvent();
		loadManagementName();
		cmbUnionLoad();
		photoSet();
		background();
	}
	private void cmbUnionLoad() {
		try {
			cmbUnion.v.clear();
			cmbUnion.v.add("");
			ResultSet rs=db.sta.executeQuery("select [union] from tbmanagementinfo where [union] != 'null' group by [union];");
			while(rs.next()) {
				cmbUnion.v.add(rs.getString("union"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
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
	private void CTxtEvent(){
		/*		txtContactNumber.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c!= '+') {
					getToolkit().beep();
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}		
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		txtSupplierName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtContactNumber.requestFocusInWindow();
			}
		});
		txtContactNumber.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtEmail.requestFocusInWindow();
			}
		});
		txtEmail.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtAddress.requestFocusInWindow();
			}
		});
		txtAddress.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					txtOpeningBalance.requestFocusInWindow();
				}
			}
		});*/


	}
	private void btnAction(){
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				try {
					System.out.println("select *from tbmanagementinfo where iSl='"+table.getValueAt(table.getSelectedRow(), 0)+"'");
					ResultSet rs=db.sta.executeQuery("select *from tbmanagementinfo where iSl='"+table.getValueAt(table.getSelectedRow(), 0)+"'");
					while(rs.next()){
						txtSl.setText(rs.getString("iSl"));
						txtName.setText(rs.getString("vName"));
						cmbDesignation.setSelectedItem(rs.getString("vDesignation"));
						//cmbReligion.setSelectedItem(rs.getString("vReglion"));
						cmbgender.setSelectedItem(rs.getString("vGender"));
						//txtNationality.setText(rs.getString("vNationality"));
						txtNationalId.setText(rs.getString("vNationalId"));
						txtPresentAddress.setText(rs.getString("tPresentAddress"));
						txtParmanentAddress.setText(rs.getString("tParmanentAddress"));
						txtEmail.setText(rs.getString("vEmail"));
						//txtTelephone.setText(rs.getString("vTelePhone"));
						txtMobile.setText(rs.getString("vMobile"));

						cmbUnion.txtMrNo.setText(rs.getString("union"));
						
						txtFatherName.setText(rs.getString("fatherName"));
						txtShareValue.setText(rs.getString("shareValue"));
						txtMotherName.setText(rs.getString("motherName"));
						txtSpouseName.setText(rs.getString("spouseName"));
						txtUnitQuantity.setText(rs.getString("unitQuantity"));
						txtNominy.setText(rs.getString("Nominy"));
						txtRelation.setText(rs.getString("Relation"));
						txtProfession.setText(rs.getString("Profession"));
						dateOfBirth.setDate(rs.getDate("dateOfBirth"));


						Blob immAsBlob = rs.getBlob("photo");
						byte[] immAsBytes = immAsBlob.getBytes(1, (int)immAsBlob.length());
						InputStream in = new ByteArrayInputStream(immAsBytes);
						buffer = ImageIO.read(in);
						lblPhotoSet();
					}

				} catch (Exception e) {
					photoSet();
					e.printStackTrace();
				}
			}
		});

		btnUpload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				uploadAction();
			}
		});

		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SaveBtnEvent();
			}
		});
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EditBtnEvent();
			}
		});
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				autoId();
				txtClear();
				ViewTableData();
			}
		});

		btnFine.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnFineEvent();
			}
		});
		checkAsc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(checkAsc.isSelected()){
					for(int a=table.getRowCount()-1;a>=0;a--){
						model.removeRow(a);
					}
					try {
						ResultSet rs=db.sta.executeQuery("select *from tbmanagementinfo order by tbmanagementinfo.iSl,tbmanagementinfo.vName asc");
						while(rs.next()){
							model.addRow(new Object[]{rs.getString("iSl"),rs.getString("vName"),rs.getString("vDesignation"),rs.getString("vmobile"),rs.getString("tPresentAddress")});
						}
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
					}
					for(int a=0;a<20;a++){
						model.addRow(new Object[]{"","","","",""});
					}
				}
			}
		});
		checkDsc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(checkDsc.isSelected()){
					for(int a=table.getRowCount()-1;a>=0;a--){
						model.removeRow(a);
					}
					try {
						ResultSet rs=db.sta.executeQuery("select *from tbmanagementinfo order by tbmanagementinfo.iSl desc");
						while(rs.next()){
							model.addRow(new Object[]{rs.getString("iSl"),rs.getString("vName"),rs.getString("vDesignation"),rs.getString("vmobile"),rs.getString("tPresentAddress")});
						}
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
					}
					for(int a=0;a<20;a++){
						model.addRow(new Object[]{"","","","",""});
					}
				}
			}
		});
		btnPrint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String query="select *from tbmanagementinfo order by [union],iSl";
				allManagementList("MisReport/ManagementList.jrxml",query);
			}
		});
		
		btnPrintProfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				map= new HashMap<>();
				String query="select * from tbmanagementinfo where iSl = '"+txtSl.getText().trim()+"';";
				try {
					logoImage = ImageIO.read(getClass().getResource("/loginlogo.jpg"));
					map.put("logoImage", logoImage);
					
					logoImage = ImageIO.read(getClass().getResource("/loginlogo_opacity.png"));
					map.put("watermark", logoImage);
					
					map.put("orgName",sessionbeam.getOrgName());
					map.put("orgAddress",sessionbeam.getOrgAddress());
					map.put("orgMobile",sessionbeam.getOrgMobile());
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				allManagementList("MisReport/ManagementHolderProfile.jrxml",query,map);
			}
		});


		txtShareValue.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c!= '.') {
					getToolkit().beep();
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}		
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		txtMobile.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c!= '.') {
					getToolkit().beep();
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}		
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		
		
		txtUnitQuantity.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c!= '.') {
					getToolkit().beep();
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}		
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});


	}
	protected void uploadAction() {
		try {

			fileChooser =new JFileChooser();
			FileNameExtensionFilter filter=new FileNameExtensionFilter("Photo Format","gif","png","jpg","jpeg");
			fileChooser.setFileFilter(filter);
			fileChooser.showOpenDialog(this);
			file =new File("");
			file=fileChooser.getSelectedFile();
			photoSet();
			
		}catch(Exception e) {
			file = new File("");
			photoSet();
		}

	}
	private void photoSet() {

		try {
			buffer = ImageIO.read(getClass().getResource("/defaultPhoto.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(file.exists()) {
			try {
				buffer = ImageIO.read(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		lblPhotoSet();
	}
	private void lblPhotoSet() {

		lblPhoto.setPreferredSize(new Dimension(150,150));
		Image size=(Image)buffer;
		Image resize=size.getScaledInstance(150,150,Image.SCALE_DEFAULT);
		lblPhoto.setIcon(new ImageIcon(resize));

		file = new File("");

	}
	private void btnFineEvent() {
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			if(!cmbSearchName.txtMrNo.getText().trim().toString().isEmpty()){
				ResultSet rs=db.sta.executeQuery("select * from tbmanagementinfo where vName='"+cmbSearchName.txtMrNo.getText().trim().toString()+"'");
				if(rs.next()){
					txtSl.setText(rs.getString("iSl"));
					txtName.setText(rs.getString("vName"));
					cmbDesignation.setSelectedItem(rs.getString("vDesignation"));
					//cmbReligion.setSelectedItem(rs.getString("vReglion"));
					cmbgender.setSelectedItem(rs.getString("vGender"));
					//txtNationality.setText(rs.getString("vNationality"));
					txtNationalId.setText(rs.getString("vNationalId"));
					txtPresentAddress.setText(rs.getString("tPresentAddress"));
					txtParmanentAddress.setText(rs.getString("tParmanentAddress"));
					txtEmail.setText(rs.getString("vEmail"));
					//txtTelephone.setText(rs.getString("vTelePhone"));
					txtMobile.setText(rs.getString("vMobile"));
					cmbUnion.txtMrNo.setText(rs.getString("union"));
					
					txtFatherName.setText(rs.getString("fatherName"));
					txtShareValue.setText(rs.getString("shareValue"));
					txtMotherName.setText(rs.getString("motherName"));
					txtSpouseName.setText(rs.getString("spouseName"));
					txtUnitQuantity.setText(rs.getString("unitQuantity"));
					txtNominy.setText(rs.getString("Nominy"));
					txtRelation.setText(rs.getString("Relation"));
					txtProfession.setText(rs.getString("Profession"));
					dateOfBirth.setDate(rs.getDate("dateOfBirth"));



					model.addRow(new Object[]{rs.getString("iSl"),rs.getString("vName"),rs.getString("vDesignation"),rs.getString("vMobile"),rs.getString("tPresentAddress")});

					Blob immAsBlob = rs.getBlob("photo");
					byte[] immAsBytes = immAsBlob.getBytes(1, (int)immAsBlob.length());
					InputStream in = new ByteArrayInputStream(immAsBytes);
					buffer = ImageIO.read(in);
					lblPhotoSet();


				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Name");
			}
			for(int a=0;a<20;a++){
				model.addRow(new Object[]{"","","","",""});
			}
		} catch (Exception e) {
			photoSet();
			e.printStackTrace();
			//JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	public void loadManagementName(){
		try {
			cmbSearchName.v.clear();
			cmbSearchName.v.add("");
			ResultSet rs=db.sta.executeQuery("select  tbmanagementinfo.vName from tbmanagementinfo order by tbmanagementinfo.vName ");
			while(rs.next()){
				cmbSearchName.v.add(rs.getString("vName"));
			}
		} catch (Exception e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}

	private void ViewTableData(){
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select *from tbmanagementinfo ");
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("iSl"),rs.getString("vName"),rs.getString("vDesignation"),rs.getString("vMobile"),rs.getString("tPresentAddress")});
			}
			for(int a=0;a<20;a++){
				model.addRow(new Object[]{"","","","",""});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private boolean checkValidation(){
		
			if(!txtName.getText().trim().toString().isEmpty()){
				if(cmbDesignation.getSelectedIndex()>0){
					if((cmbDesignation.getSelectedIndex()==2?!txtShareValue.getText().trim().isEmpty():true)){
						if(dateOfBirth.getDate()!=null){
							if(!txtMobile.getText().trim().toString().isEmpty()){
								return true;
							}
							else{
								JOptionPane.showMessageDialog(null, "Warrning!!, Please Provide Mobile");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Warrning!!, Please Provide Date Of Birth");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Warrning!!, Please Provide Share Value");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warrning!!, Please Provide Designation");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warrning!!, Please Provide Name");
			}
		
		
		return false;
	}
	private void SaveBtnEvent(){
		if(!duplicateIdCheck()) {
			if(checkValidation()){
				if(!duplicateNameCheck()){
					try {
						int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Save These Information","Confrimm...",JOptionPane.YES_NO_OPTION);
						if(confrim==JOptionPane.YES_OPTION){
							autoId();

							//imageWrite();
							//imageByteSet();

							//

							ByteArrayOutputStream baos = new ByteArrayOutputStream();
							//use another encoding if JPG is innappropriate for you
							ImageIO.write(buffer, "jpg", baos );
							baos.flush();
							byte[] immAsBytes = baos.toByteArray();
							baos.close();


							String sql="insert into tbmanagementinfo values('"+txtSl.getText().trim()+"','"+txtName.getText().trim()+"','"+cmbDesignation.getSelectedItem()+"','','"+cmbgender.getSelectedItem().toString()+"','','"+txtNationalId.getText().trim()+"','"+txtPresentAddress.getText()+"','"+txtParmanentAddress.getText().trim()+"','"+txtEmail.getText().trim()+"','','"+txtMobile.getText().trim()+"',CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"','','"+cmbUnion.txtMrNo.getText()+"','"+txtFatherName.getText().trim()+"','"+txtShareValue.getText().trim()+"','','"+txtMotherName.getText().trim()+"','"+txtSpouseName.getText().trim()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(dateOfBirth.getDate())+"','"+txtUnitQuantity.getText().trim()+"','"+txtNominy.getText().trim()+"','"+txtRelation.getText().trim()+"','"+txtProfession.getText().trim()+"');";
							System.out.println(sql);
							db.sta.executeUpdate(sql);


							//java.sql.PreparedStatement pstmt = db.con.prepareStatement("insert into tbmanagementinfo values('"+txtSl.getText().trim().toString()+"','"+txtName.getText().trim().toString()+"','"+cmbDesignation.getSelectedItem().toString()+"','','"+cmbgender.getSelectedItem().toString()+"','','"+txtNationalId.getText().trim().toString()+"','','"+txtAddress.getText().trim().toString()+"','"+txtEmail.getText().trim().toString()+"','','"+txtMobile.getText().trim().toString()+"',CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"','"+txtFormNo.getText().trim()+"','"+cmbUnion.txtMrNo.getText()+"','"+txtFatherName.getText().trim()+"','"+txtShareValue.getText()+"',?)");
							java.sql.PreparedStatement pstmt=db.con.prepareStatement("update tbmanagementinfo set Photo = ? where iSl = '"+txtSl.getText().trim()+"';");
							ByteArrayInputStream bais = new ByteArrayInputStream(immAsBytes);
							//pstmt.setString(1, txtSl.getText().trim());
							pstmt.setBinaryStream(1, bais, immAsBytes.length);
							pstmt.executeUpdate();
							pstmt.close();

							//						String sql="insert into tbmanagementinfo values('"+txtSl.getText().trim().toString()+"','"+txtName.getText().trim().toString()+"','"+cmbDesignation.getSelectedItem().toString()+"','','"+cmbgender.getSelectedItem().toString()+"','','"+txtNationalId.getText().trim().toString()+"','','"+txtAddress.getText().trim().toString()+"','"+txtEmail.getText().trim().toString()+"','','"+txtMobile.getText().trim().toString()+"',CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"','"+txtFormNo.getText().trim()+"','"+cmbUnion.txtMrNo.getText()+"','"+txtFatherName.getText().trim()+"','"+txtShareValue.getText()+"',?)";
							//						System.out.println(sql);
							//						db.sta.executeUpdate(sql);
							JOptionPane.showMessageDialog(null, "Management Information Save Succesfully!!");
							ViewTableData();
							loadManagementName();
						}
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Doplicate Name Entry Cab't Be Possible");
				}
			}
		}else {
			JOptionPane.showMessageDialog(null, "This id is Already Exist","Please Change ID",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void imageByteSet() {
		try {
			//byte[] immAsBytes;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			//use another encoding if JPG is innappropriate for you
			ImageIO.write(buffer, "jpg", baos );
			baos.flush();
			immAsBytes = baos.toByteArray();
			baos.close();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}
	}
	//	private void imageWrite() {
	//		try {
	//			File file2=new File("E://ManagementInformation//Photo");
	//			if(!file2.isDirectory()){
	//				file2.mkdirs();
	//			}
	//			buffer=ImageIO.read(file);
	//			File fileToWrite=new File(file2.getPath()+"\\"+txtSl.getText().trim()+".jpg");
	//
	//			if(fileToWrite.exists()){
	//				fileToWrite.delete();
	//			}
	//
	//			ImageIO.write(buffer, "jpg", fileToWrite);
	//		}catch(Exception e){
	//
	//		}
	//
	//	}
	private void EditBtnEvent(){
		if(duplicateIdCheck()) {
			if(checkValidation()){
				if(duplicateNameCheck()) {
					try {
						int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Edit These Information","Confrimm...",JOptionPane.YES_NO_OPTION);
						if(confrim==JOptionPane.YES_OPTION){


							String sql="update tbmanagementinfo set vName='"+txtName.getText().trim().toString()+"',vDesignation='"+cmbDesignation.getSelectedItem().toString()+"',vReglion='',vGender='"+cmbgender.getSelectedItem().toString()+"',vNationality='',vNationalId='"+txtNationalId.getText().trim().toString()+"',tPresentAddress='"+txtPresentAddress.getText().trim()+"',tParmanentAddress='"+txtParmanentAddress.getText().trim()+"',vEmail='"+txtEmail.getText().trim().toString()+"',vTelePhone='',vMobile='"+txtMobile.getText().trim().toString()+"',dEntryTime=CURRENT_TIMESTAMP,vUserId='"+sessionbeam.getUserId()+"',fromNo='',[union]='"+cmbUnion.txtMrNo.getText().trim()+"',fatherName='"+txtFatherName.getText().trim()+"',shareValue='"+txtShareValue.getText().trim()+"',motherName='"+txtMotherName.getText().trim()+"',spouseName='"+txtSpouseName.getText().trim()+"',dateOfBirth='"+new SimpleDateFormat("yyyy-MM-dd").format(dateOfBirth.getDate())+"',unitQuantity='"+txtUnitQuantity.getText().trim()+"',Nominy='"+txtNominy.getText().trim()+"',Relation='"+txtRelation.getText().trim()+"',Profession='"+txtProfession.getText().trim()+"'  where iSl='"+txtSl.getText().trim().toString()+"'";
							System.out.println(sql);
							db.sta.executeUpdate(sql);

							ByteArrayOutputStream baos = new ByteArrayOutputStream();
							//use another encoding if JPG is innappropriate for you
							ImageIO.write(buffer, "jpg", baos );
							baos.flush();
							byte[] immAsBytes = baos.toByteArray();
							baos.close();

							java.sql.PreparedStatement pstmt=db.con.prepareStatement("update tbmanagementinfo set Photo = ? where iSl = '"+txtSl.getText().trim()+"';");
							ByteArrayInputStream bais = new ByteArrayInputStream(immAsBytes);
							//pstmt.setString(1, txtSl.getText().trim());
							pstmt.setBinaryStream(1, bais, immAsBytes.length);
							pstmt.executeUpdate();
							pstmt.close();

							JOptionPane.showMessageDialog(null, "Management Information Edit Succesfully!!");
							ViewTableData();
							txtClear();
							loadManagementName();

						}
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
					}
				}else {
					JOptionPane.showMessageDialog(null, "This Name is All ready Exist","Sorry..",JOptionPane.WARNING_MESSAGE);
				}
			}

		}else {
			JOptionPane.showMessageDialog(null, "This id is Invalid!!", "Sorry..", JOptionPane.WARNING_MESSAGE);
		}


	}

	private void DeleteBtnEvent(){

		if(checkValidation()){
			if(duplicateIdCheck()){
				try {
					int confrim=JOptionPane.showConfirmDialog(null, "Are Your Sure To Delete Management Information","Confrim",JOptionPane.YES_OPTION);
					if(confrim==JOptionPane.YES_OPTION){
						String sql="delete from  tbmanagementinfo where iSl='"+txtSl.getText().toString()+"'";
						System.out.println(sql);
						db.sta.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Management Information Delete Successfully");
						txtClear();
						autoId();
						ViewTableData();
						loadManagementName();
					}
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error"+e);
				}

			}
			else{
				JOptionPane.showMessageDialog(null, "Warrning!!,Invalid Supplier Id!!");
			}
		}
	}
	private boolean duplicateNameCheck() {
		try {
			ResultSet rs=db.sta.executeQuery("select vname from tbmanagementinfo where vName='"+txtName.getText().trim()+"';");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return false;	
	}
	private boolean duplicateIdCheck(){
		try {
			ResultSet rs=db.sta.executeQuery("select vname from tbmanagementinfo where isl='"+txtSl.getText().trim()+"';");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return false;	
	}
	private void autoId(){
		try {
			String sql="select (ISNULL(max(iSl),0)+1)as iSl from tbmanagementinfo";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				Sl=rs.getString("iSl");
				txtSl.setText(rs.getString("iSl"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error!"+e);
		}
	}
	public void autoLedgerId(){
		try {
			String sql="select (ISNULL(max(ledgerId),0)+1)as ledgerId from accfledger";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				ledgerId=Integer.parseInt(rs.getString("ledgerId"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error!"+e);
		}
	}
	private void allManagementList(String report,String query,HashMap map){
		try {
			
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			
			System.out.println(query);
			jq.setText(query);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, map,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!"+e);
		}
	}
	private void allManagementList(String report,String query){
		try {
			map=new HashMap<>();
			
			System.out.println(sessionbeam.getOrgName()+" "+sessionbeam.getOrgAddress());
			map.put("orgName",sessionbeam.getOrgName());
			map.put("orgAddress",sessionbeam.getOrgAddress());
			map.put("orgMobile",sessionbeam.getOrgMobile());
			
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			
			System.out.println(query);
			jq.setText(query);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, map,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!"+e);
		}
	}
	private void txtClear(){
		txtName.setText("");
		cmbDesignation.setSelectedItem("");
		txtEmail.setText("");
		//cmbReligion.setSelectedItem("");
		cmbgender.setSelectedItem("");
		//txtNationality.setText("");
		txtNationalId.setText("");
		txtPresentAddress.setText("");
		txtParmanentAddress.setText("");
		txtMobile.setText("");
		//txtTelephone.setText("");
		
		cmbUnion.txtMrNo.setText("");
		txtFatherName.setText("");
		txtShareValue.setText("");
		txtMotherName.setText("");
		txtSpouseName.setText("");
		dateOfBirth.setDate(new Date());
		txtUnitQuantity.setText("");
		txtNominy.setText("");
		txtRelation.setText("");
		txtProfession.setText("");

		file=new File("");
		photoSet();
	}
	public void date_take(){
		DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date=new Date();
		startDate =dateformat.format(date).toString();
	}
	private void addcmp() {
		add(mainPanel);
		setOpaque(false);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(1280,670));
		mainPanel.add(westPanel,BorderLayout.CENTER);
		mainPanel.add(eastPanel,BorderLayout.EAST);
		mainPanel.setOpaque(false);
		eastPanel.setOpaque(false);
		westPanel_work();
		eastPanel_work();
	}
	private void westPanel_work() {
		westPanel.setLayout(new BorderLayout());
		westPanel.setPreferredSize(new Dimension(640,690));
		TitledBorder titlemain=BorderFactory.createTitledBorder("Share Holder Create");
		titlemain.setTitleJustification(titlemain.CENTER);
		westPanel.setBorder(titlemain);
		westPanel.setOpaque(false);
		westPanel.add(westNorthPanel,BorderLayout.NORTH);
		westPanel.add(westCenterPanel,BorderLayout.CENTER);
		westPanel.add(westEastPanel,BorderLayout.EAST);
		westPanel.add(westSouthPanel,BorderLayout.SOUTH);
		westNorthPanel_work();
		westCenterPanel_work();
		westEastPanel_work();
		westSouthPanel_work();
	}

	private void westNorthPanel_work() {
		westNorthPanel.setLayout(new FlowLayout());
		westNorthPanel.setPreferredSize(new Dimension(100,50));
		//westNorthPanel.setBorder(BorderFactory.createEmptyBorder(50,20,20,20));

		westNorthPanel.add(lblSearchName);
		lblSearchName.setFont(new Font("arial", Font.BOLD, 12));

		westNorthPanel.add(cmbSearchName.combo);
		cmbSearchName.combo.setPreferredSize(new Dimension(175, 30));

		westNorthPanel.add(btnFine);
		westNorthPanel.setOpaque(false);
		btnFine.setPreferredSize(new Dimension(85, 36));
		btnFine.setMnemonic(KeyEvent.VK_F);
	}
	private void westEastPanel_work() {
		westEastPanel.setPreferredSize(new Dimension(295, 0));
		westEastPanel.setOpaque(false);
		westEastPanel.setLayout(new BorderLayout());
		westEastPanel.add(westEastNorthPanel,BorderLayout.NORTH);
		westEastPanel.add(westEastCenterPanel,BorderLayout.CENTER);
		
		westEastCenterPanelWork();
		westEastNorthPanelWork();
		
	}
	private void westEastCenterPanelWork() {
		westEastCenterPanel.setPreferredSize(new Dimension(500,110));
		westEastCenterPanel.setLayout(new GridBagLayout());
		westEastCenterPanel.setOpaque(false);
		
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(2, 2, 2, 2);
		westEastCenterPanel.add(lblParmanentAddress,grid);
		lblParmanentAddress.setFont(new Font("arial",Font.BOLD,13));
		
		grid.gridx=1;
		grid.gridy=0;
		westEastCenterPanel.add(txtParmanentscroll,grid);
		txtParmanentAddress.setFont(new Font("arial",Font.BOLD,14));
		//txtParmanentscroll.setPreferredSize(new Dimension(120, 25));
		txtParmanentscroll.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		
		
		grid.gridx=0;
		grid.gridy=1;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(2, 2, 2, 2);
		westEastCenterPanel.add(lblEmail,grid);
		lblEmail.setFont(new Font("arial",Font.BOLD,13));
		
		grid.gridx=1;
		grid.gridy=1;
		westEastCenterPanel.add(txtEmail,grid);
		txtEmail.setFont(new Font("arial",Font.BOLD,14));
		txtEmail.setPreferredSize(new Dimension(120, 25));
		txtEmail.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		
		
		grid.gridx=0;
		grid.gridy=2;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(2, 2, 2, 2);
		westEastCenterPanel.add(lblNationalId,grid);
		lblNationalId.setFont(new Font("arial",Font.BOLD,13));
		
		grid.gridx=1;
		grid.gridy=2;
		westEastCenterPanel.add(txtNationalId,grid);
		txtNationalId.setFont(new Font("arial",Font.BOLD,14));
		txtNationalId.setPreferredSize(new Dimension(120, 25));
		txtNationalId.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		
		
		grid.gridx=0;
		grid.gridy=3;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(2, 2, 2, 2);
		westEastCenterPanel.add(lblGender,grid);
		lblGender.setFont(new Font("arial",Font.BOLD,13));
		
		grid.gridx=1;
		grid.gridy=3;
		westEastCenterPanel.add(cmbgender,grid);
		cmbgender.setSelectedIndex(0);
		cmbgender.setFont(new Font("arial",Font.BOLD,14));
		cmbgender.setPreferredSize(new Dimension(120, 25));
		cmbgender.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		
		
		grid.gridx=0;
		grid.gridy=4;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(2, 2, 2, 2);
		westEastCenterPanel.add(lblShareValue,grid);
		lblShareValue.setFont(new Font("arial",Font.BOLD,13));
		
		grid.gridx=1;
		grid.gridy=4;
		westEastCenterPanel.add(txtShareValue,grid);
		txtShareValue.setFont(new Font("arial",Font.BOLD,14));
		txtShareValue.setPreferredSize(new Dimension(120, 25));
		txtShareValue.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		
		
		grid.gridx=0;
		grid.gridy=5;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(2, 2, 34, 2);
		westEastCenterPanel.add(lblRelation,grid);
		lblRelation.setFont(new Font("arial",Font.BOLD,13));
		
		grid.gridx=1;
		grid.gridy=5;
		westEastCenterPanel.add(txtRelation,grid);
		txtRelation.setFont(new Font("arial",Font.BOLD,14));
		txtRelation.setPreferredSize(new Dimension(120, 25));
		txtRelation.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		
		
		
		
		
	}
	private void westEastNorthPanelWork() {
		westEastNorthPanel.setPreferredSize(new Dimension(100, 240));
		westEastNorthPanel.setOpaque(false);
		westEastNorthPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 50));

		lblPhoto.setPreferredSize(new Dimension(150, 150));
		lblPhoto.setBorder(BorderFactory.createLineBorder(Color.black));
		westEastNorthPanel.add(lblPhoto);

		westEastNorthPanel.add(btnUpload);
	}
	private void westCenterPanel_work() {
		westCenterPanel.setPreferredSize(new Dimension(500,110));
		westCenterPanel.setLayout(new GridBagLayout());
		westCenterPanel.setOpaque(false);
		
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(2, 2, 2, 2);
		westCenterPanel.add(lblSl,grid);
		lblSl.setFont(new Font("arial",Font.BOLD,13));
		
		grid.gridx=1;
		grid.gridy=0;
		westCenterPanel.add(txtSl,grid);
		txtSl.setFont(new Font("arial",Font.BOLD,14));
		txtSl.setPreferredSize(new Dimension(120, 25));
		txtSl.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		txtSl.setEditable(false);

		grid.gridx=0;
		grid.gridy=1;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(2, 2, 2, 2);
		westCenterPanel.add(lblName,grid);
		lblName.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=1;
		westCenterPanel.add(txtName,grid);
		txtName.setFont(new Font("arial",Font.BOLD,14));
		txtName.setPreferredSize(new Dimension(120, 25));
		txtName.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));


		grid.gridx=0;
		grid.gridy=2;
		westCenterPanel.add(lblFatherName,grid);
		lblFatherName.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=2;
		westCenterPanel.add(txtFatherName,grid);
		txtFatherName.setFont(new Font("arial",Font.BOLD,14));
		txtFatherName.setPreferredSize(new Dimension(120, 25));
		txtFatherName.requestFocus();
		txtFatherName.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));


		grid.gridx=0;
		grid.gridy=3;
		westCenterPanel.add(lblMotherName,grid);
		lblMotherName.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=3;
		westCenterPanel.add(txtMotherName,grid);
		txtMotherName.setFont(new Font("arial",Font.BOLD,14));
		txtMotherName.setPreferredSize(new Dimension(120, 25));
		txtMotherName.requestFocus();
		txtMotherName.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));


		grid.gridx=0;
		grid.gridy=4;
		westCenterPanel.add(lblSpouseName,grid);
		lblSpouseName.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=4;
		westCenterPanel.add(txtSpouseName,grid);
		txtSpouseName.setFont(new Font("arial",Font.BOLD,14));
		txtSpouseName.setPreferredSize(new Dimension(120, 25));
		txtSpouseName.requestFocus();
		txtSpouseName.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		
		
		grid.gridx=0;
		grid.gridy=5;
		westCenterPanel.add(lblDateOfbirth,grid);
		lblDateOfbirth.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=5;
		westCenterPanel.add(dateOfBirth,grid);
		dateOfBirth.setFont(new Font("arial",Font.BOLD,14));
		dateOfBirth.setPreferredSize(new Dimension(120, 25));
		dateOfBirth.requestFocus();
		dateOfBirth.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));


		grid.gridx=0;
		grid.gridy=6;
		westCenterPanel.add(lblUnion,grid);
		lblUnion.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=6;
		westCenterPanel.add(cmbUnion.combo,grid);
		cmbUnion.txtMrNo.setFont(new Font("arial",Font.BOLD,14));
		cmbUnion.combo.setPreferredSize(new Dimension(120, 25));
		cmbUnion.txtMrNo.requestFocus();
		cmbUnion.combo.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));

		grid.gridx=0;
		grid.gridy=7;
		westCenterPanel.add(lblPresentAddress,grid);
		lblPresentAddress.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=7;
		westCenterPanel.add(txtPresentscroll,grid);
		
		txtPresentAddress.setFont(new Font("arial",Font.BOLD,14));
		txtPresentscroll.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));

		grid.gridx=0;
		grid.gridy=8;
		westCenterPanel.add(lblMobile,grid);
		lblMobile.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=8;
		westCenterPanel.add(txtMobile,grid);
		txtMobile.setFont(new Font("arial",Font.BOLD,14));
		txtMobile.setPreferredSize(new Dimension(120, 25));
		txtMobile.requestFocus();
		txtMobile.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		

		grid.gridx=0;
		grid.gridy=9;
		westCenterPanel.add(lblProfession,grid);
		lblProfession.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=9;
		westCenterPanel.add(txtProfession,grid);
		txtProfession.setFont(new Font("arial",Font.BOLD,14));
		txtProfession.setPreferredSize(new Dimension(120, 25));
		txtProfession.requestFocus();
		txtProfession.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));

		grid.gridx=0;
		grid.gridy=10;
		westCenterPanel.add(lblDesignation,grid);
		lblDesignation.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=10;
		westCenterPanel.add(cmbDesignation,grid);
		cmbDesignation.setFont(new Font("arial",Font.BOLD,14));
		cmbDesignation.setPreferredSize(new Dimension(120, 25));
		cmbDesignation.requestFocus();
		cmbDesignation.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));

		grid.gridx=0;
		grid.gridy=11;
		westCenterPanel.add(lblUnitQty,grid);
		lblUnitQty.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=11;
		westCenterPanel.add(txtUnitQuantity,grid);
		txtUnitQuantity.setFont(new Font("arial",Font.BOLD,14));
		txtUnitQuantity.setPreferredSize(new Dimension(120, 25));
		txtUnitQuantity.requestFocus();
		txtUnitQuantity.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		
		grid.gridx=0;
		grid.gridy=12;
		westCenterPanel.add(lblNorminy,grid);
		lblNorminy.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=12;
		westCenterPanel.add(txtNominy,grid);
		txtNominy.setFont(new Font("arial",Font.BOLD,14));
		txtNominy.setPreferredSize(new Dimension(120, 25));
		txtNominy.requestFocus();
		txtNominy.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));



		final Component ob[] = {txtName,txtFatherName,txtMotherName,txtSpouseName,dateOfBirth,cmbUnion.combo,txtPresentAddress,txtParmanentAddress,txtMobile,txtEmail,txtProfession,txtNationalId,cmbDesignation,txtUnitQuantity,txtShareValue,txtNominy,txtRelation};	
		new FocusMoveByEnter(ob);
	}
	private void westSouthPanel_work() {


		westSouthPanel.setPreferredSize(new Dimension(100,85));
		westSouthPanel.setOpaque(false);
		FlowLayout flow=new FlowLayout();
		westSouthPanel.setLayout(flow);
		flow.setAlignment(flow.CENTER);
		westSouthPanel.add(btnSave);
		westSouthPanel.add(btnEdit);
		westSouthPanel.add(btnReset);
		westSouthPanel.add(btnPrintProfile);
		btnSave.setPreferredSize(new Dimension(86,36));
		btnEdit.setPreferredSize(new Dimension(86,36));
		btnReset.setPreferredSize(new Dimension(95,36));
		btnPrintProfile.setPreferredSize(new Dimension(120, 36));
		btnSave.setMnemonic(KeyEvent.VK_S);
		btnEdit.setMnemonic(KeyEvent.VK_E);
		btnReset.setMnemonic(KeyEvent.VK_R);
		
	}

	private void eastPanel_work() {
		eastPanel.setLayout(new FlowLayout());
		eastPanel.setPreferredSize(new Dimension(640,630));
		TitledBorder titlemain=BorderFactory.createTitledBorder("Share Holder Details");
		titlemain.setTitleJustification(titlemain.CENTER);
		eastPanel.setBorder(titlemain);
		eastPanel.setOpaque(false);
		eastPanel.add(checkAsc);
		eastPanel.add(checkDsc);
		gp.add(checkAsc);
		gp.add(checkDsc);

		eastPanel.add(btnPrint);
		btnPrint.setPreferredSize(new Dimension(200, 35));
		btnPrint.setMnemonic(KeyEvent.VK_P);
		eastPanel.add(scroll);
		scroll.setPreferredSize(new Dimension(620, 630));
		scroll.setOpaque(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(220);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(220);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.LEFT );
		for(int i=0;i<4;i++){
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		table.setRowHeight(table.getRowHeight() + 12);
		table.setShowGrid(true);
		table.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 13));
	}
}
