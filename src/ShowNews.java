import java.io.File;
import java.io.IOException;


public class ShowNews implements Runnable{
	
	
	public void rotation_main() throws InterruptedException, IOException{
    	GetNews news = new GetNews();
		while(true){
			//news.news_get(true);
	    	news.readPropertyFile(new File("./data/shed_text.xml"));//学生お知らせ読み込み
	    	//news.newsFile("./data/news.rss");//ニュース読み込み
			for(int i=0;i<news.kaisu;i++){
				BbsInit.shed.setLocation(0,10);
				BbsInit.shed.setText(BbsInit.contents[i]);
				BbsInit.shed.setSize(BbsInit.shed.getPreferredSize());
				BbsInit.shed.setSize(BbsInit.shed.getWidth(), BbsInit.time.getHeight());
				BbsInit.shed.setLocation(BbsInit.shed.getX(),BbsInit.shed.getY());
				Thread.sleep(2000);
				while(BbsInit.shed.getX()>-BbsInit.shed.getWidth()){
					Thread.sleep(80);
					BbsInit.shed.setLocation(BbsInit.shed.getX()-9, BbsInit.shed.getY());
				}
			}
			news.kaisu=0;
		}
	}

	public void run() {
		// TODO 自動生成されたメソッド・スタブ
		try {
			this.rotation_main();
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
