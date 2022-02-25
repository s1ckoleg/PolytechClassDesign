package Books;
import java.util.*;

public class AddressesBook {
    public HashMap<String, Address> membersList = new HashMap<>();
    final static String missingMemberError = "Can't find suitable member";
    final static String illegalNameError = "Name contains illegal symbols";
    final static String illegalStreetError = "Street contains illegal symbols";

    public static class Address {
        private String street;
        private Integer house, flat;

        public Address(String street, Integer house, Integer flat) {
            this.street = street;
            this.house = house;
            this.flat = flat;
        }

        public Address getAddress() {
            return new Address(street, house, flat);
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

        public void setStreet(String street) {
            this.street = street;
        }

        public Integer getHouse() {
            return house;
        }

        public void setHouse(Integer house) {
            this.house = house;
        }

        public Integer getFlat() {
            return flat;
        }

        public void setFlat(Integer flat) {
            this.flat = flat;
        }
    }

    public Boolean wordCheck(String word) {
        List<String> invalidCharacters = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
        for (String character:invalidCharacters) {
            if (word.contains(character)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public void addMember(String name, Address address) {
        if (wordCheck(name) == Boolean.FALSE) {
            throw new IllegalArgumentException(illegalNameError);
        } else if (wordCheck(address.street) == Boolean.FALSE) {
            throw new IllegalArgumentException(illegalStreetError);
        } else {
            membersList.put(name, address.getAddress());
        }
    }

    public void removeMember(String name) {
        if (membersList.containsKey(name)) {
            membersList.remove(name);
        } else {
            throw new IllegalArgumentException(missingMemberError);
        }
    }

    public void changeAddress(String name, Address newAddress) {
        if (membersList.containsKey(name)) {
            membersList.put(name, newAddress.getAddress());
        } else {
            throw new IllegalArgumentException(missingMemberError);
        }
    }

    public String getAddress(String name){
        if (membersList.containsKey(name)) {
            return membersList.get(name).toString();
        } else {
            return "";
        }
    }

    public List<String> getMemberByStreet(String street) {
        ArrayList<String> suitableMembers = new ArrayList<>();
        for (Map.Entry<String, Address> entry : membersList.entrySet()) {
            if (Objects.equals(entry.getValue().street, street)) {
                suitableMembers.add(entry.getKey());
            }
        }

        return suitableMembers;
    }

    public List<String> getMemberByStreetAndHouse(String street, Integer house) {
        ArrayList<String> suitableMembers = new ArrayList<>();
        for (Map.Entry<String, Address> entry : membersList.entrySet()) {
            if (Objects.equals(entry.getValue().street, street) && Objects.equals(entry.getValue().house, house)) {
                suitableMembers.add(entry.getKey());
            }
        }

        return suitableMembers;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (Map.Entry<String, Address> entry : membersList.entrySet()) {
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
        return Objects.equals(membersList, that.membersList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(membersList);
    }
}

