package entity.bean;

/**
 * News entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Ucollect implements java.io.Serializable {

	// Fields

     private Long id; 
     private String tablename; 
     private String pid; 
     private String uname; 
     private String ptitle; 
     private String sysuserkey; 


	// Constructors

	/** default constructor */
	public Ucollect() {
	}

	/** minimal constructor */
	public Ucollect(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Ucollect(
    Long id, 
    String tablename, 
    String pid, 
    String uname, 
    String ptitle, 
    String sysuserkey ) 
	{
     this.id = id; 
     this.tablename = tablename; 
     this.pid = pid; 
     this.uname = uname; 
     this.ptitle = ptitle; 
     this.sysuserkey = sysuserkey; 
	}

	// Property accessors

		public Long getId() {
			return this.id;
		}

		public void setId(Long id) {
			this.id = id;
		}

    public String getTablename(){ 
       return this.tablename; 
    } 

    public void setTablename(String tablename){ 
        this.tablename=tablename; 
    } 

    public String getPid(){ 
       return this.pid; 
    } 

    public void setPid(String pid){ 
        this.pid=pid; 
    } 

    public String getUname(){ 
       return this.uname; 
    } 

    public void setUname(String uname){ 
        this.uname=uname; 
    } 

    public String getPtitle(){ 
       return this.ptitle; 
    } 

    public void setPtitle(String ptitle){ 
        this.ptitle=ptitle; 
    } 

    public String getSysuserkey(){ 
       return this.sysuserkey; 
    } 

    public void setSysuserkey(String sysuserkey){ 
        this.sysuserkey=sysuserkey; 
    } 



}