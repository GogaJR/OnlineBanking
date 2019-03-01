<html>
<head>
    <title>Title</title>
    <base href="/">
</head>
<style>
    h3{
        font-family: Calibri;
        font-size: 25pt;
        font-style: normal;
        font-weight: bold;
        color: rgba(112,112,112,0.7);
        text-align: center;
    }

    table{
        font-family: Calibri;
        color:white;
        font-size: 11pt;
        font-style: normal;
        font-weight: bold;
        text-align: center;
        background-color: #5588ff;
        border-collapse: collapse;
    }
    table.inner{
        border: 0px
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
    input{
        padding: 10px;
    }

    table td{
        padding: 15px;
    }
</style>
<body>
<form method="post" action="/adminProfile/signUpBank">

    <h3>SIGN UP</h3>


    <table align="center" cellpadding = "10">

        <!----- Bank Name ---------------------------------------------------------->
        <tr>
            <td align="left">BANK NAME<br><input type="text" name="name" maxlength="30" required/>
                (max 30 characters a-z and A-Z)
            </td>
        </tr>

        <!----- Address ---------------------------------------------------------->
        <tr>
            <td align="left">ADDRESS<br><textarea name="address" rows="2" cols="30" required></textarea></td>


        <!----- Phone Numbers ---------------------------------------------------------->

            <td align="left">PHONE NUMBERS<br><textarea name="phoneNumber" rows="2" cols="30" required></textarea></td>
        </tr>

        <!----- Fax ---------------------------------------------------------->
        <tr>
            <td align="left">FAX<br><textarea name="fax" rows="2" cols="30" required></textarea></td>

        <!----- Email ---------------------------------------------------------->
            <td align="left">EMAIL<br><input style="width:200px;" type="email" name="mail" maxlength="100" required/></td>
        </tr>

        <!----- Password ---------------------------------------------------------->
        <tr>
            <td align="left">PASSWORD<br><input style="width:300px;" type="password" name="password" maxlength="100" required/></td>
        </tr>

        <!----- Working Hours ---------------------------------------------------------->
        <tr>
            <td align="left">WORKING HOURS<br><input style="width:300px;"  type="text" name="workingHours" required/>
            </td>

        <!----- Days Off ---------------------------------------------------------->

            <td align="left">DAYS OFF<br><input style="width:200px;" type="text" name="daysOff" required/>
            </td>
        </tr>

        <!----- Account ---------------------------------------------------------->
        <tr>
            <td align="left">ACCOUNT<br><input style="width:300px;" type="text" name="account" maxlength="16" required/>
            </td>
        </tr>

        <!----- Submit and Reset ------------------------------------------------->
        <tr>
            <td colspan="2" align="center">
                <button class="blue" name="button" value="ok">OK</button>
                <button class="blue" type="reset" value="Reset">Reset</button>
                <button class="blue" name="button" formnovalidate>Back</button>
            </td>
        </tr>
    </table>

</form>

</body>
</html>