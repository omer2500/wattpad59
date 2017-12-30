package wattpad.backend.core;

/**
 * Created by Yarden-PC on 30-Dec-17.
 */

public class BookInfo {


    private String id;
    private String name;
    private String description;
    private byte[] image;
    private String content;
    String wattpadId;

    //Constructors
    public BookInfo(String name, String description, byte[] image, String content, String wattpadId) {
        this.id = generateID();
        this.name = name;
        this.description = description;
        this.image = image;
        this.content = content;
        this.wattpadId = wattpadId;
    }

    public BookInfo (String id){
        this.id = id;
    }


    //Getters and Setters

    private String generateID(){
        return "book_" + System.currentTimeMillis();
    }

    public BookInfo(){
        this.id = generateID();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getImage() {
        return image;
    }

    public String getContent() {
        return content;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWattpadId() {
        return wattpadId;
    }

    public void setWattpadId(String wattpadId) {
        this.wattpadId = wattpadId;
    }
}
