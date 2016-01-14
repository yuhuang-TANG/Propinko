package com.example.tangyuhuang.propinko;

/**
 * Created by guillaumehoudayer on 16/12/15.
 */
public class Product {
    private String id, name, category, ownerId, visibility, photoExtension, description, price;

    Product(String id, String name, String category, String ownerId, String visibility, String photoExtension, String description, String price){
        this.id = id;
        this.name = name;
        this.category = category;
        this.ownerId = ownerId;
        this.visibility = visibility;
        this.photoExtension = photoExtension;
        this.description = description;
        this.price = price;
    }

    public String toString()
        {
            return id + "_" + name + "_" + category + "_" + ownerId + "_" + visibility + "_" + photoExtension + "_" + description + "_" +price;
        }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getCategory(){
        return category;
    }

    public String getOwnerId(){
        return ownerId;
    }

    public String getVisibility(){
        return visibility;
    }

    public String getPhotoExtension(){
        return photoExtension;
    }

    public String getDescription(){
        return description;
    }

    public String getPrice(){
        return price;
    }

}
