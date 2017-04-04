package ibanezman192.rocknroll;


import static android.R.attr.id;

public class Producer {
    private String name, show, showHours, bio, imageId;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getshowHours() {
        return showHours;
    }

    public void setshowHours(String showHours) {
        this.showHours = showHours;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getimageId() {
        return imageId;
    }

    public void setimageId(String imageId) {
        this.imageId = imageId;
    }

    public Producer(int id, String name, String show, String showHours, String bio, String imageId) {
        this.id=id;
        this.name = name;
        this.show = show;
        this.showHours = showHours;
        this.bio = bio;
        this.imageId = imageId;
    }
}
