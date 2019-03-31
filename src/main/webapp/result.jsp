<%@ page import ="java.util.*" %>
<%@page import="cat.jorda.model.AddressBook"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Address Book</title>
    <style type="text/css">
        * {margin: 0; padding: 0;}
        #container {height: 100%; width:50%; font-size: 0;}
        #ListAddressBook-div, #SearchAddressBook-div {display: inline-block; *display: inline; zoom: 1; vertical-align: top; font-size: 12px;}
        #left, #right {display: inline-block; *display: inline; zoom: 1; vertical-align: top; font-size: 12px;}
        #left {width: 25%;}
        #right {width: 25%;}
    </style>
</head>

<script>
    function addDataRows()
    {
        <%-- Arranging data in tabular form --%>
        for (var i = 1; i < 50; i++) {
            console.log("adding");
            var container = document.getElementById("wrap-container");
            var clone = container.firstElementChild.cloneNode(true);
            clone.getElementsByTagName('input')[0].id = "name" + i;
            clone.getElementsByTagName('input')[0].name = "name" + i;

            clone.getElementsByTagName('input')[1].id = "phone" + i;
            clone.getElementsByTagName('input')[1].name = "phone" + i;

            container.appendChild(clone);
        }
    }

</script>
<body onload="addDataRows();">
<center>
    <h1>
        Add Phone numbers
    </h1>
    <%
        Integer counter = (Integer)request.getAttribute("phones-in-memory");
        out.println("<br>Saved: " + counter + " phones in memory<br>");

        List<AddressBook> result= (List<AddressBook>) request.getAttribute("wrongInputList");

        if (result != null && result.size() > 0) {
            Iterator it = result.iterator();

            StringBuilder output = new StringBuilder();
            output.append("We have some wrong inputs<br>");

            while (it.hasNext()) {
                AddressBook addressBook = (AddressBook) it.next();
                output.append("Wrong Input Name : ").append(addressBook.name).append(" PhoneNum: ").append(addressBook.phoneNum).append("<br>");
            }

            out.println(output.toString());
        }
    %>
    <div id="container-buttons">
        <div id="ListAddressBook-div">
            <form action="AddPhoneName" method="post">
                <input type="submit" name="actionBtn" value="List Address Book">
            </form><br>
        </div>

        <div id="SearchAddressBook-div">
            <form action="AddPhoneName" method="post">
                <input type="submit" name="actionBtn" value="Search Address Book">
            </form><br>
        </div>
    </div>

    <form method="post" action="AddPhoneName">
        <input type="submit" value="Submit" name="actionBtn">
        <div id="wrap-container">
            <div id="container">
                <div id="left">Name: <input type="text" id="name0" name="name0" size="50"><br></div>
                <div id="right">Phone: <input type="text" id="phone0" name="phone0" size="50"><br></div>
            </div>
        </div>
        <BR>
        <input type="submit" value="Submit" name="actionBtn">
    </form>
</center>
</body>
</html>