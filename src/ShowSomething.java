import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.media.CannotRealizeException;
import javax.media.NoPlayerException;


public class ShowSomething {
	public void showInfo(File file){
		BbsInit.info.load(file);
		BbsInit.info.setBounds(0, 85, 1024, 693);
		BbsInit.contentPane.add(BbsInit.info,BorderLayout.CENTER);
		BbsInit.main_frame.repaint();
		this.sleep(8000);
		BbsInit.contentPane.remove(BbsInit.info);
	}
	public void showImage(File file) throws InterruptedException{
		BbsInit.image.load(file);
		BbsInit.contentPane.add(BbsInit.image);
		BbsInit.main_frame.repaint();
		this.sleep(8000);
		BbsInit.contentPane.remove(BbsInit.image);
	}
	public void showVideo(File file) throws NoPlayerException, CannotRealizeException, MalformedURLException, IOException, InterruptedException {
		//BbsInit.contentPane.remove(BbsInit.video);
		BbsInit.video = new VideoPanel();
		BbsInit.video.setBounds(0, 85, 1024, 693);
		BbsInit.video.load(file);
		BbsInit.contentPane.add(BbsInit.video,BorderLayout.CENTER);
		BbsInit.video.videoStart();
		this.sleep(BbsInit.video.duration()*1000+2000);
		BbsInit.video.videoStop();
		//if(full_screen)BbsInit.main_frame.setVisible(true);
	    BbsInit.contentPane.remove(BbsInit.video);
	}

	public synchronized void sleep(long msec)
	{	//指定ミリ秒実行を止めるメソッド
		try
		{
			wait(msec);
		}catch(InterruptedException e){}
	}

}
