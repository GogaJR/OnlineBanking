<html>
<head>
    <title>Title</title>
    <base href="/">
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
        function populateDeposit(s1, s2) {
            var s1 = document.getElementById(s1);
            var s2 = document.getElementById(s2);
            s2.innerHTML = "";

            var bankId = s1.value;
            var optionArray = ["|"];
            <#if depositList??>
            <#list depositList as deposit>
            if(bankId == ${deposit.bank.id}) {
                optionArray.push("${deposit.id}|${deposit.name}");
            }
            </#list>
            </#if>
            for (var option in optionArray) {
                var pair = optionArray[option].split("|");
                var newOption = document.createElement("option");
                newOption.value = pair[0];
                newOption.innerHTML = pair[1];
                s2.options.add(newOption);
            }
        }

        function populateAmountAndMonth(s1, i1) {
            var s1 = document.getElementById(s1);
            var i1 = document.getElementById(i1);

            var depositId = s1.value;
            <#if depositList??>
            <#list depositList as deposit>
            if(depositId == ${deposit.id}) {
                var amount = parseInt("${deposit.minAmount}".replace(/\,/g, ''), 10);
                i1.value = amount;
                i1.min = amount;
            }
            </#list>
            </#if>
        }
    </script>
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

</head>
<body>
<form method="post" action="/userProfile/putInDeposit">
    <div class="main-wrapper">
        <div class="card card-list card-list-accounts">
            <div class="list-header">
                <h4>Put in Deposit</h4>
            </div>
            <a class="row">
                <span class="account-name">Choose a Bank&nbsp;
                    <select id="bankNameSelect" name="bankName" onchange="populateDeposit(this.id, 'depositNameSelect')">
                     <option value=""></option>
                        <#if userBankList??>
                            <#list userBankList as userBank>
                                <option value="${userBank.id}">${userBank.name}</option>
                            </#list>
                        </#if>
                </select>
                </span>
            </a>
            <a class="row">
                <span class="account-name">Choose Deposit&nbsp;
                    <select id="depositNameSelect" name="depositName" onchange="populateAmountAndMonth(this.id, 'amountId')"></select>
                </span>
            </a>
            <a class="row">
                <span class="account-name">Choose Amount in AMD&nbsp;
                    <input type="number" id="amountId" name="depositAmount" >
                </span>
            </a>
            <a class="row">
                <span class="account-name">Choose Number of Months&nbsp;
                    <input type="number" id="monthId" name="numberOfMonths" value="1" min="1" max="48">
                </span>
            </a>
            <button class="blue" type="submit" name="button" value="ok">Ok</button>
            <button class="blue" name="button" value="back">Back</button>
    </div>
    </div>


</form>
</body>
</html>
