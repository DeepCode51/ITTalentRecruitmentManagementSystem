package entity.bean;

/**
 * News entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Messages implements java.io.Serializable {

	// Fields

     private Long id; 
     private String uname; 
     private String filename; 
     private String cont; 
     private String recont; 
     private String savetime; 


	// Constructors

	/** default constructor */
	public Messages() {
	}

	/** minimal constructor */
	public Messages(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Messages(
    Long id, 
    String uname, 
    String filename, 
    String cont, 
    String recont, 
    String savetime ) 
	{
     this.id = id; 
     this.uname = uname; 
     this.filename = filename; 
     this.cont = cont; 
     this.recont = recont; 
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

    public String getFilename(){ 
       return this.filename; 
    } 

    public void setFilename(String filename){ 
        this.filename=filename; 
    } 

    public String getCont(){ 
       return this.cont; 
    } 

    public void setCont(String cont){ 
        this.cont=cont; 
    } 

    public String getRecont(){ 
       return this.recont; 
    } 

    public void setRecont(String recont){ 
        this.recont=recont; 
    } 

    public String getSavetime(){ 
       return this.savetime; 
    } 

    public void setSavetime(String savetime){ 
        this.savetime=savetime; 
    } 



}