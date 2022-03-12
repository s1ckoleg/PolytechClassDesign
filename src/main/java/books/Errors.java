package books;

public enum Errors {
    ILLEGAL_NAME_ERROR("Incorrect name"),
    ILLEGAL_STREET_ERROR("Incorrect street"),
    ILLEGAL_HOUSE_ERROR("Incorrect house"),
    ILLEGAL_FLAT_ERROR("Incorrect flat");

    private final String message;

    Errors(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}
