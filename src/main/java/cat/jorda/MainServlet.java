package cat.jorda;

import cat.jorda.model.AddressBook;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;

public abstract class MainServlet extends HttpServlet {

    ArrayList<AddressBook> addressBookList = new ArrayList<AddressBook>();
}
