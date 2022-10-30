### 实现的效果

看完本文，这些效果的实现可以完全拿捏住了，并且实现还非常**简单**：

a. 按下时透明度的变化

b. 按下时塞入蒙层

![37_1667124115](https://tern-1257001564.cos.ap-guangzhou.myqcloud.com/markdown_pic/37_1667124115.gif)

可以完美覆盖大部分的点击场景了，如果没有，那就快去说服设计师更换方案！[狗头保命]



### 按下时透明度的变化

一般的思路是 xml 配置 selector，放两张图片，完美解决。但这样通用性也太差了点吧，每张图片都要配置一个 selector，有没有更好点的办法呢？这里我采用的方式是 **重写 onTouch() **：

1. 使用拓展函数，在 `MotionEvent.ACTION_DOWN` 时设置 View 的透明度，在松手时取消透明度的设置

2. 调用时直接使用 `view.fadeWhenTouch()` 即可，非常方便，如果存在多个 View 需要设置的情况，可以使用ContraintLayout 的 Group 遍历一并设置一下～

```kotlin
/**
 * 对当前 View 实现按下透明度变化的效果
 */
@SuppressLint("ClickableViewAccessibility")
fun View.fadeWhenTouch(pressedAlpha: Float = 0.5F) {
    this.setOnTouchListener { v, event ->
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> v?.alpha = pressedAlpha
            MotionEvent.ACTION_UP -> v?.alpha = 1F
            MotionEvent.ACTION_CANCEL -> v?.alpha = 1F
        }
        false
    }
}

// 实际使用时, 进行如下调用：
binding.ivLoveYou.fadeWhenTouch()
```



### 按下时塞入蒙层

这里当然也可以用 selector 来解决问题。但有没有办法直接用一个色值就可以搞定呢？实在无法忍受多余的资源文件！答案是有的，**用 Tint** !

1. 配置 selector_pressed_overlay.xml 放在 res/color/ 目录下：

   a. 按下时，我们展示 20% 透明度的黑色

   b. 其他状态则完全透明

   ```xml
   <?xml version="1.0" encoding="utf-8"?>
   <selector xmlns:android="http://schemas.android.com/apk/res/android">
       <item android:state_pressed="true" android:color="#33000000"/>
       <item android:color="#00000000" />
   </selector>
   ```

2. 对于普通 View 的 Background 按下时需要塞入蒙层的情况 (**非常实用**)

   a. background：View 所展示的 Background

   b. backgroundTintMode：tint 叠加的模式，使用 src_atop 可以完美符合需求

   c. backgroundTint：1 中的 color 配置文件，在其中配置按下态的逻辑

   d. clickable：在没有实现 onClick() 的情况下，可以响应按下态

   ```xml
   <ImageView
       ...
       android:clickable="true"
       android:background="@drawable/jiaran_angry_drawable"
       android:backgroundTint="@color/selector_pressed_overlay"
       android:backgroundTintMode="src_atop" />
   ```

3. 对于 ImageView 的 Drawable 按下需要塞入蒙层的情况

   可以参考 Background 的实现进行理解，逻辑是一样的

   ```xml
   <ImageView
       ...
       android:clickable="true"
       android:src="@drawable/jiaran_remove_bg"
       android:tintMode="src_atop"
       app:tint="@color/selector_pressed_overlay" />
   ```



### Tips

1. 对于 **RecyclerView** 中的 item ，按下时塞入蒙层的实现有一点点区别：

   ```xml
   <!-- tintMode 需要更换为 src_over -->
   android:backgroundTintMode="src_over"
   ```

2. 如果 **View 没有背景**，但需要实现按下塞入蒙层的效果。那么，可以将其 background 设置成透明的，再按照如上进行配置即可
3. 如果需要自己手动裁切圆角，除了使用 CardView，还可以使用 `ViewOutlineProvider` 实现，减少 View 的嵌套，具体可参考 [这篇Blog](https://www.jianshu.com/p/dfe5806a062c)
4. 本文源码可以参考该 [仓库](https://github.com/sunnyswag/AndroidStarter/tree/main/StatedPressedStarter)



### Reference

[Android-使用tint一张图制作selector](https://www.jianshu.com/p/9c5baee9da4c)

[Android 中常见切圆角的方式](https://www.jianshu.com/p/dfe5806a062c)

