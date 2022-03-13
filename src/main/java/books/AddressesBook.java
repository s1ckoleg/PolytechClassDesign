package books;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static books.Errors.*;

public class AddressesBook {
    private final Map<String, Address> members = new HashMap<>();
    private static final Pattern checkWordPattern = Pattern.compile("[0-9:?;!]");

    public static class Address {
        // statiс, потому что это позволяет создавать экземпляр класса без создания экземпляра суперкласса
        private String street;
        private Integer house, flat;

        public Address(String street, Integer house, Integer flat) throws BadValueException {
            this.setStreet(street);
            this.setHouse(house);
            this.setFlat(flat);
        }

        @Override
        public String toString() {
            return street + ", " + house + ", " + flat;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Address address = (Address) o;
            return Objects.equals(street, address.street) && Objects.equals(house, address.house)
                    && Objects.equals(flat, address.flat);
        }

        @Override
        public int hashCode() {
            return Objects.hash(street, house, flat);
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) throws BadValueException {

            if (street == null) {
                throw new NullPointerException(ILLEGAL_STREET_ERROR.getMessage());
            } else if (wordCheck(street)) {
                throw new BadValueException(ILLEGAL_STREET_ERROR.getMessage());
            } else {
                this.street = street;
            }
        }

        public Integer getHouse() {
            return house;
        }

        public void setHouse(Integer house) throws BadValueException {
            if (house == null) {
                throw new NullPointerException(ILLEGAL_HOUSE_ERROR.getMessage());
            } else if (house <= 0) {
                throw new BadValueException(ILLEGAL_HOUSE_ERROR.getMessage());
            } else {
                this.house = house;
            }
        }

        public Integer getFlat() {
            return flat;
        }

        public void setFlat(Integer flat) throws BadValueException {
            if (flat == null) {
                throw new NullPointerException(ILLEGAL_FLAT_ERROR.getMessage());
            } else if (flat <= 0) {
                throw new BadValueException(ILLEGAL_FLAT_ERROR.getMessage());
            } else {
                this.flat = flat;
            }
        }
    }

    private static boolean wordCheck(String word) {
        Matcher matcher = checkWordPattern.matcher(word);
        return matcher.find();
    }

    public boolean addMember(String name, Address address) throws BadValueException {

        if (wordCheck(name)) {
            throw new BadValueException(ILLEGAL_NAME_ERROR.getMessage());
        }

        if (members.containsKey(name)) {
            return false;
        } else {
            members.put(name, address);
            return true;
        }
    }

    public boolean removeMember(String name) {
        if (members.containsKey(name)) {
            members.remove(name);
            return true;
        } else {
            return false;
        }
    }

    public boolean changeAddress(String name, Address newAddress) {
        if (members.containsKey(name)) {
            members.put(name, newAddress);
            return true;
        } else {
            return false;
        }
    }

    @Nullable
    public Address getAddress( String name) {
        return members.get(name);
    }

    @NotNull
    public Set<String> getMemberListByStreet(String street) {
        Set<String> suitableMembers = new HashSet<>();

        for (Map.Entry<String, Address> entry : members.entrySet()) {
            if (Objects.equals(entry.getValue().street, street)) {
                suitableMembers.add(entry.getKey());
            }
        }

        return suitableMembers;
    }

    @NotNull
    public Set<String> getMemberListByStreetAndHouse(String street, Integer house) {
        Set<String> suitableMembers = new HashSet<>();
        for (Map.Entry<String, Address> entry : members.entrySet()) {
            if (Objects.equals(entry.getValue().street, street) && Objects.equals(entry.getValue().house, house)) {
                suitableMembers.add(entry.getKey());
            }
        }

        return suitableMembers;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (Map.Entry<String, Address> entry : members.entrySet()) {
            string.append(entry.getKey()).append(" address is ").append(entry.getValue().toString()).append(".")
                    .append(System.lineSeparator());
        }
        return string.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressesBook that = (AddressesBook) o;
        return Objects.equals(members, that.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(members);
    }
}

// maven, static
