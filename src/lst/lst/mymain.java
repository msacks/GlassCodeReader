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

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class mymain {
    
	
	String ab=" ";
	String bc= " ";
	String cd=" ";
	
	TextView txt1;
	ListView lv;
	
	  int i=-1; 
	 public rssdb dh;
	 
	 ArrayList<String> al=new ArrayList<String>();
	
	public boolean title_Flage = false;
	private static  URL m_storageUrl;
 	
	URLConnection connection = null;
 	InputStream stream = null;
 	XmlPullParser xpp = null;
 	
 	public static String check_string="";
 	
 	ArrayList<String> Title = new ArrayList<String>();
 
	
    
    public void action(Context c) {
       
  
      dh=new rssdb(c);
                    
        try{
			
			m_storageUrl= new URL("http://feeds.feedburner.com/TheBitsource");
			connection = m_storageUrl.openConnection();
			stream = connection.getInputStream();			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			xpp = XmlPullParserFactory.newInstance().newPullParser();
			xpp.setInput(stream, null);
			int eventType = xpp.getEventType();
			while(eventType != XmlPullParser.END_DOCUMENT)
			{
				if(eventType == XmlPullParser.START_TAG)
				{
					String elementName = xpp.getName();
				
					
							
							if("title".equals(elementName))
							{	
								ab=xpp.nextText();
								
							}
							if("link".equals(elementName))
							{
								i=i+1;
								cd=xpp.nextText();
														
							}
							if("category".equals(elementName))
							{
							
								dh.insert(i,ab,cd,xpp.nextText());
								
							}
														
				}
				eventType = xpp.next();
			}
			
				stream.close();
		}
		
		catch(Exception e){
			e.printStackTrace();
		}	
		ExpList.a1.clear();
		ExpList.b1.clear();
		ExpList.c1.clear();
		
		dh.retc();
		dh.retct();
	}   
       
}
