package blog.model;

public class Products {
    protected String productId;
    protected String title;
    protected String imageUrl;
    protected String productUrl;
    protected double stars;
    protected int reviews;
    protected double price;
    protected double listedPrice;
    protected Integer categoryId;
    protected boolean bestSeller;
    protected int boughtInLastMonth;
    protected int timesPosted;
    protected int numViews;
    protected int likes;
    protected int dislikes;

    public Products() {
    }

    public Products(String productId, String title, String imageUrl, String productUrl, double stars, int reviews,
            double price, double listedPrice, Integer categoryId, boolean bestSeller, int boughtInLastMonth,
            int timesPosted, int numViews, int likes, int dislikes) {
        this.productId = productId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.productUrl = productUrl;
        this.stars = stars;
        this.reviews = reviews;
        this.price = price;
        this.listedPrice = listedPrice;
        this.categoryId = categoryId;
        this.bestSeller = bestSeller;
        this.boughtInLastMonth = boughtInLastMonth;
        this.timesPosted = timesPosted;
        this.numViews = numViews;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    // Getters and Setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getListedPrice() {
        return listedPrice;
    }

    public void setListedPrice(double listedPrice) {
        this.listedPrice = listedPrice;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(boolean bestSeller) {
        this.bestSeller = bestSeller;
    }

    public int getBoughtInLastMonth() {
        return boughtInLastMonth;
    }

    public void setBoughtInLastMonth(int boughtInLastMonth) {
        this.boughtInLastMonth = boughtInLastMonth;
    }

    public int getTimesPosted() {
        return timesPosted;
    }

    public void setTimesPosted(int timesPosted) {
        this.timesPosted = timesPosted;
    }

    public int getNumViews() {
        return numViews;
    }

    public void setNumViews(int numViews) {
        this.numViews = numViews;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
}
