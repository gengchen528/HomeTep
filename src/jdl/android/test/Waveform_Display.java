package jdl.android.test;

import java.util.List;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;

public class Waveform_Display extends Activity {
	final int HEIGHT = 900; // ���û�ͼ��Χ�߶�
	final int WIDTH = 800; // ��ͼ��Χ���
	final int Y_start = 20; // ��ͼ��Χ���
	final int Y_scale = 60; // ��ͼ��Χ���
	SurfaceView surface = null;
	final int X_OFFSET = 60; // x�ᣨԭ�㣩��ʼλ��ƫ�ƻ�ͼ��Χһ��
	private int cx = X_OFFSET; // ʵʱx������
	int centerY = HEIGHT / 2; // y���λ��
	// public String[] XLabel;
	// public String[] XLabel={"10:01","10:26","10:55","11:23","11:47","12:10"};
	// public int[] Data={23,24,25,26,27,26};
	public static int Data[];
	int d[] = new int[100];
	public String[] YLabel = { "30", "31", "32", "33", "34", "35", "36", "37",
			"38", "39", "40", "41", "42", "43" };

	private SurfaceHolder holder = null; // ��ͼʹ�ã����Կ���һ��SurfaceView

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.waveform_display);
		surface = (SurfaceView) findViewById(R.id.show);
		// ��ʼ��SurfaceHolder����
		holder = surface.getHolder();
		holder.setFixedSize(WIDTH + 20, HEIGHT + 100); // ���û�����С��Ҫ��ʵ�ʵĻ�ͼλ�ô�һ��
		holder.addCallback(new Callback() {
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				drawBack(holder);
				// ���û����仰����ʹ���ڿ�ʼ���г���������Ļû�а�ɫ�Ļ�������
				// ֱ�����°�������Ϊ�ڰ������ж�drawBack(SurfaceHolder holder)�ĵ���
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

	private void drawBack(SurfaceHolder holder) {
		Canvas canvas = holder.lockCanvas(); // ��������
		// ���ư�ɫ����
		canvas.drawColor(Color.WHITE);
		Paint p = new Paint();
		Paint p1 = new Paint();
		Paint p2 = new Paint();
		Paint p3 = new Paint();
		Paint p4 = new Paint();
		p.setColor(Color.MAGENTA);
		p1.setColor(Color.BLACK);
		p2.setColor(Color.BLUE);
		p3.setColor(Color.RED);
		// p.setColor(Color.BLACK);
		p.setTextSize(30);// ���������С
		p1.setTextSize(30);// ���������С
		p2.setTextSize(30);// ���������С
		p3.setTextSize(30);// ���������С
		p4.setTextSize(20);// ���������С
		p.setStrokeWidth(3);
		p1.setStrokeWidth(3);
		p2.setStrokeWidth(3);
		p3.setStrokeWidth(3);
		p4.setStrokeWidth(2);
		p.setAntiAlias(true);// ���û��ʵľ��Ч���� true��ȥ�������һ��Ч����������
		p3.setAntiAlias(true);// ���û��ʵľ��Ч���� true��ȥ�������һ��Ч����������
		// ����������
		canvas.drawLine(X_OFFSET, HEIGHT, WIDTH, HEIGHT, p1); // ����X�� ǰ�ĸ���������ʼ����
		canvas.drawLine(X_OFFSET, 20, X_OFFSET, HEIGHT, p1); // ����Y�� ǰ�ĸ���������ʼ����

		canvas.drawText("��", X_OFFSET + 20, Y_start + 20, p1);
		// ����Y���ͷ
		Path path = new Path();
		path.moveTo(X_OFFSET, Y_start - 10);// �˵�Ϊ����ε����
		path.lineTo(X_OFFSET - 8, Y_start + 20);
		path.lineTo(X_OFFSET + 8, Y_start + 20);
		path.close(); // ʹ��Щ�㹹�ɷ�յĶ����
		canvas.drawPath(path, p1);

		for (int i = 0; i < YLabel.length; i++) {
			canvas.drawLine(X_OFFSET, (HEIGHT - Y_scale * (i + 1)),
					X_OFFSET + 15, (HEIGHT - Y_scale * (i + 1)), p1);
			canvas.drawText(YLabel[i], X_OFFSET - 50, (HEIGHT - Y_scale
					* (i + 1)) + 10, p1);
		}

		canvas.drawText("t", WIDTH - 10, HEIGHT - 20, p1);
		// canvas.drawLine(X_OFFSET,YCoord(29), X_OFFSET+Y_scale*12, YCoord(29),
		// p3);
		canvas.drawLine(X_OFFSET, YCoord(38), X_OFFSET + Y_scale * 12,
				YCoord(38), p3);
		// canvas.drawLine(X_OFFSET,YCoord(25), X_OFFSET+Y_scale*12, YCoord(25),
		// p2);
		canvas.drawLine(X_OFFSET, YCoord(36), X_OFFSET + Y_scale * 12,
				YCoord(36), p2);
		// ����X���ͷ
		Path path1 = new Path();
		path1.moveTo(WIDTH + 10, HEIGHT);// �˵�Ϊ����ε����
		path1.lineTo(WIDTH - 20, HEIGHT + 8);
		path1.lineTo(WIDTH - 20, HEIGHT - 8);
		path1.close(); // ʹ��Щ�㹹�ɷ�յĶ����
		canvas.drawPath(path1, p1);
		Log.v("BreakPoint", "�Ѿ����XY���ͼ");
		// Log.v("BreakPoint","�õ���list���鳤��Ϊ��"+MainActivity.list.size());
		// Log.v("BreakPoint","�õ���list�ĵ�һ��ֵΪ��"+MainActivity.list.get(1));
		Object obj[] = MainActivity.list.toArray();
		Log.v("BreakPoint", "��õ���������" + obj.length);
		for (int x = 0; x < obj.length; x++) {

			d[x] = Integer.parseInt(String.valueOf(obj[x]));
			;

			Log.v("BreakPoint", "��õ���������" + d[x]);
		}

		if (MainActivity.list1.size() >= 13) {
			for (int i = MainActivity.list1.size() - 13, m = 0; i < MainActivity.list1
					.size() && (m < 13); i++, m++) {

				canvas.drawLine(X_OFFSET + Y_scale * m, HEIGHT, X_OFFSET
						+ Y_scale * m, HEIGHT - 15, p);
				canvas.drawText((String) ((List) MainActivity.list1).get(i),
						X_OFFSET + Y_scale * m - 20, HEIGHT + 30, p4);

				if (m > 0) {
					canvas.drawLine(X_OFFSET + (m - 1) * Y_scale,
							YCoord(d[i - 1]), X_OFFSET + m * Y_scale,
							YCoord(d[i]), p1);
				}
				if (d[i] > 37) {
					canvas.drawCircle(X_OFFSET + m * Y_scale, YCoord(d[i]), 8,
							p3);
					canvas.drawText(String.valueOf(d[i]), X_OFFSET + m
							* Y_scale - 15, YCoord(d[i]) - 15, p3);
					for (int n = 0; n < (HEIGHT - YCoord(d[i])) / 40; n++) {
						canvas.drawLine(X_OFFSET + m * Y_scale,
								HEIGHT - 40 * n, X_OFFSET + m * Y_scale, HEIGHT
										- 40 * n - 30, p3);
					}
				}
				if (d[i] < 37) {
					canvas.drawCircle(X_OFFSET + m * Y_scale, YCoord(d[i]), 8,
							p2);
					canvas.drawText(String.valueOf(d[i]), X_OFFSET + m
							* Y_scale - 15, YCoord(d[i]) - 15, p2);
					for (int n = 0; n < (HEIGHT - YCoord(d[i])) / 40; n++) {
						canvas.drawLine(X_OFFSET + m * Y_scale,
								HEIGHT - 40 * n, X_OFFSET + m * Y_scale, HEIGHT
										- 40 * n - 30, p2);
					}
				}
				if (d[i] == 37) {
					canvas.drawCircle(X_OFFSET + m * Y_scale, YCoord(d[i]), 8,
							p);
					canvas.drawText(String.valueOf(d[i]), X_OFFSET + m
							* Y_scale - 15, YCoord(d[i]) - 15, p);
					for (int n = 0; n < (HEIGHT - YCoord(d[i])) / 40; n++) {
						canvas.drawLine(X_OFFSET + m * Y_scale,
								HEIGHT - 40 * n, X_OFFSET + m * Y_scale, HEIGHT
										- 40 * n - 30, p);
					}
				}
			}
		} else {
			for (int i = 0; i < MainActivity.list1.size(); i++) {
				canvas.drawLine(X_OFFSET + Y_scale * 2 * i, YCoord(38),
						X_OFFSET + Y_scale * (2 * i + 1) + 40, YCoord(38), p3);
				canvas.drawLine(X_OFFSET + Y_scale * 2 * i, YCoord(36),
						X_OFFSET + Y_scale * (2 * i + 1) + 40, YCoord(36), p2);
				canvas.drawLine(X_OFFSET + Y_scale * i, HEIGHT, X_OFFSET
						+ Y_scale * i, HEIGHT - 15, p);
				canvas.drawText((String) ((List) MainActivity.list1).get(i),
						X_OFFSET + Y_scale * i - 20, HEIGHT + 30, p4);
				if (i > 0) {
					canvas.drawLine(X_OFFSET + (i - 1) * Y_scale,
							YCoord(d[i - 1]), X_OFFSET + i * Y_scale,
							YCoord(d[i]), p1);
				}
				if (d[i] > 37) {
					canvas.drawCircle(X_OFFSET + i * Y_scale, YCoord(d[i]), 8,
							p3);
					canvas.drawText(String.valueOf(d[i]), X_OFFSET + i
							* Y_scale - 15, YCoord(d[i]) - 15, p3);
					for (int n = 0; n < (HEIGHT - YCoord(d[i])) / 40; n++) {
						canvas.drawLine(X_OFFSET + i * Y_scale,
								HEIGHT - 40 * n, X_OFFSET + i * Y_scale, HEIGHT
										- 40 * n - 30, p3);
					}
				}
				if (d[i] < 37) {
					canvas.drawCircle(X_OFFSET + i * Y_scale, YCoord(d[i]), 8,
							p2);
					canvas.drawText(String.valueOf(d[i]), X_OFFSET + i
							* Y_scale - 15, YCoord(d[i]) - 15, p2);
					for (int n = 0; n < (HEIGHT - YCoord(d[i])) / 40; n++) {
						canvas.drawLine(X_OFFSET + i * Y_scale,
								HEIGHT - 40 * n, X_OFFSET + i * Y_scale, HEIGHT
										- 40 * n - 30, p2);
					}
				}
				if (d[i] == 37) {
					canvas.drawCircle(X_OFFSET + i * Y_scale, YCoord(d[i]), 8,
							p);
					canvas.drawText(String.valueOf(d[i]), X_OFFSET + i
							* Y_scale - 15, YCoord(d[i]) - 15, p);
					for (int n = 0; n < (HEIGHT - YCoord(d[i])) / 40; n++) {
						canvas.drawLine(X_OFFSET + i * Y_scale,
								HEIGHT - 40 * n, X_OFFSET + i * Y_scale, HEIGHT
										- 40 * n - 30, p);
					}
				}
			}

		}
		holder.unlockCanvasAndPost(canvas); // �������� ��ʾ����Ļ��
		holder.lockCanvas(new Rect(0, 0, 0, 0)); // �����ֲ���������ط������ı�
		holder.unlockCanvasAndPost(canvas);

	}

	// ����ָ���¶ȵ������
	private int YCoord(int m) {
		// TODO Auto-generated method stub
		Log.v("BreakPoint", "���ǰ˸��ϵ�");
		int y = m - 29;

		return HEIGHT - Y_scale * y;
	}

	public void waveform_display_back(View v) { // ������ ���ذ�ť

		this.finish();
	}

}