package com.Lab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import com.ShareClass.FocusMoveByEnter;
import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
import com.ShareClass.db_coonection;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class Pathology extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;
	JPanel mainpanel=new JPanel();
	JPanel WestPanel=new JPanel();
	JPanel EastPanel=new JPanel();

	//JLabel lblSpecimen=new JLabel("Specimen");
	JLabel lblComment=new JLabel("Comment");

	JLabel lblTimeOfCollection=new JLabel("Time Of Collection");
	JLabel lblTimeOfExamination=new JLabel("Time Of Examination");
	JLabel lblVolume=new JLabel("Volume");
	JLabel lblMethodOfCollec=new JLabel("Method Of Collection");
	JLabel lblLiquifaction=new JLabel("Liquifaction");
	JLabel lblPH=new JLabel("PH");
	JLabel lblSpermCount=new JLabel("Sperm Count");
	JLabel lblViscosity=new JLabel("Consistency");
	JLabel lblSpermMorpology=new JLabel("Morphology");
	JLabel lblSpermVitality=new JLabel("Sperm Vitality");
	JLabel lblSpermClumping=new JLabel("Sperm Clumping");

	JLabel lblSpermMotility=new JLabel("Sperm motility");
	JLabel lblTotalMotility=new JLabel("Total Motility");
	JLabel lblProgressiveMotility=new JLabel("Progressive Motility");
	JLabel lblNogProgressiveMotility=new JLabel("Non-Progressive Motility");
	JLabel lblNonMotile=new JLabel("Non-Motile");

	JLabel lblCells=new JLabel("Cells");
	JLabel lblPusCells=new JLabel("Pus Cells");
	JLabel lblEpithelialCell=new JLabel("Epithelial Cell");
	JLabel lblRedBloodCells=new JLabel("Red Blood Cells");
	JLabel lblGermCells=new JLabel("Germ Cells");

	JLabel lblFructoseTest=new JLabel("Fructose Test");
	JLabel lblOpinion=new JLabel("Opinion");


	//JTextField txtTimeOfCollection=new JTextField(20);
	//JTextField txtTimeOfExamination=new JTextField(20);

	//SuggestText cmbQuantity=new SuggestText();
	JTextField txtVolume=new JTextField(20);
	SuggestText cmbMethoOfCollection=new SuggestText();
	SuggestText cmbLiquefaction=new SuggestText();
	SuggestText cmbPH=new SuggestText();
	JTextField txtSpermCount=new JTextField(20);
	SuggestText cmbSpermVitality=new SuggestText();
	JTextField txtSpermMorphology=new JTextField(20);
	SuggestText cmbSpermClumping=new SuggestText();

	JTextField txtTotalMotility=new JTextField(20);
	JTextField txtProgressiveMotility=new JTextField(20);
	JTextField txtNonProgressiveMotility=new JTextField(20);
	JTextField txtNonMotility=new JTextField(20);

	SuggestText cmbPusCell=new SuggestText();
	SuggestText cmbEpithelialCell=new SuggestText();
	SuggestText cmbRedBloodCell=new SuggestText();
	SuggestText cmbGermCell=new SuggestText();

	SuggestText cmbFructoseTest=new SuggestText();
	JTextField txtOpinion=new JTextField(20);

	JSpinner txtTimeOfCollection=new JSpinner(new SpinnerDateModel());
	JSpinner.DateEditor Editor1=new JSpinner.DateEditor(txtTimeOfCollection, "hh:mm-a dd-MM-yyyy");

	JSpinner txtTimeOfExamination=new JSpinner(new SpinnerDateModel());
	JSpinner.DateEditor Editor2=new JSpinner.DateEditor(txtTimeOfExamination, "hh:mm-a dd-MM-yyyy");

	String comment[]={"Normozoospermia","Oligospermia","Azoospermia","Hyperzoospermia","Huemospermia"};
	JComboBox cmbComment=new JComboBox(comment);


	String specimen[]={"Blood","Urine","Stool","Pus","Sputum","Semen"};
	JComboBox cmbSpecimen=new JComboBox(specimen);

	GridBagConstraints grid=new GridBagConstraints();
	String autoId="",labbillId="",startdate="",testId="";
	Date insertDate=null;
	String username="",degreeName="";
	String FullName="";
	List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
	public Pathology(SessionBeam sessionbeam){
		this.sessionBeam=sessionbeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		cmp();
		setFixedData();
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
	public void RefreshEvent(){

		cmbMethoOfCollection.txtMrNo.setText("");
		txtVolume.setText("3 ml");
		cmbLiquefaction.txtMrNo.setText("Fishy");
		txtSpermCount.setText("");
		txtSpermMorphology.setText("");
		cmbSpermVitality.txtMrNo.setText("");
		cmbSpermClumping.txtMrNo.setText("");
		txtTotalMotility.setText("");
		txtProgressiveMotility.setText("");
		txtNonProgressiveMotility.setText("");
		txtNonMotility.setText("");
		cmbPusCell.txtMrNo.setText("");
		cmbEpithelialCell.txtMrNo.setText("");
		cmbRedBloodCell.txtMrNo.setText("");
		cmbGermCell.txtMrNo.setText("");
		cmbFructoseTest.txtMrNo.setText("");
		txtOpinion.setText("");
	}
	public void btnPrintEvent(String TestCode,String TestName,String Reg,String Name,String Age,String Month,String Day,String Cabin,String Sex,String Consultant,String labBillId,String labPid,Date ordate,String FiscalYear,String CMonth){
		try {

			JasperPrint jp=null;
			HashMap map=null;

			String ConsultantName="",Degree="";

			ResultSet rs=db.sta.executeQuery("select (select Name from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as DcName,(select Degree from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as DegreeName from TbLabPatient where labId='"+labBillId+"' and FiscalYear='"+FiscalYear+"' and CMonth='"+CMonth+"'");
			while(rs.next()){
				ConsultantName=rs.getString("DcName");
				Degree=rs.getString("DegreeName");
			}

			String sql="select tblabreportvalue.value,(select username from tblogin where user_id=tblabreportvalue.createBy)as username,tblabreporthead.Name,tblabreporthead.Catagory,tblabreporthead.Unit,tblabreporthead.Ranges from tblabreporthead join tblabreportvalue on tblabreportvalue.rId=tblabreporthead.Id where tblabreportvalue.labBillId='"+labBillId+"' and tblabreportvalue.labPId='"+labPid+"' and testCode='"+TestCode+"' and tblabreportvalue.FiscalYear='"+FiscalYear+"' and tblabreportvalue.CMonth='"+CMonth+"' ";
			System.out.println(sql);
			ResultSet rs1=db.sta.executeQuery("select tblabreportvalue.value,(select name from tblogin where user_id=tblabreportvalue.createBy) as FullName,(select Degree from tblogin where user_id=tblabreportvalue.createBy) as DegreeName,(select username from tblogin where user_id=tblabreportvalue.createBy)as username,tblabreporthead.Name,tblabreporthead.Catagory,tblabreporthead.Unit,tblabreporthead.Ranges from tblabreporthead join tblabreportvalue on tblabreportvalue.rId=tblabreporthead.Id where tblabreportvalue.labBillId='"+labBillId+"' and tblabreportvalue.labPId='"+labPid+"' and testCode='"+TestCode+"' and testCode='"+TestCode+"' and tblabreportvalue.FiscalYear='"+FiscalYear+"' and tblabreportvalue.CMonth='"+CMonth+"'  order by tblabreporthead.Sorting asc");
			while(rs1.next()){
				map=new HashMap();
				map.put("LabNo",labBillId);
				map.put("PatientName",Name);
				Age=!Age.equals("")?Age+"Y":"";
				Month=!Month.equals("")?Month+"M":"";
				Day=!Day.equals("")?Day+"D":"";
				map.put("Age",Age+" "+Month+" "+Day);
				map.put("Gender",Sex);
				map.put("OrderDate",new SimpleDateFormat("dd-MM-yyyy").format(ordate));
				map.put("ReportingDate",new SimpleDateFormat("dd-MM-yyyy").format(new Date()));	
				map.put("Consultant",ConsultantName);
				map.put("Degree",Degree);
				map.put("TestList",TestName);

				map.put("TestName",rs1.getString("Name"));
				map.put("Result",rs1.getString("value"));
				map.put("MainTestName",rs1.getString("Catagory"));
				map.put("Unit",rs1.getString("Unit"));
				map.put("NormalRange",rs1.getString("Ranges"));


				if(!cmbSpecimen.getSelectedItem().toString().isEmpty()){
					map.put("Sample",cmbSpecimen.getSelectedItem().toString());
				}
				list.add(map);
			}
			
			sql = "select * from tbLabInchargeConsultantDegree where designation = 'Lab Incharge'";
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
			
			String input = "NewFormetReport/Patholog.jrxml";
			JasperReport com=JasperCompileManager.compileReport(input);
			jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(list));
			JasperViewer.viewReport(jp, false);
			JasperPrintManager.printReport(jp, true);
			list.clear();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void setData(String labPid,String labId,Date date,String TestCode,String FiscalYear,String CMonth){
		try {
			RefreshEvent();
			//String sql="select *from tblabreportvalue where labPId='"+labPid+"' && labBillId='"+labId+"' && date='"+new SimpleDateFormat("yyyy-MM-dd").format(date)+"'";
			//System.out.println(sql);
			String sql="select *,(select name from tblogin where user_id=tblabreportvalue.createBy) as FullName,(select Degree from tblogin where user_id=tblabreportvalue.createBy) as DegreeName from tblabreportvalue where labPId='"+labPid+"' and labBillId='"+labId+"' and testCode='"+TestCode+"' and FiscalYear='"+FiscalYear+"' and CMonth='"+CMonth+"'";
			System.out.println(sql);
			ResultSet rs=db.sta.executeQuery(sql);

			while(rs.next()){
				if(Integer.parseInt(rs.getString("rId").toString())==265){

					SimpleDateFormat format = new SimpleDateFormat("hh:mm-a dd-MM-yyyy");

					txtTimeOfCollection.setValue(format.parseObject(rs.getString("value")));
				}
				else if(Integer.parseInt(rs.getString("rId").toString())==266){

					SimpleDateFormat format = new SimpleDateFormat("hh:mm-a dd-MM-yyyy");
					txtTimeOfExamination.setValue(format.parseObject(rs.getString("value")));
				}
				else if(Integer.parseInt(rs.getString("rId").toString())==267){
					cmbMethoOfCollection.txtMrNo.setText(rs.getString("value"));
				}
				else if(Integer.parseInt(rs.getString("rId").toString())==268){
					txtVolume.setText(rs.getString("value"));
				}
				else if(Integer.parseInt(rs.getString("rId").toString())==269){
					cmbLiquefaction.txtMrNo.setText(rs.getString("value"));
				}
				else if(Integer.parseInt(rs.getString("rId").toString())==270){
					cmbPH.txtMrNo.setText(rs.getString("value"));
				}
				else if(Integer.parseInt(rs.getString("rId").toString())==271){
					txtSpermCount.setText(rs.getString("value"));
				}
				else if(Integer.parseInt(rs.getString("rId").toString())==272){
					cmbSpermVitality.txtMrNo.setText(rs.getString("value"));
				}
				else if(Integer.parseInt(rs.getString("rId").toString())==273){
					cmbSpermVitality.txtMrNo.setText(rs.getString("value"));
				}
				else if(Integer.parseInt(rs.getString("rId").toString())==274){
					cmbSpermClumping.txtMrNo.setText(rs.getString("value"));
				}
				else if(Integer.parseInt(rs.getString("rId").toString())==275){
					txtTotalMotility.setText(rs.getString("value"));
				}
				else if(Integer.parseInt(rs.getString("rId").toString())==276){
					txtProgressiveMotility.setText(rs.getString("value"));
				}
				else if(Integer.parseInt(rs.getString("rId").toString())==277){
					txtNonProgressiveMotility.setText(rs.getString("value"));
				}
				else if(Integer.parseInt(rs.getString("rId").toString())==278){
					txtNonProgressiveMotility.setText(rs.getString("value"));
				}
				else if(Integer.parseInt(rs.getString("rId").toString())==279){
					cmbPusCell.txtMrNo.setText(rs.getString("value"));
				}
				else if(Integer.parseInt(rs.getString("rId").toString())==280){
					cmbEpithelialCell.txtMrNo.setText(rs.getString("value"));
				}
				else if(Integer.parseInt(rs.getString("rId").toString())==281){
					cmbRedBloodCell.txtMrNo.setText(rs.getString("value"));
				}
				else if(Integer.parseInt(rs.getString("rId").toString())==282){
					cmbGermCell.txtMrNo.setText(rs.getString("value"));
				}
				else if(Integer.parseInt(rs.getString("rId").toString())==283){
					cmbFructoseTest.txtMrNo.setText(rs.getString("value"));
				}
				else if(Integer.parseInt(rs.getString("rId").toString())==284){
					txtOpinion.setText(rs.getString("value"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void savebtnActionEvent(String s,String labId,Date date,String FiscalYear,String CMonth){
		testId=s;
		labbillId=labId;
		insertDate=date;
		String LabInchargeId="";
		String CheckedById="";
		String Doctor1Id="";
		String Doctor2Id="";
		try {
			int save=0;

			if(!txtTimeOfCollection.getValue().toString().isEmpty()){
				AutoId();
				SimpleDateFormat Timeformat = new SimpleDateFormat("hh:mm-a");
				SimpleDateFormat Dateformat = new SimpleDateFormat("dd-MM-yyyy");
				//Date time = new Date();
				Date time = (Date)txtTimeOfCollection.getValue();
				Date date1 = (Date)txtTimeOfCollection.getValue();
				String formattedTime = Timeformat.format(time);
				String formattedDate = Dateformat.format(date1);

				String TimeOfCollection=formattedTime+" "+formattedDate;
				if(!checkLabReport(265,labbillId,date,s)){
					//hh:mm-a dd-MM-yyyy


					insertdata(autoId,265,TimeOfCollection,insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
				else{
					Updatedata(265,TimeOfCollection,date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
			}
			/*if(!txtTimeOfExamination.getValue().toString().isEmpty()){
				AutoId();
				SimpleDateFormat Timeformat = new SimpleDateFormat("hh:mm-a");
				SimpleDateFormat Dateformat = new SimpleDateFormat("dd-MM-yyyy");
				//Date time = new Date();
				Date time = (Date)txtTimeOfExamination.getValue();
				Date date1 = (Date)txtTimeOfExamination.getValue();
				String formattedTime = Timeformat.format(time);
				String formattedDate = Dateformat.format(date1);

				String TimeOfExamination=formattedTime+" "+formattedDate;
				if(!checkLabReport(147,labbillId,date,s)){



					//String value=new SimpleDateFormat("hh:mm-a dd-MM-yyyy").format(txtTimeOfExamination.getValue().toString());
					insertdata(autoId,147,TimeOfExamination,insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
				else{
					Updatedata(147,TimeOfExamination,date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
			}*/

			if(!cmbMethoOfCollection.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(267,labbillId,date,s)){
					insertdata(autoId,267,cmbMethoOfCollection.txtMrNo.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
				else{
					Updatedata(267,cmbMethoOfCollection.txtMrNo.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
			}
			if(!txtVolume.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(268,labbillId,date,s)){
					insertdata(autoId,268,txtVolume.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
				else{
					Updatedata(268,txtVolume.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
			}
			if(!cmbLiquefaction.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(269,labbillId,date,s)){
					insertdata(autoId,269,cmbLiquefaction.txtMrNo.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
				else{
					Updatedata(269,cmbLiquefaction.txtMrNo.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
			}
			if(!cmbPH.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(270,labbillId,date,s)){
					insertdata(autoId,270,cmbPH.txtMrNo.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
				else{
					Updatedata(270,cmbPH.txtMrNo.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
			}
			if(!txtSpermCount.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(271,labbillId,date,s)){
					insertdata(autoId,271,txtSpermCount.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
				else{
					Updatedata(271,txtSpermCount.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
			}
			if(!txtSpermMorphology.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(272,labbillId,date,s)){
					insertdata(autoId,272,txtSpermMorphology.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
				else{
					Updatedata(272,txtSpermMorphology.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
			}
			if(!cmbSpermVitality.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(273,labbillId,date,s)){
					insertdata(autoId,273,cmbSpermVitality.txtMrNo.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
				else{
					Updatedata(273,cmbSpermVitality.txtMrNo.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
			}
			if(!cmbSpermClumping.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(274,labbillId,date,s)){
					insertdata(autoId,274,cmbSpermClumping.txtMrNo.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
				else{
					Updatedata(274,cmbSpermClumping.txtMrNo.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
			}
			if(!txtTotalMotility.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(275,labbillId,date,s)){
					insertdata(autoId,275,txtTotalMotility.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
				else{
					Updatedata(275,txtTotalMotility.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
			}
			if(!txtProgressiveMotility.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(276,labbillId,date,s)){
					insertdata(autoId,276,txtProgressiveMotility.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
				else{
					Updatedata(276,txtProgressiveMotility.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
			}
			if(!txtNonProgressiveMotility.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(277,labbillId,date,s)){
					insertdata(autoId,277,txtNonProgressiveMotility.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
				else{
					Updatedata(277,txtNonProgressiveMotility.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
			}
			if(!txtNonMotility.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(278,labbillId,date,s)){
					insertdata(autoId,278,txtNonMotility.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
				else{
					Updatedata(278,txtNonMotility.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
			}

			if(!cmbPusCell.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(279,labbillId,date,s)){
					insertdata(autoId,279,cmbPusCell.txtMrNo.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
				else{
					Updatedata(279,cmbPusCell.txtMrNo.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
			}
			if(!cmbEpithelialCell.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(280,labbillId,date,s)){
					insertdata(autoId,280,cmbEpithelialCell.txtMrNo.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
				else{
					Updatedata(280,cmbEpithelialCell.txtMrNo.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
			}
			if(!cmbRedBloodCell.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(281,labbillId,date,s)){
					insertdata(autoId,281,cmbRedBloodCell.txtMrNo.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
				else{
					Updatedata(281,cmbRedBloodCell.txtMrNo.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
			}

			if(!cmbGermCell.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(282,labbillId,date,s)){
					insertdata(autoId,282,cmbGermCell.txtMrNo.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
				else{
					Updatedata(282,cmbGermCell.txtMrNo.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
			}
			
			if(!cmbFructoseTest.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(283,labbillId,date,s)){
					insertdata(autoId,283,cmbFructoseTest.txtMrNo.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
				else{
					Updatedata(283,cmbFructoseTest.txtMrNo.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
			}
			
			if(!txtOpinion.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(284,labbillId,date,s)){
					insertdata(autoId,284,txtOpinion.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
				else{
					Updatedata(284,txtOpinion.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,CMonth);
					save++;
				}
			}
			
			

			if(save!=0){
				String sql="update tblabtesthistory set ResultStatus='DONE' where  labId='"+labbillId+"' and testCode='"+testId+"'" ;
				System.out.println(sql);
				db.sta.executeUpdate(sql);
				//JOptionPane.showMessageDialog(null, "Pathology Report Sucessfully Complete");
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void insertdata(String autoID,int rId,String value,Date date,String TestCode,String LabInchargeId,String CheckedById,String Doctor1Id,String Doctor2Id,String FiscalYear,String CMonth){
		try {

			String sql="insert into tblabreportvalue (autoId,rId,value,Flag,labPId,labBillId,testCode,Sorting,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,date,entryTime,createBy,FiscalYear,CMonth) values ('"+autoID+"',"
					+ "'"+rId+"',"
					+ "'"+value+"','','10',"
					+ "'"+labbillId+"',"
					+ "'"+TestCode+"',"
					+ "' ',"
					+ "'"+LabInchargeId+"',"
					+ "'"+CheckedById+"',"
					+ "'"+Doctor1Id+"',"
					+ "'"+Doctor2Id+"',"
					+ "'"+new SimpleDateFormat("yyyy-MM-dd").format(date)+"',"
					+ "CURRENT_TIMESTAMP,"
					+ "'"+sessionBeam.getUserId()+"','"+FiscalYear+"','"+CMonth+"')";
			System.out.println(sql);
			db.sta.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void Updatedata(int rId,String value,Date date,String labbillId,String TestCode,String LabInchargeId,String CheckedById,String Doctor1Id,String Doctor2Id,String FiscalYear,String CMonth){
		try {
			String sql="update tblabreportvalue set "
					+ " rId='"+rId+"',"
					+ "value='"+value+"',labPId='10',"
					+ "labBillId='"+labbillId+"',"
					+ "testCode='"+TestCode+"',"
					+ "LabInchargeId='"+LabInchargeId+"',"
					+ "CheckedById='"+CheckedById+"',"
					+ "Doctor1Id='"+Doctor1Id+"',"
					+ "Doctor2Id='"+Doctor2Id+"',"
					+ "date='"+new SimpleDateFormat("yyyy-MM-dd").format(date)+"',"
					+ "entryTime=CURRENT_TIMESTAMP,"
					+ "createBy='"+sessionBeam.getUserId()+"' where rId='"+rId+"'  and labPId='10' and labBillId='"+labbillId+"' and FiscalYear='"+FiscalYear+"' and CMonth='"+CMonth+"' ";
			System.out.println(sql);
			db.sta.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public boolean checkLabReport(int rId,String labId,Date date,String TestCode){
		try {
			ResultSet rs=db.sta.executeQuery("select * from tblabreportvalue where  labBillId='"+labId+"' and labPId='10' and rId='"+rId+"' and testCode='"+TestCode+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private void setFixedData(){
		/*txtVolume.setText("3 ml");
		cmbMethoOfCollection.txtMrNo.setText("Whitsh");
		cmbLiquefaction.txtMrNo.setText("Fishy");
		txtSpermCount.setText("55 million/ml      (More than 40 million/ml)");*/
		
		cmbMethoOfCollection.v.add("Masturbation");
		cmbMethoOfCollection.v.add("Coitus");
		
		cmbLiquefaction.v.add("Complete liquefaction");
		cmbLiquefaction.v.add("Incomplete liquefaction");
		
		cmbPH.v.add("Acid");
		cmbPH.v.add("Alkaine");
		
		cmbSpermVitality.v.add("Not done");
		
		cmbSpermClumping.v.add("Present");
		cmbFructoseTest.v.add("Not done");
		
		cmbPusCell.v.add("2-3/HPF");
		cmbEpithelialCell.v.add("2-4/HPF");
		cmbRedBloodCell.v.add("0-1/HPF");
		cmbGermCell.v.add("Absent");
		
	}

	public void cmp(){
		add(mainpanel);
		mainpanel.setLayout(new GridBagLayout());
		mainpanel.setOpaque(false);
		mainpanel.setPreferredSize(new Dimension(1100, 460));
		mainpanel.setLayout(new GridBagLayout());
		//mainpanel.setBorder(BorderFactory.createTitledBorder("Semen Analysis Report"));
		/*		mainpanel.add(WestPanel,BorderLayout.WEST);
		WestPanel_work();
		mainpanel.add(EastPanel,BorderLayout.EAST);
		
		EastPanel_work();*/

		grid.fill=new GridBagConstraints().BOTH;
		grid.insets  =new Insets(1, 1, 1, 1);
		grid.gridx=0;
		grid.gridy=0;
		JLabel lblTime=new JLabel("Time");
		mainpanel.add(lblTime,grid);
		lblTime.setFont(new Font("arial", Font.BOLD, 16));
		lblTime.setForeground(Color.red);

		grid.insets  =new Insets(1, 45, 1, 1);
		grid.gridx=0;
		grid.gridy=1;
		mainpanel.add(lblTimeOfCollection,grid);
		
		grid.gridx=0;
		grid.gridy=2;
		mainpanel.add(lblTimeOfExamination,grid);
		
		grid.insets  =new Insets(1, 1, 1, 1);
		grid.gridx=0;
		grid.gridy=3;
		JLabel lblPysicalExamination=new JLabel("Physical Examination");
		mainpanel.add(lblPysicalExamination,grid);
		lblPysicalExamination.setFont(new Font("arial", Font.BOLD, 16));
		lblPysicalExamination.setForeground(Color.red);

		grid.insets  =new Insets(1, 45, 1, 1);
		grid.gridx=0;
		grid.gridy=4;
		mainpanel.add(lblMethodOfCollec,grid);
		
		grid.gridx=0;
		grid.gridy=5;
		mainpanel.add(lblVolume,grid);
		
		grid.gridx=0;
		grid.gridy=6;
		mainpanel.add(lblLiquifaction,grid);
		
		grid.gridx=0;
		grid.gridy=7;
		mainpanel.add(lblPH,grid);
		
		
		grid.insets  =new Insets(1, 1, 1, 1);
		grid.gridx=0;
		grid.gridy=8;
		JLabel lblMicroscopicExamination=new JLabel("Microscopic Examination");
		mainpanel.add(lblMicroscopicExamination,grid);
		lblMicroscopicExamination.setFont(new Font("arial", Font.BOLD, 16));
		lblMicroscopicExamination.setForeground(Color.red);

		grid.insets  =new Insets(1, 45, 1, 1);
		grid.gridx=0;
		grid.gridy=9;
		mainpanel.add(lblSpermCount,grid);
		
		grid.gridx=0;
		grid.gridy=10;
		mainpanel.add(lblSpermMorpology,grid);
		
		grid.gridx=0;
		grid.gridy=11;
		mainpanel.add(lblSpermVitality,grid);
		
		grid.gridx=0;
		grid.gridy=12;
		mainpanel.add(lblSpermClumping,grid);

		grid.insets  =new Insets(1, 0, 1, 1);
		grid.gridx=1;
		grid.gridy=1;
		mainpanel.add(txtTimeOfCollection,grid);
		txtTimeOfCollection.setEditor(Editor1);
		txtTimeOfCollection.setPreferredSize(new Dimension(230, 30));


		grid.gridx=1;
		grid.gridy=2;
		mainpanel.add(txtTimeOfExamination,grid);
		txtTimeOfExamination.setEditor(Editor2);
		txtTimeOfExamination.setPreferredSize(new Dimension(230, 30));


		/*		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(0, 5, 0, 5);
		mainpanel.add(lblSpecimen,grid);
		lblSpecimen.setFont(new Font("arial", Font.BOLD, 14));
		lblSpecimen.setForeground(Color.red);
		 */
		/*		grid.gridx=1;
		grid.gridy=0;
		mainpanel.add(cmbSpecimen,grid);
		cmbSpecimen.setPreferredSize(new Dimension(150, 30));*/
		

		grid.gridx=1;
		grid.gridy=4;
		mainpanel.add(cmbMethoOfCollection.combo,grid);
		cmbMethoOfCollection.combo.setPreferredSize(new Dimension(230, 30));


		grid.gridx=1;
		grid.gridy=5;
		mainpanel.add(txtVolume,grid);
		txtVolume.setPreferredSize(new Dimension(230, 30));


		grid.gridx=1;
		grid.gridy=6;
		mainpanel.add(cmbLiquefaction.combo,grid);
		cmbLiquefaction.combo.setPreferredSize(new Dimension(230, 30));

		grid.gridx=1;
		grid.gridy=7;
		mainpanel.add(cmbPH.combo,grid);
		cmbPH.combo.setPreferredSize(new Dimension(230, 30));

		

		grid.gridx=1;
		grid.gridy=9;
		mainpanel.add(txtSpermCount,grid);


		grid.gridx=1;
		grid.gridy=10;
		mainpanel.add(txtSpermMorphology,grid);

		

		grid.gridx=1;
		grid.gridy=11;
		mainpanel.add(cmbSpermVitality.combo,grid);

		grid.gridx=1;
		grid.gridy=12;
		mainpanel.add(cmbSpermClumping.combo,grid);
		
		
		grid.insets  =new Insets(1, 30, 1, 1);
		grid.gridx=2;
		grid.gridy=0;
		mainpanel.add(lblSpermMotility,grid);
		lblSpermMotility.setFont(new Font("arial", Font.BOLD, 16));
		lblSpermMotility.setForeground(Color.red);

		grid.insets  =new Insets(1, 45, 1, 1);
		grid.gridx=2;
		grid.gridy=1;
		mainpanel.add(lblTotalMotility,grid);
		
		grid.gridx=2;
		grid.gridy=2;
		mainpanel.add(lblProgressiveMotility,grid);
		
		grid.gridx=2;
		grid.gridy=3;
		mainpanel.add(lblNogProgressiveMotility,grid);
		
		grid.gridx=2;
		grid.gridy=4;
		mainpanel.add(lblNonMotile,grid);
		
		grid.insets  =new Insets(1, 30, 1, 1);
		grid.gridx=2;
		grid.gridy=5;
		mainpanel.add(lblCells,grid);
		lblCells.setFont(new Font("arial", Font.BOLD, 16));
		lblCells.setForeground(Color.red);

		grid.insets  =new Insets(1, 45, 1, 1);
		grid.gridx=2;
		grid.gridy=6;
		mainpanel.add(lblPusCells,grid);
		
		grid.gridx=2;
		grid.gridy=7;
		mainpanel.add(lblEpithelialCell,grid);
		
		grid.gridx=2;
		grid.gridy=8;
		mainpanel.add(lblRedBloodCells,grid);
		
		grid.gridx=2;
		grid.gridy=9;
		mainpanel.add(lblGermCells,grid);
		
		grid.gridx=2;
		grid.gridy=10;
		mainpanel.add(lblFructoseTest,grid);
		
		grid.gridx=2;
		grid.gridy=11;
		mainpanel.add(lblOpinion,grid);
		
		
		grid.insets  =new Insets(1, 0, 1, 1);
		grid.gridx=3;
		grid.gridy=1;
		mainpanel.add(txtTotalMotility,grid);
		txtTotalMotility.setPreferredSize(new Dimension(230, 30));

		grid.gridx=3;
		grid.gridy=2;
		mainpanel.add(txtProgressiveMotility,grid);
		txtProgressiveMotility.setPreferredSize(new Dimension(230, 30));

		grid.gridx=3;
		grid.gridy=3;
		mainpanel.add(txtNonProgressiveMotility,grid);
		txtNonProgressiveMotility.setPreferredSize(new Dimension(230, 30));

		grid.gridx=3;
		grid.gridy=4;
		mainpanel.add(txtNonMotility,grid);
		txtNonMotility.setPreferredSize(new Dimension(230, 30));

		grid.gridx=3;
		grid.gridy=6;
		mainpanel.add(cmbPusCell.combo,grid);
		cmbPusCell.combo.setPreferredSize(new Dimension(230, 30));

		grid.gridx=3;
		grid.gridy=7;
		mainpanel.add(cmbEpithelialCell.combo,grid);
		cmbEpithelialCell.combo.setPreferredSize(new Dimension(230, 30));

		grid.gridx=3;
		grid.gridy=8;
		mainpanel.add(cmbRedBloodCell.combo,grid);
		cmbRedBloodCell.combo.setPreferredSize(new Dimension(230, 30));

		grid.gridx=3;
		grid.gridy=9;
		mainpanel.add(cmbGermCell.combo,grid);
		cmbGermCell.combo.setPreferredSize(new Dimension(230, 30));

		grid.gridx=3;
		grid.gridy=10;
		mainpanel.add(cmbFructoseTest.combo,grid);
		cmbFructoseTest.combo.setPreferredSize(new Dimension(230, 30));

		grid.gridx=3;
		grid.gridy=11;
		mainpanel.add(txtOpinion,grid);
		txtOpinion.setPreferredSize(new Dimension(230, 30));


		final Component ob[] = {txtTimeOfCollection,cmbMethoOfCollection.txtMrNo,txtVolume ,cmbLiquefaction.txtMrNo,cmbPH.txtMrNo,txtSpermCount,txtSpermMorphology,cmbSpermVitality.txtMrNo,cmbSpermClumping.txtMrNo,txtTotalMotility,txtProgressiveMotility,txtNonProgressiveMotility,txtNonMotility,cmbPusCell.txtMrNo,cmbEpithelialCell.txtMrNo,cmbRedBloodCell.txtMrNo,cmbGermCell.txtMrNo,cmbFructoseTest.txtMrNo,txtOpinion};	
		new FocusMoveByEnter(ob);

		/*		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(0, 5, 0, 5);
		mainpanel.add(lblSpecimen,grid);

		grid.gridx=1;
		grid.gridy=0;
		mainpanel.add(cmbSpecimen,grid);

		grid.gridx=0;
		grid.gridy=2;
		mainpanel.add(lblQuantity,grid);

		grid.gridx=1;
		grid.gridy=2;
		mainpanel.add(txtQuantity,grid);

		grid.gridx=0;
		grid.gridy=3;
		mainpanel.add(lblColour,grid);

		grid.gridx=1;
		grid.gridy=3;
		mainpanel.add(txtColour,grid);

		grid.gridx=0;
		grid.gridy=4;
		mainpanel.add(lblOdour,grid);

		grid.gridx=1;
		grid.gridy=4;
		mainpanel.add(txtOdour,grid);

		grid.gridx=0;
		grid.gridy=5;
		mainpanel.add(lblViscosity,grid);

		grid.gridx=1;
		grid.gridy=5;
		mainpanel.add(txtViscosity,grid);

		grid.gridx=0;
		grid.gridy=6;
		mainpanel.add(lblSpermCount,grid);
		lblSpermCount.setFont(new Font("arial", Font.BOLD, 14));
		lblSpermCount.setForeground(Color.red);

		grid.gridx=1;
		grid.gridy=6;
		mainpanel.add(txtSpermCount,grid);

		grid.gridx=0;
		grid.gridy=7;
		mainpanel.add(lblActiveMotility,grid);

		grid.gridx=1;
		grid.gridy=7;
		mainpanel.add(txtActiveMotility,grid);

		grid.gridx=0;
		grid.gridy=8;
		mainpanel.add(lblWeeklyMotility,grid);

		grid.gridx=1;
		grid.gridy=8;
		mainpanel.add(txtWeeklyMotility,grid);

		grid.gridx=0;
		grid.gridy=9;
		mainpanel.add(lblNoneMotile,grid);

		grid.gridx=1;
		grid.gridy=9;
		mainpanel.add(txtNoneMotile,grid);

		grid.gridx=0;
		grid.gridy=10;
		mainpanel.add(lblMorphology,grid);

		grid.gridx=1;
		grid.gridy=10;
		mainpanel.add(txtMorphology,grid);

		grid.gridx=0;
		grid.gridy=11;
		mainpanel.add(lblOtherCells,grid);
		lblOtherCells.setFont(new Font("arial", Font.BOLD, 14));
		lblOtherCells.setForeground(Color.red);

		grid.gridx=0;
		grid.gridy=12;
		mainpanel.add(lblPusCells,grid);

		grid.gridx=1;
		grid.gridy=12;
		mainpanel.add(txtPusCell,grid);

		grid.gridx=0;
		grid.gridy=13;
		mainpanel.add(lblEpithelialCell,grid);

		grid.gridx=1;
		grid.gridy=13;
		mainpanel.add(txtEpithelialCell,grid);

		grid.gridx=0;
		grid.gridy=14;
		mainpanel.add(lblRedBloodCells,grid);

		grid.gridx=1;
		grid.gridy=14;
		mainpanel.add(txtRedBloodCell,grid);

		grid.gridx=0;
		grid.gridy=15;
		mainpanel.add(lblComment,grid);
		lblComment.setFont(new Font("arial", Font.BOLD, 14));
		lblComment.setForeground(Color.red);

		grid.gridx=1;
		grid.gridy=15;
		mainpanel.add(cmbComment,grid);
		cmbComment.setPreferredSize(new Dimension(240, 30));

		final Component ob[] = {txtQuantity,txtColour, txtOdour,txtViscosity,txtSpermCount,txtActiveMotility,txtWeeklyMotility,txtNoneMotile,txtMorphology,txtPusCell,txtEpithelialCell,txtRedBloodCell,cmbComment};	
		new FocusMoveByEnter(ob);*/

	}
	/*private void WestPanel_work(){
		WestPanel.setOpaque(false);
		WestPanel.setPreferredSize(new Dimension(500, 360));
		WestPanel.setBorder(BorderFactory.createTitledBorder("Physical Examination"));
		WestPanel.setLayout(new GridBagLayout());
	}
	private void EastPanel_work(){
		EastPanel.setOpaque(false);
		EastPanel.setPreferredSize(new Dimension(500, 360));
		EastPanel.setBorder(BorderFactory.createTitledBorder("Motility Examination"));
		EastPanel.setLayout(new GridBagLayout());
	}*/
}
