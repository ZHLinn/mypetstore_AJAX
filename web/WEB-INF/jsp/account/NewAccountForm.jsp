<%--
  Created by IntelliJ IDEA.
  User: zh
  Date: 2020/11/15
  Time: 7:09 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>


<div id="Catalog">
    <c:if test="${requestScope.registerMsg != null}">
        <p>${requestScope.registerMsg}</p>
    </c:if>
    <form action="register" method="post">
<%--        <form action="isUsernameExistServlet" method="post">--%>
        <h3>User Information</h3>
        <table class="rwd-table">
            <tr>
                <td>User ID:</td>
                <td>
<%--                    <script type="text/javascript" src="${pageContext.request.contextPath }/js/register.js"></script>--%>
                    <input type="text" name="username" id="username" onblur="isUsernameExist();" />

<%--                    <br>--%>
<%--                    <span id="isExistInfo"></span>--%>
                    <div id="isExistInfo"></div>
<%--                    <input type="text" name="username" id="username"  onblur="usernameIsExist();"/>--%>
<%--                    <div id="usernameMsg"></div>--%>
<%--                    <script type="text/javascript" src="${pageContext.request.contextPath }/js/register.js"></script>--%>
<%--                    --%>
                </td>
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
        <%@ include file="VerificationForm.jsp"%>

        <input type="submit" name="newAccount" value="Save Account Information" class="btn" />

    </form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>