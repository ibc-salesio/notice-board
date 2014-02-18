import java.util.Calendar;
import java.util.TimerTask;

   public class Timer_ extends TimerTask {
       boolean f = false;
       public void run(){
              String delim = f ? ":" : " ";
              f = !f;
              Calendar now = Calendar.getInstance(); 
              int h = now.get(Calendar.HOUR_OF_DAY);
              int m = now.get(Calendar.MINUTE);     
              //int s = now.get(Calendar.SECOND);   
            if(m<10){
             	 BbsInit.time.setText(h+delim+"0"+m);
              }else{
            	  BbsInit.time.setText(h+delim+m);
              }
       }
   }