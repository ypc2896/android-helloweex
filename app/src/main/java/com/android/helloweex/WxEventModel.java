package com.android.helloweex;

import android.widget.Toast;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXModuleAnno;

/**
 * Created by apple on 17/1/10.
 */

public class WxEventModel extends WXModule {

  @WXModuleAnno(runOnUIThread = true)
  public void showToast(String msg){
    Toast.makeText(mWXSDKInstance.getContext(), msg, Toast.LENGTH_SHORT).show();
  }

}
