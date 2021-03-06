var xhr;
var username;

function createXMLHttpRequest() {
    if ( window.XMLHttpRequest ) //非IE浏览器
    {
        xhr = new XMLHttpRequest();
    } else if ( window.ActiveObject )//IE6以上版本的IE浏览器
    {
        xhr = new ActiveObject( "Msxml2.XMLHTTP" );
    } else //IE6及以下版本IE浏览器
    {
        xhr = new ActiveObject( "Microsoft.XMLHTTP" );
    }
}

function isUsernameExist() {
    // debugger
    let input = document.getElementById( 'username' );
    username = '';
    if ( input ) {
        username = input.value;
    }
    if(username.trim() != ''){
        //声明XHR对象
        createXMLHttpRequest();
        //初始化url
        var url = "isUsernameExistServlet?username=" + username;
        //建立对服务器的调用
        xhr.open( "GET", url, true ); //默认为true,即异步通信
        xhr.onreadystatechange = processResponse;
        xhr.send( null );
    }else{
        var msgDiv = document.getElementById( 'isExistInfo' );
        msgDiv.innerHTML = "<font color='yellow'>Username Needed.</font>";
    }

}

function processResponse() {
    if ( xhr.readyState == 4 ) {
        if ( xhr.status == 200 ) {
            // debugger
            var responseInfo = xhr.responseText;

            //删除返回信息后边的回车符
            var trimedInfo = responseInfo.trim();

            //获取DOM树中显示用户名是否存在的节点
            var msgDiv = document.getElementById( 'isExistInfo' );

            if ( trimedInfo === 'Exist' ) {
                msgDiv.innerHTML = "<font color='red'>Username Already Existed.</font>";
            } else if ( trimedInfo === 'Not Exist' ) {
                msgDiv.innerHTML = "<font color='white'>Username Available.</font>";
            }
        }
    }
}