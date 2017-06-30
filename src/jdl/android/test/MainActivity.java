package jdl.android.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	// Message types sent from the BluetoothChatService Handler
	public static final int MESSAGE_STATE_CHANGE = 1;
	public static final int MESSAGE_READ = 2;
	public static final int MESSAGE_WRITE = 3;
	public static final int MESSAGE_DEVICE_NAME = 4;
	public static final int MESSAGE_TOAST = 5;
	public static int temp4 = 18;
	public static int num = 0;
	public static List list = new ArrayList();
	public static Collection list1 = new ArrayList();
	public static Collection list2 = new ArrayList();

	// Key names received from the BluetoothChatService Handler
	public static final String DEVICE_NAME = "device_name";
	public static final String TOAST = "toast";

	// Intent request codes
	private static final int REQUEST_CONNECT_DEVICE = 1;
	private static final int REQUEST_ENABLE_BT = 2;
	private static final int REQUEST_NEW_CARD = 3;
	// public static String XLabel[]= null;
	// public static int[] Data;
	// public static String[] XLabel;

	String data1 = "0000";
	String data2 = "0000";
	String Button_Buffer = "00000000";
	String seekBar_BufferString = "82";
	String messege_String = "";

	// public static String[] XLabel={};
	public String[] Text = { "20", "21", "22", "23", "24", "25", "26", "27",
			"28", "29" };
	// Layout Views
	private TextView mTitle; // ����

	// Name of the connected device
	private String mConnectedDeviceName = null;
	// String buffer for outgoing messages
	private StringBuffer mOutStringBuffer;
	// Local Bluetooth adapter
	private BluetoothAdapter mBluetoothAdapter = null;
	// Member object for the chat services
	private BluetoothService mCardService = null;
	Context mContext = null;
	// G_
	final int HEIGHT1 = 700; // ���û�ͼ��Χ�߶�
	final int WIDTH1 = 800; // ��ͼ��Χ���
	SurfaceView surface1 = null;
	private SurfaceHolder holder1 = null; // ��ͼʹ�ã����Կ���һ��SurfaceView
	private int count = 0;
	private int data01;
	private int data11 = 0;
	private int data21 = 100;
	private String str = "";// �洢�����������ݣ��¶�ֵ��
	private String ss = "";// �ݴ浱ǰ���������ֽ�
	private Button but_connect;

	// _G
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.custom_title);
		surface1 = (SurfaceView) findViewById(R.id.show1);
		but_connect = (Button) findViewById(R.id.but_connect);

		Button bt1 = (Button) findViewById(R.id.bt1);
		bt1.setOnClickListener(new Bt1());

		Button bt4 = (Button) findViewById(R.id.bt4);
		bt4.setOnClickListener(new Bt4());

		// ��ʼ��SurfaceHolder����
		holder1 = surface1.getHolder();
		holder1.setFixedSize(WIDTH1 + 20, HEIGHT1 + 100); // ���û�����С��Ҫ��ʵ�ʵĻ�ͼλ�ô�һ��
		holder1.addCallback(new Callback() {
			public void surfaceChanged(SurfaceHolder holder1, int format,
					int width, int height) {
				Method.drawBack(holder1);
				// ���û����仰����ʹ���ڿ�ʼ���г���������Ļû�а�ɫ�Ļ�������
				// ֱ�����°�������Ϊ�ڰ������ж�drawBack(SurfaceHolder holder)�ĵ���
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder1) {
				// TODO Auto-generated method stub
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder holder1) {
				// TODO Auto-generated method stub
			}
		});

		mTitle = (TextView) findViewById(R.id.title_left_text); // ���Ӧ�ñ���
		mTitle.setText(R.string.app_name);
		mTitle = (TextView) findViewById(R.id.title_right_text); // �ұ�����״̬
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter == null) {
			Toast.makeText(this, "���������ã�", Toast.LENGTH_LONG).show();
			Log.e("MainActivity", "3");
			finish();
			return;

		}

		but_connect.setOnClickListener(new But_connect());

		/*
		 * ��ť2 ----�����ط�
		 */
		Button bt2 = (Button) findViewById(R.id.bt2);
		bt2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View paramView) {
				// �ж��Ƿ��Ѿ�����
				if (mCardService.getState() == BluetoothService.STATE_CONNECTED) {
					requestSend();
				} else// ��û������
				{
					Toast.makeText(getApplicationContext(),
							R.string.not_connected, Toast.LENGTH_SHORT).show();
				}
			}
		});

		/*
		 * ����¶���ʾֵ
		 */
		Button bt3 = (Button) findViewById(R.id.bt3);
		bt3.setOnClickListener(new OnClickListener() {

			TextView et3 = (TextView) findViewById(R.id.et3);// ��ʾ����¶�ֵ��TextView
			TextView et4 = (TextView) findViewById(R.id.et4);// ��ʾ���϶ȷ��ŵ�TextView
			TextView et5 = (TextView) findViewById(R.id.et5);// ��ʾ����¶�ֵ��TextView
			TextView et6 = (TextView) findViewById(R.id.et6);// ��ʾ���϶ȷ��ŵ�TextView

			@Override
			public void onClick(View paramView) {
				et3.setText("");
				et4.setText("");
				et5.setText("");
				et6.setText("");
				data11 = 0;
				data21 = 100;
				// Method.str[]={" "," "," "};
				temp4 = 0;
			}
		});

	}

	// �˴��ǽ��յ������ݴ���Ҳ���ǽ��ܵ������ݽ���ת������¶���ʾ������Ҫ��
	public String onReceiveMess(String data) {
		TextView et3 = (TextView) findViewById(R.id.et3);// ��ʾ�¶�ֵ��TextView
		TextView et4 = (TextView) findViewById(R.id.et4);// ��ʾ�¶�ֵ��TextView
		TextView et5 = (TextView) findViewById(R.id.et5);// ��ʾ���϶ȷ��ŵ�TextView
		TextView et6 = (TextView) findViewById(R.id.et6);// ��ʾ���϶ȷ��ŵ�TextView

		String data_str = new String(data);

		String temp_signal = new String("��");

		if (data.length() != 2) {// ���͵�����Ϊ2λ������Ϊ2λ���ݣ����������ط�����
			str = "";// �������
			ss = "";
			count = 0;

			requestSend();// �����ط�
		}

		// private int data01;
		// private int data11 = 0;
		// private int data21 = 100;
		if (data.length() == 2) {
			data01 = Integer.parseInt(data.trim());
			if (data01 < 44 && data01 > 29) {
				temp4 = data01;
			}

			list.add(temp4);

			SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
			SimpleDateFormat formatter1 = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String strBeginDate = formatter.format(new Date());
			String strBeginDate1 = formatter1.format(new Date());
			list1.add(strBeginDate);
			list2.add(strBeginDate1);
			Log.v("BreakPoint", "�õ���list���鳤��Ϊ��" + list.size());

			Method.drawBack(holder1);

			num++;

			if ((data11 < data01) && (data01 < 44)) {
				data11 = data01;
			}
			if ((data21 > data01) && (data01 > 29)) {
				data21 = data01;
			}
			data = String.valueOf(data01);
			data1 = String.valueOf(data11);
			data2 = String.valueOf(data21);

			et3.setText(data1);
			et5.setText(temp_signal);
			et4.setText(data2);
			et6.setText(temp_signal);

		}
		return data;
	}

	// �豸���Ӱ�ť
	class But_connect implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent serverIntent = new Intent(MainActivity.this,
					DeviceListActivity.class);
			startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);

		}
	}

	@Override
	public void onStart() {
		// G
		Toast.makeText(getApplicationContext(), "����ʼִ��", Toast.LENGTH_SHORT)
				.show();

		super.onStart();
		// ȷ�������򿪣�Ȼ���ȡ��Ƭ��Ϣ��ʾ�ڽ�Ŀ�ϣ�δ�����ڷ�����Ϣ������setupCards()��
		if (!mBluetoothAdapter.isEnabled()) {
			Intent enableIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
			// Otherwise, setup the chat session
		} else {
			if (mCardService == null)
				setupCards(); // ��ʼ��ListView����
		}
	}

	@Override
	public synchronized void onResume() {
		super.onResume();
		// Performing this check in onResume() covers the case in which BT was
		// not enabled during onStart(), so we were paused to enable it...
		// onResume() will be called when ACTION_REQUEST_ENABLE activity
		// returns.
		if (mCardService != null) {
			// Only if the state is STATE_NONE, do we know that we haven't
			// started already
			if (mCardService.getState() == BluetoothService.STATE_NONE) {
				// Start the Bluetooth chat services
				mCardService.start();
			}
		}
	}

	private void setupCards() {
		// �����ʾ���ݺ󣬳�ʼ�������������
		mCardService = new BluetoothService(mHandler);
		// Initialize the buffer for outgoing messages
		setmOutStringBuffer(new StringBuffer(""));
	}

	public void onDestroy() {
		super.onDestroy();
		// Stop the Bluetooth chat services
		if (mCardService != null) {
			mCardService.stop();
		}
	}

	// ���������ɼ�
	private void ensureDiscoverable() {
		if (mBluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
			Intent discoverableIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			discoverableIntent.putExtra(
					BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
			startActivity(discoverableIntent);
		}
	}

	// �����̷߳�����Ϣ
	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Log.v("BreakPoint", "����handleMessage�߳�");
			switch (msg.what) {
			case MESSAGE_STATE_CHANGE:
				Log.v("BreakPoint", "���״̬�ı�");
				Toast.makeText(MainActivity.this, "change", Toast.LENGTH_LONG);
				switch (msg.arg1) {
				case BluetoothService.STATE_CONNECTED:
					mTitle.setText(R.string.title_connected_to);
					mTitle.append(mConnectedDeviceName);
					// mConversationArrayAdapter.clear();
					break;
				case BluetoothService.STATE_CONNECTING:
					mTitle.setText(R.string.title_connecting);
					break;
				case BluetoothService.STATE_LISTEN:
				case BluetoothService.STATE_NONE:
					mTitle.setText(R.string.title_not_connected);
					break;
				}
				break;
			// ��������Ϣ����ʾ�ڱ�����Ļ�ϣ���д����ʾ������ɣ�
			case MESSAGE_WRITE:

				break;
			// �յ�����Ϣ����ʾ�ڱ�����Ļ�ϣ���д�����뵽ͨ��¼�У�
			case MESSAGE_READ:

				byte[] readBuf = (byte[]) msg.obj;

				// G
				Log.v("BreakPoint", "�ѽ��յ�������ת��ΪByte����");
				// ��һ���������ֽ����飬�ڶ���Ϊƫ�������������Ǵӵ�һ��λ��д��ģ��������������ǳ��ȡ�
				ss = new String(readBuf, 0, msg.arg1);
				// System.out.print(ss);
				count++;// ����
				/*
				 * ˲���¶ȱ仯������ܻ�Ӱ������ԭ��Ŀǰ�������������̵߳ĵ����й�ϵ
				 */

				char end_char = ss.charAt(0);// String����תΪchar����ȡ��һ��char�����������ֹͣλ�жϵġ�
				// ��������ת����String���ͣ���String���ͣ��޷���ȷ�Ƚ��ַ��Ƿ���ͬ��
				Log.v("BreakPoint", "ֹͣλ�ж�");
				if (end_char == 'C') {

					onReceiveMess(str);

					str = "";
					count = 0;
					ss = "";
					Log.v("BreakPoint", "��������");
					// requestSend();

				}

				str = str + ss;
				break;
			case MESSAGE_DEVICE_NAME:
				// save the connected device's name
				mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
				Toast.makeText(getApplicationContext(),
						"�ѳɹ����ӵ� " + mConnectedDeviceName, Toast.LENGTH_SHORT)
						.show();
				// G
				requestSend();// �������ӳɹ��������ط�
				break;
			case MESSAGE_TOAST:
				Toast.makeText(getApplicationContext(),
						msg.getData().getString(TOAST), Toast.LENGTH_SHORT)
						.show();
				break;
			}
		}

	};

	// ��������Activity�ķ��ؽ��
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case REQUEST_CONNECT_DEVICE:
			// ��������豸�б�󷵻���ָ���豸��MAC��ַ�����ݴ�MAC������������
			if (resultCode == Activity.RESULT_OK) {
				// Get the device MAC address
				String address = data.getExtras().getString(
						DeviceListActivity.EXTRA_DEVICE_ADDRESS);
				// Get the BLuetoothDevice object
				BluetoothDevice device = mBluetoothAdapter
						.getRemoteDevice(address);
				// Attempt to connect to the device
				mCardService.connect(device);
			}
			break;
		case REQUEST_ENABLE_BT:
			// ��ȷ�ϴ�����Activity���أ����򿪳ɹ���ʼ����Ƭ�б�
			if (resultCode == Activity.RESULT_OK) {
				// Bluetooth is now enabled, so set up a chat session
				setupCards();
			} else {
				// User did not enable Bluetooth or an error occured
				Toast.makeText(this, R.string.bt_not_enabled_leaving,
						Toast.LENGTH_SHORT).show();
				finish();
			}
			break;
		case REQUEST_NEW_CARD:
			// ���������ƬActivity���أ����������card���¶�ȡ����ʾ
			if (resultCode == Activity.RESULT_OK) {
				setupCards();
			}
			break;
		}
	}

	/*
	 * ͨ�����������ֽ����� �����ֽ�Ҳ����
	 */

	private void sendByte(byte[] data) {
		// ����Ƿ��Ѿ�����
		if (mCardService.getState() != BluetoothService.STATE_CONNECTED) {
			Toast.makeText(this, R.string.not_connected, Toast.LENGTH_SHORT)
					.show();
			return;
		}
		// ���ﻹ���Լ�������ݵĴ���������Ϣ���ȣ�
		byte[] send = data;
		mCardService.write(send);
		// Reset out string buffer to zero and clear the edit text field
		mOutStringBuffer.setLength(0);
		Log.v("BreakPoint", "������������");
	}

	/*
	 * �����ݲ���ȷ�������ط�
	 */
	private void requestSend() {
		String tmp = "a";
		byte[] arq;
		arq = tmp.getBytes();
		sendByte(arq);
		Log.v("BreakPoint", "�����ط�");
	}

	/*
	 * �����յ�����Ϣ �¶�����Ԥ��Ϊֻ����λ������
	 */

	public void setmOutStringBuffer(StringBuffer mOutStringBuffer) {
		this.mOutStringBuffer = mOutStringBuffer;
	}

	public StringBuffer getmOutStringBuffer() {
		return mOutStringBuffer;
	}

	// ������ϸ
	class Bt1 implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent serverIntent = new Intent(MainActivity.this,
					Data_Recording.class);
			startActivity(serverIntent);
			MainActivity.this.onPause();
		}
	}

	// ��ʾ����
	class Bt4 implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent serverIntent = new Intent(MainActivity.this,
					Waveform_Display.class);
			startActivity(serverIntent);
			MainActivity.this.onPause();
		}
	}

}