<%--
  Created by IntelliJ IDEA.
  User: zh
  Date: 2021/2/1
  Time: 5:22 下午
  To change this template use File | Settings | File Templates.
--%>

<div id="log-in-card-background">
    <img>
</div>

<div id="login-card">
    <span class="closebtn" id="login-card-closebtn"></span>
    <div id="card-content">
        <div id="card-title">
            <h2>LOGIN</h2>
            <div class="underline-title"></div>
        </div>
        <form method="post" action="login" class="form" id="loginForm">
            <label for="user-name" style="padding-top:13px">
                &nbsp;Username
            </label>
            <input id="user-name" class="form-content" type="text" name="username" autocomplete="on" value="j2ee" required />
            <div class="form-border"></div>
            <label for="user-password" style="padding-top:22px">&nbsp;Password
            </label>
            <input id="user-password" class="form-content" type="password" name="password" value="j2ee" required />
            <div class="form-border"></div>
            <a href="#">
                <legend id="forgot-pass">Forgot password?</legend>
            </a>
            <input id="submit-btn" type="submit" name="submit" value="LOGIN" />
            <a href="newAccountForm" id="signup">Don't have account yet?</a>
        </form>
    </div>
</div>
