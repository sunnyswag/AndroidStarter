



暂时将 Fragment 强行理解为一个自定义的 VIewGroup 吧，真的可以像 View 一样放到任何地方，任何位置。

实际上，原理应该是不太一样的

### REFERENCE

[DEMO，Fragment 的添加](https://blog.csdn.net/zgljl2012/article/details/47423583)

[DEMO，Fragment 的排布](https://www.cnblogs.com/qianguyihao/p/3978989.html)

### 一些坑

IDE 提示使用 FragmentContainerView 标签，但使用这个标签没办法通过 findViewById 获取到 fragment 里边的控件，会报空指针异常