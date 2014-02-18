import java.awt.BorderLayout;
import java.awt.Component;
import java.io.File;
import java.io.IOException;

import javax.media.*;
import javax.swing.JPanel;

public class VideoPanel extends JPanel
    {
	private static final long serialVersionUID = 1L;

	 Player mediaPlayer;
	 double sec;
	 //public VideoPanel(){
	//
	// }
	 public void load(File file) throws NoPlayerException, CannotRealizeException, IOException{
	    	this.setBounds(0, 85, 1024, 693);
			mediaPlayer = Manager.createRealizedPlayer(file.toURI().toURL());
			sec = mediaPlayer.getDuration().getSeconds();
	        setLayout( new BorderLayout() ); // use a BorderLayout
	        // Use lightweight components for Swing compatibility
	        Manager.setHint( Manager.LIGHTWEIGHT_RENDERER, true );
	        Component video = mediaPlayer.getVisualComponent();
	        //Component controls = mediaPlayer.getControlPanelComponent();
	        if ( video != null )
	           add( video, BorderLayout.CENTER ); // add video component

	        //if ( controls != null )
	        //   add( controls, BorderLayout.SOUTH ); // add controls
	 }

	//public void load( URL uri ) throws InterruptedException, NoPlayerException, CannotRealizeException, IOException{
	//
	//} // end MediaPanel constructor
	public long duration(){
        System.out.println("動画の長さ："+(long)sec);
		return (long)sec;
	}
	public void videoStart() {
        mediaPlayer.start(); //動画を流しているのではないだろうか。
	}
	public void videoStop() {
        mediaPlayer.stop();
        mediaPlayer.close();
	}

} // end class MediaPanel
