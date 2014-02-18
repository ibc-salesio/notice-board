//www.atmarkit.co.jp/fjava/rensai3/swing02/swing02_01.html
//
import java.io.*;

import javax.media.CannotRealizeException;
import javax.media.NoPlayerException;

public class Keijiban_main{
	public static void main(String[] args) throws IOException, InterruptedException, NoPlayerException, CannotRealizeException{//こっから物語が始まった・・・・
    	Thread[] threads = new Thread[2];//スレッド作成
    	Runnable[] runnable = new Runnable[2];//並列処理
		BbsInit bbs =new BbsInit();//bbsの初期化
		bbs.BbsLoad();//bbsのBbsLoadの
    	//bbs.opening();
    	runnable[0] = new RunBBS();
    	runnable[1] = new ShowNews();
    	threads[0] = new Thread(runnable[0]);
    	threads[1] = new Thread(runnable[1]);
    	threads[0].start();
    	threads[1].start();
    }

}






