package models;


public class GlassesRx {
	
	private long id;
	private long vid;
	
	private float rx_OD_Sphere;
	private float rx_OD_Cyl;
	private float rx_OD_Axis;
	private float rx_OD_Add;
	private float rx_OS_Sphere;
	private float rx_OS_Cyl;
	private float rx_OS_Axis;
	private float rx_OS_Add;
	private String glassesRxNotes;
		
	public GlassesRx(long id, long vid, float rx_OD_Sphere, float rx_OD_Cyl, float rx_OD_Axis, float rx_OD_Add,
			float rx_OS_Sphere, float rx_OS_Cyl, float rx_OS_Axis, float rx_OS_Add, String glassesRxNotes) {
		super();
		this.id = id;
		this.vid = vid;
		this.rx_OD_Sphere = rx_OD_Sphere;
		this.rx_OD_Cyl = rx_OD_Cyl;
		this.rx_OD_Axis = rx_OD_Axis;
		this.rx_OD_Add = rx_OD_Add;
		this.rx_OS_Sphere = rx_OS_Sphere;
		this.rx_OS_Cyl = rx_OS_Cyl;
		this.rx_OS_Axis = rx_OS_Axis;
		this.rx_OS_Add = rx_OS_Add;
		this.glassesRxNotes = glassesRxNotes;
	}
	
	public GlassesRx(float rx_OD_Sphere, float rx_OD_Cyl, float rx_OD_Axis, float rx_OD_Add,
			float rx_OS_Sphere, float rx_OS_Cyl, float rx_OS_Axis, float rx_OS_Add, String glassesRxNotes) {
		super();
		this.rx_OD_Sphere = rx_OD_Sphere;
		this.rx_OD_Cyl = rx_OD_Cyl;
		this.rx_OD_Axis = rx_OD_Axis;
		this.rx_OD_Add = rx_OD_Add;
		this.rx_OS_Sphere = rx_OS_Sphere;
		this.rx_OS_Cyl = rx_OS_Cyl;
		this.rx_OS_Axis = rx_OS_Axis;
		this.rx_OS_Add = rx_OS_Add;
		this.glassesRxNotes = glassesRxNotes;
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

	public float getRx_OD_Sphere() {
		return rx_OD_Sphere;
	}

	public void setRx_OD_Sphere(float rx_OD_Sphere) {
		this.rx_OD_Sphere = rx_OD_Sphere;
	}

	public float getRx_OD_Cyl() {
		return rx_OD_Cyl;
	}

	public void setRx_OD_Cyl(float rx_OD_Cyl) {
		this.rx_OD_Cyl = rx_OD_Cyl;
	}

	public float getRx_OD_Axis() {
		return rx_OD_Axis;
	}

	public void setRx_OD_Axis(float rx_OD_Axis) {
		this.rx_OD_Axis = rx_OD_Axis;
	}

	public float getRx_OD_Add() {
		return rx_OD_Add;
	}

	public void setRx_OD_Add(float rx_OD_Add) {
		this.rx_OD_Add = rx_OD_Add;
	}

	public float getRx_OS_Sphere() {
		return rx_OS_Sphere;
	}

	public void setRx_OS_Sphere(float rx_OS_Sphere) {
		this.rx_OS_Sphere = rx_OS_Sphere;
	}

	public float getRx_OS_Cyl() {
		return rx_OS_Cyl;
	}

	public void setRx_OS_Cyl(float rx_OS_Cyl) {
		this.rx_OS_Cyl = rx_OS_Cyl;
	}

	public float getRx_OS_Axis() {
		return rx_OS_Axis;
	}

	public void setRx_OS_Axis(float rx_OS_Axis) {
		this.rx_OS_Axis = rx_OS_Axis;
	}

	public float getRx_OS_Add() {
		return rx_OS_Add;
	}

	public void setRx_OS_Add(float rx_OS_Add) {
		this.rx_OS_Add = rx_OS_Add;
	}

	public String getGlassesRxNotes() {
		return glassesRxNotes;
	}

	public void setGlassesRxNotes(String glassesRxNotes) {
		this.glassesRxNotes = glassesRxNotes;
	}
	
	
}
