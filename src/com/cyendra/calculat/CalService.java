package com.cyendra.calculat;

public class CalService {
	
	private double store = 0;
	private String firstNum = null;
	private String secondNum = null;
	private String lastOp = null;
	private boolean isSecondNum = false;
	
	private String numString = "0123456789.";
	private String opString = "+-*/";
	
	public CalService() {
		super();
	}
	
	String callMethod(String cmd,String text) throws Exception{
		if (cmd.equals("C")){
			return clearAll();
		}
		if (cmd.equals("CE")){
			return clear();
		}
		if (cmd.equals("Back")){
			return backSpace(text);
		}
		if (numString.indexOf(cmd)!=-1){
			return catNum(cmd,text);
		}
		if (opString.indexOf(cmd)!=-1){
			return setOp(cmd,text);
		}
		if (cmd.equals("=")){
			return cal(text,false);
		}
		if (cmd.equals("+/-")){
			return setNegative(text);
		}
		if (cmd.equals("1/x")){
			return setReciprocal(text);
		}
		if (cmd.equals("sqrt")){
			return sqrt(text);
		}
		if (cmd.equals("%")){
			return cal(text,true);
		}
		if (cmd.indexOf("M")==0){
			return mCmd(cmd,text);
		}
		return null;
	}
	
	public String cal(String text,boolean isPercent)
	throws Exception{
		if (lastOp==null) return firstNum;
		double secondResult= secondNum==null ? Double.valueOf(text).doubleValue()
											 : Double.valueOf(secondNum).doubleValue();
		if (secondResult==0&&this.lastOp.equals("/")){
			return "0";
		}
		if (isPercent){
			secondResult=MyMath.multiply(Double.valueOf(firstNum), MyMath.divide(secondResult, 100));
		}
		if (this.lastOp.equals("+")){
			firstNum=String.valueOf(MyMath.add(Double.valueOf(firstNum), secondResult));
		}
		if (this.lastOp.equals("-")){
			firstNum=String.valueOf(MyMath.subtract(Double.valueOf(firstNum), secondResult));
		}
		if (this.lastOp.equals("*")){
			firstNum=String.valueOf(MyMath.multiply(Double.valueOf(firstNum), secondResult));
		}
		if (this.lastOp.equals("/")){
			firstNum=String.valueOf(MyMath.divide(Double.valueOf(firstNum), secondResult));
		}
		secondNum = secondNum == null? text : secondNum;
		this.isSecondNum=true;
		return firstNum;
	}
	
	public String setReciprocal(String text){
		if (text.equals("0")) return text;
		this.isSecondNum=true;
		return String.valueOf(MyMath.divide(1, Double.valueOf(text)));
	}
	
	public String sqrt(String text){
		this.isSecondNum=true;
		return String.valueOf(Math.sqrt(Double.valueOf(text)));	
	}
	
	public String setOp(String cmd,String text){
		this.lastOp=cmd;
		this.firstNum=text;
		this.secondNum=null;
		this.isSecondNum=true;
		return null;	
	}
	
	public String setNegative(String text){
		if (text.indexOf("-") == 0) {
			return text.substring(1, text.length());
		}
		return text.equals("0") ? text : "-" + text;	
	}
	
 	public String catNum(String cmd,String text){
		String result=cmd;
		if (!text.equals("0")){
			if (isSecondNum){
				isSecondNum=false;
			}
			else{
				result=text+cmd;
			}
		}
		if (result.indexOf(".")==0){
			result="0"+result;
		}
		return result;	
	}
	
	public String backSpace(String text){
		return text.equals("0") || text.equals("") ? "0" : text.substring(0,text.length()-1);	
	}
	
	public String mCmd(String cmd,String text){
		if (cmd.equals("M+")){
			store=MyMath.add(store, Double.valueOf(text));
		}
		if (cmd.equals("MC")){
			store=0;
		}
		if (cmd.equals("MR")){
			isSecondNum=true;
			return String.valueOf(store);
		}
		if (cmd.equals("MS")){
			store=Double.valueOf(text).doubleValue();
		}
		return null;	
	}
	
	public String clearAll(){
		this.firstNum="0";
		this.secondNum=null;
		return this.firstNum;	
	}
	
	public String clear(){
		return "0";	
	}
	
	public double getStore() {
		return this.store;
	}
	
}
