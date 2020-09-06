package com.ShareClass;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.SimpleAttributeSet;

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

public class BirthCertificateCreate extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;
	JPanel mainPanel=new JPanel();
	JPanel northPanel=new JPanel();
	JPanel centerPanel=new JPanel();
	JPanel southPanel=new JPanel();
	
	JLabel lblRegNo=new JLabel("Reg.No.");
	SuggestText cmbRegNo=new SuggestText();
	
	JButton btnFine=new JButton("Find",new ImageIcon("icon/find.png"));
	JButton btnPrint=new JButton("Print Birth Certificate",new ImageIcon("icon/print.png"));
	JButton btnSubmit=new JButton("Submit",new ImageIcon("icon/save.png"));
	
	JLabel lblNameofMrs=new JLabel("Name Of Mrs");
	JLabel lblNameofMr=new JLabel("Name Of Mr");
	JLabel lblVillage=new JLabel("Village");
	JLabel lblPs=new JLabel("P/S");
	JLabel lblThana=new JLabel("Thana");
	JLabel lblDistric=new JLabel("Distric");
	JLabel lblBirthDay=new JLabel("Birth Day");
	JLabel lblAt=new JLabel("At");
	JLabel lblAuthorizedName=new JLabel("Authorized Name");
	
	JTextField txtNameofMrs=new JTextField(16);
	JTextField txtNameofMr=new JTextField(16);
	JTextField txtVillage=new JTextField(16);
	JTextField txtPS=new JTextField(16);
	JTextField txtTime=new JTextField(16);
	JTextField txtThana=new JTextField(16);
	JTextField txtDistric=new JTextField(16);
	
	JDateChooser txtBirthDay=new JDateChooser();
	JTextField txtAt=new JTextField(16);
	
	String at[]={"","AM","PM"};
	JComboBox cmbAt=new JComboBox(at);
	
	SuggestText cmbAuthorizedName=new SuggestText();
	
	GridBagConstraints grid=new GridBagConstraints();
	BufferedImage image;
	public BirthCertificateCreate(SessionBeam sessionbeam){
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.sessionBeam=sessionbeam;
		addCmp();
		loadRegNo();
		btnActionEvent();
	}
	public void btnActionEvent(){
		btnFine.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!cmbRegNo.txtMrNo.getText().trim().toString().isEmpty()){
					try {
						if(checkBrthDayCerTFiven()){
							int temp=0;
							ResultSet rs=db.sta.executeQuery("select * from tbBrithCertificate where RegNo='"+cmbRegNo.txtMrNo.getText().trim().toString()+"'");
							while(rs.next()){
								txtNameofMrs.setText(rs.getString("NameOfMrs"));
								txtNameofMr.setText(rs.getString("NameOfMr"));
								txtPS.setText(rs.getString("txtPs"));
								txtVillage.setText(rs.getString("txtVillage"));
								txtThana.setText(rs.getString("txtThana"));
								txtDistric.setText(rs.getString("txtDistrict"));
								txtBirthDay.setDate(rs.getDate("birthDay"));
								
								cmbAt.setSelectedItem(rs.getString("at"));
								cmbAuthorizedName.txtMrNo.setText(rs.getString("authorizedName"));
								temp=1;
							}
							if(temp==1){
								txtNameofMr.setEditable(false);
								txtNameofMrs.setEditable(false);
								txtPS.setEditable(false);
								txtVillage.setEditable(false);
								txtThana.setEditable(false);
								txtDistric.setEditable(false);
							}
						}
						else{
							int temp=0;
							ResultSet rs=db.sta.executeQuery("select * from tbpatientinfo where RegNo='"+cmbRegNo.txtMrNo.getText().trim().toString()+"'");
							while(rs.next()){
								
								txtNameofMrs.setText(rs.getString("PatientName"));
								txtNameofMr.setText(rs.getString("HusbandName"));
								txtPS.setText(rs.getString("PrePost"));
								txtVillage.setText(rs.getString("PreVillage"));
								txtThana.setText(rs.getString("PreUpazila"));
								txtDistric.setText(rs.getString("PerVillage"));
								temp=1;
							}
							if(temp==1){
								txtNameofMr.setEditable(false);
								txtNameofMrs.setEditable(false);
								txtPS.setEditable(false);
								txtVillage.setEditable(false);
								txtThana.setEditable(false);
								txtDistric.setEditable(false);
							}
							
						}
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Please Provide RegNo ");
				}
			}
		});
		btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkEmptyField()){
					if(!checkBrthDayCerTFiven()){
						try {
							String sql="insert into tbBrithCertificate values ('"+cmbRegNo.txtMrNo.getText().trim().toString()+"','"+txtNameofMrs.getText().trim().toString()+"','"+txtNameofMr.getText().trim().toString()+"','"+txtPS.getText().trim().toString()+"','"+txtVillage.getText().trim().toString()+"','"+txtThana.getText().trim().toString()+"','"+txtDistric.getText().trim().toString()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtBirthDay.getDate())+"','"+txtTime.getText().trim().toString()+"','"+cmbAt.getSelectedItem().toString()+"','"+cmbAuthorizedName.txtMrNo.getText().trim().toString()+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
							System.out.println(sql);;
							db.sta.executeUpdate(sql);
							JOptionPane.showMessageDialog(null, "Birth Certificate Is Now Ready");
							txtClear();
						} catch (Exception e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Brth Certificate Already Has Been Given!! ");
					}
				}
			}
		});
		btnPrint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!cmbRegNo.txtMrNo.getText().trim().toString().isEmpty()){
					if(checkBrthDayCerTFiven()){
						viewBirthCertificate();
					}
					else{
						JOptionPane.showMessageDialog(null, "Sorry!! Birth Certificate is not ready yet!");
					}		
				}
			}
		});
	}
	private void viewBirthCertificate(){
		try {
			String report="PatientRpt/BirthCertificate.jrxml";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			String view="select * from tbBrithCertificate where RegNo='"+cmbRegNo.txtMrNo.getText().trim().toString()+"'";
			System.out.println(view);
			jq.setText(view);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void txtClear(){
		txtNameofMr.setText("");
		txtNameofMrs.setText("");
		txtPS.setText("");
		txtVillage.setText("");
		txtThana.setText("");
		txtDistric.setText("");
		txtBirthDay.setDate(null);
		cmbAt.setSelectedItem("");
		cmbAuthorizedName.txtMrNo.setText("");
	}
	public boolean checkEmptyField() {
		
		if(!txtNameofMrs.getText().trim().toString().isEmpty()){
			if(!txtNameofMr.getText().trim().toString().isEmpty()){
				if(!txtPS.getText().trim().toString().isEmpty()){
					if(!txtVillage.getText().trim().toString().isEmpty()){
						if(!txtThana.getText().trim().toString().isEmpty()){
							if(!txtDistric.getText().trim().toString().isEmpty()){
								if(txtBirthDay.getDate()!=null){
									if(!txtTime.getText().trim().toString().isEmpty()){
										if(cmbAt.getSelectedIndex()!=0){
											if(!cmbAuthorizedName.txtMrNo.getText().trim().toString().isEmpty()){
												return true;
											}
											else{
												JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Authorized Name ");
											}
										}
										else{
											JOptionPane.showMessageDialog(null, "Warning!!,Please Provide AM/PM ");
										}
									}
									else{
										JOptionPane.showMessageDialog(null, "Warnin!!,Please Provide Time");
									}
								}
								else{
									JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Birth Date ");
								}
							}
							else{
								JOptionPane.showMessageDialog(null, "Warning!!,Please Provide District ");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Thana ");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Village ");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Please Provide P/S ");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Name Of Mr ");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Name Of Mrs ");
		}
		return false;
	}
	private boolean checkBrthDayCerTFiven(){
		try {
			ResultSet rs=db.sta.executeQuery("select RegNo from tbBrithCertificate where RegNo='"+cmbRegNo.txtMrNo.getText().trim().toString()+"'");
			while(rs.next()){
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
	public void loadRegNo(){
		try {
			cmbRegNo.v.clear();
			ResultSet rs=db.sta.executeQuery("select tbpatientinfo.RegNo from tbpatientinfo");
			while(rs.next()){
				cmbRegNo.v.add(rs.getString("RegNo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	public void loadAuthorizedName(){
		try {
			cmbAuthorizedName.v.clear();
			ResultSet rs=db.sta.executeQuery("select vName from  tbmanagementinfo ");
			while(rs.next()){
				cmbAuthorizedName.v.add(rs.getString("vName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	public void addCmp(){
		setOpaque(false);
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(450, 450));
		mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(northPanel,BorderLayout.NORTH);
		northPanel.setOpaque(false);
		northPanel_work();
		mainPanel.add(centerPanel,BorderLayout.CENTER);
		centerPanel.setOpaque(false);
		centerPanel_work();
		mainPanel.add(southPanel,BorderLayout.SOUTH);
		southPanel.setOpaque(false);
		southPanel_work();
	}
	private void northPanel_work() {
		northPanel.setPreferredSize(new Dimension(450, 60));
		//northPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		northPanel.setLayout(new FlowLayout());
		
		northPanel.add(lblRegNo);
		northPanel.add(cmbRegNo.combo);
		cmbRegNo.combo.setPreferredSize(new Dimension(220, 30));
		
		northPanel.add(btnFine);
		btnFine.setPreferredSize(new Dimension(100, 36));
		btnFine.setMnemonic(KeyEvent.VK_F);
	}
	private void centerPanel_work() {
		centerPanel.setPreferredSize(new Dimension(450, 90));
		//centerPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		centerPanel.setLayout(new GridBagLayout());
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(2, 5, 2, 5);
		centerPanel.add(lblNameofMrs, grid);
		grid.gridx=1;
		grid.gridy=0;
		centerPanel.add(txtNameofMrs, grid);
		grid.gridx=0;
		grid.gridy=1;
		centerPanel.add(lblNameofMr, grid);
		grid.gridx=1;
		grid.gridy=1;
		centerPanel.add(txtNameofMr, grid);
		grid.gridx=0;
		grid.gridy=2;
		centerPanel.add(lblPs, grid);
		grid.gridx=1;
		grid.gridy=2;
		centerPanel.add(txtPS, grid);
		grid.gridx=0;
		grid.gridy=3;
		centerPanel.add(lblVillage, grid);
		grid.gridx=1;
		grid.gridy=3;
		centerPanel.add(txtVillage, grid);
		grid.gridx=0;
		grid.gridy=4;
		centerPanel.add(lblThana, grid);
		grid.gridx=1;
		grid.gridy=4;
		centerPanel.add(txtThana, grid);
		grid.gridx=0;
		grid.gridy=5;
		centerPanel.add(lblDistric, grid);
		grid.gridx=1;
		grid.gridy=5;
		centerPanel.add(txtDistric, grid);
		grid.gridx=0;
		grid.gridy=6;
		centerPanel.add(lblBirthDay, grid);
		grid.gridx=1;
		grid.gridy=6;
		centerPanel.add(txtBirthDay, grid);
		grid.gridx=0;
		grid.gridy=7;
		centerPanel.add(lblAt, grid);
		grid.gridx=1;
		grid.gridy=7;
		centerPanel.add(txtTime, grid);
		grid.gridx=2;
		grid.gridy=7;
		centerPanel.add(cmbAt, grid);
		cmbAt.setPreferredSize(new Dimension(50, 28));
		grid.gridx=0;
		grid.gridy=8;
		centerPanel.add(lblAuthorizedName, grid);
		grid.gridx=1;
		grid.gridy=8;
		centerPanel.add(cmbAuthorizedName.combo, grid);
		final Component ob[] = {txtNameofMrs,txtNameofMr, txtPS,txtVillage,txtThana,txtDistric,txtBirthDay,txtAt,cmbAuthorizedName.txtMrNo};	
		new FocusMoveByEnter(ob);
		txtNameofMr.setEditable(false);
		txtNameofMrs.setEditable(false);
		txtPS.setEditable(false);
		txtVillage.setEditable(false);
		txtThana.setEditable(false);
		txtDistric.setEditable(false);
	}
	private void southPanel_work() {
		southPanel.setPreferredSize(new Dimension(450, 90));
		//southPanel.setBorder(BorderFactory.createLineBorder(Color.green));
		southPanel.setLayout(new FlowLayout());
		southPanel.add(btnSubmit);
		btnSubmit.setPreferredSize(new Dimension(100, 36));
		btnSubmit.setMnemonic(KeyEvent.VK_S);
		southPanel.add(btnPrint);
		btnPrint.setPreferredSize(new Dimension(200, 36));
		btnPrint.setMnemonic(KeyEvent.VK_P);
	}
}
