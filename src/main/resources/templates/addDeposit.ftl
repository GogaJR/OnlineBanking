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
        width:1100px;
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
<div class="main-wrapper">
    <div class="card card-list card-list-accounts">

<form method="post" action="/bankProfile/addDeposit">

    <div class="list-header"><h4 align="center">ADD DEPOSIT</h4></div>


    <table align="center" cellpadding = "10" width="1000">

        <!----- Deposit Name ---------------------------------------------------------->
        <tr>
            <td>DEPOSIT NAME</td>
            <td><input type="text" name="depositName" maxlength="30" required/>
                (max 30 characters a-z and A-Z)
            </td>
        </tr>

        <!----- MINIMUM AMOUNT ---------------------------------------------------------->
        <tr>
            <td>MINIMUM AMOUNT</td>
            <td><input type="number" name="minimumAmount" required/>
                (In AMD)
            </td>
        </tr>

        <!------------------------------------------------------------------------------->
        <tr  align="center">
            <td>
                <button class="blue" type="button" onclick="monthRanges()">Add Month Ranges And Interest Rates</button>
                <input type="hidden" id="varId" name="var"/>
            </td>
        </tr>

        <tr>
            <td>
                <div id="myDiv">
                    <script>
                        function monthRanges() {
                             var myDiv = document.getElementById("myDiv");

                            for (var i=1; i<11; i++) {
                                var inputMonthLowerBound = document.createElement("input");
                                inputMonthLowerBound.type = "number";
                                inputMonthLowerBound.min = "1";
                                inputMonthLowerBound.max = "48";
                                inputMonthLowerBound.value = i;
                                inputMonthLowerBound.id = "monthLowerBoundId";
                                inputMonthLowerBound.name = "monthLowerBound" + i;
                                myDiv.appendChild(inputMonthLowerBound);

                                var inputMonthUpperBound = document.createElement("input");
                                inputMonthUpperBound.type = "number";
                                inputMonthUpperBound.min = "2";
                                inputMonthUpperBound.max = "48";
                                inputMonthUpperBound.value = i+1;
                                inputMonthUpperBound.id = "monthUpperBoundId";
                                inputMonthUpperBound.name = "monthUpperBound" + + i;
                                myDiv.appendChild(inputMonthUpperBound);

                                var inputInterestRate = document.createElement("input");
                                inputInterestRate.type = "number";
                                inputInterestRate.min = "1";
                                inputInterestRate.step = "0.01";
                                inputInterestRate.id = "interestRateId";
                                inputInterestRate.name = "interestRate" + + i;
                                myDiv.appendChild(inputInterestRate);
                            }
                        //
                        //     var monthLowerBound = document.createElement("select");
                        //     monthLowerBound.setAttribute("name", "monthLowerBound");
                        //     monthLowerBound.id = "monthLowerBoundSelect";
                        //     myDiv.appendChild(monthLowerBound);
                        //
                        //     for (var i = 1; i < 36; i++) {
                        //         var option = document.createElement("option");
                        //         option.value = i;
                        //         option.text = i;
                        //         monthLowerBound.appendChild(option);
                        //     }
                        //
                        //     var monthUpperBound = document.createElement("select");
                        //     monthUpperBound.setAttribute("name", "monthUpperBound");
                        //     monthUpperBound.id = "monthUpperBoundSelect";
                        //     myDiv.appendChild(monthUpperBound);
                        //
                        //     for (var i = 1; i < 36; i++) {
                        //         var option = document.createElement("option");
                        //         option.value = i;
                        //         option.text = i;
                        //         monthUpperBound.appendChild(option);
                        //     }
                        }
                    </script>
                </div>
            </td>
        </tr>

        <!----- Submit and Reset ------------------------------------------------->
        <tr>
            <td colspan="2" align="center">
                <button class="blue" name="button" value="addDeposit">Add</button>
                <button class="blue" type="reset">Reset</button>
                <button class="blue" name="button" formnovalidate>Back</button>
            </td>
        </tr>
    </table>
</form>
</div>
</div>

</body>
</html>