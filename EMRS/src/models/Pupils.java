package models;

public class Pupils {
	
	private long id;
	private long vid;
	private boolean isBothPupilsNormal;
	private String bothShape;
	private int bothDiameter;
	private boolean isBothRAPD;
	private boolean isBothSynechia;
	private boolean isRightPupilNormal;
	private String rightShape;
	private int rightDiameter;
	private boolean isRightRAPD;
	private boolean isRightSynechia;
	private boolean isLeftPupilNormal;
	private String leftShape;
	private int leftDiameter;
	private boolean isLeftRAPD;
	private boolean isLeftSynechia;
	public Pupils(long id, long vid, boolean isBothPupilsNormal, String bothShape, int bothDiameter, boolean isBothRAPD,
			boolean isBothSynechia, boolean isRightPupilNormal, String rightShape, int rightDiameter,
			boolean isRightRAPD, boolean isRightSynechia, boolean isLeftPupilNormal, String leftShape, int leftDiameter,
			boolean isLeftRAPD, boolean isLeftSynechia) {
		super();
		this.id = id;
		this.vid = vid;
		this.isBothPupilsNormal = isBothPupilsNormal;
		this.bothShape = bothShape;
		this.bothDiameter = bothDiameter;
		this.isBothRAPD = isBothRAPD;
		this.isBothSynechia = isBothSynechia;
		this.isRightPupilNormal = isRightPupilNormal;
		this.rightShape = rightShape;
		this.rightDiameter = rightDiameter;
		this.isRightRAPD = isRightRAPD;
		this.isRightSynechia = isRightSynechia;
		this.isLeftPupilNormal = isLeftPupilNormal;
		this.leftShape = leftShape;
		this.leftDiameter = leftDiameter;
		this.isLeftRAPD = isLeftRAPD;
		this.isLeftSynechia = isLeftSynechia;
	}
	public Pupils(boolean isBothPupilsNormal, String bothShape, int bothDiameter, boolean isBothRAPD,
			boolean isBothSynechia, boolean isRightPupilNormal, String rightShape, int rightDiameter,
			boolean isRightRAPD, boolean isRightSynechia, boolean isLeftPupilNormal, String leftShape, int leftDiameter,
			boolean isLeftRAPD, boolean isLeftSynechia) {
		super();
		this.isBothPupilsNormal = isBothPupilsNormal;
		this.bothShape = bothShape;
		this.bothDiameter = bothDiameter;
		this.isBothRAPD = isBothRAPD;
		this.isBothSynechia = isBothSynechia;
		this.isRightPupilNormal = isRightPupilNormal;
		this.rightShape = rightShape;
		this.rightDiameter = rightDiameter;
		this.isRightRAPD = isRightRAPD;
		this.isRightSynechia = isRightSynechia;
		this.isLeftPupilNormal = isLeftPupilNormal;
		this.leftShape = leftShape;
		this.leftDiameter = leftDiameter;
		this.isLeftRAPD = isLeftRAPD;
		this.isLeftSynechia = isLeftSynechia;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getVid() {
		return vid;
	}
	public void setVid(long vid) {
		this.vid = vid;
	}
	public boolean isBothPupilsNormal() {
		return isBothPupilsNormal;
	}
	public void setBothPupilsNormal(boolean isBothPupilsNormal) {
		this.isBothPupilsNormal = isBothPupilsNormal;
	}
	public String getBothShape() {
		return bothShape;
	}
	public void setBothShape(String bothShape) {
		this.bothShape = bothShape;
	}
	public int getBothDiameter() {
		return bothDiameter;
	}
	public void setBothDiameter(int bothDiameter) {
		this.bothDiameter = bothDiameter;
	}
	public boolean isBothRAPD() {
		return isBothRAPD;
	}
	public void setBothRAPD(boolean isBothRAPD) {
		this.isBothRAPD = isBothRAPD;
	}
	public boolean isBothSynechia() {
		return isBothSynechia;
	}
	public void setBothSynechia(boolean isBothSynechia) {
		this.isBothSynechia = isBothSynechia;
	}
	public boolean isRightPupilNormal() {
		return isRightPupilNormal;
	}
	public void setRightPupilNormal(boolean isRightPupilNormal) {
		this.isRightPupilNormal = isRightPupilNormal;
	}
	public String getRightShape() {
		return rightShape;
	}
	public void setRightShape(String rightShape) {
		this.rightShape = rightShape;
	}
	public int getRightDiameter() {
		return rightDiameter;
	}
	public void setRightDiameter(int rightDiameter) {
		this.rightDiameter = rightDiameter;
	}
	public boolean isRightRAPD() {
		return isRightRAPD;
	}
	public void setRightRAPD(boolean isRightRAPD) {
		this.isRightRAPD = isRightRAPD;
	}
	public boolean isRightSynechia() {
		return isRightSynechia;
	}
	public void setRightSynechia(boolean isRightSynechia) {
		this.isRightSynechia = isRightSynechia;
	}
	public boolean isLeftPupilNormal() {
		return isLeftPupilNormal;
	}
	public void setLeftPupilNormal(boolean isLeftPupilNormal) {
		this.isLeftPupilNormal = isLeftPupilNormal;
	}
	public String getLeftShape() {
		return leftShape;
	}
	public void setLeftShape(String leftShape) {
		this.leftShape = leftShape;
	}
	public int getLeftDiameter() {
		return leftDiameter;
	}
	public void setLeftDiameter(int leftDiameter) {
		this.leftDiameter = leftDiameter;
	}
	public boolean isLeftRAPD() {
		return isLeftRAPD;
	}
	public void setLeftRAPD(boolean isLeftRAPD) {
		this.isLeftRAPD = isLeftRAPD;
	}
	public boolean isLeftSynechia() {
		return isLeftSynechia;
	}
	public void setLeftSynechia(boolean isLeftSynechia) {
		this.isLeftSynechia = isLeftSynechia;
	}
	
}
