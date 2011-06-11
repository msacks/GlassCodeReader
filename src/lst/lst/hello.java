/*
   Copyright 2011 GlassCode Web Development Corporation www.glasscodeinc.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/


package lst.lst;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.AttributedString;
import java.text.AttributedCharacterIterator.Attribute;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;

public class hello extends Activity {

	String htmls ="" ;
	String line=null;

	TextView chk;
	
	 int i;
	 int j;
	
	 rssdb h_hello;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check);
		h_hello=new rssdb(this);
		
		chk=(TextView) findViewById(R.id.TextViewcheck);
		
		
		try
		{
			 String a=rssdb.ad.get(ExpList.pos);
			 HttpClient client = new DefaultHttpClient();
			//HttpGet request = new HttpGet("http://feedproxy.google.com/~r/TheBitsource/~3/A-wpQzL8fus/");
			 HttpGet request = new HttpGet(a);
			
	
		
			HttpResponse response = client.execute(request);
			InputStream in = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder str = new StringBuilder();
		
		
	
		while((line = reader.readLine()) != null)
		{
			str.append(line);
		}
	
		in.close();
	
		htmls = str.toString();
	}
		
		catch(Exception e){
			e.printStackTrace();
		}
		
		char[] ch=htmls.toCharArray();
		
		
		for(i=0 ; i<ch.length ;i++)
		{	
			if(ch[i]=='p'&&ch[i+1]=='o'&&ch[i+2]=='s'&&ch[i+3]=='t'&&ch[i+4]=='c'&&ch[i+5]=='o'
				&&ch[i+6]=='n'&&ch[i+7]=='t'&&ch[i+8]=='e'&&ch[i+9]=='n'&&ch[i+10]=='t')				
			{
				break;
			}			
		
		}
		for(j=i;j<ch.length;j++)
		{
			if(ch[j]=='<'&&ch[j+1]=='/'&&ch[j+2]=='d' && ch[j+3]=='i' && ch[j+4]=='v')
			{
				break;
			}
		}
		
		char ch1[] = new char[j-i];
		
			
		for(int k=i, milan=0;k<j;k++,milan++)
		{
			ch1[milan]=ch[k];
			
		}
		
		String h = new String(ch1);
		
				
		 String s3=""; 
		 s3+=StringUtils.substringBetween(htmls, "<title>", "</title>");
		
		 String title2 = StringUtils.substringAfter(htmls, "<div id=\"postcontent\">");
		 String title3=StringUtils.substringBefore(title2, "<div id=\"postcomments\">");
		 
		 String titletest = title3;
		 chk.setText("");
		 
		 s3+="\n\n";
		
		while(titletest.contains("<p>") && titletest.contains("</p>"))
		 {
		
			 
			 title2 = StringUtils.substringAfter(titletest, "<p>");
			 title3=StringUtils.substringBefore(title2, "</p>");
			
			 titletest = titletest.replace("<p>"+title3+"</p>", "");
			 chk.setText(chk.getText()+ "\n\n"+title3);
		 }
		
		s3 += (String) chk.getText();
		
		
		
		while(s3.contains("<!--") && s3.contains("-->") )
		{
			String comment = StringUtils.substringBetween(s3, "<!--", "-->");
			s3 = s3.replace("<!--"+comment+"-->" , "");
		}
		
		while(s3.contains("<a") && s3.contains("</a>"))
		{
			String comment = StringUtils.substringBetween(s3, "<a", "</a>");
			s3 = s3.replace("<a"+comment+"</a>" , "");
		}
		while(s3.contains("<strong>") && s3.contains("</strong>"))
		{
			String comment = StringUtils.substringBetween(s3, "<strong>", "</strong>");
			s3 = s3.replace("<strong>"+comment+"</strong>" , "");
		}
		
		
		s3=s3.replaceAll("<br />","\n");
		s3=s3.replaceAll("&#8211;","-");
		s3=s3.replaceAll("&#8217;", "`");
		s3=s3.replaceAll("&#8220;", "\"");
		s3=s3.replaceAll("&#8221;", "\"");
		chk.setText(s3);
				
	}
		
}
	
