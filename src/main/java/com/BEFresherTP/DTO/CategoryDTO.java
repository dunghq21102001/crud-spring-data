package com.BEFresherTP.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryDTO {
    private int categoryID;
    @NotBlank(message = "Category name can't be blank")
    @Size(min = 3, max = 255, message = "Cateogory name should be between 3 and 255 characters")
    private String name;

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
