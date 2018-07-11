package com.example.wangyajie.myapplication;



        import android.app.Activity;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.GestureDetector;
        import android.view.GestureDetector.OnGestureListener;
        import android.view.Gravity;
        import android.view.LayoutInflater;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.PopupWindow;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends Activity {
    protected static final float FLIP_DISTANCE = 50;
    GestureDetector mDetector;
    private TextView mybut,mybut1; // 定义按钮
    private PopupWindow popWin = null; // 弹出窗口
    private View popView = null; // 保存弹出窗口布局
    private boolean show=true;
    private TextView tv1,tv2,tv3;

    private MyBtnClicker myBtnClicker = new MyBtnClicker();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //弹出
        popView= LayoutInflater.from(MainActivity.this).inflate(R.layout.cztest_popwin, null);
//        MainActivity.this.popView = inflater.inflate(
//                R.layout.cztest_popwin, null); // 读取布局管理器
        popWin = new PopupWindow(popView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true); // 实例化PopupWindow
        // 设置PopupWindow的弹出和消失效果
//        MainActivity.this.popWin
//                .setAnimationStyle(R.style.popupAnimation);

        popWin.setAnimationStyle(R.style.popupAnimation);

        //设置各个控件的点击响应
        tv1 = (TextView)popView.findViewById(R.id.pop_computer);
        tv2 = (TextView)popView.findViewById(R.id.pop_financial);
        tv3 = (TextView)popView.findViewById(R.id.pop_manage);
        tv1.setOnClickListener(myBtnClicker);
        tv2.setOnClickListener(myBtnClicker);
        tv3.setOnClickListener(myBtnClicker);

//滑动监听
        mDetector = new GestureDetector(this, new OnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                // TODO Auto-generated method stub

            }

            /**
             *
             * e1 The first down motion event that started the fling. e2 The
             * move motion event that triggered the current onFling.
             */
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                String aa= String.valueOf((int) e1.getX());
                String yy=String.valueOf((int) e1.getY());
//                Toast.makeText(MainActivity.this,aa+yy,Toast.LENGTH_SHORT).show();
                if (e1.getX() - e2.getX() > FLIP_DISTANCE) {
                    Log.i("MYTAG", "向左滑...");
                    Toast.makeText(MainActivity.this, aa,Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (e2.getX() - e1.getX() > FLIP_DISTANCE) {
                    Log.i("MYTAG", "向右滑...");
                    Toast.makeText(MainActivity.this,"向右滑",Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (e1.getY() - e2.getY() > FLIP_DISTANCE&&(int) e1.getY()>1900) {
                    Log.i("MYTAG", "向上滑...");
                    Toast.makeText(MainActivity.this,yy,Toast.LENGTH_SHORT).show();

                    if (show==true)
                    {

//设置焦点false
                        popWin.setFocusable(false);
                        popWin.showAtLocation(MainActivity.this.popView, Gravity.NO_GRAVITY, 0, 190); // 显示弹出窗口
//                        popWin.showAtLocation(View parent, int gravity, int x, int y)
                        show=false;
                    }
                    else
                    {
//                      popWin.setAnimationStyle(R.style.popupAnimation);
                        popWin.dismiss();
                        show=true;
                    }

                    return true;
                }
                if (e2.getY() - e1.getY() > FLIP_DISTANCE) {
                    Log.i("MYTAG", "向下滑...");
                    Toast.makeText(MainActivity.this,"向下滑",Toast.LENGTH_SHORT).show();
                    return true;
                }

                Log.d("TAG", e2.getX() + " " + e2.getY());

                return false;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                // TODO Auto-generated method stub
                return false;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    private class MyBtnClicker implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
//                case R.id.mybut:
//                    if (show==true)
//                    {
//
////设置焦点false
//                        popWin.setFocusable(false);
//                        popWin.showAtLocation(view, Gravity.NO_GRAVITY, 0, 190); // 显示弹出窗口
////                        popWin.showAtLocation(View parent, int gravity, int x, int y)
//                        show=false;
//                    }
//                    else
//                    {
////                      popWin.setAnimationStyle(R.style.popupAnimation);
//                        popWin.dismiss();
//                        show=true;
//                    }
//                    break;
//
//                case R.id.mybut1:
//                    mybut1.setBackgroundColor(Color.BLUE);
//
//                    break;
                case R.id.pop_computer:
//                    Toast.makeText(this,"clicked computer",Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "这是电脑", Toast.LENGTH_LONG).show();
                    break;
                case R.id.pop_financial:
//                    Toast.makeText(this,"clicked computer",Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "这是你好", Toast.LENGTH_LONG).show();
                    break;
                case R.id.pop_manage:
//                    Toast.makeText(this,"clicked computer",Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "这是哈哈", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
}

