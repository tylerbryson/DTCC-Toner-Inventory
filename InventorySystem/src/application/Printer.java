package application;

import javafx.beans.property.SimpleStringProperty;

public class Printer {
	int barCode;
	SimpleStringProperty serialNum, description, category, location, manuName, division, department, campus, status;
	
	public Printer() {
		
	}
	
	public Printer(int barCode, String description, String category, String location, String serialNum, String manuName, String division, String department, String campus, String status) {
		super();
		this.description = new SimpleStringProperty(description);
		this.barCode = barCode;
		this.serialNum = new SimpleStringProperty(serialNum);
		this.category = new SimpleStringProperty(category);
		this.location = new SimpleStringProperty(location);
		this.manuName = new SimpleStringProperty(manuName);
		this.division = new SimpleStringProperty(division);
		this.department = new SimpleStringProperty(department);
		this.campus = new SimpleStringProperty(campus);
		this.status = new SimpleStringProperty(status);
	}
	
	public int getBarCode() {
		return barCode;
	}

	public void setBarCode(int barCode) {
		this.barCode = barCode;
	}

	public String getSerialNum() {
		return serialNum.get();
	}

	public void setSerialNum(SimpleStringProperty serialNum) {
		this.serialNum = serialNum;
	}

	public String getDescription() {
		return description.get();
	}

	public void setDescription(SimpleStringProperty description) {
		this.description = description;
	}

	public String getCategory() {
		return category.get();
	}

	public void setCategory(SimpleStringProperty category) {
		this.category = category;
	}

	public String getLocation() {
		return location.get();
	}

	public void setLocation(SimpleStringProperty location) {
		this.location = location;
	}

	public String getManuName() {
		return manuName.get();
	}

	public void setManuName(SimpleStringProperty manuName) {
		this.manuName = manuName;
	}

	public String getDivision() {
		return division.get();
	}

	public void setDivision(SimpleStringProperty division) {
		this.division = division;
	}

	public String getDepartment() {
		return department.get();
	}

	public void setDepartment(SimpleStringProperty department) {
		this.department = department;
	}

	public String getCampus() {
		return campus.get();
	}

	public void setCampus(SimpleStringProperty campus) {
		this.campus = campus;
	}

	public String getStatus() {
		return status.get();
	}

	public void setStatus(SimpleStringProperty status) {
		this.status = status;
	}

	public boolean compareParam(String parameter, String value) {
		if(parameter.equals("barCode")) {
			try{
				if(this.barCode == Integer.parseInt(value))
					return true;
				else
					return false;
			}
			catch(NumberFormatException ex) {
				return false;
			}
		}else if(parameter.equals("description")) {
			if(this.description.equals(value))
				return true;
			else
				return false;
		}else if(parameter.equals("category")) {
			if(this.category.equals(value))
				return true;
			else
				return false;
		}else if(parameter.equals("location")) {
			if(this.location.equals(value))
				return true;
			else
				return false;
		}else if(parameter.equals("manuName")) {
			if(this.manuName.equals(value))
				return true;
			else
				return false;
		}else if(parameter.equals("division")) {
			if(this.division.equals(value))
				return true;
			else
				return false;
		}else if(parameter.equals("department")) {
			if(this.department.equals(value))
				return true;
			else
				return false;
		}else if(parameter.equals("campus")) {
			if(this.campus.equals(value))
				return true;
			else
				return false;
		}else if(parameter.equals("status")) {
			if(this.status.equals(value))
				return true;
			else
				return false;
		}else {
			System.out.println("Compare parameter (printer) error: invalid parameter");
			return false;
		}
	}
	
	
	public String toString() {
		return barCode + ", " + description + ", " + category + ", " + location + ", " + serialNum + ", " + manuName + ", " + division + ", " + department + ", " + campus + ", " + status;
	}
}
