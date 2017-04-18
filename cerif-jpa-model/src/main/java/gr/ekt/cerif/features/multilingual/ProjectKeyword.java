/**
 * 
 */
package gr.ekt.cerif.features.multilingual;

import gr.ekt.cerif.entities.base.Project;
import gr.ekt.cerif.entities.second.Language;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * Holds the multi-lingual keyword of a project entity.
 * 
 */
@Entity
@Table(name="cfProjKeyw", uniqueConstraints=@UniqueConstraint(columnNames={"cfProjId","cfLangCode","cfTrans"}))
public class ProjectKeyword implements CerifMultipleLanguageFeature {
	
	/**
	 * Serialization version.
	 */
	private static final long serialVersionUID = -1475010942172770607L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	
	/**
	 * The project.
	 */
	@ManyToOne(optional=false)
	@JoinColumn(name="cfProjId")
	private Project project;
	
	/**
	 * The language.
	 */
	@ManyToOne(optional=false)
	@JoinColumn(name="cfLangCode")
	private Language language;
	
	/**
	 * The translation.
	 */
	@NotNull
	@Column(name="cfTrans")
	@Enumerated(EnumType.STRING)
	private Translation translation;
	
	/**
	 * The project keywords.
	 */
	@NotNull
	@Column(name="cfKeyw", length=20000)
	private String keyword;

	/**
	 * Default Constructor
	 */
	public ProjectKeyword() {
		
	}
	
	/**
	 * 
	 * @param project
	 * @param language
	 * @param translation
	 * @param keyword
	 */
	public ProjectKeyword(Project project, Language language,
			Translation translation, String keyword) {
		this.project = project;
		this.language = language;
		this.translation = translation;
		this.keyword = keyword;
	}

	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * @return the language
	 */
	public Language getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}

	/**
	 * @return the translation
	 */
	public Translation getTranslation() {
		return translation;
	}

	/**
	 * @param translation the translation to set
	 */
	public void setTranslation(Translation translation) {
		this.translation = translation;
	}
	
	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * @param keywords the keywords to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result
				+ ((translation == null) ? 0 : translation.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectKeyword other = (ProjectKeyword) obj;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (translation != other.translation)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProjectKeyword [id=" + id + ", language=" + language
				+ ", translation=" + translation + ", keyword=" + keyword + "]";
	}
	
	
}