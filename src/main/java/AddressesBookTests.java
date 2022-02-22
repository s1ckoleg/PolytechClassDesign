import Books.AddressesBook;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Books.AddressesBook.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddressesBookTests {
    AddressesBook.Address address1 = new AddressesBook.Address("Mendeleeva", "2", "28");
    AddressesBook.Address address2 = new AddressesBook.Address("Polytechnicheskaya", "8", "4");

    @Test
    void addMember() {
        AddressesBook classInstance = new AddressesBook();
        classInstance.addMember("Petya", address1);
        classInstance.addMember("Vasya", address2);
        assertEquals(
                "Petya address is Mendeleeva, 2, 28.\nVasya address is Polytechnicheskaya, 8, 4.\n",
                classInstance.toString()
        );
    }

    @Test
    void removeMember() {
        AddressesBook classInstance = new AddressesBook();
        classInstance.addMember("Petya", address1);
        classInstance.addMember("Vasya", address2);
        classInstance.removeMember("Petya");
        assertEquals(
                "Vasya address is Polytechnicheskaya, 8, 4.\n",
                classInstance.toString()
        );
    }

    @Test
    void changeAddress() {
        AddressesBook classInstance = new AddressesBook();
        AddressesBook.Address newAddress = new AddressesBook.Address("Dibenko", "228", "1337");
        classInstance.addMember("Petya", address1);
        classInstance.addMember("Vasya", address2);
        classInstance.changeAddress("Petya", newAddress);

        assertEquals(
                "Petya address is Dibenko, 228, 1337.\nVasya address is Polytechnicheskaya, 8, 4.\n",
                classInstance.toString()
        );
    }

    @Test
    void getAddress() {
        AddressesBook classInstance = new AddressesBook();
        classInstance.addMember("Petya", address1);
        classInstance.addMember("Vasya", address2);
        assertEquals(
                address2.toString(),
                classInstance.getAddress("Vasya")
        );
    }

    @Test
    void getMemberByStreet() {
        AddressesBook classInstance = new AddressesBook();
        List<String> suitableMembers = Arrays.asList("Petya");
        classInstance.addMember("Petya", address1);
        classInstance.addMember("Vasya", address2);
        assertEquals(
                suitableMembers,
                classInstance.getMemberByStreet("Mendeleeva")
        );
    }

    @Test
    void getMemberByStreetAndHouse() {
        AddressesBook classInstance = new AddressesBook();
        List<String> suitableMembers = Arrays.asList("Petya");
        classInstance.addMember("Petya", address1);
        classInstance.addMember("Vasya", address2);
        assertEquals(
                suitableMembers,
                classInstance.getMemberByStreetAndHouse("Mendeleeva", "2")
        );
    }
}
