package com.ShareClass;

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
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.ShareClass.ButtonColumn;
import com.ShareClass.FocusMoveByEnter;
import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
import com.ShareClass.db_coonection;

public class SeatCreate extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;
	JPanel mainPanel=new JPanel();
	JPanel westPanel=new JPanel();
	JPanel westNorthPanel=new JPanel();
	JPanel westSouthPanel=new JPanel();
	JPanel eastPanel=new JPanel();
	JPanel eastNorthPanel=new JPanel();
	JPanel eastSouthPanel=new JPanel();
	
	JButton btnSaveCabin=new JButton("Add",new ImageIcon("icon/save.png"));
	JButton btnSaveBed=new JButton("Save",new ImageIcon("icon/save.png"));
	
	JLabel lblSeatType=new JLabel("Seat Type");
	JLabel lblCategoryType=new JLabel("Category Type");
	
	JLabel lblSeatName=new JLabel("Seat Name");
	JLabel lblSeatRate=new JLabel("Rate");


	JTextField txtSeatRate=new JTextField(6);
	JTextField txtSeatName=new JTextField(12);

	
	
	JLabel lblRoomNo=new JLabel("Room No");
	JTextField txtRoomNo=new JTextField(8);
	
	String categoryType[]={"","NORMAL","AC","AC (VIP)","AC(BOOC)"};
	JComboBox cmbcategoryType=new JComboBox(categoryType);
	
	String seatType[]={"","OPD","Ward","Cabin","ICU","HDU","CCU","SD","PCCU"};
	JComboBox cmbSeatType=new JComboBox(seatType);
	DecimalFormat df = new DecimalFormat("#.00");

	String ccol[]={"SL#","   Seat Name","  Seat Type ","   Seat Category","   Amount"};
	Object crow[][]={};
	DefaultTableModel cmodel=new DefaultTableModel(crow,ccol);
	JTable ctable=new JTable(cmodel){
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
	JScrollPane cscroll=new JScrollPane(ctable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	String SeatAutoId="";
	BufferedImage image;
	public SeatCreate(SessionBeam sessionBeam) {
		this.sessionBeam=sessionBeam;
		dbConnection();
		addCmp();
		btnActionEvent();
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
	public void dbConnection(){
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		loadSeatData();
	}
	public void btnActionEvent(){
		btnSaveCabin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnCabinSaveEvent();
			}
		});
	}
	private void btnCabinSaveEvent() {
		if(!txtSeatName.getText().toString().isEmpty()){
			if(cmbSeatType.getSelectedIndex()!=0){
				if(cmbSeatType.getSelectedIndex()!=0){
					if(!txtSeatRate.getText().trim().toString().isEmpty()){
						try {
							if(!checkSeatName()){
								SeatAutoId();
								String sql="insert into tbSeatCreate values('"+SeatAutoId+"','"+txtSeatName.getText().toString()+"','"+cmbSeatType.getSelectedItem().toString()+"','"+cmbcategoryType.getSelectedItem().toString()+"','"+txtSeatRate.getText().trim().toString()+"','0','0',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
								db.sta.executeUpdate(sql);
								
								JOptionPane.showMessageDialog(null, "Seat Create Sucessfully");
								SeatAutoId();
								loadSeatData();
							}
							else{
								JOptionPane.showMessageDialog(null, "Warning!!,Doplicate Seat Name!!");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Seat Rate");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Seat Category");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Seat Type");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Seat Name");
		}
	}
	private void loadSeatData(){
		try {
			for(int a=ctable.getRowCount()-1;a>=0;a--){
				cmodel.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select * from tbSeatCreate order by SeatType,SeatId asc");
			while(rs.next()){
				cmodel.addRow(new Object[]{rs.getString("SeatId"),rs.getString("SeatName"),rs.getString("SeatType"),rs.getString("SeatCategory"),df.format(Double.parseDouble(rs.getString("Rate")))});
			}
			RowAddSeat();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private boolean checkSeatName(){
		try {
			ResultSet rs=db.sta.executeQuery("select tbSeatCreate.SeatName from tbSeatCreate");
			while(rs.next()){
				if(txtSeatName.getText().toString().equalsIgnoreCase(rs.getString("SeatName")))
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
	public void SeatAutoId(){
		try {
			String sql="select (ISNULL(max(SeatId),0)+1)as SeatId from tbSeatCreate";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				SeatAutoId=rs.getString("SeatId");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void RowAddSeat(){
		for(int a=0;a<19;a++){
			cmodel.addRow(new Object[]{"","","","","",new ImageIcon("icon/edt.png"),new ImageIcon("icon/delete.png")});
		}
	}
	private void addCmp() {
		setOpaque(false);
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(800,500));
		mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(westPanel, BorderLayout.CENTER);
		westPanel.setOpaque(false);
		westPanel_work();
	}
	private void westPanel_work() {
		westPanel.setPreferredSize(new Dimension(630, 10));
		//westPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		westPanel.setLayout(new BorderLayout());
		westPanel.add(westNorthPanel, BorderLayout.NORTH);
		westNorthPanel.setOpaque(false);
		westNorthPanel_work();
		westPanel.add(westSouthPanel, BorderLayout.SOUTH);
		westSouthPanel.setOpaque(false);
		westSouthPanel_work();
	}
	private void westNorthPanel_work() {
		westNorthPanel.setPreferredSize(new Dimension(10, 100));
		TitledBorder titlemain=BorderFactory.createTitledBorder("Room Tariff");
		titlemain.setTitleJustification(titlemain.CENTER);
		westNorthPanel.setBorder(titlemain);
		FlowLayout flow=new FlowLayout();
		westNorthPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		westNorthPanel.add(lblSeatName);
		westNorthPanel.add(txtSeatName);
		txtSeatName.setPreferredSize(new Dimension(180, 30));
		
		westNorthPanel.add(lblSeatType);
		westNorthPanel.add(cmbSeatType);
		cmbSeatType.setPreferredSize(new Dimension(100, 30));
		
		westNorthPanel.add(lblCategoryType);
		westNorthPanel.add(cmbcategoryType);
		cmbcategoryType.setPreferredSize(new Dimension(100, 30));
		
		westNorthPanel.add(lblSeatRate);
		westNorthPanel.add(txtSeatRate);
		txtSeatRate.setPreferredSize(new Dimension(100, 30));
		
		westNorthPanel.add(btnSaveCabin);
		btnSaveCabin.setPreferredSize(new Dimension(80, 36));
		btnSaveCabin.setMnemonic(KeyEvent.VK_A);
		final Component ob[] = {txtSeatName,cmbSeatType, cmbcategoryType,txtSeatRate,btnSaveCabin};	
		new FocusMoveByEnter(ob);
	}
	private void westSouthPanel_work() {
		westSouthPanel.setPreferredSize(new Dimension(10, 400));
		//westSouthPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		westSouthPanel.setLayout(new BorderLayout());
		westSouthPanel.add(cscroll);
		ctable.getTableHeader().setReorderingAllowed(false);
		ctable.setRowHeight(ctable.getRowHeight() + 12);
		ctable.getColumnModel().getColumn(0).setPreferredWidth(50);
		ctable.getColumnModel().getColumn(1).setPreferredWidth(230);
		ctable.getColumnModel().getColumn(2).setPreferredWidth(140);
		ctable.getColumnModel().getColumn(3).setPreferredWidth(140);
		ctable.getColumnModel().getColumn(4).setPreferredWidth(140);

		ctable.setShowGrid(true);
		ctable.setOpaque(false);
		cscroll.setOpaque(false);
		cscroll.getViewport().setOpaque(false);
		ctable.setShowGrid(true);
		ctable.setSelectionForeground(Color.red);
		ctable.setFont(new Font("arial", Font.BOLD, 13));
	}
}
