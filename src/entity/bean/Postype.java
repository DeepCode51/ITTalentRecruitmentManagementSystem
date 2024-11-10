package entity.bean;

/**
 * News entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Postype implements java.io.Serializable {

	// Fields

     private Long id; 
     private String datashowname; 
     private String tglparentid; 


	// Constructors

	/** default constructor */
	public Postype() {
	}

	/** minimal constructor */
	public Postype(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Postype(
    Long id, 
    String datashowname, 
    String tglparentid ) 
	{
     this.id = id; 
     this.datashowname = datashowname; 
     this.tglparentid = tglparentid; 
	}

	// Property accessors

		public Long getId() {
			return this.id;
		}

		public void setId(Long id) {
			this.id = id;
		}

    public String getDatashowname(){ 
       return this.datashowname; 
    } 

    public void setDatashowname(String datashowname){ 
        this.datashowname=datashowname; 
    } 

    public String getTglparentid(){ 
       return this.tglparentid; 
    } 

    public void setTglparentid(String tglparentid){ 
        this.tglparentid=tglparentid; 
    } 



}