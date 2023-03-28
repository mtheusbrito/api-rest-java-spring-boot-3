package med.voll.api.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EntityManager;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.transaction.Transactional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.utils.BeanUtil;
import med.voll.api.utils.GsonUtils;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = "id")
public class Entidade implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@CreatedDate
	@Column(name = "criadoEm", nullable = false, updatable = false)
	private Date criadoEm;
	
	@LastModifiedDate
	private Date modificadoEm;
	
	
	@CreatedBy
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "criadoPor_id")
	private Usuario criadoPor;
	
	@LastModifiedBy
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "modificadoPor_id")
	private Usuario modificadoPor;
	
	
        
	@PrePersist
    public void onPrePersist() {
        audit("INSERT");
    }
     
    @PreUpdate
    public void onPreUpdate() {
        audit("UPDATE");
    }
     
    @PreRemove
    public void onPreRemove() {
        audit("DELETE");
    }

    
    @Transactional
	public void audit(String titulo) {
    	
    	
    	Boolean  instanceofLog =  Log.class.isAssignableFrom(getClass());
			 
		if(!instanceofLog) {
			
			String className = this.getClass().getSimpleName();
    		
	    	String descricao = retornaDescricaoParaLog(this);
	    	System.out.println(titulo + descricao);
	    	
	    	String idObjeto = this.getId() == null ? null : this.getId().toString();
//	    	
	    	Log log = new Log();
	    	log.setDescricao(descricao);
	    	log.setUsuario(this.getModificadoPor());
	    	log.setTitulo(titulo);
	    	log.setDescricao(descricao);
	    	log.setIdObjeto(idObjeto);
	    	log.setClasseObjeto(className);
	    	
	    	EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
	    	entityManager.persist(log);
		}
		
    	
    		
 
    	
		
	}

	private String retornaDescricaoParaLog(Entidade entidade) {
		String descricao = new GsonUtils().paraLog(entidade).toJson(entidade);
	return descricao;
	}
	
//	@Override
//	public String toString() {
//	    StringBuilder sb = new StringBuilder();
//
//	    Class<?> thisClass = null;
//	    try {
//	        thisClass = this.getClass();
//
//	        Field[] aClassFields = thisClass.getDeclaredFields();
//	        sb.append(this.getClass().getSimpleName() + " [ ");
//	        for(Field f : aClassFields){
//	        	f.setAccessible(true);
//	            String fName = f.getName();
//	            sb.append("(" + f.getType() + ") " + fName + " = " + f.get(this) + ", ");
//	        }
//	        sb.append("]");
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
//
//	    return sb.toString();
//	}
    
	
	
	
		
}
