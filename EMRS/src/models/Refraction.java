package models;

public class Refraction {
	
	private long id;
	private long vid;
	private boolean isManifest;
	private float SC_OD_Sphere;
	private float SC_OD_Cyl;
	private float SC_OD_Axis;
	private float SC_OS_Sphere;
	private float SC_OS_Cyl;
	private float SC_OS_Axis;
	private float CC_OD_Sphere;
	private float CC_OD_Cyl;
	private float CC_OD_Axis;
	private float CC_OS_Sphere;
	private float CC_OS_Cyl;
	private float CC_OS_Axis;
	
	public Refraction(long id, long vid, boolean isManifest, float sC_OD_Sphere, float sC_OD_Cyl, float sC_OD_Axis,
			float sC_OS_Sphere, float sC_OS_Cyl, float sC_OS_Axis, float cC_OD_Sphere, float cC_OD_Cyl,
			float cC_OD_Axis, float cC_OS_Sphere, float cC_OS_Cyl, float cC_OS_Axis) {
		super();
		this.id = id;
		this.vid = vid;
		this.isManifest = isManifest;
		SC_OD_Sphere = sC_OD_Sphere;
		SC_OD_Cyl = sC_OD_Cyl;
		SC_OD_Axis = sC_OD_Axis;
		SC_OS_Sphere = sC_OS_Sphere;
		SC_OS_Cyl = sC_OS_Cyl;
		SC_OS_Axis = sC_OS_Axis;
		CC_OD_Sphere = cC_OD_Sphere;
		CC_OD_Cyl = cC_OD_Cyl;
		CC_OD_Axis = cC_OD_Axis;
		CC_OS_Sphere = cC_OS_Sphere;
		CC_OS_Cyl = cC_OS_Cyl;
		CC_OS_Axis = cC_OS_Axis;
	}
	
	public Refraction(boolean isManifest, float sC_OD_Sphere, float sC_OD_Cyl, float sC_OD_Axis,
			float sC_OS_Sphere, float sC_OS_Cyl, float sC_OS_Axis, float cC_OD_Sphere, float cC_OD_Cyl,
			float cC_OD_Axis, float cC_OS_Sphere, float cC_OS_Cyl, float cC_OS_Axis) {
		super();
		this.isManifest = isManifest;
		SC_OD_Sphere = sC_OD_Sphere;
		SC_OD_Cyl = sC_OD_Cyl;
		SC_OD_Axis = sC_OD_Axis;
		SC_OS_Sphere = sC_OS_Sphere;
		SC_OS_Cyl = sC_OS_Cyl;
		SC_OS_Axis = sC_OS_Axis;
		CC_OD_Sphere = cC_OD_Sphere;
		CC_OD_Cyl = cC_OD_Cyl;
		CC_OD_Axis = cC_OD_Axis;
		CC_OS_Sphere = cC_OS_Sphere;
		CC_OS_Cyl = cC_OS_Cyl;
		CC_OS_Axis = cC_OS_Axis;
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

	public boolean isManifest() {
		return isManifest;
	}

	public void setManifest(boolean isManifest) {
		this.isManifest = isManifest;
	}

	public float getSC_OD_Sphere() {
		return SC_OD_Sphere;
	}

	public void setSC_OD_Sphere(float sC_OD_Sphere) {
		SC_OD_Sphere = sC_OD_Sphere;
	}

	public float getSC_OD_Cyl() {
		return SC_OD_Cyl;
	}

	public void setSC_OD_Cyl(float sC_OD_Cyl) {
		SC_OD_Cyl = sC_OD_Cyl;
	}

	public float getSC_OD_Axis() {
		return SC_OD_Axis;
	}

	public void setSC_OD_Axis(float sC_OD_Axis) {
		SC_OD_Axis = sC_OD_Axis;
	}

	public float getSC_OS_Sphere() {
		return SC_OS_Sphere;
	}

	public void setSC_OS_Sphere(float sC_OS_Sphere) {
		SC_OS_Sphere = sC_OS_Sphere;
	}

	public float getSC_OS_Cyl() {
		return SC_OS_Cyl;
	}

	public void setSC_OS_Cyl(float sC_OS_Cyl) {
		SC_OS_Cyl = sC_OS_Cyl;
	}

	public float getSC_OS_Axis() {
		return SC_OS_Axis;
	}

	public void setSC_OS_Axis(float sC_OS_Axis) {
		SC_OS_Axis = sC_OS_Axis;
	}

	public float getCC_OD_Sphere() {
		return CC_OD_Sphere;
	}

	public void setCC_OD_Sphere(float cC_OD_Sphere) {
		CC_OD_Sphere = cC_OD_Sphere;
	}

	public float getCC_OD_Cyl() {
		return CC_OD_Cyl;
	}

	public void setCC_OD_Cyl(float cC_OD_Cyl) {
		CC_OD_Cyl = cC_OD_Cyl;
	}

	public float getCC_OD_Axis() {
		return CC_OD_Axis;
	}

	public void setCC_OD_Axis(float cC_OD_Axis) {
		CC_OD_Axis = cC_OD_Axis;
	}

	public float getCC_OS_Sphere() {
		return CC_OS_Sphere;
	}

	public void setCC_OS_Sphere(float cC_OS_Sphere) {
		CC_OS_Sphere = cC_OS_Sphere;
	}

	public float getCC_OS_Cyl() {
		return CC_OS_Cyl;
	}

	public void setCC_OS_Cyl(float cC_OS_Cyl) {
		CC_OS_Cyl = cC_OS_Cyl;
	}

	public float getCC_OS_Axis() {
		return CC_OS_Axis;
	}

	public void setCC_OS_Axis(float cC_OS_Axis) {
		CC_OS_Axis = cC_OS_Axis;
	}
	
	
	
}
