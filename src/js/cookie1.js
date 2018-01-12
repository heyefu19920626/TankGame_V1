var page = require('webpage').create();
var address = 'http://localhost:8080/login.do';//填写需要打印的文件位置
var output = '../img/screen.png';//存储文件路径和名称
page.viewportSize = { width: 1280, height: 800 };//设置长宽

var flag = phantom.addCookie({
    "domain": "localhost",
    "expires": "Fri, 01 Jan 2038 00:00:00 GMT",
    "expiry": 2145916800,
    "httponly": true,
    "name": "JSESSIONID",
    "path": "/",
    "secure": false,
    "value": "D3C172233163F6A8DCA6486007B37D34" //这里省略了，输入自己的value即可
});

console.log(flag);

if(flag){
    page.open(address, function (status) {
        if (status !== 'success') {
            console.log('Unable to load the address!');
            phantom.exit();
        } else {
            window.setTimeout(function () {
                page.render(output);
                phantom.exit();
            }, 500);
        }
    });
}else{
    console.log('error!!!');
    phantom.exit();
}  
