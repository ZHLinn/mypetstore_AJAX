<%--
  Created by IntelliJ IDEA.
  User: zh
  Date: 2020/11/15
  Time: 2:25 下午
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>--%>


<h3>Account Information</h3>

<table>
    <tr>
        <td>First name:</td>
        <td><input type="text" name="firstName" value="${sessionScope.account.firstName}" /></td>
    </tr>
    <tr>
        <td>Last name:</td>
        <td><input type="text" name="lastName" value="${sessionScope.account.lastName}" /></td>
    </tr>
    <tr>
        <td>Email:</td>
        <td><input type="text" size="40" name="email" value="${sessionScope.account.email}" /></td>
    </tr>
    <tr>
        <td>Phone:</td>
        <td><input type="text" name="phone" value="${sessionScope.account.phone}" /></td>
    </tr>
    <tr>
        <td>Address 1:</td>
        <td><input type="text" name="address1" size="40" value="${sessionScope.account.address1}" /></td>
    </tr>
    <tr>
        <td>Address 2:</td>
        <td><input type="text" name="address2" size="40" value="${sessionScope.account.address2}" /></td>
    </tr>
    <tr>
        <td>City:</td>
        <td><input type="text" name="city" value="${sessionScope.account.city}" /></td>
    </tr>
    <tr>
        <td>State:</td>
        <td><input type="text" name="state" size="4" value="${sessionScope.account.state}" /></td>
    </tr>
    <tr>
        <td>Zip:</td>
        <td><input type="text" name="zip" size="10" value="${sessionScope.account.zip}" /></td>
    </tr>
    <tr>
        <td>Country:</td>
        <td><input type="text" name="country" size="15" value="${sessionScope.account.country}" /></td>
    </tr>
</table>

<h3>Profile Information</h3>

<table>
    <tr>
        <td>Language Preference:</td>
        <td>
            <select name="languagePreference">
                <option value="japanese"
                        <c:if test="${sessionScope.account.languagePreference eq 'japanese'}">
                            selected="selected"
                        </c:if>
                    >japanese
                </option>
                <option value="english"
                        <c:if test="${sessionScope.account.languagePreference eq 'english'}">
                            selected="selected"
                        </c:if>
                    >english
                </option>
            </select>
        </td>
    </tr>
    <tr>
        <td>Favourite Category:</td>
        <td>
            <select name="favouriteCategoryId">
                <option value="BIRDS"
                    <c:if test="${sessionScope.account.favouriteCategoryId eq 'BIRDS'}">
                        selected="selected"
                    </c:if>
                    >BIRDS
                </option>
                <option value="DOGS"
                    <c:if test="${sessionScope.account.favouriteCategoryId eq 'DOGS'}">
                        selected="selected"
                    </c:if>
                    >DOGS
                </option>
                <option value="CATS"
                    <c:if test="${sessionScope.account.favouriteCategoryId eq 'CATS'}">
                        selected="selected"
                    </c:if>
                    >CATS
                </option>
                <option value="FISH"
                    <c:if test="${sessionScope.account.favouriteCategoryId eq 'FISH'}">
                        selected="selected"
                    </c:if>
                    >FISH
                </option>
                <option value="REPTILES"
                    <c:if test="${sessionScope.account.favouriteCategoryId eq 'REPTILES'}">
                        selected="selected"
                    </c:if>
                    >REPTILES
                </option>
            </select>
        </td>
    </tr>
    <tr>
        <td>Enable MyList</td>
        <td>
            <input type="checkbox" name="listOption" value="checked"
                <c:if test="${sessionScope.account.listOption == true}">
                       checked="checked"
                </c:if>
            >
        </td>
    </tr>
    <tr>
        <td>Enable MyBanner</td>
        <td>
            <input type="checkbox" name="bannerOption" value="checked"
                <c:if test="${sessionScope.account.bannerOption == true}">
                       checked="checked"
                </c:if>
            >
        </td>
    </tr>

</table>

