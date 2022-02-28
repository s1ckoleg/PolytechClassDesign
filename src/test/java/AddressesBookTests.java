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
    void addMember() {
        assertEquals("Petya address is Mendeleeva, 2, 28.\nVasya address is Polytechnicheskaya, 8, 4.\n",
                addressBook.toString()
        );

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
    void illegalStreetAddMember() throws BadValueException {
        AddressesBook.Address newAddress = new AddressesBook.Address("Mendeleeva!", 2, 28);
        Throwable exception = assertThrows(BadValueException.class, () -> addressBook.addMember("Petya", newAddress));
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
        addressBook.removeMember("Petya");
        assertEquals(
                "Vasya address is Polytechnicheskaya, 8, 4.\n",
                addressBook.toString()
        );
    }

    @Test
    void nonExistentRemoveMemeber() {
        Throwable exception = assertThrows(BadValueException.class, () -> addressBook.removeMember("Anonymous"));
        assertEquals(MISSING_MEMBER_ERROR.getMessage(), exception.getMessage());
    }

    @Test
    void changeAddress() throws BadValueException {
        AddressesBook.Address newAddress = new AddressesBook.Address("Dibenko", 228, 1337);
        addressBook.changeAddress("Petya", newAddress);

        assertEquals(
                "Petya address is Dibenko, 228, 1337.\nVasya address is Polytechnicheskaya, 8, 4.\n",
                addressBook.toString()
        );
    }

    @Test
    void nonExistentChangeAddress() throws BadValueException {
        AddressesBook.Address newAddress = new AddressesBook.Address("Dibenko", 228, 1337);
        Throwable exception = assertThrows(BadValueException.class, () -> addressBook.changeAddress("Anonymous", newAddress));
        assertEquals(MISSING_MEMBER_ERROR.getMessage(), exception.getMessage());
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
        Set<String> suitableMembers = new HashSet<>(Arrays.asList("Petya"));
        assertEquals(
                suitableMembers,
                addressBook.getMemberByStreet("Mendeleeva")
        );
    }

    @Test
    void getMemberByStreetAndHouse() {
        Set<String> suitableMembers = new HashSet<>(Arrays.asList("Petya"));
        assertEquals(
                suitableMembers,
                addressBook.getMemberByStreetAndHouse("Mendeleeva", 2)
        );
    }
}
