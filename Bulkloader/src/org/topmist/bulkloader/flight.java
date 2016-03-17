package org.topmist.bulkloader;
import java.util.Date;

public class flight {

	private int ID;
	private int YEAR;
	private int DAY_OF_MONTH;
	private Date FL_DATE;
	private int AIRLINE_ID;
	private String CARRIER;
	private int FL_NUM;
	private int ORIGIN_AIRPORT_ID;
	private String ORIGIN;
	private String ORIGIN_CITY_NAME;
	private String ORIGIN_STATE_ABR;
	private String DEST;
	private String DEST_CITY_NAME;
	private String DEST_STATE_ABR;
	private Date DEP_TIME;
	private Date ARR_TIME;
	private Date ACTUAL_ELAPSED_TIME;
	private Date AIR_TIME;
	private int DISTANCE;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getYEAR() {
		return YEAR;
	}

	public void setYEAR(int yEAR) {
		YEAR = yEAR;
	}

	public int getDAY_OF_MONTH() {
		return DAY_OF_MONTH;
	}

	public void setDAY_OF_MONTH(int dAY_OF_MONTH) {
		DAY_OF_MONTH = dAY_OF_MONTH;
	}

	public Date getFL_DATE() {
		return FL_DATE;
	}

	public void setFL_DATE(Date fL_DATE) {
		FL_DATE = fL_DATE;
	}

	public int getAIRLINE_ID() {
		return AIRLINE_ID;
	}

	public void setAIRLINE_ID(int aIRLINE_ID) {
		AIRLINE_ID = aIRLINE_ID;
	}

	public String getCARRIER() {
		return CARRIER;
	}

	public void setCARRIER(String cARRIER) {
		CARRIER = cARRIER;
	}

	public int getFL_NUM() {
		return FL_NUM;
	}

	public void setFL_NUM(int fL_NUM) {
		FL_NUM = fL_NUM;
	}

	public int getORIGIN_AIRPORT_ID() {
		return ORIGIN_AIRPORT_ID;
	}

	public void setORIGIN_AIRPORT_ID(int oRIGIN_AIRPORT_ID) {
		ORIGIN_AIRPORT_ID = oRIGIN_AIRPORT_ID;
	}

	public String getORIGIN() {
		return ORIGIN;
	}

	public void setORIGIN(String oRIGIN) {
		ORIGIN = oRIGIN;
	}

	public String getORIGIN_CITY_NAME() {
		return ORIGIN_CITY_NAME;
	}

	public void setORIGIN_CITY_NAME(String oRIGIN_CITY_NAME) {
		ORIGIN_CITY_NAME = oRIGIN_CITY_NAME;
	}

	public String getORIGIN_STATE_ABR() {
		return ORIGIN_STATE_ABR;
	}

	public void setORIGIN_STATE_ABR(String oRIGIN_STATE_ABR) {
		ORIGIN_STATE_ABR = oRIGIN_STATE_ABR;
	}

	public String getDEST() {
		return DEST;
	}

	public void setDEST(String dEST) {
		DEST = dEST;
	}

	public String getDEST_CITY_NAME() {
		return DEST_CITY_NAME;
	}

	public void setDEST_CITY_NAME(String dEST_CITY_NAME) {
		DEST_CITY_NAME = dEST_CITY_NAME;
	}

	public String getDEST_STATE_ABR() {
		return DEST_STATE_ABR;
	}

	public void setDEST_STATE_ABR(String dEST_STATE_ABR) {
		DEST_STATE_ABR = dEST_STATE_ABR;
	}

	public Date getDEP_TIME() {
		return DEP_TIME;
	}

	public void setDEP_TIME(Date dEP_TIME) {
		DEP_TIME = dEP_TIME;
	}

	public Date getARR_TIME() {
		return ARR_TIME;
	}

	public void setARR_TIME(Date aRR_TIME) {
		ARR_TIME = aRR_TIME;
	}

	public Date getACTUAL_ELAPSED_TIME() {
		return ACTUAL_ELAPSED_TIME;
	}

	public void setACTUAL_ELAPSED_TIME(Date aCTUAL_ELAPSED_TIME) {
		ACTUAL_ELAPSED_TIME = aCTUAL_ELAPSED_TIME;
	}

	public Date getAIR_TIME() {
		return AIR_TIME;
	}

	public void setAIR_TIME(Date aIR_TIME) {
		AIR_TIME = aIR_TIME;
	}

	public int getDISTANCE() {
		return DISTANCE;
	}

	public void setDISTANCE(int dISTANCE) {
		DISTANCE = dISTANCE;
	}

}
