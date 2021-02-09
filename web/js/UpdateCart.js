var xhr;
var respMsgArray;

var updateCartRequest = function () {
    createXMLHttpRequest();
    let parameters = $( '#cartForm' ).serialize();
    let url = "updateCartQuantities?" + parameters;
    xhr.open( "GET", url, true );
    xhr.onreadystatechange = function () {
        if ( xhr.readyState == 4 ) {
            if ( xhr.status == 200 ) {
                respMsgArray = xhr.responseText.split( ',' );
                // debugger
                if ( respMsgArray.length > 1 ) {
                    if ( respMsgArray[0].split( '/' )[0] == 'Remove Action' ) {
                        var removedItemsList = respMsgArray.slice( 1 );
                        for ( let i = 0; i < removedItemsList.length; i++ ) {
                            //remove cart item whose quantity eq 0
                            $( 'tr[itemid = ' + removedItemsList[i] + ']' ).remove();
                        }
                    }
                }
                if ( respMsgArray[0].split( '/' )[1] == 'Empty' ) { /* The cart falls empty */
                    /* insert a row of message of empty cart */
                    let $emptyMsgRow = $( '<tr>\n' +
                        '                        <td colspan="8"><b>Your cart is empty.</b></td>\n' +
                        '                    </tr>' );
                    $( 'tr.trHead' ).after( $emptyMsgRow );
                }
            }
        }
    }
    xhr.send( null );
}

var removeCartItemRequest = function (itemid) {
    let removalUrl = 'removeItemFromCart?workingItemId=' + itemid;
    createXMLHttpRequest();
    xhr.open( "GET", removalUrl, true );
    xhr.onreadystatechange = function () {
        if ( xhr.readyState == 4 ) {
            if ( xhr.status == 200 ) {
                $( 'tr[itemid = ' + itemid + ']' ).remove();
                if(xhr.responseText == 'Cart Empty' ){
                    let $emptyMsgRow = $(
                        '                    <tr>\n' +
                        '                        <td colspan="8"><b>Your cart is empty.</b></td>\n' +
                        '                    </tr>'
                    );
                    $( 'tr.trHead' ).after( $emptyMsgRow );
                }
            }
        }
    }
    xhr.send( null );
}

function createXMLHttpRequest() {
    if ( window.XMLHttpRequest )  /*For all kinds of Browsers except for IE*/
    {
        xhr = new XMLHttpRequest();
    } else if ( window.ActiveObject )  /*For IE6 and above*/
    {
        xhr = new ActiveObject( "Msxml2.XMLHTTP" );
    } else /*For IE under version 6*/
    {
        xhr = new ActiveObject( "Microsoft.XMLHTTP" );
    }
}