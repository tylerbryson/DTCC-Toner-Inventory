package application;

public class Toner {
	int printers, minStock, curStock, needed;
	String printerModel, brand, model, order;
	
	public Toner() {
		
	}
	
	public Toner(String printerModel, String brand, String model, int printers, int minStock, int curStock, String order, int needed) {
		super();
		this.printers = printers;
		this.minStock = minStock;
		this.curStock = curStock;
		this.needed = needed;
		this.printerModel = printerModel;
		this.brand = brand;
		this.model = model;
		this.order = order;
	}

	public int getPrinters() {
		return printers;
	}

	public void setPrinters(int printers) {
		this.printers = printers;
	}

	public int getMinStock() {
		return minStock;
	}

	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}

	public int getCurStock() {
		return curStock;
	}

	public void setCurStock(int curStock) {
		this.curStock = curStock;
	}

	public int getNeeded() {
		return needed;
	}

	public void setNeeded(int needed) {
		this.needed = needed;
	}

	public String getPrinterModel() {
		return printerModel;
	}

	public void setPrinterModel(String printerModel) {
		this.printerModel = printerModel;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	//int printers, minStock, curStock, needed;
	//String printerModel, brand, model, order;
	
	public boolean compareParam(String parameter, String value) {
		if(parameter.equals("printers")) {
			try {
				if(this.printers == Integer.parseInt(value))
					return true;
				else
					return false;
			}
			catch(NumberFormatException ex) {
				return false;
			}
		}else if(parameter.equals("minStock")) {
			try {
				if(this.minStock == Integer.parseInt(value))
					return true;
				else
					return false;
			}
			catch(NumberFormatException ex) {
				return false;
			}
		}else if(parameter.equals("curStock")) {
			try {
				if(this.curStock == Integer.parseInt(value))
					return true;
				else
					return false;
			}
			catch(NumberFormatException ex) {
				return false;
			}
		}else if(parameter.equals("needed")) {
			try {
				if(this.needed == Integer.parseInt(value))
					return true;
				else
					return false;
			}
			catch(NumberFormatException ex) {
				return false;
			}
		}else if(parameter.equals("printerModel")) {
			if(this.printerModel.equals(value))
				return true;
			else
				return false;
		}else if(parameter.equals("brand")) {
			if(this.brand.equals(value))
				return true;
			else
				return false;
		}else if(parameter.equals("model")) {
			if(this.model.equals(value))
				return true;
			else
				return false;
		}else if(parameter.equals("order")) {
			if(this.order.equals(value))
				return true;
			else
				return false;
		}else {
			System.out.println("Compare parameter (toner) error: invalid parameter");
			return false;
		}
	}
	
	public String toString() {
		return printerModel + ", " + brand + ", " + model + ", " + printers + ", " + minStock + ", " + curStock + ", " + order + ", " + needed;
	}
}
