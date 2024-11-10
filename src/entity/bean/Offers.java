package entity.bean;

/**
 * News entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Offers implements java.io.Serializable {

	// Fields

     private Long id; 
     private String qyuname; 
     private String qytname; 
     private String touname; 
     private String otitle; 
     private String position; 
     private String content; 
     private String docname; 
     private String savetime; 
     private String tounamekey; 
     private String sysuserkey; 


	// Constructors

	/** default constructor */
	public Offers() {
	}

	/** minimal constructor */
	public Offers(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Offers(
    Long id, 
    String qyuname, 
    String qytname, 
    String touname, 
    String otitle, 
    String position, 
    String content, 
    String docname, 
    String savetime, 
    String tounamekey, 
    String sysuserkey ) 
	{
     this.id = id; 
     this.qyuname = qyuname; 
     this.qytname = qytname; 
     this.touname = touname; 
     this.otitle = otitle; 
     this.position = position; 
     this.content = content; 
     this.docname = docname; 
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

    public String getQyuname(){ 
       return this.qyuname; 
    } 

    public void setQyuname(String qyuname){ 
        this.qyuname=qyuname; 
    } 

    public String getQytname(){ 
       return this.qytname; 
    } 

    public void setQytname(String qytname){ 
        this.qytname=qytname; 
    } 

    public String getTouname(){ 
       return this.touname; 
    } 

    public void setTouname(String touname){ 
        this.touname=touname; 
    } 

    public String getOtitle(){ 
       return this.otitle; 
    } 

    public void setOtitle(String otitle){ 
        this.otitle=otitle; 
    } 

    public String getPosition(){ 
       return this.position; 
    } 

    public void setPosition(String position){ 
        this.position=position; 
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