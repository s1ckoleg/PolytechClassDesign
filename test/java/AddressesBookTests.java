import books.AddressesBook;
import books.BadValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import static books.Errors.*;

public class AddressesBookTests {
    AddressesBook addressBook = new AddressesBook(); // rename

    AddressesBook.Address address1 = new AddressesBook.Address("Mendeleeva", 2, 28);
    AddressesBook.Address address2 = new AddressesBook.Address("Polytechnicheskaya", 8, 4);

    public AddressesBookTests() throws BadValueException {
    }


    @BeforeEach
    void setUp() throws BadValueException {
        addressBook.addMember("Petya", address1);
        addressBook.addMember("Vasya", address2);
    }

    @Test
    void addMember() throws BadValueException {
        AddressesBook comparativeAddressBook = new AddressesBook();
        comparativeAddressBook.addMember("Petya", address1);
        comparativeAddressBook.addMember("Vasya", address2);

        assertEquals(comparativeAddressBook, addressBook);
    }

    @Test
    void duplicateNameAddMember() throws BadValueException {
        assertFalse(addressBook.addMember("Petya", address1));
    }

    @Test
    void illegalNameAddMember() {
        Throwable exception = assertThrows(BadValueException.class, () -> addressBook.addMember("Petya1", address1));
        assertEquals(ILLEGAL_NAME_ERROR.getMessage(), exception.getMessage());
    }

    @Test
    void nullNameAddMember() {
        Throwable exception = assertThrows(NullPointerException.class, () -> addressBook.addMember(null, address1));
        assertEquals(ILLEGAL_NAME_ERROR.getMessage(), exception.getMessage());
    }

    @Test
    void nullStreetAddMember() {
        Throwable exception = assertThrows(NullPointerException.class, () -> addressBook.addMember("Egor",
                new AddressesBook.Address(null, 2, 28)));
        assertEquals(ILLEGAL_STREET_ERROR.getMessage(), exception.getMessage());
    }

    @Test
    void illegalStreetAddMember() {
        Throwable exception = assertThrows(BadValueException.class, () -> addressBook.addMember("Petya", new AddressesBook.Address("Mendeleeva!", 2, 28)));
        assertEquals(ILLEGAL_STREET_ERROR.getMessage(), exception.getMessage());
    }

    @Test
    void nullHouseAddMember() {
        Throwable exception = assertThrows(NullPointerException.class, () -> addressBook.addMember("Egor",
                new AddressesBook.Address("Mendeleeva", null, 28)));
        assertEquals(ILLEGAL_HOUSE_ERROR.getMessage(), exception.getMessage());
    }

    @Test
    void illegalHouseAddMember() {
        Throwable exception = assertThrows(BadValueException.class, () -> addressBook.addMember("Egor",
                new AddressesBook.Address("Mendeleeva", -1, 28)));
        assertEquals(ILLEGAL_HOUSE_ERROR.getMessage(), exception.getMessage());
    }

    @Test
    void nullFlatAddMember() {
        Throwable exception = assertThrows(NullPointerException.class, () -> addressBook.addMember("Egor",
                new AddressesBook.Address("Mendeleeva", 2, null)));
        assertEquals(ILLEGAL_FLAT_ERROR.getMessage(), exception.getMessage());
    }

    @Test
    void illegalFlatAddMember() {
        Throwable exception = assertThrows(BadValueException.class, () -> addressBook.addMember("Egor",
                new AddressesBook.Address("Mendeleeva", 2, 0)));
        assertEquals(ILLEGAL_FLAT_ERROR.getMessage(), exception.getMessage());
    }

    @Test
    void removeMember() throws BadValueException {
        AddressesBook comparativeAddressBook = new AddressesBook();
        comparativeAddressBook.addMember("Vasya", address2);
        addressBook.removeMember("Petya");

        assertEquals(comparativeAddressBook, addressBook);
    }

    @Test
    void nonExistentRemoveMemeber() {
        assertFalse(addressBook.removeMember("Anonymous"));
    }

    @Test
    void changeAddress() throws BadValueException {
        AddressesBook.Address newAddress = new AddressesBook.Address("Dibenko", 228, 1337);

        AddressesBook comparativeAddressBook = new AddressesBook();
        comparativeAddressBook.addMember("Petya", newAddress);
        comparativeAddressBook.addMember("Vasya", address2);
        addressBook.changeAddress("Petya", newAddress);

        assertEquals(comparativeAddressBook, addressBook);
    }

    @Test
    void nonExistentChangeAddress() throws BadValueException {
        AddressesBook.Address newAddress = new AddressesBook.Address("Dibenko", 228, 1337);
        assertFalse(addressBook.changeAddress("Anonymous", newAddress));
    }

    @Test
    void getAddress() {
        assertEquals(
                address2,
                addressBook.getAddress("Vasya")
        );
    }

    @Test
    void nonExistentGetAddress() {
        assertNull(addressBook.getAddress("Anonymous"));
    }

    @Test
    void getMemberByStreet() { // более сложные тесты
        Set<String> suitableMembers = new HashSet<>(Set.of("Petya"));
        assertEquals(
                suitableMembers,
                addressBook.getMemberListByStreet("Mendeleeva")
        );
    }

    @Test
    void getMemberByStreetAndHouse() {
        Set<String> suitableMembers = new HashSet<>(Set.of("Petya"));
        assertEquals(
                suitableMembers,
                addressBook.getMemberListByStreetAndHouse("Mendeleeva", 2)
        );
    }

    @Test
    void setters() throws BadValueException {
        address1.setStreet("Mira");
        assertEquals("Mira", address1.getStreet());

        address1.setHouse(100);
        assertEquals(100, address1.getHouse());

        address1.setFlat(1000);
        assertEquals(1000, address1.getFlat());
    }

    @Test
    void setNullStreet() {
        Throwable exception = assertThrows(NullPointerException.class, () -> address1.setStreet(null));
        assertEquals(ILLEGAL_STREET_ERROR.getMessage(), exception.getMessage());
    }

    @Test
    void setNullHouse() {
        Throwable exception = assertThrows(NullPointerException.class, () -> address1.setHouse(null));
        assertEquals(ILLEGAL_HOUSE_ERROR.getMessage(), exception.getMessage());
    }

    @Test
    void setIllegalHouse() {
        Throwable exception = assertThrows(BadValueException.class, () -> address1.setHouse(-1));
        assertEquals(ILLEGAL_HOUSE_ERROR.getMessage(), exception.getMessage());
    }

    @Test
    void setNullFlat() {
        Throwable exception = assertThrows(NullPointerException.class, () -> address1.setFlat(null));
        assertEquals(ILLEGAL_FLAT_ERROR.getMessage(), exception.getMessage());
    }

    @Test
    void setIllegalFlat() {
        Throwable exception = assertThrows(BadValueException.class, () -> address1.setFlat(0));
        assertEquals(ILLEGAL_FLAT_ERROR.getMessage(), exception.getMessage());
    }
}
