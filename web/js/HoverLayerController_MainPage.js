var xhr;
var category;

var showHoverLayer = function ( categoryId ) {
    createXMLHttpRequest();
    category = categoryId;
    let showUrl = 'getProductListByCategory?categoryId=' + categoryId;
    xhr.open( "GET", showUrl, true );
    // debugger
    $( 'area' ).mouseover( function ( event ) {
        event.preventDefault();
    } );
    xhr.onreadystatechange = processResp;
    xhr.send( null );
}

function processResp() {
    console.log( '' + xhr.status + ' ' + xhr.readyState );
    let respArray, productList;
    if ( xhr.readyState == 4 ) {
        if ( xhr.status == 200 ) {
            /* call empty() to remove all the child element of wrapper div */
            $( '#HoverSidebarContent' ).empty();

            /* encode the response text */
            respArray = xhr.responseText.split( ';' );
            if ( respArray[0] == 'Success' ) {
                /* initial the title */
                let $title = $( '<p><strong>' + category.toUpperCase() + '</strong></p>' )
                $( '#HoverSidebarContent' ).append( $title );
                productList = respArray[1].split( ',' );
                let $list = $( '<ul></ul>' );
                $( '#HoverSidebarContent' ).append( $list );
                let $listItem;
                for ( let i = 0; i < productList.length; i++ ) {
                    $listItem = $( '<li>' + productList[i] + '</li>' );
                    $list.append( $listItem );
                }
                console.log('success');
                console.log(xhr.responseText);
            }
        }else{
            console.log('otherwise');
        }

        $( '#HoverSideBar' ).css( 'visibility', 'visible' );
    }
}

function concealHoverSideBar(){
    $( '#HoverSideBar' ).css( 'visibility', 'hidden' );
}

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