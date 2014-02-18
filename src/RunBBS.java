import java.io.File;
import java.io.IOException;
import javax.media.CannotRealizeException;
import javax.media.NoDataSourceException;
import javax.media.NoPlayerException;

public class RunBBS implements Runnable {

	ShowSomething s = new ShowSomething();

	public String getSuffix(String fileName) {
	    if (fileName == null)
	        return null;
	    int point = fileName.lastIndexOf(".");
	    if (point != -1) {
	        return fileName.substring(point + 1);
	    }
	    return fileName;
	}

	public void loading() throws InterruptedException, NoDataSourceException, IOException, NoPlayerException, CannotRealizeException{
		File directory,file;
		String[] name;
		String type;
		while(true){
			directory = new File("./data");
			name = directory.list();
			for(int i=0;i<name.length; i++){
				file = new File("./data/"+name[i]);
				System.out.println(name[i]);
				type = getSuffix(name[i]);//ファイルの中身の判定は拡張子見てるだけ
				System.out.println(type);
				System.out.println(type.length());
				if (type.equals("jpg") || type.equals("png"))
					s.showImage(file);
				else if (type.equals("mpg"))
					s.showVideo(file);
				else if(type.equals("txt"))
					s.showInfo(file);
			}
			directory = null;
		}
	}

	public void run() {
				try {
					loading();
				} catch (NoDataSourceException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				} catch (NoPlayerException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				} catch (CannotRealizeException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
	}
}
