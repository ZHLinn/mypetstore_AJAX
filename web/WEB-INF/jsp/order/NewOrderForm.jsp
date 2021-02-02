<%--
  Created by IntelliJ IDEA.
  User: zh
  Date: 2020/11/16
  Time: 11:31 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
    <form action="confirmOrder" method="post">
        <table class="rwd-table">
            <tr>
                <th colspan=2>Payment Details</th>
            </tr>
            <tr>
                <td>Card Type:</td>
                <td>
                    <select name="cardType">
                        <option value="Visa">Visa</option>
                        <option value="MasterCard">MastercCard</option>
                        <option value="American Express">American Express</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Card Number:</td>
                <td><input type="text" name="creditCard" value="${sessionScope.order.creditCard}"/> * Use a fake
                    number!</td>
            </tr>
            <tr>
                <td>Expiry Date (MM/YYYY):</td>
                <td><input type="text" name="expiryDate" value="${sessionScope.order.expiryDate}" /></td>
            </tr>
            <tr>
                <th colspan=2>Billing Address</th>
            </tr>

            <tr>
                <td>First name:</td>
                <td><input type="text" name="billToFirstName" value="${sessionScope.order.billToFirstName}"/></td>
            </tr>
            <tr>
                <td>Last name:</td>
                <td><input type="text"t name="billToLastName" value="${sessionScope.order.billToLastName}"/></td>
            </tr>
            <tr>
                <td>Address 1:</td>
                <td><input type="text" size="40" name="billAddress1" value="${sessionScope.order.billAddress1}"/></td>
            </tr>
            <tr>
                <td>Address 2:</td>
                <td><input type="text" size="40" name="billAddress2" value="${sessionScope.order.billAddress2}"/></td>
            </tr>
            <tr>
                <td>City:</td>
                <td><input type="text" name="billCity" value="${sessionScope.order.billCity}"/></td>
            </tr>
            <tr>
                <td>State:</td>
                <td><input type="text" size="4" name="billState" value="${sessionScope.order.billState}"/></td>
            </tr>
            <tr>
                <td>Zip:</td>
                <td><input type="text" size="10" name="billZip" value="${sessionScope.order.billZip}"/></td>
            </tr>
            <tr>
                <td>Country:</td>
                <td><input type="text"t size="15" name="billCountry" value="${sessionScope.order.billCountry}"/></td>
            </tr>

            <tr>
                <td colspan=2>
                    <input type="checkbox" name="shippingAddressRequired" />
                    Ship to different address...</td>
            </tr>

        </table>

        <input type="submit" name="newOrder" value="Continue" class="btn" />
    </form>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>