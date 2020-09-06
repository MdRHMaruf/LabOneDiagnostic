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
import java.awt.Insets;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.ShareClass.FocusMoveByEnter;
import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
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

public class CorporateinfoCreate extends JPanel{
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

	JLabel lblSearchName=new JLabel("<html><font color=red>*</font>Name</html>");


	JLabel lblSl=new JLabel("<html><font color=red>*</font>SL</html>");
	JLabel lblName=new JLabel("<html><font color=red>*</font>Name</html>");
	JLabel lblDesignation=new JLabel("Designation");
	JLabel lblReligion=new JLabel("Religion");
	JLabel lblGender=new JLabel("Gender");
	JLabel lblNationality=new JLabel("Nationality");
	JLabel lblNationalId=new JLabel("National ID");
	JLabel lblPresentAddress=new JLabel("Present Address");
	JLabel lblEmail=new JLabel("Email");
	JLabel lblTelephone=new JLabel("Telphone");
	JLabel lblMobile=new JLabel("Mobile");
	JLabel lblParmanentAddress=new JLabel("Parmanent Address");
	JTextField txtSl=new JTextField(20);
	public JTextField txtName=new JTextField(20);
	public JTextField txtDesignation=new JTextField(20);

	String religion[]={"","Islam","Hindo","Boddha","Christian"};
	JComboBox cmbReligion=new JComboBox(religion);

	String gender[]={"","Male","Female"};
	JComboBox cmbgender=new JComboBox(gender);

	JTextField txtNationality=new JTextField(20);
	JTextField txtNationalId=new JTextField(20);
	JTextField txtEmail=new JTextField(20);
	JTextField txtMobile=new JTextField(20);
	JTextField txtTelephone=new JTextField(20);

	SuggestText cmbSearchName=new SuggestText();

	JTextArea txtPresentAddress=new JTextArea(3,2);
	JTextArea txtParmanentAddress=new JTextArea(3,2);


