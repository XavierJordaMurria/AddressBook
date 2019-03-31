package cat.jorda;

import cat.jorda.model.AddressBook;
import java.util.regex.Pattern;

public class AddressBookChecker {

    public static boolean checkAddressBook(AddressBook addressBook)
    {
        return AddressBookChecker.checkName(addressBook.name) && AddressBookChecker.checkPhone(addressBook.phoneNum);
    }

    /**
     *
     * @param name String. Parameter to be checked
     * @return boolean. True when the name parameter contain just letters, False otherwise
     */
    private static boolean checkName(String name)
    {
        return Pattern.matches("[a-zA-Z ]+", name);
    }

    private static boolean checkPhone(String phone)
    {
        String pattern = "^\\+[1-9]\\d{1,}\\(?(\\d{0,})\\)?\\d{1,}|(0|00)[1-9]\\d{9,}$";
        return Pattern.matches(pattern, phone);
    }
}
