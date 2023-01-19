package com.example.completeandroidjavaapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/// @Data gives us kotlin equivalent of data classes,
/// no need to manually write == and toString() methods
@RequiredArgsConstructor
@Data
public class SliderData {
    public String imageUrl;

    /// generally we would have not needed this, but it is not working for now
    public SliderData(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

/// Without @Data annotation, this would be the code
// public class SliderData {
//    private String imageUrl;
//
//    public SliderData(String imageUrl){
//        this.imageUrl = imageUrl;
//    }
//
//    // Getter method
//    public String getImgUrl() {
//        return imageUrl;
//    }
//
//    // Setter method
//    public void setImgUrl(String imgUrl) {
//        this.imageUrl = imgUrl;
//    }
//}
