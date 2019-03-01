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
        width:100%;
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
        margin-top: 10px;
    }

    h2{
        color: white;
    }


</style>
<body>

<h2 align="center">Users That Have Deposits</h2>
    <div class="main-wrapper">
        <div class="card card-list card-list-accounts">
            <div class="list-header">
                <h4>Active Deposits</h4>
            </div>
            <table class="card">
                <tr>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Amount</th>
                    <th>Putting Date</th>
                    <th>Increasing Day</th>
                    <th>Interest Rate</th>
                    <th>Months</th>
                    <th>Months Left</th>
                </tr>
                <#if activeDeposits??>
                    <#list activeDeposits as putInDeposit>
                        <tr align="center">
                            <td>${putInDeposit.userDto.name}</td>
                            <td>${putInDeposit.userDto.surname}</td>
                            <td>${putInDeposit.amount}AMD</td>
                            <td>${putInDeposit.date}</td>
                            <td>${putInDeposit.increasingDay}</td>
                            <td>${putInDeposit.interestRate}%</td>
                            <td>${putInDeposit.months}</td>
                            <td>${putInDeposit.monthsLeft}</td>
                        </tr>
                    </#list>
                </#if>
            </table>
            <br>
            <div class="list-header">
               <h4> Get Back Deposits</h4>
            </div>
            <table class="card">
                <tr>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Amount</th>
                    <th>Putting Date</th>
                    <th>Increasing Day</th>
                    <th>Interest Rate</th>
                    <th>Months</th>
                    <th>Months Left</th>
                </tr>
                <#if getBackDeposits??>
                    <#list getBackDeposits as putInDeposit>
                        <tr align="center">
                            <td>${putInDeposit.userDto.name}</td>
                            <td>${putInDeposit.userDto.surname}</td>
                            <td>${putInDeposit.amount}AMD</td>
                            <td>${putInDeposit.date}</td>
                            <td>${putInDeposit.increasingDay}</td>
                            <td>${putInDeposit.interestRate}%</td>
                            <td>${putInDeposit.months}</td>
                            <td>${putInDeposit.monthsLeft}</td>
                        </tr>
                    </#list>
                </#if>
            </table>
            <br>
            <div class="list-header">
                <h4>Finished Deposits</h4>
            </div>
            <table class="card">
                <tr>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Amount</th>
                    <th>Putting Date</th>
                    <th>Increasing Day</th>
                    <th>Interest Rate</th>
                    <th>Months</th>
                </tr>
                <#if finishedDeposits??>
                    <#list finishedDeposits as putInDeposit>
                        <tr align="center">
                            <td>${putInDeposit.userDto.name}</td>
                            <td>${putInDeposit.userDto.surname}</td>
                            <td>${putInDeposit.amount}AMD</td>
                            <td>${putInDeposit.date}</td>
                            <td>${putInDeposit.increasingDay}</td>
                            <td>${putInDeposit.interestRate}%</td>
                            <td>${putInDeposit.months}</td>
                        </tr>
                    </#list>
                </#if>
            </table>
<form method="post" action="/bankProfile/showPutInDeposits" align="center">
    <button class="blue" type="submit" name="button" value="clearRecords">Clear</button>
    <button class="blue" name="button">Back</button>
</form>
        </div>
    </div>

</body>
</html>
