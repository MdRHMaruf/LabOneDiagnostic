package com.ShareClass;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SerialKeyEntryForm extends JDialog{

	JButton btnVerify = new JButton("Verify");	
	JButton btnCancel = new JButton("Cancel");
	
	JLabel lbl = new JLabel("Serial Key");
	JTextField txtSerialKey = new JTextField(15);
	
	JPanel mainPanel = new JPanel();
	
	
	db_coonection db = new db_coonection();
	

	
	public SerialKeyEntryForm() {
		// TODO Auto-generated method stub
		this.setModal(true);
		this.setSize(300, 150);
		this.setLocationRelativeTo(null);
		this.setTitle("Serial Key Entry Form");
		db.conect();
		addCmp();
		setCmpFocusAction();
		setCmpAction();
	}

	private void setCmpAction() {
		// TODO Auto-generated method stub
		btnVerify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnVerifyAction();
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnCancelAction();
				
			}

			
		});
	}

	private void btnVerifyAction(){
		Date currentDate = null;
		String serialKey = getTxtSerialKey();
		String generateSerialKey="";
		String d1="",d2="",m1="",m2="",y3="",y4="";
		try{
			if(!serialKey.isEmpty()){
				if(serialKey.length() == 12){
					
					
					ResultSet rs = db.sta.executeQuery("select getDate() as currentDate");
					if(rs.next()){
						currentDate = rs.getDate("currentDate");
					}
					
					int month = currentDate.getMonth()+1;
					System.out.println("Month = "+ month);
					switch(month){
					case 1:
					{
						d1 = serialKey.substring(3,4);
						d2 = serialKey.substring(5,6);
						
						m1 = serialKey.substring(1,2);
						m2 = serialKey.substring(2,3);
						
						y3 = serialKey.substring(8,9);
						y4 = serialKey.substring(10,11);
						
						generateSerialKey = "51358"+d1+"508"+d2+"793459\n"+
											"51354850"+m2+"145"+m1+"459\n"+
											"5135"+y4+"8"+y3+"038799459";
						break;
					}
					case 2:
					{
					
						d1 = serialKey.substring(1,2);
						d2 = serialKey.substring(3,4);
						
						m1 = serialKey.substring(2,3);
						m2 = serialKey.substring(4,5);
						
						y3 = serialKey.substring(6,7);
						y4 = serialKey.substring(9,10);
						generateSerialKey = "51358"+d1+"508"+d2+"793293\n"+
											"40354850"+m2+"679"+m1+"459\n"+
											"5935"+y4+"8"+y3+"038728459";
						break;
					}
					case 3:
					{
						d1 = serialKey.substring(2,3);
						d2 = serialKey.substring(4,5);
						
						m1 = serialKey.substring(3,4);
						m2 = serialKey.substring(6,7);
						
						y3 = serialKey.substring(7,8);
						y4 = serialKey.substring(10,11);
						
						generateSerialKey = "29358"+d1+"508"+d2+"243293\n"+
								"40894850"+m2+"290"+m1+"909\n"+
								"4035"+y4+"8"+y3+"038972459";
						break;
					}
					case 4:
					{
						d1 = serialKey.substring(0,1);
						d2 = serialKey.substring(2,3);
						
						m1 = serialKey.substring(4,5);
						m2 = serialKey.substring(6,7);
						
						y3 = serialKey.substring(8,9);
						y4 = serialKey.substring(11);
						
						generateSerialKey = "29358"+d1+"508"+d2+"241993\n"+
								"40284850"+m2+"679"+m1+"909\n"+
								"4035"+y4+"8"+y3+"038930459";
						break;
					}
					case 5:
					{
						d1 = serialKey.substring(1,2);
						d2 = serialKey.substring(11);
						
						m1 = serialKey.substring(5,6);
						m2 = serialKey.substring(3,4);
						
						y3 = serialKey.substring(7,8);
						y4 = serialKey.substring(9,10);
						
						generateSerialKey = "40358"+d1+"508"+d2+"243093\n"+
								"95284850"+m2+"589"+m1+"909\n"+
								"2839"+y4+"8"+y3+"038830459";
						break;
					}
					case 6:
					{
						d1 = serialKey.substring(3,4);
						d2 = serialKey.substring(5,6);
						
						m1 = serialKey.substring(6,7);
						m2 = serialKey.substring(8,9);
						
						y3 = serialKey.substring(0,1);
						y4 = serialKey.substring(10,11);
						
						generateSerialKey = "40908"+d1+"508"+d2+"250093\n"+
								"91834850"+m2+"290"+m1+"909\n"+
								"3847"+y4+"8"+y3+"945830459";
						break;
					}
					case 7:
					{
						d1 = serialKey.substring(2,3);
						d2 = serialKey.substring(9,10);
						
						m1 = serialKey.substring(7,8);
						m2 = serialKey.substring(10,11);
						
						y3 = serialKey.substring(0,1);
						y4 = serialKey.substring(4,5);
						
						generateSerialKey = "40908"+d1+"508"+d2+"250093\n"+
								"91834850"+m2+"290"+m1+"909\n"+
								"3847"+y4+"8"+y3+"945830459";
						break;
					}
					case 8:
					{
						d1 = serialKey.substring(3,4);
						d2 = serialKey.substring(4,5);
						
						m1 = serialKey.substring(8,9);
						m2 = serialKey.substring(5,6);
						
						y3 = serialKey.substring(7,8);
						y4 = serialKey.substring(10,11);
						
						generateSerialKey = "80908"+d1+"508"+d2+"250046\n"+
								"95034850"+m2+"678"+m1+"909\n"+
								"2367"+y4+"8"+y3+"945830459";
						break;
					}
					case 9:
					{
						d1 = serialKey.substring(2,3);
						d2 = serialKey.substring(7,8);
						
						m1 = serialKey.substring(9,10);
						m2 = serialKey.substring(6,7);
						
						y3 = serialKey.substring(11,12);
						y4 = serialKey.substring(1,2);
						
						generateSerialKey = "80708"+d1+"689"+d2+"239646\n"+
								"95034850"+m2+"432"+m1+"909\n"+
								"4589"+y4+"8"+y3+"942430459";
						break;
					}
					case 10:
					{
						d1 = serialKey.substring(7,8);
						d2 = serialKey.substring(8,9);
						
						m1 = serialKey.substring(10,11);
						m2 = serialKey.substring(2,3);
						
						y3 = serialKey.substring(4,5);
						y4 = serialKey.substring(1,2);
						
						generateSerialKey = "80708"+d1+"903"+d2+"287646\n"+
								"94934850"+m2+"289"+m1+"295\n"+
								"4589"+y4+"4"+y3+"994430459";
						break;
					}
					case 11:
					{
						d1 = serialKey.substring(2,3);
						d2 = serialKey.substring(4,5);
						
						m1 = serialKey.substring(11);
						m2 = serialKey.substring(1,2);
						
						y3 = serialKey.substring(6,7);
						y4 = serialKey.substring(8,9);
						
						generateSerialKey = "80708"+d1+"396"+d2+"287646\n"+
								"94404850"+m2+"123"+m1+"295\n"+
								"4299"+y4+"4"+y3+"993730459";
						break;
					}
					case 12:
					{
						d1 = serialKey.substring(5,6);
						d2 = serialKey.substring(10,11);
						
						m1 = serialKey.substring(0,1);
						m2 = serialKey.substring(11);
						
						y3 = serialKey.substring(6,7);
						y4 = serialKey.substring(8,9);
						
						generateSerialKey = "80708"+d1+"290"+d2+"287646\n"+
								"94404850"+m2+"490"+m1+"124\n"+
								"4299"+y4+"4"+y3+"993730459";
						break;
					}
					}
					
					SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
					System.out.println("20"+y3+y4+"-"+m1+m2+"-"+d1+d2);
					Date securityDate = sdformat.parse("20"+y3+y4+"-"+m1+m2+"-"+d1+d2);
	
					int currentYear = currentDate.getYear();
					int securityYear = securityDate.getYear();
					System.out.println("Current Year="+currentYear);
					System.out.println("sucurity Year="+securityYear);
					
					if(securityYear-currentYear <= 1 && securityYear-currentYear >= 0){
						if(currentDate.compareTo(securityDate) <= 0){
							File file=new File("security/security.txt");
							
							if(!file.exists()){
								PrintWriter writer = new PrintWriter("security/security.txt", "UTF-8");
							}
								
							
							FileWriter fileWriter = new FileWriter(file);
							fileWriter.write(generateSerialKey);
							fileWriter.close();
							
							this.dispose();
						}else{
							JOptionPane.showMessageDialog(null,"Invalid Serial Key..","Please Enter A Valid Serial Key...",JOptionPane.INFORMATION_MESSAGE);
							txtSerialKey.requestFocus();
						}
					}else{
						JOptionPane.showMessageDialog(null,"Invalid Serial Key..","Please Enter A Valid Serial Key...",JOptionPane.INFORMATION_MESSAGE);
						txtSerialKey.requestFocus();
					}
					
					
					
				}else{
					JOptionPane.showMessageDialog(null,"Invalid Serial Key..","Please Enter A Valid Serial Key...",JOptionPane.INFORMATION_MESSAGE);
					txtSerialKey.requestFocus();
				}
			}else{
				JOptionPane.showMessageDialog(null,"Empty Serial Key..","Please Enter A New Serial Key...",JOptionPane.INFORMATION_MESSAGE);
				txtSerialKey.requestFocus();
			}
		}catch(Exception a){
			a.printStackTrace();
		}
		
	}

	private void btnCancelAction() {
		// TODO Auto-generated method stub
		this.dispose();
	}

	private void addCmp() {
		// TODO Auto-generated method stub
		

		mainPanel.setLayout(null);
		
		lbl.setBounds(10, 10, 150, 25);
		txtSerialKey.setBounds(10,40,250,28);
		
		btnVerify.setBounds(35,80,100,28);
		btnCancel.setBounds(140,80,100,28);
		
		mainPanel.add(lbl);
		mainPanel.add(txtSerialKey);
		mainPanel.add(btnVerify);
		mainPanel.add(btnCancel);
		
		this.add(mainPanel);
	}

	private void setCmpFocusAction() {
		// TODO Auto-generated method stub
		txtSerialKey.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				txtSerialKey.selectAll();
			}
		});

	}
	
	
	public String getTxtSerialKey() {
		return txtSerialKey.getText().trim();
	}


	public void setTxtSerialKey(String txtSerialKey) {
		this.txtSerialKey.setText(txtSerialKey);
	}
	
	

}
