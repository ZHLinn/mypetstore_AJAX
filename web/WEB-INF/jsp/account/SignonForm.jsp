<%--
  Created by IntelliJ IDEA.
  User: zh
  Date: 2020/11/15
  Time: 10:34 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<c:if test="${requestScope.message != null}">
    <ul>
        <li>${requestScope.message}</li>
    </ul>
</c:if>

<%--<fieldset class="SignInField">--%>
<%--    <legend>Sign In</legend>--%>


<%--</fieldset>--%>

<div id="Catalog">
    <p>Please enter your username and password.</p>

    <form action="login" method="post">
        <table class="rwd-table">
            <tr>
                <td>        Username:</td>
                <td><input type="text" name="username" value="j2ee"></td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td>        Password:</td>
                <td><input type="password" name="password" value="j2ee" /></td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td>VerificationCode:</td>
                <td><input type="text" name="vrfCode"></td>
                <td><img src="verifyCode" alt="refresh" onclick="change(this)"></td>
            </tr>
        </table>

        <p>
            <input type="submit" name="signon" value="Login" class="btn"></input>
        </p>

    </form>

    Need a user name and password?
    <a href="newAccountForm" >Register Now!</a>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
