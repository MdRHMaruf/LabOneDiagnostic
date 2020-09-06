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
import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.ShareClass.AdvancedSearch;
import com.ShareClass.ButtonColumn;
import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
import com.ShareClass.db_coonection;
import com.toedter.calendar.JDateChooser;

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

public class LabBilling extends JPanel{
	HashMap map=new HashMap<>();
	db_coonection db=new db_coonection();

	AdvancedSearch advancedSearch=new AdvancedSearch(this);
	SessionBeam sessionBeam;
	JPanel mainPanel=new JPanel();
	JPanel NorthPanel=new JPanel();
	JPanel NorthWestPanel=new JPanel();
	JPanel NorthCenterPanel=new JPanel();
	JPanel NorthEastPanel=new JPanel();
	JPanel CenterPanel=new JPanel();
	JPanel CenterNorthPanel=new JPanel();
	JPanel CenterSouthPanel=new JPanel();
	JPanel SouthPanel=new JPanel();
	JPanel SouthNorthPanel=new JPanel();
	JPanel SouthSouthPanel=new JPanel();
	JLabel lblMrNo=new JLabel("MR.No    ");
	JLabel lblRegNo=new JLabel("Reg.No");
	JLabel lblBC=new JLabel("Bed/Cabin");
	JLabel lblDateTime=new JLabel("Date Time");
	JLabel lblPatientName=new JLabel("Patient Name");
	JLabel lblPhone=new JLabel("Mobile ");
	JLabel lblAge=new JLabel("Age");
	JLabel lblMonth=new JLabel("M");
	JLabel lblDay=new JLabel("D");
	JLabel lblSex=new JLabel("S");
	JLabel lblRefferBy=new JLabel("Reffer");
	JLabel lblRFPersion=new JLabel("C Person");
	JLabel lblAddress=new JLabel("Address");
	JLabel lblSampleCollection=new JLabel("Sample.C");
	JTextField txtSampleCollection=new JTextField(14);


	JLabel lblTDC=new JLabel("T.DC");
	JLabel lblTCash=new JLabel("T.Cash   ");
	JLabel lblUserCash=new JLabel("User TC");
	JLabel lblRegistrationNo=new JLabel("Reg.No ");
	JLabel lblCabin=new JLabel("Cabin     ");

	JTextField txtDoctorDegree=new JTextField(10);

	JTextField txtRegistrationNo=new JTextField(11);
	JTextField txtCabin=new JTextField(11);
	/*	String Mode[]={"General","Corporate","Director","Share Holder"};
	JComboBox cmbMode=new JComboBox(Mode);*/

	//SpinnerNumberModel nmodel=new SpinnerNumberModel(0, 0, 100000000, 1);
	public SuggestText cmbFindLabBill=new SuggestText();
	JButton btnFind=new JButton(new ImageIcon("icon/find.png"));

	JButton btnRefund=new JButton("Refund",new ImageIcon("icon/Request-Icon.png"));
	JButton btnRefundSlip=new JButton("Print Refund Slip",new ImageIcon("icon/print.png"));


	JLabel lblFiscalYear=new JLabel("Fiscal Year");
	JLabel lblBillFiscalYear=new JLabel("Fiscal Year");