	JScrollPane txtParmanentscroll=new JScrollPane(txtParmanentAddress,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	JScrollPane txtPresentscroll=new JScrollPane(txtPresentAddress,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	String cardType[]={};
	JComboBox cmbCarType=new JComboBox(cardType);
	JDateChooser txtDate=new JDateChooser();
	JCheckBox checkAsc=new JCheckBox("Assecding");
	JCheckBox checkDsc=new JCheckBox("Dessecding");
	ButtonGroup gp=new ButtonGroup();
	String col[]={"   SL#","         Name","           Designation","          Mobile No","              Address"};
	Object row[][]={};
	DefaultTableModel model=new DefaultTableModel(row,col);
	JTable table=new JTable(model){
		@Override
		public boolean isCellEditable(int row,int col){
			col=convertColumnIndexToModel(col);
			row=convertRowIndexToModel(row);
			if(col==6){
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
	JButton btnPrint=new JButton("Print Corporate List",new ImageIcon("icon/print.png"));
	JButton btnSave=new JButton("Save",new ImageIcon("icon/save.png"));
	JButton btnEdit=new JButton("Edit",new ImageIcon("icon/edt.png"));
	JButton btnDelete=new JButton("Delete",new ImageIcon("icon/delete.png"));
	JButton btnReset=new JButton("Reset",new ImageIcon("icon/reresh.png"));
	String startDate="",ledgerName="",Sl="";
	int ledgerId=0;
	BufferedImage image;
	public CorporateinfoCreate(SessionBeam sessionbeam) {
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
		loadCorporateName();
		background();
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
					ResultSet rs=db.sta.executeQuery("select *from tbCorporateinfo where iSl='"+table.getValueAt(table.getSelectedRow(), 0)+"'");
					while(rs.next()){
						txtSl.setText(rs.getString("iSl"));
						txtName.setText(rs.getString("vName"));
						txtDesignation.setText(rs.getString("vDesignation"));
						cmbReligion.setSelectedItem(rs.getString("vReglion"));
						cmbgender.setSelectedItem(rs.getString("vGender"));
						txtNationality.setText(rs.getString("vNationality"));
						txtNationalId.setText(rs.getString("vNationalId"));
						txtPresentAddress.setText(rs.getString("tPresentAddress"));
						txtParmanentAddress.setText(rs.getString("tParmanentAddress"));
						txtEmail.setText(rs.getString("vEmail"));
						txtTelephone.setText(rs.getString("vTelePhone"));
						txtMobile.setText(rs.getString("vMobile"));
						ledgerName=rs.getString("vName");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
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
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteBtnEvent();
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
						ResultSet rs=db.sta.executeQuery("select *from tbCorporateinfo order by tbCorporateinfo.iSl,tbCorporateinfo.vName asc");
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
						ResultSet rs=db.sta.executeQuery("select *from tbCorporateinfo order by tbCorporateinfo.iSl desc");
						while(rs.next()){
							model.addRow(new Object[]{rs.getString("iSl"),rs.getString("vName"),rs.getString("vDesignation"),rs.getString("vmobile"),rs.getString("tPresentAddress")});
						}
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
					}
					for(int a=0;a<20;a++){
						model.addRow(new Object[]{"","","",""});
					}
				}
			}
		});
		btnPrint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 allCorporateList();
			}
		});
	}
	private void btnFineEvent() {
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			if(!cmbSearchName.txtMrNo.getText().trim().toString().isEmpty()){
				ResultSet rs=db.sta.executeQuery("select *from tbCorporateinfo where vName='"+cmbSearchName.txtMrNo.getText().trim().toString()+"'");
				while(rs.next()){
					txtSl.setText(rs.getString("iSl"));
					txtName.setText(rs.getString("vName"));
					txtDesignation.setText(rs.getString("vDesignation"));
					cmbReligion.setSelectedItem(rs.getString("vReglion"));
					cmbgender.setSelectedItem(rs.getString("vGender"));
					txtNationality.setText(rs.getString("vNationality"));
					txtNationalId.setText(rs.getString("vNationalId"));
					txtPresentAddress.setText(rs.getString("tPresentAddress"));
					txtParmanentAddress.setText(rs.getString("tParmanentAddress"));
					txtEmail.setText(rs.getString("vEmail"));
					txtTelephone.setText(rs.getString("vTelePhone"));
					txtMobile.setText(rs.getString("vMobile"));
					ledgerName=rs.getString("vName");
					model.addRow(new Object[]{rs.getString("iSl"),rs.getString("vName"),rs.getString("vDesignation"),rs.getString("vMobile"),rs.getString("tPresentAddress")});
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Name");
			}
			for(int a=0;a<20;a++){
				model.addRow(new Object[]{"","","",""});
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	public void loadCorporateName(){
		try {
			cmbSearchName.v.clear();
			cmbSearchName.v.add("");
			ResultSet rs=db.sta.executeQuery("select  tbCorporateinfo.vName from tbCorporateinfo order by tbCorporateinfo.vName ");
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
			ResultSet rs=db.sta.executeQuery("select *from tbcorporateinfo ");
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
			return true;
		}
		else{
			JOptionPane.showMessageDialog(null, "Warrning!!, Please Provide Name");
		}
		return false;
	}
	private void SaveBtnEvent(){
		if(checkValidation()){
			if(!doplicateName()){
				try {
					int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Save These Information","Confrimm...",JOptionPane.YES_NO_OPTION);
					if(confrim==JOptionPane.YES_OPTION){
						autoId();
						autoLedgerId();
						String sql="insert into tbcorporateinfo values('"+txtSl.getText().trim().toString()+"','"+txtName.getText().trim().toString()+"','"+txtDesignation.getText().trim().toString()+"','"+cmbReligion.getSelectedItem().toString()+"','"+cmbgender.getSelectedItem().toString()+"','"+txtNationality.getText().trim().toString()+"','"+txtNationalId.getText().trim().toString()+"','"+txtPresentAddress.getText().trim().toString()+"','"+txtParmanentAddress.getText().trim().toString()+"','"+txtEmail.getText().trim().toString()+"','"+txtTelephone.getText().trim().toString()+"','"+txtMobile.getText().trim().toString()+"','',CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"')";
						System.out.println(sql);
						db.sta.executeUpdate(sql);
						
						String query="insert into accfledger (unitId,depId,ledgerId,ledgerTitle,pheadId,Type,openingBalance,date,remark,entryTime,createBy)values" +
								"(0,0,'"+ledgerId+"'," +
								"'"+txtName.getText().toString()+"'," +
								"'"+81+"'," +
								"'"+1+"'," +
								"'0'," +
								"CURRENT_TIMESTAMP," +
								"'"+txtName.getText().toString()+"'," +
								"CURRENT_TIMESTAMP," +
								"'"+sessionbeam.getUserId()+"')";
						System.out.println(query);
						db.sta.executeUpdate(query);
						String cLeger="update tbcorporateinfo set acc_ledger='"+ledgerId+"' where  vName='"+txtName.getText().toString()+"' ";
						System.out.println(cLeger);
						db.sta.executeUpdate(cLeger);
						
						JOptionPane.showMessageDialog(null, "Corporate Information Save Succesfully!!");
						txtClear();
						ViewTableData();
						loadCorporateName();
					}
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Doplicate Information Entry Cab't Be Possible");
			}
		}
	}

	private void EditBtnEvent(){
		if(checkValidation()){
			try {
				int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Edit These Information","Confrimm...",JOptionPane.YES_NO_OPTION);
				if(confrim==JOptionPane.YES_OPTION){
			
					String sql="update tbcorporateinfo set vName='"+txtName.getText().trim().toString()+"',vDesignation='"+txtDesignation.getText().trim().toString()+"',vReglion='"+cmbReligion.getSelectedItem().toString()+"',vGender='"+cmbgender.getSelectedItem().toString()+"',vNationality='"+txtNationality.getText().trim().toString()+"',vNationalId='"+txtNationalId.getText().trim().toString()+"',tPresentAddress='"+txtPresentAddress.getText().trim().toString()+"',tParmanentAddress='"+txtParmanentAddress.getText().trim().toString()+"',vEmail='"+txtEmail.getText().trim().toString()+"',vTelePhone='"+txtTelephone.getText().trim().toString()+"',vMobile='"+txtMobile.getText().trim().toString()+"',dEntryTime=CURRENT_TIMESTAMP,vUserId='"+sessionbeam.getUserId()+"'  where iSl='"+txtSl.getText().trim().toString()+"'";
					System.out.println(sql);
					db.sta.executeUpdate(sql);
					
					String invoice="update accfledger set ledgerTitle='"+txtName.getText().toString()+"' where unitId=0 and depId=0 and ledgerTitle='"+ledgerName+"'";
					System.out.println(invoice);
					db.sta.executeUpdate(invoice);
					
					JOptionPane.showMessageDialog(null, "Corporate Information Edit Succesfully!!");
					ViewTableData();
					txtClear();
					loadCorporateName();

				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
			}
		}
	}
	private void DeleteBtnEvent(){

		if(checkValidation()){
			if(!doplicateName()){
				try {
					int confrim=JOptionPane.showConfirmDialog(null, "Are Your Sure To Delete Corporate Information","Confrim",JOptionPane.YES_OPTION);
					if(confrim==JOptionPane.YES_OPTION){
						String sql="delete from  tbcorporateinfo where iSl='"+txtSl.getText().toString()+"'";
						System.out.println(sql);
						db.sta.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Corporate Information Delete Successfully");
						txtClear();
						autoId();
						ViewTableData();
						loadCorporateName();
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
	private boolean doplicateName(){
		/*		try {
			ResultSet rs=db.sta.executeQuery("select SupplierId from tbsupplierinfo  where SupplierId='"+txtSupplierId.getText().toString()+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error"+e);
		}*/
		return false;		
	}
	private void autoId(){
		try {
			String sql="select (ISNULL(max(iSl),0)+1)as iSl from tbcorporateinfo";
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
	private void allCorporateList(){
		try {
			HashMap map=new HashMap<>();
			map.put("orgName",sessionbeam.getOrgName());
			map.put("orgAddress",sessionbeam.getOrgAddress());
			map.put("orgMobile",sessionbeam.getOrgMobile());
			
			String report="MisReport/CorporateList.jrxml";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			String view="select *from tbcorporateinfo";
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
	private void txtClear(){
		txtName.setText("");
		txtDesignation.setText("");
		txtEmail.setText("");
		cmbReligion.setSelectedItem("");
		cmbgender.setSelectedItem("");
		txtNationality.setText("");
		txtNationalId.setText("");
		txtPresentAddress.setText("");
		txtParmanentAddress.setText("");
		txtMobile.setText("");
		txtTelephone.setText("");
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
		mainPanel.add(westPanel,BorderLayout.WEST);
		mainPanel.add(eastPanel,BorderLayout.EAST);
		mainPanel.setOpaque(false);
		eastPanel.setOpaque(false);
		westPanel_work();
		eastPanel_work();
	}
	private void westPanel_work() {
		westPanel.setLayout(new BorderLayout());
		westPanel.setPreferredSize(new Dimension(420,690));
		TitledBorder titlemain=BorderFactory.createTitledBorder("Corporate Create");
		titlemain.setTitleJustification(titlemain.CENTER);
		westPanel.setBorder(titlemain);
		westPanel.setOpaque(false);
		westPanel.add(westNorthPanel,BorderLayout.NORTH);
		westPanel.add(westCenterPanel,BorderLayout.CENTER);
		westPanel.add(westSouthPanel,BorderLayout.SOUTH);
		westNorthPanel_work();
		westCenterPanel_work();
		westSouthPanel_work();
	}

	private void westNorthPanel_work() {
		westNorthPanel.setLayout(new FlowLayout());
		westNorthPanel.setPreferredSize(new Dimension(100,60));
		westNorthPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));

		westNorthPanel.add(lblSearchName);
		lblSearchName.setFont(new Font("arial", Font.BOLD, 12));

		westNorthPanel.add(cmbSearchName.combo);
		cmbSearchName.combo.setPreferredSize(new Dimension(175, 30));

		westNorthPanel.add(btnFine);
		westNorthPanel.setOpaque(false);
		btnFine.setPreferredSize(new Dimension(85, 36));
		btnFine.setMnemonic(KeyEvent.VK_F);
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
		txtSl.setPreferredSize(new Dimension(220, 32));
		txtSl.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		txtSl.setEditable(false);
		grid.gridx=0;
		grid.gridy=1;
		westCenterPanel.add(lblName,grid);
		lblName.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=1;
		westCenterPanel.add(txtName,grid);
		txtName.setFont(new Font("arial",Font.BOLD,14));
		txtName.setPreferredSize(new Dimension(220, 32));
		txtName.requestFocus();
		txtName.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		grid.gridx=0;
		grid.gridy=2;
		westCenterPanel.add(lblDesignation,grid);
		lblDesignation.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=2;
		westCenterPanel.add(txtDesignation,grid);
		txtDesignation.setFont(new Font("arial",Font.BOLD,14));
		txtDesignation.setPreferredSize(new Dimension(220, 32));
		txtDesignation.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		grid.gridx=0;
		grid.gridy=3;
		westCenterPanel.add(lblReligion,grid);
		lblReligion.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=3;
		westCenterPanel.add(cmbReligion,grid);
		cmbReligion.setFont(new Font("arial",Font.BOLD,14));
		cmbReligion.setPreferredSize(new Dimension(220, 32));
		grid.gridx=0;
		grid.gridy=4;
		westCenterPanel.add(lblGender,grid);
		lblGender.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=4;
		westCenterPanel.add(cmbgender,grid);
		cmbgender.setFont(new Font("arial",Font.BOLD,14));
		grid.gridx=0;
		grid.gridy=5;
		westCenterPanel.add(lblNationality,grid);
		lblNationality.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=5;
		westCenterPanel.add(txtNationality,grid);
		txtNationality.setFont(new Font("arial",Font.BOLD,14));
		txtNationality.setPreferredSize(new Dimension(220, 32));
		txtNationality.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));

		grid.gridx=0;
		grid.gridy=6;
		westCenterPanel.add(lblNationalId,grid);
		lblNationalId.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=6;
		westCenterPanel.add(txtNationalId,grid);
		txtNationalId.setFont(new Font("arial",Font.BOLD,14));
		txtNationalId.setPreferredSize(new Dimension(220, 32));
		txtNationalId.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));

		grid.gridx=0;
		grid.gridy=7;
		westCenterPanel.add(lblPresentAddress,grid);
		lblPresentAddress.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=7;
		westCenterPanel.add(txtPresentscroll,grid);
		txtPresentAddress.setFont(new Font("arial",Font.BOLD,14));
		txtPresentAddress.setPreferredSize(new Dimension(220, 32));
		txtPresentAddress.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));

		grid.gridx=0;
		grid.gridy=8;
		westCenterPanel.add(lblParmanentAddress,grid);
		lblParmanentAddress.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=8;
		westCenterPanel.add(txtParmanentAddress,grid);
		txtParmanentAddress.setFont(new Font("arial",Font.BOLD,14));
		txtParmanentAddress.setPreferredSize(new Dimension(220, 32));
		txtParmanentAddress.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));

		grid.gridx=0;
		grid.gridy=9;
		westCenterPanel.add(lblEmail,grid);
		lblEmail.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=9;
		westCenterPanel.add(txtEmail,grid);
		txtEmail.setFont(new Font("arial",Font.BOLD,14));
		txtEmail.setPreferredSize(new Dimension(220, 32));
		txtEmail.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));

		grid.gridx=0;
		grid.gridy=10;
		westCenterPanel.add(lblTelephone,grid);
		lblTelephone.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=10;
		westCenterPanel.add(txtTelephone,grid);
		txtTelephone.setFont(new Font("arial",Font.BOLD,14));
		txtTelephone.setPreferredSize(new Dimension(220, 32));
		txtTelephone.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));

		grid.gridx=0;
		grid.gridy=11;
		westCenterPanel.add(lblMobile,grid);
		lblMobile.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=11;
		westCenterPanel.add(txtMobile,grid);
		txtMobile.setFont(new Font("arial",Font.BOLD,14));
		txtMobile.setPreferredSize(new Dimension(220, 32));
		txtMobile.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		final Component ob[] = {txtName,txtDesignation, cmbReligion,cmbgender,txtNationality,txtNationalId,txtPresentAddress,txtParmanentAddress,txtEmail,txtTelephone,txtMobile};	
		new FocusMoveByEnter(ob);
	}
	private void westSouthPanel_work() {


		westSouthPanel.setPreferredSize(new Dimension(100,80));
		westSouthPanel.setOpaque(false);
		FlowLayout flow=new FlowLayout();
		westSouthPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		westSouthPanel.add(btnSave);
		westSouthPanel.add(btnEdit);
		westSouthPanel.add(btnDelete);
		westSouthPanel.add(btnReset);
		btnSave.setPreferredSize(new Dimension(86,36));
		btnEdit.setPreferredSize(new Dimension(86,36));
		btnDelete.setPreferredSize(new Dimension(95,36));
		btnReset.setPreferredSize(new Dimension(95,36));
		btnSave.setMnemonic(KeyEvent.VK_S);
		btnEdit.setMnemonic(KeyEvent.VK_E);
		btnDelete.setMnemonic(KeyEvent.VK_D);
		btnReset.setMnemonic(KeyEvent.VK_R);
	}

	private void eastPanel_work() {
		eastPanel.setLayout(new FlowLayout());
		eastPanel.setPreferredSize(new Dimension(850,630));
		TitledBorder titlemain=BorderFactory.createTitledBorder("Corporate Details");
		titlemain.setTitleJustification(titlemain.CENTER);
		eastPanel.setBorder(titlemain);
		eastPanel.setOpaque(false);
		eastPanel.add(checkAsc);
		eastPanel.add(checkDsc);
		gp.add(checkAsc);
		gp.add(checkDsc);
		
		eastPanel.add(btnPrint);
		btnPrint.setPreferredSize(new Dimension(200, 35));

		eastPanel.add(scroll);
		scroll.setPreferredSize(new Dimension(845, 630));
		scroll.setOpaque(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(220);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(130);
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
