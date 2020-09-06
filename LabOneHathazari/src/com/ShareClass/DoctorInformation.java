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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.ShareClass.ButtonColumn;
import com.ShareClass.FocusMoveByEnter;
import com.ShareClass.SessionBeam;
import com.ShareClass.db_coonection;
import com.toedter.calendar.JDateChooser;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class DoctorInformation extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;
	JPanel mainPanel=new JPanel();
	JPanel westPanel=new JPanel();
	JPanel westNorthPanel=new JPanel();
	JPanel westCenterPanel=new JPanel();
	JPanel westSouthPanel=new JPanel();
	JPanel westEastPanel=new JPanel();
	JPanel obPanel=new JPanel();
	JPanel centerPanel=new JPanel();
	JPanel centerSouthPanel=new JPanel();
	JPanel centerCenterPanel=new JPanel();
	JPanel southNorthPanel=new JPanel();
	JPanel southSouthPanel=new JPanel();
	JLabel lbldoctorCode=new JLabel("<html><font color=red>*</font>Code</html> ");
	JLabel lbltype=new JLabel("<html><font color=red>*</font>Type</html> ");
	JLabel lbldoctorname=new JLabel("<html><font color=red>*</font>Name</html> ");
	JLabel lblfathername=new JLabel("Father's Name");
	JLabel lblmothername=new JLabel("Mother's Name");
	JLabel lblsex=new JLabel("Sex");
	JLabel lblReligion=new JLabel("Religion");
	JLabel lblBirthDay=new JLabel("Birth Day");
	JLabel lblNationalId=new JLabel("National Id");
	JLabel lbldegree=new JLabel("Degree");
	JLabel lbldegreefrom=new JLabel("Degree From");
	JLabel lblpassingyear=new JLabel("Passing year");
	JLabel lbljoindate=new JLabel("Join Date");
	JLabel lbldepartment=new JLabel("Department");
	JLabel lblMobile=new JLabel("<html><font color=red>*</font>Mobile</html> ");
	JLabel lblFee=new JLabel("Fee");
	JLabel lblpresentaddress=new JLabel("Present Address");
	JLabel lblpermanent=new JLabel("Permanent Address");
	JLabel lblChember=new JLabel("Chember");
	JLabel lblFloor=new JLabel("Floor No");
	JLabel lblRoom=new JLabel("Room No");
	JLabel lblMrDoctorName=new JLabel("Doctor Name");
	JLabel lblPhoto=new JLabel();
	
	JFileChooser fileChooser;
	File file=new File("");
	
	BufferedImage buffer;
	byte[] immAsBytes;
	
	
	SuggestText cmbDoctorName=new SuggestText();
	
	SuggestText cmbMrDoctorName=new SuggestText();
	JTextArea txtPresentAddress=new JTextArea(2,3);
	JTextArea txtPermanentAddress=new JTextArea(2,3);
	JTextField txtDoctorCode=new JTextField(20);
	JTextField txtDoctorName=new JTextField(20);
	JTextField txtFatherName=new JTextField(20);
	JTextField txtMotherName=new JTextField(20);
	JTextField txtNationalId=new JTextField(20);
	JTextField txtDegree=new JTextField(20);
	JTextField txtDegreeFrom=new JTextField(20);
	JTextField txtPassingYear=new JTextField(20);
	JTextField txtMobile=new JTextField(20);
	JTextField txtFloorNo=new JTextField(20);
	JTextField txtRoomNo=new JTextField(20);
	
	JTextField txtConsultantFee=new JTextField(20);
	JScrollPane PresentAddressScroll=new JScrollPane(txtPresentAddress,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	JScrollPane PermanentScroll=new JScrollPane(txtPermanentAddress,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	String sex[]={"","Male","Female"};
	JComboBox cmbSex=new JComboBox(sex);

	String type[]={"","Referral","Non-Referral","Consultant","Doctor","Marketing","Lab Incharge","Checked By","Village Doctor","Surgeon","AnaesTheist","Assistant","Pathologist","Nurse"};
	JComboBox cmbType=new JComboBox(type);


	String religion[]={"","Islam","Hindo","Boddha","Christian"};
	JComboBox cmbReligion=new JComboBox(religion);

	String department[]={"","",""};
	JComboBox cmbDepartment=new JComboBox(department);

	JDateChooser txtBirthDate=new JDateChooser();
	JDateChooser txtJoinDate=new JDateChooser();

	JButton btnPrint=new JButton("Print Doctor List",new ImageIcon("icon/print.png"));
	JButton btnSubmit=new JButton("Submit",new ImageIcon("icon/save.png"));
	JButton btnFind=new JButton("Find",new ImageIcon("icon/Preview.png"));
	JButton btnRefresh=new JButton("Refresh",new ImageIcon("icon/reresh.png"));
	JButton btnUpload=new JButton("Upload",new ImageIcon("icon/Upload.png"));
	GridBagConstraints grid=new GridBagConstraints();
	String col[]={"  SN  ","             Doctor Name","           Degree","     Mobile","        Type","       Address","Del  "};
	Object row[][]={};
	DefaultTableModel model=new DefaultTableModel(row,col);
	JTable table=new JTable(model){
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
	JScrollPane scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	String autoId="",startDate="";
	int search=0;
	BufferedImage image;
	int ledgerId=0;
	public DoctorInformation(SessionBeam sessionBeam) {
		this.sessionBeam=sessionBeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addCmp();
		btnActionEvent();
		loadTableData();
		loadDepartmentName();
		date_take();
		changeDepartment();
		autoId();
		background();
		LoadOnlyDoctor();
		photoSet();
		LoadDoctor();
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

		lblPhoto.setPreferredSize(new Dimension(135,135));
		Image size=(Image)buffer;
		Image resize=size.getScaledInstance(135,135,Image.SCALE_DEFAULT);
		lblPhoto.setIcon(new ImageIcon(resize));

		file = new File("");

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
	private void changeDepartment(){
		cmbType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(search==0){
					if(cmbType.getSelectedIndex()==6){
						cmbDepartment.removeAllItems();
						cmbDepartment.addItem("");
						cmbDepartment.addItem("Heamatology");
						cmbDepartment.addItem("Bio Chemestry");
						cmbDepartment.addItem("Serology");
						cmbDepartment.addItem("Hormon");
						cmbDepartment.addItem("Urine Re");
						cmbDepartment.addItem("Stoolex");
						cmbDepartment.addItem("Top");
						cmbDepartment.addItem("Micro Biology");
						cmbDepartment.addItem("Cytology");
						cmbDepartment.addItem("Biopsy");
					}
					else{
						//loadDepartmentName();
					}
				}
			}
		});
	}
	public void date_take(){
		DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date=new Date();
		startDate =dateformat.format(date).toString();
	}
	public void loadDepartmentName(){
		try {
			cmbDepartment.removeAllItems();
			cmbDepartment.addItem("");
			ResultSet rs=db.sta.executeQuery("select departmentname from tbdepartment");
			while(rs.next()){
				cmbDepartment.addItem(rs.getString("departmentname"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void btnActionEvent(){
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSubmitEvent();
			}
		});
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtClear();
				loadTableData();
				autoId();
				
			}
		});
		btnPrint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				allDoctorList();
			}
		});
		
		btnUpload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				uploadAction();
			}
		});
		
		/*		txtDoctorCode.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(!txtDoctorCode.getText().toString().isEmpty()){
					try {
						int temp=0;
						ResultSet rs=db.sta.executeQuery("select *from tbdoctorinfo where DoctorCode='"+txtDoctorCode.getText().toString()+"'");
						while(rs.next()){
							txtDoctorCode.setText(rs.getString("DoctorCode"));
							txtDoctorName.setText(rs.getString("Name"));
							txtFatherName.setText(rs.getString("FatherName"));
							txtMotherName.setText(rs.getString("MotherName"));
							cmbType.setSelectedItem(rs.getString("type"));
							cmbReligion.setSelectedItem(rs.getString("Religion"));
							cmbSex.setSelectedItem(rs.getString("Sex"));
							txtBirthDate.setDate(rs.getDate("BirthDay"));
							txtNationalId.setText(rs.getString("NationalId"));
							txtMobile.setText(rs.getString("Mobile"));
							txtConsultantFee.setText(rs.getString("Fee"));
							txtDegree.setText(rs.getString("Degree"));
							txtDegreeFrom.setText(rs.getString("DegreeFrom"));
							txtPassingYear.setText(rs.getString("PassingYear"));
							txtFloorNo.setText(rs.getString("Floor"));
							txtRoomNo.setText(rs.getString("Room"));
							txtJoinDate.setDate(rs.getDate("JoiniDate"));
							txtPresentAddress.setText(rs.getString("PresentAddress"));
							txtPermanentAddress.setText(rs.getString("PermanentAddress"));
							cmbDepartment.setSelectedItem(rs.getString("Department"));
							temp=1;
						}
						if(temp==0){
							txtClear();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, ""+e2);
					}
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});*/
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
				try {
					search=1;
					int row=table.getSelectedRow();
					String sql="select *from tbdoctorinfo where autoId='"+table.getValueAt(row, 0)+"'";
					System.out.println(sql);
					ResultSet rs=db.sta.executeQuery(sql);
					while(rs.next()){
						txtDoctorCode.setText(rs.getString("DoctorCode"));
						txtDoctorName.setText(rs.getString("Name"));
						txtFatherName.setText(rs.getString("FatherName"));
						txtMotherName.setText(rs.getString("MotherName"));
						cmbType.setSelectedItem(rs.getString("type")==null?"":rs.getString("type"));
						cmbReligion.setSelectedItem(rs.getString("Religion")==null?"":rs.getString("Religion"));
						cmbSex.setSelectedItem(rs.getString("Sex")==null?"":rs.getString("Sex"));
						/*if(rs.getString("BirthDay").toString()==""){
							txtBirthDate.setDate(new Date());
						}
						else{
							txtBirthDate.setDate(rs.getDate(new SimpleDateFormat("yyyy-MM-dd").format(rs.getString("BirthDay"))));
						}*/

						txtNationalId.setText(rs.getString("NationalId"));
						txtMobile.setText(rs.getString("Mobile"));
						txtConsultantFee.setText(rs.getString("Fee"));
						txtDegree.setText(rs.getString("Degree"));
						txtDegreeFrom.setText(rs.getString("DegreeFrom"));
						txtPassingYear.setText(rs.getString("PassingYear"));
						txtFloorNo.setText(rs.getString("Floor"));
						txtRoomNo.setText(rs.getString("Room"));
						/*if(rs.getString("JoinDay").toString()==""){
							txtJoinDate.setDate(new Date());
						}
						else{
							txtJoinDate.setDate(rs.getDate(new SimpleDateFormat("yyyy-MM-dd").format(rs.getString("JoinDay"))));
						}*/
						txtPresentAddress.setText(rs.getString("PresentAddress"));
						txtPermanentAddress.setText(rs.getString("ParmanantAddress"));
						cmbDepartment.setSelectedItem(rs.getString("Department")==null?"":rs.getString("Department"));
						
						Blob immAsBlob = rs.getBlob("photo");
						byte[] immAsBytes = immAsBlob.getBytes(1, (int)immAsBlob.length());
						InputStream in = new ByteArrayInputStream(immAsBytes);
						buffer = ImageIO.read(in);
						lblPhotoSet();
					}
					search=0;
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, ""+e2);
				}
			}
		});
		btnFind.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnFindEvent();
			}
		});
	}
	
	private void uploadAction() {
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
	private void btnSubmitEvent() {
		if(!txtDoctorCode.getText().toString().isEmpty()){
			if(!txtDoctorName.getText().toString().isEmpty()){
				if(!txtDegree.getText().trim().toString().isEmpty()){
					if(cmbType.getSelectedIndex()!=0){
						dataEntry();
						LoadOnlyDoctor();
						LoadDoctor();
					}
					else{
						JOptionPane.showMessageDialog(null, "Warning!!,Please Reffer Doctor Type");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Please provide Doctor Degree");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please provide Doctor Name");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please provide Doctor Code");
		}
	}
	private void btnFindEvent(){
		try {
			if(ValidDoctorName()){
				ResultSet rs=db.sta.executeQuery("select * from tbdoctorinfo where Name='"+cmbDoctorName.txtMrNo.getText().trim().toString()+"'");
				while(rs.next()){
					txtDoctorCode.setText(rs.getString("DoctorCode"));
					txtDoctorName.setText(rs.getString("Name"));
					txtFatherName.setText(rs.getString("FatherName"));
					txtMotherName.setText(rs.getString("MotherName"));
					cmbType.setSelectedItem(rs.getString("type")==null?"":rs.getString("type"));
					cmbReligion.setSelectedItem(rs.getString("Religion")==null?"":rs.getString("Religion"));
					cmbSex.setSelectedItem(rs.getString("Sex")==null?"":rs.getString("Sex"));
					/*if(rs.getString("BirthDay").toString()==""){
						txtBirthDate.setDate(new Date());
					}
					else{
						txtBirthDate.setDate(rs.getDate(new SimpleDateFormat("yyyy-MM-dd").format(rs.getString("BirthDay"))));
					}*/

					txtNationalId.setText(rs.getString("NationalId"));
					txtMobile.setText(rs.getString("Mobile"));
					txtConsultantFee.setText(rs.getString("Fee"));
					txtDegree.setText(rs.getString("Degree"));
					txtDegreeFrom.setText(rs.getString("DegreeFrom"));
					txtPassingYear.setText(rs.getString("PassingYear"));
					txtFloorNo.setText(rs.getString("Floor"));
					txtRoomNo.setText(rs.getString("Room"));
					/*if(rs.getString("JoinDay").toString()==""){
						txtJoinDate.setDate(new Date());
					}
					else{
						txtJoinDate.setDate(rs.getDate(new SimpleDateFormat("yyyy-MM-dd").format(rs.getString("JoinDay"))));
					}*/
					txtPresentAddress.setText(rs.getString("PresentAddress"));
					txtPermanentAddress.setText(rs.getString("ParmanantAddress"));
					cmbDepartment.setSelectedItem(rs.getString("Department")==null?"":rs.getString("Department"));
					
					Blob immAsBlob = rs.getBlob("photo");
					byte[] immAsBytes = immAsBlob.getBytes(1, (int)immAsBlob.length());
					InputStream in = new ByteArrayInputStream(immAsBytes);
					buffer = ImageIO.read(in);
					lblPhotoSet();
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Invalid Doctor Name");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private boolean ValidDoctorName(){
		try {
			ResultSet rs=db.sta.executeQuery("select Name from tbdoctorinfo where Name='"+cmbDoctorName.txtMrNo.getText().trim().toString()+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return false;
	}
	private void LoadOnlyDoctor(){
		try {
			cmbMrDoctorName.v.clear();
			ResultSet rs=db.sta.executeQuery("select Name from tbdoctorinfo where type!='Marketing' order by Name");
			while(rs.next()){
				cmbMrDoctorName.v.add(rs.getString("Name"));
			}
		} catch (Exception e) {
		e.printStackTrace();
		JOptionPane.showInternalMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void LoadDoctor(){
		try {
			cmbDoctorName.v.clear();
			ResultSet rs=db.sta.executeQuery("select Name from tbdoctorinfo  order by Name");
			while(rs.next()){
				cmbDoctorName.v.add(rs.getString("Name"));
			}
		} catch (Exception e) {
		e.printStackTrace();
		JOptionPane.showInternalMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void dataEntry(){
		try {
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			//use another encoding if JPG is innappropriate for you
			ImageIO.write(buffer, "jpg", baos );
			baos.flush();
			byte[] immAsBytes = baos.toByteArray();
			baos.close();

			if(!checkDoctorCode()){
				autoId();

				String BrithDate="";
				String JoinDate="";
				if(txtBirthDate.getDate()!=null){
					BrithDate=new SimpleDateFormat("yyyy-MM-dd").format(txtBirthDate.getDate());
				}
				if(txtJoinDate.getDate()!=null){
					JoinDate=new SimpleDateFormat("yyyy-MM-dd").format(txtJoinDate.getDate());
				}
				String MrDoctorId=getMrDoctorId();
				
				

				String sql="insert into tbdoctorinfo (autoId,DoctorCode,Name,FatherName,MotherName,type,Religion,Sex,BirthDay,NationalId,Mobile,Fee,Degree,DegreeFrom,PassingYear,Floor,Room,JoinDate,Department,PresentAddress,ParmanantAddress,acc_ledger,entryTime,createBy) values('"+autoId+"',"
						+ "'"+txtDoctorCode.getText().toString()+"',"
						+ "'"+txtDoctorName.getText().toString()+"',"
						+ "'"+txtFatherName.getText().toString()+"',"
						+ "'"+txtMotherName.getText().toString()+"',"
						+ "'"+cmbType.getSelectedItem().toString()+"',"
						+ "'"+cmbReligion.getSelectedItem().toString()+"',"
						+ "'"+cmbSex.getSelectedItem().toString()+"',"
						+ "'"+BrithDate+"',"
						+ "'"+txtNationalId.getText().toString()+"',"
						+ "'"+txtMobile.getText().toString()+"',"
						+ "'"+txtConsultantFee.getText().toString()+"',"
						+ "'"+txtDegree.getText().toString()+"',"
						+ "'"+txtDegreeFrom.getText().toString()+"',"
						+ "'"+txtPassingYear.getText().toString()+"',"
						+ "'"+txtFloorNo.getText().toString()+"',"
						+ "'"+txtRoomNo.getText().toString()+"',"
						+ "'"+JoinDate+"',"
						+ "'"+cmbDepartment.getSelectedItem().toString()+"',"
						+ "'"+txtPresentAddress.getText().toString()+"',"
						+ "'"+txtPermanentAddress.getText().toString()+"',"
						+ "'',"
						+ "'"+startDate+"',"
						+ "'"+sessionBeam.getUserId()+"'"
						+ ")";
				System.out.println(sql);
				db.sta.executeUpdate(sql);


				
				autoLedgerId();
				String query="insert into accfledger (ledgerId,ledgerTitle,pheadId,Type,openingBalance,date,remark,entryTime,createBy)values" +
						"('"+ledgerId+"'," +
						"'"+txtDoctorName.getText().toString()+"'," +
						"'"+4+"'," +
						"'"+2+"'," +
						"'"+0+"'," +
						"CURRENT_TIMESTAMP," +
						"'"+txtDoctorName.getText().toString()+"'," +
						"CURRENT_TIMESTAMP," +
						"'"+sessionBeam.getUserId()+"')";
				System.out.println(query);
				db.sta.executeUpdate(query);

				String cLeger="update tbdoctorinfo set acc_ledger='"+ledgerId+"' where Name='"+txtDoctorName.getText().toString()+"' ";
				System.out.println(cLeger);
				db.sta.executeUpdate(cLeger);

				JOptionPane.showMessageDialog(null, "Doctor Information Successfully Create");
				
			}
			else{
				String BrithDate="";
				String JoinDate="";
				if(txtBirthDate.getDate()!=null){
					BrithDate=new SimpleDateFormat("yyyy-MM-dd").format(txtBirthDate.getDate());
				}
				if(txtJoinDate.getDate()!=null){
					JoinDate=new SimpleDateFormat("yyyy-MM-dd").format(txtJoinDate.getDate());
				}
				String MrDoctorId=getMrDoctorId();
				String sql="update tbdoctorinfo set"
						+ " Name='"+txtDoctorName.getText().toString()+"',"
						+ "FatherName='"+txtFatherName.getText().toString()+"',"
						+ "MotherName='"+txtMotherName.getText().toString()+"',"
						+ "type='"+cmbType.getSelectedItem().toString()+"',"
						+ "Religion='"+cmbReligion.getSelectedItem().toString()+"',"
						+ "Sex='"+cmbSex.getSelectedItem().toString()+"',"
						+ "BirthDay='"+BrithDate+"',"
						+ "NationalId='"+txtNationalId.getText().toString()+"',"
						+ "Mobile='"+txtMobile.getText().toString()+"',"
						+ "Fee='"+txtConsultantFee.getText().toString()+"',"
						+ "Degree='"+txtDegree.getText().toString()+"',"
						+ "DegreeFrom='"+txtDegreeFrom.getText().toString()+"',"
						+ "PassingYear='"+txtPassingYear.getText().toString()+"',"
						+ "Floor='"+txtFloorNo.getText().toString()+"',"
						+ "Room='"+txtRoomNo.getText().toString()+"',"
						+ "JoinDate='"+JoinDate+"',"
						+ "Department='"+cmbDepartment.getSelectedItem().toString()+"',"
						+ "PresentAddress='"+txtPresentAddress.getText().toString()+"',"
						+ "ParmanantAddress='"+txtPermanentAddress.getText().toString()+"',"
						+ "entryTime=CURRENT_TIMESTAMP,"
						+ "createBy='"+sessionBeam.getUserId()+"'"
						+ " where DoctorCode='"+txtDoctorCode.getText().trim().toString()+"'";
				System.out.println(sql);
				db.sta.executeUpdate(sql);

				JOptionPane.showMessageDialog(null, "Doctor Information Successfully Update");
				
			}
			
			java.sql.PreparedStatement pstmt=db.con.prepareStatement("update tbdoctorinfo set Photo = ? where DoctorCode = '"+txtDoctorCode.getText().trim()+"';");
			ByteArrayInputStream bais = new ByteArrayInputStream(immAsBytes);
			//pstmt.setString(1, txtSl.getText().trim());
			pstmt.setBinaryStream(1, bais, immAsBytes.length);
			pstmt.executeUpdate();
			pstmt.close();
			
			loadTableData();
			txtClear();
			autoId();
			txtDoctorName.requestFocusInWindow();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private String getMrDoctorId(){
		String MrDoctorId="";
		
		try {
			ResultSet rs=db.sta.executeQuery("select autoId from tbdoctorinfo where Name='"+cmbMrDoctorName.txtMrNo.getText().trim().toString()+"'");
			while(rs.next()){
				MrDoctorId=rs.getString("autoId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		
		return MrDoctorId;
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
	public void txtClear(){

		txtDoctorName.setText("");
		txtFatherName.setText("");
		txtMotherName.setText("");
		txtMobile.setText("01");
		txtConsultantFee.setText("");
		txtDegree.setText("");
		txtDegreeFrom.setText("");
		txtPassingYear.setText("");
		txtPermanentAddress.setText("");
		txtPresentAddress.setText("");
		txtFloorNo.setText("");
		txtRoomNo.setText("");
		cmbType.setSelectedItem("");
		txtJoinDate.setDate(null);
		txtNationalId.setText("");
		txtBirthDate.setDate(null);
		cmbDepartment.setSelectedItem("");
		cmbReligion.setSelectedItem("");
		cmbSex.setSelectedItem("");
		
		file=new File("");
		photoSet();
	}
	public void loadTableData(){
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select *from tbdoctorinfo order by autoId desc");
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("autoId"),rs.getString("Name"),rs.getString("Degree"),rs.getString("Mobile"),rs.getString("type"),rs.getString("PresentAddress"),new ImageIcon("icon/delete.png")});
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		rowAdd();
	}
	public void autoId(){
		try {
			String sql="select (ISNULL(max(autoId),0)+1)as autoId from tbdoctorinfo";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				autoId=rs.getString("autoId");
				txtDoctorCode.setText("DC-"+rs.getString("autoId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private boolean checkDoctorCode(){
		try {
			ResultSet rs=db.sta.executeQuery("select tbdoctorinfo.DoctorCode from tbdoctorinfo");
			while(rs.next()){
				if(txtDoctorCode.getText().toString().equalsIgnoreCase(rs.getString("DoctorCode")))
				{
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private void rowAdd(){
		for(int a=0;a<14;a++){
			model.addRow(new Object[]{"","","","","","","","","",""});
		}
	}
	private void allDoctorList(){
		try {
			HashMap map=new HashMap<>();
			map.put("orgName",sessionBeam.getOrgName());
			map.put("orgAddress",sessionBeam.getOrgAddress());
			map.put("orgMobile",sessionBeam.getOrgMobile());
			
			String report="MisReport/DoctorList.jrxml";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			String view="select autoId, Name,Mobile,Degree,PresentAddress from tbdoctorinfo order by autoId asc";
			System.out.println(view);
			jq.setText(view);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, map,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!"+e);
		}
	}
	private void addCmp() {
		setOpaque(false);
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(1280,620));
		mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(westPanel,BorderLayout.WEST);
		westPanel.setOpaque(false);
		westPanel_work();
//		mainPanel.add(obPanel,BorderLayout.CENTER);
//		obPanel.setOpaque(false);
//		centerPanel_work();
		mainPanel.add(centerPanel,BorderLayout.CENTER);
		centerPanel.setOpaque(false);
		centerPanelwork();
	}
	

	private void westPanel_work() {
		westPanel.setPreferredSize(new Dimension(500, 210));
		//northPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		westPanel.setLayout(new BorderLayout());
		westPanel.add(westNorthPanel,BorderLayout.NORTH);
		westNorthPanelWork();
		westPanel.add(westCenterPanel,BorderLayout.CENTER);
		westCenterPanelWork();
		westPanel.add(westSouthPanel,BorderLayout.SOUTH);
		westSouthPanelWork();
		westPanel.add(westEastPanel,BorderLayout.EAST);
		westEastPanelWork();
		
		
	}
	private void westEastPanelWork() {
		westEastPanel.setPreferredSize(new Dimension(140, 0));
		westEastPanel.setOpaque(false);
		
		westEastPanel.setLayout(new FlowLayout());
		westEastPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		
		westEastPanel.add(lblPhoto);
		lblPhoto.setPreferredSize(new Dimension(135, 135));
		lblPhoto.setBorder(BorderFactory.createLineBorder(Color.black));
		
		westEastPanel.add(btnUpload);
		//btnUpload.setPreferredSize(new Dimension(96, 34));
	}

	private void westSouthPanelWork() {
		westSouthPanel.setPreferredSize(new Dimension(0, 80));
		westSouthPanel.setOpaque(false);
		westSouthPanel.setLayout(new FlowLayout());
		
		westSouthPanel.add(btnSubmit);
		btnSubmit.setMnemonic(KeyEvent.VK_S);
		westSouthPanel.add(btnRefresh);
		btnRefresh.setMnemonic(KeyEvent.VK_R);
		
		
		btnSubmit.setPreferredSize(new Dimension(96, 34));
		btnRefresh.setPreferredSize(new Dimension(100, 34));
		btnPrint.setPreferredSize(new Dimension(200, 34));
	}

	private void westCenterPanelWork() {
		
		westCenterPanel.setOpaque(false);
		westCenterPanel.setLayout(new GridBagLayout());
		
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(3, 3, 3, 3);
		westCenterPanel.add(lbldoctorCode, grid);
		
		grid.gridx=0;
		grid.gridy=1;
		westCenterPanel.add(lbldoctorname, grid);
		
		grid.gridx=0;
		grid.gridy=2;
		westCenterPanel.add(lbldegree, grid);
		
		grid.gridx=0;
		grid.gridy=3;
		westCenterPanel.add(lblReligion, grid);
		
		grid.gridx=0;
		grid.gridy=4;
		westCenterPanel.add(lblsex, grid);
		
		grid.gridx=0;
		grid.gridy=5;
		westCenterPanel.add(lblMobile, grid);
		
		grid.gridx=0;
		grid.gridy=6;
		westCenterPanel.add(lbltype, grid);
		
		grid.gridx=0;
		grid.gridy=7;
		westCenterPanel.add(lbldepartment, grid);
		
		grid.gridx=0;
		grid.gridy=8;
		westCenterPanel.add(lblNationalId, grid);
		
		grid.gridx=0;
		grid.gridy=9;
		westCenterPanel.add(lblpresentaddress, grid);
		
		grid.gridx=0;
		grid.gridy=10;
		westCenterPanel.add(lbljoindate, grid);
		
		grid.gridx=0;
		grid.gridy=11;
		westCenterPanel.add(lblFee, grid);
		
		
		
		
		grid.gridx=1;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(3, 3, 3, 3);
		westCenterPanel.add(txtDoctorCode, grid);
		txtDoctorCode.setEditable(false);
		
		grid.gridx=1;
		grid.gridy=1;
		westCenterPanel.add(txtDoctorName, grid);
		
		grid.gridx=1;
		grid.gridy=2;
		westCenterPanel.add(txtDegree, grid);
		
		grid.gridx=1;
		grid.gridy=3;
		westCenterPanel.add(cmbReligion, grid);
		cmbReligion.setPreferredSize(new Dimension(190, 26));
		
		grid.gridx=1;
		grid.gridy=4;
		westCenterPanel.add(cmbSex, grid);
		
		grid.gridx=1;
		grid.gridy=5;
		westCenterPanel.add(txtMobile, grid);
		
		grid.gridx=1;
		grid.gridy=6;
		westCenterPanel.add(cmbType, grid);
		
		grid.gridx=1;
		grid.gridy=7;
		westCenterPanel.add(cmbDepartment, grid);
		
		grid.gridx=1;
		grid.gridy=8;
		westCenterPanel.add(txtNationalId, grid);
		
		grid.gridx=1;
		grid.gridy=9;
		westCenterPanel.add(PresentAddressScroll, grid);
		
		grid.gridx=1;
		grid.gridy=10;
		westCenterPanel.add(txtJoinDate, grid);
		txtJoinDate.setDateFormatString("yyyy-MM-dd");
		txtJoinDate.setDate(new Date());
		
		grid.gridx=1;
		grid.gridy=11;
		westCenterPanel.add(txtConsultantFee, grid);
		
		
		
		final Component ob[] = {txtDoctorName, txtDegree, cmbReligion,cmbSex,txtMobile,cmbType,cmbDepartment,txtNationalId,txtPresentAddress,txtConsultantFee,txtJoinDate};	
		new FocusMoveByEnter(ob);
	
	}

	private void westNorthPanelWork() {
		westNorthPanel.setOpaque(false);
		westNorthPanel.setLayout(new FlowLayout());
		
		
		westNorthPanel.add(cmbDoctorName.combo);
		cmbDoctorName.combo.setPreferredSize(new Dimension(230,30));
		
		westNorthPanel.add(btnFind);
		btnFind.setMnemonic(KeyEvent.VK_F);
		
	}


	private void centerPanelwork() {
		centerPanel.setPreferredSize(new Dimension(0, 390));
		centerPanel.setOpaque(false);
		//southPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(centerSouthPanel,BorderLayout.SOUTH);
		centerSouthPanelWork();
		centerPanel.add(centerCenterPanel,BorderLayout.CENTER);
		centerCenterPanelWork();
		
	}

	private void centerSouthPanelWork() {
		centerSouthPanel.setPreferredSize(new Dimension(40, 50));
		centerSouthPanel.setOpaque(false);
		centerSouthPanel.setLayout(new FlowLayout());
		centerSouthPanel.add(btnPrint);
		
		
		btnPrint.setMnemonic(KeyEvent.VK_R);
	}

	private void centerCenterPanelWork() {
		centerCenterPanel.setOpaque(false);
		centerCenterPanel.setLayout(new BorderLayout());
		centerCenterPanel.add(scroll);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		table.setOpaque(false);
		table.setRowHeight(table.getRowHeight() + 12);

		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(220);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
		table.getColumnModel().getColumn(6).setPreferredWidth(40);
		
		table.getTableHeader().setReorderingAllowed(false);
		table.setShowGrid(true);
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 13));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );

		Action delete = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Delete Item","Confrim-----",JOptionPane.YES_NO_OPTION);
					if(confrim==JOptionPane.YES_OPTION){
						/*					String sql="delete from tbdoctorinfo where DoctorCode='"+table.getValueAt(table.getSelectedRow(), 1)+"'";
						db.sta.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Doctor Information Delete Successfully");
						//loadBedData();
						 */					}

				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error,"+e2);
				}
			}
		};
		ButtonColumn btnDelete = new ButtonColumn(table, delete, 6);
	}

}
