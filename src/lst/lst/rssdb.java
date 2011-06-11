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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class rssdb {

	private static final String db_name="rssd.db";
	private static final int db_version=1;
	private static final String t_name="mtab";
	
	Context con;
	DatabaseHelper dbhelp;
	SQLiteDatabase db;
	Cursor cursor,c,s;	
	
	static ArrayList<String> abc = new ArrayList<String>();
	static ArrayList<String> ac = new ArrayList<String>();
	static ArrayList<Integer> arr = new ArrayList<Integer>();
	static ArrayList<String> ad=new ArrayList<String>(); 
	
	ArrayList<String> todo=new ArrayList<String>();
	ArrayList<String> tod=new ArrayList<String>();
	
	
	
	
	String a;
	private static final String db_creat="create table mtab(mid integer,mtitle text not null,mlink text not null,mcat text not null)";
	


	public rssdb(Context con)
	{
		this.con=con;
		dbhelp= new DatabaseHelper(con);
		db= dbhelp.getWritableDatabase();
	}

	public static class DatabaseHelper extends SQLiteOpenHelper
	{
			DatabaseHelper(Context con) 
			{
					super(con, db_name, null, db_version);
			}
	
		

			@Override
			public void onCreate(SQLiteDatabase db) {
		
				db.execSQL(db_creat);
			}
	

			@Override
			public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
			}
	}

	
	
		public void insert(int i,String abc,String cd,String bc) {
		
				ContentValues initialval=new ContentValues();
				initialval.put("mid", i);
				initialval.put("mtitle", abc);
				initialval.put("mlink", cd);
				initialval.put("mcat", bc);
				db.insert(t_name,null,initialval);
						
		}	

		public void retc()
		{
	
	
				Cursor c1=db.rawQuery("SELECT DISTINCT mcat FROM mtab" , null);
				c1.moveToFirst();	 
	
				do
				{
					abc.add(c1.getString(c1.getColumnIndex("mcat")));
								
				}while((c1.moveToNext()));	
	
				for(int a2=0;a2<abc.size();a2++)
				
						ExpList.a1.add(abc.get(a2));
				
		}

		public void retct()
		{
	
				ac.clear();
	
				for(int i=0;i<abc.size();i++)
				{
						int milan=0;
						Cursor m1=db.rawQuery("select mtitle,mlink from mtab where mcat='"+ abc.get(i) +"'" , null);
		
						m1.moveToFirst();
						do
						{
								milan++;
								ac.add(m1.getString(m1.getColumnIndex("mtitle")));
								ad.add(m1.getString(m1.getColumnIndex("mlink")));
		
						}while(m1.moveToNext());	
			
						arr.add(milan);
	
				}	
	
				for(int i=0;i<ac.size();i++)
							ExpList.b1.add(ac.get(i));
	
				for(int i=0;i<arr.size();i++)
							ExpList.c1.add(arr.get(i));
	
	
}

/*public void ret_link()
{
	
	for(int i=0;i<abc.size();i++)
	{
		int lan=0;
		Cursor s1=db.rawQuery("SELECT  mlink FROM mtab where mtitle='"+ac.get(i)+ "'" , null);
		s1.moveToFirst();
			
	 		
	do
	{
		lan++;
		ad.add(s1.getString(s1.getColumnIndex("mlink")));
		
			
						
	}while((s1.moveToNext()));
	}
}

	public void title()
	{
		
		for(int i=0;i<abc.size();i++)
		{
			int milan=0;
			
			Cursor m1=db.rawQuery("select distinct mtitle from mtab where mcat='"+ abc.get(i) +"'ORDER BY mid" , null);
			
			m1.moveToFirst();
			do
			{
					milan++;
					as.add(m1.getString(m1.getColumnIndex("mtitle")));
			
		    }while(m1.moveToNext());
			
		}
	}*/
}
