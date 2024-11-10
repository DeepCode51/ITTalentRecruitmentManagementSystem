package entity.bean;

/**
 * News entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Ztype implements java.io.Serializable {

	// Fields

     private Long id; 
     private String zname; 


	// Constructors

	/** default constructor */
	public Ztype() {
	}

	/** minimal constructor */
	public Ztype(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Ztype(
    Long id, 
    String zname ) 
	{
     this.id = id; 
     this.zname = zname; 
	}

	// Property accessors

		public Long getId() {
			return this.id;
		}

		public void setId(Long id) {
			this.id = id;
		}

    public String getZname(){ 
       return this.zname; 
    } 

    public void setZname(String zname){ 
        this.zname=zname; 
    } 



}