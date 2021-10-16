<head>
	<link rel="stylesheet" href="views/news.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
</head>

<% session.setAttribute("view", "signup"); %>
<%@ include file="nav.jsp" %>

<div id="card">
    <fieldset class="fset-signup">
        <legend>SIGN UP</legend>
        <form action="NewsController?opt=signup" method="POST">
            <div class="signup">
                <div class="form-group">
                    <label for="firstname" class="label-up">First Name</label> 
                    <input type="text" id="firstname" name="firstname" placeholder="First Name" required autofocus>
                </div>
                <div class="form-group">
                    <label for="lastname" class="label-up">Last Name</label>
                    <input type="text" id="lastname" name="lastname" placeholder="Last Name" required>
                </div>
                <div class="form-group">
                    <label for="email" class="label-up">eMail</label>
                    <input type="email" id="email" name="email" placeholder="eMail" required>
                </div>
                <div class="form-group">
                    <label for="user" class="label-up">User</label>
                    <input type="text" id="user" name="user" placeholder="User" required>
                </div>
                <div class="form-group">
                    <label for="password" class="label-up">Password</label>
                    <input type="password" id="password" name="password" placeholder="Password" required>
                </div>
                <div class="form-group">
                    <input type="submit" id="signup" name="sigup" value="Sign Up">
                </div>
            </div>
            <div class="sign-message">
                <output><%= session.getAttribute("message") %></output>
            </div>
        </form>
    </fieldset>
</div>