import java.util.*;

public final class AddressesBook {
    public static HashMap<String, ArrayList<String>> membersList = new HashMap<String, ArrayList<String>>();
    private String name;

    static class Address {
        private String street, house, flat;

        Address(String street, String house, String flat) {
            this.street = street;
            this.house = house;
            this.flat = flat;
        }

        public ArrayList<String> getAddress() {
            ArrayList<String> address = new ArrayList<String>();
            address.add(street);
            address.add(house);
            address.add(flat);
            return address;
        }

        @Override
        public String toString() {
            return street + ", " + house + ", " + flat + ".";
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
            throw new IllegalArgumentException("This name isn't member");
        }
    }

    public void changeAddress(String name, Address newAddress) {
        if (membersList.containsKey(name)) {
            membersList.put(name, newAddress.getAddress());
        } else {
            throw new IllegalArgumentException("This name isn't member");
        }
    }

    public String getAddress(String name){
        if (membersList.containsKey(name)) {
            StringBuilder string = new StringBuilder();
            for (int i = 0; i < membersList.get(name).size(); i++) {
                string.append(membersList.get(name).get(i));
                if (i == membersList.get(name).size() - 1) {
                    string.append(".");
                } else {
                    string.append(", ");
                }
            }
            return name + " address is " + string.toString();
        } else {
            throw new IllegalArgumentException("This name isn't member");
        }
    }

    public String getMemberByStreet(String street) {
        ArrayList<String> membersOnTheStreet = new ArrayList<String>();
        for (Map.Entry<String, ArrayList<String>> entry : membersList.entrySet()) {
            if (Objects.equals(entry.getValue().get(0), street)) {
                membersOnTheStreet.add(entry.getKey());
            }
        }

        if (membersOnTheStreet.isEmpty()) {
            throw new IllegalArgumentException("No members on this street");
        }

        StringBuilder string = new StringBuilder();
        string.append("People on ").append(street).append(" street: ");
        for (int i = 0; i < membersOnTheStreet.size(); i++) {
            string.append(membersOnTheStreet.get(i));
            if (i == membersOnTheStreet.size() - 1) {
                string.append(".");
            } else {
                string.append(", ");
            }
        }
        return string.toString();
    }

    public String getMemberByStreetAndHouse(String street, String house) {
        ArrayList<String> membersOnTheStreet = new ArrayList<String>();
        for (Map.Entry<String, ArrayList<String>> entry : membersList.entrySet()) {
            if (Objects.equals(entry.getValue().get(0), street) && Objects.equals(entry.getValue().get(1), house)) {
                membersOnTheStreet.add(entry.getKey());
            }
        }

        if (membersOnTheStreet.isEmpty()) {
            throw new IllegalArgumentException("No members on this street and house");
        }

        StringBuilder string = new StringBuilder();
        string.append("People on ").append(street).append(" street and ").append(house).append(" house: ");
        for (int i = 0; i < membersOnTheStreet.size(); i++) {
            string.append(membersOnTheStreet.get(i));
            if (i == membersOnTheStreet.size() - 1) {
                string.append(".");
            } else {
                string.append(", ");
            }
        }
        return string.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (Map.Entry<String, ArrayList<String>> entry : membersList.entrySet()) {
            string.append(entry.getKey()).append(" address is ").append(entry.getValue().get(0)).append(", ")
                    .append(entry.getValue().get(1)).append(", ").append(entry.getValue().get(2)).append(".\n");
        }
        return string.toString();
    }
}

