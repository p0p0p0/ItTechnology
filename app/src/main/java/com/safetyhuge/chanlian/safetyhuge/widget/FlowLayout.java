package com.safetyhuge.chanlian.safetyhuge.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by F57 on 2016/9/28 0028.
 */
public class FlowLayout  extends ViewGroup {

    private int horizontalSpacing = 15;//水平间距
    private int verticalSpacing = 15;//行与行之间的垂直间距
    private ArrayList<Line> lineList = new ArrayList<Line>();
    public int height;
    private int width;

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //设置水平间距
    public void setHorizontalSpacing(int horizontalSpacing) {
        this.horizontalSpacing = horizontalSpacing;
    }
    /**
     * 设置垂直间距
     * @param verticalSpacing
     */
    public void setVerticalSpacing(int verticalSpacing) {
        this.verticalSpacing = verticalSpacing;
    }
    /**
     * 分行：遍历所有的子View，判断哪几个子View在同一行(排座位表)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //每次走onMEasure的方法都会进行测量
      //  lineList.clear();
        //获取FlowLayout的宽度
        width = MeasureSpec.getSize(widthMeasureSpec);
        //2.获取用于实际比较的宽度，就是除去2边的padding的宽度
        int noPaddingWidth = width -getPaddingLeft()-getPaddingRight();
        //3.遍历所有的子View，拿子View的宽和noPaddingWidth进行比较
        Line line = new Line();
        for(int i=0;i<getChildCount();i++) {
            View ChildView = getChildAt(i);
            //保证测量拿到宽高
            ChildView.measure(0, 0);
            //4.如果当前line中木有子View，则不用比较直接放入line中，因为要保证每行至少有一个子View;
            if (line.getViewList().size() == 0) {
                //直接存入
                line.addLineView(ChildView);
                //5.如果当前line的宽+水平间距+子View的宽大于noPaddingWidth,则child需要换行
            } else if (line.getLineWidth() + horizontalSpacing + ChildView.getMeasuredWidth() > noPaddingWidth) {
                lineList.add(line);

                line = new Line();
                line.addLineView(ChildView);
            } else {
                //6.说明当前child应该放入当前Line中
                line.addLineView(ChildView);
            }
            //7.如果当前child是最后的子View，那么需要保存最后的line对象
            if (i == (getChildCount() - 1)) {
                lineList.add(line);
            }
        }
            //计算FLowLayout需要的高度
            height = getPaddingTop()+getPaddingTop();
            for(int i =0;i<lineList.size();i++){
                height += lineList.get(i).getLineHeight();
            }
            height +=(lineList.size()-1)*verticalSpacing;
       setMeasuredDimension(width,height);
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for(int i=0;i<lineList.size();i++){
            Line line = lineList.get(i);
            //从第二行开始，每行的top总是比上一行的top多一个行高和垂直间距
            if(i>0){
                paddingTop+=verticalSpacing+lineList.get(i-1).getLineHeight();
            }
            ArrayList<View> viewList = line.getViewList();
            //1.获取留白的宽度
            int remainSpacing = getLineRemainSpacing(line);
            //2.计算每个view平均得到的值
            int paddingSpacing = remainSpacing/viewList.size();
            for(int j=0;j<viewList.size();j++){
                View ChildView = viewList.get(j);
                //3.将得到的perSpacing增加到view的宽度上面
                int widthSpec = MeasureSpec.makeMeasureSpec((ChildView.getMeasuredWidth()+paddingSpacing), MeasureSpec.EXACTLY);
                ChildView.measure(widthSpec,0);
                if(j==0){
                    ChildView.layout(paddingLeft,paddingTop,paddingLeft
                            +ChildView.getMeasuredWidth(),paddingTop+ChildView.getMeasuredHeight());
                }else{
                    //如果不是第一个，需要参考前一个view的right
                    View preView = viewList.get(j-1);
                    //当前view的left是前一个view的right+水平间距
                    int left = preView.getRight()+horizontalSpacing;
                    ChildView.layout(left,preView.getTop(),left+
                            ChildView.getMeasuredWidth(),preView.getBottom());
                }
            }

        }
    }
    /**
     * 获取指定line的留白
     * @param line
     * @return
     */
    private int getLineRemainSpacing(Line line){
        return getMeasuredWidth()-getPaddingLeft()-getPaddingRight()-line.getLineWidth();
    }
    class Line{
        private ArrayList<View> viewList;
        private int width;//表示所有子View的宽+水平间距
        private int height;//行的高度

        public Line(){
            viewList = new ArrayList<View>();
        }
        /**
         * 记录子VIew
         * @param child
         */
        public void addLineView(View child) {
            //1.更新Line的width
            if (!viewList.contains(child)) {
                viewList.add(child);
                //1.更新Line的width
                if (viewList.size() == 1) {
                    //说明添加的是第一个子View，那么line的宽就是子view的宽度
                    width = child.getMeasuredWidth();
                }else{
                    //如果添加的不是第一个子View，那么应该加等于水平间距和子VIew的宽度
                    width+=child.getMeasuredWidth()+horizontalSpacing;
                }
                //更新line中的height
                height = Math.max(height,child.getMeasuredHeight());
            }
        }
            /**
             * 获取当前行的宽度
             * @return
             */
            public int getLineWidth(){
                return width;
            }
            /**
             * 获取当前行的高度
             * @return
             */
            public int getLineHeight(){
                return height;
            }
            /**
             * 获取当前行的所有的子View
             * @return
             */
            public ArrayList<View> getViewList(){
                return viewList;
            }
        }
}
