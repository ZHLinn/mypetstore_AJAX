$(document).ready(function(){
    $('#login-link').click(function ( event ){
        // alert('bind successfully');
        event.preventDefault();
        showLoginCard();

        // return false;
    });

    $('#login-link-proceed').click(function ( event ){
        // alert('bind successfully');
        event.preventDefault();

        showLoginCard();

        // return false;
    });
    hideLoginCard();

    $('#login-card-closebtn').bind('click', function ( event ){
        hideLoginCard();
        event.stopPropagation();
    })

    $('#loginForm').on('submit', function (e) {
        e.preventDefault();
        $.ajax({
            type: 'post',
            url: 'login',
            data: $('form').serialize(),
            success: function ( result ) {
                if(result == 'Log-in Succeed'){
                    hideLoginCard();
                    var $loginLink = $('#login-link');
                    let $accountLinks = $('<a href="signout">Sign Out</a>\n' +
                        '                 <img align="middle" src="images/separator.gif" />\n' +
                        '                 <a href="editAccountForm">My Account</a>');
                    $loginLink.after($accountLinks);
                    $loginLink.remove();

                    if($('#to-check-out').length != 0 ){
                        let $checkoutLink = $('<a href="proceedToCheckOut" class="btn">Proceed to Checkout</a>');
                        $('#to-check-out').append($checkoutLink);
                    }

                    if($('#note').length != 0 ){
                        $('#note').remove();
                    }



                }else{
                    alert('Invalid username or password. Please try later.');
                }
            },
            error: function (){
                console.log('log-in ajax error');
            }
        });

    });

})

var hideLoginCard = function (){
    $('#login-card').css( 'visibility', 'hidden' );
    $('#log-in-card-background').css( 'visibility', 'hidden' );
    // $('#login-card').hide();
    // event.stopPropagation();
}

var showLoginCard = function (){
    $('#login-card').css( 'visibility', 'visible' );
    // $('#login-card').style.show();
    $('#log-in-card-background').css( 'visibility', 'visible' );
    return false;
}