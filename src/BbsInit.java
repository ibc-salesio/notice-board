import java.awt.*;
import java.util.Timer;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class BbsInit {
	static JFrame main_frame = new JFrame("サレジオ高専放送部電子掲示板Beta");
	//////////////////////////
	static JLabel shed = new JLabel();
	static JLabel time = new JLabel();
	static InfoLabel info = new InfoLabel();
	static ImagePanel image = new ImagePanel();
	static VideoPanel video;
	static Container contentPane = main_frame.getContentPane();
	static String contents[] = new String[150];
	//GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();



    public void BbsLoad(){
    	System.out.println("フレーム設定中");
    	main_frame.setLayout(null);
    	main_frame.setUndecorated(true);
    	main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	main_frame.setSize(1024,768);
    	main_frame.setLocationRelativeTo(null);
    	//time
    	time.setBounds(810,10,350,75);
    	time.setFont(new Font("Hiragino Gothic",Font.PLAIN,70));
    	time.setOpaque(true);
    	time.setBackground(new Color(237,237,237));
    	//流れる文字
    	shed.setBounds(0,10,10000,60);
    	shed.setFont(new Font("Hiragino Gothic",Font.PLAIN,70));
    	shed.setOpaque(false);

    	info.setBounds(0, 85, 1024, 693);
    	info.setFont(new Font("Hiragino Gothic",Font.PLAIN,60));
    	//
    //image.load("nowloading.jpg");
    	image.setBounds(0, 85, 1024, 693);
    	//きめ！
    	contentPane.add(time,BorderLayout.CENTER);
    	contentPane.add(shed,BorderLayout.CENTER);
    	System.out.println("フレーム読み込み完了");

    	//Timer
    	System.out.println("タイマー読み込み中");
    	Timer t = new Timer();
    	t.schedule(new Timer_(),0,500);
    	System.out.println("タイマー読み込み完了");
    	main_frame.setVisible(true);
    	//device.setFullScreenWindow(main_frame);
    	System.out.println("ウインドウ表示");
    }
}