	JLabel lblBillMonth=new JLabel("Month");
	String BillMonth[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
	public JComboBox cmbBillMonth=new JComboBox(BillMonth);


	String FiscalYear[]={"2017","2018","2019","2020","2021","2022","2023","2024","2025"};
	public JComboBox cmbFiscalYear=new JComboBox(FiscalYear);

	JTextField txtBillFiscalYear=new JTextField(4);
	JTextField txtBillMonth=new JTextField(4);
	JLabel lblTestCode=new JLabel("Test Code");
	JLabel lblTestName=new JLabel("Test Name");
	JLabel lblDiscount=new JLabel("Discount (%)");
	JLabel lblRate=new JLabel("Rate");
	JLabel lblSpecialDiscountTitle=new JLabel("Special Discount In Taka");
	JLabel lblAmount=new JLabel("Amoun");
	JLabel lblManualPercentice=new JLabel("M. Discount ");
	JLabel lblRemark=new JLabel("Remark     ");
	JLabel lblGrandTotal=new JLabel("Total");
	JTextField txtAmount=new JTextField(5);
	JTextField txtManualDiscount=new JTextField(4);
	//JTextField txtCO=new JTextField(24);
	JTextField txtGrandTotal=new JTextField(5);

	JLabel lblReportDelivery=new JLabel("Delivery");
	JLabel lblDate=new JLabel("Date          ");
	JLabel lblTime=new JLabel("Time");

	JLabel lblPercentDiscount=new JLabel("Discount % ");
	JTextField txtPercentDiscount=new JTextField(4);

	JLabel lblDiagnosis=new JLabel("Diagnosis");
	JLabel lblPrimary=new JLabel("Primary     ");
	JLabel lblClinical=new JLabel("Clinical     ");
	JTextField txtPrimary=new JTextField(24);
	JTextField txtClinical=new JTextField(24);

	JLabel lblTotalCharge=new JLabel("Total");
	JLabel lblTotalPayable=new JLabel("Total Payable");
	JLabel lblVat=new JLabel("Vat");
	JLabel lblGeneralDiscount=new JLabel("G. Discount");
	JLabel lblPaidInCash=new JLabel("Paid In Cash  ");
	JLabel lblSpDiscount=new JLabel("Sp. Discount");
	JLabel lblDues=new JLabel("Dues");
	JLabel lblAdvancePaid=new JLabel("Paid");
	JLabel lblCO=new JLabel("Remark  ");
	JLabel lblRefund=new JLabel("Refund");


	String PatientType[]={"Outdoor","Indoor"};
	JComboBox cmbPatientType=new JComboBox(PatientType);
	JTextField txtTotalCharge=new JTextField(5);
	JTextField txtTotalPayable=new JTextField(5);
	JTextField txtVat=new JTextField(5);
	JTextField txtGeneralDiscount=new JTextField(4);
	JTextField txtPaidInCash=new JTextField(5);
	JTextField txtSpDiscount=new JTextField(4);
	JTextField txtDues=new JTextField(5);
	JTextField txtAdvancepaid=new JTextField(5);
	JTextField txtCO=new JTextField(14);
	JTextField txtRefund=new JTextField(5);
	//JTextField txtReturn=new JTextField(10);


	JTextField txtTime=new JTextField(4);

	JSpinner txtDate=new JSpinner(new SpinnerDateModel());
	JSpinner.DateEditor Editor=new JSpinner.DateEditor(txtDate, "dd:MM:yyyy");

	SuggestText cmbTestCode=new SuggestText();
	SuggestText cmbTestName=new SuggestText();
	SuggestText cmbDiscount=new SuggestText();
	JTextField txtRate=new JTextField(8);
	JTextField txtPatientName=new JTextField(20);
	JTextField txtPhone=new JTextField(10);
	JTextField txtAge=new JTextField(2);
	JTextField txtMonth=new JTextField(2);
	JTextField txtDay=new JTextField(2);
	String sex[]={"","Male","Female","Other"};
	JComboBox cmbsex=new JComboBox(sex);
	SuggestText cmbRefferBy=new SuggestText();
	SuggestText cmbRFPersion=new SuggestText();



	JTextField txtAddress=new JTextField(10);

	JTextField txtTDc=new JTextField(5);
	JTextField txtTcash=new JTextField(5);
	JTextField txtUserCash=new JTextField(5);
	JTextField txtMrNo=new JTextField(6);
	SuggestText cmbRegNo=new SuggestText();
	JTextField txtBC=new JTextField(8);
	JDateChooser txtDateSN=new JDateChooser();
	JDateChooser txtDateTime=new JDateChooser();

	JButton btnCounter1=new JButton("C1");
	JButton btnCounter2=new JButton("C2");
	JButton btnCounter3=new JButton("C3");
	JButton btnCounter4=new JButton("C4");
	JButton btnCounter5=new JButton("C5");

	JRadioButton btnMoneyReceipt=new JRadioButton("Money Receipt");
	JRadioButton btnLabSlip=new JRadioButton("Lab Slip");

	JButton btnAdvancedSearch=new JButton("A.Search",new ImageIcon("icon/Preview.png"));
	JButton btnAdd=new JButton(new ImageIcon("icon/Add.png"));
	JButton btnAddDoctor=new JButton(new ImageIcon("icon/Add.png"));
	JButton btnTestSubmit=new JButton("Add");
	JButton btnConfrim=new JButton("Confrim",new ImageIcon("icon/save.png"));
	JButton btnPost=new JButton("Post",new ImageIcon("icon/save.png"));
	JButton btnEdit=new JButton("Edit",new ImageIcon("icon/edt.png"));
	JButton btnPrint=new JButton("Print Lab Bill",new ImageIcon("icon/print.png"));
	JButton btnMoneyReceipte=new JButton("Print Money Receipte ",new ImageIcon("icon/print.png"));
	JButton btnClear=new JButton("Clear",new ImageIcon("icon/reresh.png"));
	JButton btnDeletePendingInvoice=new JButton("Delete Counter Pending Bill",new ImageIcon("icon/reresh.png"));
	JButton btnReset=new JButton(new ImageIcon("icon/reresh.png"));
	String startDate="";


	String Col[]={"SL#","S/N","T.ID","Name Of Test","Qty","Rate","Discount","Amount","DEL","Refund"};
	Object Row[][]={};
	DefaultTableModel model=new DefaultTableModel(Row,Col);
	JTable table=new JTable(model){
		public boolean isCellEditable(int row,int col){
			if(col==4 || col==8 || col==9){
				return true;
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
		public Class getColumnClass(int Column){
			switch (Column) {
			case 9:
				return Boolean.class; 
			case 10:
				return Boolean.class; 
			default:
				return String.class;
			}
		}
	};
	JScrollPane Scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


	String ColR[]={"Add","Test Group","Test Name","Rate"};
	Object RowR[][]={};
	DefaultTableModel modelR=new DefaultTableModel(RowR,ColR);
	JTable tableR=new JTable(modelR){
		public boolean isCellEditable(int row,int col){
			if(col==0 || col==3){
				return true;
			}
			else{
				return false;
			}

		}
	};
	JScrollPane ScrollR=new JScrollPane(tableR,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);




	/*	String RegCol[]={"Reg No","Status"};
	Object RegRow[][]={};
	DefaultTableModel Regmodel=new DefaultTableModel(RegRow,RegCol);
	JTable Regtable=new JTable(Regmodel);
	JScrollPane RegScroll=new JScrollPane(Regtable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);*/
	GridBagConstraints grid=new GridBagConstraints();
	int Instatus=1,confrim=0,find=0;
	String autoLabBillId="",autoLabTestId="";
	double discount=0,fixTotaldiscount=0,totalRate=0.0,vatValue=0.0,paidValue=0.0,GrossAmount=0.0;
	String time="",transectionId="",FinaltransectionId="",f_d_l_id="",f_c_l_id="",SnId="",type="";

	List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
	String mcol[]={"Reg. No.","Massage","Time","Check"};
	Object mrow[][]={};
	DefaultTableModel mmodel=new DefaultTableModel(mrow,mcol);
	JTable mtable=new JTable(mmodel){
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
	JScrollPane mScroll=new JScrollPane(mtable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	ArrayList lablist=new  ArrayList();
	String user="";
	int check=0,finalBill=0;
	BufferedImage image;
	DecimalFormat df = new DecimalFormat("#.00");
	int counter=1;
	String paymentId="";
	double MainTestValue=0.0;
	public LabBilling(SessionBeam sessionBeam) {
		this.sessionBeam=sessionBeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}

		map.put("orgName",sessionBeam.getOrgName());
		map.put("orgAddress",sessionBeam.getOrgAddress());
		map.put("orgMobile",sessionBeam.getOrgMobile());
		addCmp();
		rowAdd();
		btnActionEvent();
		autoMRPId();
		background();
		ModelEventAction();
		btnClearEvent();
		resetFiscalYear();
		//showData("select *from tblabtesthistory where tblabtesthistory.labId IS NULL");
	}

	private void resetFiscalYear() {
		Calendar cal = Calendar.getInstance();
		cmbBillMonth.setSelectedItem(new SimpleDateFormat("MMMM").format(cal.getTime()));
		cmbFiscalYear.setSelectedItem(new SimpleDateFormat("yyyy").format(cal.getTime()));
	}


	private void ModelEventAction(){
		try {
			model.addTableModelListener(new TableModelListener() {

				@Override
				public void tableChanged(TableModelEvent e) {
					if(e.getType()==TableModelEvent.UPDATE){
						int Row=e.getFirstRow();
						int Col=e.getColumn();
						/*						if(Col==4){
							if(!table.getValueAt(Row, Col).toString().isEmpty()){
								int qty=Integer.parseInt(table.getValueAt(Row, Col).toString());

								if(qty>0){	
									try {

										String Bill="",FiscalYear="",BillYear="";
										StringTokenizer token=new StringTokenizer(cmbFindLabBill.txtMrNo.getText().trim().toString()," ");
										while(token.hasMoreTokens()){
											BillYear=token.nextToken();
											break;
										}

										int i=0;
										StringTokenizer Toekn=new StringTokenizer(BillYear,"/");
										while(Toekn.hasMoreTokens()){
											if(i==0){
												Bill=Toekn.nextToken();
											}
											if(i==1){
												FiscalYear=Toekn.nextToken();
											}
											i++;
										}

										double rate=Double.parseDouble(table.getValueAt(Row, 5).toString());
										double discount=0;
										String sql=find==0?"select discount from tblabtesthistory where FiscalYear='"+getFiscelYear()+"' and labId IS NULL and testName='"+table.getValueAt(Row, 3).toString()+"' and counter='"+counter+"' and createBy='"+sessionBeam.getUserId()+"'":"select discount from tblabtesthistory where FiscalYear='"+FiscalYear+"' and labId='"+Bill+"' and testName='"+table.getValueAt(Row, 3).toString()+"'";
										ResultSet rs=db.sta.executeQuery(sql);
										while(rs.next()){
											discount=discount+Double.parseDouble(rs.getString("discount"));
										}
										double netAmount=Double.parseDouble(table.getValueAt(Row, 7).toString());
										table.setValueAt((rate*discount/100)*qty, Row, 6);
										table.setValueAt((rate*qty)-(rate*discount/100)*qty, Row, 7);

										String update=find==0?"update tblabtesthistory set qty='"+qty+"',testName='"+table.getValueAt(Row, 3).toString()+"' where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.labId IS NULL and testName='"+table.getValueAt(Row, 3).toString()+"' and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"'":"update tblabtesthistory set qty='"+qty+"',testName='"+table.getValueAt(Row, 3).toString()+"' where tblabtesthistory.FiscalYear='"+FiscalYear+"' and tblabtesthistory.labId='"+Bill+"' and testName='"+table.getValueAt(Row, 3).toString()+"' ";
										db.sta.executeUpdate(update);

										String Udupdate=find==0?"update tbUdlabtesthistory set qty='"+qty+"',testName='"+table.getValueAt(Row, 3).toString()+"' where tbUdlabtesthistory.FiscalYear='"+getFiscelYear()+"' and tbUdlabtesthistory.labId IS NULL and testName='"+table.getValueAt(Row, 3).toString()+"' and tbUdlabtesthistory.createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"'":"update tbUdlabtesthistory set qty='"+qty+"',testName='"+table.getValueAt(Row, 3).toString()+"' where  tbUdlabtesthistory.FiscalYear='"+FiscalYear+"' and tbUdlabtesthistory.labId='"+Bill+"' and testName='"+table.getValueAt(Row, 3).toString()+"' ";
										db.sta.executeUpdate(Udupdate);

										if(find==0){
											showData("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='"+counter+"' order by type asc");
										}
										else{
											showData("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+FiscalYear+"' and tblabtesthistory.labId='"+Bill+"' order by type asc");
										}



									} catch (Exception e2) {
										e2.printStackTrace();
										JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
									}
								}
							}
							else{
								table.setValueAt(1, Row, Col);
							}
						}*/

					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	public void DeleteNullData(){
		try {
			String sql="delete from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"' and tblabtesthistory.CreateBy='"+sessionBeam.getUserId()+"' and  labId IS NULL";
			db.sta.executeUpdate(sql);

			String sql1="delete from TbLabCounterWisePatientInfomation where  TbLabCounterWisePatientInfomation.CreateBy='"+sessionBeam.getUserId()+"' and  labId IS NULL";
			db.sta.executeUpdate(sql1);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private boolean checkDischargePatient(String RegNo,String FiscalYear){
		try {

			String sql="select RegNo from tbpatientInfo where FiscalYear='"+FiscalYear+"' and RegNo='"+RegNo+"' and deschargeDate IS NULL and period='"+getCurrentPeriod(RegNo,FiscalYear)+"'";
			System.out.println(sql);
			ResultSet rs2=db.sta.executeQuery(sql);
			while(rs2.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return false;
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
	public void loadtestName(){
		try {
			for(int a=tableR.getRowCount()-1;a>=0;a--){
				modelR.removeRow(a);
			}


			cmbTestName.v.clear();
			cmbTestName.v.add("");
			String P="%"+cmbTestName.txtMrNo.getText().trim().toString()+"%";
			ResultSet rs=db.sta.executeQuery("select (select GroupName from tblabtestgroup where SN=tbtestname.TestHeadId) as GreoupName,tbtestname.TestName,tbtestname.Rate from tbtestname  where Testname like '"+P+"'");
			while(rs.next()){
				cmbTestName.v.add(rs.getString("TestName"));
				modelR.addRow(new Object[]{new ImageIcon("icon/Add.png"),rs.getString("GreoupName"),rs.getString("TestName"),df.format(Double.parseDouble(rs.getString("Rate")))});
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	public void setUserCurrentTransection(){
		try {

			/*			ResultSet rs=db.sta.executeQuery("select (select ISNULL(sum(Paid),0)) as paidAmont,(select ISNULL(sum(TotalPayable-Paid),0)) as dueAmont from tblabpatient where tblabpatient.CreateBy='"+sessionBeam.getUserId()+"' and tblabpatient.DateTime='"+new SimpleDateFormat("yyyy-MM-dd").format(txtDateTime.getDate())+"'");
			while(rs.next()){
				txtTcash.setText(df.format(Double.parseDouble(rs.getString("paidAmont"))));
				txtTDc.setText(df.format(Double.parseDouble(rs.getString("dueAmont"))));
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, ""+e.getMessage());
		}
	}
	public void loadPatientReg(){
		try {
			cmbRegNo.v.clear();
			ResultSet rs=db.sta.executeQuery("select tbpatientinfo.RegNo,tbpatientinfo.FiscalYear,tbpatientinfo.PatientName,tbpatientinfo.Cabin from tbpatientinfo where deschargeDate IS NULL  group by tbpatientinfo.RegNo,tbpatientinfo.FiscalYear,tbpatientinfo.PatientName,tbpatientinfo.Cabin order by FiscalYear desc");
			while(rs.next()){
				cmbRegNo.v.add(rs.getString("RegNo")+"/"+rs.getString("FiscalYear")+"  "+rs.getString("PatientName")+"  "+rs.getString("Cabin"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void autoId(){
		try {
			String sql="select (ISNULL(max(SN),0)+1)as SN from tblabtesthistory";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				SnId=rs.getString("SN");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void autoMRPId(){
		try {
			String sql="select (ISNULL(max(MrNo),0)+1)as MrNo from tblabpatient where FiscalYear='"+getFiscelYear()+"' and tblabpatient.CMonth='"+getCurrentMonth()+"' ";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				txtMrNo.setText(rs.getString("MrNo"));;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void loadDoctorName(){
		try {
			cmbRefferBy.v.clear();
			cmbRefferBy.v.add("");
			cmbRFPersion.v.clear();
			cmbRFPersion.v.add("");
			ResultSet rs=db.sta.executeQuery("select Name from tbdoctorinfo order by Name");
			while(rs.next()){
				cmbRefferBy.v.add(rs.getString("Name"));
				cmbRFPersion.v.add(rs.getString("Name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	private void basicInfoClear(){
		txtBC.setText("");
		txtPatientName.setText("");
		txtPhone.setText("");
		txtAge.setText("");
		txtMonth.setText("");
		txtDay.setText("");
		txtMonth.setText("");
		txtAddress.setText("");
		cmbRefferBy.txtMrNo.setText("");

		//cmbRFPersion.txtMrNo.setText("");
	}
	private void bacInfoEnable(boolean t){
		cmbRegNo.combo.setEnabled(t);
		txtPatientName.setEnabled(t);
		txtAge.setEnabled(t);
		txtMonth.setEnabled(t);
		txtDay.setEnabled(t);
		txtPhone.setEnabled(t);
		txtAddress.setEnabled(t);
		cmbsex.setEnabled(t);
		txtBC.setEnabled(t);
		txtPaidInCash.setEditable(t);
	}
	private void setEditText(){
		if(cmbPatientType.getSelectedIndex()==0){
			txtPaidInCash.setText("0");
			txtPaidInCash.setEditable(true);
			txtPercentDiscount.setEditable(true);
			txtManualDiscount.setEditable(true);
		}
		else{
			txtPaidInCash.setText("0");
			txtPaidInCash.setEditable(false);
			txtPercentDiscount.setEditable(false);
			txtManualDiscount.setEditable(false);
		}
	}

	public void btnActionEvent(){
		cmbFiscalYear.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				cmbFindLabBill.txtMrNo.setText("");

				LoadLabBillNo();
			}
		});
		cmbBillMonth.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cmbFindLabBill.txtMrNo.setText("");

				LoadLabBillNo();
			}
		});
		cmbPatientType.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(cmbPatientType.getSelectedIndex()==0){
					cmbRegNo.txtMrNo.setText("");
					cmbRegNo.combo.setEnabled(false);
					txtPaidInCash.setText("0");
					txtPaidInCash.setEditable(true);
					txtPercentDiscount.setEditable(true);
					txtManualDiscount.setEditable(true);
					basicInfoClear();
					//bacInfoEnable(true);
				}
				else{
					cmbRegNo.txtMrNo.setText("");
					cmbRegNo.combo.setEnabled(true);
					txtPaidInCash.setText("0");
					txtPaidInCash.setEditable(false);
					txtPercentDiscount.setEditable(false);
					txtManualDiscount.setEditable(false);
					basicInfoClear();
					//bacInfoEnable(false);
				}
			}
		});
		btnCounter1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cmbRegNo.combo.setEnabled(true);
				btnClearEvent();
				counter=1;
				setEditText();
				showData("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='"+counter+"' order by type asc");
				setCounterWisePendingPatientInfo("select *,(select Name from tbdoctorinfo where DoctorCode=TbLabCounterWisePatientInfomation.RefferBy) as RefferName from TbLabCounterWisePatientInfomation where labId IS NULL  and CreateBy='"+sessionBeam.getUserId()+"' and Counter='"+counter+"'");
				if(cmbRegNo.txtMrNo.getText().trim().toString().startsWith("RN-")){
					bacInfoEnable(false);
				}
				else if(cmbRegNo.txtMrNo.getText().trim().toString().isEmpty()){
					cmbRegNo.combo.setEnabled(false);
					txtPatientName.setEnabled(true);
					txtAge.setEnabled(true);
					txtMonth.setEnabled(true);
					txtDay.setEnabled(true);
					txtPhone.setEnabled(true);
					txtAddress.setEnabled(true);
					cmbRefferBy.combo.setEnabled(true);
				}
				else{
					bacInfoEnable(true);
				}
			}
		});
		btnCounter2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cmbRegNo.combo.setEnabled(true);
				btnClearEvent();
				counter=2;
				setEditText();
				showData("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='"+counter+"' order by type asc");
				setCounterWisePendingPatientInfo("select *,(select Name from tbdoctorinfo where DoctorCode=TbLabCounterWisePatientInfomation.RefferBy) as RefferName from TbLabCounterWisePatientInfomation where labId IS NULL  and CreateBy='"+sessionBeam.getUserId()+"' and Counter='"+counter+"'");
				if(cmbRegNo.txtMrNo.getText().trim().toString().startsWith("RN-")){
					bacInfoEnable(false);
				}
				else if(cmbRegNo.txtMrNo.getText().trim().toString().isEmpty()){
					cmbRegNo.combo.setEnabled(false);
					txtPatientName.setEnabled(true);
					txtAge.setEnabled(true);
					txtMonth.setEnabled(true);
					txtDay.setEnabled(true);
					txtPhone.setEnabled(true);
					txtAddress.setEnabled(true);
					cmbRefferBy.combo.setEnabled(true);
				}
				else{
					bacInfoEnable(true);
				}
			}
		});
		btnCounter3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cmbRegNo.combo.setEnabled(true);
				btnClearEvent();
				counter=3;
				setEditText();
				showData("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='"+counter+"' order by type asc");
				setCounterWisePendingPatientInfo("select *,(select Name from tbdoctorinfo where DoctorCode=TbLabCounterWisePatientInfomation.RefferBy) as RefferName from TbLabCounterWisePatientInfomation where labId IS NULL  and CreateBy='"+sessionBeam.getUserId()+"' and Counter='"+counter+"'");
				if(cmbRegNo.txtMrNo.getText().trim().toString().startsWith("RN-")){
					bacInfoEnable(false);
				}
				else if(cmbRegNo.txtMrNo.getText().trim().toString().isEmpty()){
					cmbRegNo.combo.setEnabled(false);
					txtPatientName.setEnabled(true);
					txtAge.setEnabled(true);
					txtMonth.setEnabled(true);
					txtDay.setEnabled(true);
					txtPhone.setEnabled(true);
					txtAddress.setEnabled(true);
					cmbRefferBy.combo.setEnabled(true);
				}
				else{
					bacInfoEnable(true);
				}
			}
		});
		btnCounter4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cmbRegNo.combo.setEnabled(true);
				btnClearEvent();
				counter=4;
				setEditText();
				showData("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and  tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='"+counter+"' order by type asc");
				setCounterWisePendingPatientInfo("select *,(select Name from tbdoctorinfo where DoctorCode=TbLabCounterWisePatientInfomation.RefferBy) as RefferName from TbLabCounterWisePatientInfomation where labId IS NULL  and CreateBy='"+sessionBeam.getUserId()+"' and Counter='"+counter+"'");
				if(cmbRegNo.txtMrNo.getText().trim().toString().startsWith("RN-")){
					bacInfoEnable(false);
				}
				else if(cmbRegNo.txtMrNo.getText().trim().toString().isEmpty()){
					cmbRegNo.combo.setEnabled(false);
					txtPatientName.setEnabled(true);
					txtAge.setEnabled(true);
					txtMonth.setEnabled(true);
					txtDay.setEnabled(true);
					txtPhone.setEnabled(true);
					txtAddress.setEnabled(true);
					cmbRefferBy.combo.setEnabled(true);
				}
				else{
					bacInfoEnable(true);
				}
			}
		});
		btnCounter5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnClearEvent();
				counter=5;
				setEditText();
				showData("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and  tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='"+counter+"' order by type asc");
				setCounterWisePendingPatientInfo("select *,(select Name from tbdoctorinfo where DoctorCode=TbLabCounterWisePatientInfomation.RefferBy) as RefferName from TbLabCounterWisePatientInfomation where labId IS NULL  and CreateBy='"+sessionBeam.getUserId()+"' and Counter='"+counter+"'");
				if(cmbRegNo.txtMrNo.getText().trim().toString().startsWith("RN-")){
					bacInfoEnable(false);
				}
				else if(cmbRegNo.txtMrNo.getText().trim().toString().isEmpty()){
					cmbRegNo.combo.setEnabled(false);
					txtPatientName.setEnabled(true);
					txtAge.setEnabled(true);
					txtMonth.setEnabled(true);
					txtDay.setEnabled(true);
					txtPhone.setEnabled(true);
					txtAddress.setEnabled(true);
					cmbRefferBy.combo.setEnabled(true);
				}
				else{
					bacInfoEnable(true);
				}
			}
		});
		txtAddress.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cmbRefferBy.txtMrNo.requestFocusInWindow();
			}
		});
		txtPatientName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				txtAge.requestFocusInWindow();
			}
		});
		txtPhone.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				txtAddress.requestFocusInWindow();
			}
		});
		txtAge.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				txtMonth.requestFocusInWindow();
			}
		});
		txtMonth.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				txtDay.requestFocusInWindow();
			}
		});
		txtDay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cmbsex.requestFocusInWindow();
			}
		});
		cmbsex.addKeyListener(new KeyListener() {

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
					txtPhone.requestFocusInWindow();
				}
			}
		});
		txtAddress.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmbRefferBy.txtMrNo.requestFocusInWindow();
			}
		});
		cmbRefferBy.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmbRFPersion.txtMrNo.setText(cmbRefferBy.txtMrNo.getText().trim().toString());
				cmbTestName.txtMrNo.requestFocusInWindow();
			}
		});
		cmbRefferBy.txtMrNo.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				cmbRFPersion.txtMrNo.setText(cmbRefferBy.txtMrNo.getText().trim().toString());
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		txtCO.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnPostAction();
			}
		});
		txtPercentDiscount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtManualDiscount.requestFocusInWindow();
			}
		});
		txtManualDiscount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtPaidInCash.requestFocusInWindow();
			}
		});
		txtPaidInCash.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				txtPaidInCash.selectAll();
			}
		});
		txtManualDiscount.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if(txtManualDiscount.getText().isEmpty()) {
					txtManualDiscount.setText("0");
				}
				autoCalculation();
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				txtManualDiscount.selectAll();
			}
		});
		txtPercentDiscount.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(!txtPercentDiscount.getText().trim().toString().isEmpty()){
					try {
						if(find==0){
							String sql="update tblabtesthistory set tblabtesthistory.discount='"+txtPercentDiscount.getText().trim().toString()+"' where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and tblabtesthistory.CreateBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='"+counter+"' and tblabtesthistory.type=1 and tblabtesthistory.discountAllow=1 and tblabtesthistory.labId IS NULL";
							db.sta.executeUpdate(sql);


							String Udsql="update tbUdlabtesthistory set tbUdlabtesthistory.discount='"+txtPercentDiscount.getText().trim().toString()+"' where tbUdlabtesthistory.FiscalYear='"+getFiscelYear()+"'  and tbUdlabtesthistory.CMonth='"+getCurrentMonth()+"'  and  tbUdlabtesthistory.CreateBy='"+sessionBeam.getUserId()+"' and tbUdlabtesthistory.counter='"+counter+"' and tbUdlabtesthistory.type=1 and tbUdlabtesthistory.discountAllow=1 and tbUdlabtesthistory.labId IS NULL";
							db.sta.executeUpdate(Udsql);

							String CountSql="update TbLabCounterWisePatientInfomation set PercentDiscount='"+txtPercentDiscount.getText().trim().toString()+"',ManualDiscount='"+txtManualDiscount.getText().trim().toString()+"' where  TbLabCounterWisePatientInfomation.CreateBy='"+sessionBeam.getUserId()+"' and TbLabCounterWisePatientInfomation.counter='"+counter+"' and TbLabCounterWisePatientInfomation.labId IS NULL";
							db.sta.executeUpdate(CountSql);

							showData("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"' and  tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='"+counter+"' order by type asc");
						}
						else if(find==1){

							String Bill=txtMrNo.getText().trim().toString();
							String FiscalYear=txtBillFiscalYear.getText().trim().toString();

							/*							StringTokenizer token=new StringTokenizer(cmbFindLabBill.txtMrNo.getText().trim().toString()," ");
							while(token.hasMoreTokens()){
								BillYear=token.nextToken();
								break;
							}

							int i=0;
							StringTokenizer Toekn=new StringTokenizer(BillYear,"/");
							while(Toekn.hasMoreTokens()){
								if(i==0){
									Bill=Toekn.nextToken();
								}
								if(i==1){
									FiscalYear=Toekn.nextToken();
								}
								i++;
							}*/

							String sql="update tblabtesthistory set tblabtesthistory.discount='"+txtPercentDiscount.getText().trim().toString()+"' where tblabtesthistory.FiscalYear='"+FiscalYear+"' and  tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and tblabtesthistory.type=1 and tblabtesthistory.discountAllow=1 and tblabtesthistory.labId='"+Bill+"'";
							db.sta.executeUpdate(sql);
							showData("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+FiscalYear+"' and  tblabtesthistory.CMonth='"+getCurrentMonth()+"' and  tblabtesthistory.labId='"+Bill+"' order by type asc");

						}
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
					}
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				txtPercentDiscount.selectAll();
			}
		});
		txtPatientName.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(!txtPatientName.getText().trim().toString().isEmpty()){
					if(txtPatientName.getText().trim().toString().startsWith("mrs") || txtPatientName.getText().trim().toString().startsWith("Mrs")){
						cmbsex.setSelectedItem("Female");
					}
					else if(txtPatientName.getText().trim().toString().startsWith("mr") || txtPatientName.getText().trim().toString().startsWith("Mr")){
						cmbsex.setSelectedItem("Male");
					}
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		txtPatientName.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(!txtPatientName.getText().trim().toString().isEmpty()){
					if(txtPatientName.getText().trim().toString().startsWith("Mr.")){
						cmbsex.setSelectedItem("Male");
					}					
					else if(txtPatientName.getText().trim().toString().startsWith("Mrs.")){
						cmbsex.setSelectedItem("Female");
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		cmbTestName.txtMrNo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				loadtestName();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyChar()==KeyEvent.VK_ENTER){
					String RegNo="",FiscaleYear="",RegYear="";
					StringTokenizer token=new StringTokenizer(cmbRegNo.txtMrNo.getText().trim().toString()," ");
					while(token.hasMoreTokens()){
						RegYear=token.nextToken();
						break;
					}

					int i=0;
					StringTokenizer Toekn=new StringTokenizer(RegYear,"/");
					while(Toekn.hasMoreTokens()){
						if(i==0){
							RegNo=Toekn.nextToken();
						}
						if(i==1){
							FiscaleYear=Toekn.nextToken();
						}
						i++;
					}
					btnInEvent(cmbTestName.txtMrNo.getText().trim().toString(),1);
				}
				else if(e.getKeyCode() == KeyEvent.VK_TAB) {
					txtPercentDiscount.requestFocus();
				}


			}
		});
		cmbRegNo.txtMrNo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					cmbTestName.txtMrNo.requestFocusInWindow();
				}
			}
		});
		cmbTestCode.txtMrNo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					cmbTestName.txtMrNo.requestFocusInWindow();
				}
			}
		});
		txtPercentDiscount.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					if(!txtPercentDiscount.getText().trim().toString().isEmpty()){
						try {
							if(find==0){
								String sql="update tblabtesthistory set tblabtesthistory.discount='"+txtPercentDiscount.getText().trim().toString()+"' where tblabtesthistory.FiscalYear='"+getFiscelYear()+"'  and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and  tblabtesthistory.CreateBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='"+counter+"' and tblabtesthistory.type=1 and tblabtesthistory.discountAllow=1 and tblabtesthistory.labId IS NULL";
								db.sta.executeUpdate(sql);

								String Udsql="update tbUdlabtesthistory set tbUdlabtesthistory.discount='"+txtPercentDiscount.getText().trim().toString()+"' where tbUdlabtesthistory.FiscalYear='"+getFiscelYear()+"' and  tbUdlabtesthistory.CMonth='"+getCurrentMonth()+"' and  tbUdlabtesthistory.CreateBy='"+sessionBeam.getUserId()+"' and tbUdlabtesthistory.counter='"+counter+"' and tbUdlabtesthistory.type=1 and tbUdlabtesthistory.discountAllow=1 and tbUdlabtesthistory.labId IS NULL";
								db.sta.executeUpdate(Udsql);

								String CountSql="update TbLabCounterWisePatientInfomation set PercentDiscount='"+txtPercentDiscount.getText().trim().toString()+"',ManualDiscount='"+txtManualDiscount.getText().trim().toString()+"' where  TbLabCounterWisePatientInfomation.CreateBy='"+sessionBeam.getUserId()+"' and TbLabCounterWisePatientInfomation.counter='"+counter+"' and TbLabCounterWisePatientInfomation.labId IS NULL";
								db.sta.executeUpdate(CountSql);

								showData("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"' and  tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='"+counter+"' order by type asc");

							}
							else if(find==1){

								String Bill=txtMrNo.getText().trim().toString();
								String FiscalYear=txtBillFiscalYear.getText().trim().toString();
								String BillMonth=txtBillMonth.getText().trim().toString();




								/*								StringTokenizer token=new StringTokenizer(cmbFindLabBill.txtMrNo.getText().trim().toString()," ");
								while(token.hasMoreTokens()){
									BillYear=token.nextToken();
									break;
								}

								int i=0;
								StringTokenizer Toekn=new StringTokenizer(BillYear,"/");
								while(Toekn.hasMoreTokens()){
									if(i==0){
										Bill=Toekn.nextToken();
									}
									if(i==1){
										FiscalYear=Toekn.nextToken();
									}
									i++;
								}*/

								String sql="update tblabtesthistory set tblabtesthistory.discount='"+txtPercentDiscount.getText().trim().toString()+"' where tblabtesthistory.FiscalYear='"+FiscalYear+"' and tblabtesthistory.CMonth='"+BillMonth+"' and tblabtesthistory.type=1 and tblabtesthistory.discountAllow=1 and tblabtesthistory.labId='"+Bill+"'";
								db.sta.executeUpdate(sql);
								showData("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+FiscalYear+"' and tblabtesthistory.CMonth='"+BillMonth+"'  and tblabtesthistory.labId='"+Bill+"' order by type asc");
							}

						} catch (Exception e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error!!,"+e1.getMessage());
						}
					}
				}
			}
		});
		txtManualDiscount.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){

					autoCalculation();
				}
			}
		});
		txtAdvancepaid.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					txtPaidInCash.requestFocusInWindow();
				}
			}
		});
		txtPaidInCash.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){

					txtCO.requestFocusInWindow();
				}
			}
		});
		txtCO.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				//				if(e.getKeyCode()==KeyEvent.VK_ENTER){
				//
				//					//btnPostAction();
				//				}
			}
		});
		txtPaidInCash.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				autoCalculationOfPaidInCash();

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		txtAdvancepaid.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(!txtAdvancepaid.getText().trim().toString().isEmpty()){
					double tpayable=Double.parseDouble(txtTotalCharge.getText().toString())-(Double.parseDouble(txtGeneralDiscount.getText().toString())+Double.parseDouble(txtSpDiscount.getText().toString()));
					txtTotalPayable.setText(df.format(tpayable));
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		txtManualDiscount.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(!txtManualDiscount.getText().trim().toString().isEmpty()){
					try {
						String CountSql="update TbLabCounterWisePatientInfomation set PercentDiscount='"+txtPercentDiscount.getText().trim().toString()+"',ManualDiscount='"+txtManualDiscount.getText().trim().toString()+"' where  TbLabCounterWisePatientInfomation.CreateBy='"+sessionBeam.getUserId()+"' and TbLabCounterWisePatientInfomation.counter='"+counter+"' and TbLabCounterWisePatientInfomation.labId IS NULL";
						db.sta.executeUpdate(CountSql);
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
					}
					txtSpDiscount.setText(txtManualDiscount.getText().trim().toString());
					double tpayable=Double.parseDouble(txtTotalCharge.getText().toString())-(Double.parseDouble(txtGeneralDiscount.getText().toString())+Double.parseDouble(txtSpDiscount.getText().toString()));
					txtTotalPayable.setText(df.format(tpayable));
					if(Double.parseDouble(txtTotalPayable.getText().trim().toString())>Double.parseDouble(txtPaidInCash.getText().trim().toString())){
						txtDues.setText(df.format(Double.parseDouble(txtTotalPayable.getText().trim().toString())-Double.parseDouble(txtPaidInCash.getText().trim().toString())));
					}
					else{
						txtDues.setText("0.0");
						//txtRefund.setText(df.format(Double.parseDouble(txtPaidInCash.getText().trim().toString())-Double.parseDouble(txtTotalPayable.getText().trim().toString())));
					}
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		cmbTestCode.txtMrNo.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					ResultSet rs=db.sta.executeQuery("select tbtestname.TestName from tbtestname where tbtestname.TestCode='"+cmbTestCode.txtMrNo.getText().trim().toString()+"'");
					while(rs.next()){
						cmbTestName.txtMrNo.setText(rs.getString("TestName"));
					}
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		cmbDiscount.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!cmbTestName.txtMrNo.getText().trim().toString().isEmpty()){
					try {
						if(!checkDoplicateTest(cmbTestName.txtMrNo.getText().trim().toString())){
							if(table.getRowCount()==10){
								for(int a=table.getRowCount()-1;a>=0;a--){
									model.removeRow(a);
								}
							}
							String RegNo="",FiscaleYear="",RegYear="";
							StringTokenizer token=new StringTokenizer(cmbRegNo.txtMrNo.getText().trim().toString()," ");
							while(token.hasMoreTokens()){
								RegYear=token.nextToken();
								break;
							}

							int i=0;
							StringTokenizer Toekn=new StringTokenizer(RegYear,"/");
							while(Toekn.hasMoreTokens()){
								if(i==0){
									RegNo=Toekn.nextToken();
								}
								if(i==1){
									FiscaleYear=Toekn.nextToken();
								}
								i++;
							}
							btnInEvent(cmbTestName.txtMrNo.getText().trim().toString(),1);
							cmbTestName.txtMrNo.requestFocusInWindow();
						}
						else{
							JOptionPane.showMessageDialog(null, "Warning!!,Doplicate Test Code Entry!!");
						}

					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e);
					}
				}
			}
		});
		cmbRegNo.txtMrNo.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if(!cmbRegNo.txtMrNo.getText().trim().toString().isEmpty()){
					try {
						String RegNo="",PatientFiscaleYear="",RegYear="";
						StringTokenizer token=new StringTokenizer(cmbRegNo.txtMrNo.getText().trim().toString()," ");
						while(token.hasMoreTokens()){
							RegYear=token.nextToken();
							break;
						}

						int i=0;
						StringTokenizer Toekn=new StringTokenizer(RegYear,"/");
						while(Toekn.hasMoreTokens()){
							if(i==0){
								RegNo=Toekn.nextToken();
							}
							if(i==1){
								PatientFiscaleYear=Toekn.nextToken();
							}
							i++;
						}



						if(checkDischargePatient(RegNo,PatientFiscaleYear)){
							int mode=0;
							String sql="select *,(select name from tbdoctorinfo where autoId=tbpatientinfo.RefferBy)as RefferName,(select vName from tbcorporateinfo where iSl=tbpatientinfo.Corporate)as CorporateName,(select vName from tbmanagementinfo where iSl=tbpatientinfo.Director)as DirectorName from tbpatientinfo where RegNo='"+RegNo+"' and FiscalYear='"+PatientFiscaleYear+"'  and period='"+getCurrentPeriod(RegNo, PatientFiscaleYear)+"'";
							System.out.println(sql);
							ResultSet rs=db.sta.executeQuery(sql);
							while(rs.next()){
								txtPatientName.setText(rs.getString("PatientName"));
								txtBC.setText(rs.getString("Cabin"));
								txtPhone.setText(rs.getString("MobileNo"));
								txtDateSN.setDate(rs.getDate("admissionDate"));
								txtAge.setText(rs.getString("Age"));
								txtMonth.setText(rs.getString("Month"));
								txtDay.setText(rs.getString("Day"));
								cmbsex.setSelectedItem(rs.getString("Sex"));
								cmbRefferBy.txtMrNo.setText(rs.getString("RefferName"));
								txtAddress.setText("Village#:-"+rs.getString("PreVillage")+",  Post Office#:-"+rs.getString("PrePost")+",  Upazilla#:-"+rs.getString("PreUpazila")+",  Zilla#:-"+rs.getString("PreDistrict"));
								setPatientInformation(false);
								//
							}
						}

					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e);
					}
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		btnTestSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnInEvent(cmbTestName.txtMrNo.getText().trim().toString(),1);
			}
		});
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(find==0){
					autoMRPId();
				}
			}
		});
		btnClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cmbRegNo.combo.setEnabled(true);
				btnClearEvent();
				setPatientInformation(true);
				setUserCurrentTransection();


				checkCounter();
				autoMRPId();
				resetFiscalYear();
				find=0;
				finalBill=0;
				//Instatus=0;
				cmbPatientType.setSelectedItem("Outdoor");


			}
		});
		btnDeletePendingInvoice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Clear All Pending Invoice ","Confrim.........",JOptionPane.YES_NO_OPTION);
					if(confrim==JOptionPane.YES_OPTION){
						db.sta.executeUpdate("delete from tblabtesthistory where FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and labId IS NULL and createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"'");
						db.sta.executeUpdate("delete from TbLabCounterWisePatientInfomation where  labId IS NULL and createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"'");
						checkCounter();
						btnClearEvent();
						resetFiscalYear();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
				}
			}
		});
		btnPost.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//a();
				btnPostAction();
			}
		});
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!txtMrNo.getText().trim().toString().isEmpty()){
					if(!txtPatientName.getText().trim().toString().isEmpty()){
						if(!txtAge.getText().trim().toString().isEmpty() ||  !txtMonth.getText().trim().toString().isEmpty() || !txtDay.getText().trim().toString().isEmpty()){
							if(!cmbsex.getSelectedItem().toString().isEmpty()){
								btnEditEvent();
							}
							else{
								JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Patient Gender");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Patient Age");
						}

					}
					else{
						JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Patient Name");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Please Provide Mr No");
				}
			}
		});


		btnFind.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnClearEvent();

				/*				String Bill="",FiscaleYear="",BillYear="";
				StringTokenizer token=new StringTokenizer(cmbFindLabBill.txtMrNo.getText().trim().toString()," ");
				while(token.hasMoreTokens()){
					BillYear=token.nextToken();
					break;
				}

				int i=0;
				StringTokenizer Toekn=new StringTokenizer(BillYear,"/");
				while(Toekn.hasMoreTokens()){
					if(i==0){
						Bill=Toekn.nextToken();
					}
					if(i==1){
						FiscaleYear=Toekn.nextToken();
					}
					i++;
				}*/


				//System.out.println("Fiscal "+cmbFiscalYear.getSelectedItem().toString());

				btnFindEvent(cmbFindLabBill.txtMrNo.getText().trim().toString(),cmbFiscalYear.getSelectedItem().toString(),cmbBillMonth.getSelectedItem().toString());
			}
		});
		btnPrint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*				String Bill="",FiscaleYear="",BillYear="";
				StringTokenizer token=new StringTokenizer(cmbFindLabBill.txtMrNo.getText().trim().toString()," ");
				while(token.hasMoreTokens()){
					BillYear=token.nextToken();
					break;
				}

				int i=0;
				StringTokenizer Toekn=new StringTokenizer(BillYear,"/");
				while(Toekn.hasMoreTokens()){
					if(i==0){
						Bill=Toekn.nextToken();
					}
					if(i==1){
						FiscaleYear=Toekn.nextToken();
					}
					i++;
				}*/
				if(find==1){
					btnClearEvent();
				}
				if(checkBillFound(cmbFindLabBill.txtMrNo.getText().trim().toString(),cmbFiscalYear.getSelectedItem().toString(),cmbBillMonth.getSelectedItem().toString())){
					if(find==0){
						btnFindEvent(cmbFindLabBill.txtMrNo.getText().trim().toString(),cmbFiscalYear.getSelectedItem().toString(),cmbBillMonth.getSelectedItem().toString());
					}
					btnPrintEvent(cmbFindLabBill.txtMrNo.getText().trim().toString(),cmbFiscalYear.getSelectedItem().toString(),cmbBillMonth.getSelectedItem().toString());
				}
				else{
					JOptionPane.showMessageDialog(null, "Sorry!!,At First BIll Confrim");
				}
			}
		});
		btnRefund.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//JOptionPane.showMessageDialog(null, "You Have No Permission");
				/*				String Bill="",FiscalYear="",BillYear="";
				StringTokenizer token=new StringTokenizer(cmbFindLabBill.txtMrNo.getText().trim().toString()," ");
				while(token.hasMoreTokens()){
					BillYear=token.nextToken();
					break;
				}

				int i=0;
				StringTokenizer Toekn=new StringTokenizer(BillYear,"/");
				while(Toekn.hasMoreTokens()){
					if(i==0){
						Bill=Toekn.nextToken();
					}
					if(i==1){
						FiscalYear=Toekn.nextToken();
					}
					i++;
				}*/
				if(sessionBeam.getUserType().equalsIgnoreCase("Admin")){
					btnRefundEvent(txtMrNo.getText().trim().toString(),txtBillFiscalYear.getText().trim().toString(),txtBillMonth.getText().trim().toString());
				}
				else{
					JOptionPane.showMessageDialog(null, "You Have No Permission TO Refund");
				}
			}
		});
		btnRefundSlip.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "You Have No Permission");

				/*				String Bill="",FiscalYear="",BillYear="";
				StringTokenizer token=new StringTokenizer(cmbFindLabBill.txtMrNo.getText().trim().toString()," ");
				while(token.hasMoreTokens()){
					BillYear=token.nextToken();
					break;
				}

				int i=0;
				StringTokenizer Toekn=new StringTokenizer(BillYear,"/");
				while(Toekn.hasMoreTokens()){
					if(i==0){
						Bill=Toekn.nextToken();
					}
					if(i==1){
						FiscalYear=Toekn.nextToken();
					}
					i++;
				}*/
				//btnRefundSlipEvent(txtMrNo.getText().trim().toString(),txtBillFiscalYear.getText().trim().toString());
			}
		});
		btnAddDoctor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnAddDoctorEvent();
			}
		});
		cmbFindLabBill.txtMrNo.addKeyListener(new KeyListener() {

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
					/*					String Bill="",FiscalYear="",BillYear="";
					StringTokenizer token=new StringTokenizer(cmbFindLabBill.txtMrNo.getText().trim().toString()," ");
					while(token.hasMoreTokens()){
						BillYear=token.nextToken();
						break;
					}

					int i=0;
					StringTokenizer Toekn=new StringTokenizer(BillYear,"/");
					while(Toekn.hasMoreTokens()){
						if(i==0){
							Bill=Toekn.nextToken();
						}
						if(i==1){
							FiscalYear=Toekn.nextToken();
						}
						i++;
					}
					btnClearEvent();*/
					btnClearEvent();
					btnFindEvent(cmbFindLabBill.txtMrNo.getText().trim().toString(),cmbFiscalYear.getSelectedItem().toString(),cmbBillMonth.getSelectedItem().toString());
				}
			}
		});

		btnAdvancedSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				advancedSearch.setLocation(btnAdvancedSearch.getLocationOnScreen().x-advancedSearch.getWidth(), btnAdvancedSearch.getLocationOnScreen().y);


				advancedSearch.setVisible(true);

				advancedSearch.refresh();
				advancedSearch.txtSearch.requestFocus();
			}
		});
	}
	protected void btnPostAction() {
		date_take();
		if(finalBill!=1){
			if(!txtMrNo.getText().trim().toString().isEmpty()){
				if(!txtPatientName.getText().trim().toString().isEmpty()){
					if(!txtAge.getText().trim().toString().isEmpty() ||  txtMonth.getText().trim().toString().isEmpty() || txtDay.getText().trim().toString().isEmpty()){
						if(txtDate.getValue()!=null){
							if(Double.parseDouble(txtTotalCharge.getText().trim().toString())>0){
								labpatientinserdata();
							}
							else{
								JOptionPane.showMessageDialog(null, "Sorry!!,There Are No Bill Ready For Complete Transection!!");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Delivery Report date");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Patient Age");
					}

				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Patient Name");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Please Provide Mr No");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Sorry!!,You Can't Nothing To Change This Bill");
		}
	}

	protected void autoCalculationOfPaidInCash() {
		if(!txtPaidInCash.getText().trim().toString().isEmpty()){
			double payableamt=Double.parseDouble(txtTotalPayable.getText().trim().toString())-Double.parseDouble(txtAdvancepaid.getText().trim().toString());
			if(find==0){
				Double totalPayable=Double.parseDouble(txtTotalPayable.getText().trim().toString());
				Double paidInCash=Double.parseDouble(txtPaidInCash.getText().trim().toString());
				Double totalCharge=Double.parseDouble(txtTotalCharge.getText().trim().toString());
				Double manualDiscount=Double.parseDouble(txtManualDiscount.getText().trim().toString());
				if(totalPayable>paidInCash){
					txtDues.setText(df.format(totalPayable-(Double.parseDouble(txtAdvancepaid.getText().trim().toString())+paidInCash)));
				}
				else{
					txtDues.setText("0.0");
					//txtRefund.setText(df.format(Double.parseDouble(txtPaidInCash.getText().trim().toString())-Double.parseDouble(txtTotalPayable.getText().trim().toString())));
				}

				if(Double.parseDouble(txtDues.getText().trim().toString())<0){
					txtDues.setText("0");
				}
				if(paidInCash>totalPayable) {
					if(totalCharge>=paidInCash) {
						manualDiscount -= (paidInCash - totalPayable);
						txtManualDiscount.setText(manualDiscount.toString());
						autoCalculation();
					}else
						JOptionPane.showMessageDialog(null, "You Can not Recive more than total Charge", "Warning",JOptionPane.WARNING_MESSAGE);
				}
			}

		}
	}


	protected void autoCalculation() {
		try {
			String CountSql="update TbLabCounterWisePatientInfomation set PercentDiscount='"+txtPercentDiscount.getText().trim().toString()+"',ManualDiscount='"+txtManualDiscount.getText().trim().toString()+"' where  TbLabCounterWisePatientInfomation.CreateBy='"+sessionBeam.getUserId()+"' and TbLabCounterWisePatientInfomation.counter='"+counter+"' and TbLabCounterWisePatientInfomation.labId IS NULL";
			db.sta.executeUpdate(CountSql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e1.getMessage());
		}

		txtSpDiscount.setText(txtManualDiscount.getText().trim().toString());
		double tpayable=Double.parseDouble(txtTotalCharge.getText().toString())-(Double.parseDouble(txtGeneralDiscount.getText().toString())+Double.parseDouble(txtSpDiscount.getText().toString()));
		txtTotalPayable.setText(df.format(tpayable));
		if(Double.parseDouble(txtTotalPayable.getText().trim().toString())>Double.parseDouble(txtPaidInCash.getText().trim().toString())){
			txtDues.setText(df.format(Double.parseDouble(txtTotalPayable.getText().trim().toString())-Double.parseDouble(txtPaidInCash.getText().trim().toString())));
		}
		else{
			txtDues.setText("0.0");
			//txtRefund.setText(df.format(Double.parseDouble(txtPaidInCash.getText().trim().toString())-Double.parseDouble(txtTotalPayable.getText().trim().toString())));
		}
		txtPaidInCash.requestFocusInWindow();
	}


	private void btnEditEvent(){

		if(checkBillFound(txtMrNo.getText().trim().toString(),txtBillFiscalYear.getText().trim().toString(),txtBillMonth.getText().trim().toString())){
			int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Edit Patient Information","Confrim....",JOptionPane.YES_NO_OPTION);
			if(confrim==JOptionPane.YES_OPTION){
				try {

					String refferId="",RfPersionId="",ComissionAllow="";
					ResultSet rs1=db.sta.executeQuery("select tbdoctorinfo.DoctorCode from tbdoctorinfo where tbdoctorinfo.Name='"+cmbRefferBy.txtMrNo.getText().trim().toString()+"'");
					while(rs1.next()){
						refferId=rs1.getString("DoctorCode");
					}
					ResultSet rs2=db.sta.executeQuery("select tbdoctorinfo.DoctorCode,tbdoctorinfo.type from tbdoctorinfo where tbdoctorinfo.Name='"+cmbRFPersion.txtMrNo.getText().trim().toString()+"'");
					while(rs2.next()){
						RfPersionId=rs2.getString("DoctorCode");
						ComissionAllow=rs2.getString("type").equals("Referral")?"1":"0";
					}
					String sql="update tblabpatient set PatientName='"+txtPatientName.getText().trim().toString()+"',Mobile='"+txtPhone.getText().trim().toString()+"',Age='"+txtAge.getText().trim().toString()+"',Month='"+txtMonth.getText().trim().toString()+"',day='"+txtDay.getText().trim().toString()+"',Sex='"+cmbsex.getSelectedItem().toString()+"',address='"+txtAddress.getText().trim().toString()+"',Cabin='"+txtBC.getText().trim().toString()+"',RefferBy='"+refferId+"',RfPersionId='"+RfPersionId+"',ComissionAllow='"+ComissionAllow+"',CO='"+txtCO.getText().trim().toString()+"',SampleCollect='"+txtSampleCollection.getText().trim().toString()+"',ReportDelivery='"+new SimpleDateFormat("dd-MM-yyyy").format(txtDate.getValue())+" : "+txtTime.getText().toString()+"',entryTime=CURRENT_TIMESTAMP,ModifyBy='"+sessionBeam.getUserId()+"' where labId='"+txtMrNo.getText().trim().toString()+"' and FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"' and CMonth='"+txtBillMonth.getText().trim().toString()+"' ";
					System.out.println(sql);
					db.sta.executeUpdate(sql);
					btnPrintEvent(txtMrNo.getText().trim().toString(),txtBillFiscalYear.getText().trim().toString(),txtBillMonth.getText().trim().toString());
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
				}
			}

		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Such As No Bill Found For Edit");
		}
	}
	private void btnAddDoctorEvent(){
		try {
			if(!checkDoctorName()){
				String DAutoId=getDoctorAutoId();
				String DCCode="DC-"+DAutoId;
				String sql="insert into tbdoctorinfo (autoId,DoctorCode,Name,Mobile,Degree,type) values ('"+DAutoId+"','"+DCCode+"','"+cmbRefferBy.txtMrNo.getText().trim().toString()+"','0','"+txtDoctorDegree.getText().trim().toString()+"','Non-Referral')";
				db.sta.executeUpdate(sql);
				loadDoctorName();
			}


		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private boolean checkDoctorName(){
		try {
			ResultSet rs=db.sta.executeQuery("select tbdoctorinfo.Name from tbdoctorinfo where tbdoctorinfo.Name='"+cmbRefferBy.txtMrNo.getText().toString()+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private String getDoctorAutoId(){
		String DcAutoId="";
		try {
			String sql="select (ISNULL(max(autoId),0)+1)as autoId from tbdoctorinfo";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				DcAutoId=rs.getString("autoId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return DcAutoId;
	}
	private void btnRefundSlipEvent(String Bill,String FiscalYear,String BillMonth){
		if(find==1){
			if(checkBillFound(Bill,FiscalYear,BillMonth)){
				if(checkExistRefundItem(Bill,FiscalYear,BillMonth)){
					try {
						String sql="select (select Degree from tbdoctorinfo where DoctorCode=a.RefferBy ) as Degree,(select Name from tbdoctorinfo where DoctorCode=a.RefferBy ) as DcName,(select username from tblogin where user_id=a.CreateBy) as username,a.labId as BillNo,a.RegNo,a.PatientName,a.Mobile,a.Sex,a.Age,a.Month,a.day,a.Cabin,b.ManualDiscount,b.testName,b.qty,b.rate,b.discount,a.DateTime from TbLabPatient a join tblabtesthistory b on a.labId=b.labId  and a.FiscalYear=b.FiscalYear and a.CMonth=b.CMonth where b.FiscalYear='"+FiscalYear+"' and b.CMonth='"+BillMonth+"'  and b.labId='"+Bill+"' and b.RefundStatus='1'";
						System.out.println(sql);
						String report="LabStatementReport/LabRefundMoneyRecipt.jrxml";
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
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Sorry!!,There No Refund Item Found I this Bill");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "There are no bill found");
			}
		}
	}
	private boolean checkExistRefundItem(String Bill,String FiscalYear,String BillMonth){
		try {
			String sql="select *from tblabtesthistory where FiscalYear='"+FiscalYear+"' and tblabtesthistory.CMonth='"+BillMonth+"' and labId='"+Bill+"' and RefundStatus='1' ";
			System.out.println(sql);
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return false;
	}
	private void btnRefundEvent(String Bill,String FiscalYear,String BillMonth){
		if(find==1){
			if(checkBillFound(Bill,FiscalYear,BillMonth)){
				int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Create Refund Slip","Confrim.........",JOptionPane.YES_NO_OPTION);
				if(confrim==JOptionPane.YES_OPTION){
					try {
						int refund=0;
						double RefundAmount=0,RefundTestRate=0,RefundTestAmount=0;
						for(int a=0;a<table.getRowCount();a++){
							Boolean check=(Boolean) table.getValueAt(a, 9);
							if(check){
								String update="update tblabtesthistory set RefundStatus='1' where FiscalYear='"+FiscalYear+"' and tblabtesthistory.CMonth='"+BillMonth+"'  and labId='"+Bill+"' and SN='"+table.getValueAt(a, 1).toString()+"'";
								db.sta.executeUpdate(update);
								refund++;
								RefundTestRate=RefundTestRate+(Double.parseDouble(table.getValueAt(a, 4).toString())*Double.parseDouble(table.getValueAt(a, 5).toString()));
								RefundTestAmount=RefundTestAmount+(Double.parseDouble(table.getValueAt(a, 4).toString())*Double.parseDouble(table.getValueAt(a, 7).toString()));
							}
						}
						if(refund!=0){

							//String Delete="delete from TbLabPaymentHistory where FiscalYear='"+FiscalYear+"' and BillNo='"+Bill+"' and PaymentStatus='Refund'";
							//db.sta.executeUpdate(Delete);
							double MainTestAmount=0;
							ResultSet rs=db.sta.executeQuery("SELECT ISNULL(sum(qty*rate),0) as MainTestAmount FROM tblabtesthistory WHERE labId='"+Bill+"' AND FiscalYear='"+FiscalYear+"' and tblabtesthistory.CMonth='"+BillMonth+"'  and type=1 and RefundStatus='0' and discountAllow='1'");
							while(rs.next()){
								MainTestAmount=MainTestAmount+Double.parseDouble(rs.getString("MainTestAmount"));
							}

							double TotalCharge=Double.parseDouble(txtTotalCharge.getText().trim().toString())-RefundTestRate;
							double PerCentDis=MainTestAmount*Double.parseDouble(txtPercentDiscount.getText().trim().toString())/100;
							double TotalDiscount=PerCentDis+Double.parseDouble(txtManualDiscount.getText().trim().toString());
							double TotalPayble=(TotalCharge-TotalDiscount)<0?0:(TotalCharge-TotalDiscount);
							double Paid=Double.parseDouble(txtAdvancepaid.getText().trim().toString());
							double RefundAmt=Double.parseDouble(txtRefund.getText().trim().toString());
							double tPaid=Paid-RefundAmt;
							double Due=TotalPayble-tPaid;
							double PerCent=0;
							double Manual=0;
							double BeforeManualDiscount=Double.parseDouble(txtManualDiscount.getText().trim().toString().isEmpty()?"0":txtManualDiscount.getText().trim().toString());
							double BeforeTotalCharge=Double.parseDouble(txtTotalCharge.getText().trim().toString().isEmpty()?"0":txtTotalCharge.getText().trim().toString());

							//double RefundAmt=Double.parseDouble(txtRefund.getText().trim().toString());
							System.out.println("Refund Amt "+RefundAmt);
							if(TotalPayble==0 && RefundAmt!=0 && Paid!=RefundAmt && tPaid>=0){
								RefundAmount=tPaid;
							}
							else{
								RefundAmount=Due<0?Due*-1:0;
							}
							if(TotalCharge==0){
								PerCent=0;
								Manual=0;
								TotalDiscount=0;
							}
							else{
								Manual=Double.parseDouble(txtManualDiscount.getText().trim().toString().isEmpty()?"0":txtManualDiscount.getText().trim().toString());
								PerCent=Double.parseDouble(txtPercentDiscount.getText().trim().toString().isEmpty()?"0":txtPercentDiscount.getText().trim().toString());
							}
							String UpdateMainBill="update TbLabPatient set PercentDiscount='"+PerCent+"',Discount='"+PerCentDis+"',ManualDiscount='"+Manual+"',totalDiscount='"+TotalDiscount+"',TotalCharge='"+TotalCharge+"',TotalPayable='"+TotalPayble+"' ,Paid='"+Paid+"',entryTime=CURRENT_TIMESTAMP,CreateBy='"+sessionBeam.getUserId()+"' where FiscalYear='"+FiscalYear+"' and CMonth='"+BillMonth+"'  and labId='"+Bill+"'";
							System.out.println(UpdateMainBill);
							db.sta.executeUpdate(UpdateMainBill);


							for(int a=0;a<table.getRowCount();a++){
								double RatioDiscount=(BeforeManualDiscount*Double.parseDouble(table.getValueAt(a, 5).toString()))/BeforeTotalCharge;
								String update="update tblabtesthistory set ManualDiscount='"+RatioDiscount+"' where FiscalYear='"+FiscalYear+"' and tblabtesthistory.CMonth='"+BillMonth+"'  and labId='"+Bill+"' and SN='"+table.getValueAt(a, 1).toString()+"'";
								System.out.println(update);
								db.sta.executeUpdate(update);
							}

							AutoPaymentHistoryId();
							String type=cmbPatientType.getSelectedIndex()==1?"Indoor":"Outdoor";
							String paymentquery="insert into TbLabPaymentHistory values('"+paymentId+"','"+Bill+"','"+RefundAmount+"','0','"+type+"','Refund',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','"+FiscalYear+"','"+BillMonth+"')";
							System.out.println(paymentquery);
							db.sta.executeUpdate(paymentquery);

							String Udpaymentquery="insert into TbUdLabPaymentHistory values('"+paymentId+"','"+Bill+"','"+RefundAmount+"','0','"+type+"','Refund',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','NEW','"+FiscalYear+"','"+BillMonth+"')";
							System.out.println(Udpaymentquery);
							db.sta.executeUpdate(Udpaymentquery);

							String d_l_id="",c_l_id="",legerId="";


							String BfTransId="";
							ResultSet rs1=db.sta.executeQuery("select TransId from TbLabPatient where labId='"+Bill+"' ");
							while(rs1.next()){
								BfTransId=rs1.getString("TransId");
							}

							String UpdateAccSale="update accftransection set amount='"+TotalPayble+"',entryTime=CURRENT_TIMESTAMP where transectionid='"+BfTransId+"'";
							db.sta.executeUpdate(UpdateAccSale);


							legerId=getPatientLedger();
							int TransId=0;
							//Cash Transaction..................
							if(RefundAmount>0){
								c_l_id="41";
								d_l_id=legerId;
								TransId=getTransId();
								String Cashquery1="insert into accftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy) values ('"+TransId+"','"+Bill+"','Refund','3','301','"+d_l_id+"','"+c_l_id+"','"+RefundAmount+"','Refund For Diagnostic',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
								System.out.println(Cashquery1);
								db.sta.executeUpdate(Cashquery1);

								String UdCashquery1="insert into accUdftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy,Flag) values ('"+TransId+"','"+Bill+"','Refund','3','301','"+d_l_id+"','"+c_l_id+"','"+RefundAmount+"','Refund For Diagnostic',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','NEW')";
								System.out.println(UdCashquery1);
								db.sta.executeUpdate(UdCashquery1);

								String CashUpdate="update tblabpatient set RefundId='"+TransId+"' where labId='"+Bill+"' ";
								db.sta.executeUpdate(CashUpdate);

								String UdCashUpdate="update tbUdlabpatient set RefundId='"+TransId+"' where labId='"+Bill+"' ";
								db.sta.executeUpdate(UdCashUpdate);
							}

							btnFindEvent(Bill, FiscalYear,BillMonth);
						}
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "There are no bill found");
			}
		}
	}
	protected void setPatientInformation(boolean t) {
		txtPatientName.setEnabled(t);
		txtBC.setEnabled(t);
		txtPhone.setEnabled(t);
		txtAge.setEnabled(t);
		txtMonth.setEnabled(t);
		txtDay.setEnabled(t);
		cmbsex.setEnabled(t);
		//cmbRefferBy.combo.setEnabled(t);
		txtAddress.setEnabled(t);

	}
	private boolean checkDoplicateTest(String TestName){
		try {

			if(find==0){
				ResultSet rs=db.sta.executeQuery("select testName from tblabtesthistory where labId IS NULL and FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and testName='"+TestName+"'   and createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"'");
				while(rs.next()){
					return true;
				}
			}
			else if(find==1){
				ResultSet rs=db.sta.executeQuery("select testName from tblabtesthistory where FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and labId='"+cmbFindLabBill.txtMrNo.getText().trim().toString()+"' and testName='"+TestName+"' ");
				while(rs.next()){
					return true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private void btnPrintEvent(String BillId,String FiscalYear,String BillMonth) {
		if(checkBillFound(BillId,FiscalYear,BillMonth)){
			PrintOpen(BillId,FiscalYear,BillMonth);
		}
	}
	private void PrintOpen(String labId,String FiscalYear,String BillMonth){
		try {
			String sql="";
			String report="";
			if(btnMoneyReceipt.isSelected()){
				sql="select a.testName,a.rate*a.qty as rate,a.RefundStatus,b.labId,b.RegNo,b.PatientName,b.Age,b.Month,b.day,b.Cabin,b.Sex,b.Mobile,b.DateTime,b.SampleCollect,b.ReportDelivery,(select username from tblogin where user_id=b.CreateBy) as UserName,b.RefferBy,(select Name from tbdoctorinfo where DoctorCode=b.RefferBy) as RefferName,(select Degree from tbdoctorinfo where DoctorCode=b.RefferBy) as Degree ,b.TotalCharge ,b.PercentDiscount,b.Discount,b.ManualDiscount,b.totalDiscount,b.TotalPayable as ActualPayable,(select ISNULL(sum(Cash),0) from TbLabPaymentHistory where BillNo='"+labId+"' and FiscalYear='"+FiscalYear+"' and CMonth='"+BillMonth+"'  and PaymentStatus='Paid')-(select ISNULL(sum(Cash),0) from TbLabPaymentHistory where BillNo='"+labId+"' and FiscalYear='"+FiscalYear+"' and CMonth='"+BillMonth+"'  and PaymentStatus='Refund') as TotalPaidAmount from TbLabTestHistory a join TbLabPatient b on b.labId=a.labId and b.FiscalYear=a.FiscalYear and b.CMonth=a.CMonth where b.FiscalYear='"+FiscalYear+"' and b.CMonth='"+BillMonth+"' and b.labId='"+labId+"' and a.RefundStatus!='1' and a.labId='"+labId+"' and a.FiscalYear='"+FiscalYear+"' and a.CMonth='"+BillMonth+"'  order by a.type,a.testGroupId,a.testName";
				report="LabStatementReport/PatientLabMoneyReceipte.jrxml";
				JasperDesign jd=JRXmlLoader.load(report);
				JRDesignQuery jq=new JRDesignQuery();
				System.out.println(sql);
				jq.setText(sql);
				jd.setQuery(jq);
				JasperReport jr=JasperCompileManager.compileReport(jd);
				JasperPrint jp=JasperFillManager.fillReport(jr, map,db.con);
				JasperViewer.viewReport(jp, false);
				JasperPrintManager.printReport(jp, true);
			}
			if(btnLabSlip.isSelected()){
				sql="select a.testGroupId,ISNULL((select GroupName from tblabtestgroup where SN=testGroupId),'Tube') as GroupName,(select roomNO from tblabtestgroup where SN=testGroupId)as roomNo,a.testName,a.rate*a.qty as rate,a.RefundStatus,b.labId,b.RegNo,b.PatientName,b.Age,b.Month,b.day,b.Cabin,b.Sex,b.Mobile,b.DateTime,b.SampleCollect,b.ReportDelivery,(select username from tblogin where user_id=b.CreateBy) as UserName,b.RefferBy,(select Name from tbdoctorinfo where DoctorCode=b.RefferBy) as RefferName,(select Degree from tbdoctorinfo where DoctorCode=b.RefferBy) as Degree ,b.TotalCharge ,b.PercentDiscount,b.Discount,b.ManualDiscount,b.totalDiscount,b.TotalPayable as ActualPayable,(select ISNULL(sum(Cash),0) from TbLabPaymentHistory where BillNo='"+labId+"' and FiscalYear='"+FiscalYear+"' and CMonth='"+BillMonth+"'  and PaymentStatus='Paid')-(select ISNULL(sum(Cash),0) from TbLabPaymentHistory where BillNo='"+labId+"' and FiscalYear='"+FiscalYear+"' and CMonth='"+BillMonth+"'  and PaymentStatus='Refund') as TotalPaidAmount from TbLabTestHistory a join TbLabPatient b on b.labId=a.labId and b.FiscalYear=a.FiscalYear and b.CMonth=a.CMonth where b.FiscalYear='"+FiscalYear+"' and b.CMonth='"+BillMonth+"' and b.labId='"+labId+"' and a.RefundStatus!='1' and a.labId='"+labId+"' and a.FiscalYear='"+FiscalYear+"' order by roomNo, a.type,a.testGroupId,a.testName";
				report="LabStatementReport/LabSlipDepartmentWise.jrxml";
				JasperDesign jd=JRXmlLoader.load(report);
				JRDesignQuery jq=new JRDesignQuery();
				System.out.println(sql);
				jq.setText(sql);
				jd.setQuery(jq);
				JasperReport jr=JasperCompileManager.compileReport(jd);
				JasperPrint jp=JasperFillManager.fillReport(jr, map,db.con);
				JasperViewer.viewReport(jp, false);
				JasperPrintManager.printReport(jp, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void btnFindEvent(String BillId,String FiscalYear,String BillMonth) {
		btnClearEvent();
		System.out.println("BillId "+BillId);
		System.out.println("FiscalYear "+FiscalYear);
		if(!BillId.toString().isEmpty()){
			if(checkBillFound(BillId,FiscalYear,BillMonth)){
				try {
					int mode=0;
					String CFiscalYear="";
					ResultSet rs=db.sta.executeQuery("select *,(select ISNULL(sum(cash+card),0) as Paid from TbLabPaymentHistory where FiscalYear='"+FiscalYear+"' and CMonth='"+BillMonth+"'  and BillNo='"+BillId+"' and PaymentStatus='Paid') as adPaid,(select ISNULL(sum(cash+card),0) as Refund from TbLabPaymentHistory where FiscalYear='"+FiscalYear+"' and CMonth='"+BillMonth+"'  and BillNo='"+BillId+"' and PaymentStatus='Refund') as Refund,(select tbdoctorinfo.Name from tbdoctorinfo where tbdoctorinfo.DoctorCode=tblabpatient.RefferBy) as Reffer,(select tbdoctorinfo.Name from tbdoctorinfo where tbdoctorinfo.DoctorCode=tblabpatient.RfPersionId) as RFPersion,(select vName from tbcorporateinfo where iSl=tblabpatient.CorporateId)as CorporateName,(select vName from tbmanagementinfo where iSl=tblabpatient.DirectorId)as DirectorName ,(select username from tblogin where tblogin.user_id=tblabpatient.CreateBy)as UserName from  tblabpatient where FiscalYear='"+FiscalYear+"' and CMonth='"+BillMonth+"'  and labId='"+BillId+"' ");
					while(rs.next()){

						txtMrNo.setText(rs.getString("MrNo"));
						txtDateTime.setDate(rs.getDate("DateTime"));
						cmbPatientType.setSelectedItem(rs.getString("type"));
						cmbRegNo.txtMrNo.setText(rs.getString("RegNo"));

						txtBillFiscalYear.setText(rs.getString("FiscalYear"));
						txtBillMonth.setText(rs.getString("CMonth"));
						txtBC.setText(rs.getString("Cabin"));
						txtPatientName.setText(rs.getString("PatientName"));
						txtPhone.setText(rs.getString("Mobile"));
						txtAge.setText(rs.getString("Age"));
						txtMonth.setText(rs.getString("Month"));
						txtDay.setText(rs.getString("Day"));
						cmbsex.setSelectedItem(rs.getString("Sex"));
						cmbRefferBy.txtMrNo.setText(rs.getString("Reffer"));
						cmbRFPersion.txtMrNo.setText(rs.getString("RFPersion"));
						txtAddress.setText(rs.getString("address"));
						txtPercentDiscount.setText(df.format(Double.parseDouble(rs.getString("PercentDiscount"))));
						txtManualDiscount.setText(df.format(Double.parseDouble(rs.getString("ManualDiscount"))));
						txtSpDiscount.setText(df.format(Double.parseDouble(rs.getString("ManualDiscount"))));
						txtGeneralDiscount.setText(df.format(Double.parseDouble(rs.getString("Discount"))));
						txtTotalCharge.setText(df.format(Double.parseDouble(rs.getString("TotalCharge"))));
						txtGrandTotal.setText(df.format(Double.parseDouble(rs.getString("TotalCharge"))));			
						txtTotalPayable.setText(df.format(Double.parseDouble(rs.getString("TotalPayable"))));
						txtAdvancepaid.setText(df.format(Double.parseDouble(rs.getString("Paid"))));
						txtRefund.setText(df.format(Double.parseDouble(rs.getString("Refund"))));
						txtBillFiscalYear.setText(rs.getString("FiscalYear"));
						//cmbFiscalYear.setSelectedItem(rs.getString("FiscalYear").toString());
						//double Due=Double.parseDouble(txtTotalPayable.getText().trim().toString())-Double.parseDouble(rs.getString("adPaid"));
						double Due=(Double.parseDouble(rs.getString("TotalPayable"))+Double.parseDouble(rs.getString("Refund")))-Double.parseDouble(rs.getString("Paid"));
						txtDues.setText(Due<0?"0":Double.toString(Due));
						txtCO.setText(rs.getString("CO"));
						txtSampleCollection.setText(rs.getString("SampleCollect"));
						time=rs.getString("ReportDelivery");
						paidValue=Double.parseDouble(rs.getString("Paid"));
						user=rs.getString("UserName");
					}
					StringTokenizer SparateDeliverTime=new StringTokenizer(time,":");
					int k=0;
					String DeliverDate="",DeliveryTime="";

					while(SparateDeliverTime.hasMoreElements()){
						if(k==0){
							DeliverDate=SparateDeliverTime.nextToken();
						}
						else if(k==1){
							DeliveryTime=SparateDeliverTime.nextToken();
						}
						k++;
					}

					System.out.println("DeliverDate "+DeliverDate);
					System.out.println("DeliveryTime "+DeliveryTime);

					txtDate.setValue(new SimpleDateFormat("dd:MM:yyyy").parse(DeliverDate.replaceAll("-", ":")));
					txtTime.setText(DeliveryTime);
					//cmbFiscalYear.setSelectedItem(CFiscalYear);

					for(int a=table.getRowCount()-1;a>=0;a--){
						model.removeRow(a);
					}
					int dataRow=0;
					double grandTotal=0.0;
					MainTestValue=0;
					int i=1;
					ResultSet rs1=db.sta.executeQuery("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+FiscalYear+"' and tblabtesthistory.CMonth='"+BillMonth+"'  and tblabtesthistory.labId='"+BillId+"' and tblabtesthistory.RefundStatus='0' order by RefundStatus,type asc");
					while(rs1.next()){
						grandTotal=grandTotal+Double.parseDouble(rs1.getString("rate"));

						double rateAmt=Double.parseDouble(rs1.getString("rate"));
						double discountAmt=Double.parseDouble(rs1.getString("discount"));
						int qty=Integer.parseInt(rs1.getString("qty"));
						double disValue=(rateAmt*discountAmt/100)*qty;
						double netValue=(rateAmt*qty)-(rateAmt*discountAmt/100)*qty;

						model.addRow(new Object[]{i,rs1.getString("SN"),rs1.getString("testCode"),rs1.getString("testName"),qty,rateAmt,disValue,netValue,new ImageIcon("icon/delete.png"),new Boolean(rs1.getString("RefundStatus").equals("0")?false:true)});
						i++;
						dataRow++;
					}	

					ResultSet rs2=db.sta.executeQuery("select qty,rate from tblabtesthistory where tblabtesthistory.FiscalYear='"+FiscalYear+"' and tblabtesthistory.CMonth='"+BillMonth+"'  and tblabtesthistory.labId='"+BillId+"' and type=1");
					while(rs2.next()){
						MainTestValue=MainTestValue+(Double.parseDouble(rs2.getString("qty"))*Double.parseDouble(rs2.getString("rate")));
					}
					//txtGrandTotal.setText(df.format(grandTotal));
					GrossAmount=grandTotal;
					find=1;
					Instatus=1;

					String RegNo="";
					StringTokenizer token=new StringTokenizer(cmbRegNo.txtMrNo.getText().trim().toString()," ");
					while(token.hasMoreTokens()){
						RegNo=token.nextToken();
						break;
					}
					if(checkRegisrationPatient(RegNo)){
						//IsEditable(false,1);
						txtPaidInCash.setEditable(false);
					}

					cmbRegNo.combo.setEnabled(false);
					cmbTestName.combo.setEnabled(false);
					btnTestSubmit.setEnabled(false);
					tableR.setEnabled(false);
					txtPercentDiscount.setEnabled(false);
					txtManualDiscount.setEnabled(false);

					txtPaidInCash.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e);
				}
			}
			else{
				btnClearEvent();
				JOptionPane.showMessageDialog(null, "Warning!!,Invalid Lab Bill");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Bill Number");
		}
	}
	private boolean checkBillFound(String Bill,String FiscalYear,String BillMonth){
		try {
			ResultSet rs=db.sta.executeQuery("select tblabpatient.labId from tblabpatient where  tblabpatient.labId='"+Bill+"' and tblabpatient.FiscalYear='"+FiscalYear+"' and tblabpatient.CMonth='"+BillMonth+"' ");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private void labpatientinserdata() {
		try {
			int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Confrim Parmanently","Confrim....",JOptionPane.YES_NO_OPTION);
			if(confrim==JOptionPane.YES_OPTION){
				ConfirmWithStatus(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void ConfirmWithStatus(int i){
		try {
			String RegNo="",PatientFiscaleYear="",RegYear="";
			StringTokenizer token=new StringTokenizer(cmbRegNo.txtMrNo.getText().trim().toString()," ");
			while(token.hasMoreTokens()){
				RegYear=token.nextToken();
				break;
			}

			int k=0;
			StringTokenizer Toekn=new StringTokenizer(RegYear,"/");
			while(Toekn.hasMoreTokens()){
				if(k==0){
					RegNo=Toekn.nextToken();
				}
				if(k==1){
					PatientFiscaleYear=Toekn.nextToken();
				}
				k++;
			}
			if(find==0){
				if(Double.parseDouble(txtPaidInCash.getText().trim().toString())<=Double.parseDouble(txtTotalPayable.getText().trim().toString())){
					autoMRPId();
					date_take();
					String refferId="",RfPersionId="",CommissionAllow="0";
					ResultSet rs1=db.sta.executeQuery("select tbdoctorinfo.DoctorCode from tbdoctorinfo where tbdoctorinfo.Name='"+cmbRefferBy.txtMrNo.getText().trim().toString()+"'");
					while(rs1.next()){
						refferId=rs1.getString("DoctorCode");

					}
					ResultSet rs2=db.sta.executeQuery("select tbdoctorinfo.DoctorCode,tbdoctorinfo.type from tbdoctorinfo where tbdoctorinfo.Name='"+cmbRFPersion.txtMrNo.getText().trim().toString()+"'");
					while(rs2.next()){
						RfPersionId=rs2.getString("DoctorCode");
						CommissionAllow=rs2.getString("type").equals("Referral")?"1":"0";
					}
					if(!checkRegisrationPatient(RegNo)){
						insertdata(txtMrNo.getText().trim().toString(),PatientFiscaleYear,"2",refferId,RfPersionId,CommissionAllow,i);
					}
					else{
						insertdata(RegNo,PatientFiscaleYear,"1",refferId,RfPersionId,CommissionAllow,i);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Sorry!!,Cash Amount Can't Be More Than Payable Amount");
				}

			}
			else if(find==1){


				/*				StringTokenizer token1=new StringTokenizer(cmbFindLabBill.txtMrNo.getText().trim().toString()," ");
				while(token1.hasMoreTokens()){
					BillYear=token1.nextToken();
					break;
				}

				int m=0;
				StringTokenizer Toekn1=new StringTokenizer(BillYear,"/");
				while(Toekn1.hasMoreTokens()){
					if(m==0){
						Bill=Toekn1.nextToken();
					}
					if(m==1){
						FiscaleYear=Toekn1.nextToken();
					}
					m++;
				}*/

				String refferId="",RfPersionId="",CommissionAllow="0";
				ResultSet rs1=db.sta.executeQuery("select tbdoctorinfo.DoctorCode from tbdoctorinfo where tbdoctorinfo.Name='"+cmbRefferBy.txtMrNo.getText().trim().toString()+"'");
				while(rs1.next()){
					refferId=rs1.getString("DoctorCode");
				}
				
				ResultSet rs2=db.sta.executeQuery("select tbdoctorinfo.DoctorCode,tbdoctorinfo.type from tbdoctorinfo where tbdoctorinfo.Name='"+cmbRFPersion.txtMrNo.getText().trim().toString()+"'");
				while(rs2.next()){
					RfPersionId=rs2.getString("DoctorCode");
					CommissionAllow=rs2.getString("type").equals("Referral")?"1":"0";
				}

				if(Double.parseDouble(txtPaidInCash.getText().trim().toString())<=Double.parseDouble(txtDues.getText().trim().toString())){
					date_take();
					if(Double.parseDouble(txtPaidInCash.getText().trim().toString())>0.0){
						insertdata(txtMrNo.getText().trim().toString(),PatientFiscaleYear,"2",refferId,RfPersionId,CommissionAllow,i);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Only Due Amount");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private boolean checkNullDataFound(){
		try {
			ResultSet rs=db.sta.executeQuery("select *from tblabtesthistory where FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"' and labId IS NULL");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private int getCurrentPeriod(String RegNo,String FiscalYear) {
		int period=0;
		try {
			ResultSet rs=db.sta.executeQuery("select  period from tbpatientinfo where RegNo='"+RegNo+"' and FiscalYear='"+FiscalYear+"' order by period desc");
			while(rs.next()){
				period=Integer.parseInt(rs.getString("period"));
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return period;
	}
	private void insertdata(String Reg,String PatientFiscalYear,String type,String refferId,String RfpersionId,String commionAllow,int i){
		try {

			if(find==0){
				if(checkNullDataFound()){
					String directorId="",corporateId="";
					autoMRPId();
					//double cash=Double.parseDouble(txtPaidInCash.getText().trim().toString())-Double.parseDouble(txtRefund.getText().trim().toString());
					double Totaldiscount=Double.parseDouble(txtGeneralDiscount.getText().trim().toString())+Double.parseDouble(txtSpDiscount.getText().trim().toString());

					String update="update tblabtesthistory set labId='"+txtMrNo.getText().trim().toString()+"' where FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"' and labId IS NULL ";
					System.out.println(update);
					db.sta.executeUpdate(update);

					String Udupdate="update tbUdlabtesthistory set labId='"+txtMrNo.getText().trim().toString()+"' where FiscalYear='"+getFiscelYear()+"' and tbUdlabtesthistory.CMonth='"+getCurrentMonth()+"'  and createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"' and labId IS NULL ";
					System.out.println(Udupdate);
					db.sta.executeUpdate(Udupdate);

					String Counterupdate="delete from TbLabCounterWisePatientInfomation where createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"' and labId IS NULL ";
					System.out.println(Counterupdate);
					db.sta.executeUpdate(Counterupdate);

					String patienttype=cmbPatientType.getSelectedIndex()==0?"Outdoor":"Indoor";
					int CuPeriod=getCurrentPeriod(Reg,PatientFiscalYear);

					String sql="insert into tblabpatient  values('"+txtMrNo.getText().trim().toString()+"',"
							+ "'"+patienttype+"',"
							+ "'"+txtMrNo.getText().trim().toString()+"',"
							+ "'"+Reg+"',"
							+ "'"+txtPatientName.getText().trim().toString()+"',"
							+ "'"+txtPhone.getText().trim().toString()+"',"
							+ "'"+txtAge.getText().trim().toString()+"',"
							+ "'"+txtMonth.getText().trim().toString()+"',"
							+ "'"+txtDay.getText().trim().toString()+"',"
							+ "'"+cmbsex.getSelectedItem().toString()+"',"
							+ "'"+txtBC.getText().trim().toString()+"',"
							+ "'"+txtAddress.getText().trim().toString()+"',"
							+ "'"+RfpersionId+"',"
							+ "'"+refferId+"',"
							+ "'"+commionAllow+"',"
							+ "'"+corporateId+"',"
							+ "'"+directorId+"',"
							+ "'',"
							+ "CURRENT_TIMESTAMP,"
							+ "'"+txtPercentDiscount.getText().trim().toString()+"',"
							+ "'"+txtGeneralDiscount.getText().trim().toString()+"',"
							+ "'"+txtSpDiscount.getText().trim().toString()+"',"
							+ "'"+Totaldiscount+"',"
							+ "'"+txtTotalCharge.getText().trim().toString()+"',"
							+ "'"+txtTotalPayable.getText().trim().toString()+"',"
							+ "'"+txtPaidInCash.getText().trim().toString()+"',"
							+ "'"+txtVat.getText().trim().toString()+"',"
							+ "'"+txtCO.getText().trim().toString()+"',"
							+ "'"+txtSampleCollection.getText().trim().toString()+"',"
							+ "'"+new SimpleDateFormat("dd-MM-yyyy").format(txtDate.getValue())+" : "+txtTime.getText().toString()+"',"
							+ "'"+i+"',"
							+ "'"+txtCO.getText().trim().toString()+"',"
							+ "'"+txtPaidInCash.getText().trim().toString()+"',"
							+ "CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,"
							+ "'"+sessionBeam.getUserId()+"',NULL,'"+CuPeriod+"','"+PatientFiscalYear+"','"+getFiscelYear()+"','"+getCurrentMonth()+"',NULL,'0','0','0','0')";
					System.out.println(sql);
					db.sta.executeUpdate(sql);



					String Udsql="insert into tbUdlabpatient values('"+txtMrNo.getText().trim().toString()+"',"
							+ "'"+patienttype+"',"
							+ "'"+txtMrNo.getText().trim().toString()+"',"
							+ "'"+Reg+"',"
							+ "'"+txtPatientName.getText().trim().toString()+"',"
							+ "'"+txtPhone.getText().trim().toString()+"',"
							+ "'"+txtAge.getText().trim().toString()+"',"
							+ "'"+txtMonth.getText().trim().toString()+"',"
							+ "'"+txtDay.getText().trim().toString()+"',"
							+ "'"+cmbsex.getSelectedItem().toString()+"',"
							+ "'"+txtBC.getText().trim().toString()+"',"
							+ "'"+txtAddress.getText().trim().toString()+"',"
							+ "'"+RfpersionId+"',"
							+ "'"+refferId+"',"
							+ "'"+commionAllow+"',"
							+ "'"+corporateId+"',"
							+ "'"+directorId+"',"
							+ "'',"
							+ "CURRENT_TIMESTAMP,"
							+ "'"+txtPercentDiscount.getText().trim().toString()+"',"
							+ "'"+txtGeneralDiscount.getText().trim().toString()+"',"
							+ "'"+txtSpDiscount.getText().trim().toString()+"',"
							+ "'"+Totaldiscount+"',"
							+ "'"+txtTotalCharge.getText().trim().toString()+"',"
							+ "'"+txtTotalPayable.getText().trim().toString()+"',"
							+ "'"+txtPaidInCash.getText().trim().toString()+"',"
							+ "'"+txtVat.getText().trim().toString()+"',"
							+ "'"+txtSampleCollection.getText().trim().toString()+"',"
							+ "'"+txtCO.getText().trim().toString()+"',"
							+ "'"+new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getValue())+" Time "+txtTime.getText().toString()+"',"
							+ "'"+i+"',"
							+ "'"+txtCO.getText().trim().toString()+"',"
							+ "'"+txtPaidInCash.getText().trim().toString()+"',"
							+ "CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,"
							+ "'"+sessionBeam.getUserId()+"',NULL,'NEW','"+CuPeriod+"','"+PatientFiscalYear+"','"+getFiscelYear()+"','"+getCurrentMonth()+"',NULL,'0','0','0','0')";
					System.out.println(Udsql);
					db.sta.executeUpdate(Udsql);

					String MrNo=txtMrNo.getText().trim().toString();
					PaymentHistoryTransection(txtMrNo.getText().trim().toString(),Double.parseDouble(txtPaidInCash.getText().trim().toString()),getFiscelYear(),getCurrentMonth());

					//Acccounts Part..................

					String d_l_id="",c_l_id="";
					//Sales Transaction..................
					String legerId=getPatientLedger();
					d_l_id=legerId;
					c_l_id="49";


					int TransId=getTransId();
					String query1="insert into accftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy) values ('"+TransId+"','"+MrNo+"','Income','3','301','"+d_l_id+"','"+c_l_id+"','"+txtTotalPayable.getText().trim().toString()+"','Lab Sale Net Amount',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
					System.out.println(query1);
					db.sta.executeUpdate(query1);

					String Udquery1="insert into accUdftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy,Flag) values ('"+TransId+"','"+MrNo+"','Income','3','301','"+d_l_id+"','"+c_l_id+"','"+txtTotalPayable.getText().trim().toString()+"','Lab Sale Net Amount',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','NEW')";
					System.out.println(Udquery1);
					db.sta.executeUpdate(Udquery1);

					//Update Each Acount transaction id in TbOutdoorBillingDetails table
					String TranIdUpdate="update tblabpatient set TransId='"+TransId+"' where  labId='"+MrNo+"' ";
					db.sta.executeUpdate(TranIdUpdate);

					String UdTranIdUpdate="update tbUdlabpatient set TransId='"+TransId+"' where  labId='"+MrNo+"' ";
					db.sta.executeUpdate(UdTranIdUpdate);

					//Cash Transaction..................
					if(Double.parseDouble(txtPaidInCash.getText().trim().toString())>0){
						d_l_id="41";
						c_l_id=legerId;
						TransId=getTransId();
						String Cashquery1="insert into accftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy) values ('"+TransId+"','"+MrNo+"','Cash','3','301','"+d_l_id+"','"+c_l_id+"','"+txtPaidInCash.getText().trim().toString()+"','Cash For Diagnostic',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
						System.out.println(Cashquery1);
						db.sta.executeUpdate(Cashquery1);

						String UdCashquery1="insert into accUdftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy,Flag) values ('"+TransId+"','"+MrNo+"','Cash','3','301','"+d_l_id+"','"+c_l_id+"','"+txtPaidInCash.getText().trim().toString()+"','Cash For Diagnostic',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','NEW')";
						System.out.println(UdCashquery1);
						db.sta.executeUpdate(UdCashquery1);

						String CashUpdate="update tblabpatient set CashId='"+TransId+"' where labId='"+MrNo+"' ";
						db.sta.executeUpdate(CashUpdate);

						String UdCashUpdate="update tbUdlabpatient set CashId='"+TransId+"' where labId='"+MrNo+"' ";
						db.sta.executeUpdate(UdCashUpdate);
					}


					btnPrintEvent(MrNo,getFiscelYear(),getCurrentMonth());
					btnClearEvent();
					autoMRPId();
					setUserCurrentTransection();
					find=0;
					checkCounter();
					LoadLabBillNo();
				}
				else{
					JOptionPane.showMessageDialog(null, "Sorry!!,Transection is not complete!!");
				}
			}
			else if(find==1){

				String Bill=txtMrNo.getText().trim().toString();
				String FiscalYear=txtBillFiscalYear.getText().trim().toString();
				String BillMonth=txtBillMonth.getText().trim().toString();
				/*				String Bill="",FiscalYear="",BillYear="";
				StringTokenizer token1=new StringTokenizer(cmbFindLabBill.txtMrNo.getText().trim().toString()," ");
				while(token1.hasMoreTokens()){
					BillYear=token1.nextToken();
					break;
				}

				int m=0;
				StringTokenizer Toekn1=new StringTokenizer(BillYear,"/");
				while(Toekn1.hasMoreTokens()){
					if(m==0){
						Bill=Toekn1.nextToken();
					}
					if(m==1){
						FiscalYear=Toekn1.nextToken();
					}
					m++;
				}*/

				double actualAmt=Double.parseDouble(txtTotalPayable.getText().trim().toString())+Double.parseDouble(txtRefund.getText().trim().toString())-Double.parseDouble(txtAdvancepaid.getText().trim().toString());
				double cash=Double.parseDouble(txtPaidInCash.getText().trim().toString());
				if(cash<=actualAmt){
					String directorId="",corporateId="";
					double Totaldiscount=Double.parseDouble(txtGeneralDiscount.getText().trim().toString())+Double.parseDouble(txtSpDiscount.getText().trim().toString());
					int temp=0;



					String sql="update tblabpatient set Paid=Paid+'"+cash+"',DeliverStatus='"+i+"',lasPaid='"+txtPaidInCash.getText().trim().toString()+"',entryTime=CURRENT_TIMESTAMP where labId='"+Bill+"' and FiscalYear='"+FiscalYear+"' and CMonth='"+BillMonth+"'";
					System.out.println(sql);
					db.sta.executeUpdate(sql);

					String patienttype=cmbPatientType.getSelectedIndex()==0?"Outdoor":"Indoor";
					int CuPeriod=getCurrentPeriod(Reg,PatientFiscalYear);
					String Udsql="insert into tbUdlabpatient values('"+txtMrNo.getText().trim().toString()+"',"
							+ "'"+patienttype+"',"
							+ "'"+txtMrNo.getText().trim().toString()+"',"
							+ "'"+Reg+"',"
							+ "'"+txtPatientName.getText().trim().toString()+"',"
							+ "'"+txtPhone.getText().trim().toString()+"',"
							+ "'"+txtAge.getText().trim().toString()+"',"
							+ "'"+txtMonth.getText().trim().toString()+"',"
							+ "'"+txtDay.getText().trim().toString()+"',"
							+ "'"+cmbsex.getSelectedItem().toString()+"',"
							+ "'"+txtBC.getText().trim().toString()+"',"
							+ "'"+txtAddress.getText().trim().toString()+"',"
							+ "'"+RfpersionId+"',"
							+ "'"+refferId+"',"
							+ "'"+commionAllow+"',"
							+ "'"+corporateId+"',"
							+ "'"+directorId+"',"
							+ "'',"
							+ "CURRENT_TIMESTAMP,"
							+ "'"+txtPercentDiscount.getText().trim().toString()+"',"
							+ "'"+txtGeneralDiscount.getText().trim().toString()+"',"
							+ "'"+txtSpDiscount.getText().trim().toString()+"',"
							+ "'"+Totaldiscount+"',"
							+ "'"+txtTotalCharge.getText().trim().toString()+"',"
							+ "'"+txtTotalPayable.getText().trim().toString()+"',"
							+ "'"+txtPaidInCash.getText().trim().toString()+"',"
							+ "'"+txtVat.getText().trim().toString()+"',"
							+ "'"+txtCO.getText().trim().toString()+"',"
							+ "'"+txtSampleCollection.getText().trim().toString()+"',"
							+ "'"+new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getValue())+" Time "+txtTime.getText().toString()+"',"
							+ "'"+i+"',"
							+ "'"+txtCO.getText().trim().toString()+"',"
							+ "'"+txtPaidInCash.getText().trim().toString()+"',"
							+ "CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,"
							+ "'"+sessionBeam.getUserId()+"','"+sessionBeam.getUserId()+"','UPDATE','"+CuPeriod+"','"+PatientFiscalYear+"','"+getFiscelYear()+"','"+getCurrentMonth()+"',NULL,'0','0','0','0')";
					System.out.println(Udsql);
					db.sta.executeUpdate(Udsql);

					PaymentHistoryTransection(txtMrNo.getText().trim().toString(),Double.parseDouble(txtPaidInCash.getText().trim().toString()),FiscalYear,BillMonth);
					String MrNo=txtMrNo.getText().trim().toString();

					String d_l_id="",c_l_id="";
					String legerId=getPatientLedger();



					String BfTransId="";
					ResultSet rs=db.sta.executeQuery("select TransId from TbLabPatient where labId='"+MrNo+"' ");
					while(rs.next()){
						BfTransId=rs.getString("TransId");
					}

					String UpdateAccSale="update accftransection set amount='"+txtTotalPayable.getText().trim().toString()+"',entryTime=CURRENT_TIMESTAMP where transectionid='"+BfTransId+"'";
					db.sta.executeUpdate(UpdateAccSale);
					//Cash Transaction..................
					if(Double.parseDouble(txtPaidInCash.getText().trim().toString())>0){
						d_l_id="41";
						c_l_id=legerId;
						int TransId=getTransId();
						String query1="insert into accftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy) values ('"+TransId+"','"+MrNo+"','Cash','3','301','"+d_l_id+"','"+c_l_id+"','"+txtPaidInCash.getText().trim().toString()+"','Cash For Diagnostic',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
						System.out.println(query1);
						db.sta.executeUpdate(query1);

						String Udquery1="insert into accUdftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy,Flag) values ('"+TransId+"','"+MrNo+"','Cash','3','301','"+d_l_id+"','"+c_l_id+"','"+txtPaidInCash.getText().trim().toString()+"','Cash For Diagnostic',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','Due')";
						System.out.println(Udquery1);
						db.sta.executeUpdate(Udquery1);

						String CashUpdate="update tblabpatient set CashId=CashId+',"+TransId+"' where   labId='"+MrNo+"' ";
						db.sta.executeUpdate(CashUpdate);

						String UdCashUpdate="update tbUdlabpatient set CashId=CashId+',"+TransId+"' where  labId='"+MrNo+"' ";
						db.sta.executeUpdate(UdCashUpdate);
					}

					//btnClearEvent();

					//btnFindEvent(MrNo,FiscalYear);
					btnPrintEvent(MrNo,FiscalYear,BillMonth);
					btnClearEvent();
					autoMRPId();
					setUserCurrentTransection();
					find=0;
					checkCounter();
					LoadLabBillNo();
				}
			}

			//btnClearEvent();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private int getTransId(){
		int TransId=0;
		try {
			String sql="select (ISNULL(max(transectionid),0)+1)as transectionid from accftransection";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				TransId=Integer.parseInt(rs.getString("transectionid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return TransId;
	}
	private boolean checkRegisrationPatient(String RegNo){
		try {
			if(!cmbRegNo.txtMrNo.getText().trim().toString().isEmpty()){
				ResultSet rs=db.sta.executeQuery("select RegNo,type from tbpatientinfo where RegNo='"+RegNo+"'");
				while(rs.next()){
					type=rs.getString("type");
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return false;
	}
	private String getPatientLedger(){
		String LedgerId="";
		try {

			String RegNo="",FiscaleYear="",RegYear="";
			StringTokenizer token=new StringTokenizer(cmbRegNo.txtMrNo.getText().trim().toString()," ");
			while(token.hasMoreTokens()){
				RegYear=token.nextToken();
				break;
			}

			int temp=0;
			ResultSet rs=db.sta.executeQuery("select ledgerId from accfledger where ledgerTitle='"+RegYear+"'");
			while(rs.next()){
				LedgerId=rs.getString("ledgerId");
				temp=1;
				break;
			}

			if(temp==0){
				LedgerId="125";
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return LedgerId;
	}
	public void btnClearEvent() {
		for(int a=tableR.getRowCount()-1;a>=0;a--){
			modelR.removeRow(a);
		}
		txtBillFiscalYear.setText(getFiscelYear());

		cmbRegNo.txtMrNo.setText("");
		txtBC.setText("");
		txtPatientName.setText("");
		txtDateTime.setDate(new Date());
		txtPhone.setText("");
		txtAge.setText("");
		txtMonth.setText("");
		txtDay.setText("");
		cmbsex.setSelectedItem("");
		//cmbRFPersion.txtMrNo.setText("");
		cmbRefferBy.txtMrNo.setText("");
		txtAddress.setText("");
		cmbTestCode.txtMrNo.setText("");
		cmbTestName.txtMrNo.setText("");
		txtRate.setText("");
		txtAmount.setText("");
		txtManualDiscount.setText("");
		txtCO.setText("");
		txtDate.setValue(new Date());
		txtPrimary.setText("");
		txtClinical.setText("");
		txtGrandTotal.setText("0.0");
		txtTotalCharge.setText("0.0");
		txtPercentDiscount.setText("0.0");
		txtGeneralDiscount.setText("0.0");
		txtSpDiscount.setText("0.0");
		txtAdvancepaid.setText("0.0");
		txtTotalPayable.setText("0.0");
		txtPaidInCash.setText("0.0");
		txtDues.setText("0.0");
		txtCO.setText("");
		txtSampleCollection.setText("");
		txtVat.setText("0");
		txtRefund.setText("0.0");
		//txtReturn.setText("0.0");
		cmbDiscount.txtMrNo.setText("0");
		IsEditable(true,0);



		find=0;
		confrim=0;
		for(int a=table.getRowCount()-1;a>=0;a--){
			model.removeRow(a);
		}
		rowAdd();		
		cmbRegNo.combo.setEnabled(false);

		cmbPatientType.setSelectedItem("Outdoor");

		cmbTestName.combo.setEnabled(true);
		btnTestSubmit.setEnabled(true);
		txtPercentDiscount.setEnabled(true);
		txtManualDiscount.setEnabled(true);
		tableR.setEnabled(true);

		autoMRPId();
	}
	private void IsEditable(boolean t,int i){
		//txtMrNo.setEditable(t);
		cmbRegNo.txtMrNo.setEditable(t);

		txtBC.setEditable(t);
		System.out.println("i "+i);
		if(i==1){
			txtPatientName.setEditable(t);
			txtPhone.setEditable(t);
			txtAge.setEditable(t);
			cmbsex.setEnabled(t);
			//cmbRFPersion.txtMrNo.setEditable(t);
			cmbRefferBy.txtMrNo.setEditable(t);
			txtAddress.setEditable(t);
		}
		else if(i==2){
			txtPatientName.setEditable(!t);
			txtPhone.setEditable(!t);
			txtAge.setEditable(!t);
			cmbsex.setEnabled(t);
			//cmbRFPersion.txtMrNo.setEditable(!t);
			cmbRefferBy.txtMrNo.setEditable(!t);
			txtAddress.setEditable(!t);
		}
		else{
			txtPatientName.setEditable(t);
			txtPhone.setEditable(t);
			txtAge.setEditable(t);
			cmbsex.setEnabled(t);
			cmbRefferBy.txtMrNo.setEditable(t);
			cmbRefferBy.txtMrNo.setEditable(t);
			txtAddress.setEditable(t);

		}

		cmbTestCode.txtMrNo.setEditable(t);
		cmbTestName.txtMrNo.setEditable(t);
		//txtRate.setEditable(t);
		txtAmount.setEditable(t);
		txtPercentDiscount.setEditable(t);
		txtManualDiscount.setEditable(t);
		txtCO.setEditable(t);
		txtPrimary.setEditable(t);
		txtClinical.setEditable(t);
		/*		txtGrandTotal.setEditable(t);
		txtTotalCharge.setEditable(t);
		txtGeneralDiscount.setEditable(t);
		txtSpDiscount.setEditable(t);
		txtAdvancepaid.setEditable(t);
		txtTotalPayable.setEditable(t);*/
		//txtPaidInCash.setEditable(t);



		txtCO.setEditable(t);
		//txtVat.setEditable(t);
		//txtRefund.setEditable(t);
		//txtReturn.setEditable(t);
		table.setEnabled(t);
	}
	public void AutoPaymentHistoryId(){
		try {
			String sql="select (ISNULL(max(autoId),0)+1)as autoId from TbLabPaymentHistory";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				paymentId=rs.getString("autoId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	private void PaymentHistoryTransection(String BillNo,Double CashAmount,String FiscalYear,String BillMonth){
		try {
			String collectType="";

			if(new  SimpleDateFormat("yyyy-MM-dd").format(txtDateTime.getDate()).equals(new  SimpleDateFormat("yyyy-MM-dd").format(new Date()))){
				collectType = "New Collection";
			}else {
				collectType = "Due Collection";
			}



			if(find==0){
				AutoPaymentHistoryId();
				String type=cmbPatientType.getSelectedIndex()==1?"Indoor":"Outdoor";
				String paymentquery="insert into TbLabPaymentHistory values('"+paymentId+"','"+BillNo+"','"+CashAmount+"','0','"+type+"','Paid',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','"+FiscalYear+"','"+BillMonth+"','"+collectType+"')";
				System.out.println(paymentquery);
				db.sta.executeUpdate(paymentquery);

				String Udpaymentquery="insert into TbUdLabPaymentHistory values('"+paymentId+"','"+BillNo+"','"+CashAmount+"','0','"+type+"','Paid',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','NEW','"+FiscalYear+"','"+BillMonth+"','"+collectType+"')";
				System.out.println(Udpaymentquery);
				db.sta.executeUpdate(Udpaymentquery);
			}
			else if(find==1){

				AutoPaymentHistoryId();
				String type=cmbPatientType.getSelectedIndex()==1?"Indoor":"Outdoor";
				String paymentquery="insert into TbLabPaymentHistory values('"+paymentId+"','"+BillNo+"','"+CashAmount+"','0','"+type+"','Paid',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','"+FiscalYear+"','"+BillMonth+"','"+collectType+"')";
				System.out.println(paymentquery);
				db.sta.executeUpdate(paymentquery);

				String Udpaymentquery="insert into TbUdLabPaymentHistory values('"+paymentId+"','"+BillNo+"','"+CashAmount+"','0','"+type+"','Paid',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','DUE','"+FiscalYear+"' ,'"+BillMonth+"','"+collectType+"')";
				System.out.println(Udpaymentquery);
				db.sta.executeUpdate(Udpaymentquery);
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void btnInEvent(String TestName,int Ratetype) {
		try {
			if(!TestName.isEmpty()){
				String RegNo="",PatientFiscaleYear="",RegYear="";
				StringTokenizer token=new StringTokenizer(cmbRegNo.txtMrNo.getText().trim().toString()," ");
				while(token.hasMoreTokens()){
					RegYear=token.nextToken();
					break;
				}

				int i=0;
				StringTokenizer Toekn=new StringTokenizer(RegYear,"/");
				while(Toekn.hasMoreTokens()){
					if(i==0){
						RegNo=Toekn.nextToken();
					}
					if(i==1){
						PatientFiscaleYear=Toekn.nextToken();
					}
					i++;
				}

				if(checkRegisrationPatient(RegNo)){
					RegNo=RegNo;
				}
				else{
					RegNo=txtMrNo.getText().trim().toString();
				}

				if(validTestName(TestName)){
					if(!checkDoplicateTest(TestName)){
						double Rate=0;
						String TestCode="";
						if(Ratetype==1){
							ResultSet rs=db.sta.executeQuery("select *from tbtestname where TestName='"+TestName+"'");
							while(rs.next()){
								TestCode=rs.getString("TestCode");
								Rate=Double.parseDouble(rs.getString("Rate"));
							}
						}
						else if(Ratetype==2){
							ResultSet rs=db.sta.executeQuery("select *from tbtestname where TestName='"+TestName+"'");
							while(rs.next()){
								TestCode=rs.getString("TestCode");
							}
							Rate=Double.parseDouble(tableR.getValueAt(tableR.getSelectedRow(), 3).toString());
						}

						if(find==0){
							autoId();
							String HeadId=getTestGroupId(TestName);
							int Distype=getDiscounttType(TestName);
							double CmdDiscount=getCmdDiscount(TestName);

							double TestDiscount=0;
							if(Distype==0){
								TestDiscount=0;
							}
							else{
								TestDiscount=Double.parseDouble(txtPercentDiscount.getText().trim().toString().isEmpty()?"0":txtPercentDiscount.getText().trim().toString());
							}

							String sql ="insert into tblabtesthistory (regNo,SN,testCode,testName,testGroupId,qty,rate,discount,CmdDiscount,discountAllow,ResultStatus,RefundStatus,type,counter,date,entryTime,createBy,FiscalYear,CMonth) values ('"+RegNo+"','"+SnId+"','"+TestCode+"','"+TestName+"','"+HeadId+"',1,'"+Rate+"','"+TestDiscount+"','"+CmdDiscount+"','"+Distype+"','NOT DONE','0','1','"+counter+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','"+getFiscelYear()+"','"+getCurrentMonth()+"')";
							System.out.println(sql);
							db.sta.executeUpdate(sql);

							String Udsql ="insert into tbUdlabtesthistory (regNo,SN,testCode,testName,testGroupId,qty,rate,discount,CmdDiscount,discountAllow,ResultStatus,RefundStatus,type,counter,date,entryTime,createBy,flag,FiscalYear,CMonth) values ('"+RegNo+"','"+SnId+"','"+TestCode+"','"+TestName+"','"+HeadId+"',1,'"+Rate+"','"+TestDiscount+"','"+CmdDiscount+"','"+Distype+"','NOT DONE','0','1','"+counter+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','NEW','"+getFiscelYear()+"','"+getCurrentMonth()+"')";
							System.out.println(Udsql);
							db.sta.executeUpdate(Udsql);

							ArrayList SN=new ArrayList();
							ArrayList TId=new ArrayList();
							ArrayList NameOfPericular=new ArrayList();
							ArrayList NameOfRate=new ArrayList();
							ArrayList<Integer> NameOfQty=new ArrayList<Integer>();

							int count=0;
							ResultSet rs1=db.sta.executeQuery("select *from tbtestperticularname where TestHeadId=(select SN from tbtestname where TestName='"+TestName+"')");
							while(rs1.next()){
								SN.add(rs1.getString("SN"));
								TId.add(rs1.getString("TestPerCode"));
								NameOfPericular.add(rs1.getString("TestPerName"));
								NameOfRate.add(rs1.getString("rate"));
								NameOfQty.add(Integer.parseInt(rs1.getString("Qty")));
								count++;
								//model.addRow(new Object[]{rs1.getString("SN"),rs1.getString("TestPerCode"),rs1.getString("TestPerName"),df.format(Double.parseDouble(rs1.getString("rate"))),0,df.format(Double.parseDouble(rs1.getString("rate"))),new ImageIcon("icon/delete.png")});
							}
							for(int a=0;a<count;a++){
								if(!checkInvoiceDoublePerticular("select tblabtesthistory.TestName from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and  tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and tblabtesthistory.TestName='"+NameOfPericular.get(a).toString()+"' and tblabtesthistory.labId IS NULL and tblabtesthistory.counter='"+counter+"' and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"'")){
									autoId();

									String query ="insert into tblabtesthistory (regNo,SN,testCode,testName,qty,rate,discount,discountAllow,ResultStatus,RefundStatus,type,counter,date,entryTime,createBy,FiscalYear,CMonth) values ('"+RegNo+"','"+SN.get(a).toString()+"','"+TId.get(a).toString()+"','"+NameOfPericular.get(a).toString()+"','"+NameOfQty.get(a).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(a).toString()))+"','0','0','NOT DONE','0','2','"+counter+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','"+getFiscelYear()+"','"+getCurrentMonth()+"')";
									System.out.println(query);
									db.sta.executeUpdate(query);

									String Udquery ="insert into tbUdlabtesthistory (regNo,SN,testCode,testName,qty,rate,discount,discountAllow,ResultStatus,RefundStatus,type,counter,date,entryTime,createBy,flag,FiscalYear,CMonth) values ('"+RegNo+"','"+SN.get(a).toString()+"','"+TId.get(a).toString()+"','"+NameOfPericular.get(a).toString()+"','"+NameOfQty.get(a).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(a).toString()))+"','0','0','NOT DONE','0','2','"+counter+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','NEW','"+getFiscelYear()+"','"+getCurrentMonth()+"')";
									System.out.println(Udquery);
									db.sta.executeUpdate(Udquery);
								}
							}

							SN.clear();
							TId.clear();
							NameOfPericular.clear();
							NameOfRate.clear();

							String RefferId="";
							ResultSet rs2=db.sta.executeQuery("select tbdoctorinfo.DoctorCode from tbdoctorinfo where tbdoctorinfo.Name='"+cmbRefferBy.txtMrNo.getText().trim().toString()+"'");
							while(rs2.next()){
								RefferId=rs2.getString("DoctorCode");
							}
							double Percent=Double.parseDouble(txtPercentDiscount.getText().trim().toString().isEmpty()?"0":txtPercentDiscount.getText().trim().toString());
							double manual=Double.parseDouble(txtManualDiscount.getText().trim().toString().isEmpty()?"0":txtManualDiscount.getText().trim().toString());
							String CounterPInformationSql="insert into TbLabCounterWisePatientInfomation "
									+ "(Counter,"
									+ "PatientType,"
									+ "RegNo,"
									+ "PatientName,PatientFiscalYear,"
									+ "Mobile,"
									+ "Age,"
									+ "Month,"
									+ "day,"
									+ "Sex,"
									+ "Cabin,"
									+ "address,"
									+ "RfPersionId,"
									+ "RefferBy,"
									+ "PercentDiscount,"
									+ "ManualDiscount,"
									+ "EntryTime,"
									+ "CreateBy)"
									+ " values ('"+counter+"','"+cmbPatientType.getSelectedItem().toString()+"',"
									+ "'"+RegNo+"',"
									+ "'"+txtPatientName.getText().trim().toString()+"',"
									+ "'"+PatientFiscaleYear+"',"
									+ "'"+txtPhone.getText().trim().toString()+"',"
									+ "'"+txtAge.getText().trim().toString()+"',"
									+ "'"+txtMonth.getText().trim().toString()+"',"
									+ "'"+txtDay.getText().trim().toString()+"',"
									+ "'"+cmbsex.getSelectedItem().toString()+"',"
									+ "'"+txtBC.getText().trim().toString()+"',"
									+ "'"+txtAddress.getText().trim().toString()+"',"
									+ "'"+RefferId+"',"
									+ "'"+RefferId+"',"
									+ "'"+Percent+"',"
									+ "'"+manual+"',"
									+ "CURRENT_TIMESTAMP,"
									+ "'"+sessionBeam.getUserId()+"')"
									+ "";	
							System.out.println(CounterPInformationSql);
							db.sta.executeUpdate(CounterPInformationSql);
							showData("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"'  and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='"+counter+"' order by type asc");

							if(cmbPatientType.getSelectedIndex()==1){
								txtPaidInCash.setEditable(false);
							}
							else{
								txtPaidInCash.setEditable(true);
							}
							checkCounter();
						}
						else if(find==1){
							/*
							String Bill="",FiscalYear="",BillYear="";
							StringTokenizer token1=new StringTokenizer(cmbFindLabBill.txtMrNo.getText().trim().toString()," ");
							while(token1.hasMoreTokens()){
								BillYear=token1.nextToken();
								break;
							}

							int m=0;
							StringTokenizer Toekn1=new StringTokenizer(BillYear,"/");
							while(Toekn1.hasMoreTokens()){
								if(m==0){
									Bill=Toekn1.nextToken();
								}
								if(m==1){
									FiscalYear=Toekn1.nextToken();
								}
								m++;
							}

							autoId();
							int Distype=getDiscounttType(TestName);
							String HeadId=getTestGroupId(TestName);
							double CmdDiscount=getCmdDiscount(TestName);

							double TestDiscount=0;
							if(Distype==0){
								TestDiscount=0;
							}
							else{
								TestDiscount=Double.parseDouble(txtPercentDiscount.getText().trim().toString().isEmpty()?"0":txtPercentDiscount.getText().trim().toString());
							}

							String sql ="insert into tblabtesthistory (labId,regNo,SN,testCode,testName,testGroupId,qty,rate,discount,CmdDiscount,discountAllow,ResultStatus,RefundStatus,type,counter,date,entryTime,createBy,FiscalYear,CMonth) values ('"+Bill+"','"+RegNo+"','"+SnId+"','"+TestCode+"','"+TestName+"','"+HeadId+"',1,'"+Rate+"','"+TestDiscount+"','"+CmdDiscount+"','"+Distype+"','NOT DONE','0','1','"+counter+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','"+FiscalYear+"','"+BillName+"')";
							System.out.println(sql);
							db.sta.executeUpdate(sql);

							String Udsql ="insert into tbUdlabtesthistory (labId,regNo,SN,testCode,testName,testGroupId,qty,rate,discount,CmdDiscount,discountAllow,ResultStatus,RefundStatus,type,counter,date,entryTime,createBy,flag,FiscalYear,CMonth) values ('"+Bill+"','"+RegNo+"','"+SnId+"','"+TestCode+"','"+TestName+"','"+HeadId+"',1,'"+Rate+"','"+TestDiscount+"','"+CmdDiscount+"','"+Distype+"','NOT DONE','0','1','"+counter+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','NEW','"+FiscalYear+"','"+BillName+"')";
							System.out.println(Udsql);
							db.sta.executeUpdate(Udsql);


							ArrayList SN=new ArrayList();
							ArrayList TId=new ArrayList();
							ArrayList NameOfPericular=new ArrayList();
							ArrayList NameOfRate=new ArrayList();
							ArrayList<Integer> NameOfQty=new ArrayList<Integer>();

							int count=0;
							ResultSet rs1=db.sta.executeQuery("select *from tbtestperticularname where TestHeadId=(select SN from tbtestname where TestName='"+TestName+"')");
							while(rs1.next()){
								SN.add(rs1.getString("SN"));
								TId.add(rs1.getString("TestPerCode"));
								NameOfPericular.add(rs1.getString("TestPerName"));
								NameOfRate.add(rs1.getString("rate"));
								NameOfQty.add(Integer.parseInt(rs1.getString("Qty")));
								count++;
								//model.addRow(new Object[]{rs1.getString("SN"),rs1.getString("TestPerCode"),rs1.getString("TestPerName"),df.format(Double.parseDouble(rs1.getString("rate"))),0,df.format(Double.parseDouble(rs1.getString("rate"))),new ImageIcon("icon/delete.png")});
							}

							for(int a=0;a<count;a++){
								if(!checkInvoiceDoublePerticular("select tblabtesthistory.TestName from tblabtesthistory where tblabtesthistory.FiscalYear='"+FiscalYear+"' and tblabtesthistory.TestName='"+NameOfPericular.get(a).toString()+"' and tblabtesthistory.labId='"+Bill+"' and tblabtesthistory.counter='"+counter+"' and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"'")){
									autoId();

									String query ="insert into tblabtesthistory (labId,regNo,SN,testCode,testName,qty,rate,discount,discountAllow,ResultStatus,RefundStatus,type,counter,date,entryTime,createBy,FiscalYear) values ('"+Bill+"' ,'"+RegNo+"','"+SN.get(a).toString()+"','"+TId.get(a).toString()+"','"+NameOfPericular.get(a).toString()+"','"+NameOfQty.get(a).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(a).toString()))+"','0','0','NOT DONE','0','2','"+counter+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','"+FiscalYear+"')";
									System.out.println(query);
									db.sta.executeUpdate(query);

									String Udquery ="insert into tbUdlabtesthistory (labId,regNo,SN,testCode,testName,qty,rate,discount,discountAllow,ResultStatus,RefundStatus,type,counter,date,entryTime,createBy,flag,FiscalYear) values ('"+Bill+"' ,'"+RegNo+"','"+SN.get(a).toString()+"','"+TId.get(a).toString()+"','"+NameOfPericular.get(a).toString()+"','"+NameOfQty.get(a).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(a).toString()))+"','0','0','NOT DONE','0','2','"+counter+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','NEW','"+FiscalYear+"')";
									System.out.println(Udquery);
									db.sta.executeUpdate(Udquery);
								}
							}

							SN.clear();
							TId.clear();
							NameOfPericular.clear();
							NameOfRate.clear();

							txtGrandTotal.setText("0");
							txtGeneralDiscount.setText("0");
							txtSpDiscount.setText("0");
							txtAdvancepaid.setText("0");
							txtTotalCharge.setText("0");
							txtVat.setText("0");
							txtTotalCharge.setText("0");
							txtTotalPayable.setText("0");
							txtPaidInCash.setText("0");
							txtDues.setText("0");

							showData("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+FiscalYear+"' and tblabtesthistory.labId='"+Bill+"' order by type asc");

								String DPaymentQuery="delete from TbLabPaymentHistory where BillNo='"+cmbFindLabBill.getValue().toString()+"' and PaymentStatus='Paid'";
								db.sta.executeUpdate(DPaymentQuery);
							checkCounter();
							if(cmbPatientType.getSelectedIndex()==1){
								txtPaidInCash.setEditable(false);
							}
							else{
								txtPaidInCash.setEditable(true);
							}


							double Totaldiscount=Double.parseDouble(txtGeneralDiscount.getText().trim().toString())+Double.parseDouble(txtSpDiscount.getText().trim().toString());

							String Newsql="update tblabpatient set PatientName='"+txtPatientName.getText().trim().toString()+"',Mobile='"+txtPhone.getText().trim().toString()+"',Age='"+txtAge.getText().trim().toString()+"',Month='"+txtMonth.getText().trim().toString()+"',day='"+txtDay.getText().trim().toString()+"',Sex='"+cmbsex.getSelectedItem().toString()+"',address='"+txtAddress.getText().trim().toString()+"',PercentDiscount='"+txtPercentDiscount.getText().trim().toString()+"',Discount='"+txtGeneralDiscount.getText().trim().toString()+"',ManualDiscount='"+txtSpDiscount.getText().trim().toString()+"',totalDiscount='"+Totaldiscount+"',TotalCharge='"+txtTotalCharge.getText().trim().toString()+"',TotalPayable='"+txtTotalPayable.getText().trim().toString()+"',Paid='"+txtPaidInCash.getText().trim().toString()+"',lasPaid='"+txtPaidInCash.getText().trim().toString()+"',entryTime=CURRENT_TIMESTAMP,CreateBy='"+sessionBeam.getUserId()+"' where FiscalYear='"+FiscalYear+"' and LabId='"+Bill+"'";
							System.out.println(Newsql);
							db.sta.executeUpdate(Newsql);

							PaymentHistoryTransection(txtMrNo.getText().trim().toString(),Double.parseDouble(txtPaidInCash.getText().trim().toString()),FiscalYear);
							String patienttype=cmbPatientType.getSelectedIndex()==0?"Outdoor":"Indoor";
							int CuPeriod=getCurrentPeriod(cmbRegNo.txtMrNo.getText().trim().toString(),PatientFiscaleYear);

							String UdNewsql="insert into tbUdlabpatient values('"+txtMrNo.getText().trim().toString()+"',"
									+ "'"+patienttype+"',"
									+ "'"+txtMrNo.getText().trim().toString()+"',"
									+ "'"+cmbRegNo.txtMrNo.getText().trim().toString()+"',"
									+ "'"+txtPatientName.getText().trim().toString()+"',"
									+ "'"+txtPhone.getText().trim().toString()+"',"
									+ "'"+txtAge.getText().trim().toString()+"',"
									+ "'"+txtMonth.getText().trim().toString()+"',"
									+ "'"+txtDay.getText().trim().toString()+"',"
									+ "'"+cmbsex.getSelectedItem().toString()+"',"
									+ "'"+txtBC.getText().trim().toString()+"',"
									+ "'"+txtAddress.getText().trim().toString()+"',"
									+ "'',"
									+ "'',"
									+ "'',"
									+ "'',"
									+ "'',"
									+ "CURRENT_TIMESTAMP,"
									+ "'"+txtPercentDiscount.getText().trim().toString()+"',"
									+ "'"+txtGeneralDiscount.getText().trim().toString()+"',"
									+ "'"+txtSpDiscount.getText().trim().toString()+"',"
									+ "'"+Totaldiscount+"',"
									+ "'"+txtTotalCharge.getText().trim().toString()+"',"
									+ "'"+txtTotalPayable.getText().trim().toString()+"',"
									+ "'"+txtPaidInCash.getText().trim().toString()+"',"
									+ "'"+txtVat.getText().trim().toString()+"',"
									+ "'"+txtCO.getText().trim().toString()+"',"
									+ "'"+new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getValue())+" Time "+txtTime.getText().toString()+"',"
									+ "'',"
									+ "'"+txtCO.getText().trim().toString()+"',"
									+ "'"+txtPaidInCash.getText().trim().toString()+"',"
									+ "CURRENT_TIMESTAMP,"
									+ "'"+sessionBeam.getUserId()+"','UPDATE','"+CuPeriod+"','"+PatientFiscaleYear+"','"+getFiscelYear()+"')";
							System.out.println(UdNewsql);
							db.sta.executeUpdate(UdNewsql);*/

							//JOptionPane.showMessageDialog(null, "Test Entry Successfull!!");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Warning!!,Doplicate Test Don't Allow");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Valid Test Name");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Test Name");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void setCounterWisePendingPatientInfo(String sql){
		try {
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next()){
				cmbPatientType.setSelectedItem(rs.getString("PatientType"));
				cmbRegNo.txtMrNo.setText(rs.getString("RegNo")+"/"+rs.getString("PatientFiscalYear"));

				txtBC.setText(rs.getString("Cabin"));
				txtPatientName.setText(rs.getString("PatientName"));
				txtPhone.setText(rs.getString("Mobile"));
				txtAge.setText(rs.getString("Age"));
				txtMonth.setText(rs.getString("Month"));
				txtDay.setText(rs.getString("Day"));
				cmbsex.setSelectedItem(rs.getString("Sex"));
				cmbRefferBy.txtMrNo.setText(rs.getString("RefferName"));
				//cmbRFPersion.txtMrNo.setText(rs.getString("RFPersion"));
				txtAddress.setText(rs.getString("address"));
				txtPercentDiscount.setText(df.format(Double.parseDouble(rs.getString("PercentDiscount"))));
				txtManualDiscount.setText(df.format(Double.parseDouble(rs.getString("ManualDiscount"))));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private double getCmdDiscount(String TestName){
		double CmdDiscount=0;
		try {
			ResultSet rs=db.sta.executeQuery("select ISNULL(Discount,0) as Discount from tbtestname where TestName='"+TestName+"'");
			while(rs.next()){
				CmdDiscount=Double.parseDouble(rs.getString("Discount"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return CmdDiscount;
	}
	private String getTestGroupId(String TestName){

		String GroupId="";
		try {
			ResultSet rs=db.sta.executeQuery("select TestHeadId from tbtestname where TestName='"+TestName+"'");
			while(rs.next()){
				GroupId=rs.getString("TestHeadId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return GroupId;
	}
	private int getDiscounttType(String TestName){
		int type=0;
		try {
			ResultSet rs=db.sta.executeQuery("select TestName from tbtestname where DiscountAllow=1 and TestName='"+TestName+"'");
			while(rs.next()){
				type=1;
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return type;
	}
	private boolean validTestName(String TestName){
		try {
			ResultSet rs=db.sta.executeQuery("select *from tbtestname where TestName='"+TestName+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	public void checkCounter() {
		try {
			int c1=0,c2=0,c3=0,c4=0,c5=0;
			ResultSet rs=db.sta.executeQuery("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='1' order by type asc");
			while(rs.next()){
				c1=1;
			}

			ResultSet rs1=db.sta.executeQuery("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='2' order by type asc");
			while(rs1.next()){
				c2=1;
			}

			ResultSet rs2=db.sta.executeQuery("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='3' order by type asc");
			while(rs2.next()){
				c3=1;
			}

			ResultSet rs3=db.sta.executeQuery("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='4' order by type asc");
			while(rs3.next()){
				c4=1;
			}

			ResultSet rs4=db.sta.executeQuery("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='5' order by type asc");
			while(rs4.next()){
				c5=1;
			}

			if(c1==1){
				btnCounter1.setBackground(Color.red);
			}
			else{
				btnCounter1.setBackground(Color.green);
			}
			if(c2==1){
				btnCounter2.setBackground(Color.red);
			}
			else{
				btnCounter2.setBackground(Color.green);
			}
			if(c3==1){
				btnCounter3.setBackground(Color.red);
			}
			else{
				btnCounter3.setBackground(Color.green);
			}
			if(c4==1){
				btnCounter4.setBackground(Color.red);
			}
			else{
				btnCounter4.setBackground(Color.green);
			}
			if(c5==1){
				btnCounter5.setBackground(Color.red);
			}
			else{
				btnCounter5.setBackground(Color.green);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean checkInvoiceDoublePerticular(String sql){
		try {
			System.out.println("sql "+sql);
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private double getPaidAmount(String Bill,String FiscalYear,String BillMonth){
		double PaidAmount=0;
		try {
			ResultSet rs=db.sta.executeQuery("select ISNULL(sum(Cash+Card),0) as TotalPaid from TbLabPaymentHistory where BillNo='"+Bill+"' and FiscalYear='"+FiscalYear+"' and CMonth='"+BillMonth+"'");
			while(rs.next()){
				PaidAmount=Double.parseDouble(rs.getString("TotalPaid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return PaidAmount;
	}
	public void showData(String sql){
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}

			double sum=0.0,totalCharge=0.0,generalDiscount=0.0,MainTestValue=0.0,tdisamount=0.0,discount=0.0;
			double fixTotaldiscount=0.0;
			double grandtotal=0.0;
			ResultSet rs=db.sta.executeQuery(sql);
			int dataRow=0;
			int i=1;
			while(rs.next()){
				double rateAmt=Double.parseDouble(rs.getString("rate"));
				double discountAmt=Double.parseDouble(rs.getString("discount"));
				int qty=Integer.parseInt(rs.getString("qty"));
				double disValue=(rateAmt*discountAmt/100)*qty;
				double netValue=(rateAmt*qty)-(rateAmt*discountAmt/100)*qty;
				if(rs.getString("SN").toString().equals("1")){
					MainTestValue=MainTestValue+(qty*rateAmt);
				}
				model.addRow(new Object[]{i,rs.getString("SN"),rs.getString("testCode"),rs.getString("testName"),qty,rateAmt,disValue,netValue,new ImageIcon("icon/delete.png"),new Boolean(rs.getString("RefundStatus").equals("0")?false:true)});
				i++;
				dataRow++;
			}

			for(int a=0;a<table.getRowCount();a++){
				if(table.getValueAt(a, 0).toString()!=""){
					grandtotal=grandtotal+Double.parseDouble(table.getValueAt(a, 4).toString())*Double.parseDouble(table.getValueAt(a, 5).toString());
					double discountvalue=Double.parseDouble(table.getValueAt(a, 6).toString());
					//table.setValueAt(df.format(discountvalue), a, 5);
					tdisamount=tdisamount+discountvalue;
					fixTotaldiscount=fixTotaldiscount+discountvalue;
					double discountamount=Double.parseDouble(table.getValueAt(a, 5).toString())-discountvalue;
					//table.setValueAt(df.format(discountamount), a, 6);
					sum=sum+discountamount;
					generalDiscount=generalDiscount+discountvalue;
				}
			}

			txtGrandTotal.setText(df.format(grandtotal));
			txtTotalCharge.setText(df.format(grandtotal));

			txtSpDiscount.setText(txtManualDiscount.getText().trim().toString().isEmpty()?"0.0":txtManualDiscount.getText().trim().toString());
			txtGeneralDiscount.setText(df.format(tdisamount));


			double tpayable=Double.parseDouble(txtTotalCharge.getText().trim().toString())-(Double.parseDouble(txtGeneralDiscount.getText().toString())+Double.parseDouble(txtSpDiscount.getText().toString()));

			System.out.println("tpayable "+tpayable);
			txtAmount.setText(df.format(tpayable));
			txtTotalPayable.setText(df.format(tpayable));
			if(find==1){

				String Bill=txtMrNo.getText().trim().toString();
				String FiscalYear=txtBillFiscalYear.getText().trim().toString();
				String BillMonth=txtBillMonth.getText().trim().toString();
				txtAdvancepaid.setText(Double.toString(getPaidAmount(Bill,FiscalYear,BillMonth)));
				double Due=Double.parseDouble(txtTotalCharge.getText().trim().toString())-(Double.parseDouble(txtGeneralDiscount.getText().trim().toString())+Double.parseDouble(txtSpDiscount.getText().trim().toString())+Double.parseDouble(txtAdvancepaid.getText().trim().toString()));
				txtDues.setText(df.format(Due));
			}
			else{
				double Due=Double.parseDouble(txtTotalCharge.getText().trim().toString())-(Double.parseDouble(txtGeneralDiscount.getText().trim().toString())+Double.parseDouble(txtSpDiscount.getText().trim().toString())+Double.parseDouble(txtPaidInCash.getText().trim().toString()));
				txtDues.setText(df.format(Due));
			}
			txtAmount.setText(df.format(tpayable));

			cmbTestCode.txtMrNo.setText("");
			cmbTestName.txtMrNo.setText("");
			txtRate.setText("");


			rowAdd();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void rowAdd(){
		for(int a=0;a<10;a++){
			model.addRow(new Object[]{"","","","","","","","",new ImageIcon("icon/delete.png"),new Boolean(false)});	
		}
	}
	public void LoadLabBillNo(){
		try {
			cmbFindLabBill.v.clear();
			ResultSet rs=db.sta.executeQuery("select labId,FiscalYear from TbLabPatient where FiscalYear='"+cmbFiscalYear.getSelectedItem().toString()+"' and CMonth='"+cmbBillMonth.getSelectedItem()+"'  order by labId,FiscalYear desc");
			while(rs.next()){
				cmbFindLabBill.v.add(rs.getString("labId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void date_take(){
		DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date=new Date();
		startDate =dateformat.format(date).toString();
	}
	public void loadTestCode(){
		try {
			cmbTestCode.v.clear();
			cmbTestCode.v.add("");
			ResultSet rs=db.sta.executeQuery("select tbtestname.TestCode from tbtestname");
			while(rs.next()){
				cmbTestCode.v.add(rs.getString("TestCode"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	public String getFiscelYear(){
		String Year="";
		try {
			Year=new SimpleDateFormat("yyyy").format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return Year;
	}
	public String getCurrentMonth(){
		String Month="";

		try {
			ResultSet rs=db.sta.executeQuery("SELECT  DATENAME(M,'"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"') as MName");
			while(rs.next()){
				Month=rs.getString("MName");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return Month;
	}
	private void addCmp() {
		setOpaque(false);
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(1300, 670));
		mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(new Color(216,229,217));
		mainPanel.add(NorthPanel,BorderLayout.NORTH);
		//NorthPanel.setOpaque(false);
		NorthPanel_work();
		mainPanel.add(CenterPanel,BorderLayout.CENTER);
		//CenterPanel.setOpaque(false);
		CenterPanel_work();
		mainPanel.add(SouthPanel,BorderLayout.SOUTH);
		//SouthPanel.setOpaque(false);
		SouthPanel_work();
	}

	private void SouthPanel_work() {
		NorthPanel.setPreferredSize(new Dimension(1100, 220));
		TitledBorder title=BorderFactory.createTitledBorder("");
		title.setTitleJustification(title.CENTER);
		NorthPanel.setBackground(new Color(216,229,217));
		//NorthPanel.setBorder(title);
		NorthPanel.setLayout(new BorderLayout());
		NorthPanel.add(NorthWestPanel, BorderLayout.WEST);
		//NorthWestPanel.setOpaque(false);
		NorthPanel.add(NorthCenterPanel, BorderLayout.CENTER);
		//NorthCenterPanel.setOpaque(false);
		NorthPanel.add(NorthEastPanel, BorderLayout.EAST);
		//NorthEastPanel.setOpaque(false);
		NorthWestPanel_work();
		NorthCenterPanel_work();
		NorthEastPanel_work();

	}
	private void NorthCenterPanel_work() {
		NorthCenterPanel.setPreferredSize(new Dimension(200, 180));
		//NorthCenterPanel.setBorder(BorderFactory.createLineBorder(Color.magenta,1));
		FlowLayout flow=new FlowLayout();
		NorthCenterPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		NorthCenterPanel.setBackground(new Color(216,229,217));
		JLabel lblDate=new JLabel("Date");
		lblDate.setPreferredSize(new Dimension(40, 18));
		NorthCenterPanel.add(lblDate);

		NorthCenterPanel.add(txtDateTime);
		txtDateTime.setPreferredSize(new Dimension(120,30));
		txtDateTime.setDateFormatString("yyyy-MM-dd");
		txtDateTime.setDate(new Date());
		txtDateTime.setEnabled(false);
		NorthCenterPanel.add(lblBC);
		NorthCenterPanel.add(txtBC);


		NorthCenterPanel.add(btnCounter1);
		btnCounter1.setPreferredSize(new Dimension(44, 36));
		btnCounter1.setBackground(Color.GREEN);
		btnCounter1.setFont(new Font("arial", Font.BOLD, 13));

		NorthCenterPanel.add(btnCounter2);
		btnCounter2.setPreferredSize(new Dimension(44, 36));
		btnCounter2.setBackground(Color.GREEN);
		btnCounter2.setFont(new Font("arial", Font.BOLD, 13));

		NorthCenterPanel.add(btnCounter3);
		btnCounter3.setPreferredSize(new Dimension(44, 36));
		btnCounter3.setBackground(Color.GREEN);
		btnCounter3.setFont(new Font("arial", Font.BOLD, 13));

		NorthCenterPanel.add(btnCounter4);
		btnCounter4.setPreferredSize(new Dimension(44, 36));
		btnCounter4.setBackground(Color.GREEN);
		btnCounter4.setFont(new Font("arial", Font.BOLD, 13));

		NorthCenterPanel.add(btnCounter5);
		btnCounter5.setPreferredSize(new Dimension(44, 36));
		btnCounter5.setBackground(Color.GREEN);
		btnCounter5.setFont(new Font("arial", Font.BOLD, 13));

		NorthCenterPanel.add(lblBillFiscalYear);
		lblBillFiscalYear.setVisible(false);
		NorthCenterPanel.add(txtBillFiscalYear);
		txtBillFiscalYear.setVisible(false);
	}

	private void NorthWestPanel_work() {
		NorthWestPanel.setPreferredSize(new Dimension(520, 180));
		//NorthWestPanel.setBorder(BorderFactory.createLineBorder(Color.red,1));
		FlowLayout flow=new FlowLayout();
		NorthWestPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		NorthWestPanel.setBackground(new Color(216,229,217));
		NorthWestPanel.add(cmbPatientType);
		cmbPatientType.setPreferredSize(new Dimension(80,30));
		cmbPatientType.setBackground(Color.YELLOW);
		cmbPatientType.setFont(new Font("arial",Font.BOLD,12));

		//NorthWestPanel.add(lblMrNo);
		NorthWestPanel.add(txtMrNo);
		txtMrNo.setEditable(false);
		txtMrNo.setForeground(Color.white);
		txtMrNo.setBackground(Color.BLACK);
		txtMrNo.setFont(new Font("arial",Font.BOLD,14));

		NorthWestPanel.add(btnAdd);
		btnAdd.setPreferredSize(new Dimension(38, 36));


		NorthWestPanel.add(lblRegNo);
		NorthWestPanel.add(cmbRegNo.combo);
		cmbRegNo.combo.setPreferredSize(new Dimension(230, 28));
		cmbRegNo.combo.setEnabled(false);

		NorthWestPanel.add(lblPatientName);
		lblPatientName.setPreferredSize(new Dimension(80, 28));

		NorthWestPanel.add(txtPatientName);


		NorthWestPanel.add(lblPhone);
		NorthWestPanel.add(txtPhone);

		NorthWestPanel.add(lblAge);
		lblAge.setPreferredSize(new Dimension(80, 28));
		NorthWestPanel.add(txtAge);

		NorthWestPanel.add(lblMonth);
		NorthWestPanel.add(txtMonth);

		NorthWestPanel.add(lblDay);
		NorthWestPanel.add(txtDay);

		NorthWestPanel.add(lblSex);
		NorthWestPanel.add(cmbsex);
		cmbsex.setPreferredSize(new Dimension(70, 32));

		NorthWestPanel.add(lblAddress);
		NorthWestPanel.add(txtAddress);

		NorthWestPanel.add(lblRefferBy);
		lblRefferBy.setPreferredSize(new Dimension(80, 28));
		NorthWestPanel.add(cmbRefferBy.combo);
		cmbRefferBy.combo.setPreferredSize(new Dimension(230, 28));

		NorthWestPanel.add(txtDoctorDegree);

		NorthWestPanel.add(btnAddDoctor);
		btnAddDoctor.setPreferredSize(new Dimension(40, 35));
		btnAddDoctor.setFont(new Font("arial", Font.BOLD, 13));
		btnAddDoctor.setBackground(Color.white);

		NorthWestPanel.add(lblRFPersion);
		lblRFPersion.setPreferredSize(new Dimension(80, 28));
		NorthWestPanel.add(cmbRFPersion.combo);
		cmbRFPersion.combo.setPreferredSize(new Dimension(230, 28));

		NorthWestPanel.add(new JLabel("                                                       "));

		NorthWestPanel.add(lblTestName);
		lblTestName.setPreferredSize(new Dimension(80, 28));

		NorthWestPanel.add(cmbTestName.combo);
		cmbTestName.combo.setPreferredSize(new Dimension(290, 28));
		cmbTestName.combo.setFocusTraversalKeysEnabled(false);
		cmbTestName.txtMrNo.setFocusTraversalKeysEnabled(false);


		NorthWestPanel.add(btnTestSubmit);
		btnTestSubmit.setPreferredSize(new Dimension(60, 35));
		btnTestSubmit.setFont(new Font("arial", Font.BOLD, 13));
		btnTestSubmit.setBackground(Color.white);
	}

	private void NorthEastPanel_work() {
		NorthEastPanel.setPreferredSize(new Dimension(560, 180));
		//NorthEastPanel.setBorder(BorderFactory.createLineBorder(Color.green,1));
		FlowLayout flow=new FlowLayout();
		NorthEastPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		NorthEastPanel.setBackground(new Color(216,229,217));
		/*		JLabel lblS=new JLabel("Search");
		NorthEastPanel.add(lblS);
		//lblS.setPreferredSize(new Dimension(270, 20));
		lblS.setFont(new Font("arial", Font.BOLD, 14));
		lblS.setForeground(new Color(2, 191, 185));*/



		NorthEastPanel.add(lblFiscalYear);
		lblFiscalYear.setFont(new Font("arial", Font.BOLD, 13));
		NorthEastPanel.add(cmbFiscalYear);
		cmbFiscalYear.setFont(new Font("arial", Font.BOLD, 14));
		cmbFiscalYear.setPreferredSize(new Dimension(70, 34));
		cmbFiscalYear.setSelectedItem(getFiscelYear());
		cmbFiscalYear.setEditable(false);
		cmbFiscalYear.setForeground(Color.black);
		cmbFiscalYear.setBackground(Color.YELLOW);
		cmbFiscalYear.setFont(new Font("arial",Font.BOLD,17));


		NorthEastPanel.add(lblBillMonth);
		lblBillMonth.setFont(new Font("arial", Font.BOLD, 13));
		NorthEastPanel.add(cmbBillMonth);
		cmbBillMonth.setFont(new Font("arial", Font.BOLD, 14));
		cmbBillMonth.setPreferredSize(new Dimension(120, 34));
		cmbBillMonth.setSelectedItem(getFiscelYear());
		cmbBillMonth.setEditable(false);
		cmbBillMonth.setForeground(Color.black);
		cmbBillMonth.setBackground(Color.YELLOW);
		cmbBillMonth.setFont(new Font("arial",Font.BOLD,17));

		//NorthEastPanel.add(new  JLabel("        "));

		NorthEastPanel.add(cmbFindLabBill.combo);

		cmbFindLabBill.combo.setPreferredSize(new Dimension(95, 32));
		cmbFindLabBill.txtMrNo.setFont(new Font("arial", Font.BOLD, 15));

		NorthEastPanel.add(btnAdvancedSearch);
		btnAdvancedSearch.setFont(new Font("arial", Font.BOLD, 13));
		btnAdvancedSearch.setMnemonic(KeyEvent.VK_A);


		NorthEastPanel.add(ScrollR);

		ScrollR.setPreferredSize(new Dimension(540, 150));
		tableR.setRowHeight(tableR.getRowHeight()+9);
		tableR.getTableHeader().setReorderingAllowed(false);
		tableR.getColumnModel().getColumn(0).setPreferredWidth(45);
		tableR.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableR.getColumnModel().getColumn(2).setPreferredWidth(340);
		tableR.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableR.setFont(new Font("arial", Font.BOLD, 13));
		tableR.setShowGrid(true);

		Action Add = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{

				btnInEvent(tableR.getValueAt(tableR.getSelectedRow(), 2).toString(),2);
			}
		};
		ButtonColumn Additem = new ButtonColumn(tableR, Add, 0);
	}

	private void CenterPanel_work() {
		CenterPanel.setPreferredSize(new Dimension(1100, 220));
		/*		TitledBorder title=BorderFactory.createTitledBorder("Test Details");
		title.setTitleJustification(title.CENTER);
		CenterPanel.setBorder(title);*/
		CenterPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		CenterPanel.setLayout(new BorderLayout());
		CenterPanel.setBackground(new Color(216,229,217));
		CenterPanel.add(CenterNorthPanel, BorderLayout.NORTH);
		//CenterNorthPanel.setOpaque(false);
		CenterNorthPanel_worl();
		CenterPanel.add(CenterSouthPanel, BorderLayout.SOUTH);
		//CenterSouthPanel.setOpaque(false);
		CenterSouthPanel_work();

	}

	private void CenterNorthPanel_worl() {
		CenterNorthPanel.setPreferredSize(new Dimension(1000, 250));
		//CenterNorthPanel.setBorder(BorderFactory.createLineBorder(Color.red,1));
		FlowLayout flow=new FlowLayout();
		CenterNorthPanel.setLayout(flow);
		CenterNorthPanel.setBackground(new Color(216,229,217));
		flow.setAlignment(flow.LEFT);
		/*		CenterNorthPanel.add(lblTestCode);
		lblTestCode.setPreferredSize(new Dimension(78, 9));

		CenterNorthPanel.add(cmbTestCode.combo);
		cmbTestCode.combo.setPreferredSize(new Dimension(100, 32));
		cmbTestCode.txtMrNo.setFont(new Font("Arial", Font.BOLD, 16));*/

		/*		CenterNorthPanel.add(lblTestName);
		lblTestName.setPreferredSize(new Dimension(65, 9));

		CenterNorthPanel.add(cmbTestName.combo);
		cmbTestName.combo.setPreferredSize(new Dimension(480, 34));
		cmbTestName.txtMrNo.setFont(new Font("Arial", Font.BOLD, 16));*/

		/*		CenterNorthPanel.add(btnIn);
		btnIn.setMnemonic(KeyEvent.VK_I);
		btnIn.setPreferredSize(new Dimension(70, 35));

		CenterNorthPanel.add(lblRate);
		lblRate.setPreferredSize(new Dimension(60, 9));
		lblRate.setVisible(false);

		CenterNorthPanel.add(txtRate);
		txtRate.setEnabled(false);
		txtRate.setVisible(false);



		CenterNorthPanel.add(lblDiscount);
		lblDiscount.setVisible(false);
		lblDiscount.setPreferredSize(new Dimension(140, 9));


		CenterNorthPanel.add(cmbDiscount.combo);
		cmbDiscount.combo.setPreferredSize(new Dimension(110, 27));
		cmbDiscount.combo.setEnabled(false);
		cmbDiscount.v.add("0");
		cmbDiscount.v.add("25");
		cmbDiscount.v.add("20");
		cmbDiscount.v.add("15");
		cmbDiscount.v.add("10");
		cmbDiscount.v.add("5");
		cmbDiscount.txtMrNo.setText("0");

		cmbDiscount.combo.setVisible(false);*/


		CenterNorthPanel.add(Scroll);
		Scroll.setPreferredSize(new Dimension(1280, 220));
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(420);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		table.getColumnModel().getColumn(5).setPreferredWidth(140);
		table.getColumnModel().getColumn(6).setPreferredWidth(140);
		table.getColumnModel().getColumn(7).setPreferredWidth(140);
		table.getColumnModel().getColumn(8).setPreferredWidth(34);
		table.getColumnModel().getColumn(9).setPreferredWidth(180);
		table.setRowHeight(table.getRowHeight() + 10);
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 16));
		table.setShowGrid(true);
		/*		table.setOpaque(false);
		Scroll.setOpaque(false);
		Scroll.getViewport().setOpaque(false);*/
		Action delete = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {

					String RegNo="",PatientFiscalYear="",RegYear="";
					StringTokenizer token=new StringTokenizer(cmbRegNo.txtMrNo.getText().trim().toString()," ");
					while(token.hasMoreTokens()){
						RegYear=token.nextToken();
						break;
					}

					int i=0;
					StringTokenizer Toekn=new StringTokenizer(RegYear,"/");
					while(Toekn.hasMoreTokens()){
						if(i==0){
							RegNo=Toekn.nextToken();
						}
						if(i==1){
							PatientFiscalYear=Toekn.nextToken();
						}
						i++;
					}

					int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Delete Item","Confrim-----",JOptionPane.YES_NO_OPTION);
					if(confrim==JOptionPane.YES_OPTION){
						String TestName=table.getValueAt(table.getSelectedRow(), 3).toString();
						if(checkIsTestName(TestName)){

							if(find==0){
								ArrayList DSN=new ArrayList();
								ArrayList DTId=new ArrayList();
								ArrayList DNameOfPericular=new ArrayList();
								ArrayList DNameOfRate=new ArrayList();
								int Dcount=0;
								ResultSet rs1=db.sta.executeQuery("select *from tbtestperticularname where TestHeadId=(select SN from tbtestname where TestCode='"+table.getValueAt(table.getSelectedRow(), 2).toString()+"')");
								while(rs1.next()){
									DSN.add(rs1.getString("SN"));
									DTId.add(rs1.getString("TestPerCode"));
									DNameOfPericular.add(rs1.getString("TestPerName"));
									DNameOfRate.add(rs1.getString("rate"));
									Dcount++;
									//model.addRow(new Object[]{rs1.getString("SN"),rs1.getString("TestPerCode"),rs1.getString("TestPerName"),df.format(Double.parseDouble(rs1.getString("rate"))),0,df.format(Double.parseDouble(rs1.getString("rate"))),new ImageIcon("icon/delete.png")});
								}
								for(int a=0;a<Dcount;a++){

									if(checkInvoiceDoublePerticular("select tblabtesthistory.TestName from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and tblabtesthistory.TestName='"+DNameOfPericular.get(a).toString()+"' and  tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"' and tblabtesthistory.labId IS NULL")){

										String deleteQ="delete from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and  tblabtesthistory.TestName='"+DNameOfPericular.get(a).toString()+"' and tblabtesthistory.type=2 and  tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"' and tblabtesthistory.labId IS NULL";
										System.out.println(deleteQ);
										//String query ="insert into tblabtesthistory (regNo,SN,testCode,testName,rate,discount,ResultStatus,type,date,entryTime,createBy) values ('"+Reg+"','"+SN.get(a).toString()+"','"+TId.get(a).toString()+"','"+NameOfPericular.get(a).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(a).toString()))+"','0','NOT DONE','2','"+new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getDate())+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
										//System.out.println(query);
										db.sta.executeUpdate(deleteQ);
									}
								}
								String deleteQ="delete from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and tblabtesthistory.TestName='"+TestName+"' and tblabtesthistory.type=1 and  tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"' and tblabtesthistory.labId IS NULL";
								System.out.println(deleteQ);
								db.sta.executeUpdate(deleteQ);
								String deleteQT="delete from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and  tblabtesthistory.type=2 and  tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"' and tblabtesthistory.labId IS NULL";
								System.out.println(deleteQT);
								db.sta.executeUpdate(deleteQT);

								DSN.clear();
								DTId.clear();
								DNameOfPericular.clear();
								DNameOfRate.clear();

								showData("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and  tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"' and tblabtesthistory.labId IS NULL order by type asc");
								for(int a=0;a<table.getRowCount();a++){
									if(table.getValueAt(a, 2).toString()!=""){
										ArrayList SN=new ArrayList();
										ArrayList TId=new ArrayList();
										ArrayList NameOfPericular=new ArrayList();
										ArrayList NameOfRate=new ArrayList();
										ArrayList NameOfQty=new ArrayList();
										int count=0;
										ResultSet rs2=db.sta.executeQuery("select *from tbtestperticularname where TestHeadId=(select SN from tbtestname where TestCode='"+table.getValueAt(a, 2).toString()+"')");
										while(rs2.next()){
											SN.add(rs2.getString("SN"));
											TId.add(rs2.getString("TestPerCode"));
											NameOfQty.add(Integer.parseInt(rs2.getString("qty")));
											NameOfPericular.add(rs2.getString("TestPerName"));
											NameOfRate.add(rs2.getString("rate"));
											count++;
											//model.addRow(new Object[]{rs1.getString("SN"),rs1.getString("TestPerCode"),rs1.getString("TestPerName"),df.format(Double.parseDouble(rs1.getString("rate"))),0,df.format(Double.parseDouble(rs1.getString("rate"))),new ImageIcon("icon/delete.png")});
										}


										if(checkRegisrationPatient(RegNo)){
											RegNo=RegNo;
										}
										else{
											RegNo=txtMrNo.getText().trim().toString();
										}
										for(int b=0;b<count;b++){
											if(!checkInvoiceDoublePerticular("select tblabtesthistory.TestName from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and tblabtesthistory.TestName='"+NameOfPericular.get(b).toString()+"' and  tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"' and tblabtesthistory.labId IS NULL")){
												autoId();
												String query ="insert into tblabtesthistory (regNo,SN,testCode,testName,qty,rate,discount,ResultStatus,RefundStatus,type,counter,date,entryTime,createBy,FiscalYear,CMonth) values ('"+RegNo+"','"+SN.get(b).toString()+"','"+TId.get(b).toString()+"','"+NameOfPericular.get(b).toString()+"','"+NameOfQty.get(b).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(b).toString()))+"','0','NOT DONE','0','2','"+counter+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','"+getFiscelYear()+"','"+getCurrentMonth()+"')";
												System.out.println(query);
												db.sta.executeUpdate(query);

												String Udquery ="insert into tbUdlabtesthistory (regNo,SN,testCode,testName,qty,rate,discount,ResultStatus,RefundStatus,type,counter,date,entryTime,createBy,FiscalYear,CMonth) values ('"+RegNo+"','"+SN.get(b).toString()+"','"+TId.get(b).toString()+"','"+NameOfPericular.get(b).toString()+"','"+NameOfQty.get(b).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(b).toString()))+"','0','NOT DONE','0','2','"+counter+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','"+getFiscelYear()+"','"+getCurrentMonth()+"')";
												System.out.println(Udquery);
												db.sta.executeUpdate(Udquery);
											}
										}									
										SN.clear();
										TId.clear();
										NameOfPericular.clear();
										NameOfRate.clear();
									}
								}
								showData("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CMonth='"+getCurrentMonth()+"'  and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"'  and tblabtesthistory.labId IS NULL order by type asc");
							}
							/*							else if(find==1){

								String Bill="",FiscaleYear="",BillYear="";
								StringTokenizer token1=new StringTokenizer(cmbFindLabBill.txtMrNo.getText().trim().toString()," ");
								while(token1.hasMoreTokens()){
									BillYear=token1.nextToken();
									break;
								}

								int m=0;
								StringTokenizer Toekn1=new StringTokenizer(BillYear,"/");
								while(Toekn1.hasMoreTokens()){
									if(m==0){
										Bill=Toekn1.nextToken();
									}
									if(m==1){
										FiscaleYear=Toekn1.nextToken();
									}
									m++;
								}

								ArrayList DSN=new ArrayList();
								ArrayList DTId=new ArrayList();
								ArrayList DNameOfPericular=new ArrayList();
								ArrayList DNameOfRate=new ArrayList();
								int Dcount=0;
								ResultSet rs1=db.sta.executeQuery("select *from tbtestperticularname where TestHeadId=(select SN from tbtestname where TestCode='"+table.getValueAt(table.getSelectedRow(), 2).toString()+"')");
								while(rs1.next()){
									DSN.add(rs1.getString("SN"));
									DTId.add(rs1.getString("TestPerCode"));
									DNameOfPericular.add(rs1.getString("TestPerName"));
									DNameOfRate.add(rs1.getString("rate"));
									Dcount++;
									//model.addRow(new Object[]{rs1.getString("SN"),rs1.getString("TestPerCode"),rs1.getString("TestPerName"),df.format(Double.parseDouble(rs1.getString("rate"))),0,df.format(Double.parseDouble(rs1.getString("rate"))),new ImageIcon("icon/delete.png")});
								}
								for(int a=0;a<Dcount;a++){
									if(checkInvoiceDoublePerticular("select tblabtesthistory.TestName from tblabtesthistory where tblabtesthistory.FiscalYear='"+FiscaleYear+"' and tblabtesthistory.TestName='"+DNameOfPericular.get(a).toString()+"' and  tblabtesthistory.labId='"+Bill+"'")){
										String deleteQ="delete from tblabtesthistory where tblabtesthistory.FiscalYear='"+FiscaleYear+"' and tblabtesthistory.TestName='"+DNameOfPericular.get(a).toString()+"' and tblabtesthistory.type=2 and tblabtesthistory.labId='"+Bill+"'";
										System.out.println(deleteQ);
										db.sta.executeUpdate(deleteQ);
									}
								}
								String deleteQ="delete from tblabtesthistory where tblabtesthistory.FiscalYear='"+FiscaleYear+"' and tblabtesthistory.TestName='"+TestName+"' and tblabtesthistory.type=1 and tblabtesthistory.labId ='"+Bill+"'";
								System.out.println(deleteQ);
								db.sta.executeUpdate(deleteQ);
								String deleteQT="delete from tblabtesthistory where tblabtesthistory.FiscalYear='"+FiscaleYear+"' and tblabtesthistory.type=2 and  tblabtesthistory.labId='"+Bill+"'";
								System.out.println(deleteQT);
								db.sta.executeUpdate(deleteQT);

								String DPaymentQuery="delete from TbLabPaymentHistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and BillNo='"+cmbFindLabBill.getValue().toString()+"' and PaymentStatus='Paid'";
								db.sta.executeUpdate(DPaymentQuery);


								DSN.clear();
								DTId.clear();
								DNameOfPericular.clear();
								DNameOfRate.clear();

								showData("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+FiscaleYear+"' and tblabtesthistory.labId='"+Bill+"' order by type asc");
								for(int a=0;a<table.getRowCount();a++){
									if(table.getValueAt(a, 2).toString()!=""){
										ArrayList SN=new ArrayList();
										ArrayList TId=new ArrayList();
										ArrayList NameOfPericular=new ArrayList();
										ArrayList NameOfRate=new ArrayList();
										ArrayList NameOfQty=new ArrayList();
										int count=0;
										ResultSet rs2=db.sta.executeQuery("select *from tbtestperticularname where TestHeadId=(select SN from tbtestname where TestCode='"+table.getValueAt(a, 2).toString()+"')");
										while(rs2.next()){
											SN.add(rs2.getString("SN"));
											TId.add(rs2.getString("TestPerCode"));
											NameOfQty.add(Integer.parseInt(rs2.getString("qty")));
											NameOfPericular.add(rs2.getString("TestPerName"));
											NameOfRate.add(rs2.getString("rate"));
											count++;
											//model.addRow(new Object[]{rs1.getString("SN"),rs1.getString("TestPerCode"),rs1.getString("TestPerName"),df.format(Double.parseDouble(rs1.getString("rate"))),0,df.format(Double.parseDouble(rs1.getString("rate"))),new ImageIcon("icon/delete.png")});
										}

										if(checkRegisrationPatient(RegNo)){
											RegNo=RegNo;
										}
										else{
											RegNo=txtMrNo.getText().trim().toString();
										}
										for(int b=0;b<count;b++){
											if(!checkInvoiceDoublePerticular("select tblabtesthistory.TestName from tblabtesthistory where tblabtesthistory.TestName='"+NameOfPericular.get(b).toString()+"' and tblabtesthistory.FiscalYear='"+FiscaleYear+"' and tblabtesthistory.labId='"+Bill+"'")){
												autoId();
												String query ="insert into tblabtesthistory (labId,regNo,SN,testCode,testName,qty,rate,discount,ResultStatus,RefundStatus,type,counter,date,entryTime,createBy,FiscalYear) values ('"+Bill+"','"+RegNo+"','"+SN.get(b).toString()+"','"+TId.get(b).toString()+"','"+NameOfPericular.get(b).toString()+"','"+NameOfQty.get(b).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(b).toString()))+"','0','NOT DONE','0','2','"+counter+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','"+FiscaleYear+"')";
												System.out.println(query);
												db.sta.executeUpdate(query);

												String Udquery ="insert into tbUdlabtesthistory (labId,regNo,SN,testCode,testName,qty,rate,discount,ResultStatus,RefundStatus,type,counter,date,entryTime,createBy,FiscalYear) values ('"+Bill+"','"+RegNo+"','"+SN.get(b).toString()+"','"+TId.get(b).toString()+"','"+NameOfPericular.get(b).toString()+"','"+NameOfQty.get(b).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(b).toString()))+"','0','NOT DONE','0','2','"+counter+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','"+FiscaleYear+"')";
												System.out.println(Udquery);
												db.sta.executeUpdate(Udquery);
											}
										}									
										SN.clear();
										TId.clear();
										NameOfPericular.clear();
										NameOfRate.clear();
									}
								}
								showData("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+FiscaleYear+"' and  tblabtesthistory.labId='"+Bill+"' order by type asc");

								double Totaldiscount=Double.parseDouble(txtGeneralDiscount.getText().trim().toString())+Double.parseDouble(txtSpDiscount.getText().trim().toString());

								String sql="update tblabpatient set PatientName='"+txtPatientName.getText().trim().toString()+"',Mobile='"+txtPhone.getText().trim().toString()+"',Age='"+txtAge.getText().trim().toString()+"',Month='"+txtMonth.getText().trim().toString()+"',day='"+txtDay.getText().trim().toString()+"',Sex='"+cmbsex.getSelectedItem().toString()+"',address='"+txtAddress.getText().trim().toString()+"',PercentDiscount='"+txtPercentDiscount.getText().trim().toString()+"',Discount='"+txtGeneralDiscount.getText().trim().toString()+"',ManualDiscount='"+txtSpDiscount.getText().trim().toString()+"',totalDiscount='"+Totaldiscount+"',TotalCharge='"+txtTotalCharge.getText().trim().toString()+"',TotalPayable='"+txtTotalPayable.getText().trim().toString()+"',Paid='"+txtPaidInCash.getText().trim().toString()+"',lasPaid='"+txtPaidInCash.getText().trim().toString()+"',entryTime=CURRENT_TIMESTAMP,CreateBy='"+sessionBeam.getUserId()+"' where FiscalYear='"+FiscaleYear+"' and LabId='"+Bill+"'";
								System.out.println(sql);
								db.sta.executeUpdate(sql);

								PaymentHistoryTransection(txtMrNo.getText().trim().toString(),Double.parseDouble(txtPaidInCash.getText().trim().toString()),FiscaleYear);
								String patienttype=cmbPatientType.getSelectedIndex()==0?"Outdoor":"Indoor";
								int CuPeriod=getCurrentPeriod(cmbRegNo.txtMrNo.getText().trim().toString(),PatientFiscalYear);

								String Udsql="insert into tbUdlabpatient values('"+txtMrNo.getText().trim().toString()+"',"
										+ "'"+patienttype+"',"
										+ "'"+txtMrNo.getText().trim().toString()+"',"
										+ "'"+cmbRegNo.txtMrNo.getText().trim().toString()+"',"
										+ "'"+txtPatientName.getText().trim().toString()+"',"
										+ "'"+txtPhone.getText().trim().toString()+"',"
										+ "'"+txtAge.getText().trim().toString()+"',"
										+ "'"+txtMonth.getText().trim().toString()+"',"
										+ "'"+txtDay.getText().trim().toString()+"',"
										+ "'"+cmbsex.getSelectedItem().toString()+"',"
										+ "'"+txtBC.getText().trim().toString()+"',"
										+ "'"+txtAddress.getText().trim().toString()+"',"
										+ "'',"
										+ "'',"
										+ "'',"
										+ "'',"
										+ "'',"
										+ "CURRENT_TIMESTAMP,"
										+ "'"+txtPercentDiscount.getText().trim().toString()+"',"
										+ "'"+txtGeneralDiscount.getText().trim().toString()+"',"
										+ "'"+txtSpDiscount.getText().trim().toString()+"',"
										+ "'"+Totaldiscount+"',"
										+ "'"+txtTotalCharge.getText().trim().toString()+"',"
										+ "'"+txtTotalPayable.getText().trim().toString()+"',"
										+ "'"+txtPaidInCash.getText().trim().toString()+"',"
										+ "'"+txtVat.getText().trim().toString()+"',"
										+ "'"+txtCO.getText().trim().toString()+"',"
										+ "'"+new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getValue())+" Time "+txtTime.getText().toString()+"',"
										+ "'',"
										+ "'"+txtCO.getText().trim().toString()+"',"
										+ "'"+txtPaidInCash.getText().trim().toString()+"',"
										+ "CURRENT_TIMESTAMP,"
										+ "'"+sessionBeam.getUserId()+"','UPDATE','"+CuPeriod+"','"+PatientFiscalYear+"','"+getFiscelYear()+"')";
								System.out.println(Udsql);
								db.sta.executeUpdate(Udsql);
							}*/

						}
						else{
							JOptionPane.showMessageDialog(null, "Sorry!!,You Can Delete Only Test Name!!");
						}
					}
				} catch (Exception e2) {

					e2.printStackTrace();
				}
			}
		};
		ButtonColumn btnDelete = new ButtonColumn(table, delete, 8);
	}
	private boolean checkIsTestName(String TestName){
		try {
			ResultSet rs=db.sta.executeQuery("select TestName  from tbtestname where TestName='"+TestName+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return false;
	}
	private void CenterSouthPanel_work() {
		CenterSouthPanel.setPreferredSize(new Dimension(1100, 40));
		//CenterSouthPanel.setBorder(BorderFactory.createLineBorder(Color.red,1));
		FlowLayout flow=new FlowLayout();
		CenterSouthPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		CenterSouthPanel.setBackground(new Color(216,229,217));

		CenterSouthPanel.add(lblGrandTotal);
		lblGrandTotal.setFont(new Font("arial", Font.BOLD, 14));
		CenterSouthPanel.add(txtGrandTotal);
		txtGrandTotal.setEditable(false);
		txtGrandTotal.setPreferredSize(new Dimension(100, 38));
		txtGrandTotal.setText("0.0");
		txtGrandTotal.setFont(new Font("arial",Font.BOLD,21));
		txtGrandTotal.setForeground(Color.YELLOW);
		txtGrandTotal.setBackground(Color.BLACK);

		/*		CenterSouthPanel.add(lblVat);
		//lblVat.setPreferredSize(new Dimension(40, 10));
		lblVat.setFont(new Font("arial", Font.BOLD, 14));
		CenterSouthPanel.add(txtVat);
		txtVat.setEditable(false);
		txtVat.setText("0.0");
		txtVat.setPreferredSize(new Dimension(100, 40));
		txtVat.setFont(new Font("arial",Font.BOLD,21));
		txtVat.setForeground(Color.white);
		//txtVat.setForeground(Color.white);
		txtVat.setBackground(Color.BLACK);*/

		CenterSouthPanel.add(lblPercentDiscount);
		lblPercentDiscount.setFont(new Font("arial", Font.BOLD, 14));
		CenterSouthPanel.add(txtPercentDiscount);
		txtPercentDiscount.setForeground(Color.white);
		//txtVat.setForeground(Color.white);
		txtPercentDiscount.setBackground(Color.BLACK);
		txtPercentDiscount.setText("0");
		txtPercentDiscount.setPreferredSize(new Dimension(100, 38));
		txtPercentDiscount.setFont(new Font("arial",Font.BOLD,21));

		CenterSouthPanel.add(lblManualPercentice);
		lblManualPercentice.setFont(new Font("arial", Font.BOLD, 14));
		CenterSouthPanel.add(txtManualDiscount);
		txtManualDiscount.setForeground(Color.white);
		//txtVat.setForeground(Color.white);
		txtManualDiscount.setBackground(Color.BLACK);
		txtManualDiscount.setText("0");
		txtManualDiscount.setPreferredSize(new Dimension(100, 38));
		txtManualDiscount.setFont(new Font("arial",Font.BOLD,21));



		CenterSouthPanel.add(lblPaidInCash);
		lblPaidInCash.setFont(new Font("arial", Font.BOLD, 14));
		CenterSouthPanel.add(txtPaidInCash);
		txtPaidInCash.setText("0");
		txtPaidInCash.setForeground(Color.GREEN);
		txtPaidInCash.setBackground(Color.BLACK);
		txtPaidInCash.setFont(new Font("arial",Font.BOLD,14));
		txtPaidInCash.setPreferredSize(new Dimension(100, 38));
		txtPaidInCash.setFont(new Font("arial",Font.BOLD,21));


		CenterSouthPanel.add(lblAdvancePaid);
		lblAdvancePaid.setPreferredSize(new Dimension(50, 30));
		lblAdvancePaid.setFont(new Font("arial", Font.BOLD, 14));
		CenterSouthPanel.add(txtAdvancepaid);
		txtAdvancepaid.setText("0");
		txtAdvancepaid.setForeground(Color.GREEN);
		txtAdvancepaid.setBackground(Color.BLACK);
		txtAdvancepaid.setPreferredSize(new Dimension(100, 38));
		txtAdvancepaid.setFont(new Font("arial",Font.BOLD,21));
		txtAdvancepaid.setEditable(false);


		CenterSouthPanel.add(lblDues);
		lblDues.setFont(new Font("arial", Font.BOLD, 14));
		CenterSouthPanel.add(txtDues);
		txtDues.setForeground(Color.red);
		//txtDues.setBackground(Color.BLACK);
		txtDues.setFont(new Font("arial",Font.BOLD,13));
		txtDues.setPreferredSize(new Dimension(100, 38));
		txtDues.setFont(new Font("arial",Font.BOLD,21));


		CenterSouthPanel.add(lblReportDelivery);
		lblReportDelivery.setPreferredSize(new Dimension(60, 24));
		lblReportDelivery.setFont(new Font("arial", Font.BOLD, 14));
		lblReportDelivery.setForeground(Color.black);

		//CenterSouthPanel.add(lblDate);
		CenterSouthPanel.add(txtDate);
		txtDate.setEditor(Editor);
		txtDate.setPreferredSize(new Dimension(100, 34));
		txtDate.setFont(new Font("arial", Font.BOLD, 13));

		//CenterSouthPanel.add(lblTime);
		CenterSouthPanel.add(txtTime);
		txtTime.setText("7 PM");
		txtTime.setFont(new Font("arial", Font.BOLD, 14));
		txtTime.setForeground(new Color(2, 191, 185));


	}
	private void NorthPanel_work() {
		SouthPanel.setPreferredSize(new Dimension(1100, 160));
		SouthPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		SouthPanel.setLayout(new BorderLayout());
		SouthPanel.setBackground(new Color(216,229,217));
		SouthPanel.add(SouthNorthPanel, BorderLayout.NORTH);
		//SouthNorthPanel.setOpaque(false);
		SouthNorthPanel_work();
		SouthPanel.add(SouthSouthPanel, BorderLayout.SOUTH);
		//SouthSouthPanel.setOpaque(false);
		SouthSouthPanel_work();




	}
	private void SouthNorthPanel_work() {
		SouthNorthPanel.setPreferredSize(new Dimension(1100, 80));
		//SouthNorthPanel.setBorder(BorderFactory.createLineBorder(Color.red,1));
		FlowLayout flow=new FlowLayout();
		SouthNorthPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);

		SouthNorthPanel.setBackground(new Color(216,229,217));

		//JLabel lbl=new JLabel("");
		//SouthNorthPanel.add(lbl);
		//lbl.setPreferredSize(new Dimension(250, 20));

		SouthNorthPanel.add(lblTotalCharge);
		lblTotalCharge.setFont(new Font("arial", Font.BOLD, 14));

		SouthNorthPanel.add(txtTotalCharge);
		txtTotalCharge.setEditable(false);
		txtTotalCharge.setText("0.0");
		txtTotalCharge.setForeground(Color.YELLOW);
		txtTotalCharge.setBackground(Color.BLACK);
		txtTotalCharge.setFont(new Font("arial",Font.BOLD,21));
		txtTotalCharge.setPreferredSize(new Dimension(60, 38));

		SouthNorthPanel.add(lblGeneralDiscount);
		lblGeneralDiscount.setFont(new Font("arial", Font.BOLD, 14));

		SouthNorthPanel.add(txtGeneralDiscount);
		txtGeneralDiscount.setEditable(false);
		txtGeneralDiscount.setText("0");
		txtGeneralDiscount.setForeground(Color.GREEN);
		txtGeneralDiscount.setBackground(Color.BLACK);
		txtGeneralDiscount.setFont(new Font("arial",Font.BOLD,21));
		txtGeneralDiscount.setPreferredSize(new Dimension(60, 38));


		SouthNorthPanel.add(lblSpDiscount);
		lblSpDiscount.setFont(new Font("arial", Font.BOLD, 14));

		SouthNorthPanel.add(txtSpDiscount);
		txtSpDiscount.setEditable(false);
		txtSpDiscount.setText("0");
		txtSpDiscount.setForeground(Color.GREEN);
		txtSpDiscount.setBackground(Color.BLACK);
		txtSpDiscount.setFont(new Font("arial",Font.BOLD,21));
		txtSpDiscount.setPreferredSize(new Dimension(60, 38));

		SouthNorthPanel.add(lblTotalPayable);
		lblTotalPayable.setFont(new Font("arial", Font.BOLD, 14));

		SouthNorthPanel.add(txtTotalPayable);

		txtTotalPayable.setForeground(Color.YELLOW);
		txtTotalPayable.setBackground(Color.BLACK);
		txtTotalPayable.setFont(new Font("arial",Font.BOLD,21));
		txtTotalPayable.setPreferredSize(new Dimension(60, 38));
		txtTotalPayable.setEditable(false);
		txtTotalPayable.setText("0.0");

		SouthNorthPanel.add(lblRefund);
		lblRefund.setFont(new Font("arial", Font.BOLD, 14));

		SouthNorthPanel.add(txtRefund);
		txtRefund.setForeground(Color.GREEN);
		txtRefund.setBackground(Color.BLACK);
		txtRefund.setFont(new Font("arial",Font.BOLD,21));
		txtRefund.setPreferredSize(new Dimension(60, 38));
		txtRefund.setEditable(false);
		txtRefund.setText("0");

		SouthNorthPanel.add(lblCO);
		lblCO.setFont(new Font("arial", Font.BOLD, 14));

		SouthNorthPanel.add(txtCO);
		txtCO.setFont(new Font("arial",Font.BOLD,21));
		txtCO.setPreferredSize(new Dimension(60, 30));


		JLabel lbl=new JLabel("");
		SouthNorthPanel.add(lbl);
		lbl.setPreferredSize(new Dimension(885, 20));

		SouthNorthPanel.add(lblSampleCollection);
		lblSampleCollection.setFont(new Font("arial", Font.BOLD, 14));

		SouthNorthPanel.add(txtSampleCollection);
		txtSampleCollection.setFont(new Font("arial",Font.BOLD,21));
		txtSampleCollection.setPreferredSize(new Dimension(60, 30));
		/*				grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(0, 0, 0,0);
		SouthNorthPanel.add(lblPercentDiscount, grid);
		grid.gridx=1;
		grid.gridy=0;
		SouthNorthPanel.add(txtPercentDiscount, grid);
		txtPercentDiscount.setText("0");
		grid.gridx=2;
		grid.gridy=0;
		SouthNorthPanel.add(lblGeneralDiscount, grid);
		grid.gridx=3;
		grid.gridy=0;
		SouthNorthPanel.add(txtGeneralDiscount, grid);

		grid.gridx=4;
		grid.gridy=0;
		SouthNorthPanel.add(lblSpDiscount, grid);
		grid.gridx=5;
		grid.gridy=0;
		SouthNorthPanel.add(txtSpDiscount, grid);
		txtSpDiscount.setEditable(false);
		txtSpDiscount.setText("0.0");
		grid.gridx=6;
		grid.gridy=0;
		SouthNorthPanel.add(lblRefund, grid);
		grid.gridx=7;
		grid.gridy=0;
		SouthNorthPanel.add(txtRefund, grid);
		txtRefund.setForeground(Color.GREEN);
		txtRefund.setBackground(Color.BLACK);
		txtRefund.setFont(new Font("arial",Font.BOLD,20));
		txtRefund.setPreferredSize(new Dimension(60, 38));
		txtRefund.setEditable(false);
		txtRefund.setText("0.0");
		grid.gridx=0;
		grid.gridy=1;
		SouthNorthPanel.add(lblManualPercentice, grid);
		grid.gridx=1;
		grid.gridy=1;
		SouthNorthPanel.add(txtManualDiscount, grid);
		txtManualDiscount.setText("0");
		grid.gridx=2;
		grid.gridy=1;
		SouthNorthPanel.add(lblTotalCharge, grid);
		grid.gridx=3;
		grid.gridy=1;
		SouthNorthPanel.add(txtTotalCharge, grid);
		txtTotalCharge.setEditable(false);
		txtTotalCharge.setText("0.0");
		grid.gridx=4;
		grid.gridy=1;
		SouthNorthPanel.add(lblTotalPayable, grid);
		grid.gridx=5;
		grid.gridy=1;
		SouthNorthPanel.add(txtTotalPayable, grid);

		txtTotalPayable.setForeground(Color.GREEN);
		txtTotalPayable.setBackground(Color.BLACK);
		txtTotalPayable.setFont(new Font("arial",Font.BOLD,20));
		txtTotalPayable.setPreferredSize(new Dimension(60, 38));
		txtTotalPayable.setEditable(false);
		txtTotalPayable.setText("0.0");
		grid.gridx=8;
		grid.gridy=0;
		SouthNorthPanel.add(lblCO, grid);
		grid.gridx=9;
		grid.gridy=0;
		SouthNorthPanel.add(txtCO, grid);
		grid.gridx=8;
		grid.gridy=1;
		SouthNorthPanel.add(lblSampleCollection, grid);
		grid.gridx=9;
		grid.gridy=1;
		SouthNorthPanel.add(txtSampleCollection, grid);*/
		/*		grid.gridx=0;
		grid.gridy=2;
		SouthNorthPanel.add(lblPaidInCash, grid);
		grid.gridx=1;
		grid.gridy=2;
		SouthNorthPanel.add(txtPaidInCash, grid);
		txtPaidInCash.setText("0");
		txtPaidInCash.setForeground(Color.white);
		txtPaidInCash.setBackground(Color.BLACK);
		txtPaidInCash.setFont(new Font("arial",Font.BOLD,14));*/

		/*		grid.gridx=2;
		grid.gridy=2;
		SouthNorthPanel.add(lblDues, grid);
		grid.gridx=3;
		grid.gridy=2;
		SouthNorthPanel.add(txtDues, grid);*/
		txtDues.setEditable(false);
		txtDues.setText("0.0");
		/*		grid.gridx=8;
		grid.gridy=0;
		SouthNorthPanel.add(lblRefund, grid);
		grid.gridx=9;
		grid.gridy=0;
		SouthNorthPanel.add(txtRefund, grid);
		txtRefund.setEditable(false);
		txtRefund.setText("0.0");*/
	}
	private void SouthSouthPanel_work() {
		SouthSouthPanel.setPreferredSize(new Dimension(1100, 80));
		//SouthSouthPanel.setBorder(BorderFactory.createLineBorder(Color.red,1));
		SouthSouthPanel.setLayout(new FlowLayout());


		ButtonGroup bg=new ButtonGroup();
		SouthSouthPanel.add(btnMoneyReceipt);
		SouthSouthPanel.add(btnLabSlip);
		btnMoneyReceipt.setSelected(true);
		btnLabSlip.setSelected(true);
		//bg.add(btnMoneyReceipt);
		//bg.add(btnLabSlip);

		SouthSouthPanel.add(btnPost);
		btnPost.setBackground(Color.YELLOW);
		btnPost.setFont(new Font("arial", Font.BOLD, 13));

		SouthSouthPanel.add(btnEdit);
		btnEdit.setBackground(Color.GREEN);
		btnEdit.setFont(new Font("arial", Font.BOLD, 13));
		btnEdit.setMnemonic(KeyEvent.VK_E);

		SouthSouthPanel.add(btnPrint);

		SouthSouthPanel.add(btnRefund);
		btnRefund.setPreferredSize(new Dimension(110, 36));
		btnRefund.setBackground(Color.YELLOW);
		btnRefund.setFont(new Font("arial", Font.BOLD, 13));
		btnRefund.setMnemonic(KeyEvent.VK_R);

		SouthSouthPanel.add(btnRefundSlip);
		btnRefundSlip.setPreferredSize(new Dimension(155, 36));

		//SouthSouthPanel.add(btnMoneyReceipte);
		SouthSouthPanel.add(btnClear);
		SouthSouthPanel.setBackground(new Color(216,229,217));

		SouthSouthPanel.add(btnDeletePendingInvoice);
		btnDeletePendingInvoice.setBackground(Color.YELLOW);
		btnDeletePendingInvoice.setFont(new Font("arial", Font.BOLD, 13));

		btnPrint.setPreferredSize(new Dimension(150, 36));
		btnDeletePendingInvoice.setPreferredSize(new Dimension(240, 36));
		btnClear.setPreferredSize(new Dimension(100, 36));
		btnMoneyReceipte.setPreferredSize(new Dimension(175, 36));
		btnClear.setMnemonic(KeyEvent.VK_C);
		btnPrint.setMnemonic(KeyEvent.VK_P);
		btnPost.setMnemonic(KeyEvent.VK_S);
		btnMoneyReceipte.setMnemonic(KeyEvent.VK_M);
		btnDeletePendingInvoice.setMnemonic(KeyEvent.VK_D);
	}
}
