package jdl.android.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Explain extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.explain);
	}
//	public void explain_back(View v) { // ������ ���ذ�ť
//		Intent intent = new Intent();
//		intent.setClass(Explain.this,Welcome.class);
//		startActivity(intent);
//	}
	public void explain_back(View v) { // ������ ���ذ�ť
		this.finish();
	}
}
