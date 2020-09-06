package com.Lab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import com.ShareClass.ButtonColumn;
import com.ShareClass.FocusMoveByEnter;
import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
import com.ShareClass.db_coonection;

public class LabTestCreate extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;

	JPanel mainPanel=new JPanel();
	JPanel westPanel=new JPanel();
	JPanel westNorthPanel=new JPanel();
	JPanel westCenterPanel=new JPanel();
	JPanel westSouthPanel=new JPanel();
	JPanel eastSouthTopPanel=new JPanel();
	JPanel eastSouthBottomPanel=new JPanel();
	JPanel eastPanel=new JPanel();
	JPanel eastNorthPanel=new JPanel();
	JPanel eastCenterPanel=new JPanel();
	JPanel eastSouthPanel=new JPanel();

	JButton btnSaveGroup=new JButton("Add",new ImageIcon("icon/save.png"));
	JButton btnSubTest=new JButton("Save",new ImageIcon("icon/save.png"));
	JButton btnPerticular=new JButton("Save",new ImageIcon("icon/save.png"));
	JButton btnChangePerticular=new JButton("Change",new ImageIcon("icon/save.png"));
	JButton btnGroupWiseTestView=new JButton("View",new ImageIcon("icon/Preview.png"));
	JButton btnTestNameView=new JButton("View",new ImageIcon("icon/Preview.png"));
	JButton btnRefresh=new JButton("Refresh",new ImageIcon("icon/reresh.png"));
	JButton btnPrint=new JButton("Print",new ImageIcon("icon/print.png"));

	JLabel lblPrimaryGroup=new JLabel("Primary Group Name");

	SuggestText cmbPrimaryGroup=new SuggestText();

	JButton btnGroupView=new JButton("View",new ImageIcon("icon/Preview.png"));


	JLabel lblFinalTestPerticular=new JLabel("Final Test Perticular List");

	JLabel lblConsultantFee=new JLabel("Cons. Fee");
	JLabel lblSubHeading=new JLabel("Group       ");
	JLabel lblChangeSubHeading=new JLabel("Sub Heading ");
	JLabel lblPerticulars=new JLabel("Test Name");
	JLabel lblUnit=new JLabel("Unit");
	JLabel lblRate=new JLabel("Rate          ");
	JLabel lblTestCode=new JLabel("Test Code ");
	JLabel lblNormalRange=new JLabel(" Range(S)  ");

	JTextField txtTestCode=new JTextField(19);
	JTextField txtConsultantFee=new JTextField(3);
	JTextField txtRate=new JTextField(8);
	SuggestText cmbSubHeading=new SuggestText();
	SuggestText cmbTestNameForFind=new SuggestText();
	SuggestText cmbChangeSubHeading=new SuggestText();
	JTextField txtPerticulars=new JTextField(22);
	JTextField txtUnit=new JTextField(6);
	JTextField txtNormalRange=new JTextField(10);

	JLabel lblSelectGroup=new JLabel("");
	JLabel lblGroupId=new JLabel("Gr. ID");
	JLabel lblGroupName=new JLabel("Gr. Name");
	JLabel lblGroupComisson=new JLabel("Comisson");
	JTextField txtGroupId=new JTextField(4);
	JTextField txtGroupComission=new JTextField(4);
	JTextField txtGroupName=new JTextField(12);

	String Discount[]={"Discount Allow","Discount Not Allow"};
	JComboBox cmbDiscountAllow=new JComboBox(Discount);

	String HeadShow[]={"Heading Don't Show","Heading Show"};
	JComboBox cmbHeadShow=new JComboBox(HeadShow);

	String snIdForUpdate="";

	String GroupCol[]={"GS/N","Primary Group","Group Name","RoomNo","Comission","Edit"};
	Object GroupRow[][]={};
	DefaultTableModel GroupModel=new DefaultTableModel(GroupRow,GroupCol);
	JTable Grouptable=new JTable(GroupModel){
		@Override
		public boolean isCellEditable(int row,int col){
			if(col==0 ){
				return false;
			}
			else{
				return true;
			}

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

	JScrollPane GroupScroll=new JScrollPane(Grouptable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	String TestCol[]={"S/N","Particulars Head","Report Result Particulars","Doctor Discount","Rate","Units","Normal Range","EDIT"};
	Object TestRow[][]={};
	DefaultTableModel TestModel=new DefaultTableModel(TestRow,TestCol);
	JTable Testtable=new JTable(TestModel){
		@Override
		public boolean isCellEditable(int row,int col){
			if(col!=0){
				return true;
			}
			else{
				return false;	
			}

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
	JScrollPane TestScroll=new JScrollPane(Testtable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	String FTestCol[]={"S/N","Particulars Head","Report Result Particulars","Rate","Units","Normal Range","DL"};
	Object FTestRow[][]={};
	DefaultTableModel FTestModel=new DefaultTableModel(FTestRow,FTestCol);
	JTable FTesttable=new JTable(FTestModel){
		@Override
		public boolean isCellEditable(int row,int col){
			if(col==2 || col==3 || col==4 || col==5){
				return true;
			}
			else{
				return false;	
			}
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
	JScrollPane FTestScroll=new JScrollPane(FTesttable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	String startDate="",autoSn="",parentId="",testautoid="",ledgerId="";
	int addRow=0,select=0;;
	BufferedImage image;
	int find=0;
	String findSN="";

	HashMap map;
	DecimalFormat df = new DecimalFormat("#.00");
	public LabTestCreate(SessionBeam sessionBeam) {
		this.sessionBeam=sessionBeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addCmp();
		btnActionEvent();
		date_take();
		showGroupData();
		ShowTestName();
		ShowFinalTestName();
		LoadTestName();
		GroupautoId();
		background();
		LoadPrimaryGroup();

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
	public void loadTestName(){
		try {
			cmbSubHeading.v.clear();
			cmbSubHeading.v.add("");
			ResultSet rs=db.sta.executeQuery("select GroupName from tblabtestgroup");
			while(rs.next()){
				cmbSubHeading.v.add(rs.getString("GroupName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}

	public void TestautoId(){
		try {
			String sql="select (ISNULL(max(SN),0)+1)as SN from tbLabTestGroup";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				autoSn=rs.getString("SN");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void GroupautoId(){
		try {
			String sql="select count(*) as row from tbLabTestGroup where ParentId=0";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				int row=Integer.parseInt(rs.getString("row"));
				row++;
				txtGroupId.setText("G-"+row);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void AGroupautoId(){
		try {
			String sql="select (ISNULL(max(SN),0)+1)as SN from tbLabTestGroup";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				autoSn=rs.getString("SN");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void TestId(){
		try {
			String sql="select (ISNULL(max(SN),0)+1)as SN from tbtestname";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				testautoid=rs.getString("SN");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void date_take(){
		DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date=new Date();
		startDate =dateformat.format(date).toString();
	}
	public void btnActionEvent(){

		txtGroupName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnSaveGroupEvent();
			}
		});
		btnGroupView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnGroupViewEvent();
			}
		});
		btnSaveGroup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSaveGroupEvent();
			}
		});

		btnPerticular.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnPerticularEvent();
			}
		});
		btnChangePerticular.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//btnChangePerticularEvent();
			}
		});
		btnGroupWiseTestView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnGroupWiseTestViewEvent();
			}
		});
		btnTestNameView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnTestNameViewEvent();
			}
		});
		btnPrint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnPrintEvent();
			}
		});

		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				refreshAction();
			}

		});

		Testtable.addMouseListener(new MouseListener() {

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
				int row = Testtable.getSelectedRow();
				String sn = Testtable.getValueAt(row, 0).toString();
				String testName = Testtable.getValueAt(row, 2).toString();
				testTableClickEvent(sn, testName);
			}
		});
	}
	protected void refreshAction() {
		// TODO Auto-generated method stub
		ShowTestName();
		cmbSubHeading.txtMrNo.setText("");
		txtPerticulars.setText("");
		txtConsultantFee.setText("");
		txtRate.setText("");
		cmbDiscountAllow.setSelectedIndex(0);
		cmbHeadShow.setSelectedIndex(0);
		txtUnit.setText("");
		txtNormalRange.setText("");
		snIdForUpdate = "";
	}
	private String getParentId(String PrimaryGroupName){
		String ParentId="";
		try {
			ResultSet rs=db.sta.executeQuery("select SN from TbLabPrimaryGroup where PrimaryGroupName='"+PrimaryGroupName+"'");
			while(rs.next()){
				ParentId=rs.getString("SN");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return ParentId;
	}
	private boolean checkPrimaryGroup(String PrimaryGroupName){
		try {
			ResultSet rs=db.sta.executeQuery("select * from TbLabPrimaryGroup where PrimaryGroupName='"+PrimaryGroupName+"' ");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private void btnGroupViewEvent(){
		try {
			for(int a=Grouptable.getRowCount()-1;a>=0;a--){
				GroupModel.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select *,'"+cmbPrimaryGroup.txtMrNo.getText().trim().toString()+"' as PrimaryGroup from TbLabTestGroup where  ParentId=(select SN from TbLabPrimaryGroup where PrimaryGroupName='"+cmbPrimaryGroup.txtMrNo.getText().trim().toString()+"') order by SN");			
			while(rs.next()){
				GroupModel.addRow(new Object[]{rs.getString("SN"),rs.getString("PrimaryGroup"),rs.getString("GroupName"),rs.getString("roomNo"),df.format(Double.parseDouble(rs.getString("DoctorComission"))),new ImageIcon("icon/edt.png")});
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void btnTestNameViewEvent(){
		try {
			for(int a=Testtable.getRowCount()-1;a>=0;a--){
				TestModel.removeRow(a);
			}
			String sql = "select SN,(select GroupName from tblabtestgroup where SN=TestHeadId)as Suhhead,TestName,ISNULL(Discount,0) as Discount,Rate,Unit,NormalRange,DiscountAllow,HeadShow from tbtestname where TestName='"+cmbTestNameForFind.txtMrNo.getText().trim().toString()+"' ";
			System.out.println(sql);
			ResultSet rs=db.sta.executeQuery(sql);			
			while(rs.next()){
				TestModel.addRow(new Object[]{rs.getString("SN"),rs.getString("Suhhead"),rs.getString("TestName"),df.format(Double.parseDouble(rs.getString("Discount"))),df.format(Double.parseDouble(rs.getString("Rate"))),rs.getString("Unit"),rs.getString("NormalRange"),new ImageIcon("icon/edt.png")});
				cmbSubHeading.txtMrNo.setText(rs.getString("Suhhead"));
				txtPerticulars.setText(rs.getString("TestName"));
				txtUnit.setText(rs.getString("Unit"));
				txtRate.setText(rs.getString("rate"));
				txtNormalRange.setText(rs.getString("NormalRange"));

				cmbDiscountAllow.setSelectedIndex(rs.getInt("DiscountAllow")==1?0:1);
				cmbHeadShow.setSelectedIndex(rs.getInt("HeadShow")==1?0:1);

				snIdForUpdate=rs.getString("SN").trim();

			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}

	private void testTableClickEvent(String sn,String testName){
		try {
			String sql = "select SN,(select GroupName from tblabtestgroup where SN=TestHeadId)as Suhhead,TestName,ISNULL(Discount,0) as Discount,Rate,Unit,NormalRange,DiscountAllow,HeadShow from tbtestname where TestName='"+testName+"' and sn = '"+sn+"' ";
			System.out.println(sql);
			ResultSet rs=db.sta.executeQuery(sql);			
			while(rs.next()){

				cmbSubHeading.txtMrNo.setText(rs.getString("Suhhead"));
				txtPerticulars.setText(rs.getString("TestName"));
				txtUnit.setText(rs.getString("Unit"));
				txtRate.setText(rs.getString("rate"));
				txtNormalRange.setText(rs.getString("NormalRange"));

				cmbDiscountAllow.setSelectedIndex(rs.getInt("DiscountAllow")==1?0:1);
				cmbHeadShow.setSelectedIndex(rs.getInt("HeadShow")==1?0:1);

				snIdForUpdate=rs.getString("SN").trim();

			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void btnGroupWiseTestViewEvent(){
		try {
			for(int a=Testtable.getRowCount()-1;a>=0;a--){
				TestModel.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select SN,(select GroupName from tblabtestgroup where SN=TestHeadId)as Suhhead,TestName,ISNULL(Discount,0) as Discount,Rate,Unit,NormalRange from tbtestname where TestHeadId=(select SN from tblabtestgroup where GroupName='"+cmbSubHeading.txtMrNo.getText().trim().toString()+"')  order by TestHeadId,SN asc");			
			while(rs.next()){
				TestModel.addRow(new Object[]{rs.getString("SN"),rs.getString("Suhhead"),rs.getString("TestName"),df.format(Double.parseDouble(rs.getString("Discount"))),df.format(Double.parseDouble(rs.getString("Rate"))),rs.getString("Unit"),rs.getString("NormalRange"),new ImageIcon("icon/edt.png")});
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void btnPrintEvent(){
		try {
			String sql="";
			if(!cmbSubHeading.txtMrNo.getText().toString().trim().isEmpty()){
				sql="select (select GroupName from TbLabTestGroup where SN=tbtestname.TestHeadId)as GroupName,TestCode,TestName,Rate from tbtestname where TestHeadId=(select SN from TbLabTestGroup where GroupName='"+cmbSubHeading.txtMrNo.getText().toString().trim()+"') order by TestName";
			}
			else{
				sql="select (select GroupName from TbLabTestGroup where SN=tbtestname.TestHeadId)as GroupName,TestCode,TestName,Rate from tbtestname order by TestHeadId,TestName";
			}
			map=new HashMap<>();
			map.put("orgName",sessionBeam.getOrgName());
			map.put("orgAddress",sessionBeam.getOrgAddress());
			map.put("orgMobile",sessionBeam.getOrgMobile());

			String report="LabStatementReport/TestChart.jrxml";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			System.out.println(sql);
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, map,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	public boolean TestName(){
		try {
			ResultSet rs=db.sta.executeQuery("select GroupName from tblabtestgroup");
			while(rs.next()){
				if(cmbSubHeading.txtMrNo.getText().toString().equalsIgnoreCase(rs.getString("GroupName"))){
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	public boolean DoubleTestName(){
		try {
			ResultSet rs=db.sta.executeQuery("select *from tbtestname");
			while(rs.next()){
				if(txtPerticulars.getText().toString().equalsIgnoreCase(rs.getString("TestName"))){
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	public void autoLedgerId(){
		try {
			String sql="select (ISNULL(max(ledgerId),0)+1)as ledgerId from accfledger";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				ledgerId=rs.getString("ledgerId");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error!"+e);
		}
	}
	private void btnPerticularEvent() {
		if(!cmbSubHeading.txtMrNo.getText().toString().isEmpty()){
			if(!txtPerticulars.getText().toString().isEmpty()){
				if(!txtConsultantFee.getText().trim().toString().isEmpty()){
					if(!txtRate.getText().toString().isEmpty()){

						if(TestName())
						{
							if(snIdForUpdate.equals("")){
								try {
									int confrim=JOptionPane.showConfirmDialog(null, "Are You To Save New Test Information","Confrim......",JOptionPane.YES_NO_OPTION);
									if(confrim==JOptionPane.YES_OPTION){
										TestId();
										String SN="";
										String sql = "select SN  from tblabtestgroup where GroupName='"+cmbSubHeading.txtMrNo.getText().toString()+"'";
										ResultSet rs=db.sta.executeQuery(sql);
										while(rs.next()){
											SN=rs.getString("SN");
										}
										String showType=cmbHeadShow.getSelectedIndex()==0?"1":"0";
										String discountType=cmbDiscountAllow.getSelectedIndex()==0?"1":"0";
										//String testCode=cmbSubHeading.txtMrNo.getText().toString().substring(0, 3)+"-"+testautoid;
										sql="insert into tbtestname (SN,TestHeadId,TestName,Rate,Unit,NormalRange,DiscountAllow,HeadShow,acc_ledger,entryTime,createBy) values ('"+testautoid+"','"+SN+"','"+txtPerticulars.getText().trim().toString()+"','"+txtRate.getText().trim().toString()+"','"+txtUnit.getText().toString()+"','"+txtNormalRange.getText().toString()+"','"+discountType+"','"+showType+"','',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
										System.out.println(sql);
										db.sta.executeUpdate(sql);
										String phead="";

										JOptionPane.showMessageDialog(null, "Test Perticular Successfully Create");
										ShowTestName();
										LoadTestName();
									}


								} catch (Exception e) {
									e.printStackTrace();
									JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
								}
							}
							else{
								int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Edit TestInformation","Confrim......",JOptionPane.YES_NO_OPTION);
								if(confrim==JOptionPane.YES_OPTION){
									try {
										String SN="";
										ResultSet rs=db.sta.executeQuery("select SN  from tblabtestgroup where GroupName='"+cmbSubHeading.txtMrNo.getText().toString()+"'");
										while(rs.next()){
											SN=rs.getString("SN");
										}
										String showType=cmbHeadShow.getSelectedIndex()==0?"1":"0";
										String discountType=cmbDiscountAllow.getSelectedIndex()==0?"1":"0";
										String sql="update tbtestname set TestHeadId='"+SN+"',TestName='"+txtPerticulars.getText().trim().toString()+"',Rate='"+txtRate.getText().trim().toString()+"',Unit='"+txtUnit.getText().trim().toString()+"',NormalRange='"+txtNormalRange.getText().trim().toString()+"',DiscountAllow='"+discountType+"',HeadShow='"+showType+"',entryTime=CURRENT_TIMESTAMP,createBy='"+sessionBeam.getUserId()+"' where SN='"+snIdForUpdate+"'";
										System.out.println(sql);;
										db.sta.executeUpdate(sql);
										JOptionPane.showMessageDialog(null, "Test Perticular Successfully Create");
										ShowTestName();
										LoadTestName();
									} catch (Exception e) {
										e.printStackTrace();
										JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
									}
								}
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Invalid Test Depatment!!");
						}

					}
					else{
						JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Test Rate");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Doctor Commison For This Test");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Result Perticular");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Sub Head Name");
		}
	}
	private void btnSaveGroupEvent() {
		if(!txtGroupId.getText().toString().isEmpty()){
			if(!txtGroupName.getText().toString().isEmpty()){
				if(!txtGroupComission.getText().trim().toString().isEmpty()){
					try {
						AGroupautoId();
						String ParentId=getParentId(cmbPrimaryGroup.txtMrNo.getText().trim().toString());
						String sql="insert into tbLabTestGroup values('"+autoSn+"','"+txtGroupId.getText().toString()+"','"+txtGroupName.getText().toString()+"','"+ParentId+"','"+1+"','"+txtGroupComission.getText().trim().toString()+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
						System.out.println(sql);

						db.sta.executeUpdate(sql);
						//JOptionPane.showMessageDialog(null, "Group Name Confrim Sucessfully");
						txtGroupId.setText("G-");
						loadTestName();
						txtGroupName.setText("");
						GroupautoId();
						showGroupData();
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error,"+e);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Comission");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Group Name");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Group Id");
		}
	}
	private void showGroupData(){
		try {
			for(int a=Grouptable.getRowCount()-1;a>=0;a--){
				GroupModel.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select *,(select PrimaryGroupName from TbLabPrimaryGroup where SN=tbLabTestGroup.ParentId) as PrimaryGroup from tbLabTestGroup order by ParentId,SN asc");
			while(rs.next()){
				GroupModel.addRow(new Object[]{rs.getString("SN"),rs.getString("PrimaryGroup"),rs.getString("GroupName"),rs.getString("roomNo"),df.format(Double.parseDouble(rs.getString("DoctorComission"))),new ImageIcon("icon/edt.png")});
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error,"+e);
		}
	}
	private void ShowFinalTestName(){
		try {
			for(int a=FTesttable.getRowCount()-1;a>=0;a--){
				FTestModel.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select SN,(select GroupName from tblabtestgroup where SN=TestHeadId)as Suhhead,TestName,Rate,Unit,NormalRange from tbtestname");
			while(rs.next()){
				FTestModel.addRow(new Object[]{rs.getString("SN"),rs.getString("Suhhead"),rs.getString("TestName"),rs.getString("Rate"),rs.getString("Unit"),rs.getString("NormalRange"),new ImageIcon("icon/delete.png")});
			}
			for(int a=0;a<10;a++){
				FTestModel.addRow(new Object[]{"","","","","","",new ImageIcon("icon/delete.png")});
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error,"+e);
		}
	}
	private void ShowTestName(){
		try {
			for(int a=Testtable.getRowCount()-1;a>=0;a--){
				TestModel.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select SN,(select GroupName from tblabtestgroup where SN=TestHeadId)as Suhhead,TestName,ISNULL(Discount,0) as Discount,Rate,Unit,NormalRange from tbtestname order by TestHeadId,SN asc");
			while(rs.next()){
				TestModel.addRow(new Object[]{rs.getString("SN"),rs.getString("Suhhead"),rs.getString("TestName"),df.format(Double.parseDouble(rs.getString("Discount"))),df.format(Double.parseDouble(rs.getString("Rate"))),rs.getString("Unit"),rs.getString("NormalRange"),new ImageIcon("icon/edt.png")});
			}
			/*			for(int a=0;a<10;a++){
				TestModel.addRow(new Object[]{"","","","","",new ImageIcon("icon/edt.png")});
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error,"+e);
		}
	}
	private boolean checkUserAuthenticatonForUpdate(){
		try {
			ResultSet rs=db.sta.executeQuery("select change from tblogin where username='"+sessionBeam.getUserName()+"' and change=1");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return false;
	}
	private void LoadPrimaryGroup(){
		try {
			cmbPrimaryGroup.v.clear();
			ResultSet rs=db.sta.executeQuery("select PrimaryGroupName from TbLabPrimaryGroup order by SN asc");
			while(rs.next()){
				cmbPrimaryGroup.v.add(rs.getString("PrimaryGroupName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void LoadTestName(){
		try {
			cmbTestNameForFind.v.clear();
			cmbTestNameForFind.v.add("");
			ResultSet rs=db.sta.executeQuery("select TestName from tbtestname order by TestName");
			while(rs.next()){
				cmbTestNameForFind.v.add(rs.getString("TestName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error,"+e);
		}
	}
	private void addCmp() {
		setOpaque(false);
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(1300, 600));
		mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(westPanel, BorderLayout.WEST);
		westPanel.setOpaque(false);
		westPanel_work();
		mainPanel.add(eastPanel, BorderLayout.EAST);
		eastPanel.setOpaque(false);
		eastPanel_work();
	}
	private void westPanel_work() {
		westPanel.setPreferredSize(new Dimension(440, 10));
		//westPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		westPanel.setLayout(new BorderLayout());
		westPanel.add(westNorthPanel, BorderLayout.NORTH);
		westNorthPanel.setOpaque(false);
		westNorthPanel_work();
		westPanel.add(westCenterPanel, BorderLayout.CENTER);
		westCenterPanel.setOpaque(false);
		westCenterPanel_work();
		westPanel.add(westSouthPanel, BorderLayout.SOUTH);
		westSouthPanel.setOpaque(false);
		westSouthPanel_work();
	}
	private void westNorthPanel_work() {
		westNorthPanel.setPreferredSize(new Dimension(400, 100));
		//westNorthPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		FlowLayout flow=new FlowLayout();
		westNorthPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		westNorthPanel.add(lblGroupId);
		westNorthPanel.add(txtGroupId);
		txtGroupId.setPreferredSize(new Dimension(200, 30));
		txtGroupId.setEditable(false);
		lblGroupId.setVisible(false);
		txtGroupId.setVisible(false);


		westNorthPanel.add(lblPrimaryGroup);
		westNorthPanel.add(cmbPrimaryGroup.combo);
		cmbPrimaryGroup.combo.setPreferredSize(new Dimension(200, 30));

		westNorthPanel.add(btnGroupView);
		btnGroupView.setPreferredSize(new Dimension(90, 35));


		westNorthPanel.add(lblGroupName);
		westNorthPanel.add(txtGroupName);
		txtGroupName.setPreferredSize(new Dimension(200, 30));

		westNorthPanel.add(lblGroupComisson);
		westNorthPanel.add(txtGroupComission);
		txtGroupComission.setPreferredSize(new Dimension(200, 30));

		westNorthPanel.add(btnSaveGroup);
		btnSaveGroup.setPreferredSize(new Dimension(85, 36));	
		btnSaveGroup.setMnemonic(KeyEvent.VK_A);
	}
	private void westCenterPanel_work() {
		westCenterPanel.setPreferredSize(new Dimension(400, 210));
		//westCenterPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		westCenterPanel.setLayout(new FlowLayout());
		westCenterPanel.add(GroupScroll);
		GroupScroll.setOpaque(false);
		GroupScroll.getViewport().setOpaque(false);
		GroupScroll.setPreferredSize(new Dimension(400, 500));
		Grouptable.setOpaque(false);
		Grouptable.getTableHeader().setReorderingAllowed(false);
		Grouptable.getColumnModel().getColumn(0).setPreferredWidth(50);
		Grouptable.getColumnModel().getColumn(1).setPreferredWidth(120);
		Grouptable.getColumnModel().getColumn(2).setPreferredWidth(120);
		Grouptable.getColumnModel().getColumn(3).setPreferredWidth(60);
		Grouptable.getColumnModel().getColumn(4).setPreferredWidth(60);
		Grouptable.getColumnModel().getColumn(5).setPreferredWidth(35);
		Grouptable.setRowHeight(Grouptable.getRowHeight() + 10);
		Grouptable.setShowGrid(true);
		Grouptable.setSelectionForeground(Color.red);
		Grouptable.setFont(new Font("arial", Font.BOLD, 13));
		Action Update = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Update Group","Confrim-----",JOptionPane.YES_NO_OPTION);
					if(confrim==JOptionPane.YES_OPTION){
						if(checkUserAuthenticatonForUpdate()){
							if(checkPrimaryGroup(Grouptable.getValueAt(Grouptable.getSelectedRow(), 1).toString())){
								try {

									String ParentId=getParentId(Grouptable.getValueAt(Grouptable.getSelectedRow(), 1).toString());
									String sql="update tbLabTestGroup set ParentId='"+ParentId+"', GroupName='"+Grouptable.getValueAt(Grouptable.getSelectedRow(), 2)+"',roomNo='"+Grouptable.getValueAt(Grouptable.getSelectedRow(), 3)+"',DoctorComission='"+Grouptable.getValueAt(Grouptable.getSelectedRow(), 4)+"' where SN='"+Grouptable.getValueAt(Grouptable.getSelectedRow(), 0)+"'";
									System.out.println(sql);
									db.sta.executeUpdate(sql);


								} catch (Exception e2) {
									e2.printStackTrace();
									JOptionPane.showMessageDialog(null, "Error!!,"+e2);
								}
							}
							else{
								JOptionPane.showMessageDialog(null, "Warning!!,Invalid Primary Group");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Warning!!,You Have No Permission To Edit Group Information");
						}
					}

				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error,"+e2);
				}
			}
		};
		ButtonColumn btnUpdate = new ButtonColumn(Grouptable, Update, 5);
	}
	private void westSouthPanel_work() {
		westSouthPanel.setPreferredSize(new Dimension(400, 0));
		//westSouthPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		westSouthPanel.setLayout(new BorderLayout());
		westSouthPanel.add(lblSelectGroup,BorderLayout.NORTH);
		lblSelectGroup.setOpaque(false);
		lblSelectGroup.setFont(new Font("arial", Font.BOLD, 13));
		lblSelectGroup.setForeground(new Color(12, 144, 57));
	}
	private void eastPanel_work() {
		eastPanel.setPreferredSize(new Dimension(850, 10));
		//eastPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		eastPanel.setLayout(new BorderLayout());
		eastPanel.add(eastNorthPanel, BorderLayout.NORTH);
		eastNorthPanel.setOpaque(false);
		eastNorthPanel_work();
		eastPanel.add(eastCenterPanel, BorderLayout.CENTER);
		eastCenterPanel.setOpaque(false);
		eastCenterPanel_work();
		/*		eastPanel.add(eastSouthPanel, BorderLayout.SOUTH);
		eastSouthPanel.setOpaque(false);
		eastSouthPanel_work();*/
	}
	private void eastNorthPanel_work() {
		eastNorthPanel.setPreferredSize(new Dimension(640, 140));
		//eastNorthPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		FlowLayout flow=new FlowLayout();
		eastNorthPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		eastNorthPanel.add(lblSubHeading);
		eastNorthPanel.add(cmbSubHeading.combo);
		cmbSubHeading.combo.setPreferredSize(new Dimension(140, 30));


		eastNorthPanel.add(btnGroupWiseTestView);
		btnGroupWiseTestView.setPreferredSize(new Dimension(90, 34));


		eastNorthPanel.add(btnPrint);
		btnPrint.setPreferredSize(new Dimension(90,35));
		btnPrint.setMnemonic(KeyEvent.VK_P);
		/*	eastNorthPanel.add(lblTestCode);
		eastNorthPanel.add(txtTestCode);*/

		eastNorthPanel.add(lblPerticulars);
		eastNorthPanel.add(txtPerticulars);
		txtPerticulars.setPreferredSize(new Dimension(200, 30));

		eastNorthPanel.add(lblConsultantFee);
		eastNorthPanel.add(txtConsultantFee);

		eastNorthPanel.add(lblRate);
		eastNorthPanel.add(txtRate);

		eastNorthPanel.add(cmbDiscountAllow);
		cmbDiscountAllow.setPreferredSize(new Dimension(120,30));

		eastNorthPanel.add(cmbHeadShow);
		cmbHeadShow.setPreferredSize(new Dimension(140,30));

		eastNorthPanel.add(lblUnit);
		eastNorthPanel.add(txtUnit);

		txtUnit.setText(" ");
		txtUnit.setPreferredSize(new Dimension(200, 30));

		eastNorthPanel.add(lblNormalRange);
		eastNorthPanel.add(txtNormalRange);
		txtNormalRange.setText(" ");
		txtNormalRange.setPreferredSize(new Dimension(200, 30));

		eastNorthPanel.add(btnPerticular);
		btnPerticular.setPreferredSize(new Dimension(90, 36));
		btnPerticular.setMnemonic(KeyEvent.VK_S);

		final Component ob[] = {cmbSubHeading.txtMrNo,txtPerticulars,txtRate, txtUnit,txtNormalRange,btnSubTest};	
		new FocusMoveByEnter(ob);

		eastNorthPanel.add(new JLabel("__________________________________________________________________________________________________________________________________"));

		eastNorthPanel.add(new JLabel("Test Name"));

		eastNorthPanel.add(cmbTestNameForFind.combo);
		cmbTestNameForFind.combo.setPreferredSize(new Dimension(300, 30));

		eastNorthPanel.add(btnTestNameView);
		btnTestNameView.setPreferredSize(new Dimension(90, 36));

		eastNorthPanel.add(btnRefresh);
		btnRefresh.setPreferredSize(new Dimension(100, 36));
	}
	private void eastCenterPanel_work() {
		eastCenterPanel.setPreferredSize(new Dimension(840, 10));
		//eastCenterPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		eastCenterPanel.setLayout(new FlowLayout());
		eastCenterPanel.add(TestScroll);
		TestScroll.setPreferredSize(new Dimension(840, 370));
		TestScroll.setOpaque(false);
		TestScroll.getViewport().setOpaque(false);
		Testtable.setOpaque(false);
		Testtable.getTableHeader().setReorderingAllowed(false);
		Testtable.getColumnModel().getColumn(0).setPreferredWidth(50);
		Testtable.getColumnModel().getColumn(1).setPreferredWidth(140);
		Testtable.getColumnModel().getColumn(2).setPreferredWidth(220);
		Testtable.getColumnModel().getColumn(3).setPreferredWidth(180);
		Testtable.getColumnModel().getColumn(4).setPreferredWidth(120);
		Testtable.getColumnModel().getColumn(5).setPreferredWidth(80);
		Testtable.getColumnModel().getColumn(6).setPreferredWidth(180);
		Testtable.getColumnModel().getColumn(7).setPreferredWidth(35);
		Testtable.setRowHeight(Testtable.getRowHeight() + 10);
		Testtable.setShowGrid(true);
		Testtable.setSelectionForeground(Color.red);
		Testtable.setFont(new Font("arial", Font.BOLD, 13));
		Action Edit = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Edit Test Information","Confrim-----",JOptionPane.YES_NO_OPTION);
					if(confrim==JOptionPane.YES_OPTION){

						if(checkValidGroup(Testtable.getValueAt(Testtable.getSelectedRow(), 1).toString())){
							String TestGroupId=getTestGroupId(Testtable.getValueAt(Testtable.getSelectedRow(), 1).toString());
							String sql="update tbtestname  set TestHeadId='"+TestGroupId+"', TestName='"+Testtable.getValueAt(Testtable.getSelectedRow(), 2).toString()+"',Discount='"+Testtable.getValueAt(Testtable.getSelectedRow(), 3).toString()+"',Rate='"+Testtable.getValueAt(Testtable.getSelectedRow(), 4).toString()+"',Unit='"+Testtable.getValueAt(Testtable.getSelectedRow(), 5).toString()+"',NormalRange='"+Testtable.getValueAt(Testtable.getSelectedRow(), 6).toString()+"',createBy='"+sessionBeam.getUserId()+"',entryTime=CURRENT_TIMESTAMP where SN='"+Testtable.getValueAt(Testtable.getSelectedRow(), 0).toString()+"'";
							System.out.println(sql);
							db.sta.executeUpdate(sql);
							JOptionPane.showMessageDialog(null, "Test Informatioin Update Successfully");
							ShowTestName();
							LoadTestName();
						}
						else{
							JOptionPane.showMessageDialog(null, "Warning!!,Invalid Group Name");
						}
					}

				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error,"+e2);
				}
			}
		};
		ButtonColumn btnDelete = new ButtonColumn(Testtable, Edit, 7);


	}
	private String getTestGroupId(String GroupName){
		String TestGroupId="";
		try {
			ResultSet rs=db.sta.executeQuery("select SN from TbLabTestGroup where GroupName='"+GroupName+"'");
			while(rs.next()){
				TestGroupId=rs.getString("SN");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error,"+e);
		}
		return TestGroupId;

	}
	private boolean checkValidGroup(String GroupName){

		try {
			ResultSet rs=db.sta.executeQuery("select GroupName from TbLabTestGroup where GroupName='"+GroupName+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error,"+e);
		}
		return false;

	}
	private void eastSouthPanel_work() {
		eastSouthPanel.setPreferredSize(new Dimension(640, 370));
		//eastSouthPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		eastSouthPanel.setLayout(new BorderLayout());
		eastSouthPanel.add(eastSouthTopPanel, BorderLayout.NORTH);
		eastSouthTopPanel.setOpaque(false);
		eastSouthPanel.add(eastSouthBottomPanel, BorderLayout.SOUTH);
		eastSouthBottomPanel.setOpaque(false);
		eastSouthBottomPanel_work();
	}
	private void eastSouthBottomPanel_work() {
		eastSouthBottomPanel.setPreferredSize(new Dimension(640, 320));
		//eastSouthBottomPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		eastSouthBottomPanel.setLayout(new BorderLayout());
		eastSouthBottomPanel.add(FTestScroll);
		FTestScroll.setOpaque(false);
		FTestScroll.getViewport().setOpaque(false);
		FTesttable.setOpaque(false);
		FTesttable.getTableHeader().setReorderingAllowed(false);
		FTesttable.getColumnModel().getColumn(0).setPreferredWidth(50);
		FTesttable.getColumnModel().getColumn(1).setPreferredWidth(180);
		FTesttable.getColumnModel().getColumn(2).setPreferredWidth(180);
		FTesttable.getColumnModel().getColumn(3).setPreferredWidth(140);
		FTesttable.getColumnModel().getColumn(4).setPreferredWidth(70);
		FTesttable.getColumnModel().getColumn(5).setPreferredWidth(90);
		FTesttable.getColumnModel().getColumn(6).setPreferredWidth(100);
		FTesttable.getColumnModel().getColumn(7).setPreferredWidth(30);
		FTesttable.setRowHeight(FTesttable.getRowHeight() + 10);
		FTesttable.setShowGrid(true);
		FTesttable.setSelectionForeground(Color.red);
		FTesttable.setFont(new Font("arial", Font.BOLD, 13));
		//FTesttable.getTableHeader().
		Action Delete = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Delete Item","Confrim-----",JOptionPane.YES_NO_OPTION);
					if(confrim==JOptionPane.YES_OPTION){
						/*						String sql="delete from tbLabTestGroup where SN='"+FTesttable.getValueAt(FTesttable.getSelectedRow(), 0)+"'";
						db.sta.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Sub Group Delete Successfully");
						ShowSubGroupData();*/
					}

				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error,"+e2);
				}
			}
		};
		ButtonColumn btnDelete = new ButtonColumn(FTesttable, Delete, 7);
	}
}

