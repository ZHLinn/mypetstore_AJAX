<%--
  Created by IntelliJ IDEA.
  User: zh
  Date: 2020/11/14
  Time: 10:53 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <link rel="StyleSheet" href="css/jpetstore.css" type="text/css"
          media="screen" />
    <link rel="StyleSheet" href="css/jquery-ui.css" type="text/css"
          media="screen" />

    <meta name="generator"
          content="HTML Tidy for Linux/x86 (vers 1st November 2002), see www.w3.org" />
    <title>JPetStore Demo</title>
<%--    <meta content="text/html; charset=windows-1252"--%>
<%--          http-equiv="Content-Type" />--%>
    <meta http-equiv="Cache-Control" content="max-age=0" />
    <meta http-equiv="Cache-Control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script src="js/function.js"></script>
    <script type="text/javascript" src="js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
    <script type="text/javascript" src="js/SearchBoxAutoComplete.js"></script>
</head>

<body>

    <div id="Header">

        <div id="Logo">
            <div id="LogoContent">
                <a href="main"><img src="images/logo-topbar.gif" /></a>
            </div>
        </div>

        <div id="Menu">
            <div id="MenuContent">
                <a href="viewCart">
                    <img align="middle" name="img_cart" src="images/cart.gif" />
                </a>
                <img align="middle" src="images/separator.gif" />
                <c:if test="${sessionScope.account != null }">
                    <c:if test="${!sessionScope.account.authenticated}">
                        <a href="signin">Sign In</a>
                        <img align="middle" src="images/separator.gif" />
                    </c:if>
                </c:if>
                <c:if test="${sessionScope.account == null }">
                    <a href="signin">Sign In</a>
                    <img align="middle" src="images/separator.gif" />
                </c:if>
                <c:if test="${sessionScope.account != null }">
                    <c:if test="${sessionScope.account.authenticated}">
                        <a href="signout">Sign Out</a>
                        <img align="middle" src="images/separator.gif" />
                        <a href="editAccountForm">My Account</a>
                        <img align="middle" src="images/separator.gif" />

                    </c:if>
                </c:if>
                <a href="help">
                    ?
                </a>
            </div>
        </div>

        <div id="Search">
            <div id="SearchContent">
                <form action="searchProduct" method="post">
                    <div class="autocomplete">
                        <input type="text" id="keyword" name="keyword" size="14" onclick="initAutoComplete();"/>
                        <input type="submit" name="searchProducts" value="Search" />
                    </div>
<%--                    <script>autocomplete(document.getElementById('keyword'), productArray )</script>--%>

                </form>
            </div>
        </div>

        <div id="QuickLinks">
            <a href="viewCategory?categoryId=FISH">
                <img src="images/sm_fish.gif" />
            </a>
            <img src="images/separator.gif" />
            <a href="viewCategory?categoryId=DOGS">
                <img src="images/sm_dogs.gif" />
            </a>
            <img src="images/separator.gif" />
            <a href="viewCategory?categoryId=REPTILES">
                <img src="images/sm_reptiles.gif" />
            </a>
            <img src="images/separator.gif" />
            <a href="viewCategory?categoryId=CATS">
                <img src="images/sm_cats.gif" />
            </a>
            <img src="images/separator.gif" />
            <a href="viewCategory?categoryId=BIRDS">
                <img src="images/sm_birds.gif" />
            </a>
        </div>

    </div>

    <div id="Content">

