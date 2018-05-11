package com.cqu.stuexpress.ui.homepage;

import android.graphics.Rect;
import android.os.Handler;

import com.cqu.stuexpress.R;
import com.cqu.stuexpress.bean.ImageAdapter;
import com.cqu.stuexpress.bean.ImageLoader;
import com.cqu.stuexpress.bean.Img;
import com.cqu.stuexpress.bean.MyNineGridBean;
import com.cqu.stuexpress.ui.base.TitleBarActivity;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.PermissionManager;
import com.lwkandroid.widget.ninegridview.NineGirdImageContainer;
import com.lwkandroid.widget.ninegridview.NineGridBean;
import com.lwkandroid.widget.ninegridview.NineGridView;
import com.previewlibrary.GPreviewBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 发布农料农具需求
 */

public class WantBuyActivity extends TitleBarActivity {

    NineGridView mNineGridView;
    ImageAdapter mAdapter;
    List<NineGridBean> mData;

    List<Img> mUrls;

    @Override
    public String getUITitle() {
        return "我要买";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_want_buy;
    }

    @Override
    public void init() {

        mNineGridView = (NineGridView) findViewById(R.id.ngiv);

        mData = new ArrayList<>();

        mUrls = new ArrayList<>();
        mUrls.add(new Img("http://img2.imgtn.bdimg.com/it/u=2895155641,2911373735&fm=27&gp=0.jpg"));
        mUrls.add(new Img("http://img4.imgtn.bdimg.com/it/u=3127839793,3439179457&fm=27&gp=0.jpg"));
        mUrls.add(new Img("http://img1.imgtn.bdimg.com/it/u=1853098039,1941730104&fm=27&gp=0.jpg"));
        mUrls.add(new Img("http://img5.imgtn.bdimg.com/it/u=1193554729,3776934764&fm=200&gp=0.jpg"));

        mUrls.add(new Img("http://img4.imgtn.bdimg.com/it/u=104752792,2577417750&fm=27&gp=0.jpg"));
        mUrls.add(new Img("http://img3.imgtn.bdimg.com/it/u=3531574659,27488658&fm=27&gp=0.jpg"));

        for (int i = 0; i < mUrls.size(); i++) {
            MyNineGridBean bean = new MyNineGridBean(mUrls.get(i).getUrl(), mUrls.get(i).getUrl(), mUrls.get(i));
            mData.add(bean);
        }

        mAdapter = new ImageAdapter();

        //设置图片加载器，这个是必须的，不然图片无法显示
        mNineGridView.setImageLoader(new ImageLoader());
        //设置显示列数，默认3列
        mNineGridView.setColumnCount(4);
        //设置是否为编辑模式，默认为false
        mNineGridView.setIsEditMode(true);
        //设置单张图片显示时的尺寸，默认100dp
        mNineGridView.setSingleImageSize(150);
        //设置单张图片显示时的宽高比，默认1.0f
        mNineGridView.setSingleImageRatio(1f);
        //设置最大显示数量，默认9张
        mNineGridView.setMaxNum(10);
        //设置图片显示间隔大小，默认3dp
        mNineGridView.setSpcaeSize(3);
        //设置“+”号的图片
        mNineGridView.setIcAddMoreResId(R.drawable.ic_ninegrid_addmore);
        //设置各类点击监听
        mNineGridView.setOnItemClickListener(new NineGridView.onItemClickListener() {
            @Override
            public void onNineGirdAddMoreClick(int cha) {

                //编辑模式下，图片展示数量尚未达到最大数量时，会显示一个“+”号，点击后回调这里
                try {
                    takePhoto.onPickMultiple(10);
                } catch (Exception e) {
                    myTool.log("Error!");
                }

            }

            @Override
            public void onNineGirdItemClick(final int position, NineGridBean gridBean, NineGirdImageContainer imageContainer) {
                //点击图片的监听
                //在你点击时，调用computeBoundsBackward（）方法
                Rect bounds = new Rect();
                Img img = (Img) gridBean.getT();
                imageContainer.getImageView().getGlobalVisibleRect(bounds);
                img.setBounds(bounds);

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        GPreviewBuilder.from(WantBuyActivity.this)
                                .setData(mUrls)
                                .setCurrentIndex(position)
                                .setType(GPreviewBuilder.IndicatorType.Number)
                                .start();
                    }
                });
            }

            @Override
            public void onNineGirdItemDeleted(int position, NineGridBean gridBean, NineGirdImageContainer imageContainer) {
                //编辑模式下，某张图片被删除后回调这里
            }
        });
        mNineGridView.setDataList(mData);

    }

    Handler mHandler = new Handler();


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    InvokeParam invokeParam;

    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        mUrls.clear();
        List<TImage> imgs = result.getImages();
        for (TImage image : imgs)
            mUrls.add(new Img(image.getPath()));

        mData.clear();
        for (int i = 0; i < mUrls.size(); i++) {
            MyNineGridBean bean = new MyNineGridBean(mUrls.get(i).getUrl(), mUrls.get(i).getUrl(), mUrls.get(i));
            mData.add(bean);
        }
        mNineGridView.setDataList(mData);
    }
}
