package entity.bean;

/**
 * News entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Zrtypes implements java.io.Serializable {

	// Fields

     private Long id; 
     private String tname; 


	// Constructors

	/** default constructor */
	public Zrtypes() {
	}

	/** minimal constructor */
	public Zrtypes(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Zrtypes(
    Long id, 
    String tname ) 
	{
     this.id = id; 
     this.tname = tname; 
	}

	// Property accessors

		public Long getId() {
			return this.id;
		}

		public void setId(Long id) {
			this.id = id;
		}

    public String getTname(){ 
       return this.tname; 
    } 

    public void setTname(String tname){ 
        this.tname=tname; 
    } 



}