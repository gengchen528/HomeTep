package jdl.android.test;

import jdl.android.test.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends Activity{
	private EditText mUser; // �ʺű༭��
	private EditText mPassword; // ����༭��
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		mUser = (EditText) findViewById(R.id.login_user_edit);
		mPassword = (EditText) findViewById(R.id.login_passwd_edit);

	}
	public void login_mainweixin(View v) {
		if ("admin".equals(mUser.getText().toString()) && "admin".equals(mPassword.getText().toString())) // �ж� �ʺź�����
		{
			Intent intent = new Intent();
			intent.setClass(Login.this,MainActivity.class);
			startActivity(intent);
			// Toast.makeText(getApplicationContext(), "��¼�ɹ�", Toast.LENGTH_SHORT).show();
		} 
  }
	public void login_back(View v) { // ������ ���ذ�ť
		this.finish();
	}

	
	

}
