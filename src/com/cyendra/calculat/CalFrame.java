package com.cyendra.calculat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;

public class CalFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private JTextField textField = null;
	private JButton button = null;
	private CalService service = new CalService();
	
	private String[] nOp={"7","8","9","/","sqrt","4","5","6","*",
			"%","1","2","3","-","1/x","0","+/-",".","+","="};
	private String[] mOp={"MC","MR","MS","M+"};
	private String[] rOp={"Back","CE","C"};
	
	private final int PRE_WIDTH = 360;
	private final int PRE_HEIGHT = 250;
	
	public CalFrame(){
		super();
		initialize();
	}
	
	private void initialize(){
		this.setTitle("¼ÆËãÆ÷");
		this.setResizable(false);
		
		JPanel panel =new JPanel();
		panel.setLayout(new BorderLayout(10,1));
		panel.add(getTextField(),BorderLayout.NORTH);
		panel.setPreferredSize(new Dimension(PRE_WIDTH,PRE_HEIGHT));
		
		JButton[] mButton=getMButton();
		JPanel panel_mb=new JPanel();
		panel_mb.setLayout(new GridLayout(5,1,0,5));
		for (JButton b:mButton) panel_mb.add(b);
		
		JButton[] rButton=getRButton();
		JPanel panel2=new JPanel();
		panel2.setLayout(new BorderLayout(1,5));
		JPanel panel_rb=new JPanel();
		panel_rb.setLayout(new GridLayout(1,3,3,3));
		for (JButton b:rButton) panel_rb.add(b);
		
		JButton[] nButton=getNButton();
		JPanel panel_nb=new JPanel();
		panel_nb.setLayout(new GridLayout(4,5,3,5));
		for (JButton b:nButton) panel_nb.add(b);
		
		panel2.add(panel_rb,BorderLayout.NORTH);
		panel2.add(panel_nb,BorderLayout.CENTER);
		panel.add(panel_mb,BorderLayout.WEST);
		panel.add(panel2,BorderLayout.CENTER);
		this.add(panel);
		
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 360) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 250) / 2;
		this.setLocation(w,h-100);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private JButton getButton(){
		if (button==null){
			button=new JButton();
		}
		return button;
	}
	
	public JTextField getTextField(){
		if (textField==null){
			textField=new JTextField("0");
			textField.setEditable(false);
			textField.setBackground(Color.white);
			textField.setHorizontalAlignment(JTextField.RIGHT);
		}
		return textField;
	}
	
	private JButton[] getNButton(){
		String[] redButton={"/","*","-","+","="};
		JButton[] result=new JButton[this.nOp.length];
		for (int i=0;i<this.nOp.length;i++){
			JButton b=new JButton(this.nOp[i]);
			b.addActionListener(this);
			Arrays.sort(redButton);
			if (Arrays.binarySearch(redButton,nOp[i])>=0){
				b.setForeground(Color.red);
			}
			else{
				b.setForeground(Color.blue);
			}
			result[i]=b;
		}
		return result;
	}
	
	private JButton[] getMButton(){
		JButton[] result=new JButton[mOp.length+1];
		result[0]=getButton();
		for (int i=0;i<this.mOp.length;i++){
			JButton b=new JButton(this.mOp[i]);
			b.addActionListener(this);
			b.setForeground(Color.red);
			result[i+1]=b;
		}
		return result;
	}
	
	private JButton[] getRButton(){
		JButton[] result = new JButton[rOp.length];
		for (int i = 0; i < this.rOp.length; i++){
			JButton b = new JButton(rOp[i]);
			b.addActionListener(this);
			b.setForeground(Color.red);
			result[i] = b;
		}
		return result;
	}

	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		String result=null;
		try{
			result=service.callMethod(cmd,textField.getText());
		}
		catch(Exception e1){
			System.out.println(e1.getMessage());
		}
		if (cmd.indexOf("MC")==0){
			button.setText("");
		}
		else if (cmd.indexOf("M")==0&&service.getStore()>0){
			button.setText("M");
		}
		if (result!=null){
			textField.setText(result);
		}
	}
	
}



