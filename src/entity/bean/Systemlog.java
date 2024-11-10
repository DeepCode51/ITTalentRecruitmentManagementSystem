package entity.bean;

/**
 * News entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Systemlog implements java.io.Serializable {

	// Fields

     private Long id; 
     private String uname; 
     private String tname; 
     private String tinfo; 
     private String savetime; 


	// Constructors

	/** default constructor */
	public Systemlog() {
	}

	/** minimal constructor */
	public Systemlog(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Systemlog(
    Long id, 
    String uname, 
    String tname, 
    String tinfo, 
    String savetime ) 
	{
     this.id = id; 
     this.uname = uname; 
     this.tname = tname; 
     this.tinfo = tinfo; 
     this.savetime = savetime; 
	}

	// Property accessors

		public Long getId() {
			return this.id;
		}

		public void setId(Long id) {
			this.id = id;
		}

    public String getUname(){ 
       return this.uname; 
    } 

    public void setUname(String uname){ 
        this.uname=uname; 
    } 

    public String getTname(){ 
       return this.tname; 
    } 

    public void setTname(String tname){ 
        this.tname=tname; 
    } 

    public String getTinfo(){ 
       return this.tinfo; 
    } 

    public void setTinfo(String tinfo){ 
        this.tinfo=tinfo; 
    } 

    public String getSavetime(){ 
       return this.savetime; 
    } 

    public void setSavetime(String savetime){ 
        this.savetime=savetime; 
    } 



}