var page = require('webpage').create();
var address = 'http://www.baidu.com';
page.onResourceError = function (request){
    console.error(resouceError.url + ':' + resouceError.errorString);
}
page.open(address,function(status){
    if(status !== 'success'){
        console.log(page.content);
        console.log("fail");
        console.log("1111");
        phantom.exit();
    }else{
        console.log("success");
        page.render('../img/1.png');
        phantom.exit();
    }
});