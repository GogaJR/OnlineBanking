<html>
<head>
    <title>Title</title>
    <base href="/">
</head>
<style>
body {
background: url("/img/background.jpeg");
background-repeat: no-repeat;
background-attachment: fixed;
-webkit-background-size: cover;
background-size: cover;
font-family: Helvetica, sans-serif;

}
.list-header{
background-color: #ddd;
border-radius: 15px 15px 0 0;
padding: 10px;
}
h4 {
color: #595959;
font-size: 18px;
margin: 0;
}
.row {
color: #333333;
display: block;
padding: 15px 15px 15px 30px;
position: relative;
text-decoration: none;
background-color: #7CB7EF;
}
.row:before {
background-color: rebeccapurple;
border-radius: 5px;
content: '';
display: block;
height: 45px;
margin-top: -23px;
position: absolute;
left: 9px;
top: 50%;
width: 12px;
}
.account-name {
display: block;
font-size: 18px;
font-weight: 700;
margin-bottom: 5px;
}

.account-balance {
position: absolute;
right: 15px;
top: 50%;
margin-top: -10px;
}
button {
border-radius: 5px;
padding: 15px 25px;
font-size: 22px;
text-decoration: none;
margin: 20px;
color: #fff;
position: relative;
display: inline-block;
}
button:active {
transform: translate(0px, 5px);
-webkit-transform: translate(0px, 5px);
box-shadow: 0px 1px 0px 0px;
}

.blue {
background-color: #3366ff;
box-shadow: 0px 5px 0px 0px #0d0079;
}

.blue:hover {
background-color: #6FC6FF;
}
.card{
width:900px;
margin-right: auto;
margin-left: auto;
}
.card-list{
background-color: rgba(0,0,0,0.8);
height: 100%;
color: white;
border-radius: 15px 15px 0 0;

}
table{
color: white;
padding: 20px;
}
table td{

}
table th{
font-weight: bolder;
}
.response {
    width: 500px;
    text-align: center;
}
</style>
<body>
<div class="main-wrapper">
    <div class="card card-list card-list-accounts">
        <div class="list-header"><h4>Account Requests</h4></div>
    <table class="card">
        <tr>
            <th>User ID</th>
            <th>User Name</th>
            <th>User Surname</th>
            <th>Card ID</th>
        </tr>
      <#if requests??>
          <#list requests as request>
              <tr align="center">
                  <td>${request.userId}</td>
                  <td>${request.userName}</td>
                  <td>${request.userSurname}</td>
                  <td>${request.cardId}</td>
              </tr>
          </#list>
      </#if>
    </table>
    <form id="sendResponse" method="post", action="/bankProfile/accountRequests">
        <table align="center" class="response">
            <tr><th>Response</th>
            <th>User ID And Card ID</th>
            <th>Card Balance</th></tr>
            <tr><td>
                <select name="response">
                    <option>Accept</option>
                    <option>Deny</option>
                </select>
            </td>
            <td>
                <#if requests??>
                    <select name="userIdAndCardId">
                    <#list requests as request>
                            <option value="${request.userId} ${request.cardId}">${request.userId}   ${request.cardId}</option>
                    </#list>
                    </select>
                </#if>
                </td>
            <td><input type="number" name="balance" value="0" min="0"></td></tr>
        </table>
        <center><button class="blue" name="button" value="send">Send</button>
            <button class="blue" name="button">Back</button></center>
    </div>
    </form>
    </div>
</div>
</body>
</html>
