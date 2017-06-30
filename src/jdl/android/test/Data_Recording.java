package jdl.android.test;
import java.util.List;

import jdl.android.test.Data_Recording;
import jdl.android.test.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.SurfaceHolder.Callback;

public class Data_Recording extends Activity{
	final int HEIGHT=980;   //���û�ͼ��Χ�߶�
    final int WIDTH=800;    //��ͼ��Χ���
    SurfaceView surface = null;
    private SurfaceHolder holder = null;    //��ͼʹ�ã����Կ���һ��SurfaceView
    int d[]=new int[100];
    
    
    
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_recording);
        
		surface = (SurfaceView)findViewById(R.id.show);
        //��ʼ��SurfaceHolder����
        holder = surface.getHolder();  
        holder.setFixedSize(WIDTH+20, HEIGHT+100);  //���û�����С��Ҫ��ʵ�ʵĻ�ͼλ�ô�һ��
        holder.addCallback(new Callback() {  
            public void surfaceChanged(SurfaceHolder holder,int format,int width,int height){ 
                drawBack(holder); 
                //���û����仰����ʹ���ڿ�ʼ���г���������Ļû�а�ɫ�Ļ�������
                //ֱ�����°�������Ϊ�ڰ������ж�drawBack(SurfaceHolder holder)�ĵ���
            }
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				// TODO Auto-generated method stub				
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub				
			} 
        });
    }
	 private void drawBack(SurfaceHolder holder){ 
	        Canvas canvas = holder.lockCanvas(); //��������
	        //���ư�ɫ���� 
	        canvas.drawColor(Color.WHITE); 
	        Paint p = new Paint(); 
	        Paint p1 = new Paint(); 
	        Paint p2 = new Paint(); 
	        Paint p3 = new Paint(); 
	        p.setColor(Color.DKGRAY); 
	        p1.setColor(Color.RED); 
	        p2.setColor(Color.MAGENTA); 
	        p3.setColor(Color.BLUE); 
	        
	        p.setStrokeWidth(3); 
	        
	        p.setTextSize(45);//���������С
	        p1.setTextSize(45);//���������С
	        p2.setTextSize(45);//���������С
	        p3.setTextSize(45);//���������С
	        
	        Object obj[]=  MainActivity.list.toArray(); 
	    	 Log.v("BreakPoint","��õ���������"+obj.length);
	        for(int x=0; x<obj.length; x++){
	      
	        	
	        	 d[x]=Integer.parseInt(String.valueOf(obj[x])); ;
	        	
	        	 Log.v("BreakPoint","��õ���������"+d[x]);
	        	}
	    	canvas.drawText("����ʱ��",210,100, p);
	    	canvas.drawText("�¶�",600,100, p);
	        
	        for(int i=0;i<11;i++){
	        canvas.drawLine(100,HEIGHT-80*i, 700, HEIGHT-80*i, p);
	        }
	        
	        if(MainActivity.list2.size()>=11){
	        	 for(int i = MainActivity.list1.size()-11,m=0; i <MainActivity.list2.size()&&(m<11); i++,m++){
	        		 
	        		 
	        	 if(d[i]>28){	 
	        	canvas.drawText((String) ((List) MainActivity.list2).get(i),100,180+80*m-5, p1);
	        	canvas.drawText(String.valueOf(d[i])+"��",600,180+80*m-5, p1);
	        		 }
	        	 if(d[i]<26){
	 	        	canvas.drawText((String) ((List) MainActivity.list2).get(i),100,180+80*m-5, p3);
		        	canvas.drawText(String.valueOf(d[i])+"��",600,180+80*m, p3);
	        	 
	        	 }
	        	 if((d[i]<=28)&&(d[i]>=26)){
	 	        	canvas.drawText((String) ((List) MainActivity.list2).get(i),100,180+80*m-5, p2);
		        	canvas.drawText(String.valueOf(d[i])+"��",600,180+80*m-5, p2);	 
	        	 }
	        	}
	        	
	        }
	        else
	        {
	        	for(int i = 0; i <MainActivity.list2.size(); i++){
	        		if(d[i]>28){
		        	canvas.drawText((String) ((List) MainActivity.list2).get(i),100,180+80*i-5, p1);
		        	canvas.drawText(String.valueOf(d[i])+"��",600,180+80*i-5, p1);
	        		}
	
		        	if(d[i]<26){
		        	canvas.drawText((String) ((List) MainActivity.list2).get(i),100,180+80*i-5, p3);
		        	canvas.drawText(String.valueOf(d[i])+"��",600,180+80*i-5, p3);
		        	}
	
	        		if((d[i]<=28)&&(d[i]>=26)){
		        	canvas.drawText((String) ((List) MainActivity.list2).get(i),100,180+80*i-5, p2);
		        	canvas.drawText(String.valueOf(d[i])+"��",600,180+80*i-5, p2);
	
	        	}
	        
	        
	           }
	        }
	        
	        holder.unlockCanvasAndPost(canvas);  //�������� ��ʾ����Ļ��
	        holder.lockCanvas(new Rect(0,0,0,0)); //�����ֲ���������ط������ı�
	        holder.unlockCanvasAndPost(canvas); 
	    }

	public void data_recording_back(View v) { // ������ ���ذ�ť
		this.finish();
	}
}
