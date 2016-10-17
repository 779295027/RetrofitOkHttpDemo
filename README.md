

# RetrofitOkHttpDemo

此demo只是用于Retrofit2.0和OkHttp3.0的初学使用，以供参考

如果想要在项目内使用Retrofit2.0＋OkHttp3.0，请参考retrofit2okhttp3库包

Mainactivty 中有七种使用方式，前两种为OKHttp的调用方式，然后四种为Retrofit使用方式
最后一种是经过作者自己封装的Retrofit2.0＋OkHttp3.0的调用方式

## 项目运行和库包使用
运行前请在HttpUrl中修改域名，改为要测试的域名
修改GitHubService中的login(Map)和post(Map)方法的相对路径
修改Mainactivty中使用中文变量名的地方

如果需要添加自定义token，请在HttpUrl中修改自定义token的名称
并在登录后，或在token更改时调用RetrofitInstance对象的addToken(String)方法
一个RetrofitInstance对象中只有一个token对象

自定义回调请继承NoActionAjaxCallBack类

数据返回格式为ResopnseData对象，如需要请自行改动

### 增加下载功能

与jsonpost调用方式一样，回调使用DownloadCallBack，
DownloadCallBack会返回文件的数据流，需要自己对数据流处理

（为了满足使用post请求返回图片数据流进行加载）

### 注
这只是作者测试用的,如有不对之处，请评论


# Licence

```

Copyright (c) 2016.   Sss

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


```
