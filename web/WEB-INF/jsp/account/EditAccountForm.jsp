<%--
  Created by IntelliJ IDEA.
  User: zh
  Date: 2020/11/15
  Time: 2:27 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">

    <c:if test="${requestScope.saveInfoMsg != null}">
        <p>${requestScope.saveInfoMsg}</p>
    </c:if>

    <form action="saveAccountInfo" method="post">
        <h3>User Information</h3>

        <table class="rwd-table">
            <tr>
                <td>User ID:</td>
                <td>${sessionScope.account.username}</td>
            </tr>
            <tr>
                <td>New password:</td>
                <td><input type="text" name="password" /></td>
            </tr>
            <tr>
                <td>Repeat password:</td>
                <td><input type="text" name="repeatedPassword" /></td>
            </tr>
        </table>

        <%@ include file="IncludeAccountFields.jsp"%>

        <input type="submit" name="editAccount" value="Save Account Information" />


    </form>

    <a href="viewOrderList">My Orders</a>
</div>

