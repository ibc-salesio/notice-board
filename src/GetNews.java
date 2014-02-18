import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GetNews{
	int kaisu=0;
	File newsfile = new File("./data/news.rss");
    //XML(ニュース)
	  //http://www.dd.e-mansion.com/~kumada/laboratory/column/xml_dom.html
	  //http://d.hatena.ne.jp/takigawa401/20101116/1289917283
	
	/**
	 * ニュースを取得する
	 *
	 * @param proxy
	 * @return
	 */
	public void news_get(boolean proxy) throws IOException, InterruptedException{
		int b;
		BbsInit.shed.setLocation(0,10);
		BbsInit.shed.setText("しばらくお待ちください");
		BbsInit.shed.setSize(BbsInit.shed.getPreferredSize());
		//BbsInit.shed.setSize(BbsInit.shed.getWidth(), BbsInit.time.getHeight());
		BbsInit.shed.setLocation(BbsInit.shed.getX(),BbsInit.shed.getY());
    	//rss取得
		if(proxy){
			System.setProperty("http.proxyHost", "192.168.0.20");
			System.setProperty("http.proxyPort", "7080");
		}
    	URL url = new URL("http://rss.asahi.com/rss/asahi/newsheadlines.rdf");
    	URLConnection conn;
    	InputStream in;
			try {
				conn = url.openConnection();
				conn.setConnectTimeout(15);
				in = conn.getInputStream();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				return;
			}
    	//URL url = new URL("http://kyoko-np.net/index.xml");
    	FileOutputStream out = new FileOutputStream(newsfile, false);
    	while((b = in.read()) != -1){
    		out.write(b);
    	}
    	out.close();
    	in.close();
    	Thread.sleep(1000);
    //	ここまで

	}

	public void newsFile(String filename) throws IOException{
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(newsfile.lastModified());
		int d = cal.get(Calendar.DAY_OF_MONTH);
        int h = cal.get(Calendar.HOUR_OF_DAY);
        int m = cal.get(Calendar.MINUTE);
		int i=0;
		int tmp2=0;
		//int s=0;

		String linetmp[] = new String[500];
		String tmp="";
		String saisyo="";
		String saisyo_b="";
		String saisyo_tmp="";
		String line="";
		String regex = "\\<title>(.+)\\</title>";

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"UTF-8"));
		//System.out.println("流すニュース一覧\n//////////////////////////////////////////////////");
		while ((line=br.readLine()) != null) {
			linetmp[i]=line;
			i++;
		}
		if(tmp2==0 && m>10){
			BbsInit.contents[kaisu]=d+"日"+h+":"+m+"分現在の最新ニュース:朝日新聞";
			kaisu++;
		}

		if(tmp2==0 && m<10){
			BbsInit.contents[kaisu]=d+"日"+h+":0"+m+"分現在の最新ニュース:朝日新聞";
			kaisu++;
		}

		for(tmp2=0;tmp2<i;tmp2++){
			tmp=linetmp[tmp2];
			saisyo_tmp=extractMatchString(regex, tmp);
			if(saisyo_tmp!=null){
				if(!saisyo_tmp.equals("朝日新聞デジタル")){
				saisyo=saisyo_tmp.substring(0,2);
				saisyo_b=saisyo_tmp.substring(0,1);
					if(!saisyo.equals("<!")){
						if(!saisyo_b.equals("＜")){
							BbsInit.contents[kaisu]=extractMatchString(regex,tmp);
							//System.out.println(BbsInit.contents[kaisu]);
							kaisu++;
							//if(kaisu==11){
								//break;
							//}
							//s++;
						}
					}
				}
			}
		}//TODO: なんだよこれ！！！！！！！ころすぞFor文から入ってるぞ。
		//s++;
		//System.out.println("以上"+s+"個\n"+"//////////////////////////////////////////////////");
	}

	public String extractMatchString(String regex, String tmp) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(tmp);
		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return null;
		}
	}
//XML(学校のイベント)
  //http://www.dd.e-mansion.com/~kumada/laboratory/column/xml_dom.html
  //http://d.hatena.ne.jp/takigawa401/20101116/1289917283
	/**
	 * xmlファイルを読み込む
	 *
	 * @param file
	 * @return
	 */
	public void readPropertyFile(File file){
		DocumentBuilderFactory factory;
		DocumentBuilder builder;
		Document doc;

		try {
			System.out.println("学校のニュース取得開始");
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			doc = builder.parse(file);
		} catch(ParserConfigurationException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} catch (SAXException e) {
			e.printStackTrace();
			return;
		}

		// パース開始
		NodeList childs = doc.getChildNodes();

		for(int i = 0; i < childs.getLength(); i++) {
			Node n = childs.item(i);
			Element e = (Element)n;
			if(e.getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}
			if(!"shed".equals(e.getTagName())) /*<shed>がみつかったら下に*/{
				continue;
			}
			NodeList paramNode = n.getChildNodes();
			for(int j = 0; j < paramNode.getLength(); j++) {
				Node n2 = (Node)paramNode.item(j);
				if(n2.getNodeType() != Node.ELEMENT_NODE) {
					continue;
				}
				if("contents".equals(n2.getNodeName())) /*<contents>がみつかったら*/{
					BbsInit.contents[kaisu] = n2.getFirstChild().getNodeValue();
					System.out.println(BbsInit.contents[kaisu]);
					kaisu++;
				}
			}
		}
	}
}
//ここまで

