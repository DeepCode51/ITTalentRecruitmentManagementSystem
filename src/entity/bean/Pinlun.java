package entity.bean;

/**
 * News entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Pinlun implements java.io.Serializable {

	// Fields

     private Long id; 
     private String saver; 
     private String content; 
     private String pid; 
     private String tablename; 
     private String infotitle; 
     private String savetime; 
     private String pf; 
     private String remo; 


	// Constructors

	/** default constructor */
	public Pinlun() {
	}

	/** minimal constructor */
	public Pinlun(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Pinlun(
    Long id, 
    String saver, 
    String content, 
    String pid, 
    String tablename, 
    String infotitle, 
    String savetime, 
    String pf, 
    String remo ) 
	{
     this.id = id; 
     this.saver = saver; 
     this.content = content; 
     this.pid = pid; 
     this.tablename = tablename; 
     this.infotitle = infotitle; 
     this.savetime = savetime; 
     this.pf = pf; 
     this.remo = remo; 
	}

	// Property accessors

		public Long getId() {
			return this.id;
		}

		public void setId(Long id) {
			this.id = id;
		}

    public String getSaver(){ 
       return this.saver; 
    } 

    public void setSaver(String saver){ 
        this.saver=saver; 
    } 

    public String getContent(){ 
       return this.content; 
    } 

    public void setContent(String content){ 
        this.content=content; 
    } 

    public String getPid(){ 
       return this.pid; 
    } 

    public void setPid(String pid){ 
        this.pid=pid; 
    } 

    public String getTablename(){ 
       return this.tablename; 
    } 

    public void setTablename(String tablename){ 
        this.tablename=tablename; 
    } 

    public String getInfotitle(){ 
       return this.infotitle; 
    } 

    public void setInfotitle(String infotitle){ 
        this.infotitle=infotitle; 
    } 

    public String getSavetime(){ 
       return this.savetime; 
    } 

    public void setSavetime(String savetime){ 
        this.savetime=savetime; 
    } 

    public String getPf(){ 
       return this.pf; 
    } 

    public void setPf(String pf){ 
        this.pf=pf; 
    } 

    public String getRemo(){ 
       return this.remo; 
    } 

    public void setRemo(String remo){ 
        this.remo=remo; 
    } 



}