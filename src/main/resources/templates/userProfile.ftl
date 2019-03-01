<#ftl encoding='UTF-8'>
<html>
<head>
    <link href='https://fonts.googleapis.com/css?family=Baloo Thambi' rel='stylesheet'>
</head>
<style>
    body{
        background: url("/img/UserProfileWallpaper.jpg");
        background-repeat: no-repeat;
        background-attachment: fixed;
        -webkit-background-size: cover;
        background-size: cover;
        background-color:rgba(0,0,0,0.5)
    }
    td{
        padding: 5px;
        text-align: center;
    }
    button {
        font-family: Arial, Helvetica, sans-serif;
        font-size: 14px;
        font-stretch:expanded;
        font-weight:bold;
        color: #f5f5f5;
        padding: 10px 8px;
        background: -moz-linear-gradient(
                top,
                #7badd9 0%,
                #105196);
        background: -webkit-gradient(
                linear, left top, left bottom,
                from(#7badd9),
                to(#105196));
        -moz-border-radius: 7px;
        -webkit-border-radius: 7px;
        border-radius: 7px;
        border: 1px solid #10224d;
        -moz-box-shadow:
                0px 1px 3px rgba(000,000,000,0.5),
                inset 0px 0px 1px rgba(255,255,255,0.7);
        -webkit-box-shadow:
                0px 1px 3px rgba(000,000,000,0.5),
                inset 0px 0px 1px rgba(255,255,255,0.7);
        box-shadow:
                0px 1px 3px rgba(000,000,000,0.5),
                inset 0px 0px 1px rgba(255,255,255,0.7);
        text-shadow:
                0px -1px 0px rgba(000,000,000,0.4),
                0px 1px 0px rgba(255,255,255,0.3);

        -webkit-animation-name: navigation;
        -webkit-animation-duration: 0.6s;
        -webkit-animation-delay: 2s;
        -webkit-animation-iteration-count: 1;
        -webkit-animation-fill-mode: backwards;
        -webkit-animation-timing-function: linear;

        -webkit-transition: 500ms linear 0s;
        -moz-transition: 500ms linear 0s;
        -o-transition: 500ms linear 0s;
        transition: 500ms linear 0s;
        outline: 0 none;
        cursor:inherit;
    }
    button:hover {
        background: -moz-linear-gradient(
                top,
                #51a8f5 0%,
                #09315c);
        background: -webkit-gradient(
                linear, left top, left bottom,
                from(#51a8f5),
                to(#09315c));
    }
    button:focus, button:active {
        -moz-box-shadow:
                0px 1px 3px rgba(000,000,000,0.5),
                inset 0px 0px 11px rgba(28,3,28,0.7);
        -webkit-box-shadow:
                0px 1px 3px rgba(000,000,000,0.5),
                inset 0px 0px 11px rgba(28,3,28,0.7);
        box-shadow:
                0px 1px 3px rgba(000,000,000,0.5),
                inset 0px 0px 11px rgba(28,3,28,0.7);
    }

    .form-style-2-heading{
        margin-right: 20px;
        margin-top: 10px;
        font-family: 'Baloo Thambi';
        font-size: 22px;
    }
</style>
<body>
<div align="right" class="form-style-2-heading">${user.name} ${user.surname}<br><a href="/logout">Log out</a></div>
<form method="post", action="/userProfile">
    <table align="center">
        <td><button type="submit" name="button" value="showBankList">Show Available Bank List</button></td>
        <td><button type="submit" name="button" value="showUserBankList">Show My Bank List</button></td>
        <td><button type="submit" name="button" value="showBankDepositList">Show Bank Deposit List</button></td>
        <td><button type="submit" name="button" value="showBalance">Show Balance(s)</button></td>
        <td><button type="submit" name="button" value="showAccountResponses">Show Account Responses</button></td>
        <td><button type="submit" name="button" value="putInDeposit">Put In Deposit</button></td>
        <td><button type="submit" name="button" value="getDepositBack">Get Deposit Back</button></td>
        <td><button type="submit" name="button" value="makeTransfer">Make Transfer</button></td>
        <td><button type="submit" name="button" value="showMyTransfers">Show My Transfers</button></td>
        <td><button type="submit" name="button" value="openAccount">Open Account</button></td>
    </table>
</form>
</body>
</html>