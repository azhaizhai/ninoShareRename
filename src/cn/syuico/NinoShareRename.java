package cn.syuico;

import java.io.*;
import javax.swing.filechooser.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
public class NinoShareRename {
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	private static String[] getFilesName(String folder){
		String[] filesName;
		File _folder=new File(folder);
		filesName=_folder.list();
		return filesName;
	}
	private static String getFolder(){
		int result=0;
		String path=null;
		JFileChooser fileChooser=new JFileChooser();
		FileSystemView fsv=FileSystemView.getFileSystemView();
		fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
		fileChooser.setDialogTitle("请选择要重命名的文件夹");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		result=fileChooser.showOpenDialog(fileChooser);
		if(JFileChooser.APPROVE_OPTION==result) {
			path=fileChooser.getSelectedFile().getPath();
		}
		return path;
	}
	
	private static boolean rename(String folder,String[] filesName) {
		boolean results=true;
		String renameStr;
		for(String fileName:filesName) {
			char[] fileNameChar=fileName.toCharArray();
			for(int i=0;i<fileNameChar.length;i++) {
				fileNameChar[i]++;
			}
			renameStr=String.valueOf(fileNameChar);
			boolean result=new File(folder+FILE_SEPARATOR+fileName).renameTo(new File(folder+FILE_SEPARATOR+renameStr));
			results=result&&results;
		}
		return results;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String folder=getFolder();
		if(folder==null) {
			JOptionPane.showMessageDialog(null, "抱歉，你选择的路径可能有问题", "错误信息", JOptionPane.ERROR_MESSAGE);
		}else {
			String[] filesName=getFilesName(folder);
			int warning=JOptionPane.showConfirmDialog(null, "你确定要将选中文件夹中包含的文件按照nino的规则重新命名吗？（如果选择yes，您稍后可以使用还原程序还原您的操作）", "警告信息", JOptionPane.YES_NO_OPTION);
			if(warning==0) {
				boolean result=rename(folder,filesName);
				if(result) {
					JOptionPane.showMessageDialog(null, "重命名完成");
				}else {
					JOptionPane.showMessageDialog(null, "在任务执行过程中可能出现了一些问题，也许您可以考虑使用还原程序还原您的操作");
				}
			}else if(warning==1) {
				JOptionPane.showMessageDialog(null, "没有进行任何操作");
			}
		}
	}
}