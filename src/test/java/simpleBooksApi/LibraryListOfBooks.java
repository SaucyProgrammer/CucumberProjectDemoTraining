package simpleBooksApi;

public class LibraryListOfBooks {

    private Integer id;
    private String name;
    private String type;
    private Boolean available;

    public LibraryListOfBooks() {
    }

    public LibraryListOfBooks(Integer id ,String name ,String type ,boolean available) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean isAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }


    public String bookInformation() {
        return "LibraryListOfBooks{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", available=" + available +
                '}';
    }
}
