<head>
	<link rel="stylesheet" href="views/news.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
</head>

<% session.setAttribute("view", "chgpwd"); %>
<%@ include file="nav.jsp" %>

<div class="card">
    <fieldset class="fset-chgpwd">
        <legend>CHANGE PASSWORD</legend>
        <form action="NewsController?opt=chgpwd" method="POST">
            <div class="chgpwd">
                <div class="form-group">
                    <label for="userchg" class="label-chg">User</label>
                    <input type="text" id="userchg" name="userchg" placeholder="User" required autofocus>
                </div>
                <div class="form-group">
                    <label for="pwdold" class="label-chg">Password</label>
                    <input type="password" id="pwdold" name="pwdold" placeholder="Password" required>
                </div>
                <div class="form-group">
                    <label for="pwdnew1" class="label-chg">New Password</label>
                    <input type="password" id="pwdnew1" name="pwdnew1" placeholder="New Password" required>
                </div>
                <div class="form-group">
                    <label for="pwdnew2" class="label-chg">New Password</label>
                    <input type="password" id="pwdnew2" name="pwdnew2" placeholder="New Password" required>
                </div>
                <div class="form-group">
                    <input type="submit" id="chgpwd" name="chgpwd" value="Change Password">
                </div>
            </div>
            <div class="sign-message">
                <output><%= session.getAttribute("message") %></output>
            </div>
        </form>
    </fieldset>
</div>