var page = require('webpage').create();
var address = "http://localhost:8080/export/listAndImgExport.jsp";
var output = '../img/2.png';
page.viewportSize = { width: 1600,height: 900};
page.open(address, function (status) {
    if (status !== 'success') {
        console.log('Unable to load the address!');
        phantom.exit();
    } else {
        window.setTimeout(function () {
            length = page.evaluate(function () {
                //此函数在目标页面执行的，上下文环境非本phantomjs，所以不能用到这个js中其他变量
                var div = document.getElementById('testECharts'); //要截图的div的id
                var bc = div.getBoundingClientRect();
                var top = bc.top;
                var left = bc.left;
                var width = bc.width;
                var height = bc.height;
                window.scrollTo(0, 10000);//滚动到底部
                return [top, left, width, height];
            });
            console.log(length);
            page.clipRect = { //截图的偏移和宽高
                top: length[0],
                left: length[1],
                width: length[2],
                height: length[3]
            };

        }, 5000);

        window.setTimeout(function () {
            page.render(output);
            phantom.exit();
        }, 5000 + 500);
    }
});
