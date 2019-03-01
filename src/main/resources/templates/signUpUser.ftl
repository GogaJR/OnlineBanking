<html>
<head>
    <title>Title</title>
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

    .btnSubmit {
        border-radius: 5px;
        padding: 15px 25px;
        font-size: 22px;
        text-decoration: none;
        margin: 20px;
        color: #fff;
        position: relative;
        display: inline-block;
    }

    .btnSubmit:active {
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
</style>

<body>
<#if error??>
    <div class="alert alert-danger" role="alert">There is an account with this mail</div>
</#if>
<form method="post" action="/signUp">

<h3>SIGN UP</h3>


<table align="center" cellpadding = "10" width="800">

    <!----- First Name ---------------------------------------------------------->
    <tr>
        <td align="left">FIRST NAME<br><input type="text" name="name" maxlength="30" required/>
            </td>



    <!----- Last Name ---------------------------------------------------------->

        <td align="left">LAST NAME<br><input type="text" name="surname" maxlength="30" required/>
            </td>

    </tr>

    <!----- Date Of Birth -------------------------------------------------------->


    <!----- Email ---------------------------------------------------------->
    <tr>
        <td align="left">EMAIL<br><input type="email" name="mail" maxlength="100" required/></td>


    <!----- Password ---------------------------------------------------------->
        <td align="left">PASSWORD<br><input type="password" name="password" maxlength="100" required/></td>
    </tr>

    <!----- Gender ----------------------------------------------------------->
    <tr>
        <td align="left">GENDER<br>
            Male <input type="radio" name="sex" value="Male" checked/>
            Female <input type="radio" name="sex" value="Female" />
        </td>

        <td align="left" colspan="1">DATE OF BIRTH<br>
            <select name="dayOfBirth" id="dayOfBirth" required>
                <#assign x=31>
                <#list 1..x as i>
                    <option value=${i}>${i}</option>
                </#list>
            </select>

            <select id="Birthday_Month" name="monthOfBirth" required>
                <option value="1">Jan</option>
                <option value="2">Feb</option>
                <option value="3">Mar</option>
                <option value="4">Apr</option>
                <option value="5">May</option>
                <option value="6">Jun</option>
                <option value="7">Jul</option>
                <option value="8">Aug</option>
                <option value="9">Sep</option>
                <option value="10">Oct</option>
                <option value="11">Nov</option>
                <option value="12">Dec</option>
            </select>

            <select name="yearOfBirth" id="yearOfBirth" required>
                <#assign x=1900>
                <#setting number_format="computer">
                <#list 2005..x as i>
                    <option value=${i}>${i}</option>
                </#list>
            </select>
        </td>
    </tr>

    <!----- Address ---------------------------------------------------------->
    <tr>
        <td align="left">PLACE OF BIRTH<br><textarea name="placeOfBirth" placeholder="For Example: Yerevan, Armenia" rows="2" cols="30" required></textarea></td>


    <!----- Address ---------------------------------------------------------->

        <td align="left">PLACE OF LIVING<br><textarea name="placeOfLiving" placeholder="For Example: Yerevan, Armenia" rows="2" cols="30" required></textarea></td>
    </tr>

    <!----- Passport Serial Number ---------------------------------------------------------->
    <tr>
        <td align="left">PASSPORT SERIAL NUMBER<br><input type="text" name="passportSerialNumber" minlength="9" maxlength="9" required/>
            (9 digit text)
        </td>
    </tr>

    <!----- Submit and Reset ------------------------------------------------->
    <tr>
        <td colspan="2" align="center">
            <button class="btnSubmit blue" name="button" value="ok">OK</button>
            <button class="btnSubmit blue" type="reset">Reset</button>
            <button class="btnSubmit blue" name="button" formnovalidate>Back</button>
        </td>
    </tr>
</table>

</form>

</body>
</html>