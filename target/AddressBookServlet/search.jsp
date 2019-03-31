<%@ page import ="java.util.*" %>
<%@page import="cat.jorda.model.AddressBook"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search in Address Book</title>
    <style type="text/css">
        * {margin: 0; padding: 0;}
        #ListAddressBook-div, #SearchAddressBook-div {display: inline-block; *display: inline; zoom: 1; vertical-align: top; font-size: 12px;}
    </style>
</head>
<body>

</body>
</html>

<html>
<body>
    <center>
        <h1>
            Search in Address Book
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
        <form method="get" action="AddPhoneName">
            Search By Name: <input type="text" name="searchByName">
            <input type="submit" value="Search" name="actionBtn">
        </form>

        <form method="get" action="AddPhoneName">
            Search By Phone Number: <input type="text" name="searchByPhone">
            <input type="submit" value="Search" name="actionBtn">
        </form>

        <%
            List<AddressBook> result= (List<AddressBook>) request.getAttribute("filteredList");

            if (result != null && result.size() > 0) {
                Iterator it = result.iterator();

                StringBuilder output = new StringBuilder();
                output.append("Filtered results <br>");

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