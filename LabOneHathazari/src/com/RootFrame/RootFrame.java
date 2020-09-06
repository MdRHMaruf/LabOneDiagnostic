package com.RootFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.SimpleBeanInfo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.zip.CheckedOutputStream;

import javax.imageio.ImageIO;
import javax.security.auth.callback.ChoiceCallback;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.Timer;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.joda.time.LocalDate;
import org.joda.time.YearMonth;
import org.joda.time.format.DateTimeFormatter;

import com.ShareClass.ManagementInformation;
import com.ShareClass.OrganizationInfo;
import com.ShareClass.SeatCreate;
import com.AccountsModule.AccGroupAndLedgerCreate;
import com.AccountsModule.CashTransectionReportIncomeExpense;
import com.AccountsModule.FBalanceSheet;
import com.AccountsModule.FCashPaymentVoucher;
import com.AccountsModule.FCashReceivedVoucher;
import com.AccountsModule.FIncomeStatement;
import com.AccountsModule.FProfileAndLoseAccount;
import com.AccountsModule.FTraialBalance;
import com.Lab.DoctorComissionSet;
import com.Lab.LabBilling;
import com.Lab.LabInchargeCosultentSet;
import com.Lab.LabRptHome;
import com.Lab.LabTestCreate;
import com.Lab.TestWiseNoteCreate;
import com.ShareClass.SeatCreate;
import com.ShareClass.ChangePassword;
import com.ShareClass.CorporateinfoCreate;
import com.ShareClass.DoctorInformation;
import com.ShareClass.InventoryCommonButton;
import com.ShareClass.LoginLabOne;
import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
import com.ShareClass.UserAuthentication;
import com.ShareClass.UserCreate;
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

