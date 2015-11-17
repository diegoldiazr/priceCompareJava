package main.utils;


public class OrderingBean {

	private String orderBy;
	
	public OrderingBean(String orderBy) {
		super();

				
		try {
			if (orderBy==null){
				this.setOrderBy(null);
			}else{
				this.setOrderBy(orderBy.trim());	
			}
			
		} catch (Exception e) {
			this.setOrderBy(null);
		}
	}

	
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

}
