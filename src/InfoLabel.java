import java.awt.Color;
import java.io.*;

import javax.swing.JLabel;


public class InfoLabel extends JLabel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public void load(File file){
		String tmp,s="";
		try {
			BufferedReader b = new BufferedReader(new FileReader(file));
			while((tmp=b.readLine())!=null){
				s+=tmp+"\n";
				System.out.println(tmp);
			}
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		System.out.println(s);
		BbsInit.info.setText(s);
		BbsInit.info.setBackground(Color.WHITE);
		BbsInit.info.setOpaque(true);
		//BbsInit.info.setSize();
	}
}
