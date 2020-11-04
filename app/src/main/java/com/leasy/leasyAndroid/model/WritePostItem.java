package com.leasy.leasyAndroid.model;

public class WritePostItem {
    public static enum WriteItemEnum {
        text,
        heading,
        image,
        code
    }

    private WriteItemEnum postType;

    public WritePostItem(WriteItemEnum postType) {
        this.postType = postType;
    }

    public static class  WritePostItemAddText extends WritePostItem {
        String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public WritePostItemAddText(String text) {
            super(WriteItemEnum.text);
            this.text = text;
        }
        public WritePostItemAddText() {
            super(WriteItemEnum.text);
        }
    }

    public WriteItemEnum getPostType() {
        return postType;
    }

    public void setPostType(WriteItemEnum postType) {
        this.postType = postType;
    }
}
