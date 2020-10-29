package com.random.login_app.HelperClasses.HomeAdapter;

public class featuredHelperClass {
    // int because image is in the resources and it can be fetch with the int
    int image;
    String title, description;

    public featuredHelperClass(int image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
