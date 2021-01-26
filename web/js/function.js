function change(img){
    img.src="verifyCode?"+ new Date().getTime(); //加上当前时间作为参数以防止浏览器阻碍请求
}

