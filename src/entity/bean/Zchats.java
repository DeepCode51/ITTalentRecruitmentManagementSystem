package entity.bean;

/**
 * News entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Zchats implements java.io.Serializable {

	// Fields

     private Long id; 
     private String uname; 
     private String touname; 
     private String tcontent; 
     private String savetime; 
     private String pid; 
     private String tounamekey; 
     private String sysuserkey; 


	// Constructors

	/** default constructor */
	public Zchats() {
	}

	/** minimal constructor */
	public Zchats(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Zchats(
    Long id, 
    String uname, 
    String touname, 
    String tcontent, 
    String savetime, 
    String pid, 
    String tounamekey, 
    String sysuserkey ) 
	{
     this.id = id; 
     this.uname = uname; 
     this.touname = touname; 
     this.tcontent = tcontent; 
     this.savetime = savetime; 
     this.pid = pid; 
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

    public String getTcontent(){ 
       return this.tcontent; 
    } 

    public void setTcontent(String tcontent){ 
        this.tcontent=tcontent; 
    } 

    public String getSavetime(){ 
       return this.savetime; 
    } 

    public void setSavetime(String savetime){ 
        this.savetime=savetime; 
    } 

    public String getPid(){ 
       return this.pid; 
    } 

    public void setPid(String pid){ 
        this.pid=pid; 
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