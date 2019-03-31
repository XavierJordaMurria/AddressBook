<%@ page import ="java.util.*" %>
<%@page import="cat.jorda.model.AddressBook"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Address Book</title>
    <style type="text/css">
        * {margin: 0; padding: 0;}
        #ListAddressBook-div, #SearchAddressBook-div {display: inline-block; *display: inline; zoom: 1; vertical-align: top; font-size: 12px;}
    </style>
</head>

<body>
<center>
    <h1>
        List Address Book
    </h1>
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
    <%
        List<AddressBook> result= (List<AddressBook>) request.getAttribute("address-book-list");

        if (result != null && result.size() > 0) {
            Iterator it = result.iterator();

            StringBuilder output = new StringBuilder();
            output.append("All phones in the address book <br>");

            while (it.hasNext()) {
                AddressBook addressBook = (AddressBook) it.next();
                output.append("Name : ").append(addressBook.name).append(" PhoneNum: ").append(addressBook.phoneNum).append("<br>");
            }

            out.println(output.toString());
        }
    %>
</center>
</body>
</html>