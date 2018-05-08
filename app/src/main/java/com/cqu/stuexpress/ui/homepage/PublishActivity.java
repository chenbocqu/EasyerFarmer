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

public class PublishActivity extends TitleBarActivity {

    NineGridView mNineGridView;
    ImageAdapter mAdapter;
    List<NineGridBean> mData;

    List<Img> mUrls;

    @Override
    public String getUITitle() {
        return "果蔬上架";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_publish;
    }

    @Override
    public void init() {

        mNineGridView = (NineGridView) findViewById(R.id.ngiv);

        mData = new ArrayList<>();

        mUrls = new ArrayList<>();
        mUrls.add(new Img("https://ss3.baidu.com/-rVXeDTa2gU2pMbgoY3K/it/u=207107061,197311921&fm=202&src=781&mola=new&crop=v1"));
        mUrls.add(new Img("https://ss3.baidu.com/-rVXeDTa2gU2pMbgoY3K/it/u=50356424,2368113286&fm=202&src=781&mola=new&crop=v1"));
        mUrls.add(new Img("https://ss3.baidu.com/-rVXeDTa2gU2pMbgoY3K/it/u=2881497759,4174889259&fm=202&src=781&mola=new&crop=v1"));
        mUrls.add(new Img("https://ss3.baidu.com/-rVXeDTa2gU2pMbgoY3K/it/u=849458795,3860849086&fm=202&src=781&mola=new&crop=v1"));

        mUrls.add(new Img("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526376604&di=a5306b7933107c0db968abca8925fc63&imgtype=jpg&er=1&src=http%3A%2F%2Fd6.yihaodianimg.com%2FN04%2FM03%2FCB%2F4F%2FCgQDrVN0bOeAGvdLAAEhVwjHJ_E37300.jpg"));
        mUrls.add(new Img("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525781886602&di=2a41f9807f3426a90e3ffa04e3f1ff61&imgtype=0&src=http%3A%2F%2Fimg002.hc360.cn%2Fhb%2FMTQ2MTAxMDk5NTYxNTE2ODU0ODg3ODg%3D.jpg"));
        mUrls.add(new Img("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525781886602&di=245cf594c345890045c12b72d1730665&imgtype=0&src=http%3A%2F%2Fwww.sznews.com%2Fzhuanti%2Fimages%2Fattachement%2Fjpeg%2Fsite3%2F20160819%2FIMG4439c452bd3042159067465.jpeg"));
        mUrls.add(new Img("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525781886601&di=9094f23b294920824e6c0cc383b7acd6&imgtype=0&src=http%3A%2F%2Fd6.yihaodianimg.com%2FN07%2FM04%2F56%2F84%2FCgQIz1UuNuyAN2tOAAFSowVhttE46300.jpg"));
        mUrls.add(new Img("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525781886601&di=527cb5100144ef01b00f785d3569424d&imgtype=0&src=http%3A%2F%2Fd6.yihaodianimg.com%2FN03%2FM03%2FD5%2F55%2FCgQCs1NjUiWADJAQAAE3ESZifkQ58700.jpg"));
        mUrls.add(new Img("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525781886600&di=5e2efc88ce6a8c438e5cf40f9ee21e06&imgtype=0&src=http%3A%2F%2Fdimg.cnlist.org%2Fimage%2Fupload%2Ffa%2F7a%2Fb6%2Fce%2Ffa7ab6cec2e90e5570ad6429885dd1d4.jpg"));

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
                        GPreviewBuilder.from(PublishActivity.this)
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
