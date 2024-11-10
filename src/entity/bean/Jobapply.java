package entity.bean;

/**
 * News entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Jobapply implements java.io.Serializable {

	// Fields

     private Long id; 
     private String uname; 
     private String tname; 
     private String fid; 
     private String frtitle; 
     private String fqyuname; 
     private String fqytname; 
     private String applyremo; 
     private String fshstatus; 
     private String fshremo; 
     private String savetime; 
     private String clicknums; 


	// Constructors

	/** default constructor */
	public Jobapply() {
	}

	/** minimal constructor */
	public Jobapply(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Jobapply(
    Long id, 
    String uname, 
    String tname, 
    String fid, 
    String frtitle, 
    String fqyuname, 
    String fqytname, 
    String applyremo, 
    String fshstatus, 
    String fshremo, 
    String savetime, 
    String clicknums ) 
	{
     this.id = id; 
     this.uname = uname; 
     this.tname = tname; 
     this.fid = fid; 
     this.frtitle = frtitle; 
     this.fqyuname = fqyuname; 
     this.fqytname = fqytname; 
     this.applyremo = applyremo; 
     this.fshstatus = fshstatus; 
     this.fshremo = fshremo; 
     this.savetime = savetime; 
     this.clicknums = clicknums; 
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

    public String getFid(){ 
       return this.fid; 
    } 

    public void setFid(String fid){ 
        this.fid=fid; 
    } 

    public String getFrtitle(){ 
       return this.frtitle; 
    } 

    public void setFrtitle(String frtitle){ 
        this.frtitle=frtitle; 
    } 

    public String getFqyuname(){ 
       return this.fqyuname; 
    } 

    public void setFqyuname(String fqyuname){ 
        this.fqyuname=fqyuname; 
    } 

    public String getFqytname(){ 
       return this.fqytname; 
    } 

    public void setFqytname(String fqytname){ 
        this.fqytname=fqytname; 
    } 

    public String getApplyremo(){ 
       return this.applyremo; 
    } 

    public void setApplyremo(String applyremo){ 
        this.applyremo=applyremo; 
    } 

    public String getFshstatus(){ 
       return this.fshstatus; 
    } 

    public void setFshstatus(String fshstatus){ 
        this.fshstatus=fshstatus; 
    } 

    public String getFshremo(){ 
       return this.fshremo; 
    } 

    public void setFshremo(String fshremo){ 
        this.fshremo=fshremo; 
    } 

    public String getSavetime(){ 
       return this.savetime; 
    } 

    public void setSavetime(String savetime){ 
        this.savetime=savetime; 
    } 

    public String getClicknums(){ 
       return this.clicknums; 
    } 

    public void setClicknums(String clicknums){ 
        this.clicknums=clicknums; 
    } 



}