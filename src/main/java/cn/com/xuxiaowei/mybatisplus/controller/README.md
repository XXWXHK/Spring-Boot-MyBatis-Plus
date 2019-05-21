# Controller 方法说明：

- @RestController

    - 等同于 @Controller 在加上 @ResponseBody

- UserRestController

    - 在类上使用了 @RestController，只能返回数据，不能返回页面

- 打包为jar时，找不到页面？

    - return 最前面不能有/，否则打包方式为 jar 时，出现错误
    
