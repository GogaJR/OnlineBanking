<html>
<head>
    <title>Title</title>
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
        color: white;
        display: block;
        padding: 15px 15px 15px 30px;
        position: relative;
        text-decoration: none;
    }
    .row:before {
        background-color: cornflowerblue;
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
        font-weight: bold;
    }
    table th{
        font-weight: bolder;
    }

</style>
<body>
<form method="post" action="/userProfile/openAccount">
    <div class="main-wrapper">
        <div class="card card-list card-list-accounts">
            <div class="list-header">
                <h4>Open Account</h4>
            </div>

            <a class="row">
                <span class="account-name">Select Bank Name&nbsp;
                    <select name="bankName" id="bankList">
                    <#list banks as bank>
                        <option value=${bank.id}>${bank.name}</option>
                    </#list>
                    </select></span>
            </a>

            <a class="row">
                <span class="account-name">Card ID&nbsp;<input type="text" name="cardId" minlength="16" maxlength="16" required/></span>
            </a>

            <button class="blue" type="submit" name="button" value="sendRequest">Send Request</button>
            <button class="blue" name="button" formnovalidate>Back</button>
        </div>
    </div>
</form>
</body>
</html>