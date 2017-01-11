package com.android.helloweex;

import android.text.TextUtils;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;
import java.lang.reflect.Field;

/**
 * Created by apple on 17/1/10.
 */

public class ImageAdapter implements IWXImgLoaderAdapter {
  @Override public void setImage(String url, ImageView view, WXImageQuality quality,
      WXImageStrategy strategy) {

    if(TextUtils.isEmpty(url)) {
      if(url.startsWith("drawable://") || url.startsWith("mipmap://")) {
        int resId = getResource(url);
        view.setImageResource(resId);
      }
    }

    ImageLoader.getInstance().displayImage(url, view);

  }

  public int getResource(String imageName) {
    Class drawable = R.drawable.class;
    try {
      Field field = drawable.getField(imageName);
      int resId = field.getInt(imageName);
      return resId;
    } catch (NoSuchFieldException e) {
      return 0;
    } catch (IllegalAccessException e) {
      return 0;
    }
  }
}
