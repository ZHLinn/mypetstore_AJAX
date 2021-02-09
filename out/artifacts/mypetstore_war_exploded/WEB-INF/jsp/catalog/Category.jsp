<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
	<a href="main" class>Return to Main Menu</a>
</div>

<div id="Catalog">

	<%--获取session对象里的类名--%>
	<h2>${sessionScope.category.name}</h2>

	<table class="rwd-table">
		<tr>
			<th>Product ID</th>
			<th>Name</th>
		</tr>
		<c:forEach var="product" items="${sessionScope.productList}">
			<tr>
				<td>
				<a href="viewProduct?productId=${product.productId}">${product.productId}</a>
				</td>
				<td>${product.name}</td>
			</tr>
		</c:forEach>
	</table>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>


