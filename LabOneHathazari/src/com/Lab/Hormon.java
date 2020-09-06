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
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.codehaus.groovy.runtime.metaclass.NewStaticMetaMethod;

import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
import com.ShareClass.db_coonection;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Hormon extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;
	public  ArrayList<String> pathologistitem = new ArrayList<String>();
	final DefaultComboBoxModel cmbpathologsitmodel = new DefaultComboBoxModel();
	
	public String testList="";
	public final JComboBox txtpathologsit = new JComboBox(cmbpathologsitmodel) {
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(super.getPreferredSize().width, 0);

		}
	};
	JPanel mainPanel=new JPanel();
	JPanel NorthPanel=new JPanel();
	JPanel NorthWestPanel=new JPanel();
	JPanel NorthEastPanel=new JPanel();
	JPanel CenterPanel=new JPanel();
	JPanel SouthPanel=new JPanel();


	JLabel lblCutOfValue=new JLabel("Cut Of Value");
	JLabel lblPatientSampleCount=new JLabel("Patient Sample Count");
	JLabel lblImpression=new JLabel("Impression");

	JTextField txtCutOfValue=new JTextField(18);
	JTextField txtPatientSampleCount=new JTextField(18);
	JTextField txtImpression=new JTextField(18);


	JLabel lblReport=new JLabel("Report No");
	String report[]={};
	JComboBox cmbReport=new JComboBox(report);

	JLabel lblCount=new JLabel("Count");

	//JRadioButton btnGeneral=new JRadioButton("General Report");
	JRadioButton btnConfirmatorty=new JRadioButton("Confirmatorty Report");
	ButtonGroup gp=new ButtonGroup();

	String Col[]={"T.P.ID","Test Perticulars","Test Result","Flag","Normal Ranges","Uom","Sorting","Test Name"};
	Object Row[][]={};
	DefaultTableModel model=new DefaultTableModel(Row,Col);
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
	JScrollPane Scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	int PathologistId=0;
	String labbillId="",autoId="",noteAutoId="",startdate="";
	List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
	JLabel lblSpecimen=new JLabel("Sample");
	String specimen[]={"Blood","Urine","Stool","Pus","Sputum","Semen"};
	JComboBox cmbSpecimen=new JComboBox(specimen);
	Date insertDate=null;
	BufferedImage image;
	GridBagConstraints grid=new GridBagConstraints();
	JPanel panelSpeciment=new JPanel();
	int check=0;
	String username="";
	String degreeName="";
	String FullName="";

	JRadioButton btnGroupTest=new JRadioButton("Group Test");
	JRadioButton btnSingleTest=new JRadioButton("Single Test");
	JRadioButton btnDiabetesTest=new JRadioButton("Diabetes Test");

	//ButtonGroup gp=new ButtonGroup();

	JLabel lblComment=new JLabel("Comment");
	SuggestText cmbComment=new SuggestText();
	public Hormon(SessionBeam sessionBeam){
		this.sessionBeam=sessionBeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addCmp();
		loadResultRow();
		background();
		btnActionEvent();
		loadComment();
	}
	public void loadComment(){
		try {
			cmbComment.v.clear();
			ResultSet rs=db.sta.executeQuery("select Note from TbTestWiseNote  where TestHeadId='4' order by Note");
			cmbComment.v.add("");
			while(rs.next()){
				cmbComment.v.add(rs.getString("Note"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	public void btnActionEvent(){
		txtCutOfValue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtPatientSampleCount.requestFocusInWindow();
			}
		});
		txtPatientSampleCount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtImpression.requestFocusInWindow();
			}
		});
		btnSingleTest.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(btnSingleTest.isSelected()){
					check=1;
					//loadResultRow();
					txtCutOfValue.setEnabled(false);
					txtPatientSampleCount.setEnabled(false);
					txtImpression.setEnabled(false);
				}
				else{
					txtCutOfValue.setEnabled(true);
					txtPatientSampleCount.setEnabled(true);
					txtImpression.setEnabled(true);
				}
			}
		});
		btnGroupTest.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(btnGroupTest.isSelected()){
					check=1;
					//loadResultRow();
					txtCutOfValue.setEnabled(false);
					txtPatientSampleCount.setEnabled(false);
					txtImpression.setEnabled(false);
				}
				else{
					txtCutOfValue.setEnabled(true);
					txtPatientSampleCount.setEnabled(true);
					txtImpression.setEnabled(true);
				}
			}
		});
		btnConfirmatorty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(btnConfirmatorty.isSelected()){
					check=0;
					//loadResultRow();
					txtCutOfValue.setEnabled(true);
					txtPatientSampleCount.setEnabled(true);
					txtImpression.setEnabled(true);
				}
				else{
					txtCutOfValue.setEnabled(false);
					txtPatientSampleCount.setEnabled(false);
					txtImpression.setEnabled(false);
				}
			}
		});
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
	public void date_take(){
		DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date=new Date();
		startdate =dateformat.format(date).toString();
	}
	public void setData(String testCode,String TestName){
		try {
			int temp=0;
			String sql2="select *from tbsubtestName where TestHeadId=(select Sn from tbtestname where TestCode='"+testCode+"')";
			System.out.println(sql2);
			ResultSet rs1=db.sta.executeQuery(sql2);
			while(rs1.next()){
				temp=1;
				break;
			}

			if(temp==1){
				String sql="select *from tbsubtestName where TestHeadId=(select Sn from tbtestname where TestCode='"+testCode+"')";
				ResultSet rs=db.sta.executeQuery(sql);
				while(rs.next()){
					int flag=0;
					for(int a=0;a<table.getRowCount();a++){
						if(table.getValueAt(a, 0).toString().equals(rs.getString("SN"))){
							flag++;
						}
					}
					if(flag==0){
						model.addRow(new Object[]{rs.getString("SN"),rs.getString("SubTestName"),"","",rs.getString("NormalRange"),rs.getString("Unit"),table.getRowCount()+1,TestName});
					}

				}
			}
			if(temp==0){
				String sql="select tbtestname.TestName,tbtestname.Unit,tbtestname.NormalRange from tbtestname where tbtestname.TestCode='"+testCode+"'";
				System.out.println(sql);
				ResultSet rs=db.sta.executeQuery(sql);
				while(rs.next()){
					int flag=0;
					for(int a=0;a<table.getRowCount();a++){
						if(table.getValueAt(a, 0).toString().equals(testCode)){
							flag++;
						}
					}
					if(flag==0){
						model.addRow(new Object[]{testCode,rs.getString("TestName"),"","",rs.getString("NormalRange"),rs.getString("Unit"),table.getRowCount()+1,TestName});
					}

				}
			}
			
			//loadResultRow();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}

	public void setFinalData(String labPid,String testCode,String TestName,String labId,Date date,String FiscalYear,String BillMonth){
		try {

			/*			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}*/
			int count=0;
			count=table.getRowCount();
			System.out.println("count "+count);
			int temp=0;
			ResultSet rs1=db.sta.executeQuery("select *from tbsubtestName where TestHeadId=(select Sn from tbtestname where TestCode='"+testCode+"')");
			while(rs1.next()){
				temp=1;
				break;
			}			
			if(temp==1){
				//JOptionPane.showMessageDialog(null, "Hi");
				String sql="select *from tbsubtestName where TestHeadId=(select Sn from tbtestname where TestCode='"+testCode+"')";
				//System.out.println(sql);
				ResultSet rs=db.sta.executeQuery(sql);
				int i=0;
				while(rs.next()){
					int flag=0;
					for(int a=0;a<table.getRowCount();a++){
						if(table.getValueAt(a, 0).toString().equals(rs.getString("SN"))){
							flag++;
						}
					}
					if(flag==0){
						model.addRow(new Object[]{rs.getString("SN"),rs.getString("SubTestName"),"","",rs.getString("NormalRange"),rs.getString("Unit"),table.getRowCount()+1,TestName});
						i++;	
					}
				}

				for(int a=count;a<(i+count);a++){
					ResultSet rs2=db.sta.executeQuery("select value,Flag,Sorting,(select username from tblogin where user_id=tblabreportvalue.createBy) as username from tblabreportvalue where FiscalYear='"+FiscalYear+"' and CMonth='"+BillMonth+"' and labPid=4 and labBillId='"+labId+"' and rId='"+table.getValueAt(a, 0).toString()+"'");
					while(rs2.next()){
						table.setValueAt(rs2.getString("value"), a, 2);
						table.setValueAt(rs2.getString("Flag"), a, 3);
						table.setValueAt(rs2.getString("Sorting"), a, 6);
						username=rs2.getString("username");
					}
				}
			}
			if(temp==0){
				int i=0;
				String query="select tbtestname.TestName,tbtestname.Unit,tbtestname.NormalRange from tbtestname where tbtestname.TestCode='"+testCode+"'";
				//System.out.print(query);
				ResultSet rs=db.sta.executeQuery(query);
				while(rs.next()){
					int flag=0;
					for(int a=0;a<table.getRowCount();a++){
						if(table.getValueAt(a, 0).toString().equals(testCode)){
							flag++;
						}
					}
					if(flag==0){
						model.addRow(new Object[]{testCode,rs.getString("TestName"),"","",rs.getString("NormalRange"),rs.getString("Unit"),table.getRowCount()+1,TestName});
						i++;
					}
				}
				for(int a=count;a<(i+count);a++){
					ResultSet rs2=db.sta.executeQuery("select value,CalculateValue,Flag,Sorting,(select username from tblogin where user_id=tblabreportvalue.createBy) as username from tblabreportvalue where FiscalYear='"+FiscalYear+"' and CMonth='"+BillMonth+"' and labPid=4 and labBillId='"+labId+"' and rId='"+table.getValueAt(a, 0).toString()+"'");
					while(rs2.next()){
						table.setValueAt(rs2.getString("value"), a, 2);
						table.setValueAt(rs2.getString("Flag"), a, 3);
						table.setValueAt(rs2.getString("Sorting"), a, 6);
						username=rs2.getString("username");

					}
				}
			}

			count=table.getRowCount();
			/*ResultSet rs2=db.sta.executeQuery("select Note from TbTestWiseNote where TestId=(select SN from tbtestname where TestName='"+TestName+"')");
			while(rs2.next()){
				cmbComment.txtMrNo.setText(rs2.getString("Note"));
			}*/
			
			//this code for confimatory result load
			String sql="select * from tblabreportvalue where FiscalYear='"+FiscalYear+"' and CMonth='"+BillMonth+"' and labBillId='"+labId+"' and labPid=4 and testCode between 125 and 127 and rId between 125 and 127";
			System.out.println(sql);
			ResultSet rs3=db.sta.executeQuery(sql);
			while(rs3.next()) {
				if(rs3.getString("rid").equals("125")) txtCutOfValue.setText(rs3.getString("value"));
				else if(rs3.getString("rid").equals("126")) txtPatientSampleCount.setText(rs3.getString("value"));
				else if(rs3.getString("rid").equals("127")) txtImpression.setText(rs3.getString("value"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void setPrintData(String labPid,String testCode,String TestName,String labId,Date date,String FiscalYear,String BillMonth){
		try {
			int main=0;
			int count=0;
			count=table.getRowCount();
			System.out.println("count "+count);
			int temp=0;
			ResultSet rs1=db.sta.executeQuery("select *from tbsubtestName where TestHeadId=(select Sn from tbtestname where TestCode='"+testCode+"')");
			while(rs1.next()){
				temp=1;
				break;
			}			
			if(temp==1){

				ArrayList SnList=new ArrayList();
				ArrayList SubTestList=new ArrayList();
				ArrayList NormalRangeList=new ArrayList();
				ArrayList UnitList=new ArrayList();
				String sql="select *from tbsubtestName where TestHeadId=(select Sn from tbtestname where TestCode='"+testCode+"')";
				//System.out.println(sql);
				ResultSet rs=db.sta.executeQuery(sql);
				int i=0;
				while(rs.next()){
					int flag=0;
					for(int a=0;a<table.getRowCount();a++){
						if(table.getValueAt(a, 0).toString().equals(rs.getString("SN"))){
							flag++;
						}
					}
					if(flag==0){
						SnList.add(rs.getString("SN"));
						SubTestList.add(rs.getString("SubTestName"));
						NormalRangeList.add(rs.getString("NormalRange"));
						UnitList.add(rs.getString("Unit"));
						//model.addRow(new Object[]{rs.getString("SN"),rs.getString("SubTestName"),"",rs.getString("NormalRange"),rs.getString("Unit"),"",TestName});
						i++;	
					}
				}

				for(int a=0;a<i;a++){

					ResultSet rs2=db.sta.executeQuery("select value,Flag,Sorting,(select username from tblogin where user_id=tblabreportvalue.createBy) as username from tblabreportvalue where FiscalYear='"+FiscalYear+"' and CMonth='"+BillMonth+"' and labPid=4 and labBillId='"+labId+"' and rId='"+SnList.get(a)+"'");
					while(rs2.next()){
						username=rs2.getString("username");
						model.addRow(new Object[]{SnList.get(a),SubTestList.get(a),rs2.getString("value"),rs2.getString("Flag"),NormalRangeList.get(a),UnitList.get(a),rs2.getString("Sorting"),TestName});
					}

				}
			}
			if(temp==0){
				int i=0;
				ArrayList TestList=new ArrayList();
				ArrayList NormalRangeList=new ArrayList();
				ArrayList UnitList=new ArrayList();
				String query="select tbtestname.TestName,tbtestname.Unit,tbtestname.NormalRange from tbtestname where tbtestname.TestCode='"+testCode+"'";
				//System.out.print(query);
				ResultSet rs=db.sta.executeQuery(query);
				while(rs.next()){
					int flag=0;
					for(int a=0;a<table.getRowCount();a++){
						if(table.getValueAt(a, 0).toString().equals(testCode)){
							flag++;
						}
					}
					if(flag==0){
						TestList.add(rs.getString("TestName"));
						NormalRangeList.add(rs.getString("NormalRange"));
						UnitList.add(rs.getString("Unit"));
						i++;
					}
				}
				for(int a=0;a<i;a++){
					ResultSet rs2=db.sta.executeQuery("select value,Flag,Sorting,(select username from tblogin where user_id=tblabreportvalue.createBy) as username from tblabreportvalue where FiscalYear='"+FiscalYear+"' and CMonth='"+BillMonth+"' and labPid=4 and labBillId='"+labId+"' and rId='"+testCode+"'");
					while(rs2.next()){
						username=rs2.getString("username");
						model.addRow(new Object[]{testCode,TestList.get(a),rs2.getString("value"),rs2.getString("Flag"),NormalRangeList.get(a),UnitList.get(a),rs2.getString("Sorting"),TestName});
					}
				}
			}
			count=table.getRowCount();
			//System.out.println("count "+count);

			/*ResultSet rs2=db.sta.executeQuery("select Note from TbTestWiseNote where TestId=(select SN from tbtestname where TestName='"+TestName+"')");
			while(rs2.next()){
				cmbComment.txtMrNo.setText(rs2.getString("Note"));
			}*/
			
			//Confirmatory Result Load
			String sql="select * from tblabreportvalue where FiscalYear='"+FiscalYear+"' and CMonth='"+BillMonth+"' and labBillId='"+labId+"' and labPid=4 and testCode between 125 and 127 and rId between 125 and 127";
			System.out.println(sql);
			ResultSet rs3=db.sta.executeQuery(sql);
			while(rs3.next()) {
				if(rs3.getString("rid").equals("125")) txtCutOfValue.setText(rs3.getString("value"));
				else if(rs3.getString("rid").equals("126")) txtPatientSampleCount.setText(rs3.getString("value"));
				else if(rs3.getString("rid").equals("127")) txtImpression.setText(rs3.getString("value"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void btnPrintEvent(String BillId,String Reg,String Name,String Age,String Month,String Day,String Sex,String Cabin,String Consultant,Date OrDate,String MainTestList,String FiscalYear,String BillMonth){
		try {
			list.clear();
			JasperPrint jp=null;
			HashMap map=null;


			int j=0,k=0,temp;
			int ara[]=new int[table.getRowCount()];

			for(int r = 0; r <table.getRowCount(); r++){
				ara[r]=Integer.parseInt(table.getValueAt(r, 6).toString());
			}

			for (j=0 ; j<(table.getRowCount()-1) ; j++)
			{
				for (k=0 ; k<(table.getRowCount()-1) ; k++)
				{
					if (ara[k+1]<ara[k])
					{
						temp=ara[k];
						ara[k]=ara[k+1];
						ara[k+1]=temp;
					}
				}
			}

			String ConsultantName="",Degree="";

			ResultSet rs=db.sta.executeQuery("select (select Name from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as DcName,(select Degree from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as DegreeName from TbLabPatient where FiscalYear='"+FiscalYear+"' and CMonth='"+BillMonth+"' and labId='"+BillId+"'");
			while(rs.next()){
				ConsultantName=rs.getString("DcName");
				Degree=rs.getString("DegreeName");
			}

			String normalRange="1";
			int ColCount=0;

			if(!btnConfirmatorty.isSelected()) {
				for (int r = 0; r <table.getRowCount(); r++)
				{

					for(int a=0;a<table.getRowCount();a++){
						if(ara[r]==Integer.parseInt(table.getValueAt(a, 6).toString().trim())){
							ColCount=0;
							if(!table.getValueAt(r, 2).toString().trim().equals("")){
								map=new HashMap();
								//String MonthNumber=getMonthNumber(BillMonth);
								map.put("LabNo",BillId);
								//map.put("LabNo",FiscalYear.substring(2,4)+"-"+MonthNumber+"-"+BillId);
								map.put("PatientName",Name);
								Age=!Age.equals("")?Age+"Y":"";
								Month=!Month.equals("")?Month+"M":"";
								Day=!Day.equals("")?Day+"D":"";
								map.put("Age",Age+" "+Month+" "+Day);
								map.put("RegNo",Reg);
								map.put("CabinNo",Cabin);
								map.put("Gender",Sex);
								map.put("Sample",cmbSpecimen.getSelectedItem());
								map.put("OrderDate",new SimpleDateFormat("dd-MM-yyyy").format(OrDate));
								map.put("ReportingDate",new SimpleDateFormat("dd-MM-yyyy").format(new Date()));	
								map.put("Consultant",ConsultantName);
								map.put("Degree",Degree);
								map.put("TestName",table.getValueAt(a, 1).toString().trim());
								map.put("Result",table.getValueAt(a, 2).toString().trim());
								map.put("Flag",table.getValueAt(a, 3).toString().trim());


								if(!table.getValueAt(a, 4).toString().trim().equals("")){
									normalRange=table.getValueAt(a, 4).toString().trim();
								}

								map.put("NormalRange",table.getValueAt(a, 4).toString().trim());
								map.put("Unit",table.getValueAt(a, 5).toString().trim());

								/*								if(!cmbLabIncharge.txtMrNo.getText().trim().toString().isEmpty()){
									map.put("LabIncharge",cmbLabIncharge.txtMrNo.getText().trim().toString().substring(0, cmbLabIncharge.txtMrNo.getText().trim().toString().indexOf("#")));
									map.put("LabInchargeDegree",cmbLabIncharge.txtMrNo.getText().trim().toString().substring(cmbLabIncharge.txtMrNo.getText().trim().toString().indexOf("#")+1, cmbLabIncharge.txtMrNo.getText().trim().toString().length()));
									ColCount++;
								}
								if(!cmbCheckedBy.txtMrNo.getText().trim().toString().isEmpty()){
									map.put("CheckedBy",cmbCheckedBy.txtMrNo.getText().trim().toString().substring(0, cmbCheckedBy.txtMrNo.getText().trim().toString().indexOf("#")));
									map.put("CheckedByDegree",cmbCheckedBy.txtMrNo.getText().trim().toString().substring(cmbCheckedBy.txtMrNo.getText().trim().toString().indexOf("#")+1, cmbCheckedBy.txtMrNo.getText().trim().toString().length()));
									ColCount++;
								}
								if(!cmbDoctorName1.txtMrNo.getText().trim().toString().isEmpty()){
									map.put("DoctorName1",cmbDoctorName1.txtMrNo.getText().trim().toString().substring(0, cmbDoctorName1.txtMrNo.getText().trim().toString().indexOf("#")));
									map.put("DoctorName1Degree",cmbDoctorName1.txtMrNo.getText().trim().toString().substring(cmbDoctorName1.txtMrNo.getText().trim().toString().indexOf("#")+1, cmbDoctorName1.txtMrNo.getText().trim().toString().length()));
									ColCount++;
								}
								if(!cmbDoctorName2.txtMrNo.getText().trim().toString().isEmpty()){

									map.put("DoctorName2",cmbDoctorName2.txtMrNo.getText().trim().toString().substring(0, cmbDoctorName2.txtMrNo.getText().trim().toString().indexOf("#")));
									map.put("DoctorName2Degree",cmbDoctorName2.txtMrNo.getText().trim().toString().substring(cmbDoctorName2.txtMrNo.getText().trim().toString().indexOf("#")+1, cmbDoctorName2.txtMrNo.getText().trim().toString().length()));
									ColCount++;
								}
								 */
								map.put("MainTestName",table.getValueAt(a, 7).toString().trim());
								map.put("TestList",MainTestList);
								//map.put("Note",txtNote.getText().trim().toString());
								list.add(map);
							}
						}
					}
				}

				String sql = "select * from tbLabInchargeConsultantDegree where designation = 'Lab Incharge'";
				System.out.println(sql);;
				rs = db.sta.executeQuery(sql);
				if(rs.next()) {
					map.put("inchargel1",rs.getString("line1"));
					map.put("inchargel2",rs.getString("line2"));
					map.put("inchargel3",rs.getString("line3"));
					map.put("inchargel4",rs.getString("line4"));
					map.put("inchargel5",rs.getString("line5"));
					map.put("inchargel6",rs.getString("line6"));
					map.put("inchargel7",rs.getString("line7"));
					map.put("inchargel8",rs.getString("line8"));
					
				}
				sql = "select * from tbLabInchargeConsultantDegree where name = '"+LabRptHome.getDoctorName()+"'";
				System.out.println(sql);;
				rs = db.sta.executeQuery(sql);
				if(rs.next()) {
					map.put("doctorl1",rs.getString("line1"));
					map.put("doctorl2",rs.getString("line2"));
					map.put("doctorl3",rs.getString("line3"));
					map.put("doctorl4",rs.getString("line4"));
					map.put("doctorl5",rs.getString("line5"));
					map.put("doctorl6",rs.getString("line6"));
					map.put("doctorl7",rs.getString("line7"));
					map.put("doctorl8",rs.getString("line8"));
				}
				
				map.put("Note",cmbComment.txtMrNo.getText().trim());
				
				if(btnSingleTest.isSelected()){
					String input ="NewFormetReport/HormoneWithOutGroup.jrxml";
					//input=btnSingleTest.isSelected()?"NewFormetReport/BioChemistry3ColWithOutGroup.jrxml":btnGroupTest.isSelected()?"NewFormetReport/BioChemistry3ColWithGroup.jrxml":"NewFormetReport/BioChemistry3ColWithOutGroupDiabetes.jrxml";
					JasperReport com=JasperCompileManager.compileReport(input);
					jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(list));
					JasperViewer.viewReport(jp, false);
					JasperPrintManager.printReport(jp, true);
					list.clear();
				}
				else if(btnGroupTest.isSelected()){
					String input ="NewFormetReport/HormoneWithGroup.jrxml";
					//input=btnSingleTest.isSelected()?"NewFormetReport/BioChemistry3ColWithOutGroup.jrxml":btnGroupTest.isSelected()?"NewFormetReport/BioChemistry3ColWithGroup.jrxml":"NewFormetReport/BioChemistry3ColWithOutGroupDiabetes.jrxml";
					JasperReport com=JasperCompileManager.compileReport(input);
					jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(list));
					JasperViewer.viewReport(jp, false);
					JasperPrintManager.printReport(jp, true);
					list.clear();
				}
			}
			else if(btnConfirmatorty.isSelected()){
				
				map=new HashMap();
				
				String sql="select * from tblabreportvalue where FiscalYear='"+FiscalYear+"' and CMonth='"+BillMonth+"' and labBillId='"+BillId+"' and labPid=4 and testCode between 125 and 127 and rId between 125 and 127";
				System.out.println(sql);
				ResultSet rs1=db.sta.executeQuery(sql);
				while(rs1.next()) {
					if(rs1.getString("rid").equals("125")) txtCutOfValue.setText(rs1.getString("value"));
					else if(rs1.getString("rid").equals("126")) txtPatientSampleCount.setText(rs1.getString("value"));
					else if(rs1.getString("rid").equals("127")) txtImpression.setText(rs1.getString("value"));
				}
				
				//String MonthNumber=getMonthNumber(BillMonth);
				map.put("LabNo",BillId);
				//map.put("LabNo",FiscalYear.substring(2,4)+"-"+MonthNumber+"-"+BillId);
				map.put("PatientName",Name);
				Age=!Age.equals("")?Age+"Y":"";
				Month=!Month.equals("")?Month+"M":"";
				Day=!Day.equals("")?Day+"D":"";
				map.put("Age",Age+" "+Month+" "+Day);
				map.put("RegNo",Reg);
				map.put("CabinNo",Cabin);
				map.put("Gender",Sex);
				map.put("Sample",cmbSpecimen.getSelectedItem());
				map.put("OrderDate",new SimpleDateFormat("dd-MM-yyyy").format(OrDate));
				map.put("ReportingDate",new SimpleDateFormat("dd-MM-yyyy").format(new Date()));	
				map.put("Consultant",ConsultantName);
				map.put("Degree",Degree);
				map.put("CutOfValue",txtCutOfValue.getText().trim());
				map.put("PatientSample",txtPatientSampleCount.getText().trim());
				map.put("Impression",txtImpression.getText().trim());
				map.put("TestList",testList);
				map.put("Note",cmbComment.txtMrNo.getText().trim());
				
				String input = "NewFormetReport/HormoneConfirmatoryReport.jrxml";
				list.add(map);
				
				JasperReport com=JasperCompileManager.compileReport(input);
				jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(list));
				JasperViewer.viewReport(jp, false);
				JasperPrintManager.printReport(jp, true);
				list.clear();
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private String getMonthNumber(String MonthName){
		String Number="";
		switch (MonthName) {
		case "January":
			Number="01";
			break;
		case "February":
			Number="02";
			break;
		case "March":
			Number="03";
			break;
		case "April":
			Number="04";
			break;
		case "May":
			Number="05";
			break;
		case "June":
			Number="06";
			break;
		case "July":
			Number="07";
			break;
		case "August":
			Number="08";
			break;
		case "September":
			Number="09";
			break;
		case "October":
			Number="10";
			break;
		case "November":
			Number="11";
			break;
		case "December":
			Number="12";
			break;

		default:
			break;
		}

		return Number;
	}
	/*	
	public void setFinalData(String labPid,String testCode,String TestName,String labId,Date date){
		try {

			System.out.println("labId "+labId);

			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			int temp=0;
			int Speciment=0;
			ResultSet rs1=db.sta.executeQuery("select *from tbsubtestName where TestHeadId=(select Sn from tbtestname where TestCode='"+testCode+"')");
			while(rs1.next()){
				temp=1;
				break;
			}

			if(temp==1){
				//JOptionPane.showMessageDialog(null, "Hi");
				String sql="select *from tbsubtestName where TestHeadId=(select Sn from tbtestname where TestCode='"+testCode+"')";
				System.out.println(sql);
				ResultSet rs=db.sta.executeQuery(sql);
				int i=0;
				while(rs.next()){
					model.addRow(new Object[]{rs.getString("SN"),rs.getString("SubTestName"),"",rs.getString("NormalRange"),rs.getString("Unit"),"",TestName});
					i++;
				}

				for(int a=0;a<i;a++){
					ResultSet rs2=db.sta.executeQuery("select value,Sorting,(select name from tblogin where user_id=tblabreportvalue.createBy) as FullName,(select Degree from tblogin where user_id=tblabreportvalue.createBy) as DegreeName,(select username from tblogin where user_id=tblabreportvalue.createBy) as username from tblabreportvalue where  labPid=4 and labBillId='"+labId+"' ");
					while(rs2.next()){
						table.setValueAt(rs2.getString("value"), a, 2);
						table.setValueAt(rs2.getString("Sorting"), a, 5);
						username=rs2.getString("username");
						degreeName=rs2.getString("DegreeName");
						FullName=rs2.getString("FullName");
						Speciment=1;
					}
				}
			}
			if(temp==0){
				int i=0;
				String query="select tbtestname.TestName,tbtestname.Unit,tbtestname.NormalRange from tbtestname where tbtestname.TestCode='"+testCode+"'";
				System.out.print(query);
				ResultSet rs=db.sta.executeQuery(query);
				while(rs.next()){
					model.addRow(new Object[]{testCode,rs.getString("TestName"),"",rs.getString("NormalRange"),rs.getString("Unit"),"",TestName});
					i++;
				}
				for(int a=0;a<i;a++){
					ResultSet rs2=db.sta.executeQuery("select value,Sorting,(select name from tblogin where user_id=tblabreportvalue.createBy) as FullName,(select Degree from tblogin where user_id=tblabreportvalue.createBy) as DegreeName,(select username from tblogin where user_id=tblabreportvalue.createBy) as username from tblabreportvalue where  labPid=4 and labBillId='"+labId+"' and rId='"+table.getValueAt(a, 0).toString()+"'");
					while(rs2.next()){
						table.setValueAt(rs2.getString("value"), a, 2);
						table.setValueAt(rs2.getString("Sorting"), a, 5);
						username=rs2.getString("username");
						degreeName=rs2.getString("DegreeName");
						FullName=rs2.getString("FullName");
						Speciment=1;
					}
				}
			}
			System.out.println("Speciment "+Speciment);
			if(Speciment==0 || Speciment==1){
				ResultSet rs2=db.sta.executeQuery("select *from tblabreportvalue where labBillId='"+labId+"'");
				while(rs2.next()){
					if(rs2.getString("rId").equals("125")){
						txtCutOfValue.setText(rs2.getString("value"));
					}
					if(rs2.getString("rId").equals("126")){
						txtPatientSampleCount.setText(rs2.getString("value"));
					}
					if(rs2.getString("rId").equals("127")){
						txtImpression.setText(rs2.getString("value"));
					}
				}
			}
			//loadResultRow();

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}*/


	public void savebtnActionEvent(String labId,Date date,String FiscalYear,String BillMonth){
		labbillId=labId;
		insertDate=date;
		System.out.println("labbillId "+labbillId);

		try {
			if(!btnSingleTest.isSelected() && !btnGroupTest.isSelected() && !btnConfirmatorty.isSelected()){
				JOptionPane.showMessageDialog(null, "Please Provide Hormon Result Type");
			}
			else{
				String LabInchargeId="";
				String CheckedById="";
				String Doctor1Id="";;
				String Doctor2Id="";

				if(btnSingleTest.isSelected() || btnGroupTest.isSelected() ){
					for(int a=0;a<table.getRowCount();a++){
						if(table.getValueAt(a, 0).toString()!="" && table.getValueAt(a, 6).toString()!=""){
							if(!checkLabReport(table.getValueAt(a, 0).toString(),labId,4,table.getValueAt(a, 0).toString(),FiscalYear,BillMonth)){
								AutoId();
								insertdata(a,autoId,table.getValueAt(a, 0).toString(),table.getValueAt(a, 2).toString(),table.getValueAt(a, 3).toString(),a,table.getValueAt(a, 0).toString(),table.getValueAt(a, 6).toString(),LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear,BillMonth);
							}
							else{
								updateData(a,autoId,table.getValueAt(a, 0).toString(),table.getValueAt(a, 2).toString(),table.getValueAt(a, 3).toString(),a,table.getValueAt(a, 0).toString(),table.getValueAt(a, 6).toString(),LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear,BillMonth );
							}

							int check=0;
							String TestCode="";
							ResultSet rs=db.sta.executeQuery("select tbsubtestname.TestHeadId,(select TestCode from tbtestname where SN=tbsubtestname.TestHeadId)as TestCode from tbsubtestname where SN='"+table.getValueAt(a, 0).toString().toString().substring(table.getValueAt(a, 0).toString().toString().indexOf("-")+1, table.getValueAt(a, 0).toString().toString().length())+"'");
							while(rs.next()){
								check=1;
								TestCode=rs.getString("TestCode");
								break;
							}
							if(check==1){
								String query1="update tblabtesthistory set ResultStatus='DONE' where FiscalYear='"+FiscalYear+"' and CMonth='"+BillMonth+"' and labId='"+labbillId+"' and testCode='"+TestCode+"'" ;
								System.out.println(query1);
								db.sta.executeUpdate(query1);
							}
							else{
								String query1="update tblabtesthistory set ResultStatus='DONE' where FiscalYear='"+FiscalYear+"' and CMonth='"+BillMonth+"' and labId='"+labbillId+"' and testCode='"+table.getValueAt(a, 0).toString()+"'" ;
								System.out.println(query1);
								db.sta.executeUpdate(query1);
							}
						}
					}
				}
				else if(btnConfirmatorty.isSelected()){
					if(!txtCutOfValue.getText().trim().toString().isEmpty()){
						if(!checkLabReport("125",labId,4,"125",FiscalYear,BillMonth)){
							date_take();
							AutoId();
							insertdata(125,autoId,"125",txtCutOfValue.getText().toString(),"",0,"125","1",LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear,BillMonth);
						}
						else{
							updateData(125,autoId,"125",txtCutOfValue.getText().toString(),"",0,"125","1",LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear,BillMonth);
						}
					}
					if(!txtPatientSampleCount.getText().trim().toString().isEmpty()){
						if(!checkLabReport("126",labId,4,"126",FiscalYear,BillMonth)){
							date_take();
							AutoId();
							insertdata(126,autoId,"126",txtPatientSampleCount.getText().toString(),"",1,"126","2",LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear,BillMonth);
						}
						else{
							updateData(126,autoId,"126",txtPatientSampleCount.getText().toString(),"",1,"126","2",LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear,BillMonth);
						}
					}
					if(!txtImpression.getText().trim().toString().isEmpty()){
						if(!checkLabReport("127",labId,4,"127",FiscalYear,BillMonth)){
							date_take();
							AutoId();
							insertdata(127,autoId,"127",txtImpression.getText().toString(),"",2,"127","3",LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear,BillMonth);
						}
						else{
							updateData(127,autoId,"127",txtImpression.getText().toString(),"",2,"127","3",LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear,BillMonth);
						}
					}
				}


				/*				String query1="update tblabtesthistory set ResultStatus='DONE' where  labId='"+labbillId+"' and testCode='"+TestCode+"'" ;
				System.out.println(query1);
				db.sta.executeUpdate(query1);*/
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void insertdata(int r,String autoID,String rId,String value,String Flag,int row,String TestCode,String Sorting,String LabInchargeId,String CheckedById,String Doctor1Id,String Doctor2Id,String FiscalYear,String BillMonth){
		try {
			noteAutoId();
			String sql="insert into tblabreportvalue (autoId,rId,value,Flag,labPId,labBillId,testCode,Sorting,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,date,entryTime,createBy,FiscalYear,CMonth) values ('"+autoID+"',"
					+ "'"+rId+"',"
					+ "'"+value+"','"+Flag+"','4',"
					+ "'"+labbillId+"',"
					+ "'"+TestCode+"',"
					+ "'"+Sorting+"',"
					+ "'"+LabInchargeId+"',"
					+ "'"+CheckedById+"',"
					+ "'"+Doctor1Id+"',"
					+ "'"+Doctor2Id+"',"
					+ "'"+new SimpleDateFormat("yyyy-MM-dd").format(insertDate)+"',"
					+ "CURRENT_TIMESTAMP,"
					+ "'"+sessionBeam.getUserId()+"','"+FiscalYear+"','"+BillMonth+"')";
			System.out.println(sql);
			db.sta.executeUpdate(sql);


		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void updateData(int r,String autoID,String rId,String value,String Flag,int row,String TestCode,String Sorting,String LabInchargeId,String CheckedById,String Doctor1Id,String Doctor2Id,String FiscalYear,String BillMonth){
		try {
			String sql="update tblabreportvalue set value='"+value+"',Flag='"+Flag+"',Sorting='"+Sorting+"',LabInchargeId='"+LabInchargeId+"',CheckedById='"+CheckedById+"',Doctor1Id='"+Doctor1Id+"',Doctor2Id='"+Doctor2Id+"',createBy='"+sessionBeam.getUserId()+"' where FiscalYear='"+FiscalYear+"' and CMonth='"+BillMonth+"' and rId='"+rId+"' and labBillId='"+labbillId+"' and testCode='"+TestCode+"' ";
			System.out.println(sql);
			db.sta.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void AutoId(){
		try {
			String sql="select (ISNULL(max(autoId),0)+1)as autoId from tblabreportvalue";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				autoId=rs.getString("autoId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void noteAutoId(){
		try {
			String sql="select (ISNULL(max(autoId),0)+1)as autoId from tblabreportnote";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				noteAutoId=rs.getString("autoId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public boolean checkLabReport(String rId,String labId,int pId,String TestCode,String FiscalYear,String BillMonth){
		try {
			ResultSet rs=db.sta.executeQuery("select rId,labBillId,labPId,testCode from tblabreportvalue where FiscalYear='"+FiscalYear+"' and CMonth='"+BillMonth+"' and rId='"+rId+"' and labBillId='"+labId+"' and labPId='"+pId+"' and testCode='"+TestCode+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	public void refreshEvent(){

		for(int a=table.getRowCount()-1;a>=0;a--){
			model.removeRow(a);
		}
		txtCutOfValue.setText("");
		txtPatientSampleCount.setText("");
		txtImpression.setText("");
	}
	private void loadResultRow(){
		for(int a=0;a<12;a++){
			model.addRow(new Object[]{"","","","","",""});
		}
	}
	private void addCmp() {
		setOpaque(false);
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(1300, 485));
		mainPanel.setLayout(new BorderLayout());
		/*		mainPanel.add(NorthPanel,BorderLayout.NORTH);
		NorthPanel.setOpaque(false);
		NorthPanel_work();*/
		mainPanel.add(CenterPanel,BorderLayout.CENTER);
		CenterPanel.setOpaque(false);
		CenterPanel_work();
	}
	/*	private void NorthPanel_work() {
		NorthPanel.setPreferredSize(new Dimension(1060, 70));
		NorthPanel.setBorder(BorderFactory.createTitledBorder("Speciment"));
		FlowLayout flow=new FlowLayout();
		NorthPanel.setLayout(flow);

		NorthPanel.add(checkFix);

		NorthPanel.add(lblReport);
		NorthPanel.add(cmbReport);		
		cmbReport.setPreferredSize(new Dimension(140, 28));

		NorthPanel.add(lblCount);

		NorthPanel.add(checkReport);		

	}*/
	private void CenterPanel_work() {
		CenterPanel.setPreferredSize(new Dimension(1060, 80));
		CenterPanel.setBorder(BorderFactory.createTitledBorder("Examination Result"));
		CenterPanel.setLayout(new FlowLayout());

		CenterPanel.add(lblSpecimen);
		CenterPanel.add(cmbSpecimen);
		cmbSpecimen.setPreferredSize(new Dimension(250, 34));

		CenterPanel.add(btnSingleTest);
		CenterPanel.add(btnGroupTest);
		CenterPanel.add(btnConfirmatorty);

		btnSingleTest.setSelected(true);
		gp.add(btnSingleTest);
		gp.add(btnGroupTest);
		gp.add(btnConfirmatorty);
		JLabel lblBl=new JLabel();
		CenterPanel.add(lblBl);
		lblBl.setPreferredSize(new Dimension(250,20));

		CenterPanel.add(Scroll);
		Scroll.setOpaque(false);
		Scroll.getViewport().setOpaque(false);
		table.setOpaque(false);
		table.setShowGrid(true);
		Scroll.setPreferredSize(new Dimension(750, 250));
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(280);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);
		table.getColumnModel().getColumn(5).setPreferredWidth(70);
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 13));
		table.setRowHeight(table.getRowHeight() + 15);

		CenterPanel.add(panelSpeciment);
		//panelSpeciment.setEnabled(false);
		panelSpeciment.setOpaque(false);
		panelSpeciment.setPreferredSize(new Dimension(500,180));
		panelSpeciment.setBorder(BorderFactory.createLineBorder(Color.white,10));
		panelSpeciment.setLayout(new GridBagLayout());
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(5, 5, 5, 5);
		panelSpeciment.add(lblCutOfValue,grid);
		grid.gridx=1;
		grid.gridy=0;
		panelSpeciment.add(txtCutOfValue,grid);
		grid.gridx=0;
		grid.gridy=1;
		panelSpeciment.add(lblPatientSampleCount,grid);
		grid.gridx=1;
		grid.gridy=1;
		panelSpeciment.add(txtPatientSampleCount,grid);
		grid.gridx=0;
		grid.gridy=2;
		panelSpeciment.add(lblImpression,grid);
		grid.gridx=1;
		grid.gridy=2;
		panelSpeciment.add(txtImpression,grid);


		CenterPanel.add(lblComment);
		lblComment.setFont(new Font("arial", Font.BOLD, 14));
		CenterPanel.add(cmbComment.combo);
		cmbComment.combo.setPreferredSize(new Dimension(700, 30));
	}
}
