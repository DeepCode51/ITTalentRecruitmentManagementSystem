package entity.bean;

/**
 * News entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Zresources implements java.io.Serializable {

	// Fields

     private Long id; 
     private String ntitle; 
     private String btype; 
     private String stype; 
     private String author; 
     private String nfrom; 
     private String keywords; 
     private String filename; 
     private String docname2; 
     private String docname3; 
     private String content; 
     private String uname; 
     private String savetime; 
     private String sysuserkey; 
     private String stypekey; 
     private String ztypekey; 
     private String btypekey; 
     private String clicknums; 


	// Constructors

	/** default constructor */
	public Zresources() {
	}

	/** minimal constructor */
	public Zresources(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Zresources(
    Long id, 
    String ntitle, 
    String btype, 
    String stype, 
    String author, 
    String nfrom, 
    String keywords, 
    String filename, 
    String docname2, 
    String docname3, 
    String content, 
    String uname, 
    String savetime, 
    String sysuserkey, 
    String stypekey, 
    String ztypekey, 
    String btypekey, 
    String clicknums ) 
	{
     this.id = id; 
     this.ntitle = ntitle; 
     this.btype = btype; 
     this.stype = stype; 
     this.author = author; 
     this.nfrom = nfrom; 
     this.keywords = keywords; 
     this.filename = filename; 
     this.docname2 = docname2; 
     this.docname3 = docname3; 
     this.content = content; 
     this.uname = uname; 
     this.savetime = savetime; 
     this.sysuserkey = sysuserkey; 
     this.stypekey = stypekey; 
     this.ztypekey = ztypekey; 
     this.btypekey = btypekey; 
     this.clicknums = clicknums; 
	}

	// Property accessors

		public Long getId() {
			return this.id;
		}

		public void setId(Long id) {
			this.id = id;
		}

    public String getNtitle(){ 
       return this.ntitle; 
    } 

    public void setNtitle(String ntitle){ 
        this.ntitle=ntitle; 
    } 

    public String getBtype(){ 
       return this.btype; 
    } 

    public void setBtype(String btype){ 
        this.btype=btype; 
    } 

    public String getStype(){ 
       return this.stype; 
    } 

    public void setStype(String stype){ 
        this.stype=stype; 
    } 

    public String getAuthor(){ 
       return this.author; 
    } 

    public void setAuthor(String author){ 
        this.author=author; 
    } 

    public String getNfrom(){ 
       return this.nfrom; 
    } 

    public void setNfrom(String nfrom){ 
        this.nfrom=nfrom; 
    } 

    public String getKeywords(){ 
       return this.keywords; 
    } 

    public void setKeywords(String keywords){ 
        this.keywords=keywords; 
    } 

    public String getFilename(){ 
       return this.filename; 
    } 

    public void setFilename(String filename){ 
        this.filename=filename; 
    } 

    public String getDocname2(){ 
       return this.docname2; 
    } 

    public void setDocname2(String docname2){ 
        this.docname2=docname2; 
    } 

    public String getDocname3(){ 
       return this.docname3; 
    } 

    public void setDocname3(String docname3){ 
        this.docname3=docname3; 
    } 

    public String getContent(){ 
       return this.content; 
    } 

    public void setContent(String content){ 
        this.content=content; 
    } 

    public String getUname(){ 
       return this.uname; 
    } 

    public void setUname(String uname){ 
        this.uname=uname; 
    } 

    public String getSavetime(){ 
       return this.savetime; 
    } 

    public void setSavetime(String savetime){ 
        this.savetime=savetime; 
    } 

    public String getSysuserkey(){ 
       return this.sysuserkey; 
    } 

    public void setSysuserkey(String sysuserkey){ 
        this.sysuserkey=sysuserkey; 
    } 

    public String getStypekey(){ 
       return this.stypekey; 
    } 

    public void setStypekey(String stypekey){ 
        this.stypekey=stypekey; 
    } 

    public String getZtypekey(){ 
       return this.ztypekey; 
    } 

    public void setZtypekey(String ztypekey){ 
        this.ztypekey=ztypekey; 
    } 

    public String getBtypekey(){ 
       return this.btypekey; 
    } 

    public void setBtypekey(String btypekey){ 
        this.btypekey=btypekey; 
    } 

    public String getClicknums(){ 
       return this.clicknums; 
    } 

    public void setClicknums(String clicknums){ 
        this.clicknums=clicknums; 
    } 



}