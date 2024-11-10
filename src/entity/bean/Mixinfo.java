package entity.bean;

/**
 * News entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Mixinfo implements java.io.Serializable {

	// Fields

     private Long id; 
     private String mtitle; 
     private String cotitle; 
     private String content; 
     private String infotype; 
     private String filename; 
     private String remo; 


	// Constructors

	/** default constructor */
	public Mixinfo() {
	}

	/** minimal constructor */
	public Mixinfo(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Mixinfo(
    Long id, 
    String mtitle, 
    String cotitle, 
    String content, 
    String infotype, 
    String filename, 
    String remo ) 
	{
     this.id = id; 
     this.mtitle = mtitle; 
     this.cotitle = cotitle; 
     this.content = content; 
     this.infotype = infotype; 
     this.filename = filename; 
     this.remo = remo; 
	}

	// Property accessors

		public Long getId() {
			return this.id;
		}

		public void setId(Long id) {
			this.id = id;
		}

    public String getMtitle(){ 
       return this.mtitle; 
    } 

    public void setMtitle(String mtitle){ 
        this.mtitle=mtitle; 
    } 

    public String getCotitle(){ 
       return this.cotitle; 
    } 

    public void setCotitle(String cotitle){ 
        this.cotitle=cotitle; 
    } 

    public String getContent(){ 
       return this.content; 
    } 

    public void setContent(String content){ 
        this.content=content; 
    } 

    public String getInfotype(){ 
       return this.infotype; 
    } 

    public void setInfotype(String infotype){ 
        this.infotype=infotype; 
    } 

    public String getFilename(){ 
       return this.filename; 
    } 

    public void setFilename(String filename){ 
        this.filename=filename; 
    } 

    public String getRemo(){ 
       return this.remo; 
    } 

    public void setRemo(String remo){ 
        this.remo=remo; 
    } 



}