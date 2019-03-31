package cat.jorda;

import cat.jorda.model.AddressBook;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressBookCheckerTest {

    @Test
    public void checkAddressBookCorrectName() {

        AddressBook addressBook = new AddressBook("xavi", "0034602785423");
        assertTrue(AddressBookChecker.checkAddressBook(addressBook));
    }

    @Test
    public void checkAddressBookCorrectNameBlank() {

        AddressBook addressBook = new AddressBook(" xavi ", "0034602785423");
        assertTrue(AddressBookChecker.checkAddressBook(addressBook));
    }

    @Test
    public void checkAddressBookNameOtherChars() {

        AddressBook addressBook = new AddressBook("xavi2", "0034602785423");
        assertFalse(AddressBookChecker.checkAddressBook(addressBook));
    }

    @Test
    public void checkAddressBookNameOtherChars2() {

        AddressBook addressBook = new AddressBook("@xavi#", "0034602785423");
        assertFalse(AddressBookChecker.checkAddressBook(addressBook));
    }

    @Test
    public void checkCheckPhone1() {

        AddressBook addressBook = new AddressBook("xavi", "02012345567");
        assertTrue(AddressBookChecker.checkAddressBook(addressBook));
    }

    @Test
    public void checkCheckPhone2() {

        AddressBook addressBook = new AddressBook("xavi", "+443739182931");
        assertTrue(AddressBookChecker.checkAddressBook(addressBook));
    }

    @Test
    public void checkCheckPhone3() {

        AddressBook addressBook = new AddressBook("xavi", "+44(0)203739182");
        assertTrue(AddressBookChecker.checkAddressBook(addressBook));
    }

    //--

    // does not start with a 0 or +
    @Test
    public void checkCheckPhone4() {

        AddressBook addressBook = new AddressBook("xavi", "1322282828");
        assertFalse(AddressBookChecker.checkAddressBook(addressBook));
    }

    //020-10391-20201 (has non-numeric characters)
    @Test
    public void checkCheckPhone5() {

        AddressBook addressBook = new AddressBook("xavi", "020-10391-20201");
        assertFalse(AddressBookChecker.checkAddressBook(addressBook));
    }

    //+01029818 (starts with + followed by 0)
    @Test
    public void checkCheckPhone6() {

        AddressBook addressBook = new AddressBook("xavi", "+01029818");
        assertFalse(AddressBookChecker.checkAddressBook(addressBook));
    }

    //+44(0)202839(4)3932 - more than one pair of brackets
    @Test
    public void checkCheckPhone7() {

        AddressBook addressBook = new AddressBook("xavi", "+44(0)202839(4)3932");
        assertFalse(AddressBookChecker.checkAddressBook(addressBook));
    }
}