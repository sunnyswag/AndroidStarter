## 甜品制造工厂

这是一个来自谷歌官方的 Demo



### 改进点



### 知识点

1、 持续复习 Activity 的生命周期

![img](https://developer.android.com/guide/components/images/activity_lifecycle.png)

2、 可以使用 `binding = DataBindingUtil.setContentView(this, R.layout.activity_main)` 直接获取到 `.xml` 的控件和数据，十分方便

3、 `override fun onSaveInstanceState(outState: Bundle)` 保存数据，在下次 `onCreate()` 的时候加载，一般来说是内存不足时需要 `onCreate` 的情况

4、 通过 `data class Dessert(val imageId: Int, val price: Int, val startProductionAmount: Int)` 可以很方便的创建数据类，存储一些数据

### 遇到的困难

1、 需要在 app 的 `build.gradle` 下添加如下配置，不然没法使用 dataBinding

```groovy
plugins {
    id 'kotlin-kapt'
}

android {
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}
```

2、 文件资源未找到

`～/Desktop/git/AndroidStarter/DessertStarter/app/src/main/res/mipmap-anydpi-v26/ic_dessert_clicker.xml:19: AAPT: error: resource drawable/ic_launcher_foreground (aka com.example.dessertstarter:drawable/ic_launcher_foreground) not found.`

将所有  `.xml`  的开头都加上  `<?xml version="1.0" encoding="utf-8"?>`  ，运行一遍，再把报错文件的去掉即可
