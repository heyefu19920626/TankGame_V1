var page = require('webpage').create();
var address = 'https://www.baidu.com/';//填写需要打印的HTML文件
var output = './1.png';//存储文件路径和名称
page.viewportSize = { width: 1600,height: 900 };//设置长宽
page.open(address, function (status) {
    if (status !== 'success') {
        console.log(address);
        console.log("不能载入!");
        console.log('Unable to load the address!');
        phantom.exit();
    } else {
        console.log("success!");
        window.setTimeout(function () {
            console.log("测试测试测试测试");
            // page.render(output);
            phantom.exit();
        }, 500);
    }
});