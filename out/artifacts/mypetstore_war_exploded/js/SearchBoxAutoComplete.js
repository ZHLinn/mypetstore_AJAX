var xhr;
var productArray = [];

var initAutoComplete = ( function () {
    createXMLHttpRequest();
    let url = "getAllProductList";
    xhr.open( "GET", url, true );
    xhr.onreadystatechange = function () {
        if ( xhr.readyState == 4 ) {
            if ( xhr.status == 200 ) {
                productArray = xhr.responseText.split( ',' );
                autocomplete( document.getElementById( "keyword" ), productArray );
            }
        }
    };
    xhr.send( null );
} );

function autocomplete( inp, arr ) {
    /*the autocomplete function takes two arguments,
    the text field element and an array of possible autocompleted values:*/
    var currentFocus;
    /*execute a function when someone writes in the text field:*/
    inp.addEventListener( "input", function ( e ) {
        var wrapperDiv, itemDiv, i, index, val = this.value;
        /*close any already open lists of autocompleted values*/
        closeAllLists();
        if ( !val ) {
            return false;
        }
        currentFocus = -1;
        /*create wrapperDiv DIV element that will contain the items (values):*/
        wrapperDiv = document.createElement( "DIV" );
        wrapperDiv.setAttribute( "id", this.id + "autocomplete-list" );
        wrapperDiv.setAttribute( "class", "autocomplete-items" );
        /*append the DIV element as wrapperDiv child of the autocomplete container:*/
        this.parentNode.appendChild( wrapperDiv );
        /*for each item in the array...*/
        for ( i = 0; i < arr.length; i++ ) {
            /*check if the item contains the same letters as the text field value:*/
            index = arr[i].toUpperCase().indexOf( val.toUpperCase() );
            if ( index != -1 ) {
                /*create wrapperDiv DIV element for each matching element:*/
                itemDiv = document.createElement( "DIV" );
                /*make the matching letters bold:*/
                itemDiv.innerHTML = arr[i].substring( 0, index ); //substring() returns a sub-string where index's inside [from, to - 1]
                itemDiv.innerHTML += "<strong>" + arr[i].substr( index, val.length ) + "</strong>";
                itemDiv.innerHTML += arr[i].substring( index + val.length );
                /*insert wrapperDiv input field that will hold the current array item's value:*/
                itemDiv.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
                /*execute wrapperDiv function when someone clicks on the item value (DIV element):*/
                itemDiv.addEventListener( "click", function ( e ) {
                    /*insert the value for the autocomplete text field:*/
                    inp.value = this.getElementsByTagName( "input" )[0].value;
                    /*close the list of autocompleted values,
                    (or any other open lists of autocompleted values:*/
                    closeAllLists();
                } );
                wrapperDiv.appendChild( itemDiv );
            }
        }
    } );
    /*execute a function presses a key on the keyboard:*/
    inp.addEventListener( "keydown", function ( e ) {
        var x = document.getElementById( this.id + "autocomplete-list" );
        if ( x ) x = x.getElementsByTagName( "div" );
        if ( e.keyCode == 40 ) {
            /*If the arrow DOWN key is pressed,
            increase the currentFocus variable:*/
            currentFocus++;
            /*and and make the current item more visible:*/
            addActive( x );
        } else if ( e.keyCode == 38 ) { //up

            /*If the arrow UP key is pressed,
            decrease the currentFocus variable:*/
            currentFocus--;
            /*and and make the current item more visible:*/
            addActive( x );
        } else if ( e.keyCode == 13 ) {
            /*If the ENTER key is pressed, prevent the form from being submitted,*/
            e.preventDefault();
            if ( currentFocus > -1 ) {
                /*and simulate a click on the "active" item:*/
                if ( x ) x[currentFocus].click();
            }
        }
    } );

    function addActive( x ) {
        /*a function to classify an item as "active":*/
        if ( !x ) return false;
        /*start by removing the "active" class on all items:*/
        removeActive( x );
        if ( currentFocus >= x.length ) currentFocus = 0;
        if ( currentFocus < 0 ) currentFocus = ( x.length - 1 );
        /*add class "autocomplete-active":*/
        x[currentFocus].classList.add( "autocomplete-active" );
    }

    function removeActive( x ) {
        /*a function to remove the "active" class from all autocomplete items:*/
        for ( var i = 0; i < x.length; i++ ) {
            x[i].classList.remove( "autocomplete-active" );
        }
    }

    function closeAllLists( elmnt ) {
        /*close all autocomplete lists in the document,
        except the one passed as an argument:*/
        var x = document.getElementsByClassName( "autocomplete-items" );
        for ( var i = 0; i < x.length; i++ ) {
            if ( elmnt != x[i] && elmnt != inp ) {
                x[i].parentNode.removeChild( x[i] );
            }
        }
    }

    /*execute a function when someone clicks in the document:*/
    document.addEventListener( "click", function ( e ) {
        closeAllLists( e.target );
    } );
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