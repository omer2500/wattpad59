package wattpad.backend.objects;

/**
 * Created by Yarden-PC on 06-Jan-18.
 */

public class WattpadInfo {

    private String id;
    private String password;

    public WattpadInfo(String id) {
        this.id = id;
    }

    public WattpadInfo(String id, String password) {
        this.id = id;
        this.password = password;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
