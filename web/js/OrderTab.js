var checkboxChange = function ( inp ){
    if( inp.checked == true ){
        $('#shipAddrLink').removeClass('disabled');
        console.log("checked");
    }else{
        $('#shipAddrLink').addClass('disabled');
        console.log("unchecked");
    }
}


var disableLink = function (){
    $('#shipAddrLink').addClass('disabled');
}

var enableLink = function (){
    $('#shipAddrLink').removeClass('disabled');
    // $('#shipAddrLink').addClass('')
}