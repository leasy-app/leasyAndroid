package com.leasy.leasyAndroid.model;

public class ReadPostItem {

    public static enum TypeEnum {
        Text,
        Image
//        Youtube,
    }

    private TypeEnum type;
    private int id;

     protected ReadPostItem(int id, TypeEnum type){
        this.id = id;
        this.type = type;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class ReadPostItemText extends ReadPostItem{

        private String text;

        public ReadPostItemText(int id, String text) {
            super(id, TypeEnum.Text);
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class ReadPostItemImage extends ReadPostItem{
        private String imageURL;

        public ReadPostItemImage(int id, String imageURL) {
            super(id, TypeEnum.Image);
            this.imageURL = imageURL;
        }

        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }
    }

//    public static class ReadPostItemYoutube extends ReadPostItem{
//        private String youtubeID;
//
//        public ReadPostItemYoutube(int id, String youtubeID) {
//            super(id, TypeEnum.Youtube);
//            this.youtubeID = youtubeID;
//        }
//
//        public String getYoutubeID() {
//            return youtubeID;
//        }
//
//        public void setYoutubeID(String youtubeID) {
//            this.youtubeID = youtubeID;
//        }
//    }
}

