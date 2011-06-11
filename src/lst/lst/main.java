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

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class main extends Activity {
	
	 static ListView lv;
	 
	 public static boolean flage= true;
	 
	 private List<Object> listItemsto_Eriid = new ArrayList<Object>();
	
     private ListItemsAdapter adapterto_Eriid = null; 
	 
	 String abc[]={"Administration","Infrastructure","Security","Networking","Web Works","Programming","Desktop","Community"};
	 
	 public ArrayList<String> al=new ArrayList<String>();
	 
   @Override
    public void onCreate(Bundle savedInstanceState) 
   {
	   			super.onCreate(savedInstanceState);
	   			setContentView(R.layout.mainexp);
              
        
	   			lv=(ListView) findViewById(R.id.ListView01);
	   			for(int i = 0; i < abc.length; i++ )
	   			{
	   						Object object = new Object(); 			
	   						listItemsto_Eriid.add(object);
	   			}
	   			adapterto_Eriid = new ListItemsAdapter(listItemsto_Eriid);
	   			lv.setAdapter(adapterto_Eriid);	
						
	   			lv.setOnItemClickListener(new OnItemClickListener() {

			
	   				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
	   								if(flage)
	   								{
	   										new mymain().action(main.this);
	   										flage=false;
	   								}
				
	   								startActivity(new Intent(main.this,ExpList.class));
				
	   				}
		});
		
		
	}
		
	
	private class ListItemsAdapter extends ArrayAdapter<Object> {
		
		public ListItemsAdapter(List<Object> items) {
							super(main.this, android.R.layout.simple_list_item_1, items);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
							ViewHolder holderto;
												
							
							LayoutInflater inflater = getLayoutInflater();
							convertView = inflater.inflate(R.layout.maintwo, null);
		
							holderto = new ViewHolder();
							holderto.text_Two = (TextView) convertView.findViewById(R.id.TextView01);	
							convertView.setTag(holderto);
							holderto.text_Two.setText(abc[position]);
							holderto.text_Two.setPadding(80, 0, 0, 0);
							holderto.text_Two.setGravity(Gravity.CENTER_VERTICAL);
							holderto.text_Two.setTextColor(Color.WHITE);
							holderto.text_Two.setHeight(22);
							return convertView;
		
		}
		private class ViewHolder {
			
							TextView text_Two;
						
		}
			
		}
}
        
    
