<%--
  Created by IntelliJ IDEA.
  User: zh
  Date: 2020/11/16
  Time: 8:49 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<h2>My Orders</h2>

<table class="rwd-table">
    <tr>
        <th>Order ID</th>
        <th>Date</th>
        <th>Total Price</th>
    </tr>

    <c:forEach var="order" items="${sessionScope.orderList}">
        <tr>
            <td>
                <a href="viewOrderWithOrderId?orderId=${order.orderId}">${order.orderId}</a>
            </td>
            <td>
                <fmt:formatDate value="${order.orderDate}" pattern="yyyy/MM/dd hh:mm:ss" />
            </td>
            <td>
                <fmt:formatNumber value="${order.totalPrice}" pattern="$#,##0.00" />
            </td>
        </tr>
    </c:forEach>
</table>

<%@ include file="../common/IncludeBottom.jsp"%>



