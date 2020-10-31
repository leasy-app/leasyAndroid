package com.leasy.leasyAndroid.model;

public class ListItems {
    private String sectionTitle;
    private PostItem postItem;

    @Override
    public String toString() {
        return "ListItems{" +
                "sectionTitle='" + sectionTitle + '\'' +
                ", postItem=" + postItem +
                '}';
    }

    public String getSectionTitle() {
        return sectionTitle;
    }

    public void setSectionTitle(String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

    public PostItem getPostItem() {
        return postItem;
    }

    public void setPostItem(PostItem postItem) {
        this.postItem = postItem;
    }

    public static class PostItem {
        private String title;
        private String summary;
        private String author;
        private String date;
        private String postImageURL;
        private String authorImageURL;
        private String category;
        private String id;

        public PostItem() {
        }

        public PostItem(String title, String summary, String author, String date, String postImageURL, String authorImageURL, String category, String id) {
            this.title = title;
            this.summary = summary;
            this.author = author;
            this.date = date;
            this.postImageURL = postImageURL;
            this.authorImageURL = authorImageURL;
            this.category = category;
            this.id = id;
        }

        @Override
        public String toString() {
            return "PostItem{" +
                    "title='" + title + '\'' +
                    ", summary='" + summary + '\'' +
                    ", author='" + author + '\'' +
                    ", date='" + date + '\'' +
                    ", postImageURL='" + postImageURL + '\'' +
                    ", authorImageURL='" + authorImageURL + '\'' +
                    ", category='" + category + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getPostImageURL() {
            return postImageURL;
        }

        public void setPostImageURL(String postImageURL) {
            this.postImageURL = postImageURL;
        }

        public String getAuthorImageURL() {
            return authorImageURL;
        }

        public void setAuthorImageURL(String authorImageURL) {
            this.authorImageURL = authorImageURL;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

}
