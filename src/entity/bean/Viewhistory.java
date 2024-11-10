package entity.bean;

/**
 * News entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Viewhistory implements java.io.Serializable {

	// Fields

     private Long id; 
     private String uname; 
     private String tname; 
     private String uid; 
     private String fid; 
     private String ftable; 
     private String ftitle; 
     private String savetime; 


	// Constructors

	/** default constructor */
	public Viewhistory() {
	}

	/** minimal constructor */
	public Viewhistory(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Viewhistory(
    Long id, 
    String uname, 
    String tname, 
    String uid, 
    String fid, 
    String ftable, 
    String ftitle, 
    String savetime ) 
	{
     this.id = id; 
     this.uname = uname; 
     this.tname = tname; 
     this.uid = uid; 
     this.fid = fid; 
     this.ftable = ftable; 
     this.ftitle = ftitle; 
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

    public String getUid(){ 
       return this.uid; 
    } 

    public void setUid(String uid){ 
        this.uid=uid; 
    } 

    public String getFid(){ 
       return this.fid; 
    } 

    public void setFid(String fid){ 
        this.fid=fid; 
    } 

    public String getFtable(){ 
       return this.ftable; 
    } 

    public void setFtable(String ftable){ 
        this.ftable=ftable; 
    } 

    public String getFtitle(){ 
       return this.ftitle; 
    } 

    public void setFtitle(String ftitle){ 
        this.ftitle=ftitle; 
    } 

    public String getSavetime(){ 
       return this.savetime; 
    } 

    public void setSavetime(String savetime){ 
        this.savetime=savetime; 
    } 



}