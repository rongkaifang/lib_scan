# scan
扫码封装测试库

简介：
一个基于zxing封装的扫码库（主要针对的是教师端需求：扫码登录）
包括内容：
1.权限弹窗+扫码基础控件及UI（弱光感知）

具体用法：

1.添加依赖

2.配置页面跳转与扫码后的回调（注意相机权限配置）
###########
startActivityForResult(Intent(activity, CaptureActivity::class.java), ScanMethodUtil.REQUESTACTIVITYCODE)

3.获取回调的数据
###########
        if (requestCode == ScanMethodUtil.REQUESTACTIVITYCODE && resultCode == ScanMethodUtil.REQUESTACTIVITYCODE) {
            val captureResult = data!!.getStringExtra("CaptureResult")
            if (!TextUtils.isEmpty(captureResult)) {
             #######
             获取扫描后的结果
            }
        }