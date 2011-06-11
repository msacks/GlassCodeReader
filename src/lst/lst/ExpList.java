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

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView.OnChildClickListener;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ExpList extends ExpandableListActivity implements OnChildClickListener
{
	
	ImageView img1,img2;
	static int pos=0;
		
	static ArrayList<String> a1=new ArrayList<String>();
	static ArrayList<String> b1=new ArrayList<String>();
	static ArrayList<Integer> c1=new ArrayList<Integer>();
	
    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        setContentView(R.layout.main);
        
        
        img1=(ImageView) findViewById(R.id.ImageView01);
        img2=(ImageView) findViewById(R.id.ImageView02);
        
               
                
        SimpleExpandableListAdapter expListAdapter =
			new SimpleExpandableListAdapter(
				this,
				createGroupList(),	// groupData describes the first-level entries
				R.layout.child_row,	// Layout for the first-level entries
				new String[] { "colorName" },	// Key in the groupData maps to display
				new int[] { R.id.childname },		// Data under "colorName" key goes into this TextView
				createChildList(),	// childData describes second-level entries
				R.layout.child_title,	// Layout for second-level entries
				new String[] { "shadeName", "rgb" },	// Keys in childData maps to display
				new int[] { R.id.childname, R.id.rgb }	// Data under the keys above go into these TextViews
			);
		setListAdapter( expListAdapter );
    }


	private List createGroupList() {
		
	  ArrayList result = new ArrayList();
	  
	  result.clear();
	  for( int i = 0 ; i<a1.size() ; i++ ) {
		HashMap m = new HashMap();
	    m.put( "colorName",a1.get(i) );
		result.add( m );
	  }
	  return (List)result;
    }

  private List createChildList() {
	ArrayList result = new ArrayList();
	int n = 0;
	for(int i=0;i<a1.size();i++)
	{	
		 ArrayList secList = new ArrayList();
		 
		 for(int j=0; j < c1.get(i) ; j++) 
		 {
		 	
		    HashMap child = new HashMap();
			child.put( "shadeName",b1.get(n));
			//child.put( "rgb", rssdb.ac.get(n+1) );
			secList.add(child);
			n++;
		 }
	 result.add(secList);
	}
	return result;
  }

  public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
	        int cp, long id) {
	    // use groupPosition and childPosition to locate the current item in the adapter
	  		pos=0;
	  	 for(int i=0;i<groupPosition;i++)
	  		 pos = pos + c1.get(i);
	  	 
	  	 pos = pos +cp;
	  	//pos=(groupPosition+cp);
				 
		 /* Uri uri = Uri.parse(a);
		  startActivity( new Intent( Intent.ACTION_VIEW, uri ) );*/
		 //Toast.makeText(ExpList.this, rssdb.ac.get(pos), 2000).show();
		// Toast.makeText(ExpList.this, String.valueOf(pos+cp), 2000).show();
	  	 startActivity(new Intent(ExpList.this,hello.class));
	  
	  return true;
	} 
  
}
