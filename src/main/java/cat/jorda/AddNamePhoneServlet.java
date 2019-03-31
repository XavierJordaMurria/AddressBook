package cat.jorda;

import cat.jorda.model.AddressBook;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

@WebServlet(
        name = "addnamephoneservlet",
        urlPatterns = "/AddPhoneName"
)
public class AddNamePhoneServlet extends MainServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String action = req.getParameter("actionBtn");

        if("Submit".equals(action))
            addEvent(req, resp);
        else if("List Address Book".equals(action))
            listAddressBook(req, resp);
        else if ("Search Address Book".equals(action))
            searchAddressBook(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String action = req.getParameter("actionBtn");

        if("Search".equals(action))
            filterAddressBook(req, resp);
    }

    private void addEvent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        AddressBook addressBook;
        LinkedList<AddressBook> wrongInputList = new LinkedList<AddressBook>();

        for (int i = 0; i<50; i++)
        {
            String name = req.getParameter("name"+i);
            String phone = req.getParameter("phone"+i);

            if(name == null || name.isEmpty() || phone == null || phone.isEmpty())
                continue;

            addressBook = new AddressBook(name, phone);

            if (AddressBookChecker.checkAddressBook(addressBook))
                this.addressBookList.add(addressBook);
            else
                wrongInputList.add(addressBook);
        }

        if (wrongInputList.size() > 0)
            req.setAttribute("wrongInputList", wrongInputList);

        req.setAttribute("phones-in-memory", addressBookList.size());

        RequestDispatcher view = req.getRequestDispatcher("result.jsp");
        view.forward(req, resp);
    }

    private void listAddressBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.setAttribute("address-book-list", addressBookList);
        RequestDispatcher view = req.getRequestDispatcher("ListAddressBook.jsp");
        view.forward(req, resp);
    }

    private void searchAddressBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        RequestDispatcher view = req.getRequestDispatcher("search.jsp");
        view.forward(req, resp);
    }

    private void filterAddressBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        ArrayList<AddressBook> filteredList = new ArrayList<>();
        String name = req.getParameter("searchByName");

        if (name != null)
            filteredList = this.filterByName(name);

        String phone = req.getParameter("searchByPhone");

        if (phone != null)
            filteredList = this.filterByPhone(phone);

        req.setAttribute("filteredList", filteredList);
        RequestDispatcher view = req.getRequestDispatcher("search.jsp");
        view.forward(req, resp);
    }

    private ArrayList<AddressBook> filterByName(String name)
    {
        ArrayList<AddressBook> filteredList = new ArrayList<>();

        this.addressBookList.stream()
                .filter(item->item.name.contains(name))
                .forEach(filteredList::add);

        return filteredList;
    }

    private ArrayList<AddressBook> filterByPhone(String number)
    {
        ArrayList<AddressBook> filteredList = new ArrayList<>();
        this.addressBookList.forEach(item ->
        {
            if(item.phoneNum.equals(number))
                filteredList.add(item);
        });

        return filteredList;
    }
}