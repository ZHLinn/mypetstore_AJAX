<%--
  Created by IntelliJ IDEA.
  User: zh
  Date: 2020/11/14
  Time: 10:57 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

</div>

<div id="Footer">

    <div id="PoweredBy">
        &nbsp<a href="www.csu.edu.cn">www.csu.edu.cn</a>
    </div>

    <div id="Banner">
        <c:if test="${sessionScope.account != null }">
            <c:if test="${sessionScope.account.bannerOption}">
                ${sessionScope.account.bannerName}
            </c:if>
        </c:if>
    </div>

</div>
<script type="text/javascript" src="js/CheckUsername.js"></script>
</body>
</html>