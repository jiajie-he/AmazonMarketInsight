package blog.model;

public class Categories {
	protected int categoryId;
	protected String name;
	
	// This constructor can be used for reading records from MySQL, where we have all fields,
	// including the PostId.
	public Categories(int categoryId, String name) {
		this.categoryId = categoryId;
		this.name = name;
	}
	
	// This constructor can be used for reading records from MySQL, where we only have the categoryId,
	// such as a foreign key reference to CategoryId.
	// Given CategoryId, we can fetch the full Category record.
	public Categories(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}