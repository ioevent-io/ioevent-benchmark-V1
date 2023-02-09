package io.ioevent.samples.fileprocessing.domain;


import com.opencsv.bean.CsvBindByPosition;

import io.ioevent.samples.fileprocessing.enums.ProductState;

public class Product {
	@CsvBindByPosition(position = 0)
	private String id;
	@CsvBindByPosition(position = 1)
	private String name;
	@CsvBindByPosition(position = 2)
	private String quantity;
	@CsvBindByPosition(position = 3)
	private String manufacture;
	@CsvBindByPosition(position = 4)
	private String color;
	private ProductState state;

	public Product() {
		super();
	}

	public Product(String id, String name, String quantity, String manufacture, String color) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.manufacture = manufacture;
		this.color = color;
	}

	public Product(String id, String name, String quantity, String manufacture, String color, ProductState state) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.manufacture = manufacture;
		this.color = color;
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public ProductState getState() {
		return state;
	}

	public void setState(ProductState state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", quantity=" + quantity + ", manufacture=" + manufacture
				+ ", color=" + color + ", state=" + state + "]";
	}

}
