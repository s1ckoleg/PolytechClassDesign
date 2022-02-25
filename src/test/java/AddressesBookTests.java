import Books.AddressesBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class AddressesBookTests {
    AddressesBook classInstance = new AddressesBook();

    AddressesBook.Address address1 = new AddressesBook.Address("Mendeleeva", 2, 28);
    AddressesBook.Address address2 = new AddressesBook.Address("Polytechnicheskaya", 8, 4);

    public String illegalNameError = "Name contains illegal symbols";
    public String illegalStreetError = "Street contains illegal symbols";
    public String missingMemberError = "Can't find suitable member";


    @BeforeEach
    void setUp() {
        classInstance.addMember("Petya", address1);
        classInstance.addMember("Vasya", address2);
    }

    @Test
    void addMember() {
        assertEquals(
                "Petya address is Mendeleeva, 2, 28.\nVasya address is Polytechnicheskaya, 8, 4.\n",
                classInstance.toString()
        );
    }

    @Test
    void illegalNameAddMember() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> classInstance.addMember("Petya1", address1));
        assertEquals(illegalNameError, exception.getMessage());
    }

    @Test
    void illegalStreetAddMember() {
        AddressesBook.Address newAddress = new AddressesBook.Address("Mendeleeva1", 2, 28);
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> classInstance.addMember("Petya", newAddress));
        assertEquals(illegalStreetError, exception.getMessage());
    }

    @Test
    void removeMember() {
        classInstance.removeMember("Petya");
        assertEquals(
                "Vasya address is Polytechnicheskaya, 8, 4.\n",
                classInstance.toString()
        );
    }

    @Test
    void nonExistentRemoveMemeber() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> classInstance.removeMember("Anonymous"));
        assertEquals(missingMemberError, exception.getMessage());
    }

    @Test
    void changeAddress() {
        AddressesBook.Address newAddress = new AddressesBook.Address("Dibenko", 228, 1337);
        classInstance.changeAddress("Petya", newAddress);

        assertEquals(
                "Petya address is Dibenko, 228, 1337.\nVasya address is Polytechnicheskaya, 8, 4.\n",
                classInstance.toString()
        );
    }

    @Test
    void nonExistentChangeAddress() {
        AddressesBook.Address newAddress = new AddressesBook.Address("Dibenko", 228, 1337);
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> classInstance.changeAddress("Anonymous", newAddress));
        assertEquals(missingMemberError, exception.getMessage());
    }

    @Test
    void getAddress() {
        assertEquals(
                address2.toString(),
                classInstance.getAddress("Vasya")
        );
    }

    @Test
    void nonExistentGetAddress() {
        assertEquals(
                "",
                classInstance.getAddress("Anonymous")
        );
    }

    @Test
    void getMemberByStreet() {
        List<String> suitableMembers = Arrays.asList("Petya");
        assertEquals(
                suitableMembers,
                classInstance.getMemberByStreet("Mendeleeva")
        );
    }

    @Test
    void getMemberByStreetAndHouse() {
        List<String> suitableMembers = Arrays.asList("Petya");
        assertEquals(
                suitableMembers,
                classInstance.getMemberByStreetAndHouse("Mendeleeva", 2)
        );
    }
}
