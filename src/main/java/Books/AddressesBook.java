package Books;
import java.util.*;

public class AddressesBook {
    public HashMap<String, Address> membersList = new HashMap<>();
    final static String missingMemberError = "Can't find this member";

    public static class Address {
        private String street, house, flat;

        public Address(String street, String house, String flat) {
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

        public String getHouse() {
            return house;
        }

        public void setHouse(String house) {
            this.house = house;
        }

        public String getFlat() {
            return flat;
        }

        public void setFlat(String flat) {
            this.flat = flat;
        }
    }


    public void addMember(String name, Address address) {
        membersList.put(name, address.getAddress());
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
            return "Can't find this member in book :(";
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

    public List<String> getMemberByStreetAndHouse(String street, String house) {
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