public class RootFrame extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;
	InventoryCommonButton inventoryCommonButton=new InventoryCommonButton();
	JFrame fr=new JFrame();
	JPanel panelNorth=new JPanel();
	JPanel panelWestNorth=new JPanel();
	JPanel panelCenterNorth=new JPanel();
	JPanel panelEastNorth=new JPanel();
	JPanel panelWest=new JPanel();

	JPanel panelWestTop=new JPanel();
	JPanel panelWestBottom=new JPanel();



	JScrollPane panelScroll=new JScrollPane(panelWest,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JPanel panelCenter=new JPanel();
	JLabel lblClock=new JLabel(); 
	JLabel lblHospitalTitle=new JLabel("Surgiscope Hospital Limited Unit-1"); 
	JLabel lblWelcome=new JLabel("Welcome : ");
	JLabel lblUser=new JLabel("");
	ButtonGroup gp=new ButtonGroup();
	GridBagConstraints grid=new GridBagConstraints();
	BufferedImage image;
	JLabel btnLogout=new JLabel(new ImageIcon("icon/logount.png"));
	JMenuBar bar=new JMenuBar();

	JRadioButton btnSetting=new JRadioButton("Setting");
	JRadioButton btnLabPathology=new JRadioButton("Lab & Pathology");
	JRadioButton btnAccounts=new JRadioButton("Accounts");

	ButtonGroup cGp=new ButtonGroup();
	ButtonGroup rGp=new ButtonGroup();

	JCheckBox checkSupplierCreate=new JCheckBox("SupplierCreate");
	JCheckBox checkUserCreate=new JCheckBox("User Create");
	JCheckBox checkUserAuthentication=new JCheckBox("User Authentication");
	JCheckBox checkChangePassword=new JCheckBox("Change Password");
	JCheckBox checkDepartmentCreate=new JCheckBox("Deparment Create");
	JCheckBox checkSeatCreate=new JCheckBox("Seat Create");
	JCheckBox checkDoctorCreate=new JCheckBox("Doctor Information Create");
	JCheckBox checkBirthCertificateCreate=new JCheckBox("Birth Certificate Create");
	JCheckBox checkManagementCreate=new JCheckBox("Management Information Create");
	JCheckBox checkCorporateCreate=new JCheckBox("Corporate Information Create");
	JCheckBox checkOrganizationInfo=new JCheckBox("Organization Information Set");



	String Year[]={"2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030"};
	JComboBox cmbYear=new JComboBox(Year);

	String Month[]={"January ","February","March","April","May","June","July","August","September","Octobor","Nobember","December"};
	JComboBox cmbMonth=new JComboBox(Month);

	//Lab & Pathoolgy....................................
	JCheckBox CheckLabTestCreate=new JCheckBox("Test Create");
	JCheckBox CheckLabSubTestCreate=new JCheckBox("Sub Test Create");
	JCheckBox CheckLabTestPerticularCreate=new JCheckBox("Test Perticular Create");

	JCheckBox CheckLabDoctorComissionSet=new JCheckBox("Doctor Comission Set");


	JCheckBox CheckLabBilling=new JCheckBox("Lab Billing");

	JCheckBox CheckLabNoteCreate=new JCheckBox("Note Create");
	JCheckBox CheckLabResult=new JCheckBox("Investigation Result");
	JCheckBox CheckLabInchargeConsultantDegree=new JCheckBox("Lab Incharge/Consultant Set");

	JCheckBox CheckLabSaleStatement=new JCheckBox("Lab Sale Statement");
	JCheckBox CheckLabSaleStatementUserWise=new JCheckBox("Lab Sale Statement (User Wise)");
	JCheckBox CheckLabSaleCashStatement=new JCheckBox("Lab Sale Cash Statement ");
	//JCheckBox CheckLabSaleCashStatement=new JCheckBox("Lab Sale Cash Statement Details (User Wise)");
	//JCheckBox CheckLabSaleCashStatementSummery=new JCheckBox("Lab Sale Cash Statement Summary (User Wise)");
	JCheckBox CheckLabDueReceiveStatement=new JCheckBox("Lab Due Receive Statement");
	JCheckBox CheckLabSaleDueStatement=new JCheckBox("Lab Sale Due Statement");
	JCheckBox CheckLabIdWiseRefundStatement=new JCheckBox("Lab Id Wise Refund Statement ");
	JCheckBox CheckLabDepartmentWiseSaleDueStatement=new JCheckBox("Department Wise Lab Sales Statement");
	JCheckBox CheckDepartmentInvestigationStatement=new JCheckBox("Department Wise Investigation Statement");
	JCheckBox CheckTestInvestigationStatement=new JCheckBox("Test Wise Investigation Statement");
	JCheckBox CheckLabIdWiseRefferalComissionStatement=new JCheckBox("Lab ID Wise Refferal Comission Statement");
	JCheckBox CheckLabIdWiseRefferalComissionStatementForManagement=new JCheckBox("Lab ID Wise Refferal Com. For Management");
	JCheckBox CheckAllRefferalComissionStatementSummery=new JCheckBox("All Refferal Comission Statement Summery");
	JCheckBox CheckRefferalWiseInvesgitaionTestStatus=new JCheckBox("Refferal Wise Investigation Test Status");
	JCheckBox CheckPatientWiseInvestigation=new JCheckBox("Patient Wise Investigation ");
	SuggestText cmbRefferName=new SuggestText();
	SuggestText cmbUserName=new SuggestText();
	SuggestText cmbTestName=new SuggestText();


	JCheckBox CheckAccountGroupAndLedgerCreate=new JCheckBox("Group And Ledger Create");
	JCheckBox CheckAccountCashPaymentVoucher=new JCheckBox("Cash Payment Voucher");
	JCheckBox CheckAccountBankPaymentVoucher=new JCheckBox("Bank Payment Voucher");
	JCheckBox CheckAccountCashReceiveVoucher=new JCheckBox("Cash Received Voucher");
	JCheckBox CheckAccountBankReceiveVoucher=new JCheckBox("Bank Received Voucher");
	JCheckBox CheckAccountJournalPostingVoucher=new JCheckBox("Journal Voucher Posting");

	JCheckBox CheckAccountReportByLedger=new JCheckBox("Report By Ledger");
	JCheckBox CheckAccountCashPaymentVoucherSummery=new JCheckBox("Cash Payment Voucher Summery");
	JCheckBox CheckAccountCashReceivedVoucherSummery=new JCheckBox("Cash Received Voucher Summery");


	JCheckBox CheckAccountCashTransectionIncomeExpense=new JCheckBox("Cash Transection Income & Expense");
	JCheckBox CheckMonthlyExpenditureForDiagnostic=new JCheckBox("Monthly Expenditure For Daignostic");



	JCheckBox CheckMonthlyIncomeFromDiagnostic=new JCheckBox("Monthly Income From Daignostic");
	JCheckBox CheckAccountIncomeStatement=new JCheckBox("Income Statement");
	JCheckBox CheckAccountProfitAndLoss=new JCheckBox("Profil And Loss");
	JCheckBox CheckAccountTrialBalance=new JCheckBox("Trial Balance");
	JCheckBox CheckAccountBalanceSheet=new JCheckBox("Balance Sheet");

	JCheckBox CheckAccountMonthlyIncomeExpenseSummaryDaignostic=new JCheckBox("Monthly Income Expense Summary Daignostic");





	SuggestText cmbTestDepartmentName=new SuggestText();

	SuggestText cmbAllLedgerName=new SuggestText();

	JCheckBox CheckAllTestDepartment=new JCheckBox("All");
	JCheckBox CheckAllLedgerList=new JCheckBox("All");
	JCheckBox checkSingleDate=new JCheckBox("Single Date");
	JCheckBox checkMultipleDate=new JCheckBox("Multiple Date");
	JDateChooser txtStartDate=new JDateChooser();
	JDateChooser txtEndDate=new JDateChooser();
	JButton btnPreview=new JButton("Preview",new ImageIcon("icon/print.png"));
	JButton btnExport=new JButton("Export",new ImageIcon("icon/print.png"));
	JCheckBox checkAll=new JCheckBox("All");


	JCheckBox checkTestAll=new JCheckBox("All");
	JCheckBox checkRefferAll=new JCheckBox("All");
	JCheckBox CheckAllUserName=new JCheckBox("All");

	JCheckBox checkIndoorPatient=new JCheckBox("Indoor Patient");
	JCheckBox checkOutdoorPatient=new JCheckBox("Department");
	JCheckBox checkAllPatient=new JCheckBox("All");
	ButtonGroup Pgp=new ButtonGroup();


	JCheckBox checkReferral=new JCheckBox("Referral");
	JCheckBox checkNonReferral=new JCheckBox("Non Referral");
	JCheckBox checkAllTypeReferral=new JCheckBox("All");
	ButtonGroup rererralgp=new ButtonGroup();

	JRadioButton btnSummery=new JRadioButton("Summery");
	JRadioButton btnDetails=new JRadioButton("Detials");
	ButtonGroup Lgp=new ButtonGroup();

	JRadioButton btnForm=new JRadioButton("Form");
	JRadioButton btnReport=new JRadioButton("Report");
	ButtonGroup formgp=new ButtonGroup();

	HashMap map=new HashMap();;

	private static String marqueeText;
	private static RootFrame myMarquee = new RootFrame(" ");
	private static JLabel textOutput = new JLabel(myMarquee.toString());
	private static final String DATE_PATTERN = "MM/yy";

	final JDesktopPane dtp = new JDesktopPane(){
		private static final long serialVersionUID = 1L;
		ImageIcon icon = new ImageIcon("icon/cursor.png");
		Image image = icon.getImage();

		Image newimage = image.getScaledInstance(1360, 760, Image.SCALE_SMOOTH);

		@Override
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(newimage, 0, 0, this);
		}
	};	

	public RootFrame(JFrame frm,SessionBeam sessionBeam) {
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.sessionBeam=sessionBeam;
		this.fr=frm;
		this.fr.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.fr.setLocationRelativeTo(null);
		this.fr.setVisible(true);
		this.fr.setTitle("Software For "+sessionBeam.getOrgName());
		this.fr.setResizable(true);
		//this.fr.setJMenuBar(bar);
		bar.add(lblWelcome);
		//lblWelcome
		bar.add(lblUser);
		lblUser.setText(sessionBeam.getUserName()+"    ");
		lblWelcome.setFont(new Font("arial", Font.BOLD, 13));
		lblUser.setFont(new Font("arial", Font.BOLD, 13));
		lblUser.setForeground(Color.red);
		lblUser.setPreferredSize(new Dimension(590, 20));

		bar.add(btnLogout);
		btnLogout.setPreferredSize(new Dimension(70, 22));


		map.put("orgName",sessionBeam.getOrgName());
		map.put("orgAddress",sessionBeam.getOrgAddress());
		map.put("orgMobile",sessionBeam.getOrgMobile());


		addCmp();
		RadionActionEvent();
		background();
		SettingModule();
		Lab();
		GroupButtonEvent();
		AccountsModule();
	}	

	public RootFrame(String marquee){
		marqueeText = marquee;
	}



	private void GroupButtonEvent(){

		btnForm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(btnForm.isSelected()){
					if(btnSetting.isSelected()){
						SettingPanelWork();
					}
					if(btnAccounts.isSelected()){
						AccountsPanelWork();
					}
					if(btnLabPathology.isSelected()){
						LabPathologyPanelWork();
					}
				}

			}
		});
		btnReport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(btnReport.isSelected()){
					if(btnSetting.isSelected()){
						SettingPanelWork();
					}
					if(btnAccounts.isSelected()){
						AccountsPanelWork();
					}
					if(btnLabPathology.isSelected()){
						LabPathologyPanelWork();
					}
				}

			}
		});

		btnSetting.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {	
				if(btnSetting.isSelected()){
					SettingPanelWork();
				}
			}
		});		
		btnLabPathology.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {	
				if(btnLabPathology.isSelected()){
					LabPathologyPanelWork();
				}
			}
		});

		btnAccounts.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {	
				if(btnAccounts.isSelected()){
					AccountsPanelWork();
				}
			}
		});

		checkSingleDate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkSingleDate.isSelected()){
					txtEndDate.setEnabled(false);
				}
			}
		});

		CheckAllUserName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(CheckAllUserName.isSelected() && (CheckLabSaleStatementUserWise.isSelected()  || CheckLabSaleCashStatement.isSelected()  || CheckLabDueReceiveStatement.isSelected())){
					cmbUserName.txtMrNo.setText("");
					cmbUserName.combo.setEnabled(false);
				}
				if(!CheckAllUserName.isSelected() && (CheckLabSaleStatementUserWise.isSelected()  || CheckLabSaleCashStatement.isSelected()  || CheckLabDueReceiveStatement.isSelected() )){
					cmbUserName.txtMrNo.setText("");
					cmbUserName.combo.setEnabled(true);
				}
			}
		});
		checkRefferAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkRefferAll.isSelected() && (CheckRefferalWiseInvesgitaionTestStatus.isSelected() || CheckLabIdWiseRefferalComissionStatement.isSelected()||CheckLabIdWiseRefferalComissionStatementForManagement.isSelected() || CheckAllRefferalComissionStatementSummery.isSelected())){
					cmbRefferName.txtMrNo.setText("");
					cmbRefferName.combo.setEnabled(false);
				}
				if(!checkRefferAll.isSelected() && (CheckRefferalWiseInvesgitaionTestStatus.isSelected() || CheckLabIdWiseRefferalComissionStatement.isSelected() ||CheckLabIdWiseRefferalComissionStatementForManagement.isSelected()|| CheckAllRefferalComissionStatementSummery.isSelected())){
					cmbRefferName.txtMrNo.setText("");
					cmbRefferName.combo.setEnabled(true);
				}
			}
		});
		checkTestAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkTestAll.isSelected() && CheckTestInvestigationStatement.isSelected()){
					cmbTestName.txtMrNo.setText("");
					cmbTestName.combo.setEnabled(false);
				}
				if(!checkTestAll.isSelected() && CheckTestInvestigationStatement.isSelected()){
					cmbTestName.txtMrNo.setText("");
					cmbTestName.combo.setEnabled(true);
				}
			}
		});

		CheckAllTestDepartment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(CheckAllTestDepartment.isSelected() && CheckDepartmentInvestigationStatement.isSelected()){
					cmbTestDepartmentName.txtMrNo.setText("");
					cmbTestDepartmentName.combo.setEnabled(false);
				}
				if(!CheckAllTestDepartment.isSelected() && CheckDepartmentInvestigationStatement.isSelected()){
					cmbTestDepartmentName.txtMrNo.setText("");
					cmbTestDepartmentName.combo.setEnabled(true);
				}
			}
		});


		btnPreview.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {	
				//setTestWiseDiscount();
				btnPreviewEvent();
			}
		});
	}
	private void AccountsModule(){
		CheckAccountGroupAndLedgerCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckAccountGroupAndLedgerCreate.isSelected()){
					JInternalFrame AccGroupLedgerCreate = new JInternalFrame();
					dtp.add(AccGroupLedgerCreate);
					AccGroupLedgerCreate.setTitle("Accounts Group And Ledger Creater :: "+sessionBeam.getOrgName());
					AccGroupLedgerCreate.setLocation(40,0);
					AccGroupLedgerCreate.setSize(1280,670);
					AccGroupLedgerCreate.setVisible(true);
					AccGroupLedgerCreate.setClosable(true);
					AccGroupAndLedgerCreate AccGroupLedgerCreate1=new AccGroupAndLedgerCreate(sessionBeam);
					AccGroupLedgerCreate.add(AccGroupLedgerCreate1);
					AccGroupLedgerCreate1.loadDepartment();
					AccGroupLedgerCreate1.hitMethod();
				}
			}
		});
		CheckAccountReportByLedger.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(CheckAccountReportByLedger.isSelected()){
					LoadLedgerList();
				}
			}
		});
		CheckAccountCashTransectionIncomeExpense.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckAccountCashTransectionIncomeExpense.isSelected()){
					JInternalFrame AccountCashTransectionIncomeExpense = new JInternalFrame();
					dtp.add(AccountCashTransectionIncomeExpense);
					AccountCashTransectionIncomeExpense.setTitle("Daily Cash Transactio Income And Expense:: "+sessionBeam.getOrgName());
					AccountCashTransectionIncomeExpense.setLocation(200,0);
					AccountCashTransectionIncomeExpense.setSize(950, 660);
					AccountCashTransectionIncomeExpense.setVisible(true);
					AccountCashTransectionIncomeExpense.setClosable(true);
					CashTransectionReportIncomeExpense labBill1=new CashTransectionReportIncomeExpense(sessionBeam);
					AccountCashTransectionIncomeExpense.add(labBill1);		
				}
			}
		});
		CheckAccountIncomeStatement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckAccountIncomeStatement.isSelected()){
					JInternalFrame AccountIncomeStatement = new JInternalFrame();
					dtp.add(AccountIncomeStatement);
					AccountIncomeStatement.setTitle("Income Statement:: "+sessionBeam.getOrgName());
					AccountIncomeStatement.setLocation(200,0);
					AccountIncomeStatement.setSize(950, 660);
					AccountIncomeStatement.setVisible(true);
					AccountIncomeStatement.setClosable(true);
					FIncomeStatement labBill1=new FIncomeStatement(sessionBeam);
					AccountIncomeStatement.add(labBill1);		
				}
			}
		});
		CheckAccountProfitAndLoss.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckAccountProfitAndLoss.isSelected()){
					JInternalFrame AccountProfitAndLoss = new JInternalFrame();
					dtp.add(AccountProfitAndLoss);
					AccountProfitAndLoss.setTitle("Profit And Loss Account:: "+sessionBeam.getOrgName());
					AccountProfitAndLoss.setLocation(250,0);
					AccountProfitAndLoss.setSize(850, 660);
					AccountProfitAndLoss.setVisible(true);
					AccountProfitAndLoss.setClosable(true);
					FProfileAndLoseAccount labBill1=new FProfileAndLoseAccount(sessionBeam);
					AccountProfitAndLoss.add(labBill1);		
				}
			}
		});
		CheckAccountTrialBalance.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckAccountTrialBalance.isSelected()){
					JInternalFrame AccountTrialBalance = new JInternalFrame();
					dtp.add(AccountTrialBalance);
					AccountTrialBalance.setTitle("Trail Balance Statement:: "+sessionBeam.getOrgName());
					AccountTrialBalance.setLocation(250,0);
					AccountTrialBalance.setSize(950, 660);
					AccountTrialBalance.setVisible(true);
					AccountTrialBalance.setClosable(true);
					FTraialBalance AccountTrialBalance1=new FTraialBalance(sessionBeam);
					AccountTrialBalance.add(AccountTrialBalance1);		
				}
			}
		});
		CheckAccountBalanceSheet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckAccountBalanceSheet.isSelected()){
					JInternalFrame AccountBalanceSheet = new JInternalFrame();
					dtp.add(AccountBalanceSheet);
					AccountBalanceSheet.setTitle("Balance Sheet Statement:: "+sessionBeam.getOrgName());
					AccountBalanceSheet.setLocation(250,0);
					AccountBalanceSheet.setSize(950, 660);
					AccountBalanceSheet.setVisible(true);
					AccountBalanceSheet.setClosable(true);
					FBalanceSheet AccountBalanceSheet1=new FBalanceSheet(sessionBeam);
					AccountBalanceSheet.add(AccountBalanceSheet1);		
				}
			}
		});
		CheckAccountCashPaymentVoucher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckAccountCashPaymentVoucher.isSelected()){
					JInternalFrame AccountCashPaymentVoucher = new JInternalFrame();
					dtp.add(AccountCashPaymentVoucher);
					AccountCashPaymentVoucher.setTitle("Cash Payment Voucher:: "+sessionBeam.getOrgName());
					AccountCashPaymentVoucher.setLocation(80,0);
					AccountCashPaymentVoucher.setSize(1200, 660);
					AccountCashPaymentVoucher.setVisible(true);
					AccountCashPaymentVoucher.setClosable(true);
					FCashPaymentVoucher AccountCashPaymentVoucher1=new FCashPaymentVoucher(sessionBeam);
					AccountCashPaymentVoucher.add(AccountCashPaymentVoucher1);	
					AccountCashPaymentVoucher1.loadLedgerName();
					AccountCashPaymentVoucher1.loadPaymentVoucher();
				}
			}
		});
		CheckAccountCashReceiveVoucher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckAccountCashReceiveVoucher.isSelected()){
					JInternalFrame AccountCashReceivedVoucher = new JInternalFrame();
					dtp.add(AccountCashReceivedVoucher);
					AccountCashReceivedVoucher.setTitle("Cash Received Voucher:: "+sessionBeam.getOrgName());
					AccountCashReceivedVoucher.setLocation(80,0);
					AccountCashReceivedVoucher.setSize(1200, 660);
					AccountCashReceivedVoucher.setVisible(true);
					AccountCashReceivedVoucher.setClosable(true);
					FCashReceivedVoucher AccountCashReceivedVoucher1=new FCashReceivedVoucher(sessionBeam);
					AccountCashReceivedVoucher.add(AccountCashReceivedVoucher1);	
					AccountCashReceivedVoucher1.loadLedgerName();
					AccountCashReceivedVoucher1.loadReceivedVoucher();
				}
			}
		});
	}
	private void Lab(){

		CheckLabTestCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckLabTestCreate.isSelected()){
					JInternalFrame labTestCreate = new JInternalFrame();
					dtp.add(labTestCreate);
					labTestCreate.setTitle("Lab & Pathology Test Create :: "+sessionBeam.getOrgName());
					labTestCreate.setLocation(25,30);
					labTestCreate.setSize(1300, 600);
					labTestCreate.setVisible(true);
					labTestCreate.setClosable(true);
					LabTestCreate labTestCreate1=new LabTestCreate(sessionBeam);
					labTestCreate.add(labTestCreate1);
					labTestCreate1.loadTestName();
				}
			}
		});
		CheckLabSubTestCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckLabSubTestCreate.isSelected()){
					JInternalFrame labSubTestCreate = new JInternalFrame();
					dtp.add(labSubTestCreate);
					labSubTestCreate.setTitle("Lab & Pathology Sub Test Create :: "+sessionBeam.getOrgName());
					labSubTestCreate.setLocation(400,30);
					labSubTestCreate.setSize(870, 600);
					labSubTestCreate.setVisible(true);
					labSubTestCreate.setClosable(true);
					com.Lab.LabSubTestCreate labSubTestCreate1=new com.Lab.LabSubTestCreate(sessionBeam);
					labSubTestCreate.add(labSubTestCreate1);
					labSubTestCreate1.loadTestName();
				}
			}
		});
		CheckLabTestPerticularCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckLabTestPerticularCreate.isSelected()){
					JInternalFrame labTestPerticularCreate = new JInternalFrame();
					dtp.add(labTestPerticularCreate);
					labTestPerticularCreate.setTitle("Lab & Pathology Test Perticular Create :: "+sessionBeam.getOrgName());
					labTestPerticularCreate.setLocation(400,30);
					labTestPerticularCreate.setSize(870, 600);
					labTestPerticularCreate.setVisible(true);
					labTestPerticularCreate.setClosable(true);
					com.Lab.LabTestPerticularCreate labTestPerticularCreate1=new com.Lab.LabTestPerticularCreate(sessionBeam);
					labTestPerticularCreate.add(labTestPerticularCreate1);
					labTestPerticularCreate1.loadTestName();
				}

			}
		});
		CheckLabDoctorComissionSet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckLabDoctorComissionSet.isSelected()){
					JInternalFrame labDoctorComissionCreate = new JInternalFrame();
					dtp.add(labDoctorComissionCreate);
					labDoctorComissionCreate.setTitle("Doctor Comission Set :: "+sessionBeam.getOrgName());
					labDoctorComissionCreate.setLocation(260,30);
					labDoctorComissionCreate.setSize(1000, 600);
					labDoctorComissionCreate.setVisible(true);
					labDoctorComissionCreate.setClosable(true);
					DoctorComissionSet labDoctorComissionCreate1=new DoctorComissionSet(sessionBeam);
					labDoctorComissionCreate.add(labDoctorComissionCreate1);
					labDoctorComissionCreate1.loadTestName();
					labDoctorComissionCreate1.loadDoctorName();
				}

			}
		});
		CheckLabBilling.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckLabBilling.isSelected()){
					JInternalFrame labBill = new JInternalFrame();
					dtp.add(labBill);
					labBill.setTitle("Lab Billing :: "+sessionBeam.getOrgName());
					labBill.setLocation(30,0);
					labBill.setSize(1300, 670);
					labBill.setVisible(true);
					labBill.setClosable(true);
					LabBilling labBill1=new LabBilling(sessionBeam);
					labBill.add(labBill1);
					labBill1.loadDoctorName();
					labBill1.loadPatientReg();
					labBill1.loadTestCode();
					labBill1.loadtestName();
					//labBill1.DeleteNullData();
					labBill1.checkCounter();
					labBill1.LoadLabBillNo();
					labBill1.DeleteNullData();

				}
			}
		});
		CheckLabResult.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(CheckLabResult.isSelected()){
					JInternalFrame labResult = new JInternalFrame();
					dtp.add(labResult);
					labResult.setTitle("Lab Result :: "+sessionBeam.getOrgName());
					labResult.setLocation(30,0);
					labResult.setSize(1300, 680);
					labResult.setVisible(true);
					labResult.setClosable(true);
					LabRptHome labResult1=new LabRptHome(sessionBeam);
					labResult.add(labResult1);
					labResult1.setLastBill();
				}
			}
		});
		CheckLabInchargeConsultantDegree.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(CheckLabInchargeConsultantDegree.isSelected()){
					JInternalFrame labIncharge = new JInternalFrame();
					dtp.add(labIncharge);
					labIncharge.setTitle("Lab Incharge And Consultant Set :: "+sessionBeam.getOrgName());
					labIncharge.setLocation(350,0);
					labIncharge.setSize(700, 600);
					labIncharge.setVisible(true);
					labIncharge.setClosable(true);
					LabInchargeCosultentSet labIncharge1=new LabInchargeCosultentSet(sessionBeam);
					labIncharge.add(labIncharge1);
					
				}
			}
		});
		CheckLabNoteCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(CheckLabNoteCreate.isSelected()){
					JInternalFrame labNoteCreate = new JInternalFrame();
					dtp.add(labNoteCreate);
					labNoteCreate.setTitle("Lab Note Create :: "+sessionBeam.getOrgName());
					labNoteCreate.setLocation(200,40);
					labNoteCreate.setSize(920, 550);
					labNoteCreate.setVisible(true);
					labNoteCreate.setClosable(true);
					TestWiseNoteCreate labNoteCreate1=new TestWiseNoteCreate(sessionBeam);
					labNoteCreate.add(labNoteCreate1);
					labNoteCreate1.loadTestName();
				}
			}
		});
		CheckTestInvestigationStatement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(CheckTestInvestigationStatement.isSelected()){
					loadTestName();
				}
			}
		});
		CheckDepartmentInvestigationStatement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(CheckDepartmentInvestigationStatement.isSelected()){
					loadTestGroupName();
				}
			}
		});
		CheckRefferalWiseInvesgitaionTestStatus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(CheckRefferalWiseInvesgitaionTestStatus.isSelected()){
					loadAllRefferalName();
				}
			}
		});
		CheckLabIdWiseRefferalComissionStatement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(CheckLabIdWiseRefferalComissionStatement.isSelected()){
					loadAllRefferalName();
				}
			}
		});
		
		CheckLabIdWiseRefferalComissionStatementForManagement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(CheckLabIdWiseRefferalComissionStatementForManagement.isSelected()){
					loadAllRefferalName();
				}
			}
		});
		CheckAllRefferalComissionStatementSummery.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(CheckAllRefferalComissionStatementSummery.isSelected()){
					loadAllRefferalName();
				}
			}
		});

		CheckLabSaleStatementUserWise.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(CheckLabSaleStatementUserWise.isSelected()){
					loadUserName();
				}
			}
		});

		CheckLabSaleCashStatement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(CheckLabSaleCashStatement.isSelected()){
					loadUserName();
				}
			}
		});
		//		CheckLabSaleCashStatementSummery.addActionListener(new ActionListener() {
		//
		//			@Override
		//			public void actionPerformed(ActionEvent e) {
		//				// TODO Auto-generated method stub
		//				if(CheckLabSaleCashStatementSummery.isSelected()){
		//					loadUserName();
		//				}
		//			}
		//		});

		CheckLabDueReceiveStatement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(CheckLabDueReceiveStatement.isSelected()){
					loadUserName();
				}
			}
		});

		/*		CheckAllRefferalComissionStatement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(CheckAllRefferalComissionStatement.isSelected()){
					loadAllRefferalName();
				}

			}
		});*/
	}
	private void SettingModule(){

		checkDepartmentCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JInternalFrame department = new JInternalFrame();
				dtp.add(department);
				department.setTitle(" Department Create :: "+sessionBeam.getOrgName());
				department.setLocation(390,100);
				department.setSize(520,460);
				department.setVisible(true);
				department.setClosable(true);
				com.ShareClass.DepartmentCreate departmentCreate=new com.ShareClass.DepartmentCreate(sessionBeam);
				department.add(departmentCreate);

			}
		});
		checkSeatCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JInternalFrame seat = new JInternalFrame();
				dtp.add(seat);
				seat.setTitle("Room Tariff :: "+sessionBeam.getOrgName());
				seat.setLocation(200,100);
				seat.setSize(800,500);
				seat.setVisible(true);
				seat.setClosable(true);
				SeatCreate seatCreate=new SeatCreate(sessionBeam);
				seat.add(seatCreate);
				//cabinCreate.loadUnitName();
			}
		});
		checkDoctorCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JInternalFrame doctorCreate = new JInternalFrame();
				dtp.add(doctorCreate);
				doctorCreate.setTitle("Doctor Create :: "+sessionBeam.getOrgName());
				doctorCreate.setLocation(35,0);
				doctorCreate.setSize(1280,650);
				doctorCreate.setVisible(true);
				doctorCreate.setClosable(true);
				DoctorInformation doctorCreate1=new DoctorInformation(sessionBeam);
				doctorCreate.add(doctorCreate1);
				//cabinCreate.loadUnitName();
			}
		});
		checkManagementCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JInternalFrame managementCreate = new JInternalFrame();
				dtp.add(managementCreate);
				managementCreate.setTitle("Management Create :: "+sessionBeam.getOrgName());
				managementCreate.setLocation(35,0);
				managementCreate.setSize(1280,670);
				managementCreate.setVisible(true);
				managementCreate.setClosable(true);
				ManagementInformation managementCreate1=new ManagementInformation(sessionBeam);
				managementCreate.add(managementCreate1);
				managementCreate1.txtName.requestFocusInWindow();
				//cabinCreate.loadUnitName();
			}
		});
		checkCorporateCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JInternalFrame corporateCreate = new JInternalFrame();
				dtp.add(corporateCreate);
				corporateCreate.setTitle("Corporate Create :: "+sessionBeam.getOrgName());
				corporateCreate.setLocation(35,0);
				corporateCreate.setSize(1280,670);
				corporateCreate.setVisible(true);
				corporateCreate.setClosable(true);
				CorporateinfoCreate corporateCreate1=new CorporateinfoCreate(sessionBeam);
				corporateCreate.add(corporateCreate1);
				corporateCreate1.txtName.requestFocusInWindow();
				//cabinCreate.loadUnitName();
			}
		});

		checkOrganizationInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JInternalFrame corporateCreate = new JInternalFrame();
				dtp.add(corporateCreate);
				corporateCreate.setTitle("Organization Information Set :: "+sessionBeam.getOrgName());
				corporateCreate.setLocation(430,130);
				corporateCreate.setSize(550,300);
				corporateCreate.setVisible(true);
				corporateCreate.setClosable(true);
				OrganizationInfo corporateCreate1=new OrganizationInfo(sessionBeam);
				corporateCreate.add(corporateCreate1);
				corporateCreate1.txtOrgName.requestFocusInWindow();
				//cabinCreate.loadUnitName();
			}
		});

		checkBirthCertificateCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JInternalFrame BirthCertificate = new JInternalFrame();
				dtp.add(BirthCertificate);
				BirthCertificate.setTitle("Birth Certificate Create :: "+sessionBeam.getOrgName());
				BirthCertificate.setLocation(470,50);
				BirthCertificate.setSize(450,450);
				BirthCertificate.setVisible(true);
				BirthCertificate.setClosable(true);
				com.ShareClass.BirthCertificateCreate BirthCertificate1=new com.ShareClass.BirthCertificateCreate(sessionBeam);
				BirthCertificate.add(BirthCertificate1);
				BirthCertificate1.loadRegNo();
				BirthCertificate1.loadAuthorizedName();
				//cabinCreate.loadUnitName();
			}
		});
		checkUserCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JInternalFrame userCreate = new JInternalFrame();
				dtp.add(userCreate);
				userCreate.setTitle("User Create :: "+sessionBeam.getOrgName());
				userCreate.setLocation(200,100);
				userCreate.setSize(800,400);
				userCreate.setVisible(true);
				userCreate.setClosable(true);
				UserCreate userCreate1=new UserCreate(sessionBeam);
				userCreate.add(userCreate1);
				//cabinCreate.loadUnitName();
			}
		});
		checkUserAuthentication.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JInternalFrame userAuthentication = new JInternalFrame();
				dtp.add(userAuthentication);
				userAuthentication.setTitle("User Authentication :: "+sessionBeam.getOrgName());
				userAuthentication.setLocation(200,100);
				userAuthentication.setSize(800,400);
				userAuthentication.setVisible(true);
				userAuthentication.setClosable(true);
				UserAuthentication userAuthentication1=new UserAuthentication(sessionBeam);
				userAuthentication.add(userAuthentication1);
				userAuthentication1.loadUserName();
			}
		});
		checkChangePassword.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JInternalFrame ChangePassword = new JInternalFrame();
				dtp.add(ChangePassword);
				ChangePassword.setTitle("Change Password :: "+sessionBeam.getOrgName());
				ChangePassword.setLocation(400,140);
				ChangePassword.setSize(450,260);
				ChangePassword.setVisible(true);
				ChangePassword.setClosable(true);
				ChangePassword ChangePassword1=new ChangePassword(sessionBeam);
				ChangePassword.add(ChangePassword1);

			}
		});
		checkSupplierCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JInternalFrame supplierCreate = new JInternalFrame();
				dtp.add(supplierCreate);
				supplierCreate.setTitle("Supplier Create :: "+sessionBeam.getOrgName());
				supplierCreate.setLocation(60,10);
				supplierCreate.setSize(1280,600);
				supplierCreate.setVisible(true);
				supplierCreate.setClosable(true);
				com.ShareClass.SupplierCreate ChangePassword1=new com.ShareClass.SupplierCreate(sessionBeam);
				supplierCreate.add(ChangePassword1);

			}
		});

	}

	private void btnPreviewEvent() {

		if(CheckLabSaleStatement.isSelected()){
			OpenTotalLabIdRpt();
		}
		if(CheckLabSaleStatementUserWise.isSelected()){
			OpenTotalLabIdUserWiseRpt();
		}
		if(CheckLabIdWiseRefundStatement.isSelected()){
			OpenLabIdWiseRefundStatementRpt();
		}
		if(CheckLabDepartmentWiseSaleDueStatement.isSelected()){
			OpenLabDepartmentWiseSalesSatementRpt();
		}
		if(CheckLabSaleCashStatement.isSelected()){
			if(btnSummery.isSelected()) {
				OpenLabSaleCashSummeryRpt();
			}else {
				OpenLabSaleCashRpt();
			}

		}
		//		if(CheckLabSaleCashStatementSummery.isSelected()){
		//			OpenLabSaleCashSummeryRpt();
		//		}
		if(CheckLabDueReceiveStatement.isSelected()){
			OpenLabDueReceiveStatementRpt();
		}
		if(CheckLabSaleDueStatement.isSelected()){
			OpenLabSaleDueRpt();
		}
		if(CheckTestInvestigationStatement.isSelected()){
			OpenTestWiseTotalInvestigationRpt();
		}
		if(CheckDepartmentInvestigationStatement.isSelected()){
			OpenTestDeapartmenttWiseTotalInvestigationRpt();
		}
		if(CheckRefferalWiseInvesgitaionTestStatus.isSelected()){
			OpenRefferalWiseInvesgitaionTestStatusRpt();
		}

		if(CheckLabIdWiseRefferalComissionStatement.isSelected()){
			OpenLabIdWiseRefferComissionRpt();
		}
		if(CheckLabIdWiseRefferalComissionStatementForManagement.isSelected()){
			OpenLabIdWiseRefferComissionRptForManagement();
		}
		
		if(CheckAllRefferalComissionStatementSummery.isSelected()){
			OpenAllRefferComissionRpt();
		}
		if(CheckAccountReportByLedger.isSelected()){
			OpenLedgerReport();
		}
		if(CheckAccountCashPaymentVoucherSummery.isSelected()){
			OpenCashPaymentVoucherSummery();
		}
		if(CheckAccountCashReceivedVoucherSummery.isSelected()){
			OpenCashReceivedVoucherSummery();
		}
		if(CheckMonthlyIncomeFromDiagnostic.isSelected()){
			OpenCheckMonthlyIncomeFromDaignostic();
		}
		if(CheckMonthlyExpenditureForDiagnostic.isSelected()){
			OpenCheckMonthlyExpenseFromDaignostic();
		}
		if(CheckAccountMonthlyIncomeExpenseSummaryDaignostic.isSelected()){
			OpenAccountMonthlyIncomeExpenseSummaryDaignostic();
		}

	}

	private int getCurrentPeriod(String RegNo,String PatientFiscalYear) {
		int period=0;
		try {
			ResultSet rs=db.sta.executeQuery("select  period from tbpatientinfo where RegNo='"+RegNo+"' and FiscalYear='"+PatientFiscalYear+"' order by period desc");
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

	public int getLastDayOfMonth(String dateString) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(dateString));
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}


	private void OpenLabIdWiseRefferComissionRpt() {
		try {
			setPaidDate();
			String report="";
			String sql="";
			if(!cmbRefferName.txtMrNo.getText().trim().toString().isEmpty()){
				String refferId=getRefferId();	
			
			
				if(checkReferral.isSelected()){
					sql="select * from TbRefferWiseComissionStatementDetails('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"','"+refferId+"')";
				}
				else if(checkNonReferral.isSelected()){
					sql="select * from TbRefferWiseComissionStatementForNonReferralDetails('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"','"+refferId+"')";
				}
				else {
					sql="select * from TbRefferWiseComissionStatementBothReferralNonRefferalDetails('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"','"+refferId+"')";
				}
				
				report="LabStatementReport/GroupWiseComissionreport.jrxml";

			}
			else if(checkRefferAll.isSelected()){
				
				
				if(checkReferral.isSelected()){
					sql="select * from TbAllRefferWiseComissionStatementDetails('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"')";
				}
				else if(checkNonReferral.isSelected()){
					sql="select * from TbAllRefferWiseComissionStatementForNonReferralDetails('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"')";
				}
				else {
					sql="select * from TbAllRefferWiseComissionStatementBothReferralNonRefferalDetails('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"')";
				}
				report="LabStatementReport/GroupWiseComissionreport.jrxml";
			}
			
			
			
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
	
	private void OpenLabIdWiseRefferComissionRptForManagement() {

		try {
			setPaidDate();
			String report="";
			String sql="";
			if(!cmbRefferName.txtMrNo.getText().trim().toString().isEmpty()){
				String refferId=getRefferId();	
			
			
				if(checkReferral.isSelected()){
					sql="select * from TbRefferWiseComissionStatementDetails('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"','"+refferId+"')";
				}
				else if(checkNonReferral.isSelected()){
					sql="select * from TbRefferWiseComissionStatementForNonReferralDetails('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"','"+refferId+"')";
				}
				else {
					sql="select * from TbRefferWiseComissionStatementBothReferralNonRefferalDetails('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"','"+refferId+"')";
				}
				
				report="LabStatementReport/ComissionStatementForManage.jrxml";

			}
			
			
			else if(checkRefferAll.isSelected()){
				
				
				if(checkReferral.isSelected()){
					sql="select * from TbAllRefferWiseComissionStatementDetails('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"')";
				}
				else if(checkNonReferral.isSelected()){
					sql="select * from TbAllRefferWiseComissionStatementForNonReferralDetails('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"')";
				}
				else {
					sql="select * from TbAllRefferWiseComissionStatementBothReferralNonRefferalDetails('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"')";
				}
				report="LabStatementReport/ComissionStatementForManage.jrxml";
			}
			
			
			
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
	private void OpenAllRefferComissionRpt() {
		try {
			setPaidDate();
			String sql="";
			if(!cmbRefferName.txtMrNo.getText().trim().toString().isEmpty()){

				if(checkReferral.isSelected()){
					sql="select RefferName,MobileNo,Address,sum(PathologyRate) as PathologyRate, sum(PathologyNetAmount) as PathologyNetAmount, sum(PathologyCMDDiscount) as PathologyCMDDiscount,sum(HormoneRate) as HormoneRate,sum(HormoneNetAmount) as HormoneNetAmount,avg(HormoneCMDDiscount)as HormoneCMDDiscount,sum(EchoCardRate) as EchoCardRate,sum(EchoCardNetAmount) as EchoCardNetAmount,avg(EchoCardCMDDiscount) as EchoCardCMDDiscount ,sum(UltraSonoRate) as UltraSonoRate,sum(UltraSonoNetAmount) as UltraSonoNetAmount,avg(UltraSonoCMDDiscount) as UltraSonoCMDDiscount,sum(EnDosRate) as EnDosRate,sum(EnDosNetAmount) as EnDosNetAmount,avg(EnDosCMDDiscount) as EnDosCMDDiscount,sum(XrayRate) as XrayRate,sum(XrayNetAmount) as XrayNetAmount,avg(XrayCMDDiscount) as XrayCMDDiscount,sum(ECGRate) as ECGRate,sum(ECGNetAmount) as ECGNetAmount,avg(ECGCMDDiscount) as ECGCMDDiscount,sum(FNARate) as FNARate,sum(FNANetAmount) as FNANetAmount,avg(FNACMDDiscount) as FNACMDDiscount,sum(HistopathologyRate) as HistopathologyRate,sum(HistopathologyNetAmount) as HistopathologyNetAmount,avg(HistopathologyCMDDiscount)as HistopathologyCMDDiscount,sum(BloodGroupRate) as BloodGroupRate,sum(BloodGroupNetAmount) as BloodGroupNetAmount,avg(BloodGroupCMDDiscount)as BloodGroupCMDDiscount,sum(PepsSemarRate) as PepsSemarRate,sum(PepsSemarNetAmount) as PepsSemarNetAmount,avg(PepsSemarCMDDiscount) as PepsSemarCMDDiscount,sum(OthersRate) as OthersRate,sum(OthersNetAmount) as OthersNetAmount,avg(OthersCMDDiscount) as OthersCMDDiscount,sum(TotalCharge) as TotalCharge,sum(DoctorDiscount) as DoctorDiscount,sum(ManualDiscount) as ManualDiscount,sum(TotalPaid) as TotalPaid,sum(Due) as Due,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from TbAllRefferWiseComissionStatement('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"') where RefferName='"+cmbRefferName.txtMrNo.getText().trim().toString()+"' group by RefferName,MobileNo,Address";
				}
				else if(checkNonReferral.isSelected()){
					sql="select RefferName,MobileNo,Address,sum(PathologyRate) as PathologyRate, sum(PathologyNetAmount) as PathologyNetAmount, sum(PathologyCMDDiscount) as PathologyCMDDiscount,sum(HormoneRate) as HormoneRate,sum(HormoneNetAmount) as HormoneNetAmount,avg(HormoneCMDDiscount)as HormoneCMDDiscount,sum(EchoCardRate) as EchoCardRate,sum(EchoCardNetAmount) as EchoCardNetAmount,avg(EchoCardCMDDiscount) as EchoCardCMDDiscount ,sum(UltraSonoRate) as UltraSonoRate,sum(UltraSonoNetAmount) as UltraSonoNetAmount,avg(UltraSonoCMDDiscount) as UltraSonoCMDDiscount,sum(EnDosRate) as EnDosRate,sum(EnDosNetAmount) as EnDosNetAmount,avg(EnDosCMDDiscount) as EnDosCMDDiscount,sum(XrayRate) as XrayRate,sum(XrayNetAmount) as XrayNetAmount,avg(XrayCMDDiscount) as XrayCMDDiscount,sum(ECGRate) as ECGRate,sum(ECGNetAmount) as ECGNetAmount,avg(ECGCMDDiscount) as ECGCMDDiscount,sum(FNARate) as FNARate,sum(FNANetAmount) as FNANetAmount,avg(FNACMDDiscount) as FNACMDDiscount,sum(HistopathologyRate) as HistopathologyRate,sum(HistopathologyNetAmount) as HistopathologyNetAmount,avg(HistopathologyCMDDiscount)as HistopathologyCMDDiscount,sum(BloodGroupRate) as BloodGroupRate,sum(BloodGroupNetAmount) as BloodGroupNetAmount,avg(BloodGroupCMDDiscount)as BloodGroupCMDDiscount,sum(PepsSemarRate) as PepsSemarRate,sum(PepsSemarNetAmount) as PepsSemarNetAmount,avg(PepsSemarCMDDiscount) as PepsSemarCMDDiscount,sum(OthersRate) as OthersRate,sum(OthersNetAmount) as OthersNetAmount,avg(OthersCMDDiscount) as OthersCMDDiscount,sum(TotalCharge) as TotalCharge,sum(DoctorDiscount) as DoctorDiscount,sum(ManualDiscount) as ManualDiscount,sum(TotalPaid) as TotalPaid,sum(Due) as Due,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from TbAllRefferWiseComissionStatementForNonReferral('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"') where RefferName='"+cmbRefferName.txtMrNo.getText().trim().toString()+"' group by RefferName,MobileNo,Address";
				}
				else {
					sql="select RefferName,MobileNo,Address,sum(PathologyRate) as PathologyRate, sum(PathologyNetAmount) as PathologyNetAmount, sum(PathologyCMDDiscount) as PathologyCMDDiscount,sum(HormoneRate) as HormoneRate,sum(HormoneNetAmount) as HormoneNetAmount,avg(HormoneCMDDiscount)as HormoneCMDDiscount,sum(EchoCardRate) as EchoCardRate,sum(EchoCardNetAmount) as EchoCardNetAmount,avg(EchoCardCMDDiscount) as EchoCardCMDDiscount ,sum(UltraSonoRate) as UltraSonoRate,sum(UltraSonoNetAmount) as UltraSonoNetAmount,avg(UltraSonoCMDDiscount) as UltraSonoCMDDiscount,sum(EnDosRate) as EnDosRate,sum(EnDosNetAmount) as EnDosNetAmount,avg(EnDosCMDDiscount) as EnDosCMDDiscount,sum(XrayRate) as XrayRate,sum(XrayNetAmount) as XrayNetAmount,avg(XrayCMDDiscount) as XrayCMDDiscount,sum(ECGRate) as ECGRate,sum(ECGNetAmount) as ECGNetAmount,avg(ECGCMDDiscount) as ECGCMDDiscount,sum(FNARate) as FNARate,sum(FNANetAmount) as FNANetAmount,avg(FNACMDDiscount) as FNACMDDiscount,sum(HistopathologyRate) as HistopathologyRate,sum(HistopathologyNetAmount) as HistopathologyNetAmount,avg(HistopathologyCMDDiscount)as HistopathologyCMDDiscount,sum(BloodGroupRate) as BloodGroupRate,sum(BloodGroupNetAmount) as BloodGroupNetAmount,avg(BloodGroupCMDDiscount)as BloodGroupCMDDiscount,sum(PepsSemarRate) as PepsSemarRate,sum(PepsSemarNetAmount) as PepsSemarNetAmount,avg(PepsSemarCMDDiscount) as PepsSemarCMDDiscount,sum(OthersRate) as OthersRate,sum(OthersNetAmount) as OthersNetAmount,avg(OthersCMDDiscount) as OthersCMDDiscount,sum(TotalCharge) as TotalCharge,sum(DoctorDiscount) as DoctorDiscount,sum(ManualDiscount) as ManualDiscount,sum(TotalPaid) as TotalPaid,sum(Due) as Due,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from TbAllRefferWiseComissionStatementBothReferralNonRefferal('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"') where RefferName='"+cmbRefferName.txtMrNo.getText().trim().toString()+"' group by RefferName,MobileNo,Address";
				}

			}
			else{
				if(checkReferral.isSelected()){
					sql="select RefferName,MobileNo,Address,sum(PathologyRate) as PathologyRate, sum(PathologyNetAmount) as PathologyNetAmount, sum(PathologyCMDDiscount) as PathologyCMDDiscount,sum(HormoneRate) as HormoneRate,sum(HormoneNetAmount) as HormoneNetAmount,avg(HormoneCMDDiscount)as HormoneCMDDiscount,sum(EchoCardRate) as EchoCardRate,sum(EchoCardNetAmount) as EchoCardNetAmount,avg(EchoCardCMDDiscount) as EchoCardCMDDiscount ,sum(UltraSonoRate) as UltraSonoRate,sum(UltraSonoNetAmount) as UltraSonoNetAmount,avg(UltraSonoCMDDiscount) as UltraSonoCMDDiscount,sum(EnDosRate) as EnDosRate,sum(EnDosNetAmount) as EnDosNetAmount,avg(EnDosCMDDiscount) as EnDosCMDDiscount,sum(XrayRate) as XrayRate,sum(XrayNetAmount) as XrayNetAmount,avg(XrayCMDDiscount) as XrayCMDDiscount,sum(ECGRate) as ECGRate,sum(ECGNetAmount) as ECGNetAmount,avg(ECGCMDDiscount) as ECGCMDDiscount,sum(FNARate) as FNARate,sum(FNANetAmount) as FNANetAmount,avg(FNACMDDiscount) as FNACMDDiscount,sum(HistopathologyRate) as HistopathologyRate,sum(HistopathologyNetAmount) as HistopathologyNetAmount,avg(HistopathologyCMDDiscount)as HistopathologyCMDDiscount,sum(BloodGroupRate) as BloodGroupRate,sum(BloodGroupNetAmount) as BloodGroupNetAmount,avg(BloodGroupCMDDiscount)as BloodGroupCMDDiscount,sum(PepsSemarRate) as PepsSemarRate,sum(PepsSemarNetAmount) as PepsSemarNetAmount,avg(PepsSemarCMDDiscount) as PepsSemarCMDDiscount,sum(OthersRate) as OthersRate,sum(OthersNetAmount) as OthersNetAmount,avg(OthersCMDDiscount) as OthersCMDDiscount,sum(TotalCharge) as TotalCharge,sum(DoctorDiscount) as DoctorDiscount,sum(ManualDiscount) as ManualDiscount,sum(TotalPaid) as TotalPaid,sum(Due) as Due,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from TbAllRefferWiseComissionStatement('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"') group by RefferName,MobileNo,Address order by RefferName";
				}
				else if(checkNonReferral.isSelected()){
					sql="select RefferName,MobileNo,Address,sum(PathologyRate) as PathologyRate, sum(PathologyNetAmount) as PathologyNetAmount, sum(PathologyCMDDiscount) as PathologyCMDDiscount,sum(HormoneRate) as HormoneRate,sum(HormoneNetAmount) as HormoneNetAmount,avg(HormoneCMDDiscount)as HormoneCMDDiscount,sum(EchoCardRate) as EchoCardRate,sum(EchoCardNetAmount) as EchoCardNetAmount,avg(EchoCardCMDDiscount) as EchoCardCMDDiscount ,sum(UltraSonoRate) as UltraSonoRate,sum(UltraSonoNetAmount) as UltraSonoNetAmount,avg(UltraSonoCMDDiscount) as UltraSonoCMDDiscount,sum(EnDosRate) as EnDosRate,sum(EnDosNetAmount) as EnDosNetAmount,avg(EnDosCMDDiscount) as EnDosCMDDiscount,sum(XrayRate) as XrayRate,sum(XrayNetAmount) as XrayNetAmount,avg(XrayCMDDiscount) as XrayCMDDiscount,sum(ECGRate) as ECGRate,sum(ECGNetAmount) as ECGNetAmount,avg(ECGCMDDiscount) as ECGCMDDiscount,sum(FNARate) as FNARate,sum(FNANetAmount) as FNANetAmount,avg(FNACMDDiscount) as FNACMDDiscount,sum(HistopathologyRate) as HistopathologyRate,sum(HistopathologyNetAmount) as HistopathologyNetAmount,avg(HistopathologyCMDDiscount)as HistopathologyCMDDiscount,sum(BloodGroupRate) as BloodGroupRate,sum(BloodGroupNetAmount) as BloodGroupNetAmount,avg(BloodGroupCMDDiscount)as BloodGroupCMDDiscount,sum(PepsSemarRate) as PepsSemarRate,sum(PepsSemarNetAmount) as PepsSemarNetAmount,avg(PepsSemarCMDDiscount) as PepsSemarCMDDiscount,sum(OthersRate) as OthersRate,sum(OthersNetAmount) as OthersNetAmount,avg(OthersCMDDiscount) as OthersCMDDiscount,sum(TotalCharge) as TotalCharge,sum(DoctorDiscount) as DoctorDiscount,sum(ManualDiscount) as ManualDiscount,sum(TotalPaid) as TotalPaid,sum(Due) as Due,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from TbAllRefferWiseComissionStatementForNonReferral('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"') group by RefferName,MobileNo,Address order by RefferName";
				}
				else{
					sql="select RefferName,MobileNo,Address,sum(PathologyRate) as PathologyRate, sum(PathologyNetAmount) as PathologyNetAmount, sum(PathologyCMDDiscount) as PathologyCMDDiscount,sum(HormoneRate) as HormoneRate,sum(HormoneNetAmount) as HormoneNetAmount,avg(HormoneCMDDiscount)as HormoneCMDDiscount,sum(EchoCardRate) as EchoCardRate,sum(EchoCardNetAmount) as EchoCardNetAmount,avg(EchoCardCMDDiscount) as EchoCardCMDDiscount ,sum(UltraSonoRate) as UltraSonoRate,sum(UltraSonoNetAmount) as UltraSonoNetAmount,avg(UltraSonoCMDDiscount) as UltraSonoCMDDiscount,sum(EnDosRate) as EnDosRate,sum(EnDosNetAmount) as EnDosNetAmount,avg(EnDosCMDDiscount) as EnDosCMDDiscount,sum(XrayRate) as XrayRate,sum(XrayNetAmount) as XrayNetAmount,avg(XrayCMDDiscount) as XrayCMDDiscount,sum(ECGRate) as ECGRate,sum(ECGNetAmount) as ECGNetAmount,avg(ECGCMDDiscount) as ECGCMDDiscount,sum(FNARate) as FNARate,sum(FNANetAmount) as FNANetAmount,avg(FNACMDDiscount) as FNACMDDiscount,sum(HistopathologyRate) as HistopathologyRate,sum(HistopathologyNetAmount) as HistopathologyNetAmount,avg(HistopathologyCMDDiscount)as HistopathologyCMDDiscount,sum(BloodGroupRate) as BloodGroupRate,sum(BloodGroupNetAmount) as BloodGroupNetAmount,avg(BloodGroupCMDDiscount)as BloodGroupCMDDiscount,sum(PepsSemarRate) as PepsSemarRate,sum(PepsSemarNetAmount) as PepsSemarNetAmount,avg(PepsSemarCMDDiscount) as PepsSemarCMDDiscount,sum(OthersRate) as OthersRate,sum(OthersNetAmount) as OthersNetAmount,avg(OthersCMDDiscount) as OthersCMDDiscount,sum(TotalCharge) as TotalCharge,sum(DoctorDiscount) as DoctorDiscount,sum(ManualDiscount) as ManualDiscount,sum(TotalPaid) as TotalPaid,sum(Due) as Due,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from TbAllRefferWiseComissionStatementBothReferralNonRefferal('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"') group by RefferName,MobileNo,Address order by RefferName";
				}
			}
			System.out.println(sql);
			String report="LabStatementReport/GroupWiseAllComissionreport.jrxml";
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
	private void OpenCashPaymentVoucherSummery(){
		try {
			String sql="";
			String report="AccountReport/CashPaymentVoucherSummery.jrxml";

			sql="select voucherNo,amount,PaidTo,description,(select ledgerTitle from accfledger where ledgerId=accftransection.d_l_id) as Debit,(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id) as Credit,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from accftransection where type='1' and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' order by voucherNo";
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
	private void OpenCashReceivedVoucherSummery(){
		try {
			String sql="";
			String report="AccountReport/CashReceivedVoucherSummery.jrxml";

			sql="select voucherNo,amount,PaidTo,description,(select ledgerTitle from accfledger where ledgerId=accftransection.d_l_id) as Debit,(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id) as Credit,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from accftransection where type='2' and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' order by voucherNo";
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
	private void OpenCheckMonthlyIncomeFromHospital(){
		try {
			String sql="";
			String report="AccountReport/MonthlyHospitalIncomeStatement.jrxml";

			String day=cmbMonth.getSelectedIndex()==0?"01":cmbMonth.getSelectedIndex()==1?"02":cmbMonth.getSelectedIndex()==2?"03":cmbMonth.getSelectedIndex()==3?"04":cmbMonth.getSelectedIndex()==4?"05":cmbMonth.getSelectedIndex()==5?"06":cmbMonth.getSelectedIndex()==6?"07":cmbMonth.getSelectedIndex()==7?"08":cmbMonth.getSelectedIndex()==8?"09":cmbMonth.getSelectedIndex()==9?"10":cmbMonth.getSelectedIndex()==10?"11":cmbMonth.getSelectedIndex()==11?"12":"";

			String LDate=day+"/"+cmbYear.getSelectedItem();

			int LastDate=getLastDayOfMonth(LDate);

			String endDate=cmbYear.getSelectedItem()+"-"+day+"-"+LastDate;
			sql="select *  from MonthlyIncomeForHospital('"+cmbYear.getSelectedItem()+"','"+day+"','"+endDate+"','"+cmbMonth.getSelectedItem()+"') ";
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
	private void OpenCheckMonthlyExpenseFromHospital(){
		try {
			String sql="";
			String report="AccountReport/MonthlyHospitalExpenseStatement.jrxml";

			String day=cmbMonth.getSelectedIndex()==0?"01":cmbMonth.getSelectedIndex()==1?"02":cmbMonth.getSelectedIndex()==2?"03":cmbMonth.getSelectedIndex()==3?"04":cmbMonth.getSelectedIndex()==4?"05":cmbMonth.getSelectedIndex()==5?"06":cmbMonth.getSelectedIndex()==6?"07":cmbMonth.getSelectedIndex()==7?"08":cmbMonth.getSelectedIndex()==8?"09":cmbMonth.getSelectedIndex()==9?"10":cmbMonth.getSelectedIndex()==10?"11":cmbMonth.getSelectedIndex()==11?"12":"";

			String LDate=day+"/"+cmbYear.getSelectedItem();

			int LastDate=getLastDayOfMonth(LDate);

			String endDate=cmbYear.getSelectedItem()+"-"+day+"-"+LastDate;
			sql="select *  from MonthlyExpenseForHospital('"+cmbYear.getSelectedItem()+"','"+day+"','"+endDate+"','"+cmbMonth.getSelectedItem()+"') ";
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
	private void OpenLedgerReport(){
		try {
			String report="AccountReport/LedgerReport.jrxml";

			String Type="",LedgerId="",sql="";
			ResultSet rs=db.sta.executeQuery("select Type,LedgerId from accfledger where LedgerTitle='"+cmbAllLedgerName.txtMrNo.getText().trim().toString()+"'");
			while(rs.next()){
				Type=rs.getString("Type");
				LedgerId=rs.getString("LedgerId");
			}
			if(Type.equals("1")){
				sql="select accftransection.date,((select ISNULL((select accfledger.openingBalance from accfledger where ledgerId='"+LedgerId+"' and date <'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"'),0))+(select (select ISNULL(sum(accftransection.amount),0)) from accftransection where accftransection.d_l_id='"+LedgerId+"' and date <'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' ))-(select (select ISNULL(sum(accftransection.amount),0)) from accftransection where accftransection.c_l_id='"+LedgerId+"' and date <'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"')as OB,(select ledgerTitle from accfledger where ledgerId='"+LedgerId+"')as ledger,(select ledgerTitle from accfledger where ledgerId=accftransection.d_l_id ) debitledger,(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id)as creditLedger ,(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id) as perticular,accftransection.amount,accftransection.description,(select username from tblogin where user_id=accftransection.createBy)as username from accftransection where (accftransection.d_l_id='"+LedgerId+"' or  accftransection.c_l_id='"+LedgerId+"') and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' ORDER BY transectionid";
			}
			else if(Type.equals("2")){
				sql="select accftransection.date,((select ISNULL((select accfledger.openingBalance from accfledger where ledgerId='"+LedgerId+"' and date <'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"'),0))+(select (select ISNULL(sum(accftransection.amount),0)) from accftransection where accftransection.c_l_id='"+LedgerId+"' and date <'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' ))-(select (select ISNULL(sum(accftransection.amount),0)) from accftransection where accftransection.d_l_id='"+LedgerId+"' and date <'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"')as OB,(select ledgerTitle from accfledger where ledgerId='"+LedgerId+"')as ledger,(select ledgerTitle from accfledger where ledgerId=accftransection.d_l_id ) debitledger,(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id)as creditLedger ,(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id) as perticular,accftransection.amount,accftransection.description,(select username from tblogin where user_id=accftransection.createBy)as username from accftransection where (accftransection.d_l_id='"+LedgerId+"' or  accftransection.c_l_id='"+LedgerId+"') and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' ORDER BY transectionid";
			}

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

	private void OpenAccountMonthlyIncomeExpenseSummaryDaignostic(){
		try {
			String sql="";
			String report="AccountReport/MonthlyIncomeExpenseSummeryDiagnostic.jrxml";

			sql="select * ,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate  from MonthlyDiagnosticIncomeExpenseSummery('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"') ";
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
	private void OpenCheckMonthlyExpenseFromDaignostic(){
		try {
			String sql="";
			String report="AccountReport/MonthlyDiagnosticExpenseStatement.jrxml";

			String day=cmbMonth.getSelectedIndex()==0?"01":cmbMonth.getSelectedIndex()==1?"02":cmbMonth.getSelectedIndex()==2?"03":cmbMonth.getSelectedIndex()==3?"04":cmbMonth.getSelectedIndex()==4?"05":cmbMonth.getSelectedIndex()==5?"06":cmbMonth.getSelectedIndex()==6?"07":cmbMonth.getSelectedIndex()==7?"08":cmbMonth.getSelectedIndex()==8?"09":cmbMonth.getSelectedIndex()==9?"10":cmbMonth.getSelectedIndex()==10?"11":cmbMonth.getSelectedIndex()==11?"12":"";

			String LDate=day+"/"+cmbYear.getSelectedItem();

			int LastDate=getLastDayOfMonth(LDate);

			String endDate=cmbYear.getSelectedItem()+"-"+day+"-"+LastDate;
			sql="select *  from MonthlyExpenseForDaignostic('"+cmbYear.getSelectedItem()+"','"+day+"','"+endDate+"','"+cmbMonth.getSelectedItem()+"') ";
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
	private void OpenCheckMonthlyIncomeFromDaignostic(){
		try {
			String sql="";
			String report="AccountReport/MonthlyDaignosticIncomeStatement.jrxml";
			String day=cmbMonth.getSelectedIndex()==0?"01":cmbMonth.getSelectedIndex()==1?"02":cmbMonth.getSelectedIndex()==2?"03":cmbMonth.getSelectedIndex()==3?"04":cmbMonth.getSelectedIndex()==4?"05":cmbMonth.getSelectedIndex()==5?"06":cmbMonth.getSelectedIndex()==6?"07":cmbMonth.getSelectedIndex()==7?"08":cmbMonth.getSelectedIndex()==8?"09":cmbMonth.getSelectedIndex()==9?"10":cmbMonth.getSelectedIndex()==10?"11":cmbMonth.getSelectedIndex()==11?"12":"";
			String LDate=day+"/"+cmbYear.getSelectedItem();
			int LastDate=getLastDayOfMonth(LDate);

			String endDate=cmbYear.getSelectedItem()+"-"+day+"-"+LastDate;
			sql="select *  from MonthlyIncomeForDiagnostic('"+cmbYear.getSelectedItem()+"','"+day+"','"+endDate+"','"+cmbMonth.getSelectedItem()+"') ";
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
	public void setPaidDate(){
		try {
			ArrayList labBill=new ArrayList();
			ArrayList FiscalYear=new ArrayList();
			ArrayList BillMonth=new ArrayList();
			ResultSet rs=db.sta.executeQuery("select *,TotalPayable-Paid as Due from tblabpatient where  PaidDate IS NULL and TotalPayable-Paid<=0 order by labId asc");
			while(rs.next()){
				labBill.add(rs.getString("labId"));
				FiscalYear.add(rs.getString("FiscalYear"));
				BillMonth.add(rs.getString("CMonth"));
			}	
			for(int a=0;a<labBill.size();a++){
				String date=getPaidDate(labBill.get(a).toString(),FiscalYear.get(a).toString(),BillMonth.get(a).toString());
				String updateQuery="update tblabpatient set PaidDate='"+date+"' where labId='"+labBill.get(a).toString()+"' and Cmonth='"+BillMonth.get(a).toString()+"' and FiscalYear='"+FiscalYear.get(a)+"'";
				db.sta.executeUpdate(updateQuery);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private String getPaidDate(String labBill,String FiscalYear,String BillMonth){
		String PaidDate="";
		try {
			ResultSet rs=db.sta.executeQuery("select date from TbLabPaymentHistory where BillNo='"+labBill+"' and date=(select max(date) from TbLabPaymentHistory where BillNo='"+labBill+"' and FiscalYear='"+FiscalYear+"' and  CMonth='"+BillMonth+"') and FiscalYear='"+FiscalYear+"' and CMonth='"+BillMonth+"'");
			while(rs.next())
			{
				PaidDate=new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return PaidDate;
	}
	private void OpenTestWiseTotalInvestigationRpt(){
		try {
			String sql="";
			String report="";
			if(checkTestAll.isSelected() && cmbTestName.txtMrNo.getText().trim().toString().isEmpty()){
				sql="select *,'All Test Investigation Statement' as ReportTitle,(select PatientName from TbLabPatient where labId=tblabtesthistory.labId and FiscalYear=tblabtesthistory.FiscalYear and CMonth=tblabtesthistory.CMonth) as PatientName,(select SampleCollect from TbLabPatient where labId=tblabtesthistory.labId and FiscalYear=tblabtesthistory.FiscalYear and CMonth=tblabtesthistory.CMonth) as SampleCollect,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as enddate,1 as Qty,(select (select GroupName from tblabtestgroup where SN=tbTestName.TestHeadId)  from tbTestName where TestName=tblabtesthistory.testName) as GroupHead  from tblabtesthistory where type=1 and  RefundStatus='0' and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' and labId IS NOT NULL order by GroupHead,testName";
			}
			else if(!checkTestAll.isSelected() && !cmbTestName.txtMrNo.getText().trim().toString().isEmpty()){
				sql="select *,'Test Wise Investigation Statement' as ReportTitle,(select PatientName from TbLabPatient where labId=tblabtesthistory.labId and FiscalYear=tblabtesthistory.FiscalYear) as PatientName,(select SampleCollect from TbLabPatient where labId=tblabtesthistory.labId and FiscalYear=tblabtesthistory.FiscalYear and CMonth=tblabtesthistory.CMonth) as SampleCollect,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as enddate,1 as Qty,(select (select GroupName from tblabtestgroup where SN=tbTestName.TestHeadId)  from tbTestName where TestName=tblabtesthistory.testName) as GroupHead  from tblabtesthistory where  date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' and testName='"+cmbTestName.txtMrNo.getText().trim().toString()+"' and RefundStatus='0' and labId IS NOT NULL order by GroupHead,testName";

			}
			System.out.println("sql "+sql);
			report="LabStatementReport/AllInvestigationStatement.jrxml";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, map,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void OpenTestDeapartmenttWiseTotalInvestigationRpt(){
		try {
			String sql="";
			String report="";
			if(CheckAllTestDepartment.isSelected() && cmbTestDepartmentName.txtMrNo.getText().trim().toString().isEmpty()){
				sql="select *,'All Test Investigation Statement' as ReportTitle,(select PatientName from TbLabPatient where labId=tblabtesthistory.labId and FiscalYear=tblabtesthistory.FiscalYear and CMonth=tblabtesthistory.CMonth) as PatientName,(select SampleCollect from TbLabPatient where labId=tblabtesthistory.labId and FiscalYear=tblabtesthistory.FiscalYear and CMonth=tblabtesthistory.CMonth) as SampleCollect,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as enddate,1 as Qty,(select (select GroupName from tblabtestgroup where SN=tbTestName.TestHeadId)  from tbTestName where TestName=tblabtesthistory.testName) as GroupHead  from tblabtesthistory where type=1 and RefundStatus='0' and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' and labId IS NOT NULL order by GroupHead,testName";
			}
			else if(!CheckAllTestDepartment.isSelected() && !cmbTestDepartmentName.txtMrNo.getText().trim().toString().isEmpty()){
				sql="select *,'Department Wise Investigation Statement' as ReportTitle,(select PatientName from TbLabPatient where labId=tblabtesthistory.labId and FiscalYear=tblabtesthistory.FiscalYear and CMonth=tblabtesthistory.CMonth) as PatientName,(select SampleCollect from TbLabPatient where labId=tblabtesthistory.labId and FiscalYear=tblabtesthistory.FiscalYear and CMonth=tblabtesthistory.CMonth) as SampleCollect,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as enddate,1 as Qty,(select (select GroupName from tblabtestgroup where SN=tbTestName.TestHeadId)  from tbTestName where TestName=tblabtesthistory.testName) as GroupHead  from tblabtesthistory where type=1 and  RefundStatus='0' and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' and testGroupId=(select SN from tblabtestgroup where GroupName='"+cmbTestDepartmentName.txtMrNo.getText().trim().toString()+"') and labId IS NOT NULL order by GroupHead,testName";
			}
			report="LabStatementReport/AllInvestigationStatement.jrxml";
			System.out.println("sql "+sql);
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, map,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void OpenRefferalWiseInvesgitaionTestStatusRpt(){
		try {
			String sql="";

			String Type=checkIndoorPatient.isSelected()?"Indoor":checkOutdoorPatient.isSelected()?"Outdoor":"";
			String report="";
			if(!cmbRefferName.txtMrNo.getText().trim().toString().isEmpty()){
				report="LabStatementReport/ReferralWisePatientInvestigationRegister.jrxml";
				sql="select STUFF((SELECT ', ' + CAST(testName AS VARCHAR(120)) [text()] FROM tblabtesthistory WHERE labId = b.labId and FiscalYear=b.FiscalYear and CMonth=b.CMonth order by type FOR XML PATH(''), TYPE).value('.','NVARCHAR(MAX)'),1,2,' ') AllTestList,b.labId,b.PatientName,b.RegNo,b.Cabin,b.type,(select concat(Name,Degree) from tbdoctorinfo where DoctorCode=b.RefferBy) as RefferName,RefferBy,TotalCharge,totalDiscount,Paid,TotalPayable-Paid as Due,'Indoor-Outdoor' as PatientType,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from  TbLabPatient b where b.DateTime between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' and b.RefferBy=(select DoctorCode from tbdoctorinfo where Name='"+cmbRefferName.txtMrNo.getText().trim().toString()+"')  order by b.LabId asc ";
			}
			else{
				report="LabStatementReport/PatientInvestigationRegister.jrxml";
				sql="select STUFF((SELECT ', ' + CAST(testName AS VARCHAR(120)) [text()] FROM tblabtesthistory WHERE labId = b.labId and FiscalYear=b.FiscalYear and CMonth=b.CMonth order by type FOR XML PATH(''), TYPE).value('.','NVARCHAR(MAX)'),1,2,' ') AllTestList,b.labId,b.PatientName,b.RegNo,b.Cabin,b.type,(select concat(Name,Degree) from tbdoctorinfo where DoctorCode=b.RefferBy) as RefferName,RefferBy,TotalCharge,totalDiscount,Paid,TotalPayable-Paid as Due,'"+Type+"' as PatientType,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from  TbLabPatient b where b.DateTime between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"'   order by b.RefferBy,b.LabId asc ";
			}

			System.out.println("sql "+sql);
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, map,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void OpenTotalLabIdRpt(){
		try {

			String type=checkIndoorPatient.isSelected()?"Indoor":"Outdoor";
			String sql="";
			String report="";
			String startDate=new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate());
			String endDate=new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate());

			if(checkAllPatient.isSelected()){
				if(btnDetails.isSelected()){
					sql= "select a.labId,a.PatientName,a.type,a.DateTime,a.TotalCharge,a.totalDiscount,a.TotalPayable,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where BillNo=a.labId and FiscalYear=a.FiscalYear and CMonth=a.CMonth and PaymentStatus='Paid' and PaymentType='New Collection'  and date between '"+startDate+"' and '"+endDate+"') as ActualPaid,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where BillNo=a.labId and FiscalYear=a.FiscalYear and CMonth=a.CMonth and PaymentStatus='Refund' and date between '"+startDate+"' and '"+endDate+"') as Refund,(Select [dbo].[GetRefundAmountByFromAndToDate]('"+startDate+"','"+endDate+"')) as otherRefound,(select username from tblogin where user_id=a.CreateBy) as username,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where  PaymentStatus='Paid' and paymenttype='Due Collection' and Date between '"+startDate+"' and '"+endDate+"')as totalDueRecive'"+startDate+"' as StartDate,'"+endDate+"' as EndDate from TbLabPatient a where  a.DateTime  between '"+startDate+"' and '"+endDate+"'   order by a.Type,a.CreateBy,a.labId asc";
					report="LabStatementReport/LabSaleStatement.jrxml";
				}
				else{
					sql="select DateTime,sum(TotalCharge) as TotalCharge,sum(totalDiscount) as totalDiscount,sum(TotalPayable) as TotalPayable,(Select [dbo].[GetPaidAmount](DateTime)) as Paid,(Select [dbo].[GetRefundAmount](DateTime)) as Refund,(select sum(cash) from TbLabPaymentHistory where date=TbLabPatient.DateTime and PaymentStatus='Paid') as NewCollection,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where paymenttype='Due Collection' and Date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' )as totalDueRecive, sum(TotalPayable-Paid) as NewDue,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from TbLabPatient where DateTime between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' group by DateTime order by DateTime";
					report="LabStatementReport/LabSaleStatementSummery.jrxml";
				}

			}
			else{
				if(btnDetails.isSelected()){
					sql= "select a.labId,a.PatientName,a.type,a.DateTime,a.TotalCharge,a.totalDiscount,a.TotalPayable,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where BillNo=a.labId and FiscalYear=a.FiscalYear and CMonth=a.CMonth and PaymentStatus='Paid' and PaymentType='New Collection' and date between '"+startDate+"' and '"+endDate+"') as ActualPaid,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where  BillNo=a.labId and FiscalYear=a.FiscalYear  and CMonth=a.CMonth and PaymentStatus='Refund' and date between '"+startDate+"' and '"+endDate+"') as Refund,(Select [dbo].[GetRefundAmountByFromAndToDate]('"+startDate+"','"+endDate+"')) as otherRefound,(select username from tblogin where user_id=a.CreateBy) as username,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where  PaymentStatus='Paid' and paymenttype='Due Collection' and Date between '"+startDate+"' and '"+endDate+"')as totalDueRecive,'"+startDate+"' as StartDate,'"+endDate+"' as EndDate from TbLabPatient a where  a.type='"+type+"' and a.DateTime  between '"+startDate+"' and '"+endDate+"' order by a.CreateBy,a.labId asc";
					report="LabStatementReport/LabSaleStatement.jrxml";
				}
				else{
					sql="select DateTime,sum(TotalCharge) as TotalCharge,sum(totalDiscount) as totalDiscount,sum(TotalPayable) as TotalPayable,(Select [dbo].[GetPaidAmount](DateTime)) as Paid,(Select [dbo].[GetRefundAmount](DateTime)) as Refund,(select sum(cash) from TbLabPaymentHistory where date=TbLabPatient.DateTime and PaymentStatus='Paid') as NewCollection,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where paymenttype='Due Collection' and Date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' )as totalDueRecive,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from TbLabPatient where DateTime between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' group by DateTime order by DateTime";
					report="LabStatementReport/LabSaleStatementSummery.jrxml";
				}
			}

			System.out.println(sql);

			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, map,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void OpenTotalLabIdUserWiseRpt(){
		try {
			String type=checkIndoorPatient.isSelected()?"Indoor":"Outdoor";
			String startDate=new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate());
			String endDate=new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate());
			String sql="";
			String report="";
			if(btnDetails.isSelected()){
				if(CheckAllUserName.isSelected() && cmbUserName.txtMrNo.getText().trim().toString().isEmpty()){
					if(checkAllPatient.isSelected()){
						report="LabStatementReport/LabSaleStatement.jrxml";
						sql= "select  a.labId,a.PatientName,a.type,a.DateTime,a.TotalCharge,a.totalDiscount,a.TotalPayable,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where BillNo=a.labId and FiscalYear=a.FiscalYear and CMonth=a.CMonth and PaymentStatus='Paid' and PaymentType='New Collection' and date between '"+startDate+"' and '"+endDate+"') as ActualPaid,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where BillNo=a.labId and FiscalYear=a.FiscalYear and CMonth=a.CMonth and PaymentStatus='Refund' and date between '"+startDate+"' and '"+endDate+"') as Refund,(Select [dbo].[GetRefundAmountByFromAndToDate]('"+startDate+"','"+endDate+"')) as otherRefound,(select username from tblogin where user_id=a.CreateBy) as username,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where  PaymentStatus='Paid' and paymenttype='Due Collection' and Date between '"+startDate+"' and '"+endDate+"')as totalDueRecive,'"+startDate+"' as StartDate,'"+endDate+"' as EndDate  from TbLabPatient a where  a.type='"+type+"' and a.DateTime  between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"'  order by a.CreateBy,a.labId asc";
					}
					else{
						report="LabStatementReport/LabSaleStatement.jrxml";
						sql= "select a.labId,a.PatientName,a.type,a.DateTime,a.TotalCharge,a.totalDiscount,a.TotalPayable,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where BillNo=a.labId and FiscalYear=a.FiscalYear and CMonth=a.CMonth and PaymentStatus='Paid' and PaymentType='New Collection' and date between '"+startDate+"' and '"+endDate+"') as ActualPaid,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where BillNo=a.labId and FiscalYear=a.FiscalYear and CMonth=a.CMonth and PaymentStatus='Refund' and date between '"+startDate+"' and '"+endDate+"') as Refund,(Select [dbo].[GetRefundAmountByFromAndToDate]('"+startDate+"','"+endDate+"')) as otherRefound,(select username from tblogin where user_id=a.CreateBy) as username,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where  PaymentStatus='Paid' and paymenttype='Due Collection' and Date between '"+startDate+"' and '"+endDate+"')as totalDueRecive,'"+startDate+"' as StartDate,'"+endDate+"' as EndDate  from TbLabPatient a  where  a.type='"+type+"' and a.DateTime  between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' and type='"+type+"' order by a.CreateBy,a.labId asc";
					}
				}
				else if(!cmbUserName.txtMrNo.getText().trim().toString().isEmpty()){
					if(checkAllPatient.isSelected()){
						report="LabStatementReport/LabSaleStatement.jrxml";
						sql= "select a.CreateBy,a.labId,a.PatientName,a.type,a.DateTime,a.TotalCharge,a.totalDiscount,a.TotalPayable,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where createBy=a.createBy and BillNo=a.labId and FiscalYear=a.FiscalYear and CMonth=a.CMonth and PaymentStatus='Paid' and  PaymentType='New Collection' and date between '"+startDate+"' and '"+endDate+"') as ActualPaid,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where createBy=a.createBy and BillNo=a.labId and FiscalYear=a.FiscalYear and CMonth=a.CMonth and PaymentStatus='Refund' and date between '"+startDate+"' and '"+endDate+"') as Refund,(Select [dbo].[GetRefundAmountByFromAndToDate]('"+startDate+"','"+endDate+"')) as otherRefound,(select username from tblogin where user_id=a.CreateBy) as username,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where createBy=a.createBy and  PaymentStatus='Paid' and paymenttype='Due Collection' and Date between '"+startDate+"' and '"+endDate+"')as totalDueRecive,'"+startDate+"' as StartDate,'"+endDate+"' as EndDate  from TbLabPatient a where  a.type='"+type+"' and a.DateTime  between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"'  and a.CreateBy=(select user_id from tblogin where username='"+cmbUserName.txtMrNo.getText().trim().toString()+"') order by a.CreateBy,a.labId asc";
					}
					else{
						report="LabStatementReport/LabSaleStatement.jrxml";
						sql= "select a.CreateBy,a.labId,a.PatientName,a.type,a.DateTime,a.TotalCharge,a.totalDiscount,a.TotalPayable,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where createBy=a.createBy and BillNo=a.labId and FiscalYear=a.FiscalYear and CMonth=a.CMonth and PaymentStatus='Paid' and PaymentType='New Collection' and date between '"+startDate+"' and '"+endDate+"') as ActualPaid,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where createBy=a.createBy and BillNo=a.labId and FiscalYear=a.FiscalYear and CMonth=a.CMonth and PaymentStatus='Refund' and date between '"+startDate+"' and '"+endDate+"') as Refund,(Select [dbo].[GetRefundAmountByFromAndToDate]('"+startDate+"','"+endDate+"')) as otherRefound,(select username from tblogin where user_id=a.CreateBy) as username,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where createBy=a.createBy and  PaymentStatus='Paid' and paymenttype='Due Collection' and Date between '"+startDate+"' and '"+endDate+"')as totalDueRecive,'"+startDate+"' as StartDate,'"+endDate+"' as EndDate  from TbLabPatient a where  a.type='"+type+"' and a.DateTime  between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' and a.CreateBy=(select user_id from tblogin where username='"+cmbUserName.txtMrNo.getText().trim().toString()+"') and a.type='"+type+"' order by a.CreateBy,a.labId asc";
					}
				}
			}
			else if(btnSummery.isSelected()){
				if(CheckAllUserName.isSelected() && cmbUserName.txtMrNo.getText().trim().toString().isEmpty()){
					if(checkAllPatient.isSelected()){
						report="LabStatementReport/LabSaleStatementSummaryUserWiseSummert.jrxml";
						sql= "select a.CreateBy,a.type,a.DateTime,sum(a.TotalCharge) as TotalCharge,sum(a.totalDiscount) as totalDiscount,sum(a.TotalPayable) as TotalPayable,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where createBy=a.createBy and date=a.datetime  and FiscalYear=a.FiscalYear and CMonth=a.CMonth and PaymentStatus='Paid' and PaymentType='New Collection' ) as ActualPaid,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where createBy=a.createBy and FiscalYear=a.FiscalYear and CMonth=a.CMonth and PaymentStatus='Refund' ) as Refund,(Select [dbo].[GetRefundAmountByFromAndToDate]('"+startDate+"','"+endDate+"')) as otherRefound,(select username from tblogin where user_id=a.CreateBy) as username,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where   PaymentStatus='Paid' and paymenttype='Due Collection' and date between '"+startDate+"' and '"+endDate+"' )as totalDueRecive,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where   PaymentStatus='Paid'  and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' )as TotalReceive,'"+startDate+"' as StartDate,'"+endDate+"' as EndDate  from TbLabPatient a where  a.type='"+type+"' and a.DateTime  between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"'  order by group by a.CreateBy,a.type,a.DateTime,a.FiscalYear,a.CMonth";
					}
					else{
						report="LabStatementReport/LabSaleStatementSummaryUserWiseSummert.jrxml";
						sql= "select a.CreateBy,a.type,a.DateTime,sum(a.TotalCharge) as TotalCharge,sum(a.totalDiscount) as totalDiscount,sum(a.TotalPayable) as TotalPayable,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where createBy=a.createBy  and date=a.datetime and FiscalYear=a.FiscalYear and CMonth=a.CMonth and PaymentStatus='Paid' and PaymentType='New Collection' ) as ActualPaid,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where createBy=a.createBy and FiscalYear=a.FiscalYear and CMonth=a.CMonth and PaymentStatus='Refund' ) as Refund,(Select [dbo].[GetRefundAmountByFromAndToDate]('"+startDate+"','"+endDate+"')) as otherRefound,(select username from tblogin where user_id=a.CreateBy) as username,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where   PaymentStatus='Paid' and  paymenttype='Due Collection' and date between '"+startDate+"' and '"+endDate+"' )as totalDueRecive,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where   PaymentStatus='Paid'  and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' )as TotalReceive,'"+startDate+"' as StartDate,'"+endDate+"' as EndDate  from TbLabPatient a where  a.type='"+type+"' and a.DateTime  between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"'  and a.type='"+type+"' group by a.CreateBy,a.type,a.DateTime,a.FiscalYear,a.CMonth";
					}
				}
				else if(!cmbUserName.txtMrNo.getText().trim().toString().isEmpty()){
					if(checkAllPatient.isSelected()){
						report="LabStatementReport/LabSaleStatementSummaryUserWise.jrxml";
						sql= "select a.CreateBy,a.type,a.DateTime,sum(a.TotalCharge) as TotalCharge,sum(a.totalDiscount) as totalDiscount,sum(a.TotalPayable) as TotalPayable,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where createBy=a.createBy and date=a.datetime and FiscalYear=a.FiscalYear and CMonth=a.CMonth and PaymentStatus='Paid' and PaymentType='New Collection' ) as ActualPaid,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where createBy=a.createBy and date=a.datetime and FiscalYear=a.FiscalYear and CMonth=a.CMonth and PaymentStatus='Refund' ) as Refund,(Select [dbo].[GetRefundAmountByFromAndToDate]('"+startDate+"','"+endDate+"')) as otherRefound,(select username from tblogin where user_id=a.CreateBy) as username,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where createBy=a.createBy and date=a.datetime and PaymentStatus='Paid' and paymenttype='Due Collection' )as totalDueRecive,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where   PaymentStatus='Paid'  and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' )as TotalReceive,'"+startDate+"' as StartDate,'"+endDate+"' as EndDate  from TbLabPatient a where  a.type='"+type+"' and a.DateTime  between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' and a.CreateBy=(select user_id from tblogin where username='"+cmbUserName.txtMrNo.getText().trim().toString()+"') order by group by a.CreateBy,a.type,a.DateTime,a.FiscalYear,a.CMonth";
					}
					else{
						report="LabStatementReport/LabSaleStatementSummaryUserWise.jrxml";
						sql= "select a.CreateBy,a.type,a.DateTime,sum(a.TotalCharge) as TotalCharge,sum(a.totalDiscount) as totalDiscount,sum(a.TotalPayable) as TotalPayable,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where createBy=a.createBy and date=a.datetime and FiscalYear=a.FiscalYear and CMonth=a.CMonth and PaymentStatus='Paid' and PaymentType='New Collection' ) as ActualPaid,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where createBy=a.createBy and date=a.datetime and FiscalYear=a.FiscalYear and CMonth=a.CMonth and PaymentStatus='Refund' ) as Refund,(Select [dbo].[GetRefundAmountByFromAndToDate]('"+startDate+"','"+endDate+"')) as otherRefound,(select username from tblogin where user_id=a.CreateBy) as username,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where createBy=a.createBy and date=a.datetime and PaymentStatus='Paid' and paymenttype='Due Collection' )as totalDueRecive,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where   PaymentStatus='Paid'  and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' )as TotalReceive,'"+startDate+"' as StartDate,'"+endDate+"' as EndDate  from TbLabPatient a where  a.type='"+type+"' and a.DateTime  between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' and a.CreateBy=(select user_id from tblogin where username='"+cmbUserName.txtMrNo.getText().trim().toString()+"') and a.type='"+type+"' group by a.CreateBy,a.type,a.DateTime,a.FiscalYear,a.CMonth";
					}
				}
			}

			System.out.println(sql);

			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, map,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void OpenLabIdWiseRefundStatementRpt(){
		try {
			String sql="";
			if(CheckLabIdWiseRefundStatement.isSelected() ){
				if(checkIndoorPatient.isSelected()){
					sql= "select *,'Indoor Refund Statement' as ReportTitle,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from RefundStatement('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"','Indoor') order by LabId";
				}
				else if(checkOutdoorPatient.isSelected()){
					sql= "select *,'Outdoor Refund Statement' as ReportTitle,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from RefundStatement('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"','Outdoor') order by LabId";
				}
			}

			System.out.println(sql);
			String report="LabStatementReport/RefundStatementReport.jrxml";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, map,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void OpenLabDepartmentWiseSalesSatementRpt(){
		try {

			String sql="";
			if(CheckLabDepartmentWiseSaleDueStatement.isSelected() ){
				if(checkAllPatient.isSelected()){
					sql= "select 'Indoor-Outdoor' as DepTitle,(select GroupName from tblabtestgroup where SN=a.testGroupId) as GroupName,avg(a.rate) as Rate,sum(a.qty) as Qty,sum(a.qty*a.rate) as Amount,sum(a.discount) as Discount,sum((a.qty*a.rate)-a.discount) as Total,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate, '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate  from tblabtesthistory a  join TbLabPatient b  on b.labId=a.labId and b.FiscalYear=a.FiscalYear and  b.CMonth=a.CMonth  where b.DateTime between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' and a.RefundStatus=0 and a.labId IS NOT NULL group by a.testGroupId order by a.testGroupId desc";
				}
				else if(checkIndoorPatient.isSelected()){
					sql= "select 'Indoor' as DepTitle,(select GroupName from tblabtestgroup where SN=a.testGroupId) as GroupName,avg(a.rate) as Rate,sum(a.qty) as Qty,sum(a.qty*a.rate) as Amount,sum(a.discount) as Discount,sum((a.qty*a.rate)-a.discount) as Total,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate, '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate  from tblabtesthistory a  join TbLabPatient b  on b.labId=a.labId and b.FiscalYear=a.FiscalYear and b.CMonth=a.CMonth where b.DateTime between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' and b.type='Indoor' and a.RefundStatus=0 and a.labId IS NOT NULL group by a.testGroupId order by a.testGroupId desc";
				}
				else if(checkOutdoorPatient.isSelected()){
					sql= "select 'Outdoor' as DepTitle,(select GroupName from tblabtestgroup where SN=a.testGroupId) as GroupName,avg(a.rate) as Rate,sum(a.qty) as Qty,sum(a.qty*a.rate) as Amount,sum(a.discount) as Discount,sum((a.qty*a.rate)-a.discount) as Total,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate, '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate  from tblabtesthistory a  join TbLabPatient b  on b.labId=a.labId and b.FiscalYear=a.FiscalYear and b.CMonth=a.CMonth where b.DateTime between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' and b.type='Outdoor' and a.RefundStatus=0 and a.labId IS NOT NULL group by a.testGroupId order by a.testGroupId desc";
				}

			}

			System.out.println(sql);
			String report="LabStatementReport/DepartmentWiseIndoorStatement.jrxml";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, map,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void OpenLabSaleCashRpt(){
		try {
			String type=checkIndoorPatient.isSelected()?"Indoor":"Outdoor";

			Calendar cal=Calendar.getInstance();
			cal.setTime(txtStartDate.getDate());
			cal.add(Calendar.DAY_OF_YEAR, -1);
			Date mynus2=cal.getTime();

			Calendar cal1=Calendar.getInstance();
			cal1.setTime(txtEndDate.getDate());
			cal1.add(Calendar.DAY_OF_YEAR, 0);
			Date mynus1=cal1.getTime();
			String starDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus2)+" 23:59:00.000";	
			String endDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus1)+" 23:59:00.000";


			String sql="";
			if(checkAllPatient.isSelected()){
				sql="select *from TbLabSaleCashStatement('"+starDate+"','"+endDate+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"') where  order by PatientType,UserName,DateOfBill,BillStatus";
			}
			else{
				sql="select *from TbLabSaleCashStatement('"+starDate+"','"+endDate+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"') where PatientType='"+type+"' order by UserName,DateOfBill,BillStatus";
			}

			System.out.println(sql);
			String report="LabStatementReport/LabSalesCashStatement.jrxml";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, map,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}

	private void OpenLabSaleCashSummeryRpt(){
		try {
			String type=checkIndoorPatient.isSelected()?"Indoor":checkOutdoorPatient.isSelected()?"Outdoor":"ALL";

			

			Calendar cal=Calendar.getInstance();
			cal.setTime(txtStartDate.getDate());
			cal.add(Calendar.DAY_OF_YEAR, -1);
			Date mynus2=cal.getTime();

			Calendar cal1=Calendar.getInstance();
			cal1.setTime(txtEndDate.getDate());
			cal1.add(Calendar.DAY_OF_YEAR, 0);
			Date mynus1=cal1.getTime();
			String starDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus2)+" 23:59:00.000";	
			String endDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus1)+" 23:59:00.000";	
			
			
			String sql="select UserName,sum(DueCollection) as DueCollection,sum(NewCollection) as NewCollection,sum(NewRefund) as NewRefund,sum(TotalCollection) as TotalCollection , '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from TbLabSaleCashStatementSummery('"+starDate+"','"+endDate+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+type+"') group by UserName order by UserName";
			System.out.println(sql);
			String report="LabStatementReport/LabSaleCashStatementSummery.jrxml";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, map,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}

	private void OpenLabDueReceiveStatementRpt(){
		try {
			String type=checkIndoorPatient.isSelected()?"Indoor":"Outdoor";

			Calendar cal=Calendar.getInstance();
			cal.setTime(txtStartDate.getDate());
			cal.add(Calendar.DAY_OF_YEAR, -1);
			Date mynus2=cal.getTime();

			Calendar cal1=Calendar.getInstance();
			cal1.setTime(txtEndDate.getDate());
			cal1.add(Calendar.DAY_OF_YEAR, 0);
			Date mynus1=cal1.getTime();
			String starDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus2)+" 23:59:00.000";	
			String endDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus1)+" 23:59:00.000";


			String sql="";
			if(checkAllPatient.isSelected()){
				sql="select *from TbLabSaleDueReceivedStatement('"+starDate+"','"+endDate+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"')  order by PatientType,UserName,DateOfBill,BillStatus";
			}
			else{
				sql="select *from TbLabSaleDueReceivedStatement('"+starDate+"','"+endDate+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"') where PatientType='"+type+"' order by UserName,DateOfBill,BillStatus";
			}

			System.out.println(sql);
			String report="LabStatementReport/LabDueReceiveStatement.jrxml";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, map,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}

	private void OpenLabSaleDueRpt(){
		try {
			String type=checkIndoorPatient.isSelected()?"Indoor":"Outdoor";

			Calendar cal=Calendar.getInstance();
			cal.setTime(txtStartDate.getDate());
			cal.add(Calendar.DAY_OF_YEAR, -1);
			Date mynus2=cal.getTime();

			Calendar cal1=Calendar.getInstance();
			cal1.setTime(txtEndDate.getDate());
			cal1.add(Calendar.DAY_OF_YEAR, 0);
			Date mynus1=cal1.getTime();
			String starDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus2)+" 23:59:00.000";	
			String endDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus1)+" 23:59:00.000";


			String sql="select * from TbLabSaleDueStatement('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"','"+type+"') order by DateTime,UserName";
			/*			if(checkAllPatient.isSelected()){
				sql="select * ,(select username from tblogin where user_id=TbLabPatient.CreateBy) as UserName,(select ISNULL(sum(Cash),0) from TbLabPaymentHistory where BillNo=TbLabPatient.labId and FiscalYear=TbLabPatient.FiscalYear and PaymentStatus='Paid') as ActualPaid,(select ISNULL(sum(Cash),0) from TbLabPaymentHistory where BillNo=TbLabPatient.labId and FiscalYear=TbLabPatient.FiscalYear and PaymentStatus='Refund') as Refund,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from TbLabPatient where DateTime between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' and TotalPayable-Paid>0 order by DateTime,CreateBy,labId asc";
			}
			else{
				sql="select * ,(select username from tblogin where user_id=TbLabPatient.CreateBy) as UserName,(select ISNULL(sum(Cash),0) from TbLabPaymentHistory where BillNo=TbLabPatient.labId and FiscalYear=TbLabPatient.FiscalYear and PaymentStatus='Paid') as ActualPaid,(select ISNULL(sum(Cash),0) from TbLabPaymentHistory where BillNo=TbLabPatient.labId and FiscalYear=TbLabPatient.FiscalYear and PaymentStatus='Refund') as Refund,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from TbLabPatient where type='"+type+"' and DateTime between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' and TotalPayable-Paid>0 order by DateTime,CreateBy,labId asc";
			}*/

			System.out.println(sql);
			String report="LabStatementReport/CurrentDueStatement.jrxml";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, map,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	/*	private void OpenRefferWiseComissionRpt() {
		try {
			if(!cmbRefferName.txtMrNo.getText().trim().toString().isEmpty()){
				String refferId=getRefferId();	
				String sql="select * from TbRefferWiseComissionStatement('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"','"+refferId+"')";
				System.out.println(sql);
				String report="LabStatementReport/GroupWiseComissionreport.jrxml";
				JasperDesign jd=JRXmlLoader.load(report);
				JRDesignQuery jq=new JRDesignQuery();
				System.out.println(sql);
				jq.setText(sql);
				jd.setQuery(jq);
				JasperReport jr=JasperCompileManager.compileReport(jd);
				JasperPrint jp=JasperFillManager.fillReport(jr, map,db.con);
				JasperViewer.viewReport(jp, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(map, "Error!!,"+e.getMessage());
		}
	}*/
	private String getRefferId(){
		String RefferId="";
		try {
			ResultSet rs=db.sta.executeQuery("select DoctorCode from tbdoctorinfo where Name='"+cmbRefferName.txtMrNo.getText().trim().toString()+"'");
			while(rs.next()){
				RefferId=rs.getString("DoctorCode");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return RefferId;
	}
	public void loadTestName(){
		try {
			cmbTestName.v.clear();
			cmbTestName.v.add("");
			ResultSet rs=db.sta.executeQuery("select TestName from tbTestName  order by TestName");
			while(rs.next()){
				cmbTestName.v.add(rs.getString("TestName"));
			}	
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void loadTestGroupName(){
		try {
			cmbTestDepartmentName.v.clear();
			cmbTestDepartmentName.v.add("");
			ResultSet rs=db.sta.executeQuery("select GroupName from tblabtestgroup order by GroupName");
			while(rs.next()){
				cmbTestDepartmentName.v.add(rs.getString("GroupName"));
			}	
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}

	public void background(){
		try {                
			image = ImageIO.read(new File("icon/bg.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
	}
	public void RadionActionEvent(){
		btnLogout.addMouseListener(new MouseListener() {

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
				int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Logout From System ?","Confrim......",JOptionPane.YES_NO_OPTION);
				if(confrim==JOptionPane.YES_OPTION){

					try {
						/*						String date=new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
						System.out.println("date "+date);
						String executeCmd = "mysqldump -uroot -p123cur cursorospos>"+"cursorospos"+date+".sql";
						Process runtimeProcess;
						runtimeProcess = Runtime.getRuntime().exec(new String[] { "cmd.exe", "/c", executeCmd });
						System.out.println(executeCmd);
						//			            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
						int processComplete = runtimeProcess.waitFor();
						System.out.println("processComplete"+processComplete);
						if (processComplete == 0) {
							System.out.println("Backup created successfully");

						} else {
							System.out.println("Could not create the backup");
						}*/
					} catch (Exception e) {
						// TODO: handle exception
					}
					LoginLabOne log=new LoginLabOne(sessionBeam);
					fr.dispose();
				}
			}
		});

	}
	private boolean checkAccessModule(String ModuleName){
		try {
			ResultSet rs=db.sta.executeQuery("select tblogindetails.moduleName from tblogindetails where tblogindetails.userId='"+sessionBeam.getUserId()+"'");
			while(rs.next()){
				if(rs.getString("moduleName").toString().equalsIgnoreCase(ModuleName)){
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private boolean checkAccessModuleItem(String module,String item){
		try {
			ResultSet rs=db.sta.executeQuery("select tbauthentication.Block from tbauthentication where  tbauthentication.moduleName='"+module+"' and tbauthentication.moduleItemName='"+item+"' and tbauthentication.userId='"+sessionBeam.getUserId()+"'");
			while(rs.next()){
				if(rs.getString("Block").equals("1")){
					System.out.println("Hell");
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private void loadUserName(){
		try {
			cmbUserName.v.clear();
			ResultSet rs=db.sta.executeQuery("select username from tblogin order by username ");
			while(rs.next()){
				cmbUserName.v.add(rs.getString("username"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void loadAllRefferalName(){
		try {
			cmbRefferName.v.clear();
			ResultSet rs=db.sta.executeQuery("select Name from tbdoctorinfo order by Name ");
			while(rs.next()){
				cmbRefferName.v.add(rs.getString("Name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}

	private void LoadLedgerList(){
		try {
			cmbAllLedgerName.v.clear();
			ResultSet rs=db.sta.executeQuery("select ledgerTitle from accfledger order by ledgerTitle");
			while(rs.next()){
				cmbAllLedgerName.v.add(rs.getString("ledgerTitle"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}

	private void setTestWiseDiscount(){
		try {
			ArrayList TestCodeList=new ArrayList();
			ResultSet rs=db.sta.executeQuery("select testCode from tblabtesthistory where type='1' group by testCode");
			while(rs.next()){
				TestCodeList.add(rs.getString("testCode"));
			}

			for(int a=0;a<TestCodeList.size();a++){
				double DDiscount=0;
				ResultSet rs1=db.sta.executeQuery("select Discount from tbtestname where TestCode='"+TestCodeList.get(a)+"'");
				while(rs1.next()){
					DDiscount=Double.parseDouble(rs1.getString("Discount"));
				}

				String UpdateSql="update tblabtesthistory set CmdDiscount='"+DDiscount+"' where TestCode='"+TestCodeList.get(a)+"'";
				System.out.println(UpdateSql);
				db.sta.executeUpdate(UpdateSql);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Error!!,"+e.getMessage());
		}
	}
	private void addCmp(){
		setOpaque(false);
		setLayout(new BorderLayout());
		add(panelNorth,BorderLayout.NORTH);
		panelNorth_work();
		add(panelCenter,BorderLayout.CENTER);
		panelCenter_work();
	}
	private void panelNorth_work() {

		panelNorth.setPreferredSize(new Dimension(1370, 32));
		panelNorth.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, new Color(2, 191, 185)));
		panelNorth.setBackground(Color.white);
		panelNorth.setLayout(new BorderLayout());

		panelNorth.add(panelWestNorth, BorderLayout.WEST);
		panelWestNorth.setPreferredSize(new Dimension(600, 20));
		panelWestNorth.setBackground(Color.white);

		FlowLayout flow=new FlowLayout();
		panelWestNorth.setLayout(flow);
		flow.setAlignment(flow.LEFT);

		panelWestNorth.add(lblWelcome);
		panelWestNorth.add(lblUser);
		lblUser.setText(sessionBeam.getUserName()+"    ");
		lblWelcome.setFont(new Font("arial", Font.BOLD, 13));
		lblUser.setFont(new Font("arial", Font.BOLD, 13));
		lblUser.setForeground(Color.red);
		lblUser.setPreferredSize(new Dimension(100, 20));


		if(checkAccessModule("Setting")){
			panelWestNorth.add(btnSetting);
			btnSetting.setFont(new Font("Arial", Font.BOLD, 14));
			btnSetting.setSelected(true);
			btnLabPathology.setSelected(false);
			btnAccounts.setSelected(false);
			SettingPanelWork();
		}
		if(checkAccessModule("Lab & Pathology")){
			panelWestNorth.add(btnLabPathology);
			btnLabPathology.setFont(new Font("Arial", Font.BOLD, 14));
			btnSetting.setSelected(false);
			btnLabPathology.setSelected(true);
			btnAccounts.setSelected(false);
			LabPathologyPanelWork();
		}
		if(checkAccessModule("Accounts")){
			panelWestNorth.add(btnAccounts);
			btnAccounts.setFont(new Font("Arial", Font.BOLD, 14));
			btnSetting.setSelected(false);
			btnLabPathology.setSelected(false);
			btnAccounts.setSelected(true);
			AccountsPanelWork();
		}


		gp.add(btnSetting);
		gp.add(btnLabPathology);
		gp.add(btnAccounts);

		panelNorth.add(panelCenterNorth, BorderLayout.CENTER);
		panelCenterNorth.setPreferredSize(new Dimension(300, 20));
		FlowLayout flow1=new FlowLayout();
		panelCenterNorth.setLayout(flow1);
		//flow.setAlignment(flow.LEFT);
		panelCenterNorth.setBackground(Color.white);
		//panelCenterNorth.setBorder(BorderFactory.createLineBorder(Color.red,1));

		String Value="System Developed By Cursor || www.cursorbd.com";
		try {
			ResultSet rs=db.sta.executeQuery("select EndDate,Message from TbSoftwareSecurityTime where AutoId='1' and StartDate<='"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"' and EndDate>='"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"'");
			while(rs.next()){

				Value=rs.getString("Message");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		//final RootFrame myMarquee = new RootFrame(Value);
		final JLabel textOutput = new JLabel(Value);

		final String sValue = Value;
		Timer marquee      = new Timer( 700,
				new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				char firstChar  = marqueeText.charAt( 0 );
				marqueeText = marqueeText.substring( 1, marqueeText.length() ) + firstChar;
				textOutput.setText(sValue );
			}
		} );
		panelCenterNorth.add(textOutput);
		textOutput.setFont(new Font("arial",Font.BOLD,15));
		marquee.start();



		panelNorth.add(panelEastNorth, BorderLayout.EAST);
		panelEastNorth.setPreferredSize(new Dimension(200, 20));
		panelEastNorth.setBackground(Color.white);
		panelEastNorth.add(btnLogout);
		panelEastNorth.setPreferredSize(new Dimension(100, 30));

	}
	private void DefualtSideBar(){

		JInternalFrame WestSideBar = new JInternalFrame();
		dtp.add(WestSideBar);
		WestSideBar.setTitle("Choice Your Side :: "+sessionBeam.getOrgName());
		WestSideBar.setLocation(0,0);
		WestSideBar.setSize(310,680);
		WestSideBar.setVisible(true);
		WestSideBar.setClosable(false);
		WestSideBar.add(panelWest);
		panelWest_work();
	}
	private void panelWest_work(){
		panelWest.setPreferredSize(new Dimension(310, 640));
		panelWest.setLayout(new BorderLayout());

		panelWest.add(panelWestTop,BorderLayout.NORTH);
		panelWestTop_work();

		panelWest.add(panelWestBottom,BorderLayout.SOUTH);
		panelWestBottom_work();
	}
	private void panelWestTop_work(){
		panelWestTop.setBorder(BorderFactory.createLoweredBevelBorder());
		panelWestTop.setPreferredSize(new Dimension(1360, 36));
		panelWestTop.setBackground(Color.gray);

		FlowLayout flow=new FlowLayout();
		panelWestTop.setLayout(flow);
		flow.setAlignment(flow.LEFT);

		panelWestTop.add(btnForm);
		panelWestTop.add(btnReport);




		formgp.add(btnForm);
		formgp.add(btnReport);

		btnForm.setSelected(true);
		btnForm.setFont(new Font("arial", Font.BOLD, 16));
		btnForm.setForeground(Color.white);

		btnReport.setFont(new Font("arial", Font.BOLD, 16));
		btnReport.setForeground(Color.white);
	}
	private void panelWestBottom_work(){
		panelWestBottom.setBorder(BorderFactory.createLoweredBevelBorder());
		panelWestBottom.setPreferredSize(new Dimension(1360, 595));
		panelWestBottom.setBackground(Color.lightGray);

		FlowLayout flow=new FlowLayout();
		panelWestBottom.setLayout(flow);
		flow.setAlignment(flow.LEFT);

		panelWestBottom.setLayout(flow);

		if(btnAccounts.isSelected()){
			AccountsPanelWork();
		}
		if(btnSetting.isSelected()){
			SettingPanelWork();
		}
		if(btnLabPathology.isSelected()){
			LabPathologyPanelWork();
		}
	}
	private void SettingPanelWork(){

		panelWestBottom.removeAll();
		panelWestBottom.revalidate();
		panelWestBottom.validate();
		panelWestBottom.repaint();

		if(btnForm.isSelected()){
			JLabel lblMasterSetup=new JLabel("Master Setup");
			panelWestBottom.add(lblMasterSetup);
			lblMasterSetup.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.white));
			lblMasterSetup.setBackground(Color.white);
			lblMasterSetup.setPreferredSize(new Dimension(280, 24));

			if(checkAccessModuleItem("Setting", "Seat Create")){
				panelWestBottom.add(checkSeatCreate);
				checkSeatCreate.setPreferredSize(new Dimension(260, 20));
				cGp.add(checkSeatCreate);
			}
			if(!checkAccessModuleItem("Setting", "Department Create")){
				panelWestBottom.add(checkDepartmentCreate);
				checkDepartmentCreate.setPreferredSize(new Dimension(260, 20));
				cGp.add(checkDepartmentCreate);
			}
			if(!checkAccessModuleItem("Setting", "Doctor Information Create")){
				panelWestBottom.add(checkDoctorCreate);
				checkDoctorCreate.setPreferredSize(new Dimension(260, 20));
				cGp.add(checkDoctorCreate);
			}
			if(!checkAccessModuleItem("Setting", "Management Information Create")){
				panelWestBottom.add(checkManagementCreate);
				checkManagementCreate.setPreferredSize(new Dimension(260, 20));
				cGp.add(checkManagementCreate);
			}
			if(!checkAccessModuleItem("Setting", "Corporate Information Create")){
				panelWestBottom.add(checkCorporateCreate);
				checkCorporateCreate.setPreferredSize(new Dimension(260, 20));
				cGp.add(checkCorporateCreate);
			}

			if(!checkAccessModuleItem("Setting", "Organization Information Set")){
				panelWestBottom.add(checkOrganizationInfo);
				checkOrganizationInfo.setPreferredSize(new Dimension(260, 20));
				cGp.add(checkOrganizationInfo);
			}


			JLabel lblSecurity=new JLabel("Security");
			panelWestBottom.add(lblSecurity);
			lblSecurity.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.white));
			lblSecurity.setBackground(Color.white);
			lblSecurity.setPreferredSize(new Dimension(280, 24));

			if(!checkAccessModuleItem("Setting", "User Create")){
				panelWestBottom.add(checkUserCreate);
				checkUserCreate.setPreferredSize(new Dimension(260, 20));
				cGp.add(checkUserCreate);
			}
			if(!checkAccessModuleItem("Setting", "User Authentication")){
				panelWestBottom.add(checkUserAuthentication);
				checkUserAuthentication.setPreferredSize(new Dimension(260, 20));
				cGp.add(checkUserAuthentication);
			}
			if(!checkAccessModuleItem("Setting", "Change Password")){
				panelWestBottom.add(checkChangePassword);
				checkChangePassword.setPreferredSize(new Dimension(260, 20));
				cGp.add(checkChangePassword);
			}
		}

	}

	private void LabPathologyPanelWork(){
		panelWestBottom.removeAll();
		panelWestBottom.revalidate();
		panelWestBottom.validate();
		panelWestBottom.repaint();

		if(btnForm.isSelected()){
			JLabel lblMasterSetup=new JLabel("Entry Form");
			panelWestBottom.add(lblMasterSetup);
			lblMasterSetup.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.white));
			lblMasterSetup.setBackground(Color.white);
			lblMasterSetup.setPreferredSize(new Dimension(280, 21));

			if(!checkAccessModuleItem("Lab & Pathology", "Test Create")){
				panelWestBottom.add(CheckLabTestCreate);
				CheckLabTestCreate.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabTestCreate);
			}
			if(!checkAccessModuleItem("Lab & Pathology", "Sub Test Create")){
				panelWestBottom.add(CheckLabSubTestCreate);
				CheckLabSubTestCreate.setPreferredSize(new Dimension(130, 16));
				cGp.add(CheckLabSubTestCreate);
			}
			if(!checkAccessModuleItem("Lab & Pathology", "Test Perticular Create")){
				panelWestBottom.add(CheckLabTestPerticularCreate);
				CheckLabTestPerticularCreate.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabTestPerticularCreate);
			}

			if(!checkAccessModuleItem("Lab & Pathology", "Doctor Comission Set")){
				panelWestBottom.add(CheckLabDoctorComissionSet);
				CheckLabDoctorComissionSet.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabDoctorComissionSet);
			}

			if(!checkAccessModuleItem("Lab & Pathology", "Lab Billing")){
				panelWestBottom.add(CheckLabBilling);
				CheckLabBilling.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabBilling);
			}

			if(!checkAccessModuleItem("Lab & Pathology", "Investigation Result")){
				panelWestBottom.add(CheckLabResult);
				CheckLabResult.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabResult);
			}
			if(!checkAccessModuleItem("Lab & Pathology", "Lab Incharge And Consultant Set")){
				panelWestBottom.add(CheckLabInchargeConsultantDegree);
				CheckLabInchargeConsultantDegree.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabInchargeConsultantDegree);
			}
			if(!checkAccessModuleItem("Lab & Pathology", "Note Create")){
				panelWestBottom.add(CheckLabNoteCreate);
				CheckLabNoteCreate.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabNoteCreate);
			}
		}


		if(btnReport.isSelected()){
			/*JLabel lblLabStatementReport=new JLabel("Lab & Pathology Statement Report");
			panelWestBottom.add(lblLabStatementReport);
			lblLabStatementReport.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.white));
			lblLabStatementReport.setBackground(Color.white);
			lblLabStatementReport.setPreferredSize(new Dimension(280, 21));*/

			if(!checkAccessModuleItem("Lab & Pathology", "Lab Sale Statement")){
				panelWestBottom.add(CheckLabSaleStatement);
				CheckLabSaleStatement.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabSaleStatement);
			}
			if(!checkAccessModuleItem("Lab & Pathology", "Lab Sale Statement User Wise")){
				panelWestBottom.add(CheckLabSaleStatementUserWise);
				CheckLabSaleStatementUserWise.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabSaleStatementUserWise);
			}

			if(!checkAccessModuleItem("Lab & Pathology", "Lab Sale Cash Statement")){
				panelWestBottom.add(CheckLabSaleCashStatement);
				CheckLabSaleCashStatement.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabSaleCashStatement);
			}

			if(!checkAccessModuleItem("Lab & Pathology","Lab Due Receive Statement")){
				panelWestBottom.add(CheckLabDueReceiveStatement);
				CheckLabDueReceiveStatement.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabDueReceiveStatement);

			}
			if(!checkAccessModuleItem("Lab & Pathology", "Lab Sale Due Statement")){
				panelWestBottom.add(CheckLabSaleDueStatement);
				CheckLabSaleDueStatement.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabSaleDueStatement);
			}
			if(!checkAccessModuleItem("Lab & Pathology", "Lab Id Wise Refund Statement")){
				panelWestBottom.add(CheckLabIdWiseRefundStatement);
				CheckLabIdWiseRefundStatement.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabIdWiseRefundStatement);
			}
			if(!checkAccessModuleItem("Lab & Pathology", "Department Wise Lab Sale Statement")){
				panelWestBottom.add(CheckLabDepartmentWiseSaleDueStatement);
				CheckLabDepartmentWiseSaleDueStatement.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabDepartmentWiseSaleDueStatement);
			}
			if(!checkAccessModuleItem("Lab & Pathology", "Test Wise Investigation Statement")){
				panelWestBottom.add(CheckTestInvestigationStatement);
				CheckTestInvestigationStatement.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckTestInvestigationStatement);
			}
			if(!checkAccessModuleItem("Lab & Pathology", "Test Wise Investigation Statement")){
				panelWestBottom.add(CheckTestInvestigationStatement);
				CheckTestInvestigationStatement.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckTestInvestigationStatement);
			}

			if(!checkAccessModuleItem("Lab & Pathology", "Department Wise Investigation Statement")){
				panelWestBottom.add(CheckDepartmentInvestigationStatement);
				CheckDepartmentInvestigationStatement.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckDepartmentInvestigationStatement);
			}
			if(!checkAccessModuleItem("Lab & Pathology", "Refferal Wise Investigation Test Status")){
				panelWestBottom.add(CheckRefferalWiseInvesgitaionTestStatus);
				CheckRefferalWiseInvesgitaionTestStatus.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckRefferalWiseInvesgitaionTestStatus);
			}
			if(!checkAccessModuleItem("Lab & Pathology", "Lab Id Wise Refferal Comission Statement")){
				panelWestBottom.add(CheckLabIdWiseRefferalComissionStatement);
				CheckLabIdWiseRefferalComissionStatement.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabIdWiseRefferalComissionStatement);
			}
			
			if(!checkAccessModuleItem("Lab & Pathology", "Lab Id Wise Refferal Com. For Management")){
				panelWestBottom.add(CheckLabIdWiseRefferalComissionStatementForManagement);
				CheckLabIdWiseRefferalComissionStatementForManagement.setPreferredSize(new Dimension(265, 16));
				cGp.add(CheckLabIdWiseRefferalComissionStatementForManagement);
			}

			if(!checkAccessModuleItem("Lab & Pathology", "All Refferal Comission Statement Summery")){
				panelWestBottom.add(CheckAllRefferalComissionStatementSummery);
				CheckAllRefferalComissionStatementSummery.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckAllRefferalComissionStatementSummery);
			}


			JLabel lblReportCategory=new JLabel("=============================================");
			panelWestBottom.add(lblReportCategory);
			lblReportCategory.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.white));
			lblReportCategory.setBackground(Color.white);
			lblReportCategory.setPreferredSize(new Dimension(280, 8));

			panelWestBottom.add(checkIndoorPatient);
			checkIndoorPatient.setPreferredSize(new Dimension(100, 15));
			Pgp.add(checkIndoorPatient);
			//checkIndoorPatient.setVisible(false);
			//checkIndoorPatient.setSelected(true);


			panelWestBottom.add(checkOutdoorPatient);
			checkOutdoorPatient.setPreferredSize(new Dimension(100, 15));
			Pgp.add(checkOutdoorPatient);
			checkOutdoorPatient.setSelected(true);

			panelWestBottom.add(checkAllPatient);
			checkAllPatient.setPreferredSize(new Dimension(50, 15));
			Pgp.add(checkAllPatient);


			JLabel lblStartDate=new JLabel("From");
			panelWestBottom.add(lblStartDate);
			lblStartDate.setPreferredSize(new Dimension(35, 25));

			panelWestBottom.add(txtStartDate);
			txtStartDate.setPreferredSize(new Dimension(140, 25));
			txtStartDate.setDateFormatString("yyyy-MM-dd");
			txtStartDate.setDate(new Date());

			panelWestBottom.add(btnSummery);
			//btnSummery.setPreferredSize(new Dimension(170, 28));
			Lgp.add(btnSummery);
			btnSummery.setSelected(true);

			JLabel lblEndDate=new JLabel("To");
			lblEndDate.setPreferredSize(new Dimension(35, 25));
			panelWestBottom.add(lblEndDate);

			panelWestBottom.add(txtEndDate);
			txtEndDate.setPreferredSize(new Dimension(140, 25));
			txtEndDate.setDateFormatString("yyyy-MM-dd");
			txtEndDate.setDate(new Date());
			//txtEndDate.setEnabled(false);

			panelWestBottom.add(btnDetails);
			Lgp.add(btnDetails);


			JLabel lbl=new JLabel("     ");
			panelWestBottom.add(lbl);

			panelWestBottom.add(checkReferral);
			panelWestBottom.add(checkNonReferral);
			panelWestBottom.add(checkAll);
			rererralgp.add(checkReferral);
			checkReferral.setSelected(true);
			rererralgp.add(checkNonReferral);
			rererralgp.add(checkAll);

			JLabel lbl1=new JLabel("         ");
			panelWestBottom.add(lbl1);

			JLabel lblUser=new JLabel("Userame");
			lblUser.setPreferredSize(new Dimension(70, 28));
			panelWestBottom.add(lblUser);

			panelWestBottom.add(cmbUserName.combo);
			cmbUserName.combo.setPreferredSize(new Dimension(170, 28));
			cmbUserName.combo.setEnabled(false);

			panelWestBottom.add(CheckAllUserName);
			CheckAllUserName.setPreferredSize(new Dimension(35, 28));
			CheckAllUserName.setSelected(true);


			JLabel lblTestName=new JLabel("Test Name");
			lblTestName.setPreferredSize(new Dimension(70, 28));
			panelWestBottom.add(lblTestName);

			panelWestBottom.add(cmbTestName.combo);
			cmbTestName.combo.setPreferredSize(new Dimension(170, 28));
			cmbTestName.combo.setEnabled(false);

			panelWestBottom.add(checkTestAll);
			checkTestAll.setPreferredSize(new Dimension(35, 28));
			checkTestAll.setSelected(true);

			JLabel lblTestDepartment=new JLabel("Department");
			lblTestDepartment.setPreferredSize(new Dimension(70, 28));
			panelWestBottom.add(lblTestDepartment);

			panelWestBottom.add(cmbTestDepartmentName.combo);
			cmbTestDepartmentName.combo.setPreferredSize(new Dimension(170, 28));
			cmbTestDepartmentName.combo.setEnabled(false);

			panelWestBottom.add(CheckAllTestDepartment);
			CheckAllTestDepartment.setPreferredSize(new Dimension(35, 28));
			CheckAllTestDepartment.setSelected(true);

			JLabel lblRefferName=new JLabel("Reffer Name");
			lblRefferName.setPreferredSize(new Dimension(70, 28));
			panelWestBottom.add(lblRefferName);

			panelWestBottom.add(cmbRefferName.combo);
			cmbRefferName.combo.setPreferredSize(new Dimension(170, 28));
			cmbRefferName.combo.setEnabled(false);

			panelWestBottom.add(checkRefferAll);
			checkRefferAll.setPreferredSize(new Dimension(35, 28));
			checkRefferAll.setSelected(true);

			panelWestBottom.add(new JLabel("                        "));
			panelWestBottom.add(btnPreview);
			btnPreview.setPreferredSize(new Dimension(100, 34));
		}

	}

	private void AccountsPanelWork(){
		System.out.println("Hi");
		panelWestBottom.removeAll();
		panelWestBottom.revalidate();
		panelWestBottom.validate();
		panelWestBottom.repaint();

		if(btnForm.isSelected()){
			JLabel lblMasterSetup=new JLabel("Setup");
			panelWestBottom.add(lblMasterSetup);
			lblMasterSetup.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.white));
			lblMasterSetup.setBackground(Color.white);
			lblMasterSetup.setPreferredSize(new Dimension(280, 16));


			if(!checkAccessModuleItem("Accounts", "Account Head & Ledger Create")){
				panelWestBottom.add(CheckAccountGroupAndLedgerCreate);
				CheckAccountGroupAndLedgerCreate.setPreferredSize(new Dimension(260, 17));
				cGp.add(CheckAccountGroupAndLedgerCreate);
			}



			JLabel lblTransectioni=new JLabel("Transection");
			panelWestBottom.add(lblTransectioni);
			lblTransectioni.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.white));
			lblTransectioni.setBackground(Color.white);
			lblTransectioni.setPreferredSize(new Dimension(280, 16));

			if(!checkAccessModuleItem("Accounts", "Cash Payment Voucher")){
				panelWestBottom.add(CheckAccountCashPaymentVoucher);
				CheckAccountCashPaymentVoucher.setPreferredSize(new Dimension(260, 17));
				cGp.add(CheckAccountCashPaymentVoucher);
			}

			if(!checkAccessModuleItem("Accounts", "Bank Payment Payment")){
				panelWestBottom.add(CheckAccountBankPaymentVoucher);
				CheckAccountBankPaymentVoucher.setPreferredSize(new Dimension(260, 17));
				cGp.add(CheckAccountBankPaymentVoucher);
			}

			if(!checkAccessModuleItem("Accounts", "Cash Receive Payment")){
				panelWestBottom.add(CheckAccountCashReceiveVoucher);
				CheckAccountCashReceiveVoucher.setPreferredSize(new Dimension(260, 17));
				cGp.add(CheckAccountCashReceiveVoucher);

			}

			if(!checkAccessModuleItem("Accounts", "Bank Payment Payment")){
				panelWestBottom.add(CheckAccountBankReceiveVoucher);
				CheckAccountBankReceiveVoucher.setPreferredSize(new Dimension(260, 17));
				cGp.add(CheckAccountBankReceiveVoucher);

			}
		}


		if(btnReport.isSelected()){
			JLabel lblReport=new JLabel("Report");
			panelWestBottom.add(lblReport);
			lblReport.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.white));
			lblReport.setBackground(Color.white);
			lblReport.setPreferredSize(new Dimension(280, 16));

			if(!checkAccessModuleItem("Accounts", "Report By Ledger")){
				panelWestBottom.add(CheckAccountReportByLedger);
				CheckAccountReportByLedger.setPreferredSize(new Dimension(260, 17));
				cGp.add(CheckAccountReportByLedger);

			}

			if(!checkAccessModuleItem("Accounts", "Cash Payment Voucher Summery")){
				panelWestBottom.add(CheckAccountCashPaymentVoucherSummery);
				CheckAccountCashPaymentVoucherSummery.setPreferredSize(new Dimension(260, 17));
				cGp.add(CheckAccountCashPaymentVoucherSummery);
			}

			if(!checkAccessModuleItem("Accounts", "Cash Received Voucher Summery")){
				panelWestBottom.add(CheckAccountCashReceivedVoucherSummery);
				CheckAccountCashReceivedVoucherSummery.setPreferredSize(new Dimension(260, 17));
				cGp.add(CheckAccountCashReceivedVoucherSummery);
			}

			if(!checkAccessModuleItem("Accounts", "Daily Expenditure For Daignostic")){
				panelWestBottom.add(CheckMonthlyExpenditureForDiagnostic);
				CheckMonthlyExpenditureForDiagnostic.setPreferredSize(new Dimension(260, 17));
				cGp.add(CheckMonthlyExpenditureForDiagnostic);
			}

			if(!checkAccessModuleItem("Accounts", "Monthly Income From Daignostic")){
				panelWestBottom.add(CheckMonthlyIncomeFromDiagnostic);
				CheckMonthlyIncomeFromDiagnostic.setPreferredSize(new Dimension(260, 17));
				cGp.add(CheckMonthlyIncomeFromDiagnostic);
			}

			if(!checkAccessModuleItem("Accounts", "Monthly Income And Expense Summery For Daignostic")){
				panelWestBottom.add(CheckAccountMonthlyIncomeExpenseSummaryDaignostic);
				CheckAccountMonthlyIncomeExpenseSummaryDaignostic.setPreferredSize(new Dimension(275, 17));
				cGp.add(CheckAccountMonthlyIncomeExpenseSummaryDaignostic);
			}

			if(!checkAccessModuleItem("Accounts", "Income Statement")){
				panelWestBottom.add(CheckAccountIncomeStatement);
				CheckAccountIncomeStatement.setPreferredSize(new Dimension(260, 17));
				cGp.add(CheckAccountIncomeStatement);
			}
			if(!checkAccessModuleItem("Accounts", "Profit And Loss")){
				panelWestBottom.add(CheckAccountProfitAndLoss);
				CheckAccountProfitAndLoss.setPreferredSize(new Dimension(260, 17));
				cGp.add(CheckAccountProfitAndLoss);
			}

			if(!checkAccessModuleItem("Accounts", "Trial Balance")){
				panelWestBottom.add(CheckAccountTrialBalance);
				CheckAccountTrialBalance.setPreferredSize(new Dimension(260, 17));
				cGp.add(CheckAccountTrialBalance);
			}

			if(!checkAccessModuleItem("Accounts", "Balance Sheet")){
				panelWestBottom.add(CheckAccountBalanceSheet);
				CheckAccountBalanceSheet.setPreferredSize(new Dimension(260, 17));
				cGp.add(CheckAccountBalanceSheet);
			}


			JLabel lblReport1=new JLabel("================================");
			panelWestBottom.add(lblReport1);
			lblReport1.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.white));
			lblReport1.setBackground(Color.white);
			lblReport1.setPreferredSize(new Dimension(280, 16));

			JLabel lblStartDate=new JLabel("From");
			panelWestBottom.add(lblStartDate);
			lblStartDate.setPreferredSize(new Dimension(40, 25));

			panelWestBottom.add(txtStartDate);
			txtStartDate.setPreferredSize(new Dimension(140, 25));
			txtStartDate.setDateFormatString("yyyy-MM-dd");
			txtStartDate.setDate(new Date());

			panelWestBottom.add(btnSummery);
			//btnSummery.setPreferredSize(new Dimension(170, 28));
			Lgp.add(btnSummery);
			btnSummery.setSelected(true);

			JLabel lblEndDate=new JLabel("To");
			lblEndDate.setPreferredSize(new Dimension(40, 25));
			panelWestBottom.add(lblEndDate);

			panelWestBottom.add(txtEndDate);
			txtEndDate.setPreferredSize(new Dimension(140, 25));
			txtEndDate.setDateFormatString("yyyy-MM-dd");
			txtEndDate.setDate(new Date());
			txtEndDate.setEnabled(true);

			panelWestBottom.add(btnDetails);
			Lgp.add(btnDetails);


			JLabel lblYear=new JLabel("Year");
			lblYear.setPreferredSize(new Dimension(45, 29));
			panelWestBottom.add(lblYear);

			panelWestBottom.add(cmbYear);
			cmbYear.setPreferredSize(new Dimension(90, 29));

			JLabel lblMonth=new JLabel("Month");
			lblMonth.setPreferredSize(new Dimension(40, 29));
			panelWestBottom.add(lblMonth);

			panelWestBottom.add(cmbMonth);
			cmbMonth.setPreferredSize(new Dimension(95, 29));

			JLabel lblLedgerName=new JLabel("Ledger");
			panelWestBottom.add(lblLedgerName);
			lblLedgerName.setPreferredSize(new Dimension(40, 25));

			panelWestBottom.add(cmbAllLedgerName.combo);
			cmbAllLedgerName.combo.setPreferredSize(new Dimension(190, 28));

			panelWestBottom.add(CheckAllLedgerList);
			CheckAllLedgerList.setPreferredSize(new Dimension(35, 16));

			panelWestBottom.add(new JLabel("               "));
			panelWestBottom.add(btnPreview);
			btnPreview.setPreferredSize(new Dimension(100, 34));
			btnPreview.setMnemonic(KeyEvent.VK_P);
		}



	}

	private void panelCenter_work() {
		panelCenter.setPreferredSize(new Dimension(1100, 640));
		panelCenter.setBackground(Color.white);
		//panelCenter.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 0, new Color(2, 191, 185)));
		//panelCenter.setLayout(new GridBagLayout());
		panelCenter.setLayout(new BorderLayout());
		panelCenter.add(dtp);
		DefualtSideBar();
	}

}
