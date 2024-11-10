package entity.bean;

/**
 * News entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Smail implements java.io.Serializable {

	// Fields

     private Long id; 
     private String uname; 
     private String touname; 
     private String title; 
     private String content; 
     private String docname; 
     private String fshremo; 
     private String savetime; 
     private String tounamekey; 
     private String sysuserkey; 


	// Constructors

	/** default constructor */
	public Smail() {
	}

	/** minimal constructor */
	public Smail(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Smail(
    Long id, 
    String uname, 
    String touname, 
    String title, 
    String content, 
    String docname, 
    String fshremo, 
    String savetime, 
    String tounamekey, 
    String sysuserkey ) 
	{
     this.id = id; 
     this.uname = uname; 
     this.touname = touname; 
     this.title = title; 
     this.content = content; 
     this.docname = docname; 
     this.fshremo = fshremo; 
     this.savetime = savetime; 
     this.tounamekey = tounamekey; 
     this.sysuserkey = sysuserkey; 
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

    public String getTouname(){ 
       return this.touname; 
    } 

    public void setTouname(String touname){ 
        this.touname=touname; 
    } 

    public String getTitle(){ 
       return this.title; 
    } 

    public void setTitle(String title){ 
        this.title=title; 
    } 

    public String getContent(){ 
       return this.content; 
    } 

    public void setContent(String content){ 
        this.content=content; 
    } 

    public String getDocname(){ 
       return this.docname; 
    } 

    public void setDocname(String docname){ 
        this.docname=docname; 
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

    public String getTounamekey(){ 
       return this.tounamekey; 
    } 

    public void setTounamekey(String tounamekey){ 
        this.tounamekey=tounamekey; 
    } 

    public String getSysuserkey(){ 
       return this.sysuserkey; 
    } 

    public void setSysuserkey(String sysuserkey){ 
        this.sysuserkey=sysuserkey; 
    } 



}