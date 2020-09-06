package com.ShareClass;

import java.awt.BorderLayout;
import java.awt.Color;
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

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.lowagie.text.pdf.BidiOrder;

public class OrganizationInfo extends JPanel{
	db_coonection db=new db_coonection();
	GridBagConstraints gridc=new GridBagConstraints();
	
	BufferedImage image;

	private JButton btnSubmit;
	private JButton btnRefresh;
	private SessionBeam sessionBeam;
	

	private JPanel northPanel;
	private JPanel centerPanel;

	private JLabel lblOrgName;
	private JLabel lblOrgAddress;
	private JLabel lblOrgMobile;

	public JTextField txtOrgName;
	private JTextField txtOrgAddress;
	private JTextField txtOrgMobile;


	public OrganizationInfo(SessionBeam sessionBeam) {
		this.sessionBeam = sessionBeam;
		db.conect();
		init();
		cmpAdd();
		loadInfo();
		btnAction();

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

	private void loadInfo() {
		try {
			ResultSet rs = db.sta.executeQuery("select top 1 * from tbOrganizationInfo");
			if(rs.next()) {
				txtOrgName.setText(rs.getString("orgName"));
				txtOrgAddress.setText(rs.getString("orgAddress"));
				txtOrgMobile.setText(rs.getString("orgNumber"));

			}
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
	}


	private void btnAction() {
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				submitAction();

			}
		});

		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loadInfo();
			}
		});
	}


	protected void submitAction() {
		try {
			if(isHaveARow()) {
				db.sta.executeUpdate("update tbOrganizationInfo set orgName= '"+txtOrgName.getText().trim()+"',orgAddress= '"+txtOrgAddress.getText().trim()+"',orgNumber='"+txtOrgMobile.getText().trim()+"',entryTime = CURRENT_TIMESTAMP ,userID = '"+sessionBeam.getUserId()+"'");
			}else {
				db.sta.executeUpdate("insert into tbOrganizationInfo (orgName,orgAddress,orgNumber,entrytime,userID) values('"+txtOrgName.getText().trim()+"',"
						+ "'"+txtOrgAddress.getText().trim()+"',"
						+ "'"+txtOrgMobile.getText().trim()+"',"
						+ "current_timestamp,'"+sessionBeam.getUserId()+"');");
			}
			
			sessionBeam.setOrgName(txtOrgName.getText().trim());
			sessionBeam.setOrgAddress(txtOrgAddress.getText().trim());
			sessionBeam.setOrgMobile(txtOrgMobile.getText().trim());
			JOptionPane.showMessageDialog(null, "Information Update Successfully...", "Successfull",JOptionPane.INFORMATION_MESSAGE);
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}

	}


	private boolean isHaveARow() {
		try {
			ResultSet rs = db.sta.executeQuery("select top 1 * from tbOrganizationInfo");
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
		return false;
	}


	private void cmpAdd() {
		setLayout(new BorderLayout());

		add(northPanel,BorderLayout.NORTH);
		northPanelWork();
		add(centerPanel,BorderLayout.CENTER);
		centerPanelWork();
	}


	private void northPanelWork() {
		northPanel.setPreferredSize(new Dimension(0, 220));
		northPanel.setLayout(new GridBagLayout());

		gridc.insets = new Insets(5, 5, 5, 5);
		gridc.fill = new GridBagConstraints().BOTH;
		gridc.gridx = 0;
		gridc.gridy = 0;
		northPanel.add(lblOrgName,gridc);

		gridc.gridx = 0;
		gridc.gridy = 1;
		northPanel.add(lblOrgAddress,gridc);

		gridc.gridx = 0;
		gridc.gridy = 2;
		northPanel.add(lblOrgMobile,gridc);

		gridc.gridx = 1;
		gridc.gridy = 0;
		northPanel.add(txtOrgName,gridc);
		txtOrgName.setFont(new Font("arial",Font.BOLD,14));
		txtOrgName.setPreferredSize(new Dimension(120, 25));
		txtOrgName.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));

		gridc.gridx = 1;
		gridc.gridy = 1;
		northPanel.add(txtOrgAddress,gridc);
		txtOrgAddress.setFont(new Font("arial",Font.BOLD,14));
		txtOrgAddress.setPreferredSize(new Dimension(120, 25));
		txtOrgAddress.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));

		gridc.gridx = 1;
		gridc.gridy = 2;
		northPanel.add(txtOrgMobile,gridc);
		txtOrgMobile.setFont(new Font("arial",Font.BOLD,14));
		txtOrgMobile.setPreferredSize(new Dimension(120, 25));
		txtOrgMobile.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));


	}


	private void centerPanelWork() {
		centerPanel.setLayout(new FlowLayout());

		centerPanel.add(btnSubmit);
		centerPanel.add(btnRefresh);
	}


	private void init() {
		northPanel = new JPanel();
		centerPanel = new JPanel();

		lblOrgName = new JLabel("Organiztion Name");
		lblOrgAddress = new JLabel("Organization Address");
		lblOrgMobile = new JLabel("Contac No");

		btnSubmit=new JButton("Submit",new ImageIcon("icon/save.png"));
		btnRefresh=new JButton("Refresh",new ImageIcon("icon/reresh.png"));

		txtOrgName = new JTextField(30);
		txtOrgAddress = new JTextField(30);
		txtOrgMobile = new JTextField(30);

	}



}
