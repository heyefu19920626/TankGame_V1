var page = require('webpage').create();
var address = 'http://192.168.51.251:8080/ump-console/skins/menutree/desktopLR.jsp';//填写需要打印的文件位置
var output = './screen.png';//存储文件路径和名称
page.viewportSize = { width: 1280, height: 800 };//设置长宽

var flag = phantom.addCookie({
    "domain": "192.168.51.100",
    "expires": "Fri, 01 Jan 2038 00:00:00 GMT",
    "expiry": 2145916800,
    "httponly": true,
    "name": "JSESSIONID",
    "path": "/ump-console/",
    "secure": false,
    "value": "2CDD6EDBC55B13B73218EDF298EFCDE0" //这里省略了，输入自己的value即可
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
