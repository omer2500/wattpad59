package wattpad.backend.objects;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
    public BookInfo(String id, String name, String description, byte[] image, String content, String wattpadId) {
        this.id = id;
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

    /*private String generateID(){
        return "book_" + System.currentTimeMillis();
    }*/

    public BookInfo(){
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


    public JSONObject toJson() {

        JSONObject iObj = new JSONObject();
        iObj.put("id", getId());
        iObj.put("name", getName());
        iObj.put("description", getDescription());
        iObj.put("content", getContent());
        iObj.put("image", getImage());
        iObj.put("wattpadId", getWattpadId());
        //iObj.put("img", isImageExists());

        return iObj;
    }

//	private boolean isImageExists() {
//		if (image == null || image.length == 0) {
//			return false;
//		}
//		return true;
//	}

    public static String toJson(List<BookInfo> list) {

        JSONObject jsonObj = new JSONObject();

        if (list == null) {
            return null;
        }

        if (list.size() == 0) {
            return null;
        }

        JSONArray jsonArray = new JSONArray();

        for (BookInfo postInfo : list) {

            if (postInfo != null) {

                JSONObject itemObj = postInfo.toJson();

                jsonArray.add(itemObj);
            }

        }

        jsonObj.put("books", jsonArray);

        return jsonObj.toString(2);
    }



}
