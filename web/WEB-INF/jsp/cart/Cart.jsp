<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
    <a href="main">Return to Main Menu</a>
</div>

<div id="Catalog">

    <div id="Cart">

        <h2>Shopping Cart</h2>
        <script type="text/javascript" src="js/UpdateCart.js"></script>
<%--        <form action="updateCartQuantities" method="post">--%>
        <form id="cartForm">
            <table class="rwd-table">
                <tr  class="trHead">
                    <th><b>Item ID</b></th>
                    <th><b>Product ID</b></th>
                    <th><b>Description</b></th>
                    <th><b>In Stock?</b></th>
                    <th><b>Quantity</b></th>
                    <th><b>List Price</b></th>
                    <th><b>Total Cost</b></th>
                    <th>&nbsp;</th>
                </tr>

                <c:if test="${sessionScope.cart.numberOfItems == 0}">
                    <tr>
                        <td colspan="8"><b>Your cart is empty.</b></td>
                    </tr>
                </c:if>

                <script>
                    /*To get the mouse becoming the shape of a finger when hovering over <a class="btn">*/
                    jQuery(document).ready(
                        function (){
                            $('#cartForm a.btn').click(
                                function ( event ){
                                    event.preventDefault();
                                }
                            );
                        }
                    );

                </script>

                <c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
                    <tr itemid="${cartItem.item.itemId}">
                        <td>
                            <a href="viewItem?itemId=${cartItem.item.itemId}">
                                    ${cartItem.item.itemId}
                            </a>
                        </td>
                        <td>
                                ${cartItem.item.product.productId}
                        </td>
                        <td>
                                ${cartItem.item.attribute1} ${cartItem.item.attribute2}
                                ${cartItem.item.attribute3} ${cartItem.item.attribute4}
                                ${cartItem.item.attribute5} ${cartItem.item.product.name}
                        </td>
                        <td>
                                ${cartItem.inStock}
                        </td>
                        <td>
                            <input type="text" name="${cartItem.item.itemId}" value="${cartItem.cartQuantity}" onchange="updateCartRequest()" />
                        </td>
                        <td>
                            <fmt:formatNumber value="${cartItem.item.listPrice}" pattern="$#,##0.00" />
                        </td>
                        <td>
                            <fmt:formatNumber value="${cartItem.total}" pattern="$#,##0.00" />
                        </td>
                        <td>
                            <a onclick="removeCartItemRequest('${cartItem.item.itemId}')" href="#" class="btn">Remove</a>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="7">
                        <a onclick="updateCartRequest()" href="#" value="Update Cart" class="btn">Update Cart</a>
                    </td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </form>

        <div id="to-check-out">

        </div>
        <c:if test="${sessionScope.cart.numberOfItems > 0}">
            <c:if test="${sessionScope.account != null }">
                <c:if test="${sessionScope.account.authenticated}">
                    <a href="proceedToCheckOut" class="btn">Proceed to Checkout</a>
                </c:if>
            </c:if>
            <c:if test="${sessionScope.account == null }">
                <p id="note">You need to log in before checking out.</p>
            </c:if>

        </c:if>
    </div>

    <div id="Separator">&nbsp;</div>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
