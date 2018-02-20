package cn.syuico;

import javax.swing.*;
import java.io.*;
public class LuanMa {
	public static void main(String[] args) {
		String inputValue = JOptionPane.showInputDialog("Please input a value");
		boolean fileStyle=false;
		char[] fileNameChar=inputValue.toCharArray();
		for(int i=0;i<fileNameChar.length;i++) {
			if(!(fileNameChar[i]>='0'&&fileNameChar[i]<='9')) {
				if(fileNameChar[i]=='.') {
					fileStyle=true;
				}
				if(!fileStyle) {
					fileNameChar[i]++;
				}
			}
		}
		String renameStr=String.valueOf(fileNameChar);
		setTxt(renameStr);
	} 
	private static void setTxt(String writeStr) {
		File file=new File("test.txt");
		if(file.exists()&&file.isFile()) {
			file.delete();
		}
		try {
			file.createNewFile();
			BufferedWriter write=new BufferedWriter(new FileWriter(file));
			write.write(writeStr);
			write.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
